package com.hori.grms.controller;

import java.io.File;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hori.grms.enums.ResultCode;
import com.hori.grms.model.SchemeInfo;
import com.hori.grms.service.SchemeInfoService;
import com.hori.grms.util.UUIDGenerator;

/**
 * 提案管理
 * @author wangzhen
 *
 */
@RequestMapping("/schemeInfo")
@Controller
public class SchemeInfoController extends BaseController {
	
	@Value("${fms_server_address}")
	private String fileUpLoadUrl;
	
	@Autowired
	private SchemeInfoService schemeInfoService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpServletResponse response;
	
	private static final String DIR = "upload/temp"; 
	
	/**
	 * 提案列表页面
	 * @return
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET})
	public String schemeInfoList() {
		return "/schemeInfo/schemeInfoList.jsp";
	}
	
	/**
	 * 获取提案列表数据
	 * @param keyword
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "", method = {RequestMethod.GET})
	@ResponseBody
	public Object getSchemeInfos(String keyword, Integer pageNumber, Integer pageSize) {
		Map<String, Object> result = new HashMap<>();
		try {
			int total = schemeInfoService.getSchemeInfoCount(keyword);
			List<SchemeInfo> infos = schemeInfoService.getSchemeInfos(keyword, pageNumber, pageSize);
			result.put("code", ResultCode.SUCCESS.getCode());
			result.put("total", total);
			result.put("data", infos);
		} catch (Exception e) {
			result.put("code", ResultCode.ERROR.getCode());
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 上传提案页面
	 * @return
	 */
	@RequestMapping(value = "/add", method = {RequestMethod.GET})
	public String addSchemeInfo() {
		return "/schemeInfo/addSchemeInfo.jsp";
	}
	
	/**
	 * 下载附件
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value = "/download", method = {RequestMethod.GET, RequestMethod.POST})
	public void downloadAttachment(String id) throws Exception {
		try {
			if(StringUtils.isNotBlank(id)) {
				SchemeInfo si = schemeInfoService.getSchemeInfoById(id);
				if(si != null) {
					HttpHeaders headers = new HttpHeaders();
					headers.setContentType(MediaType.MULTIPART_FORM_DATA);
					HttpEntity<Resource> httpEntity = new HttpEntity<>(headers);
					ResponseEntity<byte[]> res = restTemplate.exchange(si.getUrl(), HttpMethod.GET, 
							httpEntity, byte[].class);
					if(res.getStatusCode() == HttpStatus.OK) {
						response.reset();
						response.setContentType("APPLICATION/OCTET-STREAM");
						response.setCharacterEncoding("utf-8");
						response.setHeader("Content-Disposition", "attachment;filename=" + 
								URLEncoder.encode(si.getUrlName(), "UTF-8"));
						byte[] result = res.getBody();
						if(result == null) {
							result = new byte[0];
						}
						response.getOutputStream().write(result);
					}
				}
			}
		} catch (Exception e) {
			response.reset();
			response.getWriter().println("server error");
			e.printStackTrace();
		}
	}
	
	/**
	 * 保存
	 * @param schemeInfo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/save", method = {RequestMethod.POST})
	@ResponseBody
	public Object save(SchemeInfo schemeInfo, @RequestParam("file") MultipartFile file) {
		Map<String, Object> result = new HashMap<>();
		File tempFile = null;
		try {
			if(!schemeInfoService.isExistName(schemeInfo.getName())) {
				String url = null;
				String createrAccount = (String)request.getSession().getAttribute("userAccount");
				String fileName = file.getOriginalFilename();
				String realPath = request.getSession().getServletContext().getRealPath(DIR);
				String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
				String dateString = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
				String randomString = String.format("%04d", RandomUtils.nextInt());
				File dir = new File(realPath);
				if(!dir.exists()) {
					dir.mkdirs();
				}
				String newFileName = dateString + randomString + "." + suffix;
				String filePath = realPath + File.separator + newFileName;
				//临时文件
				tempFile = new File(filePath);
				file.transferTo(tempFile);
				//转存到fms服务器
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.MULTIPART_FORM_DATA);
				FileSystemResource fsr = new FileSystemResource(tempFile);
				MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
				params.add("file", fsr);
				String fmsUrl = fileUpLoadUrl + "/filesUpload";
				HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(params, headers);
				String resultJson = restTemplate.postForObject(fmsUrl, requestEntity, String.class);
				ObjectMapper om = new ObjectMapper();
				Map<String,Object> resultMap = om.readValue(resultJson, Map.class);
				if(resultMap != null && !resultMap.isEmpty()) {
					String res = (String) resultMap.get("result");
					if(StringUtils.isNotBlank(res) && "0".equals(res)) {
						List<Map<String,String>> paths = (List<Map<String,String>>) resultMap.get("list");
						if(paths != null && !paths.isEmpty()) {
							url = paths.get(0).get("path");
						}
					}
				}
				schemeInfo.setId(UUIDGenerator.generate());
				schemeInfo.setUrl(url);
				schemeInfo.setUrlName(fileName);
				schemeInfo.setCreaterAccount(createrAccount);
				schemeInfoService.save(schemeInfo);
				result.put("code", ResultCode.SUCCESS.getCode());
			}else {
				result.put("code", 1);
			}
		} catch (Exception e) {
			result.put("code", ResultCode.ERROR.getCode());
			e.printStackTrace();
		} finally {
			if(tempFile != null && tempFile.exists()) {
				tempFile.delete();
			}
		}
		return result;
	}
	
	/**
	 * 删除/批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/delete", method = {RequestMethod.POST})
	@ResponseBody
	public Object delete(String ids) {
		Map<String, Object> result = new HashMap<>();
		try {
			schemeInfoService.delete(Arrays.asList(ids.split(",")));
			result.put("code", ResultCode.SUCCESS.getCode());
		} catch (Exception e) {
			result.put("code", ResultCode.ERROR.getCode());
			e.printStackTrace();
		}
		return result;
	}
}
