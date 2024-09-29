package com.enviroapps.eapharmics.ui.newstudy;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enviroapps.eapharmics.exception.EAPharmicsException;
import com.enviroapps.eapharmics.services.NewStudyLoginImpl;
import com.enviroapps.eapharmics.services.ProductImpl;
import com.enviroapps.eapharmics.ui.AbstractEAPharmicsService;
import com.enviroapps.eapharmics.util.LabelHelper;
import com.enviroapps.eapharmics.vo.newstudy.CodeSwitchProductVO;
import com.enviroapps.eapharmics.vo.newstudy.DataEntryVO;
import com.enviroapps.eapharmics.vo.newstudy.GraphGeneratorVO;
import com.enviroapps.eapharmics.vo.newstudy.LabelPrintRequestDisplayVO;
import com.enviroapps.eapharmics.vo.newstudy.LabelPrintRequestVO;
import com.enviroapps.eapharmics.vo.newstudy.LabelVO;
import com.enviroapps.eapharmics.vo.newstudy.NewStudyConditionVO;
import com.enviroapps.eapharmics.vo.newstudy.NewStudyProductLotVO;
import com.enviroapps.eapharmics.vo.newstudy.NewStudyProductSummaryVO;
import com.enviroapps.eapharmics.vo.newstudy.NewStudyProductVO;
import com.enviroapps.eapharmics.vo.newstudy.NewStudyScheduleVO;
import com.enviroapps.eapharmics.vo.newstudy.NewStudyScreenDataVO;
import com.enviroapps.eapharmics.vo.newstudy.NewStudySummaryVO;
import com.enviroapps.eapharmics.vo.newstudy.NewStudyTestResultVO;
import com.enviroapps.eapharmics.vo.newstudy.ProductStudyIntervalsVO;
import com.enviroapps.eapharmics.vo.newstudy.ReportCountVO;
import com.enviroapps.eapharmics.vo.newstudy.StoredDataVO;
import com.enviroapps.eapharmics.vo.newstudy.StudyInventoryDestroyedVO;
import com.enviroapps.eapharmics.vo.security.EditReasonVO;

import lombok.RequiredArgsConstructor;

//import com.enviroapps.eapharmics.bom.newstudy.RptUserPreference;
import com.enviroapps.eapharmics.vo.newstudy.RptUserPreferenceVO;

@RestController
@RequestMapping("/eapharmics/newstudy")
@RequiredArgsConstructor
public class NewStudyLoginService extends AbstractEAPharmicsService {


	/**
	 * @param newStudyProductId
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<NewStudyProductSummaryVO> getAllNewStudyProductSummary()
			throws EAPharmicsException {
		checkSession();
		List<NewStudyProductSummaryVO> list = new ArrayList<NewStudyProductSummaryVO>();
		try {
			NewStudyLoginImpl impl = new NewStudyLoginImpl();
			list = impl.getNewStudyProductsSummary(null, null, null, null,null, null, null,false);
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "getAllNewStudyProductSummary", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		}
		return list;
	}
   /**
    * @param newStudyProductId
    * @return
    * @throws EAPharmicsException
    */
   public List<NewStudyProductSummaryVO> getAllUnApprovedNewStudyProductSummary()
         throws EAPharmicsException {
      checkSession();
      List<NewStudyProductSummaryVO> list = new ArrayList<NewStudyProductSummaryVO>();
      try {
         NewStudyLoginImpl impl = new NewStudyLoginImpl();
         list = impl.getNewStudyProductsSummary(null, null, null, null,null, null, null,true);
      } catch (EAPharmicsException e) {
         if (e.getCause() instanceof EAPharmicsException) {
            throw (EAPharmicsException) e.getCause();
         }
         throw e;
      } catch (RuntimeException e) {
         logger.error(this, "getAllUnApprovedNewStudyProductSummary", e);
         throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
      }
      return list;
   }	
   /**
    * @param newStudyProductId
    * @return
    * @throws EAPharmicsException
    */
   public List<NewStudyProductSummaryVO> getNewStudyProductsSummary(
         String barCode, String studyId, Long productId, String lotNumber, String year)
         throws EAPharmicsException {
      checkSession();
      List<NewStudyProductSummaryVO> list = new ArrayList<NewStudyProductSummaryVO>();
      try {
         NewStudyLoginImpl impl = new NewStudyLoginImpl();
         list = impl.getNewStudyProductsSummary(barCode, studyId, productId, lotNumber, year,null,null,false);
      } catch (EAPharmicsException e) {
         if (e.getCause() instanceof EAPharmicsException) {
            throw (EAPharmicsException) e.getCause();
         }
         throw e;
      } catch (RuntimeException e) {
         logger.error(this, "getNewStudyProductsSummary", e);
         throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
      }
      return list;
   }
   
