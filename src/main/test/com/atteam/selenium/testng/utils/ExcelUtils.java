package com.atteam.selenium.testng.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ExcelUtils {
	
//	@SuppressWarnings("unused")
//	public static final String driverPath = "chromedriver.exe";
//	@SuppressWarnings("unused")
//	public static final String excelPath = "test_login.xlsx";
//	@SuppressWarnings("unused")
//	public static final String imagesPath = "/images/";
	
	public static final String driverPath = "test-resources/chromedriver.exe";

	public static final String excelPath = "test-resources/data/";

	public static final String imagesPath = "test-resources/images/";
	
	// XSSF: các class có tên bắt đầu là XSSF được dùng để sử lý các file Microsoft Excel 2007 trở về sau (.xlsx)
	// [truyền đường dẫn file excel]
	public static XSSFWorkbook getWorkbook(String path) throws IOException  {
		File src = new File(path); //Lấy File từ đường dẫn
		if (!src.exists()) {
			throw new IOException("File does not exist!");
		}
		FileInputStream fis = new FileInputStream(src); //Chuyển File sang FileInputStream
		XSSFWorkbook workbook = new XSSFWorkbook(fis); //Tạo đối tượng Workbook
		fis.close(); //đóng kết nối
		return workbook;
	}
	
	public static CellStyle getRowStyle(XSSFWorkbook workbook) { //dùng để định dạng tr6en Excel cho đẹp
		CellStyle rowStyle = workbook.createCellStyle();
		rowStyle.setAlignment(HorizontalAlignment.CENTER);
		rowStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		rowStyle.setWrapText(true);

		return rowStyle;
	}
	
	// XSSFSheet là các class xử lý với Excel [lấy tên sheet]
	public static XSSFSheet getSheet(XSSFWorkbook workBook, String sheetName) {
		XSSFSheet sheet = workBook.getSheet(sheetName);
		if(sheet == null) {
			throw new NullPointerException("Sheet name does not exist!");
		}
		return sheet;
	}
	
	// lấy giá trị cột
	public static String getValueOfCell(XSSFSheet sheet, int row, int col) {
		String containValue = "";
		XSSFCell cell = sheet.getRow(row).getCell(col);
		try {
			if(cell.getCellType() == CellType.STRING) { // nếu là String thì chuyển về getStringCellValue
				containValue = cell.getStringCellValue();
			}else if(cell.getCellType() == CellType.NUMERIC) {
				containValue = String.format("%.0f", cell.getNumericCellValue());
			}else { // không lấy ký tự đặc biệt
				containValue = "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return containValue;
	}
	
	// chụp hình
	public static void takeScreenShot(WebDriver driver, String imgPath) {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File(imgPath));
		} catch (IOException e) {
			System.out.println("Cannot get path!");
		}
	}
	
	public static Object[][] readSheetData(XSSFSheet sheet){
		int rows = sheet.getPhysicalNumberOfRows();				// get all rows
		System.out.println(rows);
		int columns = sheet.getRow(0).getLastCellNum();			// get all columns
		Object[][] data = new Object[rows - 1][columns]; 		// -1 chừa hàng cho title
		for(int row = 1; row < rows; ++row) { 					// row = 1 <=> hàng thứ 2 trong Excel
			for(int col = 0; col < columns; ++col) {
				data[row - 1][col] = ExcelUtils.getValueOfCell(sheet, row, col); // gọi phương thức lấy giá trị cột bên trên
			}
		}
		return data;
	}
	
	public static void writeImage(String image, Row row, Cell cell, XSSFSheet sheet) {
		try {
			InputStream is = new FileInputStream(image);		// lấy hình - image là đường dẫn
			byte[] bytes = IOUtils.toByteArray(is);				// trung gian
			int pictureId = sheet.getWorkbook().addPicture(bytes, XSSFWorkbook.PICTURE_TYPE_PNG);
			// dùng addPicture để tạo đối tượng hình ảnh vào Workbook (chưa có hình th)
			is.close();
			
			XSSFDrawing drawing = sheet.createDrawingPatriarch();	// bắt buộc khởi tạo để chèn hình vào excel
			ClientAnchor anchor = new XSSFClientAnchor(); 		 	// định vị
			
			anchor.setCol1(cell.getColumnIndex());
			anchor.setRow1(row.getRowNum());
			anchor.setCol2(cell.getColumnIndex() + 1);
			anchor.setRow2(row.getRowNum() + 1);
			
			drawing.createPicture(anchor, pictureId);				 // PictureId để biết bỏ hình nào vào
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void export(String excelPath, XSSFWorkbook workbook) {
		try {
			FileOutputStream fos = new FileOutputStream(excelPath);
			workbook.write(fos);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
