/**
 * 
 */
package com.enviroapps.eapharmics.vo.newstudy;

import com.enviroapps.eapharmics.vo.product.ProductVO;

/**
 * @author EnviroApps
 *
 */
public class NewStudyScreenDataVO {

	private NewStudyProductVO newStudyProductVO;
	private ProductVO productVO;
	/**
	 * @return the newStudyProductVO
	 */
	public NewStudyProductVO getNewStudyProductVO() {
		return newStudyProductVO;
	}
	/**
	 * @param newStudyProductVO the newStudyProductVO to set
	 */
	public void setNewStudyProductVO(NewStudyProductVO newStudyProductVO) {
		this.newStudyProductVO = newStudyProductVO;
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
