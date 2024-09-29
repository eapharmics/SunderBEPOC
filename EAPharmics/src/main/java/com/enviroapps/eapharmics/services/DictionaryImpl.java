package com.enviroapps.eapharmics.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.enviroapps.eapharmics.bom.dictionary.DictionaryDetail;
import com.enviroapps.eapharmics.bom.dictionary.DictionaryMaster;
import com.enviroapps.eapharmics.common.Utility;
import com.enviroapps.eapharmics.exception.EAPharmicsException;
import com.enviroapps.eapharmics.persistence.DictionaryFactory;
import com.enviroapps.eapharmics.vo.dictionary.DictionaryDetailVO;
import com.enviroapps.eapharmics.vo.dictionary.DictionaryMasterVO;
import com.enviroapps.eapharmics.vo.security.UserVO;

/**
 * @author EnviroApps
 * 
 */
public class DictionaryImpl extends AbstractServiceImpl {


	private DictionaryFactory dictionaryFactory = DictionaryFactory.getInstance();

	/**
	 * @param dictionaryCode
	 * @return
	 * @throws EAPharmicsException
	 */
	public DictionaryMasterVO getDictionaryMasterForCode(String dictionaryCode) throws EAPharmicsException {
		DictionaryMaster bomObject = dictionaryFactory.getDictionaryMasterForCode(dictionaryCode);
		DictionaryMasterVO voObject = populateDictionaryMasterVOItem(bomObject);
		voObject.setDictionaryDetails(getDictionaryDetailsForCode(dictionaryCode));
		return voObject;
	}

	/**
	 * @return
	 */
	public List getAllDictionaryMasters() throws EAPharmicsException {
		List<DictionaryMaster> list = dictionaryFactory.getAllDictionaryMasters();
		return populateDictionaryMasterVO(list);
	}

	/**
	 * @param dictionaryMaster
	 */
	public void createDictionaryMaster(DictionaryMasterVO voObject) throws EAPharmicsException {
		try {
			super.beginTransaction();
			DictionaryMaster bomObject = new DictionaryMaster();
			bomObject.setDictionaryCode(voObject.getDictionaryCode());
			populateDictionaryMasterBOM(voObject, bomObject);
			bomObject.setInsertDate(Utility.getCurrentUserLocationDateTime());
			//bomObject.setInsertUser(insertUser);
			dictionaryFactory.createDictionaryMaster(bomObject);
			super.commitTransaction();
		} catch (Exception e) {
			logException("createDictionaryMaster", e);
			try {
				super.rollbackTransaction();
			} catch (Throwable t) {
				logException("createDictionaryMaster", e);				
			}
		}
	}

	/**
	 * @param voObject
	 * @throws EAPharmicsException
	 */
	public void updateDictionaryMaster(DictionaryMasterVO voObject) throws EAPharmicsException {
		try {
			super.beginTransaction();
			DictionaryMaster bomObject = dictionaryFactory.getDictionaryMaster(voObject.getEaDictionaryMasterId()); 
			populateDictionaryMasterBOM(voObject, bomObject);
			bomObject.setUpdateDate(Utility.getCurrentUserLocationDateTime());
			//bomObject.setUpdateUser(updateUser);
			dictionaryFactory.updateDictionaryMaster(bomObject);
			super.commitTransaction();
		} catch (Exception e) {
			logException("updateDictionaryMaster", e);
			try {
				super.rollbackTransaction();
			} catch (Throwable t) {
				logException("updateDictionaryMaster", e);				
			}
		}
	}

	/**
	 * @param dictionaryMaster
	 */
	public void deleteDictionaryMaster(Long dictionaryMasterId) throws EAPharmicsException {
		dictionaryFactory.deleteDictionaryMaster(dictionaryMasterId);
	}

	
	/**
	 * @param dictionaryMasterId
	 * @return
	 */
	public List getAllDictionaryDetails() throws EAPharmicsException {
		List<DictionaryDetail> list = dictionaryFactory.getAllDictionaryDetails();
		return populateDictionaryDetailVO(list);
	}

