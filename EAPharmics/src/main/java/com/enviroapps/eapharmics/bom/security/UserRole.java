package com.enviroapps.eapharmics.bom.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserRole implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String userId;
	private List roles = new ArrayList();

	/* (non-Javadoc)
	 * @see com.enviroapps.das.persistence.AbstractPersistableObject#getPK()
	 */
	public Serializable getPK() {
		return userId;
	}

	/* (non-Javadoc)
	 * @see com.enviroapps.das.persistence.AbstractPersistableObject#loadPK(java.lang.Object[])
	 */
	public void loadPK(Object[] pks) {
		this.userId = (String)pks[0];

	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the roles
	 */
	public List getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List roles) {
		this.roles = roles;
	}

}
