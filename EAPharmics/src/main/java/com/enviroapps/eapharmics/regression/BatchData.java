/**
 * 
 */
package com.enviroapps.eapharmics.regression;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author EAPharmics
 *
 */
public class BatchData {
	private int numberOfBatches;
	private String batchNumber;
	private int numberOfIntervals;
	private int sumOfIntervals; //precision 1
	private BigDecimal sumOfData; //precision 1
	private BigDecimal devSQIntervals;
	private BigDecimal covarIntervalData;
	private BigDecimal devSQData;
	private BigDecimal syprim;
	private int dfe; //number of intervals - 2
	private BigDecimal slope; //covarIntervalData / devSQIntervals
	private BigDecimal intercept1; //(sum of data/number of intervals) - (slope/xbar)
	private BigDecimal xbar; //sum of intervals/number of intervals
	private BigDecimal intercept2; //
	private List<Integer> intervals = new ArrayList<Integer>();
	private List<BigDecimal> data = new ArrayList<BigDecimal>();
	private Double lowerLimit;
	private Double upperLimit;	
	
	public BatchData() {
		
	}
	
	/**
	 * @return the numberOfBatches
	 */
	public int getNumberOfBatches() {
		return numberOfBatches;
	}
	/**
	 * @param numberOfBatches the numberOfBatches to set
	 */
	public void setNumberOfBatches(int numberOfBatches) {
		this.numberOfBatches = numberOfBatches;
	}
	/**
	 * @return the batchNumber
	 */
	public String getBatchNumber() {
		return batchNumber;
	}
	/**
	 * @param batchNumber the batchNumber to set
	 */
	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}
	/**
	 * @return the numberOfIntervals
	 */
	public int getNumberOfIntervals() {
		return numberOfIntervals;
	}
	/**
	 * @param numberOfIntervals the numberOfIntervals to set
	 */
	public void setNumberOfIntervals(int numberOfIntervals) {
		this.numberOfIntervals = numberOfIntervals;
	}
	/**
	 * @return the sumOfIntervals
	 */
	public int getSumOfIntervals() {
		return sumOfIntervals;
	}
	/**
	 * @param sumOfIntervals the sumOfIntervals to set
	 */
	public void setSumOfIntervals(int sumOfIntervals) {
		this.sumOfIntervals = sumOfIntervals;
	}
	/**
	 * @return the sumOfData
	 */
	public BigDecimal getSumOfData() {
		return sumOfData;
	}
	/**
	 * @param sumOfData the sumOfData to set
	 */
	public void setSumOfData(BigDecimal sumOfData) {
		this.sumOfData = sumOfData;
	}
	/**
	 * @return the devSQIntervals
	 */
	public BigDecimal getDevSQIntervals() {
		return devSQIntervals;
	}
	/**
	 * @param devSQIntervals the devSQIntervals to set
	 */
	public void setDevSQIntervals(BigDecimal devSQIntervals) {
		this.devSQIntervals = devSQIntervals;
	}
	/**
	 * @return the covarIntervalData
	 */
	public BigDecimal getCovarIntervalData() {
		return covarIntervalData;
	}
	/**
	 * @param covarIntervalData the covarIntervalData to set
	 */
	public void setCovarIntervalData(BigDecimal covarIntervalData) {
		this.covarIntervalData = covarIntervalData;
	}
	/**
	 * @return the devSQData
	 */
	public BigDecimal getDevSQData() {
		return devSQData;
	}
	/**
	 * @param devSQData the devSQData to set
	 */
	public void setDevSQData(BigDecimal devSQData) {
		this.devSQData = devSQData;
	}
	/**
	 * @return the syprim
	 */
	public BigDecimal getSyprim() {
		return syprim;
	}
	/**
	 * @param syprim the syprim to set
	 */
	public void setSyprim(BigDecimal intervalDataPrim) {
		this.syprim = intervalDataPrim;
	}
	/**
	 * @return the dfe
	 */
	public int getDfe() {
		return dfe;
	}
	/**
	 * @param dfe the dfe to set
	 */
	public void setDfe(int dfe) {
		this.dfe = dfe;
	}
	/**
	 * @return the slope
	 */
	public BigDecimal getSlope() {
		return slope;
	}
	/**
	 * @param slope the slope to set
	 */
	public void setSlope(BigDecimal slope) {
		this.slope = slope;
	}
	/**
	 * @return the intercept1
	 */
	public BigDecimal getIntercept1() {
		return intercept1;
	}
	/**
	 * @param intercept1 the intercept1 to set
	 */
	public void setIntercept1(BigDecimal intercept1) {
		this.intercept1 = intercept1;
	}
	/**
	 * @return the xbar
	 */
	public BigDecimal getXbar() {
		return xbar;
	}
	/**
	 * @param xbar the xbar to set
	 */
	public void setXbar(BigDecimal xbar) {
		this.xbar = xbar;
	}
	/**
	 * @return the intercept2
	 */
	public BigDecimal getIntercept2() {
		return intercept2;
	}
	/**
	 * @param intercept2 the intercept2 to set
	 */
	public void setIntercept2(BigDecimal intercept2) {
		this.intercept2 = intercept2;
	}

	/**
	 * @return the intervals
	 */
	public List<Integer> getIntervals() {
		return intervals;
	}

	/**
	 * @param intervals the intervals to set
	 */
	public void setIntervals(List<Integer> intervals) {
		this.intervals = intervals;
	}

	/**
	 * @return the data
	 */
	public List<BigDecimal> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(List<BigDecimal> data) {
		this.data = data;
	}

	
	/**
	 * @param interval
	 * @return
	 */
	public Double getDataForInterval(int interval) {
		int i = 0;
		for (Iterator iterator = intervals.iterator(); iterator.hasNext();) {
			Integer type = (Integer) iterator.next();
			if (type.intValue() == interval) {
				BigDecimal d = (BigDecimal) data.get(i);
				if (d == null) {
					return null;
				}
				return d.doubleValue();
			}
			i++;
		}
		return null;
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
}
