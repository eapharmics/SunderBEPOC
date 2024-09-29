package com.enviroapps.eapharmics.vo.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TemplateVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long appAccessTemplateId;
	private String templateName;
	private Date insertDate;
	private Long insertUser;
	private Date updateDate;
	private Long updateUser;
	private Long auditId;
	private Date startDate;
	private Date endDate;
	private boolean active;
	private String comments;

   private List<TemplateAccessAreaVO> appAreas = new ArrayList<TemplateAccessAreaVO>();
   private Long sessionAuditId;
	/**
    * @return the appAreas
    */
   public List < TemplateAccessAreaVO > getAppAreas()
   {
      return appAreas;
   }
   /**
    * @param appAreas the appAreas to set
    */
   public void setAppAreas(List < TemplateAccessAreaVO > appAreas)
   {
      this.appAreas = appAreas;
   }
   /**
	 * @return the appAccessTemplateId
	 */
	public Long getAppAccessTemplateId() {
		return appAccessTemplateId;
	}
	/**
	 * @param appAccessTemplateId the appAccessTemplateId to set
	 */
	public void setAppAccessTemplateId(Long appAccessTemplateId) {
		this.appAccessTemplateId = appAccessTemplateId;
	}
	/**
	 * @return the templateName
	 */
	public String getTemplateName() {
		return templateName;
	}
	/**
	 * @param templateName the templateName to set
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
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
	 * @return the active
	 */
	public boolean getActive() {
		return active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
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
