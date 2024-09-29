package com.enviroapps.eapharmics.persistence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.enviroapps.eapharmics.bom.admin.ApplParameter;
import com.enviroapps.eapharmics.bom.admin.Location;
import com.enviroapps.eapharmics.common.services.UtilityServiceFactory;
import com.enviroapps.eapharmics.common.services.logging.ILogger;
import com.enviroapps.eapharmics.das.persistence.DataAccessConstants;
import com.enviroapps.eapharmics.das.persistence.hibernate.HibernatePersistenceFactory;
import com.enviroapps.eapharmics.das.persistence.hibernate.HibernatePersistenceManager;
import com.enviroapps.eapharmics.das.persistence.hibernate.HibernatePersistenceManagerFactory;
import com.enviroapps.eapharmics.vo.admin.TimezoneVO;


/**
 * @author EnviroApps
 *
 */
public class AdminFactory extends HibernatePersistenceFactory implements
	DataAccessConstants {

	private static ILogger log = UtilityServiceFactory.getLogger();

	private AdminFactory() {
		persistenceManager = (HibernatePersistenceManager) HibernatePersistenceManagerFactory
				.getInstance().getPersistenceManager();
		log.debug(this, "AdminFactory", persistenceManager);
	}

	private static AdminFactory instance = new AdminFactory();

	public static AdminFactory getInstance() {
		return instance;
	}


	/**
	 * @return
	 */
	public List getAllApplParameters() {
		Session session = persistenceManager.getCurrentSession();
		List list = session.createCriteria(ApplParameter.class).add(
				Restrictions.or(Restrictions.eq("locationId", super
						.getCurrentLocationId()), Restrictions
						.isNull("locationId")))
						.addOrder(Order.asc("parameterName")).list();
		return list;
	}

	/**
	 * @param eaApplParameterId
	 * @return
	 */
	public ApplParameter getApplParameter(Long eaApplParameterId) {
		Session session = persistenceManager.getCurrentSession();
		ApplParameter applParameter = (ApplParameter) session.load(ApplParameter.class, eaApplParameterId);
		return applParameter;
	}

	/**
	 * @param parameterName
	 * @return
	 */
	public ApplParameter getApplParameterByName(String parameterName) {
		Session session = persistenceManager.getCurrentSession();
		List parms = session.createCriteria(ApplParameter.class)
			.add(Restrictions.eq("parameterName", parameterName))
			.add(Restrictions.or(Restrictions.eq("locationId", 
					super.getCurrentLocationId()), Restrictions
					.isNull("locationId")))
			.list();
		if (parms != null && parms.size() > 0) {
			return (ApplParameter) parms.get(0);
		}
		return null;
	}

	/**
	 * @param applParameter
	 */
	public void createApplParameter(ApplParameter applParameter) {
		Session session = persistenceManager.getCurrentSession();
		applParameter.setLocationId(super.getCurrentLocationId());
		session.saveOrUpdate(applParameter);
	}

	/**
	 * @param applParameter
	 */
	public void updateApplParameter(ApplParameter applParameter) {
		Session session = persistenceManager.getCurrentSession();
		session.saveOrUpdate(applParameter);
	}

	/**
	 * @param applParameterId
	 */
	public void deleteApplParameter(Long applParameterId) {
		Session session = persistenceManager.getCurrentSession();
		ApplParameter classVariable = (ApplParameter) session.get(ApplParameter.class, applParameterId);
		session.delete(classVariable);
	}

	/**
	 * @return
	 */
	public List getAllLocations() {
		Session session = persistenceManager.getCurrentSession();
		List list = session.createCriteria(Location.class).addOrder(Order.asc("locationName"))
					.list();
		return list;
	}

	/**
	 * @param eaLocationId
	 * @return
	 */
	public Location getLocation(Long eaLocationId) {
		Session session = persistenceManager.getCurrentSession();
		Location location = (Location) session.load(Location.class, eaLocationId);
		return location;
	}

	/**
	 * @param location
	 */
	public void createLocation(Location location) {
		Session session = persistenceManager.getCurrentSession();
		session.saveOrUpdate(location);
	}

	/**
	 * @param location
	 */
	public void updateLocation(Location location) {
		Session session = persistenceManager.getCurrentSession();
		session.saveOrUpdate(location);
	}

	/**
	 * @param locationId
	 */
	public void deleteLocation(Long locationId) {
		Session session = persistenceManager.getCurrentSession();
		Location classVariable = (Location) session.get(Location.class, locationId);
		session.delete(classVariable);
	}

	/**
	 * @return
	 */
	public List<TimezoneVO> getAllTimezoneNames() {
		List<TimezoneVO> list = new ArrayList<TimezoneVO> ();
		String sql = "SELECT TIMEZONE_NAME, TZ_OFFSET(TIMEZONE_NAME) AS TZ_OFFSET FROM EA_TIMEZONENAMES ORDER BY TIMEZONE_NAME";
		SQLQuery q = persistenceManager.getCurrentSession().createSQLQuery(sql);
		q.addScalar("TIMEZONE_NAME", Hibernate.STRING);
		q.addScalar("TZ_OFFSET", Hibernate.STRING);
	    List<Object[]> details = q.list();
	    for (Iterator<Object[]> iterator = details.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			TimezoneVO vo = new TimezoneVO();
			vo.setTimezoneName(((String) objects[0]));
			String tzOffset =  (String) objects[1];
			vo.setOffsetFromUTC(calculateOffsetFromUTC(tzOffset));
			list.add(vo);
	    }
		return list;
	}

	/**
	 * @param timezoneName
	 * @return
	 */
	public long getTimezoneOffset(String timezoneName) {
		if (timezoneName == null || timezoneName.trim().length() == 0) {
			return 0l;
		}
		List<TimezoneVO> list = new ArrayList<TimezoneVO> ();
		String sql = "SELECT TZ_OFFSET('" + timezoneName + "') AS TZ_OFFSET FROM DUAL";
		SQLQuery q = persistenceManager.getCurrentSession().createSQLQuery(sql)
			.addScalar("TZ_OFFSET", Hibernate.STRING);
	    List details = q.list();
		if (details != null && details.size() > 0) {
			if (details.get(0) != null) {
				String tzOffset = (String) details.get(0);
				return calculateOffsetFromUTC(tzOffset);
			}
		}
		return 0l;
	}

	/**
	 * @param tzOffset
	 * @return
	 */
	private long calculateOffsetFromUTC(String tzOffset) {
		long offsetFromUTC = 0l;
		if (tzOffset != null && tzOffset.length() > 0) {
			String sign = tzOffset.substring(0, 1);
			String hours = tzOffset.substring(1, 3);
			String minutes = tzOffset.substring(4, 6);
			offsetFromUTC = (Integer.valueOf(hours).intValue() * 60 * 60 * 1000) + 
				(Integer.valueOf(minutes).intValue() * 60 * 1000);
			if (sign.equals("-")) {
				offsetFromUTC = offsetFromUTC * -1;
			}
		}
		return offsetFromUTC;
		
	}

}
