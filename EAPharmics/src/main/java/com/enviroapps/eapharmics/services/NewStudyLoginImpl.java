package com.enviroapps.eapharmics.services;

import java.io.File;
import java.io.FilenameFilter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import com.enviroapps.eapharmics.bom.newstudy.Label;
import com.enviroapps.eapharmics.bom.newstudy.LabelPrintRequest;
import com.enviroapps.eapharmics.bom.newstudy.LabelPrintRequestDetail;
import com.enviroapps.eapharmics.bom.newstudy.NewStudyCondition;
import com.enviroapps.eapharmics.bom.newstudy.NewStudyInterval;
import com.enviroapps.eapharmics.bom.newstudy.NewStudyProduct;
import com.enviroapps.eapharmics.bom.newstudy.NewStudyRawMaterial;
import com.enviroapps.eapharmics.bom.newstudy.NewStudySchedule;
import com.enviroapps.eapharmics.bom.newstudy.NewStudyTestResult;
import com.enviroapps.eapharmics.bom.newstudy.ProductStudyIntervals;
import com.enviroapps.eapharmics.bom.newstudy.StudyInventoryDestroyed;
import com.enviroapps.eapharmics.bom.product.ProductTest;
import com.enviroapps.eapharmics.common.Utility;
import com.enviroapps.eapharmics.exception.EAPharmicsException;
import com.enviroapps.eapharmics.persistence.AdminFactory;
import com.enviroapps.eapharmics.persistence.NewStudyLoginFactory;
import com.enviroapps.eapharmics.persistence.ProductFactory;
import com.enviroapps.eapharmics.regression.BatchData;
import com.enviroapps.eapharmics.regression.MultipleRegression;
import com.enviroapps.eapharmics.regression.SingleRegression;
import com.enviroapps.eapharmics.ui.Constants;
import com.enviroapps.eapharmics.vo.newstudy.CodeSwitchProductVO;
import com.enviroapps.eapharmics.vo.newstudy.DataEntryVO;
import com.enviroapps.eapharmics.vo.newstudy.FileAttachmentVO;
import com.enviroapps.eapharmics.vo.newstudy.GraphGeneratorVO;
import com.enviroapps.eapharmics.vo.newstudy.LabelPrintRequestDetailVO;
import com.enviroapps.eapharmics.vo.newstudy.LabelPrintRequestVO;
import com.enviroapps.eapharmics.vo.newstudy.NewStudyConditionVO;
import com.enviroapps.eapharmics.vo.newstudy.NewStudyIntervalVO;
import com.enviroapps.eapharmics.vo.newstudy.NewStudyProductLotVO;
import com.enviroapps.eapharmics.vo.newstudy.NewStudyProductVO;
import com.enviroapps.eapharmics.vo.newstudy.NewStudyRawMaterialVO;
import com.enviroapps.eapharmics.vo.newstudy.NewStudyScheduleVO;
import com.enviroapps.eapharmics.vo.newstudy.NewStudySummaryVO;
import com.enviroapps.eapharmics.vo.newstudy.NewStudyTestResultVO;
import com.enviroapps.eapharmics.vo.newstudy.ProductStudyIntervalsVO;
import com.enviroapps.eapharmics.vo.newstudy.RegressionDataContainerVO;
import com.enviroapps.eapharmics.vo.newstudy.RegressionDataVO;
import com.enviroapps.eapharmics.vo.newstudy.RegressionInputVO;
import com.enviroapps.eapharmics.vo.newstudy.ReportCountVO;
import com.enviroapps.eapharmics.vo.newstudy.RptUserPreferenceVO;
import com.enviroapps.eapharmics.vo.newstudy.StoredDataVO;
import com.enviroapps.eapharmics.vo.newstudy.StudyInventoryDestroyedVO;
import com.enviroapps.eapharmics.vo.product.ProductTestVO;
import com.enviroapps.eapharmics.vo.security.EditReasonVO;
import com.enviroapps.eapharmics.vo.security.UserVO;
/**
 * @author EnviroApps
 *
 */
public class NewStudyLoginImpl extends AbstractServiceImpl {


	private NewStudyLoginFactory newStudyLoginFactory = NewStudyLoginFactory.getInstance();
	private ProductFactory productFactory = ProductFactory.getInstance();

	/**
	 * @return populateNewStudyProductBOM
	 */
	public List getNewStudyProductsSummary(String barCode, String studyId,
			Long productId, String lotNumber,String year, Date fromDate, Date toDate,boolean unApprovedStudiesOnly)
		throws EAPharmicsException {
		return newStudyLoginFactory.getNewStudyProductsSummary(barCode, studyId,
				productId, lotNumber, year, fromDate, toDate, unApprovedStudiesOnly);
	}
	
	public List getNewStudyIntervalLabel(String studyId)
		throws EAPharmicsException {
		return newStudyLoginFactory.getNewStudyIntervalLabel(studyId);
	}

	/**
	 * @param keyName
	 * @return
	 * @throws EAPharmicsException
	 */
	public NewStudyProductVO getNewStudyProduct(Long keyName) throws EAPharmicsException {
		NewStudyProduct bomObject = newStudyLoginFactory.getNewStudyProduct(keyName);
		NewStudyProductVO voObject = new NewStudyProductVO();
		voObject = populateNewStudyProductVO(bomObject, voObject);
		return voObject;
	}
	
	
	public void createNewStudyProduct(NewStudyProductVO voObject, UserVO userVO)
	throws EAPharmicsException {
		try {
		   super.beginTransaction();
		   NewStudyProduct bomObject = null;
		   if (voObject.getPrdStudyBatchId() != null
		         && voObject.getPrdStudyBatchId().longValue() != 0) {
		      throw new EAPharmicsException(
		            "NewStudyProduct already exists for key. Unable to create!");
		   }
		   bomObject = new NewStudyProduct();
		   NewStudyCondition studyCondition = new NewStudyCondition();
		   bomObject.setStudyCondition(studyCondition);
		   newStudyLoginFactory.createNewStudyProduct(bomObject);
		   populateNewStudyProductBOM(bomObject, voObject, userVO);
		   bomObject.setInsertDate(Utility.getCurrentUserLocationDateTime());
		   bomObject.setInsertUser(userVO.getAppUserId());
		   bomObject.setVersion(1);
		   if (userVO != null) {
		      // update Audit Id
		      bomObject.setAuditId(userVO.getAuditId());
		   }
		   studyCondition.setPrdStudyBatchId(bomObject.getPrdStudyBatchId());
		   newStudyLoginFactory.createNewStudyCondition(studyCondition);
		   super.commitTransaction();
		} catch (Exception e) {
		   logException("createNewStudyProduct", e);
		   try {
		      super.rollbackTransaction();
		   } catch (Throwable t) {
		      logException("createNewStudyProduct", e);
		   }
		   throw new EAPharmicsException(e);
		}
	}


	

	/**
	 * @param voObject
	 * @throws EAPharmicsException
	 */
	public void updateNewStudyProduct(NewStudyProductVO voObject, List editReasons, UserVO userVO,Boolean deleteIntervalFlag) throws EAPharmicsException {
		try {
			boolean needToCreateLabel = false;
			String approvalLevel = AdminFactory.getInstance()
					.getApplParameterByName(Constants.NEW_STUDY_APPROVAL_LEVEL)
					.getParameterValue();
			super.beginTransaction();
			NewStudyProduct bomObject = newStudyLoginFactory.getNewStudyProduct(voObject.getPrdStudyBatchId());
			if (bomObject == null) {
				throw new EAPharmicsException("Cannot find NewStudyProduct. Unable to update!");
			}
			if (bomObject.getVersion() > voObject.getVersion()) {
				throw new EAPharmicsException("This record has been updated by another user. Please refresh data!");
			}
			if (bomObject.getApprovalLevel1() == null || bomObject.getApprovalLevel1() == false) {
				if (voObject.getApprovalLevel1() != null && voObject.getApprovalLevel1()) {
					if (approvalLevel.equals("S")) {
						needToCreateLabel = true;
					}
				}
			} else if (bomObject.getApprovalLevel2() == null || bomObject.getApprovalLevel2() == false) {
				if (voObject.getApprovalLevel2() != null && voObject.getApprovalLevel2()) {
					if (approvalLevel.equals("D")) {
						needToCreateLabel = true;
					}
				}
			}
//			if(deleteIntervalFlag){
//				newStudyLoginFactory.deleteNewStudyIntervalsForStudyId(voObject.getPrdStudyBatchId());
//			}
			populateNewStudyProductBOM(bomObject, voObject, userVO);
			bomObject.setUpdateDate(Utility.getCurrentUserLocationDateTime());
			bomObject.setUpdateUser(userVO.getAppUserId());
			if (userVO != null) {
				//update Audit Id
				bomObject.setAuditId(userVO.getAuditId());
			}
			newStudyLoginFactory.updateNewStudyProduct(bomObject);
			
			if (needToCreateLabel) {
				LabelPrintRequest labelRequest = new LabelPrintRequest();
				labelRequest.setPrdStudyBatchId(voObject.getPrdStudyBatchId());
				labelRequest.setLabelId(voObject.getLabelId());
				labelRequest.setLabelPrinted(Boolean.FALSE);
				labelRequest.setAuditId(userVO.getAuditId());
				labelRequest.setInsertDate(Utility.getCurrentUserLocationDateTime());
				labelRequest.setInsertUser(userVO.getAppUserId());
				if (userVO != null) {
					//update Audit Id
					labelRequest.setAuditId(userVO.getAuditId());
				}
				newStudyLoginFactory.createLabelPrintRequest(labelRequest);
			}
			
			if (editReasons != null && editReasons.size() > 0) {
				SecurityImpl sImpl = new SecurityImpl();
				for (Iterator iterator = editReasons.iterator(); iterator
						.hasNext();) {
					EditReasonVO reasonVO = (EditReasonVO) iterator.next();
					sImpl.createEditReason(reasonVO, userVO);					
				}
			}
			super.commitTransaction();
			if (bomObject.getApprovalLevel1())
   		{
			   // create the schedules.
            super.beginTransaction();
               newStudyLoginFactory.createScheduleForStudy(bomObject.getPrdStudyBatchId(), 
                                                           bomObject.getStudyId(),
                                                           userVO.getAppUserId(), 
                                                           userVO.getAuditId());
            super.commitTransaction();
   		}
		} catch (Exception e) {
			logException("updateNewStudyProduct", e);
			try {
				super.rollbackTransaction();
			} catch (Throwable t) {
				logException("updateNewStudyProduct", e);
			}
			throw new EAPharmicsException(e);
		}
	}

	/**
	 * @param newStudyProduct
	 */
	public void deleteNewStudyProduct(Long newStudyProductId) throws EAPharmicsException {
		try {
			super.beginTransaction();
			newStudyLoginFactory.deleteNewStudyProduct(newStudyProductId);
			super.commitTransaction();
		} catch (Exception e) {
			logException("deleteNewStudyProduct", e);
			try {
				super.rollbackTransaction();
			} catch (Throwable t) {
				logException("updateNewStudyProduct", e);
			}
			throw new EAPharmicsException(e);
		}
	}

	/**
	 * @param keyName
	 * @return
	 * @throws EAPharmicsException
	 */
	public NewStudyRawMaterialVO getNewStudyRawMaterial(Long keyName) throws EAPharmicsException {
		NewStudyRawMaterial bomObject = newStudyLoginFactory.getNewStudyRawMaterial(keyName);
		NewStudyRawMaterialVO voObject = new NewStudyRawMaterialVO();
		voObject = populateNewStudyRawMaterialVO(bomObject, voObject);
		return voObject;
	}

	/**
	 * @param voObject
	 * @throws EAPharmicsException
	 */
	public void updateNewStudyRawMaterial(NewStudyRawMaterialVO voObject, UserVO userVO) throws EAPharmicsException {
		try {
			super.beginTransaction();
			NewStudyRawMaterial bomObject = newStudyLoginFactory.getNewStudyRawMaterial(voObject.getPrdStudyRawMaterialId());
			if (bomObject == null) {
				throw new EAPharmicsException("Cannot find NewStudyRawMaterial. Unable to update!");
			}
			populateNewStudyRawMaterialBOM(bomObject, voObject);
			bomObject.setUpdateDate(Utility.getCurrentUserLocationDateTime());
			bomObject.setUpdateUser(userVO.getAppUserId());
			if (userVO != null) {
				//update Audit Id
				bomObject.setAuditId(userVO.getAuditId());
			}
			newStudyLoginFactory.updateNewStudyRawMaterial(bomObject);
			super.commitTransaction();
		} catch (Exception e) {
			logException("updateNewStudyRawMaterial", e);
			try {
				super.rollbackTransaction();
			} catch (Throwable t) {
				logException("updateNewStudyRawMaterial", e);
			}
			throw new EAPharmicsException(e);
		}
	}

	/**
	 * @param keyName
	 * @return
	 * @throws EAPharmicsException
	 */
	public NewStudyIntervalVO getNewStudyInterval(Long keyName) throws EAPharmicsException {
		NewStudyInterval bomObject = newStudyLoginFactory.getNewStudyInterval(keyName);
		NewStudyIntervalVO voObject = new NewStudyIntervalVO();
		voObject = populateNewStudyIntervalVO(bomObject, voObject);
		return voObject;
	}