	/**
	 * @param dictionaryMasterId
	 * @return
	 */
	public List getAllDictionaryDetails(Long dictionaryMasterId) throws EAPharmicsException {
		List<DictionaryDetail> list = dictionaryFactory.getAllDictionaryDetails(dictionaryMasterId);
		return populateDictionaryDetailVO(list);
	}

	/**
	 * @param dictionaryCode
	 * @return
	 */
	public List<DictionaryDetailVO> getDictionaryDetailsForCode(String dictionaryCode) {
		List<DictionaryDetail> list = dictionaryFactory.getDictionaryDetailForCode(dictionaryCode);
		return populateDictionaryDetailVO(list);
	}
	
	/**
	 * @param voObject
	 * @throws EAPharmicsException
	 */
	public void createDictionaryDetail(DictionaryDetailVO voObject, UserVO userVO) throws EAPharmicsException {
		try {
			super.beginTransaction();
			if (dictionaryFactory.getDictionaryDetailForValue(voObject.getDictionaryCode(), voObject.getDictionaryCode()) != null ||
					(voObject.getEaDictionaryDetailId() != null && voObject.getEaDictionaryDetailId().longValue() != 0)) {
				throw new EAPharmicsException("Unable to add. Code already exists!");
			}
			
			DictionaryDetail bomObject = new DictionaryDetail();
			bomObject.setDictionaryCode(voObject.getDictionaryCode());
			populateDictionaryDetailBOM(voObject, bomObject);
			if (voObject.getDictionaryValue() == null ||
				voObject.getDictionaryCode().length() == 0) {
				//get a default next sequence and store it
				String code = dictionaryFactory.getDictionarySeq();
				bomObject.setDictionaryValue(code);
			} else {
				bomObject.setDictionaryValue(voObject.getDictionaryValue());
			}
			bomObject.setInsertDate(Utility.getCurrentUserLocationDateTime());
			bomObject.setDisplayOrder(
					dictionaryFactory.getMaxDetailDisplayOrder(voObject.getDictionaryCode())
					);
			if (userVO != null) {
				bomObject.setInsertUser(userVO.getAppUserId());
				bomObject.setInsertDate(Utility.getCurrentUserLocationDateTime());
				bomObject.setAuditId(userVO.getAuditId());
			}
			dictionaryFactory.createDictionaryDetail(bomObject);
			super.commitTransaction();
		} catch (Exception e) {
			logException("createDictionaryDetail", e);
			try {
				super.rollbackTransaction();
			} catch (Throwable t) {
				logException("createDictionaryDetail", e);				
			}
			throw new EAPharmicsException(e);
		}
	}

	/**
	 * @param voObject
	 * @throws EAPharmicsException
	 */
	public void updateDictionaryDetail(DictionaryDetailVO voObject, UserVO userVO) throws EAPharmicsException {
		try {
			super.beginTransaction();
			if (dictionaryFactory.getDictionaryDetail(voObject.getEaDictionaryDetailId()) == null) {
				throw new EAPharmicsException("Unable to edit. Code does not exist!");
			}
			DictionaryDetail bomObject = dictionaryFactory.getDictionaryDetail(voObject.getEaDictionaryDetailId());
			if (bomObject.getVersion() > voObject.getVersion()) {
				throw new EAPharmicsException("This record has been updated by another user. Please refresh data!");
			}
			populateDictionaryDetailBOM(voObject, bomObject);
			bomObject.setUpdateDate(Utility.getCurrentUserLocationDateTime());
			if (userVO != null) {
				bomObject.setUpdateUser(userVO.getAppUserId());
				bomObject.setUpdateDate(Utility.getCurrentUserLocationDateTime());
				bomObject.setAuditId(userVO.getAuditId());
			}
			dictionaryFactory.updateDictionaryDetail(bomObject);
			super.commitTransaction();
		} catch (Exception e) {
			logException("updateDictionaryDetail", e);
			try {
				super.rollbackTransaction();
			} catch (Throwable t) {
				logException("updateDictionaryDetail", e);				
			}
			throw new EAPharmicsException(e);
		}
	}
	
