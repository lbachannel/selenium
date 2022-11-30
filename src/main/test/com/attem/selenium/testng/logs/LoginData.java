package com.attem.selenium.testng.logs;

import java.io.IOException;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.atteam.selenium.testng.utils.ExcelUtils;

public class LoginData extends TestData implements Log<LoginData>{
	private String id;
	private String password;
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
	
	@Override
	public void writeLog(String src, String sheetName, Set<LoginData> logs) throws IOException {
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
		for (LoginData log : logs) {
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
			log.writeDataRow(log, row, sheet); 
			lastRow++;
		}
		// hàm tiện ích xuất ra file sử dụng đường dẫn và workbook chỉ định
		ExcelUtils.export(src, workbook); 
	}

	@Override
	public void writeDataRow(LoginData log, Row row, XSSFSheet sheet) throws IOException {
		CellStyle globalStyle = row.getRowStyle();
		
		Cell cell;

		cell = row.createCell(0); //Cell thứ 0
		cell.setCellValue(log.getId());
		cell.setCellStyle(globalStyle);

		cell = row.createCell(1);
		cell.setCellValue(log.getPassword());
		cell.setCellStyle(globalStyle);

		// gọi hàm writeTestData từ lớp cha
		// số 2 ở đây là start index để bắt đầu
		// bởi vì trong lớp này chỉ có 2 trường dữ liệu với index 0, 1 như trên
		// nên tiếp theo dữ liệu sẽ được thêm vào từ index 2
		// thay đổi cho phù hợp tuỳ vào class mà các ông tạo có bao nhiêu fields
		writeTestData(2, row, sheet);
	}
}
