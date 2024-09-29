package com.enviroapps.eapharmics.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.enviroapps.eapharmics.bom.admin.ApplParameter;
import com.enviroapps.eapharmics.bom.admin.Location;
import com.enviroapps.eapharmics.common.Utility;
import com.enviroapps.eapharmics.exception.EAPharmicsException;
import com.enviroapps.eapharmics.persistence.AdminFactory;
import com.enviroapps.eapharmics.vo.admin.ApplParameterVO;
import com.enviroapps.eapharmics.vo.admin.LocationVO;
import com.enviroapps.eapharmics.vo.admin.TimezoneVO;
import com.enviroapps.eapharmics.vo.security.UserVO;

/**
 * @author EnviroApps
 * 
 */
public class AdminImpl extends AbstractServiceImpl {


	private AdminFactory adminFactory = AdminFactory.getInstance();


	/**
	 * @return
	 */
	public List getAllApplParameters() throws EAPharmicsException {
		List<ApplParameter> list1 = adminFactory.getAllApplParameters();
		List<ApplParameterVO> list = new ArrayList<ApplParameterVO> ();
		for (Iterator<ApplParameter> iterator = list1.iterator(); iterator.hasNext();) {
			ApplParameter bomObject = (ApplParameter) iterator.next();
			ApplParameterVO voObject = new ApplParameterVO();
			list.add(populateApplParameterVO(bomObject, voObject));
		}
		return list;
	}

	/**
	 * @param keyName
	 * @return
	 * @throws EAPharmicsException
	 */
	public ApplParameterVO getApplParameter(Long keyName) throws EAPharmicsException {
		ApplParameter bomObject = adminFactory.getApplParameter(keyName);
		ApplParameterVO voObject = new ApplParameterVO();
		voObject = populateApplParameterVO(bomObject, voObject);
		return voObject;
	}

	/**
	 * @param keyName
	 * @return
	 * @throws EAPharmicsException
	 */
	public ApplParameterVO getApplParameterByName(String keyName) throws EAPharmicsException {
		ApplParameter bomObject = adminFactory.getApplParameterByName(keyName);
		ApplParameterVO voObject = new ApplParameterVO();
		voObject = populateApplParameterVO(bomObject, voObject);
		return voObject;
	}

	/**
	 * @param applParameter
	 */
	public void createApplParameter(ApplParameterVO voObject, UserVO userVO) throws EAPharmicsException {
		try {
			super.beginTransaction();
//			ApplParameter bomObject = adminFactory.getApplParameter(voObject.getApplParameterId()); 
			ApplParameter bomObject = null; 
			if (bomObject != null) {
				throw new EAPharmicsException("ApplParameter already exists for key. Unable to create!");
			}			
			bomObject = new ApplParameter();
			populateApplParameterBOM(bomObject, voObject);
			bomObject.setInsertDate(Utility.getCurrentUserLocationDateTime());
			bomObject.setInsertUser(userVO.getAppUserId());
			adminFactory.createApplParameter(bomObject);
			if (userVO != null) {
				//update Audit Id
				bomObject.setAuditId(userVO.getAuditId());
			}
			super.commitTransaction();
		} catch (Exception e) {
			logException("createApplParameter", e);
			try {
				super.rollbackTransaction();
			} catch (Throwable t) {
				logException("createApplParameter", e);				
			}
			throw new EAPharmicsException(e);
		}
	}

	/**
	 * @param voObject
	 * @throws EAPharmicsException
	 */
	public void updateApplParameter(ApplParameterVO voObject, UserVO userVO) throws EAPharmicsException {
		try {
			ApplParameter bomObject = adminFactory.getApplParameter(voObject.getApplParameterId()); 
			if (bomObject == null) {
				throw new EAPharmicsException("Cannot find ApplParameter. Unable to update!");
			}			
			populateApplParameterBOM(bomObject, voObject);
			bomObject.setUpdateDate(Utility.getCurrentUserLocationDateTime());
			bomObject.setUpdateUser(userVO.getAppUserId());
			if (userVO != null) {
				//update Audit Id
				bomObject.setAuditId(userVO.getAuditId());
			}
			super.beginTransaction();
			adminFactory.updateApplParameter(bomObject);
			super.commitTransaction();
		} catch (Exception e) {
			logException("updateApplParameter", e);
			try {
				super.rollbackTransaction();
			} catch (Throwable t) {
				logException("updateApplParameter", e);				
			}
			throw new EAPharmicsException(e);
		}
	}

	/**
	 * @param applParameter
	 */
	public void deleteApplParameter(Long applParameterId) throws EAPharmicsException {
		try {
			super.beginTransaction();
			adminFactory.deleteApplParameter(applParameterId);
			super.commitTransaction();
		} catch (Exception e) {
			logException("deleteApplParameter", e);
			try {
				super.rollbackTransaction();
			} catch (Throwable t) {
				logException("updateApplParameter", e);				
			}
			throw new EAPharmicsException(e);
		}
	}


