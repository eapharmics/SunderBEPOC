package com.enviroapps.eapharmics.bom.newstudy;

import java.io.Serializable;

public class LabelPrintRequestDetail {

	private static final long serialVersionUID = 1L;
	private Long eaLabelRequestDetailsId;
	private Long eaLabelPrintRequestsId;
	private Long prdStudyBatchId;
	private Long prdStudyIntervalId;
	private int labelNumber;
	private Boolean extraLabel;

	/* (non-Javadoc)
	 * @see com.enviroapps.das.persistence.AbstractPersistableObject#getPK()
	 */
	public Serializable getPK() {
		return eaLabelPrintRequestsId;
	}
	/* (non-Javadoc)
	 * @see com.enviroapps.das.persistence.AbstractPersistableObject#loadPK(java.lang.Object[])
	 */
	public void loadPK(Object[] pks) {
		eaLabelPrintRequestsId = (Long)pks[0];
	}
	
	/**
	 * @return
	 */
	public Long getEaLabelPrintRequestsId() {
		return eaLabelPrintRequestsId;
	}
	/**
	 * @param eaLabelPrintRequestsId
	 */
	public void setEaLabelPrintRequestsId(Long eaLabelPrintRequestsId) {
		this.eaLabelPrintRequestsId = eaLabelPrintRequestsId;
	}
	/**
	 * @return
	 */
	public Long getPrdStudyBatchId() {
		return prdStudyBatchId;
	}
	/**
	 * @param prdStudyBatchId
	 */
	public void setPrdStudyBatchId(Long prdStudyBatchId) {
		this.prdStudyBatchId = prdStudyBatchId;
	}
	/**
	 * @return
	 */
	public Long getPrdStudyIntervalId() {
		return prdStudyIntervalId;
	}
	/**
	 * @param prdStudyIntervalId
	 */
	public void setPrdStudyIntervalId(Long prdStudyIntervalId) {
		this.prdStudyIntervalId = prdStudyIntervalId;
	}
	/**
	 * @return
	 */
	public int getLabelNumber() {
		return labelNumber;
	}
	/**
	 * @param labelNumber
	 */
	public void setLabelNumber(int labelNumber) {
		this.labelNumber = labelNumber;
	}
	/**
	 * @return
	 */
	public Boolean getExtraLabel() {
		return extraLabel;
	}
	/**
	 * @param extraLabel
	 */
	public void setExtraLabel(Boolean extraLabel) {
		this.extraLabel = extraLabel;
	}
	public Long getEaLabelRequestDetailsId() {
		return eaLabelRequestDetailsId;
	}
	public void setEaLabelRequestDetailsId(Long eaLabelRequestDetailsId) {
		this.eaLabelRequestDetailsId = eaLabelRequestDetailsId;
	}
}