	/**
	 * @param voObject
	 * @throws EAPharmicsException
	 */
	public void updateNewStudyInterval(NewStudyIntervalVO voObject, UserVO userVO) throws EAPharmicsException {
		try {
			super.beginTransaction();
			NewStudyInterval bomObject = newStudyLoginFactory.getNewStudyInterval(voObject.getPrdStudyIntervalId());
			if (bomObject == null) {
				throw new EAPharmicsException("Cannot find NewStudyInterval. Unable to update!");
			}
			populateNewStudyIntervalBOM(bomObject, voObject);
			bomObject.setUpdateDate(Utility.getCurrentUserLocationDateTime());
			bomObject.setUpdateUser(userVO.getAppUserId());
			if (userVO != null) {
				//update Audit Id
				bomObject.setAuditId(userVO.getAuditId());
			}
			newStudyLoginFactory.updateNewStudyInterval(bomObject);
			super.commitTransaction();
		} catch (Exception e) {
			logException("updateNewStudyInterval", e);
			try {
				super.rollbackTransaction();
			} catch (Throwable t) {
				logException("updateNewStudyInterval", e);
			}
			throw new EAPharmicsException(e);
		}
	}

	/**
	 * Copy the contents of BOM object to the VO object
	 * @param bomObject
	 * @param voObject
	 */
	public NewStudyProductVO populateNewStudyProductVO(NewStudyProduct bomObject, NewStudyProductVO voObject) {
		voObject.setPrdStudyBatchId(bomObject.getPrdStudyBatchId());
		voObject.setProductId(bomObject.getProductId());
		voObject.setStudyId(bomObject.getStudyId());
		voObject.setProprietaryStudyId(bomObject.getProprietaryStudyId());
		voObject.setStudyText(bomObject.getStudyText());
		voObject.setLotNumber(bomObject.getLotNumber());
		voObject.setExpiry(bomObject.getExpiry());
		voObject.setFormulaNumber(bomObject.getFormulaNumber());
		voObject.setBatchSize(bomObject.getBatchSize());
		voObject.setPackageDate(bomObject.getPackageDate());
		voObject.setMfgDate(bomObject.getMfgDate());
		voObject.setOnStabilityDate(bomObject.getOnStabilityDate());
		voObject.setPackageSizeCode(bomObject.getPackageSizeCode());
		voObject.setContainerCode(bomObject.getContainerCode());
		voObject.setContainerResinCode(bomObject.getContainerResinCode());
		voObject.setContainerManufacturerCode(bomObject.getContainerManufacturerCode());
		voObject.setClosureCode(bomObject.getClosureCode());
		voObject.setClosureManufacturerCode(bomObject.getClosureManufacturerCode());
		voObject.setInnerSealCode(bomObject.getInnerSealCode());
		voObject.setLinerCode(bomObject.getLinerCode());
		voObject.setFillerCode(bomObject.getFillerCode());
		voObject.setDesiccantCode(bomObject.getDesiccantCode());
		voObject.setBlisterCode(bomObject.getBlisterCode());
		voObject.setLinerManufacturerCode(bomObject.getLinerManufacturerCode());
		voObject.setOtherCode1(bomObject.getOtherCode1());
		voObject.setOtherManufacturerCode1(bomObject.getOtherManufacturerCode1());
		voObject.setOtherCode2(bomObject.getOtherCode2());
		voObject.setotherManufacturerCode2(bomObject.getOtherManufacturerCode2());
		voObject.setManufacturingSiteCode(bomObject.getManufacturingSiteCode());
		voObject.setPackagingSiteCode(bomObject.getPackagingSiteCode());
		voObject.setGallerySettingsCodeId(bomObject.getGallerySettingsCodeId());
		voObject.setStudyStatus(bomObject.getStudyStatus());
		voObject.setUserDefinedColumn1(bomObject.getUserDefinedColumn1());
		voObject.setUserDefinedColumn2(bomObject.getUserDefinedColumn2());
		voObject.setUserDefinedColumn3(bomObject.getUserDefinedColumn3());
		voObject.setUserDefinedColumn4(bomObject.getUserDefinedColumn4());
		voObject.setUserDefinedColumn5(bomObject.getUserDefinedColumn5());
		voObject.setUserDefinedColumn6(bomObject.getUserDefinedColumn6());
		voObject.setUserDefinedColumn7(bomObject.getUserDefinedColumn7());
		voObject.setUserDefinedColumn8(bomObject.getUserDefinedColumn8());
		voObject.setUserDefinedColumn9(bomObject.getUserDefinedColumn9());
		voObject.setUserDefinedColumn10(bomObject.getUserDefinedColumn10());
		voObject.setInsertDate(bomObject.getInsertDate());
		voObject.setInsertUser(bomObject.getInsertUser());
		voObject.setUpdateDate(bomObject.getUpdateDate());
		voObject.setUpdateUser(bomObject.getUpdateUser());
		voObject.setAuditId(bomObject.getAuditId());
		voObject.setComments(bomObject.getComments());
		voObject.setLabelId(bomObject.getLabelId());
		voObject.setApprovalLevel1(bomObject.getApprovalLevel1());
		voObject.setApprovalLevel1By(bomObject.getApprovalLevel1By());
		voObject.setApprovalLevel1Date(bomObject.getApprovalLevel1Date());
		voObject.setApprovalLevel2(bomObject.getApprovalLevel2());
		voObject.setApprovalLevel2By(bomObject.getApprovalLevel2By());
		voObject.setApprovalLevel2Date(bomObject.getApprovalLevel2Date());
		voObject.setVersion(bomObject.getVersion());
		voObject.setFillerManufacturerCode(bomObject.getFillerManufacturerCode());
		voObject.setDessicantManufacturerCode(bomObject.getDessicantManufacturerCode());
		voObject.setLiddingCode(bomObject.getLiddingCode());
		voObject.setFormingCode(bomObject.getFormingCode());
		voObject.setLocationId(bomObject.getLocationId());
		
		List rmVOs = new ArrayList();
		Object[] rawMaterials = bomObject.getRawMaterials().toArray();
		for(int i = 0; i < rawMaterials.length; i++)
      {
		   NewStudyRawMaterial rawMaterial = (NewStudyRawMaterial) rawMaterials[i];
         NewStudyRawMaterialVO rawMaterialVO = new NewStudyRawMaterialVO();
         rawMaterialVO = populateNewStudyRawMaterialVO(rawMaterial, rawMaterialVO);
         rmVOs.add(rawMaterialVO);
      }
		voObject.setRawMaterials(rmVOs);

      List intVOs = new ArrayList();
      Object[] intervals = bomObject.getIntervals().toArray();
      for(int i = 0; i < intervals.length; i++)
      {
         NewStudyInterval Interval = (NewStudyInterval) intervals[i];
         NewStudyIntervalVO intervalVO = new NewStudyIntervalVO();
         intervalVO = populateNewStudyIntervalVO(Interval, intervalVO);
         intVOs.add(intervalVO);
      }
      voObject.setIntervals(intVOs); 
      
   
      NewStudyConditionVO newStudyConditionVO = new NewStudyConditionVO();
      NewStudyCondition studyConditionBOM = newStudyLoginFactory.getAllNewStudyConditions(bomObject.getPrdStudyBatchId());;
      if (studyConditionBOM != null) {
         newStudyConditionVO = populateNewStudyConditionVO(studyConditionBOM, newStudyConditionVO);
      }
      voObject.setStudyCondition(newStudyConditionVO);

		return voObject;

	}
	/**
	 * Copy the contents of BOM object to the VO object
	 * @param bomObject
	 * @param voObject
	 */
	public NewStudyRawMaterialVO populateNewStudyRawMaterialVO(NewStudyRawMaterial bomObject, NewStudyRawMaterialVO voObject) {
		voObject.setPrdStudyRawMaterialId(bomObject.getPrdStudyRawMaterialId());
		voObject.setPrdStudyBatchId(bomObject.getPrdStudyBatchId());
		voObject.setProductFormulationId(bomObject.getProductFormulationId());
		voObject.setMfgSupplierCode(bomObject.getMfgSupplierCode());
		voObject.setLotNumber(bomObject.getLotNumber());
		voObject.setInsertDate(bomObject.getInsertDate());
		voObject.setInsertUser(bomObject.getInsertUser());
		voObject.setUpdateDate(bomObject.getUpdateDate());
		voObject.setUpdateUser(bomObject.getUpdateUser());
		voObject.setAuditId(bomObject.getAuditId());
		voObject.setComments(bomObject.getComments());
		voObject.setProductFormulation(bomObject.getProductFormulation());
		return voObject;

	}
	/**
	 * Copy the contents of BOM object to the VO object
	 * @param bomObject
	 * @param voObject
	 */
	public NewStudyConditionVO populateNewStudyConditionVO(NewStudyCondition bomObject, NewStudyConditionVO voObject) {
	   if (bomObject == null) {
	      return voObject;
	   }
		voObject.setPrdStudyConditionId(bomObject.getPrdStudyConditionId());
		voObject.setPrdStudyBatchId(bomObject.getPrdStudyBatchId());
		voObject.setStudyCondition(bomObject.getStudyCondition());
		voObject.setNormalEnvConditionsCode(bomObject.getNormalEnvConditionsCode());
		voObject.setChallengeConditionsCode(bomObject.getChallengeConditionsCode());
		voObject.setStudyReasonCode(bomObject.getStudyReasonCode());
		voObject.setStudyTestInterval(bomObject.getStudyTestInterval());
		voObject.setIntialTestDate(bomObject.getIntialTestDate());
		voObject.setStudyLengthCode(bomObject.getStudyLengthCode());
		voObject.setIntervalLabel(bomObject.getIntervalLabel());
		voObject.setProtocolId(bomObject.getProtocolId());
		voObject.setStorageLocationCode(bomObject.getStorageLocationCode());
		voObject.setTotalValue(bomObject.getTotalValue());
		voObject.setExtraValue(bomObject.getExtraValue());
		voObject.setInitialValue(bomObject.getInitialValue());
		voObject.setStudyInventoryComment(bomObject.getStudyInventoryComment());
		voObject.setLabelFormat(bomObject.getLabelFormat());
		voObject.setLabelColor(bomObject.getLabelColor());
		voObject.setInsertDate(bomObject.getInsertDate());
		voObject.setInsertUser(bomObject.getInsertUser());
		voObject.setUpdateDate(bomObject.getUpdateDate());
		voObject.setUpdateUser(bomObject.getUpdateUser());
		voObject.setAuditId(bomObject.getAuditId());
		voObject.setComments(bomObject.getComments());
		voObject.setfillUnits(bomObject.getfillUnits());
		voObject.setunitOfMeasurement(bomObject.getunitOfMeasurement());
		voObject.setExtraLabelCount(bomObject.getExtraLabelCount());
		return voObject;

	}
	/**
	 * Copy the contents of BOM object to the VO object
	 * @param bomObject
	 * @param voObject
	 */
	public NewStudyIntervalVO populateNewStudyIntervalVO(NewStudyInterval bomObject, NewStudyIntervalVO voObject) {
		voObject.setPrdStudyIntervalId(bomObject.getPrdStudyIntervalId());
		voObject.setPrdStudyBatchId(bomObject.getPrdStudyBatchId());
		voObject.setIntervalNumber(bomObject.getIntervalNumber());
		voObject.setIntervalDate(bomObject.getIntervalDate());
		voObject.setIntervalValue(bomObject.getIntervalValue());
		voObject.setUnits(bomObject.getUnits());
		voObject.setLabelCount(bomObject.getLabelCount());
		voObject.setPastInterval(bomObject.getPastInterval());
		voObject.setInsertDate(bomObject.getInsertDate());
		voObject.setInsertUser(bomObject.getInsertUser());
		voObject.setUpdateDate(bomObject.getUpdateDate());
		voObject.setUpdateUser(bomObject.getUpdateUser());
		voObject.setAuditId(bomObject.getAuditId());
		voObject.setComments(bomObject.getComments());
		return voObject;
	}
	/**
	 * Copy the contents of VO object to the BOM object
	 * @param bomObject
	 * @param voObject
	 */
	public NewStudyProduct populateNewStudyProductBOM(
			NewStudyProduct bomObject, NewStudyProductVO voObject,
			UserVO userVO) {
		bomObject.setProductId(voObject.getProductId());
		bomObject.setStudyId(voObject.getStudyId());
		bomObject.setProprietaryStudyId(voObject.getProprietaryStudyId());
		bomObject.setStudyText(voObject.getStudyText());
		bomObject.setLotNumber(voObject.getLotNumber());
		bomObject.setExpiry(voObject.getExpiry());
		bomObject.setFormulaNumber(voObject.getFormulaNumber());
		bomObject.setBatchSize(voObject.getBatchSize());
		bomObject.setPackageDate(voObject.getPackageDate());
		bomObject.setMfgDate(voObject.getMfgDate());
		bomObject.setOnStabilityDate(voObject.getOnStabilityDate());
		bomObject.setPackageSizeCode(voObject.getPackageSizeCode());
		bomObject.setContainerCode(voObject.getContainerCode());
		bomObject.setContainerResinCode(voObject.getContainerResinCode());
		bomObject.setContainerManufacturerCode(voObject.getContainerManufacturerCode());
		bomObject.setClosureCode(voObject.getClosureCode());
		bomObject.setClosureManufacturerCode(voObject.getClosureManufacturerCode());
		bomObject.setInnerSealCode(voObject.getInnerSealCode());
		bomObject.setLinerCode(voObject.getLinerCode());
		bomObject.setFillerCode(voObject.getFillerCode());
		bomObject.setDesiccantCode(voObject.getDesiccantCode());
		bomObject.setBlisterCode(voObject.getBlisterCode());
		bomObject.setLinerManufacturerCode(voObject.getLinerManufacturerCode());
		bomObject.setOtherCode1(voObject.getOtherCode1());
		bomObject.setOtherManufacturerCode1(voObject.getOtherManufacturerCode1());
		bomObject.setOtherCode2(voObject.getOtherCode2());
		bomObject.setOtherManufacturerCode2(voObject.getOtherManufacturerCode2());
		bomObject.setManufacturingSiteCode(voObject.getManufacturingSiteCode());
		bomObject.setPackagingSiteCode(voObject.getPackagingSiteCode());
		bomObject.setGallerySettingsCodeId(voObject.getGallerySettingsCodeId());
		bomObject.setStudyStatus(voObject.getStudyStatus());
		bomObject.setUserDefinedColumn1(voObject.getUserDefinedColumn1());
		bomObject.setUserDefinedColumn2(voObject.getUserDefinedColumn2());
		bomObject.setUserDefinedColumn3(voObject.getUserDefinedColumn3());
		bomObject.setUserDefinedColumn4(voObject.getUserDefinedColumn4());
		bomObject.setUserDefinedColumn5(voObject.getUserDefinedColumn5());
		bomObject.setUserDefinedColumn6(voObject.getUserDefinedColumn6());
		bomObject.setUserDefinedColumn7(voObject.getUserDefinedColumn7());
		bomObject.setUserDefinedColumn8(voObject.getUserDefinedColumn8());
		bomObject.setUserDefinedColumn9(voObject.getUserDefinedColumn9());
		bomObject.setUserDefinedColumn10(voObject.getUserDefinedColumn10());
		bomObject.setAuditId(voObject.getAuditId());
		bomObject.setComments(voObject.getComments());
		bomObject.setLabelId(voObject.getLabelId());
		bomObject.setApprovalLevel1(voObject.getApprovalLevel1());
		//When User is approving the study, update the approval date 
		//with the current date/time at the location timezone
		if (voObject.getIsLevel1Approval() != null && voObject.getIsLevel1Approval()) {
			Date d = Utility.getCurrentUserLocationDateTime();
			bomObject.setApprovalLevel1Date(d);
			bomObject.setApprovalLevel1By(userVO.getAppUserId());
		}
		bomObject.setApprovalLevel2(voObject.getApprovalLevel2());
		bomObject.setApprovalLevel2By(voObject.getApprovalLevel2By());
		bomObject.setApprovalLevel2Date(Utility.getCurrentUserLocationDateTime(voObject.getApprovalLevel2Date()));
		bomObject.setFillerManufacturerCode(voObject.getFillerManufacturerCode());
		bomObject.setDessicantManufacturerCode(voObject.getDessicantManufacturerCode());
		bomObject.setLiddingCode(voObject.getLiddingCode());
		bomObject.setFormingCode(voObject.getFormingCode());
		
      List list = voObject.getRawMaterials();
      for (Iterator iterator = list.iterator(); iterator.hasNext();) {
         NewStudyRawMaterialVO rawMaterialVO = (NewStudyRawMaterialVO) iterator.next();
         //If this is a new entry (no key will come in), add to the product RawMaterials
         if (rawMaterialVO.getPrdStudyRawMaterialId() == null ||
               rawMaterialVO.getPrdStudyRawMaterialId().longValue() == 0) {
            NewStudyRawMaterial batch = new NewStudyRawMaterial();
            newStudyLoginFactory.create(batch);
            batch.setPrdStudyBatchId(bomObject.getPrdStudyBatchId());
            populateNewStudyRawMaterialBOM(batch, rawMaterialVO);
            batch.setInsertDate(Utility.getCurrentUserLocationDateTime());
            batch.setInsertUser(userVO.getAppUserId());
            bomObject.getRawMaterials().add(batch);
            bomObject.setAuditId(userVO.getAuditId());
         } else {
            //update
            NewStudyRawMaterial batch = bomObject.getNewStudyRawMaterial(rawMaterialVO.getPrdStudyRawMaterialId());
            batch.setUpdateDate(Utility.getCurrentUserLocationDateTime());
            batch.setUpdateUser(userVO.getAppUserId());
            bomObject.setAuditId(userVO.getAuditId());
            if (batch != null) {
               populateNewStudyRawMaterialBOM(batch, rawMaterialVO);
            }
         }
      }

      //Process the Study Intervals
      list = voObject.getIntervals();

      //Make sure if intervals where deleted in the UI, we remove them from the DB
      List<NewStudyInterval> deletedObjects = new ArrayList<NewStudyInterval>();
      for (Iterator<NewStudyInterval> iterator = bomObject.getIntervals().iterator(); iterator.hasNext();) {
		NewStudyInterval bomInterval = (NewStudyInterval) iterator.next();
		boolean matchFound = false;
		for (int i=0; i<list.size(); i++) {
	         NewStudyIntervalVO intervalVO = (NewStudyIntervalVO) list.get(i);
	         if (intervalVO.getPrdStudyIntervalId() != null &&
	                 intervalVO.getPrdStudyIntervalId().longValue() != 0) {
	        	 if (intervalVO.getPrdStudyIntervalId().longValue() ==
	        		 bomInterval.getPrdStudyIntervalId().longValue()) {
	        		 matchFound = true;
	        	 }
	         }
		}		
        if (!matchFound) {
        	//Collect the objects to be deleted
        	deletedObjects.add(bomInterval);
        }
      }
      
      //Delete the collected objects
      for (Iterator iterator = deletedObjects.iterator(); iterator.hasNext();) {
			NewStudyInterval bomInterval = (NewStudyInterval) iterator.next();
			bomObject.getIntervals().remove(bomInterval);
			newStudyLoginFactory.delete(bomInterval);
      }
      
      for (Iterator iterator = list.iterator(); iterator.hasNext();) {
         NewStudyIntervalVO intervalVO = (NewStudyIntervalVO) iterator.next();
         //If this is a new entry (no key will come in), add to the product Intervals
         if (intervalVO.getPrdStudyIntervalId() == null ||
               intervalVO.getPrdStudyIntervalId().longValue() == 0) {
            NewStudyInterval batch = new NewStudyInterval();
            batch.setPrdStudyBatchId(bomObject.getPrdStudyBatchId());
            populateNewStudyIntervalBOM(batch, intervalVO);
            batch.setInsertDate(Utility.getCurrentUserLocationDateTime());
            batch.setInsertUser(userVO.getAppUserId());
            bomObject.getIntervals().add(batch);
            bomObject.setAuditId(userVO.getAuditId());
            newStudyLoginFactory.create(batch);
         } else {
            //update
            NewStudyInterval batch = bomObject.getNewStudyInterval(intervalVO.getPrdStudyIntervalId());
            batch.setUpdateDate(Utility.getCurrentUserLocationDateTime());
            batch.setUpdateUser(userVO.getAppUserId());
            bomObject.setAuditId(userVO.getAuditId());
            populateNewStudyIntervalBOM(batch, intervalVO);
         }
      }
      
      
      NewStudyCondition studyCondition = bomObject.getStudyCondition();
      populateNewStudyConditionBOM(studyCondition, voObject.getStudyCondition());
      bomObject.setStudyCondition(studyCondition);
      return bomObject;
      
	}
	/**
	 * Copy the contents of VO object to the BOM object
	 * @param bomObject
	 * @param voObject
	 */
	public NewStudyRawMaterial populateNewStudyRawMaterialBOM(NewStudyRawMaterial bomObject, NewStudyRawMaterialVO voObject) {
		bomObject.setProductFormulationId(voObject.getProductFormulationId());
		bomObject.setMfgSupplierCode(voObject.getMfgSupplierCode());
		bomObject.setLotNumber(voObject.getLotNumber());
		bomObject.setAuditId(voObject.getAuditId());
		bomObject.setComments(voObject.getComments());
		bomObject.setProductFormulation(voObject.getProductFormulation());
		return bomObject;

	}
	/**
	 * Copy the contents of VO object to the BOM object
	 * @param bomObject
	 * @param voObject
	 */
	public NewStudyCondition populateNewStudyConditionBOM(NewStudyCondition bomObject, NewStudyConditionVO voObject) {
		bomObject.setNormalEnvConditionsCode(voObject.getNormalEnvConditionsCode());
		bomObject.setChallengeConditionsCode(voObject.getChallengeConditionsCode());
		bomObject.setStudyCondition(voObject.getStudyCondition());
		bomObject.setStudyReasonCode(voObject.getStudyReasonCode());
		bomObject.setStudyTestInterval(voObject.getStudyTestInterval());
		bomObject.setIntialTestDate(voObject.getIntialTestDate());
		bomObject.setStudyLengthCode(voObject.getStudyLengthCode());
		bomObject.setIntervalLabel(voObject.getIntervalLabel());
		bomObject.setProtocolId(voObject.getProtocolId());
		bomObject.setStorageLocationCode(voObject.getStorageLocationCode());
		bomObject.setTotalValue(voObject.getTotalValue());
		bomObject.setExtraValue(voObject.getExtraValue());
		bomObject.setInitialValue(voObject.getInitialValue());
		bomObject.setStudyInventoryComment(voObject.getStudyInventoryComment());
		bomObject.setLabelFormat(voObject.getLabelFormat());
		bomObject.setLabelColor(voObject.getLabelColor());
		bomObject.setAuditId(voObject.getAuditId());
		bomObject.setComments(voObject.getComments());
		bomObject.setfillUnits(voObject.getfillUnits());
		bomObject.setunitOfMeasurement(voObject.getunitOfMeasurement());
		bomObject.setExtraLabelCount(voObject.getExtraLabelCount());
		return bomObject;

	}
	/**
	 * Copy the contents of VO object to the BOM object
	 * @param bomObject
	 * @param voObject
	 */
	public NewStudyInterval populateNewStudyIntervalBOM(NewStudyInterval bomObject, NewStudyIntervalVO voObject) {
	
		bomObject.setIntervalNumber(voObject.getIntervalNumber());
		bomObject.setIntervalDate(Utility.stripTime(voObject.getIntervalDate()));
		bomObject.setIntervalValue(voObject.getIntervalValue());
		bomObject.setUnits(voObject.getUnits());
		bomObject.setLabelCount(voObject.getLabelCount());
		bomObject.setPastInterval(voObject.getPastInterval());
		bomObject.setAuditId(voObject.getAuditId());
		bomObject.setComments(voObject.getComments());
		return bomObject;

	}

