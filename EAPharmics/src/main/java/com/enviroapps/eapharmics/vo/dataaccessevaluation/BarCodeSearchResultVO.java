package com.enviroapps.eapharmics.vo.dataaccessevaluation;

import com.enviroapps.eapharmics.vo.product.ProductVO;

/**
 * @author EAPharmics
 *
 */
public class BarCodeSearchResultVO {

	private DataAccessEvaluationVO result;
	private ProductVO productVO = null;
	
	/**
	 * Default Constructor
	 */
	public BarCodeSearchResultVO() {
		
	}

	/**
	 * @return the result
	 */
	public DataAccessEvaluationVO getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(DataAccessEvaluationVO result) {
		this.result = result;
	}

	/**
	 * @return the productVO
	 */
	public ProductVO getProductVO() {
		return productVO;
	}

	/**
	 * @param productVO the productVO to set
	 */
	public void setProductVO(ProductVO productVO) {
		this.productVO = productVO;
	}

}
