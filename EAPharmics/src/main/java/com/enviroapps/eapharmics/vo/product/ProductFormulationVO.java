package com.enviroapps.eapharmics.vo.product;

import java.util.Date;

public class ProductFormulationVO {

	private static final long serialVersionUID = 1L;

	private Long productFormulationId;
	private Long productId;
	private Long lineNumber;
	private String productFormulation;
	private Date insertDate;
	private Long insertUser;
	private Date updateDate;
	private Long updateUser;
	private Long auditId;
	private String comments;

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
	 * @return the lineNumber
	 */
	public Long getLineNumber() {
		return lineNumber;
	}
	/**
	 * @param lineNumber the lineNumber to set
	 */
	public void setLineNumber(Long lineNumber) {
		this.lineNumber = lineNumber;
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
}
