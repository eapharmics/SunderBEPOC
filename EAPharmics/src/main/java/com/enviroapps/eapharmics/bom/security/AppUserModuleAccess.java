package com.enviroapps.eapharmics.bom.security;

import java.io.Serializable;
import java.util.Date;

import com.enviroapps.eapharmics.das.persistence.AbstractPersistableObject;

public class AppUserModuleAccess extends AbstractPersistableObject {

	private static final Long serialVersionUID = 1L;
	private Long appUserModuleAccessId;
	private Long appModuleId;
	private Long appUserId;
	private Boolean isAccessible = Boolean.FALSE;
	
	private String comments;
	
	private Date insertDate;
	private String insertUser;
	private Date updateDate;
	private Long updateUser;
	
	private Long auditId; 
	private Date startDate;
	private Date endDate;
	private Boolean isActive = Boolean.FALSE;

	private AppModule appModule;
	
	/* (non-Javadoc)
	 * @see com.enviroapps.das.persistence.AbstractPersistableObject#getPK()
	 */
	public Serializable getPK() {
		return appUserModuleAccessId;
	}

	/* (non-Javadoc)
	 * @see com.enviroapps.das.persistence.AbstractPersistableObject#loadPK(java.lang.Object[])
	 */
	public void loadPK(Object[] pks) {
		this.appUserModuleAccessId = (Long)pks[0];

	}

	/**
	 * @return the appUserModuleAccessId
	 */
	public Long getAppUserModuleAccessId() {
		return appUserModuleAccessId;
	}

	/**
	 * @param appUserModuleAccessId the appUserModuleAccessId to set
	 */
	public void setAppUserModuleAccessId(Long appUserModuleAccessId) {
		this.appUserModuleAccessId = appUserModuleAccessId;
	}

	/**
	 * @return the appModuleId
	 */
	public Long getAppModuleId() {
		return appModuleId;
	}

	/**
	 * @param appModuleId the appModuleId to set
	 */
	public void setAppModuleId(Long moduleId) {
		this.appModuleId = moduleId;
	}

	/**
	 * @return the appUserId
	 */
	public Long getAppUserId() {
		return appUserId;
	}

	/**
	 * @param appUserId the appUserId to set
	 */
	public void setAppUserId(Long userId) {
		this.appUserId = userId;
	}

	/**
	 * @return the isAccessible
	 */
	public Boolean getIsAccessible() {
		return isAccessible;
	}

	/**
	 * @param isAccessible the isAccessible to set
	 */
	public void setIsAccessible(Boolean isAccessible) {
		this.isAccessible = isAccessible;
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
	public String getInsertUser() {
		return insertUser;
	}

	/**
	 * @param insertUser the insertUser to set
	 */
	public void setInsertUser(String insertUser) {
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
	 * @return the isActive
	 */
	public Boolean getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the appModule
	 */
	public AppModule getAppModule() {
		return appModule;
	}

	/**
	 * @param appModule the appModule to set
	 */
	public void setAppModule(AppModule appModule) {
		this.appModule = appModule;
	}

}
