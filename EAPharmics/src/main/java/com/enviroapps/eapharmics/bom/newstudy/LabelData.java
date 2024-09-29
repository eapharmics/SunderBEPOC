/**
 * 
 */
package com.enviroapps.eapharmics.bom.newstudy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.enviroapps.eapharmics.common.Utility;

/**
 * @author EnviroApps
 * 
 */
public class LabelData {
	private static final long serialVersionUID = 1L;

	private String labelDataKey;
	private Long eaLabelPrintRequestsId;
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
	private String manufacturingSite;
	private String packagingSite;
	private Date manufacturingDate;
	private Date packagingDate;
	private Date initialDate;
	private String fill;
	private String container;
	private String closure;
	private String reason;
	private String clientName;
	private String SSPNumber;
	private int intervalUnits;
	private int intervalValue;
	private Date intervalDate;
	private String barCode;
	private String intervalCount;
	private Long labelId;
	private String intervalLabel;
	private String twoDBarCode;
	private int labelCount;
	private int extraLabelCount;
	private Long locationId;
	private boolean isExtra;
	private Boolean labelPrinted;
	private int labelNumber;
	private Boolean extraLabel;
	
	/* (non-Javadoc)
	 * @see com.enviroapps.das.persistence.AbstractPersistableObject#getPK()
	 */
	public Serializable getPK() {
		return labelDataKey;
	}
	/* (non-Javadoc)
	 * @see com.enviroapps.das.persistence.AbstractPersistableObject#loadPK(java.lang.Object[])
	 */
	public void loadPK(Object[] pks) {
		labelDataKey = (String)pks[0];
	}
	/**
	 * @return the labelDataKey
	 */
	public String getLabelDataKey() {
		return labelDataKey;
	}
	/**
	 * @param key the labelDataKey to set
	 */
	public void setLabelDataKey(String pKey) {
		this.labelDataKey = pKey;
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
	 * @return the iD
	 */
	public String getStudyId() {
		return studyId;
	}
	/**
	 * @param id the iD to set
	 */
	public void setStudyId(String id) {
		studyId = id;
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
	public void setStudyCondition(String condition) {
		this.studyCondition = condition;
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
	 * @return the manufacturingSite
	 */
	public String getManufacturingSite() {
		return manufacturingSite;
	}
	/**
	 * @param manufacturingSite the manufacturingSite to set
	 */
	public void setManufacturingSite(String manufacturingSite) {
		this.manufacturingSite = manufacturingSite;
	}
	/**
	 * @return the packagingSite
	 */
	public String getPackagingSite() {
		return packagingSite;
	}
	/**
	 * @param packagingSite the packagingSite to set
	 */
	public void setPackagingSite(String packagingSite) {
		this.packagingSite = packagingSite;
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
	 * @return the packagingDate
	 */
	public Date getPackagingDate() {
		return packagingDate;
	}
	/**
	 * @param packagingDate the packagingDate to set
	 */
	public void setPackagingDate(Date packagingDate) {
		this.packagingDate = packagingDate;
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
	 * @return the container
	 */
	public String getContainer() {
		return container;
	}
	/**
	 * @param container the container to set
	 */
	public void setContainer(String container) {
		this.container = container;
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
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}
	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	/**
	 * @return the clientName
	 */
	public String getClientName() {
		return clientName;
	}
	/**
	 * @param clientName the clientName to set
	 */
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	/**
	 * @return the sSPNumber
	 */
	public String getSSPNumber() {
		return SSPNumber;
	}
	/**
	 * @param number the sSPNumber to set
	 */
	public void setSSPNumber(String number) {
		SSPNumber = number;
	}
	/**
	 * @return the intervalUnits
	 */
	public int getIntervalUnits() {
		return intervalUnits;
	}
	/**
	 * @param intervalUnits the intervalUnits to set
	 */
	public void setIntervalUnits(int intervalUnits) {
		this.intervalUnits = intervalUnits;
	}
	/**
	 * @return the intervalValue
	 */
	public int getIntervalValue() {
		return intervalValue;
	}
	/**
	 * @param intervalValue the intervalValue to set
	 */
	public void setIntervalValue(int intervalValue) {
		this.intervalValue = intervalValue;
	}
	/**
	 * @return the intervalDate
	 */
	public Date getIntervalDate() {
		return intervalDate;
	}
	/**
	 * @param intervalDate the intervalDate to set
	 */
	public void setIntervalDate(Date intervalDate) {
		this.intervalDate = intervalDate;
	}
	/**
	 * @return the barCode
	 */
	public String getBarCode() {
		return barCode;
	}
	/**
	 * @param barCode the barCode to set
	 */
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	/**
	 * @return the intervalCount
	 */
	public String getIntervalCount() {
		return intervalCount;
	}
	/**
	 * @param intervalCount the intervalCount to set
	 */
	public void setIntervalCount(String intervalCount) {
		this.intervalCount = intervalCount;
	}

	/**
	 * @return the labelId
	 */
	public Long getLabelId() {
		return labelId;
	}
	/**
	 * @param labelId the labelId to set
	 */
	public void setLabelId(Long labelId) {
		this.labelId = labelId;
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
	 * @return the twoDBarCode
	 */
	public String getTwoDBarCode() {
		return twoDBarCode;
	}
	/**
	 * @param twoDBarCode the twoDBarCode to set
	 */
	public void setTwoDBarCode(String twoDBarCode) {
		this.twoDBarCode = twoDBarCode;
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
	
	/**
	 * @return the labelCount
	 */
	public int getLabelCount() {
		return labelCount;
	}
	/**
	 * @param LabelCount the LabelCount to set
	 */
	public void setLabelCount(int labelCount) {
		this.labelCount =labelCount;
	}
	
	/**
	 * @return the isExtra
	 */
	public boolean isExtra() {
		return isExtra;
	}
	/**
	 * @param isExtra the isExtra to set
	 */
	public void setExtra(boolean isExtra) {
		this.isExtra = isExtra;
	}

	/**
	 * @param printExtraLabels
	 * @return
	 */
	public List<LabelData> createLabelDataList(boolean printExtraLabels, String serverUrl) {
		List<LabelData> list = new ArrayList<LabelData>();
		if (this.labelNumber != 0) {
			LabelData l1 = populateLabelData(this.labelNumber-1, this.extraLabel, serverUrl);
			list.add(l1);
			return list;
		}
		if (printExtraLabels) {
			for (int i=0; i<this.getExtraLabelCount(); i++) {
				LabelData l1 = populateLabelData(i, true, serverUrl);
				list.add(l1);
			}
		}
		for (int i=0; i<this.getLabelCount(); i++) {
			LabelData l1 = populateLabelData(i, false, serverUrl);
			list.add(l1);
		}			
		return list;
	}
	/**
	 * @param i
	 * @return
	 */
	private LabelData populateLabelData(int i, boolean isExtraLabel, String serverUrl) {
		LabelData l1 = new LabelData();
		l1.setExtra(isExtraLabel);
		l1.setBatchSize(Utility.ensureDefaultSpace(this.getBatchSize()));
		l1.setClientName(Utility.ensureDefaultSpace(this.getClientName()));
		l1.setClosure(Utility.ensureDefaultSpace(this.getClosure()));
		l1.setStudyCondition(Utility.ensureDefaultSpace(this.getStudyCondition()));
		l1.setContainer(Utility.ensureDefaultSpace(this.getContainer()));
		l1.setFill(Utility.ensureDefaultSpace(this.getFill()));
		l1.setFormula(Utility.ensureDefaultSpace(this.getFormula()));
		l1.setStudyId(Utility.ensureDefaultSpace(this.getStudyId()));
		l1.setInitialDate(this.getInitialDate());
		l1.setIntervalDate(this.getIntervalDate());
		l1.setIntervalUnits(this.getIntervalUnits());
		l1.setIntervalValue(this.getIntervalValue());
		l1.setLotNumber(Utility.ensureDefaultSpace(this.getLotNumber()));
		l1.setManufacturingDate(this.getManufacturingDate());
		l1.setManufacturingSite(Utility.ensureDefaultSpace(this.getManufacturingSite()));
		l1.setPackagingDate(this.getPackagingDate());
		l1.setPackagingSite(Utility.ensureDefaultSpace(this.getPackagingSite()));
		l1.setLabelDataKey(Utility.ensureDefaultSpace(this.getLabelDataKey()));
		l1.setPrdStudyBatchId(this.getPrdStudyBatchId());
		l1.setPrdStudyIntervalId(this.getPrdStudyIntervalId());
		l1.setProductCode(Utility.ensureDefaultSpace(this.getProductCode()));
		l1.setProductDescription(Utility.ensureDefaultSpace(this.getProductDescription()));
		l1.setProductName(Utility.ensureDefaultSpace(this.getProductName()));
		l1.setReason(Utility.ensureDefaultSpace(this.getReason()));
		l1.setSSPNumber(Utility.ensureDefaultSpace(this.getSSPNumber()));
		l1.setStorageLocation(Utility.ensureDefaultSpace(this.getStorageLocation()));
		l1.setIntervalLabel(Utility.ensureDefaultSpace(this.getIntervalLabel()));
		if (isExtraLabel) {
			l1.setBarCode(l1.getStudyId() + "/Extra" + 
					"/" + (i+1));
			l1.setIntervalCount((i+1) + " of " + this.getExtraLabelCount());
		} else {
			l1.setBarCode(l1.getStudyId() + "/" + 
					l1.getIntervalValue() + l1.getIntervalLabel().substring(0, 1) +
					"/" + (i+1));
			l1.setIntervalCount((i+1) + " of " + this.getIntervalUnits());
		}
		String x = "http://" + serverUrl + "/eapharmics/servlet/LabelServlet?barCode=" + l1.getBarCode();
		l1.setTwoDBarCode(x);
		return l1;
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
	 * @return the isLabelPrinted
	 */
	public Boolean getLabelPrinted() {
		return labelPrinted;
	}
	/**
	 * @param isLabelPrinted the isLabelPrinted to set
	 */
	public void setLabelPrinted(Boolean labelPrinted) {
		this.labelPrinted = labelPrinted;
	}
	/**
	 * @return the labelNumber
	 */
	public int getLabelNumber() {
		return labelNumber;
	}
	/**
	 * @param labelNumber the labelNumber to set
	 */
	public void setLabelNumber(int labelNumber) {
		this.labelNumber = labelNumber;
	}
	/**
	 * @return the extraLabel
	 */
	public Boolean getExtraLabel() {
		return extraLabel;
	}
	/**
	 * @param extraLabel the extraLabel to set
	 */
	public void setExtraLabel(Boolean extraLabel) {
		this.extraLabel = extraLabel;
	}
	/**
	 * @return the eaLabelPrintRequestsId
	 */
	public Long getEaLabelPrintRequestsId() {
		return eaLabelPrintRequestsId;
	}
	/**
	 * @param eaLabelPrintRequestsId the eaLabelPrintRequestsId to set
	 */
	public void setEaLabelPrintRequestsId(Long eaLabelPrintRequestsId) {
		this.eaLabelPrintRequestsId = eaLabelPrintRequestsId;
	}
}
