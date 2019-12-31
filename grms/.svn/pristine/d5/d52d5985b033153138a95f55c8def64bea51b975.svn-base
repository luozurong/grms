package com.hori.grms.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;


/**
 * 导出数据到excel 
 */
public class ExportExcelUtil  {

	/**
	 * 表格默认最小宽度
	 */
	public static final int COL_DEFAULE_MIN_WIDTH = 20*256;
	
	/**
	 * 导出数据列表保存至 excel2003 
	 * @param title
	 * @param headers
	 * @param dataList
	 * @param fileName
	 */
	public static void experotExcelFor2003(String title, String[] headers, List<?> dataList, String fileName, PoiCell setData, HttpServletRequest request, HttpServletResponse response){
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(title);			
	    /*################################表头部分设置START################################*/
	    // 表头字体设置
	    HSSFFont headerfont = workbook.createFont();
	    // 字体加粗 	
	    headerfont.setBoldweight(Font.BOLDWEIGHT_BOLD);  
	    
	    //表头style设置
	    HSSFCellStyle headerStyle = workbook.createCellStyle();
	    // 设置长文本自动换行
//	    headerStyle.setWrapText(true);
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
	    HSSFFont commonfont = workbook.createFont();
	    HSSFCellStyle commonStyle = workbook.createCellStyle();
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
	    //生成第一行标题行
	    //XSSFRow fristHeadRow = sheet.createRow(0);
	        
	    //生成第一行表头行
	    HSSFRow headerRow = sheet.createRow(0);
	    HSSFRichTextString text;
	    for (int i = 0; i < headers.length; i++) {
	    	HSSFCell cell = headerRow.createCell(i);
	    	cell.setCellStyle(headerStyle);
	    	text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}
	    //数据填充
	    HSSFRow row;
	    int index = 0;
	    for (Object data : dataList) {
	    	index++;
	    	row = sheet.createRow(index);
	    	setData.setCellData(data, row, commonStyle);

		}
	    for (int i = 0; i < headers.length; i++) {
	    	sheet.autoSizeColumn(i);
	    	if(sheet.getColumnWidth(i) < COL_DEFAULE_MIN_WIDTH){
	    		sheet.setColumnWidth(i, COL_DEFAULE_MIN_WIDTH);
	    	}
		}
		//以浏览器下载的形式导出Excel文件
    	downFile(fileName, workbook, request, response);
	}
	
	/**
	 * 浏览器下载的形式导出Excel文件(方法抽取)
	 * @param fileName
	 * @param workbook
	 */	
	private static void downFile(String fileName, Workbook workbook, HttpServletRequest request, HttpServletResponse response) {
		ServletOutputStream output = null;
		//HttpServletRequest request = ServletActionContext.getRequest();
		try {
			//HttpServletResponse response = ServletActionContext.getResponse();
			// 设置下载文件类型
			response.setContentType("application/octet-stream;charset=ISO-8859-1");
			// 设置下载文件头
			String agent = request.getHeader("USER-AGENT");
			if (null != agent && -1 != agent.indexOf("MSIE") || null != agent && -1 != agent.indexOf("Trident")) {// ie
				fileName = java.net.URLEncoder.encode(fileName, "UTF8");
				response.setHeader("Content-Disposition", "filename=\"" + fileName + "\"");
			} else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等
				fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
				response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			}
			output = response.getOutputStream();
			if (null != workbook) {
				workbook.write(output);
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

	/**
	 * <b>Desc:</b> 处理导出excel表格数据 
	 */
	public interface PoiCell{
		/**
		 * <b>Desc:</b> 设置表格数据 
		 * @param obj 数据对象
		 * @param row 数据写入对应表格行
		 * @param style 表格样式
		 */
		public void setCellData(Object obj, HSSFRow row, HSSFCellStyle style);
	}
	
	public static void downSpcialFile(String fileName, Workbook workbook, HttpServletRequest request, HttpServletResponse response) {
		//以浏览器下载的形式导出Excel文件
    	downFile(fileName, workbook, request, response);
	}
}

