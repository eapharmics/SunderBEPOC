package com.enviroapps.eapharmics.vo.security;
import java.util.ArrayList;
import java.util.List;
public class TemplateAccessAreaVO implements java.io.Serializable {
   
   private String appAreaDescription;
   private List modules = new ArrayList();
   
   public TemplateAccessAreaVO() {
      
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
   public List getModules() {
      return modules;
   }

   /**
    * @param modules the modules to set
    */
   public void setModules(List modules) {
      this.modules = modules;
   }

}

