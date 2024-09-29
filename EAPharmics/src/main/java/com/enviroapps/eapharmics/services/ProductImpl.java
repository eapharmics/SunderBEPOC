package com.enviroapps.eapharmics.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import com.enviroapps.eapharmics.bom.admin.ApplParameter;
import com.enviroapps.eapharmics.bom.product.Inventory;
import com.enviroapps.eapharmics.bom.product.Product;
import com.enviroapps.eapharmics.bom.product.ProductBatch;
import com.enviroapps.eapharmics.bom.product.ProductFormulation;
import com.enviroapps.eapharmics.bom.product.ProductSpec;
import com.enviroapps.eapharmics.bom.product.ProductTest;
import com.enviroapps.eapharmics.bom.product.Protocol;
import com.enviroapps.eapharmics.bom.product.ProtocolDetail;
import com.enviroapps.eapharmics.bom.product.Test;
import com.enviroapps.eapharmics.bom.security.AppUser;
import com.enviroapps.eapharmics.common.Utility;
import com.enviroapps.eapharmics.exception.EAPharmicsException;
import com.enviroapps.eapharmics.persistence.AdminFactory;
import com.enviroapps.eapharmics.persistence.ProductFactory;
import com.enviroapps.eapharmics.persistence.SecurityFactory;
import com.enviroapps.eapharmics.ui.Constants;
import com.enviroapps.eapharmics.util.MailHelper;
import com.enviroapps.eapharmics.vo.product.InventoryVO;
import com.enviroapps.eapharmics.vo.product.ProductBatchVO;
import com.enviroapps.eapharmics.vo.product.ProductFormulationVO;
import com.enviroapps.eapharmics.vo.product.ProductSpecVO;
import com.enviroapps.eapharmics.vo.product.ProductSummaryVO;
import com.enviroapps.eapharmics.vo.product.ProductTestVO;
import com.enviroapps.eapharmics.vo.product.ProductVO;
import com.enviroapps.eapharmics.vo.product.ProtocolDetailVO;
import com.enviroapps.eapharmics.vo.product.ProtocolVO;
import com.enviroapps.eapharmics.vo.product.ProtocolsForProductVO;
import com.enviroapps.eapharmics.vo.product.TestVO;
import com.enviroapps.eapharmics.vo.security.EditReasonVO;
import com.enviroapps.eapharmics.vo.security.UserVO;

/**
 * @author EnviroApps
 * 
 */
public class ProductImpl extends AbstractServiceImpl {

	private ProductFactory productFactory = ProductFactory.getInstance();

	/**
	 * @return
	 */
	public List getAllTests() throws EAPharmicsException {
		List<Test> list1 = productFactory.getAllTests();
		List<TestVO> list = new ArrayList<TestVO>();
		for (Iterator<Test> iterator = list1.iterator(); iterator.hasNext();) {
			Test bomObject = (Test) iterator.next();
			TestVO voObject = new TestVO();
			list.add(populateTestVO(bomObject, voObject));
		}
		return list;
	}

	/**
	 * @param keyName
	 * @return
	 * @throws EAPharmicsException
	 */
	public TestVO getTest(Long keyName) throws EAPharmicsException {
		Test bomObject = productFactory.getTest(keyName);
		TestVO voObject = new TestVO();
		voObject = populateTestVO(bomObject, voObject);
		return voObject;
	}

	/**
	 * @param productTest
	 */
	public void saveTest(TestVO voObject, UserVO userVO)
			throws EAPharmicsException {
		try {
			super.beginTransaction();
			// Test bomObject =
			// productFactory.getProductTest(voObject.getTestId());
			Test bomObject = null;
			if (bomObject != null) {
				throw new EAPharmicsException(
						"Test already exists for key. Unable to create!");
			}
			if (voObject.getTestId() != null && voObject.getTestId().longValue() != 0) {
				bomObject = productFactory.getTest(voObject.getTestId());
				bomObject.setAuditId(userVO.getAuditId());
				populateTestBOM(bomObject, voObject, userVO);
				productFactory.updateTest(bomObject);
			} else {
				bomObject = new Test();
				bomObject.setInsertDate(Utility.getCurrentUserLocationDateTime());
				bomObject.setInsertUser(userVO.getAppUserId());
				bomObject.setAuditId(userVO.getAuditId());
				populateTestBOM(bomObject, voObject, userVO);
				productFactory.createTest(bomObject);
			}
			super.commitTransaction();
		} catch (Exception e) {
			logException("createProductTest", e);
			try {
				super.rollbackTransaction();
			} catch (Throwable t) {
				logException("createProductTest", e);
			}
			throw new EAPharmicsException(e);
		}
	}

	/**
	 * @param productTest
	 */
	public void deleteTest(Long testId) throws EAPharmicsException {
		try {
			super.beginTransaction();
			productFactory.deleteTest(testId);
			super.commitTransaction();
		} catch (Exception e) {
			logException("deleteProductTest", e);
			try {
				super.rollbackTransaction();
			} catch (Throwable t) {
				logException("updateProductTest", e);
			}
			throw new EAPharmicsException(e);
		}
	}

	/**
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<ProductSummaryVO> getAllProductsSummary(String productType, boolean forApproval) throws EAPharmicsException {
		List details = productFactory.getAllProductsSummary(productType);
		ApplParameter parameter = AdminFactory.getInstance()
			.getApplParameterByName(Constants.SPECIFICATION_APPROVAL_LEVEL);
	    List<ProductSummaryVO> list = new ArrayList();
	    for (Iterator<Object[]> iterator = details.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			ProductSummaryVO vo = new ProductSummaryVO();
			vo.setProductId((Long) objects[0]);
			vo.setProductCode((String) objects[1]);
			vo.setProductName((String) objects[2]);
			vo.setProductDescription((String) objects[3]);
			String approvalLevel = parameter.getParameterValue();
			String approval1 = (String) objects[4];
			String approval2 = (String) objects[5];
			vo.setApprovalLevel1(approval1.equals("Y"));
			vo.setApprovalLevel2(approval2.equals("Y"));
			vo.setExpirationPeriod((String) objects[6]);
			vo.setProductType((String) objects[7]);
			vo.setAnnualReportAnniversaryDate((Date) objects[8]);
			vo.setReadyForApproval(((String)objects[9]).equals("Y"));
			if (approvalLevel.equals("N")) {
				vo.setApproved(true);
			} else if (approvalLevel.equals("S") && "Y".equals(approval1)) {
				vo.setApproved(true);
			} else if (approvalLevel.equals("D") && "Y".equals(approval1) && "Y".equals(approval2)) {
				vo.setApproved(true);
			} else {
				vo.setApproved(false);
			}
			if (forApproval) {
				//Send only the un-approved products for approval
				if (vo.isApprovalLevel1() || vo.isApprovalLevel2() || vo.isReadyForApproval()) {
					list.add(vo);
				}
			} else {
				//request all products
				list.add(vo);
			}
		}
		return list;
	}
	
	/**
	 * @param product
	 */
	public void saveAnnualReportAnniversaryDate(
			Long productId, Date annualReportAnniversaryDate, UserVO userVO) 
		throws EAPharmicsException {
		try {
			super.beginTransaction();
			productFactory.saveAnnualReportAnniversaryDate(productId,
					annualReportAnniversaryDate, userVO);
			super.commitTransaction();
		} catch (Exception e) {
			logException("saveAnnualReportAnniversaryDate", e);
			try {
				super.rollbackTransaction();
			} catch (Throwable t) {
				logException("saveAnnualReportAnniversaryDate", e);
			}
			throw new EAPharmicsException(e);
		}
	}

	/**
	 * @return
	 */
	public List getAllProducts() throws EAPharmicsException {
		List<Product> list1 = productFactory.getAllProducts();
		List<ProductVO> list = new ArrayList<ProductVO>();
		for (Iterator<Product> iterator = list1.iterator(); iterator.hasNext();) {
			Product bomObject = (Product) iterator.next();
			ProductVO voObject = new ProductVO();
			list.add(populateProductVO(bomObject, voObject));
		}
		return list;
	}

	/**
	 * @param productCode
	 * @return
	 * @throws EAPharmicsException
	 */
	public Boolean productExistsForProductCode(String productCode) throws EAPharmicsException {
		//ensure there is no duplicate product code already in the database
		return productFactory.productExistsForProductCode(productCode);
	}
	
	
	/**
	 * @param keyName
	 * @return
	 * @throws EAPharmicsException
	 */
	public ProductVO getProduct(Long keyName) throws EAPharmicsException {
		Product bomObject = productFactory.getProduct(keyName);
		ProductVO voObject = new ProductVO();
		voObject = populateProductVO(bomObject, voObject);
		ApplParameter parameter = AdminFactory.getInstance()
				.getApplParameterByName(Constants.SPECIFICATION_APPROVAL_LEVEL);
		voObject.setApprovalLevelRequired(parameter.getParameterValue());		
		return voObject;
	}

