package com.enviroapps.eapharmics.das.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.enviroapps.eapharmics.common.Utility;

/**
 * @author EnviroApps
 * @version $Revision: 1.1 $
 */
public abstract class AbstractBusinessObject extends AbstractDomainObject {

	private static final long serialVersionUID = 1L;

	protected BusinessObjectState objectState = null;

	protected List addedLinkedObjects = null;

	protected List deletedLinkedObjects = null;

	public AbstractBusinessObject() {
	}

	protected void deleteLinkedObject(Object object) {
		if (object == null) {
			return;
		}
		if (deletedLinkedObjects == null) {
			deletedLinkedObjects = new ArrayList();
		}
		deletedLinkedObjects.add(object);
	}

	public List getDeletedLinkedObjects() {
		if (deletedLinkedObjects == null) {
			return null;
		}
		return unmodifiableList(deletedLinkedObjects);
	}

	public void clearDeletedLinkedObjects() {
		deletedLinkedObjects = null;
	}

	protected void addLinkedObject(Object object) {
		if (object == null) {
			return;
		}
		if (addedLinkedObjects == null) {
			addedLinkedObjects = new ArrayList();
		}
		addedLinkedObjects.add(object);
	}

	public List getAddedLinkedObjects() {
		if (addedLinkedObjects == null) {
			return null;
		}
		return unmodifiableList(addedLinkedObjects);
	}

	public void clearAddedLinkedObjects() {
		addedLinkedObjects = null;
	}

	/**
	 * Returns the objectState.
	 *
	 * @return BusinessObjectState
	 */
	public BusinessObjectState getObjectState() {
		return objectState;
	}

	/**
	 * Sets the objectState.
	 *
	 * @param objectState
	 *            The objectState to set
	 */
	protected void setObjectState(BusinessObjectState objectState) {
		if (this.objectState == null) {
			this.objectState = objectState;
			return;
		}
		switch (this.objectState.ord()) {
		case 0: {
			this.objectState = objectState;
			break;
		}
		case 1: {
			break;
		}
		case 2: {
			if (objectState.equals(BusinessObjectState.DELETED)) {
				this.objectState = objectState;
			}
			break;
		}
		case 3: {
			break;
		}
		}
	}

	public void setObjectStateToCreated() {
		setObjectState(BusinessObjectState.CREATED);
	}

	public void setObjectStateToUnchanged() {
		setObjectState(BusinessObjectState.UNCHANGED);
	}

	public void setObjectStateToDeleted() {
		setObjectState(BusinessObjectState.DELETED);
	}

	public boolean isObjectStateCreated() {
		return BusinessObjectState.CREATED.equals(objectState);
	}

	public boolean isObjectStateDeleted() {
		return BusinessObjectState.DELETED.equals(objectState);
	}

	public Date stripTime(Date date) {
		return Utility.stripTime(date);
	}
}