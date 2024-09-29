/**
 * 
 */
package com.enviroapps.eapharmics.regression;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sound.sampled.DataLine;

import org.apache.commons.math.distribution.TDistributionImpl;

import com.enviroapps.eapharmics.services.NewStudyLoginImpl;
import com.enviroapps.eapharmics.ui.newstudy.NewStudyLoginService;
import com.enviroapps.eapharmics.util.math.MathFunction;
import com.enviroapps.eapharmics.vo.newstudy.RegressionDataContainerVO;
import com.enviroapps.eapharmics.vo.newstudy.RegressionDataVO;
import com.enviroapps.eapharmics.vo.newstudy.RegressionInputVO;

/**
 * @author EAPharmics
 *
 */
public class SingleRegression {

	private static int DIVISION_SCALE = 13;
	private static int REG_RESULTS_PER_BATCH = 73;

	private static MathContext mc = new MathContext(DIVISION_SCALE, RoundingMode.HALF_UP);
	private List<BatchData> results = new ArrayList<BatchData>();
	private List<RegressionDataVO> regressionResults = new ArrayList<RegressionDataVO>();

	private double graphTo = 0.1;
	
	/**
	 * 
	 */
	public SingleRegression() {
	}
	
	/**
	 * @return the results
	 */
	public List<BatchData> getResults() {
		return results;
	}

	/**
	 * @return the regressionResults
	 */
	public List<RegressionDataVO> getRegressionResults() {
		return regressionResults;
	}

	public void processSingleRegression(List<RegressionInputVO> regInputList, Double graphTo) {
		if (graphTo != null && graphTo.doubleValue() != 0) {
			this.graphTo = graphTo.doubleValue();
		}
		BatchData batchData = new BatchData();
		batchData.setNumberOfBatches(1);
		batchData.setNumberOfIntervals(regInputList.size());
		batchData.setSumOfData(getSumofData(regInputList));
		processRegression(batchData, regInputList);
		results.add(batchData);
		computeTotals(batchData, regInputList);

		//System.out.println(Utility.toString(results));
	}
	
	private void processRegression(BatchData batchData, List<RegressionInputVO> regressionInputVOData) {
		BigDecimal[] intervals = new BigDecimal[regressionInputVOData.size()];
		BigDecimal[] data = new BigDecimal[regressionInputVOData.size()];
		
		List<Integer> intervalList = new ArrayList<Integer>();
		List<BigDecimal> dataList = new ArrayList<BigDecimal>();
		
		int count = 0;
		for (Iterator<RegressionInputVO> iterator = regressionInputVOData.iterator(); iterator
				.hasNext();) {
			RegressionInputVO regressionInputVO = (RegressionInputVO) iterator.next();
			intervals[count] = new BigDecimal(regressionInputVO.getInterval(), mc);
			if (regressionInputVO.getResult() != null) {
				data[count] = new BigDecimal(regressionInputVO.getResult(), mc);
			}
			intervalList.add(new Integer(regressionInputVO.getInterval()));
			if (regressionInputVO.getResult() == null) {
				dataList.add(null);
			} else {
				dataList.add(new BigDecimal(regressionInputVO.getResult(), mc));
			}
			batchData.setBatchNumber(regressionInputVO.getBatchId());
			batchData.setLowerLimit(regressionInputVO.getLowerLimit());
			batchData.setUpperLimit(regressionInputVO.getUpperLimit());
			count++;
		}
		
		batchData.setIntervals(intervalList);
		batchData.setData(dataList);
		
		BigDecimal sumOfIntervals = MathFunction.getSum(intervals);
		batchData.setSumOfData(MathFunction.getSum(data));
		if (sumOfIntervals != null) {
			batchData.setSumOfIntervals(sumOfIntervals.intValue());
		}
		batchData.setDevSQIntervals(MathFunction.getDEVSQ(intervals));
		
		BigDecimal covar = MathFunction.getCOVAR(intervals, data);
		if (covar != null) {
			batchData.setCovarIntervalData(covar.multiply(new BigDecimal(regressionInputVOData.size(), mc)));
		}
		
		batchData.setDevSQData(MathFunction.getDEVSQ(data));
		if (batchData.getDevSQData() != null && covar != null) {
			BigDecimal exp1 =
				batchData.getCovarIntervalData().multiply(
						batchData.getCovarIntervalData()).divide(
						batchData.getDevSQIntervals(), DIVISION_SCALE, BigDecimal.ROUND_HALF_UP);
			batchData.setSyprim(batchData.getDevSQData().subtract(exp1, mc));
		}
		batchData.setDfe(regressionInputVOData.size() - 2);
		
		if (batchData.getDevSQData() != null && covar != null) {
			batchData.setSlope(
				batchData.getCovarIntervalData().divide(
						batchData.getDevSQIntervals(),BigDecimal.ROUND_HALF_UP));
		}
		
		BigDecimal xBar = new BigDecimal(batchData.getSumOfIntervals(), mc)
				.divide(new BigDecimal(regressionInputVOData.size(), mc), mc);
		batchData.setXbar(xBar);
		
		BigDecimal exp1 = batchData.getSumOfData().divide(
				new BigDecimal(regressionInputVOData.size(), mc), mc);
		BigDecimal exp2 = batchData.getSlope().multiply(batchData.getXbar(), mc);
		BigDecimal intercept1 = exp1.subtract(exp2, mc);
		batchData.setIntercept1(intercept1);

	}
	
