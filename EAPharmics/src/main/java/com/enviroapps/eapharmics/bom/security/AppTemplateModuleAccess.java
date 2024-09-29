package com.enviroapps.eapharmics.bom.security;

import java.io.Serializable;
import java.util.Date;

import com.enviroapps.eapharmics.das.persistence.AbstractPersistableObject;

public class AppTemplateModuleAccess extends AbstractPersistableObject {

	private static final long serialVersionUID = 1L;
	private Long appTemplateModuleAccessId;
	private Long appAccessTemplateId;

   private Long appModuleId;
	private boolean isAccessible = Boolean.FALSE;
	private String comments;
	
	private Date insertDate;
	private Long insertUser;
	private Date updateDate;
	private Long updateUser;
	
	private Long auditId; 
	private Date startDate;
	private Date endDate;
	private boolean isActive = Boolean.FALSE;
	
	private AppModule appModule;
	/* (non-Javadoc)
	 * @see com.enviroapps.das.persistence.AbstractPersistableObject#getPK()
	 */
	public Serializable getPK() {
		return appTemplateModuleAccessId;
	}

	/* (non-Javadoc)
	 * @see com.enviroapps.das.persistence.AbstractPersistableObject#loadPK(java.lang.Object[])
	 */
	public void loadPK(Object[] pks) {
		this.appTemplateModuleAccessId = (Long)pks[0];

	}

	/**
	 * @return the appTemplateModuleAccessId
	 */
	public Long getAppTemplateModuleAccessId() {
		return appTemplateModuleAccessId;
	}

	/**
	 * @param appTemplateModuleAccessId the appTemplateModuleAccessId to set
	 */
	public void setAppTemplateModuleAccessId(Long appTemplateModuleId) {
		this.appTemplateModuleAccessId = appTemplateModuleId;
	}

	  /**
    * @return the appAccessTemplateId
    */
   public Long getAppAccessTemplateId()
   {
      return appAccessTemplateId;
   }

   /**
    * @param appAccessTemplateId the appAccessTemplateId to set
    */
   public void setAppAccessTemplateId(Long appAccessTemplateId)
   {
      this.appAccessTemplateId = appAccessTemplateId;
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
	 * @return the isAccessible
	 */
	public boolean getIsAccessible() {
		return isAccessible;
	}

	/**
	 * @param isAccessible the isAccessible to set
	 */
	public void setIsAccessible(boolean isAccessible) {
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
	public void setUpdateUser(Long updateUserId) {
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
