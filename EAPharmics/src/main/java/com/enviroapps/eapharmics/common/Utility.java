package com.enviroapps.eapharmics.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.Vector;

import com.enviroapps.eapharmics.common.services.UtilityServiceFactory;
import com.enviroapps.eapharmics.ui.Constants;
import com.enviroapps.eapharmics.vo.security.UserVO;

/**
 * Utility class with several utility functions
 *
 * @author EnviroApps
 * @version $Revison$
 */
public class Utility {
	/**
	 * Default format of date time when represented in a string.
	 */
	public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd-HH:mm:ss:SSSSSS";

	/**
	 * Format of date time when represented in a string.
	 */
	public static final String SIMPLE_DATE_TIME_FORMAT = "MM/dd/yyyy hh:mm:ss a";

	/**
	 * Format of date represented as a 12 hour time string with AM_PM. As in
	 * "2:53 PM"
	 */
	public static final String SIMPLE_12_HOUR_TIME_FORMAT = "hh:mm a";

	/**
	 * Default format of date time when represented in a string.
	 */
	public static final String DEFAULT_DATE_FORMAT = "MM/dd/yyyy";

	/**
	 * Default format of date time when represented in a string.
	 */
	public static final String LABEL_DEFAULT_DATE_FORMAT = "MM-dd-yyyy";

	/**
	 * Default time zone id.
	 */
	public static final String DEFAULT_TIME_ZONE_ID = "EST";

	/**
	 * Number of milliseconds in a day 24 hours * 60 min * 60 * seconds * 1000
	 * milliseconds
	 */
	public static final long MILLISECONDS_IN_A_DAY = 24 * 60 * 60 * 1000;

	public static final String DATE_FORMAT = "yyyy-MM-dd";

	public static final String TIME_FORMAT = "yyyy-MM-dd-hh:mm:ss";
	
	/**
	 * Utility constructor comment.
	 */
	public Utility() {
		super();
	}

	public static boolean areValuesEqual(byte val1, byte val2) {
		return val1 == val2;
	}

	/**
	 * Insert the method's description here. Creation date: (6/20/00 10:23:27
	 * AM)
	 */
	public static boolean areValuesEqual(double val1, double val2) {
		return val1 == val2;
	}

	/**
	 * Insert the method's description here. Creation date: (6/20/00 10:23:27
	 * AM)
	 */
	public static boolean areValuesEqual(int val1, int val2) {
		return val1 == val2;
	}

	public static boolean areValuesEqual(Boolean val1, Boolean val2) {
		if (val1 == null) {
			if (val2 == null) {
				return true;
			} else {
				return false;
			}
		}
		return val1.equals(val2);
	}

	/**
	 * Insert the method's description here. Creation date: (6/20/00 10:23:27
	 * AM)
	 */
	public static boolean areValuesEqual(Double val1, Double val2) {
		if (val1 == null) {
			if (val2 == null) {
				return true;
			} else {
				return false;
			}
		}
		return val1.equals(val2);
	}

	/**
	 * Insert the method's description here. Creation date: (6/20/00 10:23:27
	 * AM)
	 */
	public static boolean areValuesEqual(String val1, String val2) {
		if (val1 == null) {
			if (val2 == null) {
				return true;
			} else {
				return false;
			}
		}
		if (val1 != null) {
			val1 = val1.trim();
		}
		if (val2 != null) {
			val2 = val2.trim();
		}
		return val1.equals(val2);
	}

	/**
	 * Insert the method's description here. Creation date: (6/20/00 10:23:27
	 * AM)
	 */
	public static boolean areValuesEqual(java.util.Date val1,
			java.util.Date val2) {
		if (val1 == null) {
			if (val2 == null) {
				return true;
			} else {
				return false;
			}
		}
		return val1.equals(val2);
	}

	/**
	 * Insert the method's description here. Creation date: (6/20/00 10:23:27
	 * AM)
	 */
	public static boolean areValuesEqual(boolean val1, boolean val2) {
		return val1 == val2;
	}

	/**
	 * Insert the method's description here. Creation date: (6/20/00 10:23:27
	 * AM)
	 */
	public static Double bigDecimalToDoubleWrapper(BigDecimal d) {
		return d != null ? new Double(d.doubleValue()) : null;
	}

	public static int bigDecimalToInt(BigDecimal d) {
		return d == null ? 0 : d.intValue();
	}

	public static double bigDecimalToDouble(BigDecimal d) {
		Double value = bigDecimalToDoubleWrapper(d);
		return value == null ? 0 : value.doubleValue();
	}

	public static float bigDecimalToFloat(BigDecimal d) {
		Double value = bigDecimalToDoubleWrapper(d);
		return value == null ? 0 : value.floatValue();
	}

	public static String bigDecimalToString(BigDecimal d) {
		return d != null ? d.toString() : null;
	}

