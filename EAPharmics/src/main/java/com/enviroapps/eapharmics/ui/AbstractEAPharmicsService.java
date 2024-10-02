/**
 * 
 */
package com.enviroapps.eapharmics.ui;

import com.enviroapps.eapharmics.common.services.UtilityServiceFactory;
import com.enviroapps.eapharmics.exception.EAPharmicsException;
import com.enviroapps.eapharmics.vo.security.UserVO;

import jakarta.servlet.http.HttpServletRequest;

/**
 * @author EAPharmics
 *
 */
public abstract class AbstractEAPharmicsService {
	
	/**
	 * 
	 */
	public UserVO getUser() {
		return UtilityServiceFactory.getUserFromFlexSession();
	}
	
	public UserVO getUser(HttpServletRequest req) {
		if (req.getSession() != null && req.getSession().getAttribute(Constants.USER_ATTRIBUTE) != null)
			return (UserVO) req.getSession().getAttribute(Constants.USER_ATTRIBUTE);
		return null;
	}
	
	/**
	 * Please make sure that the user is still logged in and has a valid session
	 * @throws EAPharmicsException
	 */
	public void checkSession() throws EAPharmicsException {
		if (getUser() == null) {
			//@TODO Sundar
			//throw new EAPharmicsException("Please login to continue.");
		}
	}

	/**
	 * Please make sure that the user is still logged in and has a valid session
	 * @throws EAPharmicsException
	 */
	public void checkSession(HttpServletRequest req) throws EAPharmicsException {
		if (req.getSession() == null || req.getSession().getAttribute(Constants.USER_ATTRIBUTE) == null) {
			//@TODO Sundar
			//throw new EAPharmicsException("Please login to continue.");
			System.out.println("user session not found!");
		}
	}

}