	/**
	 * @return
	 */
	public List getAllLabelPrintRequests() throws EAPharmicsException {
		List<LabelPrintRequest> list1 = newStudyLoginFactory.getAllLabelPrintRequests();
		List<LabelPrintRequestVO> list = new ArrayList<LabelPrintRequestVO> ();
		for (Iterator<LabelPrintRequest> iterator = list1.iterator(); iterator.hasNext();) {
			LabelPrintRequest bomObject = (LabelPrintRequest) iterator.next();
			LabelPrintRequestVO voObject = new LabelPrintRequestVO();
			list.add(populateLabelPrintRequestVO(bomObject, voObject));
		}
		return list;
	}
	
	/**
	 * @return
	 */
	public void deleteLabelPrintRequest(Long labelId, Long eaLabelPrintRequestId, UserVO userVO) throws EAPharmicsException {
		if (eaLabelPrintRequestId == null || eaLabelPrintRequestId.longValue() == 0) {
			throw new EAPharmicsException("Label Request Id is missing or invalid!");
		}
		try {
			super.beginTransaction();
    		NewStudyLoginFactory.getInstance().updatePendingLabelPrintRequest(labelId, eaLabelPrintRequestId, userVO.getAppUserId());
			super.commitTransaction();
		} catch (Exception e) {
			logException("deleteLabelPrintRequest", e);
			try {
				super.rollbackTransaction();
			} catch (Throwable t) {
				logException("deleteLabelPrintRequest", e);				
			}
			throw new EAPharmicsException(e);
		}
	}
	
	public List getSequenceId(Date stabilityDate) throws EAPharmicsException {

		log.debug(this, "getAllPharma in Pharma Impl",
				"********* getAllPharma *********");
		List list = newStudyLoginFactory.getSequenceId(stabilityDate);
		return list;
	}

	/**
	 * @return
	 */
	public List getAllPendingLabelPrintRequestsDisplay() throws EAPharmicsException {
		return newStudyLoginFactory.getAllPendingLabelPrintRequestsDisplay();
	}

	/**
	 * @return
	 */
	public List getAllPendingLabelPrintRequests(Long labelId) throws EAPharmicsException {
		List<LabelPrintRequest> list1 = newStudyLoginFactory.getAllPendingLabelPrintRequests(labelId);
		List<LabelPrintRequestVO> list = new ArrayList<LabelPrintRequestVO> ();
		for (Iterator<LabelPrintRequest> iterator = list1.iterator(); iterator.hasNext();) {
			LabelPrintRequest bomObject = (LabelPrintRequest) iterator.next();
			LabelPrintRequestVO voObject = new LabelPrintRequestVO();
			list.add(populateLabelPrintRequestVO(bomObject, voObject));
		}
		return list;
	}

	/**
	 * @param keyName
	 * @return
	 * @throws EAPharmicsException
	 */
	public LabelPrintRequestVO getLabelPrintRequest(Long keyName) throws EAPharmicsException {
		LabelPrintRequest bomObject = newStudyLoginFactory.getLabelPrintRequest(keyName);
		LabelPrintRequestVO voObject = new LabelPrintRequestVO();
		voObject = populateLabelPrintRequestVO(bomObject, voObject);
		return voObject;
	}

