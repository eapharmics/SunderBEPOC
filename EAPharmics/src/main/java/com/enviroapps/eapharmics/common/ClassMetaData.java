package com.enviroapps.eapharmics.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import com.enviroapps.eapharmics.das.persistence.AbstractPersistableObject;

/**
 * ClassMetaData class encapsulates and caches meta data about the classes. This
 * class should be use to query any meta data about a class. Convience class for
 * reflection.
 *
 * @author EnviroApps
 * @version $Revison$
 */
public class ClassMetaData {

	private static ClassMetaData instance = new ClassMetaData();

	private Hashtable classes = new Hashtable();

	private Vector invalidClasses = new Vector();

	private Hashtable declaredFields = new Hashtable();

	private Hashtable methods = new Hashtable();

	private Hashtable accessors = new Hashtable();

	private Hashtable mutators = new Hashtable();

	/**
	 * ClassMetaData constructor.
	 */
	private ClassMetaData() {
		super();
	}

	/**
	 * Returns the accessor method for a specified field of the specified class.
	 *
	 * @param
	 *
	 * @return
	 */
	public Method getAccessor(String className, String fieldName) {
		Map accessorMap = null;
		synchronized (accessors) {
			accessorMap = (Map) accessors.get(className);
			if (accessorMap == null) {
				accessorMap = new HashMap();
				accessors.put(className, accessorMap);
			}
		}
		fieldName = fieldName.toUpperCase();
		synchronized (accessorMap) {
			Method methodObject = (Method) accessorMap.get(fieldName);
			if (methodObject != null) {
				return methodObject;
			}
			Method[] methods = getMethods(className);
			String methodName = "get" + fieldName;
			for (int i = 0; i < methods.length; i++) {
				if (methods[i].getName().equalsIgnoreCase(methodName)) {
					methodObject = methods[i];
					accessorMap.put(fieldName, methodObject);
					return methodObject;
				}
			}
		}
		throw new IllegalArgumentException("invalid attribute name "
				+ fieldName);
	}

	/**
	 * Returns the accessor method for a specified field of the specified
	 * object.
	 *
	 * @param
	 *
	 * @return
	 */
	public Method getAccessor(Object o, String attributeName) {
		return getAccessor(o.getClass().getName(), attributeName);
	}

	/**
	 * Returns the class for the specified class name.
	 *
	 * @param
	 *
	 * @return
	 */
	public Class getClass(String className) {
		try {
			synchronized (classes) {
				Class classObject = (Class) classes.get(className);
				if (classObject == null) {
					classObject = Thread.currentThread()
							.getContextClassLoader().loadClass(className);
					if (classObject == null) {
						throw new IllegalArgumentException(
								"Could not load class " + className);
					}
					classes.put(classObject.getName(), classObject);
				}
				return classObject;
			}
		} catch (ClassNotFoundException e) {
			invalidClasses.add(className);
			throw new IllegalArgumentException(e.toString());
		}
	}

	/**
	 * Returns all the declared fields of the specified class.
	 *
	 * @param
	 *
	 * @return
	 */
	public Field[] getDeclaredFields(String className) {
		synchronized (declaredFields) {
			Field[] fieldObjects = (Field[]) declaredFields.get(className);
			if (fieldObjects == null) {
				Class classObject = getClass(className);
				Class currentClassObject = classObject;
				Vector fieldObjectVector = new Vector();
				while (currentClassObject.getName().equals(
						java.lang.Object.class.getName()) == false)

				{
					Field[] currentFieldObjects = currentClassObject
							.getDeclaredFields();
					for (int i = 0; currentFieldObjects != null
							&& i < currentFieldObjects.length; i++) {
						fieldObjectVector.add(currentFieldObjects[i]);
					}
					currentClassObject = currentClassObject.getSuperclass();
				}
				fieldObjects = new Field[fieldObjectVector.size()];
				fieldObjectVector.copyInto(fieldObjects);
				declaredFields.put(classObject.getName(), fieldObjects);
			}
			Field[] copyFieldObjects = new Field[fieldObjects.length];
			System.arraycopy(fieldObjects, 0, copyFieldObjects, 0,
					fieldObjects.length);
			return copyFieldObjects;
		}
	}

	public static ClassMetaData getInstance() {
		return instance;
	}

	/**
	 * Returns all the methods of the specified class.
	 *
	 * @param
	 *
	 * @return
	 */
	public Method[] getMethods(String className) {
		synchronized (methods) {
			Method[] methodObjects = (Method[]) methods.get(className);
			if (methodObjects == null) {
				Class classObject = getClass(className);
				try {
					methodObjects = classObject.getMethods();
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
			methods.put(className, methodObjects);
			return methodObjects;
		}
	}

	/**
	 * Returns the mutator method for a specified field of the specified class.
	 *
	 * @param
	 *
	 * @return
	 */
	public Method getMutator(String className, String fieldName) {
		Map mutatorMap = null;
		synchronized (mutators) {
			mutatorMap = (Map) mutators.get(className);
			if (mutatorMap == null) {
				mutatorMap = new HashMap();
				mutators.put(className, mutatorMap);
			}
		}
		fieldName = fieldName.toUpperCase();
		synchronized (mutatorMap) {
			Method methodObject = (Method) mutatorMap.get(fieldName);
			if (methodObject != null) {
				return methodObject;
			}
			Method[] methods = getMethods(className);
			String methodName = "set" + fieldName;
			for (int i = 0; i < methods.length; i++) {
				if (methods[i].getName().equalsIgnoreCase(methodName)) {
					methodObject = methods[i];
					mutatorMap.put(fieldName, methodObject);
					return methodObject;
				}
			}
			methodName = "add" + fieldName;
			for (int i = 0; i < methods.length; i++) {
				if (methods[i].getName().equalsIgnoreCase(methodName)) {
					return methods[i];
				}
			}
		}
		throw new IllegalArgumentException("No mutator for attribute = < "
				+ fieldName + "> class = <" + className + ">");
	}

	/**
	 * Returns the mutator method for a specified field of the specified class.
	 *
	 * @param
	 *
	 * @return
	 */
	public Method getMutator(Object o, String attributeName) {
		return getMutator(o.getClass().getName(), attributeName);
	}

	/**
	 * Returns true if the class is valid.
	 *
	 * @param
	 *
	 * @return
	 */
	public boolean isClassInvalid(String className) {
		if (invalidClasses.contains(className)) {
			return true;
		}
		return false;
	}

	public static AbstractPersistableObject createCopy(AbstractPersistableObject old) {
		try {
			AbstractPersistableObject o = (AbstractPersistableObject) old.getClass().newInstance();
			Field[] fields = o.getClass().getFields();
			for (int x=0;x<fields.length;x++) {
				Field field = fields[x];
				if (field.getType() == AbstractPersistableObject.class) {
					field.set(o, (Object)createCopy((AbstractPersistableObject)field.get(old)));
				} else if (field.getType() == Set.class) {
					Iterator it = ((Set)field.get(o)).iterator();
					HashSet objs = new HashSet();
					while (it.hasNext()) {
						AbstractPersistableObject co = (AbstractPersistableObject)it.next();
						objs.add(createCopy(co));
					}
					field.set(o, objs);
				}
			}
			o.loadPK(new Object[]{null});
			return o;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		return null;
	}
}