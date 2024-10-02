package com.enviroapps.eapharmics.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.enviroapps.eapharmics.bom.admin.ApplParameter;
import com.enviroapps.eapharmics.bom.admin.Location;
import com.enviroapps.eapharmics.bom.security.AppAccessTemplate;
import com.enviroapps.eapharmics.bom.security.AppModule;
import com.enviroapps.eapharmics.bom.security.AppTemplateModuleAccess;
import com.enviroapps.eapharmics.bom.security.AppUser;
import com.enviroapps.eapharmics.bom.security.AppUserLocation;
import com.enviroapps.eapharmics.bom.security.AppUserModuleAccess;
import com.enviroapps.eapharmics.bom.security.EditReason;
import com.enviroapps.eapharmics.bom.security.ModuleAccessAudit;
import com.enviroapps.eapharmics.bom.security.ProductRegistration;
import com.enviroapps.eapharmics.common.Utility;
import com.enviroapps.eapharmics.das.persistence.hibernate.HibernatePersistenceManager;
import com.enviroapps.eapharmics.exception.EAPharmicsException;
import com.enviroapps.eapharmics.persistence.AdminFactory;
import com.enviroapps.eapharmics.persistence.SecurityFactory;
import com.enviroapps.eapharmics.ui.Constants;
import com.enviroapps.eapharmics.vo.security.AppUserLocationVO;
import com.enviroapps.eapharmics.vo.security.EditReasonVO;
import com.enviroapps.eapharmics.vo.security.ModuleAccessAuditVO;
import com.enviroapps.eapharmics.vo.security.ProductRegistrationVO;
import com.enviroapps.eapharmics.vo.security.TemplateAccessAreaVO;
import com.enviroapps.eapharmics.vo.security.TemplateAccessModuleVO;
import com.enviroapps.eapharmics.vo.security.TemplateVO;
import com.enviroapps.eapharmics.vo.security.UserAccessAreaVO;
import com.enviroapps.eapharmics.vo.security.UserAccessModuleVO;
import com.enviroapps.eapharmics.vo.security.UserVO;

import jakarta.servlet.http.HttpSession;

//import flex.messaging.FlexContext;
//import flex.messaging.FlexSession;
//import jakarta.servlet.http.HttpSession;

/**
 * @author EnviroApps
 * 
 */
