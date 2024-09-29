package com.enviroapps.eapharmics.bom.security;

import java.io.Serializable;

public class ProductRegistration {

	private static final long serialVersionUID = 1L;

	private Long   licenseId;
	private String licenseKey;
	private String licenseString;
	private String licenseInfo;

	/* (non-Javadoc)
	 * @see com.enviroapps.das.persistence.AbstractPersistableObject#getPK()
	 */
	public Serializable getPK() {
		return licenseId;
	}
	/* (non-Javadoc)
	 * @see com.enviroapps.das.persistence.AbstractPersistableObject#loadPK(java.lang.Object[])
	 */
	public void loadPK(Object[] pks) {
		this.licenseId = (Long)pks[0];
	}
	/**
	 * @return the licenseId
	 */
	public Long getLicenseId() {
		return licenseId;
	}
	/**
	 * @param licenseId the licenseId to set
	 */
	public void setLicenseId(Long licenseId) {
		this.licenseId = licenseId;
	}
	/**
	 * @return the licenseKey
	 */
	public String getLicenseKey() {
		return licenseKey;
	}
	/**
	 * @param licenseKey the licenseKey to set
	 */
	public void setLicenseKey(String licenseKey) {
		this.licenseKey = licenseKey;
	}
	/**
	 * @return the licenseString
	 */
	public String getLicenseString() {
		return licenseString;
	}
	/**
	 * @param licenseString the licenseString to set
	 */
	public void setLicenseString(String licenseString) {
		this.licenseString = licenseString;
	}

	/**
	 * @return the licenseInfo
	 */
	public String getLicenseInfo() {
		return licenseInfo;
	}
	/**
	 * @param licenseInfo the licenseInfo to set
	 */
	public void setLicenseInfo(String licenseInfo) {
		this.licenseInfo = licenseInfo;
	}
	
}