	/**
	 * Copy the contents of BOM object to the VO object
	 * @param bomObject
	 * @param voObject
	 */
	public ApplParameterVO populateApplParameterVO(ApplParameter bomObject, ApplParameterVO voObject) {
		voObject.setApplParameterId(bomObject.getApplParameterId());
		voObject.setParameterName(bomObject.getParameterName());
		voObject.setParameterDescription(bomObject.getParameterDescription());
		voObject.setParameterValue(bomObject.getParameterValue());
		voObject.setInsertDate(bomObject.getInsertDate());
		voObject.setInsertUser(bomObject.getInsertUser());
		voObject.setUpdateDate(bomObject.getUpdateDate());
		voObject.setUpdateUser(bomObject.getUpdateUser());
		voObject.setAuditId(bomObject.getAuditId());
		voObject.setStartDate(bomObject.getStartDate());
		voObject.setEndDate(bomObject.getEndDate());
		voObject.setComments(bomObject.getComments());
		voObject.setActive(bomObject.getActive());
		voObject.setParameterDataType(bomObject.getParameterDataType());
		voObject.setParameterDataLength(bomObject.getParameterDataLength());
		voObject.setLocationId(bomObject.getLocationId());
		return voObject;

	}

	
	/**
	 * Copy the contents of VO object to the BOM object
	 * @param bomObject
	 * @param voObject
	 */
	public ApplParameter populateApplParameterBOM(ApplParameter bomObject, ApplParameterVO voObject) {
		bomObject.setApplParameterId(voObject.getApplParameterId());
		bomObject.setParameterName(voObject.getParameterName());
		bomObject.setParameterDescription(voObject.getParameterDescription());
		bomObject.setParameterValue(voObject.getParameterValue());
		bomObject.setAuditId(voObject.getAuditId());
		bomObject.setStartDate(voObject.getStartDate());
		bomObject.setEndDate(voObject.getEndDate());
		bomObject.setComments(voObject.getComments());
		bomObject.setActive(voObject.getActive());
		bomObject.setParameterDataType(voObject.getParameterDataType());
		if (voObject.getParameterDataLength() != null && voObject.getParameterDataLength().intValue() == 0) {
			voObject.setParameterDataLength(null);
		}
		bomObject.setParameterDataLength(voObject.getParameterDataLength());
		if (voObject.getLocationId()>0)
		{
		   bomObject.setLocationId(voObject.getLocationId());
		}
		else
		{
		   bomObject.setLocationId(null);
		}
		return bomObject;

	}

	/**
	 * @return
	 */
	public List getAllLocations() throws EAPharmicsException {
		List<Location> list1 = adminFactory.getAllLocations();
		List<LocationVO> list = new ArrayList<LocationVO> ();
		for (Iterator<Location> iterator = list1.iterator(); iterator.hasNext();) {
			Location bomObject = (Location) iterator.next();
			LocationVO voObject = new LocationVO();
			list.add(populateLocationVO(bomObject, voObject));
		}
		return list;
	}

	/**
	 * @param keyName
	 * @return
	 * @throws EAPharmicsException
	 */
	public LocationVO getLocation(Long keyName) throws EAPharmicsException {
		Location bomObject = adminFactory.getLocation(keyName);
		LocationVO voObject = new LocationVO();
		voObject = populateLocationVO(bomObject, voObject);
		return voObject;
	}

	/**
	 * @param location
	 */
	public void createLocation(LocationVO voObject, UserVO userVO) throws EAPharmicsException {
		try {
			super.beginTransaction();
//			Location bomObject = adminFactory.getLocation(voObject.getLocationId()); 
			Location bomObject = null; 
			if (bomObject != null) {
				throw new EAPharmicsException("Location already exists for key. Unable to create!");
			}			
			bomObject = new Location();
			populateLocationBOM(bomObject, voObject);
			bomObject.setInsertDate(Utility.getCurrentUserLocationDateTime());
			bomObject.setInsertUser(userVO.getAppUserId());
			if (userVO != null) {
				//update Audit Id
				bomObject.setAuditId(userVO.getAuditId());
			}
			adminFactory.createLocation(bomObject);
			super.commitTransaction();
		} catch (Exception e) {
			logException("createLocation", e);
			try {
				super.rollbackTransaction();
			} catch (Throwable t) {
				logException("createLocation", e);				
			}
			throw new EAPharmicsException(e);
		}
	}

	/**
	 * @param voObject
	 * @throws EAPharmicsException
	 */
	public void updateLocation(LocationVO voObject, UserVO userVO) throws EAPharmicsException {
		try {
			super.beginTransaction();
			Location bomObject = adminFactory.getLocation(voObject.getLocationId()); 
			if (bomObject == null) {
				throw new EAPharmicsException("Cannot find Location. Unable to update!");
			}			
			populateLocationBOM(bomObject, voObject);
			bomObject.setUpdateDate(Utility.getCurrentUserLocationDateTime());
			bomObject.setUpdateUser(userVO.getAppUserId());
			if (userVO != null) {
				//update Audit Id
				bomObject.setAuditId(userVO.getAuditId());
			}
			adminFactory.updateLocation(bomObject);
			super.commitTransaction();
		} catch (Exception e) {
			logException("updateLocation", e);
			try {
				super.rollbackTransaction();
			} catch (Throwable t) {
				logException("updateLocation", e);				
			}
			throw new EAPharmicsException(e);
		}
	}