	/**
	 * @param newStudyProductId
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<NewStudyProductSummaryVO> getNewStudyProductsSummary(
			String barCode, String studyId, Long productId, String lotNumber, String year, Date fromDate, Date toDate,boolean unApprovedStudiesOnly)
			throws EAPharmicsException {
		checkSession();
		List<NewStudyProductSummaryVO> list = new ArrayList<NewStudyProductSummaryVO>();
		try {
			NewStudyLoginImpl impl = new NewStudyLoginImpl();
			list = impl.getNewStudyProductsSummary(barCode, studyId, productId, lotNumber, year, fromDate, toDate,unApprovedStudiesOnly);
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "getNewStudyProductsSummary", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		}
		return list;
	}
	
   /**
    * @param newStudyProductId
    * @return
    * @throws EAPharmicsException
    */
   public List<NewStudyProductSummaryVO> getNewStudyProductsSummary(
         String barCode, String studyId, Long productId, String lotNumber, String year, Date fromDate, Date toDate)
         throws EAPharmicsException {
      checkSession();
      List<NewStudyProductSummaryVO> list = new ArrayList<NewStudyProductSummaryVO>();
      try {
         NewStudyLoginImpl impl = new NewStudyLoginImpl();
         list = impl.getNewStudyProductsSummary(barCode, studyId, productId, lotNumber, year, fromDate, toDate,false);
      } catch (EAPharmicsException e) {
         if (e.getCause() instanceof EAPharmicsException) {
            throw (EAPharmicsException) e.getCause();
         }
         throw e;
      } catch (RuntimeException e) {
         logger.error(this, "getNewStudyProductsSummary", e);
         throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
      }
      return list;
   }	
	
	/**
	 * @param studyId
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<NewStudyConditionVO> getNewStudyIntervalLabel(String studyId)
			throws EAPharmicsException {
		checkSession();
		List<NewStudyConditionVO> list = new ArrayList<NewStudyConditionVO>();
		try {
			NewStudyLoginImpl impl = new NewStudyLoginImpl();
			list = impl.getNewStudyIntervalLabel(studyId);
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "getNewStudyIntervalLabel", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		}
		return list;
	}

	/**
	 * @param voObject
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<NewStudyProductSummaryVO> updateNewStudyProduct(NewStudyProductVO voObject, List editReasons,Boolean deleteIntervalFlag,
			String barCode, String studyId, Long productId, String lotNumber, Date fromDate, Date toDate) 
	throws EAPharmicsException {
		checkSession();
		List<NewStudyProductSummaryVO> list = new ArrayList<NewStudyProductSummaryVO>();
		try {
			NewStudyLoginImpl impl = new NewStudyLoginImpl();
			impl.updateNewStudyProduct(voObject, editReasons, getUser(),deleteIntervalFlag);
			list = impl.getNewStudyProductsSummary(barCode, studyId, productId, lotNumber, null, fromDate, toDate,false);
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "updateNewStudyProduct", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.");
		}
		return list;
	}

	/**
	 * @param newStudyProductId
	 * @return
	 * @throws EAPharmicsException
	 */
	public NewStudyProductVO getNewStudyProduct(Long prdStudyBatchId) 
	throws EAPharmicsException {
		checkSession();
		try {
			NewStudyLoginImpl impl = new NewStudyLoginImpl();
			return impl.getNewStudyProduct(prdStudyBatchId);
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "getNewStudyProduct", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.");
		}
	}

	/**
	 * @param newStudyProductId
	 * @return
	 * @throws EAPharmicsException
	 */
	public NewStudyScreenDataVO getNewStudyProductAndProduct(Long prdStudyBatchId) 
	throws EAPharmicsException {
		checkSession();
		try {
			NewStudyLoginImpl impl = new NewStudyLoginImpl();
			NewStudyScreenDataVO screenVO = new NewStudyScreenDataVO();
			screenVO.setNewStudyProductVO(impl.getNewStudyProduct(prdStudyBatchId));
			screenVO.setProductVO((new ProductImpl()).getProduct(screenVO.getNewStudyProductVO().getProductId()));
			return screenVO;
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "getNewStudyProductAndProduct", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.");
		}
	}

	/**
	 * @param voObject
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<NewStudyProductSummaryVO> addNewStudyProduct(NewStudyProductVO voObject,
			String barCode, String studyId, Long productId, String lotNumber, Date fromDate, Date toDate) 
	throws EAPharmicsException {
		checkSession();
		List<NewStudyProductSummaryVO> list = new ArrayList<NewStudyProductSummaryVO>();
		try {
			NewStudyLoginImpl impl = new NewStudyLoginImpl();
			impl.createNewStudyProduct(voObject, getUser());
			list = impl.getNewStudyProductsSummary(barCode, studyId, productId, lotNumber, null, fromDate, toDate,false);
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "addNewStudyProduct", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.");
		}
		return list;
	}

	/**
	 * @return
	 */
	public List getAllLabels() throws EAPharmicsException {
		try {
			return new LabelHelper().getAllLabels();
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "getAllLabels", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		}
	}

	/**
	 * @param labelPrintRequestId
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<LabelPrintRequestVO> getAllLabelPrintRequests() 
		throws EAPharmicsException {
		checkSession();
		List<LabelPrintRequestVO> list = new ArrayList<LabelPrintRequestVO>();
		try {
			NewStudyLoginImpl impl = new NewStudyLoginImpl();
			list = impl.getAllLabelPrintRequests();			
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "getLabelPrintRequests", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		}
		return list;
	}

	/**
	 * @return
	 */
	public List getAllPendingLabelPrintRequestsDisplay() throws EAPharmicsException {
		checkSession();
		List<LabelPrintRequestDisplayVO> list = new ArrayList<LabelPrintRequestDisplayVO>();
		try {
			NewStudyLoginImpl impl = new NewStudyLoginImpl();
			list = impl.getAllPendingLabelPrintRequestsDisplay();
			if (list == null) {
				list = new ArrayList();
			}
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "getAllPendingLabelPrintRequestsDisplay", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		}
		return list;
	}

