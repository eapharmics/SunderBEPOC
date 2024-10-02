package com.enviroapps.eapharmics.reports;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.enviroapps.eapharmics.exception.EAPharmicsException;
import com.enviroapps.eapharmics.services.DynamicReportImpl;
import com.enviroapps.eapharmics.ui.newstudy.NewStudyLoginService;
import com.enviroapps.eapharmics.util.EAReportsHelper;
import com.enviroapps.eapharmics.vo.newstudy.ProductStudyIntervalsVO;
import com.enviroapps.eapharmics.vo.newstudy.RptUserPreferenceVO;
import com.enviroapps.eapharmics.vo.reports.ReportsVO;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class StabilityDatabaseLogsReportImpl extends DynamicReportImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(StabilityDatabaseLogsReportImpl.class);
	
	public StabilityDatabaseLogsReportImpl() {
	}
	
	public String runReport(ReportsVO reportsVO, List<RptUserPreferenceVO> displayColumns) throws EAPharmicsException {
		String returnUrl = "";
		// setup report columns to display for easy lookup
		// instead of looping through each column
		HashMap reportColumns = new HashMap();
      HashMap sch = new HashMap();
      sch = reportsVO.getParameters();

      String reportName = (String) sch.get("P_REPORT_NAME");

      String primarySortColumn=null;
      String primarySortColumnOrder=null;
      String secondarySortColumn=null;
      String secondarySortColumnOrder=null;		                                                     
      String productCode  = null;
      
      if (displayColumns == null)
      {
         List<String> arr = new ArrayList<String>();
         arr.add("SUMMARY_REPORTS");
         displayColumns = (new NewStudyLoginService().getAllRptUserPreferences(arr,"database_logs"));
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

		productCode = (String) sch.get("P_PRODUCT_CODE");
		String stabilityYear = (String) sch.get("P_STABILITY_YEAR");
      Date fromStabilityDate = (Date) sch.get("P_FROM_DATE");
      Date toStabilityDate = (Date) sch.get("P_TO_DATE");
      String lotNumber = (String) sch.get("P_CONTAINER");
      String containerCode = (String) sch.get("P_LOT_NUMBER");
      String studyStatus = (String) sch.get("P_STUDY_STATUS");
      String studyId = (String) sch.get("P_STUDY_ID");
		//List<NewStudySummaryVO> pendingList = new NewStudyLoginService().getAllNewStudySummariesForLMM("", "", "", "", "", "", dt1, Utility.getCurrentDateTime());
		List<ProductStudyIntervalsVO> pendingList = new NewStudyLoginService().getStabilityLogsReportData(stabilityYear,
		                                                                                                  fromStabilityDate,
		                                                                                                  toStabilityDate,
		                                                                                                  productCode,    
		                                                                                                  lotNumber,
		                                                                                                  containerCode,
		                                                                                                  studyStatus,
		                                                                                                  studyId,
		                                                                                                  primarySortColumn,
		                                                                                                  primarySortColumnOrder,
		                                                                                                  secondarySortColumn,
		                                                                                                  secondarySortColumnOrder);
		try {

			FastReportBuilder frb = new FastReportBuilder();
			int totalColumnWidth = 0;
			addColumn(frb, reportColumns, "codeYear", "Code Yr.", "java.lang.String", 50);

			//addColumn(frb, reportColumns, "productCode", "Product Code", "java.lang.String", 60, true); // fixed width
			addColumn(frb, reportColumns, "productName", "Product", "java.lang.String", 100);
			//addColumn(frb, reportColumns, "productDescription", "Product Description", "java.lang.String", 100);

			addColumn(frb, reportColumns, "lotNumber", "Lot", "java.lang.String", 30);
         addColumn(frb, reportColumns, "fill", "Fill", "java.lang.String", 30);
         addColumn(frb, reportColumns, "containerCode", "Container", "java.lang.String", 100);
         addColumn(frb, reportColumns, "closure", "Closure", "java.lang.String", 30);			
			addColumn(frb, reportColumns, "studyId", "ID", "java.lang.String", 30);
			addColumn(frb, reportColumns, "studyCondition", "Condition", "java.lang.String", 60);

			addColumn(frb, reportColumns, "formula", "Formula", "java.lang.String", 50);
         addColumn(frb, reportColumns, "storageLocation", "Location", "java.lang.String", 30);
         if (reportName.compareTo("PULL_DAYS_REPORT")==0)
         {
           
         }
         else
         {
            addColumn(frb, reportColumns,"initialDate", "Initial","java.util.Date", 40);
         }        
         addColumn(frb, reportColumns, "studyStatus", "Status", "java.lang.String", 25);
         addColumn(frb, reportColumns, "studyReasonCode", "Reason", "java.lang.String", 25);
         		
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
			if (reportName.compareTo("PULL_DAYS_REPORT")==0)
			{
			   int maxColumns = getMaxPullDateColumn(pendingList);
            for (int idx=0; idx<=maxColumns; idx++)
             {
                String pullDateColumn = "pullDate"+idx;
                  for (Iterator<RptUserPreferenceVO> iterator = displayColumns.iterator(); iterator.hasNext();) {
                     //String columnName = (String) iterator.next();
                     RptUserPreferenceVO rptUserPref = new RptUserPreferenceVO();
                     rptUserPref = iterator.next();
                     if ((rptUserPref.getFieldName()).compareTo(pullDateColumn)==0)
                     {
                        addColumn(frb, reportColumns,pullDateColumn, rptUserPref.getFieldHeader(), "java.util.Date", 40);
                     }
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
   private int getMaxPullDateColumn(List<ProductStudyIntervalsVO> pendingList)
   {
      int maxColumns = 0;
         for (ProductStudyIntervalsVO item:pendingList)
         {
            if (item.getPullDate19() != null )
            {
               maxColumns = 19;
               break;
            }
            else if (item.getPullDate18() != null )
            {
              maxColumns = 18 > maxColumns ? 18:maxColumns;
            }
            else if (item.getPullDate17() != null )
            {
              maxColumns = 17 > maxColumns ? 17:maxColumns;
            }
            else if (item.getPullDate16() != null )
            {
              maxColumns = 16 > maxColumns ? 16:maxColumns;
            }
            else if (item.getPullDate15() != null )
            {
              maxColumns = 15 > maxColumns ? 15:maxColumns;
            }
            else if (item.getPullDate14() != null )
            {
              maxColumns = 14 > maxColumns ? 14:maxColumns;
            }
            else if (item.getPullDate13() != null )
            {
              maxColumns = 13 > maxColumns ? 13:maxColumns;
            }
            else if (item.getPullDate12() != null )
            {
              maxColumns = 12 > maxColumns ? 12:maxColumns;
            }
            else if (item.getPullDate11() != null )
            {
              maxColumns = 11 > maxColumns ? 11:maxColumns;
            }
            else if (item.getPullDate10() != null )
            {
              maxColumns = 10 > maxColumns ? 10:maxColumns;
            }
            else if (item.getPullDate9() != null )
            {
              maxColumns = 9 > maxColumns ? 9:maxColumns;
            }
            else if (item.getPullDate8() != null )
            {
              maxColumns = 8 > maxColumns ? 8:maxColumns;
            }
            else if (item.getPullDate7() != null )
            {
              maxColumns = 7 > maxColumns ? 7:maxColumns;
            }   
            else if (item.getPullDate6() != null )
            {
              maxColumns = 6 > maxColumns ? 6:maxColumns;
            }   
            else if (item.getPullDate5() != null )
            {
              maxColumns = 5 > maxColumns ? 5:maxColumns;
            } 
            else if (item.getPullDate4() != null )
            {
              maxColumns = 4 > maxColumns ? 4:maxColumns;
            }   
            else if (item.getPullDate3() != null )
            {
              maxColumns = 3 > maxColumns ? 3:maxColumns;
            }     
            else if (item.getPullDate2() != null )
            {
              maxColumns = 2 > maxColumns ? 2:maxColumns;
            } 
            else if (item.getPullDate1() != null )
            {
              maxColumns = 1 > maxColumns ? 1:maxColumns;
            }             
         }
      return maxColumns;
   }
}