public class SecurityImpl extends AbstractServiceImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityImpl.class);

	private SecurityFactory securityFactory = SecurityFactory.getInstance();
	private AdminFactory adminFactory = AdminFactory.getInstance();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.enviroapps.eapharmics.bom.services.SecurityInterface#authenticate
	 * (java.lang.String, java.lang.String)
	 */
	public UserVO authenticate(String userName, String password)
			throws EAPharmicsException {

		AppUser user = securityFactory.getUser(userName);
		if (user == null) {
			createAppUserAudit(null, userName, false, "Invalid User Id and/or password combination");
			throw new EAPharmicsException(
					"Invalid User Id and/or password combination");
		}
		//Get the invalid count parameter and use that here		
		ApplParameter parameter = adminFactory.getApplParameterByName(Constants.INVALID_LOGIN_COUNT);
		int invalidLoginCount = 3;
		if (parameter != null) {
			try {
				invalidLoginCount = Integer.parseInt(parameter.getParameterValue());
			} catch (NumberFormatException e) {
				logException("authenticate", e);
			}
		}
		if (user.getInvalidLoginCount() >= invalidLoginCount) {
			createAppUserAudit(null, userName, false, "Unable to login. User Id is locked.", user.getDefaultLocationId());
			throw new EAPharmicsException("Unable to login. User Id is locked.");
		}
		if (user.getIsActive() == false) {
			createAppUserAudit(null, userName, false, "Unable to login. User Id is not Active.", user.getDefaultLocationId());
			throw new EAPharmicsException(
					"Unable to login. User Id is not Active.");
		}

		if (user.getDefaultLocationId() == null || user.getDefaultLocationId().longValue() == 0) {
			createAppUserAudit(null, userName, false, "Unable to login. No default location found for user!", user.getDefaultLocationId());
			throw new EAPharmicsException(
					"Unable to login. No default location found for user!");
		}

		String passwordHash = "";
		
		passwordHash = getPasswordHash(user.getUserName(), password );

		beginTransaction();
		
		if (user.getPassword().equals(passwordHash) == false) {
			user.setInvalidLoginCount(user.getInvalidLoginCount() + 1);
			securityFactory.updateUser(user);
			commitTransaction();
			if (user.getInvalidLoginCount() >= invalidLoginCount) {
				createAppUserAudit(null, userName,
						false, "Invalid User Id and/or password combination. User Id is locked.", user.getDefaultLocationId());
				throw new EAPharmicsException(
						"Invalid User Id and/or password combination. User Id is locked.");
			}
			createAppUserAudit(null, userName,
					false, "Invalid User Id and/or password combination.", user.getDefaultLocationId());
			throw new EAPharmicsException(
					"Invalid User Id and/or password combination");
		}

		//Check to see if the user has changed password in 30 days.		
		parameter = adminFactory.getApplParameterByName(Constants.PASSWORD_VALID_PERIOD);
		int daysSincePasswordChanged = 30;
		if (parameter != null) {
			try {
				daysSincePasswordChanged = Integer.parseInt(parameter.getParameterValue());
			} catch (NumberFormatException e) {
				logException("authenticate", e);
			}
		}
		Location location =  adminFactory.getLocation(user.getDefaultLocationId());
		if (location == null) {
			throw new EAPharmicsException("No default location set for User");
		}
		long tzOffset = adminFactory.getTimezoneOffset(location.getTimezoneName());
        // Set the Timezone name and its offset from UTC of the selected location in the session attribute
        //@TODO Sundar to comeback
//		FlexSession session = FlexContext.getFlexSession();
//        if(session != null) {
//        	String tzName = adminFactory.getLocation(user.getDefaultLocationId()).getTimezoneName();
//        	session.setAttribute(Constants.SELECTED_LOCATION_UTC_OFFSET, new Long(tzOffset));
//        	session.setAttribute(Constants.SELECTED_LOCATION_TZNAME, tzName);
//        }
		
		//Zero means password never expires
		if (daysSincePasswordChanged > 0) {
			int days = Utility.dateDiff(user.getPasswordChangeDate(), Utility.getCurrentUserLocationDateTime());
			if (days > daysSincePasswordChanged) {
				user.setPasswordExpired(true);
			}
		}
		user.setInvalidLoginCount(0);
		securityFactory.updateUser(user);
		Long auditId = securityFactory.createAppUserAudit(user.getAppUserId(), userName, true, "Login Successful", user.getDefaultLocationId());
		commitTransaction();

		UserVO userVO = new UserVO();
		userVO = populateUserVO(user, userVO);
		userVO.setSelectedLocationId(userVO.getDefaultLocationId());
		userVO.setAuditId(auditId);

		return userVO;
	}
	
	/**
	 * @param appUserId
	 * @param userName
	 * @param loginSuccess
	 * @param reason
	 * @throws EAPharmicsException
	 */
	private void createAppUserAudit(Long appUserId, String userName, boolean loginSuccess, String reason) 
		throws EAPharmicsException {
		/**
		 * @param appUserId
		 * @param userName
		 * @param loginSuccess
		 * @param reason
		 * @throws EAPharmicsException
		 */
		createAppUserAudit(appUserId, userName, loginSuccess, reason, null);
	}
	
	private void createAppUserAudit(Long appUserId, String userName, boolean loginSuccess, String reason, Long currentLocationId) 
			throws EAPharmicsException {
		try {
			beginTransaction();		
			securityFactory.createAppUserAudit(appUserId, userName, loginSuccess, reason, currentLocationId);
			commitTransaction();
		} catch (Exception e) {
			logException("createAppUserAudit", e);
			try {
				super.rollbackTransaction();
			} catch (Throwable t) {
				logException("createAppUserAudit", e);				
			}
			throw new EAPharmicsException(e);
		}
	}

	public String getPasswordHash(String userName, String password) {
		String passwordHash = "";
		List passwordHashResult = HibernatePersistenceManager.getInstance().openSession()
		                          .createSQLQuery("SELECT security_pkg.get_hash('" + 
					                        		  userName + "', '" + 
					                        		  password + "') AS PASSWORD_HASH " +
		                        		          "FROM dual")
		                          .addScalar("PASSWORD_HASH", Hibernate.STRING).list();
		if (passwordHashResult != null && passwordHashResult.size() > 0) {
			passwordHash = ((String) passwordHashResult.get(0));
		}

		return passwordHash;
	}

	/**
	 * @param userName
	 * @return
	 * @throws EAPharmicsException
	 */
	public UserVO getUserDetails(String userName) throws EAPharmicsException {
		AppUser user = securityFactory.getUser(userName);
		UserVO userVO = new UserVO();
		userVO = populateUserVO(user, userVO);
		return userVO;
	}
	
	/**
	 * @param bomObject
	 * @param voObject
	 * @return
	 */
	private UserVO populateUserVO(AppUser bomObject, UserVO voObject) {
		voObject = new UserVO();
		voObject.setActive(bomObject.getIsActive());
		voObject.setIsTerminated(bomObject.getIsTerminated());
		voObject.setAppUserId(bomObject.getAppUserId());
		voObject.setPassword(bomObject.getPassword()); // temporary, remove it.
		voObject.setAuditId(bomObject.getAuditId());
		voObject.setComments(bomObject.getComments());
		voObject.setEmail(bomObject.getEmail());
		voObject.setEndDate(bomObject.getEndDate());
		voObject.setFirstName(bomObject.getFirstName());
		voObject.setInsertDate(bomObject.getInsertDate());
		voObject.setInsertUser(bomObject.getInsertUser());
		voObject.setLastName(bomObject.getLastName());
		voObject.setStartDate(bomObject.getStartDate());
		voObject.setUpdateDate(bomObject.getUpdateDate());
		voObject.setUpdateUser(bomObject.getUpdateUser());
		voObject.setUserName(bomObject.getUserName());
		voObject.setPasswordExpired(bomObject.getPasswordExpired());
		voObject.setPasswordChangeDate(bomObject.getPasswordChangeDate());
		voObject.setInvalidLoginCount(bomObject.getInvalidLoginCount());
		voObject.setLocale(bomObject.getLocale());
		voObject.setDefaultLocationId(bomObject.getDefaultLocationId());
		
		voObject.setAppAreas(getUserAccess(voObject.getAppUserId()));

		List<Long> list = new ArrayList<Long>(); 
		for (Iterator iterator = bomObject.getAppUserLocations().iterator(); iterator.hasNext();) {
			AppUserLocation type = (AppUserLocation) iterator.next();
			list.add(type.getLocationId());
		}
		voObject.setAppUserLocations(list);
		
		return voObject;
	}
	
	/**
	 * Copy the contents of BOM object to the VO object
	 * @param bomObject
	 * @param voObject
	 */
	public AppUserLocationVO populateAppUserLocationVO(AppUserLocation bomObject, AppUserLocationVO voObject) {
		voObject.setAppUserLocationId(bomObject.getAppUserLocationId());
		voObject.setLocationId(bomObject.getLocationId());
		voObject.setAppUserId(bomObject.getAppUserId());
		voObject.setComments(bomObject.getComments());
		voObject.setInsertDate(bomObject.getInsertDate());
		voObject.setInsertUser(bomObject.getInsertUser());
		voObject.setUpdateDate(bomObject.getUpdateDate());
		voObject.setUpdateUser(bomObject.getUpdateUser());
		voObject.setAuditId(bomObject.getAuditId());
		return voObject;

	}

	/**
	 * Copy the contents of VO object to the BOM object
	 * @param bomObject
	 * @param voObject
	 */
	public AppUserLocation populateAppUserLocationBOM(AppUserLocation bomObject, AppUserLocationVO voObject) {
		bomObject.setLocationId(voObject.getLocationId());
		bomObject.setAppUserId(voObject.getAppUserId());
		bomObject.setActive(voObject.getActive());
		bomObject.setComments(voObject.getComments());
		bomObject.setAuditId(voObject.getAuditId());
		return bomObject;

	}

	/**
	 * @param bomObject
	 * @param voObject
	 * @return
	 */
	private UserAccessModuleVO populateUserAccessModuleVO(AppModule bomObject, UserAccessModuleVO voObject) {
		voObject = new UserAccessModuleVO() ;
		voObject.setAppModuleId(bomObject.getAppModuleId());
		voObject.setModuleDescription(bomObject.getModuleDescription());
		return voObject;
	}

	/**
	 * @param user
	 */
	public List getUserAccess(Long appUserId) {
		List<Object[]> access = securityFactory
				.getAppUserModuleAccessForUser(appUserId);
		String prevArea = "";
		List<UserAccessAreaVO> appAreas = new ArrayList<UserAccessAreaVO>();
		List<UserAccessModuleVO> modules = new ArrayList<UserAccessModuleVO>();
		UserAccessAreaVO area = new UserAccessAreaVO();
		for (Iterator<Object[]> iterator = access.iterator(); iterator
				.hasNext();) {
			Object[] appUserModuleAccess = (Object[]) iterator.next();
			Long appUserModuleAccessId = (Long) appUserModuleAccess[0];
			String appAreaDescription = appUserModuleAccess[1].toString();
			String moduleDescription = appUserModuleAccess[2].toString();
			boolean hasAccess = (appUserModuleAccess[3].toString().equals("Y"));
			Long appModuleId = (Long) appUserModuleAccess[4];
			if (!prevArea.equals(appAreaDescription)) {
				if (prevArea.length() > 0) {
					area.setModules(modules);
					appAreas.add(area);
				}
				area = new UserAccessAreaVO();
				area.setAppAreaDescription(appAreaDescription);
				prevArea = appAreaDescription;
				modules = new ArrayList<UserAccessModuleVO>();
			}
			UserAccessModuleVO module = new UserAccessModuleVO();
			module.setAppUserModuleAccessId(appUserModuleAccessId);
			module.setModuleDescription(moduleDescription);
			module.setHasAccess(hasAccess);
			module.setAppModuleId(appModuleId);
			modules.add(module);
		}
		if (prevArea.length() > 0) {
			area.setModules(modules);
			appAreas.add(area);
		}
		return appAreas;
	}

	/**
	 * @param userId
	 * @param password
	 * @param newPassword
	 * @throws EAPharmicsException
	 */
	public UserVO changePassword(String userId, String password,
			String newPassword) throws EAPharmicsException {
		logger.debug("changePassword",
						"********* Password Change *********");
		AppUser bomObject = securityFactory.getUser(userId);
		// here we need to pass the hash of the old password entered
		validatePassword(userId, getPasswordHash(bomObject.getUserName(), password), newPassword, bomObject);
		try {
			super.beginTransaction();
			updatePassword(newPassword, bomObject);
			bomObject.setPasswordExpired(false);
			bomObject.setInvalidLoginCount(0);
			securityFactory.updateUser(bomObject);
			super.commitTransaction();
		} catch (Exception e) {
			logException("changePassword", e);
			try {
				super.rollbackTransaction();
			} catch (Throwable t) {
				logException("changePassword", e);				
			}
			throw new EAPharmicsException(e);
		}
		UserVO userVO = new UserVO();
		userVO = populateUserVO(bomObject, userVO);
		return userVO;
	}

	/**
	 * @param newPassword
	 * @param bomObject
	 */
	private void updatePassword(String newPassword, AppUser bomObject) {
		bomObject.setPrevPassword3(bomObject.getPrevPassword2());
		bomObject.setPrevPassword2(bomObject.getPrevPassword1());
		bomObject.setPrevPassword1(bomObject.getPassword());
		bomObject.setPassword(newPassword);
		bomObject.setPasswordChangeDate(Utility.getCurrentUserLocationDateTime());
	}

	/**
	 * @param userId
	 * @param password
	 * @param newPassword
	 * @param user
	 * @throws EAPharmicsException
	 */
	private void validatePassword(String userId, String password,
			String newPassword, AppUser user) throws EAPharmicsException {
		if (userId == null || userId.trim().length() == 0) {
			throw new EAPharmicsException("Please enter user Id");
		}
		if (password == null || password.trim().length() == 0) {
			throw new EAPharmicsException("Please enter password");
		}
		if (newPassword == null || newPassword.trim().length() == 0) {
			throw new EAPharmicsException("Please enter New Password");
		}

		String newPasswordHash = getPasswordHash(user.getUserName(), newPassword);

		if (user == null || user.getPassword().equals(password) == false) {
			throw new EAPharmicsException(
					"Invalid AppUser Id and/or password combination");
		}
		if (user == null || user.getPassword().equals(newPassword)) {
			throw new EAPharmicsException(
					"New Password cannot be the same as the previous three passwords.");
		}
		if (user.getPrevPassword1() != null && newPasswordHash.equals(user.getPrevPassword1())) {
			throw new EAPharmicsException(
				"New Password cannot be the same as the previous three passwords.");
		}
		if (user.getPrevPassword2() != null && newPasswordHash.equals(user.getPrevPassword2())) {
			throw new EAPharmicsException(
				"New Password cannot be the same as the previous three passwords.");
		}
		if (user.getPrevPassword3() != null && newPasswordHash.equals(user.getPrevPassword3())) {
			throw new EAPharmicsException(
				"New Password cannot be the same as the previous three passwords.");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.enviroapps.eapharmics.bom.services.SecurityInterface#getUser(java
	 * .lang.String)
	 */
	public AppUser getUser(String userName) throws EAPharmicsException {

		logger.debug("getUser", "********* authenticate user *********");
		AppUser user = securityFactory.getUser(userName);
		if (user == null) {
			throw new EAPharmicsException("Invalid AppUser Id");
		}
		return user;
	}

	/**
	 * @param appUserId
	 * @return
	 */
	public List getAppUserAuditForUser(Long appUserId)
			throws EAPharmicsException {
		return securityFactory.getAppUserAuditForUser(appUserId);
	}

	/* begin ramya changes */
	
	/**
	 * @param voObject
	 * @throws EAPharmicsException
	 */
	public void createUser(UserVO voObject) throws EAPharmicsException {

		try {
			//@TODO Sundar to update
			
//			FlexSession session = FlexContext.getFlexSession();
//			UserVO loggedUser = (UserVO) session.getAttribute(Constants.USER_ATTRIBUTE);
			UserVO loggedUser = new UserVO();
			
			AppUser bomObject = securityFactory.getUser(voObject.getUserName());
			if (bomObject != null) {
				throw new EAPharmicsException(
						"UserName already exists.Choose another UserName");
			}
			bomObject = new AppUser();
			super.beginTransaction();
			populateBOMObject(voObject, bomObject);
			bomObject.setAuditId((loggedUser.getAuditId()));
			bomObject.setInsertDate(Utility.getCurrentUserLocationDateTime());
			bomObject.setInsertUser((loggedUser.getAppUserId()).toString());
			bomObject.setPasswordChangeDate(Utility.getCurrentUserLocationDateTime());
			securityFactory.createUser(bomObject);
			Set<AppUserLocation> arr = new HashSet<AppUserLocation>();
			for (int i=0; i<voObject.getAppUserLocations().size(); i++) {
			   Object obj = voObject.getAppUserLocations().get(i);
			   Long locationId;
			   if ( obj instanceof java.lang.Integer)
			   {
			      Integer x = (Integer) obj;
			      locationId =  x.longValue();
			   }
			   else
			   {
			      locationId = (Long) obj;
			   }
				AppUserLocation loc = new AppUserLocation();
				loc.setAppUserId(bomObject.getAppUserId());
				loc.setLocationId(locationId);
				loc.setActive(true);
				loc.setInsertUser(loggedUser.getAppUserId());
				loc.setInsertDate(Utility.getCurrentUserLocationDateTime());	
				arr.add(loc);
			}
			bomObject.setAppUserLocations(arr);
			super.commitTransaction();

		} catch (Exception e) {
			logException("createUserImpl", e);
			try {
				super.rollbackTransaction();
			} catch (Throwable t) {
				logException("createUserImpl", e);
			}
			throw new EAPharmicsException(e);
		}
	}

	/**
	 * @param useraccessmoduleVO
	 * @throws EAPharmicsException
	 */
	public void UpdateAccessModule(UserAccessModuleVO useraccessmoduleVO)
			throws EAPharmicsException {
		try {
			Date updateDate = Utility.getCurrentUserLocationDateTime();
			//FlexSession session = FlexContext.getFlexSession();
			//@TODO Sundar
			HttpSession session = null;
			UserVO loggedUser = (UserVO) session.getAttribute(Constants.USER_ATTRIBUTE);
			AppUserModuleAccess bomObject = new AppUserModuleAccess();
			super.beginTransaction();
			bomObject.setAppUserModuleAccessId(useraccessmoduleVO
					.getAppUserModuleAccessId());
			bomObject.setIsAccessible(useraccessmoduleVO.isHasAccess());
			bomObject.setAppModuleId(useraccessmoduleVO.getAppModuleId());
			bomObject.setAppUserId(useraccessmoduleVO.getAppUserId());
			bomObject.setAuditId((loggedUser.getAuditId()));
			bomObject.setUpdateUser((loggedUser.getAppUserId()));
			bomObject.setUpdateDate(updateDate);
			securityFactory.updateAccess(bomObject);
			super.commitTransaction();
		} catch (Exception e) {
			logException("AccessImpl", e);
			try {
				super.rollbackTransaction();
			} catch (Throwable t) {
				logException("AccessImpl", e);
			}
			throw new EAPharmicsException(e);
		}

	}

	/**
	 * @param voObject
	 * @throws EAPharmicsException
	 */
	public void updateUser(UserVO voObject) throws EAPharmicsException {

		try {
		   boolean passwordChanging = false;
		   //@TODO Sundar to come back
			//FlexSession session = FlexContext.getFlexSession();
		   HttpSession session = null;
			UserVO loggedUser = (UserVO) session.getAttribute(Constants.USER_ATTRIBUTE);
			AppUser bomObject = securityFactory.getUser(voObject.getUserName());
			if (!voObject.getPassword().equals(bomObject.getPassword())) {
				//password is changing, validate
			   passwordChanging = true;
				validatePassword(voObject.getUserName(), bomObject.getPassword(), 
						voObject.getPassword(), bomObject);
				updatePassword(voObject.getPassword(), bomObject);
			}
			populateBOMObject(voObject, bomObject);
			bomObject.setAuditId((loggedUser.getAuditId()));
			bomObject.setUpdateDate(Utility.getCurrentUserLocationDateTime());
			bomObject.setUpdateUser((loggedUser.getAppUserId()).toString());
			super.beginTransaction();
			//Take care of the deleted locations
			Set<AppUserLocation> arr = new HashSet<AppUserLocation>();
			for (Iterator iterator = bomObject.getAppUserLocations().iterator(); iterator.hasNext();) {
				AppUserLocation type = (AppUserLocation) iterator.next();
				boolean matchFound = false;
				for (int i=0; i<voObject.getAppUserLocations().size(); i++) {
					Object obj = voObject.getAppUserLocations().get(i);
					Long locationId;
					if (obj instanceof Integer) {
						locationId = new Long(((Integer) obj).intValue());
					} else {
						locationId = (Long) obj;
					}
					if (locationId.longValue() == type.getLocationId().longValue()) {
						matchFound = true; 
					}
				}
				if (matchFound == false) {
					//deleted on UI
				   arr.add(type);
				}
			}
         for (Iterator iterator = arr.iterator(); iterator.hasNext();) 
			{
            AppUserLocation type = (AppUserLocation) iterator.next();
	         bomObject.getAppUserLocations().remove(type);
	         securityFactory.delete(type);			   
			}

			//Add the newly added locations
			for (int i=0; i<voObject.getAppUserLocations().size(); i++) {				
				Object obj = voObject.getAppUserLocations().get(i);
				Long locationId;
				if (obj instanceof Integer) {
					locationId = new Long(((Integer) obj).intValue());
				} else {
					locationId = (Long) obj;
				}
				if (bomObject.getAppUserLocationForLocation(locationId) == null) {
					AppUserLocation loc = new AppUserLocation();
					loc.setAppUserId(bomObject.getAppUserId());
					loc.setLocationId(locationId);
					loc.setActive(true);
					loc.setInsertUser(loggedUser.getAppUserId());
					loc.setInsertDate(Utility.getCurrentUserLocationDateTime());
					//securityFactory.create(loc);
					bomObject.getAppUserLocations().add(loc);
				}
			}		
         securityFactory.updateUser(bomObject);	
			super.commitTransaction();
         if (voObject.getPasswordExpired() && passwordChanging)
         {
            createAppUserAudit(null, voObject.getUserName(),
                            false, "Password reset done by "+loggedUser.getUserName(), loggedUser.getDefaultLocationId());
         }  			
		} catch (Exception e) {
			logException("updateUserImpl", e);
			try {
				super.rollbackTransaction();
			} catch (Throwable t) {
				logException("updateUserImpl", e);
			}
			throw new EAPharmicsException(e);
		}

	}

	/**
	 * @param voObject
	 * @param loggedUser
	 * @param bomObject
	 */
	private void populateBOMObject(UserVO voObject, AppUser bomObject) {
		bomObject.setIsActive(voObject.getActive());
		bomObject.setIsTerminated(voObject.getIsTerminated());
		bomObject.setComments(voObject.getComments());
		bomObject.setEmail(voObject.getEmail());
		bomObject.setEndDate(voObject.getEndDate());
		bomObject.setFirstName(voObject.getFirstName());
		bomObject.setEndDate(voObject.getEndDate());
		bomObject.setLastName(voObject.getLastName());
		bomObject.setPassword(voObject.getPassword());
		bomObject.setStartDate(voObject.getStartDate());
		bomObject.setUserName(voObject.getUserName());
		bomObject.setPasswordExpired(voObject.getPasswordExpired());
		bomObject.setInvalidLoginCount(voObject.getInvalidLoginCount());
		bomObject.setLocale(voObject.getLocale());
		bomObject.setDefaultLocationId(voObject.getDefaultLocationId());
	}

	/**
	 * @return
	 * @throws EAPharmicsException
	 */
	public List getAllUser() throws EAPharmicsException {
		List list = securityFactory.getAllUser();
		List < UserVO > list1 = new ArrayList < UserVO >();
		for(Iterator iterator = list.iterator(); iterator.hasNext();)
      {
		   AppUser user = (AppUser) iterator.next();
	      UserVO userVO = new UserVO();
	      userVO = populateUserVO(user, userVO);
         list1.add(userVO);
      }
		return list1;

	}
	
	/**
	 * @return
	 * @throws EAPharmicsException
	 */
	public List getAllModules() throws EAPharmicsException {
		List list = securityFactory.getAllModules();
		List < UserAccessModuleVO > list1 = new ArrayList < UserAccessModuleVO >();
		for(Iterator iterator = list.iterator(); iterator.hasNext();)
      {
		   AppModule appuserModule = (AppModule) iterator.next();
		   UserAccessModuleVO userAccessModuleVO  = new UserAccessModuleVO();
		   userAccessModuleVO = populateUserAccessModuleVO(appuserModule,  userAccessModuleVO);
         list1.add( userAccessModuleVO);
      }
		return list1;

	}
	
	/**
	 * @param voObject
	 * @param auditUser
	 * @return
	 * @throws EAPharmicsException
	 */
	public UserVO updateUserAccess(String userName,
                                  Long appUserId,
                                  List accessModules,
                                  UserVO auditUser) 
		throws EAPharmicsException {
		try {
			super.beginTransaction();
			List accesses = securityFactory
					.getAllAppUserModuleAccessForUser(appUserId);
			for (Iterator iterator2 = accessModules.iterator(); iterator2
					.hasNext();) {
				UserAccessModuleVO moduleAccessVO = (UserAccessModuleVO) iterator2.next();
				AppUserModuleAccess moduleAccess = null;
				if (moduleAccessVO.getAppUserModuleAccessId() == null
						|| moduleAccessVO.getAppUserModuleAccessId()
								.longValue() == 0) {
					throw new EAPharmicsException(
							"Invalid Module Access Found!");
				}
				for (Iterator iterator1 = accesses.iterator(); iterator1.hasNext();) {
					AppUserModuleAccess object = (AppUserModuleAccess) iterator1.next();
					if (object.getAppUserModuleAccessId().equals(
							moduleAccessVO.getAppUserModuleAccessId())) {
						moduleAccess = object;
						break;
					}
				}
				if (moduleAccess.getIsActive() != moduleAccessVO.getHasAccess()) {
					moduleAccess.setIsActive(moduleAccessVO.getHasAccess());
					moduleAccess.setUpdateDate(Utility.getCurrentUserLocationDateTime());
					moduleAccess.setUpdateUser(auditUser.getAppUserId());
					securityFactory.updateAccess(moduleAccess);
				}
			}
			super.commitTransaction();
		} catch (Exception e) {
			logException("updateUserAccess", e);
			try {
				super.rollbackTransaction();
			} catch (Throwable t) {
				logException("updateUserAccess", e);
			}
			throw new EAPharmicsException(e);
		}
				
		return getUserDetails(userName);
	}

	
	/**
	 * @return
	 */
	public List getAllEditReasons() throws EAPharmicsException {
		List<EditReason> list1 = securityFactory.getAllEditReasons();
		List<EditReasonVO> list = new ArrayList<EditReasonVO> ();
		for (Iterator<EditReason> iterator = list1.iterator(); iterator.hasNext();) {
			EditReason bomObject = (EditReason) iterator.next();
			EditReasonVO voObject = new EditReasonVO();
			list.add(populateEditReasonVO(bomObject, voObject));
		}
		return list;
	}

	/**
	 * @param keyName
	 * @return
	 * @throws EAPharmicsException
	 */
	public EditReasonVO getEditReason(Long keyName) throws EAPharmicsException {
		EditReason bomObject = securityFactory.getEditReason(keyName);
		EditReasonVO voObject = new EditReasonVO();
		voObject = populateEditReasonVO(bomObject, voObject);
		return voObject;
	}

	/**
	 * @param editReason
	 */
	public void createEditReason(EditReasonVO voObject, UserVO userVO) throws EAPharmicsException {
		try {
			//Should not start a transaction
			//it will be part of an existing transaction
			EditReason bomObject = null; 
			//temp fix for dem0
			if (voObject.getReason() == null || voObject.getReason().length() == 0) {
				//do not save if no reason
				return;
			}
			bomObject = new EditReason();
			populateEditReasonBOM(bomObject, voObject);
			//convert the date/time from UI to the time in the location
			Date d = Utility.getCurrentUserLocationDateTime(voObject.getInsertDate());
			bomObject.setInsertDate(d);
			bomObject.setInsertUser(userVO.getAppUserId());
			if (userVO != null) {
				//update Audit Id
				bomObject.setAuditId(userVO.getAuditId());
			}
			securityFactory.createEditReason(bomObject);
		} catch (Exception e) {
			logException("createEditReason", e);
			throw new EAPharmicsException(e);
		}
	}


	/**
	 * Copy the contents of BOM object to the VO object
	 * @param bomObject
	 * @param voObject
	 */
	public EditReasonVO populateEditReasonVO(EditReason bomObject, EditReasonVO voObject) {
		voObject.setEditReasonId(bomObject.getEditReasonId());
		voObject.setScreenName(bomObject.getScreenName());
		voObject.setTableName(bomObject.getTableName());
		voObject.setPkValue1(bomObject.getPkValue1());
		voObject.setPkValue2(bomObject.getPkValue2());
		voObject.setReason(bomObject.getReason());
		voObject.setOldValue(bomObject.getOldValue());
		voObject.setNewValue(bomObject.getNewValue());
		voObject.setInsertDate(bomObject.getInsertDate());
		voObject.setInsertUser(bomObject.getInsertUser());
		voObject.setUpdateDate(bomObject.getUpdateDate());
		voObject.setUpdateUser(bomObject.getUpdateUser());
		voObject.setAuditId(bomObject.getAuditId());
		voObject.setComments(bomObject.getComments());
		voObject.setFieldName(bomObject.getFieldName());
		return voObject;

	}

	
	/**
	 * Copy the contents of VO object to the BOM object
	 * @param bomObject
	 * @param voObject
	 */
	public EditReason populateEditReasonBOM(EditReason bomObject, EditReasonVO voObject) {
		bomObject.setScreenName(voObject.getScreenName());
		bomObject.setTableName(voObject.getTableName());
		bomObject.setPkValue1(voObject.getPkValue1());
		bomObject.setPkValue2(voObject.getPkValue2());
		bomObject.setReason(voObject.getReason());
		bomObject.setOldValue(voObject.getOldValue());
		bomObject.setNewValue(voObject.getNewValue());
		bomObject.setAuditId(voObject.getAuditId());
		bomObject.setComments(voObject.getComments());
		StringBuffer sb = new StringBuffer("");
		if (voObject.getFieldName() != null && voObject.getFieldName().length() > 0) {
			String fieldName = voObject.getFieldName();
			for (int i=0; i<fieldName.length(); i++) {
				String x = fieldName.substring(i, i+1);
				if (i == 0) {
					x = x.toUpperCase();
				} else if (x.equals(x.toUpperCase())) {
					sb.append(" ");
				}
				sb.append(x);
			}
			fieldName = sb.toString();
			bomObject.setFieldName(fieldName);
		} else {
			bomObject.setFieldName(voObject.getFieldName());
		}
		return bomObject;

	}

	  /**
    * @return
    */
   public List getAllAppAccessTemplates() throws EAPharmicsException {
      List<AppAccessTemplate> list1 = securityFactory.getAllAppAccessTemplates();
      List<TemplateVO> list = new ArrayList<TemplateVO> ();
      for (Iterator<AppAccessTemplate> iterator = list1.iterator(); iterator.hasNext();) {
         AppAccessTemplate bomObject = (AppAccessTemplate) iterator.next();
         TemplateVO voObject = new TemplateVO();
         list.add(populateAppAccessTemplateVO(bomObject, voObject));
      }
      return list;
   }

   /**
    * @param keyName
    * @return
    * @throws EAPharmicsException
    */
   public TemplateVO getAppAccessTemplate(Long keyName) throws EAPharmicsException {
      AppAccessTemplate bomObject = securityFactory.getAppAccessTemplate(keyName);
      TemplateVO voObject = new TemplateVO();
      voObject = populateAppAccessTemplateVO(bomObject, voObject);
      return voObject;
   }

   /**
    * @param appAccessTemplate
    */
   public void createAppAccessTemplate(TemplateVO voObject, UserVO userVO) throws EAPharmicsException {
      try {
         super.beginTransaction();
//       AppAccessTemplate bomObject = securityFactory.getAppAccessTemplate(voObject.getAppAccessTemplateId()); 
         AppAccessTemplate bomObject = null; 
         if (bomObject != null) {
            throw new EAPharmicsException("AppAccessTemplate already exists for key. Unable to create!");
         }        
         bomObject = new AppAccessTemplate();
         populateAppAccessTemplateBOM(bomObject, voObject);
         bomObject.setInsertDate(Utility.getCurrentUserLocationDateTime());
         bomObject.setInsertUser(userVO.getAppUserId());
         if (userVO != null) {
            //update Audit Id
            bomObject.setAuditId(userVO.getAuditId());
         }
         securityFactory.createAppAccessTemplate(bomObject);
         super.commitTransaction();
      } catch (Exception e) {
         logException("createAppAccessTemplate", e);
         try {
            super.rollbackTransaction();
         } catch (Throwable t) {
            logException("createAppAccessTemplate", e);           
         }
         throw new EAPharmicsException(e);
      }
   }

   /**
    * @param voObject
    * @throws EAPharmicsException
    */
   public void updateAppAccessTemplate(TemplateVO voObject, UserVO userVO) throws EAPharmicsException {
      try {
         super.beginTransaction();
         AppAccessTemplate bomObject = securityFactory.getAppAccessTemplate(voObject.getAppAccessTemplateId()); 
         if (bomObject == null) {
            throw new EAPharmicsException("Cannot find AppAccessTemplate. Unable to update!");
         }        
         populateAppAccessTemplateBOM(bomObject, voObject);
         bomObject.setUpdateDate(Utility.getCurrentUserLocationDateTime());
         bomObject.setUpdateUser(userVO.getAppUserId());
         if (userVO != null) {
            //update Audit Id
            bomObject.setAuditId(userVO.getAuditId());
         }
         securityFactory.updateAppAccessTemplate(bomObject);
         super.commitTransaction();
      } catch (Exception e) {
         logException("updateAppAccessTemplate", e);
         try {
            super.rollbackTransaction();
         } catch (Throwable t) {
            logException("updateAppAccessTemplate", e);           
         }
         throw new EAPharmicsException(e);
      }
   }

   /**
    * @param appAccessTemplate
    */
   public void deleteAppAccessTemplate(Long appAccessTemplateId) throws EAPharmicsException {
      try {
         super.beginTransaction();
         securityFactory.deleteAppAccessTemplate(appAccessTemplateId);
         super.commitTransaction();
      } catch (Exception e) {
         logException("deleteAppAccessTemplate", e);
         try {
            super.rollbackTransaction();
         } catch (Throwable t) {
            logException("updateAppAccessTemplate", e);           
         }
         throw new EAPharmicsException(e);
      }
   }


   /**
    * Copy the contents of BOM object to the VO object
    * @param bomObject
    * @param voObject
    */
   public TemplateVO populateAppAccessTemplateVO(AppAccessTemplate bomObject, TemplateVO voObject) {
      voObject.setAppAccessTemplateId(bomObject.getAppAccessTemplateId());
      voObject.setTemplateName(bomObject.getTemplateName());
      voObject.setInsertDate(bomObject.getInsertDate());
      voObject.setInsertUser(bomObject.getInsertUser());
      voObject.setUpdateDate(bomObject.getUpdateDate());
      voObject.setUpdateUser(bomObject.getUpdateUser());
      voObject.setAuditId(bomObject.getAuditId());
      voObject.setStartDate(bomObject.getStartDate());
      voObject.setEndDate(bomObject.getEndDate());
      voObject.setActive(bomObject.getActive());
      voObject.setComments(bomObject.getComments());
      //krishna
      voObject.setAppAreas(getTemplateAccess(voObject.getAppAccessTemplateId()));
      
      return voObject;

   }

   
   /**
    * Copy the contents of VO object to the BOM object
    * @param bomObject
    * @param voObject
    */
   public AppAccessTemplate populateAppAccessTemplateBOM(AppAccessTemplate bomObject, TemplateVO voObject) {
      bomObject.setTemplateName(voObject.getTemplateName());
      bomObject.setAuditId(voObject.getAuditId());
      bomObject.setStartDate(voObject.getStartDate());
      bomObject.setEndDate(voObject.getEndDate());
      bomObject.setActive(voObject.getActive());
      bomObject.setComments(voObject.getComments());
      return bomObject;

   }

   /**
    * @param user
    */
   public List getTemplateAccess(Long appAccessTemplateId) {
      List<Object[]> access = securityFactory
            .getAppTemplateModuleAccessForTemplate(appAccessTemplateId);
      String prevArea = "";
      List<TemplateAccessAreaVO> appAreas = new ArrayList<TemplateAccessAreaVO>();
      List<TemplateAccessModuleVO> modules = new ArrayList<TemplateAccessModuleVO>();
      TemplateAccessAreaVO area = new TemplateAccessAreaVO();
      for (Iterator<Object[]> iterator = access.iterator(); iterator
            .hasNext();) {
         Object[] appTemplateModuleAccess = (Object[]) iterator.next();
         Long appTemplateModuleAccessId = (Long) appTemplateModuleAccess[0];
         String appAreaDescription = appTemplateModuleAccess[1].toString();
         String moduleDescription = appTemplateModuleAccess[2].toString();
         boolean hasAccess = (appTemplateModuleAccess[3].toString().equals("Y"));
         Long appModuleId = (Long) appTemplateModuleAccess[4];
         if (!prevArea.equals(appAreaDescription)) {
            if (prevArea.length() > 0) {
               area.setModules(modules);
               appAreas.add(area);
            }
            area = new TemplateAccessAreaVO();
            area.setAppAreaDescription(appAreaDescription);
            prevArea = appAreaDescription;
            modules = new ArrayList<TemplateAccessModuleVO>();
         }
         TemplateAccessModuleVO module = new TemplateAccessModuleVO();
         module.setAppAccessTemplateId(appAccessTemplateId);
         module.setAppModuleId(appModuleId);
         module.setAppTemplateModuleAccessId(appTemplateModuleAccessId);
         module.setModuleDescription(moduleDescription);
         module.setHasAccess(hasAccess);
         modules.add(module);
      }
      if (prevArea.length() > 0) {
         area.setModules(modules);
         appAreas.add(area);
      }
      return appAreas;
   }
   
   public TemplateVO updateTemplateAccess(String templateName,
                                  Long appTemplateId,
                                  List accessModules,
                                  UserVO auditUser) 
      throws EAPharmicsException {
      try {
         super.beginTransaction();
         List accesses = securityFactory
               .getAllAppTemplateModuleAccessForTemplate(appTemplateId);
         for (Iterator iterator2 = accessModules.iterator(); iterator2
               .hasNext();) {
            TemplateAccessModuleVO moduleAccessVO = (TemplateAccessModuleVO) iterator2.next();
            AppTemplateModuleAccess moduleAccess = null;
            if (moduleAccessVO.getAppTemplateModuleAccessId() == null
                  || moduleAccessVO.getAppTemplateModuleAccessId()
                        .longValue() == 0) {
               throw new EAPharmicsException(
                     "Invalid Module Access Found!");
            }
            for (Iterator iterator1 = accesses.iterator(); iterator1.hasNext();) {
               AppTemplateModuleAccess object = (AppTemplateModuleAccess) iterator1.next();
               if (object.getAppTemplateModuleAccessId().equals(
                     moduleAccessVO.getAppTemplateModuleAccessId())) {
                  moduleAccess = object;
                  break;
               }
            }
            if (moduleAccess.getIsActive() != moduleAccessVO.getHasAccess()) { 
               moduleAccess.setIsActive(moduleAccessVO.getHasAccess());
               moduleAccess.setUpdateDate(Utility.getCurrentUserLocationDateTime());
               moduleAccess.setUpdateUser(auditUser.getAppUserId());
               securityFactory.updateTemplateAccess(moduleAccess);
            }
         }
         super.commitTransaction();
      } catch (Exception e) {
         logException("updateTemplateAccess", e);
         try {
            super.rollbackTransaction();
         } catch (Throwable t) {
            logException("updateTemplateAccess", e);
         }
         throw new EAPharmicsException(e);
      }
            
      return getTemplateDetails(templateName);
   }
   
   /**
    * @param userName
    * @return
    * @throws EAPharmicsException
    */
   public TemplateVO getTemplateDetails(String templateName) throws EAPharmicsException {
      AppAccessTemplate template = securityFactory.getTemplate(templateName);
      TemplateVO templateVO = new TemplateVO();
      templateVO = populateAppAccessTemplateVO(template, templateVO);
      return templateVO;
   }

	/**
	 * @return
	 */
	public List getAllModuleAccessAudits(Date fromDate, Date toDate, List userIds, List moduleIds) throws EAPharmicsException {
		List<ModuleAccessAudit> list1 = securityFactory.getModuleAccessAudits(fromDate, toDate, userIds, moduleIds);
		List<ModuleAccessAuditVO> list = new ArrayList<ModuleAccessAuditVO> ();
		for (Iterator<ModuleAccessAudit> iterator = list1.iterator(); iterator.hasNext();) {
			ModuleAccessAudit bomObject = (ModuleAccessAudit) iterator.next();
			ModuleAccessAuditVO voObject = new ModuleAccessAuditVO();
			list.add(populateModuleAccessAuditVO(bomObject, voObject));
		}
		return list;
	}

	/**
	 * @param keyName
	 * @return
	 * @throws EAPharmicsException
	 */
	public ModuleAccessAuditVO getModuleAccessAudit(Long keyName) throws EAPharmicsException {
		ModuleAccessAudit bomObject = securityFactory.getModuleAccessAudit(keyName);
		ModuleAccessAuditVO voObject = new ModuleAccessAuditVO();
		voObject = populateModuleAccessAuditVO(bomObject, voObject);
		return voObject;
	}

	/**
	 * @param moduleAccessAudit
	 */
	public void createModuleAccessAudit(ModuleAccessAuditVO voObject, UserVO userVO) throws EAPharmicsException {
		try {
			super.beginTransaction();
//			ModuleAccessAudit bomObject = securityFactory.getModuleAccessAudit(voObject.getModuleAccessAuditId()); 
			ModuleAccessAudit bomObject = null; 
			if (bomObject != null) {
				throw new EAPharmicsException("ModuleAccessAudit already exists for key. Unable to create!");
			}			
			bomObject = new ModuleAccessAudit();
			populateModuleAccessAuditBOM(bomObject, voObject);
//			bomObject.setInsertDate(Utility.getCurrentUserLocationDateTime());
//			bomObject.setInsertUser(userVO.getAppUserId());
//			if (userVO != null) {
//				//update Audit Id
//				bomObject.setAuditId(userVO.getSessionAuditId());
//			}
			securityFactory.createModuleAccessAudit(bomObject);
			super.commitTransaction();
		} catch (Exception e) {
			logException("createModuleAccessAudit", e);
			try {
				super.rollbackTransaction();
			} catch (Throwable t) {
				logException("createModuleAccessAudit", e);				
			}
			throw new EAPharmicsException(e);
		}
	}

	/**
	 * Copy the contents of BOM object to the VO object
	 * @param bomObject
	 * @param voObject
	 */
	public ModuleAccessAuditVO populateModuleAccessAuditVO(ModuleAccessAudit bomObject, ModuleAccessAuditVO voObject) {
		voObject.setModuleAccessAuditId(bomObject.getModuleAccessAuditId());
		voObject.setAppModuleId(bomObject.getAppModuleId());
		voObject.setAppUserId(bomObject.getAppUserId());
		voObject.setActivity(bomObject.getActivity());
		voObject.setAccessTime(bomObject.getAccessTime());
		voObject.setAuditId(bomObject.getAuditId());
		if (bomObject.getAppUser() != null) {
			voObject.setFirstName(bomObject.getAppUser().getFirstName());
			voObject.setLastName(bomObject.getAppUser().getLastName());
			voObject.setUserName(bomObject.getAppUser().getUserName());
		} else {
			voObject.setFirstName("");
			voObject.setLastName("");
			voObject.setUserName("");
		}
		if (bomObject.getAppModule() != null) {
			voObject.setModuleName(bomObject.getAppModule().getModuleDescription());
		} else {
			voObject.setModuleName("");
		}
		return voObject;

	}

	
	/**
	 * Copy the contents of VO object to the BOM object
	 * @param bomObject
	 * @param voObject
	 */
	public ModuleAccessAudit populateModuleAccessAuditBOM(ModuleAccessAudit bomObject, ModuleAccessAuditVO voObject) {
		bomObject.setAppModuleId(voObject.getAppModuleId());
		bomObject.setAppUserId(voObject.getAppUserId());
		bomObject.setActivity(voObject.getActivity());
		bomObject.setAccessTime(voObject.getAccessTime());
		bomObject.setAuditId(voObject.getAuditId());
		return bomObject;

	}

	/**
	 * @return
	 */
	public List<ProductRegistrationVO> getAllProductRegistrations() throws EAPharmicsException {
		List<ProductRegistration> list1 = securityFactory.getAllProductRegistrations();
		List<ProductRegistrationVO> list = new ArrayList<ProductRegistrationVO> ();
		for (Iterator<ProductRegistration> iterator = list1.iterator(); iterator.hasNext();) {
			ProductRegistration bomObject = (ProductRegistration) iterator.next();
			ProductRegistrationVO voObject = new ProductRegistrationVO();
			list.add(populateProductRegistrationVO(bomObject, voObject));
		}
		return list;
	}

	/**
	 * @param keyName
	 * @return
	 * @throws EAPharmicsException
	 */
	public ProductRegistrationVO getProductRegistration(Long productRegisrationId) throws EAPharmicsException {
		ProductRegistration bomObject = securityFactory.getProductRegistration(productRegisrationId);
		ProductRegistrationVO voObject = new ProductRegistrationVO();
		voObject = populateProductRegistrationVO(bomObject, voObject);
		return voObject;
	}

	/**
	 * @param productRegistration
	 */
	public void createProductRegistration(ProductRegistrationVO voObject, UserVO userVO) throws EAPharmicsException {
		try {
			super.beginTransaction();
//			ProductRegistration bomObject = securityFactory.getProductRegistration(voObject.getLicenseKey()); 
			ProductRegistration bomObject = null; 
			if (bomObject != null) {
				throw new EAPharmicsException("ProductRegistration already exists for key. Unable to create!");
			}			
			bomObject = new ProductRegistration();
			populateProductRegistrationBOM(bomObject, voObject);
			securityFactory.createProductRegistration(bomObject);
			super.commitTransaction();
		} catch (Exception e) {
			logException("createProductRegistration", e);
			try {
				super.rollbackTransaction();
			} catch (Throwable t) {
				logException("createProductRegistration", e);				
			}
			throw new EAPharmicsException(e);
		}
	}

	/**
	 * @param voObject
	 * @throws EAPharmicsException
	 */
	public void updateProductRegistration(ProductRegistrationVO voObject, UserVO userVO) throws EAPharmicsException {
		try {
			ProductRegistration bomObject = securityFactory.getProductRegistration(voObject.getLicenseId()); 
			if (bomObject == null) {
				throw new EAPharmicsException("Cannot find ProductRegistration. Unable to update!");
			}			
			populateProductRegistrationBOM(bomObject, voObject);
			super.beginTransaction();
			securityFactory.updateProductRegistration(bomObject);
			super.commitTransaction();
		} catch (Exception e) {
			logException("updateProductRegistration", e);
			try {
				super.rollbackTransaction();
			} catch (Throwable t) {
				logException("updateProductRegistration", e);				
			}
			throw new EAPharmicsException(e);
		}
	}

	/**
	 * @param productRegistration
	 */
	public void deleteProductRegistration(Long productRegistrationId) throws EAPharmicsException {
		try {
			super.beginTransaction();
			securityFactory.deleteProductRegistration(productRegistrationId);
			super.commitTransaction();
		} catch (Exception e) {
			logException("deleteProductRegistration", e);
			try {
				super.rollbackTransaction();
			} catch (Throwable t) {
				logException("updateProductRegistration", e);				
			}
			throw new EAPharmicsException(e);
		}
	}


	/**
	 * Copy the contents of BOM object to the VO object
	 * @param bomObject
	 * @param voObject
	 */
	public ProductRegistrationVO populateProductRegistrationVO(ProductRegistration bomObject, ProductRegistrationVO voObject) {
		voObject.setLicenseId(bomObject.getLicenseId());
		voObject.setLicenseKey(bomObject.getLicenseKey());
		voObject.setLicenseString(bomObject.getLicenseString());
		voObject.setLicenseInfo(bomObject.getLicenseInfo());
		return voObject;
	}

	
	/**
	 * Copy the contents of VO object to the BOM object
	 * @param bomObject
	 * @param voObject
	 */
	public ProductRegistration populateProductRegistrationBOM(ProductRegistration bomObject, ProductRegistrationVO voObject) {
		bomObject.setLicenseId(voObject.getLicenseId());
		bomObject.setLicenseKey(voObject.getLicenseKey());
		bomObject.setLicenseString(voObject.getLicenseString());
		bomObject.setLicenseInfo(voObject.getLicenseInfo());
		return bomObject;
	}

	/**
	 * Get the license info from the database
	 * @return the license info
	 */
	public String getLicenseInfo(String licenseKey) {
		return securityFactory.getLicenseInfo(licenseKey);
	}
	
	public void setLicense(ProductRegistrationVO voObject, String companyName) throws EAPharmicsException {
		AdminImpl adminImpl = new AdminImpl();
		UserVO userVO = getUserDetails("ADMIN");	
		adminImpl.updateApplParameter("COMPANY NAME", companyName, userVO);
		voObject.setLicenseInfo(securityFactory.getEncryptedLicenseInfo(voObject.getLicenseKey()));
		updateProductRegistration(voObject, userVO);

	}


}