	/**
	 * @param voObjects
	 * @param userVO
	 * @throws EAPharmicsException
	 */
	public void updateDictionaryDetails(List voObjects, UserVO userVO) throws EAPharmicsException {
		try {
			super.beginTransaction();
			for (Iterator iterator = voObjects.iterator(); iterator.hasNext();) {
				DictionaryDetailVO voObject = (DictionaryDetailVO) iterator.next();
				if (dictionaryFactory.getDictionaryDetail(voObject.getEaDictionaryDetailId()) == null) {
					throw new EAPharmicsException("Unable to edit. Code does not exist!");
				}
				DictionaryDetail bomObject = dictionaryFactory.getDictionaryDetail(voObject.getEaDictionaryDetailId());
				populateDictionaryDetailBOM(voObject, bomObject);
				bomObject.setUpdateDate(Utility.getCurrentUserLocationDateTime());
				if (userVO != null) {
					bomObject.setUpdateUser(userVO.getAppUserId());
					bomObject.setUpdateDate(Utility.getCurrentUserLocationDateTime());
					bomObject.setAuditId(userVO.getAuditId());
				}
				dictionaryFactory.updateDictionaryDetail(bomObject);
			}
			super.commitTransaction();
		} catch (Exception e) {
			logException("updateDictionaryDetail", e);
			try {
				super.rollbackTransaction();
			} catch (Throwable t) {
				logException("updateDictionaryDetail", e);				
			}
			throw new EAPharmicsException(e);
		}
	}

	/**
	 * @param dictionaryDetailId
	 * @throws EAPharmicsException
	 */
	public void deleteDictionaryDetail(Long dictionaryDetailId, UserVO userVO) throws EAPharmicsException {
		try {
			super.beginTransaction();
			dictionaryFactory.deleteDictionaryDetail(dictionaryDetailId);
			//if we don't want to physically delete the record,
			//update the active flag. 
			//Do we also need to update the display Order on each of the other records?
			//If not, there will be gaps. It may be OK to leave it the way it is.
			super.commitTransaction();
		} catch (Exception e) {
			logException("deleteDictionaryDetail(Long, UserVO)", e);
			try {
				super.rollbackTransaction();
			} catch (Throwable t) {
				logException("deleteDictionaryDetail(Long, UserVO)", e);				
			}
			throw new EAPharmicsException(e);
		}
		
	}


	/**
	 * @param list1
	 */
	private List populateDictionaryMasterVO(List<DictionaryMaster> list1) {
		List<DictionaryMasterVO> list = new ArrayList<DictionaryMasterVO> ();
		for (Iterator<DictionaryMaster> iterator = list1.iterator(); iterator.hasNext();) {
			DictionaryMaster bomObject = (DictionaryMaster) iterator.next();
			//DictionaryMasterVO voObject = new DictionaryMasterVO();
			list.add(populateDictionaryMasterVOItem(bomObject));
		}
		return list;
	}

	/**
	 * @param bomObject
	 * @param voObject
	 */
	private DictionaryMasterVO populateDictionaryMasterVOItem(DictionaryMaster bomObject) {
		DictionaryMasterVO voObject = new DictionaryMasterVO();
		voObject.setEaDictionaryMasterId(bomObject.getEaDictionaryMasterId());
		voObject.setDictionaryCode(bomObject.getDictionaryCode());
		voObject.setDictionaryDescription(bomObject.getDictionaryDescription());
		voObject.setActive(bomObject.getActive());
		voObject.setGraphDictionary(bomObject.getGraphDictionary());
		voObject.setCustomField1(bomObject.getCustomField1());
		voObject.setCustomField2(bomObject.getCustomField2());
		voObject.setComments(bomObject.getComments());
		voObject.setInsertDate(bomObject.getInsertDate());
		voObject.setInsertUser(bomObject.getInsertUser());
		voObject.setUpdateDate(bomObject.getUpdateDate());
		voObject.setUpdateUser(bomObject.getUpdateUser());
		voObject.setAuditId(bomObject.getAuditId());
		voObject.setStartDate(bomObject.getStartDate());
		voObject.setEndDate(bomObject.getEndDate());
		voObject.setShowDescription(bomObject.getShowDescription());
		voObject.setAllowChangeDisplayOrder(bomObject.getAllowChangeDisplayOrder());
		voObject.setCodeDisplay(bomObject.getCodeDisplay());
		voObject.setValueDisplay(bomObject.getValueDisplay());
		
		return voObject;
	}

