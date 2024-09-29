package com.enviroapps.eapharmics.bom.security;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.enviroapps.eapharmics.das.persistence.AbstractPersistableObject;

public class AppUser extends AbstractPersistableObject {

	private static final long serialVersionUID = 1L;
	private Long appUserId;
	private String firstName;
	private String lastName;
	private String userName;
	private String email;
	private String password;
	private boolean isActive;
	private boolean isTerminated;
	
	private Date insertDate;
	private String insertUser;
	private Date updateDate;
	private String updateUser;
	
	private String comments;
	
	private Long auditId; 
	private Date startDate;
	private Date endDate;
	private int invalidLoginCount = 0;
	private String prevPassword1 = "";
	private String prevPassword2 = "";
	private String prevPassword3 = "";
	private boolean passwordExpired = false;
	private Date passwordChangeDate;
	
	private String locale;
	
	private Set<AppUserLocation> appUserLocations = new HashSet<AppUserLocation>();
	private Long defaultLocationId;
	
	/* (non-Javadoc)
	 * @see com.enviroapps.das.persistence.AbstractPersistableObject#getPK()
	 */
	public Serializable getPK() {
		return appUserId;
	}

	/* (non-Javadoc)
	 * @see com.enviroapps.das.persistence.AbstractPersistableObject#loadPK(java.lang.Object[])
	 */
	public void loadPK(Object[] pks) {
		this.appUserId = (Long)pks[0];

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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the isActive
	 */
	public boolean getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
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
	public String getUpdateUser() {
		return updateUser;
	}

	/**
	 * @param updateUser the updateUser to set
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
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
	 * @return the invalidLoginCount
	 */
	public int getInvalidLoginCount() {
		return invalidLoginCount;
	}

	/**
	 * @param invalidLoginCount the invalidLoginCount to set
	 */
	public void setInvalidLoginCount(int invalidLoginCount) {
		this.invalidLoginCount = invalidLoginCount;
	}

	/**
	 * @return the prevPassword1
	 */
	public String getPrevPassword1() {
		return prevPassword1;
	}

	/**
	 * @param prevPassword1 the prevPassword1 to set
	 */
	public void setPrevPassword1(String prevPassword1) {
		this.prevPassword1 = prevPassword1;
	}

	/**
	 * @return the prevPassword2
	 */
	public String getPrevPassword2() {
		return prevPassword2;
	}

	/**
	 * @param prevPassword2 the prevPassword2 to set
	 */
	public void setPrevPassword2(String prevPassword2) {
		this.prevPassword2 = prevPassword2;
	}

	/**
	 * @return the passwordExpired
	 */
	public boolean getPasswordExpired() {
		return passwordExpired;
	}

	/**
	 * @param passwordExpired the passwordExpired to set
	 */
	public void setPasswordExpired(boolean firstLogin) {
		this.passwordExpired = firstLogin;
	}

	/**
	 * @return the passwordChangeDate
	 */
	public Date getPasswordChangeDate() {
		return passwordChangeDate;
	}

	/**
	 * @param passwordChangeDate the passwordChangeDate to set
	 */
	public void setPasswordChangeDate(Date passwordChangeDate) {
		this.passwordChangeDate = passwordChangeDate;
	}

	/**
	 * @return the prevPassword3
	 */
	public String getPrevPassword3() {
		return prevPassword3;
	}

	/**
	 * @param prevPassword3 the prevPassword3 to set
	 */
	public void setPrevPassword3(String prevPassword3) {
		this.prevPassword3 = prevPassword3;
	}

	/**
	 * @return the locale
	 */
	public String getLocale() {
		return locale;
	}

	/**
	 * @param locale the locale to set
	 */
	public void setLocale(String locale) {
		this.locale = locale;
	}

	/**
	 * @return the isTerminated
	 */
	public boolean getIsTerminated() {
		return isTerminated;
	}

	/**
	 * @param isTerminated the isTerminated to set
	 */
	public void setIsTerminated(boolean isTerminated) {
		this.isTerminated = isTerminated;
	}

	/**
	 * @return the batches
	 */
	public Set<AppUserLocation> getAppUserLocations() {
		return appUserLocations;
	}

	/**
	 * @param appUserLocations the appUserLocations to set
	 */
	public void setAppUserLocations(Set<AppUserLocation> appUserLocations) {
		this.appUserLocations = appUserLocations;
	}

	/**
	 * @param productBatchId
	 * @return
	 */
	public AppUserLocation getAppUserLocationForLocation(Long locationId) {
		Object[] objs = appUserLocations.toArray();
		for (int i = 0; i < objs.length; i++) {
			AppUserLocation object = (AppUserLocation) objs[i];
			if (object.getLocationId().longValue() == locationId.longValue()) {
				return object;
			}
		}
		return null;
	}

	/**
	 * @return the defaultLocationId
	 */
	public Long getDefaultLocationId() {
		return defaultLocationId;
	}

	/**
	 * @param defaultLocationId the defaultLocationId to set
	 */
	public void setDefaultLocationId(Long defaultLocationId) {
		this.defaultLocationId = defaultLocationId;
	}
}
