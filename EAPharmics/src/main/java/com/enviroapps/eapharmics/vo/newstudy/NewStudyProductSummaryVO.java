package com.enviroapps.eapharmics.vo.newstudy;

import java.util.Date;

public class NewStudyProductSummaryVO {

	private static final long serialVersionUID = 1L;

	private Long prdStudyBatchId;
	private Long productId;
	private String lotNumber;
	private Long labelId;
	private String studyCondition;
	private String productCode;
	private String productName;
	private String productDescription;
	private String studyId;
	private String displayString;
	
	private String expiry;
	private String batchSize;
	private Date packageDate;
	private Date mfgDate;
	private Date onStabilityDate;
	private String studyStatus;
	private String proprietaryStudyId;
	private Boolean approvalLevel1;
	
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
 * @return the labelId
 */
public Long getLabelId() {
	return labelId;
}
/**
 * @param labelId the labelId to set
 */
public void setLabelId(Long labelId) {
	this.labelId = labelId;
}
/**
 * @return the studyCondition
 */
public String getStudyCondition() {
	return studyCondition;
}
/**
 * @param studyCondition the studyCondition to set
 */
public void setStudyCondition(String studyCondition) {
	this.studyCondition = studyCondition;
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
 * @return the iD
 */
public String getStudyId() {
	return studyId;
}
/**
 * @param id the iD to set
 */
public void setStudyId(String id) {
	studyId = id;
}

public String getProprietaryStudyId(){
   return proprietaryStudyId;
}

public void setProprietaryStudyId(String proprietaryStudyId){
   this.proprietaryStudyId=proprietaryStudyId;
}
/**
 * @return the displayString
 */
public String getDisplayString() {
	return displayString;
}
/**
 * @param displayString the displayString to set
 */
public void setDisplayString(String displayString) {
	this.displayString = displayString;
}
/**
 * @return the expiry
 */
public String getExpiry() {
	return expiry;
}
/**
 * @param expiry the expiry to set
 */
public void setExpiry(String expiry) {
	this.expiry = expiry;
}
/**
 * @return the batchSize
 */
public String getBatchSize() {
	return batchSize;
}
/**
 * @param batchSize the batchSize to set
 */
public void setBatchSize(String batchSize) {
	this.batchSize = batchSize;
}
/**
 * @return the packageDate
 */
public Date getPackageDate() {
	return packageDate;
}
/**
 * @param packageDate the packageDate to set
 */
public void setPackageDate(Date packageDate) {
	this.packageDate = packageDate;
}
/**
 * @return the mfgDate
 */
public Date getMfgDate() {
	return mfgDate;
}
/**
 * @param mfgDate the mfgDate to set
 */
public void setMfgDate(Date mfgDate) {
	this.mfgDate = mfgDate;
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
 * @return the studyStatus
 */
public String getStudyStatus() {
	return studyStatus;
}
/**
 * @param studyStatus the studyStatus to set
 */
public void setStudyStatus(String studyStatus) {
	this.studyStatus = studyStatus;
}
/**
 * @return the approvalLevel1
 */
public Boolean getApprovalLevel1() {
	return approvalLevel1;
}
/**
 * @param approvalLevel1 the approvalLevel1 to set
 */
public void setApprovalLevel1(Boolean approvalLevel1) {
	this.approvalLevel1 = approvalLevel1;
}
}