	/**
	 * @param labelPrintRequest
	 */
	public void createLabelPrintRequest(List<LabelPrintRequestVO> voObjects, List editReasons, 
			UserVO userVO) throws EAPharmicsException {
		try {
			super.beginTransaction();
			for (Iterator<LabelPrintRequestVO> iterator = voObjects.iterator(); iterator.hasNext();) {
				LabelPrintRequestVO voObject = iterator.next();
				LabelPrintRequest bomObject = null; 
				bomObject = new LabelPrintRequest();
				populateLabelPrintRequestBOM(bomObject, voObject);
				bomObject.setInsertDate(Utility.getCurrentUserLocationDateTime());
				bomObject.setInsertUser(userVO.getAppUserId());
				if (userVO != null) {
					//update Audit Id
					bomObject.setAuditId(userVO.getAuditId());
				}
				newStudyLoginFactory.createLabelPrintRequest(bomObject);
				//bomObject = (LabelPrintRequest) newStudyLoginFactory.registerObject(bomObject);
				if (voObject.getDetails() != null && voObject.getDetails().size() > 0) {
					for (Iterator<LabelPrintRequestDetailVO> iterator1 = voObject.getDetails().iterator(); iterator1
						.hasNext();) {
						LabelPrintRequestDetailVO detailVO = iterator1.next();
						LabelPrintRequestDetail detail = new LabelPrintRequestDetail();
						detail.setEaLabelPrintRequestsId(bomObject.getEaLabelPrintRequestsId());
						detail.setExtraLabel(detailVO.getExtraLabel());
						detail.setLabelNumber(detailVO.getLabelNumber());
						detail.setPrdStudyBatchId(detailVO.getPrdStudyBatchId());
						detail.setPrdStudyIntervalId(detailVO.getPrdStudyIntervalId());
						bomObject.getDetails().add(detail);
					}
				}
			}
			if (editReasons != null && editReasons.size() > 0) {
				SecurityImpl sImpl = new SecurityImpl();
				for (Iterator<EditReasonVO> iterator = editReasons.iterator(); iterator
						.hasNext();) {
					EditReasonVO reasonVO = iterator.next();
					sImpl.createEditReason(reasonVO, userVO);					
				}
			}
			super.commitTransaction();
		} catch (Exception e) {
			logException("createLabelPrintRequest", e);
			try {
				super.rollbackTransaction();
			} catch (Throwable t) {
				logException("createLabelPrintRequest", e);				
			}
			throw new EAPharmicsException(e);
		}
	}

	/**
	 * Copy the contents of BOM object to the VO object
	 * @param bomObject
	 * @param voObject
	 */
	public LabelPrintRequestVO populateLabelPrintRequestVO(LabelPrintRequest bomObject, LabelPrintRequestVO voObject) {
		voObject.setEaLabelPrintRequestsId(bomObject.getEaLabelPrintRequestsId());
		voObject.setPrdStudyBatchId(bomObject.getPrdStudyBatchId());
		voObject.setLabelId(bomObject.getLabelId());
		voObject.setInsertDate(bomObject.getInsertDate());
		voObject.setInsertUser(bomObject.getInsertUser());
		voObject.setUpdateDate(bomObject.getUpdateDate());
		voObject.setUpdateUser(bomObject.getUpdateUser());
		voObject.setAuditId(bomObject.getAuditId());
		voObject.setComments(bomObject.getComments());
		voObject.setLabelPrinted(bomObject.getLabelPrinted());
		voObject.setReason(bomObject.getReason());
		return voObject;

	}

	
	/**
	 * Copy the contents of VO object to the BOM object
	 * @param bomObject
	 * @param voObject
	 */
	public LabelPrintRequest populateLabelPrintRequestBOM(LabelPrintRequest bomObject, LabelPrintRequestVO voObject) {
		bomObject.setPrdStudyBatchId(voObject.getPrdStudyBatchId());
		bomObject.setLabelId(voObject.getLabelId());
		bomObject.setComments(voObject.getComments());
		bomObject.setLabelPrinted(voObject.getLabelPrinted());
		bomObject.setReason(voObject.getReason());
		return bomObject;

	}
	
	
	public List getNewStudyIntervalValueChartData(Long prdStudyBatchId) throws EAPharmicsException {
		List<NewStudyInterval> list1 = newStudyLoginFactory.getNewStudyIntervalValueChartData(prdStudyBatchId);
		List<NewStudyIntervalVO> list = new ArrayList<NewStudyIntervalVO> ();
		for (Iterator<NewStudyInterval> iterator = list1.iterator(); iterator.hasNext();) {
			NewStudyInterval bomObject = (NewStudyInterval) iterator.next();
			NewStudyIntervalVO voObject = new NewStudyIntervalVO();
			list.add(populateNewStudyIntervalVO(bomObject, voObject));
		}
		return list;
	}

	/**
	 * @param keyName
	 * @param checkForAllIntervalsCompleted
	 * @return
	 * @throws EAPharmicsException
	 */
	public NewStudyScheduleVO getNewStudySchedule(Long keyName, boolean checkForAllIntervalsCompleted) throws EAPharmicsException {
		NewStudySchedule bomObject = newStudyLoginFactory.getNewStudySchedule(keyName);
		NewStudyScheduleVO voObject = new NewStudyScheduleVO();
		voObject = populateNewStudyScheduleVO(bomObject, voObject);
		if (checkForAllIntervalsCompleted) {
			boolean val = newStudyLoginFactory.getCheckForAllIntervalsCompleted(
					voObject.getPrdStudyBatchId(), keyName);
			voObject.setAllIntervalsCompleted(val);
		}
		return voObject;
	}


	/**
	 * @param voObject
	 * @throws EAPharmicsException
	 */
	public void updateNewStudySchedule(NewStudyScheduleVO voObject, UserVO userVO) throws EAPharmicsException {
		try {
			super.beginTransaction();
			NewStudySchedule bomObject = newStudyLoginFactory.getNewStudySchedule(voObject.getPrdStudyScheduleId()); 
			if (bomObject == null) {
				throw new EAPharmicsException("Cannot find NewStudySchedule. Unable to update!");
			}			
			if (bomObject.getVersion() > voObject.getVersion()) {
				throw new EAPharmicsException("This record has been updated by another user. Please refresh data!");
			}
			populateNewStudyScheduleBOM(bomObject, voObject);
			bomObject.setUpdateDate(Utility.getCurrentUserLocationDateTime());
			bomObject.setUpdateUser(userVO.getAppUserId());
			if (userVO != null) {
				//update Audit Id
				bomObject.setAuditId(userVO.getAuditId());
			}
			newStudyLoginFactory.updateNewStudySchedule(bomObject);
			super.commitTransaction();
		} catch (Exception e) {
			logException("updateNewStudySchedule", e);
			try {
				super.rollbackTransaction();
			} catch (Throwable t) {
				logException("updateNewStudySchedule", e);				
			}
			throw new EAPharmicsException(e);
		}
	}

	/**
	 * Copy the contents of BOM object to the VO object
	 * @param bomObject
	 * @param voObject
	 */
	public NewStudyScheduleVO populateNewStudyScheduleVO(NewStudySchedule bomObject, NewStudyScheduleVO voObject) {
		voObject.setPrdStudyScheduleId(bomObject.getPrdStudyScheduleId());
		voObject.setPrdStudyBatchId(bomObject.getPrdStudyBatchId());
		voObject.setPrdStudyIntervalId(bomObject.getPrdStudyIntervalId());
		voObject.setScheduleDate(bomObject.getScheduleDate());
		voObject.setIntervalValue(bomObject.getIntervalValue());
		voObject.setUnitsPulled(bomObject.getUnitsPulled());
		voObject.setScheduleStatus(bomObject.getScheduleStatus());
		voObject.setScheduleStatusDate(bomObject.getScheduleStatusDate());
		voObject.setPendingStatusDate(bomObject.getPendingStatusDate());
		voObject.setCompletedStatusDate(bomObject.getCompletedStatusDate());
		voObject.setTerminatedStatusDate(bomObject.getTerminatedStatusDate());
		voObject.setDatePulled(bomObject.getDatePulled());
		voObject.setPulledBy(bomObject.getPulledBy());
		voObject.setTestStartDate(bomObject.getTestStartDate());
		voObject.setTestEndDate(bomObject.getTestEndDate());
		voObject.setInsertDate(bomObject.getInsertDate());
		voObject.setInsertUser(bomObject.getInsertUser());
		voObject.setUpdateDate(bomObject.getUpdateDate());
		voObject.setUpdateUser(bomObject.getUpdateUser());
		voObject.setAuditId(bomObject.getAuditId());
		voObject.setComments(bomObject.getComments());
		voObject.setExtraPulled(bomObject.getExtraPulled());
		voObject.setExtraPulledDate(bomObject.getExtraPulledDate());
		voObject.setExtraPulledBy(bomObject.getExtraPulledBy());
		voObject.setIntervalTestStatus(bomObject.getIntervalTestStatus());
		voObject.setIntervalTestStatusDate(bomObject.getIntervalTestStatusDate());
		voObject.setIntervalTestStatusBy(bomObject.getIntervalTestStatusBy());
		voObject.setApprovalLevel1(bomObject.getApprovalLevel1());
		voObject.setApprovalLevel1By(bomObject.getApprovalLevel1By());
		voObject.setApprovalLevel1Date(bomObject.getApprovalLevel1Date());
		voObject.setApprovalLevel2(bomObject.getApprovalLevel2());
		voObject.setApprovalLevel2By(bomObject.getApprovalLevel2By());
		voObject.setApprovalLevel2Date(bomObject.getApprovalLevel2Date());
		return voObject;

	}

	
	/**
	 * Copy the contents of VO object to the BOM object
	 * @param bomObject
	 * @param voObject
	 */
	public NewStudySchedule populateNewStudyScheduleBOM(NewStudySchedule bomObject, NewStudyScheduleVO voObject) {
		bomObject.setPrdStudyBatchId(voObject.getPrdStudyBatchId());
		bomObject.setPrdStudyIntervalId(voObject.getPrdStudyIntervalId());
		bomObject.setScheduleDate(voObject.getScheduleDate());
		bomObject.setIntervalValue(voObject.getIntervalValue());
		bomObject.setUnitsPulled(voObject.getUnitsPulled());
		bomObject.setScheduleStatus(voObject.getScheduleStatus());
		bomObject.setTerminatedStatusDate(voObject.getTerminatedStatusDate());
		bomObject.setDatePulled(voObject.getDatePulled());
		bomObject.setPulledBy(voObject.getPulledBy());
		bomObject.setTestStartDate(voObject.getTestStartDate());
		bomObject.setTestEndDate(voObject.getTestEndDate());
		bomObject.setAuditId(voObject.getAuditId());
		bomObject.setComments(voObject.getComments());
		bomObject.setExtraPulled(voObject.getExtraPulled());
		bomObject.setExtraPulledDate(voObject.getExtraPulledDate());
		bomObject.setExtraPulledBy(voObject.getExtraPulledBy());
		bomObject.setIntervalTestStatus(voObject.getIntervalTestStatus());
		bomObject.setIntervalTestStatusBy(voObject.getIntervalTestStatusBy());
		//these fields should be updated during update of Test Results
//		bomObject.setScheduleStatusDate(voObject.getScheduleStatusDate());
//		bomObject.setPendingStatusDate(voObject.getPendingStatusDate());
//		bomObject.setCompletedStatusDate(voObject.getCompletedStatusDate());
//		bomObject.setIntervalTestStatusDate(voObject.getIntervalTestStatusDate());
//		bomObject.setApprovalLevel1(voObject.getApprovalLevel1());
//		bomObject.setApprovalLevel1By(voObject.getApprovalLevel1By());
//		bomObject.setApprovalLevel1Date(voObject.getApprovalLevel1Date());
//		bomObject.setApprovalLevel2(voObject.getApprovalLevel2());
//		bomObject.setApprovalLevel2By(voObject.getApprovalLevel2By());
//		bomObject.setApprovalLevel2Date(voObject.getApprovalLevel2Date());
		return bomObject;

	}

	/**
	 * @return
	 */
	public List<NewStudySummaryVO> getAllNewStudySummaries(Date startDate, Date endDate) 
		throws EAPharmicsException {
		List<NewStudySummaryVO> list = newStudyLoginFactory.getAllNewStudySummaries(startDate, endDate);
		return list;
	}
	
	/**
	 * @param newStudyScheduleIds
	 * @param userVO
	 * @throws EAPharmicsException
	 */
	public List updateNewStudySchedules(List<Long> newStudyScheduleIds,String scheduleStatus,List<Long> returnScheduleIds,UserVO userVO) throws EAPharmicsException {
		try {
			super.beginTransaction();
			newStudyLoginFactory.updateNewStudySchedules(newStudyScheduleIds,scheduleStatus,userVO.getAppUserId(), userVO.getAuditId()); 
			super.commitTransaction();
		} catch (Exception e) {
			logException("updateNewStudySchedules", e);
			try {
				super.rollbackTransaction();
			} catch (Throwable t) {
				logException("updateNewStudySchedules", e);				
			}
			throw new EAPharmicsException(e);
		}
		return getAllNewStudySummaries(returnScheduleIds); 
	}
	public List<NewStudySummaryVO> getAllPendingLists() 
	throws EAPharmicsException {
	List<NewStudySummaryVO> list = newStudyLoginFactory.getAllPendingLists();
	return list;
    }
	