	/**
	 * Insert the method's description here. Creation date: (6/20/00 10:23:27
	 * AM)
	 */
	public static Integer booleanToInteger(boolean b) {
		return b == true ? new Integer(1) : new Integer(0);
	}

	/**
	 * Insert the method's description here. Creation date: (6/20/00 10:23:27
	 * AM)
	 */
	public static String booleanWrapperToString(Boolean b) {
		if (b == null) {
			return null;
		}
		return b.equals(Boolean.TRUE) ? "Y" : "N";
	}

	public static String booleanToString(boolean b) {
		return b == true ? "Y" : "N";
	}

	public static int byteToInt(byte b) {
		return new Byte(b).intValue();
	}

	public static String byteToString(byte b) {
		return new Byte(b).toString();
	}

	/**
	 * Insert the method's description here. Creation date: (10/13/00 9:21:58
	 * AM)
	 */
	public static Object cloneObject(Object object) throws IOException {
		byte[] byteArray = writeObject(object);
		Object clonedObject = readObject(byteArray);
		return clonedObject;
	}

	public static long dateToLong(java.util.Date date) {
		return date != null ? date.getTime() : 0;
	}

	public static String dateToLongString(java.util.Date date) {
		return date != null ? Long.toString(date.getTime()) : null;
	}

	public static Long dateToLongWrapper(java.util.Date date) {
		return date != null ? new Long(date.getTime()) : null;
	}

	/**
	 * Insert the method's description here. Creation date: (6/20/00 10:23:27
	 * AM)
	 */
	public static java.sql.Date dateToSqlDate(java.util.Date d) {
		return d != null ? new java.sql.Date(d.getTime()) : null;
	}

	/** Converts date to string. */
	public static String dateToString(java.util.Date date) {
		if (date == null) {
			return null;
		}
		String dateString = dateToString(date, DEFAULT_DATE_TIME_FORMAT);
		return dateString;
	}

	public static String dateToSortableString(java.util.Date date) {
		final String zeroes = "00000000000000000000";
		final int length = zeroes.length();
		if (date == null) {
			return null;
		}
		String dateString = dateToLongString(date);
		if (dateString.length() >= length) {
			return dateString;
		} else {
			return zeroes.substring(0, length - dateString.length())
					+ dateString;
		}
	}

	/** Converts date to string. */
	public static String dateToString(java.util.Date date, String formatPattern) {
		if (date == null) {
			return null;
		}
		if (formatPattern == null || formatPattern.length() <= 0) {
			formatPattern = DEFAULT_DATE_TIME_FORMAT;
		}
		SimpleDateFormat dateFormatter = new SimpleDateFormat(formatPattern);
		String dateString = dateFormatter.format(date);
		return dateString;
	}

	/** Converts date to string. */
	public static String dateToString(java.util.Date date,
			String formatPattern, String timeZoneId) {
		if (date == null) {
			return null;
		}
		if (formatPattern == null || formatPattern.length() <= 0) {
			formatPattern = DEFAULT_DATE_TIME_FORMAT;
		}
		if (timeZoneId == null || timeZoneId.length() <= 0) {
			timeZoneId = DEFAULT_TIME_ZONE_ID;
		}
		SimpleDateFormat dateFormatter = new SimpleDateFormat(formatPattern);
		java.util.TimeZone tz = new java.util.SimpleTimeZone(0, timeZoneId);
		dateFormatter.setTimeZone(tz);
		String dateString = dateFormatter.format(date);
		return dateString;
	}

	/**
	 * Insert the method's description here. Creation date: (6/20/00 10:23:27
	 * AM)
	 */
	public static java.sql.Timestamp dateToTimestamp(java.util.Date d) {
		return d != null ? new java.sql.Timestamp(d.getTime()) : null;
	}

	/**
	 * Converts string to date using default format.
	 *
	 * @param dateString
	 *            date string
	 */
	public static java.util.Date stringToDate(String dateString) {
		java.util.Date date = stringToDate(dateString, DEFAULT_DATE_TIME_FORMAT);
		return date;
	}

