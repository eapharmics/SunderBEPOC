package com.enviroapps.eapharmics.vo.security;

import java.util.Date;

public class ModuleAccessAuditVO {

	private static final long serialVersionUID = 1L;

	private Long moduleAccessAuditId;
	private Long appModuleId;
	private Long appUserId;
	private String activity;
	private Date accessTime;
	private Long auditId;
	private String userName;
	private String moduleName;
	private String firstName;
	private String lastName;

	private Long locationId;

	/**
	 * @return the moduleAccessAuditId
	 */
	public Long getModuleAccessAuditId() {
		return moduleAccessAuditId;
	}
	/**
	 * @param moduleAccessAuditId the moduleAccessAuditId to set
	 */
	public void setModuleAccessAuditId(Long moduleAccessAuditId) {
		this.moduleAccessAuditId = moduleAccessAuditId;
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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the moduleName
	 */
	public String getModuleName() {
		return moduleName;
	}
	/**
	 * @param moduleName the moduleName to set
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
