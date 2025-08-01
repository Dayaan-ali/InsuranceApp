package in.sp.main.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import in.sp.main.entities.CitizenPlan;

@Component
public class PdfGenerator {

	public void pdfGenerate(HttpServletResponse response, List<CitizenPlan> records, File f) throws Exception {
		
		Document document = new Document(PageSize.A4);
		PdfWriter pdfWriter = PdfWriter.getInstance(document, response.getOutputStream());
		PdfWriter writer= PdfWriter.getInstance(document, new FileOutputStream(f));
		document.open();

		com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setSize(20);
		Paragraph p = new Paragraph("Citizens Plan Info", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(p);
		PdfPTable table = new PdfPTable(8);
		table.addCell("ID");
		table.addCell("Citizen Name");
		table.addCell("Gender");
		table.addCell("Plan Name");
		table.addCell("Plan Status");
		table.addCell("Plan Start Date");
		table.addCell("Plan End Date");
		table.addCell("Benefit Amount");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		for (CitizenPlan plan : records) {
			table.addCell(String.valueOf(plan.getCitizenId()));
			table.addCell(plan.getCitizenName());
			table.addCell(plan.getGender());
			table.addCell(plan.getPlanName());
			table.addCell(plan.getPlanStatus());
			if (plan.getPlanStartDate() != null) {
				table.addCell(plan.getPlanStartDate().format(formatter));
			} else {
				table.addCell("N/A");
			}
			if (plan.getPlanEndDate() != null) {
				table.addCell(plan.getPlanEndDate().format(formatter));
			} else {
				table.addCell("N/A");
			}
			if (plan.getBenefitAmount() != null) {
				table.addCell(String.valueOf(plan.getBenefitAmount()));
			} else {
				table.addCell("N/A");
			}
		}
		document.add(table);
		document.close();
	}
}
