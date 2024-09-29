package com.enviroapps.eapharmics.vo.admin;

public class TimezoneVO {

	private String timezoneName;
	private long offsetFromUTC;
	/**
	 * @return the timezoneName
	 */
	public String getTimezoneName() {
		return timezoneName;
	}
	/**
	 * @param timezoneName the timezoneName to set
	 */
	public void setTimezoneName(String timezoneName) {
		this.timezoneName = timezoneName;
	}
	/**
	 * @return the offsetFromUTC
	 */
	public long getOffsetFromUTC() {
		return offsetFromUTC;
	}
	/**
	 * @param offsetFromUTC the offsetFromUTC to set
	 */
	public void setOffsetFromUTC(long offsetFromUTC) {
		this.offsetFromUTC = offsetFromUTC;
	}
	
}
