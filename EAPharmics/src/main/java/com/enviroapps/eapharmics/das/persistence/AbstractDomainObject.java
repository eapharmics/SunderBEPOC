package com.enviroapps.eapharmics.das.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.enviroapps.eapharmics.common.Utility;

/**
 * @author EnviroApps
 * @version $Revision: 1.1 $
 */
public abstract class AbstractDomainObject implements Serializable {
	public AbstractDomainObject() {
	}

	public String toString() {
		return ""; // super.toString() + ":" + Utility.toString(this);
	}

	protected static String booleanWrapperToString(Boolean b) {
		return Utility.booleanWrapperToString(b);
	}

	protected static Boolean stringToBooleanWrapper(String s) {
		return Utility.stringToBooleanWrapper(s);
	}

	protected static void checkNullString(String stringName, String s) {
		if (s == null) {
			throw new IllegalArgumentException(stringName + " is null");
		}
		if (s.trim().length() <= 0) {
			throw new IllegalArgumentException(stringName + " is zero length");
		}
	}

	protected static void checkNullArgument(Object arg) {
		if (arg == null) {
			throw new IllegalArgumentException("Argument is null");
		}
	}

	protected static void checkNullStringArgument(String arg) {
		if (arg == null) {
			throw new IllegalArgumentException("String argument is null");
		}
		if (arg.trim().length() <= 0) {
			throw new IllegalArgumentException("String argument is zero length");
		}
	}

	protected static void checkNullObjectId(Object o, String id) {
		if (id == null) {
			throw new IllegalArgumentException("Object id of object type <"
					+ o.getClass().getName() + "> is null");
		}
		if (id.trim().length() <= 0) {
			throw new IllegalArgumentException("Object id of object type <"
					+ o.getClass().getName() + "> is empty");
		}
	}

	protected static void checkObjectType(Object o, Class objectType) {
		if (o == null) {
			return;
		}
		if (objectType.isInstance(o) == false) {
			throw new IllegalArgumentException("Invalid object type : "
					+ o.getClass() + "needs to be of type " + objectType);
		}
	}

	protected static String trimStringArgument(String s) {
		if (s == null) {
			return null;
		}
		return s.trim();
	}

	protected static List unmodifiableList(List list) {
		return Collections.unmodifiableList(list);
	}

	protected static Map unmodifiableMap(Map map) {
		return Collections.unmodifiableMap(map);
	}

	protected static List asList(Collection collection) {
		return Arrays.asList(collection.toArray());
	}

	protected static List cloneList(List list) {
		List cloneList = new ArrayList();
		cloneList.addAll(list);
		return cloneList;
	}

	protected boolean isNullOrEmpty(String s) {
		return Utility.isNullOrEmpty(s);
	}
}