	/**
	 * @param product
	 */
	public void saveProduct(ProductVO voObject, List editReasons, UserVO userVO,
			boolean submitForApproval, boolean level1Approval, boolean level2Approval2,
			boolean unapprove)
			throws EAPharmicsException {		
		try {
			boolean level2Approval = false;
			boolean multiLevelApproval = false;
			String mailId = "";
			String mailMessage = "";  
			Product bomObject = null;
			boolean sendMail = false;
			boolean isReApproval = false;
			
			ApplParameter parameter = AdminFactory.getInstance()
				.getApplParameterByName(Constants.NOTIFY_SPEC_APPROVAL_REQUEST);
			if (parameter != null && parameter.getParameterValue() != null &&
					parameter.getParameterValue().equals("Y")) {
				sendMail = true;
			}
			parameter = AdminFactory.getInstance()
				.getApplParameterByName(Constants.SPECIFICATION_APPROVAL_LEVEL);
			if (parameter != null && parameter.getParameterValue() != null &&
					parameter.getParameterValue().equals("D")) {
				multiLevelApproval = true;
			}

			super.beginTransaction();
			if (voObject.getProductId() != null && voObject.getProductId().longValue() != 0) {
				bomObject = productFactory.getProduct(voObject.getProductId());
				if (bomObject.getVersion() > voObject.getVersion()) {
					throw new EAPharmicsException("This record has been updated by another user. Please refresh data!");
				}
				if (sendMail) {
					//notification to the user based on application setting
					if (submitForApproval) {
						//if user has submitted a product for approval, we may need to send
						//notification to the user based on application setting
						mailMessage = " is waiting for approval";
					} else if (level1Approval) {
						mailMessage = " is waiting for final approval";
					} else if (unapprove) {
						//unapproval
						mailMessage = " has been returned for editing";
						//Need to send the last edited user (not the level 1 approved user)
						//an email message
						AppUser mailUser = SecurityFactory.getInstance().getUser(bomObject.getUpdateUser());
						if (mailUser != null && mailUser.getEmail() != null) {
							mailId = mailUser.getEmail();
						}
					}
				}
				if (bomObject.getApprovedOnce()) {
					if (multiLevelApproval) {
						if (level2Approval2) {
							isReApproval = true;
						}
					} else if (level1Approval || level2Approval || level2Approval2) {
						isReApproval = true;
					}
				}
			} else {
				//ensure there is no duplicate product code already in the database
				if (productFactory.productExistsForProductCode(voObject.getProductCode())) {
					throw new EAPharmicsException("Product Code is in use by another product!");
				}
				bomObject = new Product();
				productFactory.createProduct(bomObject);
				bomObject.setActive(Boolean.TRUE);
			}
			if (editReasons != null && editReasons.size() > 0) {
				SecurityImpl sImpl = new SecurityImpl();
				for (Iterator iterator = editReasons.iterator(); iterator
						.hasNext();) {
					EditReasonVO reasonVO = (EditReasonVO) iterator.next();
					sImpl.createEditReason(reasonVO, userVO);
					
				}
			}
			populateProductBOM(bomObject, voObject, userVO);
			if (level1Approval || level2Approval2) {
				//Record that the product has been approved once.
				//further unapprovals will not change this.
				//once approved, product edit needs a reason to be entered 
				bomObject.setApprovedOnce(true);
			}
			if (unapprove) {
				bomObject.setApprovalLevel1(Boolean.FALSE);
				bomObject.setApprovalLevel1By(null);
				bomObject.setApprovalLevel1Date(null);
				bomObject.setApprovalLevel2(Boolean.FALSE);
				bomObject.setApprovalLevel2By(null);
				bomObject.setApprovalLevel2Date(null);
			}
			super.commitTransaction();
			
			if (isReApproval) {
				//if throws an error, it will create a rollback, we need to avoid id.
				super.beginTransaction();
				productFactory.updateApproval(bomObject.getProductId(), userVO
						.getAppUserId(), userVO.getAuditId());
				super.commitTransaction();
			}
			
			if (sendMail && mailMessage.length() > 0) {
				String moduleName = "Level1 Approval";
				if (level2Approval2) {
					moduleName = "Final Approval";
				}
				List<String> list = new ArrayList<String>();
				if (mailId.length() > 0) { 
					list.add(mailId);
				} else {
					list = SecurityFactory.getInstance().getEmailIdsForFunctionAccess(moduleName, bomObject.getLocationId());
				}
				String subject = "Product " + bomObject.getProductDescription() + 
					" (" + bomObject.getProductCode() + ") " + mailMessage;
				String message = "Product " + bomObject.getProductDescription() + 
				" (" + bomObject.getProductCode() + ") " + mailMessage;
				if (voObject.getMailMessage() != null && voObject.getMailMessage().length() > 0) {
					message = message + "\n" + voObject.getMailMessage();
				}
				MailHelper.sendMail(list, subject, message);
			}
			
		} catch (Exception e) {
			logException("saveProduct", e);
			try {
				super.rollbackTransaction();
			} catch (Throwable t) {
				logException("saveProduct", e);
			}
			throw new EAPharmicsException(e);
		}
	}

	/**
	 * @param product
	 */
	public void deleteProduct(Long productId, UserVO userVO) throws EAPharmicsException {
		try {
			if (!productFactory.canDeleteProduct(productId)) {
				throw new EAPharmicsException("Cannot delete product. Studies exist!");
			}
			super.beginTransaction();
			Product product = productFactory.getProduct(productId);
			product.setActive(Boolean.FALSE);
			product.setUpdateDate(Utility.getCurrentUserLocationDateTime());
			product.setUpdateUser(userVO.getAppUserId());
			product.setAuditId(userVO.getAuditId());
			productFactory.updateProduct(product);
			super.commitTransaction();
		} catch (Exception e) {
			logException("deleteProduct", e);
			try {
				super.rollbackTransaction();
			} catch (Throwable t) {
				logException("updateProduct", e);
			}
			throw new EAPharmicsException(e);
		}
	}

	/**
	 * @return
	 */
	public List getAllInventories(Long protocolId) throws EAPharmicsException {
		List<Inventory> list1 = productFactory.getAllInventories(protocolId);
		List<InventoryVO> list = new ArrayList<InventoryVO>();
		for (Iterator<Inventory> iterator = list1.iterator(); iterator
				.hasNext();) {
			Inventory bomObject = (Inventory) iterator.next();
			InventoryVO voObject = new InventoryVO();
			list.add(populateInventoryVO(bomObject, voObject));
		}
		return list;
	}

	/**
	 * @param keyName
	 * @return
	 * @throws EAPharmicsException
	 */
	public InventoryVO getInventory(Long keyName) throws EAPharmicsException {
		Inventory bomObject = productFactory.getInventory(keyName);
		InventoryVO voObject = new InventoryVO();
		voObject = populateInventoryVO(bomObject, voObject);
		return voObject;
	}

	/**
	 * @return
	 */
	public List getAllProductSpecs(Long productId) throws EAPharmicsException {
		List<ProductSpec> list1 = productFactory.getAllProductSpecs(productId);
		List<ProductSpecVO> list = new ArrayList<ProductSpecVO>();
		for (Iterator<ProductSpec> iterator = list1.iterator(); iterator
				.hasNext();) {
			ProductSpec bomObject = (ProductSpec) iterator.next();
			ProductSpecVO voObject = new ProductSpecVO();
			list.add(populateProductSpecVO(bomObject, voObject));
		}
		return list;
	}

	/**
	 * @param keyName
	 * @return
	 * @throws EAPharmicsException
	 */
	public ProductSpecVO getProductSpec(Long keyName)
			throws EAPharmicsException {
		ProductSpec bomObject = productFactory.getProductSpec(keyName);
		ProductSpecVO voObject = new ProductSpecVO();
		voObject = populateProductSpecVO(bomObject, voObject);
		return voObject;
	}

