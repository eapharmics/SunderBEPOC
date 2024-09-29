package com.enviroapps.eapharmics.das.persistence;

import java.io.Serializable;
import java.util.Enumeration;

/**
 * A Java enumerated object representing various trace levels.
 *
 * @author EnviroApps
 * @version $Revision: 1.1 $
 */
public class BusinessObjectState implements Serializable {

	/**
	 * The string id for this enum instance.
	 */
	private String id;

	/**
	 * The integer ordinate unique for each new instance. This will allow the
	 * enum to be used as an Array index. e.g. MyArray[RoleType.ASCENDING.ord()] =
	 * 10;
	 */
	private int ord;

	/**
	 * Links to the previous (ord--) enum.
	 */
	private BusinessObjectState prev;

	/**
	 * Links to the next (ord++) enum.
	 */
	private BusinessObjectState next;

	/**
	 * The number of enumerated items defined by this class.
	 */
	private static int upperBound = 0;

	/**
	 * Links to the first enum.
	 */
	private static BusinessObjectState first = null;

	/**
	 * Links to the last enum.
	 */
	private static BusinessObjectState last = null;

	/**
	 * Private constructor (can only be used by this class).
	 */
	private BusinessObjectState(String id) {
		this.id = id;
		this.ord = upperBound++;
		if (first == null) {
			first = this;
		}
		if (last != null) {
			this.prev = last;
			last.next = this;
		}
		last = this;
	}

	/**
	 * Returns an enumeration that can be used to step through the enum list.
	 */
	public static Enumeration elements() {
		return new Enumeration() {
			private BusinessObjectState curr = first;

			public boolean hasMoreElements() {
				return (curr != null);
			}

			public Object nextElement() {
				BusinessObjectState e = curr;
				curr = curr.next();
				return e;
			}
		}; // End of anonymous class
	} // End of method

	public boolean equals(Object other) {
		if (!(other instanceof BusinessObjectState)) {
			return false;
		}
		BusinessObjectState businessObjectState = (BusinessObjectState) other;
		if (id.equals(businessObjectState.id)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns the first defined enum.
	 */
	public static BusinessObjectState first() {
		return first;
	}

	/**
	 * Convert businessObjectState string to RoleType.
	 */
	public static BusinessObjectState fromString(String businessObjectState) {
		if (businessObjectState == null) {
			return null;
		}
		businessObjectState = businessObjectState.trim();
		if (businessObjectState.equalsIgnoreCase(BusinessObjectState.CREATED
				.toString().trim())) {
			return BusinessObjectState.CREATED;
		} else if (businessObjectState
				.equalsIgnoreCase(BusinessObjectState.MODIFIED.toString()
						.trim())) {
			return BusinessObjectState.MODIFIED;
		} else if (businessObjectState
				.equalsIgnoreCase(BusinessObjectState.DELETED.toString().trim())) {
			return BusinessObjectState.DELETED;
		} else if (businessObjectState
				.equalsIgnoreCase(BusinessObjectState.UNCHANGED.toString()
						.trim())) {
			return BusinessObjectState.UNCHANGED;
		} else {
			return null;
		}
	}

	public int hashCode() {
		return id.hashCode();
	}

	/**
	 * Returns the last defined enum.
	 */
	public static BusinessObjectState last() {
		return last;
	}

	/**
	 * Returns the enum defined next to this in the enum order.
	 */
	public BusinessObjectState next() {
		return this.next;
	}

	/**
	 * Returns the unique ordinate of this enum.
	 */
	public int ord() {
		return ord;
	}

	/**
	 * Returns the enum defined previous to this in the enum order.
	 */
	public BusinessObjectState prev() {
		return this.prev;
	}

	/**
	 * Returns the size (number of defined) enums.
	 */
	public static int size() {
		return upperBound;
	}

	/**
	 * Returns the String representation for this enum.
	 */
	public String toString() {
		return this.id;
	}

	public static final BusinessObjectState UNCHANGED = new BusinessObjectState(
			"UNCHANGED");

	public static final BusinessObjectState CREATED = new BusinessObjectState(
			"NEW");

	public static final BusinessObjectState MODIFIED = new BusinessObjectState(
			"MODIFIED");

	public static final BusinessObjectState DELETED = new BusinessObjectState(
			"DELETED");
}