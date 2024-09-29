/**
 * 
 */
package com.enviroapps.eapharmics.vo.security;


/**
 * @author EnvrioApps
 *
 */
public class UserAccessModuleVO implements java.io.Serializable {
	
	private Long appUserModuleAccessId;
	private Long appUserId;
	private Long appModuleId;
	private String moduleDescription;
	private boolean hasAccess;
	
	public UserAccessModuleVO() {
		
	}

	/**
	 * @return the appUserModuleAccessId
	 */
	public Long getAppUserModuleAccessId() {
		return appUserModuleAccessId;
	}

	/**
	 * @param appUserModuleAccessId the appUserModuleAccessId to set
	 */
	public void setAppUserModuleAccessId(Long appUserModuleAccessId) {
		this.appUserModuleAccessId = appUserModuleAccessId;
	}
	public Long getAppUserId() {
      return appUserId;
   }

   /**
    * @param appUserModuleAccessId the appUserModuleAccessId to set
    */
   public void setAppUserId(Long appUserId) {
      this.appUserId = appUserId;
   }
	/**
	 * @return the moduleDescription
	 */
   public Long getAppModuleId() {
      return appModuleId;
   }

   /**
    * @param appModuleId the appModuleId to set
    */
   public void setAppModuleId(Long moduleId) {
      this.appModuleId = moduleId;
   }
   
   
	public String getModuleDescription() {
		return moduleDescription;
	}

	/**
	 * @param moduleDescription the moduleDescription to set
	 */
	public void setModuleDescription(String moduleDescription) {
		this.moduleDescription = moduleDescription;
	}

	/**
	 * @return the hasAccess
	 */
	public boolean isHasAccess() {
		return hasAccess;
	}

	  public boolean getHasAccess() {
	      return hasAccess;
	   }
	/**
	 * @param hasAccess the hasAccess to set
	 */
	public void setHasAccess(boolean hasAccess) {
		this.hasAccess = hasAccess;
	}

}
