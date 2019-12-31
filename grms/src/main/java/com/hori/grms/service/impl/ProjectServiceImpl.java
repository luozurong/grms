/**
 * 
 */
package com.hori.grms.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.hori.grms.dao.AttachmentMapper;
import com.hori.grms.dao.CustomerMapper;
import com.hori.grms.dao.PendingEventMapper;
import com.hori.grms.dao.ProductMenuMapper;
import com.hori.grms.dao.ProjectApproveLogMapper;
import com.hori.grms.dao.ProjectMapper;
import com.hori.grms.dao.ProjectPeopleMapper;
import com.hori.grms.dao.ProjectProductMapper;
import com.hori.grms.dao.ProjectProductMenuAreaMapper;
import com.hori.grms.dao.ProjectProductMenuMapper;
import com.hori.grms.model.Attachment;
import com.hori.grms.model.Contract;
import com.hori.grms.model.Customer;
import com.hori.grms.model.PendingEvent;
import com.hori.grms.model.ProductMenu;
import com.hori.grms.model.Project;
import com.hori.grms.model.ProjectApproveLog;
import com.hori.grms.model.ProjectExample;
import com.hori.grms.model.ProjectPeople;
import com.hori.grms.model.ProjectProduct;
import com.hori.grms.model.ProjectProductMenu;
import com.hori.grms.model.ProjectProductMenuArea;
import com.hori.grms.queryBean.ProjectQueryBean;
import com.hori.grms.service.ContractService;
import com.hori.grms.service.PendingEventService;
import com.hori.grms.service.ProjectApproveLogService;
import com.hori.grms.service.ProjectProductMenuService;
import com.hori.grms.service.ProjectProductService;
import com.hori.grms.service.ProjectService;
import com.hori.grms.util.UUIDGeneratorUtil;
import com.hori.grms.vo.ProjectDetailsVo;
import com.hori.grms.vo.AreaDataRspVo.AreaData;
import com.hori.grms.vo.ContractVo;
import com.hori.grms.vo.project.ProductMenuVo;
import com.hori.grms.vo.project.ProjectMenuVo;
import com.hori.grms.vo.project.ProjectVo;

/**
 * @ClassName: ProjectServiceImpl
 * @Description: 项目
 * @author zhuqiang
 * @date 2018年8月8日 下午6:49:43
 */
