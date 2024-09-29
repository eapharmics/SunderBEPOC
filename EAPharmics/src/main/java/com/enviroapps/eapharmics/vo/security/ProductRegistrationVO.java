/**
 * 
 */
package com.enviroapps.eapharmics.vo.security;


/**
 * @author EnviroApps
 *
 */
public class ProductRegistrationVO {

	private static final long serialVersionUID = 1L;

	private Long   licenseId;
	private String licenseKey;
	private String licenseString;
	private String licenseInfo;
	
	/**
	 * 
	 * @param licenseId
	 */
	public void setLicenseId(Long licenseId) {
		this.licenseId = licenseId;
	}
	
	/**
	 * 
	 * @return the license Id
	 */
	public Long getLicenseId() {
		return licenseId;
	}
	/**
	 * 
	 * @param licenseKey
	 */
	public void setLicenseKey(String licenseKey) {
		this.licenseKey = licenseKey;
	}
	/**
	 * 
	 * @return the license key
	 */
	public String getLicenseKey() {
		return licenseKey;
	}
	/**
	 * 
	 * @param the license string
	 */
	public void setLicenseString(String licenseString) {
		this.licenseString = licenseString;
	}
	/**
	 * 
	 * @return String
	 */
	public String getLicenseString() {
		return licenseString;
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
