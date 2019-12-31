package com.hori.grms.service.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hori.grms.dao.CloseCaseMapper;
import com.hori.grms.model.Attachment;
import com.hori.grms.model.BackMoneyPlan;
import com.hori.grms.model.CloseCaseInfo;
import com.hori.grms.model.Project;
import com.hori.grms.model.ProjectActionException;
import com.hori.grms.queryBean.CloseCaseQueryBean;
import com.hori.grms.service.BackMoneyPlanService;
import com.hori.grms.service.CloseCaseService;
import com.hori.grms.service.ContractService;
import com.hori.grms.service.PendingEventService;
import com.hori.grms.service.ProjectActionExceptionService;
import com.hori.grms.service.ProjectProductMenuAreaService;
import com.hori.grms.service.ProjectProductService;
import com.hori.grms.util.UUIDGenerator;
import com.hori.grms.util.UUIDGeneratorUtil;
import com.hori.grms.vo.CloseCaseVo;
import com.hori.grms.vo.ContractVo;
import com.hori.grms.vo.ProjectProductVo;

@Service
public class CloseCaseServiceImpl implements CloseCaseService {

	@Autowired
	private CloseCaseMapper closeCaseMapper;
	
	@Autowired
	private ProjectProductService projectProductService;
	
	@Autowired
	private ContractService contractService;
	
	@Autowired
	private BackMoneyPlanService backMoneyPlanService;
	
	@Autowired
	private ProjectProductMenuAreaService projectProductMenuAreaService;
	
	@Autowired
	private PendingEventService pendingEventService;
	
	@Autowired
	private ProjectActionExceptionService projectActionExceptionService;
	
	@Override
	public List<CloseCaseVo> getCloseCases(CloseCaseQueryBean queryBean) {
		if(queryBean != null) {
			queryBean.setPageIndex((queryBean.getPageNumber() - 1) * queryBean.getPageSize());
			if(StringUtils.isNotBlank(queryBean.getKeyword())) {
				queryBean.setKeyword("%" + queryBean.getKeyword() + "%");
			}
			if(StringUtils.isNotBlank(queryBean.getCreateTimeEnd())) {
				queryBean.setCreateTimeEnd(queryBean.getCreateTimeEnd().trim() + " 23:59:59");
			}
			if(StringUtils.isNotBlank(queryBean.getActionTimeEnd())) {
				queryBean.setActionTimeEnd(queryBean.getActionTimeEnd().trim() + " 23:59:59");
			}
		}
		List<CloseCaseVo> ccis = closeCaseMapper.getCloseCases(queryBean);
		if(ccis != null && !ccis.isEmpty()) {
			for(CloseCaseVo cc : ccis) {
				//业务
				if(cc.getRoleType() == 0) {
					//展示所有执行结案的附件
					List<Attachment> attachments = new LinkedList<>();
					List<CloseCaseInfo> cinfos = closeCaseMapper.getCloseCaseByProjectCode(cc.getProjectCode());
					if(cinfos != null && !cinfos.isEmpty()) {
						List<Attachment> attachs = null;
						for(CloseCaseInfo cinfo : cinfos) {
							attachs = closeCaseMapper.getAttachmentByCloseCaseId(cinfo.getId());
							attachments.addAll(attachs);
						}
					}
					cc.setAttachments(attachments);
				//执行
				}else {
					//附件
					List<Attachment> attachments = closeCaseMapper.getAttachmentByCloseCaseId(cc.getId());
					cc.setAttachments(attachments);
				}
			}
		}
		return ccis;
	}

	@Override
	public int getCloseCaseCount(CloseCaseQueryBean queryBean) {
		if(queryBean != null) {
			if(StringUtils.isNotBlank(queryBean.getKeyword())) {
				queryBean.setKeyword("%" + queryBean.getKeyword() + "%");
			}
			if(StringUtils.isNotBlank(queryBean.getCreateTimeEnd())) {
				queryBean.setCreateTimeEnd(queryBean.getCreateTimeEnd().trim() + " 23:59:59");
			}
			if(StringUtils.isNotBlank(queryBean.getActionTimeEnd())) {
				queryBean.setActionTimeEnd(queryBean.getActionTimeEnd().trim() + " 23:59:59");
			}
		}
		return closeCaseMapper.getCloseCaseCount(queryBean);
	}
	
	@Override
	public Attachment getAttachmentById(String id) {
		return closeCaseMapper.getAttachmentById(id);
	}
	
	@Override
	public CloseCaseInfo getCloseCaseForBackMoney(String backMoneyPlanCode) {
		return closeCaseMapper.getCloseCaseForBackMoney(backMoneyPlanCode);
	}
	
	@Override
	public int updateCloseCaseForBackMoney(String backMoneyPlanCode) {
		return closeCaseMapper.updateCloseCaseForBackMoney(backMoneyPlanCode);
	}
	
