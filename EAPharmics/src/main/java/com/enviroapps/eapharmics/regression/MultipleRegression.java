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

import org.apache.commons.math.distribution.FDistributionImpl;
import org.apache.commons.math.distribution.TDistributionImpl;

import com.enviroapps.eapharmics.ui.newstudy.NewStudyLoginService;
import com.enviroapps.eapharmics.util.math.MathFunction;
import com.enviroapps.eapharmics.vo.newstudy.RegressionDataVO;
import com.enviroapps.eapharmics.vo.newstudy.RegressionInputVO;

/**
 * @author EAPharmics
 *
 */
public class MultipleRegression {

	private static int DIVISION_SCALE = 13;
	private static int REG_RESULTS_PER_BATCH = 73;

	private static MathContext mc = new MathContext(DIVISION_SCALE, RoundingMode.HALF_UP);
	private BatchData sumWithinGroups = new BatchData();
	private BatchData allTotal = new BatchData();
	private List<BatchData> results = new ArrayList<BatchData>();
	private List<BigDecimal> allIntervals = new ArrayList<BigDecimal>();
	private List<BigDecimal> allData = new ArrayList<BigDecimal>();
	private List<List<RegressionDataVO>> regressionResults = new ArrayList<List<RegressionDataVO>>();

	private double graphTo = 0.25;
	