	/**
	 * @param voObject
	 * @throws EAPharmicsException
	 */
	public void saveProductSpecs(List list, UserVO userVO)
			throws EAPharmicsException {
		try {
			super.beginTransaction();
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				ProductSpecVO voObject = (ProductSpecVO) iterator.next();
				ProductSpec bomObject = null;
				if (voObject.getProductSpecId() == null
						|| voObject.getProductSpecId().longValue() == 0) {
					bomObject = new ProductSpec();
					bomObject.setInsertDate(Utility.getCurrentUserLocationDateTime());
					bomObject.setInsertUser(userVO.getAppUserId());
				} else {
					bomObject = productFactory.getProductSpec(voObject
							.getProductSpecId());
					if (bomObject.getTextLine().equals(voObject.getTextLine())) {
						continue;
					}
				}
				if (bomObject == null) {
					throw new EAPharmicsException(
							"Cannot find ProductSpec. Unable to update!");
				}
				populateProductSpecBOM(bomObject, voObject);
				bomObject.setUpdateDate(Utility.getCurrentUserLocationDateTime());
				bomObject.setUpdateUser(userVO.getAppUserId());
				bomObject.setAuditId(userVO.getAuditId());
				productFactory.updateProductSpec(bomObject);
			}
			super.commitTransaction();
		} catch (Exception e) {
			logException("saveProductSpecs", e);
			try {
				super.rollbackTransaction();
			} catch (Throwable t) {
				logException("saveProductSpecs", e);
			}
			throw new EAPharmicsException(e);
		}
	}

	/**
	 * @param productSpec
	 */
	public void deleteProductSpec(Long productSpecId)
			throws EAPharmicsException {
		try {
			super.beginTransaction();
			productFactory.deleteProductSpec(productSpecId);
			super.commitTransaction();
		} catch (Exception e) {
			logException("deleteProductSpec", e);
			try {
				super.rollbackTransaction();
			} catch (Throwable t) {
				logException("updateProductSpec", e);
			}
			throw new EAPharmicsException(e);
		}
	}

	/**
	 * Copy the contents of BOM object to the VO object
	 * 
	 * @param bomObject
	 * @param voObject
	 */
	private ProductVO populateProductVO(Product bomObject, ProductVO voObject) {
		voObject.setProductId(bomObject.getProductId());
		voObject.setProductCode(bomObject.getProductCode());
		voObject.setProductName(bomObject.getProductName());
		voObject.setProductDescription(bomObject.getProductDescription());
		voObject.setExpirationPeriod(bomObject.getExpirationPeriod());
		voObject.setInsertDate(bomObject.getInsertDate());
		voObject.setInsertUser(bomObject.getInsertUser());
		voObject.setUpdateDate(bomObject.getUpdateDate());
		voObject.setUpdateUser(bomObject.getUpdateUser());
		voObject.setAuditId(bomObject.getAuditId());
		voObject.setStartDate(bomObject.getStartDate());
		voObject.setEndDate(bomObject.getEndDate());
		voObject.setComments(bomObject.getComments());
		voObject.setApprovalLevel1(bomObject.getApprovalLevel1());
		voObject.setApprovalLevel1By(bomObject.getApprovalLevel1By());
		voObject.setApprovalLevel1Date(bomObject.getApprovalLevel1Date());
		voObject.setApprovalLevel2(bomObject.getApprovalLevel2());
		voObject.setApprovalLevel2By(bomObject.getApprovalLevel2By());
		voObject.setApprovalLevel2Date(bomObject.getApprovalLevel2Date());
		voObject.setVersion(bomObject.getVersion());
		voObject.setProductType(bomObject.getProductType());
		voObject.setReadyForApproval(bomObject.getReadyForApproval());
		voObject.setLocationId(bomObject.getLocationId());
		voObject.setApprovedOnce(bomObject.getApprovedOnce());

		Object[] batches = (Object[]) bomObject.getBatches().toArray();
		List list = new ArrayList();
		for (int i = 0; i < batches.length; i++) {
			ProductBatch productBatch = (ProductBatch) batches[i];
			ProductBatchVO batchVO = new ProductBatchVO();
			populateProductBatchVO(productBatch, batchVO);
			list.add(batchVO);
		}
		voObject.setBatches(list);

		Object[] formulations = (Object[]) bomObject.getFormulations()
				.toArray();
		list = new ArrayList();
		for (int i = 0; i < formulations.length; i++) {
			ProductFormulation productFormulation = (ProductFormulation) formulations[i];
			ProductFormulationVO formulationVO = new ProductFormulationVO();
			populateProductFormulationVO(productFormulation, formulationVO);
			list.add(formulationVO);
		}
		voObject.setFormulations(list);

		Object[] productTests = (Object[]) bomObject.getProductTests()
				.toArray();
		list = new ArrayList();
		for (int i = 0; i < productTests.length; i++) {
			ProductTest ProductTest = (ProductTest) productTests[i];
			ProductTestVO ProductTestVO = new ProductTestVO();
			populateProductTestVO(ProductTest, ProductTestVO);
			list.add(ProductTestVO);
		}
		voObject.setProductTests(list);

		Object[] protcols = (Object[]) bomObject.getProtocols().toArray();
		list = new ArrayList();
		for (int i = 0; i < protcols.length; i++) {
			Protocol Protocol = (Protocol) protcols[i];
			ProtocolVO ProtocolVO = new ProtocolVO();
			populateProtocolVO(Protocol, ProtocolVO, voObject.getProductTests());
			list.add(ProtocolVO);
		}
		voObject.setProtocols(list);

		return voObject;

	}

	/**
	 * Copy the contents of BOM object to the VO object
	 * 
	 * @param bomObject
	 * @param voObject
	 */
	private ProductBatchVO populateProductBatchVO(ProductBatch bomObject,
			ProductBatchVO voObject) {
		voObject.setProductBatchId(bomObject.getProductBatchId());
		voObject.setProductId(bomObject.getProduct().getProductId());
		voObject.setBatchLineNumber(bomObject.getBatchLineNumber());
		voObject.setBatchSize(bomObject.getBatchSize());
		voObject.setInsertDate(bomObject.getInsertDate());
		voObject.setInsertUser(bomObject.getInsertUser());
		voObject.setUpdateDate(bomObject.getUpdateDate());
		voObject.setUpdateUser(bomObject.getUpdateUser());
		voObject.setAuditId(bomObject.getAuditId());
		voObject.setComments(bomObject.getComments());
		return voObject;

	}

	/**
	 * Copy the contents of BOM object to the VO object
	 * 
	 * @param bomObject
	 * @param voObject
	 */
	private ProductFormulationVO populateProductFormulationVO(
			ProductFormulation bomObject, ProductFormulationVO voObject) {
		voObject.setProductFormulationId(bomObject.getProductFormulationId());
		voObject.setProductId(bomObject.getProduct().getProductId());
		voObject.setLineNumber(bomObject.getLineNumber());
		voObject.setProductFormulation(bomObject.getProductFormulation());
		voObject.setInsertDate(bomObject.getInsertDate());
		voObject.setInsertUser(bomObject.getInsertUser());
		voObject.setUpdateDate(bomObject.getUpdateDate());
		voObject.setUpdateUser(bomObject.getUpdateUser());
		voObject.setAuditId(bomObject.getAuditId());
		voObject.setComments(bomObject.getComments());
		return voObject;

	}

	/**
	 * Copy the contents of BOM object to the VO object
	 * 
	 * @param bomObject
	 * @param voObject
	 */
	private TestVO populateTestVO(Test bomObject, TestVO voObject) {
		voObject.setTestId(bomObject.getTestId());
		voObject.setTestName(bomObject.getTestName());
		voObject.setLowerLimit(bomObject.getLowerLimit());
		voObject.setUpperLimit(bomObject.getUpperLimit());
		voObject.setTextLimit(bomObject.getTextLimit());
		voObject.setNumOfEntries(bomObject.getNumOfEntries());
		voObject.setMethod(bomObject.getMethod());
		voObject.setDecimalPoints(bomObject.getDecimalPoints());
		voObject.setResultsFormat(bomObject.getResultsFormat());
		voObject.setMeasurement(bomObject.getMeasurement());
		voObject.setGraph(bomObject.getGraph());
		voObject.setLabNumber(bomObject.getLabNumber());
		voObject.setTextLine(bomObject.getTextLine());
		voObject.setAlertLowerLimit(bomObject.getAlertLowerLimit());
		voObject.setAlertUpperLimit(bomObject.getAlertUpperLimit());
		voObject.setAlertLowerVariance(bomObject.getAlertLowerVariance());
		voObject.setAlertUpperVariance(bomObject.getAlertUpperVariance());
		voObject.setInsertDate(bomObject.getInsertDate());
		voObject.setInsertUser(bomObject.getInsertUser());
		voObject.setUpdateDate(bomObject.getUpdateDate());
		voObject.setUpdateUser(bomObject.getUpdateUser());
		voObject.setAuditId(bomObject.getAuditId());
		voObject.setComments(bomObject.getComments());
		voObject.setDisplayName(voObject.computeDisplayName());
		voObject.setLocationId(bomObject.getLocationId());
		return voObject;

	}

	/**
	 * Copy the contents of BOM object to the VO object
	 * 
	 * @param bomObject
	 * @param voObject
	 */
	private ProductTestVO populateProductTestVO(ProductTest bomObject,
			ProductTestVO voObject) {
		voObject.setProductTestId(bomObject.getProductTestId());
		voObject.setProductId(bomObject.getProductId());
		voObject.setSortOrderPrevious(bomObject.getSortOrderPrevious());
		voObject.setSortOrderCurrent(bomObject.getSortOrderCurrent());
		voObject.setInsertDate(bomObject.getInsertDate());
		voObject.setInsertUser(bomObject.getInsertUser());
		voObject.setUpdateDate(bomObject.getUpdateDate());
		voObject.setUpdateUser(bomObject.getUpdateUser());
		voObject.setAuditId(bomObject.getAuditId());
		voObject.setComments(bomObject.getComments());
		voObject.setTestName(bomObject.getTestName());
		voObject.setLowerLimit(bomObject.getLowerLimit());
		voObject.setUpperLimit(bomObject.getUpperLimit());
		voObject.setTextLimit(bomObject.getTextLimit());
		voObject.setNumOfEntries(bomObject.getNumOfEntries());
		voObject.setMethod(bomObject.getMethod());
		voObject.setDecimalPoints(bomObject.getDecimalPoints());
		voObject.setResultsFormat(bomObject.getResultsFormat());
		voObject.setMeasurement(bomObject.getMeasurement());
		voObject.setGraph(bomObject.getGraph());
		voObject.setLabNumber(bomObject.getLabNumber());
		voObject.setTextLine(bomObject.getTextLine());
		voObject.setAlertLowerLimit(bomObject.getAlertLowerLimit());
		voObject.setAlertUpperLimit(bomObject.getAlertUpperLimit());
		voObject.setAlertLowerVariance(bomObject.getAlertLowerVariance());
		voObject.setAlertUpperVariance(bomObject.getAlertUpperVariance());
		voObject.setDisplayName(voObject.computeDisplayName());
		return voObject;

	}

	/**
	 * Copy the contents of BOM object to the VO object
	 * 
	 * @param bomObject
	 * @param voObject
	 */
	private ProtocolVO populateProtocolVO(Protocol bomObject, ProtocolVO voObject, List productTests) {
		voObject.setProtocolId(bomObject.getProtocolId());
		voObject.setProductId(bomObject.getProductId());
		voObject.setProtocolNumber(bomObject.getProtocolNumber());
		voObject.setProtocolName(bomObject.getProtocolName());
		voObject.setIntervalUnits(bomObject.getIntervalUnits());
		voObject.setTestIntervalValue1(bomObject.getTestIntervalValue1());
		voObject.setTestIntervalValue2(bomObject.getTestIntervalValue2());
		voObject.setTestIntervalValue3(bomObject.getTestIntervalValue3());
		voObject.setTestIntervalValue4(bomObject.getTestIntervalValue4());
		voObject.setTestIntervalValue5(bomObject.getTestIntervalValue5());
		voObject.setTestIntervalValue6(bomObject.getTestIntervalValue6());
		voObject.setTestIntervalValue7(bomObject.getTestIntervalValue7());
		voObject.setTestIntervalValue8(bomObject.getTestIntervalValue8());
		voObject.setTestIntervalValue9(bomObject.getTestIntervalValue9());
		voObject.setTestIntervalValue10(bomObject.getTestIntervalValue10());
		voObject.setTestIntervalValue11(bomObject.getTestIntervalValue11());
		voObject.setTestIntervalValue12(bomObject.getTestIntervalValue12());
		voObject.setTestIntervalValue13(bomObject.getTestIntervalValue13());
		voObject.setTestIntervalValue14(bomObject.getTestIntervalValue14());
		voObject.setTestIntervalValue15(bomObject.getTestIntervalValue15());
		voObject.setTestIntervalValue16(bomObject.getTestIntervalValue16());
		voObject.setTestIntervalValue17(bomObject.getTestIntervalValue17());
		voObject.setTestIntervalValue18(bomObject.getTestIntervalValue18());
		voObject.setTestIntervalValue19(bomObject.getTestIntervalValue19());
		voObject.setTestIntervalValue20(bomObject.getTestIntervalValue20());
		if (bomObject.getTestIntervalValue1() != null && 
			bomObject.getTestIntervalValue1().longValue() != 0) {
			voObject.setTestIntervalValue1Str(bomObject.getTestIntervalValue1().toString());
		}
		if (bomObject.getTestIntervalValue2() != null && 
			bomObject.getTestIntervalValue2().longValue() != 0) {
			voObject.setTestIntervalValue2Str(bomObject.getTestIntervalValue2().toString());
		}
		if (bomObject.getTestIntervalValue3() != null && 
			bomObject.getTestIntervalValue3().longValue() != 0) {
			voObject.setTestIntervalValue3Str(bomObject.getTestIntervalValue3().toString());
		}
		if (bomObject.getTestIntervalValue4() != null && 
			bomObject.getTestIntervalValue4().longValue() != 0) {
			voObject.setTestIntervalValue4Str(bomObject.getTestIntervalValue4().toString());
		}
		if (bomObject.getTestIntervalValue5() != null && 
			bomObject.getTestIntervalValue5().longValue() != 0) {
			voObject.setTestIntervalValue5Str(bomObject.getTestIntervalValue5().toString());
		}
		if (bomObject.getTestIntervalValue6() != null && 
			bomObject.getTestIntervalValue6().longValue() != 0) {
			voObject.setTestIntervalValue6Str(bomObject.getTestIntervalValue6().toString());
		}
		if (bomObject.getTestIntervalValue7() != null && 
			bomObject.getTestIntervalValue7().longValue() != 0) {
			voObject.setTestIntervalValue7Str(bomObject.getTestIntervalValue7().toString());
		}
		if (bomObject.getTestIntervalValue8() != null && 
			bomObject.getTestIntervalValue8().longValue() != 0) {
			voObject.setTestIntervalValue8Str(bomObject.getTestIntervalValue8().toString());
		}
		if (bomObject.getTestIntervalValue9() != null && 
			bomObject.getTestIntervalValue9().longValue() != 0) {
			voObject.setTestIntervalValue9Str(bomObject.getTestIntervalValue9().toString());
		}
		if (bomObject.getTestIntervalValue10() != null && 
			bomObject.getTestIntervalValue10().longValue() != 0) {
			voObject.setTestIntervalValue10Str(bomObject.getTestIntervalValue10().toString());
		}
		if (bomObject.getTestIntervalValue11() != null && 
				bomObject.getTestIntervalValue11().longValue() != 0) {
				voObject.setTestIntervalValue11Str(bomObject.getTestIntervalValue11().toString());
		}
		if (bomObject.getTestIntervalValue12() != null && 
			bomObject.getTestIntervalValue12().longValue() != 0) {
			voObject.setTestIntervalValue12Str(bomObject.getTestIntervalValue12().toString());
		}
		if (bomObject.getTestIntervalValue13() != null && 
			bomObject.getTestIntervalValue13().longValue() != 0) {
			voObject.setTestIntervalValue13Str(bomObject.getTestIntervalValue13().toString());
		}
		if (bomObject.getTestIntervalValue14() != null && 
			bomObject.getTestIntervalValue14().longValue() != 0) {
			voObject.setTestIntervalValue14Str(bomObject.getTestIntervalValue14().toString());
		}
		if (bomObject.getTestIntervalValue15() != null && 
			bomObject.getTestIntervalValue15().longValue() != 0) {
			voObject.setTestIntervalValue15Str(bomObject.getTestIntervalValue15().toString());
		}
		if (bomObject.getTestIntervalValue16() != null && 
			bomObject.getTestIntervalValue16().longValue() != 0) {
			voObject.setTestIntervalValue16Str(bomObject.getTestIntervalValue16().toString());
		}
		if (bomObject.getTestIntervalValue17() != null && 
			bomObject.getTestIntervalValue17().longValue() != 0) {
			voObject.setTestIntervalValue17Str(bomObject.getTestIntervalValue17().toString());
		}
		if (bomObject.getTestIntervalValue18() != null && 
			bomObject.getTestIntervalValue18().longValue() != 0) {
			voObject.setTestIntervalValue18Str(bomObject.getTestIntervalValue18().toString());
		}
		if (bomObject.getTestIntervalValue19() != null && 
			bomObject.getTestIntervalValue19().longValue() != 0) {
			voObject.setTestIntervalValue19Str(bomObject.getTestIntervalValue19().toString());
		}
		if (bomObject.getTestIntervalValue20() != null && 
			bomObject.getTestIntervalValue20().longValue() != 0) {
			voObject.setTestIntervalValue20Str(bomObject.getTestIntervalValue20().toString());
		}
		voObject.setInsertDate(bomObject.getInsertDate());
		voObject.setInsertUser(bomObject.getInsertUser());
		voObject.setUpdateDate(bomObject.getUpdateDate());
		voObject.setUpdateUser(bomObject.getUpdateUser());
		voObject.setAuditId(bomObject.getAuditId());
		voObject.setComments(bomObject.getComments());

		Hashtable<Long, ProductTestVO> tests = new Hashtable<Long, ProductTestVO>();
		for (Iterator iterator = productTests.iterator(); iterator.hasNext();) {
			ProductTestVO object = (ProductTestVO) iterator.next();
			tests.put(object.getProductTestId(), object);
		}
		
		// Get the Protocol details
		List details = productFactory.getAllProtocolDetails(bomObject
				.getProtocolId());
		List detailsList = new ArrayList();
		for (Iterator iterator = details.iterator(); iterator.hasNext();) {
			ProtocolDetail protocolDetail = (ProtocolDetail) iterator.next();
			ProtocolDetailVO detailVO = new ProtocolDetailVO();
			populateProtocolDetailVO(protocolDetail, detailVO);
			String testName = ((ProductTestVO) tests.get(protocolDetail.getTestId())).getTestName();
			String displayTestName = ((ProductTestVO) tests.get(protocolDetail.getTestId())).computeDisplayName();
			detailVO.setTestName(testName);
			detailVO.setDisplayTestName(displayTestName);
			detailsList.add(detailVO);
		}
		voObject.setDetails(detailsList);

		List<Inventory> list1 = productFactory.getAllInventories(bomObject.getProtocolId());
		List<InventoryVO> inventories = new ArrayList<InventoryVO>();
		for (Iterator<Inventory> iterator = list1.iterator(); iterator
				.hasNext();) {
			Inventory inventory = (Inventory) iterator.next();
			InventoryVO inventoryVO = new InventoryVO();
			inventories.add(populateInventoryVO(inventory, inventoryVO));
		}
		
		voObject.setInventories(inventories);
		
		return voObject;

	}

	/**
	 * Copy the contents of BOM object to the VO object
	 * 
	 * @param bomObject
	 * @param voObject
	 */
	private ProtocolDetailVO populateProtocolDetailVO(ProtocolDetail bomObject,
			ProtocolDetailVO voObject) {
		voObject.setProtocolDetailId(bomObject.getProtocolDetailId());
		voObject.setProtocolIntervalId(bomObject.getProtocolIntervalId());
		voObject.setTestId(bomObject.getTestId());
		voObject.setTestIntervalUsed1(bomObject.getTestIntervalUsed1());
		voObject.setTestIntervalUsed2(bomObject.getTestIntervalUsed2());
		voObject.setTestIntervalUsed3(bomObject.getTestIntervalUsed3());
		voObject.setTestIntervalUsed4(bomObject.getTestIntervalUsed4());
		voObject.setTestIntervalUsed5(bomObject.getTestIntervalUsed5());
		voObject.setTestIntervalUsed6(bomObject.getTestIntervalUsed6());
		voObject.setTestIntervalUsed7(bomObject.getTestIntervalUsed7());
		voObject.setTestIntervalUsed8(bomObject.getTestIntervalUsed8());
		voObject.setTestIntervalUsed9(bomObject.getTestIntervalUsed9());
		voObject.setTestIntervalUsed10(bomObject.getTestIntervalUsed10());
		voObject.setTestIntervalUsed11(bomObject.getTestIntervalUsed11());
		voObject.setTestIntervalUsed12(bomObject.getTestIntervalUsed12());
		voObject.setTestIntervalUsed13(bomObject.getTestIntervalUsed13());
		voObject.setTestIntervalUsed14(bomObject.getTestIntervalUsed14());
		voObject.setTestIntervalUsed15(bomObject.getTestIntervalUsed15());
		voObject.setTestIntervalUsed16(bomObject.getTestIntervalUsed16());
		voObject.setTestIntervalUsed17(bomObject.getTestIntervalUsed17());
		voObject.setTestIntervalUsed18(bomObject.getTestIntervalUsed18());
		voObject.setTestIntervalUsed19(bomObject.getTestIntervalUsed19());
		voObject.setTestIntervalUsed20(bomObject.getTestIntervalUsed20());
		voObject.setInsertDate(bomObject.getInsertDate());
		voObject.setInsertUser(bomObject.getInsertUser());
		voObject.setUpdateDate(bomObject.getUpdateDate());
		voObject.setUpdateUser(bomObject.getUpdateUser());
		voObject.setAuditId(bomObject.getAuditId());
		voObject.setComments(bomObject.getComments());
		voObject.setProtocolId(bomObject.getProtocolId());
		return voObject;

	}

	/**
	 * Copy the contents of BOM object to the VO object
	 * 
	 * @param bomObject
	 * @param voObject
	 */
	private InventoryVO populateInventoryVO(Inventory bomObject,
			InventoryVO voObject) {
		voObject.setInventoryId(bomObject.getInventoryId());
		voObject.setProductId(bomObject.getProductId());
		voObject.setProtocolId(bomObject.getProtocolId());
		voObject.setFillUnits(bomObject.getFillUnits());
		voObject.setUnitOfMeasure(bomObject.getUnitOfMeasure());
		voObject.setTotal(bomObject.getTotal());
		voObject.setExtra(bomObject.getExtra());
		voObject.setTestIntervalInventory1(bomObject.getTestIntervalInventory1());
		voObject.setTestIntervalInventory2(bomObject.getTestIntervalInventory2());
		voObject.setTestIntervalInventory3(bomObject.getTestIntervalInventory3());
		voObject.setTestIntervalInventory4(bomObject.getTestIntervalInventory4());
		voObject.setTestIntervalInventory5(bomObject.getTestIntervalInventory5());
		voObject.setTestIntervalInventory6(bomObject.getTestIntervalInventory6());
		voObject.setTestIntervalInventory7(bomObject.getTestIntervalInventory7());
		voObject.setTestIntervalInventory8(bomObject.getTestIntervalInventory8());
		voObject.setTestIntervalInventory9(bomObject.getTestIntervalInventory9());
		voObject.setTestIntervalInventory10(bomObject.getTestIntervalInventory10());
		voObject.setTestIntervalInventory11(bomObject.getTestIntervalInventory11());
		voObject.setTestIntervalInventory12(bomObject.getTestIntervalInventory12());
		voObject.setTestIntervalInventory13(bomObject.getTestIntervalInventory13());
		voObject.setTestIntervalInventory14(bomObject.getTestIntervalInventory14());
		voObject.setTestIntervalInventory15(bomObject.getTestIntervalInventory15());
		voObject.setTestIntervalInventory16(bomObject.getTestIntervalInventory16());
		voObject.setTestIntervalInventory17(bomObject.getTestIntervalInventory17());
		voObject.setTestIntervalInventory18(bomObject.getTestIntervalInventory18());
		voObject.setTestIntervalInventory19(bomObject.getTestIntervalInventory19());
		voObject.setTestIntervalInventory20(bomObject.getTestIntervalInventory20());
		if (bomObject.getTestIntervalInventory1() != null && 
			bomObject.getTestIntervalInventory1().longValue() != 0) {
			voObject.setTestIntervalInventory1Str(bomObject.getTestIntervalInventory1().toString());
		}
		if (bomObject.getTestIntervalInventory2() != null && 
			bomObject.getTestIntervalInventory2().longValue() != 0) {
			voObject.setTestIntervalInventory2Str(bomObject.getTestIntervalInventory2().toString());
		}
		if (bomObject.getTestIntervalInventory3() != null && 
			bomObject.getTestIntervalInventory3().longValue() != 0) {
			voObject.setTestIntervalInventory3Str(bomObject.getTestIntervalInventory3().toString());
		}
		if (bomObject.getTestIntervalInventory4() != null && 
			bomObject.getTestIntervalInventory4().longValue() != 0) {
			voObject.setTestIntervalInventory4Str(bomObject.getTestIntervalInventory4().toString());
		}
		if (bomObject.getTestIntervalInventory5() != null && 
			bomObject.getTestIntervalInventory5().longValue() != 0) {
			voObject.setTestIntervalInventory5Str(bomObject.getTestIntervalInventory5().toString());
		}
		if (bomObject.getTestIntervalInventory6() != null && 
			bomObject.getTestIntervalInventory6().longValue() != 0) {
			voObject.setTestIntervalInventory6Str(bomObject.getTestIntervalInventory6().toString());
		}
		if (bomObject.getTestIntervalInventory7() != null && 
			bomObject.getTestIntervalInventory7().longValue() != 0) {
			voObject.setTestIntervalInventory7Str(bomObject.getTestIntervalInventory7().toString());
		}
		if (bomObject.getTestIntervalInventory8() != null && 
			bomObject.getTestIntervalInventory8().longValue() != 0) {
			voObject.setTestIntervalInventory8Str(bomObject.getTestIntervalInventory8().toString());
		}
		if (bomObject.getTestIntervalInventory9() != null && 
			bomObject.getTestIntervalInventory9().longValue() != 0) {
			voObject.setTestIntervalInventory9Str(bomObject.getTestIntervalInventory9().toString());
		}
		if (bomObject.getTestIntervalInventory10() != null && 
			bomObject.getTestIntervalInventory10().longValue() != 0) {
			voObject.setTestIntervalInventory10Str(bomObject.getTestIntervalInventory10().toString());
		}
		if (bomObject.getTestIntervalInventory11() != null && 
				bomObject.getTestIntervalInventory11().longValue() != 0) {
				voObject.setTestIntervalInventory11Str(bomObject.getTestIntervalInventory11().toString());
		}
		if (bomObject.getTestIntervalInventory12() != null && 
			bomObject.getTestIntervalInventory12().longValue() != 0) {
			voObject.setTestIntervalInventory12Str(bomObject.getTestIntervalInventory12().toString());
		}
		if (bomObject.getTestIntervalInventory13() != null && 
			bomObject.getTestIntervalInventory13().longValue() != 0) {
			voObject.setTestIntervalInventory13Str(bomObject.getTestIntervalInventory13().toString());
		}
		if (bomObject.getTestIntervalInventory14() != null && 
			bomObject.getTestIntervalInventory14().longValue() != 0) {
			voObject.setTestIntervalInventory14Str(bomObject.getTestIntervalInventory14().toString());
		}
		if (bomObject.getTestIntervalInventory15() != null && 
			bomObject.getTestIntervalInventory15().longValue() != 0) {
			voObject.setTestIntervalInventory15Str(bomObject.getTestIntervalInventory15().toString());
		}
		if (bomObject.getTestIntervalInventory16() != null && 
			bomObject.getTestIntervalInventory16().longValue() != 0) {
			voObject.setTestIntervalInventory16Str(bomObject.getTestIntervalInventory16().toString());
		}
		if (bomObject.getTestIntervalInventory17() != null && 
			bomObject.getTestIntervalInventory17().longValue() != 0) {
			voObject.setTestIntervalInventory17Str(bomObject.getTestIntervalInventory17().toString());
		}
		if (bomObject.getTestIntervalInventory18() != null && 
			bomObject.getTestIntervalInventory18().longValue() != 0) {
			voObject.setTestIntervalInventory18Str(bomObject.getTestIntervalInventory18().toString());
		}
		if (bomObject.getTestIntervalInventory19() != null && 
			bomObject.getTestIntervalInventory19().longValue() != 0) {
			voObject.setTestIntervalInventory19Str(bomObject.getTestIntervalInventory19().toString());
		}
		if (bomObject.getTestIntervalInventory20() != null && 
			bomObject.getTestIntervalInventory20().longValue() != 0) {
			voObject.setTestIntervalInventory20Str(bomObject.getTestIntervalInventory20().toString());
		}
		voObject.setInsertDate(bomObject.getInsertDate());
		voObject.setInsertUser(bomObject.getInsertUser());
		voObject.setUpdateDate(bomObject.getUpdateDate());
		voObject.setUpdateUser(bomObject.getUpdateUser());
		voObject.setAuditId(bomObject.getAuditId());
		voObject.setComments(bomObject.getComments());

		return voObject;

	}

	/**
	 * Copy the contents of BOM object to the VO object
	 * 
	 * @param bomObject
	 * @param voObject
	 */
	private ProductSpecVO populateProductSpecVO(ProductSpec bomObject,
			ProductSpecVO voObject) {
		voObject.setProductSpecId(bomObject.getProductSpecId());
		voObject.setProductId(bomObject.getProductId());
		voObject.setLineNumber(bomObject.getLineNumber());
		voObject.setTextLine(bomObject.getTextLine());
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
	 * 
	 * @param bomObject
	 * @param voObject
	 */
	private Product populateProductBOM(Product bomObject, ProductVO voObject,
			UserVO userVO) throws EAPharmicsException {
		bomObject.setProductCode(voObject.getProductCode());
		bomObject.setProductName(voObject.getProductName());
		bomObject.setProductDescription(voObject.getProductDescription());
		bomObject.setExpirationPeriod(voObject.getExpirationPeriod());
		bomObject.setAuditId(voObject.getAuditId());
		bomObject.setStartDate(voObject.getStartDate());
		bomObject.setEndDate(voObject.getEndDate());
		bomObject.setComments(voObject.getComments());
		bomObject.setApprovalLevel1(voObject.getApprovalLevel1());
		Date date = Utility.getCurrentUserLocationDateTime();
		if (voObject.getIsLevel1Approval() != null && voObject.getIsLevel1Approval()) {
			bomObject.setApprovalLevel1Date(date);
			bomObject.setApprovalLevel1By(userVO.getAppUserId());
		}
		bomObject.setApprovalLevel2(voObject.getApprovalLevel2());
		if (voObject.getIsLeve12Approval() != null && voObject.getIsLeve12Approval()) {
			bomObject.setApprovalLevel2Date(Utility.getCurrentUserLocationDateTime(voObject.getApprovalLevel2Date()));
			bomObject.setApprovalLevel2By(userVO.getAppUserId());
		}
		if (voObject.getProductId() == null
				|| voObject.getProductId().longValue() == 0) {
			bomObject.setInsertDate(Utility.getCurrentUserLocationDateTime());
			bomObject.setInsertUser(userVO.getAppUserId());
			// update Audit Id
			bomObject.setAuditId(userVO.getAuditId());
		} 
		if ((voObject.getIsLevel1Approval() != null && voObject.getIsLevel1Approval()) ||
				(voObject.getIsLeve12Approval() != null && voObject.getIsLeve12Approval())) {
			//if user is approving, do not update the last update user
			//we need to know who last updated the product before it was
			//sent for approval. We need this person's email address
			//to send it back if and when it is returned
		} else {
			bomObject.setUpdateDate(Utility.getCurrentUserLocationDateTime());
			bomObject.setUpdateUser(userVO.getAppUserId());
		}
		// update Audit Id
		bomObject.setAuditId(userVO.getAuditId());
		bomObject.setProductType(voObject.getProductType());
		bomObject.setComments(voObject.getComments());
		bomObject.setReadyForApproval(voObject.getReadyForApproval());
		bomObject.setApprovedOnce(voObject.getApprovedOnce());
		
		processBatches(bomObject, voObject, userVO);

		processFormulations(bomObject, voObject, userVO);

		processProductTests(bomObject, voObject, userVO);

		processProtocols(bomObject, voObject, userVO);
		
		return bomObject;

	}

	/**
	 * @param bomObject
	 * @param voObject
	 * @param userVO
	 */
	private void processBatches(Product bomObject, ProductVO voObject,
			UserVO userVO) {
		List list = voObject.getBatches();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			ProductBatchVO batchVO = (ProductBatchVO) iterator.next();
			// If this is a new entry (no key will come in), add to the product
			// batches
			boolean newRecord = false;
			ProductBatch batch = new ProductBatch();
			if (batchVO.getProductBatchId() == null
					|| batchVO.getProductBatchId().longValue() == 0) {
				if (batchVO.getBatchSize() == null ||
						batchVO.getBatchSize().trim().length() == 0) {
					//no data, new record, skip entry
					continue;
				}
				batch = new ProductBatch();
				newRecord = true;
			} else {
				batch = bomObject.getProductBatches(batchVO.getProductBatchId());
				if (batchVO.getBatchSize() == null || batchVO.getBatchSize().trim().length() == 0) {
					bomObject.getBatches().remove(batch);
					productFactory.delete(batch);
				}
			}
			populateProductBatchBOM(bomObject, batch, batchVO, userVO);
			if (newRecord) {
				bomObject.getBatches().add(batch);
			}
		}
	}

	/**
	 * @param bomObject
	 * @param voObject
	 * @param userVO
	 */
	private void processFormulations(Product bomObject, ProductVO voObject,
			UserVO userVO) {
		List list = voObject.getFormulations();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			ProductFormulationVO formulationVO = (ProductFormulationVO) iterator
					.next();
			// If this is a new entry (no key will come in), add to the product
			// formulations
			boolean newRecord = false;
			ProductFormulation formulation = null;
			if (formulationVO.getProductFormulationId() == null
					|| formulationVO.getProductFormulationId().longValue() == 0) {
				if (formulationVO.getProductFormulation() == null ||
						formulationVO.getProductFormulation().trim().length() == 0) {
					//new record but no data, skip entry
					continue;
				}
				formulation = new ProductFormulation();
				formulation.setProduct(bomObject);
				newRecord = true;
			} else {
				formulation = bomObject
						.getProductFormulations(formulationVO
								.getProductFormulationId());
				if (formulationVO.getProductFormulation() == null || 
						formulationVO.getProductFormulation().trim().length() == 0) {
					bomObject.getFormulations().remove(formulation);
					productFactory.delete(formulation);
				}
			}
			populateProductFormulationBOM(bomObject, formulation, formulationVO, userVO);
			if (newRecord) {
				bomObject.getFormulations().add(formulation);
			}
		}
		
