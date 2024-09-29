package com.enviroapps.eapharmics.ui.daes;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enviroapps.eapharmics.exception.EAPharmicsException;
import com.enviroapps.eapharmics.services.DataAccessEvaluationImpl;
import com.enviroapps.eapharmics.services.ProductImpl;
import com.enviroapps.eapharmics.ui.AbstractEAPharmicsService;
import com.enviroapps.eapharmics.vo.dataaccessevaluation.BarCodeSearchResultVO;
import com.enviroapps.eapharmics.vo.dataaccessevaluation.DataAccessEvaluationVO;
import com.enviroapps.eapharmics.vo.dataaccessevaluation.GraphDataVO;
import com.enviroapps.eapharmics.vo.dataaccessevaluation.RegressionReportVO;
import com.enviroapps.eapharmics.vo.newstudy.GraphGeneratorVO;
import com.enviroapps.eapharmics.vo.newstudy.NewStudyProductVO;
import com.enviroapps.eapharmics.vo.product.ProductVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/eapharmics/dataaccess")
@RequiredArgsConstructor
public class DataAccessEvaluationService extends AbstractEAPharmicsService {
	
	/**
	 * @param productId
	 * @param studyId
	 * @param lotNumber
	 * @param passCondition
	 * @param sendYear
	 * @return
	 * @throws EAPharmicsException
	 */
	@GetMapping("/getAllDataAccessEvaluation")
	public List<DataAccessEvaluationVO> getAllDataAccessEvaluation(
			@RequestParam(value = "productId") Long productId, 
			@RequestParam(value = "studyIds") ArrayList<Long> studyIds, 
			@RequestParam(value = "lotNumber")String lotNumber,
			@RequestParam(value = "passCondition") String passCondition, 
			@RequestParam(value = "sendYear") String sendYear) throws EAPharmicsException {
		checkSession();
		List<DataAccessEvaluationVO> list = new ArrayList<DataAccessEvaluationVO>();
		try {
			DataAccessEvaluationImpl impl = new DataAccessEvaluationImpl();
			list = impl.getAllDataAccessEvaluation(productId, studyIds,
					lotNumber, passCondition, sendYear);
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
			throw new EAPharmicsException(
					"Unable to process request. Please contact support.\n"
							+ e.getMessage());
		}
		return list;
	}	
	
