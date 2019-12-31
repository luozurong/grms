package com.hori.adms.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
/**
 * 文件上传工具类
 * 
 * @author wenl 2012-05-16
 * 
 */
@SuppressWarnings("restriction")
public class FileUploadUtil {
	/**
	 * 生成的缩略图的名字
	 */
	public static String FILE_NAME;
	
	/**
	 * 容许上传文件大小(KB)
	 */
	public static final int MAX_FILE_SIZE = 300;

	private static final int BUFFER_SIZE = 16 * 1024;

	/**
	 * 获取文件路径 以日期命名 年-月-日
	 * 
	 * @author wenl 2012-05-16
	 */
	public static String getFilePath(String subPath) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String path = "/upload/" + subPath + "/" + sdf.format(date) + "/";
		return path;
	}

	/**
	 * getIMGName:获取文件缩略图名称
	 * 
	 * @author wenl 20102-05-16
	 */
	public static String getIMGName(String fileName) {
		// 生成缩略图的文件名<——START——>
		int index = fileName.lastIndexOf(".");
		String newFile = "";
		if (index != -1)
			newFile = String.valueOf(System.currentTimeMillis())
					+ fileName.substring(index);
		else
			newFile = String.valueOf(System.currentTimeMillis());
		// 生成缩略图的文件名<——END——>
		return newFile;
	}

	/**
	 * 上传文件
	 * 
	 * @author wenl 2012-05-16
	 * @return
	 */
	public static void fileUpload(File file, String fileFileName, String path) {
		if (file != null) {
			// 替换目录路径标志“/”为“\\”
			File fileDir = new File(path.replace("\\", "/"));
			// 路径不存在及创建路径
			if (!fileDir.exists())
				fileDir.mkdirs();

			BufferedOutputStream bos = null;
			BufferedInputStream bis = null;

			try {
				FileInputStream fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				FileOutputStream fos = new FileOutputStream(new File(fileDir,
						fileFileName));
				bos = new BufferedOutputStream(fos);
				// 传输字节数
				byte[] buf = new byte[4096];
				int len = -1;
				while ((len = bis.read(buf)) != -1) {
					bos.write(buf, 0, len);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (null != bis)
						bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					if (null != bos)
						bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 创建图片缩略图
	 * 
	 * @author wenl 2012-05-16
	 * @param filePath
	 *            文件路径
	 * @param fileName
	 *            文件名，原文件文件名
	 * @param width
	 *            缩放宽度
	 * @param high
	 *            缩放高度
	 *//*
	@SuppressWarnings("restriction")
	public static Map<String, Boolean> createIMG(String filePath,
			String fileName, double width, double high) throws Exception {
		File oldFile = new File(filePath, fileName);
		// 收集生成缩略图出现的信息
		Map<String, Boolean> infoMap = new HashMap<String, Boolean>();
		infoMap.put("GIF_ERROR", false);
		infoMap.put("NOT_IMG", false);
		infoMap.put("IMG_TOOLARGE", false);
		infoMap.put("IMG_SUCCESS", false);

		if (oldFile.isFile()) {
			// 判断图片的类型
			String imgType = judgeImageType(filePath + fileName);
			if (imgType.equals("") || imgType.equals("gif")) {
				infoMap.put("GIF_ERROR", true);
			} else {
				Image img = javax.imageio.ImageIO.read(oldFile);
				BufferedImage tagImg = new BufferedImage((int) width,
						(int) ((double) img.getHeight(null) * width / img
								.getWidth(null)), BufferedImage.TYPE_INT_RGB);
				// 将文件类型改为图片类型，避免误该后缀的情况
				if (null == tagImg || tagImg.getWidth() == 0
						|| tagImg.getHeight() == 0) {
					infoMap.put("NOT_IMG", true);
				}
				tagImg.getGraphics()
						.drawImage(
								img.getScaledInstance((int) width,
										(int) ((double) img.getHeight(null)
												* width / img.getWidth(null)),
										Image.SCALE_SMOOTH), 0, 0, null);
				FILE_NAME = getIMGName(fileName);
				FileOutputStream out = new FileOutputStream(filePath
						+ FILE_NAME);
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				encoder.encode(tagImg);

				// 判断生成后图片大小不能大于500KB
				File finallFile = new File(filePath + FILE_NAME);
				if (finallFile != null && finallFile.exists()
						&& finallFile.isFile()) {
					if (finallFile.length() / 1024 > 500) {
						finallFile.delete();
						out.close();
						infoMap.put("IMG_TOOLARGE", true);
					}
				}
				out.close();
			}
			infoMap.put("IMG_SUCCESS", true);
		} else {
			throw new Exception(oldFile + "不是图片文件，上传失败");
		}
		return infoMap;
	}
*/
	/**
	 * 根据头文件信息，判断图片的类型
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 *//*
	public static String judgeImageType(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		// 取得文件流的可用的字节数
		int leng = fis.available();
		BufferedInputStream buff = new BufferedInputStream(fis);
		byte[] mapObj = new byte[leng];
		buff.read(mapObj, 0, leng);
		String type = "";
		ByteArrayInputStream bais = null;
		MemoryCacheImageInputStream mcis = null;
		try {
			bais = new ByteArrayInputStream(mapObj);
			mcis = new MemoryCacheImageInputStream(bais);
			Iterator itr = ImageIO.getImageReaders(mcis);
			while (itr.hasNext()) {
				ImageReader reader = (ImageReader) itr.next();
				if (reader instanceof GIFImageReader) {
					type = "gif";
				} else if (reader instanceof JPEGImageReader) {
					type = "jpg";
				} else if (reader instanceof PNGImageReader) {
					type = "png";
				} else if (reader instanceof BMPImageReader) {
					type = "bmp";
				}
			}
		} finally {
			if (mcis != null) {
				try {
					mcis.close();
				} catch (IOException ioe) {
				}
			}
			if (bais != null) {
				try {
					bais.close();
				} catch (IOException ioe) {
				}
			}
			if (buff != null) {
				try {
					buff.close();
				} catch (IOException ioe) {
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException ioe) {
				}
			}
		}
		return type;
	}
	*/
	/**
	 * 检测图片是否合法
	 * @return
	 * @throws Exception 
	 * @author viliam 2014-01-03
	 *//*
	public static Map<String, Boolean>  checkImg(String filePath,
			String fileName, double width, double high) throws Exception{
		
		File oldFile = new File(filePath, fileName);
		// 收集生成缩略图出现的信息
		Map<String, Boolean> infoMap = new HashMap<String, Boolean>();
		infoMap.put("GIF_ERROR", false);
		infoMap.put("NOT_IMG", false);
		infoMap.put("IMG_TOOLARGE", false);
		infoMap.put("IMG_SUCCESS", false);

		if (oldFile.isFile()) {
			
			// 判断图片的类型
			String imgType = judgeImageType(filePath + "/" + fileName);
			
			if (imgType.equals("") || imgType.equals("gif")) {
				infoMap.put("GIF_ERROR", true);
			} else {
				
				Image img = javax.imageio.ImageIO.read(oldFile);
				BufferedImage tagImg = new BufferedImage((int) width,
						(int) ((double) img.getHeight(null) * width / img
								.getWidth(null)), BufferedImage.TYPE_INT_RGB);
				
				// 将文件类型改为图片类型，避免误该后缀的情况
				if (null == tagImg || tagImg.getWidth() == 0
						|| tagImg.getHeight() == 0) {
					infoMap.put("NOT_IMG", true);
				}

				// 判断生成后图片大小不能大于300KB
				if (oldFile.length() / 1024 > MAX_FILE_SIZE) {
					oldFile.delete();
					infoMap.put("IMG_TOOLARGE", true);
				}
			}
			infoMap.put("IMG_SUCCESS", true);
		}
		return infoMap;
	}*/

	/**
	 * 删除文件
	 * 
	 * @author wenl 2012-05-16
	 * @param path
	 * @return
	 */
	public static boolean deleteFile(String path) {
		File file = new File(path.replace("\\", "/"));
		try {
			if (file.exists() && file.isFile())
				file.delete();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

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
	 * 删除文件或者文件夹
	 * 
	 * @param filepath
	 */
	public static void delete(String filepath) {
		File file = new File(filepath);
		if (null != file && file.exists()) {
			if (file.isFile()) {
				file.delete();
			} else if (file.isDirectory()) {
				File files[] = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					FileUploadUtil.delete(files[i].getAbsolutePath());
				}
			}
		}
	}

	/**
	 * 获取文件后缀
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getExtention(String fileName) {
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(pos);
	}

	/**
	 * 生成唯一的图片名 uuid_findName
	 * 
	 * @param fileName
	 *            文件名,含后缀
	 * @return
	 */
	public static String generateFileName(String fileName) {
		StringBuffer newFileName = new StringBuffer(UUID.randomUUID()
				.toString());
		String extension = getExtention(fileName);
		newFileName.append(extension);
		return newFileName.toString();
	}

	/**
	 * 上传文件
	 * 
	 * @param request
	 * @param folderPath
	 *            保存文件的相对路径
	 * @param fileName
	 *            文件名,含后缀
	 * @param uploadFile
	 *            action接收到File对象
	 * @return 保存到数据库的图片路径
	 */
	public static String upload(HttpServletRequest request, String folderPath,
			String fileName, File uploadFile) {
		String iFileName = FileUploadUtil.generateFileName(fileName);
		String path = request.getSession().getServletContext()
				.getRealPath(folderPath)
				+ "/";
		File fileFolder = new File(path);
		if (!fileFolder.exists()) {
			fileFolder.mkdirs();
		}
		File file = new File(path + iFileName);
		FileUploadUtil.copy(uploadFile, file);
		return (folderPath + iFileName);
	}

	/**
	 * 获取文件大小
	 * @param path
	 * @return
	 */
	public static int getSize(String path) {
		File file = new File(path);
		if (file.exists() && file.isFile()) {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(file);
				return fis.available() / 1024;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (fis != null)
						fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}
	
	static char hexdigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8','9', 'a', 'b', 'c', 'd', 'e', 'f' };
     /**
      * 对文件全文生成MD5摘要
      * @param file
      *            要加密的文件
      * @return MD5摘要码
      */
     public static String getFileMD5(File file) {
        FileInputStream fis = null;
        try {
          MessageDigest md = MessageDigest.getInstance("MD5");
          fis = new FileInputStream(file);
          byte[] buffer = new byte[2048];
          int length = -1;
          long s = System.currentTimeMillis();
          while ((length = fis.read(buffer)) != -1) {
             md.update(buffer, 0, length);
          }
          byte[] b = md.digest();
          return byteToHexString(b);
          // 16位加密

          // return buf.toString().substring(8, 24);

        } catch (Exception ex) {

          ex.printStackTrace();

          return null;

        } finally {

          try {

             fis.close();

          } catch (IOException ex) {

             ex.printStackTrace();

          }

        }
     }

     /**
      * 把byte[]数组转换成十六进制字符串表示形式
      * @param tmp    要转换的byte[]
      * @return 十六进制字符串表示形式
      */
     public static String byteToHexString(byte[] tmp) {
        String s;
        // 用字节表示就是 16 个字节
        char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
        // 所以表示成 16 进制需要 32 个字符
        int k = 0; // 表示转换结果中对应的字符位置
        for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
          // 转换成 16 进制字符的转换
          byte byte0 = tmp[i]; // 取第 i 个字节
          str[k++] = hexdigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换, 
          // >>> 为逻辑右移，将符号位一起右移
          str[k++] = hexdigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
        }
        s = new String(str); // 换后的结果转换为字符串
        return s;
     }
	
	

    //首字母转小写
	public static String toLowerCaseFirstOne(String s){
        if(Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }
	
	//首字母转大写(方式一)
    public static String toUpperCaseFirstOne(String s){
        if(Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }
    
    //首字母转大写(方式二)
    public static String toUpperCaseFirstOneOther(String s){
	    StringBuilder sb = new StringBuilder(s);
	    sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
	    s = sb.toString(); 
	    return s;
    }
    
	
	
	/**
	 * 浏览器下载的形式导出Excel文件(方法抽取)
	 * @param fileName
	 * @param workbook2003
	 * @param workbook2007
	 */
	private static void generateExcelByDownload(String fileName, HSSFWorkbook workbook2003, XSSFWorkbook workbook2007,HttpServletResponse response) {
		//以下载的形式导出EXCEL2003或2007
		//HttpServletResponse response = ServletActionContext.getResponse();
		response.reset();
		response.setContentType("application/force-download");
		//根据不同浏览器输出
		String agent = ServletActionContext.getRequest().getHeader("User-Agent");
		boolean isMSIE = (agent != null && agent.indexOf("MSIE") != -1);
		try {
			if (isMSIE) {
				fileName = URLEncoder.encode(fileName, "UTF-8");
			} else {
				fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
			}
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		response.addHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
		OutputStream os = null;
		
		try {
			os = response.getOutputStream();
			if(null != workbook2003){
				//输出excel2003文件流
				workbook2003.write(os);
			}else{
				workbook2007.write(os);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
