package com.enviroapps.eapharmics.ui.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enviroapps.eapharmics.exception.EAPharmicsException;
import com.enviroapps.eapharmics.services.ProductImpl;
import com.enviroapps.eapharmics.ui.AbstractEAPharmicsService;
import com.enviroapps.eapharmics.ui.Constants;
import com.enviroapps.eapharmics.ui.newstudy.NewStudyLoginService;
import com.enviroapps.eapharmics.vo.product.InventoryVO;
import com.enviroapps.eapharmics.vo.product.ProductSpecVO;
import com.enviroapps.eapharmics.vo.product.ProductSummaryVO;
import com.enviroapps.eapharmics.vo.product.ProductVO;
import com.enviroapps.eapharmics.vo.product.ProtocolsForProductVO;
import com.enviroapps.eapharmics.vo.product.TestVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/eapharmics/product")
@RequiredArgsConstructor
public class ProductService extends AbstractEAPharmicsService {

	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	/**
	 * @param productCode
	 * @return
	 * @throws EAPharmicsException
	 */
	public Boolean productExistsForProductCode(String productCode) 
		throws EAPharmicsException {
		checkSession();
		try {
			ProductImpl impl = new ProductImpl();
			return impl.productExistsForProductCode(productCode);			
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error("productExistsForProductCode", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		}
	}

	/**
	 * @param productId
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<ProductSummaryVO> getAllProductsSummary() 
		throws EAPharmicsException {
		checkSession();
		List<ProductSummaryVO> list = new ArrayList<ProductSummaryVO>();
		try {
			ProductImpl impl = new ProductImpl();
			list = impl.getAllProductsSummary(null, false);			
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error("getAllProductsSummary", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		}
		return list;
	}

	/**
	 * @param productId
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<ProductSummaryVO> getControlledProductsSummary() 
		throws EAPharmicsException {
		checkSession();
		List<ProductSummaryVO> list = new ArrayList<ProductSummaryVO>();
		try {
			ProductImpl impl = new ProductImpl();
			list = impl.getAllProductsSummary(Constants.CONTROLLED_SUBSTANCE, false);			
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error("getAllProductsSummary", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		}
		return list;
	}

	/**
	 * @param productId
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<ProductSummaryVO> getRegularProductsSummary() 
		throws EAPharmicsException {
		checkSession();
		List<ProductSummaryVO> list = new ArrayList<ProductSummaryVO>();
		try {
			ProductImpl impl = new ProductImpl();
			list = impl.getAllProductsSummary(Constants.REGULAR_SUBSTANCE, false);			
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error("getAllProductsSummary", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		}
		return list;
	}

	/**
	 * @param productId
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<ProductSummaryVO> getAllProductsSummary(boolean forApproval ) 
		throws EAPharmicsException {
		checkSession();
		List<ProductSummaryVO> list = new ArrayList<ProductSummaryVO>();
		try {
			ProductImpl impl = new ProductImpl();
			list = impl.getAllProductsSummary(null, forApproval);			
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error("getAllProductsSummaryForApproval", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		}
		return list;
	}

	/**
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<ProductSummaryVO> getAllProductsSummaryForAnnualReport() 
		throws EAPharmicsException {
		checkSession();
		List<ProductSummaryVO> list = new ArrayList<ProductSummaryVO>();
		try {
			ProductImpl impl = new ProductImpl();
			list = impl.getAllProductsSummary(null, false);
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				ProductSummaryVO productSummaryVO = (ProductSummaryVO) iterator
						.next();
				//Send only products whose anniversary date is set
				if (productSummaryVO.getAnnualReportAnniversaryDate() == null) {
					iterator.remove();
				}
			}
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error("getAllProductsSummaryForAnnualReport", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		}
		return list;
	}

	/**
	 * @param productId
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<ProductVO> getAllProducts() 
		throws EAPharmicsException {
		checkSession();
		List<ProductVO> list = new ArrayList<ProductVO>();
		try {
			ProductImpl impl = new ProductImpl();
			list = impl.getAllProducts();			
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error("getProducts", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		}
		return list;
	}

	/**
	 * @param productId
	 * @return
	 * @throws EAPharmicsException
	 */
	@GetMapping("/getProduct")
	public ProductVO getProduct(@RequestParam(value = "productId") Long productId) 
		throws EAPharmicsException {
		checkSession();
		try {
			ProductImpl impl = new ProductImpl();
			return impl.getProduct(productId);			
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error("getProduct", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		}
	}

	/**
	 * @param voObject
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<ProductSummaryVO> saveProduct(ProductVO voObject, List editReasons, 
			boolean submitForApproval, boolean level1Approval, boolean level2Approval2,
			boolean unapprove, boolean isAuthorize) 
	throws EAPharmicsException {
		checkSession();
		List<ProductSummaryVO> list = new ArrayList<ProductSummaryVO>();
		try {
			ProductImpl impl = new ProductImpl();
			impl.saveProduct(voObject, editReasons, getUser(),
					submitForApproval, level1Approval, 
					level2Approval2,unapprove);
			list = impl.getAllProductsSummary(null, isAuthorize);		
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error("updateProduct", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.");
		}
		return list;
	}

	/**
	 * @param voObject
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<ProductSummaryVO> saveAnnualReportAnniversaryDate(
			Long productId, Date annualReportAnniversaryDate) 
	throws EAPharmicsException {
		checkSession();
		try {
			ProductImpl impl = new ProductImpl();
			impl.saveAnnualReportAnniversaryDate(productId, annualReportAnniversaryDate, getUser());
			return getAllProductsSummaryForAnnualReport();		
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error("updateProduct", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.");
		}
	}

	/**
	 * @param voObject
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<ProductSummaryVO> deleteProduct(Long productId) 
	throws EAPharmicsException {
		checkSession();
		List<ProductSummaryVO> list = new ArrayList<ProductSummaryVO>();
		try {
			ProductImpl impl = new ProductImpl();
			impl.deleteProduct(productId, getUser());
			list = impl.getAllProductsSummary(null, false);		
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error("updateProduct", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.");
		}
		return list;
	}

	/**
	 * @param productTestId
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<TestVO> getAllTests() 
		throws EAPharmicsException {
		checkSession();
		List<TestVO> list = new ArrayList<TestVO>();
		try {
			ProductImpl impl = new ProductImpl();
			list = impl.getAllTests();			
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error("getAllTests", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		}
		return list;
	}

	/**
	 * @param voObject
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<TestVO> saveTest(TestVO voObject) 
	throws EAPharmicsException {
		checkSession();
		List<TestVO> list = new ArrayList<TestVO>();
		try {
			ProductImpl impl = new ProductImpl();
			impl.saveTest(voObject, getUser());
			list = impl.getAllTests();		
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error("saveTest", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.");
		}
		return list;
	}


	/**
	 * @param voObject
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<TestVO> deleteTest(TestVO voObject) 
	throws EAPharmicsException {
		checkSession();
		List<TestVO> list = new ArrayList<TestVO>();
		try {
			ProductImpl impl = new ProductImpl();
			impl.deleteTest(voObject.getTestId());
			list = impl.getAllTests();		
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error("deleteTest", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.");
		}
		return list;
	}


	/**
	 * @param inventoryId
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<InventoryVO> getAllInventories(Long protocolId) 
		throws EAPharmicsException {
		checkSession();
		List<InventoryVO> list = new ArrayList<InventoryVO>();
		try {
			ProductImpl impl = new ProductImpl();
			list = impl.getAllInventories(protocolId);			
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error("getInventorys", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		}
		return list;
	}

	/**
	 * @param productSpecId
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<ProductSpecVO> getAllProductSpecs(Long productId) 
		throws EAPharmicsException {
		checkSession();
		List<ProductSpecVO> list = new ArrayList<ProductSpecVO>();
		try {
			ProductImpl impl = new ProductImpl();
			list = impl.getAllProductSpecs(productId);			
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error("getProductSpecs", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		}
		return list;
	}

	/**
	 * @param voObject
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<ProductSpecVO> saveProductSpecs(Long productId, List voObjects) 
	throws EAPharmicsException {
		checkSession();
		List<ProductSpecVO> list = new ArrayList<ProductSpecVO>();
		try {
			ProductImpl impl = new ProductImpl();
			impl.saveProductSpecs(voObjects, getUser());
			list = impl.getAllProductSpecs(productId);		
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error("updateProductSpec", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.");
		}
		return list;
	}
	/**
	 * @param productId
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<ProductSummaryVO> getAllApprovedProductsSummary() 
		throws EAPharmicsException {
		checkSession();
		List<ProductSummaryVO> list = new ArrayList<ProductSummaryVO>();
		try {
			ProductImpl impl = new ProductImpl();
			list = impl.getAllApprovedProductsSummary();			
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error("getAllApprovedProductsSummary", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		}
		return list;
	}

	/**
	 * @param productId
	 * @return
	 * @throws EAPharmicsException
	 */
	@GetMapping("/getAllApprovedProductsSummaryWithGraph")
	public List<ProductSummaryVO> getAllApprovedProductsSummaryWithGraph() 
		throws EAPharmicsException {
		checkSession();
		List<ProductSummaryVO> list = new ArrayList<ProductSummaryVO>();
		try {
			ProductImpl impl = new ProductImpl();
			list = impl.getAllApprovedProductsSummaryWithGraph();			
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error("getAllApprovedProductsSummaryWithGraph", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		}
		return list;
	}
	
	public List<ProtocolsForProductVO> getAllProtocolsForProduct(String productCode)
		throws EAPharmicsException {
		checkSession();
		List<ProtocolsForProductVO> list = new ArrayList<ProtocolsForProductVO>();
		try {
			ProductImpl impl = new ProductImpl();
			list = impl.getAllProtocolsForProduct(productCode);
		} catch(EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error("getAllApprovedProductsSummaryWithGraph", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		}
		return list;
	}

}
