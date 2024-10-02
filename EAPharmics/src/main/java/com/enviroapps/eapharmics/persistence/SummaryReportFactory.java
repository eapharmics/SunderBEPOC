package com.enviroapps.eapharmics.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.enviroapps.eapharmics.das.persistence.DataAccessConstants;
import com.enviroapps.eapharmics.das.persistence.hibernate.HibernatePersistenceFactory;
import com.enviroapps.eapharmics.das.persistence.hibernate.HibernatePersistenceManager;
import com.enviroapps.eapharmics.das.persistence.hibernate.HibernatePersistenceManagerFactory;

public class SummaryReportFactory extends HibernatePersistenceFactory implements DataAccessConstants {

	private static final Logger logger = LoggerFactory.getLogger(SummaryReportFactory.class);

	private SummaryReportFactory() {
		persistenceManager = (HibernatePersistenceManager) HibernatePersistenceManagerFactory
				.getInstance().getPersistenceManager();
		logger.debug("SummaryReportFactory", persistenceManager);
	}

	private static SummaryReportFactory instance = new SummaryReportFactory();

	public static SummaryReportFactory getInstance() {
		return instance;
	}

}
