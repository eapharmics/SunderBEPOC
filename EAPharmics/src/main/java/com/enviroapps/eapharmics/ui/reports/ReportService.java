package com.enviroapps.eapharmics.ui.reports;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enviroapps.eapharmics.exception.EAPharmicsException;
import com.enviroapps.eapharmics.reports.StabilityDatabaseLogsReportImpl;
import com.enviroapps.eapharmics.services.PendingSchedulerReportImpl;
import com.enviroapps.eapharmics.services.ReportsImpl;
import com.enviroapps.eapharmics.ui.AbstractEAPharmicsService;
import com.enviroapps.eapharmics.vo.newstudy.RptUserPreferenceVO;
import com.enviroapps.eapharmics.vo.reports.ReportsVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/eapharmics/reports")
@RequiredArgsConstructor

public class ReportService extends AbstractEAPharmicsService {

	@GetMapping("/runReport")
	public String runReport(@RequestParam Map<String, String> params, @RequestParam String reportName) throws EAPharmicsException {
		ReportsVO reportsVO = new ReportsVO();
		reportsVO.setReportName(reportName);
		HashMap<String, String> map = new HashMap<String, String>();
		params.forEach((key, value) -> map.put(key, value));
		reportsVO.setParameters(map);
		ReportsImpl reportsImpl = new ReportsImpl();
		return reportsImpl.runReport(reportsVO);
	}

	@GetMapping("/runMultipleRegressionReport")
	public String runMultipleRegressionReport(@RequestParam(value = "reportsVO") ReportsVO reportsVO) throws EAPharmicsException {
		ReportsImpl reportsImpl = new ReportsImpl();
		return reportsImpl.runMultipleRegressionReport(reportsVO);
	}

	@GetMapping("/runMultipleReports")
	public String runMultipleReports(@RequestParam(value = "reports") List<ReportsVO> reports) throws EAPharmicsException {
		ReportsImpl reportsImpl = new ReportsImpl();
		return reportsImpl.runMultipleReports(reports);
	}

	@GetMapping("/runPendingSchedulerReport")
	public String runPendingSchedulerReport(@RequestParam(value = "reportsVO")ReportsVO reportsVO, @RequestParam(value = "displayColumns") List<RptUserPreferenceVO> displayColumns)
			throws EAPharmicsException {
		PendingSchedulerReportImpl pendingSchedulerReport = new PendingSchedulerReportImpl();
		return pendingSchedulerReport.runReport(reportsVO, displayColumns);
	}

	@GetMapping("/runStabilityDatabaseLogsReport")
	public String runStabilityDatabaseLogsReport(ReportsVO reportsVO,  @RequestParam(value = "displayColumns") List<RptUserPreferenceVO> displayColumns)
			throws EAPharmicsException {
		StabilityDatabaseLogsReportImpl stabilityDatabaseLogsReport = new StabilityDatabaseLogsReportImpl();
		return stabilityDatabaseLogsReport.runReport(reportsVO, displayColumns);
	}
}
