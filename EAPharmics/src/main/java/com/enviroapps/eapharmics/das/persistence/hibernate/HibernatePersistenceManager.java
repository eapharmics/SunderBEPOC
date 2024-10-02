package com.enviroapps.eapharmics.das.persistence.hibernate;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.enviroapps.eapharmics.common.ObfuscationUtil;
import com.enviroapps.eapharmics.common.services.UtilityServiceFactory;
import com.enviroapps.eapharmics.das.persistence.PersistenceManager;
import com.enviroapps.eapharmics.das.persistence.config.hibernate.HibernatePersistenceConfig;

/**
 * abstract Persistence strategy class for managing the Toplink Session
 * and Session broker. It also handles transactions if JTS is enabled.
 * 
 * @author EnviroApps
 * @version $Revision: 1.1 $
 */
public class HibernatePersistenceManager implements PersistenceManager {
	
	private static final Logger logger = LoggerFactory.getLogger(HibernatePersistenceManager.class);

	private static HibernatePersistenceManager instance;// = new HibernatePersistenceManager();

	/**
	 * Hibernate SessionFactory.
	 */
	protected SessionFactory sessionFactory;
	
	/**
	 * Thread local to hold unit of work when JTS is disabled.
	 */
	private ThreadLocal currentUnitOfWorks = new ThreadLocal();
	
	/**
	 * Local Transaction
	 */
	private Transaction trans;

	/**
	 * True if external transaction controller is used. By default it's set to
	 * FALSE. It can be controlled by setting the value of
	 * <eapharmics.dataaccess.use.external.txn.controller> key in the
	 * DataAccess property file
	 */
	private boolean useExternalTransactionController = false;

	/**
	 * Class for loading Persistence related configuration files
	 */
	protected HibernatePersistenceConfig persistenceConfig = new HibernatePersistenceConfig();	

	/**
	 * Default constructor that intializes all the persistence related
	 * parameters with values by reading ORM XMLs and dataaccess.properties
	 * property file to establish a connection to the database.
	 * 
	 */
	private HibernatePersistenceManager() {

		// Loading all the persistence realted configuration
		// details from the <dataaccess> property file
		preInit();

		// Create a ORM Session using PersistenceManager

		init();

		// blank implementation
		postInit();

		// Connect to the database by using login details
		// defined in the orm session description
		login();

		// logging persistence initialization
		logger.info("init", "Persistence manager is initialized");
	}

	public Session getCurrentSession() {
		if (useExternalTransactionController) {
			return sessionFactory.getCurrentSession();
		}
		
		Session session = (Session)currentUnitOfWorks.get();
		if (session == null) {
			session = getClientSession();
		}
		return session;
	}
	
	/**
	 * Acquires and returns a client session broker.
	 *
	 * @return SessionBroker client session broker
	 */
	protected Session getClientSession() {
		return sessionFactory.openSession();
	}

	/**
	 * @return open and return a session
	 */
	public Session openSession() {
		return sessionFactory.openSession();
	}
	
	/**
	 * closes the session factory
	 */
	public void closeSession(){
		if (sessionFactory.getCurrentSession() != null) {
			sessionFactory.getCurrentSession().close();	
		}
	}
		
	/**
	 * Depending on the External Transaction Controller setting on the property
	 * file, begins a transaction. Used only when JTS is disabled.
	 */
	public void beginTransaction() {
		Session session = instance.getClientSession();
		currentUnitOfWorks.set(session);
		/*
		 * Cannot begin a External transaction i.e JTS. Only local transaction
		 * can be set to begin a transaction.
		 */
		if (useExternalTransactionController) return;
		trans = session.beginTransaction();
	}

	/**
	 * Depending on the External Transaction Controller setting on the property
	 * file, Commits a transaction. Used only when JTS is disabled.
	 */
	public void commitTransaction() {
		/*
		 * Cannot commit a External transaction i.e JTS. Only capable of
		 * committing the local transaction.
		 */
		if (useExternalTransactionController) return;
		
		Session session = (Session)currentUnitOfWorks.get();
		if (session != null) {
//			session.flush();
			trans.commit();
			if (session.isDirty()) {
				logger.debug("commitTransaction", " session is dirty.");
				session.flush();
			} else
				logger.debug("commitTransaction", "session is clean.");
			session.close();
			currentUnitOfWorks.set(null);
			trans = null;
		}
	}

