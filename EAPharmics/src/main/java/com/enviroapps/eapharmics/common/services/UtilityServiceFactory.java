package com.enviroapps.eapharmics.common.services;

import java.util.Hashtable;

import com.enviroapps.eapharmics.common.services.configurator.Configurator;
import com.enviroapps.eapharmics.ui.Constants;
import com.enviroapps.eapharmics.vo.security.UserVO;

import jakarta.servlet.http.HttpSession;

/**
 * UtilityServiceFactory class is the factory for all utility services like
 * Configurator, Logger, GUIDGenerator etc. This is a singleton, which contains
 * other singleton versions of the utility services.
 *
 * @author EnviroApps
 * @version $Revision: 1.1 $
 */
public class UtilityServiceFactory {

	// The utility service factory singlton
	private static UtilityServiceFactory instance = new UtilityServiceFactory();

	private String logServiceName = "Logger";

	private String configServiceName = "Configurator";

	private String guidServiceName = "GUIDGenerator";

	// distribute locks for services
	private Hashtable serviceObjectLocks = null;

	// configuration singleton
	private Configurator configurator = null;

	/**
	 * Private constructor. Populates the lock mechanism.
	 */
	private UtilityServiceFactory() {
		serviceObjectLocks = new Hashtable();
		serviceObjectLocks.put(logServiceName, new Object());
		serviceObjectLocks.put(configServiceName, new Object());
		serviceObjectLocks.put(guidServiceName, new Object());
	}

	/**
	 * Returns the singleton instance of the class.
	 *
	 * @return
	 */
	static UtilityServiceFactory getInstance() {
		return instance;
	}

	/**
	 * Returns the configurator service object. If the object is not yet created, it
	 * creates it.
	 *
	 * @return configurator
	 */
	public static Configurator getConfigurator() {
		return getInstance().getConfiguratorInternal();
	}

	/**
	 * Returns the configurator service object. If the object is not yet created, it
	 * creates it.
	 *
	 * @return configurator
	 */
	private Configurator getConfiguratorInternal() {
		if (configurator != null) {
			return configurator;
		} else {
			Object lockObject = serviceObjectLocks.get(configServiceName);
			// use the service lock to ensure thread safety during configuration
			// initialization
			synchronized (lockObject) {
				if (configurator != null) {
					return configurator;
				} else {
					// Instantiate configuration service and load the parameters
					String configuratorClassName = "com.enviroapps.eapharmics.common.services.configurator.GenericConfigurator";
					Configurator newConfigurator = (Configurator) createServiceObject(configuratorClassName);
					/* newConfigurator.loadConfigParameters(); */
					configurator = newConfigurator;
					return configurator;
				}
			}
		}
	}

	/**
	 * Helper method to create service object. Create a service based on the class
	 * name provided.
	 *
	 * @param serviceClassName a valid class name in the class path to create a new
	 *                         service object from
	 * @return the service object created
	 * @throws IllegalArgumentException if the class name given is not valid
	 */
	private Object createServiceObject(String serviceClassName) {
		try {
			Class serviceClass = Thread.currentThread().getContextClassLoader().loadClass(serviceClassName);
			Object serviceObject = serviceClass.newInstance();
			return serviceObject;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		throw new IllegalArgumentException("Invalid service class name: " + serviceClassName);
	}

	/**
	 * @return
	 */
	public static UserVO getUserFromFlexSession() {
		// @TODO Sundar
		// FlexSession session = FlexContext.getFlexSession();
		HttpSession session = null;
		if (session != null) {
			UserVO userVO = (UserVO) session.getAttribute(Constants.USER_ATTRIBUTE);
			return userVO;
		} else {
			System.err.println("No flex session found");
			return null;
		}
	}

	/**
	 * @return
	 */
	public static long getSelectedLocationTimezoneOffset() {
		// @TODO Sundar
		// FlexSession session = FlexContext.getFlexSession();
		HttpSession session = null;
		if (session != null) {
			Long val = (Long) session.getAttribute(Constants.SELECTED_LOCATION_UTC_OFFSET);
			if (val == null) {
				System.err.println(Constants.SELECTED_LOCATION_UTC_OFFSET + " session variable not found");
				return 0l;
			}
			return val.longValue();
		} else {
			System.err.println("No flex session found");
			return 0l;
		}
	}
}