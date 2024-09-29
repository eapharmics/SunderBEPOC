package com.enviroapps.eapharmics.bom.product;

import java.io.Serializable;
import java.util.Date;

public class ProductTest {

	private static final long serialVersionUID = 1L;

	private Long productTestId;
	private Long productId;
	private Long sortOrderPrevious;
	private Long sortOrderCurrent;
	private Date insertDate;
	private Long insertUser;
	private Date updateDate;
	private Long updateUser;
	private Long auditId;
	private String comments;
	private String testName;
	private String lowerLimit;
	private String upperLimit;
	private Long numOfEntries;
	private String method;
	private Long decimalPoints;
	private String resultsFormat;
	private String measurement;
	private Boolean graph;
	private String labNumber;
	private String textLine;
	private String alertLowerLimit;
	private String alertUpperLimit;
	private String alertLowerVariance;
	private String alertUpperVariance;
	private Product product;
	private String textLimit;

	/* (non-Javadoc)
	 * @see com.enviroapps.das.persistence.AbstractPersistableObject#getPK()
	 */
	public Serializable getPK() {
		return productTestId;
	}
	/* (non-Javadoc)
	 * @see com.enviroapps.das.persistence.AbstractPersistableObject#loadPK(java.lang.Object[])
	 */
	public void loadPK(Object[] pks) {
		productTestId = (Long)pks[0];
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
	 * @return the productId
	 */
	public Long getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	/**
	 * @return the sortOrderPrevious
	 */
	public Long getSortOrderPrevious() {
		return sortOrderPrevious;
	}
	/**
	 * @param sortOrderPrevious the sortOrderPrevious to set
	 */
	public void setSortOrderPrevious(Long sortOrderPrevious) {
		this.sortOrderPrevious = sortOrderPrevious;
	}
	/**
	 * @return the sortOrderCurrent
	 */
	public Long getSortOrderCurrent() {
		return sortOrderCurrent;
	}
	/**
	 * @param sortOrderCurrent the sortOrderCurrent to set
	 */
	public void setSortOrderCurrent(Long sortOrderCurrent) {
		this.sortOrderCurrent = sortOrderCurrent;
	}
	/**
	 * @return the insertDate
	 */
	public Date getInsertDate() {
		return insertDate;
	}
	/**
	 * @param insertDate the insertDate to set
	 */
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	/**
	 * @return the insertUser
	 */
	public Long getInsertUser() {
		return insertUser;
	}
	/**
	 * @param insertUser the insertUser to set
	 */
	public void setInsertUser(Long insertUser) {
		this.insertUser = insertUser;
	}
	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}
	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * @return the updateUser
	 */
	public Long getUpdateUser() {
		return updateUser;
	}
	/**
	 * @param updateUser the updateUser to set
	 */
	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}
	/**
	 * @return the auditId
	 */
	public Long getAuditId() {
		return auditId;
	}
	/**
	 * @param auditId the auditId to set
	 */
	public void setAuditId(Long auditId) {
		this.auditId = auditId;
	}
	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
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
	 * @return the lowerLimit
	 */
	public String getLowerLimit() {
		return lowerLimit;
	}
	/**
	 * @param lowerLimit the lowerLimit to set
	 */
	public void setLowerLimit(String lowerLimit) {
		this.lowerLimit = lowerLimit;
	}
	/**
	 * @return the upperLimit
	 */
	public String getUpperLimit() {
		return upperLimit;
	}
	/**
	 * @param upperLimit the upperLimit to set
	 */
	public void setUpperLimit(String upperLimit) {
		this.upperLimit = upperLimit;
	}
	/**
	 * @return the numOfEntries
	 */
	public Long getNumOfEntries() {
		return numOfEntries;
	}
	/**
	 * @param numOfEntries the numOfEntries to set
	 */
	public void setNumOfEntries(Long numOfEntries) {
		this.numOfEntries = numOfEntries;
	}
	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}
	/**
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	/**
	 * @return the decimalPoints
	 */
	public Long getDecimalPoints() {
		return decimalPoints;
	}
	/**
	 * @param decimalPoints the decimalPoints to set
	 */
	public void setDecimalPoints(Long decimalPoints) {
		this.decimalPoints = decimalPoints;
	}
	/**
	 * @return the resultsFormat
	 */
	public String getResultsFormat() {
		return resultsFormat;
	}
	/**
	 * @param resultsFormat the resultsFormat to set
	 */
	public void setResultsFormat(String resultsFormat) {
		this.resultsFormat = resultsFormat;
	}
	/**
	 * @return the measurement
	 */
	public String getMeasurement() {
		return measurement;
	}
	/**
	 * @param measurement the measurement to set
	 */
	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}
	/**
	 * @return the graph
	 */
	public Boolean getGraph() {
		return graph;
	}
	/**
	 * @param graph the graph to set
	 */
	public void setGraph(Boolean graph) {
		this.graph = graph;
	}
	/**
	 * @return the labNumber
	 */
	public String getLabNumber() {
		return labNumber;
	}
	/**
	 * @param labNumber the labNumber to set
	 */
	public void setLabNumber(String labNumber) {
		this.labNumber = labNumber;
	}
	/**
	 * @return the textLine
	 */
	public String getTextLine() {
		return textLine;
	}
	/**
	 * @param textLine the textLine to set
	 */
	public void setTextLine(String textLine) {
		this.textLine = textLine;
	}
	/**
	 * @return the alertLowerLimit
	 */
	public String getAlertLowerLimit() {
		return alertLowerLimit;
	}
	/**
	 * @param alertLowerLimit the alertLowerLimit to set
	 */
	public void setAlertLowerLimit(String alertLowerLimit) {
		this.alertLowerLimit = alertLowerLimit;
	}
	/**
	 * @return the alertUpperLimit
	 */
	public String getAlertUpperLimit() {
		return alertUpperLimit;
	}
	/**
	 * @param alertUpperLimit the alertUpperLimit to set
	 */
	public void setAlertUpperLimit(String alertUpperLimit) {
		this.alertUpperLimit = alertUpperLimit;
	}
	/**
	 * @return the alertLowerVariance
	 */
	public String getAlertLowerVariance() {
		return alertLowerVariance;
	}
	/**
	 * @param alertLowerVariance the alertLowerVariance to set
	 */
	public void setAlertLowerVariance(String alertLowerVariance) {
		this.alertLowerVariance = alertLowerVariance;
	}
	/**
	 * @return the alertUpperVariance
	 */
	public String getAlertUpperVariance() {
		return alertUpperVariance;
	}
	/**
	 * @param alertUpperVariance the alertUpperVariance to set
	 */
	public void setAlertUpperVariance(String alertUpperVariance) {
		this.alertUpperVariance = alertUpperVariance;
	}
	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}
	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
	/**
	 * @return the textLimit
	 */
	public String getTextLimit() {
		return textLimit;
	}
	/**
	 * @param textLimit the textLimit to set
	 */
	public void setTextLimit(String textLimit) {
		this.textLimit = textLimit;
	}
	/**
	 * @param displayName the displayName to set
	 */
	public String computeDisplayName() {
		if (testName == null || testName == "") {
			return "";
		}
		if (textLimit == null || textLimit == "") {
			if (lowerLimit != null && lowerLimit.length() > 0 &&
				upperLimit != null && upperLimit.length() > 0) {
				return testName + " - " + lowerLimit + " to " + upperLimit;
			} else if (lowerLimit != null && lowerLimit.length() > 0) {
				return testName + " NLT " + lowerLimit;
			} else if (upperLimit != null && upperLimit.length() > 0) {
				return testName + " NMT " + upperLimit;
			} else {
				return  testName;
			}
		} else {
			return testName + " - " + textLimit;
		}
	}
}
