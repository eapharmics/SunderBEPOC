package com.enviroapps.eapharmics.common.services.logging;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.enviroapps.eapharmics.common.services.UtilityServiceFactory;

/**
 * Log4JLogger class implements logger interface for application logging.
 *
 * @author EnviroApps
 * @version $Revision: 1.1 $
 */
public final class Log4JLogger implements ILogger {
	private boolean debugLogEnabled = false;

	private boolean infoLogEnabled = false;

	private boolean warnLogEnabled = false;

	private boolean errorLogEnabled = false;

	private boolean fatalLogEnabled = false;

	private boolean timingLogEnabled = false;

	private boolean stackTraceLogEnabled = false;

	private boolean exceptionStackTraceLogEnabled = false;

	public Log4JLogger() {
		debugLogEnabled = UtilityServiceFactory.getConfigurator().getBoolean(
				"log.debug.enabled");
		infoLogEnabled = UtilityServiceFactory.getConfigurator().getBoolean(
				"log.info.enabled");
		warnLogEnabled = UtilityServiceFactory.getConfigurator().getBoolean(
				"log.warn.enabled");
		errorLogEnabled = UtilityServiceFactory.getConfigurator().getBoolean(
				"log.error.enabled");
		fatalLogEnabled = UtilityServiceFactory.getConfigurator().getBoolean(
				"log.fatal.enabled");
		timingLogEnabled = UtilityServiceFactory.getConfigurator().getBoolean(
				"log.timing.enabled");
		stackTraceLogEnabled = UtilityServiceFactory.getConfigurator()
				.getBoolean("log.stacktrace.enabled");
		exceptionStackTraceLogEnabled = UtilityServiceFactory.getConfigurator()
				.getBoolean("log.exception.stacktrace.enabled");
		System.out
				.println("Log4JLogger loaded: " + getClass().getClassLoader());
	}

	/**
	 * Returns true if debug log is enabled.
	 *
	 * @return true if debug log is enabled
	 */
	public boolean isDebugEnabled() {
		return debugLogEnabled;
	}

	/**
	 * Logs a debug message.
	 *
	 * @param source
	 *            source object logging the debug message
	 * @param methodName
	 *            name of the method logging the debug message
	 * @param message
	 *            debug message
	 */
	public void debug(Object source, String methodName, Object message) {
		if (!debugLogEnabled) {
			return;
		}
		debug(source.getClass().getName(), methodName, message);
	}

	/**
	 * Logs a debug message.
	 *
	 * @param className
	 *            name of the class logging the debug message
	 * @param methodName
	 *            name of the method logging the debug message
	 * @param message
	 *            debug message
	 */
	public void debug(String className, String methodName, Object message) {
		if (!debugLogEnabled) {
			return;
		}
		Logger logger = getLogger(className);
		if (!stackTraceLogEnabled) {
			logger.debug(getMessage(methodName, message));
		} else {
			logger.debug(getMessage(methodName, message), new Throwable());
		}
	}

	/**
	 * Logs an exception for debugging. This method should be used to log an
	 * exception if the exception does not affectthe current execution sequence.
	 *
	 * @param source
	 *            source object logging the excetpion
	 * @param methodName
	 *            name of the method logging the excetpion
	 * @param exception
	 *            exception message
	 */
	public void debug(Object source, String methodName, Throwable exception) {
		if (!debugLogEnabled) {
			return;
		}
		debug(source.getClass().getName(), methodName, exception);
	}

	/**
	 * Logs an exception for debugging. This method should be used to log an
	 * exception if the exception does not affectthe current execution sequence.
	 *
	 * @param className
	 *            name of the class logging the exception
	 * @param methodName
	 *            name of the method logging the exception
	 * @param exception
	 *            exception message
	 */
	public void debug(String className, String methodName, Throwable exception) {
		if (!debugLogEnabled) {
			return;
		}
		Logger logger = getLogger(className);
		if (!exceptionStackTraceLogEnabled) {
			logger.debug(getMessage(methodName, exception));
		} else {
			logger.debug(getMessage(methodName, exception), exception);
		}
	}

