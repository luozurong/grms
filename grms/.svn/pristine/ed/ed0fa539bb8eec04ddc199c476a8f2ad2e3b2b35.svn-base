package com.hori.grms.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hori.grms.model.Attachment;
import com.hori.grms.model.Project;
import com.hori.grms.model.ProjectApproveLog;
import com.hori.grms.model.ProjectPeople;
import com.hori.grms.pageModel.Json;
import com.hori.grms.queryBean.ProjectQueryBean;
import com.hori.grms.service.AreaDataService;
import com.hori.grms.service.AttachmentService;
import com.hori.grms.service.BackMoneyPlanService;
import com.hori.grms.service.CloseCaseService;
import com.hori.grms.service.ContractService;
import com.hori.grms.service.ProjectActionService;
import com.hori.grms.service.ProjectApproveLogService;
import com.hori.grms.service.ProjectPeopleService;
import com.hori.grms.service.ProjectProductService;
import com.hori.grms.service.ProjectService;
import com.hori.grms.util.ExportExcelUtil;
import com.hori.grms.vo.AreaDataRspVo;
import com.hori.grms.vo.BackMoneyPlanVo;
import com.hori.grms.vo.ProjectActionVo;
import com.hori.grms.vo.ProjectDetailsVo;
import com.hori.grms.vo.project.ProductMenuVo;
import com.hori.grms.vo.project.ProjectMenuVo;
import com.hori.grms.vo.project.ProjectVo;
import com.hori.vo.UserDetailLoginVo;

/**
 * 项目管理controller
 * 
 * @author Chenrw
 * @date 2018年8月9日
 */
