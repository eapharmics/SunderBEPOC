/**
 * 
 */
package com.enviroapps.eapharmics.vo.newstudy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author EnviroApps
 *
 */
public class NewStudyProductLotVO {

	private String productCode;
	private List<NewStudyProductLotContainerVO> lotNumbers = new ArrayList <NewStudyProductLotContainerVO>(); 

	public NewStudyProductLotVO() {
		//Default Constructor
	}

	/**
	 * @return the productCode
	 */
	public String getProductCode() {
		return productCode;
	}

	/**
	 * @param productCode the productCode to set
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	/**
	 * @return the lotNumbers
	 */
	public List<NewStudyProductLotContainerVO> getLotNumbers() {
		return lotNumbers;
	}

	/**
	 * @param lotNumbers the lotNumbers to set
	 */
	public void setLotNumbers(List<NewStudyProductLotContainerVO> lotNumbers) {
		this.lotNumbers = lotNumbers;
	}
}
