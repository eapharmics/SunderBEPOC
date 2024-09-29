/**
 * 
 */
package com.enviroapps.eapharmics.bom.admin;

import java.util.Date;

/**
 * @author EAPharmics
 *
 */
public class Timezone {

	private String timezoneName;
	private long offsetFromUTC;	//This is milliseconds
	private String comments;
	private Date insertDate;
	private Long insertUser;
	private Date updateDate;
	private Long updateUser;
	private Long auditId;
	
	/**
	 * @return the timezoneName
	 */
	public String getTimezoneName() {
		return timezoneName;
	}
	/**
	 * @param timezoneName the timezoneName to set
	 */
	public void setTimezoneName(String timezoneName) {
		this.timezoneName = timezoneName;
	}
	/**
	 * @return the offsetFromUTC
	 */
	public long getOffsetFromUTC() {
		return offsetFromUTC;
	}
	/**
	 * @param offsetFromUTC the offsetFromUTC to set
	 */
	public void setOffsetFromUTC(long offsetFromUTC) {
		this.offsetFromUTC = offsetFromUTC;
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
}