@Service("projectService")
public class ProjectServiceImpl implements ProjectService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProjectMapper projectMapper;

	@Autowired
	private CustomerMapper customerMapper;

	@Autowired
	private ProjectPeopleMapper projectPeopleMapper;

	@Autowired
	private ProjectProductMapper projectProductMapper;

	@Autowired
	private ProjectApproveLogService projectApproveLogService;

	@Autowired
	private ProjectProductMenuAreaMapper projectProductMenuAreaMapper;

	@Autowired
	private ProductMenuMapper productMenuMapper;

	@Autowired
	private ProjectProductMenuMapper projectProductMenuMapper;

	@Autowired
	private AttachmentMapper attachmentMapper;

	@Autowired
	private ProjectProductService projectProductService;
	@Autowired
	private PendingEventService pendingEventService;
	@Autowired
	private PendingEventMapper pendingEventMapper;
	@Autowired
	private ContractService contractService;
	@Autowired
	private ProjectApproveLogMapper  projectApproveLogMapper;

	@Override
	public List<Project> list(ProjectQueryBean projectQueryBean) {
		return projectMapper.list(projectQueryBean);
	}

	@Override
	public ProjectDetailsVo findProjectDetailsVoByProjectCode(String projectCode) {
		return projectMapper.findProjectDetailsVoByProjectCode(projectCode);
	}

	@Override
	public ProjectVo findProjectModel(String productCode) {
		// 1.查找项目信息 project
		ProjectVo projectVo = projectMapper.findProjectByProjectCode(productCode);

		if (StringUtils.isNotBlank(projectVo.getCustomerId())) {
			Customer customer = customerMapper.selectByPrimaryKey(projectVo.getCustomerId());			
			if (customer != null&& customer.getIsDel().trim().equals("0")) {
				projectVo.setCustomerInfo(
						customer.getContactor() + "-" + customer.getDepartment() + "-" + customer.getName());
			}else{
				projectVo.setCustomerId("");
			}
		}

		// 2. 项目联系人信息 project-people
		List<ProjectPeople> projectPeoples = projectPeopleMapper.findByProjectCode(productCode);
		if (projectPeoples != null) {
			projectVo.setProjectPeoples(projectPeoples);
		}

		return projectVo;

	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public void revoke(String productCode, String userName, String userAccount) throws Exception {

		// 撤回项目编号不能为空
		if (StringUtils.isEmpty(productCode)) {

			throw new Exception("项目编号参数不能为空！！");

		}

		Project project = projectMapper.selectByProductCode(productCode);

		if (null == project) {

			throw new Exception("项目不存在！！");

		}

		if (project.getStatus() != 0) {

			throw new Exception("项目状态为待审核的项目才能进行撤回操作！！");

		}

		ProjectApproveLog projectApproveLog = createApproveLog((short) 4, productCode, userName, userAccount, null);

		projectApproveLogService.inserLog(projectApproveLog);

		projectMapper.revoke(productCode);

		// 解决待办
		pendingEventService.updateProjectPendingEvent(productCode, (short) 4);
	}

	/**
	 * 注解式事务 事务隔离级别和传播属性都采用默认 注意： 1.
	 * 默认配置下，spring只有在抛出的异常为运行时unchecked异常时才回滚该事务，也就是抛出的异常为RuntimeException的子类(
	 * Errors也会导致事务回滚)，而抛出checked异常则不会导致事务回滚。
	 * 可以明确的配置在抛出那些异常时回滚事务，包括checked异常。也可以明确定义那些异常抛出时不回滚事务。
	 * 我们这里指定无论抛出unchecked异常还是cheched异常都执行事务回滚
	 * 2.默认情况下，只有来自外部的方法调用才会被AOP代理捕获，也就是，类内部方法调用本类内部的其他方法并不会引起事务行为，
	 * 即使被调用方法使用@Transactional注解进行修饰
	 * 
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public void delet(String productCode, String userName, String userAccount) throws Exception {

		// 要删除的项目编号不能为空
		if (StringUtils.isEmpty(productCode)) {

			throw new Exception("项目编号参数不能为空！！");

		}

		Project project = projectMapper.selectByProductCode(productCode);

		if (null == project) {

			throw new Exception("项目不存在！！");

		}

		if (project.getStatus() != 2 && project.getStatus() != 3 && project.getStatus() != 4) {

			throw new Exception("项目当前状态不允许进行删除操作！！");

		}

		ProjectApproveLog projectApproveLog = createApproveLog((short) -1, productCode, userName, userAccount, null);
		// 保存操作日志
		projectApproveLogService.inserLog(projectApproveLog);
		// 项目状态改为删除状态
		projectMapper.delet(productCode);

		// 解决待办
		pendingEventService.updateProjectPendingEvent(productCode, (short) -1);

		logger.info("项目：" + productCode + "删除成功");

	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public void stopProject(String productCode, String remark, String userName, String userAccount) throws Exception {

		// 要终止的项目编号不能为空
		if (StringUtils.isEmpty(productCode)) {

			throw new Exception("项目编号参数不能为空！！");

		}

		Project project = projectMapper.selectByProductCode(productCode);

		if (null == project) {

			throw new Exception("项目不存在！！");

		}

		if (project.getStatus() != 1) {

			throw new Exception("项目当前状态不允许进行终止操作！！");
		}

		// 合同状态为完成不可终止立项
		ContractVo contract = contractService.getExecuteContract(productCode);

		if (null!=contract&&contract.getStatus() == 7) {

			throw new Exception("合同已完成，不允许进行终止操作！！");

		}

		// 改变项目状态
		project.setStatus((short) 3);
		// 生成项目操作日志
		ProjectApproveLog projectApproveLog = createApproveLog((short) 3, productCode, userName, userAccount, remark);
		// 更新项目
		projectMapper.stopProject(productCode);
		// 释放资源
		projectProductMenuMapper.releaseLock(productCode);
		// 保存操作日志
		projectApproveLogService.inserLog(projectApproveLog);
		//删除对应的合同及其附件
		if(null!=contract&&contract.getStatus() != 7){
			contractService.delContractInfoById(contract.getId());
		}
		
		// 解决待办
		pendingEventService.updateProjectPendingEvent(productCode, (short) 3);

		logger.info("项目：" + productCode + "终止成功");

	}

	public int total(ProjectQueryBean projectQueryBean) {

		return projectMapper.total(projectQueryBean);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public void examProject(String productCode, String remark, String userName, String userAccount, short examResult)
			throws Exception {

		// 要审核的项目编号不能为空
		if (StringUtils.isEmpty(productCode)) {
			logger.info("操作失败！项目编号为空");
			throw new Exception("项目编号参数不能为空！！");

		}

		Project project = projectMapper.selectByProductCode(productCode);

		if (null == project) {
			logger.info("操作失败！项目不存在");
			throw new Exception("项目不存在！！");

		}

		if (project.getStatus() != 0) {

			throw new Exception("项目当前状态不允许进审核操作！！");
		}

		List<ProjectMenuVo> projectMenuVos = projectProductService.findVoByProjectCode(productCode);

		if (examResult!=(short)2&&(!(projectMenuVos.size() > 0))) {
			throw new Exception("执行清单不能为空！！");
		}

		if (examResult == (short) 1) {

			boolean checkDate = checkDate(projectMenuVos);

			if (!checkDate) {
				logger.info("项目:" + productCode + "的资源已被占用！！");
				throw new Exception("项目资源已被占用！！请重新选择");
			}

			logger.info("项目:" + productCode + "的资源校验通过！！");
			// 校验成功，锁定资源
			projectProductMenuMapper.lockResources(productCode);
		}

		// 修改项目状态
		project.setStatus(examResult);

		logger.info("生成项目操作日志！！");
		ProjectApproveLog projectApproveLog = createApproveLog(examResult, productCode, userName, userAccount, remark);
		// 更新项目记录
		projectMapper.updateByPrimaryKeySelective(project);
		// 参入操作日志

		projectApproveLogService.inserLog(projectApproveLog);

		// 解决待办
		pendingEventService.updateProjectPendingEvent(productCode, examResult);
	}

	/**
	 * 项目立项
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 72000, rollbackFor = Exception.class)
	public void saveProject(ProjectVo projectVo, String userAccount, String userName, String departId,
			Map<String, AreaData> areaDatas) {

		// 封装Project对象
		// 根据项目id 判断是立项还是更改立项
		Project project = null;
		if (StringUtils.isNotBlank(projectVo.getId())) {
			project = projectMapper.selectByPrimaryKey(projectVo.getId());
		}
		// 记录是新建还是修改,用于代办
		boolean ifNew = true; // 默认新建

		if (project != null) {
			// 更新立项
			project.setName(projectVo.getName());
			project.setCustomerId(projectVo.getCustomerId());
			project.setCustomerName(projectVo.getCustomerInfo().split("-")[0]);
			project.setDescription(projectVo.getDescription());
			project.setUpdateTime(new Date());
			project.setStatus((short) 0);
			project.setExceptionStatus((short) 0);

			projectMapper.updateByPrimaryKeySelective(project);
			// 删除项目相关其他信息
			// project_people
			projectPeopleMapper.deleteByProjectCode(project.getProductCode());

			List<String> project_product_menuIds = projectProductMenuMapper.findByProjectCode(project.getProductCode());

			// project_product_menu_area
			if (project_product_menuIds != null && project_product_menuIds.size() > 0) {
				projectProductMenuAreaMapper.deleteByProjectProductMenuIds(project_product_menuIds);
			}

			// project_product_menu

			projectProductMenuMapper.deleteByProjectCode(project.getProductCode());

			// project_product
			projectProductMapper.deleteByProjectCode(project.getProductCode());

			// attachment
			attachmentMapper.deleteByCorrelationId(project.getProductCode());

			ifNew = false;

		} else {
			// 创建立项
			project = new Project();
			project.setId(UUIDGeneratorUtil.generateUUID());
			project.setName(projectVo.getName());
			project.setCustomerId(projectVo.getCustomerId());
			project.setCustomerName(projectVo.getCustomerInfo().split("-")[0]);
			project.setDescription(projectVo.getDescription());
			project.setUpdateTime(new Date());
			project.setStatus((short) 0); // 状态
			project.setExceptionStatus((short) 0); // 异常状态
			project.setActionStatus((short) 0); // 项目执行状态
			project.setCreateTime(new Date());
			project.setCreaterAccount(userAccount);// 创建者账号
			project.setCreaterName(userName);// 创建者名称
			project.setCreaterDedpartmentId(departId);// 创建者部门id

			// 获得当前时间组成的编号
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Long product_code = Long.parseLong(sdf.format(new Date()));
			project.setProductCode(product_code + "");

			int i = 0;
			while (true) {
				try {
					projectMapper.insert(project);
					break;
				} catch (Exception e) {
					e.printStackTrace();
					product_code++;
					project.setProductCode(product_code + "");
					i++;
					if (i == 3) {
						throw new RuntimeException("保存项目报错");
					}
				}
			}
		}

		/**
		 * 封装相关数据
		 */
		// attachment
		List<Attachment> attachments = new ArrayList<>();
		if (projectVo.getAttchments() != null) {
			for (Attachment attachment : projectVo.getAttchments()) {
				attachment.setType(1);
				attachment.setCorrelationId(project.getProductCode());
				attachments.add(attachment);
			}
		}

		// project_people
		List<ProjectPeople> projectPeoples = projectVo.getProjectPeoples();
		if (projectPeoples != null && projectPeoples.size() > 0) {
			for (ProjectPeople projectPeople : projectPeoples) {
				projectPeople.setId(UUIDGeneratorUtil.generateUUID());
				projectPeople.setProjectCode(project.getProductCode());
			}
		}
		// project_product_menu_area
		List<ProjectProductMenuArea> ppmaS = new ArrayList<>();
		// project_product_menu
		List<ProjectProductMenu> ppmS = new ArrayList<>();
		// project_product
		List<ProjectProduct> ppS = new ArrayList<>();

		List<ProjectMenuVo> list = new ArrayList<>();
		if (projectVo.getProject1() != null && projectVo.getProject1().size() > 0) {
			for (ProjectMenuVo projectMenuVo : projectVo.getProject1()) {
				list.add(projectMenuVo);
			}
		}
		if (projectVo.getProject2() != null)
			list.add(projectVo.getProject2());
		if (projectVo.getProject3() != null)
			list.add(projectVo.getProject3());
		if (projectVo.getProject4() != null)
			list.add(projectVo.getProject4());

		ProjectProductMenuArea ppma = null;
		ProjectProductMenu ppm = null;
		ProjectProduct pp = null;
		AreaData ad = null;
		Set<String> areaNames = null;
		for (ProjectMenuVo pmv : list) {
			pp = new ProjectProduct();
			pp.setId(UUIDGeneratorUtil.generateUUID());
			pp.setProjectCode(project.getProductCode());
			pp.setBusinessType(pmv.getBusinessType());

			Date begin_Date = null;
			Date end_Date = null;

			if (pmv.getBusinessType().trim().equals("1")) {
				pp.setBeginTime(pmv.getBeginTime());
				pp.setEndTime(pmv.getEndTime());
				ad = areaDatas.get(pmv.getOrganizationSeq());
				pp.setAreaName(ad.getAreaName());
				pp.setOrganizationSeq(pmv.getOrganizationSeq());
				pp.setProvince(ad.getProvince());
				pp.setProvinceName(ad.getProvinceName());
				pp.setCity(ad.getCity());
				pp.setCityName(ad.getCityName());
				pp.setCountry(ad.getCountry());
				pp.setCountryName(ad.getCountryName());
				pp.setAreaAddress(ad.getAreaAddress());
				pp.setFieldName(pmv.getFieldName());
				pp.setIsDistributionField(pmv.getIsDistributionField());
				pp.setIsDistributionTime(pmv.getIsDistributionTime());
			}
			List<ProductMenuVo> pdmvS = pmv.getProjectMenus();
			if (pdmvS != null && pdmvS.size() != 0) {
				for (ProductMenuVo pdmv : pdmvS) {
					ppm = new ProjectProductMenu();
					areaNames = new HashSet<>();
					ppm.setId(UUIDGeneratorUtil.generateUUID());
					ppm.setProjectProductId(pp.getId());
					ppm.setProductMenuId(pdmv.getId());
					ppm.setBuyNum(pdmv.getBuyNum());
					ppm.setProjectCode(project.getProductCode());
					if (pmv.getBusinessType().trim().equals("1")) {
						ppm.setBeginTime(pp.getBeginTime());
						ppm.setEndTime(pp.getEndTime());
						ppm.setAreaNames(pp.getAreaName());
					} else {
						ppm.setBeginTime(pdmv.getBeginTime());
						ppm.setEndTime(pdmv.getEndTime());

						// 小区
						String[] orgS = pdmv.getAreaIds().split(",");
						for (String org : orgS) {
							ppma = new ProjectProductMenuArea();
							ppma.setId(UUIDGeneratorUtil.generateUUID());
							ad = areaDatas.get(org.trim());
							ppma.setAreaName(ad.getAreaName());
							ppma.setOrganizationSeq(org.trim());
							ppma.setProjectProductMenuId(ppm.getId());
							ppma.setProvince(ad.getProvince());
							ppma.setProvinceName(ad.getProvinceName());
							ppma.setCity(ad.getCity());
							ppma.setCityName(ad.getCityName());
							ppma.setCountry(ad.getCountry());
							ppma.setCountryName(ad.getCountryName());
							ppmaS.add(ppma);
							areaNames.add(ad.getAreaName());
						}
						if (begin_Date == null) {
							begin_Date = pdmv.getBeginTime();
						} else if (begin_Date.getTime() > pdmv.getBeginTime().getTime()) {
							begin_Date = pdmv.getBeginTime();
						}

						if (end_Date == null) {
							end_Date = pdmv.getEndTime();
						} else if (end_Date.getTime() < pdmv.getEndTime().getTime()) {
							end_Date = pdmv.getEndTime();
						}
						String ans = "";
						for (String an : areaNames) {
							ans += an + ",";
						}
						ppm.setAreaNames(ans.substring(0, ans.length() - 1));
					}
					ppm.setLocked((short) 0);
					if (pdmv.getOtherNum1() != null) {
						ppm.setOtherNum1(pdmv.getOtherNum1());
					}
					ppmS.add(ppm);
				}
			}
			if (!pmv.getBusinessType().trim().equals("1")) {
				pp.setBeginTime(begin_Date);
				pp.setEndTime(end_Date);
			}

			ppS.add(pp);
		}
		/**
		 * 保存相关数据
		 */
		// attachment
		for (Attachment attachment : attachments) {
			attachmentMapper.insert(attachment);
		}
		// project_people
		for (ProjectPeople projectPeople : projectPeoples) {
			projectPeopleMapper.insert(projectPeople);
		}

		// project_product
		for (ProjectProduct projectProduct : ppS) {
			projectProductMapper.insert(projectProduct);
		}

		// project_product_menu
		for (ProjectProductMenu projectProductMenu : ppmS) {
			projectProductMenuMapper.insert(projectProductMenu);
		}

		// project_product_menu_area
		for (ProjectProductMenuArea projectProductMenuArea : ppmaS) {
			projectProductMenuAreaMapper.insert(projectProductMenuArea);
		}

		/*if (ifNew) {*/
		// 生成代办 ...
		pendingEventService.updateProjectPendingEvent(project.getProductCode(), 5);
		pendingEventService.createProjectPendingEvent(project.getName(), project.getProductCode(), userAccount,
					userName);
		/*} else {
			// 更改代办...
			PendingEvent pendingEvent = this.pendingEventMapper.selectByModelCodeAndName(project.getProductCode(),
					project.getProductCode(), "项目管理", "");
			pendingEvent.setRoleType(1);
			pendingEvent.setProjectName(project.getName());
			pendingEvent.setStatus(0);
			pendingEvent.setUpdateTime(new Date());
			this.pendingEventMapper.updateByModelCode(pendingEvent);
		}*/
		ProjectApproveLog projectApproveLog=new ProjectApproveLog();
		projectApproveLog.setId(UUIDGeneratorUtil.generateUUID());
		projectApproveLog.setCreaterAccount(userAccount);
		projectApproveLog.setCreaterName(userName);
		projectApproveLog.setStatus(0);
		projectApproveLog.setProjectCode(project.getProductCode());
		projectApproveLog.setApproveTime(new Date());
		projectApproveLogMapper.insert(projectApproveLog);
	}

	/**
	 * 生成操作日志
	 * 
	 * @param status
	 *            状态：-1删除 0待审核 1审核通过 2审核不通过 3立项终止 4撤回
	 * @param productCode
	 *            项目编号
	 * @param userName
	 *            审核人名字
	 * @param userAccount
	 *            审核人帐号
	 * @param remark
	 *            审核/终止意见备注
	 */
	private ProjectApproveLog createApproveLog(short status, String productCode, String userName, String userAccount,
			String remark) {

		ProjectApproveLog projectApproveLog = new ProjectApproveLog();

		projectApproveLog.setId(UUIDGeneratorUtil.generateUUID());

		projectApproveLog.setApproveTime(new Date());

		projectApproveLog.setStatus((int) status);

		projectApproveLog.setCreaterAccount(userAccount);

		projectApproveLog.setCreaterName(userName);

		projectApproveLog.setProjectCode(productCode);

		projectApproveLog.setRemark(remark);

		return projectApproveLog;
	}

	@Override
	public Project getByCode(String code) {
		Assert.notNull(code, "code 不能为空");
		ProjectExample example = new ProjectExample();
		example.or().andProductCodeEqualTo(code);
		List<Project> list = this.projectMapper.selectByExample(example);
		if (null != list && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 校验数据
	 * 
	 * @param projectMenuVos
	 *            项目清单
	 * @return
	 */
	@Override
	public boolean checkDate(List<ProjectMenuVo> projectMenuVos) {
		boolean flag = true;
		// 获取知道项目编号所有项目产品清单信息
		List<ProductMenu> productMenus = productMenuMapper.findAllProductMenus();
		Map<String, ProductMenu> mapR = new HashMap<>();
		for (ProductMenu productMenu : productMenus) {
			mapR.put(productMenu.getId(), productMenu);
		}
		// 进行数据校验
		for (ProjectMenuVo projectMenuVo : projectMenuVos) {

				// 媒管清单数据校验
				List<ProductMenuVo> projectMenus = projectMenuVo.getProjectMenus();

				for (ProductMenuVo productMenuVo : projectMenus) {
					logger.info("校验资源："+productMenuVo.getId());
					if (!(mapR.get(productMenuVo.getId()).getNumLimit()==0)) {
						// numLimit为0则不限制数量
						ProductMenu productMenu = mapR.get(productMenuVo.getId());
						System.out.println(productMenu.getNumLimitType()==(short) 1);
						if (mapR.get(productMenuVo.getId()).getNumLimitType()==(short) 1){
							String organizationSeqStr = productMenuVo.getAreaIds();
							if (StringUtils.isNotBlank(organizationSeqStr)) {
								String[] organizationSeqs = organizationSeqStr.split(",");
								for (String organizationSeq : organizationSeqs) {

									Integer num = projectProductMenuMapper.getMGTotal(productMenuVo.getId(),
											organizationSeq, productMenuVo.getBeginTime(), productMenuVo.getEndTime());
									if (num == null)
										num = 0;
									if (num + productMenuVo.getBuyNum() > mapR.get(productMenuVo.getId()).getNumLimit()) {
										flag = false;
										logger.info("资源："+productMenuVo.getId()+"冲突");
										return flag;
									}
								}

							}
						}else{
							String organizationSeqStr = productMenuVo.getAreaIds();
							if (StringUtils.isNotBlank(organizationSeqStr)) {

								Integer num = projectProductMenuMapper.getYHorDSTotal(productMenuVo.getId(),
										productMenuVo.getBeginTime());
								if (num == null)
									num = 0;
								if (num + productMenuVo.getBuyNum() > mapR.get(productMenuVo.getId()).getNumLimit()) {
									flag = false;
									logger.info("资源："+productMenuVo.getId()+"冲突");
									return flag;
								}
							}
						}
						
					}
				}
		}
		return flag;
	}
	
	/**
	 * 校验数据
	 * 
	 * @param projectMenuVos
	 *            项目清单
	 * @return
	 */
	@Override
	public Map<Integer,Set<String>> checkDateSave(List<ProjectMenuVo> projectMenuVos) {
		Map<Integer,Set<String>> map=new HashMap<>();
		// 获取知道项目编号所有项目产品清单信息
		// 进行数据校验
		for (ProjectMenuVo projectMenuVo : projectMenuVos) {
			List<ProductMenu> productMenus = productMenuMapper.findAllProductMenus();
			Map<String, ProductMenu> mapR = new HashMap<>();
			for (ProductMenu productMenu : productMenus) {
				mapR.put(productMenu.getId(), productMenu);
			}

			if (projectMenuVo.getBusinessType().equals("2")) {
				Set<String> set2=new HashSet<>();
				// 媒管清单数据校验
				List<ProductMenuVo> projectMenus = projectMenuVo.getProjectMenus();

				for (ProductMenuVo productMenuVo : projectMenus) {
					if (!mapR.get(productMenuVo.getId()).getNumLimit().equals(new Integer(0))) {
						// numLimit为0则不限制数量
						String organizationSeqStr = productMenuVo.getAreaIds();
						if (StringUtils.isNotBlank(organizationSeqStr)) {
							String[] organizationSeqs = organizationSeqStr.split(",");
							for (String organizationSeq : organizationSeqs) {

								Integer num = projectProductMenuMapper.getMGTotal(productMenuVo.getId(),
										organizationSeq, productMenuVo.getBeginTime(), productMenuVo.getEndTime());
								if (num == null)
									num = 0;
								if (num + productMenuVo.getBuyNum() > mapR.get(productMenuVo.getId()).getNumLimit()) {
									set2.add(mapR.get(productMenuVo.getId()).getProductSpec());
								}
							}

						}
					}
				}
				map.put(2, set2);
			} else if (projectMenuVo.getBusinessType().equals("3") || projectMenuVo.getBusinessType().equals("4")) {
				// 用户运营 电商运营清单数据校验
				List<ProductMenuVo> projectMenus = projectMenuVo.getProjectMenus();
				
				Set<String> set3=new HashSet<>();
				Set<String> set4=new HashSet<>();

				for (ProductMenuVo productMenuVo : projectMenus) {
					if (!mapR.get(productMenuVo.getId()).getNumLimit().equals(new Integer(0))) {
						// numLimit为0则不限制数量
						String organizationSeqStr = productMenuVo.getAreaIds();
						if (StringUtils.isNotBlank(organizationSeqStr)) {

							Integer num = projectProductMenuMapper.getYHorDSTotal(productMenuVo.getId(),
									productMenuVo.getBeginTime());
							if (num == null)
								num = 0;
							if (num + productMenuVo.getBuyNum() > mapR.get(productMenuVo.getId()).getNumLimit()) {
								if(projectMenuVo.getBusinessType().equals("3")){
									set3.add(mapR.get(productMenuVo.getId()).getProductSpec());
								}
								if(projectMenuVo.getBusinessType().equals("4")){
									set4.add(mapR.get(productMenuVo.getId()).getProductSpec());								
								}
							}
						}
					}
				}
				map.put(3, set3);
				map.put(4, set4);

			}
		}
		return map;
	}


	@Override
	public Project getByContractCode(String contractCode) {
		
		return projectMapper.getByContractCode(contractCode);
	}


	@Override
	public int update(Project project) {
		return projectMapper.updateByPrimaryKeySelective(project);
	}

	@Override
	public boolean examDate(String productCode,short examResult) {
		List<ProjectMenuVo> projectMenuVos = projectProductService.findVoByProjectCode(productCode);
		
		if(!(projectMenuVos.size()>0)){
			return false;
		}
		
		boolean checkDate = checkDate(projectMenuVos);

		if (!checkDate) {
			logger.info("项目:" + productCode + "的资源已被占用！！");
			return false;
		}
		
		if (examResult!=(short)2&&(!(projectMenuVos.size() > 0))) {
			return false;
		}
		
		logger.info("项目:" + productCode + "的资源校验通过！！");
		return true;

	}

	@Override
	public boolean stop(String productCode) throws Exception {
		
		// 合同状态为完成不可终止立项
		ContractVo contract = contractService.getExecuteContract(productCode);

		if (null!=contract&&contract.getStatus() == 7) {
			logger.info("项目编号为："+productCode+"的项目合同已经完成不允许终止");
			return false;

		}else{
			return true;
		}
		
	}

}
