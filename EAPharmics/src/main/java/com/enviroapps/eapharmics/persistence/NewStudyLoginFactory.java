package com.enviroapps.eapharmics.persistence;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.enviroapps.eapharmics.bom.admin.ApplParameter;
import com.enviroapps.eapharmics.bom.dictionary.DictionaryDetail;
import com.enviroapps.eapharmics.bom.newstudy.BarCode;
import com.enviroapps.eapharmics.bom.newstudy.Image;
import com.enviroapps.eapharmics.bom.newstudy.Label;
import com.enviroapps.eapharmics.bom.newstudy.LabelData;
import com.enviroapps.eapharmics.bom.newstudy.LabelPrintRequest;
import com.enviroapps.eapharmics.bom.newstudy.LabelPrintRequestDetail;
import com.enviroapps.eapharmics.bom.newstudy.NewStudyCondition;
import com.enviroapps.eapharmics.bom.newstudy.NewStudyInterval;
import com.enviroapps.eapharmics.bom.newstudy.NewStudyProduct;
import com.enviroapps.eapharmics.bom.newstudy.NewStudyRawMaterial;
import com.enviroapps.eapharmics.bom.newstudy.NewStudySchedule;
import com.enviroapps.eapharmics.bom.newstudy.NewStudyTestResult;
import com.enviroapps.eapharmics.bom.newstudy.StudyInventoryDestroyed;
import com.enviroapps.eapharmics.bom.product.ProductTest;
import com.enviroapps.eapharmics.common.Utility;
import com.enviroapps.eapharmics.das.persistence.DataAccessConstants;
import com.enviroapps.eapharmics.das.persistence.hibernate.HibernatePersistenceFactory;
import com.enviroapps.eapharmics.das.persistence.hibernate.HibernatePersistenceManager;
import com.enviroapps.eapharmics.das.persistence.hibernate.HibernatePersistenceManagerFactory;
import com.enviroapps.eapharmics.exception.EAPharmicsException;
import com.enviroapps.eapharmics.ui.Constants;
import com.enviroapps.eapharmics.vo.newstudy.CodeSwitchProductVO;
import com.enviroapps.eapharmics.vo.newstudy.DataEntryVO;
import com.enviroapps.eapharmics.vo.newstudy.GraphGeneratorVO;
import com.enviroapps.eapharmics.vo.newstudy.LabelPrintRequestDisplayVO;
import com.enviroapps.eapharmics.vo.newstudy.LabelVO;
import com.enviroapps.eapharmics.vo.newstudy.NewStudyConditionVO;
import com.enviroapps.eapharmics.vo.newstudy.NewStudyProductLotContainerVO;
import com.enviroapps.eapharmics.vo.newstudy.NewStudyProductLotVO;
import com.enviroapps.eapharmics.vo.newstudy.NewStudyProductSummaryVO;
import com.enviroapps.eapharmics.vo.newstudy.NewStudySummaryVO;
import com.enviroapps.eapharmics.vo.newstudy.ProductStudyIntervalsVO;
import com.enviroapps.eapharmics.vo.newstudy.RegressionDataContainerVO;
import com.enviroapps.eapharmics.vo.newstudy.RegressionInputVO;
import com.enviroapps.eapharmics.vo.newstudy.ReportCountVO;
import com.enviroapps.eapharmics.vo.newstudy.RptUserPreferenceVO;
import com.enviroapps.eapharmics.vo.newstudy.StoredDataVO;
import com.enviroapps.eapharmics.vo.security.UserVO;


/**
 * @author EnviroApps
 *
 */