	/**
	 * 
	 */
	public MultipleRegression() {
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
	public List<List<RegressionDataVO>> getRegressionResults() {
		return regressionResults;
	}

	public void processMultipleRegression(List<List<RegressionInputVO>> batches, Double graphTo) {
		if (graphTo != null && graphTo.doubleValue() != 0) {
			this.graphTo = graphTo.doubleValue();
		}
		for (Iterator<List<RegressionInputVO>> iterator = batches.iterator(); iterator.hasNext();) {
			List<RegressionInputVO> regInputList = (List<RegressionInputVO>) iterator.next();
			BatchData batchData = new BatchData();
			batchData.setNumberOfBatches(batches.size());
			batchData.setNumberOfIntervals(regInputList.size());
			batchData.setSumOfData(getSumofData(regInputList));
			processRegression(batchData, regInputList);
			results.add(batchData);
		}
		sumWithinGroups.setNumberOfBatches(batches.size());
		allTotal.setNumberOfBatches(batches.size());
		computeTotals();
		
		results.add(sumWithinGroups);
		results.add(allTotal);
		
		//System.out.println(Utility.toString(results));
	}
	
	private void processRegression(BatchData batchData, List<RegressionInputVO> RegressionInputVOData) {
		BigDecimal[] intervals = new BigDecimal[RegressionInputVOData.size()];
		BigDecimal[] data = new BigDecimal[RegressionInputVOData.size()];
		
		List<Integer> intervalList = new ArrayList<Integer>();
		List<BigDecimal> dataList = new ArrayList<BigDecimal>();
		
		int count = 0;
		for (Iterator<RegressionInputVO> iterator = RegressionInputVOData.iterator(); iterator
				.hasNext();) {
			RegressionInputVO regressionInputVO = (RegressionInputVO) iterator.next();
			intervals[count] = new BigDecimal(regressionInputVO.getInterval(), mc);
			if (regressionInputVO.getResult() != null) {
				data[count] = new BigDecimal(regressionInputVO.getResult(), mc);
			}
			allIntervals.add(intervals[count]);
			allData.add(data[count]);
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
			batchData.setCovarIntervalData(covar.multiply(new BigDecimal(RegressionInputVOData.size(), mc)));
		}
		
		batchData.setDevSQData(MathFunction.getDEVSQ(data));
		if (batchData.getDevSQData() != null && covar != null) {
			BigDecimal exp1 =
				batchData.getCovarIntervalData().multiply(
						batchData.getCovarIntervalData()).divide(
						batchData.getDevSQIntervals(), DIVISION_SCALE, BigDecimal.ROUND_HALF_UP);
			batchData.setSyprim(batchData.getDevSQData().subtract(exp1, mc));
		}
		batchData.setDfe(RegressionInputVOData.size() - 2);
		
		if (batchData.getDevSQData() != null && covar != null) {
			batchData.setSlope(
				batchData.getCovarIntervalData().divide(
						batchData.getDevSQIntervals(),mc));
		}
		
		BigDecimal xBar = new BigDecimal(batchData.getSumOfIntervals(), mc)
				.divide(new BigDecimal(RegressionInputVOData.size(), mc), mc);
		batchData.setXbar(xBar);
		
		BigDecimal exp1 = batchData.getSumOfData().divide(
				new BigDecimal(RegressionInputVOData.size(), mc), mc);
		BigDecimal exp2 = batchData.getSlope().multiply(batchData.getXbar(), mc);
		BigDecimal intercept1 = exp1.subtract(exp2, mc);
		batchData.setIntercept1(intercept1);
		
		//update Sum Within Group values 
		if (sumWithinGroups.getDevSQIntervals() == null) {
			sumWithinGroups.setDevSQIntervals(batchData.getDevSQIntervals());
		} else {
			sumWithinGroups.setDevSQIntervals(sumWithinGroups.getDevSQIntervals().add(batchData.getDevSQIntervals(), mc));
		}
		if (sumWithinGroups.getCovarIntervalData() == null) {
			sumWithinGroups.setCovarIntervalData(batchData.getCovarIntervalData());
		} else {
			sumWithinGroups.setCovarIntervalData(sumWithinGroups.getCovarIntervalData().add(batchData.getCovarIntervalData(), mc));
		}
		if (sumWithinGroups.getDevSQData() == null) {
			sumWithinGroups.setDevSQData(batchData.getDevSQData());
		} else {
			sumWithinGroups.setDevSQData(sumWithinGroups.getDevSQData().add(batchData.getDevSQData(), mc));
		}
		sumWithinGroups.setDfe(sumWithinGroups.getDfe() + batchData.getDfe());

		//update All total values 
		allTotal.setNumberOfIntervals(allTotal.getNumberOfIntervals() + batchData.getNumberOfIntervals());
		allTotal.setSumOfIntervals(allTotal.getSumOfIntervals() + batchData.getSumOfIntervals());

		if (allTotal.getSumOfData() == null) {
			allTotal.setSumOfData(batchData.getSumOfData());
		} else {
			allTotal.setSumOfData(allTotal.getSumOfData().add(batchData.getSumOfData(), mc));
		}

	}
	
	/**
	 * 
	 */
	private void computeTotals() {

		//Calculate the remaining sum within group totals
		if (sumWithinGroups.getCovarIntervalData() != null) {
			BigDecimal exp1 =
				sumWithinGroups.getCovarIntervalData().multiply(
						sumWithinGroups.getCovarIntervalData(), mc).divide(
						sumWithinGroups.getDevSQIntervals(), mc);
			sumWithinGroups.setSyprim(sumWithinGroups.getDevSQData().subtract(exp1, mc));
			BigDecimal exp2 = sumWithinGroups.getCovarIntervalData().divide(
					sumWithinGroups.getDevSQIntervals(), mc);
			sumWithinGroups.setSlope(exp2);
		}

		//calculate the remaining all total values
		BigDecimal[] intervals = new BigDecimal[allIntervals.size()];
		intervals = allIntervals.toArray(intervals);
		BigDecimal[] data = allData.toArray(new BigDecimal[allData.size()]);
		data = allData.toArray(data);
		
		allTotal.setDevSQIntervals(MathFunction.getDEVSQ(intervals));
		allTotal.setCovarIntervalData(MathFunction.getCOVAR(intervals, data)
				.multiply(new BigDecimal(intervals.length, mc)));
		
		BigDecimal covar = MathFunction.getCOVAR(intervals, data);
		if (covar != null) {
			allTotal.setCovarIntervalData(covar.multiply(new BigDecimal(
					allTotal.getNumberOfIntervals(), mc)));
		}
		allTotal.setDevSQData(MathFunction.getDEVSQ(data));
		
		BigDecimal exp1 =
			allTotal.getCovarIntervalData().multiply(
					allTotal.getCovarIntervalData()).divide(
							allTotal.getDevSQIntervals(), mc);
		allTotal.setSyprim(allTotal.getDevSQData().subtract(exp1, mc));
		allTotal.setDfe(allTotal.getNumberOfIntervals() - 2);
		allTotal.setSlope(allTotal.getCovarIntervalData().divide(
							allTotal.getDevSQIntervals(), mc));

		BigDecimal xBar = new BigDecimal(allTotal.getSumOfIntervals(), mc)
			.divide(new BigDecimal(allTotal.getNumberOfIntervals()), mc);
		allTotal.setXbar(xBar);

		BigDecimal exp3 = allTotal.getSumOfData().divide(new BigDecimal(allIntervals.size(), mc), mc);
		BigDecimal exp4 = allTotal.getSlope().multiply(allTotal.getXbar(), mc);
		allTotal.setIntercept1(exp3.subtract(exp4, mc));

		//Compute all remaining values
		BigDecimal sumSyprim = new BigDecimal(0, mc);
		BigDecimal syPrimDiff1 = new BigDecimal(0, mc);
		BigDecimal syPrimDiff2 = new BigDecimal(0, mc);
		for (Iterator iterator = results.iterator(); iterator.hasNext();) {
			BatchData result1 = (BatchData) iterator.next();
			if (result1.getNumberOfIntervals() > 0) {
				BigDecimal exp5 = sumWithinGroups.getSlope().multiply(result1.getXbar());
				BigDecimal exp6 = result1.getSumOfData().divide(
						new BigDecimal(result1.getNumberOfIntervals(), mc), mc);
				result1.setIntercept2(exp6.subtract(exp5, mc));
			} else {
				result1.setIntercept2(new BigDecimal(0, mc));
			}
			//System.out.println(Utility.toString(result1));
			
			sumSyprim = sumSyprim.add(result1.getSyprim(), mc);
		}
		syPrimDiff1 = sumWithinGroups.getSyprim().subtract(sumSyprim, mc);
		syPrimDiff2 = allTotal.getSyprim().subtract(sumWithinGroups.getSyprim(), mc);
		
		int df1 = allTotal.getNumberOfIntervals() - (2 * allTotal.getNumberOfBatches());
		int df2 = allTotal.getNumberOfBatches() - 1;
		int df3 = df2;
		
		BigDecimal ms1 = sumSyprim.divide(new BigDecimal(df1), mc);
		BigDecimal ms2 = syPrimDiff1.divide(new BigDecimal(df2), mc);
		BigDecimal ms3 = syPrimDiff2.divide(new BigDecimal(df3), mc);

		BigDecimal fEqualSlope = ms2.divide(ms1, mc);
		BigDecimal pEqualSlope = new BigDecimal(0, mc);
		BigDecimal point25Slope = new BigDecimal(0);
        FDistributionImpl impl1 = new FDistributionImpl(df1, df2);
        FDistributionImpl impl2 = new FDistributionImpl(df2, df1);
        try {
			pEqualSlope = BigDecimal.ONE.subtract(new BigDecimal(impl2.cumulativeProbability(fEqualSlope.doubleValue()), mc), mc);
	        double y = impl1.inverseCumulativeProbability(graphTo); 
	        point25Slope = new BigDecimal(Math.pow(y, -1));
		} catch (Exception e) {
			
		}
		
		BigDecimal expt1 = syPrimDiff2.divide(new BigDecimal(df2), mc);
		BigDecimal expt2 = sumSyprim.add(syPrimDiff1);
		BigDecimal expt3 = new BigDecimal(df1 + df2);
		BigDecimal expt4 = expt2.divide(expt3, mc);
		BigDecimal fEqualIntercepts = expt1.divide(expt4, mc);
        BigDecimal pIntercept = new BigDecimal(0);
        BigDecimal point25Intercept = new BigDecimal(0);
        impl1 = new FDistributionImpl(df3, (df1 + df2));
        impl2 = new FDistributionImpl((df1 + df2), df3);
        try {
        	pIntercept = BigDecimal.ONE.subtract(new BigDecimal(impl1.cumulativeProbability(fEqualIntercepts.doubleValue()), mc), mc);
	        double y = impl2.inverseCumulativeProbability(graphTo); 
	        point25Intercept = new BigDecimal(Math.pow(y, -1));
		} catch (Exception e) {
			
		}

		BigDecimal mse = new BigDecimal(0);
		if (pEqualSlope.doubleValue() > graphTo && pIntercept.doubleValue() > graphTo) {
			mse = allTotal.getSyprim().divide(new BigDecimal(allTotal.getDfe()), mc);
		} else {
			if (pEqualSlope.doubleValue() <= graphTo) {
				mse = ms1;
			} else {
				BigDecimal exptt1 = sumSyprim.add(syPrimDiff1, mc);
				BigDecimal exptt2 = new BigDecimal(df1 + df2);
				mse = exptt1.divide(exptt2, mc);
			}
		}

		BigDecimal tinv = new BigDecimal(0, mc);
        try {
            double d1 = 0d; 
    		if (pEqualSlope.doubleValue() > graphTo && pIntercept.doubleValue() > graphTo) {
    			d1 = (double) allTotal.getDfe();
    		} else {
    			d1 = (double) df1;
    			if (pEqualSlope.doubleValue() <= graphTo) {
    			} else {
    				d1 = d1 + (double) df2;
    			}
    		}
            TDistributionImpl distImp = new TDistributionImpl(d1);
            double d = distImp.inverseCumulativeProbability(0.1/2) * -1;
            tinv = new BigDecimal(d, mc);
		} catch (Exception e) {
			
		}
        
		BigDecimal tSqrtMse = new BigDecimal(Math.sqrt(mse.doubleValue()) * tinv.doubleValue());
		
		//System.out.println("Pring Graph Data");
		
		//Calculate Regression, Lower and Upper Bands
		for (Iterator iterator = results.iterator(); iterator.hasNext();) {
			BatchData batchData = (BatchData) iterator.next();
			//System.out.println("Graph Data for: " + batchData.getBatchNumber());
			List<RegressionDataVO> batchResults = new ArrayList<RegressionDataVO>();
			for (int i=0; i<REG_RESULTS_PER_BATCH; i++) {
				RegressionDataVO rData = new RegressionDataVO();
				rData.setInterval(i);
				//rData.setLowerBand(lowerBand);
				//rData.setUpperBand(upperBand);
				BigDecimal rExp1 = new BigDecimal(0);
				//P2*IF($C$35>0.25,IF($C$40>0.25,$K$26,$K$25),$K$2)+IF($C$35>0.25,IF($C$40>0.25,$L$26,$N$2),$L$2)
				if (pEqualSlope.doubleValue() > graphTo) {
					if (pIntercept.doubleValue() > graphTo) {
						rExp1 = allTotal.getSlope();
					} else {
						rExp1 = sumWithinGroups.getSlope();
					}
				} else {
					rExp1 = batchData.getSlope();
				}
				rExp1 = rExp1.multiply(new BigDecimal(i, mc), mc);
				BigDecimal rExp2 = new BigDecimal(0, mc);
				if (pEqualSlope.doubleValue() > graphTo) {
					if (pIntercept.doubleValue() > graphTo) {
						rExp2 = allTotal.getIntercept1();
					} else {
						rExp2 = batchData.getIntercept2();
					}
				} else {
					rExp2 = batchData.getIntercept1();
				}
				rData.setRegression(rExp1.add(rExp2, mc).doubleValue());

				
				//=R2-$C$46*SQRT((1/IF(AND($C$35>0.25,$C$40>0.25),$C$26,$C$2)+SUMSQ(IF(AND($C$35>0.25,$C$40>0.25),$M$26,$M$2)-P2)/IF($C$35>0.25,IF($C$40>0.25,$F$26,$F$25),$F$2)))				
				BigDecimal rExp3 = new BigDecimal(0, mc);
				int val1 = batchData.getNumberOfIntervals();
				if (pEqualSlope.doubleValue() > graphTo &&
						pIntercept.doubleValue() > graphTo) {
					val1 = allTotal.getNumberOfIntervals();
				}
				rExp3 = BigDecimal.ONE.divide(new BigDecimal(val1), mc);
				
				BigDecimal sumsq = batchData.getXbar();
				if (pEqualSlope.doubleValue() > graphTo &&
						pIntercept.doubleValue() > graphTo) {
					sumsq = allTotal.getXbar();
				}
				sumsq = sumsq.subtract(new BigDecimal(rData.getInterval(), mc), mc);
				sumsq = sumsq.multiply(sumsq, mc);
				
				BigDecimal x3 = new BigDecimal(0, mc);
				if (pEqualSlope.doubleValue() > graphTo) {
					if (pIntercept.doubleValue() > graphTo) {
						x3 = allTotal.getDevSQIntervals(); 
					} else {
						x3 = sumWithinGroups.getDevSQIntervals();
					}
				} else {
					x3 = batchData.getDevSQIntervals();
				}				
				sumsq = sumsq.divide(x3, mc);
				
				rExp3 = rExp3.add(sumsq, mc);
				
				double sq = Math.sqrt(rExp3.doubleValue());
				rExp3 = new BigDecimal(sq, mc);

				rExp3 = tSqrtMse.multiply(rExp3, mc);
				BigDecimal lowerBand = new BigDecimal(rData.getRegression(), mc).subtract(rExp3, mc);
				rData.setLowerBand(lowerBand.doubleValue());
				
				BigDecimal upperBand = new BigDecimal(rData.getRegression(), mc).add(rExp3, mc);
				rData.setUpperBand(upperBand.doubleValue());
				
				rData.setData(batchData.getDataForInterval(i));
				rData.setLowerLimit(batchData.getLowerLimit());
				rData.setUpperLimit(batchData.getUpperLimit());
				
				rData.calculateOkFlag();
				
				rData.setPSlope(pEqualSlope.doubleValue());
				rData.setPIntercept(pIntercept.doubleValue());
				
				batchResults.add(rData);
				
				//System.out.println(rData.toString());
			}
			regressionResults.add(batchResults);
		}
	}
	
	private BigDecimal getSumofData(List<RegressionInputVO> regressionInput) {
		int[] values = new int[regressionInput.size()];
		for (int i = 0; i < values.length; i++) {
			values[i] = ((RegressionInputVO) regressionInput.get(i)).getInterval();
		}
		return MathFunction.getSumForInt(values);
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		List<RegressionInputVO> batch1 = new ArrayList<RegressionInputVO> ();
		RegressionInputVO data1 = new RegressionInputVO(new Double(20d), null, "10j21A",  0, new Double(.45));
		RegressionInputVO data2 = new RegressionInputVO(new Double(20d), null, "10j21A",  3, new Double(.53));
		RegressionInputVO data3 = new RegressionInputVO(new Double(20d), null, "10j21A",  6, new Double(.58));
		RegressionInputVO data4 = new RegressionInputVO(new Double(20d), null, "10j21A",  9, new Double(.57));
		RegressionInputVO data5 = new RegressionInputVO(new Double(20d), null, "10j21A",  12, new Double(.57));
		RegressionInputVO data6 = new RegressionInputVO(new Double(20d), null, "10j21A",  15, new Double(.56));
		RegressionInputVO data7 = new RegressionInputVO(new Double(20d), null, "10j21A",  18, new Double(.54));
		RegressionInputVO data8 = new RegressionInputVO(new Double(20d), null, "10j21A",  18, new Double(0));
		batch1.add(data1);
		batch1.add(data2);
		batch1.add(data3);
		batch1.add(data4);
		batch1.add(data5);
		batch1.add(data6);
		batch1.add(data7);
		batch1.add(data8);

		List<RegressionInputVO> batch2 = new ArrayList<RegressionInputVO> ();
		data1 = new RegressionInputVO(new Double(20d), null, "10j22A",  0, new Double(0.56));
		data2 = new RegressionInputVO(new Double(20d), null, "10j22A",  3, new Double(0.57));
		data3 = new RegressionInputVO(new Double(20d), null, "10j22A",  6, new Double(0.64));
		data4 = new RegressionInputVO(new Double(20d), null, "10j22A",  9, new Double(0.66));
		data5 = new RegressionInputVO(new Double(20d), null, "10j22A",  12, new Double(0.70));
		data6 = new RegressionInputVO(new Double(20d), null, "10j22A",  15, new Double(0.70));
		data7 = new RegressionInputVO(new Double(20d), null, "10j22A",  18, new Double(0.69));
		data8 = new RegressionInputVO(new Double(20d), null, "10j22A",  24, new Double(0));
		batch2.add(data1);
		batch2.add(data2);
		batch2.add(data3);
		batch2.add(data4);
		batch2.add(data5);
		batch2.add(data6);
		batch2.add(data7);
		batch2.add(data8);

//		List<RegressionInputVO> batch3 = new ArrayList<RegressionInputVO> ();
//		data1 = new RegressionInputVO(new Double(20d), null, "10j23A`",  0, new Double(38));
//		data2 = new RegressionInputVO(new Double(20d), null, "10j23A",  3, new Double(43));
//		data3 = new RegressionInputVO(new Double(20d), null, "10j23A",  6, new Double(43));
//		data4 = new RegressionInputVO(new Double(20d), null, "10j23A",  9, new Double(40));
//		data5 = new RegressionInputVO(new Double(20d), null, "10j23A",  12, new Double(36));
//		data6 = new RegressionInputVO(new Double(20d), null, "10j23A",  15, new Double(33));
//		data7 = new RegressionInputVO(new Double(20d), null, "10j23A",  18,new Double(26));
//		batch3.add(data1);
//		batch3.add(data2);
//		batch3.add(data3);
//		batch3.add(data4);
//		batch3.add(data5);
//		batch3.add(data6);
//		batch3.add(data7);

		List<List<RegressionInputVO>> batches = new ArrayList<List<RegressionInputVO>>();
		batches.add(batch1);
		batches.add(batch2);
//		batches.add(batch3);
		
//		MultipleRegression testMR = new MultipleRegression();
//		testMR.processMultipleRegression(batches, null);
		
		List<String> studyIds = new ArrayList<String>();
		studyIds.add("100169");
		studyIds.add("100170");

		Long productTestId = new Long(100);
		NewStudyLoginService testMR = new NewStudyLoginService();
		//List list = testMR.getMultipleRegressionGraphData(studyIds, productTestId);
		
		List list = testMR.getMultipleRegressionReportData(studyIds, productTestId, Boolean.FALSE, null, null);

		System.out.println(list);
		
	}

	/**
	 * @return the sumWithinGroups
	 */
	public BatchData getSumWithinGroups() {
		return sumWithinGroups;
	}

}
