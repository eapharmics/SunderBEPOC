package com.enviroapps.eapharmics.persistence;

import com.enviroapps.eapharmics.common.services.UtilityServiceFactory;
import com.enviroapps.eapharmics.common.services.logging.ILogger;
import com.enviroapps.eapharmics.das.persistence.DataAccessConstants;
import com.enviroapps.eapharmics.das.persistence.hibernate.HibernatePersistenceFactory;
import com.enviroapps.eapharmics.das.persistence.hibernate.HibernatePersistenceManager;
import com.enviroapps.eapharmics.das.persistence.hibernate.HibernatePersistenceManagerFactory;

public class SummaryReportFactory extends HibernatePersistenceFactory implements DataAccessConstants {

	private static ILogger log = UtilityServiceFactory.getLogger();

	private SummaryReportFactory() {
		persistenceManager = (HibernatePersistenceManager) HibernatePersistenceManagerFactory
				.getInstance().getPersistenceManager();
		log.debug(this, "SummaryReportFactory", persistenceManager);
	}

	private static SummaryReportFactory instance = new SummaryReportFactory();

	public static SummaryReportFactory getInstance() {
		return instance;
	}

}
