package com.enviroapps.eapharmics.persistence;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.enviroapps.eapharmics.bom.dictionary.DictionaryDetail;
import com.enviroapps.eapharmics.bom.product.ProductTest;
import com.enviroapps.eapharmics.common.services.UtilityServiceFactory;
import com.enviroapps.eapharmics.common.services.logging.ILogger;
import com.enviroapps.eapharmics.das.persistence.DataAccessConstants;
import com.enviroapps.eapharmics.das.persistence.hibernate.HibernatePersistenceFactory;
import com.enviroapps.eapharmics.das.persistence.hibernate.HibernatePersistenceManager;
import com.enviroapps.eapharmics.das.persistence.hibernate.HibernatePersistenceManagerFactory;
import com.enviroapps.eapharmics.ui.Constants;
import com.enviroapps.eapharmics.vo.dataaccessevaluation.DataAccessEvaluationVO;
import com.enviroapps.eapharmics.vo.dataaccessevaluation.RegressionReportVO;
import com.enviroapps.eapharmics.vo.newstudy.GraphGeneratorVO;
import com.enviroapps.eapharmics.vo.newstudy.NewStudyProductVO;

public class DataAccessEvaluationFactory extends HibernatePersistenceFactory
		implements DataAccessConstants {

	private static ILogger log = UtilityServiceFactory.getLogger();

	private DataAccessEvaluationFactory() {
		persistenceManager = (HibernatePersistenceManager) HibernatePersistenceManagerFactory
				.getInstance().getPersistenceManager();
		log.debug(this, "DataAccessEvaluation", persistenceManager);
	}

	private static DataAccessEvaluationFactory instance = new DataAccessEvaluationFactory();

	public static DataAccessEvaluationFactory getInstance() {
		return instance;
	}

	public List getAllProductTestsForKeys(List productTestIds) {
		Session session = persistenceManager.getCurrentSession();
		List list = session.createCriteria(ProductTest.class).add(
				Restrictions.in("productTestId", productTestIds)).list();
		return list;
	}

	/**
	 * @param studyIds
	 * @param productTestIds
	 * @param productIds
	 * @param Year
	 * @return
	 */
	public List getDataAccessEvaluationChart(ArrayList studyIds,
			ArrayList productTestIds, ArrayList productIds, String Year) {
		Session session = persistenceManager.getCurrentSession();
		String id = "";
		String chartDataStr = "";
		String multipleProductId = "";

		if (studyIds != null && studyIds.size() > 0) {
			for (Iterator iteratorStr = studyIds.iterator(); iteratorStr
					.hasNext();) {
				if (id.length() > 0) {
					id = id + "," + "'" + iteratorStr.next().toString() + "'";
				} else {
					id = "'" + iteratorStr.next().toString() + "'";
				}
			}

		}
		if (productTestIds != null && productTestIds.size() > 0) {
			for (Iterator iteratorData = productTestIds.iterator(); iteratorData
					.hasNext();) {
				if (chartDataStr.length() > 0) {
					chartDataStr = chartDataStr + "," + "'"
							+ iteratorData.next().toString() + "'";
				} else {
					chartDataStr = "'" + iteratorData.next().toString() + "'";
				}
			}

		}
		if (productIds != null && productIds.size() > 0) {
			for (Iterator iteratorproductIds = productIds.iterator(); iteratorproductIds
					.hasNext();) {
				if (multipleProductId.length() > 0) {
					multipleProductId = multipleProductId + "," + "'"
							+ iteratorproductIds.next().toString() + "'";
				} else {
					multipleProductId = "'"
							+ iteratorproductIds.next().toString() + "'";
				}
			}
		}

		String sql = "select ET.STUDY_ID, ET.PRODUCT_TEST_ID, ES.PRD_STUDY_SCHEDULE_ID,";
		sql = sql + "ES.INTERVAL_VALUE,";
		sql = sql + "PT.UPPER_LIMIT,";
		sql = sql + "PT.LOWER_LIMIT,";
		sql = sql + "PT.TEST_NAME, PT.RESULTS_FORMAT, ";
		sql = sql + "EPPS.LOT_NUMBER,";
		sql = sql + "NVL (psc.normal_env_conditions_code, psc.challenge_conditions_code) study_condition, ";
		sql = sql + "EPPS.PACKAGE_SIZE_CODE,";
		sql = sql + "ET.RESULT_AVERAGE,";
		sql = sql + "ET.RESULT_1,";
		sql = sql + "PT.MEASUREMENT,";
		sql = sql + "PSC.INTERVAL_LABEL,";
		sql = sql + "EPPS.CONTAINER_CODE, ";
		sql = sql + "PT.TEXT_LIMIT, ";
		sql = sql + "PT.DECIMAL_POINTS, ";
		sql = sql + "ET.RESULT_STATUS, ";
		sql = sql + "EPPS.PACKAGE_SIZE_CODE, ";
		sql = sql + "EPPS.CLOSURE_CODE, ";
		sql = sql + "EPPS.MFG_DATE, ";
		sql = sql + "EPPS.ON_STABILITY_DATE, ";
		sql = sql + "NVL((SELECT dd.dictionary_value_description ";
		sql = sql + "FROM ea_dictionary_detail dd ";
		sql = sql + "WHERE dd.dictionary_value = psc.study_reason_code ";
		sql = sql + "AND dd.location_id = epps.location_id), psc.study_reason_code) study_reason_desc ";
		sql = sql + " from EA_PRD_STUDY_TEST_RESULTS ET,EA_PRD_STUDY_SCHEDULES ES ,EA_PRD_PRODUCT_TESTS PT,";
		sql = sql + "EA_PRD_PRODUCT_STUDY EPPS, ea_prd_study_conditions psc where ";
		sql = sql + "EPPS.PRD_STUDY_BATCH_ID = ES.PRD_STUDY_BATCH_ID " 
				  + "AND ES.PRD_STUDY_SCHEDULE_ID = ET.PRD_STUDY_SCHEDULE_ID "
				  + "AND ET.PRODUCT_TEST_ID = PT.PRODUCT_TEST_ID "
				  + "AND EPPS.PRD_STUDY_BATCH_ID = PSC.PRD_STUDY_BATCH_ID ";
		sql = sql + " and EPPS.LOCATION_ID=" + super.getCurrentLocationId();

		if (chartDataStr != "") {
			sql = sql + " and ET.PRODUCT_TEST_ID IN ( " + chartDataStr + " )";
		}
		if (id != "") {
			sql = sql + " and EPPS.STUDY_ID IN ( " + id + ")";
		}
		if (multipleProductId != "") {
			sql = sql + " and PT.PRODUCT_ID IN ( " + multipleProductId + " )";
		}
		if (Year != null && Year.length() > 0) {
			sql = sql + " and to_char(EPPS.ON_STABILITY_DATE, 'yyyy') = '"
					+ Year + "'";
		}

		String order = " order by EPPS.STUDY_ID, PT.SORT_ORDER_CURRENT, ES.INTERVAL_VALUE";
		sql = sql + order;
		SQLQuery q = persistenceManager.getCurrentSession().createSQLQuery(sql);
		q.addScalar("STUDY_ID", Hibernate.STRING);
		q.addScalar("PRODUCT_TEST_ID", Hibernate.LONG);
		q.addScalar("PRD_STUDY_SCHEDULE_ID", Hibernate.LONG);
		q.addScalar("INTERVAL_VALUE", Hibernate.LONG);
		q.addScalar("UPPER_LIMIT", Hibernate.STRING);
		q.addScalar("LOWER_LIMIT", Hibernate.STRING);
		q.addScalar("TEST_NAME", Hibernate.STRING);
		q.addScalar("RESULTS_FORMAT", Hibernate.STRING);
		q.addScalar("LOT_NUMBER", Hibernate.STRING);
		q.addScalar("STUDY_CONDITION", Hibernate.STRING);
		q.addScalar("PACKAGE_SIZE_CODE", Hibernate.STRING);
		q.addScalar("RESULT_AVERAGE", Hibernate.STRING);
		q.addScalar("RESULT_1", Hibernate.STRING);
		q.addScalar("MEASUREMENT", Hibernate.STRING);
		q.addScalar("INTERVAL_LABEL", Hibernate.STRING);
		q.addScalar("CONTAINER_CODE", Hibernate.STRING);
		q.addScalar("TEXT_LIMIT", Hibernate.STRING);
		q.addScalar("DECIMAL_POINTS", Hibernate.LONG);
		q.addScalar("RESULT_STATUS", Hibernate.STRING);
		q.addScalar("PACKAGE_SIZE_CODE", Hibernate.STRING);
		q.addScalar("CLOSURE_CODE", Hibernate.STRING);
		q.addScalar("MFG_DATE", Hibernate.DATE);
		q.addScalar("ON_STABILITY_DATE", Hibernate.DATE);
		q.addScalar("study_reason_desc", Hibernate.STRING);
		
		List details = q.list();

		List<DictionaryDetail> dictDetails = DictionaryFactory.getInstance()
				.getDictionaryDetailForCode("ABBREVIATION");
		Hashtable<String, String> hash = new Hashtable<String, String>();
		for (Iterator iterator = dictDetails.iterator(); iterator.hasNext();) {
			DictionaryDetail dictionaryDetail = (DictionaryDetail) iterator
					.next();
			if (dictionaryDetail.getGraphTo() != null
					&& dictionaryDetail.getGraphTo().length() > 0) {
				hash.put(dictionaryDetail.getDictionaryValue(),
						dictionaryDetail.getGraphTo());
			}
		}

		List<GraphGeneratorVO> list = new ArrayList<GraphGeneratorVO>();
		for (Iterator<Object[]> iterator = details.iterator(); iterator
				.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			GraphGeneratorVO vo = new GraphGeneratorVO();
			String object = (String) objects[12];
			if (object != null && object.trim().length() > 0) {
				try {
					double d = Double.parseDouble(object);
				} catch (NumberFormatException nfe) {
					// we have an alpha value. Need to get the
					// abbreviation from the dictionary and add it
					if (hash.containsKey(object)) {
						vo.setAbbreviationResult1(object);
						objects[12] = hash.get(object);
						objects[11] = hash.get(object);
					}
				}
			}
			vo.setStudyId((String) objects[0]);
			vo.setTestId((Long) objects[1]);
			vo.setPrdStudyScheduleId((Long) objects[2]);
			vo.setIntervalValue((Long) objects[3]);
			vo.setUpperLimit((String) objects[4]);
			vo.setLowerLimit((String) objects[5]);
			vo.setTestName((String) objects[6]);
			vo.setResultFormat((String) objects[7]);
			vo.setLotNumber((String) objects[8]);
			vo.setStudyCondition((String) objects[9]);
			vo.setFillUnits((String) objects[10]);
			vo.setResult1((String) objects[12]);
			vo.setMeasurement((String) objects[13]);
			vo.setIntervalLabel((String) objects[14]);
			vo.setContainer((String) objects[15]);
			vo.setTextLimit((String) objects[16]);
			vo.setDecimalPoints((Long) objects[17]);
			String resultStatus = (String) objects[18];
			String results;
			results = vo.getResultFormat();
			if (Constants.APPROVED.equals(resultStatus) && results.equals("V")
					&& vo.getResult1() != null && vo.getResult1().length() > 0) {
				try {
					Double d = Double.valueOf((String) objects[11]);
					vo.setAverageResult(d);					
				} catch (NumberFormatException e) {
					//ignore if no numbers
				}
			}
			vo.setPackageStr((String) objects[19]);
			vo.setClosure((String) objects[20]);
			vo.setManufacturingDate((Date) objects[21]);
			vo.setOnStabilityDate((Date) objects[22]);
			vo.setStudyReason((String) objects[23]);

			list.add(vo);
		}
		return list;
	}
	
	/**
	 * @param studyId
	 * @return
	 */
	public Long getStudyProductId(String studyId) {
		Long productId = null;
		Session session = persistenceManager.getCurrentSession();
		String sql = "select EPPS.PRODUCT_ID";
		sql = sql + " FROM EA_PRD_PRODUCT_STUDY EPPS"; 
		sql = sql + " WHERE EPPS.STUDY_ID = '" + studyId + "'";
		sql = sql + " AND EPPS.LOCATION_ID = " + super.getCurrentLocationId();
		SQLQuery q = persistenceManager.getCurrentSession().createSQLQuery(sql);
		q.addScalar("PRODUCT_ID", Hibernate.LONG);

		List details = q.list();
		if (details != null && details.size() > 0) {
			if (details.get(0) != null) {
				productId = ((Long) details.get(0)).longValue();;
			}
		}
		return productId;
	}

	/**
	 * @param productId
	 * @param studyId
	 * @param lotNumber
	 * @param passCondition
	 * @param sendYear
	 * @return
	 */
	public List getAllDataAccessEvaluation(Long productId, ArrayList studyId,
			String lotNumber, String passCondition, String sendYear) {

		String id = "";
		if (studyId != null && studyId.size() > 0) {
			for (Iterator iteratorStr = studyId.iterator(); iteratorStr
					.hasNext();) {
				if (id.length() > 0) {
					id = id + "," + "'" + iteratorStr.next().toString() + "'";
				} else {
					id = "'" + iteratorStr.next().toString() + "'";
				}
			}
		}

		Session session = persistenceManager.getCurrentSession();
		String sql = "select EPP.PRODUCT_ID,";
		sql = sql + "EPP.PRODUCT_CODE,";
		sql = sql + "EPP.PRODUCT_NAME,";
		sql = sql + "EPP.PRODUCT_DESCRIPTION,";
		sql = sql + "EPPS.STUDY_ID,";
		sql = sql + "EPPS.LOT_NUMBER,";
		sql = sql + "EPPS.ON_STABILITY_DATE,";
		sql = sql + "EPSC.PRD_STUDY_BATCH_ID,";
		sql = sql + "EPSC.NORMAL_ENV_CONDITIONS_CODE,";
		sql = sql + "EPSC.CHALLENGE_CONDITIONS_CODE,";
		sql = sql + "EPPS.PACKAGE_SIZE_CODE,";
		sql = sql + "EPPS.CONTAINER_CODE";
		sql = sql + " FROM EA_PRD_PRODUCTS EPP,EA_PRD_PRODUCT_STUDY EPPS,EA_PRD_STUDY_CONDITIONS EPSC ";
		sql = sql + " WHERE   EPP.PRODUCT_ID = EPPS.PRODUCT_ID AND EPPS.PRD_STUDY_BATCH_ID = EPSC.PRD_STUDY_BATCH_ID ";
		sql = sql + " and EPPS.LOCATION_ID=" + super.getCurrentLocationId();
		if (productId != null && productId.longValue() != 0) {
			sql = sql + " and EPP.PRODUCT_ID = " + productId;
		}
		if (id != "") {
			sql = sql + " and EPPS.STUDY_ID IN ( " + id + ")";
		}
		if (lotNumber != null && lotNumber.length() > 0) {
			sql = sql + " and EPPS.LOT_NUMBER = '" + lotNumber + "'";
		}

		// if (passCondition != null && passCondition.length() > 0) {
		sql = sql + " " + passCondition;
		// }
		if (sendYear != null && sendYear.length() > 0) {
			sql = sql + " and to_char(EPPS.ON_STABILITY_DATE, 'yyyy') = '"
					+ sendYear + "'";
		}
		sql = sql + " order by epps.study_id";

		List<DataAccessEvaluationVO> list = runDataEvaluationQuery(sql);
		return list;

	}

	public List getNewStudyDetails(String productId) {
		Session session = persistenceManager.getCurrentSession();
		String sql = "select Lot_Number,Study_id from Ea_prd_product_study where product_id = "
				+ productId;
		sql = sql + " and LOCATION_ID=" + super.getCurrentLocationId();

		SQLQuery q = persistenceManager.getCurrentSession().createSQLQuery(sql);
		q.addScalar("LOT_NUMBER", Hibernate.STRING);
		q.addScalar("STUDY_ID", Hibernate.STRING);

		List details = q.list();
		List<NewStudyProductVO> list = new ArrayList<NewStudyProductVO>();
		for (Iterator<Object[]> iterator = details.iterator(); iterator
				.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			NewStudyProductVO vo = new NewStudyProductVO();
			vo.setLotNumber((String) objects[0]);
			vo.setStudyId((String) objects[1]);
			list.add(vo);
		}
		return list;
	}

	public List getChartDisplayParameters(Long productId, ArrayList studyId,
			String lotNumber, String passCondition, String sendYear) {

		String id = "";
		if (studyId != null && studyId.size() > 0) {
			for (Iterator iteratorStr = studyId.iterator(); iteratorStr
					.hasNext();) {
				if (id.length() > 0) {
					id = id + "," + "'" + iteratorStr.next().toString() + "'";
				} else {
					id = "'" + iteratorStr.next().toString() + "'";
				}
			}
		}

		Session session = persistenceManager.getCurrentSession();
		String sql = "select EPP.PRODUCT_ID,";
		sql = sql + "EPP.PRODUCT_CODE,";
		sql = sql + "EPP.PRODUCT_NAME,";
		sql = sql + "EPP.PRODUCT_DESCRIPTION,";
		sql = sql + "EPPS.STUDY_ID,";
		sql = sql + "EPPS.LOT_NUMBER,";
		sql = sql + "EPPS.ON_STABILITY_DATE,";
		sql = sql + "EPSC.PRD_STUDY_BATCH_ID,";
		sql = sql + "EPSC.NORMAL_ENV_CONDITIONS_CODE,";
		sql = sql + "EPSC.CHALLENGE_CONDITIONS_CODE,";
		sql = sql + "EPPS.PACKAGE_SIZE_CODE,";
		sql = sql + "EPPS.CONTAINER_CODE";
		sql = sql
				+ " FROM EA_PRD_PRODUCTS EPP,EA_PRD_PRODUCT_STUDY EPPS,EA_PRD_STUDY_CONDITIONS EPSC WHERE   EPP.PRODUCT_ID = EPPS.PRODUCT_ID AND EPPS.PRD_STUDY_BATCH_ID = EPSC.PRD_STUDY_BATCH_ID ";
		sql = sql + " and EPPS.LOCATION_ID=" + super.getCurrentLocationId();
		if (productId != null) {
			sql = sql + " and EPP.PRODUCT_ID = " + productId;
		}
		if (id != "") {
			sql = sql + " and EPPS.STUDY_ID IN ( " + id + ")";
		} else {
		}
		if (lotNumber != null && lotNumber.length() > 0) {
			sql = sql + " and EPPS.LOT_NUMBER = '" + lotNumber + "'";
		} else {
		}

		// if (passCondition != null && passCondition.length() > 0) {
		sql = sql + " " + passCondition;
		// }
		if (sendYear != null && sendYear.length() > 0) {
			sql = sql + " and to_char(EPPS.ON_STABILITY_DATE, 'yyyy') = '"
					+ sendYear + "'";
		} else {
		}
		sql = sql + " order by epps.study_id";

		List<DataAccessEvaluationVO> list = runDataEvaluationQuery(sql);
		return list;

	}

	/**
	 * @param sql
	 * @return
	 */
	private List<DataAccessEvaluationVO> runDataEvaluationQuery(String sql) {
		SQLQuery q = persistenceManager.getCurrentSession().createSQLQuery(sql);
		q.addScalar("PRODUCT_ID", Hibernate.LONG);
		q.addScalar("PRODUCT_CODE", Hibernate.STRING);
		q.addScalar("PRODUCT_NAME", Hibernate.STRING);
		q.addScalar("PRODUCT_DESCRIPTION", Hibernate.STRING);
		q.addScalar("STUDY_ID", Hibernate.STRING);
		q.addScalar("LOT_NUMBER", Hibernate.STRING);
		q.addScalar("ON_STABILITY_DATE", Hibernate.DATE);
		q.addScalar("PRD_STUDY_BATCH_ID", Hibernate.LONG);
		q.addScalar("NORMAL_ENV_CONDITIONS_CODE", Hibernate.STRING);
		q.addScalar("CHALLENGE_CONDITIONS_CODE", Hibernate.STRING);
		q.addScalar("PACKAGE_SIZE_CODE", Hibernate.STRING);
		q.addScalar("CONTAINER_CODE", Hibernate.STRING);
		List details = q.list();
		List<DataAccessEvaluationVO> list = new ArrayList<DataAccessEvaluationVO>();
		for (Iterator<Object[]> iterator = details.iterator(); iterator
				.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			DataAccessEvaluationVO vo = new DataAccessEvaluationVO();
			vo.setProductId((Long) objects[0]);
			vo.setProductCode((String) objects[1]);
			vo.setProductName((String) objects[2]);
			vo.setProductDescription((String) objects[3]);
			vo.setStudyId((String) objects[4]);
			vo.setLotNumber((String) objects[5]);
			vo.setOnStabilityDate((Date) objects[6]);
			vo.setPrdStudyBatchId((Long) objects[7]);
			vo.setNormalEnvConditionsCode((String) objects[8]);
			vo.setChallengeConditionsCode((String) objects[9]);
			vo.setFillCode((String) objects[10]);
			vo.setContainerCode((String) objects[11]);
			String displayString = vo.getStudyId() + "/" + vo.getProductCode()
					+ "/" + vo.getProductName() + vo.getLotNumber() + "/"
					+ vo.getOnStabilityDate();
			if (vo.getNormalEnvConditionsCode() != null && vo.getNormalEnvConditionsCode().length() > 0) {
				displayString = displayString + "/Normal Condition:"
					+ vo.getNormalEnvConditionsCode();
			}
			if (vo.getChallengeConditionsCode() != null && vo.getChallengeConditionsCode().length() > 0) {
				displayString = displayString + "/Challenge Condition:"
					+ vo.getChallengeConditionsCode();
			}
			vo.setDisplayString(displayString);
			list.add(vo);
		}
		return list;
	}

	public List getChartDisplayParametersTest(ArrayList productId,
			ArrayList studyId, String lotNumber, String passCondition,
			String sendYear) {

		String id = "";
		String pid = "";
		if (productId != null && productId.size() > 0) {
			for (Iterator iteratorPStr = productId.iterator(); iteratorPStr
					.hasNext();) {
				if (pid.length() > 0) {
					pid = pid + "," + "'" + iteratorPStr.next().toString()
							+ "'";
				} else {
					pid = "'" + iteratorPStr.next().toString() + "'";
				}
			}
		}

		if (studyId != null && studyId.size() > 0) {
			for (Iterator iteratorStr = studyId.iterator(); iteratorStr
					.hasNext();) {
				if (id.length() > 0) {
					id = id + "," + "'" + iteratorStr.next().toString() + "'";
				} else {
					id = "'" + iteratorStr.next().toString() + "'";
				}
			}
		}

		Session session = persistenceManager.getCurrentSession();
		String sql = "select EPP.PRODUCT_ID,";
		sql = sql + "EPP.PRODUCT_CODE,";
		sql = sql + "EPP.PRODUCT_NAME,";
		sql = sql + "EPP.PRODUCT_DESCRIPTION,";
		sql = sql + "EPPS.STUDY_ID,";
		sql = sql + "EPPS.LOT_NUMBER,";
		sql = sql + "EPPS.ON_STABILITY_DATE,";
		sql = sql + "EPSC.PRD_STUDY_BATCH_ID,";
		sql = sql + "EPSC.NORMAL_ENV_CONDITIONS_CODE,";
		sql = sql + "EPSC.CHALLENGE_CONDITIONS_CODE,";
		sql = sql + "EPPS.PACKAGE_SIZE_CODE,";
		sql = sql + "EPPS.CONTAINER_CODE";
		sql = sql
				+ " FROM EA_PRD_PRODUCTS EPP,EA_PRD_PRODUCT_STUDY EPPS,EA_PRD_STUDY_CONDITIONS EPSC WHERE   EPP.PRODUCT_ID = EPPS.PRODUCT_ID AND EPPS.PRD_STUDY_BATCH_ID = EPSC.PRD_STUDY_BATCH_ID ";
		sql = sql + " and EPPS.LOCATION_ID=" + super.getCurrentLocationId();
		if (productId != null) {
			sql = sql + " and EPP.PRODUCT_ID IN ( " + pid + ")";
		}
		if (id != "") {
			sql = sql + " and EPPS.STUDY_ID IN ( " + id + ")";
		} else {
		}
		if (lotNumber != null && lotNumber.length() > 0) {
			sql = sql + " and EPPS.LOT_NUMBER = '" + lotNumber + "'";
		} else {
		}

		// if (passCondition != null && passCondition.length() > 0) {
		sql = sql + " " + passCondition;
		// }
		if (sendYear != null && sendYear.length() > 0) {
			sql = sql + " and to_char(EPPS.ON_STABILITY_DATE, 'yyyy') = '"
					+ sendYear + "'";
		} else {
		}
		sql = sql + " order by epps.study_id";

		List<DataAccessEvaluationVO> list = runDataEvaluationQuery(sql);
		return list;

	}

	public List getNumberOfResults(ArrayList chartData, String studyId) {

		String id = "";
		String tid = "";
		if (chartData != null && chartData.size() > 0) {
			for (Iterator iteratorPStr = chartData.iterator(); iteratorPStr
					.hasNext();) {
				if (tid.length() > 0) {
					tid = tid + "," + "'" + iteratorPStr.next().toString()
							+ "'";
				} else {
					tid = "'" + iteratorPStr.next().toString() + "'";
				}
			}
		}

		Session session = persistenceManager.getCurrentSession();
		String sql = "SELECT erfs.study_id,";
		sql = sql + "erfs.product_test_id,";
		sql = sql
				+ "ppt.test_name,ppt.sort_order_current,MAX (erfs.number_of_results) as MAX_RESULT FROM ";
		sql = sql
				+ " eavw_results_for_study erfs, ea_prd_product_tests ppt WHERE   erfs.product_test_id = ppt.product_test_id ";
		sql = sql + " and erfs.LOCATION_ID=" + super.getCurrentLocationId();
		if (chartData != null) {
			sql = sql + " and erfs.product_test_id IN ( " + tid + ")";
		}

		if (studyId != null && studyId.length() > 0) {
			sql = sql + " and erfs.study_id = '" + studyId + "'";
		}
		sql = sql
				+ " GROUP BY erfs.study_id,erfs.product_test_id,PPT.TEST_NAME,PPT.SORT_ORDER_CURRENT ORDER BY erfs.study_id, PPT.SORT_ORDER_CURRENT";

		SQLQuery q = persistenceManager.getCurrentSession().createSQLQuery(sql);
		q.addScalar("STUDY_ID", Hibernate.STRING);
		q.addScalar("PRODUCT_TEST_ID", Hibernate.LONG);
		q.addScalar("TEST_NAME", Hibernate.STRING);
		q.addScalar("SORT_ORDER_CURRENT", Hibernate.LONG);
		q.addScalar("MAX_RESULT", Hibernate.LONG);

		List details = q.list();

		List<RegressionReportVO> list = new ArrayList<RegressionReportVO>();
		for (Iterator<Object[]> iterator = details.iterator(); iterator
				.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			RegressionReportVO vo = new RegressionReportVO();
			vo.setStudyId((String) objects[0]);
			vo.setprdTestId((Long) objects[1]);
			vo.setTestName((String) objects[2]);
			vo.setsortOrderCurrent((Long) objects[3]);
			vo.setnumberOfResults((Long) objects[4]);
			list.add(vo);

		}
		return list;
	}

}