	/**
	 * @param eaLabelPrintRequestGroupId
	 * @param labelId
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<LabelPrintRequestVO> getAllPendingLabelPrintRequests(Long labelId) 
		throws EAPharmicsException {
		checkSession();
		List<LabelPrintRequestVO> list = new ArrayList<LabelPrintRequestVO>();
		try {
			NewStudyLoginImpl impl = new NewStudyLoginImpl();
			list = impl.getAllPendingLabelPrintRequests(labelId);			
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "getLabelPrintRequests", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		}
		return list;
	}

	/**
	 * @param voObject
	 * @return
	 * @throws EAPharmicsException
	 */
	public void addLabelPrintRequest(List<LabelPrintRequestVO> voObjects, List editReasons) 
	throws EAPharmicsException {
		checkSession();
		try {
			NewStudyLoginImpl impl = new NewStudyLoginImpl();
			impl.createLabelPrintRequest(voObjects, editReasons, getUser());
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "addLabelPrintRequest", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.");
		}
	}

	/**
	 * @param voObject
	 * @return
	 * @throws EAPharmicsException
	 */
	public List deleteLabelPrintRequest(Long labelId, Long eaLabelPrintRequestsId) 
	throws EAPharmicsException {
		checkSession();
		try {
			NewStudyLoginImpl impl = new NewStudyLoginImpl();
			impl.deleteLabelPrintRequest(labelId, eaLabelPrintRequestsId, getUser());
			return getAllPendingLabelPrintRequestsDisplay();
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "deleteLabelPrintRequest", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.");
		}
	}

	public List<NewStudyProductVO> getNewStudyIntervalValueChartData(Long prdStudyBatchId) 
	throws EAPharmicsException {
	checkSession();
	List<NewStudyProductVO> list = new ArrayList<NewStudyProductVO>();
	try {
		NewStudyLoginImpl impl = new NewStudyLoginImpl();
		list = impl.getNewStudyIntervalValueChartData(prdStudyBatchId);			
	} catch (EAPharmicsException e) {
		if (e.getCause() instanceof EAPharmicsException) {
			throw (EAPharmicsException) e.getCause();
		}
		throw e;
	} catch (RuntimeException e) {
		logger.error(this, "getLabelPrintRequests", e);
		throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
	}
	return list;
}
	
	public List getSequenceId(Date stabilityDate) throws EAPharmicsException {

		try {
			
			NewStudyLoginImpl impl = new NewStudyLoginImpl();
			List list = impl.getSequenceId(stabilityDate);
			return list;

		} catch (Exception e) {
			logger.error(this, "getSequenceId", e);
			return null;
		}

	}	

	/**
	 * @param newStudyScheduleId
	 * @return
	 * @throws EAPharmicsException
	 */
	public NewStudyScheduleVO getNewStudySchedule(Long newStudyScheduleId) 
		throws EAPharmicsException {
		return getNewStudySchedule(newStudyScheduleId, false);
	}

	/**
	 * @param newStudyScheduleId
	 * @param checkForAllIntervalsCompleted
	 * @return
	 * @throws EAPharmicsException
	 */
	public NewStudyScheduleVO getNewStudySchedule(Long newStudyScheduleId, boolean checkForAllIntervalsCompleted) 
		throws EAPharmicsException {
		checkSession();
		try {
			NewStudyLoginImpl impl = new NewStudyLoginImpl();
			return impl.getNewStudySchedule(newStudyScheduleId, checkForAllIntervalsCompleted);			
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "getNewStudySchedule", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		}
	}
	/**
	 * @return
	 */
	public List<NewStudySummaryVO> getAllNewStudySummaries(Date startDate, Date endDate) 
		throws EAPharmicsException {
		checkSession();
		List<NewStudySummaryVO> list = new ArrayList<NewStudySummaryVO>();
		try {
			NewStudyLoginImpl impl = new NewStudyLoginImpl();
			list = impl.getAllNewStudySummaries(startDate, endDate);
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "getAllNewStudySummaries", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		}
		return list;
	}
	
	/**
	 * @param newStudyScheduleIds
	 * @throws EAPharmicsException
	 */
	public List updateNewStudySchedules(List<Long> newStudyScheduleIds,String scheduleStatus,List<Long> allScheduleIds) throws EAPharmicsException {
		checkSession();
		List<NewStudyScheduleVO> list = new ArrayList<NewStudyScheduleVO>();
		try {
			NewStudyLoginImpl impl = new NewStudyLoginImpl();
			return impl.updateNewStudySchedules(newStudyScheduleIds, scheduleStatus,allScheduleIds,getUser());
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "updateNewStudySchedules", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.");
		}
	}
	public List<NewStudySummaryVO> getAllPendingLists() throws EAPharmicsException {
		checkSession();
		List<NewStudySummaryVO> list = new ArrayList<NewStudySummaryVO>();
		try {
			NewStudyLoginImpl impl = new NewStudyLoginImpl();
			list = impl.getAllPendingLists();
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "getAllPendingLists", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		}
		return list;
    }

