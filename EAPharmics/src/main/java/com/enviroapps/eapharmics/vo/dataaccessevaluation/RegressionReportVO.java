package com.enviroapps.eapharmics.vo.dataaccessevaluation;



public class RegressionReportVO {
	
	
	private String studyId;
	private Long prdTestId;
	private String testName;
	private Long sortOrderCurrent;
	private Long numberOfResults;
	
    
    
	
	/**
	 * @return the TestId
	 */
	public Long getprdTestId() {
		return prdTestId;
	}
	/**
	 * @param TestId the productId to set
	 */
	public void setprdTestId(Long prdTestId) {
		this.prdTestId =prdTestId;
	}
	/**
	 * @return the TestName
	 */
	public String getTestName() {
		return testName;
	}
	/**
	 * @param TestName the TestName to set
	 */
	public void setTestName(String testName) {
		this.testName = testName;
	}
	
	/**
	 * @return the studyId
	 */
	public String getStudyId() {
		return studyId;
	}
	/**
	 * @param studyId the studyId to set
	 */
	public void setStudyId(String studyId) {
		this.studyId = studyId;
	}
	
	/**
	 * @return the sortOrderResult
	 */
	public Long getsortOrderCurrent() {
		return sortOrderCurrent;
	}
	/**
	 * @param sortOrderCurrent the sortOrderCurrent to set
	 */
	public void setsortOrderCurrent(Long sortOrderCurrent) {
		this.sortOrderCurrent =sortOrderCurrent;
	}
	
	/**
	 * @return the numberOfResults
	 */
	public Long getnumberOfResults() {
		return numberOfResults;
	}
	/**
	 * @param numberOfResults the numberOfResults to set
	 */
	public void setnumberOfResults(Long numberOfResults) {
		this.numberOfResults =numberOfResults;
	}
	
}

