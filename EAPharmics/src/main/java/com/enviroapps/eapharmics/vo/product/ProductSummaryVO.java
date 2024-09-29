/**
 * 
 */
package com.enviroapps.eapharmics.vo.product;

import java.util.Date;



/**
 * @author EnviroApps
 *
 */
public class ProductSummaryVO {
	private Long productId;
	private String productCode;
	private String productName;
	private String productDescription;
	private boolean active;
	private boolean approved;
	private String expirationPeriod;
	private String productType;
	private Date annualReportAnniversaryDate;
	private boolean readyForApproval;
	private boolean approvalLevel1;
	private boolean approvalLevel2;
	
	/**
	 * @return the productId
	 */
	public Long getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
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
	 * @return the active
	 */
	public boolean getActive() {
		return active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	/**
	 * @return the approved
	 */
	public boolean getApproved() {
		return approved;
	}
	/**
	 * @param approved the approved to set
	 */
	public void setApproved(boolean authorized) {
		this.approved = authorized;
	}
	/**
	 * @return the expirationPeriod
	 */
	public String getExpirationPeriod() {
		return expirationPeriod;
	}
	/**
	 * @param expirationPeriod the expirationPeriod to set
	 */
	public void setExpirationPeriod(String expirationPeriod) {
		this.expirationPeriod = expirationPeriod;
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
	 * @return the annualReportAnniversaryDate
	 */
	public Date getAnnualReportAnniversaryDate() {
		return annualReportAnniversaryDate;
	}
	/**
	 * @param annualReportAnniversaryDate the annualReportAnniversaryDate to set
	 */
	public void setAnnualReportAnniversaryDate(Date annualReportAnniversaryDate) {
		this.annualReportAnniversaryDate = annualReportAnniversaryDate;
	}
	/**
	 * @return the readyForApproval
	 */
	public boolean isReadyForApproval() {
		return readyForApproval;
	}
	/**
	 * @param readyForApproval the readyForApproval to set
	 */
	public void setReadyForApproval(boolean readyForApproval) {
		this.readyForApproval = readyForApproval;
	}
	/**
	 * @return the approvalLevel1
	 */
	public boolean isApprovalLevel1() {
		return approvalLevel1;
	}
	/**
	 * @param approvalLevel1 the approvalLevel1 to set
	 */
	public void setApprovalLevel1(boolean approvalLevel1) {
		this.approvalLevel1 = approvalLevel1;
	}
	/**
	 * @return the approvalLevel2
	 */
	public boolean isApprovalLevel2() {
		return approvalLevel2;
	}
	/**
	 * @param approvalLevel2 the approvalLevel2 to set
	 */
	public void setApprovalLevel2(boolean approvalLevel2) {
		this.approvalLevel2 = approvalLevel2;
	}

}
