package in.sp.main.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import in.sp.main.entities.CitizenPlan;

@Component
public class ExcelGenerator {

	public void excelGenerate(HttpServletResponse response, List<CitizenPlan> records, File file) throws IOException {

		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet("Plan-Report");

		// Create date cell style
		CreationHelper creationHelper = workbook.getCreationHelper();
		CellStyle dateCellStyle = workbook.createCellStyle();
		dateCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("dd-MM-yyyy"));

		Row headerRow = sheet.createRow(0);

		headerRow.createCell(0).setCellValue("ID");
		headerRow.createCell(1).setCellValue("Citizen Name");
		headerRow.createCell(2).setCellValue("Gender");
		headerRow.createCell(3).setCellValue("Plan Name");
		headerRow.createCell(4).setCellValue("Plan Status");
		headerRow.createCell(5).setCellValue("Plan Start Date");
		headerRow.createCell(6).setCellValue("Plan End Date");
		headerRow.createCell(7).setCellValue("Benefit Amount");

		int dataRowIndex = 1;

		for (CitizenPlan plan : records) {
			Row dataRow = sheet.createRow(dataRowIndex);

			dataRow.createCell(0).setCellValue(plan.getCitizenId());
			dataRow.createCell(1).setCellValue(plan.getCitizenName());
			dataRow.createCell(2).setCellValue(plan.getGender());
			dataRow.createCell(3).setCellValue(plan.getPlanName());
			dataRow.createCell(4).setCellValue(plan.getPlanStatus());

			// Format Start Date
			if (plan.getPlanStartDate() != null) {
				Cell startDateCell = dataRow.createCell(5);
				startDateCell.setCellValue(java.sql.Date.valueOf(plan.getPlanStartDate()));
				startDateCell.setCellStyle(dateCellStyle);
			} else {
				dataRow.createCell(5).setCellValue("N/A");
			}

			// Format End Date
			if (plan.getPlanEndDate() != null) {
				Cell endDateCell = dataRow.createCell(6);
				endDateCell.setCellValue(java.sql.Date.valueOf(plan.getPlanEndDate()));
				endDateCell.setCellStyle(dateCellStyle);
			} else {
				dataRow.createCell(6).setCellValue("N/A");
			}

			if (plan.getBenefitAmount() != null) {
				dataRow.createCell(7).setCellValue(plan.getBenefitAmount());
			} else {
				dataRow.createCell(7).setCellValue("N/A");
			}

			dataRowIndex++;
		}

		// Auto-size all columns
		for (int i = 0; i <= 7; i++) {
			sheet.autoSizeColumn(i);
		}

		FileOutputStream fos = new FileOutputStream(file);
		workbook.write(fos);
		fos.close();

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
	}
}
