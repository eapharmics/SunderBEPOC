package com.enviroapps.eapharmics.ui;

/**
 * @author EAPharmics
 *
 * Possible error types. It will be used to format
 * the error message correctly on the screen.
 *
 * Entries in this class is tied to struts ApplicationResources.properties file.
 *
 */
public class ErrorType
{
	public final static String		ERR_VALIDATION 		= "validation"; // user input data validation.
	public final static String		ERR_AUTHORIZATION	= "security"; // Security authorization.
	public final static String		ERR_GENERAL			= "general"; // e.g session expiration etc.
}
