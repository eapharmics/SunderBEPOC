package com.enviroapps.eapharmics.bom.newstudy;

import java.io.Serializable;
import java.util.Date;

public class NewStudyCondition {

	private static final long serialVersionUID = 1L;

	private Long prdStudyConditionId;
	private Long prdStudyBatchId;
	private String studyCondition;
	private String normalEnvConditionsCode;
	private String challengeConditionsCode;
	private String studyReasonCode;
	private String studyTestInterval;
	private Date intialTestDate;
	private String studyLengthCode;
	private String intervalLabel;
	private Long protocolId;
	private String storageLocationCode;
	private Long totalValue;
	private Long extraValue;
	private Long initialValue;
	private String studyInventoryComment;
	private String labelFormat;
	private String labelColor;
	private Date insertDate;
	private Long insertUser;
	private Date updateDate;
	private Long updateUser;
	private Long auditId;
	private String comments;
	private Long fillUnits;
	private String unitOfMeasurement;
    private int extraLabelCount;

	/* (non-Javadoc)
	 * @see com.enviroapps.das.persistence.AbstractPersistableObject#getPK()
	 */
	public Serializable getPK() {
		return prdStudyConditionId;
	}
	/* (non-Javadoc)
	 * @see com.enviroapps.das.persistence.AbstractPersistableObject#loadPK(java.lang.Object[])
	 */
	public void loadPK(Object[] pks) {
		prdStudyConditionId = (Long)pks[0];
	}
	/**
	 * @return the prdStudyConditionId
	 */
	public Long getPrdStudyConditionId() {
		return prdStudyConditionId;
	}
	/**
	 * @param prdStudyConditionId the prdStudyConditionId to set
	 */
	public void setPrdStudyConditionId(Long prdStudyConditionId) {
		this.prdStudyConditionId = prdStudyConditionId;
	}
	/**
	 * @return the prdStudyBatchId
	 */
	
	