	/**
	 * @return
	 */
	public List getAllNewStudyTestResults(Long prdStudyScheduleId) throws EAPharmicsException {
		List<NewStudyTestResult> list1 = newStudyLoginFactory.getAllNewStudyTestResults(prdStudyScheduleId);
		
		//we need to get the corresponding product Test for each record
		//and send it back in the VO
		List testIds = new ArrayList();
		Hashtable results = new Hashtable<Long, NewStudyTestResult>();
		for (Iterator iterator = list1.iterator(); iterator.hasNext();) {
			NewStudyTestResult newStudyTestResult = (NewStudyTestResult) iterator
					.next();
			testIds.add(newStudyTestResult.getProductTestId());
			results.put(newStudyTestResult.getProductTestId(), newStudyTestResult);
		}
		List tests = new ProductImpl().getAllProductTestsForKeys(testIds);
		
		List<NewStudyTestResultVO> list = new ArrayList<NewStudyTestResultVO> ();
		for (Iterator iterator = tests.iterator(); iterator.hasNext();) {
			ProductTestVO productTestVO = (ProductTestVO) iterator.next();			
			NewStudyTestResult bomObject = (NewStudyTestResult) results.get(productTestVO.getProductTestId());
			NewStudyTestResultVO voObject = new NewStudyTestResultVO();
			voObject.setProductTestVO(productTestVO);
			list.add(populateNewStudyTestResultVO(bomObject, voObject));
		}
		return list;
	}
	
	public List getAllNewStudyTestResultsChartData(Long prdStudyBatchId,String studyId) throws EAPharmicsException {
		List<GraphGeneratorVO> list = newStudyLoginFactory.getAllNewStudyTestResultsChartData(prdStudyBatchId,studyId);
		return list;
	}
	
	


	/**
	 * @param keyName
	 * @return
	 * @throws EAPharmicsException
	 */
	public NewStudyTestResultVO getNewStudyTestResult(Long keyName) throws EAPharmicsException {
		NewStudyTestResult bomObject = newStudyLoginFactory.getNewStudyTestResult(keyName);
		NewStudyTestResultVO voObject = new NewStudyTestResultVO();
		voObject = populateNewStudyTestResultVO(bomObject, voObject);
		return voObject;
	}

	/**
	 * @param voObject
	 * @throws EAPharmicsException
	 */
	public void updateNewStudyTestResults(NewStudyScheduleVO scheduleVO,
			List voObjects, List additionalSchedulesToUpdate, List editReasons, UserVO userVO)
			throws EAPharmicsException {
		try {
			NewStudySchedule schedule = null;
			List bomObjects = null;
			schedule = newStudyLoginFactory.getNewStudySchedule(scheduleVO.getPrdStudyScheduleId());
			if (schedule == null) {
				throw new EAPharmicsException("Cannot find NewStudySchedule. Unable to update!");
			}			
			if (schedule.getVersion() > scheduleVO.getVersion()) {
				throw new EAPharmicsException("This record has been updated by another user. Please refresh data!");
			}
			bomObjects = newStudyLoginFactory.getAllNewStudyTestResults(schedule.getPrdStudyScheduleId());
			
			super.beginTransaction();
			
			for (Iterator iterator = voObjects.iterator(); iterator.hasNext();) {
				NewStudyTestResultVO voObject = (NewStudyTestResultVO) iterator.next();
				NewStudyTestResult bomObject = null;
				for (Iterator iterator2 = bomObjects.iterator(); iterator2.hasNext();) {
					NewStudyTestResult tempObject = (NewStudyTestResult) iterator2.next();
					if (voObject.getPrdStudyTestResultsId().longValue() == 
						tempObject.getPrdStudyTestResultsId().longValue()) {
						bomObject = tempObject;
						break;
					}
				}
				if (bomObject == null) {
					throw new EAPharmicsException("Cannot find NewStudyTestResult. Unable to update!");
				}			
				populateNewStudyTestResultBOM(bomObject, voObject);
				bomObject.setUpdateDate(Utility.getCurrentUserLocationDateTime());
				bomObject.setUpdateUser(userVO.getAppUserId());
				if (userVO != null) {
					//update Audit Id
					bomObject.setAuditId(userVO.getAuditId());
				}
				newStudyLoginFactory.updateNewStudyTestResult(bomObject);
			}

			if (schedule.getApprovalLevel1().booleanValue() != 
				scheduleVO.getApprovalLevel1().booleanValue()) {
				schedule.setApprovalLevel1(scheduleVO.getApprovalLevel1());
				schedule.setApprovalLevel1By(scheduleVO.getApprovalLevel1By());
				Date d = Utility.getCurrentUserLocationDateTime(scheduleVO.getInputApprovalLevel1Date());
				schedule.setApprovalLevel1Date(d);
			}
			if (schedule.getApprovalLevel2().booleanValue() != 
				scheduleVO.getApprovalLevel2().booleanValue()) {
				schedule.setApprovalLevel2(scheduleVO.getApprovalLevel2());
				schedule.setApprovalLevel2By(scheduleVO.getApprovalLevel2By());
				Date d = Utility.getCurrentUserLocationDateTime(scheduleVO.getInputApprovalLevel2Date());
				schedule.setApprovalLevel2Date(d);
			}
			
			schedule.setUpdateDate(Utility.getCurrentUserLocationDateTime());
			schedule.setUpdateUser(userVO.getAppUserId());
			schedule.setComments(scheduleVO.getComments());
			
			schedule.setScheduleStatus(scheduleVO.getScheduleStatus());
			schedule.setScheduleStatusDate(scheduleVO.getScheduleStatusDate());
			schedule.setPendingStatusDate(scheduleVO.getPendingStatusDate());
			
			schedule.setIntervalTestStatus(scheduleVO.getIntervalTestStatus());
			schedule.setIntervalTestStatusBy(scheduleVO.getIntervalTestStatusBy());
			schedule.setIntervalTestStatusDate(scheduleVO.getIntervalTestStatusDate());
			
			if (scheduleVO.getInputCompletedStatusDate() != null) {
				Date d = Utility.getCurrentUserLocationDateTime(scheduleVO.getInputCompletedStatusDate());
				schedule.setCompletedStatusDate(d);
			} else if (schedule.getApprovalLevel2().booleanValue() == false) {
				schedule.setCompletedStatusDate(null);
			}
			
			schedule.setVersion(schedule.getVersion() +  1);
			
			newStudyLoginFactory.updateNewStudySchedule(schedule);
			
			if (editReasons != null && editReasons.size() > 0) {
				SecurityImpl sImpl = new SecurityImpl();
				for (Iterator iterator = editReasons.iterator(); iterator
						.hasNext();) {
					EditReasonVO reasonVO = (EditReasonVO) iterator.next();
					sImpl.createEditReason(reasonVO, userVO);
				}
			}

			if (scheduleVO.getScheduleStatus().equals(Constants.STATUS_COMPLETE) &&
					scheduleVO.getAllIntervalsCompleted()) {
				//Data Entry for all tests for all schedules of the study
				//are completed. Update the study status to complete;
				newStudyLoginFactory.updateStudyStatus(scheduleVO.getPrdStudyBatchId(), userVO, Constants.STATUS_COMPLETE);
			}
			
			super.commitTransaction();
		} catch (Exception e) {
			logException("updateNewStudyTestResult", e);
			try {
				super.rollbackTransaction();
			} catch (Throwable t) {
				logException("updateNewStudyTestResult", e);				
			}
			throw new EAPharmicsException(e);
		}
	}

	/**
	 * @param scheduleVO
	 * @param voObjects
	 * @param editReasons
	 * @param userVO
	 * @throws EAPharmicsException
	 */
	public void updateCDSTestUsageForm(NewStudyScheduleVO scheduleVO,
			List voObjects, List editReasons, UserVO userVO)
			throws EAPharmicsException {
		try {
			List bomObjects = null;
			NewStudySchedule schedule = newStudyLoginFactory
					.getNewStudySchedule(scheduleVO.getPrdStudyScheduleId());
			if (schedule == null) {
				throw new EAPharmicsException("Cannot find NewStudySchedule. Unable to update!");
			}			
			if (schedule.getVersion() > scheduleVO.getVersion()) {
				throw new EAPharmicsException("This record has been updated by another user. Please refresh data!");
			}
			bomObjects = newStudyLoginFactory.getAllNewStudyTestResults(schedule.getPrdStudyScheduleId());
			super.beginTransaction();
			for (Iterator iterator = voObjects.iterator(); iterator.hasNext();) {
				NewStudyTestResultVO voObject = (NewStudyTestResultVO) iterator.next();
				NewStudyTestResult bomObject = null;
				for (Iterator iterator2 = bomObjects.iterator(); iterator2.hasNext();) {
					NewStudyTestResult tempObject = (NewStudyTestResult) iterator2.next();
					if (voObject.getPrdStudyTestResultsId().longValue() == 
						tempObject.getPrdStudyTestResultsId().longValue()) {
						bomObject = tempObject;
						break;
					}
				}
				if (bomObject == null) {
					throw new EAPharmicsException("Cannot find NewStudyTestResult. Unable to update!");
				}			
				bomObject.setFillUnitsUsed(voObject.getFillUnitsUsed());
				bomObject.setUsedDate(Utility.stripTime(voObject.getUsedDate()));
				bomObject.setUsedBy(voObject.getUsedBy());
				bomObject.setUpdateDate(Utility.getCurrentUserLocationDateTime());
				bomObject.setUpdateUser(userVO.getAppUserId());
				bomObject.setAuditId(userVO.getAuditId());
				newStudyLoginFactory.updateNewStudyTestResult(bomObject);
			}
			schedule.setUpdateDate(Utility.getCurrentUserLocationDateTime());
			schedule.setUpdateUser(userVO.getAppUserId());
			newStudyLoginFactory.updateNewStudySchedule(schedule);
			
			if (editReasons != null && editReasons.size() > 0) {
				SecurityImpl sImpl = new SecurityImpl();
				for (Iterator iterator = editReasons.iterator(); iterator
						.hasNext();) {
					EditReasonVO reasonVO = (EditReasonVO) iterator.next();
					sImpl.createEditReason(reasonVO, userVO);
				}
			}

			super.commitTransaction();
		} catch (Exception e) {
			logException("updateCDSTestUsageForm", e);
			try {
				super.rollbackTransaction();
			} catch (Throwable t) {
				logException("updateCDSTestUsageForm", e);				
			}
			throw new EAPharmicsException(e);
		}
	}