public class NewStudyLoginFactory extends HibernatePersistenceFactory implements
	DataAccessConstants {

	private static final Logger logger = LoggerFactory.getLogger(NewStudyLoginFactory.class);

	private NewStudyLoginFactory() {
		persistenceManager = (HibernatePersistenceManager) HibernatePersistenceManagerFactory
				.getInstance().getPersistenceManager();
		logger.debug("NewStudyLoginFactory", persistenceManager);
	}

	private static NewStudyLoginFactory instance = new NewStudyLoginFactory();

	public static NewStudyLoginFactory getInstance() {
		return instance;
	}

	/**
	 * @return
	 */
	public List getNewStudyProductsSummary(String barCode, String studyId,
			Long productId, String lotNumber,String year) throws EAPharmicsException {
		return getNewStudyProductsSummary(barCode, studyId, productId, lotNumber, year, null, null,false);
	}
	
	/**
	 * @return
	 */
	public List getNewStudyProductsSummary(String barCodes, String studyId,
			Long productId, String lotNumber,String year, Date fromDate, Date toDate, boolean unApprovedStudiesOnly) throws EAPharmicsException {
		String sql = "select nsp.prd_study_batch_id, nsp.product_id, nsp.lot_number, ";
		sql = sql + " p.product_code, p.product_name, p.product_description, nsp.label_id, ";
		sql = sql + " NVL (psc.normal_env_conditions_code, ";
        sql = sql + "     psc.challenge_conditions_code)";
        sql = sql + "     study_condition, ";
		sql = sql + " nsp.study_id, ";
		sql = sql + " nsp.propreitary_study_id, ";
		sql = sql + " nsp.expiry, nsp.batch_size, nsp.package_date, nsp.mfg_date, ";
		sql = sql + " nsp.on_stability_date, nsp.study_status, nsp.approval_level1_yn ";
		sql = sql + "from ea_prd_product_study nsp, ea_prd_products p, ea_prd_study_conditions psc ";
		sql = sql + "where nsp.product_id = p.product_id ";
		sql = sql + "and nsp.prd_study_batch_id = PSC.prd_study_batch_id ";
		sql = sql + "and nsp.location_id = " + super.getCurrentLocationId() + " ";
		if (barCodes != null && barCodes.length() > 0) {
			String delimiter = "\n";
			if (barCodes.indexOf("\r") >= 0) {
				delimiter = "\r";
			}
			StringTokenizer st = new StringTokenizer(barCodes, delimiter);
			StringBuffer sb = null;
			while (st.hasMoreTokens()) {
				String barCode = st.nextToken();
				if (barCode != null && barCode.trim().length() > 0) {
					Hashtable<String, String> map = BarCode.getBarCodeElements(barCode);
					if (map.containsKey(Constants.STUDY_ID)) {
						if (sb != null) {
							sb.append(",");
						} else {
							sb = new StringBuffer();
						}
						sb.append("'" + map.get(Constants.STUDY_ID) + "'");
					}
				}
			}
			if (sb != null) {
				sql = sql + "and nsp.study_id in (" + sb.toString() + ") ";
			}
		} else {
			if (studyId != null && studyId.length() > 0) {
				if (studyId.indexOf("*") >= 0) {
					sql = sql + "and nsp.study_id LIKE '" + studyId.replace("*", "%") + "'";
				} else {
					sql = sql + "and nsp.study_id = '" + studyId + "'";
				}
			}
			if (productId != null && productId.longValue() > 0) {
				sql = sql + "and p.product_id ='" + productId + "'";
			}	
			if (lotNumber != null && lotNumber.length() > 0) {
				if (lotNumber.indexOf("*") >= 0) {
					sql = sql + "and nsp.lot_number LIKE'" + lotNumber.replace("*", "%") + "'";
				} else {
					sql = sql + "and nsp.lot_number ='" + lotNumber + "'";
				}
			}
			if (year != null && year.length() > 0){
			   sql = sql + " and TO_CHAR(nsp.on_stability_date,'YYYY')" + " = '"+year+"'";
			}
			if (unApprovedStudiesOnly)
			{
			   sql = sql + " and nsp.approval_level1_yn = 'N'";
			}
			sql = sql + super.getQueryString("nsp.on_stability_date", fromDate, toDate, true);
		}
		
		sql = sql + " order by  nsp.study_id";
		
		SQLQuery q = persistenceManager.getCurrentSession().createSQLQuery(sql);
		q.addScalar("prd_study_batch_id", Hibernate.LONG);
		q.addScalar("product_id", Hibernate.LONG);
		q.addScalar("lot_number", Hibernate.STRING);
		q.addScalar("product_code", Hibernate.STRING);
		q.addScalar("product_name", Hibernate.STRING);
		q.addScalar("product_description", Hibernate.STRING);
		q.addScalar("label_id", Hibernate.LONG);
		q.addScalar("study_condition", Hibernate.STRING);
		q.addScalar("study_id", Hibernate.STRING);
		q.addScalar("propreitary_study_id", Hibernate.STRING);
		q.addScalar("expiry", Hibernate.STRING);
		q.addScalar("batch_size", Hibernate.STRING);
		q.addScalar("package_date", Hibernate.DATE);
		q.addScalar("mfg_date", Hibernate.DATE);
		q.addScalar("on_stability_date", Hibernate.DATE);
		q.addScalar("study_status", Hibernate.STRING);
		
		q.addScalar("approval_level1_yn", Hibernate.YES_NO);
		
		List details = q.list();
		super.checkResultSize(details);
	    List<NewStudyProductSummaryVO> list = new ArrayList<NewStudyProductSummaryVO>();
	    for (Iterator<Object[]> iterator = details.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			NewStudyProductSummaryVO vo = new NewStudyProductSummaryVO();
			vo.setPrdStudyBatchId((Long) objects[0]);
			vo.setProductId((Long) objects[1]);
			vo.setLotNumber((String) objects[2]);
			vo.setProductCode((String) objects[3]);
			vo.setProductName((String) objects[4]);
			vo.setProductDescription((String) objects[5]);
			vo.setLabelId((Long) objects[6]);
			vo.setStudyCondition((String) objects[7]);
			vo.setStudyId((String) objects[8]);
			vo.setProprietaryStudyId((String) objects[9]);
			String displayString = vo.getProductCode() + "/" + vo.getStudyId() + "/"
				+ vo.getLotNumber() + "/" + vo.getStudyCondition();
			vo.setDisplayString(displayString);

			vo.setExpiry((String) objects[10]);
			vo.setBatchSize((String) objects[11]);
			vo.setPackageDate((Date) objects[12]);
			vo.setMfgDate((Date) objects[13]);
			vo.setOnStabilityDate((Date) objects[14]);
			vo.setStudyStatus((String) objects[15]);
			
			//vo.setExpiry((String) objects[15]);

			vo.setApprovalLevel1((Boolean) objects[16]);
			
			list.add(vo);
		}
		return list;
	}

	public List getNewStudyIntervalLabel(String studyId){
		
		Session session = persistenceManager.getCurrentSession();
		String sql="SELECT  psc.interval_label FROM   ea_prd_study_conditions psc, ea_prd_product_study pps ";
		sql=sql + " WHERE   psc.prd_study_batch_id = pps.prd_study_batch_id ";
		sql = sql + "and pps.location_id = " + super.getCurrentLocationId() + " ";
		if (studyId != null && studyId.length() > 0) {
			sql = sql + "and pps.study_id  = '" + studyId + "'";
		}
		SQLQuery q = persistenceManager.getCurrentSession().createSQLQuery(sql);
		q.addScalar("INTERVAL_LABEL", Hibernate.STRING);
		List<String> details = q.list();
		
		List<NewStudyConditionVO> list = new ArrayList<NewStudyConditionVO>();
		NewStudyConditionVO vo = new NewStudyConditionVO();
		vo.setIntervalLabel(details.get(0));
		list.add(vo);		
		return list;
	}

	/**
	 * @param eaNewStudyProductId
	 * @return
	 */
	public NewStudyProduct getNewStudyProduct(Long eaNewStudyProductId) {
		Session session = persistenceManager.getCurrentSession();
		NewStudyProduct newStudyProduct = (NewStudyProduct) session.get(NewStudyProduct.class, eaNewStudyProductId);
		return newStudyProduct;
	}

	/**
	 * @param newStudyProduct
	 */
	public void createNewStudyProduct(NewStudyProduct newStudyProduct) {
		Session session = persistenceManager.getCurrentSession();
		newStudyProduct.setLocationId(super.getCurrentLocationId());
		session.saveOrUpdate(newStudyProduct);
	}

	/**
	 * @param newStudyProduct
	 */
	public void updateNewStudyProduct(NewStudyProduct newStudyProduct) {
		Session session = persistenceManager.getCurrentSession();
		session.saveOrUpdate(newStudyProduct);
	}

	/**
	 * @param prdStudyBatchId
	 */
	public void deleteNewStudyProduct(Long prdStudyBatchId) {
		Session session = persistenceManager.getCurrentSession();
		NewStudyProduct classVariable = (NewStudyProduct) session.get(NewStudyProduct.class, prdStudyBatchId);
		session.delete(classVariable);
	}


	/**
	 * @param eaNewStudyRawMaterialId
	 * @return
	 */
	public NewStudyRawMaterial getNewStudyRawMaterial(Long eaNewStudyRawMaterialId) {
		Session session = persistenceManager.getCurrentSession();
		NewStudyRawMaterial newStudyRawMaterial = (NewStudyRawMaterial) session.load(NewStudyRawMaterial.class, eaNewStudyRawMaterialId);
		return newStudyRawMaterial;
	}

	/**
	 * @param newStudyRawMaterial
	 */
	public void createNewStudyRawMaterial(NewStudyRawMaterial newStudyRawMaterial) {
		Session session = persistenceManager.getCurrentSession();
		session.saveOrUpdate(newStudyRawMaterial);
	}

	/**
	 * @param newStudyRawMaterial
	 */
	public void updateNewStudyRawMaterial(NewStudyRawMaterial newStudyRawMaterial) {
		Session session = persistenceManager.getCurrentSession();
		session.saveOrUpdate(newStudyRawMaterial);
	}

	/**
	 * @param prdStudyRawMaterialId
	 */
	public void deleteNewStudyRawMaterial(Long prdStudyRawMaterialId) {
		Session session = persistenceManager.getCurrentSession();
		NewStudyRawMaterial classVariable = (NewStudyRawMaterial) session.get(NewStudyRawMaterial.class, prdStudyRawMaterialId);
		session.delete(classVariable);
	}


	/**
	 * @return
	 */
	public NewStudyCondition getAllNewStudyConditions(Long prdStudyBatchId) {
		Session session = persistenceManager.getCurrentSession();
		List list = session.createCriteria(NewStudyCondition.class)
               .add(Restrictions.eq("prdStudyBatchId", prdStudyBatchId))
					.list();
		if (list.size() > 0) {
		   return(NewStudyCondition) list.get(0);
		}
		return null;
	}

	/**
	 * @param eaNewStudyConditionId
	 * @return
	 */
	public NewStudyCondition getNewStudyCondition(Long eaNewStudyConditionId) {
		Session session = persistenceManager.getCurrentSession();
		NewStudyCondition newStudyCondition = (NewStudyCondition) session.load(NewStudyCondition.class, eaNewStudyConditionId);
		return newStudyCondition;
	}

	/**
	 * @param newStudyCondition
	 */
	public void createNewStudyCondition(NewStudyCondition newStudyCondition) {
		Session session = persistenceManager.getCurrentSession();
		session.saveOrUpdate(newStudyCondition);
	}

	/**
	 * @param newStudyCondition
	 */
	public void updateNewStudyCondition(NewStudyCondition newStudyCondition) {
		Session session = persistenceManager.getCurrentSession();
		session.saveOrUpdate(newStudyCondition);
	}

	/**
	 * @param prdStudyConditionId
	 */
	public void deleteNewStudyCondition(Long prdStudyConditionId) {
		Session session = persistenceManager.getCurrentSession();
		NewStudyCondition classVariable = (NewStudyCondition) session.get(NewStudyCondition.class, prdStudyConditionId);
		session.delete(classVariable);
	}
	
	/**
	 * @param prdStudyBatchId
	 */

	public void deleteNewStudyIntervalId(Long prdStudyBatchId) {
		Session session = persistenceManager.getCurrentSession();
		NewStudyInterval classVariable = (NewStudyInterval) session.get(
				NewStudyInterval.class, prdStudyBatchId);
	}

	public void deleteNewStudyIntervalsForStudyId(Long prdStudyBatchId) {
		Session session = persistenceManager.getCurrentSession();
		List list = session.createCriteria(NewStudyInterval.class)
               .add(Restrictions.eq("prdStudyBatchId", prdStudyBatchId))
               .list();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			NewStudyInterval interval = (NewStudyInterval) iterator.next();
			session.delete(interval);
		}
	}


	public List getNewStudyIntervalsForStudy(Long prdStudyBatchId) {
		Session session = persistenceManager.getCurrentSession();
		List list = session.createCriteria(NewStudyInterval.class)
               .add(Restrictions.eq("prdStudyBatchId", prdStudyBatchId))
			   .addOrder(Order.asc("prdStudyIntervalId"))
               .list();
		return list;
	}
	
	/**
	 * @param eaNewStudyIntervalId
	 * @return
	 */
	public NewStudyInterval getNewStudyInterval(Long eaNewStudyIntervalId) {
		Session session = persistenceManager.getCurrentSession();
		NewStudyInterval newStudyInterval = (NewStudyInterval) session.load(NewStudyInterval.class, eaNewStudyIntervalId);
		return newStudyInterval;
	}

	/**
	 * @param newStudyInterval
	 */
	public void createNewStudyInterval(NewStudyInterval newStudyInterval) {
		Session session = persistenceManager.getCurrentSession();
		session.saveOrUpdate(newStudyInterval);
	}

	/**
	 * @param newStudyInterval
	 */
	public void updateNewStudyInterval(NewStudyInterval newStudyInterval) {
		Session session = persistenceManager.getCurrentSession();
		session.saveOrUpdate(newStudyInterval);
	}

	/**
	 * @param prdStudyIntervalId
	 */
	public void deleteNewStudyInterval(Long prdStudyIntervalId) {
		Session session = persistenceManager.getCurrentSession();
		NewStudyInterval classVariable = (NewStudyInterval) session.get(NewStudyInterval.class, prdStudyIntervalId);
		session.delete(classVariable);
	}
	

	/**
	 * @return
	 */
	public List getAllLabels() {
		Session session = persistenceManager.getCurrentSession();
		String sql = "SELECT LABEL_ID, LABEL_NAME, UPDATE_DATE FROM EA_PRD_LABELS ORDER BY LABEL_ID";
		//sql = sql + "and location_id = " + super.getCurrentLocationId() + " ";
		SQLQuery q = persistenceManager.getCurrentSession().createSQLQuery(sql);
		q.addScalar("LABEL_ID", Hibernate.LONG);
		q.addScalar("LABEL_NAME", Hibernate.STRING);
		q.addScalar("UPDATE_DATE", Hibernate.DATE);
	    List details = q.list();
	    List<LabelVO> list = new ArrayList<LabelVO>();
	    for (Iterator<Object[]> iterator = details.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			LabelVO vo = new LabelVO();
			vo.setLabelId((Long) objects[0]);
			vo.setLabelName((String) objects[1]);
			vo.setUpdateDate((Date) objects[2]);
			Label l = getLabel(vo.getLabelId());
//			System.out.println("Id:" + vo.getLabelId());
//			System.out.println(new String(l.getLabelData()));
			list.add(vo);
		}
		return list;
	}

	/**
	 * @param labelId
	 * @return
	 */
	public Label getLabel(Long labelId) {
		Session session = persistenceManager.getCurrentSession();
		Label Label = (Label) session.load(Label.class, labelId);
		return Label;
	}

	/**
	 * @param labelId
	 * @return
	 */
	public Label getLabelForName(String labelName) {
		Session session = persistenceManager.getCurrentSession();
		List list = session.createCriteria(Label.class)
			.add(Restrictions.eq("labelName", labelName))
			.list();
		if (list.size() > 0){
			return (Label) list.get(0);
		}
		return null;
	}

	/**
	 * @param label
	 */
	public void createLabel(Label label) {
		Session session = persistenceManager.getCurrentSession();
		//label.setLocationId(super.getSelectedLocationId());
		session.saveOrUpdate(label);
	}

	/**
	 * @param label
	 */
	public void updateLabel(Label label) {
		Session session = persistenceManager.getCurrentSession();
		session.saveOrUpdate(label);
	}

	/**
	 * @param labelId
	 */
	public void deleteLabel(Long labelId) {
		Session session = persistenceManager.getCurrentSession();
		Label classVariable = (Label) session.get(Label.class, labelId);
		session.delete(classVariable);
	}

	/**
	 * @param imageId
	 * @return
	 */
	public Image getImage(Long imageId) {
		Session session = persistenceManager.getCurrentSession();
		Image Image = (Image) session.load(Image.class, imageId);
		return Image;
	}

	/**
	 * @param labelId
	 * @return
	 */
	public Image getImageForLabelElement(Long labelId, String elementName) {
		Session session = persistenceManager.getCurrentSession();
		List list = session.createCriteria(Label.class)
			.add(Restrictions.eq("elementName", elementName))
			.add(Restrictions.eq("labelId", labelId)).list();
		if (list.size() > 0){
			return (Image) list.get(0);
		}
		return null;
	}

	/**
	 * @param labelId
	 * @return
	 */
	public Label getLabelForLabelID(Long labelId) {
		Session session = persistenceManager.getCurrentSession();
		List list = session.createCriteria(Label.class)
			.add(Restrictions.eq("labelId", labelId)).list();
		if (list.size() > 0){
			return (Label) list.get(0);
		}
		return null;
	}

	/**
	 * @param labelId
	 * @return
	 */
	public Image getImageForLabel(String elementName) {
		Session session = persistenceManager.getCurrentSession();
		List list = session.createCriteria(Label.class)
			.add(Restrictions.eq("elementName", elementName))
			.list();
		if (list.size() > 0){
			return (Image) list.get(0);
		}
		return null;
	}

	/**
	 * @param Image
	 */
	public void createImage(Image Image) {
		Session session = persistenceManager.getCurrentSession();
		session.saveOrUpdate(Image);
	}

	/**
	 * @param Image
	 */
	public void updateImage(Image Image) {
		Session session = persistenceManager.getCurrentSession();
		session.saveOrUpdate(Image);
	}

	/**
	 * @param imageId
	 */
	public void deleteImage(Long imageId) {
		Session session = persistenceManager.getCurrentSession();
		Image classVariable = (Image) session.get(Image.class, imageId);
		session.delete(classVariable);
	}

	/**
	 * @param labelId
	 * @return
	 */
	public List getLabelData(Long labelId, Long eaLabelPrintRequestsId) {
		Session session = persistenceManager.getCurrentSession();
		List<Long> list1 = new ArrayList <Long>();
		if (eaLabelPrintRequestsId != null){
			list1.add(eaLabelPrintRequestsId);
		} else {
			list1 = session.createSQLQuery("SELECT EA_LABEL_PRINT_REQUESTS_ID FROM EA_LABEL_PRINT_REQUESTS WHERE LABEL_PRINTED_YN = 'N' AND LABEL_ID = " + labelId)
			.addScalar("EA_LABEL_PRINT_REQUESTS_ID", Hibernate.LONG).list();
		}
		List list = session.createCriteria(LabelData.class)
			.add(Restrictions.in("eaLabelPrintRequestsId", list1))
			.addOrder(Order.asc("studyId"))
			.addOrder(Order.asc("eaLabelPrintRequestsId"))
			.addOrder(Order.asc("intervalDate"))
			.addOrder(Order.desc("extraLabel"))
			.addOrder(Order.asc("labelNumber"))
			.list();
		return list;
	}

	/**
	 * @return
	 */
	public List getAllPendingLabelPrintRequestsDisplay() {
		Session session = persistenceManager.getCurrentSession();
		String sql = "select to_char(pp.product_name || '/' || pps.study_id || '/' || pps.lot_number || '/' || ";
		sql = sql + "    NVL (psc.normal_env_conditions_code, psc.challenge_conditions_code) ) as LABEL_REQUEST, ";
		sql = sql + "     pl.label_name, pl.label_id, pps.prd_study_batch_id, lpr.EA_LABEL_PRINT_REQUESTS_ID ";
		sql = sql + "from EA_LABEL_PRINT_REQUESTS lpr, EA_PRD_PRODUCT_STUDY pps, EA_PRD_LABELS pl, EA_PRD_PRODUCTS pp, EA_PRD_STUDY_CONDITIONS psc ";
		sql = sql + "where lpr.prd_study_batch_id = pps.prd_study_batch_id ";
		sql = sql + "and   lpr.label_id = pl.label_id ";
		sql = sql + "and   pps.product_id = pp.product_id ";
		sql = sql + "and   pps.prd_study_batch_id = psc.prd_study_batch_id ";
		sql = sql + "and lpr.label_printed_yn = 'N'";
		sql = sql + "and pps.location_id = " + super.getCurrentLocationId() + " ";
		SQLQuery q = persistenceManager.getCurrentSession().createSQLQuery(sql);
		q.addScalar("LABEL_REQUEST", Hibernate.STRING);
		q.addScalar("LABEL_NAME", Hibernate.STRING);
		q.addScalar("LABEL_ID", Hibernate.LONG);
		q.addScalar("EA_LABEL_PRINT_REQUESTS_ID", Hibernate.LONG);
	    List details = q.list();
	    List<LabelPrintRequestDisplayVO> list = new ArrayList<LabelPrintRequestDisplayVO>();
	    for (Iterator<Object[]> iterator = details.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			LabelPrintRequestDisplayVO vo = new LabelPrintRequestDisplayVO();
			vo.setLabelRequest((String) objects[0]);
			vo.setLabelName((String) objects[1]);
			vo.setLabelId((Long) objects[2]);
			vo.setEaLabelPrintRequestsId((Long)objects[3]);
			list.add(vo);
		}
		return list;
	}
	
	/**
	 * @param eaLabelPrintRequestGroupId
	 * @param labelId
	 * @return
	 */
	public List getAllPendingLabelPrintRequests(Long labelId) {
		Session session = persistenceManager.getCurrentSession();
		List list = session.createCriteria(LabelPrintRequest.class)
               .add(Restrictions.eq("labelId", labelId))
			.addOrder(Order.asc("labelId"))
			.addOrder(Order.asc("prdStudyBatchId"))
               .list();
		return list;
	}


	/**
	 * @return
	 */
	public List getAllLabelPrintRequests() {
		Session session = persistenceManager.getCurrentSession();
		List list = session.createCriteria(LabelPrintRequest.class)
			.addOrder(Order.asc("labelId"))
			.addOrder(Order.asc("prdStudyBatchId"))
					.list();
		return list;
	}

	/**
	 * @param eaLabelPrintRequestId
	 * @return
	 */
	public Long getLabelTypeToPrint() {
		Session session = persistenceManager.getCurrentSession();
		String sql = "SELECT DISTINCT A.LABEL_ID FROM EA_LABEL_PRINT_REQUESTS A, EA_PRD_PRODUCT_STUDY B WHERE A.PRD_STUDY_BATCH_ID = B.prd_study_batch_id";
		sql = sql + " AND B.LOCATION_ID = " + super.getCurrentLocationId(); 
		sql = sql + " AND A.LABEL_PRINTED_YN = 'N' ORDER BY A.LABEL_ID";
		List details = session.createSQLQuery(sql).addScalar("LABEL_ID", Hibernate.LONG).list();
		if (details != null && details.size() > 0) {
			if (details.get(0) != null) {
				return (Long) details.get(0);
			}
		}
		return null;
	}

	/**
	 * @param labelId
	 */
	public void updatePendingLabelPrintRequest(Long labelId, Long eaLabelPrintRequestsId, Long appUserId) {
		Session session = persistenceManager.getCurrentSession();
		String sql = "UPDATE EA_LABEL_PRINT_REQUESTS SET LABEL_PRINTED_YN = 'Y' ";
			sql = sql + ", UPDATE_USER=" + appUserId;
			sql = sql + ", UPDATE_DATE=SYSDATE";
			sql = sql + " WHERE LABEL_PRINTED_YN = 'N' AND LABEL_ID = " + labelId;
		if (eaLabelPrintRequestsId != null) {
			sql = sql + " AND EA_LABEL_PRINT_REQUESTS_ID=" + eaLabelPrintRequestsId;
		}
		session.createSQLQuery(sql).executeUpdate();
	}

	/**
	 * @param eaLabelPrintRequestId
	 * @return
	 */
	public LabelPrintRequest getLabelPrintRequest(Long eaLabelPrintRequestId) {
		Session session = persistenceManager.getCurrentSession();
		LabelPrintRequest labelPrintRequest = (LabelPrintRequest) session.load(LabelPrintRequest.class, eaLabelPrintRequestId);
		return labelPrintRequest;
	}

	/**
	 * @param labelPrintRequest
	 */
	public void createLabelPrintRequest(LabelPrintRequest labelPrintRequest) {
		Session session = persistenceManager.getCurrentSession();
		session.saveOrUpdate(labelPrintRequest);
	}

	/**
	 * @param labelPrintRequestDetail
	 */
	public void createLabelPrintRequestDetail(LabelPrintRequestDetail labelPrintRequestDetail) {
		Session session = persistenceManager.getCurrentSession();
		session.saveOrUpdate(labelPrintRequestDetail);
	}

	/**
	 * @param labelPrintRequest
	 */
	public void updateLabelPrintRequest(LabelPrintRequest labelPrintRequest) {
		Session session = persistenceManager.getCurrentSession();
		session.saveOrUpdate(labelPrintRequest);
	}

	/**
	 * @param eaLabelPrintRequestsId
	 */
	public void deleteLabelPrintRequest(Long eaLabelPrintRequestsId) {
		Session session = persistenceManager.getCurrentSession();
		LabelPrintRequest classVariable = (LabelPrintRequest) session.get(LabelPrintRequest.class, eaLabelPrintRequestsId);
		session.delete(classVariable);
	}

	public List getNewStudyIntervalValueChartData(Long prdStudyBatchId) {
		Session session = persistenceManager.getCurrentSession();
		List list = session.createCriteria(NewStudyInterval.class)
               .add(Restrictions.eq("prdStudyBatchId", prdStudyBatchId))
			   .addOrder(Order.asc("prdStudyBatchId"))
               .list();
		return list;
	}
	
	
	
	public List getAllNewStudyTestResultsChartData(Long prdStudyBatchId,String studyId) {
		Session session = persistenceManager.getCurrentSession();
		String sql = "select ES.PRD_STUDY_SCHEDULE_ID,ET.RESULT_1,";
		sql = sql + "ET.RESULT_2,";
		sql = sql + "ET.RESULT_3,";
		sql = sql + "ET.RESULT_4,";
		sql = sql + "ET.RESULT_5,";
		sql = sql + "ET.RESULT_6,";
		sql = sql + "ET.RESULT_7,";
		sql = sql + "ET.RESULT_8,";
		sql = sql + "ET.RESULT_9,";
		sql = sql + "ET.RESULT_10,";
		sql = sql + "ET.RESULT_11,";
		sql = sql + "ET.RESULT_12,";
		sql = sql + "ET.RESULT_13,";
		sql = sql + "ET.RESULT_14,";
		sql = sql + "ET.RESULT_15,";
		sql = sql + "ET.RESULT_16,";
		sql = sql + "ET.RESULT_17,";
		sql = sql + "ET.RESULT_18,";
		sql = sql + "ET.RESULT_19,";
		sql = sql + "ET.RESULT_20,";
		sql = sql + "ET.RESULT_21,";
		sql = sql + "ET.RESULT_22,";
		sql = sql + "ET.RESULT_23,";
		sql = sql + "ET.RESULT_24,";
		sql = sql + "ET.RESULT_25,";
		sql = sql + "ET.RESULT_26,";
		sql = sql + "ET.RESULT_27,";
		sql = sql + "ET.RESULT_28,";
		sql = sql + "ET.RESULT_29,";
		sql = sql + "ET.RESULT_30,";
		sql = sql + "ES.INTERVAL_VALUE,";
		sql = sql + "PT.UPPER_LIMIT,";
		sql = sql + "PT.LOWER_LIMIT,";
		sql = sql + "PT.TEST_NAME ";
		sql = sql + " from EA_PRD_STUDY_TEST_RESULTS ET,EA_PRD_STUDY_SCHEDULES ES ,EA_PRD_PRODUCT_TESTS PT, ea_dictionary_detail d, EA_PRD_PRODUCT_STUDY pps where ";
		sql = sql + "ES.PRD_STUDY_BATCH_ID=ET.PRD_STUDY_BATCH_ID AND PT.PRODUCT_TEST_ID=ET.PRODUCT_TEST_ID AND ES.STUDY_ID= ET.STUDY_ID AND ES.PRD_STUDY_SCHEDULE_ID=ET.PRD_STUDY_SCHEDULE_ID  ";
		sql = sql + "and  d.dictionary_code (+) = 'ABBREVIATION' and d.dictionary_value (+) = et.result_1 ";
		sql = sql + "and  pps.prd_study_batch_id = es.prd_study_batch_id and pps.location_id = " + super.getCurrentLocationId();
		if (prdStudyBatchId != null ) {
			sql = sql + " and ES.PRD_STUDY_BATCH_ID = " + prdStudyBatchId;
		}
		if (studyId != null && studyId.length() > 0) {
			sql = sql + " and ES.STUDY_ID = '" + studyId +"'";
		}
		SQLQuery q = persistenceManager.getCurrentSession().createSQLQuery(sql);
		q.addScalar("RESULT_1", Hibernate.STRING);
		q.addScalar("RESULT_2", Hibernate.STRING);
		q.addScalar("RESULT_3", Hibernate.STRING);
		q.addScalar("RESULT_4", Hibernate.STRING);
		q.addScalar("RESULT_5", Hibernate.STRING);
		q.addScalar("RESULT_6", Hibernate.STRING);
		q.addScalar("RESULT_7", Hibernate.STRING);
		q.addScalar("RESULT_8", Hibernate.STRING);
		q.addScalar("RESULT_9", Hibernate.STRING);
		q.addScalar("RESULT_10", Hibernate.STRING);
		q.addScalar("RESULT_11", Hibernate.STRING);
		q.addScalar("RESULT_12", Hibernate.STRING);
		q.addScalar("RESULT_13", Hibernate.STRING);
		q.addScalar("RESULT_14", Hibernate.STRING);
		q.addScalar("RESULT_15", Hibernate.STRING);
		q.addScalar("RESULT_16", Hibernate.STRING);
		q.addScalar("RESULT_17", Hibernate.STRING);
		q.addScalar("RESULT_18", Hibernate.STRING);
		q.addScalar("RESULT_19", Hibernate.STRING);
		q.addScalar("RESULT_20", Hibernate.STRING);
		q.addScalar("RESULT_21", Hibernate.STRING);
		q.addScalar("RESULT_22", Hibernate.STRING);
		q.addScalar("RESULT_23", Hibernate.STRING);
		q.addScalar("RESULT_24", Hibernate.STRING);
		q.addScalar("RESULT_25", Hibernate.STRING);
		q.addScalar("RESULT_26", Hibernate.STRING);
		q.addScalar("RESULT_27", Hibernate.STRING);
		q.addScalar("RESULT_28", Hibernate.STRING);
		q.addScalar("RESULT_29", Hibernate.STRING);
		q.addScalar("RESULT_30", Hibernate.STRING);
		q.addScalar("INTERVAL_VALUE", Hibernate.LONG);
		q.addScalar("LOWER_LIMIT", Hibernate.STRING);
		q.addScalar("UPPER_LIMIT", Hibernate.STRING);
		q.addScalar("TEST_NAME", Hibernate.STRING);
		List details = q.list();
	    List<GraphGeneratorVO> list = new ArrayList<GraphGeneratorVO>();
	    for (Iterator<Object[]> iterator = details.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			GraphGeneratorVO vo = new GraphGeneratorVO();
			vo.setResult1((String) objects[0]);
			vo.setResult2((String) objects[1]);
			vo.setResult3((String) objects[2]);
			vo.setResult4((String) objects[3]);
			vo.setResult5((String) objects[4]);
			vo.setResult6((String) objects[5]);
			vo.setResult7((String) objects[6]);
			vo.setResult8((String) objects[7]);
			vo.setResult9((String) objects[8]);
			vo.setResult10((String) objects[9]);
			vo.setResult11((String) objects[10]);
			vo.setResult12((String) objects[11]);
			vo.setResult13((String) objects[12]);
			vo.setResult14((String) objects[13]);
			vo.setResult15((String) objects[14]);
			vo.setResult16((String) objects[15]);
			vo.setResult17((String) objects[16]);
			vo.setResult18((String) objects[17]);
			vo.setResult19((String) objects[18]);
			vo.setResult20((String) objects[19]);
			vo.setResult21((String) objects[20]);
			vo.setResult22((String) objects[21]);
			vo.setResult23((String) objects[22]);
			vo.setResult24((String) objects[23]);
			vo.setResult25((String) objects[24]);
			vo.setResult26((String) objects[25]);
			vo.setResult27((String) objects[26]);
			vo.setResult28((String) objects[27]);
			vo.setResult29((String) objects[28]);
			vo.setResult30((String) objects[29]);
			vo.setIntervalValue((Long) objects[30]);
			vo.setLowerLimit((String) objects[31]);
			vo.setUpperLimit((String) objects[32]);
			vo.setTestName((String) objects[33]);
			vo.setAbbreviationResult1((String) objects [34]);
			
			//vo.setLabelRequest((String) objects[0]);
			//vo.setLabelName((String) objects[1]);
			list.add(vo);
		}
		return list;
	}
	
	
	public List getSequenceId(Date stabilityDate) {
		Session session = persistenceManager.getCurrentSession();
		String sql="select pkg_new_study_management.fun_get_study_id(:dt) from dual";
		List list1 = session.createSQLQuery(sql).setParameter("dt",stabilityDate)
		.list();
		return list1;
	}
	
	

	/**
	 * @param eaNewStudyScheduleId
	 * @return
	 */
	public NewStudySchedule getNewStudySchedule(Long eaNewStudyScheduleId) {
		Session session = persistenceManager.getCurrentSession();
		List list = session.createCriteria(NewStudySchedule.class)
		.add(Restrictions.eq("prdStudyScheduleId", eaNewStudyScheduleId))
		.list();
		if (list != null && list.size() > 0) {
			return (NewStudySchedule) list.get(0);
		}
		return null;
	}

	/**
	 * @param newStudySchedule
	 */
	public void createNewStudySchedule(NewStudySchedule newStudySchedule) {
		Session session = persistenceManager.getCurrentSession();
		session.saveOrUpdate(newStudySchedule);
	}

	/**
	 * @param newStudySchedule
	 */
	public void updateNewStudySchedule(NewStudySchedule newStudySchedule) {
		Session session = persistenceManager.getCurrentSession();
		session.saveOrUpdate(newStudySchedule);
	}

	/**
	 * @param prdStudyScheduleId
	 */
	public void deleteNewStudySchedule(Long prdStudyScheduleId) {
		Session session = persistenceManager.getCurrentSession();
		NewStudySchedule classVariable = (NewStudySchedule) session.get(NewStudySchedule.class, prdStudyScheduleId);
		session.delete(classVariable);
	}

	/**
	 * @param newStudySchedule
	 */
	public void updateNewStudySchedules(List<Long> newStudyScheduleIds, String scheduleStatus,Long userId, Long auditId) {
		Session session = persistenceManager.getCurrentSession();
		String sql1 = "UPDATE EA_PRD_STUDY_SCHEDULES SET SCHEDULE_STATUS = :SCHEDULE_STATUS"
			+ ", update_user = :update_user" 
			+ ", update_date = :update_date"
			+ ", audit_id = :audit_id"
			+ " WHERE PRD_STUDY_SCHEDULE_ID IN (:ids)";
		session.createSQLQuery(sql1)
			.setParameter("SCHEDULE_STATUS", scheduleStatus)
			.setParameter("update_user", userId)
			.setParameter("update_date", Utility.getCurrentUserLocationDateTime())
			.setParameter("audit_id", auditId)
			.setParameterList("ids", newStudyScheduleIds)
			.executeUpdate();
	}

	/**
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws EAPharmicsException
	 */
	public List getAllNewStudySummaries(Date startDate, Date endDate) throws EAPharmicsException {
		Session session = persistenceManager.getCurrentSession();
		List list = session.createCriteria(NewStudySummaryVO.class)
					.add(Restrictions.between("scheduleDate", startDate, endDate))
					.add(Restrictions.eq("scheduleStatus", "SCHEDULED"))
					.add(Restrictions.eq("locationId", super.getCurrentLocationId()))
					.addOrder(Order.asc("scheduleDate"))
               .addOrder(Order.asc("productName"))
					.list();
		super.checkResultSize(list);
		return list;
	}

	/**
	 * @param scheduleStatus
	 * @param barCodes
	 * @param studyId
	 * @param productCode
	 * @param lotNumber
	 * @param productType
	 * @param startDate
	 * @param endDate
	 * @param level1Approval
	 * @param finalApproval
	 * @param pulledScheduleOnly
	 * @param forLMM
	 * @return
	 * @throws EAPharmicsException
	 */
	public List getAllNewStudySummaries(String scheduleStatus, String barCodes,
			String studyId, String productCode, String lotNumber, String productType,
			Date startDate, Date endDate, Boolean level1Approval, Boolean finalApproval,Boolean pulledScheduleOnly,
			Boolean forLMM) 
		throws EAPharmicsException {
		Session session = persistenceManager.getCurrentSession();
		Criteria criteria = session.createCriteria(NewStudySummaryVO.class);
		List studiesReadyForApprovalList = new ArrayList();
		if (level1Approval != null || finalApproval != null) {
			studiesReadyForApprovalList = getScheduleIdsWithTestResultsForApproval(level1Approval, finalApproval);
			if (studiesReadyForApprovalList == null || studiesReadyForApprovalList.size() == 0) {
				return studiesReadyForApprovalList;
			}
			if (studiesReadyForApprovalList.size() > 999) {
				//oracle "IN" clause limits a maximum of 1000 entries
				throw new EAPharmicsException("Too many entries to display. Please use filter criteria to reduce results!");
			}
			super.checkResultSize(studiesReadyForApprovalList);
			criteria.add(Restrictions.in("prdStudyScheduleId", studiesReadyForApprovalList));
		}
		criteria.add(Restrictions.eq("locationId", super.getCurrentLocationId()));
		if (scheduleStatus != null && scheduleStatus.length() > 0) {
			criteria = criteria.add(Restrictions.eq("scheduleStatus", scheduleStatus));
		}
		if (barCodes != null && barCodes.length() > 0) {
			String delimiter = "\n";
			if (barCodes.indexOf("\r") >= 0) {
				delimiter = "\r";
			}
			StringTokenizer st = new StringTokenizer(barCodes, delimiter);
			LogicalExpression exp = null;
			while (st.hasMoreTokens()) {
				String barCode = st.nextToken();
				Criterion c1 = null;
				Criterion c2 = null;
				if (barCode != null && barCode.trim().length() > 0) {
					Hashtable<String, String> map = BarCode.getBarCodeElements(barCode);
					if (map.containsKey(Constants.STUDY_ID)) {
						studyId = map.get(Constants.STUDY_ID);
						c1 = Restrictions.eq("studyId", studyId);
					}
					if (map.containsKey(Constants.INTERVAL_VALUE)) {
						String intervalValue = map.get(Constants.INTERVAL_VALUE);
						c2 = Restrictions.eq("intervalValue", intervalValue);
					}
					LogicalExpression exp1 = null;
					if (c1 != null && c2 != null) {
						exp1 = Restrictions.and(c1, c2);						
					} else {
						//temp fix for now. need to investigate
						exp1 = Restrictions.and(c1, c1);
					}
					if (exp == null) {
						exp = exp1;
					} else {
						exp = Restrictions.or(exp, exp1);
					}
				}
			}
			if (exp != null) {
				criteria = criteria.add(exp);
			}
		} else {
			if (studyId != null && studyId.length() > 0) {
				criteria = criteria.add(super.getExpression("studyId", studyId));
			}
		}
		if (productCode != null && productCode.length() > 0) {
			criteria = criteria.add(super.getExpression("productCode", productCode));
		}
		if (lotNumber != null && lotNumber.length() > 0) {
			criteria = criteria.add(super.getExpression("lotNumber", lotNumber));
		}
		if (productType != null && productType.length() > 0) {
			criteria = criteria.add(Restrictions.eq("productType", productType));
		}
		if (startDate != null && endDate != null) {
			criteria = criteria.add(Restrictions.between("scheduleDate", startDate, endDate));
		}
		if (pulledScheduleOnly != null && pulledScheduleOnly)
		{
		   criteria = criteria.add(Restrictions.isNotNull("datePulled"));
		}
		if (forLMM != null && forLMM)
		{
		   criteria = criteria.add(Restrictions.ne("intervalValue","0"));
		   criteria = criteria.add(Restrictions.eq("pastInterval",false));
		}
		criteria.addOrder(Order.asc("scheduleDate"));
		criteria.addOrder(Order.asc("productName"));

		List list = new ArrayList();
		list = criteria.list();
		super.checkResultSize(list);
		return list;
	}
	
	public List getAllPendingLists() {
		//Added Fix for reducing the number of entries that shows in the pending list.
		Session session = persistenceManager.getCurrentSession();
		List list = session.createCriteria(NewStudySummaryVO.class)
					//.add(Restrictions.between("scheduleDate", startDate, endDate))
					.add(Restrictions.eq("locationId", super.getCurrentLocationId()))
					.add(Restrictions.eq("scheduleStatus", "PENDING"))
					.addOrder(Order.asc("scheduleDate"))
					.addOrder(Order.asc("productName"))
					.add(Restrictions.eq("pastInterval",false))
					.add(Restrictions.ne("intervalValue","0"))
					.list();
		return list;
	}

	/**
	 * @return
	 */
	public List getAllNewStudyTestResults(Long prdStudyScheduleId) {
		Session session = persistenceManager.getCurrentSession();
		List list = session.createCriteria(NewStudyTestResult.class)
					.add(Restrictions.eq("prdStudyScheduleId", prdStudyScheduleId))
					.list();
		return list;
	}

	/**
	 * @param level1Approval
	 * @param finalApproval
	 * @return
	 */
	public List getScheduleIdsWithTestResultsForApproval(Boolean level1Approval, Boolean finalApproval) {
		ApplParameter parameter = AdminFactory.getInstance()
			.getApplParameterByName(Constants.DATA_APPROVAL_LEVEL);		
		
		String sql = "select t.prd_study_schedule_id";
		sql = sql + " from ( select tr.prd_study_schedule_id, ";
		sql = sql + "    sum(case when (tr.test_interval_used= 'Y' and tr.result_1 is not null) then 1 else 0 end) as req_result_count,";
		sql = sql + "    sum(case when tr.test_interval_used = 'Y' then 1 else 0 end) as req_test_count,";
		sql = sql + "    sum(case when (tr.test_interval_used= 'N' and tr.result_1 is not null) then 1 else 0 end) as non_req_result_count ";
		sql = sql + " from EA_PRD_STUDY_SCHEDULES ss, EA_PRD_STUDY_TEST_RESULTS tr, ea_prd_product_study pps";
		sql = sql + " where ss.prd_study_schedule_id = tr.prd_study_schedule_id ";
		sql = sql + " and pps.prd_study_batch_id = tr.prd_study_batch_id ";
		sql = sql + " and pps.location_id = " + super.getCurrentLocationId();
		if (level1Approval != null && Boolean.TRUE.equals(level1Approval)) {
			sql = sql + " and ss.approval_level1_yn = 'N'";
		}
		if (finalApproval != null && Boolean.TRUE.equals(finalApproval)) {
			sql = sql + " and ss.approval_level2_yn = 'N'";
			if ("D".equals(parameter.getParameterValue())) {
				sql = sql + " and ss.approval_level1_yn = 'Y'";
			}
		}
		sql = sql + " group by tr.prd_study_schedule_id";
		sql = sql + " ) t";
		sql = sql + " where ((t.req_test_count > 0 and t.req_result_count >= t.req_test_count) or";
		sql = sql + "      (t.req_test_count = 0 and t.non_req_result_count > 0))";
		
		SQLQuery q = persistenceManager.getCurrentSession().createSQLQuery(sql).addScalar("prd_study_schedule_id", Hibernate.LONG);
		List details = q.list();
		return details;
	}

	/**
	 * @param prdStudyBatchId
	 * @param prdStudyScheduleId
	 * @return
	 */
	public boolean getCheckForAllIntervalsCompleted(Long prdStudyBatchId,
			Long prdStudyScheduleId) {
		boolean returnValue = true;
		String sql = "  SELECT ss.prd_study_schedule_id,";
		sql = sql + "         ss.schedule_status";
		sql = sql + "    FROM EA_PRD_STUDY_SCHEDULES ss,";
		sql = sql + "         ea_prd_product_study pps";
		sql = sql + "   WHERE pps.prd_study_batch_id = ss.prd_study_batch_id";
		sql = sql + "         AND pps.location_id = " + super.getCurrentLocationId();
		sql = sql + "         and pps.prd_study_batch_id = " + prdStudyBatchId;
		sql = sql + " ORDER BY ss.prd_study_schedule_id";
		
		SQLQuery q = persistenceManager.getCurrentSession().createSQLQuery(sql);
		q.addScalar("prd_study_schedule_id", Hibernate.LONG);
		q.addScalar("schedule_status", Hibernate.STRING);
		List details = q.list();
	    for (Iterator<Object[]> iterator = details.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			Long key = (Long) objects[0];
			String status = (String) objects[1];
			if (status.equals("COMPLETED") == false && key.longValue() != prdStudyScheduleId) {
				//not the current schedule and the schedule is not complete 
				returnValue = false;
				break;
			}
	    }	
		return returnValue;
	}

	/**
	 * @param eaNewStudyTestResultId
	 * @return
	 */
	public void updateStudyStatus(Long prdStudyBatchId, UserVO userVO, String studyStatus) {
		Session session = persistenceManager.getCurrentSession();
		String sql1 = "UPDATE ea_prd_product_study SET STUDY_STATUS = :study_status"
			+ ", update_user = :update_user" 
			+ ", update_date = :update_date"
			+ ", audit_id = :audit_id"
			+ " WHERE PRD_STUDY_BATCH_ID = (:id)";
		session.createSQLQuery(sql1)
			.setParameter("study_status", studyStatus)
			.setParameter("update_user", userVO.getAppUserId())
			.setParameter("update_date", Utility.getCurrentUserLocationDateTime())
			.setParameter("audit_id", userVO.getAuditId())
			.setParameter("id", prdStudyBatchId)
			.executeUpdate();
		
	}

	/**
	 * @param eaNewStudyTestResultId
	 * @return
	 */
	public NewStudyTestResult getNewStudyTestResult(Long eaNewStudyTestResultId) {
		Session session = persistenceManager.getCurrentSession();
		NewStudyTestResult newStudyTestResult = (NewStudyTestResult) session.load(NewStudyTestResult.class, eaNewStudyTestResultId);
		return newStudyTestResult;
	}

	/**
	 * @param newStudyTestResult
	 */
	public void createNewStudyTestResult(NewStudyTestResult newStudyTestResult) {
		Session session = persistenceManager.getCurrentSession();
		session.saveOrUpdate(newStudyTestResult);
	}

	/**
	 * @param newStudyTestResult
	 */
	public void updateNewStudyTestResult(NewStudyTestResult newStudyTestResult) {
		Session session = persistenceManager.getCurrentSession();
		session.saveOrUpdate(newStudyTestResult);
	}

	/**
	 * @param prdStudyTestResultsId
	 */
	public void deleteNewStudyTestResult(Long prdStudyTestResultsId) {
		Session session = persistenceManager.getCurrentSession();
		NewStudyTestResult classVariable = (NewStudyTestResult) session.get(NewStudyTestResult.class, prdStudyTestResultsId);
		session.delete(classVariable);
	}
   
   public void updateInventoryPullDate(List<NewStudySummaryVO> studySummaryVO,String pulledBy,Date pullDate,long unitsPulled,long extrasPulled,UserVO userVO) {
      String ids = "";
      int count = 0;
      for(Iterator iterator = studySummaryVO.iterator(); iterator.hasNext();)
      {
         NewStudySummaryVO summaryVO = new NewStudySummaryVO();
         summaryVO = (NewStudySummaryVO) iterator.next();
         String id = summaryVO.getPrdStudyScheduleId().toString();   
         String sqlString;
         Session session = persistenceManager.getCurrentSession();
         String extraSqlString = " ";
         if (extrasPulled > 0)
         {
            extraSqlString = ",EXTRA_PULLED = "+ extrasPulled + 
                             ",EXTRA_PULLED_BY = '"+ pulledBy + "' " + 
                             ",EXTRA_PULLED_DATE = TO_DATE('" + Utility.dateToString(Utility.stripTime(pullDate), Utility.DEFAULT_DATE_FORMAT) +
                                                   "', '" + Utility.DEFAULT_DATE_FORMAT + "')" ;                           
         }
         if (unitsPulled == 0)
         {
            sqlString = "UPDATE EA_PRD_STUDY_SCHEDULES " +
                               "SET DATE_PULLED = TO_DATE('" + Utility.dateToString(Utility.stripTime(pullDate), Utility.DEFAULT_DATE_FORMAT) +
                                               "', '" + Utility.DEFAULT_DATE_FORMAT + "')" +
                               ",PULLED_BY = '"+ pulledBy + "' " + 
                               ",UNITS_PULLED = "+ summaryVO.getIntervalUnits()+ 
                               ",SCANNED_UNITS ='"+summaryVO.getScannedUnits()+"'"+
                               ",AUDIT_ID= "+ userVO.getAuditId() +
                               ",UPDATE_DATE = SYSDATE "+
                               ",UPDATE_USER = "+ userVO.getAppUserId() + 
                               extraSqlString + 
                               " WHERE DATE_PULLED IS NULL AND PRD_STUDY_SCHEDULE_ID = "+ id;
         }
         else
         {
            sqlString = "UPDATE EA_PRD_STUDY_SCHEDULES " +
            "SET DATE_PULLED = TO_DATE('" + Utility.dateToString(Utility.stripTime(pullDate), Utility.DEFAULT_DATE_FORMAT) +
                            "', '" + Utility.DEFAULT_DATE_FORMAT + "')" +
            ",PULLED_BY = '"+ pulledBy + "' " + 
            ",AUDIT_ID= "+ userVO.getAuditId() +
            ",UNITS_PULLED = "+ unitsPulled +  
            ",UPDATE_DATE = SYSDATE "+
            ",UPDATE_USER = "+ userVO.getAppUserId() + 
            extraSqlString +
            " WHERE DATE_PULLED IS NULL AND PRD_STUDY_SCHEDULE_ID = "+ id;        
         }
         int i = session.createSQLQuery(sqlString).executeUpdate(); //.setParameter("ids",ids).executeUpdate();
      }
   }
   
   public List getAllNewStudySummaries(List scheduleIds) {
      Session session = persistenceManager.getCurrentSession();  
      List<Long> ids = new ArrayList<Long>();
      for(Iterator iterator = scheduleIds.iterator(); iterator.hasNext();)
      {
         Object obj = iterator.next();
         if (obj instanceof NewStudySummaryVO){
            NewStudySummaryVO summaryVO = new NewStudySummaryVO();
            summaryVO = (NewStudySummaryVO) obj;
            ids.add(summaryVO.getPrdStudyScheduleId());
         }
         else
         {
            String idStr = obj.toString();
            Long idLong = Long.parseLong(idStr);
            ids.add(idLong);
         }
      }
      List list = session.createCriteria(NewStudySummaryVO.class)
               .add(Restrictions.eq("locationId", super.getCurrentLocationId()))
               .add(Restrictions.in("prdStudyScheduleId", ids))
               .addOrder(Order.asc("scheduleDate"))
               .addOrder(Order.asc("productName"))
               .list();
      return list;
   }	

   public List getAllNewStudySummaries(Date startDate, Date endDate,String scheduleStatus) {
      Session session = persistenceManager.getCurrentSession();
      List list = session.createCriteria(NewStudySummaryVO.class)
               .add(Restrictions.between("scheduleDate", startDate, endDate))
               .add(Restrictions.eq("scheduleStatus",scheduleStatus ))
               .list();
      return list;
   }
	public ReportCountVO getPendingReportCounts() {
		Session session = persistenceManager.getCurrentSession();
		long locationId =  super.getCurrentLocationId();
		String sql = "SELECT get_pending_samples("+locationId+") pending, get_samples_not_started("+locationId+") not_started, get_samples_not_completed("+locationId+") not_completed from dual";
		SQLQuery q = persistenceManager.getCurrentSession().createSQLQuery(sql);
		q.addScalar("PENDING", Hibernate.INTEGER);
		q.addScalar("NOT_STARTED", Hibernate.INTEGER);
		q.addScalar("NOT_COMPLETED", Hibernate.INTEGER);
	    List details = q.list();
	    ReportCountVO vo = new ReportCountVO();
	    for (Iterator<Object[]> iterator = details.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			vo.setPendingCount(((Integer) objects[0]).intValue());
			vo.setNotStartedCount(((Integer) objects[1]).intValue());
			vo.setNotCompletedCount(((Integer) objects[2]).intValue());
		}
		return vo;
	}
   public void updateInventoryTestStartDate(List<NewStudySummaryVO> scheduleIds,Date testStartDate,UserVO userVO) {
      String ids = "";
      int count = 0;
      for(Iterator iterator = scheduleIds.iterator(); iterator.hasNext();)
      {
         String sqlString ="";
         Session session = persistenceManager.getCurrentSession();
         NewStudySummaryVO summaryVO = new NewStudySummaryVO();
         summaryVO = (NewStudySummaryVO) iterator.next();
         String id = summaryVO.getPrdStudyScheduleId().toString();   
         String testStartDateSql = "TEST_START_DATE = TO_DATE('" + Utility.dateToString(Utility.stripTime(testStartDate), Utility.DEFAULT_DATE_FORMAT) +
                                                             "', '" + Utility.DEFAULT_DATE_FORMAT + "')" ;
         String dmlString ="UPDATE EA_PRD_STUDY_SCHEDULES " +
            "SET AUDIT_ID= "+ userVO.getAuditId() +
            ",TEST_STARTED_BY = '"+ userVO.getUserName() +"'"+
            ",UPDATE_DATE = SYSDATE "+
            ",UPDATE_USER = "+ userVO.getAppUserId();
         String whereClause = " WHERE TEST_START_DATE IS NULL AND PRD_STUDY_SCHEDULE_ID = :id";
         if (testStartDateSql != "")
         {
            sqlString = dmlString +
            ","+ testStartDateSql +
            whereClause; 
         }
         int i = session.createSQLQuery(sqlString).setParameter("id",id).executeUpdate();
      }
   }  
   public void updateInventoryTestEndDate(List<NewStudySummaryVO> scheduleIds,Date testEndDate, UserVO userVO) {
      String ids = "";
      int count = 0;
      for(Iterator iterator = scheduleIds.iterator(); iterator.hasNext();)
      {   
         String sqlString ="";
         Session session = persistenceManager.getCurrentSession();
         NewStudySummaryVO summaryVO = new NewStudySummaryVO();
         summaryVO = (NewStudySummaryVO) iterator.next();
         String id = summaryVO.getPrdStudyScheduleId().toString(); 
         String testEndDateSql = "TEST_END_DATE = TO_DATE('" + Utility.dateToString(Utility.stripTime(testEndDate), Utility.DEFAULT_DATE_FORMAT) +
                                                                      "', '" + Utility.DEFAULT_DATE_FORMAT + "')";
         String dmlString ="UPDATE EA_PRD_STUDY_SCHEDULES " +
            "SET AUDIT_ID= "+ userVO.getAuditId() +
            ",TEST_ENDED_BY = '"+ userVO.getUserName() +"'"+
            ",UPDATE_DATE = SYSDATE "+
            ",UPDATE_USER = "+ userVO.getAppUserId();
         String whereClause = " WHERE TEST_END_DATE IS NULL AND PRD_STUDY_SCHEDULE_ID = :id";
         if (testEndDateSql != "" )
         {
            sqlString = dmlString +
            ","+ testEndDateSql +
            whereClause; 
         }
         int i = session.createSQLQuery(sqlString).setParameter("id",id).executeUpdate();
      }
   }    
   
   public void updateInventoryInitiateDate(List<NewStudySummaryVO> scheduleIds,String updateUser, Date initiateDate, UserVO userVO) {
      List<Long> ids = new ArrayList<Long>();
      int count = 0;
      for(Iterator iterator = scheduleIds.iterator(); iterator.hasNext();)
      {
         NewStudySummaryVO summaryVO = new NewStudySummaryVO();
         summaryVO = (NewStudySummaryVO) iterator.next();
         ids.add(summaryVO.getPrdStudyScheduleId());          
      }
      if (ids.size()> 0)
      {
         Session session = persistenceManager.getCurrentSession();
         String sqlString = "UPDATE EA_PRD_STUDY_SCHEDULES " +
                            "SET PULL_INITIATED_DATE = TO_DATE('" + Utility.dateToString(Utility.stripTime(initiateDate), Utility.DEFAULT_DATE_FORMAT) +
                                            "', '" + Utility.DEFAULT_DATE_FORMAT + "')" +
                            ",PULL_INITIATED_BY = :update_user"+
                            ",AUDIT_ID= :audit_id " +
                            ",UPDATE_DATE = SYSDATE "+
                            ",UPDATE_USER = :update_user_id " +
                            " WHERE PULL_INITIATED_DATE IS NULL AND PRD_STUDY_SCHEDULE_ID IN (:ids)"; 
         int i = session.createSQLQuery(sqlString)
                                      .setParameter("update_user",updateUser)
                                      .setParameter("audit_id",userVO.getAuditId())
                                      .setParameter("update_user_id",userVO.getAppUserId())
                                      .setParameterList("ids", ids)
                                      .executeUpdate();
      }
   }   
	/**
	 * @return
	 */
	public List getAllStudyInventoryDestroyeds(Long prdStudyScheduleId) {
		Session session = persistenceManager.getCurrentSession();
		List list = session.createCriteria(StudyInventoryDestroyed.class)
					.add(Restrictions.eq("prdStudyScheduleId", prdStudyScheduleId))
					.list();
		return list;
	}

	/**
	 * @param eaStudyInventoryDestroyedId
	 * @return
	 */
	public StudyInventoryDestroyed getStudyInventoryDestroyed(Long eaStudyInventoryDestroyedId) {
		Session session = persistenceManager.getCurrentSession();
		StudyInventoryDestroyed studyInventoryDestroyed = (StudyInventoryDestroyed) session.load(StudyInventoryDestroyed.class, eaStudyInventoryDestroyedId);
		return studyInventoryDestroyed;
	}

	/**
	 * @param studyInventoryDestroyed
	 */
	public void createStudyInventoryDestroyed(StudyInventoryDestroyed studyInventoryDestroyed) {
		Session session = persistenceManager.getCurrentSession();
		session.saveOrUpdate(studyInventoryDestroyed);
	}

	/**
	 * @param studyInventoryDestroyed
	 */
	public void updateStudyInventoryDestroyed(StudyInventoryDestroyed studyInventoryDestroyed) {
		Session session = persistenceManager.getCurrentSession();
		session.saveOrUpdate(studyInventoryDestroyed);
	}

	/**
	 * @param prdStudyInvDestroyedId
	 */
	public void deleteStudyInventoryDestroyed(Long prdStudyInvDestroyedId) {
		Session session = persistenceManager.getCurrentSession();
		StudyInventoryDestroyed classVariable = (StudyInventoryDestroyed) session.get(StudyInventoryDestroyed.class, prdStudyInvDestroyedId);
		session.delete(classVariable);
	}

	/**
	 * @param eaProductStudyIntervalsId
	 * @return
	 */
	public ProductStudyIntervalsVO getProductStudyIntervals(Long eaProductStudyIntervalsId) {
		Session session = persistenceManager.getCurrentSession();
		ProductStudyIntervalsVO productStudyIntervalsVO = (ProductStudyIntervalsVO) session.load(ProductStudyIntervalsVO.class, eaProductStudyIntervalsId);
		return productStudyIntervalsVO;
	}
   /**
    * @param
    * @return
    */
   public List<ProductStudyIntervalsVO> getAllProductStudyIntervals() {
      Session session = persistenceManager.getCurrentSession();
      List list = session.createCriteria(ProductStudyIntervalsVO.class)
               .list();
      return list;      
   }

	/**
	 * @return
	 */
	public List getProtocolReportFilterData() {
		Session session = persistenceManager.getCurrentSession();
		String sql = "select distinct p.product_code, nsp.lot_number, nsp.container_code";
		sql = sql + " from ea_prd_product_study nsp, ea_prd_products p";
		sql = sql + " where nsp.product_id = p.product_id";
		sql = sql + " and nsp.approval_level1_yn = 'Y'";
		sql = sql + " and p.location_id = " + super.getCurrentLocationId();
		sql = sql + " order by product_code, lot_number, container_code";
		SQLQuery q = persistenceManager.getCurrentSession().createSQLQuery(sql);
		q.addScalar("product_code", Hibernate.STRING);
		q.addScalar("lot_number", Hibernate.STRING);
		q.addScalar("container_code", Hibernate.STRING);
		List details = q.list();
		
		List<String> containers = new ArrayList <String> ();
		List<NewStudyProductLotContainerVO> lots = new ArrayList <NewStudyProductLotContainerVO> ();
		List<NewStudyProductLotVO> products = new ArrayList<NewStudyProductLotVO>();
		
		String prevProductCode = "";
		String prevLotNumber = "";		
	    for (Iterator<Object[]> iterator = details.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			String productCode = (String) objects[0];
			String lotNumber = (String) objects[1];
			String container = (String) objects[2];
			if (!productCode.equals(prevProductCode)) {
				if (prevProductCode.length() > 0) {
					NewStudyProductLotContainerVO vo1 = new NewStudyProductLotContainerVO();
					vo1.setLotNumber(prevLotNumber);
					vo1.setContainers(containers);
					lots.add(vo1);

					NewStudyProductLotVO pLot = new NewStudyProductLotVO();
					pLot.setProductCode(prevProductCode);
					pLot.setLotNumbers(lots);
					products.add(pLot);
				}
				
				prevProductCode = productCode;
				prevLotNumber = lotNumber;
				lots = new ArrayList<NewStudyProductLotContainerVO>();
				containers = new ArrayList<String>();

			} else if (!prevLotNumber.equals(lotNumber)) {
				if (prevLotNumber.length() > 0) {
					NewStudyProductLotContainerVO vo1 = new NewStudyProductLotContainerVO();
					vo1.setLotNumber(prevLotNumber);
					vo1.setContainers(containers);
					lots.add(vo1);
				}
				prevLotNumber = lotNumber;
				containers = new ArrayList<String>();
			}
			containers.add(container);
	    }
	    if (prevProductCode.length() > 0) {
			NewStudyProductLotContainerVO vo1 = new NewStudyProductLotContainerVO();
			vo1.setLotNumber(prevLotNumber);
			vo1.setContainers(containers);
			lots.add(vo1);

			NewStudyProductLotVO pLot = new NewStudyProductLotVO();
			pLot.setProductCode(prevProductCode);
			pLot.setLotNumbers(lots);
			products.add(pLot);
	    }
		return products;
	}

   public void updateInventoryExtraPulled(List<NewStudySummaryVO> studySummaryVO,String pulledBy,Date pullDate,long extrasPulled,UserVO userVO) {
      String ids = "";
      int count = 0;
      for(Iterator iterator = studySummaryVO.iterator(); iterator.hasNext();)
      {
         NewStudySummaryVO summaryVO = new NewStudySummaryVO();
         summaryVO = (NewStudySummaryVO) iterator.next();
         String id = summaryVO.getPrdStudyScheduleId().toString();   
         String sqlString = "";
         Session session = persistenceManager.getCurrentSession();
         String extraSqlString = " ";
         if (extrasPulled > 0)
         {
            extraSqlString = ",EXTRA_PULLED = NVL(EXTRA_PULLED,0) + "+ extrasPulled + 
                             ",EXTRA_PULLED_BY = '"+ pulledBy + "' " + 
                             ",EXTRA_PULLED_DATE = TO_DATE('" + Utility.dateToString(Utility.stripTime(pullDate), Utility.DEFAULT_DATE_FORMAT) +
                                                   "', '" + Utility.DEFAULT_DATE_FORMAT + "')" ;                           

            sqlString = "UPDATE EA_PRD_STUDY_SCHEDULES " +
                               "SET "+
                               " AUDIT_ID= "+ userVO.getAuditId() +
                               ",UPDATE_DATE = SYSDATE "+
                               ",UPDATE_USER = "+ userVO.getAppUserId() + 
                               extraSqlString + 
                               " WHERE DATE_PULLED IS NOT NULL AND PRD_STUDY_SCHEDULE_ID = "+ id;
            int i = session.createSQLQuery(sqlString).executeUpdate(); //.setParameter("ids",ids).executeUpdate();
         }
      }
   }
   
   public void restoreInventoryPull(List<NewStudySummaryVO> studySummaryVO,UserVO userVO) {
      String ids = "";
      int count = 0;
      for(Iterator iterator = studySummaryVO.iterator(); iterator.hasNext();)
      {
         NewStudySummaryVO summaryVO = new NewStudySummaryVO();
         summaryVO = (NewStudySummaryVO) iterator.next();
         String id = summaryVO.getPrdStudyScheduleId().toString();   
         String sqlString = "";
         Session session = persistenceManager.getCurrentSession();                        

         sqlString = "UPDATE EA_PRD_STUDY_SCHEDULES " +
                            "SET "+
                            " AUDIT_ID= "+ userVO.getAuditId() +
                            ",UPDATE_DATE = SYSDATE "+
                            ",UPDATE_USER = "+ userVO.getAppUserId() + 
                            ",UNITS_PULLED = 0 " +
                            ",TEST_STARTED_BY = NULL" +
                            ",TEST_START_DATE = NULL " +
                            ",TEST_ENDED_BY = NULL" +
                            ",TEST_END_DATE = NULL "+
                            ",DATE_PULLED = NULL "+
                            ",PULLED_BY = NULL" +
                            ",EXTRA_PULLED = 0 "+
                            ",EXTRA_PULLED_DATE = NULL "+
                            ",EXTRA_PULLED_BY = NULL" +
                            " WHERE DATE_PULLED IS NOT NULL AND PRD_STUDY_SCHEDULE_ID = "+ id;
            int i = session.createSQLQuery(sqlString).executeUpdate(); //.setParameter("ids",ids).executeUpdate();
      }
   }   

   /**
	 * @param studyIds
	 * @param productTestId
	 * @return
	 */
	public List getRegressionInput(List<String> studyIds, ProductTest productTest) {
		return getRegressionInput(studyIds, productTest, Boolean.FALSE, null, null);
	}

	/**
	 * @param studyIds
	 * @param productTest
	 * @param t0percentCalc
	 * @param lowerLimit
	 * @param upperLimit
	 * @return
	 */
	public List getRegressionInput(List<String> studyIds,
			ProductTest productTest, Boolean t0percentCalc, String lowerLimit,
			String upperLimit) {

		int NUM_OF_RESULTS = 30;
	   
		Hashtable dictionaries = new Hashtable<String, Double>();
		
	   String sql = " select a.study_id, b.interval_value, get_result_avg(d.prd_study_test_results_id) as result, d.result_1 ";
	   sql = sql + "from EA_PRD_PRODUCT_STUDY a, EA_PRD_STUDY_INTERVALS b, EA_PRD_STUDY_SCHEDULES c, EA_PRD_STUDY_TEST_RESULTS d  ";
	   sql = sql + "where a.prd_study_batch_id = b.prd_study_batch_id ";
	   sql = sql + "and b.prd_study_interval_id = c.prd_study_interval_id ";
	   sql = sql + "and c.prd_study_schedule_id = d.prd_study_schedule_id ";
	   sql = sql + "and d.product_test_id = " + productTest.getProductTestId() + " ";
	   sql = sql + " and a.location_id = " + super.getCurrentLocationId() + " ";

	   if (studyIds.size() > 0) {
			int i = 0;
			sql = sql + "and a.study_id in (";
			for (Iterator iterator = studyIds.iterator(); iterator.hasNext();) {
				String studyId = (String) iterator.next();
				if (i > 0) {
					sql = sql + ", ";
				}
				sql = sql + "'" + studyId + "'";
				i++;
			}
			sql = sql + ") ";
		}
		sql = sql + "order by a.study_id, interval_value ";

		SQLQuery q = persistenceManager.getCurrentSession().createSQLQuery(sql);
		q.addScalar("study_id", Hibernate.STRING);
		q.addScalar("interval_value", Hibernate.INTEGER);
		q.addScalar("result", Hibernate.DOUBLE);
		q.addScalar("result_1", Hibernate.STRING);
		int precision = -1;

		List details = q.list();
		String prevBatchId = "";
		List<List<RegressionInputVO>> batches = new ArrayList<List<RegressionInputVO>>();
		List<RegressionInputVO> list = new ArrayList<RegressionInputVO>();
		int count = 0;
		Double t0Result = null;
		NumberFormat formatter = null;
		boolean allNullResults = true;
		for (Iterator<Object[]> iterator = details.iterator(); iterator
				.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			RegressionInputVO vo = new RegressionInputVO();
			String batchId = (String) objects[0];
			if (batchId.equals(prevBatchId) == false) {
				if (count > 1 && prevBatchId.length() > 0) {
					batches.add(list);
				}
				list = new ArrayList<RegressionInputVO>();
				prevBatchId = batchId;
				count = 0;
				t0Result = null;
				precision = -1;
			} else if (batchId.length() == 0) {
				prevBatchId = batchId;
			}
			vo.setBatchId(batchId);
			vo.setInterval(((Integer) objects[1]).intValue());
			vo.setResult(((Double) objects[2]));
			if (vo.getResult() == null) {
				String result1 = (String) objects[3];
				if (result1 == null) {
					continue;
				}
				Double graphTo = null;
				if (!dictionaries.containsKey(result1)) {
					DictionaryDetail dd = DictionaryFactory.getInstance()
							.getDictionaryDetailForValue("ABBREVIATION",
									result1);
					if (dd == null) {
						continue;
					}
					try {
						graphTo = Double.valueOf(dd.getGraphTo());
					} catch (Exception e) {
						// TODO: handle exception
					}
					if (graphTo == null) {
						continue;
					}
					dictionaries.put(result1, graphTo);
				} else {
					graphTo = (Double) dictionaries.get(result1);
				}
				vo.setResult(graphTo);
			}
			if (t0percentCalc) {
				if (vo.getInterval() == 0 && vo.getResult() != null
						&& vo.getResult().doubleValue() != 0) {
					t0Result = vo.getResult();
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
				if (vo.getInterval() != 0 && vo.getResult() != null
						&& t0Result != null) {
					// the results will come in interval order. some customers
					// may want the % difference from
					// the first interval which is t0 or 0 interval/initial.
					double percentT0Result = (vo.getResult().doubleValue())
							* 100 / t0Result.doubleValue();
					vo.setResult(Double.valueOf(formatter.format(percentT0Result)));
				} else if (vo.getInterval() == 0){
					vo.setResult(new Double(100d));
				} else if (vo.getInterval() == 0){
					vo.setResult(null);
				}
			}
			count++;
			try {
				if (t0percentCalc && lowerLimit != null && lowerLimit.length() > 0) {
					vo.setLowerLimit(Double.valueOf(lowerLimit));
				} else {
					if (productTest.getLowerLimit() != null) {
						Double d = Double.valueOf(productTest.getLowerLimit());
						vo.setLowerLimit(d);
					}
				}
			} catch (NumberFormatException e) {
				// ignore if non-numeric value
				vo.setLowerLimit(null);
			}
			try {
				if (t0percentCalc && upperLimit != null && upperLimit.length() > 0) {
					vo.setUpperLimit(Double.valueOf(upperLimit));
				} else {
					if (productTest.getUpperLimit() != null) {
						Double d = Double.valueOf(productTest.getUpperLimit());
						vo.setUpperLimit(d);
					}
				}
			} catch (NumberFormatException e) {
				// ignore if non-numeric value
				vo.setUpperLimit(null);
			}
			list.add(vo);
		}
		if (count > 1 && prevBatchId.length() > 0) {
			batches.add(list);
		}
		
	   return batches;
	}

	/**
	 * @param studyIds
	 * @return
	 */
	public List<RegressionDataContainerVO> getRegressionNewStudyData(List<String> studyIds) {
		String sql = "SELECT   pps.study_id, " +
		"           pps.lot_number, " +
		"           NVL(psc.normal_env_conditions_code, psc.challenge_conditions_code) study_condition, " +
		"           pps.filler_code, " +
		"           pps.container_code, " +
		"           pps.closure_code, " +
		"           pps.mfg_date, " +
		"           pps.on_stability_date, " +
		"           nvl(rcdd.dictionary_value_description, psc.study_reason_code) as dictionary_value_description, " +
		"           psc.interval_label " +
		"    FROM   ea_prd_product_study pps, " +
		"           ea_prd_study_conditions psc, " +
		"           ea_dictionary_detail rcdd " +
		"   WHERE       psc.prd_study_batch_id = pps.prd_study_batch_id " +
		"           AND rcdd.dictionary_value (+) = psc.study_reason_code " +
		"           AND rcdd.dictionary_code (+) = 'REASON FOR STUDY' ";
		sql = sql + " and pps.location_id = " + super.getCurrentLocationId() + " ";
		sql = sql + " and rcdd.location_id (+) = " + super.getCurrentLocationId() + " ";
		
		if (studyIds.size() > 0) {
			   int i = 0;
			   sql = sql + "and pps.study_id in (";
		for (Iterator iterator = studyIds.iterator(); iterator.hasNext();) {
			   String studyId = (String) iterator.next();
			   if (i > 0) {
				   sql = sql + ", ";
		}
		sql = sql + "'" + studyId + "'";
			   i++;
		}
		sql = sql + ") ";
		}
		sql = sql + "order by pps.study_id";

		SQLQuery q = persistenceManager.getCurrentSession().createSQLQuery(sql);
		q.addScalar("study_id", Hibernate.STRING);
		q.addScalar("lot_number", Hibernate.STRING);
		q.addScalar("study_condition", Hibernate.STRING);
		q.addScalar("filler_code", Hibernate.STRING);
		q.addScalar("container_code", Hibernate.STRING);
		q.addScalar("closure_code", Hibernate.STRING);
		q.addScalar("mfg_date", Hibernate.DATE);
		q.addScalar("on_stability_date", Hibernate.DATE);
		q.addScalar("dictionary_value_description", Hibernate.STRING);
		q.addScalar("interval_label", Hibernate.STRING);
		
		List details = q.list();
		List<RegressionDataContainerVO> batches = new ArrayList<RegressionDataContainerVO>(); 
		for (Iterator<Object[]> iterator = details.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			RegressionDataContainerVO vo = new RegressionDataContainerVO();
			vo.setStudyId((String) objects[0]);
			vo.setLotNumber((String) objects[1]);
			vo.setCondition((String) objects[2]);
			vo.setFill((String) objects[3]);
			vo.setContainer((String) objects[4]);
			vo.setClosure((String) objects[5]);
			vo.setManufacturedDate((Date) objects[6]);
			vo.setOnStabilityDate((Date) objects[7]);
			vo.setReasonForStudy((String) objects[8]);
			vo.setIntervalLabel((String) objects[9]);
			batches.add(vo);
		}
		return batches;
	}

	/**
	 * @param studyId
	 * @param productTestId
	 * @return
	 */
	public List<StoredDataVO> getDataEntryResults(String studyId, Long productTestId) {
	   String sql = " select b.interval_value, get_result_avg(d.prd_study_test_results_id) as result, d.result_1 ";
		sql = sql + "from EA_PRD_PRODUCT_STUDY a, EA_PRD_STUDY_INTERVALS b, EA_PRD_STUDY_SCHEDULES c, EA_PRD_STUDY_TEST_RESULTS d  ";
		sql = sql + "where a.prd_study_batch_id = b.prd_study_batch_id ";
		sql = sql + "and b.prd_study_interval_id = c.prd_study_interval_id ";
		sql = sql + "and c.prd_study_schedule_id = d.prd_study_schedule_id ";
		sql = sql + "and d.product_test_id = " + productTestId + " ";
		sql = sql + "and a.study_id = '" + studyId + "'";
		sql = sql + " and a.location_id = " + super.getCurrentLocationId()
				+ " ";
		sql = sql + "order by b.interval_value";

		SQLQuery q = persistenceManager.getCurrentSession().createSQLQuery(sql);
		q.addScalar("interval_value", Hibernate.INTEGER);
		q.addScalar("result", Hibernate.DOUBLE);
		q.addScalar("result_1", Hibernate.STRING);

		List details = q.list();
		List<StoredDataVO> results = new ArrayList<StoredDataVO>(); 
		Hashtable dictionaries = new Hashtable<String, Double>();
		for (Iterator<Object[]> iterator = details.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			StoredDataVO vo = new StoredDataVO();
			vo.setInterval(((Integer) objects[0]).intValue());
			Double result = (Double) objects[1];
			String result1 = (String) objects[2];
			vo.setResult("");
			if (result == null) {
				if (result1 != null) {
					vo.setResult(result1);
					Double graphTo = null;
					if (!dictionaries.containsKey(result1)) {
						DictionaryDetail dd = DictionaryFactory.getInstance()
							.getDictionaryDetailForValue("ABBREVIATION", result1);
						if (dd != null) {
							try {
								graphTo = Double.valueOf(dd.getGraphTo());
							} catch (Exception e) {
								// TODO: handle exception
							}
							if (graphTo != null) {
								dictionaries.put(result1,graphTo);
								vo.setResult(graphTo.toString());
							}
						}
					} else {
						graphTo = (Double) dictionaries.get(result1);
						vo.setResult(graphTo.toString());
					}
				}
			} else {
				vo.setResult(result.toString());
			}
			results.add(vo);
		}
		return results;
	}
	/**
	 * @param studyId
	 * @param productTestId
	 * @return
	 */
	public List<DataEntryVO> getDataEntryResultsSummary(String studyId) {
	   String sql = " select d.prd_study_test_results_id, d.product_test_id, b.interval_value, d.result_average as result, d.result_1 ";
		sql = sql + "from EA_PRD_PRODUCT_STUDY a, EA_PRD_STUDY_INTERVALS b, EA_PRD_STUDY_SCHEDULES c, EA_PRD_STUDY_TEST_RESULTS d,  EA_PRD_PRODUCT_TESTS e ";
		sql = sql + "where a.prd_study_batch_id = b.prd_study_batch_id ";
		sql = sql + "and b.prd_study_interval_id = c.prd_study_interval_id ";
		sql = sql + "and c.prd_study_schedule_id = d.prd_study_schedule_id ";
		sql = sql + "and d.product_test_id = e.product_test_id ";
		sql = sql + "and a.study_id = '" + studyId + "' ";
		sql = sql + "and a.location_id = " + super.getCurrentLocationId();
		sql = sql + " order by e.sort_order_current, d.product_test_id, b.interval_value";	//just in case sort order current is not set

		SQLQuery q = persistenceManager.getCurrentSession().createSQLQuery(sql);
		q.addScalar("prd_study_test_results_id", Hibernate.LONG);
		q.addScalar("product_test_id", Hibernate.LONG);
		q.addScalar("interval_value", Hibernate.INTEGER);
		q.addScalar("result", Hibernate.STRING);
		q.addScalar("result_1", Hibernate.STRING);

		List details = q.list();
		List<DataEntryVO> results = new ArrayList<DataEntryVO>(); 
		Hashtable dictionaries = new Hashtable<String, Double>();
		for (Iterator<Object[]> iterator = details.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			DataEntryVO vo = new DataEntryVO();
			vo.setPrdStudyTestResultsId((Long) objects[0]);
			vo.setProductTestId((Long) objects[1]);
			vo.setIntervalValue(((Integer) objects[2]).intValue());
			String resultStr = (String) objects[3];
			Double result = null;
			if (resultStr != null && resultStr.length() > 0) {
				try {
					result = Double.valueOf(resultStr);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
			String result1 = (String) objects[4];
			vo.setResult("");
			if (result == null) {
				if (result1 != null) {
					vo.setResult(result1);
					Double graphTo = null;
					if (!dictionaries.containsKey(result1)) {
						DictionaryDetail dd = DictionaryFactory.getInstance()
							.getDictionaryDetailForValue("ABBREVIATION", result1);
						if (dd != null) {
							try {
								graphTo = Double.valueOf(dd.getGraphTo());
							} catch (Exception e) {
								// TODO: handle exception
							}
							if (graphTo != null) {
								dictionaries.put(result1,graphTo);
								vo.setResult(graphTo.toString());
							}
						}
					} else {
						graphTo = (Double) dictionaries.get(result1);
						vo.setResult(graphTo.toString());
					}
				}
			} else {
				vo.setResult(result.toString());
			}
			results.add(vo);
		}
		return results;
	}

	public List<CodeSwitchProductVO> getCodeSwitchProductsForProduct(String productCode) {
		String sql = 
			"SELECT product_id, product_code, product_name " +
			"  FROM ea_prd_products pp " +
			" WHERE pp.product_code != '" + productCode + "' " +
			"   AND NOT EXISTS (  " +
			"           (SELECT test_name " +
			"               FROM ea_prd_products pp1, ea_prd_product_tests ppt1 " +
			"              WHERE pp1.product_code = '" + productCode + "' " +
			"                AND ppt1.product_id = pp1.product_id " +
			"             MINUS " +
			"             SELECT test_name " +
			"               FROM ea_prd_product_tests ppt " +
			"              WHERE ppt.product_id = pp.product_id) " +
			"           UNION ALL " +
			"           (SELECT test_name " +
			"              FROM ea_prd_product_tests ppt " +
			"             WHERE ppt.product_id = pp.product_id " +
			"            MINUS " +
			"            SELECT test_name " +
			"              FROM ea_prd_products pp1, ea_prd_product_tests ppt1 " +
			"             WHERE pp1.product_code = '" + productCode + "' " +
			"               AND ppt1.product_id = pp1.product_id)) " ;
		
		SQLQuery q = persistenceManager.getCurrentSession().createSQLQuery(sql);
		q.addScalar("product_id", Hibernate.LONG);
		q.addScalar("product_code", Hibernate.STRING);
		q.addScalar("product_name", Hibernate.STRING);
		List products = q.list();
		List<CodeSwitchProductVO> results = new ArrayList<CodeSwitchProductVO>();
		for (Iterator<Object[]> iterator = products.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			CodeSwitchProductVO vo = new CodeSwitchProductVO();
			vo.setProductId((Long) objects[0]);
			vo.setProductCode((String) objects[1]);
			vo.setProductDescription((String) objects[2]);
			results.add(vo);
		}

		return results;
	}
	
	/**
	 * @return
	 */
	public NewStudySummaryVO getNewStudySummaryForReport(String studyId) throws EAPharmicsException {
		Session session = persistenceManager.getCurrentSession();		
		return (NewStudySummaryVO) session.createCriteria(NewStudySummaryVO.class)
											.add(Restrictions.eq("studyId", studyId))
											.setMaxResults(1)
											.uniqueResult();
	}
	
	public NewStudyProduct getProductForStudy(String studyId) throws EAPharmicsException {
		Session session = persistenceManager.getCurrentSession();		
		return (NewStudyProduct) session.createCriteria(NewStudyProduct.class)
		.add(Restrictions.eq("studyId", studyId))
		.setMaxResults(1)
		.uniqueResult();
	}		
	/**
    * @return
    */
   public List getAllRptUserPreferences(List<String> reportGroupName, String reportName, UserVO userVO) {
      Session session = persistenceManager.getCurrentSession();
      Criteria criteria = session.createCriteria(RptUserPreferenceVO.class);
      criteria = criteria.add(Restrictions.eq("appUserId",userVO.getAppUserId()))
                  .add(Restrictions.in("reportGroupName", reportGroupName));
      if (reportName != null)
      {
         criteria = criteria.add(Restrictions.eq("reportName", reportName));
      }
      criteria.add(Restrictions.eq("activeField", true));
      criteria = criteria.addOrder(Order.asc("reportGroupName"))
                          .addOrder(Order.asc("displayOrder"));
      return criteria.list();
   }
   /**
    * @return
    */
   public List getAllRptLocationPreferences(List<String> reportGroupName, String reportName, UserVO userVO) {
      Session session = persistenceManager.getCurrentSession();
      Criteria criteria = session.createCriteria(RptUserPreferenceVO.class);
      criteria = criteria.add(Restrictions.eq("locationId",super.getCurrentLocationId()))
                  .add(Restrictions.in("reportGroupName", reportGroupName));
      if (reportName != null)
      {
         criteria = criteria.add(Restrictions.eq("reportName", reportName));
      }
      criteria.add(Restrictions.eq("activeField", true));
      criteria = criteria.addOrder(Order.asc("reportGroupName"))
                          .addOrder(Order.asc("displayOrder"));
      return criteria.list();
   }
   /**
    * @param eaRptUserPreferenceId
    * @return
    */
   public RptUserPreferenceVO getRptUserPreference(Long eaRptUserPreferenceId) {
      Session session = persistenceManager.getCurrentSession();
      RptUserPreferenceVO rptUserPreference = (RptUserPreferenceVO) session.load(RptUserPreferenceVO.class, eaRptUserPreferenceId);
      return rptUserPreference;
   }

   /**
    * @param rptUserPreference
    */
   public void createRptUserPreference(RptUserPreferenceVO rptUserPreference) {
      Session session = persistenceManager.getCurrentSession();
      session.saveOrUpdate(rptUserPreference);
   }

   /**
    * @param rptUserPreference
    */
   public void updateRptUserPreference(List<RptUserPreferenceVO> rptUserPreference,UserVO userVO) {
         Session session = persistenceManager.getCurrentSession();
         String sqlString = " ";
         RptUserPreferenceVO rptUserPreferenceVO = new RptUserPreferenceVO();
         for (Iterator<RptUserPreferenceVO> iterator = rptUserPreference.iterator(); iterator.hasNext();) 
         {
            rptUserPreferenceVO = iterator.next();     
            String activeFlag = rptUserPreferenceVO.getActive()?"Y":"N";
            sqlString = "UPDATE EA_RPT_USER_PREFERENCE " +
                         "SET "+
                         " ACTIVE = '"+activeFlag + "'"+
                         ",AUDIT_ID= :audit_id"+
                         ",FIELD_VALUE= :field_value"+
                         ",UPDATE_DATE = SYSDATE "+
                         ",UPDATE_USER =:app_user_id"+
                         ",ORDER_BY = :order_by "+
                         ",ORDER_BY_POSITION = :order_by_position" +
                         " WHERE RPT_USER_PREFERENCE_ID = :ref_id";
            int i = session.createSQLQuery(sqlString)
                    .setParameter("audit_id", userVO.getAuditId())
                    .setParameter("field_value", rptUserPreferenceVO.getFieldValue())
                    .setParameter("app_user_id", rptUserPreferenceVO.getAppUserId())
                    .setParameter("ref_id", rptUserPreferenceVO.getRptUserPreferenceId())
                    .setParameter("order_by",rptUserPreferenceVO.getOrderBy() )
                    .setParameter("order_by_position",rptUserPreferenceVO.getOrderByPosition() )
                    .executeUpdate();        
         }     
   }

   /**
    * @param rptUserPreferenceId
    */
   public void deleteRptUserPreference(Long rptUserPreferenceId) {
      Session session = persistenceManager.getCurrentSession();
      RptUserPreferenceVO classVariable = (RptUserPreferenceVO) session.get(RptUserPreferenceVO.class, rptUserPreferenceId);
      session.delete(classVariable);
   }

   /**
    * 
    */
   public List<NewStudySummaryVO> getPendingSchedulerReportData(List scheduleIds,
                                                                String scheduleStatus,
                                                                String monthQuery,
                                                                Long unpulledDays,
                                                                Long lateDaysStart,
                                                                Long lateDaysEnd,
                                                                Long testingCycleNotStarted,
                                                                Long testingCycleNotEnded,                                                                
                                                                String productCode,
                                                                String primarySortColumn,
                                                                String primarySortColumnOrder,
                                                                String secondarySortColumn,
                                                                String secondarySortColumnOrder)
      throws EAPharmicsException 
   {
      ArrayList<Long> scheduleIdParameter = new ArrayList<Long>();
      if (scheduleIds != null && scheduleIds.size() > 0)
      {
         Iterator schId =  scheduleIds.iterator();         
         while (schId.hasNext())
         {
            Long sch = ((Integer)schId.next()).longValue();
            scheduleIdParameter.add(sch);
         }         
      }          
      Session session = persistenceManager.getCurrentSession();
      Criteria criteria = session.createCriteria(NewStudySummaryVO.class);
      // mandatory criterias
      criteria.add(Restrictions.eq("locationId", super.getCurrentLocationId()));
      //criteria = criteria.add(Restrictions.ne("intervalValue","0"));
      criteria = criteria.add(Restrictions.eq("pastInterval",false));
      // build the criterias based on the parameters
      if (scheduleIdParameter != null && scheduleIdParameter.size()>0)
      {
         // if schedule id is passed, we know what records we are looking for
         criteria = criteria.add(Restrictions.in("prdStudyScheduleId", scheduleIdParameter));
      }
      else
      {
         // all other criterias
         if (scheduleStatus != null && scheduleStatus.length() > 0)
         {
            criteria.add(Restrictions.eq("scheduleStatus", scheduleStatus));  
         }
         if (monthQuery != null && monthQuery.length()>0)
         {
            
            criteria.add(Restrictions.eq("scheduledPeriod", monthQuery));
            
         }
         if (unpulledDays != null)
         {
            criteria.add(Restrictions.isNull("datePulled"));
            criteria.add(Restrictions.ge("numberOfDaysLate",unpulledDays));
         }
         if (lateDaysStart != null)
         {
            if (lateDaysEnd != null)
            {
               criteria.add(Restrictions.between("numberOfDaysLate", lateDaysStart, lateDaysEnd));
            }
            else
            {
               criteria.add(Restrictions.gt("numberOfDaysLate", lateDaysStart));
            }
         }
         if (testingCycleNotStarted != null)
         {
            criteria.add(Restrictions.isNull("testStartDate"));
            criteria.add(Restrictions.ge("numberOfDaysLate",testingCycleNotStarted));            
         }
         if (testingCycleNotEnded != null)
         {
            criteria.add(Restrictions.isNull("testEndDate"));
            criteria.add(Restrictions.ge("numberOfDaysLate",testingCycleNotEnded));
         }
         if (productCode != null && productCode.length()>0)
         {
            criteria.add(Restrictions.eq("productCode", productCode));
         }         
      }
      if (primarySortColumn != null)
      {
         if (primarySortColumnOrder.compareToIgnoreCase("asc")==0)
         {
            criteria.addOrder(Order.asc(primarySortColumn));
         }
         else
         {
            criteria.addOrder(Order.desc(primarySortColumn));
         }
         if (secondarySortColumn != null)
         {
            if (secondarySortColumnOrder.compareToIgnoreCase("asc")== 0)
            {
               criteria.addOrder(Order.asc(secondarySortColumn));
            }
            else
            {
               criteria.addOrder(Order.desc(secondarySortColumn));
            }            
         }
      }  
      return criteria.list();

   }
   /**
    * 
    */
   public List<ProductStudyIntervalsVO> getStabilityLogsReportData(String stabilityYear,
                                                                   Date fromStabilityDate,
                                                                   Date toStabilityDate,
                                                                   String productCode, 
                                                                   String lotNumber,
                                                                   String containerCode,
                                                                   String studyStatus,
                                                                   String studyId,
                                                                   String primarySortColumn,
                                                                   String primarySortColumnOrder,
                                                                   String secondarySortColumn,
                                                                   String secondarySortColumnOrder)                                                            
                                                                
      throws EAPharmicsException 
   {       
      Session session = persistenceManager.getCurrentSession();
      Criteria criteria = session.createCriteria(ProductStudyIntervalsVO.class);
      // mandatory criterias
      criteria.add(Restrictions.eq("locationId", super.getCurrentLocationId()));
         // all other criterias
      if (stabilityYear != null && stabilityYear.length()> 0 )
      {
         criteria.add(Restrictions.eq("stabilityYear", stabilityYear));
      }
      if (fromStabilityDate != null)
      {
         if (fromStabilityDate != null && toStabilityDate != null)
         {
            criteria.add(Restrictions.between("onStabilityDate", fromStabilityDate, toStabilityDate));
         }
      }
      if (productCode != null && productCode.length()>0)
      {
         criteria.add(Restrictions.eq("productCode", productCode));
      }  
      if ( lotNumber != null && lotNumber.length() > 0)
      {
         criteria.add(Restrictions.eq("lotNumber",lotNumber));
      }      
      if ( containerCode != null && containerCode.length() > 0 )
      {
         criteria.add(Restrictions.eq("containerCode",containerCode));
      }
      if (studyStatus != null && studyStatus.length() > 0)
      {
         criteria.add(Restrictions.eq("studyStatus",studyStatus));
      }
      if (studyId != null && studyId.length() > 0)
      {
         criteria.add(Restrictions.eq("studyId",studyId));
      }      
      
      if (primarySortColumn != null)
      {
         if (primarySortColumnOrder.compareToIgnoreCase("asc")==0)
         {
            criteria.addOrder(Order.asc(primarySortColumn));
         }
         else
         {
            criteria.addOrder(Order.desc(primarySortColumn));
         }
         if (secondarySortColumn != null)
         {
            if (secondarySortColumnOrder.compareToIgnoreCase("asc")== 0)
            {
               criteria.addOrder(Order.asc(secondarySortColumn));
            }
            else
            {
               criteria.addOrder(Order.desc(secondarySortColumn));
            }            
         }
      }  
      return criteria.list();

   }   
   /**
    * @param productId
    * @param userId
    * @param auditId
    * @throws Exception
    */
   public void createScheduleForStudy(Long prdStudyBatchId, String studyId, Long appUserId,Long auditId) throws Exception {
      Session session = persistenceManager.getCurrentSession();
      Query queryObject = session.getNamedQuery("createScheduleForStudy");
      queryObject.setParameter(0, prdStudyBatchId);
      queryObject.setParameter(1, studyId);
      queryObject.setParameter(2, appUserId);
      queryObject.setParameter(3, auditId);
      queryObject.executeUpdate();
   }   
}
