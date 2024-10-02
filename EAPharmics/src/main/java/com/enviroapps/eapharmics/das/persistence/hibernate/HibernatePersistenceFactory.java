package com.enviroapps.eapharmics.das.persistence.hibernate;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.TransientObjectException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.beans.factory.annotation.Value;

import com.enviroapps.eapharmics.common.Utility;
import com.enviroapps.eapharmics.common.services.UtilityServiceFactory;
import com.enviroapps.eapharmics.das.persistence.AbstractBusinessObject;
import com.enviroapps.eapharmics.das.persistence.AbstractPersistableObject;
import com.enviroapps.eapharmics.das.persistence.BusinessObjectFactory;
import com.enviroapps.eapharmics.das.persistence.PersistenceFactory;
import com.enviroapps.eapharmics.das.persistence.query.OrderingParameter;
import com.enviroapps.eapharmics.exception.EAPharmicsException;
import com.enviroapps.eapharmics.ui.Constants;
import com.enviroapps.eapharmics.vo.security.UserVO;

import jakarta.servlet.http.HttpSession;

/**
 * abstract persistence class that provides all the persistence related
 * methods by hiding the Toplink persistence implemetation.
 * 
 * @author EnviroApps
 * @version $Revision: 1.1 $
 */
public abstract class HibernatePersistenceFactory implements PersistenceFactory {

	/**
	 * Instance of Persistance Manager
	 */
	protected static HibernatePersistenceManager persistenceManager;

	// Not used
	protected boolean auditingEnabled;

	@Value("${dataaccess.max.resultset.size}")
	private Integer maxResultsetSize = null; 
	
	/**
	 * Get an instance of DAS Framework Query Builder class.
	 * 
	 */
	protected HibernatePersistenceFactory() {
	}

	/**
	 * Create a record in the database for the specifed new object.
	 * 
	 * @param object
	 *            to be created
	 */
	public void create(Object o) {
		Session session = persistenceManager.getCurrentSession();
		session.saveOrUpdate(o);
	}

	/**
	 * Create a record in the database for the specifed object whether it's
	 * already existing or new.
	 * 
	 * @param o
	 * @param hasExistingObjects
	 *            ignored
	 */
	public void create(Object o, boolean hasExistingObjects) {
		Session session = persistenceManager.getCurrentSession();
		session.saveOrUpdate(o);
	}

	/**
	 * Call a named query in the ORM mapping. Provide a set of ordered arguments
	 * to be bound to the query.
	 * 
	 * @param namedQuery
	 *            the query to call
	 * @param args
	 *            the query parameters in order.
	 * @return results of query.
	 */
	public Object[] getNamedQuery(String namedQuery, Object[] args) {

		Session session = persistenceManager.getCurrentSession();
		Query query = session.getNamedQuery(namedQuery);
		// if we have arguments
		if (args != null && args.length > 0) {
			for (int x = 0; x < args.length; x++) {
				query.setParameter(x, args[x]);
			}
		}
		return query.list().toArray();
	}

	/**
	 * Fetch a record from the persistence for the specified class and primary
	 * key for the class.
	 * 
	 * @param objectClass
	 * @param pk
	 * @return object containg the record
	 */
	public Object load(Class objectClass, Object pk) {
		return load(objectClass, new Object[] { pk });
	}

	/**
	 * Fetch a record from the persistence for the specified class and primary
	 * keys for the class.
	 * 
	 * @param objectClass
	 * @param pk
	 * @return object containg the record
	 */
	public Object load(Class objectClass, Object[] pk) {
		AbstractPersistableObject po = BusinessObjectFactory
				.newPersistableObjectInstance(objectClass);
		po.loadPK(pk);
		return load(po);
	}

