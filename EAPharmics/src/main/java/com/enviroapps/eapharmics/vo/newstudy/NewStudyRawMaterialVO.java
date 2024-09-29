package com.enviroapps.eapharmics.vo.newstudy;

import java.util.Date;

public class NewStudyRawMaterialVO {

	private static final long serialVersionUID = 1L;

	private Long prdStudyRawMaterialId;
	private Long prdStudyBatchId;
	private Long productFormulationId;
	private String mfgSupplierCode;
	private String lotNumber;
	private Date insertDate;
	private Long insertUser;
	private Date updateDate;
	private Long updateUser;
	private Long auditId;
	private String comments;
	private String productFormulation;

	/**
	 * @return the prdStudyRawMaterialId
	 */
	public Long getPrdStudyRawMaterialId() {
		return prdStudyRawMaterialId;
	}
	/**
	 * @param prdStudyRawMaterialId the prdStudyRawMaterialId to set
	 */
	public void setPrdStudyRawMaterialId(Long prdStudyRawMaterialId) {
		this.prdStudyRawMaterialId = prdStudyRawMaterialId;
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
	 * @return the productFormulationId
	 */
	public Long getProductFormulationId() {
		return productFormulationId;
	}
	/**
	 * @param productFormulationId the productFormulationId to set
	 */
	public void setProductFormulationId(Long productFormulationId) {
		this.productFormulationId = productFormulationId;
	}
	/**
	 * @return the mfgSupplierCode
	 */
	public String getMfgSupplierCode() {
		return mfgSupplierCode;
	}
	/**
	 * @param mfgSupplierCode the mfgSupplierCode to set
	 */
	public void setMfgSupplierCode(String mfgSupplierCode) {
		this.mfgSupplierCode = mfgSupplierCode;
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
	 * @return the productFormulation
	 */
	public String getProductFormulation() {
		return productFormulation;
	}
	/**
	 * @param productFormulation the productFormulation to set
	 */
	public void setProductFormulation(String productFormulation) {
		this.productFormulation = productFormulation;
	}
}
