package com.enviroapps.eapharmics.vo.dataaccessevaluation;

import java.util.Date;

public class DataAccessEvaluationVO {
	
	private Long productId;
	private String productCode;
	private String productName;
	private String productDescription;
	private String studyId;
	private String lotNumber;
	private Date onStabilityDate;
	private Long prdStudyBatchId;
	private String studyCondition;
	private String displayString;
	private String normalEnvConditionsCode;
	private String challengeConditionsCode;
    private String fillCode;
    private String containerCode;
    
    public String getFillCode() {
		return fillCode;
	}
	public void setFillCode(String fillCode) {
		this.fillCode = fillCode;
	}
	public String getContainerCode() {
		return containerCode;
	}
	public void setContainerCode(String containerCode) {
		this.containerCode = containerCode;
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
	 * @return the productCode
	 */
	public String getProductCode() {
		return productCode;
	}
	/**
	 * @param productCode the productCode to set
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
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
	 * @return the productDescription
	 */
	public String getProductDescription() {
		return productDescription;
	}
	/**
	 * @param productDescription the productDescription to set
	 */
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
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
	 * @return the prdStudyBatchId
	 */
	public Long getPrdStudyBatchId() {
		return prdStudyBatchId;
	}
	/**
	 * @param prdStudyBatchId the prdStudyBatchId to set
	 */
	public void setPrdStudyBatchId(Long prdStudyBatchId) {
		this.prdStudyBatchId = prdStudyBatchId;
	}
	/**
	 * @return the normalEnvConditionsCode
	 */
	public String getNormalEnvConditionsCode() {
		return normalEnvConditionsCode;
	}
	/**
	 * @param normalEnvConditionsCode the normalEnvConditionsCode to set
	 */
	public void setNormalEnvConditionsCode(String normalEnvConditionsCode) {
		this.normalEnvConditionsCode = normalEnvConditionsCode;
	}
	/**
	 * @return the challengeConditionsCode
	 */
	public String getChallengeConditionsCode() {
		return challengeConditionsCode;
	}
	/**
	 * @param challengeConditionsCode the challengeConditionsCode to set
	 */
	public void setChallengeConditionsCode(String challengeConditionsCode) {
		this.challengeConditionsCode = challengeConditionsCode;
	}

	public String getDisplayString() {
		return displayString;
	}
	/**
	 * @param displayString the displayString to set
	 */
	public void setDisplayString(String displayString) {
		this.displayString = displayString;
	}
	
	public String getStudyCondition() {
		return studyCondition;
	}
	/**
	 * @param studyCondition the studyCondition to set
	 */
	public void setStudyCondition(String studyCondition) {
		this.studyCondition = studyCondition;
	}
	
}

