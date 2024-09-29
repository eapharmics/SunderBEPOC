/**
 * 
 */
package com.enviroapps.eapharmics.util.math;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * @author EAPharmics
 *
 */
public class MathFunction {

	private static int DIVISION_SCALE = 13;
	private static MathContext mc = new MathContext(DIVISION_SCALE, RoundingMode.HALF_UP);
	/**
	 * @param values
	 * @return
	 */
	public static BigDecimal getSumForInt(int[] values) {
		int total = 0;
		for (int i = 0; i < values.length; i++) {
			total += values[i];
		}
		return new BigDecimal(total, mc);
	}
	
	public static BigDecimal[] removeNulls(BigDecimal[] values) {
		int length = 0;
		for (int i = 0; i < values.length; i++) {
			if (values[i] != null) {
				length++;
			}
		}
		BigDecimal[] vals = new BigDecimal[length];
		int index = 0;
		for (int i = 0; i < values.length; i++) {
			if (values[i] != null) {
				vals[index] = values[i];
				index++;
			};
		}
		return vals;
	}
	
	/**
	 * @param values
	 * @return
	 */
	public static BigDecimal getSum(BigDecimal[] values) {
		BigDecimal total = new BigDecimal(0, mc);
		for (int i = 0; i < values.length; i++) {
			if (values[i] != null) {
				total = total.add(values[i], mc);
			}
		}
		return total;
	}
	
	/**
	 * @param values
	 * @return
	 */
	public static BigDecimal getAverage(BigDecimal[] values) {
		BigDecimal total = new BigDecimal(0, mc);
		int length = 0;
		for (int i = 0; i < values.length; i++) {
			if (values[i] != null) {
				total = total.add(values[i], mc);
				length++;
			}
		}
		BigDecimal count = new BigDecimal(length);
		return total.divide(count, mc);		
	}
	
	/**
	 * @param values
	 * @return
	 */
	public static BigDecimal[] getStandardDeviation(BigDecimal[] values) {
		BigDecimal average = MathFunction.getAverage(values);
		BigDecimal[] sd = new BigDecimal [values.length];
		for (int i = 0; i < values.length; i++) {
			if (values[i] != null) {
				sd[i] = values[i].subtract(average, mc);
			}
		}
		return sd;
	}
	
	public static BigDecimal getDEVSQ(BigDecimal[] values) {
		BigDecimal[] sd = MathFunction.getStandardDeviation(values);
		BigDecimal devSQ = new BigDecimal (0, mc);
		for (int i = 0; i < values.length; i++) {
			if (sd[i] != null) {
				devSQ = devSQ.add(sd[i].multiply(sd[i], mc), mc);
			}
		}
		return devSQ;
	}
	
	public static BigDecimal getCOVAR(BigDecimal[] values1, BigDecimal[] values2) {
		BigDecimal[] sd1 = MathFunction.getStandardDeviation(values1);
		BigDecimal[] sd2 = MathFunction.getStandardDeviation(values2);
		BigDecimal total = new BigDecimal (0, mc);
		int length = 0;
		for (int i = 0; i < values1.length; i++) {
			if (sd1[i] != null && i < values2.length && sd2[i] != null) {
				total = total.add(sd1[i].multiply(sd2[i], mc), mc);
				length++;
			}
		}
		BigDecimal covar = total.divide(new BigDecimal(length, mc), mc);
		return covar;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] array1 = {0, 3, 6, 9, 12, 15, 18};
		Double[] array2 = {38d, 43d, null, 38d, 37d, 34d, 28d};
		BigDecimal[] values1 = new BigDecimal [array1.length];
		for (int i = 0; i < array1.length; i++) {
			values1[i] = new BigDecimal(array1[i]);
		}
		BigDecimal devSQ = MathFunction.getDEVSQ(values1);
		System.out.println("DEV SQ: " + devSQ);
		
		BigDecimal[] values2 = new BigDecimal [array2.length];
		for (int i = 0; i < array2.length; i++) {
			if (array2[i] != null) {
				values2[i] = new BigDecimal(array2[i], mc);
			}
		}
		BigDecimal avg = MathFunction.getAverage(values2);
		System.out.println("avg: " + avg);
		BigDecimal covar = MathFunction.getCOVAR(values1, values2);
		covar = covar.multiply(new BigDecimal(values1.length, mc), mc);
		System.out.println("COVAR: " + covar);

	}

}
