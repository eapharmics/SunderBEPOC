package com.enviroapps.eapharmics.vo.newstudy;

import java.util.Date;

public class StudyInventoryDestroyedVO {

	private static final long serialVersionUID = 1L;

	private Long prdStudyInvDestroyedId;
	private Long prdStudyScheduleId;
	private Long unitsDestroyed;
	private String destroyedBy;
	private Date destroyedDate;
	private Date insertDate;
	private Long insertUser;
	private Date updateDate;
	private Long updateUser;
	private Long auditId;
	private String comments;

	/**
	 * @return the prdStudyInvDestroyedId
	 */
	public Long getPrdStudyInvDestroyedId() {
		return prdStudyInvDestroyedId;
	}
	/**
	 * @param prdStudyInvDestroyedId the prdStudyInvDestroyedId to set
	 */
	public void setPrdStudyInvDestroyedId(Long prdStudyInvDestroyedId) {
		this.prdStudyInvDestroyedId = prdStudyInvDestroyedId;
	}
	/**
	 * @return the prdStudyScheduleId
	 */
	public Long getPrdStudyScheduleId() {
		return prdStudyScheduleId;
	}
	/**
	 * @param prdStudyScheduleId the prdStudyScheduleId to set
	 */
	public void setPrdStudyScheduleId(Long prdStudyScheduleId) {
		this.prdStudyScheduleId = prdStudyScheduleId;
	}
	/**
	 * @return the unitsDestroyed
	 */
	public Long getUnitsDestroyed() {
		return unitsDestroyed;
	}
	/**
	 * @param unitsDestroyed the unitsDestroyed to set
	 */
	public void setUnitsDestroyed(Long unitsDestroyed) {
		this.unitsDestroyed = unitsDestroyed;
	}
	/**
	 * @return the destroyedBy
	 */
	public String getDestroyedBy() {
		return destroyedBy;
	}
	/**
	 * @param destroyedBy the destroyedBy to set
	 */
	public void setDestroyedBy(String destroyedBy) {
		this.destroyedBy = destroyedBy;
	}
	/**
	 * @return the destroyedDate
	 */
	public Date getDestroyedDate() {
		return destroyedDate;
	}
	/**
	 * @param destroyedDate the destroyedDate to set
	 */
	public void setDestroyedDate(Date destroyedDate) {
		this.destroyedDate = destroyedDate;
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