	public Long getPrdStudyBatchId() {
		return prdStudyBatchId;
	}
	/**
	 * @param prdStudyBatchId the prdStudyBatchId to set
	 */
	public void setPrdStudyBatchId(Long prdStudyBatchId) {
		this.prdStudyBatchId = prdStudyBatchId;
	}
	/**
	 * @return the studyCondition
	 */
	public String getStudyCondition() {
		return studyCondition;
	}
	/**
	 * @param studyCondition the studyCondition to set
	 */
	public void setStudyCondition(String studyCondition) {
		this.studyCondition = studyCondition;
	}
	/**
	 * @return the normalEnvConditionsCode
	 */
	public String getNormalEnvConditionsCode() {
		return normalEnvConditionsCode;
	}
	/**
	 * @param normalEnvConditionsCode the normalEnvConditionsCode to set
	 */
	public void setNormalEnvConditionsCode(String normalEnvConditionsCode) {
		this.normalEnvConditionsCode = normalEnvConditionsCode;
	}
	/**
	 * @return the challengeConditionsCode
	 */
	public String getChallengeConditionsCode() {
		return challengeConditionsCode;
	}
	/**
	 * @param challengeConditionsCode the challengeConditionsCode to set
	 */
	public void setChallengeConditionsCode(String challengeConditionsCode) {
		this.challengeConditionsCode = challengeConditionsCode;
	}
	/**
	 * @return the studyReasonCode
	 */
	public String getStudyReasonCode() {
		return studyReasonCode;
	}
	/**
	 * @param studyReasonCode the studyReasonCode to set
	 */
	public void setStudyReasonCode(String studyReasonCode) {
		this.studyReasonCode = studyReasonCode;
	}
	/**
	 * @return the studyTestInterval
	 */
	public String getStudyTestInterval() {
		return studyTestInterval;
	}
	/**
	 * @param studyTestInterval the studyTestInterval to set
	 */
	public void setStudyTestInterval(String studyTestInterval) {
		this.studyTestInterval = studyTestInterval;
	}
	/**
	 * @return the intialTestDate
	 */
	public Date getIntialTestDate() {
		return intialTestDate;
	}
	/**
	 * @param intialTestDate the intialTestDate to set
	 */
	public void setIntialTestDate(Date intialTestDate) {
		this.intialTestDate = intialTestDate;
	}
	/**
	 * @return the studyLengthCode
	 */
	public String getStudyLengthCode() {
		return studyLengthCode;
	}
	/**
	 * @param studyLengthCode the studyLengthCode to set
	 */
	public void setStudyLengthCode(String studyLengthCode) {
		this.studyLengthCode = studyLengthCode;
	}
	/**
	 * @return the intervalLabel
	 */
	public String getIntervalLabel() {
		return intervalLabel;
	}
	/**
	 * @param intervalLabel the intervalLabel to set
	 */
	public void setIntervalLabel(String intervalLabel) {
		this.intervalLabel = intervalLabel;
	}
	/**
	 * @return the protocolId
	 */
	public Long getProtocolId() {
		return protocolId;
	}
	/**
	 * @param protocolId the protocolId to set
	 */
	public void setProtocolId(Long protocolId) {
		this.protocolId = protocolId;
	}
	/**
	 * @return the storageLocationCode
	 */
	public String getStorageLocationCode() {
		return storageLocationCode;
	}
	/**
	 * @param storageLocationCode the storageLocationCode to set
	 */
	public void setStorageLocationCode(String storageLocationCode) {
		this.storageLocationCode = storageLocationCode;
	}
	/**
	 * @return the totalValue
	 */
	public Long getTotalValue() {
		return totalValue;
	}
	/**
	 * @param totalValue the totalValue to set
	 */
	public void setTotalValue(Long totalValue) {
		this.totalValue = totalValue;
	}
	/**
	 * @return the extraValue
	 */
	public Long getExtraValue() {
		return extraValue;
	}
	/**
	 * @param extraValue the extraValue to set
	 */
	public void setExtraValue(Long extraValue) {
		this.extraValue = extraValue;
	}
	/**
	 * @return the initialValue
	 */
	public Long getInitialValue() {
		return initialValue;
	}
	/**
	 * @param initialValue the initialValue to set
	 */
	public void setInitialValue(Long initialValue) {
		this.initialValue = initialValue;
	}
	/**
	 * @return the studyInventoryComment
	 */
	public String getStudyInventoryComment() {
		return studyInventoryComment;
	}
	/**
	 * @param studyInventoryComment the studyInventoryComment to set
	 */
	public void setStudyInventoryComment(String studyInventoryComment) {
		this.studyInventoryComment = studyInventoryComment;
	}
	/**
	 * @return the labelFormat
	 */
	public String getLabelFormat() {
		return labelFormat;
	}
	/**
	 * @param labelFormat the labelFormat to set
	 */
	public void setLabelFormat(String labelFormat) {
		this.labelFormat = labelFormat;
	}
	/**
	 * @return the labelColor
	 */
	public String getLabelColor() {
		return labelColor;
	}
	/**
	 * @param labelColor the labelColor to set
	 */
	public void setLabelColor(String labelColor) {
		this.labelColor = labelColor;
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
	
	public Long getfillUnits() {
		return fillUnits;
	}
	/**
	 * @param fillUnits the fillUnits to set
	 */
	public void setfillUnits(Long fillUnits) {
		this.fillUnits = fillUnits;
	}
	
	public String getunitOfMeasurement() {
		return unitOfMeasurement;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setunitOfMeasurement(String unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
	}
	/**
	 * @return the extraLabelCount
	 */
	public int getExtraLabelCount() {
		return extraLabelCount;
	}
	/**
	 * @param extraLabelCount the extraLabelCount to set
	 */
	public void setExtraLabelCount(int extraLabelCount) {
		this.extraLabelCount = extraLabelCount;
	}
}