	/**
	 * Copy the contents of BOM object to the VO object
	 * @param bomObject
	 * @param voObject
	 */
	public NewStudyTestResultVO populateNewStudyTestResultVO(NewStudyTestResult bomObject, NewStudyTestResultVO voObject) {
		voObject.setPrdStudyTestResultsId(bomObject.getPrdStudyTestResultsId());
		voObject.setPrdStudyScheduleId(bomObject.getPrdStudyScheduleId());
		voObject.setProductTestId(bomObject.getProductTestId());
		voObject.setAnalyst(bomObject.getAnalyst());
		voObject.setReference(bomObject.getReference());
		voObject.setResultDate(bomObject.getResultDate());
		voObject.setResultStatus(bomObject.getResultStatus());
		voObject.setResult1(bomObject.getResult1());
		voObject.setResult2(bomObject.getResult2());
		voObject.setResult3(bomObject.getResult3());
		voObject.setResult4(bomObject.getResult4());
		voObject.setResult5(bomObject.getResult5());
		voObject.setResult6(bomObject.getResult6());
		voObject.setResult7(bomObject.getResult7());
		voObject.setResult8(bomObject.getResult8());
		voObject.setResult9(bomObject.getResult9());
		voObject.setResult10(bomObject.getResult10());
		voObject.setResult11(bomObject.getResult11());
		voObject.setResult12(bomObject.getResult12());
		voObject.setResult13(bomObject.getResult13());
		voObject.setResult14(bomObject.getResult14());
		voObject.setResult15(bomObject.getResult15());
		voObject.setResult16(bomObject.getResult16());
		voObject.setResult17(bomObject.getResult17());
		voObject.setResult18(bomObject.getResult18());
		voObject.setResult19(bomObject.getResult19());
		voObject.setResult20(bomObject.getResult20());
		voObject.setResult21(bomObject.getResult21());
		voObject.setResult22(bomObject.getResult22());
		voObject.setResult23(bomObject.getResult23());
		voObject.setResult24(bomObject.getResult24());
		voObject.setResult25(bomObject.getResult25());
		voObject.setResult26(bomObject.getResult26());
		voObject.setResult27(bomObject.getResult27());
		voObject.setResult28(bomObject.getResult28());
		voObject.setResult29(bomObject.getResult29());
		voObject.setResult30(bomObject.getResult30());
		voObject.setInsertDate(bomObject.getInsertDate());
		voObject.setInsertUser(bomObject.getInsertUser());
		voObject.setUpdateDate(bomObject.getUpdateDate());
		voObject.setUpdateUser(bomObject.getUpdateUser());
		voObject.setAuditId(bomObject.getAuditId());
		voObject.setComments(bomObject.getComments());
		voObject.setPrdStudyBatchId(bomObject.getPrdStudyBatchId());
		voObject.setStudyId(bomObject.getStudyId());
		voObject.setTestIntervalUsed(bomObject.getTestIntervalUsed());
		voObject.setResultStatus(bomObject.getResultStatus());
		voObject.setFillUnitsUsed(bomObject.getFillUnitsUsed());
		voObject.setUsedBy(bomObject.getUsedBy());
		voObject.setUsedDate(bomObject.getUsedDate());
		voObject.setResultAverage(bomObject.getResultAverage());
		
		return voObject;

	}

	
	/**
	 * Copy the contents of VO object to the BOM object
	 * @param bomObject
	 * @param voObject
	 */
	public NewStudyTestResult populateNewStudyTestResultBOM(NewStudyTestResult bomObject, NewStudyTestResultVO voObject) {
		bomObject.setPrdStudyScheduleId(voObject.getPrdStudyScheduleId());
		bomObject.setProductTestId(voObject.getProductTestId());
		bomObject.setAnalyst(voObject.getAnalyst());
		bomObject.setReference(voObject.getReference());
		bomObject.setResultDate(voObject.getResultDate());
		bomObject.setResultStatus(voObject.getResultStatus());
		bomObject.setResult1(voObject.getResult1());
		bomObject.setResult2(voObject.getResult2());
		bomObject.setResult3(voObject.getResult3());
		bomObject.setResult4(voObject.getResult4());
		bomObject.setResult5(voObject.getResult5());
		bomObject.setResult6(voObject.getResult6());
		bomObject.setResult7(voObject.getResult7());
		bomObject.setResult8(voObject.getResult8());
		bomObject.setResult9(voObject.getResult9());
		bomObject.setResult10(voObject.getResult10());
		bomObject.setResult11(voObject.getResult11());
		bomObject.setResult12(voObject.getResult12());
		bomObject.setResult13(voObject.getResult13());
		bomObject.setResult14(voObject.getResult14());
		bomObject.setResult15(voObject.getResult15());
		bomObject.setResult16(voObject.getResult16());
		bomObject.setResult17(voObject.getResult17());
		bomObject.setResult18(voObject.getResult18());
		bomObject.setResult19(voObject.getResult19());
		bomObject.setResult20(voObject.getResult20());
		bomObject.setResult21(voObject.getResult21());
		bomObject.setResult22(voObject.getResult22());
		bomObject.setResult23(voObject.getResult23());
		bomObject.setResult24(voObject.getResult24());
		bomObject.setResult25(voObject.getResult25());
		bomObject.setResult26(voObject.getResult26());
		bomObject.setResult27(voObject.getResult27());
		bomObject.setResult28(voObject.getResult28());
		bomObject.setResult29(voObject.getResult29());
		bomObject.setResult30(voObject.getResult30());
		bomObject.setAuditId(voObject.getAuditId());
		bomObject.setComments(voObject.getComments());
		bomObject.setPrdStudyBatchId(voObject.getPrdStudyBatchId());
		bomObject.setStudyId(voObject.getStudyId());
		bomObject.setTestIntervalUsed(voObject.getTestIntervalUsed());
		bomObject.setResultStatus(voObject.getResultStatus());
		bomObject.setFillUnitsUsed(voObject.getFillUnitsUsed());
		bomObject.setUsedBy(voObject.getUsedBy());
		bomObject.setUsedDate(voObject.getUsedDate());
		bomObject.setResultAverage(voObject.getResultAverage());
		return bomObject;
	}

	/**
	 * @return
	 */
	public List<NewStudySummaryVO> getAllNewStudySummaries(
			String scheduleStatus, String barCode, String studyId,
			String productCode, String lotNumber, String productType,
			Date startDate, Date endDate, Boolean level1Approval, Boolean finalApproval,Boolean pulledScheduleOnly,
			Boolean forLMM) 
		throws EAPharmicsException {
		return newStudyLoginFactory
				.getAllNewStudySummaries(scheduleStatus, barCode, studyId,
						productCode, lotNumber, productType, startDate, endDate,
						level1Approval, finalApproval,pulledScheduleOnly,forLMM);
	}

	  public List<NewStudySummaryVO> getAllNewStudySummaries(List scheduleIds)
	   throws EAPharmicsException {
      return newStudyLoginFactory.getAllNewStudySummaries(scheduleIds);
	  }
	
	public List<NewStudySummaryVO> updateInventoryPullDate(List<NewStudySummaryVO> scheduleIds,String pulledBy,Date pullDate,int unitsPulled,int extrasPulled,UserVO userVO,List<EditReasonVO> editReasons,List<NewStudySummaryVO> allScheduleIds) throws EAPharmicsException {
      try {
         super.beginTransaction();
         newStudyLoginFactory.updateInventoryPullDate(scheduleIds,pulledBy,pullDate,unitsPulled,extrasPulled,userVO);
         if (editReasons != null && editReasons.size() > 0) {
            SecurityImpl sImpl = new SecurityImpl();
            for (Iterator iterator = editReasons.iterator(); iterator
                  .hasNext();) {
               EditReasonVO reasonVO = (EditReasonVO) iterator.next();
               sImpl.createEditReason(reasonVO, userVO);
            }
         }         
         super.commitTransaction();
         
      } catch (Exception e) {
         logException("updateInventoryPullDate", e);
         try {
            super.rollbackTransaction();
         } catch (Throwable t) {
            logException("updateInventoryPullDate", e);           
         }
         throw new EAPharmicsException(e);
      }
      return newStudyLoginFactory.getAllNewStudySummaries(allScheduleIds);
	}
   /**
    * @return
    */
   public List<NewStudySummaryVO> getAllNewStudySummaries(Date startDate, Date endDate,String scheduleStatus) 
      throws EAPharmicsException {
      List<NewStudySummaryVO> list = newStudyLoginFactory.getAllNewStudySummaries(startDate, endDate,scheduleStatus);
      return list;
   }

	/**
	 * @return
	 */
	public ReportCountVO getPendingReportCounts() throws EAPharmicsException {
		return newStudyLoginFactory.getPendingReportCounts();
	}

   public List<NewStudySummaryVO> updateInventoryTestStartDate(List<NewStudySummaryVO> scheduleIds,Date testStartDate, UserVO userVO,List<EditReasonVO> editReasons,List<NewStudySummaryVO> allScheduleIds) throws EAPharmicsException {
      try {
         super.beginTransaction();
         newStudyLoginFactory.updateInventoryTestStartDate(scheduleIds,testStartDate,userVO);
         if (editReasons != null && editReasons.size() > 0) {
            SecurityImpl sImpl = new SecurityImpl();
            for (Iterator iterator = editReasons.iterator(); iterator
                  .hasNext();) {
               EditReasonVO reasonVO = (EditReasonVO) iterator.next();
               sImpl.createEditReason(reasonVO, userVO);
            }
         }           
         super.commitTransaction();
      } catch (Exception e) {
         logException("updateInventoryTestStartDate", e);
         try {
            super.rollbackTransaction();
         } catch (Throwable t) {
            logException("updateInventoryTestStartDate", e);           
         }
         throw new EAPharmicsException(e);
      }
      return newStudyLoginFactory.getAllNewStudySummaries(allScheduleIds);
   }  

   public List<NewStudySummaryVO> updateInventoryTestEndDate(List<NewStudySummaryVO> scheduleIds,Date testEndDate, UserVO userVO,List<EditReasonVO> editReasons,List<NewStudySummaryVO> allScheduleIds) throws EAPharmicsException {
      try {
         super.beginTransaction();
         newStudyLoginFactory.updateInventoryTestEndDate(scheduleIds,testEndDate,userVO);
         if (editReasons != null && editReasons.size() > 0) {
            SecurityImpl sImpl = new SecurityImpl();
            for (Iterator iterator = editReasons.iterator(); iterator
                  .hasNext();) {
               EditReasonVO reasonVO = (EditReasonVO) iterator.next();
               sImpl.createEditReason(reasonVO, userVO);
            }
         }           
         super.commitTransaction();
      } catch (Exception e) {
         logException("updateInventoryTestEndDate", e);
         try {
            super.rollbackTransaction();
         } catch (Throwable t) {
            logException("updateInventoryTestEndDate", e);           
         }
         throw new EAPharmicsException(e);
      }
      return newStudyLoginFactory.getAllNewStudySummaries(allScheduleIds);
   } 
   public List<NewStudySummaryVO> updateInventoryInitiateDate(List<NewStudySummaryVO> scheduleIds, String updateUser,Date initiateDate,UserVO userVO,List<EditReasonVO> editReasons,List<NewStudySummaryVO> allScheduleIds) throws EAPharmicsException {
      try {
         super.beginTransaction();
         newStudyLoginFactory.updateInventoryInitiateDate(scheduleIds,updateUser,initiateDate,userVO);
         if (editReasons != null && editReasons.size() > 0) {
            SecurityImpl sImpl = new SecurityImpl();
            for (Iterator iterator = editReasons.iterator(); iterator
                  .hasNext();) {
               EditReasonVO reasonVO = (EditReasonVO) iterator.next();
               sImpl.createEditReason(reasonVO, userVO);
            }
         }           
         super.commitTransaction();
      } catch (Exception e) {
         logException("updateInventoryInitialDate", e);
         try {
            super.rollbackTransaction();
         } catch (Throwable t) {
            logException("updateInventoryInitialDate", e);           
         }
         throw new EAPharmicsException(e);
      }
      return newStudyLoginFactory.getAllNewStudySummaries(allScheduleIds);
   }
   
	/**
	 * @param prdStudyScheduleId
	 * @return
	 * @throws EAPharmicsException
	 */
	public List getAllStudyInventoryDestroyeds(Long prdStudyScheduleId) throws EAPharmicsException {
		List<StudyInventoryDestroyed> list1 = newStudyLoginFactory.getAllStudyInventoryDestroyeds(prdStudyScheduleId);
		List<StudyInventoryDestroyedVO> list = new ArrayList<StudyInventoryDestroyedVO> ();
		for (Iterator<StudyInventoryDestroyed> iterator = list1.iterator(); iterator.hasNext();) {
			StudyInventoryDestroyed bomObject = (StudyInventoryDestroyed) iterator.next();
			StudyInventoryDestroyedVO voObject = new StudyInventoryDestroyedVO();
			list.add(populateStudyInventoryDestroyedVO(bomObject, voObject));
		}
		return list;
	}

	/**
	 * @param studyInventoryDestroyed
	 */
	public void saveStudyInventoryDestroyed(StudyInventoryDestroyedVO voObject, List editReasons, UserVO userVO) throws EAPharmicsException {
		try {
			super.beginTransaction();
			StudyInventoryDestroyed bomObject = null;
			if (voObject.getPrdStudyInvDestroyedId() != null && voObject.getPrdStudyInvDestroyedId().longValue() != 0) {
				bomObject = newStudyLoginFactory.
					getStudyInventoryDestroyed(voObject.getPrdStudyInvDestroyedId());
			}
			if (bomObject == null) {
				bomObject = new StudyInventoryDestroyed();
				bomObject.setInsertDate(Utility.getCurrentUserLocationDateTime());
				bomObject.setInsertUser(userVO.getAppUserId());
			} else {
				bomObject.setUpdateDate(Utility.getCurrentUserLocationDateTime());
				bomObject.setUpdateUser(userVO.getAppUserId());
			}
			populateStudyInventoryDestroyedBOM(bomObject, voObject);
			if (userVO != null) {
				//update Audit Id
				bomObject.setAuditId(userVO.getAuditId());
			}
			newStudyLoginFactory.createStudyInventoryDestroyed(bomObject);

			if (editReasons != null && editReasons.size() > 0) {
				SecurityImpl sImpl = new SecurityImpl();
				for (Iterator iterator = editReasons.iterator(); iterator
						.hasNext();) {
					EditReasonVO reasonVO = (EditReasonVO) iterator.next();
					sImpl.createEditReason(reasonVO, userVO);
				}
			}
			
			super.commitTransaction();
		} catch (Exception e) {
			logException("createStudyInventoryDestroyed", e);
			try {
				super.rollbackTransaction();
			} catch (Throwable t) {
				logException("createStudyInventoryDestroyed", e);				
			}
			throw new EAPharmicsException(e);
		}
	}

	/**
	 * Copy the contents of BOM object to the VO object
	 * @param bomObject
	 * @param voObject
	 */
	public StudyInventoryDestroyedVO populateStudyInventoryDestroyedVO(StudyInventoryDestroyed bomObject, StudyInventoryDestroyedVO voObject) {
		voObject.setPrdStudyInvDestroyedId(bomObject.getPrdStudyInvDestroyedId());
		voObject.setPrdStudyScheduleId(bomObject.getPrdStudyScheduleId());
		voObject.setUnitsDestroyed(bomObject.getUnitsDestroyed());
		voObject.setDestroyedBy(bomObject.getDestroyedBy());
		voObject.setDestroyedDate(bomObject.getDestroyedDate());
		voObject.setInsertDate(bomObject.getInsertDate());
		voObject.setInsertUser(bomObject.getInsertUser());
		voObject.setUpdateDate(bomObject.getUpdateDate());
		voObject.setUpdateUser(bomObject.getUpdateUser());
		voObject.setAuditId(bomObject.getAuditId());
		voObject.setComments(bomObject.getComments());
		return voObject;
	}
	
	/**
	 * Copy the contents of VO object to the BOM object
	 * @param bomObject
	 * @param voObject
	 */
	public StudyInventoryDestroyed populateStudyInventoryDestroyedBOM(StudyInventoryDestroyed bomObject, StudyInventoryDestroyedVO voObject) {
		bomObject.setPrdStudyScheduleId(voObject.getPrdStudyScheduleId());
		bomObject.setUnitsDestroyed(voObject.getUnitsDestroyed());
		bomObject.setDestroyedBy(voObject.getDestroyedBy());
		bomObject.setDestroyedDate(voObject.getDestroyedDate());
		bomObject.setAuditId(voObject.getAuditId());
		bomObject.setComments(voObject.getComments());
		return bomObject;
	}