	/**
	 * Load the record from the persistence for the specified object
	 * 
	 * @param o
	 * @return object containg the record
	 */
	public Object load(Object o) {
		Session session = null;
		try {
			session = persistenceManager.getCurrentSession();
			Object object = session.get(o.getClass(),
					((AbstractPersistableObject) o).getPK());
			// Object cloneObject = Utility.cloneObject(object);
			return object;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Register the object in Toplink for update.
	 * 
	 * @param objectClass
	 * @param pk
	 * @param lockRequired
	 * @return Object
	 */
	public Object loadForUpdate(Class objectClass, Object pk,
			boolean lockRequired) {
		return loadForUpdate(objectClass, new Object[] { pk }, lockRequired);
	}

	/**
	 * Register the object in Toplink for update.
	 * 
	 * @param objectClass
	 * @param pk
	 * @param lockRequired
	 * @return Object
	 */
	public Object loadForUpdate(Class objectClass, Object[] pk,
			boolean lockRequired) {
		AbstractPersistableObject po = BusinessObjectFactory
				.newPersistableObjectInstance(objectClass);
		po.loadPK(pk);
		return loadForUpdate(po, lockRequired);
	}

	/**
	 * Register the object in Hibernate for update with an option of putting a
	 * lock on the record. Utilizes <code>Session.refresh()</code> with a
	 * <code>LockMode.UPGRADE</code> if lockRequired is true.
	 * 
	 * @param o
	 * @param lockRequired
	 * @return Object
	 */
	public Object loadForUpdate(Object o, boolean lockRequired) {
		Session session = persistenceManager.getCurrentSession();
		// register for update
		session.saveOrUpdate(o);
		// modify locking
		if (lockRequired) {
			session.refresh(o, LockMode.UPGRADE);
		}
		return o;
	}

	/**
	 * Register the object in Hibernate for update with an option of forcing a
	 * optimistic read lock check with no version update on the record as well
	 * as putting a lock on the record.
	 * 
	 * @param o
	 * @param lockRequired
	 *            refresh object with <code>LockMode.UPGRADE</code>
	 * @param forceUpdateVersionLock
	 *            refresh object with <code>LockMode.FORCE</code>
	 * @return the refreshed version of o for API consistency.
	 */
	public Object loadForUpdate(Object o, boolean lockRequired,
			boolean forceUpdateVersionLock) {
		Session session = persistenceManager.getCurrentSession();
		// register for update
		session.saveOrUpdate(o);
		// modify locking
		if (lockRequired) {
			session.lock(o, LockMode.UPGRADE);
		}
		// modify versioning
		if (forceUpdateVersionLock) {
			session.lock(o, LockMode.FORCE);
		}
		return o;
	}

	/**
	 * Update the record in the database for the specified object
	 * 
	 * @param o
	 */
	public void store(Object o) {
		store(o, false);
	}

	/**
	 * Update the record in the database for the specified object with an option
	 * of forcing optimistic read lock check with no version update
	 * 
	 * @param o
	 * @param forceUpdateVersionLock
	 *            Update object with <code>LockMode.FORCE</code>
	 */
	public void store(Object o, boolean forceUpdateVersionLock) {
		Session session = persistenceManager.getCurrentSession();
		// register for update
		try {
			session.merge(o);
		} catch (TransientObjectException e) {
			session.saveOrUpdate(o);
		}
		// modify versioning
		if (forceUpdateVersionLock) {
			session.lock(o, LockMode.FORCE);
		}
	}

	/**
	 * Abstracts the Hibernate Optimistic read lock check operation.
	 * 
	 * @param o
	 */
	public void forceUpdateVersionLock(Object o) {
		Session session = persistenceManager.getCurrentSession();
		// modify versioning
		session.lock(o, LockMode.FORCE);
	}

	/**
	 * Abstracts the Hibernate delete operation.
	 * 
	 * @param o
	 */
	public void delete(Object o) {
		Session session = persistenceManager.getCurrentSession();
		session.delete(o);
	}

	/**
	 * Abstracts the Hibernate delete operation using primary key.
	 * 
	 * @param objectClass
	 * @param pk
	 */
	public void delete(Class objectClass, Object pk) {
		delete(objectClass, new Object[] { pk });
	}

	/**
	 * Abstracts the Hibernate delete operation using primary key list.
	 * 
	 * @param objectClass
	 * @param pk
	 */
	public void delete(Class objectClass, Object[] pk) {
		Object po = load(objectClass, pk);
		delete(po);
	}

	/**
	 * Create multiple new records in the database for the specified business
	 * object.
	 * 
	 * @param businessObject
	 */
	public void createLinkedObjects(AbstractBusinessObject businessObject) {
		List createLinkedObjects = businessObject.getAddedLinkedObjects();
		createObjects(createLinkedObjects);
		businessObject.clearAddedLinkedObjects();
	}

	/**
	 * Create multiple new records in the database for the specified list
	 * containing business object.
	 * 
	 * @param createdObjects
	 */
	public void createObjects(List createdObjects) {
		Session session = persistenceManager.getCurrentSession();
		if (createdObjects != null) {
			Iterator iter = createdObjects.iterator();

			/*
			 * Loop through each object and register it with toplink to create a
			 * new record in the database
			 */
			while (iter.hasNext()) {
				Object createdObject = iter.next();
				// create persistent object
				session.save(createdObject);
			}
		}
	}

	/**
	 * Delete multiple records from the database for the specified business
	 * object.
	 * 
	 * @param businessObject
	 */
	public void deleteLinkedObjects(AbstractBusinessObject businessObject) {
		List deletedLinkedObjects = businessObject.getDeletedLinkedObjects();
		deleteObjects(deletedLinkedObjects);
		businessObject.clearDeletedLinkedObjects();
	}

	/**
	 * Delete multiple records from the database for the specified list
	 * containing business object.
	 * 
	 * @param deletedObjects
	 */
	public void deleteObjects(List deletedObjects) {
		Session session = persistenceManager.getCurrentSession();
		if (deletedObjects != null) {
			Iterator iter = deletedObjects.iterator();
			/*
			 * Loop through each object and call hibernate methods to delete an
			 * existing record from the database
			 */
			while (iter.hasNext()) {
				Object deletedObject = iter.next();
				// delete persistent objects
				session.delete(deletedObject);
			}
		}
	}

	/**
	 * Query the database for the specified object
	 * 
	 * @param queryObject
	 * @return list of objects
	 */
	public Object[] query(Object queryObject) {
		return query(queryObject, (List) null);
	}

	/**
	 * Query the database based on the Query object and sort order parameters
	 * and fetch the list of objects as result.
	 * 
	 * @param queryObject
	 * @param orderingParameters
	 * @return list of objects
	 */
	public Object[] query(Object queryObject, List orderingParameters) {
		Session session = null;
		session = persistenceManager.getCurrentSession();
		Criteria crit = session.createCriteria(queryObject.getClass());

		if (orderingParameters != null && !orderingParameters.isEmpty()) {

			/*
			 * Loops through each of the ordering parameter and set the order in
			 * the query.
			 */
			Iterator orderingParameterIter = orderingParameters.iterator();
			while (orderingParameterIter.hasNext()) {
				OrderingParameter orderingParameter = (OrderingParameter) orderingParameterIter
						.next();
				if (orderingParameter.isDescending()) {
					crit.addOrder(Order.desc(orderingParameter
							.getOrderingParameterName()));
				} else {
					crit.addOrder(Order.asc(orderingParameter
							.getOrderingParameterName()));
				}
			}
		}

		// Query the database and convert the result into a list of objects
		return crit.list().toArray();
	}

	/**
	 * Get all the records for the specified Query Class.
	 * 
	 * @param queryObjectClass
	 * @return list of Objects
	 */
	public Object[] queryAll(Class queryObjectClass) {
		return queryAll(queryObjectClass, (List) null);
	}

	/**
	 * Get all the records for the specified Query Class and list of sort order
	 * parameters.
	 * 
	 * @param queryObjectClass
	 * @param orderingParameters
	 * @return list of Objects
	 */
	public Object[] queryAll(Class queryObjectClass, List orderingParameters) {
		return query(queryObjectClass, orderingParameters);
	}

	/**
	 * Load object by registering it with ORM for update only purpose.
	 * 
	 * @param object
	 * @return object
	 */
	public Object registerObject(Object object) {
		return loadForUpdate(object, false);
	}
	

	/** 
	 * Return the selected session id for the current logged in user
	 * data is partitioned by this location, hence when creating or
	 * querying this separated data, we need to provide this data
	 * @return
	 */
	public Long getCurrentLocationId() {
		//@TODO Sundar
		//FlexSession session = FlexContext.getFlexSession();
		HttpSession session = null;
		if (session != null) {
			UserVO userVO = (UserVO) session.getAttribute(Constants.USER_ATTRIBUTE);
			if (userVO == null) {
				return null;
			}
			return userVO.getSelectedLocationId();
		} else {
			System.err.println("No flex session found");
			return new Long(1);
		}
	}
	
	/**
	 * This method ensures that the result set is a small number of records 
	 * that are sent out to the UI. If we get more than the MAX_RESULTS_SIZE
	 * field, we will ask the user to use some kind of filter criteria.  
	 * 
	 * @param list
	 * @throws EAPharmicsException
	 */
	protected void checkResultSize(List list) throws EAPharmicsException {
		if (list == null || list.size() == 0) {
			return;
		}
		if (maxResultsetSize == null) {
			maxResultsetSize = new Integer(0);
			String value = UtilityServiceFactory.getConfigurator().getProperty(Constants.MAX_RESULTS_SIZE);
			if (value != null && value.length() > 0) {
				try {
					maxResultsetSize = new Integer(Integer.parseInt(value));
				} catch (NumberFormatException e) {
					//ignore error and assume a value of 0 
				}
			}
		}
		if (maxResultsetSize.intValue() == 0) {
			//value of 0 means don't check size. allow all results
			return;
		}
		if (list.size() > maxResultsetSize) {
			throw new EAPharmicsException("Too many entries to display. Please use filter criteria to reduce results!");
		}
	}
	
	/**
	 * @param fieldName
	 * @param minValue
	 * @param maxValue
	 * @param addAndClause
	 * @return
	 */
	public String getQueryString(String fieldName, Date minValue, Date maxValue, boolean addAndClause) {
		String sql = "";
		if (minValue == null && maxValue == null) {
			return sql;
		}
		
		if (addAndClause) {
			sql = sql + " AND ";
		}
		
		if (minValue != null & maxValue != null) {
			sql = sql + fieldName + " between to_date('" + Utility.dateToString(minValue, Utility.DEFAULT_DATE_FORMAT) +  "', '" + Utility.DEFAULT_DATE_FORMAT + "')";
			sql = sql + " AND to_date('" + Utility.dateToString(maxValue, Utility.DEFAULT_DATE_FORMAT) +  "', '" + Utility.DEFAULT_DATE_FORMAT + "')";
			return sql;
		}
		
		if (minValue != null ) {
			sql = "AND " + fieldName + " >= to_date('" + Utility.dateToString(minValue, Utility.DEFAULT_DATE_FORMAT) +  ", '" + Utility.DEFAULT_DATE_FORMAT + "')";
			return sql;
		}
		
		if (maxValue != null) {
			sql = "AND " + fieldName + " <= to_date('" + Utility.dateToString(maxValue, Utility.DEFAULT_DATE_FORMAT) +  ", '" + Utility.DEFAULT_DATE_FORMAT + "')";
		}
		
		return sql;
	}
	
	/**
	 * @param fieldName
	 * @param value
	 * @return
	 */
	public SimpleExpression getExpression(String fieldName, String value) {
		if (value.indexOf("*") >= 0) {
			return Restrictions.like(fieldName, value.replace("*", "%"));
		}
		return Restrictions.eq(fieldName, value);
	}
}