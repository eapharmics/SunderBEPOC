package com.enviroapps.eapharmics.bom.newstudy;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class NewStudyProduct {

	private static final long serialVersionUID = 1L;

	private Long prdStudyBatchId;
	private Long productId;
	private String studyId;
	private String proprietaryStudyId;
	private String studyText;
	private String lotNumber;
	private String expiry;
	private String formulaNumber;
	private String batchSize;
	private Date packageDate;
	private Date mfgDate;
	private Date onStabilityDate;

	private String packageSizeCode;
	private String containerCode;
	private String containerResinCode;
	private String containerManufacturerCode;
	private String closureCode;
	private String closureManufacturerCode;
	private String innerSealCode;
	private String linerCode;
	private String fillerCode;
	private String desicantCode;
	private String blisterCode;
	private String linerManufacturerCode;
	private String otherCode1;
	private String otherManufacturerCode1;
	private String otherCode2;
	private String otherManufacturerCode2;
	private String manufacturingSiteCode;
	private String packagingSiteCode;
    private String liddingCode;
    private String formingCode;
    private String dessicantManufacturerCode;
	private String fillerManufacturerCode;
	private Long gallerySettingsCodeId;
	private String studyStatus;
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
	private Date insertDate;
	private Long insertUser;
	private Date updateDate;
	private Long updateUser;
	private Long auditId;
	private String comments;

	private Long labelId;

	private Boolean approvalLevel1;
	private Long approvalLevel1By;
	private Date approvalLevel1Date;
	private Boolean approvalLevel2;
	private Long approvalLevel2By;
	private Date approvalLevel2Date;

	private Set<NewStudyRawMaterial> rawMaterials = new HashSet<NewStudyRawMaterial>();
	private Set<NewStudyInterval> intervals = new HashSet<NewStudyInterval>();
	private NewStudyCondition studyCondition;

	private int version;
	private Long locationId;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.enviroapps.das.persistence.AbstractPersistableObject#getPK()
	 */
	public Serializable getPK() {
		return prdStudyBatchId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.enviroapps.das.persistence.AbstractPersistableObject#loadPK(java.
	 * lang.Object[])
	 */
	public void loadPK(Object[] pks) {
		prdStudyBatchId = (Long) pks[0];
	}

	/**
	 * @return the prdStudyBatchId
	 */
	public Long getPrdStudyBatchId() {
		return prdStudyBatchId;
	}

	/**
	 * @param prdStudyBatchId
	 *            the prdStudyBatchId to set
	 */
	public void setPrdStudyBatchId(Long prdStudyBatchId) {
		this.prdStudyBatchId = prdStudyBatchId;
	}

	/**
	 * @return the productId
	 */
	public Long getProductId() {
		return productId;
	}

	/**
	 * @param productId
	 *            the productId to set
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getStudyId() {
		return studyId;
	}

	/**
	 * @param StudyId
	 *            the StudyId to set
	 */
	public void setStudyId(String studyId) {
		this.studyId = studyId;
	}
	
	public String getProprietaryStudyId(){
	   return proprietaryStudyId;
	}
	
	public void setProprietaryStudyId(String proprietaryStudyId){
	   this.proprietaryStudyId=proprietaryStudyId;
	}

	public String getStudyText() {
		return studyText;
	}

	/**
	 * @param studyText
	 *            the studyText to set
	 */
	public void setStudyText(String studyText) {
		this.studyText = studyText;
	}

	/**
	 * @return the lotNumber
	 */
	public String getLotNumber() {
		return lotNumber;
	}

	/**
	 * @param lotNumber
	 *            the lotNumber to set
	 */
	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}

	/**
	 * @return the expiry
	 */
	public String getExpiry() {
		return expiry;
	}

	/**
	 * @param expiry
	 *            the expiry to set
	 */
	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}

	/**
	 * @return the formulaNumber
	 */
	public String getFormulaNumber() {
		return formulaNumber;
	}

	/**
	 * @param formulaNumber
	 *            the formulaNumber to set
	 */
	public void setFormulaNumber(String formulaNumber) {
		this.formulaNumber = formulaNumber;
	}

	/**
	 * @return the batchSize
	 */
	public String getBatchSize() {
		return batchSize;
	}

	/**
	 * @param batchSize
	 *            the batchSize to set
	 */
	public void setBatchSize(String batchSize) {
		this.batchSize = batchSize;
	}

	/**
	 * @return the packageDate
	 */
	public Date getPackageDate() {
		return packageDate;
	}

	/**
	 * @param packageDate
	 *            the packageDate to set
	 */
	public void setPackageDate(Date packageDate) {
		this.packageDate = packageDate;
	}

	/**
	 * @return the mfgDate
	 */
	public Date getMfgDate() {
		return mfgDate;
	}

	/**
	 * @param mfgDate
	 *            the mfgDate to set
	 */
	public void setMfgDate(Date mfgDate) {
		this.mfgDate = mfgDate;
	}

	/**
	 * @return the onStabilityDate
	 */
	public Date getOnStabilityDate() {
		return onStabilityDate;
	}

	/**
	 * @param onStabilityDate
	 *            the onStabilityDate to set
	 */
	public void setOnStabilityDate(Date onStabilityDate) {
		this.onStabilityDate = onStabilityDate;
	}

	/**
	 * @return the packageSizeCode
	 */
	public String getPackageSizeCode() {
		return packageSizeCode;
	}

	/**
	 * @param packageSizeCode
	 *            the packageSizeCode to set
	 */
	public void setPackageSizeCode(String packageSizeCode) {
		this.packageSizeCode = packageSizeCode;
	}

	/**
	 * @return the containerCode
	 */
	public String getContainerCode() {
		return containerCode;
	}

	/**
	 * @param containerCode
	 *            the containerCode to set
	 */
	public void setContainerCode(String containerCode) {
		this.containerCode = containerCode;
	}

	/**
	 * @return the containerResinCode
	 */
	public String getContainerResinCode() {
		return containerResinCode;
	}

	/**
	 * @param containerResinCode
	 *            the containerResinCode to set
	 */
	public void setContainerResinCode(String containerResinCode) {
		this.containerResinCode = containerResinCode;
	}

	/**
	 * @return the containerManufacturerCode
	 */
	public String getContainerManufacturerCode() {
		return containerManufacturerCode;
	}

	/**
	 * @param containerManufacturerCode
	 *            the containerManufacturerCode to set
	 */
	public void setContainerManufacturerCode(String containerManufacturerCode) {
		this.containerManufacturerCode = containerManufacturerCode;
	}

	/**
	 * @return the closureCode
	 */
	public String getClosureCode() {
		return closureCode;
	}

	/**
	 * @param closureCode
	 *            the closureCode to set
	 */
	public void setClosureCode(String closureCode) {
		this.closureCode = closureCode;
	}

	/**
	 * @return the closureManufacturerCode
	 */
	public String getClosureManufacturerCode() {
		return closureManufacturerCode;
	}

	/**
	 * @param closureManufacturerCode
	 *            the closureManufacturerCode to set
	 */
	public void setClosureManufacturerCode(String closureManufacturerCode) {
		this.closureManufacturerCode = closureManufacturerCode;
	}

	/**
	 * @return the innerSealCode
	 */
	public String getInnerSealCode() {
		return innerSealCode;
	}

	/**
	 * @param innerSealCode
	 *            the innerSealCode to set
	 */
	public void setInnerSealCode(String innerSealCode) {
		this.innerSealCode = innerSealCode;
	}

	/**
	 * @return the fillerCode
	 */
	public String getFillerCode() {
		return fillerCode;
	}

	/**
	 * @param fillerCode
	 *            the fillerCode to set
	 */
	public void setFillerCode(String fillerCode) {
		this.fillerCode = fillerCode;
	}

	/**
	 * @return the desicantCode
	 */
	public String getDesiccantCode() {
		return desicantCode;
	}

	/**
	 * @param desicantCode
	 *            the desicantCode to set
	 */
	public void setDesiccantCode(String desiccantCode) {
		this.desicantCode = desiccantCode;
	}

	/**
	 * @return the blisters
	 */
	public String getBlisterCode() {
		return blisterCode;
	}

	/**
	 * @param desicantCode
	 *            the desicantCode to set
	 */
	public void setBlisterCode(String blisterCode) {
		this.blisterCode = blisterCode;
	}

	/**
	 * @return the linerCode
	 */
	public String getLinerCode() {
		return linerCode;
	}

	/**
	 * @param linerCode
	 *            the linerCode to set
	 */
	public void setLinerCode(String linerCode) {
		this.linerCode = linerCode;
	}

	/**
	 * @return the linerManufacturerCode
	 */
	public String getLinerManufacturerCode() {
		return linerManufacturerCode;
	}

	/**
	 * @param linerManufacturerCode
	 *            the linerManufacturerCode to set
	 */
	public void setLinerManufacturerCode(String linerManufacturerCode) {
		this.linerManufacturerCode = linerManufacturerCode;
	}

	/**
	 * @return the otherCode1
	 */
	public String getOtherCode1() {
		return otherCode1;
	}

	/**
	 * @param otherCode1
	 *            the otherCode1 to set
	 */
	public void setOtherCode1(String otherCode1) {
		this.otherCode1 = otherCode1;
	}

	/**
	 * @return the otherManufacturerCode1
	 */
	public String getOtherManufacturerCode1() {
		return otherManufacturerCode1;
	}

	/**
	 * @param otherManufacturerCode1
	 *            the otherManufacturerCode1 to set
	 */
	public void setOtherManufacturerCode1(String otherManufacturerCode1) {
		this.otherManufacturerCode1 = otherManufacturerCode1;
	}

	/**
	 * @return the otherCode2
	 */
	public String getOtherCode2() {
		return otherCode2;
	}

	/**
	 * @param otherCode2
	 *            the otherCode2 to set
	 */
	public void setOtherCode2(String otherCode2) {
		this.otherCode2 = otherCode2;
	}

	/**
	 * @return the otherManufacturerCode2
	 */
	public String getOtherManufacturerCode2() {
		return otherManufacturerCode2;
	}

	/**
	 * @param otherManufacturerCode2
	 *            the otherManufacturerCode2 to set
	 */
	public void setOtherManufacturerCode2(String otherManufacturerCode2) {
		this.otherManufacturerCode2 = otherManufacturerCode2;
	}

	/**
	 * @return the manufacturingSiteCode
	 */
	public String getManufacturingSiteCode() {
		return manufacturingSiteCode;
	}

	/**
	 * @param manufacturingSiteCode
	 *            the manufacturingSiteCode to set
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
	 * @param packagingSiteCode
	 *            the packagingSiteCode to set
	 */
	public void setPackagingSiteCode(String packagingSiteCode) {
		this.packagingSiteCode = packagingSiteCode;
	}

	/**
	 * @return the gallerySettingsCodeId
	 */
	public Long getGallerySettingsCodeId() {
		return gallerySettingsCodeId;
	}

	/**
	 * @param gallerySettingsCodeId
	 *            the gallerySettingsCodeId to set
	 */
	public void setGallerySettingsCodeId(Long gallerySettingsCodeId) {
		this.gallerySettingsCodeId = gallerySettingsCodeId;
	}

	/**
	 * @return the studyStatus
	 */
	public String getStudyStatus() {
		return studyStatus;
	}

	/**
	 * @param studyStatus
	 *            the studyStatus to set
	 */
	public void setStudyStatus(String studyStatus) {
		this.studyStatus = studyStatus;
	}

	/**
	 * @return the userDefinedColumn1
	 */
	public String getUserDefinedColumn1() {
		return userDefinedColumn1;
	}

	/**
	 * @param userDefinedColumn1
	 *            the userDefinedColumn1 to set
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
	 * @param userDefinedColumn2
	 *            the userDefinedColumn2 to set
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
	 * @param userDefinedColumn3
	 *            the userDefinedColumn3 to set
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
	 * @param userDefinedColumn4
	 *            the userDefinedColumn4 to set
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
	 * @param userDefinedColumn5
	 *            the userDefinedColumn5 to set
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
	 * @param userDefinedColumn6
	 *            the userDefinedColumn6 to set
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
	 * @param userDefinedColumn7
	 *            the userDefinedColumn7 to set
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
	 * @param userDefinedColumn8
	 *            the userDefinedColumn8 to set
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
	 * @param userDefinedColumn9
	 *            the userDefinedColumn9 to set
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
	 * @param userDefinedColumn10
	 *            the userDefinedColumn10 to set
	 */
	public void setUserDefinedColumn10(String userDefinedColumn10) {
		this.userDefinedColumn10 = userDefinedColumn10;
	}

	/**
	 * @return the insertDate
	 */
	public Date getInsertDate() {
		return insertDate;
	}

	/**
	 * @param insertDate
	 *            the insertDate to set
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
	 * @param insertUser
	 *            the insertUser to set
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
	 * @param updateDate
	 *            the updateDate to set
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
	 * @param updateUser
	 *            the updateUser to set
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
	 * @param auditId
	 *            the auditId to set
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
	 * @param comments
	 *            the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return the rawMaterials
	 */
	public Set<NewStudyRawMaterial> getRawMaterials() {
		return rawMaterials;
	}

	/**
	 * @param rawMaterials
	 *            the rawMaterials to set
	 */
	public void setRawMaterials(Set<NewStudyRawMaterial> rawMaterials) {
		this.rawMaterials = rawMaterials;
	}

	/**
	 * @return the intervals
	 */
	public Set<NewStudyInterval> getIntervals() {
		return intervals;
	}

	/**
	 * @param intervals
	 *            the intervals to set
	 */
	public void setIntervals(Set<NewStudyInterval> intervals) {
		this.intervals = intervals;
	}

	/**
	 * @return the studyCondition
	 */

	public NewStudyCondition getStudyCondition() {
		return studyCondition;
	}

	public void setStudyCondition(NewStudyCondition studyCondition) {
		this.studyCondition = studyCondition;
	}

	/**
	 * @param prdStudyRawMaterialId
	 * @return
	 */
	public NewStudyRawMaterial getNewStudyRawMaterial(Long prdStudyRawMaterialId) {
		Object[] objs = rawMaterials.toArray();
		for (int i = 0; i < objs.length; i++) {
			NewStudyRawMaterial object = (NewStudyRawMaterial) objs[i];
			if (object.getPrdStudyRawMaterialId().longValue() == prdStudyRawMaterialId) {
				return object;
			}
		}
		return null;
	}

	/**
	 * @param prdStudyIntervalId
	 * @return
	 */
	public NewStudyInterval getNewStudyInterval(Long prdStudyIntervalId) {
		Object[] objs = intervals.toArray();
		for (int i = 0; i < objs.length; i++) {
			NewStudyInterval object = (NewStudyInterval) objs[i];
			if (object.getPrdStudyIntervalId().longValue() == prdStudyIntervalId) {
				return object;
			}
		}
		return null;
	}

	/**
	 * @return the labelId
	 */
	public Long getLabelId() {
		return labelId;
	}

	/**
	 * @param labelId
	 *            the labelId to set
	 */
	public void setLabelId(Long labelId) {
		this.labelId = labelId;
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
	public Long getApprovalLevel1By() {
		return approvalLevel1By;
	}
	/**
	 * @param approvalLevel1By the approvalLevel1By to set
	 */
	public void setApprovalLevel1By(Long approvalLevel1By) {
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
	public Long getApprovalLevel2By() {
		return approvalLevel2By;
	}
	/**
	 * @param approvalLevel2By the approvalLevel2By to set
	 */
	public void setApprovalLevel2By(Long approvalLevel2By) {
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
	 * @return the liddingCode
	 */
	public String getLiddingCode() {
		return liddingCode;
	}

	/**
	 * @param liddingCode the liddingCode to set
	 */
	public void setLiddingCode(String liddingCode) {
		this.liddingCode = liddingCode;
	}

	/**
	 * @return the formingCode
	 */
	public String getFormingCode() {
		return formingCode;
	}

	/**
	 * @param formingCode the formingCode to set
	 */
	public void setFormingCode(String formingCode) {
		this.formingCode = formingCode;
	}

	/**
	 * @return the dessicantManufacturerCode
	 */
	public String getDessicantManufacturerCode() {
		return dessicantManufacturerCode;
	}

	/**
	 * @param dessicantManufacturerCode the dessicantManufacturerCode to set
	 */
	public void setDessicantManufacturerCode(String dessicantManufacturerCode) {
		this.dessicantManufacturerCode = dessicantManufacturerCode;
	}

	/**
	 * @return the fillerManufacturerCode
	 */
	public String getFillerManufacturerCode() {
		return fillerManufacturerCode;
	}

	/**
	 * @param fillerManufacturerCode the fillerManufacturerCode to set
	 */
	public void setFillerManufacturerCode(String fillerManufacturerCode) {
		this.fillerManufacturerCode = fillerManufacturerCode;
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
