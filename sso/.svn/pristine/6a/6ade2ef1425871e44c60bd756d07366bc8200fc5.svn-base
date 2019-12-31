package com.jlit.uaas.oauth2.servlet;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
/**
 * 文件上传servlet
 * @author laizs
 * @time 2014-5-6 下午5:18:26
 * @file FileImageUploadServlet.java
 */
public class FileUploadServlet extends HttpServlet {
	private final static Logger  logger=Logger.getLogger(FileUploadServlet.class);
	private static final long serialVersionUID = 1L;
	private ServletFileUpload upload;
	private final long MAXSize = 4194304*2L;//4*2MB
	//文件存放的路径
	private String filedir=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploadServlet() {
        super();
    }
	/**
	 * 设置文件上传的初始化信息
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		FileItemFactory factory = new DiskFileItemFactory();// Create a factory for disk-based file items
		this.upload = new ServletFileUpload(factory);// Create a new file upload handler
		this.upload.setSizeMax(this.MAXSize);// Set overall request size constraint 4194304
		filedir=config.getServletContext().getRealPath("upload");
		logger.info("文件上传存放的路径："+filedir);
		File file=new File(filedir);
		if(!file.exists()){
			file.mkdir();
		}
	}
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		try {
			List<FileItem> items = this.upload.parseRequest(request);
			if(items!=null	&& !items.isEmpty()){
				for (FileItem fileItem : items) {
					String filename=fileItem.getName();
					String filepath=filedir+File.separator+filename;
					logger.info("接受文件上传，文件名："+filename+",文件保存路径："+filepath);
					File file=new File(filepath);
					InputStream inputSteam=fileItem.getInputStream();
					BufferedInputStream fis=new BufferedInputStream(inputSteam);
				    FileOutputStream fos=new FileOutputStream(file);
				    int f;
				    while((f=fis.read())!=-1)
				    {
				       fos.write(f);
				    }
				    fos.flush();
				    fos.close();
				    fis.close();
					inputSteam.close();
					logger.info("文件上传成功："+filename);
				}
			}
			out.write("success");
		} catch (FileUploadException e) {
			e.printStackTrace();
			out.write("fail:"+e.getMessage());
		}
	}

}
