package com.enviroapps.eapharmics.util;

/**
 * @author EnviroApps
 *
 */
public class ApplicationContext implements java.io.Serializable {

	private String userId;
	private String userSessionId;
	/**
	 * @return
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return
	 */
	public String getUserSessionId() {
		return userSessionId;
	}
	/**
	 * @param userSessionId
	 */
	public void setUserSessionId(String userSessionId) {
		this.userSessionId = userSessionId;
	}

}
