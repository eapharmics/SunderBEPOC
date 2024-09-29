package com.enviroapps.eapharmics.bom.security;

import java.io.Serializable;
import java.util.Date;

import com.enviroapps.eapharmics.das.persistence.AbstractPersistableObject;

public class ModuleAccessAudit extends AbstractPersistableObject {

	private static final long serialVersionUID = 1L;
	private Long moduleAccessAuditId;
	private Long appModuleId;
	private Long appUserId;
	private String activity;
	private Date accessTime;
	private Long auditId;
	private AppUser appUser;
	private AppModule appModule;
	private Long locationId;
	
	/* (non-Javadoc)
	 * @see com.enviroapps.das.persistence.AbstractPersistableObject#getPK()
	 */
	public Serializable getPK() {
		return moduleAccessAuditId;
	}

	/* (non-Javadoc)
	 * @see com.enviroapps.das.persistence.AbstractPersistableObject#loadPK(java.lang.Object[])
	 */
	public void loadPK(Object[] pks) {
		this.moduleAccessAuditId = (Long)pks[0];

	}

	/**
	 * @return the moduleAccessAuditId
	 */
	public Long getModuleAccessAuditId() {
		return moduleAccessAuditId;
	}

	/**
	 * @param moduleAccessAuditId the moduleAccessAuditId to set
	 */
	public void setModuleAccessAuditId(Long appUserModuleAccessId) {
		this.moduleAccessAuditId = appUserModuleAccessId;
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
	public void setAppModuleId(Long appModuleId) {
		this.appModuleId = appModuleId;
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
	public void setAppUserId(Long appUserId) {
		this.appUserId = appUserId;
	}

	/**
	 * @return the activity
	 */
	public String getActivity() {
		return activity;
	}

	/**
	 * @param activity the activity to set
	 */
	public void setActivity(String activity) {
		this.activity = activity;
	}

	/**
	 * @return the accessTime
	 */
	public Date getAccessTime() {
		return accessTime;
	}

	/**
	 * @param accessTime the accessTime to set
	 */
	public void setAccessTime(Date accessTime) {
		this.accessTime = accessTime;
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
	 * @return the appUser
	 */
	public AppUser getAppUser() {
		return appUser;
	}

	/**
	 * @param appUser the appUser to set
	 */
	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
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
