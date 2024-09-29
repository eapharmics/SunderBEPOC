package com.enviroapps.eapharmics.vo.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductVO {

	private static final long serialVersionUID = 1L;

	private Long productId;
	private String productCode;
	private String productName;
	private String productDescription;
	private String expirationPeriod;
	private Date insertDate;
	private Long insertUser;
	private Date updateDate;
	private Long updateUser;
	private Long auditId;
	private Date startDate;
	private Date endDate;
	private String comments;
	private Boolean approvalLevel1;
	private Long approvalLevel1By;
	private Date approvalLevel1Date;
	private Boolean approvalLevel2;
	private Long approvalLevel2By;
	private Date approvalLevel2Date;
	private Boolean active;
	private List<ProductBatchVO> batches = new ArrayList<ProductBatchVO>();
	private List<ProductFormulationVO> formulations = new ArrayList<ProductFormulationVO>();
	private List<ProtocolVO> protocols = new ArrayList<ProtocolVO>();
	private List<ProductTestVO> productTests = new ArrayList<ProductTestVO>();
	private String approvalLevelRequired = "";
	private int version;
	private String productType;
	private Date annualReportAnniversaryDate;
	private Boolean readyForApproval;
	private String mailMessage;
	private Long locationId;
	private Boolean approvedOnce;
	private Boolean isLevel1Approval = false;
	private Boolean isLeve12Approval = false;
	
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
	 * @return the expirationPeriod
	 */
	public String getExpirationPeriod() {
		return expirationPeriod;
	}
	/**
	 * @param expirationPeriod the expirationPeriod to set
	 */
	public void setExpirationPeriod(String expirationPeriod) {
		this.expirationPeriod = expirationPeriod;
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
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	 * @return the batches
	 */
	public List<ProductBatchVO> getBatches() {
		return batches;
	}
	/**
	 * @param batches the batches to set
	 */
	public void setBatches(List<ProductBatchVO> batches) {
		this.batches = batches;
	}
	/**
	 * @return the formulations
	 */
	public List<ProductFormulationVO> getFormulations() {
		return formulations;
	}
	/**
	 * @param formulations the formulations to set
	 */
	public void setFormulations(List<ProductFormulationVO> formulations) {
		this.formulations = formulations;
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
	/**
	 * @return the approvalLevel1By
	 */
	public Long getApprovalLevel1By() {
		return approvalLevel1By;
	}
	/**
	 * @param approvalLevel1By the approvalLevel1By to set
	 */
	public void setApprovalLevel1By(Long approvalLevel1By) {
		this.approvalLevel1By = approvalLevel1By;
	}
	/**
	 * @return the approvalLevel1Date
	 */
	public Date getApprovalLevel1Date() {
		return approvalLevel1Date;
	}
	/**
	 * @param approvalLevel1Date the approvalLevel1Date to set
	 */
	public void setApprovalLevel1Date(Date approvalLevel1Date) {
		this.approvalLevel1Date = approvalLevel1Date;
	}
	/**
	 * @return the approvalLevel2
	 */
	public Boolean getApprovalLevel2() {
		return approvalLevel2;
	}
	/**
	 * @param approvalLevel2 the approvalLevel2 to set
	 */
	public void setApprovalLevel2(Boolean approvalLevel2) {
		this.approvalLevel2 = approvalLevel2;
	}
	/**
	 * @return the approvalLevel2By
	 */
	public Long getApprovalLevel2By() {
		return approvalLevel2By;
	}
	/**
	 * @param approvalLevel2By the approvalLevel2By to set
	 */
	public void setApprovalLevel2By(Long approvalLevel2By) {
		this.approvalLevel2By = approvalLevel2By;
	}
	/**
	 * @return the approvalLevel2Date
	 */
	public Date getApprovalLevel2Date() {
		return approvalLevel2Date;
	}
	/**
	 * @param approvalLevel2Date the approvalLevel2Date to set
	 */
	public void setApprovalLevel2Date(Date approvalLevel2Date) {
		this.approvalLevel2Date = approvalLevel2Date;
	}
	/**
	 * @return the productTests
	 */
	public List<ProductTestVO> getProductTests() {
		return productTests;
	}
	/**
	 * @param productTests the productTests to set
	 */
	public void setProductTests(List<ProductTestVO> productTests) {
		this.productTests = productTests;
	}
	/**
	 * @return the protocols
	 */
	public List<ProtocolVO> getProtocols() {
		return protocols;
	}
	/**
	 * @param protocols the protocols to set
	 */
	public void setProtocols(List<ProtocolVO> protocols) {
		this.protocols = protocols;
	}
	/**
	 * @return the approvalLevelRequired
	 */
	public String getApprovalLevelRequired() {
		return approvalLevelRequired;
	}
	/**
	 * @param approvalLevelRequired the approvalLevelRequired to set
	 */
	public void setApprovalLevelRequired(String approvalLevelRequired) {
		this.approvalLevelRequired = approvalLevelRequired;
	}
	/**
	 * @return the active
	 */
	public Boolean getActive() {
		return active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}
	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}
	/**
	 * @return the productType
	 */
	public String getProductType() {
		return productType;
	}
	/**
	 * @param productType the productType to set
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}
	/**
	 * @return the annualReportAnniversaryDate
	 */
	public Date getAnnualReportAnniversaryDate() {
		return annualReportAnniversaryDate;
	}
	/**
	 * @param annualReportAnniversaryDate the annualReportAnniversaryDate to set
	 */
	public void setAnnualReportAnniversaryDate(Date annualReportAnniversaryDate) {
		this.annualReportAnniversaryDate = annualReportAnniversaryDate;
	}
	/**
	 * @return the readyForApproval
	 */
	public Boolean getReadyForApproval() {
		return readyForApproval;
	}
	/**
	 * @param readyForApproval the readyForApproval to set
	 */
	public void setReadyForApproval(Boolean readyForApproval) {
		this.readyForApproval = readyForApproval;
	}
	/**
	 * @return the mailMessage
	 */
	public String getMailMessage() {
		return mailMessage;
	}
	/**
	 * @param mailMessage the mailMessage to set
	 */
	public void setMailMessage(String mailMessage) {
		this.mailMessage = mailMessage;
	}

	/**
	 * @param locationId the locationId to set
	 */
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	/**
	 * 
	 * @return the locationId
	 */
	public Long getLocationId() {
		return locationId;
	}
	/**
	 * @return the approvedOnce
	 */
	public Boolean getApprovedOnce() {
		return approvedOnce;
	}
	/**
	 * @param approvedOnce the approvedOnce to set
	 */
	public void setApprovedOnce(Boolean approvedOnce) {
		this.approvedOnce = approvedOnce;
	}
	/**
	 * @return the isLevel1Approval
	 */
	public Boolean getIsLevel1Approval() {
		return isLevel1Approval;
	}
	/**
	 * @param isLevel1Approval the isLevel1Approval to set
	 */
	public void setIsLevel1Approval(Boolean isLevel1Approval) {
		this.isLevel1Approval = isLevel1Approval;
	}
	/**
	 * @return the isLeve12Approval
	 */
	public Boolean getIsLeve12Approval() {
		return isLeve12Approval;
	}
	/**
	 * @param isLeve12Approval the isLeve12Approval to set
	 */
	public void setIsLeve12Approval(Boolean isLeve12Approval) {
		this.isLeve12Approval = isLeve12Approval;
	}
}
