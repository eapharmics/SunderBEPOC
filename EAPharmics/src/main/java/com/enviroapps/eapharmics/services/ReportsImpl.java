package com.enviroapps.eapharmics.services;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import com.enviroapps.eapharmics.das.persistence.hibernate.HibernatePersistenceManager;
import com.enviroapps.eapharmics.exception.EAPharmicsException;
import com.enviroapps.eapharmics.util.EAReportsHelper;
import com.enviroapps.eapharmics.util.PdfHelper;
import com.enviroapps.eapharmics.vo.newstudy.RegressionDataContainerVO;
import com.enviroapps.eapharmics.vo.reports.ReportsVO;



/**
 * @author EnviroApps
 * 
 */
public class ReportsImpl extends AbstractServiceImpl {
	
	private Integer getReportRunSequence() throws Exception {
		Integer reportRunSequence = new Integer(0);
		Session session = HibernatePersistenceManager.getInstance().getCurrentSession();		
			List seq = session.createSQLQuery("SELECT report_run_seq.NEXTVAL AS REPORT_RUN_SEQ FROM dual")
			 .addScalar("REPORT_RUN_SEQ", Hibernate.INTEGER).list();
			if (seq != null && seq.size() > 0) {
				reportRunSequence = ((Integer) seq.get(0));
			}
		return reportRunSequence;
	}
	
	private ReportsVO createReportOutput(ReportsVO report, Connection conn) throws EAPharmicsException {
		ReportsVO reportsVO = new ReportsVO();
		Integer reportRunSequence = new Integer(0);
		JasperPrint jpr = null;		
		try {
			reportRunSequence = getReportRunSequence();
			String reportOutputDir = EAReportsHelper.REPORTS_BASE_DIR + "/output";
			HashMap reportParams = report.getParameters();
			reportParams.put("SUBREPORT_DIR", EAReportsHelper.REPORTS_BASE_DIR + "/");
			reportParams.put("P_IMAGE_DIR", EAReportsHelper.REPORTS_BASE_DIR + "/");
    		reportsVO.setParameters(reportParams);

			String reportOutput = report.getReportName() + reportRunSequence.intValue();
			jpr = JasperFillManager.fillReport( EAReportsHelper.REPORTS_BASE_DIR + "/" + report.getReportName() + ".jasper", report.getParameters(), conn);			
			File outputFile = File.createTempFile(reportOutput, ".pdf", new File(reportOutputDir));
			FileOutputStream outputStream = new FileOutputStream(outputFile);
			JasperExportManager.exportReportToPdfStream(jpr, outputStream);
			outputStream.flush();
			outputStream.close();
			// JasperExportManager.exportReportToPdfFile(jpr, reportOutputDir + "/" + reportOutput);
            // check to see if there is any margin settings
            if (reportParams.get("P_REPORT_SETTINGS") != null) {
            	HashMap settings = (HashMap) reportParams.get("P_REPORT_SETTINGS");
            	if (settings.get("P_LEFT_MARGIN") != null) {
            		PdfHelper.changeMargins(reportOutputDir + "/" + outputFile.getName(), 
            				settings.get("P_LEFT_MARGIN").toString(),
            				settings.get("P_RIGHT_MARGIN").toString(),
            				settings.get("P_TOP_MARGIN").toString(), 
            				settings.get("P_BOTTOM_MARGIN").toString());
            	}
            } 
            reportsVO.setReturnURL(EAReportsHelper.REPORTS_BASE_URL + outputFile.getName());
			jpr = null;
		} catch (Throwable e) {
			log.error(this, "JRException", e);
			throw new EAPharmicsException("Error running report");
		}
		
		return reportsVO;
	}
	
