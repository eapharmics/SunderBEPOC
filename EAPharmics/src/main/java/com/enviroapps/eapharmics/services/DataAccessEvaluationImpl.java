package com.enviroapps.eapharmics.services;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.enviroapps.eapharmics.exception.EAPharmicsException;
import com.enviroapps.eapharmics.persistence.DataAccessEvaluationFactory;
import com.enviroapps.eapharmics.persistence.ProductFactory;
import com.enviroapps.eapharmics.vo.dataaccessevaluation.DataAccessEvaluationVO;
import com.enviroapps.eapharmics.vo.dataaccessevaluation.GraphDataVO;
import com.enviroapps.eapharmics.vo.dataaccessevaluation.RegressionReportVO;
import com.enviroapps.eapharmics.vo.newstudy.GraphGeneratorVO;
import com.enviroapps.eapharmics.vo.newstudy.RegressionDataContainerVO;
import com.enviroapps.eapharmics.vo.newstudy.RegressionDataVO;

public class DataAccessEvaluationImpl extends AbstractServiceImpl {

	private DataAccessEvaluationFactory dataaccessFactory = DataAccessEvaluationFactory.getInstance();
	private ProductFactory productFactory = ProductFactory.getInstance();
	
	
	public Long getStudyProductId(String studyId) throws EAPharmicsException {
		return dataaccessFactory.getStudyProductId(studyId);
	}
	
public List getAllDataAccessEvaluation(Long productId,ArrayList studyId,String lotNumber,String passCondition,String sendYear) throws EAPharmicsException {
		List<DataAccessEvaluationVO> list = dataaccessFactory.getAllDataAccessEvaluation(productId, studyId, lotNumber,passCondition,sendYear);
		return list;
	}

public List getNewStudyDetails(String productId)throws EAPharmicsException {
	return dataaccessFactory.getNewStudyDetails(productId);
}

public List getDataAccessEvaluationChart(ArrayList studyIds,ArrayList productTests,ArrayList productIds,Long productId,String year,String studyId,Long testId) throws EAPharmicsException {
	if (productId != null && productId != 0) {
		if (productIds == null) {
			productIds = new ArrayList();
		}
		productIds.add(productId);
	}
	if (studyId != null && studyId.length() > 0) {
		if (studyIds == null) {
			studyIds = new ArrayList();
		}
		studyIds.add(studyId);
	}
	if (testId != null && testId != 0) {
		if (productTests == null) {
			productTests = new ArrayList();
		}
		productTests.add(testId);
	}
	List<GraphGeneratorVO> list = dataaccessFactory.getDataAccessEvaluationChart(studyIds,productTests,productIds,year);
	NewStudyLoginImpl impl = new NewStudyLoginImpl();
	String prevStudyId = "";
	long prevTestId = 0;
	List<RegressionDataVO> regData = null;	
	for (Iterator<GraphGeneratorVO> iterator = list.iterator(); iterator.hasNext();) {
		GraphGeneratorVO graphGeneratorVO = (GraphGeneratorVO) iterator.next();
		if (prevStudyId.equals(graphGeneratorVO.getStudyId()) == false ||
				prevTestId == graphGeneratorVO.getTestId().longValue() == false) {
			RegressionDataContainerVO regressionDataContainerVO;
			regData = null;
			try {
				List<RegressionDataContainerVO> regList = impl
						.getSingleRegressionReportData(graphGeneratorVO
								.getStudyId(), graphGeneratorVO.getTestId());
				regressionDataContainerVO = (RegressionDataContainerVO) regList
						.get(0);
				regData = regressionDataContainerVO.getRegressionData();
			} catch (Exception e) {
				//may not have enough data for regression 
			}
			prevStudyId = graphGeneratorVO.getStudyId();
			prevTestId = graphGeneratorVO.getTestId().longValue();
		}
		if (regData != null) {
			for (Iterator<RegressionDataVO> iterator2 = regData.iterator(); iterator2.hasNext();) {
				RegressionDataVO regressionDataVO = (RegressionDataVO) iterator2
						.next();
				if (regressionDataVO.getInterval() == graphGeneratorVO.getIntervalValue().intValue()) {
					graphGeneratorVO.setLowerBand(regressionDataVO.getLowerBand());
					graphGeneratorVO.setUpperBand(regressionDataVO.getUpperBand());
					graphGeneratorVO.setRegression(regressionDataVO.getRegression());
					graphGeneratorVO.setIntercept(regressionDataVO.getIntercept());
					graphGeneratorVO.setRSquare(regressionDataVO.getRSquare());
					graphGeneratorVO.setSlope(regressionDataVO.getSlope());
					graphGeneratorVO.setOkFlag(regressionDataVO.getOkFlag());
				}
				if ("ok".equalsIgnoreCase(regressionDataVO.getOkFlag())) {
					graphGeneratorVO.setExpiry(regressionDataVO.getInterval());
				}
			}
		}
	}

	
	return list;
}

public List getChartDisplayParameters(Long productId,ArrayList studyId,String lotNumber,String passCondition,String sendYear) throws EAPharmicsException {
	List<DataAccessEvaluationVO> list = dataaccessFactory.getChartDisplayParameters(productId, studyId, lotNumber,passCondition,sendYear);
	return list;
}

public List getChartDisplayParametersTest(ArrayList productId,ArrayList studyId,String lotNumber,String passCondition,String sendYear) throws EAPharmicsException {
	List<DataAccessEvaluationVO> list = dataaccessFactory.getChartDisplayParametersTest(productId, studyId, lotNumber,passCondition,sendYear);
	return list;
}

public List getNumberOfResults(ArrayList chartData,String studyId) throws EAPharmicsException {
	List<RegressionReportVO> list = dataaccessFactory.getNumberOfResults(chartData,studyId);
	return list;
}