	/**
	 * @param newStudyTestResultId
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<NewStudyTestResultVO> getAllNewStudyTestResults(Long prdStudyScheduleId) 
		throws EAPharmicsException {
		checkSession();
		List<NewStudyTestResultVO> list = new ArrayList<NewStudyTestResultVO>();
		try {
			NewStudyLoginImpl impl = new NewStudyLoginImpl();
			list = impl.getAllNewStudyTestResults(prdStudyScheduleId);			
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "getNewStudyTestResults", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		}
		return list;
	}
	
	public List<GraphGeneratorVO> getAllNewStudyTestResultsChartData(Long prdStudyBatchId,String studyId) 
	throws EAPharmicsException {
	checkSession();
	List<GraphGeneratorVO> list = new ArrayList<GraphGeneratorVO>();
	try {
		NewStudyLoginImpl impl = new NewStudyLoginImpl();
		list = impl.getAllNewStudyTestResultsChartData(prdStudyBatchId,studyId);
		if (list == null) {
			list = new ArrayList();
		}
	} catch (EAPharmicsException e) {
		if (e.getCause() instanceof EAPharmicsException) {
			throw (EAPharmicsException) e.getCause();
		}
		throw e;
	} catch (RuntimeException e) {
		logger.error(this, "getNewStudyTestResultsChartData", e);
		throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
	}
	return list;
}

	/**
	 * @param voObject
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<NewStudyTestResultVO> updateNewStudyTestResults(
			NewStudyScheduleVO scheduleVO, List voObjects, List additionalSchedulesToUpdate, List editReasons)
			throws EAPharmicsException {
		checkSession();
		List<NewStudyTestResultVO> list = new ArrayList<NewStudyTestResultVO>();
		try {
			NewStudyLoginImpl impl = new NewStudyLoginImpl();
			impl.updateNewStudyTestResults(scheduleVO, voObjects, additionalSchedulesToUpdate, editReasons, getUser());
			if (voObjects.size() > 0) {
				NewStudyTestResultVO voObject = (NewStudyTestResultVO) voObjects.get(0);
				list = impl.getAllNewStudyTestResults(voObject.getPrdStudyScheduleId());
			}
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "updateNewStudyTestResult", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.");
		}
		return list;
	}

	public void updateCDSTestUsageForm(
			NewStudyScheduleVO scheduleVO, List voObjects, List editReasons)
			throws EAPharmicsException {
		checkSession();
		try {
			NewStudyLoginImpl impl = new NewStudyLoginImpl();
			impl.updateCDSTestUsageForm(scheduleVO, voObjects, editReasons, getUser());
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "updateCDSTestUsageForm", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.");
		}
	}

	/**
	 * @return
	 */
	public List<NewStudySummaryVO> getAllNewStudySummaries(
			String scheduleStatus, String barCode, String studyId,
			String productCode, String lotNumber, String productType,
			Date startDate, Date endDate) throws EAPharmicsException {
		checkSession();
		List<NewStudySummaryVO> list = new ArrayList<NewStudySummaryVO>();
		try {
			NewStudyLoginImpl impl = new NewStudyLoginImpl();
			list = impl.getAllNewStudySummaries(scheduleStatus, barCode,
					studyId, productCode, lotNumber, productType, startDate, endDate, null, null,null,null);
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "getAllNewStudySummaries", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		}
		return list;
	}

	/**
	 * @return
	 */
	public List<NewStudySummaryVO> getAllNewStudySummariesForApproval(
			String scheduleStatus, String barCode, String studyId,
			String productCode, String lotNumber, String productType,
			Date startDate, Date endDate, Boolean level1Approval, Boolean finalApproval) throws EAPharmicsException {
		checkSession();
		List<NewStudySummaryVO> list = new ArrayList<NewStudySummaryVO>();
		try {
			NewStudyLoginImpl impl = new NewStudyLoginImpl();
			list = impl.getAllNewStudySummaries(scheduleStatus, barCode,
					studyId, productCode, lotNumber, productType, startDate, endDate,
					level1Approval, finalApproval,null,null);
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "getAllNewStudySummaries", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		}
		return list;
	}
	public List<NewStudySummaryVO> updateInventoryPullDate(List<NewStudySummaryVO> scheduleIds,String pulledBy,Date pullDate,int unitsPulled,int extrasPulled,List<EditReasonVO> editReasonVO,List<NewStudySummaryVO> allScheduleIds) 
      throws EAPharmicsException {
      checkSession();
      List<NewStudySummaryVO> list = new ArrayList<NewStudySummaryVO>();
      //List<NewStudySummaryVO> list = new ArrayList<NewStudySummaryVO>();
      try {
         NewStudyLoginImpl impl = new NewStudyLoginImpl();
         list = impl.updateInventoryPullDate(scheduleIds,pulledBy,pullDate,unitsPulled,extrasPulled,getUser(),editReasonVO,allScheduleIds);
      } catch (EAPharmicsException e) {
         if (e.getCause() instanceof EAPharmicsException) {
            throw (EAPharmicsException) e.getCause();
         }
         throw e;
      } catch (RuntimeException e) {
         logger.error(this, "updateInventoryPullDate", e);
         throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
      }   
      return list;
   }      

