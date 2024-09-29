package com.enviroapps.eapharmics.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.enviroapps.eapharmics.bom.dictionary.DictionaryDetail;
import com.enviroapps.eapharmics.bom.dictionary.DictionaryMaster;
import com.enviroapps.eapharmics.common.services.UtilityServiceFactory;
import com.enviroapps.eapharmics.common.services.logging.ILogger;
import com.enviroapps.eapharmics.das.persistence.DataAccessConstants;
import com.enviroapps.eapharmics.das.persistence.hibernate.HibernatePersistenceFactory;
import com.enviroapps.eapharmics.das.persistence.hibernate.HibernatePersistenceManager;
import com.enviroapps.eapharmics.das.persistence.hibernate.HibernatePersistenceManagerFactory;
import com.enviroapps.eapharmics.exception.EAPharmicsException;


/**
 * @author EnviroApps
 *
 */
public class DictionaryFactory extends HibernatePersistenceFactory implements
	DataAccessConstants {

	private static ILogger log = UtilityServiceFactory.getLogger();

	private DictionaryFactory() {
		persistenceManager = (HibernatePersistenceManager) HibernatePersistenceManagerFactory
				.getInstance().getPersistenceManager();
		log.debug(this, "SecurityFactory", persistenceManager);
	}

	private static DictionaryFactory instance = new DictionaryFactory();

	public static DictionaryFactory getInstance() {
		return instance;
	}

	/**
	 * @return
	 */
	public List getAllDictionaryMasters() {
		Session session = persistenceManager.getCurrentSession();
		List masters = session.createCriteria(DictionaryMaster.class)
			.addOrder(Order.asc("dictionaryDescription")).list();
		return masters;
	}

	public List getAllDictionaryDetails() {
		Session session = persistenceManager.getCurrentSession();
		List masters = session.createCriteria(DictionaryDetail.class)
			.add(Restrictions.eq("locationId", super.getCurrentLocationId()))
			.addOrder(Order.asc("dictionaryCode")).list();
		return masters;
	}

	/**
	 * @param dictionaryMasterId
	 * @return
	 */
	public List getAllDictionaryDetails(Long dictionaryMasterId) {
		Session session = persistenceManager.getCurrentSession();
		List details = session.createCriteria(DictionaryMaster.class)
			.add(Restrictions.eq("dictionaryMasterId", dictionaryMasterId))
			.add(Restrictions.eq("locationId", super.getCurrentLocationId()))
			.addOrder(Order.asc("dictionaryValueDescription")).list();
		return details;
	}

	/**
	 * @param eaDictionaryMasterId
	 * @return
	 */
	public DictionaryMaster getDictionaryMaster(Long eaDictionaryMasterId) {
		Session session = persistenceManager.getCurrentSession();
		DictionaryMaster dictionaryMaster = (DictionaryMaster) session.load(DictionaryMaster.class, eaDictionaryMasterId);
		return dictionaryMaster;
	}

	/**
	 * @param dictionaryCode
	 * @return
	 */
	public DictionaryMaster getDictionaryMasterForCode(String dictionaryCode) {
		Session session = persistenceManager.getCurrentSession();
		List details = session.createCriteria(DictionaryMaster.class)
		.add(Restrictions.eq("dictionaryCode", dictionaryCode)).list();
		return (DictionaryMaster) details.get(0);
	}

	/**
	 * @param dictionaryMaster
	 */
	public void createDictionaryMaster(DictionaryMaster dictionaryMaster) {
		Session session = persistenceManager.getCurrentSession();
		session.saveOrUpdate(dictionaryMaster);
	}

	/**
	 * @param dictionaryMaster
	 */
	public void updateDictionaryMaster(DictionaryMaster dictionaryMaster) {
		Session session = persistenceManager.getCurrentSession();
		session.saveOrUpdate(dictionaryMaster);
	}

	/**
	 * @param dictionaryMasterId
	 */
	public void deleteDictionaryMaster(Long dictionaryMasterId) {
		Session session = persistenceManager.getCurrentSession();
		DictionaryMaster master = (DictionaryMaster) session.get(DictionaryMaster.class, dictionaryMasterId);
		session.delete(master);
	}

	/**
	 * @param eaDictionaryDetailId
	 * @return
	 */
	public DictionaryDetail getDictionaryDetail(Long eaDictionaryDetailId) {
		Session session = persistenceManager.getCurrentSession();
		DictionaryDetail dictionaryDetail = (DictionaryDetail) session.load(DictionaryDetail.class, eaDictionaryDetailId);
		return dictionaryDetail;
	}

	/**
	 * @param dictionaryCode
	 * @return
	 */
	public List<DictionaryDetail> getDictionaryDetailForCode(
			String dictionaryCode) {
		if (dictionaryCode == null || dictionaryCode.length() == 0) {
			return new ArrayList<DictionaryDetail>();
		}
		Session session = persistenceManager.getCurrentSession();
		List details = null;
		DictionaryMaster m = getDictionaryMasterForCode(dictionaryCode);
		if (m.getGlobalDictionary()) {
			details = session.createCriteria(DictionaryDetail.class).add(
					Restrictions.eq("dictionaryCode", dictionaryCode)).add(
					Restrictions.isNull("locationId"))
					.addOrder(Order.asc("displayOrder")).list();
		} else {
			details = session.createCriteria(DictionaryDetail.class).add(
					Restrictions.eq("dictionaryCode", dictionaryCode))
					.add(
							Restrictions.eq("locationId", super
									.getCurrentLocationId()))
									.addOrder(Order.asc("displayOrder")).list();
		}
		if (details != null && details.size() > 0) {
			return (List<DictionaryDetail>) details;
		}
		return new ArrayList<DictionaryDetail>();
	}

	/**
	 * @param dictionaryCode
	 * @param dictionaryValue
	 * @return
	 */
	public DictionaryDetail getDictionaryDetailForValue(String dictionaryCode, String dictionaryValue) {
		Session session = persistenceManager.getCurrentSession();
		List details = null;
		DictionaryMaster m = getDictionaryMasterForCode(dictionaryCode);
		if (m.getGlobalDictionary()) {
			details = session.createCriteria(DictionaryDetail.class)
				.add(Restrictions.eq("dictionaryCode", dictionaryCode))
				.add(Restrictions.eq("dictionaryValue", dictionaryValue))
				.add(Restrictions.isNull("locationId"))
				.addOrder(Order.asc("displayOrder")).list();
		} else {
			details = session.createCriteria(DictionaryDetail.class)
				.add(Restrictions.eq("dictionaryCode", dictionaryCode))
				.add(Restrictions.eq("dictionaryValue", dictionaryValue))
				.add(Restrictions.eq("locationId", 
						super.getCurrentLocationId()))
				.addOrder(Order.asc("displayOrder")).list();
		}
		if (details != null && details.size() > 0) {
			return (DictionaryDetail) details.get(0);
		}
		return null;
	}

	/**
	 * @param dictionaryDetail
	 */
	public void createDictionaryDetail(DictionaryDetail dictionaryDetail) {
		Session session = persistenceManager.getCurrentSession();
		DictionaryMaster m = getDictionaryMasterForCode(dictionaryDetail.getDictionaryCode());
		if (m.getGlobalDictionary()) {
			dictionaryDetail.setLocationId(null);
		} else {
			dictionaryDetail.setLocationId(super.getCurrentLocationId());
		}
		session.saveOrUpdate(dictionaryDetail);
	}

	/**
	 * @param dictionaryDetail
	 */
	public void updateDictionaryDetail(DictionaryDetail dictionaryDetail) {
		Session session = persistenceManager.getCurrentSession();
		session.saveOrUpdate(dictionaryDetail);
	}

	/**
	 * @param dictionaryDetail
	 */
	public void deleteDictionaryDetail(Long dictionaryDetailId) {
		Session session = persistenceManager.getCurrentSession();
		DictionaryDetail detail = (DictionaryDetail) session.get(DictionaryDetail.class, dictionaryDetailId);
		session.delete(detail);
	}
	
	/**
	 * @param dictionaryCode
	 * @return
	 * @throws EAPharmicsException
	 */
	public int getMaxDetailDisplayOrder(String dictionaryCode) throws EAPharmicsException {
		int displayOrder = 0;
		Session session = persistenceManager.getCurrentSession();
		DictionaryMaster master = getDictionaryMasterForCode(dictionaryCode);
		if (master == null) {
			throw new EAPharmicsException("Cannot find Dictionary Master for code " + dictionaryCode);
		}
		String sql = "SELECT MAX(DISPLAY_ORDER) AS DISPLAY_ORDER FROM EA_DICTIONARY_DETAIL WHERE DICTIONARY_CODE='" + dictionaryCode +"'";
		if (master.getGlobalDictionary() == true) {
			//global dictionaries don't have location ids  
			sql = sql + " AND LOCATION_ID IS NULL";
		} else {
			sql = sql + " AND LOCATION_ID=" + super.getCurrentLocationId();
		}
		//Get the last used display order for this dictionary code
		//and add 1 and return
		List details = session.createSQLQuery(sql)
		 .addScalar("DISPLAY_ORDER", Hibernate.INTEGER).list();
		if (details != null && details.size() > 0) {
			if (details.get(0) != null) {
				displayOrder = ((Integer) details.get(0)).intValue();
				return displayOrder + 1;
			}
		}
		return displayOrder == 0 ? 1:displayOrder;
	}
	
	/*Added by Ramya */
	public List getAllDictionary() {
      Session session = persistenceManager.getCurrentSession();
      Criteria criteria = session.createCriteria(DictionaryDetail.class)
		.add(Restrictions.eq("locationId", super.getCurrentLocationId()))
      	.addOrder(Order.asc("dictionaryCode"))
      	.addOrder(Order.asc("displayOrder"));
      List list = criteria.list();
      return list;
   }  
	/**
	 * @param dictionaryCode
	 * @return
	 */
	public String getDictionarySeq() {
		long code = 0;
		Session session = persistenceManager.getCurrentSession();
		//Get the last used display order for this dictionary code
		//and add 1 and return
		List details = session.createSQLQuery("SELECT REF_SEQ.NEXTVAL AS VAL FROM DUAL")
		 .addScalar("VAL", Hibernate.LONG).list();
		if (details != null && details.size() > 0) {
			if (details.get(0) != null) {
				code = ((Long) details.get(0)).longValue();
				return "Code " + code;
			}
		}
		return "Code1 ";
	}
	
}
