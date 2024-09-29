package com.enviroapps.eapharmics.ui;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.enviroapps.eapharmics.vo.security.UserVO;

public class SessionFilter implements Filter {
	
	private String homePage = "";
	private String errorPage = "";

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		String url = request.getServletPath(); 
		if (url == null || url.length() == 0) {
			response.sendRedirect(homePage);
			return;
		}
		
    	if (request.getSession().getAttribute(Constants.USER_ATTRIBUTE) == null || 
    			!(request.getSession().getAttribute(Constants.USER_ATTRIBUTE) instanceof UserVO)) {
    		int serverPort = request.getServerPort();
    		String serverUrl = request.getServerName();

    		if (!(serverPort == 0 || serverPort == 80)) {
    			serverUrl = serverUrl + ":" + serverPort;
    		}
    		String reDirectUrl = "http://" + serverUrl + "/eapharmics/" + homePage;
    		response.sendRedirect(reDirectUrl);
    		return;
    	}
		chain.doFilter(request, res);
	}

	public void init(FilterConfig config) throws ServletException {

		// Get init parameter
		homePage = config.getInitParameter("home-page");
		errorPage = config.getInitParameter("error-page");

		// Print the init parameter
		System.out.println("Home Page: " + homePage);
		System.out.println("Error Page: " + errorPage);
	}

	public void destroy() {
		// add code to release any resource
	}
}
