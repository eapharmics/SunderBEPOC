package com.enviroapps.eapharmics.bom.newstudy;

import java.io.Serializable;
import java.util.Date;

public class Image {

	private static final long serialVersionUID = 1L;
	
	private Long imageId;
	private Long labelId;
	private int imageType;
	private String elementName;
	private byte[] imageData;
	private Date insertDate;
	private Long insertUser;
	private Date updateDate;
	private Long updateUser;
	private Long auditId;
	private String comments;
	
	/* (non-Javadoc)
	 * @see com.enviroapps.das.persistence.AbstractPersistableObject#getPK()
	 */
	public Serializable getPK() {
		return imageId;
	}
	/* (non-Javadoc)
	 * @see com.enviroapps.das.persistence.AbstractPersistableObject#loadPK(java.lang.Object[])
	 */
	public void loadPK(Object[] pks) {
		imageId = (Long)pks[0];
	}
	/**
	 * @return the imageId
	 */
	public Long getImageId() {
		return imageId;
	}
	/**
	 * @param imageId the imageId to set
	 */
	public void setImageId(Long imageId) {
		this.imageId = imageId;
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
	 * @return the imageType
	 */
	public int getImageType() {
		return imageType;
	}
	/**
	 * @param imageType the imageType to set
	 */
	public void setImageType(int imageType) {
		this.imageType = imageType;
	}
	/**
	 * @return the elementName
	 */
	public String getElementName() {
		return elementName;
	}
	/**
	 * @param elementName the elementName to set
	 */
	public void setElementName(String elementName) {
		this.elementName = elementName;
	}
	/**
	 * @return the imageData
	 */
	public byte[] getImageData() {
		return imageData;
	}
	/**
	 * @param imageData the imageData to set
	 */
	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
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