	/**
	 * Converts string to date using default format.
	 *
	 * @param dateString
	 *            date string
	 * @param format
	 *            pattern associated format pattern
	 */
	public static java.util.Date stringToDate(String dateString,
			String formatPattern) {
		if (dateString == null || dateString.length() == 0) {
			return null;
		}
		if (formatPattern == null || formatPattern.length() <= 0) {
			formatPattern = DEFAULT_DATE_TIME_FORMAT;
		}
		SimpleDateFormat dateFormatter = new SimpleDateFormat(formatPattern);
		java.util.Date date = null;
		try {
			date = dateFormatter.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * Converts string to date using default format.
	 *
	 * @param dateString
	 *            date string
	 * @param format
	 *            pattern associated format pattern
	 */
	public static java.util.Date stringToDate(String dateString,
			String formatPattern, String timeZoneId) {
		if (dateString == null || dateString.length() == 0) {
			return null;
		}
		if (formatPattern == null || formatPattern.length() <= 0) {
			formatPattern = DEFAULT_DATE_TIME_FORMAT;
		}
		if (timeZoneId == null || timeZoneId.length() <= 0) {
			timeZoneId = DEFAULT_TIME_ZONE_ID;
		}
		SimpleDateFormat dateFormatter = new SimpleDateFormat(formatPattern);
		java.util.TimeZone tz = new java.util.SimpleTimeZone(0, timeZoneId);
		dateFormatter.setTimeZone(tz);
		java.util.Date date = null;
		try {
			date = dateFormatter.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * Converts string to sql date.
	 */
	public static java.sql.Date stringToSqlDate(String dateString) {
		if (dateString == null) {
			return null;
		}
		java.util.Date date = stringToDate(dateString, DEFAULT_DATE_FORMAT);
		if (date == null) {
			return null;
		}
		long millis = date.getTime();
		java.sql.Date sqlDate = new java.sql.Date(millis);
		return sqlDate;
	}

	/**
	 * Converts string to timestamp using default format.
	 */
	public static java.sql.Timestamp stringToTimestamp(String dateString) {
		java.sql.Timestamp timestamp = stringToTimestamp(dateString,
				DEFAULT_DATE_TIME_FORMAT);
		return timestamp;
	}

	/**
	 * Converts string to timestamp using default format.
	 */
	public static java.sql.Timestamp stringToTimestamp(String dateString,
			String formatPattern) {
		if (dateString == null || dateString.length() == 0) {
			return null;
		}
		if (formatPattern == null || formatPattern.length() <= 0) {
			formatPattern = DEFAULT_DATE_TIME_FORMAT;
		}
		java.util.Date date = stringToDate(dateString, formatPattern);
		if (date == null) {
			return null;
		}
		long millis = date.getTime();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(millis);
		return timestamp;
	}

	/**
	 * Converts timestamp to string.
	 */
	public static String timestampToString(java.sql.Timestamp timestamp) {
		if (timestamp == null) {
			return null;
		}
		java.util.Date date = new java.util.Date(timestamp.getTime());
		String dateString = dateToString(date);
		return dateString;
	}

	/**
	 * Insert the method's description here. Creation date: (6/20/00 10:23:27
	 * AM)
	 */
	public static BigDecimal doubleToBigDecimal(double d) {
		return doubleWrapperToBigDecimal(new Double(d));
	}

	/**
	 * Insert the method's description here. Creation date: (6/20/00 10:23:27
	 * AM)
	 */
	public static BigDecimal doubleWrapperToBigDecimal(Double d) {
		if (d == null) {
			return null;
		}
		String doubleString = d.toString();
		int index = doubleString.indexOf("E");
		if (index == -1) {
			return new BigDecimal(doubleString);
		}
		String numberString = doubleString.substring(0, index);
		String exponentString = doubleString.substring(index + 1);
		int exponent = Integer.parseInt(exponentString);
		BigDecimal number = new BigDecimal(numberString);
		if (exponent > 0) {
			number = number.movePointRight(exponent);
		} else {
			number = number.movePointLeft(-exponent);
		}
		return number;
	}

	/**
	 * Insert the method's description here. Creation date: (6/20/00 10:23:27
	 * AM)
	 */
	public static Integer doubleToIntWrapper(double d) {
		return new Integer((new Double(d)).intValue());
	}

	/**
	 * Insert the method's description here. Creation date: (6/20/00 10:23:27
	 * AM)
	 */
	public static String doubleToString(Double d) {
		return d != null ? d.toString() : null;
	}

	public static BigDecimal floatToBigDecimal(float f) {
		return doubleWrapperToBigDecimal(new Double(String.valueOf(f)));
	}

	/** Insert the method's description here. Creation date: (6/23/00 5:59:03 PM) */
	private static Method getAccessor(Method[] methods, String accessorName) {
		for (int i = 0; i < methods.length; i++) {
			if (accessorName.equalsIgnoreCase(methods[i].getName())) {
				return methods[i];
			}
		}
		return null;
	}

	/**
	 * Insert the method's description here. Creation date: (6/20/00 10:23:27
	 * AM)
	 */
	public static java.util.Date getDate(String yearStr, String monthStr,
			String dayStr) {
		if (yearStr == null || yearStr.length() <= 0) {
			return null;
		}
		int year = 2000;
		int month = 1;
		int day = 1;
		if (yearStr != null) {
			try {
				year = Integer.parseInt(yearStr);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return null;
			}
		}
		if (monthStr != null) {
			try {
				month = Integer.parseInt(monthStr);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return null;
			}
		}
		if (dayStr != null) {
			try {
				day = Integer.parseInt(dayStr);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return null;
			}
		}
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.set(year, month, day);
		return calendar.getTime();
	}

	/**
	 * Insert the method's description here. Creation date: (6/20/00 10:23:27
	 * AM)
	 */
	public static double getDoubleValue(Double d) {
		return d != null ? d.doubleValue() : 0.00;
	}

	/**
	 * Insert the method's description here. Creation date: (6/20/00 10:23:27
	 * AM)
	 */
	public static double getDoubleValue(BigDecimal d) {
		return d != null ? d.doubleValue() : 0.00;
	}

	/**
	 * Insert the method's description here. Creation date: (6/20/00 10:23:27
	 * AM)
	 */
	public static int getIntValue(Integer i) {
		return i != null ? i.intValue() : 0;
	}

	public static String getYear(java.util.Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		return String.valueOf(year);
	}

	/**
	 * Insert the method's description here. Creation date: (6/20/00 10:23:27
	 * AM)
	 */
	public static boolean integerWrapperToBoolean(Integer i) {
		return i != null && i.intValue() > 0 ? true : false;
	}

	/**
	 * Insert the method's description here. Creation date: (6/20/00 10:23:27
	 * AM)
	 */
	public static String integerWrapperToString(Integer i) {
		return i != null ? i.toString() : null;
	}

	/**
	 * Insert the method's description here. Creation date: (6/20/00 10:23:27
	 * AM)
	 */
	public static Boolean integerWrapperToBooleanWrapper(Integer i) {
		if (i == null) {
			return null;
		}
		return i.intValue() > 0 ? Boolean.TRUE : Boolean.FALSE;
	}

	/**
	 * Converts a String to a Boolean returns null if a the string is blank or
	 * not a valid value string.
	 */
	public static boolean stringToBoolean(String s) {
		if (!isNullOrEmpty(s)) {
			s = s.trim();
			return s.equalsIgnoreCase("Y") || s.equalsIgnoreCase("Yes")
					|| s.equalsIgnoreCase("TRUE") ? true : false;
		} else {
			return false;
		}
	}

	/**
	 * Converts a String to a Boolean returns null if a the string is blank or
	 * not a valid value string.
	 */
	public static Boolean stringToBooleanWrapper(String s) {
		if (!isNullOrEmpty(s)) {
			s = s.trim();
			return s.equalsIgnoreCase("Y") || s.equalsIgnoreCase("Yes")
					|| s.equalsIgnoreCase("TRUE") ? Boolean.TRUE
					: Boolean.FALSE;
		} else {
			return null;
		}
	}

	/**
	 * Converts a String to an Integer returns null if a the string is blank or
	 * not a valid value string.
	 */
	public static Integer stringToIntegerWrapper(String s) {
		return !isNullOrEmpty(s) ? Integer.valueOf(s) : null;
	}

	/**
	 * Converts a String to a Double returns null if a the string is blank or
	 * not a valid value string.
	 */
	public static Double stringToDoubleWrapper(String s) {
		return !isNullOrEmpty(s) ? Double.valueOf(s) : null;
	}

	/**
	 * Converts a String to a Byte returns null if a the string is blank or not
	 * a valid value string.
	 */
	public static Byte stringToByteWrapper(String s) {
		return !isNullOrEmpty(s) ? Byte.valueOf(s) : null;
	}

	/**
	 * Converts a String to a Float returns null if a the string is blank or not
	 * a valid value string.
	 */
	public static Float stringToFloatWrapper(String s) {
		return !isNullOrEmpty(s) ? Float.valueOf(s) : null;
	}

	/**
	 * Converts a String to a Long returns null if a the string is blank or not
	 * a valid value string.
	 */
	public static Long stringToLongWrapper(String s) {
		return !isNullOrEmpty(s) ? Long.valueOf(s) : null;
	}

	/**
	 * Converts a String to a Short returns null if a the string is blank or not
	 * a valid value string.
	 */
	public static Short stringToShortWrapper(String s) {
		return !isNullOrEmpty(s) ? Short.valueOf(s) : null;
	}

	/**
	 * Converts the first character of a String to a Character returns null if a
	 * the string is blank or not a valid value string.
	 */
	public static Character stringToCharacterWrapper(String s) {
		return !isNullOrEmpty(s) ? new Character(s.charAt(0)) : null;
	}

	public static BigDecimal intToBigDecimal(int i) {
		return new BigDecimal(String.valueOf(i));
	}

	public static byte intToByte(int i) {
		return new Integer(i).byteValue();
	}

	public static java.util.Date longToDate(long milliseconds) {
		return milliseconds == 0 ? null : new Date(milliseconds);
	}

	public static java.util.Date longWrapperToDate(Long milliseconds) {
		return milliseconds != null ? new Date(milliseconds.longValue()) : null;
	}

	/**
	 * Describe the class
	 *
	 * @version $Revison$
	 * @since
	 */
	public static Object readObject(byte[] byteArray) throws IOException {
		try {
			ByteArrayInputStream byteInStream = new ByteArrayInputStream(
					byteArray);
			ObjectInputStream objectInStream = new ObjectInputStream(
					byteInStream);
			Object object = objectInStream.readObject();
			byteInStream.close();
			return object;
		} catch (ClassNotFoundException e) {
			throw new IOException(e.toString());
		}
	}

	/**
	 * Insert the method's description here. Creation date: (6/20/00 10:23:27
	 * AM)
	 */
	public static String toString(java.lang.Object object) {
		try {
			// get all fields
			Vector fieldVector = new Vector();
			Class currentClass = object.getClass();
			while (currentClass.equals(java.lang.Object.class) == false) {
				Field[] fields = currentClass.getDeclaredFields();
				for (int i = fields.length - 1; i >= 0; i--) {
					fieldVector.insertElementAt(fields[i], 0);
				}
				currentClass = currentClass.getSuperclass();
			}
			// get all methods
			Method[] methods = object.getClass().getMethods();
			StringBuffer strBuf = new StringBuffer(256);
			strBuf.append("\t" + object.getClass().getName() + " = {" + "\n");
			for (int i = 0; i < fieldVector.size(); i++) {
				Field field = (Field) fieldVector.elementAt(i);
				String fieldName = field.getName();
				String fieldType = field.getType().getName();
				String accessorName = null;
				if (fieldType.equals(boolean.class.getName())) {
					accessorName = "is" + fieldName;
				}
				// else if (
				// fieldType.equals(Vector.class.getName())
				// || fieldType.equals(Hashtable.class.getName())
				// || fieldType.equals(List.class.getName())
				// || fieldType.equals(Map.class.getName()))
				//
				// {
				// accessorName = "getAll" + fieldName;
				// }
				else {
					accessorName = "get" + fieldName;
				}
				Method accessor = getAccessor(methods, accessorName);
				if (accessor != null) {
					Object fieldValue = accessor.invoke(object, new Object[0]);
					String fieldValueStr = null;
					if (fieldType.equals(Vector.class.getName())
							|| fieldType.equals(Hashtable.class.getName())
							|| fieldType.equals(List.class.getName())
							|| fieldType.equals(Map.class.getName())) {
						if (fieldValue instanceof Enumeration) {
							Enumeration iter = (Enumeration) fieldValue;
							Vector elements = new Vector();
							while (iter.hasMoreElements()) {
								elements.addElement(iter.nextElement());
							}
							if (elements.isEmpty()) {
								fieldValueStr = null;
							} else {
								fieldValueStr = elements.toString();
							}
						} else if (fieldValue instanceof Iterator) {
							Iterator iter = (Iterator) fieldValue;
							Vector elements = new Vector();
							while (iter.hasNext()) {
								elements.addElement(iter.next());
							}
							if (elements.isEmpty()) {
								fieldValueStr = null;
							} else {
								fieldValueStr = elements.toString();
							}
						} else {
							fieldValueStr = fieldValue != null ? fieldValue
									.toString() : null;
						}
					} else if (fieldValue != null
							&& fieldValue.getClass().isArray()) {
						Object[] vals = (Object[]) fieldValue;
						for (int j = 0; j < vals.length; j++) {
							Object val = vals[j].toString();
							strBuf.append("\t\t" + fieldName + "[" + j + "] =<"
									+ val + ">\n");
						}
						fieldValueStr = fieldValue != null ? fieldValue
								.toString() : null;
					} else {
						fieldValueStr = fieldValue != null ? fieldValue
								.toString() : null;
					}
					strBuf.append("\t\t" + fieldName + " =<" + fieldValueStr
							+ ">\n");
				} else {
				}
			}
			strBuf.append("\t}");
			return strBuf.toString();
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * Describe the class
	 *
	 * @version $Revison$
	 * @since
	 */
	public static byte[] writeObject(Object object) throws IOException {
		ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutStream = new ObjectOutputStream(
				byteOutStream);
		objectOutStream.writeObject(object);
		objectOutStream.close();
		return byteOutStream.toByteArray();
	}

	/**
	 * Calculate the size of the object.
	 *
	 */
	public static int calculateSizeOfObject(Object object) {
		ObjectOutputStream objectOutStream = null;
		try {
			NullOutputStream nullOutStream = new NullOutputStream();
			objectOutStream = new ObjectOutputStream(nullOutStream);
			objectOutStream.writeObject(object);
			return nullOutStream.size();
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalArgumentException();
		} finally {
			try {
				if (objectOutStream != null) {
					objectOutStream.close();
				}
			} catch (IOException e) {
			}
		}
	}

	public static java.util.Date stripTime(java.util.Date date) {
		if (date == null) {
			return null;
		}
		Date cloneDate = new Date(date.getTime());
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(cloneDate);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static java.util.Date getEndOfDayTime(java.util.Date date) {
		if (date == null) {
			return null;
		}
		Date cloneDate = new Date(date.getTime());
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(cloneDate);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	/**
	 *
	 * gives the date difference date2 - date1
	 */
	public static int dateDiff(java.util.Date date1, java.util.Date date2) {
		if (date1 == null || date2 == null) {
			return 0;
		}
		Date fromDate = stripTime(date1);
		Calendar cal1 = GregorianCalendar.getInstance();
		Calendar cal2 = GregorianCalendar.getInstance();
		cal1.setTime(date2);
		cal2.set(cal1.get(Calendar.YEAR), cal1.get(Calendar.MONTH), cal1
				.get(Calendar.DATE), 23, 59, 59);
		Date toDate = cal2.getTime();
		long f = (toDate.getTime() - fromDate.getTime())
				/ MILLISECONDS_IN_A_DAY;
		return new Integer(Long.toString(f)).intValue();
	}

	/**
	 * Get the last date of the maximum four digit year. The reason we take only
	 * a four digit year instead of a Maximum long is that SQL Server does not
	 * support dates beyond this. TBD if this is true.
	 */
	public static Date getOpenEndedDateTime() {
		return stringToDate("9999-12-31-23:59:59", "yyyy-MM-dd-HH:mm:ss");
	}

	/**
	 * Get the last date of the maximum four digit year. The reason we take only
	 * a four digit year instead of a Maximum long is that SQL Server does not
	 * support dates beyond this. TBD if this is true.
	 */
	public static Date getOpenEndedDate() {
		return stringToDate("9999-12-31", "yyyy-MM-dd");
	}

	/**
	 *
	 * Returns the System date without the time information
	 */
	public static Date getCurrentDate() {
		return stringToDate(dateToString(new Date()), DATE_FORMAT);
	}

	/**
	 *
	 * Returns the System date with the time information upto the second (no
	 * milliseconds)
	 */
	public static Date getCurrentDateTime() {
		return stringToDate(dateToString(new Date()), TIME_FORMAT);
	}

	public static Date addTime(Date date, int type, int amount) {
		if (null == date) {
			return null;
		}
		Date d = new Date(date.getTime());
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(d);
		cal.add(type, amount);
		return cal.getTime();
	}

	/**
	 * Determines if a string is null or empty returns false if string has a
	 * non-empty value returns true if string is null or empty
	 *
	 * @return boolean :
	 * @param String
	 */
	public static boolean isNullOrEmpty(String s) {
		if (s == null || s.trim().length() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Determines if an object is null or not.
	 * returns true if object is null and false if object is not null.
	 *
	 * @return boolean :
	 * @param Oject
	 */
	public static boolean isNullOrEmpty(Object obj) {
		if (obj == null) {
			return true;
		}
		return false;
	}

	public static Double roundDecimalNumber(Double d,
			int numberOfDigitsAfterDecimal) {
		if (d == null) {
			return null;
		}
		if (numberOfDigitsAfterDecimal < 0) {
			throw new IllegalArgumentException(
					"Number of digits after decimal needs to be > 0");
		}
		double roundingFactor = Math.pow(10.0, numberOfDigitsAfterDecimal);
		double rounded = Math.round(d.doubleValue() * roundingFactor)
				/ roundingFactor;
		return new Double(rounded);
	}

	public static String removeChar(String source, char removeChar) {
		StringBuffer buffer = new StringBuffer();
		int index = -1, beginIndex = 0;
		while (true) {
			index = source.indexOf(removeChar);
			if (-1 == index) {
				buffer.append(source);
				break;
			}
			buffer.append(source.substring(beginIndex, index));
			source = source.substring(index + 1);
			index = -1;
		}
		return buffer.toString();
	}

	/**
	 * Converts a string separated by a token into name value pairs
	 *
	 * @param nameValuePairs
	 * @return
	 */
	public static Properties stringToNameValuePairs(String nameValuePairs,
			String token) {
		Properties properties = new Properties();
		StringTokenizer tokenizer = new StringTokenizer(nameValuePairs, token);
		while (tokenizer.hasMoreTokens()) {
			StringTokenizer nameValueTokenizer = new StringTokenizer(tokenizer
					.nextToken(), "=");
			String name = null;
			String value = null;
			if (nameValueTokenizer.hasMoreTokens()) {
				name = nameValueTokenizer.nextToken();
			}
			if (nameValueTokenizer.hasMoreTokens()) {
				value = nameValueTokenizer.nextToken();
			}
			if (name != null && value != null) {
				properties.put(name, value);
			}
		}
		return properties;
	}


	/**
	 * @param date
	 * @return
	 */
	public static Date getMonthEndDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}

	/**
	 * @param date
	 * @param months
	 * @return
	 */
	public static Date getMonthEndDate(Date date, int months) {
		Date date1 = addTime(date, Calendar.MONTH, months);
		return getMonthEndDate(date1);
	}

	/**
	 * Returns Year and Month as a number YYYYMM format for an input date
	 * @param inputDate
	 * @return
	 * @throws ParseException
	 */
	public static long getYearMonthFromDateYYYYMM(Date inputDate){

		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(inputDate);
		int month = calendar.get(Calendar.MONTH)+1;
		int year = calendar.get(Calendar.YEAR);
		if (month > 11) {
			month = 0;
			year = year + 1;
		}
		long yyyymm = (long)((year*100) + month);
		return yyyymm;
	}

	/**
	 * Returns current Year and Month as a number YYYYMM format
	 * @return
	 * @throws ParseException
	 */
	public static long getCurrentYearMonthYYYYMM(){

		Date currentDate = getCurrentDate();
		return getYearMonthFromDateYYYYMM(currentDate);

	}

	/**
	 * @param value
	 * @return
	 */
	public static String ensureNotNull(String value) {
		return value == null ? "" : value;
	}
	
	/**
	 * @param value
	 * @return
	 */
	public static String ensureDefaultSpace(String value) {
		return value == null ? " " : value;
	}

	/**
	 * @param fileName
	 * @return
	 */
	public static byte[] getBytesFromFile(String fileName) throws IOException {

		File file = new File(fileName);
		InputStream is = new FileInputStream(file);

		// Get the size of the file
		long length = file.length();

		// Create the byte array to hold the data
		byte[] bytes = new byte[(int) length];

		// Read in the bytes
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length
				&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}

		// Ensure all the bytes have been read in
		if (offset < bytes.length) {
			throw new IOException("Could not completely read file "
					+ file.getName());
		}

		// Close the input stream and return bytes
		is.close();
		return bytes;
	}
	
	public static String getDateStringForLocale(Date date, String locale) {		
		if (date == null) {
			return null;
		}
		if (locale == null || locale.equals("en_US")) {
			return dateToString(date, DEFAULT_DATE_FORMAT);
		}

		return dateToString(date, "dd/MM/yyyy");
		
	}
	
	/**
	 * @param date
	 * @param locale
	 * @return
	 */
	public static String getDateTimeStringForLocale(Date date, String locale) {
		if (date == null) {
			return null;
		}
		if (locale == null || locale.equals("en_US")) {
			return dateToString(date, SIMPLE_DATE_TIME_FORMAT);
		}

		return dateToString(date, "dd/MM/yyyy hh:mm:ss a");
	}

	/**
	 * @param d
	 * @return
	 */
	public static Date getCurrentUserLocationDateTime(Date d, long timezoneOffset) {
		if (d == null) {
			return null;
		}
		//location's time = current server time - server tz offset + location tz offset		
		long time = d.getTime() - TimeZone.getDefault().getOffset(d.getTime()) + timezoneOffset;
		return new Date(time);
	}
		
	/**
	 * @param d
	 * @return
	 */
	public static Date getCurrentUserLocationDateTime(Date d) {
		return Utility.getCurrentUserLocationDateTime(d, UtilityServiceFactory.getSelectedLocationTimezoneOffset());
	}
		
	/**
	 * @return
	 */
	public static Date getCurrentUserLocationDateTime() {
		return Utility.getCurrentUserLocationDateTime(new Date());
	}
		
	/**
	 * @param date
	 * @return
	 */
	public static Date getAdjustedDateForIncomingDate(Date date) {
		return Utility.getAdjustedDateForTimezone(date, false);
	}
	
	/**
	 * @param date
	 * @return
	 */
	public static Date getAdjustedDateForOutgoingDate(Date date) {
		return Utility.getAdjustedDateForTimezone(date, true);
	}
	
	/**
	 * @param date
	 * @param isOutgoingDate
	 * @return
	 */
	public static Date getAdjustedDateForTimezone(Date date, boolean isOutgoingDate) {
		if (date == null) {
			return null;
		}
		//@TODO Sundar
		long clientServerTimeDiff = 0l;
//		if (FlexContext.getFlexSession() != null && 
//				FlexContext.getFlexSession().getAttribute(Constants.CLIENT_SERVER_TIME_DIFF) != null) {
//			Long val = (Long) FlexContext.getFlexSession().getAttribute(Constants.CLIENT_SERVER_TIME_DIFF);
//			clientServerTimeDiff = val.longValue();
//		}
		if (isOutgoingDate) {
			return new Date(date.getTime() - clientServerTimeDiff);
		}
		return new Date(date.getTime() + clientServerTimeDiff);
	}

	/**
	 * @param object
	 */
	public static void adjustDateForOutgoingObject(java.lang.Object object) {
		Utility.adjustObjectDateForTimezone(object, true);
	}
	
	/**
	 * @param object
	 */
	public static void adjustDateForIncomingObject(java.lang.Object object) {
		Utility.adjustObjectDateForTimezone(object, false);
	}
	
	/**
	 * @param object
	 */
	@SuppressWarnings("unchecked")
	public static void adjustObjectDateForTimezone(java.lang.Object object,
			boolean isOutgoingObject) {
		if (object == null) {
			return;
		} else if (object instanceof List) {
			Iterator iter = ((List) object).iterator();
			while (iter.hasNext()) {
				Utility.adjustObjectDateForTimezone(iter.next(),
						isOutgoingObject);
			}
			return;
		} else if (object instanceof java.util.Date) {
			Utility.getAdjustedDateForTimezone((Date)object, isOutgoingObject);
		}
		try {
			Vector fieldVector = new Vector();
			Class currentClass = object.getClass();
			while (currentClass.equals(java.lang.Object.class) == false) {
				Field[] fields = currentClass.getDeclaredFields();
				for (int i = fields.length - 1; i >= 0; i--) {
					fieldVector.insertElementAt(fields[i], 0);
				}
				currentClass = currentClass.getSuperclass();
			}
			// get all methods
			Method[] methods = object.getClass().getMethods();
			for (int i = 0; i < fieldVector.size(); i++) {
				Field field = (Field) fieldVector.elementAt(i);
				String fieldName = field.getName();
				String fieldType = field.getType().getName();
				String getAccessorName = null;
				String setAccessorName = null;
				getAccessorName = "get"
						+ fieldName.substring(0, 1).toUpperCase()
						+ fieldName.substring(1);
				setAccessorName = "set"
						+ fieldName.substring(0, 1).toUpperCase()
						+ fieldName.substring(1);
				Method accessor = getAccessor(methods, getAccessorName);
				if (accessor != null) {
					Object fieldValue = accessor.invoke(object, new Object[0]);
					if (fieldType.equals(Vector.class.getName())
							|| fieldType.equals(List.class.getName())
							|| fieldType.equals(ArrayList.class.getName())) {
						if (fieldValue != null) {
							Iterator iter = ((List) fieldValue).iterator();
							while (iter.hasNext()) {
								Object obj2 = iter.next();
								if (obj2 instanceof Date) {
									((Date) obj2)
											.setTime(Utility
													.getAdjustedDateForTimezone(
															(Date) obj2,
															isOutgoingObject)
													.getTime());
								} else {
									Utility.adjustObjectDateForTimezone(obj2,
											isOutgoingObject);
								}
							}
						}
					} else if (fieldType.equals(Hashtable.class.getName())
							|| fieldType.equals(Map.class.getName())
							|| fieldType.equals(HashMap.class.getName())) {
						if (fieldValue != null) {
							Iterator iter = ((Map) fieldValue).values()
									.iterator();
							while (iter.hasNext()) {
								Object obj2 = iter.next();
								if (obj2 instanceof Date) {
									((Date) obj2)
											.setTime(Utility
													.getAdjustedDateForTimezone(
															(Date) obj2,
															isOutgoingObject)
													.getTime());
								} else {
									Utility.adjustObjectDateForTimezone(obj2,
											isOutgoingObject);
								}
							}
						}
					} else if (fieldValue != null
							&& fieldValue.getClass().isArray()) {
						Object[] vals = (Object[]) fieldValue;
						for (int j = 0; j < vals.length; j++) {
							Utility.adjustObjectDateForTimezone(vals[i],
									isOutgoingObject);
						}
					} else if (fieldType.equals(Date.class.getName())) {
						if (fieldValue != null) {
							Class[] types = new Class[] {};
							types = new Class[] { Date.class };
							Method method = object.getClass().getMethod(
									setAccessorName, types);
							method.invoke(object,
									new Object[] { Utility
											.getAdjustedDateForTimezone(
													(Date) fieldValue,
													isOutgoingObject) });
						}
					} else if (fieldType.contains(".eapharmics.")) { // container
																		// class
						Utility.adjustObjectDateForTimezone(fieldValue,
								isOutgoingObject);
					}
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param date
	 * @return
	 */
	public static String formatDateTimeForLocale(java.util.Date date) {
		if (date == null) {
			return null;
		}
		//@TODO Sundar
//		if (FlexContext.getFlexSession() != null
//				&& FlexContext.getFlexSession().getAttribute(
//						Constants.USER_ATTRIBUTE) != null) {
//			UserVO userVO = (UserVO) FlexContext.getFlexSession().getAttribute(
//					Constants.USER_ATTRIBUTE);
//			if (userVO.getLocale().equals("en_GB")) {
//				return Utility.dateToString(date, "dd/MM/yyyy hh:mm:ss");
//			}
//		}
		return Utility.dateToString(date);
	}
	
	/**
	 * @param date
	 * @return
	 */
	public static java.util.Date getDateFromStringLocale(String date) {
		if (date == null) {
			return null;
		}
		//@TODO Sundar
//		if (FlexContext.getFlexSession() != null && 
//				FlexContext.getFlexSession().getAttribute(Constants.USER_ATTRIBUTE) != null) {
//			UserVO userVO = (UserVO) FlexContext.getFlexSession().getAttribute(Constants.USER_ATTRIBUTE);
//			if (userVO.getLocale().equals("en_GB")) {
//				return Utility.stringToDate(date, "dd/MM/yyyy hh:mm:ss");
//			}
//		}
		return Utility.stringToDate(date);
	}
	
}