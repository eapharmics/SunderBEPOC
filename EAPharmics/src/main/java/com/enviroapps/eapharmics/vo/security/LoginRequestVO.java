/**
 * 
 */
package com.enviroapps.eapharmics.vo.security;

/**
 * 
 */
public class LoginRequestVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String userId = "";
	private String password = "";
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
