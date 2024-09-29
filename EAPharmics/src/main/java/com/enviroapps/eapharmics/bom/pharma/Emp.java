package com.enviroapps.eapharmics.bom.pharma;

import java.io.Serializable;
import com.enviroapps.eapharmics.das.persistence.AbstractPersistableObject;


public class Emp extends AbstractPersistableObject
{

   private static final long serialVersionUID = 1L;

   private Long empno;
   private String empname;
   private String empdesc;
   
   public Serializable getPK() {
      return empno;
   }

   /* (non-Javadoc)
    * @see com.enviroapps.das.persistence.AbstractPersistableObject#loadPK(java.lang.Object[])
    */
   public void loadPK(Object[] pks) {
      this.empno = (Long)pks[0];

   }
   
   
   /**
    * @return the empno
    */
   public Long getEmpno()
   {
      return empno;
   }

   /**
    * @param empno the empno to set
    */
   public void setEmpno(Long empno)
   {
      this.empno = empno;
   }

   /**
    * @return the empname
    */
   public String getEmpname()
   {
      return empname;
   }

   /**
    * @param empname the empname to set
    */
   public void setEmpname(String empname)
   {
      this.empname = empname;
   }

   /**
    * @return the empdesc
    */
   public String getEmpdesc()
   {
      return empdesc;
   }

   /**
    * @param empdesc the empdesc to set
    */
   public void setEmpdesc(String empdesc)
   {
      this.empdesc = empdesc;
   }

   
   
      
   
   
}