	/**
	 * Returns true if information log is enabled.
	 *
	 * @return true if information log is enabled
	 */
	public boolean isInfoEnabled() {
		return infoLogEnabled;
	}

	/**
	 * Logs an informational message.
	 *
	 * @param source
	 *            source object logging the informational message
	 * @param methodName
	 *            name of the method logging the informational message
	 * @param message
	 *            informational message
	 */
	public void info(Object source, String methodName, Object message) {
		if (!infoLogEnabled) {
			return;
		}
		info(source.getClass().getName(), methodName, message);
	}

	/**
	 * Logs an informational message.
	 *
	 * @param className
	 *            name of the class logging the informational message
	 * @param methodName
	 *            name of the method logging the informational message
	 * @param message
	 *            informational message
	 */
	public void info(String className, String methodName, Object message) {
		if (!infoLogEnabled) {
			return;
		}
		Logger logger = getLogger(className);
		if (!stackTraceLogEnabled) {
			logger.info(getMessage(methodName, message));
		} else {
			logger.info(getMessage(methodName, message), new Throwable());
		}
	}

	/**
	 * Returns true if warning log is enabled.
	 *
	 * @return true if warning log is enabled
	 */
	public boolean isWarnEnabled() {
		return warnLogEnabled;
	}

	/**
	 * Logs an warning message.
	 *
	 * @param source
	 *            source object logging the warning message
	 * @param methodName
	 *            name of the method logging the warning message
	 * @param message
	 *            warning message
	 */
	public void warn(Object source, String methodName, Object message) {
		if (!warnLogEnabled) {
			return;
		}
		warn(source.getClass().getName(), methodName, message);
	}

	/**
	 * Logs a warning message.
	 *
	 * @param className
	 *            name of the class logging the warning message
	 * @param methodName
	 *            name of the method logging the warning message
	 * @param message
	 *            warning message
	 */
	public void warn(String className, String methodName, Object message) {
		if (!warnLogEnabled) {
			return;
		}
		Logger logger = getLogger(className);
		if (!stackTraceLogEnabled) {
			logger.warn(getMessage(methodName, message));
		} else {
			logger.warn(getMessage(methodName, message), new Throwable());
		}
	}

	/**
	 * Returns true if error log is enabled.
	 *
	 * @return true if error log is enabled
	 */
	public boolean isErrorEnabled() {
		return errorLogEnabled;
	}

	/**
	 * Logs a error message.
	 *
	 * @param source
	 *            source object logging the error message
	 * @param methodName
	 *            name of the method logging the error message
	 * @param message
	 *            error message
	 */
	public void error(Object source, String methodName, Object message) {
		if (!errorLogEnabled) {
			return;
		}
		error(source.getClass().getName(), methodName, message);
	}

	/**
	 * Logs a error message.
	 *
	 * @param className
	 *            name of the class logging the error message
	 * @param methodName
	 *            name of the method logging the error message
	 * @param message
	 *            error message
	 */
	public void error(String className, String methodName, Object message) {
		if (!errorLogEnabled) {
			return;
		}
		Logger logger = getLogger(className);
		if (!stackTraceLogEnabled) {
			logger.error(getMessage(methodName, message));
		} else {
			logger.error(getMessage(methodName, message), new Throwable());
		}
	}

	/**
	 * Logs an exception. This method should be used to log an exception if the
	 * exception hinders the current execution sequence.
	 *
	 * @param source
	 *            source object logging the error message
	 * @param methodName
	 *            name of the method logging the eception
	 * @param exception
	 *            exception message
	 */
	public void error(Object source, String methodName, Throwable exception) {
		if (!errorLogEnabled) {
			return;
		}
		error(source.getClass().getName(), methodName, exception);
	}

	/**
	 * Logs an exception. This method should be used to log an exception if the
	 * exception hinders the current execution sequence.
	 *
	 * @param className
	 *            name of the class logging the exception
	 * @param methodName
	 *            name of the method logging the eception
	 * @param exception
	 *            exception message
	 */
	public void error(String className, String methodName, Throwable exception) {
		if (!errorLogEnabled) {
			return;
		}
		Logger logger = getLogger(className);
		if (!exceptionStackTraceLogEnabled) {
			logger.error(getMessage(methodName, exception));
		} else {
			logger.error(getMessage(methodName, exception), exception);
		}
	}

