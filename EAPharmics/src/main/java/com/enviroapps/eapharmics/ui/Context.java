package com.enviroapps.eapharmics.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.enviroapps.eapharmics.bom.security.AppUser;
import com.enviroapps.eapharmics.util.ApplicationContext;
/**
 * Provide a container to hold model objects and attributes needed for managing web application.
 * At runtime, there's a context for each session. The context object is created when the user
 * is authenticated
 *
 * For any holder added to this class the following
 * methods should be created:
 * 		resetHolder()
 *
 * and the following methods should be updated:
 * 		reset();
 */
public class Context implements java.io.Serializable {
	private AppUser user;
	// support/common attributes
	private String[] uiContextInfo = new String[6];
	private List errorList = new ArrayList();
	private ApplicationContext applicationContext;

	/**
	 * Constructor
	 */
	public Context() {
		reset();
	}
	public void setUIContextInfo(int index, String propertyName) {
		if (index < uiContextInfo.length)
			uiContextInfo[index] = propertyName;
	}
	public List getUIContextInfo() {
		if(uiContextInfo != null){
			return Arrays.asList(this.uiContextInfo);
		}
		return new ArrayList();
	}
	public void resetUIContextInfo() {
		uiContextInfo[0] = "patientHolder.fullName";
		uiContextInfo[1] = "patientHolder.dateOfBirth";
		uiContextInfo[2] = "patientHolder.gender";
		uiContextInfo[3] = "patientHolder.permanentContactHolder.homePhoneNumber";
		uiContextInfo[4] = "patientHolder.permanentAddressHolder.fullAddress";
		uiContextInfo[5] = null;
	}

	public void clearUIContextInfo() {
		uiContextInfo[0] = null;
		uiContextInfo[1] = null;
		uiContextInfo[2] = null;
		uiContextInfo[3] = null;
		uiContextInfo[4] = null;
		uiContextInfo[5] = null;
	}

	public void reset() {

        resetUIContextInfo();
        resetDur();
        resetErrorList();

	}

	public void resetDur() {
		//durReports = new ArrayList();
//		durFromDate = "";
//		durToDate = "";
	}

	public String toString() {
		StringBuffer msg = new StringBuffer(this.getClass() + "\n");

		return msg.toString();
	}

	/**
	 * Returns the errorList.
	 * @return ArrayList
	 */
	public List getErrorList() {
		return errorList;
	}

	/**
	 * Sets the errorList.
	 * @param errorList The errorList to set
	 */
	public void setErrorList(List errorList) {
		this.errorList = errorList;
	}

	/**
	 * Sets the errorList.
	 * @param errorList The errorList to set
	 */
	public void resetErrorList() {
		if (this.errorList != null)
			this.errorList.clear();
	}

	/**
	 * @return AppUser
	 */
	public AppUser getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 * @param user The user to set
	 */
	public void setUser(AppUser user) {
		this.user = user;
	}
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
}