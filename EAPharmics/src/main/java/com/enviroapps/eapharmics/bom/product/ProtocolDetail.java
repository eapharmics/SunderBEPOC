package com.enviroapps.eapharmics.bom.product;

import java.io.Serializable;
import java.util.Date;

public class ProtocolDetail {

	private static final long serialVersionUID = 1L;

	private Long protocolDetailId;
	private Long protocolIntervalId;
	private Long protocolId;
	private Long testId;
	private boolean testIntervalUsed1;
	private boolean testIntervalUsed2;
	private boolean testIntervalUsed3;
	private boolean testIntervalUsed4;
	private boolean testIntervalUsed5;
	private boolean testIntervalUsed6;
	private boolean testIntervalUsed7;
	private boolean testIntervalUsed8;
	private boolean testIntervalUsed9;
	private boolean testIntervalUsed10;
	private boolean testIntervalUsed11;
	private boolean testIntervalUsed12;
	private boolean testIntervalUsed13;
	private boolean testIntervalUsed14;
	private boolean testIntervalUsed15;
	private boolean testIntervalUsed16;
	private boolean testIntervalUsed17;
	private boolean testIntervalUsed18;
	private boolean testIntervalUsed19;
	private boolean testIntervalUsed20;
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
		return protocolDetailId;
	}
	/* (non-Javadoc)
	 * @see com.enviroapps.das.persistence.AbstractPersistableObject#loadPK(java.lang.Object[])
	 */
	public void loadPK(Object[] pks) {
		protocolDetailId = (Long)pks[0];
	}
	/**
	 * @return the protocolDetailId
	 */
	public Long getProtocolDetailId() {
		return protocolDetailId;
	}
	/**
	 * @param protocolDetailId the protocolDetailId to set
	 */
	public void setProtocolDetailId(Long protocolDetailId) {
		this.protocolDetailId = protocolDetailId;
	}
	/**
	 * @return the protocolIntervalId
	 */
	public Long getProtocolIntervalId() {
		return protocolIntervalId;
	}
	/**
	 * @param protocolIntervalId the protocolIntervalId to set
	 */
	public void setProtocolIntervalId(Long protocolIntervalId) {
		this.protocolIntervalId = protocolIntervalId;
	}
	/**
	 * @return the testId
	 */
	public Long getTestId() {
		return testId;
	}
	/**
	 * @param testId the testId to set
	 */
	public void setTestId(Long testId) {
		this.testId = testId;
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
	 * @return the testIntervalUsed1
	 */
	public boolean getTestIntervalUsed1() {
		return testIntervalUsed1;
	}
	/**
	 * @param testIntervalUsed1 the testIntervalUsed1 to set
	 */
	public void setTestIntervalUsed1(boolean testIntervalUsed1) {
		this.testIntervalUsed1 = testIntervalUsed1;
	}
	/**
	 * @return the testIntervalUsed2
	 */
	public boolean getTestIntervalUsed2() {
		return testIntervalUsed2;
	}
	/**
	 * @param testIntervalUsed2 the testIntervalUsed2 to set
	 */
	public void setTestIntervalUsed2(boolean testIntervalUsed2) {
		this.testIntervalUsed2 = testIntervalUsed2;
	}
	/**
	 * @return the testIntervalUsed3
	 */
	public boolean getTestIntervalUsed3() {
		return testIntervalUsed3;
	}
	/**
	 * @param testIntervalUsed3 the testIntervalUsed3 to set
	 */
	public void setTestIntervalUsed3(boolean testIntervalUsed3) {
		this.testIntervalUsed3 = testIntervalUsed3;
	}
	/**
	 * @return the testIntervalUsed4
	 */
	public boolean getTestIntervalUsed4() {
		return testIntervalUsed4;
	}
	/**
	 * @param testIntervalUsed4 the testIntervalUsed4 to set
	 */
	public void setTestIntervalUsed4(boolean testIntervalUsed4) {
		this.testIntervalUsed4 = testIntervalUsed4;
	}
	/**
	 * @return the testIntervalUsed5
	 */
	public boolean getTestIntervalUsed5() {
		return testIntervalUsed5;
	}
	/**
	 * @param testIntervalUsed5 the testIntervalUsed5 to set
	 */
	public void setTestIntervalUsed5(boolean testIntervalUsed5) {
		this.testIntervalUsed5 = testIntervalUsed5;
	}
	/**
	 * @return the testIntervalUsed6
	 */
	public boolean getTestIntervalUsed6() {
		return testIntervalUsed6;
	}
	/**
	 * @param testIntervalUsed6 the testIntervalUsed6 to set
	 */
	public void setTestIntervalUsed6(boolean testIntervalUsed6) {
		this.testIntervalUsed6 = testIntervalUsed6;
	}
	/**
	 * @return the testIntervalUsed7
	 */
	public boolean getTestIntervalUsed7() {
		return testIntervalUsed7;
	}
	/**
	 * @param testIntervalUsed7 the testIntervalUsed7 to set
	 */
	public void setTestIntervalUsed7(boolean testIntervalUsed7) {
		this.testIntervalUsed7 = testIntervalUsed7;
	}
	/**
	 * @return the testIntervalUsed8
	 */
	public boolean getTestIntervalUsed8() {
		return testIntervalUsed8;
	}
	/**
	 * @param testIntervalUsed8 the testIntervalUsed8 to set
	 */
	public void setTestIntervalUsed8(boolean testIntervalUsed8) {
		this.testIntervalUsed8 = testIntervalUsed8;
	}
	/**
	 * @return the testIntervalUsed9
	 */
	public boolean getTestIntervalUsed9() {
		return testIntervalUsed9;
	}
	/**
	 * @param testIntervalUsed9 the testIntervalUsed9 to set
	 */
	public void setTestIntervalUsed9(boolean testIntervalUsed9) {
		this.testIntervalUsed9 = testIntervalUsed9;
	}
	/**
	 * @return the testIntervalUsed10
	 */
	public boolean getTestIntervalUsed10() {
		return testIntervalUsed10;
	}
	/**
	 * @param testIntervalUsed10 the testIntervalUsed10 to set
	 */
	public void setTestIntervalUsed10(boolean testIntervalUsed10) {
		this.testIntervalUsed10 = testIntervalUsed10;
	}
	/**
	 * @return the testIntervalUsed11
	 */
	public boolean getTestIntervalUsed11() {
		return testIntervalUsed11;
	}
	/**
	 * @param testIntervalUsed11 the testIntervalUsed11 to set
	 */
	public void setTestIntervalUsed11(boolean testIntervalUsed11) {
		this.testIntervalUsed11 = testIntervalUsed11;
	}
	/**
	 * @return the testIntervalUsed12
	 */
	public boolean getTestIntervalUsed12() {
		return testIntervalUsed12;
	}
	/**
	 * @param testIntervalUsed12 the testIntervalUsed12 to set
	 */
	public void setTestIntervalUsed12(boolean testIntervalUsed12) {
		this.testIntervalUsed12 = testIntervalUsed12;
	}
	/**
	 * @return the testIntervalUsed13
	 */
	public boolean getTestIntervalUsed13() {
		return testIntervalUsed13;
	}
	/**
	 * @param testIntervalUsed13 the testIntervalUsed13 to set
	 */
	public void setTestIntervalUsed13(boolean testIntervalUsed13) {
		this.testIntervalUsed13 = testIntervalUsed13;
	}
	/**
	 * @return the testIntervalUsed14
	 */
	public boolean getTestIntervalUsed14() {
		return testIntervalUsed14;
	}
	/**
	 * @param testIntervalUsed14 the testIntervalUsed14 to set
	 */
	public void setTestIntervalUsed14(boolean testIntervalUsed14) {
		this.testIntervalUsed14 = testIntervalUsed14;
	}
	/**
	 * @return the testIntervalUsed15
	 */
	public boolean getTestIntervalUsed15() {
		return testIntervalUsed15;
	}
	/**
	 * @param testIntervalUsed15 the testIntervalUsed15 to set
	 */
	public void setTestIntervalUsed15(boolean testIntervalUsed15) {
		this.testIntervalUsed15 = testIntervalUsed15;
	}
	/**
	 * @return the testIntervalUsed16
	 */
	public boolean getTestIntervalUsed16() {
		return testIntervalUsed16;
	}
	/**
	 * @param testIntervalUsed16 the testIntervalUsed16 to set
	 */
	public void setTestIntervalUsed16(boolean testIntervalUsed16) {
		this.testIntervalUsed16 = testIntervalUsed16;
	}
	/**
	 * @return the testIntervalUsed17
	 */
	public boolean getTestIntervalUsed17() {
		return testIntervalUsed17;
	}
	/**
	 * @param testIntervalUsed17 the testIntervalUsed17 to set
	 */
	public void setTestIntervalUsed17(boolean testIntervalUsed17) {
		this.testIntervalUsed17 = testIntervalUsed17;
	}
	/**
	 * @return the testIntervalUsed18
	 */
	public boolean getTestIntervalUsed18() {
		return testIntervalUsed18;
	}
	/**
	 * @param testIntervalUsed18 the testIntervalUsed18 to set
	 */
	public void setTestIntervalUsed18(boolean testIntervalUsed18) {
		this.testIntervalUsed18 = testIntervalUsed18;
	}
	/**
	 * @return the testIntervalUsed19
	 */
	public boolean getTestIntervalUsed19() {
		return testIntervalUsed19;
	}
	/**
	 * @param testIntervalUsed19 the testIntervalUsed19 to set
	 */
	public void setTestIntervalUsed19(boolean testIntervalUsed19) {
		this.testIntervalUsed19 = testIntervalUsed19;
	}
	/**
	 * @return the testIntervalUsed20
	 */
	public boolean getTestIntervalUsed20() {
		return testIntervalUsed20;
	}
	/**
	 * @param testIntervalUsed20 the testIntervalUsed20 to set
	 */
	public void setTestIntervalUsed20(boolean testIntervalUsed20) {
		this.testIntervalUsed20 = testIntervalUsed20;
	}
}
