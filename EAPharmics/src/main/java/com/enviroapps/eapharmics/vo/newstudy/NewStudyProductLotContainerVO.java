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
public class NewStudyProductLotContainerVO {

	private String lotNumber;
	private List<String> containers = new ArrayList <String>();
	
	public NewStudyProductLotContainerVO() {
		//Default Constructor
	}
	
	/**
	 * @return the lotNumber
	 */
	public String getLotNumber() {
		return lotNumber;
	}
	/**
	 * @param lotNumber the lotNumber to set
	 */
	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}
	/**
	 * @return the containers
	 */
	public List<String> getContainers() {
		return containers;
	}
	/**
	 * @param containers the containers to set
	 */
	public void setContainers(List<String> containers) {
		this.containers = containers;
	} 
}
