package com.enviroapps.eapharmics.persistence;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.enviroapps.eapharmics.bom.security.AppAccessTemplate;
import com.enviroapps.eapharmics.bom.security.AppArea;
import com.enviroapps.eapharmics.bom.security.AppModule;
import com.enviroapps.eapharmics.bom.security.AppTemplateModuleAccess;
import com.enviroapps.eapharmics.bom.security.AppUser;
import com.enviroapps.eapharmics.bom.security.AppUserAudit;
import com.enviroapps.eapharmics.bom.security.AppUserModuleAccess;
import com.enviroapps.eapharmics.bom.security.EditReason;
import com.enviroapps.eapharmics.bom.security.ModuleAccessAudit;
import com.enviroapps.eapharmics.bom.security.ProductRegistration;
import com.enviroapps.eapharmics.common.Utility;
import com.enviroapps.eapharmics.das.persistence.DataAccessConstants;
import com.enviroapps.eapharmics.das.persistence.hibernate.HibernatePersistenceFactory;
import com.enviroapps.eapharmics.das.persistence.hibernate.HibernatePersistenceManager;
import com.enviroapps.eapharmics.das.persistence.hibernate.HibernatePersistenceManagerFactory;
import com.enviroapps.eapharmics.vo.security.ProductRegistrationVO;

/**
 * @author EnviroApps
 *
 */
/**
 * @author Ramya
 * 
 */
