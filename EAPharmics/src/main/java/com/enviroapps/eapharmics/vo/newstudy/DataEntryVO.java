/**
 * 
 */
package com.enviroapps.eapharmics.vo.newstudy;

import java.util.Date;

/**
 * @author EAPharmics
 *
 */
public class DataEntryVO {

	private Long prdStudyTestResultsId;
	private Long productTestId;
	private int intervalValue;
	private String result;
	private int resultCount;
	private String prevResult;
	private String initialResult;
	private String changeFromPrev;
	private String changeFromInitial;
	private String analyst;
	private Date date;
	private String reference;
	private Boolean changeFromInitailOOR;
	private Boolean changeFromPrevOOR;
	/**
	 * 
	 */
	public DataEntryVO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the prdStudyTestResultsId
	 */
	public Long getPrdStudyTestResultsId() {
		return prdStudyTestResultsId;
	}

	/**
	 * @param prdStudyTestResultsId the prdStudyTestResultsId to set
	 */
	public void setPrdStudyTestResultsId(Long prdStudyTestResultsId) {
		this.prdStudyTestResultsId = prdStudyTestResultsId;
	}

	/**
	 * @return the productTestId
	 */
	public Long getProductTestId() {
		return productTestId;
	}

	/**
	 * @param productTestId the productTestId to set
	 */
	public void setProductTestId(Long productTestId) {
		this.productTestId = productTestId;
	}

	/**
	 * @return the intervalValue
	 */
	public int getIntervalValue() {
		return intervalValue;
	}

	/**
	 * @param intervalValue the intervalValue to set
	 */
	public void setIntervalValue(int intervalValue) {
		this.intervalValue = intervalValue;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * @return the resultCount
	 */
	public int getResultCount() {
		return resultCount;
	}

	/**
	 * @param resultCount the resultCount to set
	 */
	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}

	/**
	 * @return the prevResult
	 */
	public String getPrevResult() {
		return prevResult;
	}

	/**
	 * @param prevResult the prevResult to set
	 */
	public void setPrevResult(String prevResult) {
		this.prevResult = prevResult;
	}

	/**
	 * @return the initialResult
	 */
	public String getInitialResult() {
		return initialResult;
	}

	/**
	 * @param initialResult the initialResult to set
	 */
	public void setInitialResult(String initialResult) {
		this.initialResult = initialResult;
	}

	/**
	 * @return the changeFromPrev
	 */
	public String getChangeFromPrev() {
		return changeFromPrev;
	}

	/**
	 * @param changeFromPrev the changeFromPrev to set
	 */
	public void setChangeFromPrev(String changeFromPrev) {
		this.changeFromPrev = changeFromPrev;
	}

	/**
	 * @return the changeFromInitial
	 */
	public String getChangeFromInitial() {
		return changeFromInitial;
	}

	/**
	 * @param changeFromInitial the changeFromInitial to set
	 */
	public void setChangeFromInitial(String changeFromInitial) {
		this.changeFromInitial = changeFromInitial;
	}

	/**
	 * @return the analyst
	 */
	public String getAnalyst() {
		return analyst;
	}

	/**
	 * @param analyst the analyst to set
	 */
	public void setAnalyst(String analyst) {
		this.analyst = analyst;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * @param reference the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * @return the changeFromInitailOOR
	 */
	public Boolean getChangeFromInitailOOR() {
		return changeFromInitailOOR;
	}

	/**
	 * @param changeFromInitailOOR the changeFromInitailOOR to set
	 */
	public void setChangeFromInitailOOR(Boolean changeFromInitailOOR) {
		this.changeFromInitailOOR = changeFromInitailOOR;
	}

	/**
	 * @return the changeFromPrevOOR
	 */
	public Boolean getChangeFromPrevOOR() {
		return changeFromPrevOOR;
	}

	/**
	 * @param changeFromPrevOOR the changeFromPrevOOR to set
	 */
	public void setChangeFromPrevOOR(Boolean changeFromPrevOOR) {
		this.changeFromPrevOOR = changeFromPrevOOR;
	}

}
