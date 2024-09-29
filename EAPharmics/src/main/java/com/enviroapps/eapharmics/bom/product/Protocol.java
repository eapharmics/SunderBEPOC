package com.enviroapps.eapharmics.bom.product;

import java.io.Serializable;
import java.util.Date;

public class Protocol {

	private static final long serialVersionUID = 1L;

	private Long protocolId;
	private Long productId;
	private Long protocolNumber;
	private String protocolName;
	private String intervalUnits;
	private Long testIntervalValue1;
	private Long testIntervalValue2;
	private Long testIntervalValue3;
	private Long testIntervalValue4;
	private Long testIntervalValue5;
	private Long testIntervalValue6;
	private Long testIntervalValue7;
	private Long testIntervalValue8;
	private Long testIntervalValue9;
	private Long testIntervalValue10;
	private Long testIntervalValue11;
	private Long testIntervalValue12;
	private Long testIntervalValue13;
	private Long testIntervalValue14;
	private Long testIntervalValue15;
	private Long testIntervalValue16;
	private Long testIntervalValue17;
	private Long testIntervalValue18;
	private Long testIntervalValue19;
	private Long testIntervalValue20;
	private Date insertDate;
	private Long insertUser;
	private Date updateDate;
	private Long updateUser;
	private Long auditId;
	private String comments;
	private Product product;
	/* (non-Javadoc)
	 * @see com.enviroapps.das.persistence.AbstractPersistableObject#getPK()
	 */
	public Serializable getPK() {
		return protocolId;
	}
	/* (non-Javadoc)
	 * @see com.enviroapps.das.persistence.AbstractPersistableObject#loadPK(java.lang.Object[])
	 */
	public void loadPK(Object[] pks) {
		protocolId = (Long)pks[0];
	}
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
	/**
	 * @return the intervalUnits
	 */
	public String getIntervalUnits() {
		return intervalUnits;
	}
	/**
	 * @param intervalUnits the intervalUnits to set
	 */
	public void setIntervalUnits(String intervalUnits) {
		this.intervalUnits = intervalUnits;
	}
	/**
	 * @return the insertDate
	 */
	public Date getInsertDate() {
		return insertDate;
	}
	/**
	 * @param insertDate the insertDate to set
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
	 * @param insertUser the insertUser to set
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
	 * @param updateDate the updateDate to set
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
	 * @param updateUser the updateUser to set
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
	 * @param auditId the auditId to set
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
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}
	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
	/**
	 * @return the testIntervalValue1
	 */
	public Long getTestIntervalValue1() {
		return testIntervalValue1;
	}
	/**
	 * @param testIntervalValue1 the testIntervalValue1 to set
	 */
	public void setTestIntervalValue1(Long testIntervalValue1) {
		this.testIntervalValue1 = testIntervalValue1;
	}
	/**
	 * @return the testIntervalValue2
	 */
	public Long getTestIntervalValue2() {
		return testIntervalValue2;
	}
	/**
	 * @param testIntervalValue2 the testIntervalValue2 to set
	 */
	public void setTestIntervalValue2(Long testIntervalValue2) {
		this.testIntervalValue2 = testIntervalValue2;
	}
	/**
	 * @return the testIntervalValue3
	 */
	public Long getTestIntervalValue3() {
		return testIntervalValue3;
	}
	/**
	 * @param testIntervalValue3 the testIntervalValue3 to set
	 */
	public void setTestIntervalValue3(Long testIntervalValue3) {
		this.testIntervalValue3 = testIntervalValue3;
	}
	/**
	 * @return the testIntervalValue4
	 */
	public Long getTestIntervalValue4() {
		return testIntervalValue4;
	}
	/**
	 * @param testIntervalValue4 the testIntervalValue4 to set
	 */
	public void setTestIntervalValue4(Long testIntervalValue4) {
		this.testIntervalValue4 = testIntervalValue4;
	}
	/**
	 * @return the testIntervalValue5
	 */
	public Long getTestIntervalValue5() {
		return testIntervalValue5;
	}
	/**
	 * @param testIntervalValue5 the testIntervalValue5 to set
	 */
	public void setTestIntervalValue5(Long testIntervalValue5) {
		this.testIntervalValue5 = testIntervalValue5;
	}
	/**
	 * @return the testIntervalValue6
	 */
	public Long getTestIntervalValue6() {
		return testIntervalValue6;
	}
	/**
	 * @param testIntervalValue6 the testIntervalValue6 to set
	 */
	public void setTestIntervalValue6(Long testIntervalValue6) {
		this.testIntervalValue6 = testIntervalValue6;
	}
	/**
	 * @return the testIntervalValue7
	 */
	public Long getTestIntervalValue7() {
		return testIntervalValue7;
	}
	/**
	 * @param testIntervalValue7 the testIntervalValue7 to set
	 */
	public void setTestIntervalValue7(Long testIntervalValue7) {
		this.testIntervalValue7 = testIntervalValue7;
	}
	/**
	 * @return the testIntervalValue8
	 */
	public Long getTestIntervalValue8() {
		return testIntervalValue8;
	}
	/**
	 * @param testIntervalValue8 the testIntervalValue8 to set
	 */
	public void setTestIntervalValue8(Long testIntervalValue8) {
		this.testIntervalValue8 = testIntervalValue8;
	}
	/**
	 * @return the testIntervalValue9
	 */
	public Long getTestIntervalValue9() {
		return testIntervalValue9;
	}
	/**
	 * @param testIntervalValue9 the testIntervalValue9 to set
	 */
	public void setTestIntervalValue9(Long testIntervalValue9) {
		this.testIntervalValue9 = testIntervalValue9;
	}
	/**
	 * @return the testIntervalValue10
	 */
	public Long getTestIntervalValue10() {
		return testIntervalValue10;
	}
	/**
	 * @param testIntervalValue10 the testIntervalValue10 to set
	 */
	public void setTestIntervalValue10(Long testIntervalValue10) {
		this.testIntervalValue10 = testIntervalValue10;
	}
	/**
	 * @return the testIntervalValue11
	 */
	public Long getTestIntervalValue11() {
		return testIntervalValue11;
	}
	/**
	 * @param testIntervalValue11 the testIntervalValue11 to set
	 */
	public void setTestIntervalValue11(Long testIntervalValue11) {
		this.testIntervalValue11 = testIntervalValue11;
	}
	/**
	 * @return the testIntervalValue12
	 */
	public Long getTestIntervalValue12() {
		return testIntervalValue12;
	}
	/**
	 * @param testIntervalValue12 the testIntervalValue12 to set
	 */
	public void setTestIntervalValue12(Long testIntervalValue12) {
		this.testIntervalValue12 = testIntervalValue12;
	}
	/**
	 * @return the testIntervalValue13
	 */
	public Long getTestIntervalValue13() {
		return testIntervalValue13;
	}
	/**
	 * @param testIntervalValue13 the testIntervalValue13 to set
	 */
	public void setTestIntervalValue13(Long testIntervalValue13) {
		this.testIntervalValue13 = testIntervalValue13;
	}
	/**
	 * @return the testIntervalValue14
	 */
	public Long getTestIntervalValue14() {
		return testIntervalValue14;
	}
	/**
	 * @param testIntervalValue14 the testIntervalValue14 to set
	 */
	public void setTestIntervalValue14(Long testIntervalValue14) {
		this.testIntervalValue14 = testIntervalValue14;
	}
	/**
	 * @return the testIntervalValue15
	 */
	public Long getTestIntervalValue15() {
		return testIntervalValue15;
	}
	/**
	 * @param testIntervalValue15 the testIntervalValue15 to set
	 */
	public void setTestIntervalValue15(Long testIntervalValue15) {
		this.testIntervalValue15 = testIntervalValue15;
	}
	/**
	 * @return the testIntervalValue16
	 */
	public Long getTestIntervalValue16() {
		return testIntervalValue16;
	}
	/**
	 * @param testIntervalValue16 the testIntervalValue16 to set
	 */
	public void setTestIntervalValue16(Long testIntervalValue16) {
		this.testIntervalValue16 = testIntervalValue16;
	}
	/**
	 * @return the testIntervalValue17
	 */
	public Long getTestIntervalValue17() {
		return testIntervalValue17;
	}
	/**
	 * @param testIntervalValue17 the testIntervalValue17 to set
	 */
	public void setTestIntervalValue17(Long testIntervalValue17) {
		this.testIntervalValue17 = testIntervalValue17;
	}
	/**
	 * @return the testIntervalValue18
	 */
	public Long getTestIntervalValue18() {
		return testIntervalValue18;
	}
	/**
	 * @param testIntervalValue18 the testIntervalValue18 to set
	 */
	public void setTestIntervalValue18(Long testIntervalValue18) {
		this.testIntervalValue18 = testIntervalValue18;
	}
	/**
	 * @return the testIntervalValue19
	 */
	public Long getTestIntervalValue19() {
		return testIntervalValue19;
	}
	/**
	 * @param testIntervalValue19 the testIntervalValue19 to set
	 */
	public void setTestIntervalValue19(Long testIntervalValue19) {
		this.testIntervalValue19 = testIntervalValue19;
	}
	/**
	 * @return the testIntervalValue20
	 */
	public Long getTestIntervalValue20() {
		return testIntervalValue20;
	}
	/**
	 * @param testIntervalValue20 the testIntervalValue20 to set
	 */
	public void setTestIntervalValue20(Long testIntervalValue20) {
		this.testIntervalValue20 = testIntervalValue20;
	}
}
