package com.enviroapps.eapharmics.vo.newstudy;


public class LabelPrintRequestDetailVO {

	private static final long serialVersionUID = 1L;
	private Long eaLabelRequestDetailsId;
	private Long eaLabelPrintRequestsId;
	private Long prdStudyBatchId;
	private Long prdStudyIntervalId;
	private int labelNumber;
	private Boolean extraLabel;
	
	/**
	 * @return the eaLabelRequestDetailsId
	 */
	public Long getEaLabelRequestDetailsId() {
		return eaLabelRequestDetailsId;
	}
	/**
	 * @param eaLabelRequestDetailsId the eaLabelRequestDetailsId to set
	 */
	public void setEaLabelRequestDetailsId(Long eaLabelRequestDetailsId) {
		this.eaLabelRequestDetailsId = eaLabelRequestDetailsId;
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
}
