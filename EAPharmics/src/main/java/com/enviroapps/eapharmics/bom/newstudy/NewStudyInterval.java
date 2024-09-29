package com.enviroapps.eapharmics.bom.newstudy;

import java.io.Serializable;
import java.util.Date;

public class NewStudyInterval {

	private static final long serialVersionUID = 1L;

	private Long prdStudyIntervalId;
	private Long prdStudyBatchId;
	private Long intervalNumber;
	private Date intervalDate;
	private Long intervalValue;
	private Long units;
	private Long labelCount;
	private Date insertDate;
	private Long insertUser;
	private Date updateDate;
	private Long updateUser;
	private Long auditId;
	private String comments;
	private Boolean pastInterval;

	/* (non-Javadoc)
	 * @see com.enviroapps.das.persistence.AbstractPersistableObject#getPK()
	 */
	public Serializable getPK() {
		return prdStudyIntervalId;
	}
	/* (non-Javadoc)
	 * @see com.enviroapps.das.persistence.AbstractPersistableObject#loadPK(java.lang.Object[])
	 */
	public void loadPK(Object[] pks) {
		prdStudyIntervalId = (Long)pks[0];
	}
	/**
	 * @return the prdStudyIntervalId
	 */
	public Long getPrdStudyIntervalId() {
		return prdStudyIntervalId;
	}
	/**
	 * @param prdStudyIntervalId the prdStudyIntervalId to set
	 */
	public void setPrdStudyIntervalId(Long prdStudyIntervalId) {
		this.prdStudyIntervalId = prdStudyIntervalId;
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
	 * @return the intervalNumber
	 */
	public Long getIntervalNumber() {
		return intervalNumber;
	}
	/**
	 * @param intervalNumber the intervalNumber to set
	 */
	public void setIntervalNumber(Long intervalNumber) {
		this.intervalNumber = intervalNumber;
	}
	/**
	 * @return the intervalDate
	 */
	public Date getIntervalDate() {
		return intervalDate;
	}
	/**
	 * @param intervalDate the intervalDate to set
	 */
	public void setIntervalDate(Date intervalDate) {
		this.intervalDate = intervalDate;
	}
	/**
	 * @return the intervalValue
	 */
	public Long getIntervalValue() {
		return intervalValue;
	}
	/**
	 * @param intervalValue the intervalValue to set
	 */
	public void setIntervalValue(Long intervalValue) {
		this.intervalValue = intervalValue;
	}
	/**
	 * @return the units
	 */
	public Long getUnits() {
		return units;
	}
	/**
	 * @param units the units to set
	 */
	public void setUnits(Long units) {
		this.units = units;
	}
	/**
	 * @return the labelCount
	 */
	public Long getLabelCount() {
		return labelCount;
	}
	/**
	 * @param labelCount the labelCount to set
	 */
	public void setLabelCount(Long labelCount) {
		this.labelCount = labelCount;
	}
	public Boolean getPastInterval(){
	   return pastInterval;
	}
	public void setPastInterval(Boolean pastInterval){
	   this.pastInterval=pastInterval;
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
