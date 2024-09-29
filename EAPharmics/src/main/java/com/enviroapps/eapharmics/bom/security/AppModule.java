package com.enviroapps.eapharmics.bom.security;

import java.io.Serializable;
import java.util.Date;

import com.enviroapps.eapharmics.das.persistence.AbstractPersistableObject;

public class AppModule extends AbstractPersistableObject {

	private static final long serialVersionUID = 1L;
	private Long appModuleId;
	private Long appAreaId;
	private String moduleDescription;
	private String comments;
	
	private Date insertDate;
	private String insertUser;
	private Date updateDate;
	private String updateUser;
	
	private Long auditId; 
	private Date startDate;
	private Date endDate;
	private boolean isActive;
	private Long menuOrder;
	private AppArea appArea;
	
	/* (non-Javadoc)
	 * @see com.enviroapps.das.persistence.AbstractPersistableObject#getPK()
	 */
	public Serializable getPK() {
		return appModuleId;
	}

	/* (non-Javadoc)
	 * @see com.enviroapps.das.persistence.AbstractPersistableObject#loadPK(java.lang.Object[])
	 */
	public void loadPK(Object[] pks) {
		this.appModuleId = (Long)pks[0];

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
	 * @return the appAreaId
	 */
	public Long getAppAreaId() {
		return appAreaId;
	}

	/**
	 * @param appAreaId the appAreaId to set
	 */
	public void setAppAreaId(Long appAreaId) {
		this.appAreaId = appAreaId;
	}

	/**
	 * @return the moduleDescription
	 */
	public String getModuleDescription() {
		return moduleDescription;
	}

	/**
	 * @param moduleDescription the moduleDescription to set
	 */
	public void setModuleDescription(String areaDescription) {
		this.moduleDescription = areaDescription;
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
    * @return the menuOrder
    */
   public Long getMenuOrder()
   {
      return menuOrder;
   }

   /**
    * @param menuOrder the menuOrder to set
    */
   public void setMenuOrder(Long menuOrder)
   {
      this.menuOrder = menuOrder;
   }

   /**
	 * @return the appArea
	 */
	public AppArea getAppArea() {
		return appArea;
	}

	/**
	 * @param appArea the appArea to set
	 */
	public void setAppArea(AppArea appArea) {
		this.appArea = appArea;
	}

}
