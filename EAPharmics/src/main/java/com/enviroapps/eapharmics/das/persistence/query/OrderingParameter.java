package com.enviroapps.eapharmics.das.persistence.query;

import com.enviroapps.eapharmics.common.Utility;

/**
 * Holds query parameters that define the result set ordering for a query to the
 * <code>PersistenceManager</code>.
 *
 * @author EnviroApps
 * @version $Revision$
 */
public class OrderingParameter {

	private String orderingParameterName;

	private boolean descending = false;

	/**
	 * Constructor for OrderingParameter.
	 */
	public OrderingParameter() {
		super();
	}

	public OrderingParameter(String orderingParameterName) {
		super();
		this.orderingParameterName = orderingParameterName;
	}

	public OrderingParameter(String orderingParameterName, boolean descending) {
		super();
		this.orderingParameterName = orderingParameterName;
		this.descending = descending;
	}

	public String toString() {
		return Utility.toString(this);
	}

	/**
	 * Returns the orderingParameterName.
	 *
	 * @return String
	 */
	public String getOrderingParameterName() {
		return orderingParameterName;
	}

	/**
	 * Sets the orderingParameterName.
	 *
	 * @param orderingParameterName
	 *            The orderingParameterName to set
	 */
	public void setOrderingParameterName(String orderingParameterName) {
		this.orderingParameterName = orderingParameterName;
	}

	/**
	 * Returns the descending.
	 *
	 * @return Boolean
	 */
	public boolean isDescending() {
		return descending;
	}

	/**
	 * Sets the descending.
	 *
	 * @param descending
	 *            The descending to set
	 */
	public void setDescending(boolean descending) {
		this.descending = descending;
	}
}
