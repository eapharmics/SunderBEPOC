package com.enviroapps.eapharmics.vo.reports;

import java.util.HashMap;


public class ReportsVO {

	private static final long serialVersionUID = 1L;

	private String reportName;
	private HashMap parameters;
	private String returnURL;

	/**
	 * @return the reportName
	 */
	public String getReportName() {
		return reportName;
	}

	/**
	 * @param reportName the reportName to set
	 */
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	/**
	 * @return the parameters
	 */
	public HashMap getParameters() {
		return parameters;
	}

	/**
	 * @param parameters the parameters to set
	 */
	public void setParameters(HashMap parameters) {
		this.parameters = parameters;
	}

	/**
	 * @return the returnURL
	 */
	public String getReturnURL() {
		return returnURL;
	}

	/**
	 * @param returnURL the returnURL to set
	 */
	public void setReturnURL(String returnMessage) {
		this.returnURL = returnMessage;
	}

	
}
