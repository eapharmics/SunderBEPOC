package com.enviroapps.eapharmics.das.persistence;



/**
 * @author EnviroApps
 * @version $Revision: 1.1 $
 */
public class BusinessObjectFactory {
	public static String getGUID() {
		String guid = "111";
		return guid;
	}

	public static String getObjectId(Class classObject) {
		String oid = "111";
		return oid;
	}

	/**
	 * Should only be called data access integration layer.
	 */
	public static Object newBusinessObjectInstance(Class businessObjectClass) {
		try {
			Object o = businessObjectClass.newInstance();
			return o;
		} catch (InstantiationException e) {
			throw new IllegalArgumentException("Invalid class");
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException("Constructor not available");
		}
	}

	/**
	 * @param persistentObjectClass
	 * @return
	 */
	public static AbstractPersistableObject newPersistableObjectInstance(
			Class persistentObjectClass) {
		try {
			AbstractPersistableObject o = (AbstractPersistableObject) persistentObjectClass
					.newInstance();
			return o;
		} catch (InstantiationException e) {
			throw new IllegalArgumentException("Invalid class");
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException("Constructor not available");
		}
	}

}