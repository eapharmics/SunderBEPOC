package com.enviroapps.eapharmics.bom.newstudy;

import java.io.Serializable;
import java.util.Date;

public class Label {

	private static final long serialVersionUID = 1L;
	
	private Long labelId;
	private String labelName;
	private byte[] labelData = new byte[0];
	private Date insertDate;
	private Long insertUser;
	private Date updateDate;
	private Long updateUser;
	private Long auditId;
	private String comments;
	private String labelTemplate = "";

	private Long locationId;
	
	/* (non-Javadoc)
	 * @see com.enviroapps.das.persistence.AbstractPersistableObject#getPK()
	 */
	public Serializable getPK() {
		return labelId;
	}
	/* (non-Javadoc)
	 * @see com.enviroapps.das.persistence.AbstractPersistableObject#loadPK(java.lang.Object[])
	 */
	public void loadPK(Object[] pks) {
		labelId = (Long)pks[0];
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
	 * @return the labelName
	 */
	public String getLabelName() {
		return labelName;
	}
	/**
	 * @param labelName the labelName to set
	 */
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	/**
	 * @return the labelData
	 */
	public byte[] getLabelData() {
		return labelData;
	}
	/**
	 * @param labelData the labelData to set
	 */
	public void setLabelData(byte[] labeldata) {
		this.labelData = labeldata;
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
	 * @return the labelTemplate
	 */
	public String getLabelTemplate() {
		return labelTemplate;
	}
	/**
	 * @param labelTemplate the labelTemplate to set
	 */
	public void setLabelTemplate(String labelTemplate) {
		this.labelTemplate = labelTemplate;
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

}
