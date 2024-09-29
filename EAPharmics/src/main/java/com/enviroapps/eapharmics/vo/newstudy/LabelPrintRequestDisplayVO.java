package com.enviroapps.eapharmics.vo.newstudy;


public class LabelPrintRequestDisplayVO {

	private static final long serialVersionUID = 1L;

	private Long eaLabelPrintRequestsId;
	private String labelRequest;
	private String labelName;
	private Long labelId;
	private Long prdStudyBatchId;
	/**
	 * @return the labelRequest
	 */
	public String getLabelRequest() {
		return labelRequest;
	}
	/**
	 * @param labelRequest the labelRequest to set
	 */
	public void setLabelRequest(String labelRequest) {
		this.labelRequest = labelRequest;
	}
	/**
	 * @return the labelName
	 */
	public String getLabelName() {
		return labelName;
	}
	/**
	 * @param labelName the labelName to set
	 */
	public void setLabelName(String labelName) {
		this.labelName = labelName;
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
