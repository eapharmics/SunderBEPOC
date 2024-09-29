package com.enviroapps.eapharmics.vo.security;

public class TemplateAccessModuleVO implements java.io.Serializable {
	/**
	 * 
	 */
	private Long appTemplateModuleAccessId;
	private Long appAccessTemplateId;
	private Long appModuleId;
	private String moduleDescription;
	private boolean hasAccess;

	/**
	 * @return the appTemplateModuleAccessId
	 */
	public Long getAppTemplateModuleAccessId() {
		return appTemplateModuleAccessId;
	}

	/**
	 * @param appTemplateModuleAccessId
	 *            the appTemplateModuleAccessId to set
	 */
	public void setAppTemplateModuleAccessId(Long appTemplateModuleAccessId) {
		this.appTemplateModuleAccessId = appTemplateModuleAccessId;
	}

	/**
	 * @return the appAccessTemplateId
	 */
	public Long getAppAccessTemplateId() {
		return appAccessTemplateId;
	}

	/**
	 * @param appAccessTemplateId
	 *            the appAccessTemplateId to set
	 */
	public void setAppAccessTemplateId(Long appAccessTemplateId) {
		this.appAccessTemplateId = appAccessTemplateId;
	}

	/**
	 * @return the appModuleId
	 */
	public Long getAppModuleId() {
		return appModuleId;
	}

	/**
	 * @param appModuleId
	 *            the appModuleId to set
	 */
	public void setAppModuleId(Long appModuleId) {
		this.appModuleId = appModuleId;
	}

	/**
	 * @return the moduleDescription
	 */
	public String getModuleDescription() {
		return moduleDescription;
	}

	/**
	 * @param moduleDescription
	 *            the moduleDescription to set
	 */
	public void setModuleDescription(String moduleDescription) {
		this.moduleDescription = moduleDescription;
	}

	/**
	 * @return the hasAccess
	 */
	public boolean getHasAccess() {
		return hasAccess;
	}

	/**
	 * @param hasAccess
	 *            the hasAccess to set
	 */
	public void setHasAccess(boolean hasAccess) {
		this.hasAccess = hasAccess;
	}

}