	/**
	 * @param location
	 */
	public void deleteLocation(Long locationId) throws EAPharmicsException {
		try {
			super.beginTransaction();
			adminFactory.deleteLocation(locationId);
			super.commitTransaction();
		} catch (Exception e) {
			logException("deleteLocation", e);
			try {
				super.rollbackTransaction();
			} catch (Throwable t) {
				logException("updateLocation", e);				
			}
			throw new EAPharmicsException(e);
		}
	}


	/**
	 * Copy the contents of BOM object to the VO object
	 * @param bomObject
	 * @param voObject
	 */
	public LocationVO populateLocationVO(Location bomObject, LocationVO voObject) {
		voObject.setLocationId(bomObject.getLocationId());
		voObject.setLocationName(bomObject.getLocationName());
		voObject.setActive(bomObject.getActive());
		voObject.setAddressLine1(bomObject.getAddressLine1());
		voObject.setCity(bomObject.getCity());
		voObject.setAddressLine2(bomObject.getAddressLine2());
		voObject.setState(bomObject.getState());
		voObject.setCountry(bomObject.getCountry());
		voObject.setPostalCode(bomObject.getPostalCode());
		voObject.setContactPhone(bomObject.getContactPhone());
		voObject.setContactPerson(bomObject.getContactPerson());
		voObject.setLocationDescription(bomObject.getLocationDescription());
		voObject.setTimezoneName(bomObject.getTimezoneName());
		voObject.setComments(bomObject.getComments());
		voObject.setDefaultLocale(bomObject.getDefaultLocale());
		voObject.setInsertDate(bomObject.getInsertDate());
		voObject.setInsertUser(bomObject.getInsertUser());
		voObject.setUpdateDate(bomObject.getUpdateDate());
		voObject.setUpdateUser(bomObject.getUpdateUser());
		voObject.setAuditId(bomObject.getAuditId());
		voObject.setStartDate(bomObject.getStartDate());
		voObject.setEndDate(bomObject.getEndDate());
		voObject.setLabel(bomObject.getLocationName());
		return voObject;

	}

	
	/**
	 * Copy the contents of VO object to the BOM object
	 * @param bomObject
	 * @param voObject
	 */
	public Location populateLocationBOM(Location bomObject, LocationVO voObject) {
		bomObject.setLocationName(voObject.getLocationName());
		bomObject.setActive(voObject.getActive());
		bomObject.setAddressLine1(voObject.getAddressLine1());
		bomObject.setAddressLine2(voObject.getAddressLine2());
		bomObject.setCity(voObject.getCity());
		bomObject.setState(voObject.getState());
		bomObject.setCountry(voObject.getCountry());
		bomObject.setPostalCode(voObject.getPostalCode());
		bomObject.setContactPhone(voObject.getContactPhone());
		bomObject.setContactPerson(voObject.getContactPerson());
		bomObject.setLocationDescription(voObject.getLocationDescription());
		bomObject.setTimezoneName(voObject.getTimezoneName());
		bomObject.setComments(voObject.getComments());
		bomObject.setDefaultLocale(voObject.getDefaultLocale());
		bomObject.setAuditId(voObject.getAuditId());
		bomObject.setStartDate(voObject.getStartDate());
		bomObject.setEndDate(voObject.getEndDate());
		return bomObject;

	}


	/**
	 * @param voObject
	 * @throws EAPharmicsException
	 */
	public void updateApplParameter(String parameterName, String parameterValue, UserVO userVO) throws EAPharmicsException {
		try {
			ApplParameter bomObject = adminFactory.getApplParameterByName(parameterName); 
			if (bomObject == null) {
				throw new EAPharmicsException("Cannot find ApplParameter. Unable to update!");
			}
			if (bomObject.getParameterValue().compareTo(parameterValue) != 0) {
				bomObject.setUpdateDate(Utility.getCurrentUserLocationDateTime());
				bomObject.setUpdateUser(userVO.getAppUserId());
				bomObject.setParameterValue(parameterValue);
				if (userVO != null) {
					//update Audit Id
					bomObject.setAuditId(userVO.getAuditId());
				}
				super.beginTransaction();
				adminFactory.updateApplParameter(bomObject);
				super.commitTransaction();
			}
		} catch (Exception e) {
			logException("updateApplParameter", e);
			try {
				super.rollbackTransaction();
			} catch (Throwable t) {
				logException("updateApplParameter", e);				
			}
			throw new EAPharmicsException(e);
		}
	}
	
	/**
	 * @return
	 */
	public List<TimezoneVO> getAllTimezoneNames() throws EAPharmicsException {
		try {
			return adminFactory.getAllTimezoneNames();
		} catch (Exception e) {
			throw new EAPharmicsException(e);
		}
	}

}
