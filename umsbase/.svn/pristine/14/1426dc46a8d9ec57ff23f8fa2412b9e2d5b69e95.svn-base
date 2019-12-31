package com.hori.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.nio.channels.FileChannel;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.hori.model.Attchment;
import com.hori.pageModel.Json;
import com.hori.service.AttchmentService;
import com.hori.util.HttpClientUtil;
import com.hori.util.PictureUtil;
import com.hori.utils.RandomUtil;
import com.hori.utils.StaticValue;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;


/**
 * 用户ACTION
 * 
 * @author zhangdaihao
 * 
 */
@Action(value = "attchmentAction")
public class AttchmentAction extends BaseAction  {
	
	private static final Log log = LogFactory.getLog(AttchmentAction.class);
	
	private static final String DIRETORY = "upload/attchment";
	
	//private static String fmsServerUrl = "http://192.168.0.5:8090/fms";
	private static String fmsServerUrl = StaticValue.FMS_SERVER_URL;
	@Autowired
	private AttchmentService attchmentService;
	
	private static final int FILE_MAX_LENGTH = 500 * 1024 * 1024;
	
    // 封装上传文件域的属性  
    private File upload;  
   
    //这个uploadFileName，uploadContentType。如果File 属性名xxx(private File xxx;) 。那这个必须是xxxFileName, xxxContentType。然后也是分别给set 方法就可以。
    private String uploadContentType; // 文件的内容类型  
    
    private String uploadFileName; // 上传文件名  
       

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	public String page(){
		return "page";
	}
	
	public void upload(){
		Json json = new Json();
		json.setSuccess(false);
		File tempFile = null;
		String savePath="";
		JSONObject obj=null;
		try {
			//创建存储名字，避免上传名字相同的情况 
			if(FILE_MAX_LENGTH < this.getUpload().length()){
				log.error("上传文件过大，文件名[" + this.getUpload().getName() + "] 文件大小[" + this.getUpload().length() + "] 最大允许大小[" + FILE_MAX_LENGTH + "]");
				json.setMsg("上传文件过大,最大允许:" + FILE_MAX_LENGTH / 1024 / 1024 + "mb");
				return ;
			}
			//System.out.println("上传文件过大，文件名[" + this.getUpload().getName() + "] 文件大小[" + this.getUpload().length() + "] 最大允许大小[" + FILE_MAX_LENGTH + "]");
			
			String dirPath = null;
			dirPath = getRequest().getSession().getServletContext().getRealPath(DIRETORY);
			
			File directory = new File(dirPath);
			if (!directory.exists()) {
				directory.mkdir();
			}
			String fileName = this.getUploadFileName();
			// 文件后缀
			String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);
			String tempFileName = PictureUtil.generateFileName();
			// 新文件名
			String newFileName = tempFileName + "." + fileSuffix;
			// 原图文件路径
			String filepath = dirPath + File.separator + newFileName;
			//System.out.println("上传文件过大，文件名[" + fileName + "] 文件大小[" + this.getUpload().length() + "] 最大允许大小[" + FILE_MAX_LENGTH + "]");
			
			tempFile = new File(filepath);
			FileUtils.copyFile(this.getUpload(), tempFile);
			savePath = HttpClientUtil.transFileToFms(new File[] { tempFile }, fmsServerUrl + "/filesUploadNoRename");
			System.out.println(savePath);
			if(true == StringUtils.isNoneBlank(savePath)){
				obj = JSONObject.fromObject(savePath);
				obj.put("fileFileName", fileName);
				JSONArray pathArray =  obj.getJSONArray("list");
				 Attchment attchment = new Attchment();
			     attchment.setLocation((String) pathArray.getJSONObject(0).get("path"));
			     attchment.setName(this.getUploadFileName());
			     attchment.setSize((int) this.getUpload().length());
			     attchment.setType(fileSuffix);
			     String result = attchmentService.save(attchment);
			     if(true == StringUtils.isNoneBlank(result)){
				     obj.put("attchmentId", result);
				     json.setObj(obj);
			    	 json.setSuccess(true);
			    	 json.setMsg("文件上传成功");
			    	 return;
			     }
			}
			
			json.setMsg("文件上传失败");
		} catch (Throwable e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			log.error(e.toString(),e);
			json.setMsg("文件上传错误");
		}finally{
			super.writeJson(json);
		}
	}
}










