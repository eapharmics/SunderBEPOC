/**
 * 
 */
package com.enviroapps.eapharmics.vo.security;

import java.util.ArrayList;
import java.util.List;

/**
 * @author EnvrioApps
 *
 */
public class UserAccessAreaVO implements java.io.Serializable {
	
	private String appAreaDescription;
	private List<UserAccessModuleVO> modules = new ArrayList();
	
	public UserAccessAreaVO() {
		
	}

	/**
	 * @return the appAreaDescription
	 */
	public String getAppAreaDescription() {
		return appAreaDescription;
	}

	/**
	 * @param appAreaDescription the appAreaDescription to set
	 */
	public void setAppAreaDescription(String appAreaDescription) {
		this.appAreaDescription = appAreaDescription;
	}

	/**
	 * @return the modules
	 */
	public List<UserAccessModuleVO> getModules() {
		return modules;
	}

	/**
	 * @param modules the modules to set
	 */
	public void setModules(List<UserAccessModuleVO> modules) {
		this.modules = modules;
	}

}
