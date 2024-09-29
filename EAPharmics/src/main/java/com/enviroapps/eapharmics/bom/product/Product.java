package com.enviroapps.eapharmics.bom.product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Product {

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
	private Set<ProductBatch> batches = new HashSet<ProductBatch>();
	private Set<ProductFormulation> formulations = new HashSet<ProductFormulation>();
	private Set<ProductTest> productTests = new HashSet<ProductTest>();
	private Set<Protocol> protocols = new HashSet<Protocol>();
	private int version;
	private String productType;
	private Date annualReportAnniversaryDate;
	private Boolean readyForApproval;
	private Long locationId;
	private Boolean approvedOnce = Boolean.FALSE;


	/* (non-Javadoc)
	 * @see com.enviroapps.das.persistence.AbstractPersistableObject#getPK()
	 */
	public Serializable getPK() {
		return productId;
	}
	/* (non-Javadoc)
	 * @see com.enviroapps.das.persistence.AbstractPersistableObject#loadPK(java.lang.Object[])
	 */
	public void loadPK(Object[] pks) {
		productId = (Long)pks[0];
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
	public Set<ProductBatch> getBatches() {
		return batches;
	}
	/**
	 * @param batches the batches to set
	 */
	public void setBatches(Set<ProductBatch> batches) {
		this.batches = batches;
	}
	/**
	 * @return the formulations
	 */
	public Set<ProductFormulation> getFormulations() {
		return formulations;
	}
	/**
	 * @param formulations the formulations to set
	 */
	public void setFormulations(Set<ProductFormulation> formulations) {
		this.formulations = formulations;
	}
	
	/**
	 * @param productBatchId
	 * @return
	 */
	public ProductBatch getProductBatches(Long productBatchId) {
		Object[] objs = batches.toArray();
		for (int i = 0; i < objs.length; i++) {
			ProductBatch object = (ProductBatch) objs[i];
			if (object.getProductBatchId().longValue() == productBatchId) {
				return object;
			}
		}
		return null;
	}
	/**
	 * @param productFormulationId
	 * @return
	 */
	public ProductFormulation getProductFormulations(Long productFormulationId) {
		Object[] objs = formulations.toArray();
		for (int i = 0; i < objs.length; i++) {
			ProductFormulation object = (ProductFormulation) objs[i];
			if (object.getProductFormulationId().longValue() == productFormulationId) {
				return object;
			}
		}
		return null;
	}
	/**
	 * @param productTestId
	 * @return
	 */
	public ProductTest getProductTests(Long productTestId) {
		Object[] objs = productTests.toArray();
		for (int i = 0; i < objs.length; i++) {
			ProductTest object = (ProductTest) objs[i];
			if (object.getProductTestId().longValue() == productTestId) {
				return object;
			}
		}
		return null;
	}
	/**
	 * @param protocolId
	 * @return
	 */
	public Protocol getProtocols(Long protocolId) {
		Object[] objs = protocols.toArray();
		for (int i = 0; i < objs.length; i++) {
			Protocol object = (Protocol) objs[i];
			if (object.getProtocolId().longValue() == protocolId) {
				return object;
			}
		}
		return null;
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
	public Set<ProductTest> getProductTests() {
		return productTests;
	}
	/**
	 * @param productTests the productTests to set
	 */
	public void setProductTests(Set<ProductTest> productTests) {
		this.productTests = productTests;
	}
	/**
	 * @return the protocols
	 */
	public Set<Protocol> getProtocols() {
		return protocols;
	}
	/**
	 * @param protocols the protocols to set
	 */
	public void setProtocols(Set<Protocol> protocols) {
		this.protocols = protocols;
	}

	/**
	 * @return
	 */
	public List getProductTestsAsList() {
		List list = new ArrayList();
		Object[] objs = productTests.toArray();
		for (int i = 0; i < objs.length; i++) {
			ProductTest object = (ProductTest) objs[i];
			list.add(object);
		}
		return list;
	}
	/**
	 * @param testName
	 * @return
	 */
	public ProductTest getProductTestByName(String testName) {
		Object[] objs = productTests.toArray();
		for (int i = 0; i < objs.length; i++) {
			ProductTest object = (ProductTest) objs[i];
			if (object.getTestName().equals(testName)) {
				return object;
			}
		}
		return null;
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
	 * @return
	 */
	public List getProtocolsAsList() {
		List list = new ArrayList();
		Object[] objs = protocols.toArray();
		for (int i = 0; i < objs.length; i++) {
			Protocol object = (Protocol) objs[i];
			list.add(object);
		}
		return list;
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
}
