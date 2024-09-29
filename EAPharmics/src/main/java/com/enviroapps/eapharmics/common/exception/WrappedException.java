package com.enviroapps.eapharmics.common.exception;

/**
 * Basic Exception infrastructure class extended by all other Exception classes.
 * Allows for nested <code>Exceptions</code>.
 * 
 * @author EnviroApps
 * @version $Revision: 1.1 $
 */
public abstract class WrappedException extends Exception {

	/**
	 * The throwable nests original throwable.
	 */
	protected Throwable nestedThrowable = null;

	/**
	 * Creates new WrappedException without detail message.
	 */
	public WrappedException() {
		super();
	}

	/**
	 * Constructs a new WrappedException with the specified detail message.
	 * 
	 * @param message
	 *            detailed message
	 */
	public WrappedException(String message) {
		super(message);
	}

	/**
	 * Constructs a new WrappedException with the specified nested throwable.
	 * 
	 * @param e
	 *            nested throwable
	 */
	public WrappedException(Throwable e) {
		super("\n\t NestedThrowable:" + e.getClass().getName() + "\n\t");
		nestedThrowable = e;
	}

	/**
	 * Constructs a new WrappedException with the specified message and nested
	 * throwable.
	 * 
	 * @param message
	 *            detailed message about this throwable
	 * @param e
	 *            throwable that initiated creation of this throwable
	 */
	public WrappedException(String message, Throwable e) {
		super(message);
		nestedThrowable = e;
	}

	/**
	 * Returns the wrapped throwable.
	 * 
	 * @return
	 */
	public Throwable getNestedThrowable() {
		return nestedThrowable;
	}

	/**
	 * Returns the message
	 * 
	 * @return message
	 */
	public String getMessage() {
		StringBuffer sb = new StringBuffer();
		sb.append(super.getMessage());
		if (getNestedThrowable() != null) {
			sb.append(" [ Nested throwable: " + getNestedThrowable() + " ]");
		}
		return sb.toString();
	}

	/**
	 * Overridden to print the nested detail throwable's stack trace in addition
	 * to this one's.
	 */
	public void printStackTrace() {
		super.printStackTrace();
		if (getNestedThrowable() != null) {
			synchronized (System.err) {
				System.err.print("\n\tNested throwable stack trace:\n\t");
				getNestedThrowable().printStackTrace();
			}
		}
	}

	/**
	 * Overridden to print the nested detail throwable's stack trace in addition
	 * to this one's.
	 * 
	 * @param stream
	 *            print stream
	 */
	public void printStackTrace(java.io.PrintStream stream) {
		super.printStackTrace(stream);
		if (getNestedThrowable() != null) {
			synchronized (stream) {
				stream.print("\n\tNested throwable stack trace:\n\t");
				getNestedThrowable().printStackTrace(stream);
			}
		}
	}

	/**
	 * Overridden to print the nested detail throwable's stack trace in addition
	 * to this one's.
	 * 
	 * @param writer
	 *            print writer
	 */
	public void printStackTrace(java.io.PrintWriter writer) {
		super.printStackTrace(writer);
		if (getNestedThrowable() != null) {
			synchronized (writer) {
				writer.print("\n\tNested throwable stack trace:\n\t");
				getNestedThrowable().printStackTrace(writer);
			}
		}
	}

	/**
	 * Simple setter for the throwablethat initiated creation of this
	 * Persistence throwable.
	 * 
	 * @param anThrowable
	 *            The new detailThrowable value
	 */
	private void setNestedThrowable(Throwable e) {
		nestedThrowable = e;
	}

	/**
	 * Get the message from a nested throwable.
	 * 
	 * @return the message from the nested throwable
	 */
	public String getOriginalMessage() {
		Throwable t = nestedThrowable;
		String message = getMessage();
		while (t != null) {
			message = t.getMessage();
			if (t instanceof WrappedException) {
				WrappedException e = (WrappedException) t;
				t = e.getNestedThrowable();
			} else if (t instanceof WrappedRuntimeException) {
				WrappedRuntimeException e = (WrappedRuntimeException) t;
				t = e.getNestedThrowable();
			} else {
				break;
			}
		}
		return message;
	}
}