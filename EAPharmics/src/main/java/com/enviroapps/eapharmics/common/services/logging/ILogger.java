package com.enviroapps.eapharmics.common.services.logging;
/**
 * Logger interface defines an adapter interface for different logging tool
 * like log4j, jdk1.4 log utility etc. This interface lets application logging
 * to integrate with any one of these offs-the-shelf logging tools.
 *
 * @author EnviroApps
 * @version $Revision: 1.1 $
 */
public interface ILogger {

  /**
   * Returns true if debug log is enabled.
   *
   * @return true if debug log is enabled
   */
  public boolean isDebugEnabled();
  /**
   * Logs a debug message.
   *
   * @param source source object logging the debug message
   * @param methodName name of the method logging the debug message
   * @param message debug message
   */
  public void debug(Object source, String methodName, Object message);
  /**
   * Logs a debug message.
   *
   * @param className name of the class logging the debug message
   * @param methodName name of the method logging the debug message
   * @param message debug message
   */
  public void debug(String className, String methodName, Object message);
  /**
   * Logs an exception for debugging. This method should be used to log an
   * exception if the exception does not affectthe current execution sequence.
   *
  * @param source source object logging the excetpion
   * @param methodName name of the method logging the excetpion
   * @param exception exception message
   */
  public void debug(Object source, String methodName, Throwable exception);
  /**
   * Logs an exception for debugging. This method should be used to log an
   * exception if the exception does not affectthe current execution sequence.
   *
   * @param className name of the class logging the exception
   * @param methodName name of the method logging the exception
   * @param exception exception message
   */
  public void debug(String className, String methodName, Throwable exception);
  /**
   * Returns true if information log is enabled.
   *
   * @return true if information log is enabled
   */

   public boolean isInfoEnabled();
  /**
   * Logs an informational message.
   *
   * @param source source object logging the informational message
   * @param methodName name of the method logging the informational message
   * @param message informational message
   */
  public void info(Object source, String methodName, Object message);
  /**
   * Logs an informational message.
   *
   * @param className name of the class logging the informational message
   * @param methodName name of the method logging the informational message
   * @param message informational message
   */
  public void info(String className, String methodName, Object message);
  /**
   * Returns true if warning log is enabled.
   *
   * @return true if warning log is enabled
   */
  public boolean isWarnEnabled();
  /**
   * Logs an warning message.
   *
   * @param source source object logging the warning message
   * @param methodName name of the method logging the warning message
   * @param message warning message
   */
  public void warn(Object source, String methodName, Object message);
  /**
   * Logs a warning message.
   *
   * @param className name of the class logging the warning message
   * @param methodName name of the method logging the warning message
   * @param message warning message
   */
  public void warn(String className, String methodName, Object message);
  /**
   * Returns true if error log is enabled.
   *
   * @return true if error log is enabled
   */
  public boolean isErrorEnabled();
  /**
   * Logs a error message.
   *
   * @param source source object logging the error message
   * @param methodName name of the method logging the error message
   * @param message error message
   */
  public void error(Object source, String methodName, Object message);
  /**
   * Logs a error message.
   *
   * @param className name of the class logging the error message
   * @param methodName name of the method logging the error message
   * @param message error message
   */
  public void error(String className, String methodName, Object message);
  /**
   * Logs an exception. This method should be used to log an exception
   * if the exception hinders the current execution sequence.
   *
   * @param source source object logging the error message
   * @param methodName name of the method logging the eception
   * @param exception exception message
   */
  public void error(Object source, String methodName, Throwable exception);
  /**
   * Logs an exception. This method should be used to log an exception
   * if the exception hinders the current execution sequence.
   *
   * @param className name of the class logging the exception
   * @param methodName name of the method logging the eception
   * @param exception exception message
   */
  public void error(String className, String methodName, Throwable exception);
  /**
   * Returns true if fatal error log is enabled.
   *
   * @return true if fatal error log is enabled
   */
  public boolean isFatalEnabled();
  /**
   * Logs a fatal message.
   *
   * @param source source object logging the fatal message
   * @param methodName name of the method logging the fatal message
   * @param message fatal message
   */
  public void fatal(Object source, String methodName, Object message);
  /**
   * Logs a fatal message.
   *
   * @param className name of the class logging the fatal message
   * @param methodName name of the method logging the fatal message
   * @param message fatal error message
   */
  public void fatal(String className, String methodName, Object message);
  /**
  * Logs a fatal exception. This method should be used to log an exception
  * if the exception hinders the current execution sequence.
  *
  * @param source source object logging the fatal exception
  * @param methodName name of the method logging the fatal exception
  * @param exception fatal exception
  */
  public void fatal(Object source, String methodName, Throwable exception);
  /**
   * Logs a fatal exception. This method should be used to log an exception
   * if the exception hinders the current execution sequence.
   *
   * @param className name of the class logging the fatal exception
   * @param methodName name of the method logging the fatal exception
   * @param exception fatal exception
   */
  public void fatal(String className, String methodName, Throwable exception);
  /**
   * Returns true if timing log is enabled.
   *
   * @return true if timing log is enabled
   */
  public boolean isTimingEnabled();
  /**
   * Logs a timing message.
   *
   * @param source source object logging the timing message
   * @param methodName name of the method logging the timing message
   * @param startTime start time in millis
   * @param endTime end time in millis
   * @param message any message
   */
  public void timing(
    Object source,
    String methodName,
    long startTime,
    long endTime,
    String message);
  /**
   * Logs a timing message.
   *
   * @param className name of the class logging the timing message
   * @param methodName name of the method logging the timing message
   * @param startTime start time in millis
   * @param endTime end time in millis
   * @param message any message
   */
  public void timing(
    String className,
    String methodName,
    long startTime,
    long endTime,
    String message);
}