	/**
	 * Logs a fatal message.
	 *
	 * @param source
	 *            source object logging the fatal message
	 * @param methodName
	 *            name of the method logging the fatal message
	 * @param message
	 *            fatal message
	 */
	public void fatal(Object source, String methodName, Object message) {
		if (!fatalLogEnabled) {
			return;
		}
		fatal(source.getClass().getName(), methodName, message);
	}

	/**
	 * Logs a fatal message.
	 *
	 * @param className
	 *            name of the class logging the fatal message
	 * @param methodName
	 *            name of the method logging the fatal message
	 * @param message
	 *            fatal error message
	 */
	public void fatal(String className, String methodName, Object message) {
		if (!fatalLogEnabled) {
			return;
		}
		Logger logger = getLogger(className);
		if (!stackTraceLogEnabled) {
			logger.fatal(getMessage(methodName, message));
		} else {
			logger.fatal(getMessage(methodName, message), new Throwable());
		}
	}

	/**
	 * Returns true if fatal error log is enabled.
	 *
	 * @return true if fatal error log is enabled
	 */
	public boolean isFatalEnabled() {
		return fatalLogEnabled;
	}

	/**
	 * Logs a fatal exception. This method should be used to log an exception if
	 * the exception hinders the current execution sequence.
	 *
	 * @param source
	 *            source object logging the fatal exception
	 * @param methodName
	 *            name of the method logging the fatal exception
	 * @param exception
	 *            fatal exception
	 */
	public void fatal(Object source, String methodName, Throwable exception) {
		if (!fatalLogEnabled) {
			return;
		}
		fatal(source.getClass().getName(), methodName, exception);
	}

	/**
	 * Logs a fatal exception. This method should be used to log an exception if
	 * the exception hinders the current execution sequence.
	 *
	 * @param className
	 *            name of the class logging the fatal exception
	 * @param methodName
	 *            name of the method logging the fatal exception
	 * @param exception
	 *            fatal exception
	 */
	public void fatal(String className, String methodName, Throwable exception) {
		if (!fatalLogEnabled) {
			return;
		}
		Logger logger = getLogger(className);
		if (!exceptionStackTraceLogEnabled) {
			logger.fatal(getMessage(methodName, exception));
		} else {
			logger.fatal(getMessage(methodName, exception), exception);
		}
	}

	/**
	 * Returns true if timing log is enabled.
	 *
	 * @return true if timing log is enabled
	 */
	public boolean isTimingEnabled() {
		return timingLogEnabled;
	}

	/**
	 * Logs a timing message.
	 *
	 * @param source
	 *            source object logging the timing message
	 * @param methodName
	 *            name of the method logging the timing message
	 * @param startTime
	 *            start time in millis
	 * @param endTime
	 *            end time in millis
	 * @param message
	 *            any message
	 */
	public void timing(Object source, String methodName, long startTime,
			long endTime, String message) {
		if (!timingLogEnabled) {
			return;
		}
		timing(source.getClass().getName(), methodName, startTime, endTime,
				message);
	}

	/**
	 * Logs a timing message.
	 *
	 * @param className
	 *            name of the class logging the timing message
	 * @param methodName
	 *            name of the method logging the timing message
	 * @param startTime
	 *            start time in millis
	 * @param endTime
	 *            end time in millis
	 * @param message
	 *            any message
	 */
	public void timing(String className, String methodName, long startTime,
			long endTime, String message) {
		if (!timingLogEnabled) {
			return;
		}
		Logger logger = getLogger(className);
		logger.log(Log4JLogLevel.TIMING, (endTime - startTime) + "ms "
				+ message);
	}

	/**
	 * Helper method to get logger for the class.
	 */
	private Logger getLogger(String className) {
		return Logger.getLogger(className);
	}

	public String getMessage(String methodName, Object message) {
		return methodName + "|" + message;
	}
}