	@Override
	public int updateStatusForAction(List<String> ids, Integer status, Integer roleType) {
		int count = 0;
		if(ids == null || ids.isEmpty()) {
			return count;
		}
		CloseCaseInfo info = null;
		Integer roleTypeTemp = roleType;
		for(String id : ids) {
			//如果是管理员提交或撤回，角色类型要在更新前获取
			info = closeCaseMapper.getCloseCaseById(id);
			if(info == null) {
				continue;
			}
			if(roleType == -1) {
				roleType = info.getRoleType();
			}
			//更新
			count += closeCaseMapper.commit(id, status);

			//提交，判断是否所有执行部门都已提交
			if(status == 5) {
				//更新提交的待办事件(提交后，删除待办事件)
				pendingEventService.updateCloseCasePendingEvent(info.getProjectCode(), info.getCloseCaseCode(), status, roleType);
				//查找未提交的结案，如果找不到说明所有都已提交
				List<CloseCaseInfo> ccis = closeCaseMapper.getNotCommitCloseCases(id);
				if(ccis == null || ccis.isEmpty()) {
					//创建业务结案记录
					createCloseForBusiness(id, status);
					//新增业务待办事件
					pendingEventService.createCloseCasePendingEvent(info.getProjectName(), 
							info.getProjectCode(), info.getCloseCaseCode(), 0);
				}
			//撤回，判断是否有生成业务结案，如果有，则将业务结案删除，变为不可处理状态，同时更新业务的待办事件
			}else {
				List<CloseCaseInfo> ccis = closeCaseMapper.getCloseCasesByIdAndStatus(id, 6);
				if(ccis != null && !ccis.isEmpty()) {
					for(CloseCaseInfo cc : ccis) {
						closeCaseMapper.delete(cc.getId());
						pendingEventService.updateCloseCasePendingEvent(cc.getProjectCode(), 
								cc.getCloseCaseCode(), cc.getStatus(), cc.getRoleType());
					}
				}
				//撤回后，结案记录变为可提交状态，需要生成待办事件
				pendingEventService.createCloseCasePendingEvent(info.getProjectName(), 
						info.getProjectCode(), info.getCloseCaseCode(), roleType);
			}
			roleType = roleTypeTemp;
		}
		return count;
	}
	
