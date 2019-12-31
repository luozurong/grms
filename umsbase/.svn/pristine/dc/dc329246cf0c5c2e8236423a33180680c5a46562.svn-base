package com.hori.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;


public class FileUtil {
	private static final int BUFFER_SIZE = 16 * 1024;

	/**
	 * 自己封装的一个把源文件对象复制成目标文件对象
	 * 
	 * @param src
	 * @param dst
	 */
	public static void copy(File src, File dst) {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(dst),
					BUFFER_SIZE);
			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 删除文件
	 * @param filepath
	 */
	public static void delete(String filepath){
		File file=new File(filepath);
		if(null!=file && file.exists()){
			file.delete();
		}
	}
	
	/**
	 * 在当前file文件的路径下复制该文件改后缀
	 * @param src
	 * @param suffix
	 * @return
	 * @throws Exception
	 */
	public static File changeSuffix(File src,String suffix) throws Exception{
		if(null==src&&!src.exists())
			return null;
		String cpaht=src.getCanonicalPath();
		   if(cpaht.indexOf(".")>=0)     
	          {     
			   cpaht   =   cpaht.substring(0,cpaht.lastIndexOf("."));     
	          }     
		File destFile=new File(cpaht+suffix);
		FileUtils.copyFile(src,destFile );
		return destFile;
	}
	
	public static File[] changeSuffix(File[] src,String[] suffix) throws Exception{
		if(null==src||null==suffix||src.length!=suffix.length){
			return null;
		}
		File[] resFiles= new File[src.length];
		for(int i=0;i<src.length;i++){
			String cpaht=src[i].getCanonicalPath();
		    if(cpaht.indexOf(".")>=0){     
			   cpaht = cpaht.substring(0,cpaht.lastIndexOf("."));     
            } 
		    File destFile=new File(cpaht+suffix[i]);
			FileUtils.copyFile(src[i],destFile );
			resFiles[i]=destFile;
		}
		
		
		return resFiles;
	}
	
	public static void main(String[] args) {
		copy(new File("c:\\a.txt"), new File("c:\\c.txt"));
	}
	
	
}
