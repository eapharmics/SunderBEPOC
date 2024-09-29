package com.enviroapps.eapharmics.bom.product;

import java.io.Serializable;
import java.util.Date;

public class Inventory {

	private static final long serialVersionUID = 1L;

	private Long inventoryId;
	private Long productId;
	private Long protocolId;
	private Long fillUnits;
	private String unitOfMeasure;
	private Long total;
	private Long extra;
	private Long testIntervalInventory1;
	private Long testIntervalInventory2;
	private Long testIntervalInventory3;
	private Long testIntervalInventory4;
	private Long testIntervalInventory5;
	private Long testIntervalInventory6;
	private Long testIntervalInventory7;
	private Long testIntervalInventory8;
	private Long testIntervalInventory9;
	private Long testIntervalInventory10;
	private Long testIntervalInventory11;
	private Long testIntervalInventory12;
	private Long testIntervalInventory13;
	private Long testIntervalInventory14;
	private Long testIntervalInventory15;
	private Long testIntervalInventory16;
	private Long testIntervalInventory17;
	private Long testIntervalInventory18;
	private Long testIntervalInventory19;
	private Long testIntervalInventory20;
	private Date insertDate;
	private Long insertUser;
	private Date updateDate;
	private Long updateUser;
	private Long auditId;
	private String comments;
	/* (non-Javadoc)
	 * @see com.enviroapps.das.persistence.AbstractPersistableObject#getPK()
	 */
	public Serializable getPK() {
		return inventoryId;
	}
	/* (non-Javadoc)
	 * @see com.enviroapps.das.persistence.AbstractPersistableObject#loadPK(java.lang.Object[])
	 */
	public void loadPK(Object[] pks) {
		inventoryId = (Long)pks[0];
	}
	/**
	 * @return the inventoryId
	 */
	public Long getInventoryId() {
		return inventoryId;
	}
	/**
	 * @param inventoryId the inventoryId to set
	 */
	public void setInventoryId(Long inventoryId) {
		this.inventoryId = inventoryId;
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
	 * @return the fillUnits
	 */
	public Long getFillUnits() {
		return fillUnits;
	}
	/**
	 * @param fillUnits the fillUnits to set
	 */
	public void setFillUnits(Long fillUnits) {
		this.fillUnits = fillUnits;
	}
	/**
	 * @return the total
	 */
	public Long getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(Long total) {
		this.total = total;
	}
	/**
	 * @return the extra
	 */
	public Long getExtra() {
		return extra;
	}
	/**
	 * @param extra the extra to set
	 */
	public void setExtra(Long extra) {
		this.extra = extra;
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
	 * @return the testIntervalInventory1
	 */
	public Long getTestIntervalInventory1() {
		return testIntervalInventory1;
	}
	/**
	 * @param testIntervalInventory1 the testIntervalInventory1 to set
	 */
	public void setTestIntervalInventory1(Long testIntervalInventory1) {
		this.testIntervalInventory1 = testIntervalInventory1;
	}
	/**
	 * @return the testIntervalInventory2
	 */
	public Long getTestIntervalInventory2() {
		return testIntervalInventory2;
	}
	/**
	 * @param testIntervalInventory2 the testIntervalInventory2 to set
	 */
	public void setTestIntervalInventory2(Long testIntervalInventory2) {
		this.testIntervalInventory2 = testIntervalInventory2;
	}
	/**
	 * @return the testIntervalInventory3
	 */
	public Long getTestIntervalInventory3() {
		return testIntervalInventory3;
	}
	/**
	 * @param testIntervalInventory3 the testIntervalInventory3 to set
	 */
	public void setTestIntervalInventory3(Long testIntervalInventory3) {
		this.testIntervalInventory3 = testIntervalInventory3;
	}
	/**
	 * @return the testIntervalInventory4
	 */
	public Long getTestIntervalInventory4() {
		return testIntervalInventory4;
	}
	/**
	 * @param testIntervalInventory4 the testIntervalInventory4 to set
	 */
	public void setTestIntervalInventory4(Long testIntervalInventory4) {
		this.testIntervalInventory4 = testIntervalInventory4;
	}
	/**
	 * @return the testIntervalInventory5
	 */
	public Long getTestIntervalInventory5() {
		return testIntervalInventory5;
	}
	/**
	 * @param testIntervalInventory5 the testIntervalInventory5 to set
	 */
	public void setTestIntervalInventory5(Long testIntervalInventory5) {
		this.testIntervalInventory5 = testIntervalInventory5;
	}
	/**
	 * @return the testIntervalInventory6
	 */
	public Long getTestIntervalInventory6() {
		return testIntervalInventory6;
	}
	/**
	 * @param testIntervalInventory6 the testIntervalInventory6 to set
	 */
	public void setTestIntervalInventory6(Long testIntervalInventory6) {
		this.testIntervalInventory6 = testIntervalInventory6;
	}
	/**
	 * @return the testIntervalInventory7
	 */
	public Long getTestIntervalInventory7() {
		return testIntervalInventory7;
	}
	/**
	 * @param testIntervalInventory7 the testIntervalInventory7 to set
	 */
	public void setTestIntervalInventory7(Long testIntervalInventory7) {
		this.testIntervalInventory7 = testIntervalInventory7;
	}
	/**
	 * @return the testIntervalInventory8
	 */
	public Long getTestIntervalInventory8() {
		return testIntervalInventory8;
	}
	/**
	 * @param testIntervalInventory8 the testIntervalInventory8 to set
	 */
	public void setTestIntervalInventory8(Long testIntervalInventory8) {
		this.testIntervalInventory8 = testIntervalInventory8;
	}
	/**
	 * @return the testIntervalInventory9
	 */
	public Long getTestIntervalInventory9() {
		return testIntervalInventory9;
	}
	/**
	 * @param testIntervalInventory9 the testIntervalInventory9 to set
	 */
	public void setTestIntervalInventory9(Long testIntervalInventory9) {
		this.testIntervalInventory9 = testIntervalInventory9;
	}
	/**
	 * @return the testIntervalInventory10
	 */
	public Long getTestIntervalInventory10() {
		return testIntervalInventory10;
	}
	/**
	 * @param testIntervalInventory10 the testIntervalInventory10 to set
	 */
	public void setTestIntervalInventory10(Long testIntervalInventory10) {
		this.testIntervalInventory10 = testIntervalInventory10;
	}
	/**
	 * @return the testIntervalInventory11
	 */
	public Long getTestIntervalInventory11() {
		return testIntervalInventory11;
	}
	/**
	 * @param testIntervalInventory11 the testIntervalInventory11 to set
	 */
	public void setTestIntervalInventory11(Long testIntervalInventory11) {
		this.testIntervalInventory11 = testIntervalInventory11;
	}
	/**
	 * @return the testIntervalInventory12
	 */
	public Long getTestIntervalInventory12() {
		return testIntervalInventory12;
	}
	/**
	 * @param testIntervalInventory12 the testIntervalInventory12 to set
	 */
	public void setTestIntervalInventory12(Long testIntervalInventory12) {
		this.testIntervalInventory12 = testIntervalInventory12;
	}
	/**
	 * @return the testIntervalInventory13
	 */
	public Long getTestIntervalInventory13() {
		return testIntervalInventory13;
	}
	/**
	 * @param testIntervalInventory13 the testIntervalInventory13 to set
	 */
	public void setTestIntervalInventory13(Long testIntervalInventory13) {
		this.testIntervalInventory13 = testIntervalInventory13;
	}
	/**
	 * @return the testIntervalInventory14
	 */
	public Long getTestIntervalInventory14() {
		return testIntervalInventory14;
	}
	/**
	 * @param testIntervalInventory14 the testIntervalInventory14 to set
	 */
	public void setTestIntervalInventory14(Long testIntervalInventory14) {
		this.testIntervalInventory14 = testIntervalInventory14;
	}
	/**
	 * @return the testIntervalInventory15
	 */
	public Long getTestIntervalInventory15() {
		return testIntervalInventory15;
	}
	/**
	 * @param testIntervalInventory15 the testIntervalInventory15 to set
	 */
	public void setTestIntervalInventory15(Long testIntervalInventory15) {
		this.testIntervalInventory15 = testIntervalInventory15;
	}
	/**
	 * @return the testIntervalInventory16
	 */
	public Long getTestIntervalInventory16() {
		return testIntervalInventory16;
	}
	/**
	 * @param testIntervalInventory16 the testIntervalInventory16 to set
	 */
	public void setTestIntervalInventory16(Long testIntervalInventory16) {
		this.testIntervalInventory16 = testIntervalInventory16;
	}
	/**
	 * @return the testIntervalInventory17
	 */
	public Long getTestIntervalInventory17() {
		return testIntervalInventory17;
	}
	/**
	 * @param testIntervalInventory17 the testIntervalInventory17 to set
	 */
	public void setTestIntervalInventory17(Long testIntervalInventory17) {
		this.testIntervalInventory17 = testIntervalInventory17;
	}
	/**
	 * @return the testIntervalInventory18
	 */
	public Long getTestIntervalInventory18() {
		return testIntervalInventory18;
	}
	/**
	 * @param testIntervalInventory18 the testIntervalInventory18 to set
	 */
	public void setTestIntervalInventory18(Long testIntervalInventory18) {
		this.testIntervalInventory18 = testIntervalInventory18;
	}
	/**
	 * @return the testIntervalInventory19
	 */
	public Long getTestIntervalInventory19() {
		return testIntervalInventory19;
	}
	/**
	 * @param testIntervalInventory19 the testIntervalInventory19 to set
	 */
	public void setTestIntervalInventory19(Long testIntervalInventory19) {
		this.testIntervalInventory19 = testIntervalInventory19;
	}
	/**
	 * @return the testIntervalInventory20
	 */
	public Long getTestIntervalInventory20() {
		return testIntervalInventory20;
	}
	/**
	 * @param testIntervalInventory20 the testIntervalInventory20 to set
	 */
	public void setTestIntervalInventory20(Long testIntervalInventory20) {
		this.testIntervalInventory20 = testIntervalInventory20;
	}
	/**
	 * @return the unitOfMeasure
	 */
	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}
	/**
	 * @param unitOfMeasure the unitOfMeasure to set
	 */
	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}
}
