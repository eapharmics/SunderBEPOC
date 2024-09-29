/**
 * 
 */
package com.enviroapps.eapharmics.vo.newstudy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author EAPharmics
 *
 */
public class RegressionDataContainerVO {

	private String studyId;
	private String lotNumber;
	private String condition;
	private String fill;
	private String container;
	private String closure;
	private Date manufacturedDate;
	private Date onStabilityDate;
	private String reasonForStudy;
	private String testName;
	private Double slope;
	private Double intercept;
	private Double intercept2;
	private List<RegressionDataVO> regressionData = new ArrayList<RegressionDataVO> ();	

	private String productName;
	private String slopeText;
	private String interceptText;
	private String commonSlope;
	private String commonIntercepts;
	private Double pSlope;
	private Double pIntercept;
	private int shelfLife;
	private String intervalLabel;

	public RegressionDataContainerVO() {

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
	 * @return the lotNumber
	 */
	public String getLotNumber() {
		return lotNumber;
	}

	/**
	 * @param lotNumber the lotNumber to set
	 */
	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}

	/**
	 * @return the condition
	 */
	public String getCondition() {
		return condition;
	}

	/**
	 * @param condition the condition to set
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}

	/**
	 * @return the fill
	 */
	public String getFill() {
		return fill;
	}

	/**
	 * @param fill the fill to set
	 */
	public void setFill(String fill) {
		this.fill = fill;
	}

	/**
	 * @return the container
	 */
	public String getContainer() {
		return container;
	}

	/**
	 * @param container the container to set
	 */
	public void setContainer(String container) {
		this.container = container;
	}

	/**
	 * @return the closure
	 */
	public String getClosure() {
		return closure;
	}

	/**
	 * @param closure the closure to set
	 */
	public void setClosure(String closure) {
		this.closure = closure;
	}

	/**
	 * @return the manufacturedDate
	 */
	public Date getManufacturedDate() {
		return manufacturedDate;
	}

	/**
	 * @param manufacturedDate the manufacturedDate to set
	 */
	public void setManufacturedDate(Date manufacturedDate) {
		this.manufacturedDate = manufacturedDate;
	}

	/**
	 * @return the onStabilityDate
	 */
	public Date getOnStabilityDate() {
		return onStabilityDate;
	}

	/**
	 * @param onStabilityDate the onStabilityDate to set
	 */
	public void setOnStabilityDate(Date onStabilityDate) {
		this.onStabilityDate = onStabilityDate;
	}

	/**
	 * @return the reasonForStudy
	 */
	public String getReasonForStudy() {
		return reasonForStudy;
	}

	/**
	 * @param reasonForStudy the reasonForStudy to set
	 */
	public void setReasonForStudy(String reasonForStudy) {
		this.reasonForStudy = reasonForStudy;
	}

	/**
	 * @return the testName
	 */
	public String getTestName() {
		return testName;
	}

	/**
	 * @param testName the testName to set
	 */
	public void setTestName(String testName) {
		this.testName = testName;
	}

	/**
	 * @return the regressionData
	 */
	public List<RegressionDataVO> getRegressionData() {
		return regressionData;
	}

	/**
	 * @param regressionData the regressionData to set
	 */
	public void setRegressionData(List<RegressionDataVO> regressionData) {
		this.regressionData = regressionData;
	}

	/**
	 * @return the slope
	 */
	public Double getSlope() {
		return slope;
	}

	/**
	 * @param slope the slope to set
	 */
	public void setSlope(Double slope) {
		this.slope = slope;
	}

	/**
	 * @return the intercept
	 */
	public Double getIntercept() {
		return intercept;
	}

	/**
	 * @param intercept the intercept to set
	 */
	public void setIntercept(Double intercept) {
		this.intercept = intercept;
	}

	/**
	 * @return the intercept2
	 */
	public Double getIntercept2() {
		return intercept2;
	}

	/**
	 * @param intercept2 the intercept2 to set
	 */
	public void setIntercept2(Double intercept2) {
		this.intercept2 = intercept2;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the slopeText
	 */
	public String getSlopeText() {
		return slopeText;
	}

	/**
	 * @param slopeText the slopeText to set
	 */
	public void setSlopeText(String slopeText) {
		this.slopeText = slopeText;
	}

	/**
	 * @return the interceptText
	 */
	public String getInterceptText() {
		return interceptText;
	}

	/**
	 * @param interceptText the interceptText to set
	 */
	public void setInterceptText(String interceptText) {
		this.interceptText = interceptText;
	}

	/**
	 * @return the commonSlope
	 */
	public String getCommonSlope() {
		return commonSlope;
	}

	/**
	 * @param commonSlope the commonSlope to set
	 */
	public void setCommonSlope(String commonSlope) {
		this.commonSlope = commonSlope;
	}

	/**
	 * @return the commonIntercepts
	 */
	public String getCommonIntercepts() {
		return commonIntercepts;
	}

	/**
	 * @param commonIntercepts the commonIntercepts to set
	 */
	public void setCommonIntercepts(String commonIntercepts) {
		this.commonIntercepts = commonIntercepts;
	}

	/**
	 * @return the pSlope
	 */
	public Double getPSlope() {
		return pSlope;
	}

	/**
	 * @param slope the pSlope to set
	 */
	public void setPSlope(Double slope) {
		pSlope = slope;
	}

	/**
	 * @return the pIntercept
	 */
	public Double getPIntercept() {
		return pIntercept;
	}

	/**
	 * @param intercept the pIntercept to set
	 */
	public void setPIntercept(Double intercept) {
		pIntercept = intercept;
	}

	/**
	 * @return the shelfLife
	 */
	public int getShelfLife() {
		return shelfLife;
	}

	/**
	 * @param shelfLife the shelfLife to set
	 */
	public void setShelfLife(int shelfLife) {
		this.shelfLife = shelfLife;
	}

	/**
	 * @return the intervalLabel
	 */
	public String getIntervalLabel() {
		return intervalLabel;
	}

	/**
	 * @param intervalLabel the intervalLabel to set
	 */
	public void setIntervalLabel(String intervalLabel) {
		this.intervalLabel = intervalLabel;
	}

}
