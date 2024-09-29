package com.enviroapps.eapharmics.bom.refdata;

import java.util.ArrayList;
import java.util.List;

public class UserRole extends AbstractEnumeration {
	public static UserRole CSR = new UserRole("CSR", "CSR");
	public static UserRole SUPERVISOR = new UserRole("SUPERVISOR", "SUPERVISOR");
	public static UserRole CUSTOMER = new UserRole("CUSTOMER", "CUSTOMER");
	public static UserRole PROGRAM_ADMIN = new UserRole("PROGRAM_ADMIN", "PROGRAM_ADMIN");
	public static UserRole SYSTEM_ADMIN = new UserRole( "SYSTEM_ADMIN", "SYSTEM_ADMIN");

	private static List userRoles = new ArrayList();

    static
    {
    	userRoles.add(CSR);
    	userRoles.add(SUPERVISOR);
    	userRoles.add(CUSTOMER);
    	userRoles.add(PROGRAM_ADMIN);
    	userRoles.add(SYSTEM_ADMIN);
    }

	private UserRole(String name, String value) {
		super(name, value);
	}

	/**
	 * @param value
	 * @return
	 */
	public static UserRole getUserTypeForValue(String value) {
		return (UserRole) getEnumerationForValue(value);
	}

	/**
	 * @param value
	 * @return
	 */
	public static UserRole getUserTypeForName(String name) {
		return (UserRole) getEnumerationForName(name);
	}

	public static List getAllUserRoles()
	{
		return userRoles;
	}
}
