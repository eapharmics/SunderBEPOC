/**
 * 
 */
package com.enviroapps.eapharmics.bom.newstudy;

import java.util.Hashtable;
import java.util.StringTokenizer;

import com.enviroapps.eapharmics.ui.Constants;

/**
 * @author EAPharmics
 *
 */
public class BarCode {
	
	/**
	 * @param studyId
	 * @param intervalValue
	 * @param intervalLabel
	 * @param intervalLabelCount
	 * @return
	 */
	public static String getBarCode(String studyId, int intervalValue,
			String intervalLabel, int intervalLabelCount) {
		StringBuffer sb = new StringBuffer();
		sb.append(studyId);
		sb.append("/");
		sb.append(intervalValue);
		if (intervalLabel != null) {
			sb.append(intervalLabel.substring(0,1));
		}
		sb.append("/");
		sb.append(intervalLabelCount);
		return sb.toString();
	}
	
	/**
	 * @param barCode
	 * @return
	 */
	public static String getStudyId(String barCode) {
		StringTokenizer st = new StringTokenizer(barCode, "/");
		if (st.hasMoreTokens()) {
			return st.nextToken();
		}
		return null;
	}
	
	/**
	 * @param barCode
	 * @return
	 */
	public static Hashtable<String, String> getBarCodeElements(String barCode) {
		Hashtable<String, String> map = new Hashtable<String, String>();
		StringTokenizer st = new StringTokenizer(barCode, "/");
		if (st.hasMoreTokens()) {
			map.put(Constants.STUDY_ID, st.nextToken());	//Study Id
		}
		if (st.hasMoreTokens()) {
			String intervalLabelStr = st.nextToken();
			if (intervalLabelStr != null && intervalLabelStr.equals("Extra")) {
				//No intervals will be there for extra labels
			} else {
				String intervalValue = intervalLabelStr.substring(0, intervalLabelStr.length()-1);
				String intervalLabelAbbr = intervalLabelStr.substring(intervalLabelStr.length()-1);
				map.put(Constants.INTERVAL_VALUE, intervalValue);
				map.put(Constants.INTERVAL_LABEL_ABBR, intervalLabelAbbr);
				if (intervalLabelAbbr.equalsIgnoreCase("D")) {
					map.put(Constants.INTERVAL_LABEL, "Days");
				} else if (intervalLabelAbbr.equalsIgnoreCase("M")) {
					map.put(Constants.INTERVAL_LABEL, "Months");
				} else if (intervalLabelAbbr.equalsIgnoreCase("W")) {
					map.put(Constants.INTERVAL_LABEL, "Weeks");
				}			
			}
		}
		if (st.hasMoreTokens()) {
			map.put(Constants.INTERVAL_LABEL_COUNT, st.nextToken());
		}
		return map;
	}

}