	/**
	 * @param labelId
	 * @param userVO
	 * @throws EAPharmicsException
	 */
	public void copyLabel(Long labelId, UserVO userVO) throws EAPharmicsException {
		try {
			Label label1 = newStudyLoginFactory.getLabel(labelId);
			if (label1 == null) {
				throw new EAPharmicsException("Label for id " + labelId + " not found!");
			}
			super.beginTransaction();
			Label label = new Label();
    		label.setLabelName(label1.getLabelName() + "1");
    		label.setInsertUser(userVO.getAppUserId());
    		label.setInsertDate(Utility.getCurrentUserLocationDateTime());
    		label.setLabelTemplate(label1.getLabelTemplate());
    		label.setLabelData(label1.getLabelData());
    		label.setLocationId(label1.getLocationId());
    		newStudyLoginFactory.createLabel(label);
    		super.commitTransaction();
		} catch (Exception e) {
			logException("copyLabel", e);
			try {
				super.rollbackTransaction();
			} catch (Throwable t) {
				logException("copyLabel", e);				
			}
			throw new EAPharmicsException(e);
		}
	}

	/**
	 * @param keyName
	 * @return
	 * @throws EAPharmicsException
	 */
	public ProductStudyIntervalsVO getProductStudyIntervals(Long keyName) throws EAPharmicsException {
		//ProductStudyIntervals bomObject = newStudyLoginFactory.getProductStudyIntervals(keyName);
		ProductStudyIntervalsVO voObject = newStudyLoginFactory.getProductStudyIntervals(keyName);
		//voObject = populateProductStudyIntervalsVO(bomObject, voObject);
		return voObject;
	}
   /**
    * @param 
    * @return
    * @throws EAPharmicsException
    */
   public List<ProductStudyIntervalsVO> getAllProductStudyIntervals() throws EAPharmicsException {
      //ProductStudyIntervals bomObject = newStudyLoginFactory.getProductStudyIntervals(keyName);
      List<ProductStudyIntervalsVO> voObject = newStudyLoginFactory.getAllProductStudyIntervals();
      //voObject = populateProductStudyIntervalsVO(bomObject, voObject);
      return voObject;
   }

	/**
	 * Copy the contents of BOM object to the VO object
	 * @param bomObject
	 * @param voObject
	 */
	public ProductStudyIntervalsVO populateProductStudyIntervalsVO(ProductStudyIntervals bomObject, ProductStudyIntervalsVO voObject) {
		voObject.setPrdStudyBatchId(bomObject.getPrdStudyBatchId());
		voObject.setProductId(bomObject.getProductId());
		voObject.setProductCode(bomObject.getProductCode());
		voObject.setProductName(bomObject.getProductName());
		voObject.setLotNumber(bomObject.getLotNumber());
		voObject.setFill(bomObject.getFill());
		voObject.setContainerCode(bomObject.getContainerCode());
		voObject.setClosure(bomObject.getClosure());
		voObject.setStudyId(bomObject.getStudyId());
//		voObject.setTestingLab(bomObject.getTestingLab());
//		voObject.setPartNo(bomObject.getPartNo());
		voObject.setFormula(bomObject.getFormula());
		voObject.setStudyCondition(bomObject.getStudyCondition());
		voObject.setStorageLocation(bomObject.getStorageLocation());
		voObject.setStudyReasonCode(bomObject.getStudyReasonCode());
		voObject.setPullDate0(bomObject.getPullDate0());
		voObject.setPullDate1(bomObject.getPullDate1());
		voObject.setPullDate2(bomObject.getPullDate2());
		voObject.setPullDate3(bomObject.getPullDate3());
		voObject.setPullDate4(bomObject.getPullDate4());
		voObject.setPullDate5(bomObject.getPullDate5());
		voObject.setPullDate6(bomObject.getPullDate6());
		voObject.setPullDate7(bomObject.getPullDate7());
		voObject.setPullDate8(bomObject.getPullDate8());
		voObject.setPullDate9(bomObject.getPullDate9());
		voObject.setPullDate10(bomObject.getPullDate10());
		voObject.setPullDate11(bomObject.getPullDate11());
		voObject.setPullDate12(bomObject.getPullDate12());
		voObject.setPullDate13(bomObject.getPullDate13());
		voObject.setPullDate14(bomObject.getPullDate14());
		voObject.setPullDate15(bomObject.getPullDate15());
		voObject.setPullDate16(bomObject.getPullDate16());
		voObject.setPullDate17(bomObject.getPullDate17());
		voObject.setPullDate18(bomObject.getPullDate18());
      voObject.setPullDate19(bomObject.getPullDate19());
      voObject.setCodeYear(bomObject.getCodeYear());
      voObject.setInitialDate(bomObject.getInitialDate());
		return voObject;

	}

	
	/**
	 * Copy the contents of VO object to the BOM object
	 * @param bomObject
	 * @param voObject
	 */
	public ProductStudyIntervals populateProductStudyIntervalsBOM(ProductStudyIntervals bomObject, ProductStudyIntervalsVO voObject) {
		bomObject.setProductId(voObject.getProductId());
		bomObject.setProductCode(voObject.getProductCode());
		bomObject.setProductName(voObject.getProductName());
		bomObject.setLotNumber(voObject.getLotNumber());
		bomObject.setFill(voObject.getFill());
		bomObject.setContainerCode(voObject.getContainerCode());
		bomObject.setClosure(voObject.getClosure());
		bomObject.setStudyId(voObject.getStudyId());
//		bomObject.setTestingLab(voObject.getTestingLab());
//		bomObject.setPartNo(voObject.getPartNo());
		bomObject.setFormula(voObject.getFormula());
		bomObject.setStudyCondition(voObject.getStudyCondition());
		bomObject.setStorageLocation(voObject.getStorageLocation());
		bomObject.setStudyReasonCode(voObject.getStudyReasonCode());
		bomObject.setPullDate0(voObject.getPullDate0());
		bomObject.setPullDate1(voObject.getPullDate1());
		bomObject.setPullDate2(voObject.getPullDate2());
		bomObject.setPullDate3(voObject.getPullDate3());
		bomObject.setPullDate4(voObject.getPullDate4());
		bomObject.setPullDate5(voObject.getPullDate5());
		bomObject.setPullDate6(voObject.getPullDate6());
		bomObject.setPullDate7(voObject.getPullDate7());
		bomObject.setPullDate8(voObject.getPullDate8());
		bomObject.setPullDate9(voObject.getPullDate9());
		bomObject.setPullDate10(voObject.getPullDate10());
		bomObject.setPullDate11(voObject.getPullDate11());
		bomObject.setPullDate12(voObject.getPullDate12());
		bomObject.setPullDate13(voObject.getPullDate13());
		bomObject.setPullDate14(voObject.getPullDate14());
		bomObject.setPullDate15(voObject.getPullDate15());
		bomObject.setPullDate16(voObject.getPullDate16());
		bomObject.setPullDate17(voObject.getPullDate17());
		bomObject.setPullDate18(voObject.getPullDate18());
		bomObject.setPullDate19(voObject.getPullDate19());
		bomObject.setCodeYear(voObject.getCodeYear());
		bomObject.setInitialDate(voObject.getInitialDate());      
		return bomObject;

	}

	/**
	 * @return
	 */
	public List<NewStudyProductLotVO> getProtocolReportFilterData() 
		throws EAPharmicsException {
		return newStudyLoginFactory.getProtocolReportFilterData();
	}
   public List revertNewStudySchedules(List<Long> newStudyScheduleIds,String scheduleStatus,List returnScheduleIds,UserVO userVO) throws EAPharmicsException {
      try {
         super.beginTransaction();
         newStudyLoginFactory.updateNewStudySchedules(newStudyScheduleIds,scheduleStatus,userVO.getAppUserId(), userVO.getAuditId()); 
         super.commitTransaction();
      } catch (Exception e) {
         logException("updateNewStudySchedules", e);
         try {
            super.rollbackTransaction();
         } catch (Throwable t) {
            logException("updateNewStudySchedules", e);           
         }
         throw new EAPharmicsException(e);
      }
      return getAllNewStudySummaries(returnScheduleIds);
   }	

   public List<NewStudySummaryVO> updateInventoryExtraPulled(List<NewStudySummaryVO> scheduleIds,String pulledBy,Date pullDate,int extrasPulled,UserVO userVO,List<EditReasonVO> editReasons,List<NewStudySummaryVO> allScheduleIds) throws EAPharmicsException {
      try {
         super.beginTransaction();
         newStudyLoginFactory.updateInventoryExtraPulled(scheduleIds,pulledBy,pullDate,extrasPulled,userVO);
         if (editReasons != null && editReasons.size() > 0) {
            SecurityImpl sImpl = new SecurityImpl();
            for (Iterator iterator = editReasons.iterator(); iterator
                  .hasNext();) {
               EditReasonVO reasonVO = (EditReasonVO) iterator.next();
               sImpl.createEditReason(reasonVO, userVO);
            }
         }         
         super.commitTransaction();
         
      } catch (Exception e) {
         logException("updateInventoryExtraPulled", e);
         try {
            super.rollbackTransaction();
         } catch (Throwable t) {
            logException("updateInventoryExtraPulled", e);           
         }
         throw new EAPharmicsException(e);
      }
      return newStudyLoginFactory.getAllNewStudySummaries(allScheduleIds);
   }
   
   public List<NewStudySummaryVO> restoreInventoryPull(List<NewStudySummaryVO> scheduleIds,UserVO userVO,List<EditReasonVO> editReasons,List<NewStudySummaryVO> allScheduleIds) throws EAPharmicsException {
      try {
         super.beginTransaction();
         newStudyLoginFactory.restoreInventoryPull(scheduleIds,userVO);
         if (editReasons != null && editReasons.size() > 0) {
            SecurityImpl sImpl = new SecurityImpl();
            for (Iterator iterator = editReasons.iterator(); iterator
                  .hasNext();) {
               EditReasonVO reasonVO = (EditReasonVO) iterator.next();
               sImpl.createEditReason(reasonVO, userVO);
            }
         }         
         super.commitTransaction();
         
      } catch (Exception e) {
         logException("restoreInventoryPull", e);
         try {
            super.rollbackTransaction();
         } catch (Throwable t) {
            logException("restoreInventoryPull", e);           
         }
         throw new EAPharmicsException(e);
      }
      return newStudyLoginFactory.getAllNewStudySummaries(allScheduleIds);
   }   

