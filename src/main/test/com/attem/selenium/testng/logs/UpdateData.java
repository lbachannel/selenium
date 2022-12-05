package com.attem.selenium.testng.logs;

import java.io.IOException;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.atteam.selenium.testng.utils.ExcelUtils;

public class UpdateData extends TestData implements Log<UpdateData>{
	private String id;
	private String password;
	private String fullname;
	private String email;
	private boolean admin;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	@Override
	public void writeLog(String src, String sheetName, Set<UpdateData> ups) throws IOException {
		// hàm tiện ích tạo đối tượng workbook từ đường dẫn file chỉ định
		XSSFWorkbook workbook = ExcelUtils.getWorkbook(src);
		
		// hàm tiện ích gọi sheet cần sử dụng (đã được tạo thủ công sẵn) từ tên chỉ định 
		// lưu ý tên chỉ định phải khớp với tên sheet trong file
		XSSFSheet sheet = ExcelUtils.getSheet(workbook, sheetName);
		
		// đống này xử lý việc ghi tiếp dữ liệu từ hàng cuối cùng của dữ liệu hiện tại
		int startRow = 0, lastRow = sheet.getPhysicalNumberOfRows(); //lastRow bằng hàng cuối cùng / Nếu ko có dữ liệu sẽ ra -1
		if (lastRow < startRow)
			lastRow = startRow; //SỬ DỤNG lastRow

		// hàm tiện ích tạo đối tượng rowStyle đã được thiết lập sẵn các giá trị
		CellStyle rowStyle = ExcelUtils.getRowStyle(workbook);
		
		// duyệt qua bộ dữ liệu
		for (UpdateData up : ups) {
			// tạo row mới dựa vào index của row cuối cùng, đồng thời tăng lastRow lên 1 cho vòng lặp kế tiếp
			Row row = sheet.createRow(lastRow);
			 // thiết lập chiều cao của row, nên để mặc định là 60 cho tiện hiển thị ảnh thumbnail
			row.setHeightInPoints(60);
			row.setRowStyle(rowStyle); // cài đặt style (đã khai báo ở trên) vào row
			
			// gọi hàm ghi dữ liệu, lưu ý phải dùng log.writeDataRow để chỉ định đang gọi phương thức của log hiện tại
			// dùng writeDataRow vẫn không báo lỗi, tuy nhiên lúc này đang trỏ vào bộ nhớ của bản thân class này 
			// chứ không phải của log hiện tại
			// vì vậy sẽ không đọc được dữ liệu kế thừa từ lớp TestData
			// dữ liệu in ra sẽ không đầy đủ
			up.writeDataRow(up, row, sheet); 
			lastRow++;
		}
		// hàm tiện ích xuất ra file sử dụng đường dẫn và workbook chỉ định
		ExcelUtils.export(src, workbook); 
	}

	@Override
	public void writeDataRow(UpdateData say, Row row, XSSFSheet sheet) throws IOException {
		CellStyle globalStyle = row.getRowStyle();
		
		Cell cell;

		cell = row.createCell(0); //Cell thứ 0
		cell.setCellValue(say.getId());
		cell.setCellStyle(globalStyle);
		
		cell = row.createCell(1);
		cell.setCellValue(say.getPassword());
		cell.setCellStyle(globalStyle);
		
		cell = row.createCell(2);
		cell.setCellValue(say.getFullname());
		cell.setCellStyle(globalStyle);
		
		cell = row.createCell(3);
		cell.setCellValue(say.getEmail());
		cell.setCellStyle(globalStyle);
	
		
		writeTestData(4, row, sheet);	
	}



}
