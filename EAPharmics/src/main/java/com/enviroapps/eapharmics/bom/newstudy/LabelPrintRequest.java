package com.enviroapps.eapharmics.bom.newstudy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LabelPrintRequest {

	private static final long serialVersionUID = 1L;

	private Long eaLabelPrintRequestsId;
	private Long prdStudyBatchId;
	private Long labelId;
	private Date insertDate;
	private Long insertUser;
	private Date updateDate;
	private Long updateUser;
	private Long auditId;
	private String comments;
	private Boolean labelPrinted;
	private String reason;
	private Set<LabelPrintRequestDetail> details = new HashSet<LabelPrintRequestDetail>();

	/* (non-Javadoc)
	 * @see com.enviroapps.das.persistence.AbstractPersistableObject#getPK()
	 */
	public Serializable getPK() {
		return eaLabelPrintRequestsId;
	}
	/* (non-Javadoc)
	 * @see com.enviroapps.das.persistence.AbstractPersistableObject#loadPK(java.lang.Object[])
	 */
	public void loadPK(Object[] pks) {
		eaLabelPrintRequestsId = (Long)pks[0];
	}
	/**
	 * @return the eaLabelPrintRequestsId
	 */
	public Long getEaLabelPrintRequestsId() {
		return eaLabelPrintRequestsId;
	}
	/**
	 * @param eaLabelPrintRequestsId the eaLabelPrintRequestsId to set
	 */
	public void setEaLabelPrintRequestsId(Long eaLabelPrintRequestsId) {
		this.eaLabelPrintRequestsId = eaLabelPrintRequestsId;
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
	 * @return the labelPrinted
	 */
	public Boolean getLabelPrinted() {
		return labelPrinted;
	}
	/**
	 * @param labelPrinted the labelPrinted to set
	 */
	public void setLabelPrinted(Boolean labelPrinted) {
		this.labelPrinted = labelPrinted;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	/**
	 * @return the details
	 */
	public Set<LabelPrintRequestDetail> getDetails() {
		return details;
	}
	/**
	 * @param details the details to set
	 */
	public void setDetails(Set<LabelPrintRequestDetail> details) {
		this.details = details;
	}
}
