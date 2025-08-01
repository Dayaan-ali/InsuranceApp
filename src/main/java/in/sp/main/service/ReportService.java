package in.sp.main.service;

import in.sp.main.entities.CitizenPlan;
import in.sp.main.request.SearchRequest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

public interface ReportService {

	List<String> getPlanNames();

	List<String> getPlanStatuses();

	Boolean exportPdf(HttpServletResponse response) throws Exception;

	Boolean exportExcel(HttpServletResponse response) throws Exception;

	List<CitizenPlan> search(SearchRequest request);

}
