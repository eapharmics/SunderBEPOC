package com.enviroapps.eapharmics.ui.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import com.eapharmics.license.LicenseChecker;
import com.enviroapps.eapharmics.common.services.UtilityServiceFactory;
import com.enviroapps.eapharmics.exception.EAPharmicsException;
import com.enviroapps.eapharmics.persistence.AdminFactory;
import com.enviroapps.eapharmics.services.SecurityImpl;
import com.enviroapps.eapharmics.ui.AbstractEAPharmicsService;
import com.enviroapps.eapharmics.ui.Constants;
import com.enviroapps.eapharmics.vo.security.EditReasonVO;
import com.enviroapps.eapharmics.vo.security.LoginRequestVO;
import com.enviroapps.eapharmics.vo.security.ModuleAccessAuditVO;
import com.enviroapps.eapharmics.vo.security.ProductRegistrationVO;
import com.enviroapps.eapharmics.vo.security.TemplateVO;
import com.enviroapps.eapharmics.vo.security.UserAccessModuleVO;
import com.enviroapps.eapharmics.vo.security.UserVO;
//import com.verhas.licensor.HardwareBinder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@ControllerAdvice
@RequestMapping("/eapharmics")
@RequiredArgsConstructor
public class SecurityService extends AbstractEAPharmicsService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.enviroapps.eapharmics.ui.AbstractEAPharmicsService#checkSession()
	 */
	@Override
	public void checkSession() throws EAPharmicsException {
		super.checkSession();
		// valid session found also make sure the product is registered
		//@TODO Sundar
//		if (isProductRegistered() == false) {
//			throw new EAPharmicsException("Product is not registered. Please contact support");
//		}
	}

	/**
	 * @param userId
	 * @param password
	 * @return
	 */
	@PostMapping
	public UserVO authenticate(@RequestBody LoginRequestVO reqVO, HttpServletRequest request) throws EAPharmicsException {
		UserVO userVO = null;
		try {
			SecurityImpl impl = new SecurityImpl();
			userVO = impl.authenticate(reqVO.getUserId(), reqVO.getPassword());

			// Set the User object in the session attribute
			HttpSession session = request.getSession();
			if (session != null) {
				session.setAttribute(Constants.USER_ATTRIBUTE, userVO);
			} else {
				System.err.println("No flex session found");
			}

		} catch (RuntimeException e) {
			UtilityServiceFactory.getLogger().error(this, "authenticate", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.");
		}
		// setContext(user);
		return userVO;
	}

	/**
	 * @param userId
	 * @throws EAPharmicsException
	 */
	@GetMapping("/logout")
	public void logout(HttpServletRequest request) throws EAPharmicsException {

		// Set the User object in the session attribute
		HttpSession session = request.getSession();
		if (session != null) {
			session.removeAttribute(Constants.USER_ATTRIBUTE);
			session.setMaxInactiveInterval(0);
			session.invalidate();
		} else {
			System.err.println("No flex session found");
		}
	}

	/**
	 * @param userId
	 * @throws EAPharmicsException
	 */
	public UserVO changePassword(String userId, String password, String newPassword) throws EAPharmicsException {
		UserVO userVO = null;
		try {
			SecurityImpl impl = new SecurityImpl();
			userVO = impl.changePassword(userId, password, newPassword);

		} catch (RuntimeException e) {
			UtilityServiceFactory.getLogger().error(this, "authenticate", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.");
		}
		// setContext(user);
		return userVO;
	}

	/**
	 * @return
	 * @throws EAPharmicsException
	 */
	public List getAllUser() throws EAPharmicsException {
		try {
			SecurityImpl impl = new SecurityImpl();
			List list = impl.getAllUser();
			return list;
		} catch (Exception e) {
			logger.error(this, "getAllUser", e);
			throw new EAPharmicsException("Unknown error. Please contact System Administrator!");
		}
	}

	/**
	 * @param appUserId
	 * @return
	 * @throws EAPharmicsException
	 */
	public List getAppUserAuditForUser(Long appUserId) throws EAPharmicsException {

		try {
			SecurityImpl impl = new SecurityImpl();
			List list = impl.getAppUserAuditForUser(appUserId);
			return list;
		} catch (Exception e) {
			logger.error(this, "getAppUserAuditForUser", e);
			throw new EAPharmicsException("Unknown error. Please contact System Administrator!");
		}
	}

	/**
	 * @param userVO
	 * @return
	 * @throws EAPharmicsException
	 */
	public List createUser(UserVO userVO) throws EAPharmicsException {

		try {
			SecurityImpl impl = new SecurityImpl();
			impl.createUser(userVO);
			return impl.getAllUser();
			// return getUser();
		} catch (EAPharmicsException e) {
			// e.printStackTrace();
			throw e;
		} catch (Exception e) {
			// e.printStackTrace();
			UtilityServiceFactory.getLogger().error(this, "createUser", e);

			throw new EAPharmicsException("system error. Please cotact system administrator");
		}
		// setContext(user);
	}

	/**
	 * @param userVO
	 * @return
	 * @throws EAPharmicsException
	 */
	public List UpdateUser(UserVO userVO) throws EAPharmicsException {

		try {
			SecurityImpl impl = new SecurityImpl();
			impl.updateUser(userVO);
			return impl.getAllUser();
		} catch (EAPharmicsException e) {
			throw e;
		} catch (Exception e) {
			logger.error(this, "updateUser", e);
			throw new EAPharmicsException("Unable to process Request. Contact Support.", e);
		}
	}

	/**
	 * @param userAMVO
	 * @return
	 * @throws EAPharmicsException
	 */
	public List UpdateAccessModule(UserAccessModuleVO userAMVO) throws EAPharmicsException {

		try {
			SecurityImpl impl = new SecurityImpl();
			impl.UpdateAccessModule(userAMVO);
			// return getUser();
		} catch (Exception e) {
			// e.printStackTrace();
		}
		// setContext(user);
		return null;
	}

	/**
	 * @param userAMVO
	 * @return
	 * @throws EAPharmicsException
	 */
	public UserVO updateUserAccess(String userName, Long appUserId, List accessModules) throws EAPharmicsException {
		checkSession();
		try {
			SecurityImpl impl = new SecurityImpl();
			return impl.updateUserAccess(userName, appUserId, accessModules, getUser());
		} catch (EAPharmicsException e) {
			throw e;
		} catch (Exception e) {
			logger.error(this, "updateUserAccess", e);
			throw new EAPharmicsException("Unable to process Request. Contact Support.", e);
		}
	}

	/* end changes */

	public String getPasswordHash(String userName, String password) throws EAPharmicsException {
		try {
			SecurityImpl impl = new SecurityImpl();
			return impl.getPasswordHash(userName, password);
		} catch (Exception e) {
			logger.error(this, "getPasswordHash", e);
			throw new EAPharmicsException("Unable to process Request. Contact Support.", e);
		}
	}

	/**
	 * @param editReasonId
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<EditReasonVO> getAllEditReasons() throws EAPharmicsException {
		checkSession();
		List<EditReasonVO> list = new ArrayList<EditReasonVO>();
		try {
			SecurityImpl impl = new SecurityImpl();
			list = impl.getAllEditReasons();
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "getEditReasons", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		}
		return list;
	}

	/**
	 * @param voObject
	 * @return
	 * @throws EAPharmicsException
	 */
	public void addEditReason(EditReasonVO voObject) throws EAPharmicsException {
		checkSession();
		List<EditReasonVO> list = new ArrayList<EditReasonVO>();
		try {
			SecurityImpl impl = new SecurityImpl();
			impl.createEditReason(voObject, getUser());
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "addEditReason", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.");
		}
	}

	/**
	 * @param appAccessTemplateId
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<TemplateVO> getAllAppAccessTemplates() throws EAPharmicsException {
		checkSession();
		List<TemplateVO> list = new ArrayList<TemplateVO>();
		try {
			SecurityImpl impl = new SecurityImpl();
			list = impl.getAllAppAccessTemplates();
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "getAppAccessTemplates", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		}
		return list;
	}

	/**
	 * @param voObject
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<TemplateVO> updateAppAccessTemplate(TemplateVO voObject) throws EAPharmicsException {
		checkSession();
		List<TemplateVO> list = new ArrayList<TemplateVO>();
		try {
			SecurityImpl impl = new SecurityImpl();
			impl.updateAppAccessTemplate(voObject, getUser());
			list = impl.getAllAppAccessTemplates();
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "updateAppAccessTemplate", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.");
		}
		return list;
	}

	/**
	 * @param voObject
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<TemplateVO> addAppAccessTemplate(TemplateVO voObject) throws EAPharmicsException {
		checkSession();
		List<TemplateVO> list = new ArrayList<TemplateVO>();
		try {
			SecurityImpl impl = new SecurityImpl();
			impl.createAppAccessTemplate(voObject, getUser());
			list = impl.getAllAppAccessTemplates();
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "addAppAccessTemplate", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.");
		}
		return list;
	}

	public TemplateVO updateTemplateAccess(String templateName, Long appTemplateId, List accessModules)
			throws EAPharmicsException {
		checkSession();
		try {
			SecurityImpl impl = new SecurityImpl();
			return impl.updateTemplateAccess(templateName, appTemplateId, accessModules, getUser());
		} catch (EAPharmicsException e) {
			throw e;
		} catch (Exception e) {
			logger.error(this, "updateTemplateAccess", e);
			throw new EAPharmicsException("Unable to process Request. Contact Support.", e);
		}
	}

	/**
	 * @param moduleAccessAuditId
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<ModuleAccessAuditVO> getModuleAccessAudits(Date fromDate, Date toDate, List userIds, List moduleIds)
			throws EAPharmicsException {
		checkSession();
		List<ModuleAccessAuditVO> list = new ArrayList<ModuleAccessAuditVO>();
		try {
			SecurityImpl impl = new SecurityImpl();
			list = impl.getAllModuleAccessAudits(fromDate, toDate, userIds, moduleIds);
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "getModuleAccessAudits", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		}
		return list;
	}

	/**
	 * @param voObject
	 * @return
	 * @throws EAPharmicsException
	 */
	@PostMapping("/createModuleAccessAudit")
	public void createModuleAccessAudit(@RequestBody ModuleAccessAuditVO voObject) throws EAPharmicsException {
		checkSession();
		try {
			SecurityImpl impl = new SecurityImpl();
			impl.createModuleAccessAudit(voObject, getUser());
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "createModuleAccessAudit", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.");
		}
	}

	/**
	 * @param productRegistrationId
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<ProductRegistrationVO> getAllProductRegistrations() throws EAPharmicsException {
		List<ProductRegistrationVO> list = new ArrayList<ProductRegistrationVO>();
		try {
			SecurityImpl impl = new SecurityImpl();
			list = impl.getAllProductRegistrations();
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "getProductRegistrations", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		}
		return list;
	}

	/**
	 * @param voObject
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<ProductRegistrationVO> updateProductRegistration(ProductRegistrationVO voObject)
			throws EAPharmicsException {
		checkSession();
		List<ProductRegistrationVO> list = new ArrayList<ProductRegistrationVO>();
		try {
			SecurityImpl impl = new SecurityImpl();
			impl.updateProductRegistration(voObject, getUser());
			list = impl.getAllProductRegistrations();
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "updateProductRegistration", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.");
		}
		return list;
	}

	/**
	 * @param voObject
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<ProductRegistrationVO> addProductRegistration(ProductRegistrationVO voObject)
			throws EAPharmicsException {
		checkSession();
		List<ProductRegistrationVO> list = new ArrayList<ProductRegistrationVO>();
		try {
			SecurityImpl impl = new SecurityImpl();
			impl.createProductRegistration(voObject, getUser());
			list = impl.getAllProductRegistrations();
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "addProductRegistration", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.");
		}
		return list;
	}

	public ProductRegistrationVO getProductRegistration() throws EAPharmicsException {
		return (ProductRegistrationVO) getAllProductRegistrations().get(0);
	}

	/*
	 * @TODO Sundar
	private LicenseChecker getLicenseChecker() throws EAPharmicsException {
		LicenseChecker licenseChecker = null;
		try {
			ProductRegistrationVO reg = getProductRegistration();
			licenseChecker = new LicenseChecker(reg.getLicenseString());
			if (licenseChecker.isLicenseValid(new SecurityImpl().getLicenseInfo(reg.getLicenseKey())) == false) {
				throw new EAPharmicsException("Invalid License");
			}
		} catch (Exception e) {
			logger.debug(this, "getLicenseChecker", e);
			throw new EAPharmicsException("Invalid License");
		}
		return licenseChecker;
	}

	public boolean isProductRegistered() {
		try {
			if (getLicenseChecker() != null) {
				return true;
			}
		} catch (EAPharmicsException e) {
		}
		return false;
	}

	public int getMaxLocations() throws EAPharmicsException {
		LicenseChecker licenseChecker = getLicenseChecker();
		try {
			return licenseChecker.getMaxLocations();
		} catch (Exception e) {
			e.printStackTrace();
			throw new EAPharmicsException("");
		}
	}

	public boolean isBarcode2DEnabled() throws EAPharmicsException {
		return getLicenseChecker().isBarCode2DEnabled();
	}

	public boolean isEncryptionEnabled() throws EAPharmicsException {
		return getLicenseChecker().isEncryptionEnabled();
	}

	public boolean isDemoVersion() throws EAPharmicsException {
		return getLicenseChecker().isDemoVersion();
	}

	public boolean isLiteVersion() throws EAPharmicsException {
		return getLicenseChecker().isDemoVersion();
	}

	public boolean isLitePlusVersion() throws EAPharmicsException {
		return getLicenseChecker().isLitePlusVersion();
	}

	public boolean setLicense(ProductRegistrationVO productRegistrationVO) throws EAPharmicsException {
		try {
			ProductRegistrationVO reg = getProductRegistration();
			String licenseKey = reg.getLicenseKey();
			// cannot use the same license key to register again
			if (licenseKey.equals(productRegistrationVO.getLicenseKey())) {
				return false;
			}

			LicenseChecker licenseChecker = new LicenseChecker(productRegistrationVO.getLicenseString());
			String companyName = licenseChecker.getCompanyName();
			String licenseInfo = productRegistrationVO.getLicenseInfo();
			new SecurityImpl().setLicense(productRegistrationVO, companyName);
			return true;
		} catch (Exception e) {
			logger.debug(this, "setLicense", e);
		}
		return false;
	}

	public String getMachineId() {
		String machineId = "";
		HardwareBinder binder = new HardwareBinder();
		binder.ignoreNetwork();
		machineId = binder.getMachineIdString();
		machineId = machineId.replaceAll("-", "");
		String returnMachineId = "";
		for (int i = 0; i < machineId.length(); i++) {
			returnMachineId += machineId.substring(i, i + 1);
			if ((i + 1) % 4 == 0 && i < machineId.length() - 1) {
				returnMachineId += "-";
			}
		}
		return returnMachineId.toUpperCase();
	}
	 */

	public List getAllModules() throws EAPharmicsException {
		try {
			SecurityImpl impl = new SecurityImpl();
			List list = impl.getAllModules();
			return list;

		} catch (Exception e) {
			logger.error(this, "getAllModules", e);
			return null;
		}
	}

	/**
	 * User has changed the location they want to work in
	 * 
	 * @param selectedLocationId
	 * @return
	 * @throws EAPharmicsException
	 */
	public UserVO updateSelectedLocation(Long selectedLocationId, HttpServletRequest request) throws EAPharmicsException {
		try {
			super.checkSession();
			UserVO userVO = getUser();
			userVO.setSelectedLocationId(selectedLocationId);
			// Set the Timezone name of the selected location in the session attribute
			HttpSession session = request.getSession();
			if (session != null) {
				session.setAttribute(Constants.USER_ATTRIBUTE, userVO);
				String tzName = AdminFactory.getInstance().getLocation(userVO.getSelectedLocationId())
						.getTimezoneName();
				session.setAttribute(Constants.SELECTED_LOCATION_TZNAME, tzName);
				session.setAttribute(Constants.SELECTED_LOCATION_UTC_OFFSET,
						new Long(AdminFactory.getInstance().getTimezoneOffset(tzName)));
			}
			return userVO;
		} catch (Exception e) {
			logger.error(this, "updatedSelectedLocation", e);
			throw new EAPharmicsException("Unknown Error. Please contact the System Administrator!");
		}
	}

}
