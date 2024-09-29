package com.enviroapps.eapharmics.vo.newstudy;

import java.util.Date;

public class NewStudySummaryVO {

	private static final long serialVersionUID = 1L;

	private Long prdStudyScheduleId;
	private Long prdStudyBatchId;
	private Long prdStudyIntervalId;
	private String productCode;
	private String productName;
	private String productDescription;
	private String lotNumber;
	private String studyId;
	private String studyCondition;
	private String storageLocation;
	private String formula;
	private String batchSize;
	private String manufacturingSiteCode;
	private String packagingSiteCode;
	private Date manufacturingDate;
	private Date packageDate;
	private Date initialDate;
	private String intervalLabel;
	private String fill;
	private String containerCode;
	private String closure;
	private Long totalValue;
	private Long extraValue;
	private Long initialValue;
	private String intervalValue;
	private String unitsPulled;
	private Date datePulled;
	private String pulledBy;
	private Date testStartDate;
	private Date testEndDate;
	private String scheduleStatus;
	private Date scheduleStatusDate;
	private Date pendingStatusDate;
	private Date completedStatusDate;
	private Date terminatedStatusDate;
	private String intervalTestStatus;
	private String intervalTestStatusBy;
	private Date intervalTestStatusDate;
	private Long extraPulled;
	private Date extraPulledDate;
	private String extraPulledBy;
	private Date scheduleDate;
	private Long intervalUnits;
	private Long extrasAvailable;	
	private String studySummaryString;
	private String testStartedBy;
	private String testEndedBy;
	private Date pullInitiatedDate;
	private String pullInitiatedBy;
	private String productType;
	private Long fillUnits;
	private String packageSizeCode;
	private String unitOfMeasurement;
	private String intervalText;
	private String studyReasonCode;
	private String userDefinedColumn1;
	private String userDefinedColumn2;
	private String userDefinedColumn3;
	private String userDefinedColumn4;
	private String userDefinedColumn5;
	private String userDefinedColumn6;
	private String userDefinedColumn7;
	private String userDefinedColumn8;
	private String userDefinedColumn9;
	private String userDefinedColumn10;
	private Boolean flag;
	private Long pullRank;
	private Long pullInitiateRank;
	private Long alreadyPulledRank;
   private String codeYear;
   private String testingLab;
   private String partNo;
   private String barcodeString;
	private String fillerCode;
	private Long locationId;
	private String studyStatus;
	private String scannedUnits;
	private String studyReasonDescription;
	private Boolean pastInterval;
	private String expiry;
	private Long numberOfDaysLate;
	private Long protocolId;
	private String scheduledPeriod;
	private String blisterCode;
	private String closureManufacturerCode;
	private String containerManufacturerCode;
	private String containerResinCode;
	private String desiccantCode;
	private String desiccantManufacturerCode;
	private String fillerManufacturerCode;
	private String formingFoilCode;
	private String innersealCode;
	private String liddingFoilCode;
	private String linerCode;
	private String linerManufacturerCode;
	private String otherCode1;
	private String otherCode2;
	private String otherManufacturerCode1;
	private String otherManufacturerCode2;
	private Long productId;
	private String propreitaryStudyId;
	private String studyText;
	private Date onStabilityDate;
	private String lateDaysDisplay;
	private Date scheduleDateForSort;
	private Date initialDateForSort;
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
	 * @return the productCode
	 */
	public String getProductCode() {
		return productCode;
	}
	/**
	 * @param productCode the productCode to set
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * @return the productDescription
	 */
	public String getProductDescription() {
		return productDescription;
	}
	/**
	 * @param productDescription the productDescription to set
	 */
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	/**
	 * @return the lotNumber
	 */
	public String getLotNumber() {
		return lotNumber;
	}
	/**
	 * @param lotNumber the lotNumber to set
	 */
	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}
	/**
	 * @return the studyId
	 */
	public String getStudyId() {
		return studyId;
	}
	/**
	 * @param studyId the studyId to set
	 */
	public void setStudyId(String studyId) {
		this.studyId = studyId;
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
	 * @return the storageLocation
	 */
	public String getStorageLocation() {
		return storageLocation;
	}
	/**
	 * @param storageLocation the storageLocation to set
	 */
	public void setStorageLocation(String storageLocation) {
		this.storageLocation = storageLocation;
	}
	/**
	 * @return the formula
	 */
	public String getFormula() {
		return formula;
	}
	/**
	 * @param formula the formula to set
	 */
	public void setFormula(String formula) {
		this.formula = formula;
	}
	/**
	 * @return the batchSize
	 */
	public String getBatchSize() {
		return batchSize;
	}
	/**
	 * @param batchSize the batchSize to set
	 */
	public void setBatchSize(String batchSize) {
		this.batchSize = batchSize;
	}
	/**
	 * @return the manufacturingSiteCode
	 */
	public String getManufacturingSiteCode() {
		return manufacturingSiteCode;
	}
	/**
	 * @param manufacturingSiteCode the manufacturingSiteCode to set
	 */
	public void setManufacturingSiteCode(String manufacturingSiteCode) {
		this.manufacturingSiteCode = manufacturingSiteCode;
	}
	/**
	 * @return the packagingSiteCode
	 */
	public String getPackagingSiteCode() {
		return packagingSiteCode;
	}
	/**
	 * @param packagingSiteCode the packagingSiteCode to set
	 */
	public void setPackagingSiteCode(String packagingSiteCode) {
		this.packagingSiteCode = packagingSiteCode;
	}
	/**
	 * @return the manufacturingDate
	 */
	public Date getManufacturingDate() {
		return manufacturingDate;
	}
	/**
	 * @param manufacturingDate the manufacturingDate to set
	 */
	public void setManufacturingDate(Date manufacturingDate) {
		this.manufacturingDate = manufacturingDate;
	}
	/**
	 * @return the packageDate
	 */
	public Date getPackageDate() {
		return packageDate;
	}
	/**
	 * @param packageDate the packageDate to set
	 */
	public void setPackageDate(Date packageDate) {
		this.packageDate = packageDate;
	}
	/**
	 * @return the initialDate
	 */
	public Date getInitialDate() {
		return initialDate;
	}
	/**
	 * @param initialDate the initialDate to set
	 */
	public void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
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
	 * @return the fill
	 */
	public String getFill() {
		return fill;
	}
	/**
	 * @param fill the fill to set
	 */
	public void setFill(String fill) {
		this.fill = fill;
	}
	/**
	 * @return the containerCode
	 */
	public String getContainerCode() {
		return containerCode;
	}
	/**
	 * @param containerCode the containerCode to set
	 */
	public void setContainerCode(String containerCode) {
		this.containerCode = containerCode;
	}
	/**
	 * @return the closure
	 */
	public String getClosure() {
		return closure;
	}
	/**
	 * @param closure the closure to set
	 */
	public void setClosure(String closure) {
		this.closure = closure;
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
	 * @return the extraPulled
	 */
	public Long getExtraPulled() {
		return extraPulled;
	}
	/**
	 * @param extraPulled the extraPulled to set
	 */
	public void setExtraPulled(Long extraPulled) {
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
    * @return the intervalUnits
    */
   public Long getIntervalUnits()
   {
      return intervalUnits;
   }
   /**
    * @param intervalUnits the intervalUnits to set
    */
   public void setIntervalUnits(Long intervalUnits)
   {
      this.intervalUnits = intervalUnits;
   }
   /**
    * @return the studySummaryString
    */
   public String getStudySummaryString()
   {
      return studySummaryString;
   }
   /**
    * @param studySummaryString the studySummaryString to set
    */
   public void setStudySummaryString(String studySummaryString)
   {
      this.studySummaryString = studySummaryString;
   }
   /**
    * @return the extrasAvailable
    */
   public Long getExtrasAvailable()
   {
      return extrasAvailable;
   }
   /**
    * @param extrasAvailable the extrasAvailable to set
    */
   public void setExtrasAvailable(Long extrasAvailable)
   {
      this.extrasAvailable = extrasAvailable;
   }
   /**
    * @return the testStartedBy
    */
   public String getTestStartedBy()
   {
      return testStartedBy;
   }
   /**
    * @param testStartedBy the testStartedBy to set
    */
   public void setTestStartedBy(String testStartedBy)
   {
      this.testStartedBy = testStartedBy;
   }
   /**
    * @return the testEndedBy
    */
   public String getTestEndedBy()
   {
      return testEndedBy;
   }
   /**
    * @param testEndedBy the testEndedBy to set
    */
   public void setTestEndedBy(String testEndedBy)
   {
      this.testEndedBy = testEndedBy;
   }
   /**
    * @return the pullInitiatedDate
    */
   public Date getPullInitiatedDate()
   {
      return pullInitiatedDate;
   }
   /**
    * @param pullInitiatedDate the pullInitiatedDate to set
    */
   public void setPullInitiatedDate(Date pullInitiatedDate)
   {
      this.pullInitiatedDate = pullInitiatedDate;
   }
   /**
    * @return the pullInitiatedBy
    */
   public String getPullInitiatedBy()
   {
      return pullInitiatedBy;
   }
   /**
    * @param pullInitiatedBy the pullInitiatedBy to set
    */
   public void setPullInitiatedBy(String pullInitiatedBy)
   {
      this.pullInitiatedBy = pullInitiatedBy;
   }
/**
 * @return the productType
 */
public String getProductType() {
	return productType;
}
/**
 * @param productType the productType to set
 */
public void setProductType(String productType) {
	this.productType = productType;
}
/**
 * @return the fillUnits
 */
public Long getFillUnits() {
	return fillUnits;
}
/**
 * @param fillUnits the fillUnits to set
 */
public void setFillUnits(Long fillUnits) {
	this.fillUnits = fillUnits;
}
/**
 * @return the packageSizeCode
 */
public String getPackageSizeCode() {
	return packageSizeCode;
}
/**
 * @param packageSizeCode the packageSizeCode to set
 */
public void setPackageSizeCode(String packageSizeCode) {
	this.packageSizeCode = packageSizeCode;
}
/**
 * @return the unitOfMeasurement
 */
public String getUnitOfMeasurement() {
	return unitOfMeasurement;
}
/**
 * @param unitOfMeasurement the unitOfMeasurement to set
 */
public void setUnitOfMeasurement(String unitOfMeasurement) {
	this.unitOfMeasurement = unitOfMeasurement;
}
/**
 * @return the intervalText
 */
public String getIntervalText()
{
   return intervalText;
}
/**
 * @param intervalText the intervalText to set
 */
public void setIntervalText(String intervalText)
{
   this.intervalText = intervalText;
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
 * @return the userDefinedColumn1
 */
public String getUserDefinedColumn1() {
	return userDefinedColumn1;
}
/**
 * @param userDefinedColumn1 the userDefinedColumn1 to set
 */
public void setUserDefinedColumn1(String userDefinedColumn1) {
	this.userDefinedColumn1 = userDefinedColumn1;
}
/**
 * @return the userDefinedColumn2
 */
public String getUserDefinedColumn2() {
	return userDefinedColumn2;
}
/**
 * @param userDefinedColumn2 the userDefinedColumn2 to set
 */
public void setUserDefinedColumn2(String userDefinedColumn2) {
	this.userDefinedColumn2 = userDefinedColumn2;
}
/**
 * @return the userDefinedColumn3
 */
public String getUserDefinedColumn3() {
	return userDefinedColumn3;
}
/**
 * @param userDefinedColumn3 the userDefinedColumn3 to set
 */
public void setUserDefinedColumn3(String userDefinedColumn3) {
	this.userDefinedColumn3 = userDefinedColumn3;
}
/**
 * @return the userDefinedColumn4
 */
public String getUserDefinedColumn4() {
	return userDefinedColumn4;
}
/**
 * @param userDefinedColumn4 the userDefinedColumn4 to set
 */
public void setUserDefinedColumn4(String userDefinedColumn4) {
	this.userDefinedColumn4 = userDefinedColumn4;
}
/**
 * @return the userDefinedColumn5
 */
public String getUserDefinedColumn5() {
	return userDefinedColumn5;
}
/**
 * @param userDefinedColumn5 the userDefinedColumn5 to set
 */
public void setUserDefinedColumn5(String userDefinedColumn5) {
	this.userDefinedColumn5 = userDefinedColumn5;
}
/**
 * @return the userDefinedColumn6
 */
public String getUserDefinedColumn6() {
	return userDefinedColumn6;
}
/**
 * @param userDefinedColumn6 the userDefinedColumn6 to set
 */
public void setUserDefinedColumn6(String userDefinedColumn6) {
	this.userDefinedColumn6 = userDefinedColumn6;
}
/**
 * @return the userDefinedColumn7
 */
public String getUserDefinedColumn7() {
	return userDefinedColumn7;
}
/**
 * @param userDefinedColumn7 the userDefinedColumn7 to set
 */
public void setUserDefinedColumn7(String userDefinedColumn7) {
	this.userDefinedColumn7 = userDefinedColumn7;
}
/**
 * @return the userDefinedColumn8
 */
public String getUserDefinedColumn8() {
	return userDefinedColumn8;
}
/**
 * @param userDefinedColumn8 the userDefinedColumn8 to set
 */
public void setUserDefinedColumn8(String userDefinedColumn8) {
	this.userDefinedColumn8 = userDefinedColumn8;
}
/**
 * @return the userDefinedColumn9
 */
public String getUserDefinedColumn9() {
	return userDefinedColumn9;
}
/**
 * @param userDefinedColumn9 the userDefinedColumn9 to set
 */
public void setUserDefinedColumn9(String userDefinedColumn9) {
	this.userDefinedColumn9 = userDefinedColumn9;
}
/**
 * @return the userDefinedColumn10
 */
public String getUserDefinedColumn10() {
	return userDefinedColumn10;
}
/**
 * @param userDefinedColumn10 the userDefinedColumn10 to set
 */
public void setUserDefinedColumn10(String userDefinedColumn10) {
	this.userDefinedColumn10 = userDefinedColumn10;
}
/**
 * @return the flag
 */
public Boolean getFlag()
{
   return flag;
}
/**
 * @param flag the flag to set
 */
public void setFlag(Boolean flag)
{
   this.flag = flag;
}
/**
 * @return the pullRank
 */
public Long getPullRank()
{
   return pullRank;
}
/**
 * @param pullRank the pullRank to set
 */
public void setPullRank(Long pullRank)
{
   this.pullRank = pullRank;
}
/**
 * @return the pullInitiateRank
 */
public Long getPullInitiateRank()
{
   return pullInitiateRank;
}
/**
 * @param pullInitiateRank the pullInitiateRank to set
 */
public void setPullInitiateRank(Long pullInitiateRank)
{
   this.pullInitiateRank = pullInitiateRank;
}
/**
 * @return the alreadyPulledRank
 */
public Long getAlreadyPulledRank()
{
   return alreadyPulledRank;
}
/**
 * @param alreadyPulledRank the alreadyPulledRank to set
 */
public void setAlreadyPulledRank(Long alreadyPulledRank)
{
   this.alreadyPulledRank = alreadyPulledRank;
}
/**
 * @return the codeYear
 */
public String getCodeYear()
{
   return codeYear;
}
/**
 * @param codeYear the codeYear to set
 */
public void setCodeYear(String codeYear)
{
   this.codeYear = codeYear;
}
/**
 * @return the testingLab
 */
public String getTestingLab()
{
   return testingLab;
}
/**
 * @param testingLab the testingLab to set
 */
public void setTestingLab(String testingLab)
{
   this.testingLab = testingLab;
}
/**
 * @return the partNo
 */
public String getPartNo()
{
   return partNo;
}
/**
 * @param partNo the partNo to set
 */
public void setPartNo(String partNo)
{
   this.partNo = partNo;
}
/**
 * @return the barcodeString
 */
public String getBarcodeString()
{
   return barcodeString;
}
/**
 * @param barcodestring the barcodeString to set
 */
public void setBarcodeString(String barcodeString)
{
   this.barcodeString = barcodeString;
}
/**
 * @return the fillerCode
 */
public String getFillerCode()
{
   return fillerCode;
}
/**
 * @param fillerCode the fillerCode to set
 */
public void setFillerCode(String fillerCode)
{
   this.fillerCode = fillerCode;
}
/**
 * @return the locationId
 */
public Long getLocationId() {
	return locationId;
}
/**
 * @param locationId the locationId to set
 */
public void setLocationId(Long locationId) {
	this.locationId = locationId;
}
/**
 * @return the studyStatus
 */
public String getStudyStatus()
{
   return studyStatus;
}
/**
 * @param studyStatus the studyStatus to set
 */
public void setStudyStatus(String studyStatus)
{
   this.studyStatus = studyStatus;
}

/**
 * @return the scannedUnits
 */
public String getScannedUnits()
{
   return scannedUnits;
}
/**
 * @param scannedUnits the scannedUnits to set
 */
public void setScannedUnits(String scannedUnits)
{
   this.scannedUnits = scannedUnits;
}
public String getStudyReasonDescription() {
	return studyReasonDescription;
}
public void setStudyReasonDescription(String studyReasonDescription) {
	this.studyReasonDescription = studyReasonDescription;
}
/**
 * @return the pastInterval
 */
public Boolean getPastInterval()
{
   return pastInterval;
}
/**
 * @param pastInterval the pastInterval to set
 */
public void setPastInterval(Boolean pastInterval)
{
   this.pastInterval = pastInterval;
}
/**
 * @return the expiry
 */
public String getExpiry()
{
   return expiry;
}
/**
 * @param expiry the expiry to set
 */
public void setExpiry(String expiry)
{
   this.expiry = expiry;
}
/**
 * @return the numberOfDaysLate
 */
public Long getNumberOfDaysLate()
{
   return numberOfDaysLate;
}
/**
 * @param numberOfDaysLate the numberOfDaysLate to set
 */
public void setNumberOfDaysLate(Long numberOfDaysLate)
{
   this.numberOfDaysLate = numberOfDaysLate;
}
/**
 * @return the protocolId
 */
public Long getProtocolId()
{
   return protocolId;
}
/**
 * @return the scheduledPeriod
 */
public String getScheduledPeriod()
{
   return scheduledPeriod;
}
/**
 * @param protocolId the protocolId to set
 */
public void setProtocolId(Long protocolId)
{
   this.protocolId = protocolId;
}
/**
 * @param scheduledPeriod the scheduledPeriod to set
 */
public void setScheduledPeriod(String scheduledPeriod)
{
   this.scheduledPeriod = scheduledPeriod;
}
/**
 * @return the blisterCode
 */
public String getBlisterCode()
{
   return blisterCode;
}
/**
 * @return the closureManufacturerCode
 */
public String getClosureManufacturerCode()
{
   return closureManufacturerCode;
}
/**
 * @return the containerManufacturerCode
 */
public String getContainerManufacturerCode()
{
   return containerManufacturerCode;
}
/**
 * @return the containerResinCode
 */
public String getContainerResinCode()
{
   return containerResinCode;
}
/**
 * @return the desiccantCode
 */
public String getDesiccantCode()
{
   return desiccantCode;
}
/**
 * @return the desiccantManufacturerCode
 */
public String getDesiccantManufacturerCode()
{
   return desiccantManufacturerCode;
}
/**
 * @return the fillerManufacturerCode
 */
public String getFillerManufacturerCode()
{
   return fillerManufacturerCode;
}
/**
 * @return the formingFoilCode
 */
public String getFormingFoilCode()
{
   return formingFoilCode;
}
/**
 * @return the innersealCode
 */
public String getInnersealCode()
{
   return innersealCode;
}
/**
 * @return the liddingFoilCode
 */
public String getLiddingFoilCode()
{
   return liddingFoilCode;
}
/**
 * @return the linerCode
 */
public String getLinerCode()
{
   return linerCode;
}
/**
 * @return the linerManufacturerCode
 */
public String getLinerManufacturerCode()
{
   return linerManufacturerCode;
}
/**
 * @return the otherCode1
 */
public String getOtherCode1()
{
   return otherCode1;
}
/**
 * @return the otherCode2
 */
public String getOtherCode2()
{
   return otherCode2;
}
/**
 * @return the otherManufacturerCode1
 */
public String getOtherManufacturerCode1()
{
   return otherManufacturerCode1;
}
/**
 * @return the otherManufacturerCode2
 */
public String getOtherManufacturerCode2()
{
   return otherManufacturerCode2;
}
/**
 * @return the productId
 */
public Long getProductId()
{
   return productId;
}
/**
 * @return the propreitaryStudyId
 */
public String getPropreitaryStudyId()
{
   return propreitaryStudyId;
}
/**
 * @return the studyText
 */
public String getStudyText()
{
   return studyText;
}
/**
 * @param blisterCode the blisterCode to set
 */
public void setBlisterCode(String blisterCode)
{
   this.blisterCode = blisterCode;
}
/**
 * @param closureManufacturerCode the closureManufacturerCode to set
 */
public void setClosureManufacturerCode(String closureManufacturerCode)
{
   this.closureManufacturerCode = closureManufacturerCode;
}
/**
 * @param containerManufacturerCode the containerManufacturerCode to set
 */
public void setContainerManufacturerCode(String containerManufacturerCode)
{
   this.containerManufacturerCode = containerManufacturerCode;
}
/**
 * @param containerResinCode the containerResinCode to set
 */
public void setContainerResinCode(String containerResinCode)
{
   this.containerResinCode = containerResinCode;
}
/**
 * @param desiccantCode the desiccantCode to set
 */
public void setDesiccantCode(String desiccantCode)
{
   this.desiccantCode = desiccantCode;
}
/**
 * @param desiccantManufacturerCode the desiccantManufacturerCode to set
 */
public void setDesiccantManufacturerCode(String desiccantManufacturerCode)
{
   this.desiccantManufacturerCode = desiccantManufacturerCode;
}
/**
 * @param fillerManufacturerCode the fillerManufacturerCode to set
 */
public void setFillerManufacturerCode(String fillerManufacturerCode)
{
   this.fillerManufacturerCode = fillerManufacturerCode;
}
/**
 * @param formingFoilCode the formingFoilCode to set
 */
public void setFormingFoilCode(String formingFoilCode)
{
   this.formingFoilCode = formingFoilCode;
}
/**
 * @param innersealCode the innersealCode to set
 */
public void setInnersealCode(String innersealCode)
{
   this.innersealCode = innersealCode;
}
/**
 * @param liddingFoilCode the liddingFoilCode to set
 */
public void setLiddingFoilCode(String liddingFoilCode)
{
   this.liddingFoilCode = liddingFoilCode;
}
/**
 * @param linerCode the linerCode to set
 */
public void setLinerCode(String linerCode)
{
   this.linerCode = linerCode;
}
/**
 * @param linerManufacturerCode the linerManufacturerCode to set
 */
public void setLinerManufacturerCode(String linerManufacturerCode)
{
   this.linerManufacturerCode = linerManufacturerCode;
}
/**
 * @param otherCode1 the otherCode1 to set
 */
public void setOtherCode1(String otherCode1)
{
   this.otherCode1 = otherCode1;
}
/**
 * @param otherCode2 the otherCode2 to set
 */
public void setOtherCode2(String otherCode2)
{
   this.otherCode2 = otherCode2;
}
/**
 * @param otherManufacturerCode1 the otherManufacturerCode1 to set
 */
public void setOtherManufacturerCode1(String otherManufacturerCode1)
{
   this.otherManufacturerCode1 = otherManufacturerCode1;
}
/**
 * @param otherManufacturerCode2 the otherManufacturerCode2 to set
 */
public void setOtherManufacturerCode2(String otherManufacturerCode2)
{
   this.otherManufacturerCode2 = otherManufacturerCode2;
}
/**
 * @param productId the productId to set
 */
public void setProductId(Long productId)
{
   this.productId = productId;
}
/**
 * @param propreitaryStudyId the propreitaryStudyId to set
 */
public void setPropreitaryStudyId(String propreitaryStudyId)
{
   this.propreitaryStudyId = propreitaryStudyId;
}
/**
 * @param studyText the studyText to set
 */
public void setStudyText(String studyText)
{
   this.studyText = studyText;
}
/**
 * @return the onStabilityDate
 */
public Date getOnStabilityDate()
{
   return onStabilityDate;
}
/**
 * @param onStabilityDate the onStabilityDate to set
 */
public void setOnStabilityDate(Date onStabilityDate)
{
   this.onStabilityDate = onStabilityDate;
}
/**
 * @return the lateDaysDisplay
 */
public String getLateDaysDisplay()
{
   return lateDaysDisplay;
}
/**
 * @param lateDaysDisplay the lateDaysDisplay to set
 */
public void setLateDaysDisplay(String lateDaysDisplay)
{
   this.lateDaysDisplay = lateDaysDisplay;
}
/**
 * @return the scheduleDateForSort
 */
public Date getScheduleDateForSort()
{
   return scheduleDateForSort;
}
/**
 * @param scheduleDateForSort the scheduleDateForSort to set
 */
public void setScheduleDateForSort(Date scheduleDateForSort)
{
   this.scheduleDateForSort = scheduleDateForSort;
}
/**
 * @return the initialDateForSort
 */
public Date getInitialDateForSort()
{
   return initialDateForSort;
}
/**
 * @param initialDateForSort the initialDateForSort to set
 */
public void setInitialDateForSort(Date initialDateForSort)
{
   this.initialDateForSort = initialDateForSort;
}

}
