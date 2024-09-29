package com.enviroapps.eapharmics.bom.security;

import java.io.Serializable;
import java.util.Date;

import com.enviroapps.eapharmics.das.persistence.AbstractPersistableObject;

public class AppUserAudit extends AbstractPersistableObject {

	private static final long serialVersionUID = 1L;
	private Long appUserAuditId;
	private Long appUserId;
	private Date loginTime;
	private Boolean loginSuccess;	
	private Date logoutTime;
	private String userName;
	private String reason;
	private Long locationId;
	
	/* (non-Javadoc)
	 * @see com.enviroapps.das.persistence.AbstractPersistableObject#getPK()
	 */
	public Serializable getPK() {
		return appUserAuditId;
	}

	/* (non-Javadoc)
	 * @see com.enviroapps.das.persistence.AbstractPersistableObject#loadPK(java.lang.Object[])
	 */
	public void loadPK(Object[] pks) {
		this.appUserAuditId = (Long)pks[0];

	}

	/**
	 * @return the appUserAuditId
	 */
	public Long getAppUserAuditId() {
		return appUserAuditId;
	}

	/**
	 * @param appUserAuditId the appUserAuditId to set
	 */
	public void setAppUserAuditId(Long appUserAuditId) {
		this.appUserAuditId = appUserAuditId;
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
	 * @return the loginTime
	 */
	public Date getLoginTime() {
		return loginTime;
	}

	/**
	 * @param loginTime the loginTime to set
	 */
	public void setLoginTime(Date logionTime) {
		this.loginTime = logionTime;
	}

	/**
	 * @return the loginSuccess
	 */
	public Boolean getLoginSuccess() {
		return loginSuccess;
	}

	/**
	 * @param loginSuccess the loginSuccess to set
	 */
	public void setLoginSuccess(Boolean loginSuccess) {
		this.loginSuccess = loginSuccess;
	}

	/**
	 * @return the logoutTime
	 */
	public Date getLogoutTime() {
		return logoutTime;
	}

	/**
	 * @param logoutTime the logoutTime to set
	 */
	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
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

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public Long getLocationId() {
		return locationId;
	}

}
