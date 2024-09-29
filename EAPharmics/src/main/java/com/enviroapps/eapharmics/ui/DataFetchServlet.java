package com.enviroapps.eapharmics.ui;

import jakarta.servlet.ServletException;
import jakarta.servlet.UnavailableException;
import jakarta.servlet.http.HttpServlet;

import com.enviroapps.eapharmics.persistence.DictionaryFactory;

public final class DataFetchServlet extends HttpServlet {
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
	 * Initialize this servlet, including loading our initial database from
	 * persistent storage. The following servlet initialization parameters are
	 * processed, with default values in square brackets:
	 * <ul>
	 * <li><strong>debug</strong> - The debugging detail level for this
	 * servlet, which controls how much information is logged. [0]
	 * <li><strong>pathname</strong> - Resource pathname to our persistent
	 * storage ["/WEB-INF/database.xml"]
	 * </ul>
	 *
	 * @exception ServletException
	 *                if we cannot configure ourselves correctly
	 */
	public void init() throws ServletException {
		// Process our servlet initialization parameters
		String value = getServletConfig().getInitParameter("debug");
		try {
			debug = Integer.parseInt(value);
		} catch (Throwable t) {
			debug = 0;
		}

		// Initialize the db connections in hibernate 
		try {
			//This is a dummy invocation and don't care about the results
			DictionaryFactory.getInstance().getMaxDetailDisplayOrder("ABBREVIATION");
		} catch (Exception e) {
			log("Connection Initialization Error", e);
			e.printStackTrace();
			throw new UnavailableException(
					"Connection Initialization Error: Cannot initialize connection.");
		}
	}

	/**
	 * Return the debugging detail level for this servlet.
	 */
	public int getDebug() {
		return debug;
	}
}
