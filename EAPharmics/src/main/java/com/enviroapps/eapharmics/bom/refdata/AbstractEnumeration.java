package com.enviroapps.eapharmics.bom.refdata;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.enviroapps.eapharmics.das.persistence.AbstractDomainObject;

/**
 * @author EnviroApps
 *
 */
public class AbstractEnumeration extends AbstractDomainObject {

	private String name;
	private String value;
	private String code;	//optional may not be there all the time

	private static List enumerations = new ArrayList();

	public AbstractEnumeration() {

	}

	public AbstractEnumeration(String name, String value) {
		this(name, value, null);
	}

	public AbstractEnumeration(String name, String value, String code) {
		this.name = name;
		this.value = value;
		this.code = code;
		enumerations.add(this);
	}

	/**
	 * @param value
	 * @return
	 */
	protected static AbstractEnumeration getEnumerationForValue(String value) {
		for (Iterator iter = enumerations.iterator(); iter.hasNext();) {
			AbstractEnumeration element = (AbstractEnumeration) iter.next();
			if (element.getValue().equals(value)) {
				return element;
			}
		}
		return null;
	}

	/**
	 * @param c
	 * @return
	 */
	protected static AbstractEnumeration getEnumerationForName(String name) {
		for (Iterator iter = enumerations.iterator(); iter.hasNext();) {
			AbstractEnumeration element = (AbstractEnumeration) iter.next();
			if (element.getName().equals(name)) {
				return element;
			}
		}
		return null;
	}

	/**
	 * @param code
	 * @return
	 */
	protected static AbstractEnumeration getEnumerationForCode(String code) {
		if (code == null) {
			return null;
		}
		for (Iterator iter = enumerations.iterator(); iter.hasNext();) {
			AbstractEnumeration element = (AbstractEnumeration) iter.next();
			if (element.getCode() == null) {
				if (code == null) {
					return element;
				}
				continue;
			}
			if (element.getCode().equals(code)) {
				return element;
			}
		}
		return null;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Returns the value.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            The value to set.
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return Returns the code.
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code The code to set.
	 */
	public void setCode(String code) {
		this.code = code;
	}

}