   /**
    * @return
    */
   public List<NewStudySummaryVO> getAllNewStudySummaries(Date startDate, Date endDate,String scheduleStatus) 
      throws EAPharmicsException {
      checkSession();
      List<NewStudySummaryVO> list = new ArrayList<NewStudySummaryVO>();
      try {
         NewStudyLoginImpl impl = new NewStudyLoginImpl();
         list = impl.getAllNewStudySummaries(startDate, endDate,scheduleStatus);
      } catch (EAPharmicsException e) {
         if (e.getCause() instanceof EAPharmicsException) {
            throw (EAPharmicsException) e.getCause();
         }
         throw e;
      } catch (RuntimeException e) {
         logger.error(this, "getAllNewStudySummaries", e);
         throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
      }
      return list;
   }
   
	/**
	 * @return
	 */
	public ReportCountVO getPendingReportCounts()
      throws EAPharmicsException {
          checkSession();
          try {
             NewStudyLoginImpl impl = new NewStudyLoginImpl();
             return impl.getPendingReportCounts();
          } catch (EAPharmicsException e) {
             if (e.getCause() instanceof EAPharmicsException) {
                throw (EAPharmicsException) e.getCause();
             }
             throw e;
          } catch (RuntimeException e) {
             logger.error(this, "getPendingReportCounts", e);
             throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
          }
      }
   /**
    * @return 
    */	
   public List<NewStudySummaryVO> updateInventoryTestStartDate(List<NewStudySummaryVO> scheduleIds,Date testStartDate,List<EditReasonVO> editReasonVO,List<NewStudySummaryVO> allScheduleIds)
     throws EAPharmicsException {
     checkSession();
     List<NewStudySummaryVO> list = new ArrayList<NewStudySummaryVO>();
     try {
        NewStudyLoginImpl impl = new NewStudyLoginImpl();
        list = impl.updateInventoryTestStartDate(scheduleIds,testStartDate,getUser(),editReasonVO,allScheduleIds); 
     } catch (EAPharmicsException e) {
        if (e.getCause() instanceof EAPharmicsException) {
           throw (EAPharmicsException) e.getCause();
        }
        throw e;
     } catch (RuntimeException e) {
        logger.error(this, "updateInventoryTestStartDate", e);
        throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
     }   
     return list;
  }	
   /**
    * @return 
    */   
   public List<NewStudySummaryVO> updateInventoryTestEndDate(List<NewStudySummaryVO> scheduleIds, Date testEndDate,List<EditReasonVO> editReasonVO,List<NewStudySummaryVO> allScheduleIds)
     throws EAPharmicsException {
     checkSession();
     List<NewStudySummaryVO> list = new ArrayList<NewStudySummaryVO>();
     try {
        NewStudyLoginImpl impl = new NewStudyLoginImpl();
        list = impl.updateInventoryTestEndDate(scheduleIds,testEndDate,getUser(),editReasonVO,allScheduleIds); 
     } catch (EAPharmicsException e) {
        if (e.getCause() instanceof EAPharmicsException) {
           throw (EAPharmicsException) e.getCause();
        }
        throw e;
     } catch (RuntimeException e) {
        logger.error(this, "updateInventoryTestEndDate", e);
        throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
     }   
     return list;
  }
   /**
    * @return
    */   
   public List<NewStudySummaryVO> updateInventoryInitiateDate(List<NewStudySummaryVO> scheduleIds, String updateUser,Date initiateDate,List<EditReasonVO> editReasonVO,List<NewStudySummaryVO> allScheduleIds)
     throws EAPharmicsException {
     checkSession();
     List<NewStudySummaryVO> list = new ArrayList<NewStudySummaryVO>();
     try {
        NewStudyLoginImpl impl = new NewStudyLoginImpl();
        list = impl.updateInventoryInitiateDate(scheduleIds,updateUser,initiateDate,getUser(),editReasonVO,allScheduleIds);
     } catch (EAPharmicsException e) {
        if (e.getCause() instanceof EAPharmicsException) {
           throw (EAPharmicsException) e.getCause();
        }
        throw e;
     } catch (RuntimeException e) {
        logger.error(this, "updateInventoryInitialDate", e);
        throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
     }
     return list;
  }
   
	/**
	 * @param studyInventoryDestroyedId
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<StudyInventoryDestroyedVO> getAllStudyInventoryDestroyeds(Long prdStudyScheduleId) 
		throws EAPharmicsException {
		checkSession();
		List<StudyInventoryDestroyedVO> list = new ArrayList<StudyInventoryDestroyedVO>();
		try {
			NewStudyLoginImpl impl = new NewStudyLoginImpl();
			list = impl.getAllStudyInventoryDestroyeds(prdStudyScheduleId);			
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "getStudyInventoryDestroyeds", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		}
		return list;
	}

	/**
	 * @param voObject
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<StudyInventoryDestroyedVO> saveStudyInventoryDestroyed(
			Long prdStudyScheduleId, StudyInventoryDestroyedVO voObject,
			List editReasons) 
	throws EAPharmicsException {
		checkSession();
		List<StudyInventoryDestroyedVO> list = new ArrayList<StudyInventoryDestroyedVO>();
		try {
			NewStudyLoginImpl impl = new NewStudyLoginImpl();
			impl.saveStudyInventoryDestroyed(voObject, editReasons, getUser());
			return impl.getAllStudyInventoryDestroyeds(prdStudyScheduleId);		
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "saveStudyInventoryDestroyed", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.");
		}
	}

	/**
	 * @param labelId
	 * @param userVO
	 * @throws EAPharmicsException
	 */
	public List<LabelVO> copyLabel(Long labelId) throws EAPharmicsException {
		try {
			checkSession();
			NewStudyLoginImpl impl = new NewStudyLoginImpl();
			impl.copyLabel(labelId, getUser());
			return new LabelHelper().getAllLabels();
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "copyLabel", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		}
	}

