package com.enviroapps.eapharmics.common.services.configurator;

import java.util.Properties;

/**
 * This provides a generic service interface for the configuration service. All
 * configuration implementations must implement this interface in order to be
 * used as a configuration service.
 *
 * @author EnviroApps
 * @version $Revision: 1.1 $
 */
public interface Configurator {
	/**
	 * Get boolean config parameter.
	 *
	 * @param name
	 *            of the parameter
	 *
	 * @return boolean value of the parameter
	 */
	public boolean getBoolean(String name);

	/**
	 * Get config parameter.
	 *
	 * @param name
	 *            of the parameter
	 *
	 * @return value of the parameter
	 */
	public String getProperty(String name);

	/**
	 * Get config directory path.
	 *
	 * @return value of config directory path
	 */
	public String getConfigDirPath();

	/**
	 * Get absolute file path. If the configured file path is absolute it
	 * returns the absolute path but it is configure as relative path, it
	 * returns absolute file path by concatenating config directory path.
	 *
	 * @param name
	 *            of the parameter
	 *
	 * @return value of the parameter
	 */
	public String getAbsoluteFilePath(String name);

	/**
	 * Get absolute file paths (multiple). If the configured file path is
	 * absolute it returns the absolute path but it is configure as relative
	 * path, it returns absolute file path by concatenating config directory
	 * path.
	 *
	 * @param name
	 *            of the parameter
	 *
	 * @return values of the parameter
	 */
	public String[] getAbsoluteFilePaths(String name);

	/**
	 * Get config parameter. If the config parameter is not set then the default
	 * value is returned back.
	 *
	 * @param name
	 *            of the parameter
	 * @param default
	 *            value of the parameter
	 *
	 * @return value of the parameter
	 */
	public String getProperty(String name, String defaultValue);

	/**
	 * Loads all property resource files from a default directory or current
	 * directory.
	 */
	public void loadConfigParameters();

	/**
	 * Loads all property resource files from a given directory.
	 *
	 * @param directoryPath
	 *            configuration directory path containing all the config files
	 */
	public void loadConfigParameters(String directoryPath);

	/**
	 * Sets configuration parameters to be called from a admin utility to reset
	 * the config parameters.
	 *
	 * @param newConfigParams
	 *            new config parameters
	 */
	public void setConfigParameters(Properties newConfigParams);

	/**
	 * Returns configuration parameters.
	 */
	public Properties getConfigParameters();
}
