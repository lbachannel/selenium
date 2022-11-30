package com.attem.selenium.testng.logs;

import java.util.Date;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.atteam.selenium.testng.utils.ExcelUtils;

public class TestData {
	
	private String action;
	private Date testTime;
	private String testMethod;
	private String expected;
	private String actual;
	private String status;
	private String exception = null;
	private String image = null;
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Date getTestTime() {
		return testTime;
	}
	public void setTestTime(Date testTime) {
		this.testTime = testTime;
	}
	public String getTestMethod() {
		return testMethod;
	}
	public void setTestMethod(String testMethod) {
		this.testMethod = testMethod;
	}
	public String getExpected() {
		return expected;
	}
	public void setExpected(String expected) {
		this.expected = expected;
	}
	public String getActual() {
		return actual;
	}
	public void setActual(String actual) {
		this.actual = actual;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public void writeTestData(int startIndex, Row row, XSSFSheet sheet) {
		CreationHelper creationHelper = sheet.getWorkbook().getCreationHelper();
		CellStyle globalStyle = row.getRowStyle();
		Cell cell; // tạo đối tượng cell để ghi data
		
		cell = row.createCell(startIndex); // vị trí column bắt đầu
		cell.setCellValue(getAction());
		cell.setCellStyle(globalStyle);
		
		cell = row.createCell(startIndex + 1);
		cell.setCellValue(getTestTime());  // kiểu date - trả về con số nên phải Format lại kiểu ngày tháng năm
		CellStyle datetimeStyle = globalStyle;
		datetimeStyle.setDataFormat(creationHelper.createDataFormat().getFormat("hh:mm:ss dd-mm-yyyy"));
		cell.setCellStyle(datetimeStyle);
		
		cell = row.createCell(startIndex + 2);
		cell.setCellValue(getTestMethod());
		cell.setCellStyle(globalStyle);
		
		cell = row.createCell(startIndex + 3);
		cell.setCellValue(getExpected());
		cell.setCellStyle(globalStyle);
		
		cell = row.createCell(startIndex + 4);
		cell.setCellValue(getActual());
		cell.setCellStyle(globalStyle);
		
		cell = row.createCell(startIndex + 5);
		cell.setCellValue(getStatus());
		cell.setCellStyle(globalStyle);
		
		if(getException() != null) {
			cell = row.createCell(startIndex + 6);
			cell.setCellValue(getException());
			cell.setCellStyle(globalStyle);		
		}
		
		if(getImage() != null) {
			cell = row.createCell(startIndex + 7);
			cell.setCellStyle(globalStyle);
			ExcelUtils.writeImage(getImage(), row, cell, sheet);
			
			cell = row.createCell(startIndex + 8);
			cell.setCellValue("Link Screenshot");
			cell.setCellStyle(globalStyle);
			
			// tạo Hyperlink | trong excel trỏ vào đường dẫn để mở to hình ảnh 
			XSSFHyperlink hyperLink = (XSSFHyperlink) creationHelper.createHyperlink(HyperlinkType.URL);
			hyperLink.setAddress(getImage().replace("\\", "/")); // setAddress vào hyperlink
			cell.setHyperlink(hyperLink);
		}
	}
}