	/**
	 * @param studyIds
	 * @param productTestIds
	 * @param productIds
	 * @param Year
	 * @param t0percentCalc
	 * @param lowerLimit
	 * @param upperLimit
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<GraphDataVO> getChartData(ArrayList studyIds,
			ArrayList productTestIds, ArrayList productIds,
			String Year, Boolean t0percentCalc, String lowerLimit, String upperLimit)
			throws EAPharmicsException {
	List<GraphDataVO> returnList = new ArrayList<GraphDataVO>();
	List<GraphGeneratorVO> list = dataaccessFactory
				.getDataAccessEvaluationChart(studyIds, productTestIds,
						productIds, Year);
	NewStudyLoginImpl impl = new NewStudyLoginImpl();
	String prevStudyId = "";
	long prevTestId = 0;
	int precision = 0;
	NumberFormat formatter = null;
	Double t0Result = null;
	List<RegressionDataVO> regData = null;	
	for (Iterator<GraphGeneratorVO> iterator = list.iterator(); iterator.hasNext();) {
		GraphGeneratorVO graphGeneratorVO = (GraphGeneratorVO) iterator.next();
		GraphDataVO dataVO = new GraphDataVO();
		dataVO.setAbbreviationResult1(graphGeneratorVO.getAbbreviationResult1());
		if (graphGeneratorVO.getAverageResult() != null && graphGeneratorVO.getAverageResult().doubleValue() != 0) {
			dataVO.setAverageResult(graphGeneratorVO.getAverageResult().toString());
			if (t0percentCalc) {
				if (graphGeneratorVO.getIntervalValue() == 0) {
					//t0 Value. used for % calculation
					t0Result = graphGeneratorVO.getAverageResult();
					if (t0Result != null) {
						String[] parts = t0Result.toString().split(".");
						precision = 0;
						if (parts != null && parts.length > 1) {
							precision = parts.length;
						}
						String format = "#0";
						if (precision > 0) {
							format = format + ".";
							for (int i=0; i<precision; i++) {
								format = format + "0";
							}
						}
						formatter = new DecimalFormat(format);
					}
					dataVO.setAverageResult("No Value");
				} else if (t0Result != null) {
					dataVO.setAverageResult(graphGeneratorVO.getAverageResult().toString());
					//the results will come in interval order. some customers may want the % difference from
					//the first interval which is t0 or 0 interval/initial.
					double percentT0Result =
						(graphGeneratorVO.getAverageResult().doubleValue())
							* 100 / t0Result.doubleValue();
					dataVO.setAverageResult(formatter.format(percentT0Result));
				}
			}
		} else {
			dataVO.setAverageResult("No Value");
		}
		dataVO.setFillUnits(graphGeneratorVO.getFillUnits());
		dataVO.setIntervalValue((int)graphGeneratorVO.getIntervalValue().longValue());
		dataVO.setIntervalText(graphGeneratorVO.getIntervalValue().toString());
		dataVO.setLotNumber(graphGeneratorVO.getLotNumber());
		if (t0percentCalc) {
			dataVO.setLowerLimit(lowerLimit);
		} else {
			dataVO.setLowerLimit(graphGeneratorVO.getLowerLimit());
		}
		dataVO.setStudyCondition(graphGeneratorVO.getStudyCondition());
		dataVO.setStudyId(graphGeneratorVO.getStudyId());
		dataVO.setTestId(graphGeneratorVO.getTestId().toString());
		dataVO.setTestName(graphGeneratorVO.getTestName());
		if (t0percentCalc) {
			dataVO.setUpperLimit(upperLimit);
		} else {
			dataVO.setUpperLimit(graphGeneratorVO.getUpperLimit());
		}
		dataVO.setContainer(graphGeneratorVO.getContainer());
		dataVO.setIntervalLabel(graphGeneratorVO.getIntervalLabel());
		if (graphGeneratorVO.getMeasurement() == null) {
			dataVO.setMeasurement("");
		} else {
			dataVO.setMeasurement(graphGeneratorVO.getMeasurement());
		}
		dataVO.setTextLimit(graphGeneratorVO.getTextLimit());
		dataVO.setDecimalPoints(graphGeneratorVO.getDecimalPoints());
		dataVO.setPackageStr(graphGeneratorVO.getPackageStr());
		dataVO.setClosure(graphGeneratorVO.getClosure());
		dataVO.setManufacturingDate(graphGeneratorVO.getManufacturingDate());
		dataVO.setOnStabilityDate(graphGeneratorVO.getOnStabilityDate());
		dataVO.setStudyReason(graphGeneratorVO.getStudyReason());
		if (prevStudyId.equals(graphGeneratorVO.getStudyId()) == false ||
				prevTestId == graphGeneratorVO.getTestId().longValue() == false) {
			RegressionDataContainerVO regressionDataContainerVO;
			regData = null;
			try {
				List<RegressionDataContainerVO> regList = impl
						.getSingleRegressionReportData(graphGeneratorVO
								.getStudyId(), graphGeneratorVO.getTestId(),
								t0percentCalc, lowerLimit, upperLimit);
				regressionDataContainerVO = (RegressionDataContainerVO) regList
						.get(0);
				regData = regressionDataContainerVO.getRegressionData();
			} catch (Exception e) {
				//may not have enough data for regression 
			}
			prevStudyId = graphGeneratorVO.getStudyId();
			prevTestId = graphGeneratorVO.getTestId().longValue();
		}
		if (regData != null) {
			for (Iterator<RegressionDataVO> iterator2 = regData.iterator(); iterator2.hasNext();) {
				RegressionDataVO regressionDataVO = (RegressionDataVO) iterator2
						.next();
				if (regressionDataVO.getInterval() == graphGeneratorVO.getIntervalValue().intValue()) {
					graphGeneratorVO.setLowerBand(regressionDataVO.getLowerBand());
					graphGeneratorVO.setUpperBand(regressionDataVO.getUpperBand());
					graphGeneratorVO.setRegression(regressionDataVO.getRegression());
					dataVO.setLowerBand(regressionDataVO.getLowerBand());
					dataVO.setUpperBand(regressionDataVO.getUpperBand());
					dataVO.setRegression(regressionDataVO.getRegression());
					dataVO.setIntercept(regressionDataVO.getIntercept());
					dataVO.setRSquare(regressionDataVO.getRSquare());
					dataVO.setSlope(regressionDataVO.getSlope());
					dataVO.setOkFlag(regressionDataVO.getOkFlag());
				}
				if ("ok".equalsIgnoreCase(regressionDataVO.getOkFlag())) {
					dataVO.setExpiry(regressionDataVO.getInterval());
				}
			}
		}
		returnList.add(dataVO);
	}

	return returnList;
}

}