	/**
	 * 
	 */
	private void computeTotals(BatchData batchData, List<RegressionInputVO> regressionInputVOData) {

		BigDecimal mse = new BigDecimal(0, mc);
		if (batchData.getDfe() > 0) {
			mse = batchData.getSyprim().divide(new BigDecimal(batchData.getDfe(), mc), mc);
		}
		BigDecimal tinv = new BigDecimal(0, mc);
        try {
            TDistributionImpl distImp = new TDistributionImpl(batchData.getDfe());
            double d = distImp.inverseCumulativeProbability(0.1/2) * -1;
            tinv = new BigDecimal(d, mc);
		} catch (Exception e) {
			
		}
        
		BigDecimal tSqrtMse = new BigDecimal(Math.sqrt(mse.doubleValue()) * tinv.doubleValue(), mc);
		
		//System.out.println(Utility.toString(batchData));
		
		//System.out.println("Pring Graph Data");
		for (int i=0; i<REG_RESULTS_PER_BATCH; i++) {
			RegressionDataVO rData = new RegressionDataVO();
			rData.setInterval(i);

			BigDecimal rExp1 = batchData.getSlope();
			rExp1 = rExp1.multiply(new BigDecimal(i, mc), mc);
			BigDecimal rExp2 = batchData.getIntercept1();
			BigDecimal regression = rExp1.add(rExp2, mc);
			rData.setRegression(regression.doubleValue());

			//+Q22-$D$33*SQRT((1/$C$27)+(($M$27-O22)^2)/($F$27))
			BigDecimal val1 = batchData.getXbar().subtract(new BigDecimal(i, mc), mc);
			val1 = val1.multiply(val1, mc);
			val1 = val1.divide(batchData.getDevSQIntervals(), mc);
			BigDecimal val2 = BigDecimal.ONE.divide(new BigDecimal(batchData.getIntervals().size(), mc), mc);
			val1 = val1.add(val2, mc);
			val1 = new BigDecimal(Math.sqrt(val1.doubleValue()), mc);
			BigDecimal rExp3 = tSqrtMse.multiply(val1, mc);

			//lowerBand
			BigDecimal rExp4 = regression.subtract(rExp3, mc);
			rData.setLowerBand(rExp4.doubleValue());
			
			//UpperBand
			rExp4 = regression.add(rExp3, mc);
			rData.setUpperBand(rExp4.doubleValue());
			
			rData.setData(batchData.getDataForInterval(i));
			rData.setLowerLimit(batchData.getLowerLimit());
			rData.setUpperLimit(batchData.getUpperLimit());
			
			rData.setSlope(batchData.getSlope().doubleValue());
			rData.setIntercept(batchData.getIntercept1().doubleValue());
			//rData.setRSquare(batchData.get);
			rData.calculateOkFlag();
			
			regressionResults.add(rData);
			
			//System.out.println(rData);
				
		}

		BigDecimal sumOfA = new BigDecimal(0, mc);	//Sum of Data
		BigDecimal sumOfB = new BigDecimal(0, mc);		//Sum of Regression
		BigDecimal sumOfAB = new BigDecimal(0, mc);
		BigDecimal sumOfA2 = new BigDecimal(0, mc);
		BigDecimal sumOfB2 = new BigDecimal(0, mc);
		int dataSize = 0;
		for (Iterator<RegressionInputVO> iterator = regressionInputVOData.iterator(); 
			iterator.hasNext();) {
			RegressionInputVO regressionInputVO = (RegressionInputVO) iterator.next();
			if (regressionInputVO.getResult() == null) {
				continue;
			}
			RegressionDataVO b1 = null;
			for (Iterator iterator2 = regressionResults.iterator(); iterator2
					.hasNext();) {
				b1 = (RegressionDataVO) iterator2.next();
				if (b1.getInterval() == regressionInputVO.getInterval()) {
					break;
				}
				b1 = null;
			}
			if (b1 == null) {
				continue;
			}
			double ab = b1.getData().doubleValue() * b1.getRegression().doubleValue();
			double a2 = b1.getData().doubleValue() * b1.getData().doubleValue();
			double b2 = b1.getRegression().doubleValue() * b1.getRegression().doubleValue();
			sumOfA = sumOfA.add(new BigDecimal(b1.getData().doubleValue()));
			sumOfB = sumOfB.add(new BigDecimal(b1.getRegression().doubleValue()), mc);
			sumOfAB = sumOfAB.add(new BigDecimal(ab), mc);
			sumOfA2 = sumOfA2.add(new BigDecimal(a2), mc);
			sumOfB2 = sumOfB2.add(new BigDecimal(b2), mc);
			dataSize++;
		}
		BigDecimal dataListSize = new BigDecimal(dataSize, mc);
		//=(A10*E10-C10*D10)/SQRT((A10*F10-(C10*C10))*(A10*G10-(D10*D10)))
		//=(ints*sab-sa*sb)/SQRT((ints*sa2-(sa*sa))*(ints*sb2-(sb*sb)))
		//R = (6*(sum of ab column) - (sum of a column)*(sum of b column)) / [sqrt((6*(sum a^2 column) - (sum of a column)^2)*(6*(sum of b^2 column) - (sum of b column)^2)
		//N = (6*(sum of ab column) - (sum of a column)*(sum of b column)) 
		//D = [sqrt((6*(sum a^2 column) - (sum of a column)^2)*(6*(sum of b^2 column) - (sum of b column)^2)

		BigDecimal numer = sumOfAB.multiply(dataListSize).subtract(sumOfA.multiply(sumOfB));

		BigDecimal l1 = sumOfA2.multiply(dataListSize, mc).subtract((sumOfA.multiply(sumOfA, mc)), mc);
		BigDecimal l2 =	sumOfB2.multiply(dataListSize, mc).subtract((sumOfB.multiply(sumOfB, mc)), mc);
		double denDouble = Math.sqrt(l1.multiply(l2, mc).doubleValue());
		BigDecimal denom = new BigDecimal(denDouble, mc);
		BigDecimal r = numer.divide(denom, mc);
		BigDecimal r2 = r.multiply(r);	//R-Squared
		for (Iterator<RegressionDataVO> iterator = regressionResults.iterator(); iterator.hasNext();) {
			RegressionDataVO regDataVO = iterator.next();
			regDataVO.setRSquare(r2.doubleValue());
		}
		
	}
	
