package com.enviroapps.eapharmics.ui;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.enviroapps.eapharmics.common.services.UtilityServiceFactory;
import com.enviroapps.eapharmics.common.services.logging.ILogger;
import com.enviroapps.eapharmics.vo.security.UserVO;

public class EAPharmicsServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The debugging detail level for this servlet.
	 */
	private int debug = 0;

	/**
	 * 10 that were allocated at initialization.
	 */
	public void destroy() {
	}

	/**
	 * @param req
	 * @return
	 */
	public String getServerPath(HttpServletRequest req) {
		int serverPort = req.getServerPort();
		String serverUrl = req.getServerName();

		if (!(serverPort == 0 || serverPort == 80)) {
			serverUrl = serverUrl + ":" + serverPort;
		}
		String url = "http://" + serverUrl + "/eapharmics";
		return url;
	}
	
	/**
	 * @param req
	 * @return
	 */
	public String getServerHomePage(HttpServletRequest req) {
		int serverPort = req.getServerPort();
		String serverUrl = req.getServerName();

		if (!(serverPort == 0 || serverPort == 80)) {
			serverUrl = serverUrl + ":" + serverPort;
		}
		String url = getServerPath(req) + "/index.html";
		return url;
	}
	
	/**
	 * @param req
	 * @return
	 */
	public String getServerErrorPage(HttpServletRequest req) {
		int serverPort = req.getServerPort();
		String serverUrl = req.getServerName();

		if (!(serverPort == 0 || serverPort == 80)) {
			serverUrl = serverUrl + ":" + serverPort;
		}
		String url = getServerPath(req) + "/error.html";
		return url;
	}
	
    public synchronized boolean isValidSession(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {
    	if (req.getSession().getAttribute(Constants.USER_ATTRIBUTE) == null || 
    			!(req.getSession().getAttribute(Constants.USER_ATTRIBUTE) instanceof UserVO)) {
    		return false;
    	}
    	return true;
    }

	/**
	 * Return the debugging detail level for this servlet.
	 */
	public int getDebug() {
		return debug;
	}
	
	public ILogger getLogger() {
		return UtilityServiceFactory.getLogger();
	}
}
