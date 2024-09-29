package com.enviroapps.eapharmics.vo.dataaccessevaluation;

import java.util.Date;

/**
 * @author EnviroApps
 *
 */
public class GraphDataVO {

	private static final long serialVersionUID = 1L;
	
	private String studyId;
	private String testId;
	private String testName;
	private int intervalValue;
	private String upperLimit;
	private String lowerLimit;
	private String averageResult;
	private String abbreviationResult1;
	private Boolean hide;
	private String lotNumber;
	private String studyCondition;
	private String fillUnits;
	private Double lowerBand;
	private Double upperBand;
	private Double regression;
	private String intervalText;
	private String intervalLabel;
	private String container;
	private String measurement;
	private Double intercept;
	private Double slope;
	private Double rSquare;
	private String okFlag;
	private int expiry;
	private String textLimit;
	private Long decimalPoints;
	private String packageStr;
	private String closure;
	private Date manufacturingDate;
	private Date onStabilityDate;
	private String studyReason;
	
	/**
	 * Default Constructor
	 */
	public GraphDataVO() {		
	}
	
	public String getStudyId() {
		return studyId;
	}
	public void setStudyId(String studyId) {
		this.studyId = studyId;
	}
	public String getTestId() {
		return testId;
	}
	public void setTestId(String testId) {
		this.testId = testId;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public int getIntervalValue() {
		return intervalValue;
	}
	public void setIntervalValue(int intervalValue) {
		this.intervalValue = intervalValue;
	}
	public String getUpperLimit() {
		return upperLimit;
	}
	public void setUpperLimit(String upperLimit) {
		this.upperLimit = upperLimit;
	}
	public String getLowerLimit() {
		return lowerLimit;
	}
	public void setLowerLimit(String lowerLimit) {
		this.lowerLimit = lowerLimit;
	}
	public String getAverageResult() {
		return averageResult;
	}
	public void setAverageResult(String averageResult) {
		this.averageResult = averageResult;
	}
	public String getAbbreviationResult1() {
		return abbreviationResult1;
	}
	public void setAbbreviationResult1(String abbreviationResult1) {
		this.abbreviationResult1 = abbreviationResult1;
	}
	public Boolean getHide() {
		return hide;
	}
	public void setHide(Boolean hide) {
		this.hide = hide;
	}
	public String getLotNumber() {
		return lotNumber;
	}
	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}
	public String getStudyCondition() {
		return studyCondition;
	}
	public void setStudyCondition(String studyCondition) {
		this.studyCondition = studyCondition;
	}
	public String getFillUnits() {
		return fillUnits;
	}
	public void setFillUnits(String fillUnits) {
		this.fillUnits = fillUnits;
	}
	public Double getLowerBand() {
		return lowerBand;
	}
	public void setLowerBand(Double lowerBand) {
		this.lowerBand = lowerBand;
	}
	public Double getUpperBand() {
		return upperBand;
	}
	public void setUpperBand(Double upperBand) {
		this.upperBand = upperBand;
	}
	public Double getRegression() {
		return regression;
	}
	public void setRegression(Double regression) {
		this.regression = regression;
	}

	public String getIntervalText() {
		return intervalText;
	}

	public void setIntervalText(String intervalText) {
		this.intervalText = intervalText;
	}

	public String getIntervalLabel() {
		return intervalLabel;
	}

	public void setIntervalLabel(String intervalLabel) {
		this.intervalLabel = intervalLabel;
	}

	public String getContainer() {
		return container;
	}

	public void setContainer(String container) {
		this.container = container;
	}

	public String getMeasurement() {
		return measurement;
	}

	public void setMeasurement(String measurement) {
		this.measurement = measurement;
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
	 * @return the expiry
	 */
	public int getExpiry() {
		return expiry;
	}

	/**
	 * @param expiry the expiry to set
	 */
	public void setExpiry(int expiry) {
		this.expiry = expiry;
	}

	/**
	 * @return the testText
	 */
	public String getTextLimit() {
		return textLimit;
	}

	/**
	 * @param textLimit the testText to set
	 */
	public void setTextLimit(String textLimit) {
		this.textLimit = textLimit;
	}

	/**
	 * @return the decimalPoints
	 */
	public Long getDecimalPoints() {
		return decimalPoints;
	}

	/**
	 * @param decimalPoints the decimalPoints to set
	 */
	public void setDecimalPoints(Long decimalPoints) {
		this.decimalPoints = decimalPoints;
	}

	/**
	 * @return the packageStr
	 */
	public String getPackageStr() {
		return packageStr;
	}

	/**
	 * @param packageStr the packageStr to set
	 */
	public void setPackageStr(String packageStr) {
		this.packageStr = packageStr;
	}

	/**
	 * @return the closure
	 */
	public String getClosure() {
		return closure;
	}

	/**
	 * @param closure the closure to set
	 */
	public void setClosure(String closure) {
		this.closure = closure;
	}

	/**
	 * @return the manufacturingDate
	 */
	public Date getManufacturingDate() {
		return manufacturingDate;
	}

	/**
	 * @param manufacturingDate the manufacturingDate to set
	 */
	public void setManufacturingDate(Date manufacturingDate) {
		this.manufacturingDate = manufacturingDate;
	}

	/**
	 * @return the onStabilityDate
	 */
	public Date getOnStabilityDate() {
		return onStabilityDate;
	}

	/**
	 * @param onStabilityDate the onStabilityDate to set
	 */
	public void setOnStabilityDate(Date onStabilityDate) {
		this.onStabilityDate = onStabilityDate;
	}

	/**
	 * @return the studyReason
	 */
	public String getStudyReason() {
		return studyReason;
	}

	/**
	 * @param studyReason the studyReason to set
	 */
	public void setStudyReason(String studyReason) {
		this.studyReason = studyReason;
	}

}

