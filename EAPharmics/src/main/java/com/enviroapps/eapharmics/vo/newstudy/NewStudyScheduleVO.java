package com.enviroapps.eapharmics.vo.newstudy;

import java.util.Date;

public class NewStudyScheduleVO {

	private static final long serialVersionUID = 1L;

	private Long prdStudyScheduleId;
	private Long prdStudyBatchId;
	private Long prdStudyIntervalId;
	private Date scheduleDate;
	private String intervalValue;
	private String unitsPulled;
	private String scheduleStatus;
	private Date scheduleStatusDate;
	private Date pendingStatusDate;
	private Date completedStatusDate;
	private Date terminatedStatusDate;
	private Date datePulled;
	private String pulledBy;
	private Date testStartDate;
	private Date testEndDate;
	private Date insertDate;
	private Long insertUser;
	private Date updateDate;
	private Long updateUser;
	private Long auditId;
	private String comments;
	private String extraPulled;
	private Date extraPulledDate;
	private String extraPulledBy;
	private String intervalTestStatus;
	private Date intervalTestStatusDate;
	private String intervalTestStatusBy;
	private Boolean approvalLevel1;
	private String approvalLevel1By;
	private Date approvalLevel1Date;
	private Boolean approvalLevel2;
	private String approvalLevel2By;
	private Date approvalLevel2Date;
	private int version;
	private boolean allIntervalsCompleted;
	private Date inputApprovalLevel1Date;
	private Date inputApprovalLevel2Date;
	private Date inputCompletedStatusDate;
	/**
	 * @return the prdStudyScheduleId
	 */
	public Long getPrdStudyScheduleId() {
		return prdStudyScheduleId;
	}
	/**
	 * @param prdStudyScheduleId the prdStudyScheduleId to set
	 */
	public void setPrdStudyScheduleId(Long prdStudyScheduleId) {
		this.prdStudyScheduleId = prdStudyScheduleId;
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
	 * @return the prdStudyIntervalId
	 */
	public Long getPrdStudyIntervalId() {
		return prdStudyIntervalId;
	}
	/**
	 * @param prdStudyIntervalId the prdStudyIntervalId to set
	 */
	public void setPrdStudyIntervalId(Long prdStudyIntervalId) {
		this.prdStudyIntervalId = prdStudyIntervalId;
	}
	/**
	 * @return the scheduleDate
	 */
	public Date getScheduleDate() {
		return scheduleDate;
	}
	/**
	 * @param scheduleDate the scheduleDate to set
	 */
	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	/**
	 * @return the intervalValue
	 */
	public String getIntervalValue() {
		return intervalValue;
	}
	/**
	 * @param intervalValue the intervalValue to set
	 */
	public void setIntervalValue(String intervalValue) {
		this.intervalValue = intervalValue;
	}
	/**
	 * @return the unitsPulled
	 */
	public String getUnitsPulled() {
		return unitsPulled;
	}
	/**
	 * @param unitsPulled the unitsPulled to set
	 */
	public void setUnitsPulled(String unitsPulled) {
		this.unitsPulled = unitsPulled;
	}
	/**
	 * @return the scheduleStatus
	 */
	public String getScheduleStatus() {
		return scheduleStatus;
	}
	/**
	 * @param scheduleStatus the scheduleStatus to set
	 */
	public void setScheduleStatus(String scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}
	/**
	 * @return the scheduleStatusDate
	 */
	public Date getScheduleStatusDate() {
		return scheduleStatusDate;
	}
	/**
	 * @param scheduleStatusDate the scheduleStatusDate to set
	 */
	public void setScheduleStatusDate(Date scheduleStatusDate) {
		this.scheduleStatusDate = scheduleStatusDate;
	}
	/**
	 * @return the pendingStatusDate
	 */
	public Date getPendingStatusDate() {
		return pendingStatusDate;
	}
	/**
	 * @param pendingStatusDate the pendingStatusDate to set
	 */
	public void setPendingStatusDate(Date pendingStatusDate) {
		this.pendingStatusDate = pendingStatusDate;
	}
	/**
	 * @return the completedStatusDate
	 */
	public Date getCompletedStatusDate() {
		return completedStatusDate;
	}
	/**
	 * @param completedStatusDate the completedStatusDate to set
	 */
	public void setCompletedStatusDate(Date completedStatusDate) {
		this.completedStatusDate = completedStatusDate;
	}
	/**
	 * @return the terminatedStatusDate
	 */
	public Date getTerminatedStatusDate() {
		return terminatedStatusDate;
	}
	/**
	 * @param terminatedStatusDate the terminatedStatusDate to set
	 */
	public void setTerminatedStatusDate(Date terminatedStatusDate) {
		this.terminatedStatusDate = terminatedStatusDate;
	}
	/**
	 * @return the datePulled
	 */
	public Date getDatePulled() {
		return datePulled;
	}
	/**
	 * @param datePulled the datePulled to set
	 */
	public void setDatePulled(Date datePulled) {
		this.datePulled = datePulled;
	}
	/**
	 * @return the pulledBy
	 */
	public String getPulledBy() {
		return pulledBy;
	}
	/**
	 * @param pulledBy the pulledBy to set
	 */
	public void setPulledBy(String pulledBy) {
		this.pulledBy = pulledBy;
	}
	/**
	 * @return the testStartDate
	 */
	public Date getTestStartDate() {
		return testStartDate;
	}
	/**
	 * @param testStartDate the testStartDate to set
	 */
	public void setTestStartDate(Date testStartDate) {
		this.testStartDate = testStartDate;
	}
	/**
	 * @return the testEndDate
	 */
	public Date getTestEndDate() {
		return testEndDate;
	}
	/**
	 * @param testEndDate the testEndDate to set
	 */
	public void setTestEndDate(Date testEndDate) {
		this.testEndDate = testEndDate;
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
	/**
	 * @return the extraPulled
	 */
	public String getExtraPulled() {
		return extraPulled;
	}
	/**
	 * @param extraPulled the extraPulled to set
	 */
	public void setExtraPulled(String extraPulled) {
		this.extraPulled = extraPulled;
	}
	/**
	 * @return the extraPulledDate
	 */
	public Date getExtraPulledDate() {
		return extraPulledDate;
	}
	/**
	 * @param extraPulledDate the extraPulledDate to set
	 */
	public void setExtraPulledDate(Date extraPulledDate) {
		this.extraPulledDate = extraPulledDate;
	}
	/**
	 * @return the extraPulledBy
	 */
	public String getExtraPulledBy() {
		return extraPulledBy;
	}
	/**
	 * @param extraPulledBy the extraPulledBy to set
	 */
	public void setExtraPulledBy(String extraPulledBy) {
		this.extraPulledBy = extraPulledBy;
	}
	/**
	 * @return the intervalTestStatus
	 */
	public String getIntervalTestStatus() {
		return intervalTestStatus;
	}
	/**
	 * @param intervalTestStatus the intervalTestStatus to set
	 */
	public void setIntervalTestStatus(String intervalTestStatus) {
		this.intervalTestStatus = intervalTestStatus;
	}
	/**
	 * @return the intervalTestStatusDate
	 */
	public Date getIntervalTestStatusDate() {
		return intervalTestStatusDate;
	}
	/**
	 * @param intervalTestStatusDate the intervalTestStatusDate to set
	 */
	public void setIntervalTestStatusDate(Date intervalTestStatusDate) {
		this.intervalTestStatusDate = intervalTestStatusDate;
	}
	/**
	 * @return the intervalTestStatusBy
	 */
	public String getIntervalTestStatusBy() {
		return intervalTestStatusBy;
	}
	/**
	 * @param intervalTestStatusBy the intervalTestStatusBy to set
	 */
	public void setIntervalTestStatusBy(String intervalTestStatusBy) {
		this.intervalTestStatusBy = intervalTestStatusBy;
	}
	/**
	 * @return the approvalLevel1
	 */
	public Boolean getApprovalLevel1() {
		return approvalLevel1;
	}
	/**
	 * @param approvalLevel1 the approvalLevel1 to set
	 */
	public void setApprovalLevel1(Boolean approvalLevel1) {
		this.approvalLevel1 = approvalLevel1;
	}
	/**
	 * @return the approvalLevel1By
	 */
	public String getApprovalLevel1By() {
		return approvalLevel1By;
	}
	/**
	 * @param approvalLevel1By the approvalLevel1By to set
	 */
	public void setApprovalLevel1By(String approvalLevel1By) {
		this.approvalLevel1By = approvalLevel1By;
	}
	/**
	 * @return the approvalLevel1Date
	 */
	public Date getApprovalLevel1Date() {
		return approvalLevel1Date;
	}
	/**
	 * @param approvalLevel1Date the approvalLevel1Date to set
	 */
	public void setApprovalLevel1Date(Date approvalLevel1Date) {
		this.approvalLevel1Date = approvalLevel1Date;
	}
	/**
	 * @return the approvalLevel2
	 */
	public Boolean getApprovalLevel2() {
		return approvalLevel2;
	}
	/**
	 * @param approvalLevel2 the approvalLevel2 to set
	 */
	public void setApprovalLevel2(Boolean approvalLevel2) {
		this.approvalLevel2 = approvalLevel2;
	}
	/**
	 * @return the approvalLevel2By
	 */
	public String getApprovalLevel2By() {
		return approvalLevel2By;
	}
	/**
	 * @param approvalLevel2By the approvalLevel2By to set
	 */
	public void setApprovalLevel2By(String approvalLevel2By) {
		this.approvalLevel2By = approvalLevel2By;
	}
	/**
	 * @return the approvalLevel2Date
	 */
	public Date getApprovalLevel2Date() {
		return approvalLevel2Date;
	}
	/**
	 * @param approvalLevel2Date the approvalLevel2Date to set
	 */
	public void setApprovalLevel2Date(Date approvalLevel2Date) {
		this.approvalLevel2Date = approvalLevel2Date;
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
	 * @return the allIntervalsCompleted
	 */
	public boolean getAllIntervalsCompleted() {
		return allIntervalsCompleted;
	}
	/**
	 * @param allIntervalsCompleted the allIntervalsCompleted to set
	 */
	public void setAllIntervalsCompleted(boolean allIntervalsCompleted) {
		this.allIntervalsCompleted = allIntervalsCompleted;
	}
	/**
	 * @return the inputApprovalLevel1Date
	 */
	public Date getInputApprovalLevel1Date() {
		return inputApprovalLevel1Date;
	}
	/**
	 * @param inputApprovalLevel1Date the inputApprovalLevel1Date to set
	 */
	public void setInputApprovalLevel1Date(Date inputApprovalLevel1Date) {
		this.inputApprovalLevel1Date = inputApprovalLevel1Date;
	}
	/**
	 * @return the inputApprovalLevel2Date
	 */
	public Date getInputApprovalLevel2Date() {
		return inputApprovalLevel2Date;
	}
	/**
	 * @param inputApprovalLevel2Date the inputApprovalLevel2Date to set
	 */
	public void setInputApprovalLevel2Date(Date inputApprovalLevel2Date) {
		this.inputApprovalLevel2Date = inputApprovalLevel2Date;
	}
	/**
	 * @return the inputCompletedStatusDate
	 */
	public Date getInputCompletedStatusDate() {
		return inputCompletedStatusDate;
	}
	/**
	 * @param inputCompletedStatusDate the inputCompletedStatusDate to set
	 */
	public void setInputCompletedStatusDate(Date inputCompletedStatusDate) {
		this.inputCompletedStatusDate = inputCompletedStatusDate;
	}
}
