package com.hori.adms.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.hori.pageModel.AdMaterialTotalPage;
import com.hori.pageModel.TerminalPage;


public class ExportexcelWithTerminal {

	/**
	 * 导出数据列表保存至 excel2003
	 * @param fileName
	 * @param list
	 */
	public static void generateclsjExcelFor2003(String fileName, List<TerminalPage> list,
			HttpServletResponse response,Map<String, String> pcatownMap){
		HSSFWorkbook workbook2003 = new HSSFWorkbook();
		HSSFSheet sheet = workbook2003.createSheet("终端信息表");
		int qtyLong = 8;
		//对列设置宽度为80像素
	    sheet.setColumnWidth(0, 80* 80);   
	    sheet.setColumnWidth(1, 80 * 80);   
	    sheet.setColumnWidth(2, 80 * 120);  
  	    sheet.setColumnWidth(3, 80 * 80); 
  	    sheet.setColumnWidth(4, 80* 120);  	
  	    sheet.setColumnWidth(5, 80 * 120);  
	    sheet.setColumnWidth(6, 80 * 80); 
	    sheet.setColumnWidth(7, 80* 120); 
  	    
	    //---------------------------------------------------
	

	    //生成第一行标题行
	    //XSSFRow fristHeadRow = sheet.createRow(0);
	        
	    //生成第二行表头行
	    HSSFRow secondHeadRow = sheet.createRow(0);
			
	    /*################################表头部分设置START################################*/
	    // 创建样式
	    HSSFFont headerfont = workbook2003.createFont();
	    HSSFCellStyle headerStyle = workbook2003.createCellStyle();
	    // 字体加粗
	    headerfont.setBoldweight(Font.BOLDWEIGHT_BOLD);   

	    // 设置长文本自动换行
	    headerStyle.setWrapText(true);
	    headerStyle.setFont(headerfont);
			
	    //水平方向对齐
	    headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	    // 垂直方向的对齐方式
	    headerStyle.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
			
	    //设置边框
	    headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	    headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
	    headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
	    headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			
	    //设置背景颜色
	    headerStyle.setFillBackgroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
	    /*################################表头部分设置END################################*/  
			
	    /*################################普通单元格部分设置START################################*/
	    // 创建样式
	    HSSFFont commonfont = workbook2003.createFont();
	    HSSFCellStyle commonStyle = workbook2003.createCellStyle();
	    commonStyle.setWrapText(true);
	    commonStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中    
	    commonStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中 
	    commonStyle.setFont(commonfont);
	    //水平方向对齐
	    commonStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	    //设置边框
	    commonStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	    commonStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
	    commonStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
	    commonStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	    /*################################普通单元格部分设置END################################*/
	    for(int i = 0 ; i < qtyLong ; i++){
	    	//第二行生成单元格
	    	HSSFCell cell = secondHeadRow.createCell(i);
	    	//应用表头样式
	    	cell.setCellStyle(headerStyle);
	    	switch (i) {
	    	case 0:
				cell.setCellValue("小区名称");
				break;
			case 1:
				cell.setCellValue("省 ");
				break;
			case 2:
				cell.setCellValue("市");
				break;
			case 3:
				cell.setCellValue("区");
				break;
			case 4:
				cell.setCellValue("终端名称");
				break;
			case 5:
				cell.setCellValue("终端序列号");
				break;
			case 6:
				cell.setCellValue("同步是否成功");
				break;
			case 7:
				cell.setCellValue("成功时间");
				break;
			default:
				break;
			}
	    }
	    if(list != null && list.size() > 0) {
	    	//从工作表第二行开始为数据内容部分
	    	for(int j = 0 ; j < list.size() ; j++){
	    		TerminalPage data = list.get(j);
	    		//生成行
	    		HSSFRow row = sheet.createRow(j+1);
				
				HSSFCell descript = row.createCell(0);
				descript.setCellValue(data.getCommunityName());
				descript.setCellStyle(commonStyle);
				
				
				HSSFCell province = row.createCell(1);
				province.setCellValue(pcatownMap.get(data.getProvince()));
				province.setCellStyle(commonStyle);
				
				HSSFCell city = row.createCell(2);
				city.setCellValue(pcatownMap.get(data.getCity()));
				city.setCellStyle(commonStyle);
				
				HSSFCell country = row.createCell(3);
				country.setCellValue(pcatownMap.get(data.getCountry()));
				country.setCellStyle(commonStyle);
				
				HSSFCell terminalName = row.createCell(4);
				terminalName.setCellValue(data.getTerminalName());
				terminalName.setCellStyle(commonStyle); 	
				
				HSSFCell terminalSerial = row.createCell(5);
				terminalSerial.setCellValue(data.getTerminalSerial());
				terminalSerial.setCellStyle(commonStyle);
				
				if(data.getStatuType()==1){
					HSSFCell status = row.createCell(6);
					status.setCellValue("是");
					status.setCellStyle(commonStyle); 
					
					HSSFCell time = row.createCell(7);
					time.setCellValue(data.getStatuTime());
					time.setCellStyle(commonStyle); 	
				}else{
					HSSFCell status = row.createCell(6);
					status.setCellValue("否");
					status.setCellStyle(commonStyle); 
					
					HSSFCell time = row.createCell(7);
					time.setCellValue("");
					time.setCellStyle(commonStyle); 
				}
				
			  	   
	    	}
			
			
			//以浏览器下载的形式导出Excel文件
	    	downFile(fileName,workbook2003, null,response);
	    }
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
	
	  public  static void downFile(String fileName, HSSFWorkbook workbook2003, XSSFWorkbook workbook2007,HttpServletResponse response){
		ServletOutputStream output = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
		 response = ServletActionContext.getResponse();
		// 设置下载文件类型
		response.setContentType("application/octet-stream;charset=ISO-8859-1");
		// 设置下载文件头
		 String agent = request.getHeader("USER-AGENT");    
         if (null != agent && -1 != agent.indexOf("MSIE") || null != agent    
                 && -1 != agent.indexOf("Trident")) {// ie    
        	 fileName = java.net.URLEncoder.encode(fileName, "UTF8"); 
        	 response.setHeader("Content-Disposition",
     				"filename=\"" + fileName+"\"");
         } else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等    
        	 fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");    
        	 response.setHeader("Content-Disposition",
     				"attachment; filename=\"" + fileName+"\"");
         } 
         output = response.getOutputStream();
			if(null != workbook2003){
				//输出excel2003文件流
				workbook2003.write(output);
			}else{
				workbook2007.write(output);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (output != null) {
					output.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	
}
