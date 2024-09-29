/**
 * 
 */
package com.enviroapps.eapharmics.vo.newstudy;

/**
 * @author EAPharmics
 *
 */
public class RegressionInputVO {

	private Double lowerLimit;
	private Double upperLimit;
	private String batchId;
	private int interval;
	private Double result;
	
	/**
	 * 
	 */
	public RegressionInputVO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param lowerLimit
	 * @param upperLimit
	 * @param batchId
	 * @param interval
	 * @param result
	 */
	public RegressionInputVO(Double lowerLimit, Double upperLimit,
			String batchId, int interval, Double result) {
		this.batchId = batchId;
		this.lowerLimit = lowerLimit;
		this.upperLimit = upperLimit;
		this.batchId = batchId;
		this.interval = interval;
		this.result = result;
	}
	
	/**
	 * @return the lowerLimit
	 */
	public Double getLowerLimit() {
		return lowerLimit;
	}

	/**
	 * @param lowerLimit the lowerLimit to set
	 */
	public void setLowerLimit(Double lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	/**
	 * @return the upperLimit
	 */
	public Double getUpperLimit() {
		return upperLimit;
	}

	/**
	 * @param upperLimit the upperLimit to set
	 */
	public void setUpperLimit(Double upperLimit) {
		this.upperLimit = upperLimit;
	}

	/**
	 * @return the batchId
	 */
	public String getBatchId() {
		return batchId;
	}

	/**
	 * @param batchId the batchId to set
	 */
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	/**
	 * @return the interval
	 */
	public int getInterval() {
		return interval;
	}

	/**
	 * @param interval the interval to set
	 */
	public void setInterval(int interval) {
		this.interval = interval;
	}

	/**
	 * @return the result
	 */
	public Double getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(Double result) {
		this.result = result;
	}

}
