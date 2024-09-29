/**
 * 
 */
package com.enviroapps.eapharmics.vo.newstudy;

/**
 * @author EAPharmics
 *
 */
public class RegressionDataVO {

	private int interval;
	private Double lowerBand;
	private Double regression;
	private Double upperBand;
	private Double data;
	private Double lowerLimit;
	private Double upperLimit;
	private String okFlag;
	private Double intercept;
	private Double slope;
	private Double rSquare;
	private Double pSlope;
	private Double pIntercept;
	
	/**
	 * 
	 */
	public RegressionDataVO() {
		// TODO Auto-generated constructor stub
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
	 * @return the lowerBand
	 */
	public Double getLowerBand() {
		return lowerBand;
	}

	/**
	 * @param lowerBand the lowerBand to set
	 */
	public void setLowerBand(Double lowerBand) {
		this.lowerBand = lowerBand;
	}

	/**
	 * @return the regression
	 */
	public Double getRegression() {
		return regression;
	}

	/**
	 * @param regression the regression to set
	 */
	public void setRegression(Double regression) {
		this.regression = regression;
	}

	/**
	 * @return the upperBand
	 */
	public Double getUpperBand() {
		return upperBand;
	}

	/**
	 * @param upperBand the upperBand to set
	 */
	public void setUpperBand(Double upperBand) {
		this.upperBand = upperBand;
	}

	/**
	 * @return the data
	 */
	public Double getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Double data) {
		this.data = data;
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

	public String toString() {
		StringBuffer retVal = new StringBuffer("");
		retVal.append(interval + "\t");
		if (regression != null) {
			retVal.append(regression);
		}
		retVal.append("\t");
		if (lowerBand != null) {
			retVal.append(lowerBand);
		}
		retVal.append("\t");
		if (upperBand != null) {
			retVal.append(upperBand);
		}
		retVal.append("\t");
		if (data != null) {
			retVal.append(data);
		}
		retVal.append("\t");
		if (lowerLimit != null) {
			retVal.append(lowerLimit);
		}
		retVal.append("\t");
		if (upperLimit != null) {
			retVal.append(upperLimit);
		}

		return retVal.toString();
	}

	/**
	 * @return the okFlag
	 */
	public String getOkFlag() {
		return okFlag;
	}

	/**
	 * @param okFlag the okFlag to set
	 */
	public void setOkFlag(String okFlag) {
		this.okFlag = okFlag;
	}

	/**
	 * @return the intercept
	 */
	public Double getIntercept() {
		return intercept;
	}

	/**
	 * @param intercept the intercept to set
	 */
	public void setIntercept(Double intercept) {
		this.intercept = intercept;
	}

	/**
	 * @return the slope
	 */
	public Double getSlope() {
		return slope;
	}

	/**
	 * @param slope the slope to set
	 */
	public void setSlope(Double slope) {
		this.slope = slope;
	}

	/**
	 * @return the rSquare
	 */
	public Double getRSquare() {
		return rSquare;
	}

	/**
	 * @param square the rSquare to set
	 */
	public void setRSquare(Double square) {
		rSquare = square;
	}

	/**
	 * @return
	 */
	public void calculateOkFlag() {
		okFlag = "*";
		if (upperLimit != null && lowerLimit != null) {
			if (getLowerBand().doubleValue() >= lowerLimit.doubleValue() &&
					getUpperBand().doubleValue() <= upperLimit.doubleValue()) {
				okFlag = "ok";
			}
		} else if (lowerLimit != null) {
			if (getLowerBand().doubleValue() >= lowerLimit.doubleValue()) {
				okFlag = "ok";
			}						
		} else if (upperLimit != null) {
			if (getUpperBand().doubleValue() <= upperLimit.doubleValue()) {
				okFlag = "ok";
			}						
		}
	}

	/**
	 * @return the pSlope
	 */
	public Double getPSlope() {
		return pSlope;
	}

	/**
	 * @param slope the pSlope to set
	 */
	public void setPSlope(Double slope) {
		pSlope = slope;
	}

	/**
	 * @return the pIntercept
	 */
	public Double getPIntercept() {
		return pIntercept;
	}

	/**
	 * @param intercept the pIntercept to set
	 */
	public void setPIntercept(Double intercept) {
		pIntercept = intercept;
	}

}