	/**
	 * @param newStudyProductId
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<NewStudyProductLotVO> getProtocolReportFilterData()
			throws EAPharmicsException {
		checkSession();
		try {
			NewStudyLoginImpl impl = new NewStudyLoginImpl();
			return impl.getProtocolReportFilterData();
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "getProtocolReportFilterData", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		}
	}
	
   /**
    * @param newStudyScheduleIds
    * @throws EAPharmicsException
    */
   public List revertNewStudySchedules(List<Long> newStudyScheduleIds,String scheduleStatus,List returnScheduleIds) throws EAPharmicsException {
      checkSession();
      List<NewStudyScheduleVO> list = new ArrayList<NewStudyScheduleVO>();
      try {
         NewStudyLoginImpl impl = new NewStudyLoginImpl();
         return impl.revertNewStudySchedules(newStudyScheduleIds, scheduleStatus,returnScheduleIds,getUser());
      } catch (EAPharmicsException e) {
         if (e.getCause() instanceof EAPharmicsException) {
            throw (EAPharmicsException) e.getCause();
         }
         throw e;
      } catch (RuntimeException e) {
         logger.error(this, "updateNewStudySchedules", e);
         throw new EAPharmicsException("Unable to process request. Please contact support.");
      }
   }
   /**
    * @return
    */
   public List<NewStudySummaryVO> getAllPulledNewStudySummaries(
         String scheduleStatus, String barCode, String studyId,
         String productCode, String lotNumber, String productType,
         Date startDate, Date endDate) throws EAPharmicsException {
      checkSession();
      List<NewStudySummaryVO> list = new ArrayList<NewStudySummaryVO>();
      try {
         NewStudyLoginImpl impl = new NewStudyLoginImpl();
         list = impl.getAllNewStudySummaries(scheduleStatus, barCode,
               studyId, productCode, lotNumber, productType, startDate, endDate, null, null,true,true); 
      } catch (EAPharmicsException e) {
         if (e.getCause() instanceof EAPharmicsException) {
            throw (EAPharmicsException) e.getCause();
         }
         throw e;
      } catch (RuntimeException e) {
         logger.error(this, "getAllNewStudySummaries", e);
         throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
      }
      return list;
   }   
   public List<NewStudySummaryVO> updateInventoryExtraPulled(List<NewStudySummaryVO> scheduleIds,String pulledBy,Date pullDate,int extrasPulled,List<EditReasonVO> editReasonVO,List<NewStudySummaryVO> allScheduleIds) 
   throws EAPharmicsException {
   checkSession();
   List<NewStudySummaryVO> list = new ArrayList<NewStudySummaryVO>();
   //List<NewStudySummaryVO> list = new ArrayList<NewStudySummaryVO>();
   try {
      NewStudyLoginImpl impl = new NewStudyLoginImpl();
      list = impl.updateInventoryExtraPulled(scheduleIds,pulledBy,pullDate,extrasPulled,getUser(),editReasonVO,allScheduleIds);
   } catch (EAPharmicsException e) {
      if (e.getCause() instanceof EAPharmicsException) {
         throw (EAPharmicsException) e.getCause();
      }
      throw e;
   } catch (RuntimeException e) {
      logger.error(this, "updateInventoryExtraPulled", e);
      throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
   }   
      return list;
   }    
   
   public List<NewStudySummaryVO> restoreInventoryPull(List<NewStudySummaryVO> scheduleIds,List<EditReasonVO> editReasonVO,List<NewStudySummaryVO> allScheduleIds) 
	   throws EAPharmicsException {
	   checkSession();
	   List<NewStudySummaryVO> list = new ArrayList<NewStudySummaryVO>();
	   //List<NewStudySummaryVO> list = new ArrayList<NewStudySummaryVO>();
	   try {
	      NewStudyLoginImpl impl = new NewStudyLoginImpl();
	      list = impl.restoreInventoryPull(scheduleIds,getUser(),editReasonVO,allScheduleIds);
	   } catch (EAPharmicsException e) {
	      if (e.getCause() instanceof EAPharmicsException) {
	         throw (EAPharmicsException) e.getCause();
	      }
	      throw e;
	   } catch (RuntimeException e) {
	      logger.error(this, "restoreInventoryPull", e);
	      throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
	   }   
	   return list;
   } 
   
	/**
	 * @param studyIds
	 * @param productTestId
	 * @return
	 */
	public List getMultipleRegressionReportData(List studyIds, Long productTestId, Boolean chkT0PercentCalc, String lowerLimit, String upperLimit)
	   throws EAPharmicsException {
	   try {
		   NewStudyLoginImpl impl = new NewStudyLoginImpl();
		   return impl.getMultipleRegressionReportData(studyIds, productTestId, chkT0PercentCalc, lowerLimit, upperLimit);
	   } catch (RuntimeException e) {
	      logger.error(this, "getMultipleRegressionReportData", e);
	      throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
	   }   
	}
	