	/**
	 * @param voObject
	 * @param bomObject
	 */
	private void populateDictionaryMasterBOM(DictionaryMasterVO voObject,
			DictionaryMaster bomObject) {
		bomObject.setDictionaryDescription(voObject.getDictionaryDescription());
		bomObject.setActive(voObject.getActive());
		bomObject.setGraphDictionary(voObject.getGraphDictionary());
		bomObject.setCustomField1(voObject.getCustomField1());
		bomObject.setCustomField2(voObject.getCustomField2());
		bomObject.setComments(voObject.getComments());
		bomObject.setAuditId(voObject.getAuditId());
		bomObject.setStartDate(voObject.getStartDate());
		bomObject.setEndDate(voObject.getEndDate());
		bomObject.setShowDescription(voObject.getShowDescription());
		bomObject.setAllowChangeDisplayOrder(voObject.getAllowChangeDisplayOrder());
		bomObject.setCodeDisplay(voObject.getCodeDisplay());
		bomObject.setValueDisplay(voObject.getValueDisplay());
	}

	/**
	 * @param list1
	 */
	private List populateDictionaryDetailVO(List<DictionaryDetail> list1) {
		List<DictionaryDetailVO> list = new ArrayList<DictionaryDetailVO> ();
		for (Iterator<DictionaryDetail> iterator = list1.iterator(); iterator.hasNext();) {
			DictionaryDetail bomObject = (DictionaryDetail) iterator.next();
			DictionaryDetailVO voObject = new DictionaryDetailVO();
			voObject.setEaDictionaryDetailId(bomObject.getEaDictionaryDetailId());
			voObject.setDictionaryCode(bomObject.getDictionaryCode());
			voObject.setDictionaryValue(bomObject.getDictionaryValue());
			voObject.setDictionaryValueDescription(bomObject.getDictionaryValueDescription());
			voObject.setDisplayOrder(bomObject.getDisplayOrder());
			voObject.setCustomField1(bomObject.getCustomField1());
			voObject.setCustomField2(bomObject.getCustomField2());
			voObject.setGraphTo(bomObject.getGraphTo());
			voObject.setActive(bomObject.getActive());
			voObject.setComments(bomObject.getComments());
			voObject.setInsertDate(bomObject.getInsertDate());
			voObject.setInsertUser(bomObject.getInsertUser());
			voObject.setUpdateDate(bomObject.getUpdateDate());
			voObject.setUpdateUser(bomObject.getUpdateUser());
			voObject.setAuditId(bomObject.getAuditId());
			voObject.setVersion(bomObject.getVersion());
			voObject.setLocationId(bomObject.getLocationId());
			list.add(voObject);
		}
		return list;
	}

	/**
	 * @param voObject
	 * @param bomObject
	 */
	private void populateDictionaryDetailBOM(DictionaryDetailVO voObject,
			DictionaryDetail bomObject) {
		bomObject.setDictionaryValue(voObject.getDictionaryValue());
		bomObject.setDictionaryValueDescription(voObject.getDictionaryValueDescription());
		bomObject.setDisplayOrder(voObject.getDisplayOrder());
		bomObject.setCustomField1(voObject.getCustomField1());
		bomObject.setCustomField2(voObject.getCustomField2());
		bomObject.setGraphTo(voObject.getGraphTo());
		bomObject.setActive(voObject.getActive());
		bomObject.setComments(voObject.getComments());
		bomObject.setAuditId(voObject.getAuditId());
		if(voObject.getDictionaryValueDescription()==""){
			bomObject.setDictionaryValueDescription(voObject.getDictionaryValue());
		}
	}
	
	
/* Added By Ramya Srinivasan*/
   
   public List getAllDictionary() throws EAPharmicsException {
      List<DictionaryDetail> list = dictionaryFactory.getAllDictionary();
      return populateDictionaryDetailVO(list);
   }
   
	
	
}