//		for (Iterator iter=bomObject.getFormulations().iterator(); iter.hasNext();) {
//			ProductFormulation formulation = (ProductFormulation) iter.next();
//			for (int i=0; i<list.size(); i++) {
//				ProductFormulationVO vo = (ProductFormulationVO) list.get(i);
//				if (vo.getProductFormulationId().longValue() == formulation.getProductFormulationId().longValue()) {
//					iter.remove();
//					break;
//				}
//			}
//		}
	}

	/**
	 * @param bomObject
	 * @param voObject
	 * @param userVO
	 */
	private void processProductTests(Product bomObject, ProductVO voObject,
			UserVO userVO) throws EAPharmicsException {
		List list = voObject.getProductTests();
		//check if any of the tests have been deleted in the UI
		//loop through the BOM objects and find a match in the test VO list
		//if no match found, then it has been deleted
		for (Iterator iter=bomObject.getProductTests().iterator(); iter.hasNext();) {
			ProductTest test = (ProductTest) iter.next();
			boolean found = false;
			for (int i=0; i<list.size(); i++) {
				ProductTestVO vo = (ProductTestVO) list.get(i);
				if (vo.getProductTestId().longValue() != 0 &&   // new 
					vo.getProductTestId().longValue() == test.getProductTestId().longValue()) { // existing
					found = true;
					break;
				}
			}
			if (!found) {
				if (bomObject.getApprovedOnce()) {
					if (productFactory.testHasStudyResults(test.getProductTestId())) {
						throw new EAPharmicsException("Study results exist for test. Cannot delete test.");
					}
				}
				//there could be protocol details associated with this test
				//make sure we delete all the protocol details associated this test as well
				List pDetails = productFactory.getAllProtocolDetailsForTest(test.getProductTestId());
				for (Iterator iterator = pDetails.iterator(); iterator
						.hasNext();) {
					ProtocolDetail pDetail = (ProtocolDetail) iterator.next();
					productFactory.delete(pDetail);
				}
				productFactory.deleteProductTest(test.getProductTestId());
				iter.remove();
			}
		}

		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			ProductTestVO prodcutTestVO = (ProductTestVO) iterator
					.next();
			// If this is a new entry (no key will come in), add to the product
			// tests
			boolean newRecord = false;
			ProductTest test = null;
			if (prodcutTestVO.getProductTestId() == null
					|| prodcutTestVO.getProductTestId().longValue() == 0) {
				test = new ProductTest();
				test.setProduct(bomObject);
				productFactory.createProductTest(test);
				newRecord = true;
			} else {
				test = bomObject.getProductTests(prodcutTestVO.getProductTestId());
			}
			populateProductTestBOM(bomObject, test, prodcutTestVO, userVO);
			if (newRecord) {
				bomObject.getProductTests().add(test);
			}
		}

	}

	/**
	 * @param bomObject
	 * @param voObject
	 * @param userVO
	 */
	private void processProtocols(Product bomObject, ProductVO voObject,
			UserVO userVO) {
		//Check for deleted Protocols
		List bomList = bomObject.getProtocolsAsList();
		for (Iterator iterator = bomList.iterator(); iterator.hasNext();) {
			Protocol protocol = (Protocol) iterator.next();
			boolean matchFound = false;
			List list1 = voObject.getProtocols();
			for (Iterator iterator1 = list1.iterator(); iterator1.hasNext();) {
				ProtocolVO protocolVO = (ProtocolVO) iterator1.next();
				if (protocolVO.getProtocolId() != null && protocolVO.getProtocolId().longValue() != 0) {
					if (protocol.getProtocolId().longValue() == protocolVO.getProtocolId().longValue()) {
						matchFound = true;
					}
				}
			}
			//BOM object exists, but VO object is not there. This means the protocol has been deleted
			//We need to delete the dependent records from Protocol Detail and Inventory
			if (!matchFound) {
				List details = productFactory.getAllProtocolDetails(protocol.getProtocolId());
				for (Iterator iterator2 = details.iterator(); iterator2.hasNext();) {
					ProtocolDetail detail = (ProtocolDetail) iterator2.next();
					productFactory.delete(detail);					
				}
				List inventories = productFactory.getAllInventories(protocol.getProtocolId());
				for (Iterator iterator2 = inventories.iterator(); iterator2.hasNext();) {
					Inventory inventory = (Inventory) iterator2.next();
					productFactory.delete(inventory);					
				}
				productFactory.delete(protocol);
				bomObject.getProtocols().remove(protocol);
			}
		}
		
		List list = voObject.getProtocols();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			ProtocolVO protocolVO = (ProtocolVO) iterator
					.next();
			// If this is a new entry (no key will come in), add to the product
			// protocols
			boolean newRecord = false;
			Protocol protocol = null;
			if (protocolVO.getProtocolId() == null
					|| protocolVO.getProtocolId().longValue() == 0) {
				protocol = new Protocol();
				protocol.setProduct(bomObject);
				productFactory.createProtocol(protocol);
				protocol.setInsertDate(Utility.getCurrentUserLocationDateTime());
				protocol.setInsertUser(userVO.getAppUserId());
				newRecord = true;
			} else {
				protocol = bomObject.getProtocols(protocolVO.getProtocolId());
			}
			populateProtocolBOM(bomObject, protocol, protocolVO, userVO);
			if (newRecord) {
				bomObject.getProtocols().add(protocol);
			}
		}
		
	}


	/**
	 * @param bomObject
	 * @param voObject
	 * @param userVO
	 * @return
	 */
	private ProductBatch populateProductBatchBOM(Product product,
			ProductBatch bomObject, ProductBatchVO voObject, UserVO userVO) {
		// bomObject.setProductId(voObject.getProductId());
		if (voObject.getProductBatchId() != null && voObject.getProductBatchId().longValue() != 0) {
			bomObject.setProductBatchId(voObject.getProductBatchId());
		}
		if (bomObject.getProductBatchId() == null
				|| bomObject.getProductBatchId().longValue() == 0) {
			bomObject.setProduct(product);
			bomObject.setInsertDate(Utility.getCurrentUserLocationDateTime());
			bomObject.setInsertUser(userVO.getAppUserId());
		}
		bomObject.setBatchLineNumber(voObject.getBatchLineNumber());
		if (bomObject.getBatchSize() == null ||
			bomObject.getBatchSize().equals(voObject.getBatchSize()) == false) {
			bomObject.setBatchSize(voObject.getBatchSize());
			bomObject.setAuditId(voObject.getAuditId());
			bomObject.setComments(voObject.getComments());
			bomObject.setUpdateDate(Utility.getCurrentUserLocationDateTime());
			bomObject.setUpdateUser(userVO.getAppUserId());
		}
		return bomObject;

	}

	/**
	 * @param product
	 * @param bomObject
	 * @param voObject
	 * @param userVO
	 * @return
	 */
	private ProductFormulation populateProductFormulationBOM(Product product,
			ProductFormulation bomObject, ProductFormulationVO voObject,
			UserVO userVO) {
		if (voObject.getProductFormulationId() != null && voObject.getProductFormulationId().longValue() != 0) {
			bomObject.setProductFormulationId(voObject.getProductFormulationId());
		}
		if (bomObject.getProductFormulationId() == null
				|| bomObject.getProductFormulationId().longValue() == 0) {
			bomObject.setProduct(product);
			bomObject.setInsertDate(Utility.getCurrentUserLocationDateTime());
			bomObject.setInsertUser(userVO.getAppUserId());
		}
		bomObject.setLineNumber(voObject.getLineNumber());
		if (bomObject.getProductFormulation() == null ||
			bomObject.getProductFormulation().equals(
				voObject.getProductFormulation()) == false) {
			bomObject.setProductFormulation(voObject.getProductFormulation());
			bomObject.setAuditId(voObject.getAuditId());
			bomObject.setComments(voObject.getComments());
			bomObject.setUpdateDate(Utility.getCurrentUserLocationDateTime());
			bomObject.setUpdateUser(userVO.getAppUserId());
		}
		return bomObject;

	}

	/**
	 * @param bomObject
	 * @param voObject
	 * @param userVO
	 * @return
	 */
	private Test populateTestBOM(Test bomObject, TestVO voObject, UserVO userVO) {
		bomObject.setTextLimit(voObject.getTextLimit());
		bomObject.setTestName(voObject.getTestName());
		bomObject.setLowerLimit(voObject.getLowerLimit());
		bomObject.setUpperLimit(voObject.getUpperLimit());
		bomObject.setTextLimit(voObject.getTextLimit());		
		bomObject.setNumOfEntries(voObject.getNumOfEntries());
		bomObject.setMethod(voObject.getMethod());
		bomObject.setDecimalPoints(voObject.getDecimalPoints());
		bomObject.setResultsFormat(voObject.getResultsFormat());
		bomObject.setMeasurement(voObject.getMeasurement());
		bomObject.setGraph(voObject.getGraph());
		bomObject.setLabNumber(voObject.getLabNumber());
		bomObject.setTextLine(voObject.getTextLine());
		bomObject.setAlertLowerLimit(voObject.getAlertLowerLimit());
		bomObject.setAlertUpperLimit(voObject.getAlertUpperLimit());
		bomObject.setAlertLowerVariance(voObject.getAlertLowerVariance());
		bomObject.setAlertUpperVariance(voObject.getAlertUpperVariance());
		bomObject.setAuditId(voObject.getAuditId());
		bomObject.setComments(voObject.getComments());
		return bomObject;

	}

	/**
	 * @param product
	 * @param bomObject
	 * @param voObject
	 * @param userVO
	 * @return
	 */
	private ProductTest populateProductTestBOM(Product product,
			ProductTest bomObject, ProductTestVO voObject, UserVO userVO) {
		if (voObject.getProductTestId() == null
				|| voObject.getProductTestId().longValue() == 0) {
			bomObject.setProduct(product);
			bomObject.setInsertDate(Utility.getCurrentUserLocationDateTime());
			bomObject.setInsertUser(userVO.getAppUserId());
		} else {
			bomObject.setProductTestId(voObject.getProductTestId());
		}
		bomObject.setSortOrderPrevious(voObject.getSortOrderPrevious());
		bomObject.setSortOrderCurrent(voObject.getSortOrderCurrent());
		bomObject.setAuditId(voObject.getAuditId());
		bomObject.setComments(voObject.getComments());
		bomObject.setTestName(voObject.getTestName());
		bomObject.setLowerLimit(voObject.getLowerLimit());
		bomObject.setUpperLimit(voObject.getUpperLimit());
		bomObject.setTextLimit(voObject.getTextLimit());		
		bomObject.setNumOfEntries(voObject.getNumOfEntries());
		bomObject.setMethod(voObject.getMethod());
		bomObject.setDecimalPoints(voObject.getDecimalPoints());
		bomObject.setResultsFormat(voObject.getResultsFormat());
		bomObject.setMeasurement(voObject.getMeasurement());
		bomObject.setGraph(voObject.getGraph());
		bomObject.setLabNumber(voObject.getLabNumber());
		bomObject.setTextLine(voObject.getTextLine());
		bomObject.setAlertLowerLimit(voObject.getAlertLowerLimit());
		bomObject.setAlertUpperLimit(voObject.getAlertUpperLimit());
		bomObject.setAlertLowerVariance(voObject.getAlertLowerVariance());
		bomObject.setAlertUpperVariance(voObject.getAlertUpperVariance());
		bomObject.setUpdateDate(Utility.getCurrentUserLocationDateTime());
		bomObject.setUpdateUser(userVO.getAppUserId());
		return bomObject;

	}

	/**
	 * @param product
	 * @param bomObject
	 * @param voObject
	 * @param userVO
	 * @return
	 */
	private Protocol populateProtocolBOM(Product product, Protocol bomObject,
			ProtocolVO voObject, UserVO userVO) {
		bomObject.setProductId(voObject.getProductId());
		bomObject.setProtocolNumber(voObject.getProtocolNumber());
		bomObject.setProtocolName(voObject.getProtocolName());
		bomObject.setIntervalUnits(voObject.getIntervalUnits());
		bomObject.setTestIntervalValue1(voObject.getTestIntervalValue1());
		bomObject.setTestIntervalValue2(voObject.getTestIntervalValue2());
		bomObject.setTestIntervalValue3(voObject.getTestIntervalValue3());
		bomObject.setTestIntervalValue4(voObject.getTestIntervalValue4());
		bomObject.setTestIntervalValue5(voObject.getTestIntervalValue5());
		bomObject.setTestIntervalValue6(voObject.getTestIntervalValue6());
		bomObject.setTestIntervalValue7(voObject.getTestIntervalValue7());
		bomObject.setTestIntervalValue8(voObject.getTestIntervalValue8());
		bomObject.setTestIntervalValue9(voObject.getTestIntervalValue9());
		bomObject.setTestIntervalValue10(voObject.getTestIntervalValue10());
		bomObject.setTestIntervalValue11(voObject.getTestIntervalValue11());
		bomObject.setTestIntervalValue12(voObject.getTestIntervalValue12());
		bomObject.setTestIntervalValue13(voObject.getTestIntervalValue13());
		bomObject.setTestIntervalValue14(voObject.getTestIntervalValue14());
		bomObject.setTestIntervalValue15(voObject.getTestIntervalValue15());
		bomObject.setTestIntervalValue16(voObject.getTestIntervalValue16());
		bomObject.setTestIntervalValue17(voObject.getTestIntervalValue17());
		bomObject.setTestIntervalValue18(voObject.getTestIntervalValue18());
		bomObject.setTestIntervalValue19(voObject.getTestIntervalValue19());
		bomObject.setTestIntervalValue20(voObject.getTestIntervalValue20());
		bomObject.setAuditId(voObject.getAuditId());
		bomObject.setComments(voObject.getComments());
		bomObject.setUpdateDate(Utility.getCurrentUserLocationDateTime());
		bomObject.setUpdateUser(userVO.getAppUserId());

		List details = productFactory.getAllProtocolDetails(voObject.getProtocolId());
		List inventories = productFactory.getAllInventories(voObject.getProtocolId());
		
		List tests = product.getProductTestsAsList();
		for (int i=0; i<tests.size(); i++) {
			ProductTest productTest = (ProductTest) tests.get(i);
			int index = 0;
			for (Iterator iterator = voObject.getDetails().iterator(); iterator.hasNext();) {
				ProtocolDetailVO detailVO = (ProtocolDetailVO) iterator.next();
				if (detailVO.getTestName().equals(productTest.getTestName())) {
					ProtocolDetail detail = null;					
					if (detailVO.getProtocolDetailId() != null || 
							detailVO.getProtocolDetailId().longValue() != 0) {
						for (Iterator iterator2 = details.iterator(); iterator2.hasNext();) {
							ProtocolDetail dbDetail = (ProtocolDetail) iterator2.next();
							if (dbDetail.getProtocolDetailId().longValue() ==
								detailVO.getProtocolDetailId().longValue()) {
								detail = dbDetail;
								break;
							}
						}
					}
					if (detailVO.getProtocolDetailId() == null || 
							detailVO.getProtocolDetailId().longValue() == 0) { 
						//need to create a new detail
						detail = new ProtocolDetail();
						detail.setInsertDate(Utility.getCurrentUserLocationDateTime());
						detail.setInsertUser(userVO.getAppUserId());
						detail.setAuditId(userVO.getAuditId());
						detail.setProtocolId(bomObject.getProtocolId());
						populateProtocolDetailBOM(detail, detailVO);
						detail.setTestId(productTest.getProductTestId());
						detail.setUpdateDate(Utility.getCurrentUserLocationDateTime());
						detail.setUpdateUser(userVO.getAppUserId());
						productFactory.updateProtocolDetail(detail);
					} else {
						populateProtocolDetailBOM(detail, detailVO);
						detail.setUpdateDate(Utility.getCurrentUserLocationDateTime());
						detail.setUpdateUser(userVO.getAppUserId());
						productFactory.updateProtocolDetail(detail);
					}
					index++;
				}
			}
		}
		
		List list = voObject.getInventories();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			InventoryVO inventoryVO = (InventoryVO) iterator.next();
			Inventory inventory = null;
			if (inventoryVO.getInventoryId() == null
					|| inventoryVO.getInventoryId().longValue() == 0) {
				inventory = new Inventory();
				inventory.setInsertDate(Utility.getCurrentUserLocationDateTime());
				inventory.setInsertUser(userVO.getAppUserId());
				inventory.setProtocolId(bomObject.getProtocolId());
				inventory.setProductId(product.getProductId());
				productFactory.createInventory(inventory);
			} else {
				inventory = productFactory.getInventory(inventoryVO
						.getInventoryId());
			}
			populateInventoryBOM(inventory, inventoryVO, bomObject, userVO);
			inventory.setUpdateDate(Utility.getCurrentUserLocationDateTime());
			inventory.setUpdateUser(userVO.getAppUserId());
			inventory.setAuditId(userVO.getAuditId());
			productFactory.updateInventory(inventory);
		}

		return bomObject;

	}

	/**
	 * Copy the contents of VO object to the BOM object
	 * 
	 * @param bomObject
	 * @param voObject
	 */
	private ProtocolDetail populateProtocolDetailBOM(ProtocolDetail bomObject,
			ProtocolDetailVO voObject) {
		bomObject.setProtocolIntervalId(voObject.getProtocolIntervalId());
		bomObject.setTestId(voObject.getTestId());
		bomObject.setTestIntervalUsed1(voObject.getTestIntervalUsed1());
		bomObject.setTestIntervalUsed2(voObject.getTestIntervalUsed2());
		bomObject.setTestIntervalUsed3(voObject.getTestIntervalUsed3());
		bomObject.setTestIntervalUsed4(voObject.getTestIntervalUsed4());
		bomObject.setTestIntervalUsed5(voObject.getTestIntervalUsed5());
		bomObject.setTestIntervalUsed6(voObject.getTestIntervalUsed6());
		bomObject.setTestIntervalUsed7(voObject.getTestIntervalUsed7());
		bomObject.setTestIntervalUsed8(voObject.getTestIntervalUsed8());
		bomObject.setTestIntervalUsed9(voObject.getTestIntervalUsed9());
		bomObject.setTestIntervalUsed10(voObject.getTestIntervalUsed10());
		bomObject.setTestIntervalUsed11(voObject.getTestIntervalUsed11());
		bomObject.setTestIntervalUsed12(voObject.getTestIntervalUsed12());
		bomObject.setTestIntervalUsed13(voObject.getTestIntervalUsed13());
		bomObject.setTestIntervalUsed14(voObject.getTestIntervalUsed14());
		bomObject.setTestIntervalUsed15(voObject.getTestIntervalUsed15());
		bomObject.setTestIntervalUsed16(voObject.getTestIntervalUsed16());
		bomObject.setTestIntervalUsed17(voObject.getTestIntervalUsed17());
		bomObject.setTestIntervalUsed18(voObject.getTestIntervalUsed18());
		bomObject.setTestIntervalUsed19(voObject.getTestIntervalUsed19());
		bomObject.setTestIntervalUsed20(voObject.getTestIntervalUsed20());
		return bomObject;

	}

	/**
	 * Copy the contents of VO object to the BOM object
	 * 
	 * @param bomObject
	 * @param voObject
	 */
	private Inventory populateInventoryBOM(Inventory bomObject,
			InventoryVO voObject, Protocol protocol, UserVO userVO) {
		bomObject.setTestIntervalInventory1(voObject.getTestIntervalInventory1());
		bomObject.setTestIntervalInventory2(voObject.getTestIntervalInventory2());
		bomObject.setTestIntervalInventory3(voObject.getTestIntervalInventory3());
		bomObject.setTestIntervalInventory4(voObject.getTestIntervalInventory4());
		bomObject.setTestIntervalInventory5(voObject.getTestIntervalInventory5());
		bomObject.setTestIntervalInventory6(voObject.getTestIntervalInventory6());
		bomObject.setTestIntervalInventory7(voObject.getTestIntervalInventory7());
		bomObject.setTestIntervalInventory8(voObject.getTestIntervalInventory8());
		bomObject.setTestIntervalInventory9(voObject.getTestIntervalInventory9());
		bomObject.setTestIntervalInventory10(voObject.getTestIntervalInventory10());
		bomObject.setTestIntervalInventory11(voObject.getTestIntervalInventory11());
		bomObject.setTestIntervalInventory12(voObject.getTestIntervalInventory12());
		bomObject.setTestIntervalInventory13(voObject.getTestIntervalInventory13());
		bomObject.setTestIntervalInventory14(voObject.getTestIntervalInventory14());
		bomObject.setTestIntervalInventory15(voObject.getTestIntervalInventory15());
		bomObject.setTestIntervalInventory16(voObject.getTestIntervalInventory16());
		bomObject.setTestIntervalInventory17(voObject.getTestIntervalInventory17());
		bomObject.setTestIntervalInventory18(voObject.getTestIntervalInventory18());
		bomObject.setTestIntervalInventory19(voObject.getTestIntervalInventory19());
		bomObject.setTestIntervalInventory20(voObject.getTestIntervalInventory20());
		if ((bomObject.getFillUnits() == null || 
				bomObject.getFillUnits().equals(voObject.getFillUnits()) == false) ||
			(bomObject.getFillUnits() == null || 
					bomObject.getFillUnits().equals(voObject.getFillUnits()) == false) ||
			(bomObject.getTotal() == null || 
					bomObject.getTotal().equals(voObject.getTotal()) == false) ||
			(bomObject.getUnitOfMeasure() == null || 
					bomObject.getUnitOfMeasure().equals(voObject.getUnitOfMeasure()) == false)
			) {
			bomObject.setFillUnits(voObject.getFillUnits());
			bomObject.setUnitOfMeasure(voObject.getUnitOfMeasure());
			bomObject.setTotal(voObject.getTotal());
			bomObject.setExtra(voObject.getExtra());
			bomObject.setAuditId(voObject.getAuditId());
			bomObject.setComments(voObject.getComments());
			bomObject.setAuditId(userVO.getAuditId());
			bomObject.setUpdateDate(Utility.getCurrentUserLocationDateTime());
			bomObject.setUpdateUser(userVO.getAppUserId());
		}


		return bomObject;

	}

	/**
	 * Copy the contents of VO object to the BOM object
	 * 
	 * @param bomObject
	 * @param voObject
	 */
	private ProductSpec populateProductSpecBOM(ProductSpec bomObject,
			ProductSpecVO voObject) {
		bomObject.setProductId(voObject.getProductId());
		bomObject.setLineNumber(voObject.getLineNumber());
		bomObject.setTextLine(voObject.getTextLine());
		bomObject.setAuditId(voObject.getAuditId());
		bomObject.setComments(voObject.getComments());
		return bomObject;

	}

	/**
	 * @param productTestIds
	 * @return
	 */
	public List<ProductTestVO> getAllProductTestsForKeys(List productTestIds) {
		List<ProductTest> list = productFactory.getAllProductTestsForKeys(productTestIds);
		List <ProductTestVO> returnList = new ArrayList<ProductTestVO>();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			ProductTest productTest = (ProductTest) iterator.next();
			ProductTestVO voObject = new ProductTestVO();
			returnList.add(populateProductTestVO(productTest, voObject));
		}
		return returnList;
		
	}

	private List<ProductSummaryVO> getProductSummaryVO(List details) {
		List<ProductSummaryVO> list = new ArrayList();
	    for (Iterator<Object[]> iterator = details.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			ProductSummaryVO vo = new ProductSummaryVO();
			vo.setProductId((Long) objects[0]);
			vo.setProductCode((String) objects[1]);
			vo.setProductName((String) objects[2]);
			vo.setProductDescription((String) objects[3]);
			list.add(vo);
		}
		return list;
	}

	/**
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<ProductSummaryVO> getAllApprovedProductsSummary() throws EAPharmicsException {
		return getProductSummaryVO(productFactory.getAllApprovedProductsSummary());
	}

	/**
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<ProductSummaryVO> getAllApprovedProductsSummaryWithGraph() throws EAPharmicsException {
		return getProductSummaryVO(productFactory.getAllApprovedProductsSummaryWithGraph());
	}

	private List<ProtocolsForProductVO> getProtocolsForProductVO(List details) {
		List<ProtocolsForProductVO> list = new ArrayList();
	    for (Iterator<Object[]> iterator = details.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			ProtocolsForProductVO vo = new ProtocolsForProductVO();
			vo.setProtocolId((Long) objects[0]);
			vo.setProtocolName((String) objects[1]);
			vo.setProtocolNumber((Long) objects[2]);
			list.add(vo);
		}
		return list;
	}

	/**
	 * @return
	 * @throws EAPharmicsException
	 */
	public List<ProtocolsForProductVO> getAllProtocolsForProduct(String productCode) throws EAPharmicsException {
		return getProtocolsForProductVO(productFactory.getAllProtocolsForProduct(productCode));
	}
	
}