	/**
	 * @param studyId
	 * @param productId
	 * @return
	 * @throws EAPharmicsException
	 */
	@GetMapping("/getAllDataAccessEvaluationForStudyId")
	public BarCodeSearchResultVO getAllDataAccessEvaluationForStudyId(
			@RequestParam(value = "studyId") String studyId, 
			@RequestParam(value = "productId") Long productId) throws EAPharmicsException {
		checkSession();
		BarCodeSearchResultVO resultVO = new BarCodeSearchResultVO();
		try {
			List<DataAccessEvaluationVO> list = new ArrayList<DataAccessEvaluationVO>();
			DataAccessEvaluationImpl impl = new DataAccessEvaluationImpl();
			Long nProductId = impl.getStudyProductId(studyId);
			if (nProductId == null) {
				throw new EAPharmicsException("Unable to get product for Bar code");
			}
			if (productId != null && productId.longValue() != 0) {
				if (nProductId.longValue() != productId.longValue()) {
					throw new EAPharmicsException("Study does not belong to the same product");
				}
			}
			
			ArrayList<String> studyIds = new ArrayList<String>();
			studyIds.add(studyId);
			list = impl.getAllDataAccessEvaluation(nProductId, studyIds, "", "", "");
			if (list != null && list.size() > 0) {
				resultVO.setResult(list.get(0));
			}
			if (productId == null || productId.longValue() == 0) {
				ProductImpl pImpl = new ProductImpl();
				ProductVO productVO = pImpl.getProduct(nProductId);
				resultVO.setProductVO(productVO);
			}
			return resultVO;			
			
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "getAllDataAccessEvaluationForStudyId", e);
			throw new EAPharmicsException(
					"Unable to process request. Please contact support.\n"
							+ e.getMessage());
		}
	}
	
	/**
	 * @param productId
	 * @return
	 * @throws EAPharmicsException
	 */
	@GetMapping("/getNewStudyDetails")
	public List<NewStudyProductVO> getNewStudyDetails(
			@RequestParam(value = "productId") String productId)
			throws EAPharmicsException {

		checkSession();
		List<NewStudyProductVO> list = new ArrayList<NewStudyProductVO>();
		try {
			DataAccessEvaluationImpl impl = new DataAccessEvaluationImpl();
			list = impl.getNewStudyDetails(productId);
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "getNewStudyDetails", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		}
		return list;
	}
	
	
	
	/**
	 * @param liststudyIdCollection
	 * @param chartData
	 * @param productIds
	 * @param productId
	 * @param Year
	 * @param studyId
	 * @param testId
	 * @return
	 * @throws EAPharmicsException
	 */
	
	@GetMapping("/getDataAccessEvaluationChart")
	public List<GraphGeneratorVO> getDataAccessEvaluationChart(
			@RequestParam(value = "liststudyIdCollection") ArrayList liststudyIdCollection, 
			@RequestParam(value = "chartData") ArrayList chartData,
			@RequestParam(value = "productIds") ArrayList<Long> productIds, 
			@RequestParam(value = "productId") Long productId, 
			@RequestParam(value = "Year") String Year, 
			@RequestParam(value = "studyId") String studyId,
			@RequestParam(value = "testId") Long testId) throws EAPharmicsException {
		checkSession();
		List<GraphGeneratorVO> list = new ArrayList<GraphGeneratorVO>();
		try {
			DataAccessEvaluationImpl impl = new DataAccessEvaluationImpl();
			list = impl.getDataAccessEvaluationChart(liststudyIdCollection,
					chartData, productIds, productId, Year, studyId, testId);
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
			throw new EAPharmicsException(
					"Unable to process request. Please contact support.\n"
							+ e.getMessage());
		}
		return list;
	}
	
	/**
	 * @param studyIds
	 * @param productTestIds
	 * @param productIds
	 * @param Year
	 * @param t0percentCalc
	 * @param lowerLimit
	 * @param upperLimit
	 * @return
	 * @throws EAPharmicsException
	 */
	@GetMapping("/getChartData")
	public List<GraphDataVO> getChartData(
			@RequestParam(value = "studyIds") ArrayList<Long> studyIds,
			@RequestParam(value = "productTestIds") ArrayList<Long> productTestIds, 
			@RequestParam(value = "productIds") ArrayList<Long> productIds, 
			@RequestParam(value = "Year") String Year,
			@RequestParam(value = "t0percentCalc") Boolean t0percentCalc, 
			@RequestParam(value = "lowerLimit") String lowerLimit, 
			@RequestParam(value = "upperLimit") String upperLimit)
			throws EAPharmicsException {
		checkSession();
		List<GraphDataVO> list = new ArrayList<GraphDataVO>();
		try {
			DataAccessEvaluationImpl impl = new DataAccessEvaluationImpl();
			list = impl.getChartData(studyIds, productTestIds, productIds, Year, t0percentCalc, lowerLimit, upperLimit);
			if (list == null) {
				list = new ArrayList<GraphDataVO>();
			}
			return list;
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "getChartData", e);
			throw new EAPharmicsException(
					"Unable to process request. Please contact support.\n"
							+ e.getMessage());
		}
	}
	
	/**
	 * @param productId
	 * @param studyId
	 * @param lotNumber
	 * @param passCondition
	 * @param sendYear
	 * @return
	 * @throws EAPharmicsException
	 */
	@GetMapping("/getChartDisplayParameters")
	public List<DataAccessEvaluationVO> getChartDisplayParameters(
			@RequestParam(value = "productId") Long productId,
			@RequestParam(value = "studyId") ArrayList studyId,
			@RequestParam(value = "lotNumber") String lotNumber,
			@RequestParam(value = "passCondition") String passCondition,
			@RequestParam(value = "sendYear") String sendYear) 
	throws EAPharmicsException {
	checkSession();
	List<DataAccessEvaluationVO> list = new ArrayList<DataAccessEvaluationVO>();
	try {
		DataAccessEvaluationImpl impl = new DataAccessEvaluationImpl();
		list = impl.getChartDisplayParameters(productId,studyId,lotNumber,passCondition,sendYear);
		if (list == null) {
			list = new ArrayList();
		}
	} catch (EAPharmicsException e) {
		if (e.getCause() instanceof EAPharmicsException) {
			throw (EAPharmicsException) e.getCause();
		}
		throw e;
	} catch (RuntimeException e) {
		logger.error(this, "getChartDisplayParameters", e);
		throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
	}
	return list;
	}
	
	/**
	 * @param productId
	 * @param studyId
	 * @param lotNumber
	 * @param passCondition
	 * @param sendYear
	 * @return
	 * @throws EAPharmicsException
	 */
	@GetMapping("/getChartDisplayParametersTest")
	public List<DataAccessEvaluationVO> getChartDisplayParametersTest(
			@RequestParam(value = "productId") ArrayList productId,
			@RequestParam(value = "studyId") ArrayList studyId,
			@RequestParam(value = "lotNumber") String lotNumber,
			@RequestParam(value = "passCondition") String passCondition,
			@RequestParam(value = "sendYear") String sendYear) 
	throws EAPharmicsException {
	checkSession();
	List<DataAccessEvaluationVO> list = new ArrayList<DataAccessEvaluationVO>();
	try {
		DataAccessEvaluationImpl impl = new DataAccessEvaluationImpl();
		list = impl.getChartDisplayParametersTest(productId,studyId,lotNumber,passCondition,sendYear);
		if (list == null) {
			list = new ArrayList();
		}
	} catch (EAPharmicsException e) {
		if (e.getCause() instanceof EAPharmicsException) {
			throw (EAPharmicsException) e.getCause();
		}
		throw e;
	} catch (RuntimeException e) {
		logger.error(this, "getChartDisplayParameters", e);
		throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
	}
	return list;
}
	
	
	/**
	 * @param chartData
	 * @param studyId
	 * @return
	 * @throws EAPharmicsException
	 */
	@GetMapping("/getNumberOfResults")
	public List<RegressionReportVO> getNumberOfResults(
			@RequestParam(value = "chartData") ArrayList<RegressionReportVO> chartData,
			@RequestParam(value = "studyId") String studyId) 
	throws EAPharmicsException {
	checkSession();
	List<RegressionReportVO> list = new ArrayList<RegressionReportVO>();
	try {
		DataAccessEvaluationImpl impl = new DataAccessEvaluationImpl();
		list = impl.getNumberOfResults(chartData,studyId);
		if (list == null) {
			list = new ArrayList();
		}
	} catch (EAPharmicsException e) {
		if (e.getCause() instanceof EAPharmicsException) {
			throw (EAPharmicsException) e.getCause();
		}
		throw e;
	} catch (RuntimeException e) {
		logger.error(this, "getNumberOfResults", e);
		throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
	}
	return list;
}	
}
