package com.enviroapps.eapharmics.vo.dictionary;

import java.util.Date;

/**
 * @author EnviroApps
 *
 */
public class DictionaryDetailVO {

	private static final long serialVersionUID = 1L;

	private Long eaDictionaryDetailId;
	private String dictionaryCode;
	private String dictionaryValue;
	private String dictionaryValueDescription;
	private int displayOrder;
	private String customField1;
	private String customField2;
	private String graphTo;
	private Boolean active;
	private String comments;
	private Date insertDate;
	private Long insertUser;
	private Date updateDate;
	private Long updateUser;
	private Long auditId;
	private int version;

	private Long locationId;

	/**
	 * @return the eaDictionaryDetailId
	 */
	public Long getEaDictionaryDetailId() {
		return eaDictionaryDetailId;
	}
	/**
	 * @param eaDictionaryDetailId the eaDictionaryDetailId to set
	 */
	public void setEaDictionaryDetailId(Long eaDictionaryDetailId) {
		this.eaDictionaryDetailId = eaDictionaryDetailId;
	}
	/**
	 * @return the dictionaryCode
	 */
	public String getDictionaryCode() {
		return dictionaryCode;
	}
	/**
	 * @param dictionaryCode the dictionaryCode to set
	 */
	public void setDictionaryCode(String dictionaryCode) {
		this.dictionaryCode = dictionaryCode;
	}
	/**
	 * @return the dictionaryValue
	 */
	public String getDictionaryValue() {
		return dictionaryValue;
	}
	/**
	 * @param dictionaryValue the dictionaryValue to set
	 */
	public void setDictionaryValue(String dictionaryValue) {
		this.dictionaryValue = dictionaryValue;
	}
	/**
	 * @return the dictionaryValueDescription
	 */
	public String getDictionaryValueDescription() {
		return dictionaryValueDescription;
	}
	/**
	 * @param dictionaryValueDescription the dictionaryValueDescription to set
	 */
	public void setDictionaryValueDescription(String dictionaryValueDescription) {
		this.dictionaryValueDescription = dictionaryValueDescription;
	}
	/**
	 * @return the displayOrder
	 */
	public int getDisplayOrder() {
		return displayOrder;
	}
	/**
	 * @param displayOrder the displayOrder to set
	 */
	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}
	/**
	 * @return the customField1
	 */
	public String getCustomField1() {
		return customField1;
	}
	/**
	 * @param customField1 the customField1 to set
	 */
	public void setCustomField1(String customField1) {
		this.customField1 = customField1;
	}
	/**
	 * @return the customField2
	 */
	public String getCustomField2() {
		return customField2;
	}
	/**
	 * @param customField2 the customField2 to set
	 */
	public void setCustomField2(String customField2) {
		this.customField2 = customField2;
	}
	/**
	 * @return the graphTo
	 */
	public String getGraphTo() {
		return graphTo;
	}
	/**
	 * @param graphTo the graphTo to set
	 */
	public void setGraphTo(String graphTo) {
		this.graphTo = graphTo;
	}
	/**
	 * @return the active
	 */
	public Boolean getActive() {
		return active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(Boolean active) {
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
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
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
