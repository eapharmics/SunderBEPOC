/**
 * 
 */
package com.enviroapps.eapharmics.vo.security;

import java.util.Date;

/**
 * @author EnviroApps
 *
 */
public class EditReasonVO {

	private static final long serialVersionUID = 1L;

	private Long editReasonId;
	private String screenName;
	private String tableName;
	private String pkValue1;
	private String pkValue2;
	private String reason;
	private String oldValue;
	private String newValue;
	private Date insertDate;
	private Long insertUser;
	private Date updateDate;
	private Long updateUser;
	private Long auditId;
	private String comments;
	private String fieldName;
	private Long locationId;
	/**
	 * @return the editReasonId
	 */
	public Long getEditReasonId() {
		return editReasonId;
	}
	/**
	 * @param editReasonId the editReasonId to set
	 */
	public void setEditReasonId(Long editReasonId) {
		this.editReasonId = editReasonId;
	}
	/**
	 * @return the screenName
	 */
	public String getScreenName() {
		return screenName;
	}
	/**
	 * @param screenName the screenName to set
	 */
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}
	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	/**
	 * @return the pkValue1
	 */
	public String getPkValue1() {
		return pkValue1;
	}
	/**
	 * @param pkValue1 the pkValue1 to set
	 */
	public void setPkValue1(String pkValue1) {
		this.pkValue1 = pkValue1;
	}
	/**
	 * @return the pkValue2
	 */
	public String getPkValue2() {
		return pkValue2;
	}
	/**
	 * @param pkValue2 the pkValue2 to set
	 */
	public void setPkValue2(String pkValue2) {
		this.pkValue2 = pkValue2;
	}
	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}
	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
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
	 * @return the oldValue
	 */
	public String getOldValue() {
		return oldValue;
	}
	/**
	 * @param oldValue the oldValue to set
	 */
	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}
	/**
	 * @return the newValue
	 */
	public String getNewValue() {
		return newValue;
	}
	/**
	 * @param newValue the newValue to set
	 */
	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}
	/**
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}
	/**
	 * @param fieldName the fieldName to set
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
   /**
    * @return the locationId
    */
   public Long getLocationId()
   {
      return locationId;
   }
   /**
    * @param locationId the locationId to set
    */
   public void setLocationId(Long locationId)
   {
      this.locationId = locationId;
   }

}
