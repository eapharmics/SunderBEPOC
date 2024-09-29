package com.enviroapps.eapharmics.vo.dictionary;

import java.util.Date;
import java.util.List;

public class DictionaryMasterVO {

	private static final long serialVersionUID = 1L;

	private Long eaDictionaryMasterId;
	private String dictionaryCode;
	private String dictionaryDescription;
	private boolean active;
	private boolean graphDictionary;
	private String customField1;
	private String customField2;
	private String comments;
	private Date insertDate;
	private Long insertUser;
	private Date updateDate;
	private Long updateUser;
	private Long auditId;
	private Date startDate;
	private Date endDate;
	private boolean showDescription;
	private boolean allowChangeDisplayOrder;
	private String codeDisplay;
	private String valueDisplay;
	private boolean globalDictionary;
	private List dictionaryDetails; 

	/**
	 * @return the eaDictionaryMasterId
	 */
	public Long getEaDictionaryMasterId() {
		return eaDictionaryMasterId;
	}
	/**
	 * @param eaDictionaryMasterId the eaDictionaryMasterId to set
	 */
	public void setEaDictionaryMasterId(Long eaDictionaryMasterId) {
		this.eaDictionaryMasterId = eaDictionaryMasterId;
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
	 * @return the dictionaryDescription
	 */
	public String getDictionaryDescription() {
		return dictionaryDescription;
	}
	/**
	 * @param dictionaryDescription the dictionaryDescription to set
	 */
	public void setDictionaryDescription(String dictionaryDescription) {
		this.dictionaryDescription = dictionaryDescription;
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
	 * @return the graphDictionary
	 */
	public boolean getGraphDictionary() {
		return graphDictionary;
	}
	/**
	 * @param graphDictionary the graphDictionary to set
	 */
	public void setGraphDictionary(boolean graphDictionary) {
		this.graphDictionary = graphDictionary;
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
	 * @return the dictionaryDetails
	 */
	public List getDictionaryDetails() {
		return dictionaryDetails;
	}
	/**
	 * @param dictionaryDetails the dictionaryDetails to set
	 */
	public void setDictionaryDetails(List dictionaryDetails) {
		this.dictionaryDetails = dictionaryDetails;
	}
	/**
	 * @return the showDescription
	 */
	public boolean getShowDescription() {
		return showDescription;
	}
	/**
	 * @param showDescription the showDescription to set
	 */
	public void setShowDescription(boolean showDescription) {
		this.showDescription = showDescription;
	}
	/**
	 * @return the allowChangeDisplayOrder
	 */
	public boolean getAllowChangeDisplayOrder() {
		return allowChangeDisplayOrder;
	}
	/**
	 * @param allowChangeDisplayOrder the allowChangeDisplayOrder to set
	 */
	public void setAllowChangeDisplayOrder(boolean allowChangeDisplayOrder) {
		this.allowChangeDisplayOrder = allowChangeDisplayOrder;
	}
	/**
	 * @return the codeDisplay
	 */
	public String getCodeDisplay() {
		return codeDisplay;
	}
	/**
	 * @param codeDisplay the codeDisplay to set
	 */
	public void setCodeDisplay(String codeDisplay) {
		this.codeDisplay = codeDisplay;
	}
	/**
	 * @return the valueDisplay
	 */
	public String getValueDisplay() {
		return valueDisplay;
	}
	/**
	 * @param valueDisplay the valueDisplay to set
	 */
	public void setValueDisplay(String valueDisplay) {
		this.valueDisplay = valueDisplay;
	}
   /**
    * @return the globalDictionary
    */
   public boolean getGlobalDictionary()
   {
      return globalDictionary;
   }
   /**
    * @param globalDictionary the globalDictionary to set
    */
   public void setGlobalDictionary(boolean globalDictionary)
   {
      this.globalDictionary = globalDictionary;
   }
}
