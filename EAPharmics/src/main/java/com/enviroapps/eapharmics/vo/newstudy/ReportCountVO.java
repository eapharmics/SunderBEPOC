package com.enviroapps.eapharmics.vo.newstudy;


public class ReportCountVO {

	private static final long serialVersionUID = 1L;
	
	private int pendingCount;
	private int notStartedCount;
	private int notCompletedCount;
	/**
	 * @return the pendingCount
	 */
	public int getPendingCount() {
		return pendingCount;
	}
	/**
	 * @param pendingCount the pendingCount to set
	 */
	public void setPendingCount(int pendingCount) {
		this.pendingCount = pendingCount;
	}
	/**
	 * @return the notStartedCount
	 */
	public int getNotStartedCount() {
		return notStartedCount;
	}
	/**
	 * @param notStartedCount the notStartedCount to set
	 */
	public void setNotStartedCount(int notStartedCount) {
		this.notStartedCount = notStartedCount;
	}
	/**
	 * @return the notCompletedCount
	 */
	public int getNotCompletedCount() {
		return notCompletedCount;
	}
	/**
	 * @param notCompletedCount the notCompletedCount to set
	 */
	public void setNotCompletedCount(int notCompletedCount) {
		this.notCompletedCount = notCompletedCount;
	}
}
