package in.sp.main.service;

import in.sp.main.entities.CitizenPlan;
import in.sp.main.repo.CitizenPlanRepo;
import in.sp.main.request.SearchRequest;
import in.sp.main.utils.EmailUtils;
import in.sp.main.utils.ExcelGenerator;
import in.sp.main.utils.PdfGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private CitizenPlanRepo planRepo;

	@Autowired
	private ExcelGenerator excelGenerator;

	@Autowired
	private PdfGenerator pdfGenerator;

	@Autowired
	private EmailUtils emailUtils;

	@Override
	public List<CitizenPlan> search(SearchRequest request) {

		CitizenPlan entity = new CitizenPlan();
		if (null != request.getPlanName() && !"".equals(request.getPlanName())) {
			entity.setPlanName(request.getPlanName());
		}
		if (null != request.getPlanStatus() && !"".equals(request.getPlanStatus())) {
			entity.setPlanStatus(request.getPlanStatus());
		}
		if (null != request.getGender() && !"".equals(request.getGender())) {
			entity.setGender(request.getGender());
		}
		if (null != request.getStartDate() && !"".equals(request.getStartDate())) {
			String startDate = request.getStartDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(startDate, formatter);
			entity.setPlanStartDate(localDate);
		}
		if (null != request.getEndDate() && !"".equals(request.getEndDate())) {
			String endDate = request.getEndDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate lcoaDate = LocalDate.parse(endDate, formatter);
			entity.setPlanEndDate(lcoaDate);
		}
		return planRepo.findAll(Example.of(entity));
	}

	@Override
	public Boolean exportExcel(HttpServletResponse response) throws Exception {

		File f = new File("Plans.xls");

		List<CitizenPlan> allrecords = planRepo.findAll();
		excelGenerator.excelGenerate(response, allrecords, f);
		String subject = "Test Mail Dayaan";
		String body = "Testing Mail.";
		String to = "alidayaan08@gmail.com";
		emailUtils.sendEmail(subject, body, to, f);
		f.delete();
		return true;
	}

	@Override
	public Boolean exportPdf(HttpServletResponse response) throws Exception {

		File f = new File("Plans.pdf");
		List<CitizenPlan> allrecords = planRepo.findAll();
		pdfGenerator.pdfGenerate(response, allrecords, f);
		String subject = "Test Mail Dayaan";
		String body = "Testing Mail.";
		String to = "alidayaan08@gmail.com";
		emailUtils.sendEmail(subject, body, to, f);
		f.delete();
		return true;
	}

	@Override
	public List<String> getPlanNames() {
		return planRepo.getPlanNames();
	}

	@Override
	public List<String> getPlanStatuses() {
		return planRepo.getPlanStatus();
	}

}