	public String runReport(ReportsVO report) throws EAPharmicsException {
		HibernatePersistenceManager hpm = HibernatePersistenceManager.getInstance();
		Session session = hpm.openSession();		
		Connection conn = null;
		try {
			conn = session.connection();
			return runReport(report, conn);
		} catch (Throwable e) {
			log.error(this, "runMultipleReports", e);
			throw new EAPharmicsException("Error running report");
		} finally {
	        if ( conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					log.error(this, "SQLException", e);
				}
	        }
		}
	}

	private String runReport(ReportsVO report, Connection conn) throws EAPharmicsException {
		return createReportOutput(report, conn).getReturnURL();
	}

	public String runMultipleReports(List <ReportsVO> reports) throws EAPharmicsException {
		HibernatePersistenceManager hpm = HibernatePersistenceManager.getInstance();
		Session session = hpm.openSession();		
		Connection conn = null;
		String mergedPdfOutputFile = "";
		ArrayList<String> pdfOutputFiles = new ArrayList<String>();
		try {
			ReportsVO reportsVO = null;
			conn = session.connection();;
			for (Iterator<ReportsVO> iterator = reports.iterator(); iterator.hasNext();) {
				reportsVO = (ReportsVO) iterator.next();
				String fileName =  EAReportsHelper.REPORTS_OUTPUT_DIR + "/" + runReport(reportsVO, conn).replace(EAReportsHelper.REPORTS_BASE_URL, "");
				pdfOutputFiles.add(fileName);
			}
			mergedPdfOutputFile = reportsVO.getReportName() + getReportRunSequence().intValue();
			File outputFile = File.createTempFile(mergedPdfOutputFile, ".pdf", new File(EAReportsHelper.REPORTS_OUTPUT_DIR));
			mergedPdfOutputFile = outputFile.getName();
			PdfHelper.mergePdfs(pdfOutputFiles, EAReportsHelper.REPORTS_OUTPUT_DIR + "/" + mergedPdfOutputFile);
		} catch (Throwable e) {
			log.error(this, "runMultipleReports", e);
			throw new EAPharmicsException("Error running report");
		} finally {
	        if ( conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					log.error(this, "SQLException", e);
				}
	        }
		}
		
		return EAReportsHelper.REPORTS_BASE_URL + mergedPdfOutputFile;
	}	
	
	public String[] getRegressionAscii(String studyId, String testId, String testNumber) {
		try {
	    	ArrayList<String> queryOutput = new ArrayList<String>();
			HibernatePersistenceManager hpm = HibernatePersistenceManager.getInstance();
			Session session = hpm.openSession();		
			Connection conn = session.connection();
				String query = "SELECT COLUMN_VALUE TXT FROM TABLE(get_regression_ascii('" + studyId + "'," + testId + "," + testNumber + "))";
				List outputLines = session.createSQLQuery(query)
				 .addScalar("TXT", Hibernate.STRING).list();
				queryOutput = new ArrayList(outputLines);			
			String [] ret = new String[queryOutput.size()];
			int i = 0;
			for (Iterator iterator = queryOutput.iterator(); iterator.hasNext();) {
				String string = (String) iterator.next();
				ret[i] = string;
				i++;
			}
	    	return ret;
    	} catch (Exception e) {
			log.error(this, "getRegressionAscii", e);
		}
    	return null;
	}
	
	public String runMultipleRegressionReport(ReportsVO report) throws EAPharmicsException {
		ReportsVO reportsVO = new ReportsVO();
		int reportRunSequence = 0;
		HibernatePersistenceManager hpm = HibernatePersistenceManager.getInstance();
		Session session = hpm.openSession();		
		Connection conn = session.connection();
		JasperPrint jpr = null;
		
		try {
			List seq = session.createSQLQuery("SELECT report_run_seq.NEXTVAL AS REPORT_RUN_SEQ FROM dual")
			 .addScalar("REPORT_RUN_SEQ", Hibernate.INTEGER).list();
			if (seq != null && seq.size() > 0) {
				reportRunSequence = ((Integer) seq.get(0)).intValue();
			}
			
			String reportBaseDir = EAReportsHelper.REPORTS_BASE_DIR;
			String reportOutputDir = reportBaseDir + "/output";
			HashMap reportParams = report.getParameters();
			reportParams.put("SUBREPORT_DIR", reportBaseDir + "/");
			reportParams.put("P_IMAGE_DIR", reportBaseDir + "/");
    		reportsVO.setParameters(reportParams);
    		
    		List studyIds = (ArrayList) reportParams.get("P_STUDY_IDS");
    		Long productTestId = Long.parseLong((String)reportParams.get("P_PRODUCT_TEST_ID"));
    		Boolean t0percentCalc = Boolean.FALSE;
    		String lowerLimit = null;
    		String upperLimit = null;
    		if (reportParams.get("P_T0_PERCENT_CALC") != null) {
    			t0percentCalc = Boolean.TRUE;
    			lowerLimit = (String) reportParams.get("P_LOWER_LIMIT");
    			upperLimit = (String) reportParams.get("P_UPPER_LIMIT");
    		}
    		
    		NewStudyLoginImpl impl = new NewStudyLoginImpl();
    		List <RegressionDataContainerVO> reportData = impl.getMultipleRegressionReportData(studyIds, productTestId, t0percentCalc, lowerLimit, upperLimit);
    		reportParams.put("P_REPORT_DATA", reportData);
			String reportOutput = report.getReportName() + reportRunSequence + ".pdf";
			jpr = JasperFillManager.fillReport( reportBaseDir + "/" + report.getReportName() + ".jasper", report.getParameters(), conn);
            JasperExportManager.exportReportToPdfFile(jpr, reportOutputDir + "/" + reportOutput);
            reportsVO.setReturnURL("/servlet/ReportsServlet?reportOutput=" + reportOutput);
			jpr = null;
			conn.commit();
		} catch(EAPharmicsException ex) {
			log.error(this, "EAPharmicsException", ex);
			throw ex;
		} catch (Exception e) {
			log.error(this, "JRException", e);
			throw new EAPharmicsException("Error running report");
		} finally {
            if ( conn != null) {
				try {
					conn.rollback();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					log.error(this, "SQLException", e);
				}
            }
		}
		
		return reportsVO.getReturnURL();
	}

}