	public List getAttachmentsForStudy(String studyId) throws EAPharmicsException {
		try {
			NewStudyLoginImpl impl = new NewStudyLoginImpl();
			   return impl.getAttachmentsForStudy(studyId);
		   } catch (RuntimeException e) {
		      logger.error(this, "getAttachmentsForStudy", e);
		      throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		   }
	}

	public List deleteAttachmentForStudy(String studyId, String fileName) throws EAPharmicsException {
		try {
			NewStudyLoginImpl impl = new NewStudyLoginImpl();
			   return impl.deleteAttachmentForStudy(studyId, fileName);
		   } catch (RuntimeException e) {
		      logger.error(this, "deleteAttachmentForStudy", e);
		      throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		   }
	}

	/**
	 * @param studyIds
	 * @param productTestId
	 * @return
	 */
	public List getSingleRegressionReportData(String studyId, Long productTestId)
	   throws EAPharmicsException {
	   try {
		   NewStudyLoginImpl impl = new NewStudyLoginImpl();
			return impl.getSingleRegressionReportData(studyId, productTestId);
	   } catch (RuntimeException e) {
	      logger.error(this, "getSingleRegressionReportData", e);
	      throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
	   }   
	}

	/**
	 * @param studyId
	 * @param productTestId
	 * @param t0PercentCalc
	 * @param lowerLimit
	 * @param upperLimt
	 * @return
	 * @throws EAPharmicsException
	 */
	public List getSingleRegressionReportData(String studyId, Long productTestId, 
			Boolean t0PercentCalc, String lowerLimit, String upperLimt)
	   throws EAPharmicsException {
	   try {
		   NewStudyLoginImpl impl = new NewStudyLoginImpl();
		   return impl.getSingleRegressionReportData(studyId, productTestId,
					t0PercentCalc, lowerLimit, upperLimt);
	   } catch (RuntimeException e) {
	      logger.error(this, "getSingleRegressionReportData", e);
	      throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
	   }   
	}
	/**
	 * @param newStudyProductId
	 * @return
	 * @throws EAPharmicsException
	 */
	public NewStudyScreenDataVO getNewStudyProductAndDifferentProduct(Long prdStudyBatchId, Long productId) 
	throws EAPharmicsException {
		checkSession();
		try {
			NewStudyLoginImpl impl = new NewStudyLoginImpl();
			NewStudyScreenDataVO screenVO = new NewStudyScreenDataVO();
			screenVO.setNewStudyProductVO(impl.getNewStudyProduct(prdStudyBatchId));
			screenVO.setProductVO((new ProductImpl()).getProduct(productId));
			return screenVO;
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "getNewStudyProductAndDifferentProduct", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.");
		}
	}	


	/**
	 * @param studyId
	 * @param productTestId
	 * @return
	 */
	public List<StoredDataVO> getDataEntryResults(String studyId, Long productTestId) 
	throws EAPharmicsException {
		checkSession();
		try {
			NewStudyLoginImpl impl = new NewStudyLoginImpl();
			return impl.getDataEntryResults(studyId, productTestId);
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "getDataEntryResults", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.");
		}
	}	

	/**
	 * @param studyId
	 * @param intervalValue
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<DataEntryVO> getDataEntryResultsSummary(String studyId,
			int intervalValue) throws EAPharmicsException {
		checkSession();
		try {
			NewStudyLoginImpl impl = new NewStudyLoginImpl();
			return impl.getDataEntryResultsSummary(studyId, intervalValue);
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "getDataEntryResultsSummary", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.");
		}
	}

   /**
    * @return
    * This is for Log Manipulation Module
    */
   public List<NewStudySummaryVO> getAllNewStudySummariesForLMM(
         String scheduleStatus, String barCode, String studyId,
         String productCode, String lotNumber, String productType,
         Date startDate, Date endDate) throws EAPharmicsException {
      checkSession();
      List<NewStudySummaryVO> list = new ArrayList<NewStudySummaryVO>();
      try {
         NewStudyLoginImpl impl = new NewStudyLoginImpl();
         list = impl.getAllNewStudySummaries(scheduleStatus, barCode,
               studyId, productCode, lotNumber, productType, startDate, endDate, null, null,null,true);
      } catch (EAPharmicsException e) {
         if (e.getCause() instanceof EAPharmicsException) {
            throw (EAPharmicsException) e.getCause();
         }
         throw e;
      } catch (RuntimeException e) {
         logger.error(this, "getAllNewStudySummaries", e);
         throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
      }
      return list;
   }
   