public class SecurityFactory extends HibernatePersistenceFactory implements
                                                                DataAccessConstants
{

	private static final Logger logger = LoggerFactory.getLogger(SecurityFactory.class);

   private SecurityFactory()
   {
      persistenceManager = (HibernatePersistenceManager) HibernatePersistenceManagerFactory.getInstance().getPersistenceManager();
      logger.debug("SecurityFactory", persistenceManager);
   }

   private static SecurityFactory instance = new SecurityFactory();

   public static SecurityFactory getInstance()
   {
      return instance;
   }

   /**
    * @param userName
    * @return
    */
   public AppUser getUser(String userName)
   {
      Session session = persistenceManager.getCurrentSession();
      List users = session.createCriteria(AppUser.class).add(Restrictions.eq("userName",
                                                                             userName)).list();
      if(users != null && users.size() > 0)
      {
         return (AppUser) users.get(0);
      }
      return null;
   }

   public AppUser getUser(Long userappUserId)
   {
      Session session = persistenceManager.getCurrentSession();
      List users = session.createCriteria(AppUser.class).add(Restrictions.eq("appUserId",
                                                                             userappUserId)).list();
      if(users != null && users.size() > 0)
      {
         return (AppUser) users.get(0);
      }
      return null;
   }

   /**
    * @param user
    */
   public void createUser(AppUser user)
   {
      super.create(user);
   }

   /**
    * @param user
    */
   public void updateUser(AppUser user)
   {
      super.store(user);
   }

   /**
    * @param user
    */
   public Long createAppUserAudit(Long appUserId, String userName, boolean loginSuccess, String reason, Long currentLocationId)
   {
      AppUserAudit audit = new AppUserAudit();
      Session session = persistenceManager.getCurrentSession();
      BigDecimal appUserAuditId = (BigDecimal) session.createSQLQuery("select SEC_SEQ.nextval from dual").uniqueResult();
      audit.setAppUserAuditId(new Long(appUserAuditId.longValue()));
      audit.setAppUserId(appUserId);
      audit.setUserName(userName);
      audit.setLoginSuccess(loginSuccess);
      audit.setLoginTime(Utility.getCurrentUserLocationDateTime());
      audit.setReason(reason);
      audit.setLocationId(currentLocationId);
      super.store(audit);
      return audit.getAppUserAuditId();
   }

   /**
    * @param appUserId
    * @return
    */
   public List getAppUserAuditForUser(Long appUserId)
   {
      Session session = persistenceManager.getCurrentSession();
      List list = session.createCriteria(AppUserAudit.class)
      		.add(Restrictions.eq("appUserId", appUserId))
      		.add(Restrictions.eq("locationId", super.getCurrentLocationId()))
      		.list();
      return list;
   }

   /**
    * @param appUserId
    * @return
    */
   public List getAppUserModuleAccessForUser(Long appUserId)
   {
      Session session = persistenceManager.getCurrentSession();
      String sql = "SELECT AUM.APP_USER_MODULE_ACCESS_ID, AA.AREA_DESCRIPTION, AM.MODULE_DESCRIPTION, AUM.ACCESS_YN, AM.APP_MODULE_ID "
                   + "FROM EA_APP_AREA AA, EA_APP_MODULE AM, EA_APP_USER_MODULE_ACCESS AUM "
                   + "WHERE AUM.APP_MODULE_ID = AM.APP_MODULE_ID AND "
                   +" AUM.APP_USER_ID = "+ appUserId 
                   + " AND AM.APP_AREA_ID = AA.APP_AREA_ID "
                   + "ORDER BY AA.AREA_DESCRIPTION, AM.MENU_ORDER";
      SQLQuery q = persistenceManager.getCurrentSession().createSQLQuery(sql);

      q.addScalar("APP_USER_MODULE_ACCESS_ID", Hibernate.LONG);
      q.addScalar("AREA_DESCRIPTION", Hibernate.STRING);
      q.addScalar("MODULE_DESCRIPTION", Hibernate.STRING);
      q.addScalar("ACCESS_YN", Hibernate.STRING);
      q.addScalar("APP_MODULE_ID", Hibernate.LONG);
      
      List list = q.list();
      return list;
   }

   /**
    * @param appUserId
    * @return
    */
   public List getAllAppUserModuleAccessForUser(Long appUserId)
   {
      Session session = persistenceManager.getCurrentSession();
      List list = session.createCriteria(AppUserModuleAccess.class)
      	.add(Restrictions.eq("appUserId", appUserId)).list();
      return list;
   }

   /**
    * @return
    */
   public List < AppModule > getAppModules()
   {
      Session session = persistenceManager.getCurrentSession();
      List list = session.createCriteria(AppModule.class).addOrder(Order.asc("moduleDescription")).addOrder(Order.asc("menuOrder")).list();
      return list;
   }

   /**
    * @return
    */
   public List < AppModule > getAppModulesForAppUser(Long appUserId)
   {
      Session session = persistenceManager.getCurrentSession();
      List list = session.createCriteria(AppModule.class).add(Restrictions.eq("appUserId",
                                                                              appUserId)).addOrder(Order.asc("appUserId")).list();
      return list;
   }

   /**
    * @return
    */
   public List < AppArea > getAppAreas()
   {
      Session session = persistenceManager.getCurrentSession();
      List list = session.createCriteria(AppArea.class).addOrder(Order.asc("areaDescription")).list();
      return list;
   }

   /**
    * @return
    */
   public List < AppUser > getAllUser()
   {
      Session session = persistenceManager.getCurrentSession();
      Criteria criteria = session.createCriteria(AppUser.class);
      List list = criteria.list();
      return list;
   }
   
   /**
    * @return
    */
   public List < AppModule > getAllModules()
   {
      Session session = persistenceManager.getCurrentSession();
      Criteria criteria = session.createCriteria(AppModule.class);
      List list = session.createCriteria(AppModule.class).addOrder(Order.asc("appAreaId")).list();
      //List list = criteria.list();
      return list;
   }

   /**
    * @param accessuser
    */
   public void updateAccess(AppUserModuleAccess accessuser)
   {
      Session session = persistenceManager.getCurrentSession();
      session.saveOrUpdate(accessuser);

   }

   /**
    * @param appUserId
    * @return
    */
   public List getAppAccessModuleId(Long appUserId)
   {
      Session session = persistenceManager.getCurrentSession();
      String sql = "SELECT APP_USER_MODULE_ACCESS_ID,APP_MODULE_ID,ACCESS_YN "
                   + " FROM EA_APP_USER_MODULE_ACCESS WHERE  APP_USER_ID = "
                   + appUserId
                   + " ORDER BY APP_USER_MODULE_ACCESS_ID";
      SQLQuery q = persistenceManager.getCurrentSession().createSQLQuery(sql);
      List list = q.list();
      return list;
   }

   /**
    * @param appuser
    */
   public void createUserM(AppUser appuser)
   {
      super.create(appuser);
   }
   /**
    * @return
    */
   /**
    * @return
    */
   public List getAllAppAccessTemplates() {
      Session session = persistenceManager.getCurrentSession();
      List list = session.createCriteria(AppAccessTemplate.class)
               .list();
      return list;
   }

   /**
    * @param eaAppAccessTemplateId
    * @return
    */
   public AppAccessTemplate getAppAccessTemplate(Long eaAppAccessTemplateId) {
      Session session = persistenceManager.getCurrentSession();
      AppAccessTemplate appAccessTemplate = (AppAccessTemplate) session.load(AppAccessTemplate.class, eaAppAccessTemplateId);
      return appAccessTemplate;
   }

   /**
    * @param appAccessTemplate
    */
   public void createAppAccessTemplate(AppAccessTemplate appAccessTemplate) {
      Session session = persistenceManager.getCurrentSession();
      session.saveOrUpdate(appAccessTemplate);
   }

   /**
    * @param appAccessTemplate
    */
   public void updateAppAccessTemplate(AppAccessTemplate appAccessTemplate) {
      Session session = persistenceManager.getCurrentSession();
      session.saveOrUpdate(appAccessTemplate);
   }

   /**
    * @param appAccessTemplateId
    */
   public void deleteAppAccessTemplate(Long appAccessTemplateId) {
      Session session = persistenceManager.getCurrentSession();
      AppAccessTemplate classVariable = (AppAccessTemplate) session.get(AppAccessTemplate.class, appAccessTemplateId);
      session.delete(classVariable);
   }

	/**
	 * @return
	 */
	public List getAllEditReasons() {
		Session session = persistenceManager.getCurrentSession();
		List list = session.createCriteria(EditReason.class)
					.list();
		return list;
	}

	/**
	 * @param eaEditReasonId
	 * @return
	 */
	public EditReason getEditReason(Long eaEditReasonId) {
		Session session = persistenceManager.getCurrentSession();
		EditReason editReason = (EditReason) session.load(EditReason.class, eaEditReasonId);
		return editReason;
	}

	/**
	 * @param editReason
	 */
	public void createEditReason(EditReason editReason) {
		Session session = persistenceManager.getCurrentSession();
		editReason.setLocationId(super.getCurrentLocationId());
		session.saveOrUpdate(editReason);
	}

	/**
	 * @param editReason
	 */
	public void updateEditReason(EditReason editReason) {
		Session session = persistenceManager.getCurrentSession();
		session.saveOrUpdate(editReason);
	}

	/**
	 * @param editReasonId
	 */
	public void deleteEditReason(Long editReasonId) {
		Session session = persistenceManager.getCurrentSession();
		EditReason classVariable = (EditReason) session.get(EditReason.class, editReasonId);
		session.delete(classVariable);
	}
	
   /**
    * @param appUserId
    * @return
    */
   public List getAppTemplateModuleAccessForTemplate(Long appAccessTemplateId)
   {
      Session session = persistenceManager.getCurrentSession();
      String sql = "SELECT AUM.APP_TEMPLATE_MODULE_ACCESS_ID, AA.AREA_DESCRIPTION, AM.MODULE_DESCRIPTION, AUM.ACCESS_YN,AM.APP_MODULE_ID "
                   + "FROM EA_APP_AREA AA, EA_APP_MODULE AM, EA_APP_TEMPLATE_MODULE AUM "
                   + "WHERE AUM.APP_MODULE_ID = AM.APP_MODULE_ID AND "
                   +" AUM.APP_ACCESS_TEMPLATE_ID = "+ appAccessTemplateId 
                   + " AND AM.APP_AREA_ID = AA.APP_AREA_ID "
                   + "ORDER BY AA.AREA_DESCRIPTION, AM.MENU_ORDER";
      SQLQuery q = persistenceManager.getCurrentSession().createSQLQuery(sql);
      q.addScalar("APP_TEMPLATE_MODULE_ACCESS_ID", Hibernate.LONG);
      q.addScalar("AREA_DESCRIPTION", Hibernate.STRING);
      q.addScalar("MODULE_DESCRIPTION", Hibernate.STRING);
      q.addScalar("ACCESS_YN", Hibernate.STRING);
      q.addScalar("APP_MODULE_ID",Hibernate.LONG);
      List list = q.list();
      return list;
   }
   public List getAllAppTemplateModuleAccessForTemplate(Long appAccessTemplateId)
   {
      Session session = persistenceManager.getCurrentSession();
      List list = session.createCriteria(AppTemplateModuleAccess.class)
         .add(Restrictions.eq("appAccessTemplateId", appAccessTemplateId)).list();
      return list;
   }
   
   /**
    * @param accessuser
    */
   public void updateTemplateAccess(AppTemplateModuleAccess accessTemplate)
   {
      Session session = persistenceManager.getCurrentSession();
      session.saveOrUpdate(accessTemplate);

   }
   
   /**
    * @param userName
    * @return
    */
   public AppAccessTemplate getTemplate(String templateName)
   {
      Session session = persistenceManager.getCurrentSession();
      List templates = session.createCriteria(AppAccessTemplate.class).add(Restrictions.eq("templateName",
                                                                             templateName)).list();
      if(templates != null && templates.size() > 0)
      {
         return (AppAccessTemplate) templates.get(0);
      }
      return null;
   }

	/**
	 * @return
	 */
	public List getAllModuleAccessAudits() {
		Session session = persistenceManager.getCurrentSession();
		List list = session.createCriteria(ModuleAccessAudit.class)
			.add(Restrictions.eq("locationId", super.getCurrentLocationId()))
			.list();
		return list;
	}

	/**
	 * @param eaModuleAccessAuditId
	 * @return
	 */
	public ModuleAccessAudit getModuleAccessAudit(Long eaModuleAccessAuditId) {
		Session session = persistenceManager.getCurrentSession();
		ModuleAccessAudit moduleAccessAudit = (ModuleAccessAudit) session.load(ModuleAccessAudit.class, eaModuleAccessAuditId);
		return moduleAccessAudit;
	}

	/**
	 * @param eaModuleAccessAuditId
	 * @return
	 */
	public List getModuleAccessAudits(Date fromDate, Date toDate, List userIds, List moduleIds) {
		Session session = persistenceManager.getCurrentSession();
		Criteria crit = session.createCriteria(ModuleAccessAudit.class)
					.add(Restrictions.eq("locationId", super.getCurrentLocationId()));
		if (fromDate != null) {
			fromDate = Utility.stripTime(fromDate);
			crit = crit.add(Restrictions.ge("accessTime", fromDate));
		}
		if (toDate != null) {
			toDate = Utility.getEndOfDayTime(toDate);
			crit = crit.add(Restrictions.ge("accessTime", toDate));
		}
		if (userIds != null && userIds.size() > 0) {
			crit = crit.add(Restrictions.in("appUserId", userIds));
		}
		if (moduleIds != null && moduleIds.size() > 0) {
			crit = crit.add(Restrictions.in("appModuleId", moduleIds));
			crit.addOrder(Order.asc("appModuleId"));
		}
		crit.addOrder(Order.desc("accessTime"));
		
		return crit.list();
	}

	/**
	 * @param moduleAccessAudit
	 */
	public void createModuleAccessAudit(ModuleAccessAudit moduleAccessAudit) {
		Session session = persistenceManager.getCurrentSession();
		moduleAccessAudit.setLocationId(super.getCurrentLocationId());
		session.saveOrUpdate(moduleAccessAudit);
	}

	/**
	 * @param moduleAccessAudit
	 */
	public void updateModuleAccessAudit(ModuleAccessAudit moduleAccessAudit) {
		Session session = persistenceManager.getCurrentSession();
		session.saveOrUpdate(moduleAccessAudit);
	}

	/**
	 * @param moduleAccessAuditId
	 */
	public void deleteModuleAccessAudit(Long moduleAccessAuditId) {
		Session session = persistenceManager.getCurrentSession();
		ModuleAccessAudit classVariable = (ModuleAccessAudit) session.get(ModuleAccessAudit.class, moduleAccessAuditId);
		session.delete(classVariable);
	}

	/**
	 * @param moduleName
	 * @return
	 */
	public List<String> getEmailIdsForFunctionAccess(String moduleName, Long locationId) {
		Session session = persistenceManager.getCurrentSession();
		List<String> list = new ArrayList<String>();
		String sql = "select distinct u.email ";
		sql = sql + "from EA_APP_USER_MODULE_ACCESS a, EA_APP_MODULE m, EA_APP_USER u, EA_APP_USER_LOCATIONS l ";
		sql = sql + "where a.app_module_id = m.app_module_id ";
		sql = sql + "and a.app_user_id = u.app_user_id ";
		sql = sql + "and a.app_user_id = l.app_user_id ";
		sql = sql + "and l.location_id = " + locationId + " ";
		sql = sql + "and a.access_yn = 'Y' "; 
		sql = sql + "and m.module_description = '" + moduleName + "'";
		SQLQuery q = persistenceManager.getCurrentSession().createSQLQuery(sql);
		q.addScalar("EMAIL", Hibernate.STRING);
	      
	    List list1 = q.list();
	    for (Iterator<String> iterator = list1.iterator(); iterator.hasNext();) {
			list.add((String) iterator.next());
		}
		return list;
	}
	
	/**
	 * @return
	 */
	public List getAllProductRegistrations() {
		Session session = persistenceManager.getCurrentSession();
		List list = session.createCriteria(ProductRegistration.class).list();
		return list;
	}

	/**
	 * @param eaProductRegistrationId
	 * @return
	 */
	public ProductRegistration getProductRegistration(Long eaProductRegistrationId) {
		Session session = persistenceManager.getCurrentSession();
		ProductRegistration productRegistration = (ProductRegistration) session.load(ProductRegistration.class, eaProductRegistrationId);
		return productRegistration;
	}

	/**
	 * @param productRegistration
	 */
	public void createProductRegistration(ProductRegistration productRegistration) {
		Session session = persistenceManager.getCurrentSession();
		session.saveOrUpdate(productRegistration);
	}

	/**
	 * @param productRegistration
	 */
	public void updateProductRegistration(ProductRegistration productRegistration) {
		Session session = persistenceManager.getCurrentSession();
		session.saveOrUpdate(productRegistration);
	}

	/**
	 * @param licenseKey
	 */
	public void deleteProductRegistration(Long licenseKey) {
		Session session = persistenceManager.getCurrentSession();
		ProductRegistration classVariable = (ProductRegistration) session.get(ProductRegistration.class, licenseKey);
		session.delete(classVariable);
	}
	
	/**
	 * @param voObject
	 * @return
	 */
	public String getEncryptedLicense(ProductRegistrationVO voObject) {
		List licenseInfoResult = persistenceManager.getCurrentSession()
		.createSQLQuery("SELECT security_pkg.set_license_info (?) LICENSE_INFO FROM dual " )
		.addScalar("LICENSE_INFO", Hibernate.STRING)
		.setString(0, voObject.getLicenseKey()).list();
		return (String) licenseInfoResult.get(0);
	}

	public String getLicenseInfo(String licenseKey) {
		List licenseInfoResult = persistenceManager.getCurrentSession()
        .createSQLQuery("SELECT security_pkg.get_license_info (?) LICENSE_INFO " +
      		  "  FROM ea_product_registration " )
        .addScalar("LICENSE_INFO", Hibernate.STRING)
        .setString(0, licenseKey.replace("-", "")).list();
		if (licenseInfoResult != null && licenseInfoResult.size() > 0) {
			return ((String) licenseInfoResult.get(0));
		}
		return null;
	}
	
	public String getEncryptedLicenseInfo(String key) {
		List licenseInfoResult = HibernatePersistenceManager.getInstance().openSession()
        .createSQLQuery("SELECT security_pkg.set_license_info (?) LICENSE_INFO " +
      		  "  FROM dual " )
        .addScalar("LICENSE_INFO", Hibernate.STRING)
        .setString(0, key).list();
		if (licenseInfoResult != null && licenseInfoResult.size() > 0) {
			return ((String) licenseInfoResult.get(0));
		}
		return null;
	}
	
}
