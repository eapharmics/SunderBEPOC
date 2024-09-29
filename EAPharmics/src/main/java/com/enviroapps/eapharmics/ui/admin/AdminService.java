package com.enviroapps.eapharmics.ui.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enviroapps.eapharmics.exception.EAPharmicsException;
import com.enviroapps.eapharmics.services.AdminImpl;
import com.enviroapps.eapharmics.ui.AbstractEAPharmicsService;
import com.enviroapps.eapharmics.vo.admin.ApplParameterVO;
import com.enviroapps.eapharmics.vo.admin.LocationVO;
import com.enviroapps.eapharmics.vo.admin.TimezoneVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/eapharmics/admin")
@RequiredArgsConstructor

public class AdminService extends AbstractEAPharmicsService {


	/**
	 * @param applParameterId
	 * @return
	 * @throws EAPharmicsException
	 */
	@GetMapping("/getAllApplParameters")
	public List<ApplParameterVO> getAllApplParameters() 
		throws EAPharmicsException {
		checkSession();
		List<ApplParameterVO> list = new ArrayList<ApplParameterVO>();
		try {
			AdminImpl impl = new AdminImpl();
			list = impl.getAllApplParameters();
			return list;
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "getApplParameters", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		}
	}

	/**
	 * @param voObject
	 * @return
	 * @throws EAPharmicsException
	 */
	@GetMapping("/updateApplParameter")
	public List<ApplParameterVO> updateApplParameter(@RequestBody ApplParameterVO voObject) 
	throws EAPharmicsException {
		checkSession();
		List<ApplParameterVO> list = new ArrayList<ApplParameterVO>();
		try {
			AdminImpl impl = new AdminImpl();
			impl.updateApplParameter(voObject, getUser());
			list = impl.getAllApplParameters();
			return list;
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "updateApplParameter", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.");
		}
	}

	/**
	 * @param voObject
	 * @return
	 * @throws EAPharmicsException
	 */
	@GetMapping("/addApplParameter")
	public List<ApplParameterVO> addApplParameter(@RequestBody ApplParameterVO voObject) 
	throws EAPharmicsException {
		checkSession();
		List<ApplParameterVO> list = new ArrayList<ApplParameterVO>();
		try {
			AdminImpl impl = new AdminImpl();
			impl.createApplParameter(voObject, getUser());
			list = impl.getAllApplParameters();		
			return list;
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "addApplParameter", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.");
		}
	}

	/**
	 * @return
	 * @throws EAPharmicsException
	 */
	@GetMapping("/getAllLocations")
	public List<LocationVO> getAllLocations() 
		throws EAPharmicsException {
		checkSession();
		List<LocationVO> list = new ArrayList<LocationVO>();
		try {
			AdminImpl impl = new AdminImpl();
			list = impl.getAllLocations();
			return list;
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "getLocations", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		}
	}

	/**
	 * @param voObject
	 * @return
	 * @throws EAPharmicsException
	 */
	@GetMapping("/updateLocation")
	public List<LocationVO> updateLocation(@RequestBody LocationVO voObject) 
	throws EAPharmicsException {
		checkSession();
		List<LocationVO> list = new ArrayList<LocationVO>();
		try {
			AdminImpl impl = new AdminImpl();
			impl.updateLocation(voObject, getUser());
			list = impl.getAllLocations();
			return list;
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "updateLocation", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.");
		}
	}

	/**
	 * @param voObject
	 * @return
	 * @throws EAPharmicsException
	 */
	@GetMapping("/addLocation")
	public List<LocationVO> addLocation(@RequestBody LocationVO voObject) 
	throws EAPharmicsException {
		checkSession();
		List<LocationVO> list = new ArrayList<LocationVO>();
		try {
			AdminImpl impl = new AdminImpl();
			impl.createLocation(voObject, getUser());
			list = impl.getAllLocations();
			return list;
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "addLocation", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.");
		}
	}

	/**
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<TimezoneVO> getAllTimezoneNames() 
		throws EAPharmicsException {
		checkSession();
		List<TimezoneVO> list = new ArrayList<TimezoneVO>();
		try {
			AdminImpl impl = new AdminImpl();
			list = impl.getAllTimezoneNames();
			return list;
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "getAllTimezoneNames", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		}
	}


}
