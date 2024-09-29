package com.enviroapps.eapharmics.vo.product;


public class ProtocolsForProductVO {

	private static final long serialVersionUID = 1L;

	private Long protocolId;
	private Long protocolNumber;
	private String protocolName;

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
	 * @return the protocolNumber
	 */
	public Long getProtocolNumber() {
		return protocolNumber;
	}
	/**
	 * @param protocolNumber the protocolNumber to set
	 */
	public void setProtocolNumber(Long protocolNumber) {
		this.protocolNumber = protocolNumber;
	}
	/**
	 * @return the protocolName
	 */
	public String getProtocolName() {
		return protocolName;
	}
	/**
	 * @param protocolName the protocolName to set
	 */
	public void setProtocolName(String protocolName) {
		this.protocolName = protocolName;
	}
}