	/**
	 * 创建业务结案记录
	 * @param id
	 * @param status
	 * @return
	 */
	private int createCloseForBusiness(String id, Integer status) {
		List<CloseCaseInfo> ccis = closeCaseMapper.getCloseCasesByIdAndStatus(id, status);
		Set<String> actionAreaSet = new HashSet<>();
		LocalDateTime newActionTime = null;
		String timeString = null;
		for(int i = 0; i < ccis.size(); i++) {
			CloseCaseInfo cc = ccis.get(i);
			actionAreaSet.add(cc.getActionArea());
			if(cc.getActionTime().length() > 19) {
				timeString = cc.getActionTime().substring(0, cc.getActionTime().lastIndexOf("."));
			}else {
				timeString = cc.getActionTime();
			}
			//取最早的日期
			if(i == 0) {
				newActionTime = LocalDateTime.parse(timeString,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			}else {
				LocalDateTime nextDate = LocalDateTime.parse(timeString,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
				if(nextDate.isBefore(newActionTime)) {
					newActionTime = nextDate;
				}
			}
		}
		StringBuffer newActionArea = new StringBuffer();
		if(!actionAreaSet.isEmpty()) {
			for(String s : actionAreaSet) {
				newActionArea.append(s);
				newActionArea.append(",");
			}
		}
		String newActionAreaString = null;
		if(newActionArea.length() > 0) {
			newActionAreaString = newActionArea.toString().substring(0,newActionArea.length() - 1);
		}
		CloseCaseInfo caseInfo = new CloseCaseInfo();
		caseInfo.setId(UUIDGenerator.generate());
		caseInfo.setCloseCaseCode(ccis.get(0).getCloseCaseCode());
		caseInfo.setProjectCode(ccis.get(0).getProjectCode());
		caseInfo.setProjectName(ccis.get(0).getProjectName());
		caseInfo.setContractCode(ccis.get(0).getContractCode());
		caseInfo.setContractName(ccis.get(0).getContractName());
		caseInfo.setCreaterAccount(ccis.get(0).getProjectCreaterAccount());
		caseInfo.setProjectCreaterAccount(ccis.get(0).getProjectCreaterAccount());
		caseInfo.setBackMoneyPlanCode(ccis.get(0).getBackMoneyPlanCode());
		caseInfo.setType(ccis.get(0).getType());
		caseInfo.setActionArea(newActionAreaString);
		caseInfo.setActionTime(newActionTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		caseInfo.setCreateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		caseInfo.setRoleType(0);
		caseInfo.setStatus(6);
		return closeCaseMapper.save(caseInfo);
	}
	
	@Override
	public int updateStatusForBusiness(List<String> ids, Integer status) {
		int count = 0;
		if(ids != null && !ids.isEmpty()) {
			CloseCaseInfo ccinfo = null;
			for(String id : ids) {
				ccinfo = closeCaseMapper.getCloseCaseById(id);
				if(ccinfo == null) {
					continue;
				}
				//如果是处理
				if(status == 7) {
					//将业务结案记录待处理的状态更新为处理状态
					count += closeCaseMapper.handle(ccinfo.getProjectCode(), status, 0);
					//将执行记录与业务结案状态同步
					closeCaseMapper.handle(ccinfo.getProjectCode(), status, 1);
					//更新（删除）业务待处理待办事件
					pendingEventService.updateCloseCasePendingEvent(ccinfo.getProjectCode(), 
							ccinfo.getCloseCaseCode(), status, 0);
					//新增财务的待办事件
					pendingEventService.createCloseCasePendingEvent(ccinfo.getProjectName(), 
							ccinfo.getProjectCode(), ccinfo.getBackMoneyPlanCode(), 3);
				//如果是撤回
				}else {
					//将业务结案记录待结案的状态更新为待处理状态
					count += closeCaseMapper.handle(ccinfo.getProjectCode(), status, 0);
					//将执行记录更新为已提交状态
					closeCaseMapper.handle(ccinfo.getProjectCode(), 5, 1);
					//更新（删除）财务待结案待办事件
					pendingEventService.updateCloseCasePendingEvent(ccinfo.getProjectCode(), 
							ccinfo.getBackMoneyPlanCode(), status, 3);
					//新增业务的待处理待办事件
					pendingEventService.createCloseCasePendingEvent(ccinfo.getProjectName(), 
							ccinfo.getProjectCode(), ccinfo.getCloseCaseCode(), 0);
				}
			}
		}
		return count;
	}

	@Override
	public int uploadAttachment(List<Attachment> attachments) {
		int count = 0;
		if(attachments != null && !attachments.isEmpty()) {
			for(Attachment attachment : attachments) {
				count += closeCaseMapper.uploadAttachment(attachment);
			}
		}
		return count;
	}
	
	@Override
	public int deleteAttachments(List<String> ids) {
		int count = 0;
		if(ids != null && !ids.isEmpty()) {
			for(String id : ids) {
				if(StringUtils.isNotBlank(id)) {
					count += closeCaseMapper.deleteAttachment(id);
				}
			}
		}
		return count;
	}
	
	@Override
	public int save(CloseCaseInfo closeCaseInfo) {
		return closeCaseMapper.save(closeCaseInfo);
	}

	@Override
	public void createCloseCaseInfo(Project project, String exceptionRecordId) {
		ContractVo contract = contractService.getExecuteContract(project.getProductCode());
		BackMoneyPlan backMoneyPlan = backMoneyPlanService.getBackMoneyPlanByContractCode(contract.getContractCode());
		// 获取项目关联的所有项目清单
		List<ProjectProductVo> projectProducts = projectProductService.getProductsOrderBy(project.getProductCode());
		
		if (projectProducts != null && projectProducts.size() > 0) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
			String closeCaseCode = sdf.format(new Date());// 结案编号
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String createTime = sdf2.format(new Date());
			List<CloseCaseInfo> nullCloseCases = new ArrayList<>();
			for (ProjectProductVo projectProduct : projectProducts) {
				String actionTime = sdf2.format(projectProduct.getBeginTime());
				CloseCaseInfo closeCase = new CloseCaseInfo();
				closeCase.setId(UUIDGeneratorUtil.generateUUID());
				closeCase.setCloseCaseCode(closeCaseCode);
				closeCase.setProjectCode(project.getProductCode());
				closeCase.setProjectName(project.getName());
				closeCase.setContractCode(contract.getContractCode());
				closeCase.setContractName(contract.getContractName());
				closeCase.setActionTime(actionTime);
				closeCase.setBackMoneyPlanCode(backMoneyPlan.getBackMoneyPlanCode());
				
				if ("1".equals(projectProduct.getBusinessType())) {// 社区运营
					closeCase.setActionArea(projectProduct.getAreaName());
					closeCase.setStatus(1);// 1：待提交社区运营
					closeCase.setRoleType(4);//社区运营执行员
				} else if ("2".equals(projectProduct.getBusinessType())) {// 媒管
					String areaNames = projectProductMenuAreaService.getAreaNamesByPPID(projectProduct.getId());
					closeCase.setActionArea(areaNames);
					closeCase.setStatus(4);// 4：待提交媒管
					closeCase.setRoleType(7);//媒管执行员
				} else if ("3".equals(projectProduct.getBusinessType())) {// 用户运营
					String areaNames = projectProductMenuAreaService.getAreaNamesByPPID(projectProduct.getId());
					closeCase.setActionArea(areaNames);
					closeCase.setStatus(2);// 2：待提交用户运营
					closeCase.setRoleType(6);//用户运营执行员
				} else if ("4".equals(projectProduct.getBusinessType())) {// 电商运营
					String areaNames = projectProductMenuAreaService.getAreaNamesByPPID(projectProduct.getId());
					closeCase.setActionArea(areaNames);
					closeCase.setStatus(3);// 3：待提交电商运营
					closeCase.setRoleType(5);//电商运营执行员
				}
				//结案类型
				if (project.getExceptionStatus() == 0) {
					closeCase.setType(1);// 正常结案
				} else if (project.getExceptionStatus() == 1) {
					closeCase.setType(0);// 异常结案
				}
				//如果是异常结案，删除所有执行的待办事件
				if(closeCase.getType() == 0) {
					pendingEventService.updateProjectActionPendingEvent(closeCase.getProjectCode(), 
							projectProduct.getActionCode(), "", 6, closeCase.getRoleType());
				}
				
				closeCase.setProjectCreaterAccount(project.getCreaterAccount());
				closeCase.setCreateTime(createTime);
				//如果执行未被领取
				if(StringUtils.isBlank(projectProduct.getReceiveAccount())) {
					nullCloseCases.add(closeCase);
					continue;
				}
				closeCase.setCreaterAccount(projectProduct.getReceiveAccount());
				
				closeCaseMapper.save(closeCase);
				//新增执行待提交待办事件
				pendingEventService.createCloseCasePendingEvent(closeCase.getProjectName(), 
						closeCase.getProjectCode(), closeCaseCode, closeCase.getRoleType());
			}
			//如果有未领取的执行清单
			if(!nullCloseCases.isEmpty()) {
				Set<String> actionAreaSet = new HashSet<>();
				LocalDateTime newActionTime = null;
				String timeString = null;
				boolean isAll = false;
				//如果全部执行清单都没有领取人
				if(nullCloseCases.size() == projectProducts.size()) {
					isAll = true;
				}
				//如果全部执行清单都没有领取人，直接生成待处理记录，如果部分有，则生成已提交结案记录
				for(int i = 0; i < nullCloseCases.size(); i++) {
					CloseCaseInfo cci = nullCloseCases.get(i);
					if(isAll) {
						actionAreaSet.add(cci.getActionArea());
						if(cci.getActionTime().length() > 19) {
							timeString = cci.getActionTime().substring(0, cci.getActionTime().lastIndexOf("."));
						}else {
							timeString = cci.getActionTime();
						}
						//取最早的日期
						if(i == 0) {
							newActionTime = LocalDateTime.parse(timeString,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
						}else {
							LocalDateTime nextDate = LocalDateTime.parse(timeString,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
							if(nextDate.isBefore(newActionTime)) {
								newActionTime = nextDate;
							}
						}
						cci.setStatus(6);
						cci.setRoleType(0);
					}else {
						cci.setStatus(5);
						closeCaseMapper.save(cci);
					}
				}
				if(isAll) {
					StringBuffer newActionArea = new StringBuffer();
					if(!actionAreaSet.isEmpty()) {
						for(String s : actionAreaSet) {
							newActionArea.append(s);
							newActionArea.append(",");
						}
					}
					String newActionAreaString = null;
					if(newActionArea.length() > 0) {
						newActionAreaString = newActionArea.toString().substring(0, newActionArea.length() - 1);
					}
					CloseCaseInfo cInfo = nullCloseCases.get(0);
					cInfo.setCreaterAccount(cInfo.getProjectCreaterAccount());
					cInfo.setActionArea(newActionAreaString);
					cInfo.setActionTime(newActionTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
					closeCaseMapper.save(cInfo);
					//新增业务的待处理待办事件
					pendingEventService.createCloseCasePendingEvent(cInfo.getProjectName(), 
							cInfo.getProjectCode(), cInfo.getCloseCaseCode(), 0);
				}
			}
			//更新确认状态
			if(StringUtils.isNotBlank(exceptionRecordId)) {
				ProjectActionException pae = projectActionExceptionService.getById(exceptionRecordId);
				if(pae.getType() == 1) {
					projectActionExceptionService.updateConfirmStatus(pae.getProjectActionCode(), 1);
				}else if(pae.getType() == 2) {
					projectActionExceptionService.updateConfirmStatus(pae.getProjectCode(), 2);
				}
			}
		}
	}

	@Override
	public long countByProjectCode(String projectCode) {
		return closeCaseMapper.countByProjectCode(projectCode);
	}

	@Override
	public void changeStatusByProjectCode(String projectCode, int type) {
		closeCaseMapper.changeStatusByProjectCode(projectCode, type);
	}
}
