package com.enviroapps.eapharmics.persistence;

import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.enviroapps.eapharmics.bom.product.Inventory;
import com.enviroapps.eapharmics.bom.product.Product;
import com.enviroapps.eapharmics.bom.product.ProductSpec;
import com.enviroapps.eapharmics.bom.product.ProductTest;
import com.enviroapps.eapharmics.bom.product.Protocol;
import com.enviroapps.eapharmics.bom.product.ProtocolDetail;
import com.enviroapps.eapharmics.bom.product.Test;
import com.enviroapps.eapharmics.common.Utility;
import com.enviroapps.eapharmics.das.persistence.DataAccessConstants;
import com.enviroapps.eapharmics.das.persistence.hibernate.HibernatePersistenceFactory;
import com.enviroapps.eapharmics.das.persistence.hibernate.HibernatePersistenceManager;
import com.enviroapps.eapharmics.das.persistence.hibernate.HibernatePersistenceManagerFactory;
import com.enviroapps.eapharmics.vo.security.UserVO;


/**
 * @author EnviroApps
 *
 */
public class ProductFactory extends HibernatePersistenceFactory implements
	DataAccessConstants {

	private static final Logger logger = LoggerFactory.getLogger(ProductFactory.class);

	private ProductFactory() {
		persistenceManager = (HibernatePersistenceManager) HibernatePersistenceManagerFactory
				.getInstance().getPersistenceManager();
		logger.debug("ProductFactory", persistenceManager);
	}

	private static ProductFactory instance = new ProductFactory();

	public static ProductFactory getInstance() {
		return instance;
	}

	/**
	 * @param productCode
	 * @return
	 */
	public boolean productExistsForProductCode(String productCode) {
		Session session = persistenceManager.getCurrentSession();
		//return true if a product exists for the given product code
		//used for duplicate checking
		String sql = "SELECT PRODUCT_ID ";
		sql = sql + "  FROM EA_PRD_PRODUCTS WHERE PRODUCT_CODE = '" + productCode + "'";
		SQLQuery q = persistenceManager.getCurrentSession().createSQLQuery(sql);
	    List details = q.list();
	    return (details != null && details.size() > 0);
	}

	/**
	 * @return
	 */
	public List getAllProductsSummary(String productType) {
		Session session = persistenceManager.getCurrentSession();
		//Get the last used display order for this dictionary code
		//and add 1 and return
		String sql = "SELECT PRODUCT_ID,PRODUCT_CODE, PRODUCT_NAME, PRODUCT_DESCRIPTION, ";
		sql = sql + " APPROVAL_LEVEL1_YN, APPROVAL_LEVEL2_YN, EXPIRATION_PERIOD, PRODUCT_TYPE,";
		sql = sql + " ANNUAL_RPT_ANNIVERSARY_DATE, READY_FOR_APPROVAL_YN FROM EA_PRD_PRODUCTS WHERE ACTIVE = 'Y' ";
		sql = sql + " AND LOCATION_ID = " + super.getCurrentLocationId();
		if (productType != null && productType.trim().length() > 0) {
			sql = sql + " AND PRODUCT_TYPE = '" + productType + "'";
		}
		sql = sql + " ORDER BY PRODUCT_NAME";
		SQLQuery q = persistenceManager.getCurrentSession().createSQLQuery(sql);
		q.addScalar("PRODUCT_ID", Hibernate.LONG);
		q.addScalar("PRODUCT_CODE", Hibernate.STRING);
		q.addScalar("PRODUCT_NAME", Hibernate.STRING);
		q.addScalar("PRODUCT_DESCRIPTION", Hibernate.STRING);
		q.addScalar("APPROVAL_LEVEL1_YN", Hibernate.STRING);
		q.addScalar("APPROVAL_LEVEL2_YN", Hibernate.STRING);
		q.addScalar("EXPIRATION_PERIOD", Hibernate.STRING);
		q.addScalar("PRODUCT_TYPE", Hibernate.STRING);
		q.addScalar("ANNUAL_RPT_ANNIVERSARY_DATE", Hibernate.DATE);
		q.addScalar("READY_FOR_APPROVAL_YN", Hibernate.STRING);
	    List details = q.list();
	    return details;
	}

	/**
	 * @param productId
	 * @param annualReportAnniversaryDate
	 * @param userVO
	 */
	public void saveAnnualReportAnniversaryDate(
			Long productId, Date annualReportAnniversaryDate, UserVO userVO) {
		Session session = persistenceManager.getCurrentSession();
		String sql1 = "UPDATE EA_PRD_PRODUCTS SET ANNUAL_RPT_ANNIVERSARY_DATE = :anniversary_date"
			+ ", update_user = :update_user" 
			+ ", update_date = :update_date"
			+ ", audit_id = :audit_id"
			+ " WHERE PRODUCT_ID = :product_id";
		session.createSQLQuery(sql1)
			.setParameter("anniversary_date", annualReportAnniversaryDate, Hibernate.DATE)
			.setParameter("update_user", userVO.getAppUserId(), Hibernate.LONG)
			.setParameter("update_date", Utility.getCurrentUserLocationDateTime(), Hibernate.DATE)
			.setParameter("audit_id", userVO.getAuditId(), Hibernate.LONG)
			.setParameter("product_id", productId, Hibernate.LONG)
			.executeUpdate();

	}
	/**
	 * @return
	 */
	public List getAllProducts() {
		Session session = persistenceManager.getCurrentSession();
		List list = session.createCriteria(Product.class)
			.add(Restrictions.eq("locationId", super.getCurrentLocationId()))
			.list();
		return list;
	}

	/**
	 * @param eaProductId
	 * @return
	 */
	public Product getProduct(Long eaProductId) {
		Session session = persistenceManager.getCurrentSession();
		Product product = (Product) session.load(Product.class, eaProductId);
		return product;
	}

	/**
	 * @param product
	 */
	public void createProduct(Product product) {
		Session session = persistenceManager.getCurrentSession();
		product.setLocationId(super.getCurrentLocationId());
		session.saveOrUpdate(product);
	}

	/**
	 * @param product
	 */
	public void updateProduct(Product product) {
		Session session = persistenceManager.getCurrentSession();
		session.saveOrUpdate(product);
	}

	/**
	 * @param productId
	 */
	public void deleteProduct(Long productId) {
		Session session = persistenceManager.getCurrentSession();
		Product classVariable = (Product) session.get(Product.class, productId);
		session.delete(classVariable);
	}

	public boolean canDeleteProduct(Long productId) {
		long count = 0;
		String sql = "select count(*) as studies from ea_prd_product_study where product_id = " + productId.toString();
		sql = sql + " and location_id = " + super.getCurrentLocationId();
		Session session = persistenceManager.getCurrentSession();
		List details = session.createSQLQuery(sql)
		 .addScalar("studies", Hibernate.LONG).list();
		if (details != null && details.size() > 0) {
			if (details.get(0) != null) {
				count = ((Long) details.get(0)).longValue();
			}
		}
		return (count == 0);
	}
	

	/**
	 * @return
	 */
	public List getAllTests() {
		Session session = persistenceManager.getCurrentSession();
		List list = session.createCriteria(Test.class)
				.add(Restrictions.eq("locationId", super.getCurrentLocationId()))
					.addOrder(Order.asc("testName"))
					.addOrder(Order.asc("textLimit"))
					.addOrder(Order.asc("lowerLimit"))
					.addOrder(Order.asc("upperLimit"))
					.list();
		return list;
	}

	/**
	 * @param eaProductTestId
	 * @return
	 */
	public Test getTest(Long eaTestId) {
		Session session = persistenceManager.getCurrentSession();
		Test productTest = (Test) session.load(Test.class, eaTestId);
		return productTest;
	}

	/**
	 * @param productTest
	 */
	public void createTest(Test test) {
		Session session = persistenceManager.getCurrentSession();
		test.setLocationId(super.getCurrentLocationId());
		session.saveOrUpdate(test);
	}

	/**
	 * @param productTest
	 */
	public void updateTest(Test test) {
		Session session = persistenceManager.getCurrentSession();
		session.saveOrUpdate(test);
	}

	/**
	 * @param testId
	 */
	public void deleteTest(Long testId) {
		Session session = persistenceManager.getCurrentSession();
		Test classVariable = (Test) session.get(Test.class, testId);
		session.delete(classVariable);
	}


	/**
	 * @return
	 */
	public List getAllProductTestsForKeys(List productTestIds) {
		Session session = persistenceManager.getCurrentSession();
		List list = session.createCriteria(ProductTest.class)
					.add(Restrictions.in("productTestId", productTestIds))
					.addOrder(Order.asc("sortOrderCurrent"))
					.list();
		return list;
	}

	/**
	 * @return
	 */
	public List getAllProductTests(Long productId) {
		Session session = persistenceManager.getCurrentSession();
		List list = session.createCriteria(ProductTest.class)
					.add(Restrictions.eq("productId", productId)).list();
		return list;
	}

	/**
	 * @param eaTestId
	 * @return
	 */
	public ProductTest getProductTest(Long eaProductTestId) {
		Session session = persistenceManager.getCurrentSession();
		ProductTest test = (ProductTest) session.load(ProductTest.class, eaProductTestId);
		return test;
	}

	/**
	 * @param productTest
	 */
	public void createProductTest(ProductTest productTest) {
		Session session = persistenceManager.getCurrentSession();
		session.saveOrUpdate(productTest);
	}

	/**
	 * @param productTest
	 */
	public void updateProductTest(ProductTest productTest) {
		Session session = persistenceManager.getCurrentSession();
		session.saveOrUpdate(productTest);
	}

	/**
	 * @param productTestId
	 */
	public void deleteProductTest(Long productTestId) {
		Session session = persistenceManager.getCurrentSession();
		ProductTest classVariable = (ProductTest) session.get(ProductTest.class, productTestId);
		session.delete(classVariable);
	}


	/**
	 * @return
	 */
	public List getAllProtocols(Long productId) {
		Session session = persistenceManager.getCurrentSession();
		List list = session.createCriteria(Protocol.class)
					.add(Restrictions.eq("productId", productId)).list();
		return list;
	}

	/**
	 * @param eaProtocolId
	 * @return
	 */
	public Protocol getProtocol(Long eaProtocolId) {
		Session session = persistenceManager.getCurrentSession();
		Protocol protocol = (Protocol) session.load(Protocol.class, eaProtocolId);
		return protocol;
	}

	/**
	 * @param protocol
	 */
	public void createProtocol(Protocol protocol) {
		Session session = persistenceManager.getCurrentSession();
		session.saveOrUpdate(protocol);
	}

	/**
	 * @param protocol
	 */
	public void updateProtocol(Protocol protocol) {
		Session session = persistenceManager.getCurrentSession();
		session.saveOrUpdate(protocol);
	}

	/**
	 * @param protocolId
	 */
	public void deleteProtocol(Long protocolId) {
		Session session = persistenceManager.getCurrentSession();
		Protocol classVariable = (Protocol) session.get(Protocol.class, protocolId);
		session.delete(classVariable);
	}

	/**
	 * @return
	 */
	public List getAllProtocolDetails(Long protocolId) {
		Session session = persistenceManager.getCurrentSession();
		List list = session.createCriteria(ProtocolDetail.class)
					.add(Restrictions.eq("protocolId", protocolId))
					.addOrder(Order.asc("testId")).list();
		return list;
	}

	/**
	 * @return
	 */
	public List getAllProtocolDetailsForTest(Long testId) {
		Session session = persistenceManager.getCurrentSession();
		List list = session.createCriteria(ProtocolDetail.class)
					.add(Restrictions.eq("testId", testId)).list();
		return list;
	}

	/**
	 * @param eaProtocolDetailId
	 * @return
	 */
	public ProtocolDetail getProtocolDetail(Long eaProtocolDetailId) {
		Session session = persistenceManager.getCurrentSession();
		ProtocolDetail protocolDetail = (ProtocolDetail) session.load(ProtocolDetail.class, eaProtocolDetailId);
		return protocolDetail;
	}

	/**
	 * @param protocolDetail
	 */
	public void updateProtocolDetail(ProtocolDetail protocolDetail) {
		Session session = persistenceManager.getCurrentSession();
		session.saveOrUpdate(protocolDetail);
	}

	/**
	 * @return
	 */
	public List getAllInventories(Long protocolId) {
		Session session = persistenceManager.getCurrentSession();
		List list = session.createCriteria(Inventory.class)
					.add(Restrictions.eq("protocolId", protocolId)).list();
		return list;
	}

	/**
	 * @param eaInventoryId
	 * @return
	 */
	public Inventory getInventory(Long eaInventoryId) {
		Session session = persistenceManager.getCurrentSession();
		Inventory inventory = (Inventory) session.load(Inventory.class, eaInventoryId);
		return inventory;
	}

	/**
	 * @param inventory
	 */
	public void createInventory(Inventory inventory) {
		Session session = persistenceManager.getCurrentSession();
		session.saveOrUpdate(inventory);
	}

	/**
	 * @param inventory
	 */
	public void updateInventory(Inventory inventory) {
		Session session = persistenceManager.getCurrentSession();
		session.saveOrUpdate(inventory);
	}

	/**
	 * @param inventoryId
	 */
	public void deleteInventory(Long inventoryId) {
		Session session = persistenceManager.getCurrentSession();
		Inventory classVariable = (Inventory) session.get(Inventory.class, inventoryId);
		session.delete(classVariable);
	}

	/**
	 * @return
	 */
	public List getAllProductSpecs(Long productId) {
		Session session = persistenceManager.getCurrentSession();
		List list = session.createCriteria(ProductSpec.class)
					.add(Restrictions.eq("productId", productId)).list();
		return list;
	}

	/**
	 * @param eaProductSpecId
	 * @return
	 */
	public ProductSpec getProductSpec(Long eaProductSpecId) {
		Session session = persistenceManager.getCurrentSession();
		ProductSpec productSpec = (ProductSpec) session.load(ProductSpec.class, eaProductSpecId);
		return productSpec;
	}

	/**
	 * @param productSpec
	 */
	public void createProductSpec(ProductSpec productSpec) {
		Session session = persistenceManager.getCurrentSession();
		session.saveOrUpdate(productSpec);
	}

	/**
	 * @param productSpec
	 */
	public void updateProductSpec(ProductSpec productSpec) {
		Session session = persistenceManager.getCurrentSession();
		session.saveOrUpdate(productSpec);
	}

	/**
	 * @param productSpecId
	 */
	public void deleteProductSpec(Long productSpecId) {
		Session session = persistenceManager.getCurrentSession();
		ProductSpec classVariable = (ProductSpec) session.get(ProductSpec.class, productSpecId);
		session.delete(classVariable);
	}

	/**
	 * @return
	 */
	public List getAllApprovedProductsSummary(Long productId) {
		Session session = persistenceManager.getCurrentSession();
		//Get the last used display order for this dictionary code
		//and add 1 and return
		String sql = "SELECT PRODUCT_ID,PRODUCT_CODE, PRODUCT_NAME, PRODUCT_DESCRIPTION FROM EAVW_APPROVED_PRODUCTS WHERE LOCATION_ID = ";
		sql = sql + super.getCurrentLocationId();
		if (productId != null && productId.longValue() != 0) {
			sql = sql + " AND PRODUCT_ID = " + productId;
		}
		sql = sql + " ORDER BY PRODUCT_NAME";
		SQLQuery q = persistenceManager.getCurrentSession().createSQLQuery(sql);
		q.addScalar("PRODUCT_ID", Hibernate.LONG);
		q.addScalar("PRODUCT_CODE", Hibernate.STRING);
		q.addScalar("PRODUCT_NAME", Hibernate.STRING);
		q.addScalar("PRODUCT_DESCRIPTION", Hibernate.STRING);
	    List details = q.list();
	    return details;
	}

	/**
	 * @return
	 */
	public List getAllApprovedProductsSummary() {
		return getAllApprovedProductsSummary(null);
	}

	public List getAllApprovedProductsSummaryWithGraph() {
		Session session = persistenceManager.getCurrentSession();
		//Get the last used display order for this dictionary code
		//and add 1 and return
		String sql = "SELECT PRODUCT_ID,PRODUCT_CODE, PRODUCT_NAME, PRODUCT_DESCRIPTION " +
		             "FROM eavw_approved_products ap " +
					 "WHERE EXISTS (SELECT 1 " +
					 "FROM ea_prd_product_tests pt " +
					 "               WHERE pt.product_id = ap.product_id " +
					 "                 AND pt.graph = 'Y') AND " +
					 "LOCATION_ID = " + super.getCurrentLocationId() +
					 " ORDER BY PRODUCT_NAME";
		SQLQuery q = persistenceManager.getCurrentSession().createSQLQuery(sql);
		q.addScalar("PRODUCT_ID", Hibernate.LONG);
		q.addScalar("PRODUCT_CODE", Hibernate.STRING);
		q.addScalar("PRODUCT_NAME", Hibernate.STRING);
		q.addScalar("PRODUCT_DESCRIPTION", Hibernate.STRING);
	    List details = q.list();
	    return details;
	}

	public List getAllProtocolsForProduct(String productCode) {
		Session session = persistenceManager.getCurrentSession();
		//Get the last used display order for this dictionary code
		//and add 1 and return
		String sql = "SELECT protocol_id, protocol_name, protocol_number " +
				     "  FROM ea_prd_protocols pro, ea_prd_products prd" +
				     " WHERE pro.product_id = prd.product_id" +
				     " AND prd.location_id = " + super.getCurrentLocationId() +
				     "   AND prd.product_code = '" + productCode + "'" +
				     " ORDER BY 3";
		SQLQuery q = persistenceManager.getCurrentSession().createSQLQuery(sql);
		q.addScalar("PROTOCOL_ID", Hibernate.LONG);
		q.addScalar("PROTOCOL_NAME", Hibernate.STRING);
		q.addScalar("PROTOCOL_NUMBER", Hibernate.LONG);
	    List details = q.list();
	    return details;
	}

	/**
	 * @param productId
	 * @param userId
	 * @param auditId
	 * @throws Exception
	 */
	public void updateApproval(Long productId, Long userId, Long auditId) throws Exception {
		Session session = persistenceManager.getCurrentSession();
		Query queryObject = session.getNamedQuery("updateProductSchedule");
		queryObject.setParameter(0, productId);
		queryObject.setParameter(1, userId);
		queryObject.setParameter(2, auditId);
		queryObject.executeUpdate();
	}
	
	/**
	 * @param productId
	 * @param productTestId
	 * @return
	 */
	public boolean testHasStudyResults(Long productTestId) {
		//When a test is being deleted, make sure there are no study results
		//for the test with data entered. If it does, the test cannot be deleted
		Session session = persistenceManager.getCurrentSession();
		String sql = "select count(r.prd_study_test_results_id) as result_count";
		sql = sql + " from ea_prd_study_test_results r";
		sql = sql + " where r.product_test_id = " + productTestId;
		sql = sql + " and r.result_1 is not null and length(r.result_1) > 0";
		List details = session.createSQLQuery(sql)
		 .addScalar("result_count", Hibernate.LONG).list();
		Long resultCount = new Long(0);
		if (details != null && details.size() > 0) {
			if (details.get(0) != null) {
				resultCount = ((Long) details.get(0)).longValue();
			}
		}
		return resultCount == 0 ? false:true;
	}
}