	/**
	 * Depending on the External Transaction Controller setting on the property
	 * file, make a rollback transaction decision. Used only when JTS is
	 * disabled.
	 */
	public void rollbackTransaction() {
		/*
		 * Cannot rollback a External transaction i.e JTS. Only capable of
		 * rolling back the local transaction.
		 */
		if (useExternalTransactionController) return;
		
		/*
		 * Make sure that Unit Of Work is not NULL before releasing it for the
		 * transaction rollback.
		 */
		Session session = (Session)currentUnitOfWorks.get();
		if (session != null) {
			trans.rollback();
			session.close();
			currentUnitOfWorks.set(null);
			trans = null;
		}
	}

	/**
	 * Initialize the persistence manager by loading the Hibernate XML
	 * configuration file and initializing the Hibernate Session. Sets the
	 * External Transaction Controller if it's set to true i.e. JTS is enabled
	 */
	public void init() {
		logger.info("init", "Persistence manager is being initialized");

		// customization of the hibernate cfg properties are stored here.
		Properties custom = new Properties();

		try {
			// Get the JTS option from config
			useExternalTransactionController = persistenceConfig
					.isUseExternalTransactionController();
			/*
			 * Load the external transaction controller class specified in the
			 * dataaccess properties file. Get the instance of controller to be
			 * used by the Hibernate session.
			 */
			if (useExternalTransactionController) {
				custom.put("hibernate.transaction.factory_class",
						persistenceConfig
								.getExternalTransactionControllerClassName());
			}

			/*
			 * Load the Hibernate configuration from the classpath.
			 */
			if (persistenceConfig.getSessionXmlFile() != null) {

				Configuration cfg = new Configuration().configure(persistenceConfig.getSessionXmlFile());
				String encPassword = cfg.getProperty("connection.password");
				//@TODO Sundar
//				byte[] val = ObfuscationUtil.fromHexString(encPassword);
//				ObfuscationUtil util = new ObfuscationUtil(ObfuscationUtil.ENCRYPTION_KEY);
//				String decryptedPassword = new String(util.decrypt(val));
				String decryptedPassword = encPassword;
				cfg.setProperty("connection.password", decryptedPassword.trim());
				cfg.setProperty("hibernate.connection.password", decryptedPassword.trim());
				cfg.setNamingStrategy(ImprovedNamingStrategy.INSTANCE);
				cfg.addProperties(custom);
				sessionFactory = cfg.buildSessionFactory();
			}
		} catch (Exception e) {
			logger.info("init", "ERROR: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * login to the hibernate session
	 */
	public void login() {
		// sessionBroker.login();
	}

	/**
	 * Accessor method that returns Persistence Manager instance
	 * 
	 * @return instance of DefaultPersistenceManager
	 */
	public static HibernatePersistenceManager getInstance() {

		// Singleton Pattern :
		// Making sure by cheking NULL that Only one
		// instance of Hibernate Persistence Manager is
		// created for the application.
		if (instance == null) {
			instance = new HibernatePersistenceManager();
		}
		return instance;
	}

	/**
	 * Loading the Persistence configuration information before initializing the
	 * Persistence object.
	 */
	public void preInit() {
		// Boolean value whether to use External Transaction controller or not
		persistenceConfig
				.setUseExternalTransactionController(false);

		// Class name to register a listener to the externally controlled
		// transaction.
		persistenceConfig
				.setExternalTransactionControllerClassName(null);

		// Hibernate session xml file name to be loaded
		persistenceConfig.setSessionXmlFile("hibernate.cfg.xml");

		// Hibernate Session Broker name -- not currently used.
		persistenceConfig.setSessionBrokerName(null);

		logger.debug("preInit", persistenceConfig.getSessionXmlFile() + " " + persistenceConfig.getSessionXmlFile());

	}

	// Not implemented
	public void postInit() {

	}
	
	public static void main(String[] args) throws Exception {
		byte[] val = ObfuscationUtil.fromHexString("8bea7e9296891e185d7a0a945beb347b");
		ObfuscationUtil util = new ObfuscationUtil(ObfuscationUtil.ENCRYPTION_KEY);
		String decryptedPassword = new String(util.decrypt(val));	
		System.out.println(decryptedPassword);
	}

}