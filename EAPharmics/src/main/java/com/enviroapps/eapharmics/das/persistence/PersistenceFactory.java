package com.enviroapps.eapharmics.das.persistence;

import java.util.List;

public interface PersistenceFactory {

	/**
	 * Create a record in the database for the specifed new object.
	 *
	 * @param object
	 *            to be created
	 */
	public abstract void create(Object o);

	/**
	 * Create a record in the database for the specifed object whether it's
	 * already existing or new.
	 *
	 * @param o
	 * @param hasExistingObjects
	 */
	public abstract void create(Object o, boolean hasExistingObjects);

	/**
	 * Fetch a record from the persistence for the specified class and primary
	 * key for the class.
	 *
	 * @param objectClass
	 * @param pk
	 * @return object containing the record
	 */
	public abstract Object load(Class objectClass, Object pk);

	/**
	 * Fetch a record from the persistence for the specified class and primary
	 * keys for the class.
	 *
	 * @param objectClass
	 * @param pk
	 * @return object containg the record
	 */
	public abstract Object load(Class objectClass, Object[] pk);

	/**
	 * Load the record from the persistence for the specified object
	 *
	 * @param o
	 * @return object containg the record
	 */
	public abstract Object load(Object o);

	/**
	 * Register the object in ORM for update.
	 *
	 * @param objectClass
	 * @param pk
	 * @param lockRequired
	 * @return Object
	 */
	public abstract Object loadForUpdate(Class objectClass, Object pk,
			boolean lockRequired);

	/**
	 * Register the object in ORM for update.
	 *
	 * @param objectClass
	 * @param pk
	 * @param lockRequired
	 * @return Object
	 */
	public abstract Object loadForUpdate(Class objectClass, Object[] pk,
			boolean lockRequired);

	/**
	 * Register the object in ORM for update with an option of putting a
	 * lock on the record.
	 *
	 * @param o
	 * @param lockRequired
	 * @return Object
	 */
	public abstract Object loadForUpdate(Object o, boolean lockRequired);

	/**
	 * Register the object in ORM for update with an option of forcing a
	 * optimistic read lock check with no version update on the record as well
	 * as putting a lock on the record.
	 *
	 * @param o
	 * @param lockRequired
	 * @param forceUpdateVersionLock
	 * @return Object
	 */
	public abstract Object loadForUpdate(Object o, boolean lockRequired,
			boolean forceUpdateVersionLock);

	/**
	 * Update the record in the database for the specified object
	 *
	 * @param o
	 */
	public abstract void store(Object o);

	/**
	 * Update the record in the database for the specified object with an option
	 * of forcing optimistic read lock check with no version update
	 *
	 * @param o
	 * @param forceUpdateVersionLock
	 */
	public abstract void store(Object o, boolean forceUpdateVersionLock);

	/**
	 * Abstracts the ORM Optimistic read lock check operation.
	 *
	 * @param o
	 */
	public abstract void forceUpdateVersionLock(Object o);

	/**
	 * Abstracts the ORM delete operation.
	 *
	 * @param o
	 */
	public abstract void delete(Object o);

	/**
	 * Abstracts the ORM delete operation using primary key.
	 *
	 * @param objectClass
	 * @param pk
	 */
	public abstract void delete(Class objectClass, Object pk);

	/**
	 * Abstracts the ORM delete operation using primary key list.
	 *
	 * @param objectClass
	 * @param pk
	 */
	public abstract void delete(Class objectClass, Object[] pk);

	/**
	 * Create multiple new records in the database for the specified business
	 * object.
	 *
	 * @param businessObject
	 */
	public abstract void createLinkedObjects(
			AbstractBusinessObject businessObject);

	/**
	 * Create multiple new records in the database for the specified list
	 * containing business object.
	 *
	 * @param createdObjects
	 */
	public abstract void createObjects(List createdObjects);

	/**
	 * Delete multiple records from the database for the specified business
	 * object.
	 *
	 * @param businessObject
	 */
	public abstract void deleteLinkedObjects(
			AbstractBusinessObject businessObject);

	/**
	 * Delete multiple records from the database for the specified list
	 * containing business object.
	 *
	 * @param deletedObjects
	 */
	public abstract void deleteObjects(List deletedObjects);

	/**
	 * Query the database for the specified object
	 *
	 * @param queryObject
	 * @return list of objects
	 */
	public abstract Object[] query(Object queryObject);

	/**
	 * Query the database based on the Query object and sort order parameters
	 * and fetch the list of objects as result.
	 *
	 * @param queryObject
	 * @param orderingParameters
	 * @return list of objects
	 */
	public abstract Object[] query(Object queryObject, List orderingParameters);

	/**
	 * Get all the records for the specified Query Class.
	 *
	 * @param queryObjectClass
	 * @return list of Objects
	 */
	public abstract Object[] queryAll(Class queryObjectClass);

	/**
	 * Get all the records for the specified Query Class and list of sort order
	 * parameters.
	 *
	 * @param queryObjectClass
	 * @param orderingParameters
	 * @return list of Objects
	 */
	public abstract Object[] queryAll(Class queryObjectClass,
			List orderingParameters);

	/**
	 * Load object by registering it with ORM for update only purpose.
	 *
	 * @param object
	 * @return object
	 */
	public abstract Object registerObject(Object object);

}