	/**
	 * @param studyIds
	 * @param productTestId
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<RegressionDataContainerVO> getMultipleRegressionReportData(
			List<String> studyIds, Long productTestId)
			throws EAPharmicsException {
		return getMultipleRegressionReportData(studyIds, productTestId, Boolean.FALSE, null, null);
	}   

	/**
	 * @param studyIds
	 * @param productTestId
	 * @param t0percentCalc
	 * @param lowerLimit
	 * @param upperLimit
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<RegressionDataContainerVO> getMultipleRegressionReportData(
			List<String> studyIds, Long productTestId, Boolean t0percentCalc, String lowerLimit,
			String upperLimit)
			throws EAPharmicsException {
		if (t0percentCalc == null) {
			t0percentCalc = Boolean.FALSE;
		}
		if (studyIds == null || studyIds.size() < 2) {
			throw new EAPharmicsException(
				"Insufficient data for evaluation. Must have a minimum of 2 data points for at least 2 studies to perform calculation on pooled data!");
		}
		ProductTest productTest = ProductFactory.getInstance().getProductTest(productTestId);
		if (productTestId == null || productTestId.longValue() == 0 || productTest == null) {
			throw new EAPharmicsException("Must select a test!");
		}
		List products = ProductFactory.getInstance().getAllApprovedProductsSummary(productTest.getProductId());
		if (products == null || products.size() == 0) {
			throw new EAPharmicsException("Must select a Product and test!");
		}
		Object[] productFields = (Object[]) products.get(0);
		String productName = (String) productFields[2];
		List<RegressionDataContainerVO> summaries = newStudyLoginFactory.getRegressionNewStudyData(studyIds);
		int index = 0;
		
		List<List<RegressionInputVO>> batches = newStudyLoginFactory.getRegressionInput(studyIds, productTest, t0percentCalc, lowerLimit, upperLimit);
		if (batches.size() < 2) {
			throw new EAPharmicsException(
				"Insufficient data for evaluation. Must have a minimum of 2 data points for at least 2 studies to perform calculation on pooled data!");
		}
		MultipleRegression mr = new MultipleRegression();
		mr.processMultipleRegression(batches, null);

		int expiryInterval = 99;
		String slope = "";
	    NumberFormat formatter = new DecimalFormat("#0.0###########");
		
		List<List<RegressionDataVO>> list = mr.getRegressionResults();
		List<BatchData> batchData = mr.getResults();
		for (Iterator<RegressionDataContainerVO> iterator = summaries.iterator(); iterator.hasNext();) {			
			RegressionDataContainerVO regressionDataContainerVO = iterator.next();
			regressionDataContainerVO.setTestName(productTest.computeDisplayName());
			BatchData bData = batchData.get(index);
			regressionDataContainerVO.setSlope(bData.getSlope().doubleValue());
			regressionDataContainerVO.setIntercept(bData.getIntercept1().doubleValue());
			regressionDataContainerVO.setIntercept2(bData.getIntercept2().doubleValue());
			regressionDataContainerVO.setProductName(productName);
			int studyExpiryInterval = 99;
			if (batches.size() > index) {
				List<RegressionDataVO> regressionDatalist = (List<RegressionDataVO>) list.get(index);
				regressionDataContainerVO.setRegressionData(regressionDatalist);
				for (Iterator<RegressionDataVO> iterator2 = regressionDatalist.iterator(); iterator2
						.hasNext();) {
					RegressionDataVO regressionDataVO2 = iterator2.next();
					if (slope == "") {
						regressionDataContainerVO.setPIntercept(regressionDataVO2.getPIntercept());
						regressionDataContainerVO.setPSlope(regressionDataVO2.getPSlope());
						//slopes are poolable if p-value is > 0.25 i.e. Common Slope
						if (regressionDataVO2.getPSlope() >= 0.25) {
							slope = "Common Slope";
							regressionDataContainerVO.setSlopeText("Common Slope");
							regressionDataContainerVO.setCommonSlope(formatter.format(mr.getSumWithinGroups().getSlope().doubleValue()));
						} else {
							regressionDataContainerVO.setSlopeText("Uncommon Slope");
							slope = "Uncommon Slope";
							regressionDataContainerVO.setCommonSlope("na");
						}
						//intercepts are poolable if p-value is > 0.25 but only if slopes are poolable first i.e. this is Unequal Intercept
						if (regressionDataVO2.getPSlope() >= 0.25 && regressionDataVO2.getPIntercept() >= 0.25) {
							regressionDataContainerVO.setInterceptText("Equal Intercepts");
							if (mr.getSumWithinGroups().getIntercept1() != null) {
								regressionDataContainerVO.setCommonIntercepts(formatter.format(mr.getSumWithinGroups().getIntercept1().doubleValue()));
							}
						} else {
							regressionDataContainerVO.setInterceptText("Unequal Intercepts");
							regressionDataContainerVO.setCommonIntercepts("na");
						}
					}
					regressionDataVO2.calculateOkFlag();
					if (regressionDataVO2.getOkFlag().equals("ok")) {
						studyExpiryInterval = regressionDataVO2.getInterval();
					}
				}
			}
			if (studyExpiryInterval < expiryInterval) {
				expiryInterval = studyExpiryInterval;
			}
			index++;
		}
		for (Iterator<RegressionDataContainerVO> iterator = summaries.iterator(); iterator.hasNext();) {			
			RegressionDataContainerVO regressionDataContainerVO = iterator.next();
			regressionDataContainerVO.setShelfLife(expiryInterval);
		}
		
		return summaries;
	}


	/**
	 * @param studyId
	 * @return
	 */
	public List getAttachmentsForStudy(final String studyId) {
		String uploadDir = System.getProperty("application.uploads.location");
		String[] files = null;
		File dir = new File(uploadDir);
		if (dir.isDirectory()) {
			FilenameFilter filter = new FilenameFilter() {
				public boolean accept(File file, String name) {
					return name.startsWith(studyId);
				}
			};
			files = dir.list(filter);
		}
		List results = new ArrayList();
		for (int i = 0; i < files.length; i++) {
			FileAttachmentVO fileAttachment = new FileAttachmentVO();
			String fileName = files[i];
			if (files[i].indexOf(studyId + "_") == 0) {
				fileName = files[i].substring((studyId + "_").length());
			}
			fileAttachment.setFileName(fileName);
			results.add(fileAttachment);
		}
		return results;
	}
	
	public List deleteAttachmentForStudy(String studyId, String fileName) {
		String uploadDir = System.getProperty("application.uploads.location");
		File file = new File(uploadDir + "/" + studyId + "_" + fileName);
		if (file.exists()) {
			file.delete();
		}
		return getAttachmentsForStudy(studyId);
	}

	/**
	 * @param studyIds
	 * @param productTestId
	 * @return
	 */
	public List<RegressionDataContainerVO> getSingleRegressionReportData(
			String studyId, Long productTestId) throws EAPharmicsException {
		return getSingleRegressionReportData(studyId, productTestId, Boolean.FALSE, null, null);
	}
	
	/**
	 * @param studyId
	 * @param productTestId
	 * @param t0PercentCalc
	 * @param lowerLimit
	 * @param upperLimit
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<RegressionDataContainerVO> getSingleRegressionReportData(
			String studyId, Long productTestId, Boolean t0PercentCalc,
			String lowerLimit, String upperLimit) throws EAPharmicsException {
		if (t0PercentCalc == null) {
			t0PercentCalc = Boolean.FALSE;
		}
		if (studyId == null || studyId.length() == 0) {
			throw new EAPharmicsException("At least one Study is required!");
		}
		ProductTest productTest = ProductFactory.getInstance().getProductTest(productTestId);
		if (productTestId == null || productTestId.longValue() == 0 || productTest == null) {
			throw new EAPharmicsException("Must select a test!");
		}

		List<String> studyIds = new ArrayList<String>();
		studyIds.add(studyId);
		List<RegressionDataContainerVO> summaries = newStudyLoginFactory.getRegressionNewStudyData(studyIds);
		
		List<List<RegressionInputVO>> batches = newStudyLoginFactory.getRegressionInput(studyIds, productTest, t0PercentCalc, lowerLimit, upperLimit);
		if (batches.size() != 1) {
			throw new EAPharmicsException(
				"Insufficient data for evaluation. Must have a minimum of 2 data points for at least study to perform calculation on pooled data!");
		}
		SingleRegression sr = new SingleRegression();
		sr.processSingleRegression(batches.get(0), null);

		//Only one batch is allowed, so we will get only one List
		//It is a shared method on the factory with multiple regression which will have at least 2 studies
		RegressionDataContainerVO regressionDataContainerVO = (RegressionDataContainerVO) summaries.get(0);
		regressionDataContainerVO.setTestName(productTest.computeDisplayName());
		regressionDataContainerVO.setProductName(productTest.getProduct().getProductName());
		List<RegressionDataVO> regressionDatalist = sr.getRegressionResults();
		regressionDataContainerVO.setRegressionData(regressionDatalist);
		RegressionDataContainerVO data  = (RegressionDataContainerVO) summaries.get(0);
		data.setRegressionData(regressionDatalist);

		return summaries;
	}

	/**
	 * @param studyId
	 * @param productTestId
	 * @return
	 */
	public List<StoredDataVO> getDataEntryResults(String studyId, Long productTestId) throws EAPharmicsException {
		return newStudyLoginFactory.getDataEntryResults(studyId, productTestId);
	}

	/**
	 * @param studyId
	 * @param intervalValue
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<DataEntryVO> getDataEntryResultsSummary(String studyId, int intervalValue) throws EAPharmicsException {
		List<DataEntryVO> list1 = new ArrayList<DataEntryVO>();
		List<DataEntryVO> list = newStudyLoginFactory.getDataEntryResultsSummary(studyId);
		String prevResult = "";
		String firstResult = "";
		for (Iterator<DataEntryVO> iterator = list.iterator(); iterator.hasNext();) {
			DataEntryVO dataEntryVO = iterator.next();
			if (dataEntryVO.getIntervalValue() == 0) {
				//initial or first interval. Capture the result
				firstResult = dataEntryVO.getResult();
				prevResult = "";
			}
			
			if (dataEntryVO.getIntervalValue() == intervalValue) {
				//Current value, create new row, update the value and add to list
				DataEntryVO newVO = new DataEntryVO();
				newVO.setPrdStudyTestResultsId(dataEntryVO.getPrdStudyTestResultsId());
				newVO.setProductTestId(dataEntryVO.getProductTestId());
				newVO.setIntervalValue(dataEntryVO.getIntervalValue());
				newVO.setResult(dataEntryVO.getResult());
				if (intervalValue != 0) {
					//Not the initial interval, so we can compare with first and previous 
					newVO.setInitialResult(firstResult);
					newVO.setPrevResult(prevResult);
				} else {
					newVO.setChangeFromInitial("");
					newVO.setChangeFromPrev("");
				}
				list1.add(newVO);
			}
			//Make this the previous result
			prevResult = dataEntryVO.getResult();
		}
		return list1;
	}

	public List<CodeSwitchProductVO> getCodeSwitchProductsForProduct(String productCode) throws EAPharmicsException {
		return newStudyLoginFactory.getCodeSwitchProductsForProduct(productCode);
	}
	/**
    * @return
    */
   public List getAllRptUserPreferences(List<String> reportGroupName, String reportName, UserVO userVO) throws EAPharmicsException {
      List<RptUserPreferenceVO> list1 = newStudyLoginFactory.getAllRptUserPreferences(reportGroupName,reportName,userVO);
      return list1;
   }
   /**
    * @return
    */
   public List getAllRptLocationPreferences(List<String> reportGroupName, String reportName, UserVO userVO) throws EAPharmicsException {
      List<RptUserPreferenceVO> list1 = newStudyLoginFactory.getAllRptLocationPreferences(reportGroupName,reportName,userVO);
      return list1;
   }
   /**
    * @param voObject
    * @throws EAPharmicsException
    */
   public List<RptUserPreferenceVO> updateRptUserPreference(List<RptUserPreferenceVO> voObject, UserVO userVO) throws EAPharmicsException {
      RptUserPreferenceVO rptUserPreferenceVO = new RptUserPreferenceVO();
      for (Iterator<RptUserPreferenceVO> iterator = voObject.iterator(); iterator.hasNext();) 
      {
         rptUserPreferenceVO = iterator.next(); 
         break;
      }        
      try {
         super.beginTransaction();       
         newStudyLoginFactory.updateRptUserPreference(voObject, userVO);
         super.commitTransaction();
      } catch (Exception e) {
         logException("updateRptUserPreference", e);
         try {
            super.rollbackTransaction();
         } catch (Throwable t) {
            logException("updateRptUserPreference", e);           
         }
         throw new EAPharmicsException(e);
      }
      List<String> arr = new ArrayList<String>();
      arr.add(rptUserPreferenceVO.getReportGroupName());
   return getAllRptUserPreferences(arr,
                                   rptUserPreferenceVO.getReportName(),
                                   userVO);
}
   /**
    * @param voObject
    * @throws EAPharmicsException
    */
   public List<RptUserPreferenceVO> updateRptLocationPreference(List<RptUserPreferenceVO> voObject, UserVO userVO) throws EAPharmicsException {
      RptUserPreferenceVO rptUserPreferenceVO = new RptUserPreferenceVO();
      for (Iterator<RptUserPreferenceVO> iterator = voObject.iterator(); iterator.hasNext();) 
      {
         rptUserPreferenceVO = iterator.next(); 
         break;
      }        
      try {
         super.beginTransaction();       
         newStudyLoginFactory.updateRptUserPreference(voObject, userVO);
         super.commitTransaction();
      } catch (Exception e) {
         logException("updateRptUserPreference", e);
         try {
            super.rollbackTransaction();
         } catch (Throwable t) {
            logException("updateRptUserPreference", e);           
         }
         throw new EAPharmicsException(e);
      }
      List<String> arr = new ArrayList<String>();
      arr.add(rptUserPreferenceVO.getReportGroupName());
   return getAllRptLocationPreferences(arr,
                                   rptUserPreferenceVO.getReportName(),
                                   userVO);
}   
   
   /**
    * @return
    */
   public List<NewStudySummaryVO>  getPendingSchedulerReportData(List scheduleIds,
                                                                 String scheduleStatus,
                                                                 String monthQuery,
                                                                Long unpulledDays,
                                                                 Long lateDaysStart,
                                                                 Long lateDaysEnd,
                                                                 Long testingCycleNotStarted,
                                                                 Long testingCycleNotEnded,                                                                
                                                                 String productCode,                                                                 
                                                                 String primarySortColumn,
                                                                 String primarySortColumnOrder,
                                                                 String secondarySortColumn,
                                                                 String secondarySortColumnOrder)
      throws EAPharmicsException {
      return newStudyLoginFactory
            .getPendingSchedulerReportData(scheduleIds,
                                           scheduleStatus,
                                           monthQuery,
                                           unpulledDays,
                                           lateDaysStart,
                                           lateDaysEnd,
                                           testingCycleNotStarted,
                                           testingCycleNotEnded,                                                                
                                           productCode,                                           
                                           primarySortColumn,
                                           primarySortColumnOrder,
                                           secondarySortColumn,
                                           secondarySortColumnOrder);
   }   
   
   /**
    * 
    */
   public List<ProductStudyIntervalsVO> getStabilityLogsReportData(String stabilityYear,
                                                                  Date fromStabilityDate,
                                                                  Date toStabilityDate,
                                                                  String productCode,  
                                                                  String lotNumber,
                                                                  String containerCode,
                                                                  String studyStatus,
                                                                  String studyId,
                                                                  String primarySortColumn,
                                                                  String primarySortColumnOrder,
                                                                  String secondarySortColumn,
                                                                  String secondarySortColumnOrder)                                                            
                                                                
      throws EAPharmicsException 
   {
      
      return newStudyLoginFactory.getStabilityLogsReportData(stabilityYear,
                                                             fromStabilityDate,
                                                             toStabilityDate,
                                                             productCode,                                                                   
                                                             containerCode,
                                                             lotNumber,
                                                             studyStatus,
                                                             studyId,
                                                             primarySortColumn,
                                                             primarySortColumnOrder,
                                                             secondarySortColumn,
                                                             secondarySortColumnOrder);
      
   }
}