@Controller
@RequestMapping("/project")
public class ProjectController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProjectService projectService;

	@Autowired
	private ProjectPeopleService projectPeopleService;
	@Autowired
	private ProjectProductService projectProductService;
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private ProjectActionService projectActionService;
	@Autowired
	private ProjectApproveLogService projectApproveLogService;
	@Autowired
	private AreaDataService areaDataService;
	@Autowired
	private CloseCaseService closeCaseService;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private BackMoneyPlanService backMoneyPlanService;

	/**
	 * 跳转项目列表页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/list")
	public String getProjectList(HttpServletRequest request, HttpServletResponse response) {
		// 待办事件跳转
		String projectCode = request.getParameter("projectCode");
		String projectStatus = request.getParameter("projectStatus");
		String pendingParamType = request.getParameter("pendingParamType");
		if ("1".equals(pendingParamType)) {
			String pendingStatus = "1";
			if ("0".equals(projectStatus)) {
				pendingStatus = "2";
			} else if ("1".equals(projectStatus)) {
				pendingStatus = "3";
			} else if ("2".equals(projectStatus)) {
				pendingStatus = "4";
			} else if ("3".equals(projectStatus)) {
				pendingStatus = "6";
			} else if ("4".equals(projectStatus)) {
				pendingStatus = "5";
			}
			request.setAttribute("pendingStatus", pendingStatus);
			request.getSession().setAttribute("projectCode", projectCode);
			request.getSession().setAttribute("projectStatus", projectStatus);
			request.getSession().setAttribute("pendingParamType", pendingParamType);
		}

		HttpSession session = getSession();
		session.removeAttribute("projectQueryBeanS");
		Byte roleType = (Byte) session.getAttribute("roleType");
		String userType = (String) session.getAttribute("userType");

		request.setAttribute("userType", userType);
		request.setAttribute("roleType", roleType);

		return "/project/projectList.jsp";
	}

	/**
	 * 返回项目列表
	 * 
	 * @return
	 */
	@RequestMapping("/goback")
	public String goback(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = getSession();
		String querryBean = (String) session.getAttribute("projectQueryJsonStr");

		ProjectQueryBean projectQueryBean = JSON.parseObject(querryBean, ProjectQueryBean.class);

		Byte roleType = (Byte) session.getAttribute("roleType");

		request.setAttribute("roleType", roleType);

		String userType = (String) session.getAttribute("userType");

		request.setAttribute("userType", userType);

		request.setAttribute("querryBean", projectQueryBean);

		return "/project/projectList.jsp";
	}

	/**
	 * 获取项目列表数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getlistData", produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String getProjectListData(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			String parameter = request.getParameter("projectQueryBean");

			HttpSession session = getSession();
			String userAccount = (String) session.getAttribute("userAccount");
			Byte roleType = (Byte) session.getAttribute("roleType");
			Byte dataArea = (Byte) session.getAttribute("dataArea");

			ProjectQueryBean projectQueryBean = JSON.parseObject(parameter, ProjectQueryBean.class);

			if (null == projectQueryBean) {
				projectQueryBean = new ProjectQueryBean();
			}

			if (null != dataArea && null != roleType && dataArea == 0 && roleType == 0) {

				projectQueryBean.setAccount(userAccount);

			}

			if (null != roleType) {
				projectQueryBean.setRoleType(roleType);
			}

			// 待办事件跳转
			String pendingParamType = (String) request.getSession().getAttribute("pendingParamType");
			if ("1".equals(pendingParamType) && pendingParamType != null) {
				String projectCode = (String) request.getSession().getAttribute("projectCode");
				String projectStatus = (String) request.getSession().getAttribute("projectStatus");
				projectQueryBean.setPageNumber(1);
				projectQueryBean.setPageSize(10);
				projectQueryBean.setProductCode(projectCode);
				projectQueryBean.setStatus(Integer.parseInt(projectStatus));

			}

			// 保存数据,用于回显
			session.setAttribute("projectQueryJsonStr", parameter);
			if (null != dataArea && null != roleType && dataArea != 2 && roleType != 0 && roleType != 1) {
				resultMap.put("success", true);
				resultMap.put("rows", new ArrayList<Project>());
				resultMap.put("total", 0);
			} else {

				List<Project> list = projectService.list(projectQueryBean);

				int total = projectService.total(projectQueryBean);

				resultMap.put("success", true);
				resultMap.put("rows", list);
				resultMap.put("total", total);

			}

			logger.info("获取项目列表成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("错误原因：" + e.getMessage());
			resultMap.put("success", false);

		} finally {
			// 待办事件跳转
			request.getSession().removeAttribute("pendingParamType");
			request.getSession().removeAttribute("projectCode");
			request.getSession().removeAttribute("projectStatus");
		}

		return JSON.toJSONStringWithDateFormat(resultMap, "yyyy-MM-dd");
	}

	/**
	 * 
	 * @param type
	 *            0-新建,1-修改
	 * @param productCode
	 * @return
	 */
	@RequestMapping(value = "/addProject", produces = "text/html;charset=utf-8")
	public String addProject(@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "productCode", required = false) String productCode) {
		HttpSession session = getSession();
		HttpServletRequest request = getRequest();

		// 新建
		session.removeAttribute("projectVoStr"); // 项目信息
		session.removeAttribute("project1Str"); // 1
		session.removeAttribute("project2Str"); // 2
		session.removeAttribute("project3Str"); // 3
		session.removeAttribute("project4Str"); // 4
		session.removeAttribute("attchmentsFromStr"); // 要添加的附件
		session.removeAttribute("projectMeauList"); // 清单列表集合
		session.removeAttribute("attchmentsFromRMStr"); // 要删除的附件
		if (type == 1) {
			// 修改,查找并封装数据
			ProjectVo projectVo = projectService.findProjectModel(productCode);

			request.setAttribute("projectVo", projectVo);
			session.setAttribute("projectVoStr", JSON.toJSONString(projectVo));

			List<ProjectMenuVo> projectMs = projectProductService.findVoByProjectCode(productCode);

			List<ProjectMenuVo> project1 = new ArrayList<>();

			for (ProjectMenuVo pv : projectMs) {
				if (pv.getBusinessType().trim().equals("1")) {
					project1.add(pv);
				}

				// 项目2-执行清单
				if (pv.getBusinessType().trim().equals("2")) {
					session.setAttribute("project2Str", JSON.toJSONString(pv));
				}

				// 项目3-执行清单
				if (pv.getBusinessType().trim().equals("3")) {
					System.out.println("3：" + JSON.toJSONString(pv));
					session.setAttribute("project3Str", JSON.toJSONString(pv));
				}

				// 项目4-执行清单
				if (pv.getBusinessType().trim().equals("4")) {
					session.setAttribute("project4Str", JSON.toJSONString(pv));
				}
			}
			// 项目1-执行清单
			session.setAttribute("project1Str", JSON.toJSONString(project1));

			// 项目清单组合装json
			session.setAttribute("projectMeauList", JSON.toJSONStringWithDateFormat(projectMs, "yyyy年MM月dd日   HH:mm:ss",
					SerializerFeature.DisableCircularReferenceDetect));

			// 附件清单 attchments
			List<Attachment> attachments = attachmentService.findBycorrelationId(productCode, 1);

			request.setAttribute("attchmentsFrom", attachments);
			session.setAttribute("attchmentsFromStr", JSON.toJSONString(attachments));

		}

		return "/project/addProject.jsp";
	}

	@RequestMapping("/addProjectAction")
	public String addProjectAction() {
		return "/project/addProjectProdect.jsp";
	}

	/**
	 * 撤回项目
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/revoke", produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String revokeProject(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {

			Byte roleType = (Byte) session.getAttribute("roleType");

			String userType = (String) session.getAttribute("userType");

			if ((!"0".equals(userType)) && roleType != null && roleType != 0) {

				logger.info("该用户无权限进行项目操作！！");
				resultMap.put("success", false);
				resultMap.put("msg", "该用户无权限进行项目操作！！");

			} else {
				String productCode = request.getParameter("productCode");

				String userAccount = (String) session.getAttribute("userAccount");
				String userName = (String) session.getAttribute("userName");
				projectService.revoke(productCode, userName, userAccount);
				logger.info("项目编号为：" + productCode + "的项目撤回成功");
				resultMap.put("success", true);

			}

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("msg", e.getMessage());
		}

		return JSON.toJSONString(resultMap);
	}

	@RequestMapping("/viewProject")
	public String viewProject(HttpServletRequest request, HttpServletResponse response) {
		try {
			String projectCode = request.getParameter("projectCode");
			if (null != projectCode && !projectCode.equals("")) { // 判断ID是否为空
				ProjectDetailsVo projectVo = projectService.findProjectDetailsVoByProjectCode(projectCode); // 项目信息数据
				List<ProjectPeople> projectPeopleList = projectPeopleService.findByProjectCode(projectCode); // 项目角色数据
				projectVo.setRoles(projectPeopleList);
				request.setAttribute("projectVo", projectVo);

				List<ProjectMenuVo> projectProductList = projectProductService.findVoByProjectCode(projectCode); // 资源清单列表
				request.setAttribute("projectProductListStr",
						JSON.toJSONStringWithDateFormat(projectProductList, "yyyy年MM月dd日   HH:mm:ss"));
				
				List<Attachment> projectAttachmentList = attachmentService.findBycorrelationId(projectCode, 1); // 资源附件数据
				request.setAttribute("projectAttachmentList", projectAttachmentList);

				List<ProjectActionVo> projectActionList = projectActionService
						.findProjectActionVoByProjectCode(projectCode); // 执行清单数据
				request.setAttribute("projectActionListStr",
						JSON.toJSONStringWithDateFormat(projectActionList, "yyyy-MM-dd"));

				List<BackMoneyPlanVo> backMoneyPlanlist = backMoneyPlanService
						.getBackMoneyPlanListByProjectCode(projectCode);// 收款计划列表
				request.setAttribute("backMoneyPlanlistStr",
						JSON.toJSONStringWithDateFormat(backMoneyPlanlist, "yyyy年MM月dd日   HH:mm:ss"));

				List<Attachment> closeAttachmentList = attachmentService.findBycorrelationIdForClose(projectCode, 4); // 结案附件清单
				request.setAttribute("closeAttachmentList", closeAttachmentList);

				List<ProjectApproveLog> projectApproveLogList = projectApproveLogService.findByProjectCode(projectCode); // 审核记录数据
				request.setAttribute("projectApproveLogList", projectApproveLogList);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/project/projectView.jsp";
	}

	/**
	 * 删除项目
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/delet", produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String deletProject(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			HttpSession session = request.getSession();

			Byte roleType = (Byte) session.getAttribute("roleType");

			String userType = (String) session.getAttribute("userType");

			if ((!"0".equals(userType)) && roleType != null && roleType != 0) {

				logger.info("该用户无权限进行项目操作！！");
				resultMap.put("success", false);
				resultMap.put("msg", "该用户无权限进行项目操作！！");

			} else {

				String userAccount = (String) session.getAttribute("userAccount");
				String userName = (String) session.getAttribute("userName");
				String productCode = request.getParameter("productCode");
				logger.info("项目编号为：" + productCode + "的项目删除成功");
				projectService.delet(productCode, userName, userAccount);
				resultMap.put("success", true);
			}

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("msg", e.getMessage());
		}

		return JSON.toJSONString(resultMap);
	}

	/**
	 * 终止项目
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/stopProject", produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String stopProject(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {

			HttpSession session = request.getSession();

			Byte roleType = (Byte) session.getAttribute("roleType");

			String userType = (String) session.getAttribute("userType");

			if ((!"0".equals(userType)) && roleType != null && roleType != 1) {

				logger.info("该用户无权限进行项目操作！！");
				resultMap.put("success", false);
				resultMap.put("msg", "该用户无权限进行项目操作！！");

			} else {

				String userAccount = (String) session.getAttribute("userAccount");
				String userName = (String) session.getAttribute("userName");

				String productCode = request.getParameter("productCode");

				String remark = request.getParameter("remark");

				String flag = request.getParameter("flag");

				if ("1".equals(flag)) {

					boolean result = projectService.stop(productCode);

					resultMap.put("success", result);

				} else {

					projectService.stopProject(productCode, remark, userName, userAccount);
					logger.info("项目编号为：" + productCode + "的项目终止成功");
					resultMap.put("success", true);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("msg", e.getMessage());
		}

		return JSON.toJSONString(resultMap);
	}

	/**
	 * 项目审核
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/exam", produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String examProject(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {

			HttpSession session = request.getSession();

			Byte roleType = (Byte) session.getAttribute("roleType");

			String userType = (String) session.getAttribute("userType");

			if ((!"0".equals(userType)) && roleType != null && roleType != 1) {

				logger.info("该用户无权限进行项目操作！！");
				resultMap.put("success", false);
				resultMap.put("msg", "该用户无权限进行项目操作！！");

			} else {

				String productCode = request.getParameter("productCode");

				String remark = request.getParameter("remark");

				String flag = request.getParameter("flag");

				String userAccount = (String) session.getAttribute("userAccount");
				String userName = (String) session.getAttribute("userName");
				short examResult = Short.valueOf(request.getParameter("examResult"));

				List<ProjectMenuVo> projectMenuVos = projectProductService.findVoByProjectCode(productCode);

				if (!(projectMenuVos.size() > 0)) {

					resultMap.put("success", false);
					resultMap.put("msg", "该项目不存在资源配置！！请添加资源！！");

				} else {

					if ("1".equals(flag)) {

						boolean result = projectService.examDate(productCode, examResult);

						resultMap.put("success", result);
						if (!result) {
							resultMap.put("msg", "该项目资源存在冲突！！请重新编辑！！");
						}

					} else {

						projectService.examProject(productCode, remark, userName, userAccount, examResult);
						logger.info("项目编号为：" + productCode + "的项目审核操作成功");
						resultMap.put("success", true);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("msg", e.getMessage());
		}

		return JSON.toJSONString(resultMap);
	}

	/**
	 * 项目立项
	 * 
	 * @return
	 */
	@RequestMapping(value = "/createProject", produces = "text/html;charset=utf-8")
	@ResponseBody
	public String createProject(@RequestBody ProjectVo projectVo) {
		Json json = new Json();
		try {
			HttpSession session = getSession();
			String project1Str = (String) session.getAttribute("project1Str");

			List<ProjectMenuVo> project1 = null;
			if (StringUtils.isNotBlank(project1Str)) {
				project1 = JSON.parseArray(project1Str, ProjectMenuVo.class);
			}
			String project2Str = (String) session.getAttribute("project2Str");
			String project3Str = (String) session.getAttribute("project3Str");
			String project4Str = (String) session.getAttribute("project4Str");

			ProjectMenuVo project2 = getProjectMenuVo(project2Str);
			ProjectMenuVo project3 = getProjectMenuVo(project3Str);
			ProjectMenuVo project4 = getProjectMenuVo(project4Str);

			List<Attachment> attachments = null;
			String attchmentsFromStr = (String) session.getAttribute("attchmentsFromStr"); // 附件列表
			if (StringUtils.isNotBlank(attchmentsFromStr)) {
				attachments = JSON.parseArray(attchmentsFromStr, Attachment.class);
			}
			/**
			 * 存储小区机构编号
			 */
			Set<String> orgS = new HashSet<>();

			// 用于校验
			List<ProjectMenuVo> listCheck = new ArrayList<>();

			if (project1 != null) {
				projectVo.setProject1(project1);
				for (ProjectMenuVo pmv : project1) {
					orgS.add(pmv.getOrganizationSeq());
					listCheck.add(pmv);
				}
			}
			if (project2 != null) {
				projectVo.setProject2(project2);
				for (ProductMenuVo pdmv : project2.getProjectMenus()) {
					String[] orgStr = pdmv.getAreaIds().split(",");
					for (String str : orgStr) {
						orgS.add(str.trim());
					}
					listCheck.add(project2);
				}
			}
			if (project3 != null) {
				projectVo.setProject3(project3);
				for (ProductMenuVo pdmv : project3.getProjectMenus()) {
					String[] orgStr = pdmv.getAreaIds().split(",");
					for (String str : orgStr) {
						orgS.add(str.trim());
					}
				}
				listCheck.add(project3);
			}
			if (project4 != null) {
				projectVo.setProject4(project4);
				for (ProductMenuVo pdmv : project4.getProjectMenus()) {
					String[] orgStr = pdmv.getAreaIds().split(",");
					for (String str : orgStr) {
						orgS.add(str.trim());
					}
				}
				listCheck.add(project4);
			}
			if (listCheck.size() > 0) {
				if (!projectService.checkDate(listCheck)) {
					throw new RuntimeException("资源被占用,不能立项");
				}
			}else{
				throw new RuntimeException("清单为空,不能立项");
			}

			// 如果要返回前台后台冲突资源,如下
			/*
			 * StringBuilder checkBug=new StringBuilder(); //冲突资源字符串拼接 boolean
			 * checkfalg=true; //默认没有冲突 if (listCheck.size() > 0) {
			 * Map<Integer,Set<String>> listMIS=
			 * projectService.checkDateSave(listCheck); if(listMIS!=null){
			 * Set<String> set2=listMIS.get(2); if(set2!=null&&set2.size()>0){
			 * checkBug.append("煤管:"+pingSet(set2)+"</br>"); checkfalg=false; }
			 * Set<String> set3=listMIS.get(3); if(set3!=null&&set3.size()>0){
			 * checkBug.append("用户运营:"+pingSet(set3)+"</br>"); checkfalg=false;
			 * } Set<String> set4=listMIS.get(4); if(set4!=null&&set4.size()>0){
			 * checkBug.append("电商运营:"+pingSet(set4)+"</br>"); checkfalg=false;
			 * } } } if(!checkfalg){ throw new
			 * RuntimeException(checkBug.toString()); }
			 */

			if (attachments != null)
				projectVo.setAttchments(attachments);
			Map<String, AreaDataRspVo.AreaData> map = areaDataService.getAreaDataByOrganizationSeqs(orgS);

			projectService.saveProject(projectVo, (String) session.getAttribute("userAccount"),
					(String) session.getAttribute("userName"),
					((UserDetailLoginVo) session.getAttribute("userDetailVo")).getDepartId(), map);

			json.setSuccess(true);
			json.setMsg("立项成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		} finally {
			return JSON.toJSONString(json);
		}
	}

	/**
	 * 将set集合里的字符串用","拼接
	 */
	private String pingSet(Set<String> set) {
		StringBuilder sb = new StringBuilder();
		for (String str : set) {
			sb.append("," + str.trim());
		}
		return sb.toString().substring(1);
	}

	private ProjectMenuVo getProjectMenuVo(String projectMenuVo) {
		if (StringUtils.isBlank(projectMenuVo)) {
			return null;
		}
		return JSON.parseObject(projectMenuVo, ProjectMenuVo.class);
	}

	@RequestMapping(value = "/exportDetail")
	public void exportDetail(HttpServletRequest request, HttpServletResponse response) {
		String projectCode = request.getParameter("projectCode");
		String projectType = request.getParameter("projectType");
		List<ProjectMenuVo> projectProductList = new ArrayList<>();
		if (null != projectCode && !projectCode.equals("")) { // 判断ID是否为空

			projectProductList = projectProductService.findVoByProjectCode(projectCode);
		}
		if (null != projectType && projectType.trim().equals("1")) {
			HttpSession session = request.getSession();
			// 1
			String project1Str = (String) session.getAttribute("project1Str");
			if (StringUtils.isNotBlank(project1Str)) {
				List<ProjectMenuVo> list1 = JSON.parseArray(project1Str, ProjectMenuVo.class);
				if (list1 != null && list1.size() > 0) {
					for (ProjectMenuVo projectMenuVo : list1) {
						projectProductList.add(projectMenuVo);
					}
				}
			}
			// 2
			String project2Str = (String) session.getAttribute("project2Str");
			if (StringUtils.isNotBlank(project2Str)) {
				ProjectMenuVo p2 = JSON.parseObject(project2Str, ProjectMenuVo.class);
				projectProductList.add(p2);
			}
			// 3
			String project3Str = (String) session.getAttribute("project3Str");
			if (StringUtils.isNotBlank(project3Str)) {
				ProjectMenuVo p3 = JSON.parseObject(project3Str, ProjectMenuVo.class);
				projectProductList.add(p3);
			}
			// 4
			String project4Str = (String) session.getAttribute("project4Str");
			if (StringUtils.isNotBlank(project4Str)) {
				ProjectMenuVo p3 = JSON.parseObject(project4Str, ProjectMenuVo.class);
				projectProductList.add(p3);
			}

		}
		if (projectProductList != null && projectProductList.size() > 0) {

			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("资源清单");
			/*
			 * ################################表头部分设置START######################
			 * ##########
			 */
			// 表头字体设置
			HSSFFont headerfont = workbook.createFont();
			// 字体加粗
			headerfont.setBoldweight(Font.BOLDWEIGHT_BOLD);

			// 表头style设置
			HSSFCellStyle headerStyle = workbook.createCellStyle();
			// 设置长文本自动换行
			headerStyle.setFont(headerfont);

			// 水平方向对齐
			headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// 垂直方向的对齐方式
			headerStyle.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);

			// 设置边框
			headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);

			// 设置背景颜色
			headerStyle.setFillBackgroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
			/*
			 * ################################表头部分设置END########################
			 * ########
			 */

			/*
			 * ################################普通单元格部分设置START###################
			 * #############
			 */
			// 创建样式
			HSSFFont commonfont = workbook.createFont();
			HSSFCellStyle commonStyle = workbook.createCellStyle();
			commonStyle.setWrapText(true);
			commonStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
			commonStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直居中
			commonStyle.setFont(commonfont);
			// 水平方向对齐
			commonStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// 设置边框
			commonStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			commonStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			commonStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			commonStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			/*
			 * ################################普通单元格部分设置END#####################
			 * ###########
			 */
			// 生成第一行标题行
			// XSSFRow fristHeadRow = sheet.createRow(0);

			// 生成第一行表头行
			String[] headers = { "场次名称", "业务", "类型", "产品清单", "产品规格", "购买数量", "执行开始时间", "执行结束时间", "已选小区" };
			HSSFRow headerRow = sheet.createRow(0);
			HSSFRichTextString text;
			for (int i = 0; i < headers.length; i++) {
				HSSFCell cell = headerRow.createCell(i);
				cell.setCellStyle(headerStyle);
				text = new HSSFRichTextString(headers[i]);
				cell.setCellValue(text);
			}
			int rowNum = 1;
			SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy年MM月dd日");
			SimpleDateFormat fmt2 = new SimpleDateFormat("yyyy年MM月dd日   HH:mm");
			for (ProjectMenuVo pvo : projectProductList) {
				if (pvo.getBusinessType().equals("1")) {
					sheet.addMergedRegion(
							new CellRangeAddress(rowNum, rowNum + (pvo.getProjectMenus().size() - 1), 0, 0));
					sheet.addMergedRegion(
							new CellRangeAddress(rowNum, rowNum + (pvo.getProjectMenus().size() - 1), 1, 1));
					sheet.addMergedRegion(
							new CellRangeAddress(rowNum, rowNum + (pvo.getProjectMenus().size() - 1), 8, 8));
					int row1 = 1;
					for (ProductMenuVo vo : pvo.getProjectMenus()) {
						if (row1 == 1) {
							int s = 0;
							HSSFRow row = sheet.createRow(rowNum++);
							// 场次名称
							HSSFCell cell = row.createCell(s++);
							cell.setCellStyle(commonStyle);
							cell.setCellValue(pvo.getFieldName());
							// 业务
							cell = row.createCell(s++);
							cell.setCellStyle(commonStyle);
							cell.setCellValue(pvo.getBusinessName());
							// 类型
							cell = row.createCell(s++);
							cell.setCellStyle(commonStyle);
							cell.setCellValue(vo.getProductType());
							// 产品清单
							cell = row.createCell(s++);
							cell.setCellStyle(commonStyle);
							cell.setCellValue(vo.getProductMenu());
							// 产品规格
							cell = row.createCell(s++);
							cell.setCellStyle(commonStyle);
							cell.setCellValue(vo.getProductSpec());
							// 购买数量
							cell = row.createCell(s++);
							cell.setCellStyle(commonStyle);
							cell.setCellValue(vo.getBuyNum());
							// 执行开始时间
							cell = row.createCell(s++);
							cell.setCellStyle(commonStyle);
							cell.setCellValue(fmt2.format(vo.getBeginTime()));
							// 执行结束时间
							cell = row.createCell(s++);
							cell.setCellStyle(commonStyle);
							cell.setCellValue(fmt2.format(vo.getEndTime()));
							// 已选小区
							cell = row.createCell(s++);
							cell.setCellStyle(commonStyle);
							cell.setCellValue(pvo.getAreaName());
						} else {
							int s = 2;
							HSSFRow row = sheet.createRow(rowNum++);
							// 场次名称

							// 类型
							HSSFCell cell = row.createCell(s++);
							cell.setCellStyle(commonStyle);
							cell.setCellValue(vo.getProductType());
							// 产品清单
							cell = row.createCell(s++);
							cell.setCellStyle(commonStyle);
							cell.setCellValue(vo.getProductMenu());
							// 产品规格
							cell = row.createCell(s++);
							cell.setCellStyle(commonStyle);
							cell.setCellValue(vo.getProductSpec());
							// 购买数量
							cell = row.createCell(s++);
							cell.setCellStyle(commonStyle);
							cell.setCellValue(vo.getBuyNum());
							// 执行开始时间
							cell = row.createCell(s++);
							cell.setCellStyle(commonStyle);
							cell.setCellValue(fmt2.format(vo.getBeginTime()));
							// 执行结束时间
							cell = row.createCell(s++);
							cell.setCellStyle(commonStyle);
							cell.setCellValue(fmt2.format(vo.getEndTime()));
						}
					}
				} else {
					sheet.addMergedRegion(
							new CellRangeAddress(rowNum, rowNum + (pvo.getProjectMenus().size() - 1), 1, 1));
					int row1 = 1;
					for (ProductMenuVo vo : pvo.getProjectMenus()) {
						if (row1 == 1) {
							int s = 0;
							HSSFRow row = sheet.createRow(rowNum++);
							// 场次名称
							HSSFCell cell = row.createCell(s++);
							cell.setCellStyle(commonStyle);
							cell.setCellValue(pvo.getFieldName());
							// 业务
							cell = row.createCell(s++);
							cell.setCellStyle(commonStyle);
							cell.setCellValue(pvo.getBusinessName());
							// 类型
							cell = row.createCell(s++);
							cell.setCellStyle(commonStyle);
							cell.setCellValue(vo.getProductType());
							// 产品清单
							cell = row.createCell(s++);
							cell.setCellStyle(commonStyle);
							cell.setCellValue(vo.getProductMenu());
							// 产品规格
							cell = row.createCell(s++);
							cell.setCellStyle(commonStyle);
							cell.setCellValue(vo.getProductSpec());
							// 购买数量
							cell = row.createCell(s++);
							cell.setCellStyle(commonStyle);
							cell.setCellValue(vo.getBuyNum());
							// 执行开始时间
							cell = row.createCell(s++);
							cell.setCellStyle(commonStyle);
							cell.setCellValue(fmt1.format(vo.getBeginTime()));
							// 执行结束时间
							cell = row.createCell(s++);
							cell.setCellStyle(commonStyle);
							cell.setCellValue(fmt1.format(vo.getEndTime()));
							// 已选小区
							cell = row.createCell(s++);
							cell.setCellStyle(commonStyle);
							cell.setCellValue(vo.getAreaNames());
						} else {
							int s = 0;
							HSSFRow row = sheet.createRow(rowNum++);
							// 场次名称
							HSSFCell cell = row.createCell(s++);
							s++; // 跳过业务
							cell.setCellStyle(commonStyle);
							cell.setCellValue(pvo.getFieldName());

							// 类型
							cell = row.createCell(s++);
							cell.setCellStyle(commonStyle);
							cell.setCellValue(vo.getProductType());
							// 产品清单
							cell = row.createCell(s++);
							cell.setCellStyle(commonStyle);
							cell.setCellValue(vo.getProductMenu());
							// 产品规格
							cell = row.createCell(s++);
							cell.setCellStyle(commonStyle);
							cell.setCellValue(vo.getProductSpec());
							// 购买数量
							cell = row.createCell(s++);
							cell.setCellStyle(commonStyle);
							cell.setCellValue(vo.getBuyNum());
							// 执行开始时间
							cell = row.createCell(s++);
							cell.setCellStyle(commonStyle);
							cell.setCellValue(fmt1.format(vo.getBeginTime()));
							// 执行结束时间
							cell = row.createCell(s++);
							cell.setCellStyle(commonStyle);
							cell.setCellValue(fmt1.format(vo.getEndTime()));
							// 已选小区
							cell = row.createCell(s++);
							cell.setCellStyle(commonStyle);
							cell.setCellValue(vo.getAreaNames());
						}
					}
				}
			}

			for (int i = 0; i < headers.length; i++) {
				sheet.autoSizeColumn(i);
				if (sheet.getColumnWidth(i) < 20 * 256) {
					sheet.setColumnWidth(i, 20 * 256);
				}
			}
			ExportExcelUtil.downSpcialFile("资料清单.xls", workbook, request, response);
		}

	}

	/**
	 * 下载附件
	 * 
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value = "/download")
	public void downloadAttachment(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		try {
			if (StringUtils.isNotBlank(id)) {
				Attachment attachment = closeCaseService.getAttachmentById(id);
				if (attachment != null) {
					HttpHeaders headers = new HttpHeaders();
					headers.setContentType(MediaType.MULTIPART_FORM_DATA);
					HttpEntity<Resource> httpEntity = new HttpEntity<>(headers);
					ResponseEntity<byte[]> res = restTemplate.exchange(attachment.getFileUrl(), HttpMethod.GET,
							httpEntity, byte[].class);
					if (res.getStatusCode() == HttpStatus.OK) {
						String fileName = attachment.getFileName();
						// 设置下载文件类型
						response.setContentType("application/octet-stream;charset=ISO-8859-1");
						// 设置下载文件头
						String agent = request.getHeader("USER-AGENT");
						if (null != agent && -1 != agent.indexOf("MSIE")
								|| null != agent && -1 != agent.indexOf("Trident")) {// ie
							fileName = java.net.URLEncoder.encode(fileName, "UTF8");
							response.setHeader("Content-Disposition", "filename=\"" + fileName + "\"");
						} else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等
							fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
							response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
						}
						response.getOutputStream().write(res.getBody());
					}
				}
			}
		} catch (Exception e) {
			response.getWriter().println("服务器异常！数据可能存在错误！");
			e.printStackTrace();
		}
	}

}
