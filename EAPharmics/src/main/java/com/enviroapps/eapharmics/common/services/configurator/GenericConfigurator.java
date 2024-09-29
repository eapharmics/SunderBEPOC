package com.enviroapps.eapharmics.common.services.configurator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Vector;

/**
 * GenericConfigurator class implements configurator interface to load and
 * retrieve application configuration parameters.
 *
 * @author EnviroApps
 * @version $Revision: 1.1 $
 */
public final class GenericConfigurator implements Configurator {

	/**
	 * This is the name of the JVM system property that contains the path to the
	 * configuration directory.
	 */
	private static final String CONFIG_DIRECTORY_PATH_PROPERTY_NAME = "application.configuration.location";

	/**
	 * This is appended to the value in application.configuration.location to
	 * create the entire path to the configuration directory. Do not set if the
	 * path in CONFIG_DIRECTORY_PATH_PROPERTY_NAME is sufficient.
	 */
	private static final String CONFIG_DIRECTORY_APPENDER_NAME = "application.configuration.location.name";

	/**
	 * The configuration cache.
	 */
	private Properties configParams = null;

	/**
	 * Constructor. Instantiate, but do not load configuration.
	 */
	public GenericConfigurator() {
		System.out.println("GenericConfigurator loaded : "
				+ getClass().getClassLoader());
	}

	/**
	 * Get boolean config parameter.
	 *
	 * @param name
	 *            of the parameter
	 *
	 * @return boolean value of the parameter
	 */
	public boolean getBoolean(String name) {
		if (configParams == null) {
			throw new IllegalStateException("Configurator not initialized");
		}
		String value = configParams.getProperty(name);
		return ((value != null) && value.toLowerCase().startsWith("true"));
	}

	/**
	 * Get config parameter.
	 *
	 * @param name
	 *            of the parameter
	 *
	 * @return value of the parameter
	 */
	public String getProperty(String name) {
		if (configParams == null) {
			throw new IllegalStateException("Configurator not initialized");
		}
		return configParams.getProperty(name);
	}

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
	public String getProperty(String name, String defaultValue) {
		if (configParams == null) {
			throw new IllegalStateException("Configurator not initialized");
		}
		return configParams.getProperty(name, defaultValue);
	}

	/**
	 * Return the configuration directory path as used by the service.
	 *
	 * @return the value of the property named by the
	 *         CONFIG_DIRECTORY_PATH_PROPERTY_NAME field
	 */
	public String getConfigDirPath() {
		return configParams.getProperty(CONFIG_DIRECTORY_PATH_PROPERTY_NAME);
	}

	/**
	 * Return the absolute file path from a property name, construct an absolute
	 * file path if the value of the property is not absolute. The method uses
	 * the property name to aquire a property value that is either an absolute
	 * of partial file path. File paths that are not absolute are made so by
	 * prepending the value of the property named in the field
	 * CONFIG_DIRECTORY_PATH_PROPERTY_NAME.
	 *
	 * @param name
	 *            the name of a property that has a file path for a value
	 * @return the absolute path of the given file name
	 * @throws IllegalArgumentException
	 *             if the value of the property named in
	 *             CONFIG_DIRECTORY_PATH_PROPERTY_NAME is not absolute
	 */
	public String getAbsoluteFilePath(String name) {
		String filepath = configParams.getProperty(name);
		File file = new File(filepath);
		if (file.isAbsolute()) {
			System.out.println("File path is absolute : " + filepath + " "
					+ file.getAbsolutePath());
			return filepath;
		} else {
			String configDirPath = getConfigDirPath();
			File configDir = new File(configDirPath);
			if (configDir.isAbsolute() == false) {
				throw new IllegalArgumentException(
						"Config dir path is not absolute; so can not return absolute path");
			}

			filepath = configDirPath + "/" + filepath;
			filepath = filepath.replace('\\', '/');
			return filepath;
		}
	}

	/**
	 * Return a property value parsed into a String array of file paths, given a
	 * property name. The method uses the property name to aquire a property
	 * value that is a comma delimited set of file paths. This set is tokenized
	 * into a String array. During tokenization, file paths that are not
	 * absolute are made so by prepending the value of the property named in the
	 * field CONFIG_DIRECTORY_PATH_PROPERTY_NAME.
	 *
	 * @param name
	 *            the property to assemble the file paths from
	 * @return a list of file paths from the property in name
	 * @throws IllegalArgumentException
	 *             if the value of the property named in
	 *             CONFIG_DIRECTORY_PATH_PROPERTY_NAME is not absolute
	 */
	public String[] getAbsoluteFilePaths(String name) {
		String fileNames = configParams.getProperty(name);
		if (fileNames == null) {
			return null;
		}
		List fileList = new ArrayList();
		StringTokenizer tokenizer = new StringTokenizer(fileNames, ",");
		while (tokenizer.hasMoreTokens()) {
			String token = (String) tokenizer.nextToken();
			File file = new File(token);
			if (file.isAbsolute()) {
				System.out.println("File path is absolute : " + token + " "
						+ file.getAbsolutePath());
			} else {
				String configDirPath = getConfigDirPath();
				File configDir = new File(configDirPath);
				if (configDir.isAbsolute() == false) {
					throw new IllegalArgumentException(
							"Config dir path is not absolute; so can not return absolute path");
				}

				token = configDirPath + "/" + token;
				token = token.replace('\\', '/');
			}
			fileList.add((String) token);
		}
		String[] filePaths = new String[fileList.size()];
		return (String[]) fileList.toArray(filePaths);
	}