   public List<CodeSwitchProductVO> getCodeSwitchProductsForProduct(String productCode) throws EAPharmicsException {
	   checkSession();
	   List<CodeSwitchProductVO> list = new ArrayList<CodeSwitchProductVO>();
      try {
          NewStudyLoginImpl impl = new NewStudyLoginImpl();
          list = impl.getCodeSwitchProductsForProduct(productCode);
       } catch (EAPharmicsException e) {
          if (e.getCause() instanceof EAPharmicsException) {
             throw (EAPharmicsException) e.getCause();
          }
          throw e;
       } catch (RuntimeException e) {
          logger.error(this, "getCodeSwitchProductsForProduct", e);
          throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
       }
       return list;
   }
   /**
    * @param rptUserPreferenceId
    * @return
    * @throws EAPharmicsException
    */
   public List<RptUserPreferenceVO> getAllRptUserPreferences(List<String> reportGroupName, String reportName) 
      throws EAPharmicsException {
      checkSession();
      List<RptUserPreferenceVO> list = new ArrayList<RptUserPreferenceVO>();
      try {
         NewStudyLoginImpl impl = new NewStudyLoginImpl();
         list = impl.getAllRptUserPreferences(reportGroupName,reportName,getUser());         
      } catch (EAPharmicsException e) {
         if (e.getCause() instanceof EAPharmicsException) {
            throw (EAPharmicsException) e.getCause();
         }
         throw e;
      } catch (RuntimeException e) {
         logger.error(this, "getRptUserPreferences", e);
         throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
      }
      return list;
   }
   /**
    * @param rptUserPreferenceId
    * @return
    * @throws EAPharmicsException
    */
   public List<RptUserPreferenceVO> getAllRptLocationPreferences(List<String> reportGroupName, String reportName) 
      throws EAPharmicsException {
      checkSession(); 
      List<RptUserPreferenceVO> list = new ArrayList<RptUserPreferenceVO>();
      try {
         NewStudyLoginImpl impl = new NewStudyLoginImpl();
         list = impl.getAllRptLocationPreferences(reportGroupName,reportName,getUser());         
      } catch (EAPharmicsException e) {
         if (e.getCause() instanceof EAPharmicsException) {
            throw (EAPharmicsException) e.getCause();
         }
         throw e;
      } catch (RuntimeException e) {
         logger.error(this, "getRptUserPreferences", e);
         throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
      }
      return list; 
   }
   /**
    * @param voObject
    * @return
    * @throws EAPharmicsException
    */
   public List<RptUserPreferenceVO> updateRptUserPreference(List<RptUserPreferenceVO> voObject) 
   throws EAPharmicsException {
      checkSession();
      NewStudyLoginImpl impl = new NewStudyLoginImpl();
      List<RptUserPreferenceVO> list = new ArrayList<RptUserPreferenceVO>();
      try {           
            list = impl.updateRptUserPreference(voObject, getUser());     
      } catch (EAPharmicsException e) {
         if (e.getCause() instanceof EAPharmicsException) {
            throw (EAPharmicsException) e.getCause();
         }
         throw e;
      } catch (RuntimeException e) {
         logger.error(this, "updateRptUserPreference", e);
         throw new EAPharmicsException("Unable to process request. Please contact support.");
      }
      return list;
   } 
   /**
    * @param voObject
    * @return
    * @throws EAPharmicsException
    */
   public List<RptUserPreferenceVO> updateRptLocationPreference(List<RptUserPreferenceVO> voObject) 
   throws EAPharmicsException {
      checkSession();
      NewStudyLoginImpl impl = new NewStudyLoginImpl();
      List<RptUserPreferenceVO> list = new ArrayList<RptUserPreferenceVO>();
      try {           
            list = impl.updateRptUserPreference(voObject, getUser());     
      } catch (EAPharmicsException e) {
         if (e.getCause() instanceof EAPharmicsException) {
            throw (EAPharmicsException) e.getCause();
         }
         throw e;
      } catch (RuntimeException e) {
         logger.error(this, "updateRptUserPreference", e);
         throw new EAPharmicsException("Unable to process request. Please contact support.");
      }
      return list;
   }   
   /**
    * @return
    * This is for Log Manipulation Module
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
      checkSession();
      List<NewStudySummaryVO> list = new ArrayList<NewStudySummaryVO>();
      try {
         NewStudyLoginImpl impl = new NewStudyLoginImpl();
         list = impl.getPendingSchedulerReportData(scheduleIds,
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
      } catch (EAPharmicsException e) {
         if (e.getCause() instanceof EAPharmicsException) {
            throw (EAPharmicsException) e.getCause();
         }
         throw e;
      } catch (RuntimeException e) {
         logger.error(this, "getPendingSchedulerReportData", e);
         throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
      }
      return list;
   }
   /**
    * @return
    * This is for Print Module --> Stability Database Logs
    */
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
         checkSession();
         List<ProductStudyIntervalsVO> list = new ArrayList<ProductStudyIntervalsVO>();
         try {
            NewStudyLoginImpl impl = new NewStudyLoginImpl();
            list = impl.getStabilityLogsReportData(stabilityYear,
                                                   fromStabilityDate,
                                                   toStabilityDate,
                                                   productCode,  
                                                   lotNumber,
                                                   containerCode,
                                                   studyStatus,
                                                   studyId,
                                                   primarySortColumn,
                                                   primarySortColumnOrder,
                                                   secondarySortColumn,
                                                   secondarySortColumnOrder);
         } catch (EAPharmicsException e) {
            if (e.getCause() instanceof EAPharmicsException) {
               throw (EAPharmicsException) e.getCause();
            }
            throw e;
         } catch (RuntimeException e) {
            logger.error(this, "getStabilityLogsReportData", e);
            throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
         }
         return list;
   }   
}
