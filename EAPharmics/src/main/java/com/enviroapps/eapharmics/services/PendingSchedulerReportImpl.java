package com.enviroapps.eapharmics.services;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.enviroapps.eapharmics.common.Utility;
import com.enviroapps.eapharmics.exception.EAPharmicsException;
import com.enviroapps.eapharmics.ui.newstudy.NewStudyLoginService;
import com.enviroapps.eapharmics.util.EAReportsHelper;
import com.enviroapps.eapharmics.vo.newstudy.NewStudySummaryVO;
import com.enviroapps.eapharmics.vo.newstudy.RptUserPreferenceVO;
import com.enviroapps.eapharmics.vo.reports.ReportsVO;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.CustomExpression;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class PendingSchedulerReportImpl extends DynamicReportImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(PendingSchedulerReportImpl.class);
	
	public PendingSchedulerReportImpl() {
	}
	
	public String runReport(ReportsVO reportsVO, List<RptUserPreferenceVO> displayColumns) throws EAPharmicsException {
		String returnUrl = "";
		// setup report columns to display for easy lookup
		// instead of looping through each column
		HashMap reportColumns = new HashMap();
      HashMap sch = new HashMap();
      sch = reportsVO.getParameters();
      List schId = (List) sch.get("P_SCHEDULE_ID_STRING");
      String reportName = (String) sch.get("P_REPORT_NAME");
      
      String primarySortColumn=null;
      String primarySortColumnOrder=null;
      String secondarySortColumn=null;
      String secondarySortColumnOrder=null;		
      String scheduleStatus = null;
      String monthQuery = null;
      Long unpulledDays  = null;
      Long lateDaysStart = null;
      Long lateDaysEnd  = null;  
      Long testingCycleNotStarted = null;
      Long testingCycleNotEnded = null;                                                       
      String productCode  = null;
      Long lateDaysOKlevel = null;
      if (displayColumns == null)
      {
         if ((reportName.compareTo("SCHEDULE_PENDING_REPORT_FOR_UNPULLED_DAYS")==0) ||
               (reportName.compareTo("SCHEDULE_PENDING_REPORT_FOR_TESTING_CYCLE_NOT_STARTED")==0) ||
                 (reportName.compareTo("SCHEDULE_PENDING_REPORT_FOR_TESTING_CYCLE_NOT_COMPLETED")==0) )
         {
            List<String> arr = new ArrayList<String>();
            arr.add("PENDING_REPORTS");
            displayColumns = (new NewStudyLoginService().getAllRptUserPreferences(arr,"pending_scheduler_report_late_days"));
         }
      }
		for (Iterator<RptUserPreferenceVO> iterator = displayColumns.iterator(); iterator.hasNext();) {
			//String columnName = (String) iterator.next();
		   RptUserPreferenceVO rptUserPref = new RptUserPreferenceVO();
		   rptUserPref = iterator.next();
		   if (rptUserPref.getActive())
		   {
		      String columnName = rptUserPref.getFieldName();
		      reportColumns.put(columnName, rptUserPref.getFieldHeader());
		   }	
		   if (rptUserPref.getOrderByPosition()!=null && rptUserPref.getOrderByPosition() == 1)
		   {
		      primarySortColumn = rptUserPref.getSortFieldName();
		      primarySortColumnOrder = rptUserPref.getOrderBy();
		   }
         if (rptUserPref.getOrderByPosition()!=null && rptUserPref.getOrderByPosition() == 2)
         {
            secondarySortColumn = rptUserPref.getSortFieldName();
            secondarySortColumnOrder = rptUserPref.getOrderBy();
         }		   
		}
		
		HashMap reportParams = reportsVO.getParameters();
		reportParams.put("SUBREPORT_DIR", EAReportsHelper.REPORTS_BASE_DIR + "/");
		reportParams.put("P_IMAGE_DIR", EAReportsHelper.REPORTS_BASE_DIR + "/");
		reportsVO.setParameters(reportParams);
		
		//TODO remove this later
		Calendar calendar = Calendar.getInstance();
		calendar.set(1900, 1, 1);
		Date dt1 = calendar.getTime();
		//TODO change this to get the correct list based on the parameters
		//List pendingList = new NewStudyLoginImpl().getAllNewStudySummaries(dt1, Utility.getCurrentUserLocationDateTime());
		
		if (reportName.compareTo("SCHEDULE_PENDING_REPORT")== 0 )
		{
		   scheduleStatus = "PENDING";
		}
		if (reportName.compareTo("SCHEDULE_PENDING_REPORT_GRID_DATA") == 0)
		{
		   scheduleStatus = "PENDING";
		}
		if ( reportName.compareTo("SCHEDULE_PENDING_REPORT_MARK_LATE_DAYS_OK")== 0 )
		{
		   scheduleStatus = "PENDING";
		   lateDaysOKlevel = Long.parseLong((String) sch.get("P_LATE_DAYS_OK"));
		}
      if (reportName.compareTo("SCHEDULE_PENDING_REPORT_FOR_MONTH")==0)
      {
         scheduleStatus = "PENDING";
         monthQuery = (String) sch.get("P_MONTH");
      }
      else if (reportName.compareTo("SCHEDULE_PENDING_REPORT_FOR_UNPULLED_DAYS")==0)
      {
         scheduleStatus = "PENDING"; 
         unpulledDays = Long.parseLong((String) sch.get("P_UNPULLED_DAYS"));
      }
      else if (reportName.compareTo("SCHEDULE_PENDING_REPORT_CALCULATE_LATE_DAYS")==0)
      {
         scheduleStatus = "PENDING";
         lateDaysStart = Long.parseLong((String) sch.get("P_LATE_DAYS_START"));
         if (((String) sch.get("P_LATE_DAYS_END")).length()>0)
         {
            lateDaysEnd = Long.parseLong((String) sch.get("P_LATE_DAYS_END"));  
         }
      }
      else if (reportName.compareTo("SCHEDULE_PENDING_REPORT_FOR_TESTING_CYCLE_NOT_STARTED")==0)
      {
         scheduleStatus = "PENDING";    
         testingCycleNotStarted = Long.parseLong((String) sch.get("P_TESTING_CYCLE_NOT_STARTED"));         
      }
      else if (reportName.compareTo("SCHEDULE_PENDING_REPORT_FOR_TESTING_CYCLE_NOT_COMPLETED")==0)
      {
         scheduleStatus = "PENDING";
         testingCycleNotEnded = Long.parseLong((String) sch.get("P_TESTING_CYCLE_NOT_COMPLETED"));  
      }
      else if (reportName.compareTo("SCHEDULE_PENDING_REPORT_FOR_PRODUCT_CODE")==0)
      {
         scheduleStatus = "PENDING";  
         productCode = (String) sch.get("P_PRODUCT_CODE");
      }
      else if (reportName.compareTo("SCHEDULE_INVENTORY_REPORT")==0)
      {
         if (!reportColumns.isEmpty())
         {
            reportColumns.put("intervalUnits", "Sched. Units");
         }
      }
      if ((String) sch.get("P_LATE_DAYS_OK") != null)
      {
         lateDaysOKlevel = Long.parseLong((String) sch.get("P_LATE_DAYS_OK"));
      }
		//List<NewStudySummaryVO> pendingList = new NewStudyLoginService().getAllNewStudySummariesForLMM("", "", "", "", "", "", dt1, Utility.getCurrentUserLocationDateTime());
		List<NewStudySummaryVO> pendingList = new NewStudyLoginService().getPendingSchedulerReportData(schId,
		                                                                                               scheduleStatus,
		                                                                                               monthQuery,
		                                                                                               unpulledDays,
		                                                                                               lateDaysStart,
		                                                                                               lateDaysEnd,
		                                                                                               testingCycleNotStarted,
		                                                                                               testingCycleNotEnded,                                                                
		                                                                                               productCode,  
		                                                                                               primarySortColumn,
		                                                                                               primarySortColumnOrder,
		                                                                                               secondarySortColumn,
		                                                                                               secondarySortColumnOrder);
     
      for(NewStudySummaryVO item:pendingList)
      {
         if(item.getNumberOfDaysLate() != null)
         {
            if (lateDaysOKlevel != null && lateDaysOKlevel >= 0 &&
                    (item.getNumberOfDaysLate()<= lateDaysOKlevel))
            {
               item.setLateDaysDisplay("OK");
            }
            else
            {
               item.setLateDaysDisplay(item.getNumberOfDaysLate().toString());
            }
         }
         if (item.getIntervalValue().compareTo("0")== 0 && item.getIntervalUnits()== 0)
         {
            item.setUnitsPulled("N/A");
            item.setDatePulled(null);
            item.setPulledBy("N/A");
            item.setTestStartDate(null);
            item.setTestStartedBy("N/A");
            item.setTestEndDate(null);
            item.setTestEndedBy("N/A");
         }
      }
		try {

			FastReportBuilder frb = new FastReportBuilder();
			int totalColumnWidth = 0;
			addColumn(frb, reportColumns, "codeYear", "Code Yr.", "java.lang.String", 50);

			addColumn(frb, reportColumns, "productCode", "Product Code", "java.lang.String", 60, true); // fixed width
			addColumn(frb, reportColumns, "productName", "Product", "java.lang.String", 100);
			addColumn(frb, reportColumns, "productDescription", "Product Description", "java.lang.String", 100);

			addColumn(frb, reportColumns, "lotNumber", "Lot", "java.lang.String", 30);
         addColumn(frb, reportColumns, "fill", "Fill", "java.lang.String", 40);
         addColumn(frb, reportColumns, "containerCode", "Container", "java.lang.String", 100);
         addColumn(frb, reportColumns, "closure", "Closure", "java.lang.String", 30);			
			addColumn(frb, reportColumns, "studyId", "ID", "java.lang.String", 30);
			addColumn(frb, reportColumns, "studyCondition", "Condition", "java.lang.String", 100);

			addColumn(frb, reportColumns, "formula", "Formula", "java.lang.String", 50);
         addColumn(frb, reportColumns, "storageLocation", "Location", "java.lang.String", 60);
         addColumn(frb, reportColumns,"initialDate", "Initial","java.util.Date", 40);
         addColumn(frb, reportColumns, "scheduleDate", "Due Date", "java.util.Date", 40);
         addColumn(frb, reportColumns, "intervalText", "Int.", "java.lang.String", 30);
         
         addColumn(frb, reportColumns, "studyStatus", "Status", "java.lang.String", 25);
         addColumn(frb, reportColumns, "studyReasonCode", "Reason", "java.lang.String", 25);
         
			addColumn(frb, reportColumns, "manufacturingSiteCode", "Mfg. Site Code", "java.lang.String", 100);
			addColumn(frb, reportColumns, "packagingSiteCode", "Pkg. Site Code", "java.lang.String", 100);
			addColumn(frb, reportColumns, "manufacturingDate", "Mfg. Date", "java.util.Date", 30);
			addColumn(frb, reportColumns, "packageDate", "Pkg. Date", "java.util.Date", 40);
			addColumn(frb, reportColumns, "totalValue", "Total", "java.lang.Long", 30);
			addColumn(frb, reportColumns, "extraValue", "Extra", "java.lang.Long", 30);
			addColumn(frb, reportColumns, "initialValue", "Initial", "java.lang.Long", 30);

			// Units Pulled is String - need to apply right align style
			for (Iterator iterator = frb.getColumns().iterator(); iterator.hasNext();) {
				AbstractColumn column = (AbstractColumn) iterator.next();
				if (column.getTitle().equals("Units Pulled")) {
					column.setStyle(EAReportsHelper.DETAIL_RIGHT_ALIGN_STYLE);
				}
			}
			addColumn(frb, reportColumns, "datePulled", "Date Pulled", "java.util.Date", 40);
         addColumn(frb, reportColumns, "unitsPulled", "Units Pulled", "java.lang.String", 30);			
			addColumn(frb, reportColumns, "pulledBy", "Pulled By", "java.lang.String", 50);
			addColumn(frb, reportColumns, "testStartDate", "Testing Started", "java.util.Date", 40);
			addColumn(frb, reportColumns, "testEndDate", "Testing Ended", "java.util.Date", 40);
			
			addColumn(frb, reportColumns, "extraPulled", "Extra Pulled", "java.lang.Long", 30);

			 addColumn(frb, reportColumns, "intervalUnits", "Sched. Units", "java.lang.Long", 30);
			 addColumn(frb, reportColumns, "numberOfDaysLate", "Late", "java.lang.Long", 30);
			 addColumn(frb, reportColumns, "lateDaysDisplay", "Late", "java.lang.String", 30);   
		 
			 for (int idx=1; idx<11; idx++)
			 {
			    String userDefinedColumn = "userDefinedColumn"+idx;
			      for (Iterator<RptUserPreferenceVO> iterator = displayColumns.iterator(); iterator.hasNext();) {
			         //String columnName = (String) iterator.next();
			         RptUserPreferenceVO rptUserPref = new RptUserPreferenceVO();
			         rptUserPref = iterator.next();
			         if ((rptUserPref.getFieldName()).compareTo(userDefinedColumn)==0)
			         {
			            addColumn(frb, reportColumns,userDefinedColumn, rptUserPref.getFieldHeader(), "java.lang.String", 30);
			         }
			      }
			 }
			
			frb.setHeaderHeight(27)
				.setDetailHeight(10)
				.setUseFullPageWidth(true)
				.setDefaultStyles(EAReportsHelper.TITLE_STYLE, 
						          EAReportsHelper.SUB_TITLE_STYLE, 
						          EAReportsHelper.COLUMN_HEADER_STYLE,
						          EAReportsHelper.COLUMN_DETAIL_STYLE)
				.setTitle("Number of records: " + pendingList.size())
				.setTemplateFile(EAReportsHelper.getTemplateFile(getTotalColumnWidth()));

			DynamicReport dr = frb.build();
			
			JRDataSource ds = new JRBeanCollectionDataSource(pendingList);
			JasperPrint jpr = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), ds, reportParams);
			
			File outputFile = File.createTempFile("pending_scheduler_report", ".pdf", new File(EAReportsHelper.REPORTS_OUTPUT_DIR));
			FileOutputStream outputStream = new FileOutputStream(outputFile);
			JasperExportManager.exportReportToPdfStream(jpr, outputStream);
			outputStream.flush();
			outputStream.close();
	        returnUrl = EAReportsHelper.REPORTS_BASE_URL + outputFile.getName();
		} catch(Throwable e) {
			logger.error("runReport", e);
			throw new EAPharmicsException("Error running report");
		}
	
		return returnUrl;
	}


	private AbstractColumn getColumnForLateDays() throws Exception {
		ColumnBuilder cb = ColumnBuilder.getNew()
		.setTitle("Late Days")
		.setWidth(20)
		.setCustomExpression(
				new CustomExpression() {
	                    public Object evaluate(Map fields, Map variables, Map parameters) {
	                    	java.util.Date scheduleDate = (java.util.Date) fields.get("scheduleDate");
	                    	if (scheduleDate == null) {
	                    		return "";
	                    	}
	                    	return java.lang.Integer.toString(Utility.dateDiff(scheduleDate, Utility.getCurrentUserLocationDateTime()));
	                    }
	                    public String getClassName() {
	                        return "java.lang.String";
	                    }
	            })
	      .setStyle(EAReportsHelper.DETAIL_RIGHT_ALIGN_STYLE);
		return cb.build();
	}
	
}