	/**
	 * TODO Make generic default configuration paths Loads all property resource
	 * files from a default directory or current directory. This method will
	 * first look for a directory by appending the value of the JVM system
	 * property named in the field CONFIG_DIRECTORY_APPENDER_NAME to the value
	 * of the JVM system property named in the field
	 * CONFIG_DIRECTORY_PATH_PROPERTY_NAME. If PATH_PROPERTY_NAME is null, it
	 * will look for the JVM system property named CONFIG_DIRECTORY and use it's
	 * value verbatim. If the CONFIG_DIRECTORY property is not set, a default
	 * value of "config" is used.
	 */
	public synchronized void loadConfigParameters() {
		// Get env base config dir.
		String configDirPath = System
				.getProperty(CONFIG_DIRECTORY_PATH_PROPERTY_NAME);
		if (configDirPath == null)
			System.out.println("ENV variable "
					+ CONFIG_DIRECTORY_PATH_PROPERTY_NAME + " not found.");

		// If doesn't exist, assume use fallback env.
		if (configDirPath == null) {
			configDirPath = System.getProperty("CONFIG_DIRECTORY");
			if (configDirPath == null)
				System.out.println("ENV variable CONFIG_DIRECTORY not found.");
		} else {
			// append location name if it does exist.
			String configExt = System
					.getProperty(CONFIG_DIRECTORY_APPENDER_NAME);
			if (configExt == null)
				System.out.println("ENV variable "
						+ CONFIG_DIRECTORY_APPENDER_NAME + " not found.");
			else
				configDirPath = configDirPath.concat("/"
						+ System.getProperty(CONFIG_DIRECTORY_APPENDER_NAME));
		}
		// If neither exists, default to hardcoded value.
		if (configDirPath == null)
			configDirPath = "c:/dev/EAPharmics/config";
		System.out.println("configDirPath: " + configDirPath);
		if (configDirPath == null || configDirPath.length() <= 0) {
			return;
		}
		loadConfigParameters(configDirPath);
	}

	/**
	 * Loads all properies from files ending with .properties from a given
	 * directory into the configuration cache.
	 *
	 * @param directoryPath
	 *            configuration directory path containing all the properties
	 *            files
	 */
	public synchronized void loadConfigParameters(String directoryPath) {
		try {
			System.out.println("Config dir path: " + directoryPath);
			File directory = new File(directoryPath);
			String[] fileNamesArray = directory.list(new FilenameFilter() {
				public boolean accept(File directory, String fileName) {
					return fileName.endsWith(".properties");
				}
			});
			if (fileNamesArray == null) {
				System.out.println("No resource file in config directory : "
						+ directoryPath);
				return;
			}
			Vector propertiesVector = new Vector();
			for (int i = 0; i < fileNamesArray.length; i++) {
				String fileName = fileNamesArray[i];
				String filePath = directoryPath + File.separator + fileName;
				System.out.println("Loading " + filePath + " ...");
				Properties properties = loadResourceFile(filePath);
				propertiesVector.add(properties);
			}
			Properties[] propertiesArray = new Properties[propertiesVector
					.size()];
			propertiesVector.copyInto(propertiesArray);
			Properties newConfigParams = mergeProperties(propertiesArray);
			// this is done to make this class thread safe when call from admin
			// service
			configParams = newConfigParams;
			// add the config dir path as a config parameter
			configParams
					.put(CONFIG_DIRECTORY_PATH_PROPERTY_NAME, directoryPath);
			printConfigParams();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sets configuration parameters to be called from a admin utility to reset
	 * the config parameters.
	 *
	 * @param newConfigParams
	 *            new config parameters
	 */
	public synchronized void setConfigParameters(Properties newConfigParams) {
		configParams = newConfigParams;
	}

	/**
	 * Returns all properties loaded into the configuration cache.
	 *
	 * @return a cloned version of the configuration cache
	 */
	public synchronized Properties getConfigParameters() {
		return configParams != null ? (Properties) configParams.clone() : null;
	}

	/**
	 * Loads properties from a specific filename in the classpath.
	 *
	 * @param resourceFile
	 *            the filename, including path to the file to be loaded
	 * @return the properties successfully loaded from the file
	 * @throws IOException
	 *             if a FileInputStream cannot be constructed from the value
	 *             given in resourceFile
	 */
	private Properties loadResourceFile(String resourceFile) throws IOException {
		Properties properties = new Properties();
		InputStream inStrm = new FileInputStream(resourceFile);
		if (inStrm == null) {
			throw new IOException(
					"Check to see that config directory is in CLASSPATH");
		}
		properties.load(inStrm);
		return properties;
	}

	/**
	 * Helper method to merge multiple properties. Converts an array of
	 * Properties objects into a single Properties object containing all the
	 * array member's properties.
	 *
	 * @param propertiesArray
	 *            the objects to be merged
	 * @return the result of the merge
	 */
	private Properties mergeProperties(Properties[] propertiesArray)
			throws IOException {
		ByteArrayOutputStream outStrm = new ByteArrayOutputStream();
		// loop through array, store each Properties object in the
		// ByteArrayOutputStream
		for (int i = 0; i < propertiesArray.length; i++) {
			propertiesArray[i].store(outStrm, null);
		}
		outStrm.close();
		// Convert output into input stream
		ByteArrayInputStream inStrm = new ByteArrayInputStream(outStrm
				.toByteArray());
		Properties mergedProperties = new Properties();
		// populate return value
		mergedProperties.load(inStrm);
		return mergedProperties;
	}

	/**
	 * Dump all properties in the configuration cache to System.out
	 */
	private void printConfigParams() {
		SortedMap sortedConfigParams = new TreeMap(configParams);
		Iterator iter = sortedConfigParams.entrySet().iterator();
		System.out.println("Config parameters are:");
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}
	}
}
