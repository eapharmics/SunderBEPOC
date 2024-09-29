package com.enviroapps.eapharmics.common.services.logging;

import org.apache.log4j.Level;

/**
 * This class introduces a new level level called TIMING.
 */
public class Log4JLogLevel extends Level {
 static public final int TIMING_INT = Level.DEBUG_INT + 1;

 private static String TIMING_STR = "TIMING";

 public static final Log4JLogLevel TIMING = new Log4JLogLevel(TIMING_INT,
    TIMING_STR, 7);

 /**
  * @param level
  * @param strLevel
  * @param syslogEquiv
  */
 protected Log4JLogLevel(int level, String strLevel, int syslogEquiv) {
  super(level, strLevel, syslogEquiv);
 }

 /* (non-Javadoc)
  * Convert the string passed as argument to a level. If the
  * conversion fails, then this method returns {@link #TIMING}.
  * @see org.apache.log4j.Level#toLevel(java.lang.String)
  */
 public static Level toLevel(String arg) {
  return (Level) toLevel(arg, Log4JLogLevel.TIMING);
 }

 /* (non-Javadoc)
  * @see org.apache.log4j.Level#toLevel(java.lang.String, org.apache.log4j.Level)
  */
 public static Level toLevel(String arg, Level defaultValue) {
  if (arg == null) {
    return defaultValue;
  }
  String stringVal = arg.toUpperCase();
  if (stringVal.equals(TIMING_STR)) {
    return Log4JLogLevel.TIMING;
  }
  return Level.toLevel(arg, defaultValue);
 }

 /* (non-Javadoc)
  * @see org.apache.log4j.Level#toLevel(int)
  */
 public static Level toLevel(int i) throws IllegalArgumentException {
  switch (i) {
  case TIMING_INT:
    return Log4JLogLevel.TIMING;
  }
  return Level.toLevel(i);
 }
}