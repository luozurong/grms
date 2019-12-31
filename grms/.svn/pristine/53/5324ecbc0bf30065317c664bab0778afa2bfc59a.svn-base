package com.hori.grms.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hori.grms.component.FmsUploadUtil;
import com.hori.grms.enums.ResultCode;
import com.hori.grms.util.PictureUtil;
import com.hori.grms.util.Result;
import com.hori.grms.vo.FilePathVo;
@RequestMapping("fileUpload")
@Controller
public class FileUploadController {
	private final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	//图片文件存放临时文件夹
	private static final String DIRETORY="upload/temp";
	//读取培正文件路径
	private String storePath;
	@Resource(name="fmsUploadUtil")
	private FmsUploadUtil fmsUploadUtil;

    @RequestMapping(value="/upload",method=RequestMethod.POST)
    @ResponseBody
    public String  upload(@RequestParam("file") MultipartFile file,
            HttpServletRequest request){
        Map<String, Object> resResult=new HashMap<String, Object>();
		resResult.put("result", "false");
		//保存的临时文件
		File tempFile=null;
		try {
			//先把文件存到临时文件夹，文件http上传至fms后，再删除临时文件
			//文件存放的路径
			String dirPath=null;
			dirPath= request.getSession().getServletContext().getRealPath(DIRETORY);
			
			String contextPath = request.getSession().getServletContext().getContextPath();
			
			if(null==dirPath){
				//使用项目的相对路径找到绝对路径
				String location = request.getSession().getServletContext().getRealPath(contextPath);
				dirPath = location+"/"+DIRETORY;
			}
			logger.info("文件上传存放目录："+dirPath);
			File directory=new File(dirPath);
			if(!directory.exists()){
				directory.mkdirs();
			}
			//文件原文件名
			String originalFilename=file.getOriginalFilename();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
			//问了防止并发上传文件名冲突，修改文件名，加上时间
			
			//文件后缀
			String fileSuffix=originalFilename.substring(originalFilename.indexOf(".")+1);
			//String tempFileName=PictureUtil.generateFileName();
			//新文件名
			String newFileName=originalFilename.substring(0,originalFilename.indexOf("."))+"("+sdf.format(new Date())+")"+"."+fileSuffix;
			
			//原图文件路径
			String filepath=dirPath+File.separator+newFileName;
			logger.info("临时文件路径："+filepath);
			tempFile=new File(filepath);
			file.transferTo(tempFile); //保存文件
			//文件上传fms系统
			Map fmsUpResult=this.fmsUploadUtil.transfilesToFmsNoRename(new File[]{tempFile});
			//上传到fms的结果
			String fmsUpResultStr=(String) fmsUpResult.get("result");
			if(StringUtils.isNotBlank(fmsUpResultStr)&&"true".equals(fmsUpResultStr)){
				//######上传成功
				List<FilePathVo> filePathVos=(List<FilePathVo>) fmsUpResult.get("list");
				//文件访问路径
				resResult.put("result", "true");
				resResult.put("path", filePathVos.get(0).getPath());
				resResult.put("fileName", newFileName);//文件名
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//删除临时文件
			if( null!=tempFile && tempFile.exists()){
				tempFile.delete();
			}
		}
		return JSONObject.toJSONString(resResult);

    }

  /*  @RequestMapping(value="/uploadMany",method=RequestMethod.POST)
    @ResponseBody
    public Result<Map<String,Object>>  uploadMany(@RequestParam("file") MultipartFile[] file,String path,
            HttpServletRequest request){

    	String contextPath = request.getContextPath();//"/SpringMvcFileUpload"
        String servletPath = request.getServletPath();//"/gotoAction"
        String scheme = request.getScheme();//"http"
        if (file.length>0) {
            for(int i=0;i<file.length;i++){
            	if(!file[i].isEmpty()){
            	String fileName = file[i].getOriginalFilename();
            	String allPath=storePath+"//"+path;
                File filepath = new File(allPath, fileName);
                if (!filepath.getParentFile().exists()) {
                    filepath.getParentFile().mkdirs();//如果目录不存在，创建目录
                }

                try {
                	file[i].transferTo(new File(allPath+File.separator+fileName));//把文件写入目标文件地址

                } catch (Exception e) {
                    e.printStackTrace();
                    return new Result<Map<String,Object>>(ResultCode.ERROR);

                }

            }
            }
            
            return new Result<Map<String,Object>>(ResultCode.SUCCESS);

        }else {

            return new Result<Map<String,Object>>(ResultCode.ERROR);
        }

    }*/

	public String getStorePath() {
		return storePath;
	}

	public void setStorePath(String storePath) {
		this.storePath = storePath;
	}
    
}