	private BigDecimal getSumofData(List<RegressionInputVO> regressionInput) {
		int[] values = new int[regressionInput.size()];
		for (int i = 0; i < values.length; i++) {
			values[i] = ((RegressionInputVO) regressionInput.get(i)).getInterval();
		}
		return MathFunction.getSumForInt(values);
	}
	
	public static List getSingleRegressionReportData(String studyId, String productTestId,
			Boolean t0percentCalc, String lowerLimit, String upperLimit) throws Exception {		
		NewStudyLoginImpl singleRegression = new NewStudyLoginImpl();		
		List<RegressionDataContainerVO> list = singleRegression.getSingleRegressionReportData(studyId, Long.parseLong(productTestId),
				t0percentCalc, lowerLimit, upperLimit);
		if (list != null) {
			RegressionDataContainerVO container = list.get(0);
			List<RegressionDataVO> data = container.getRegressionData();
			int i = 0;
			for (Iterator iterator = data.iterator(); iterator.hasNext(); i++) {
				RegressionDataVO regressionDataVO = (RegressionDataVO) iterator.next();
				if(regressionDataVO.getOkFlag().equals("ok")) {
					container.setShelfLife(i);					
				} else {
					break;
				}				
			}
		}
		return list;
	}

	public static List getSingleRegressionReportData(String studyId, String productTestId) throws Exception {
		return SingleRegression.getSingleRegressionReportData(studyId, productTestId, Boolean.FALSE, null, null);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		List<RegressionInputVO> batch1 = new ArrayList<RegressionInputVO> ();
		RegressionInputVO data1 = new RegressionInputVO(new Double(90d), new Double(110d), "10j21A",  0, new Double(99.3));
		RegressionInputVO data2 = new RegressionInputVO(new Double(90d), new Double(110d), "10j21A",  3, new Double(94.1));
		RegressionInputVO data3 = new RegressionInputVO(new Double(90d), new Double(110d), "10j21A",  6, new Double(92.9));
		RegressionInputVO data4 = new RegressionInputVO(new Double(90d), new Double(110d), "10j21A",  9, new Double(95.6));
		RegressionInputVO data5 = new RegressionInputVO(new Double(90d), new Double(110d), "10j21A",  12, new Double(94.2));
		RegressionInputVO data6 = new RegressionInputVO(new Double(90d), new Double(110d), "10j21A",  18, new Double(91.5));
		RegressionInputVO data7 = new RegressionInputVO(new Double(90d), new Double(110d), "10j21A",  24, new Double(92.0));
		batch1.add(data1);
		batch1.add(data2);
		batch1.add(data3);
		batch1.add(data4);
		batch1.add(data5);
		batch1.add(data6);
		batch1.add(data7);

//		SingleRegression sr = new SingleRegression();
//		sr.processSingleRegression(batch1, null);
		
		Long productTestId = new Long(100);
		NewStudyLoginService testMR = new NewStudyLoginService();		
		List list = testMR.getSingleRegressionReportData("100169", productTestId);

		System.out.println(list);
	}
	
}
