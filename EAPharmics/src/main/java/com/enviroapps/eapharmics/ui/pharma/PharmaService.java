/**
 * 
 */
package com.enviroapps.eapharmics.ui.pharma;
import java.util.List;
import java.util.ArrayList;
import com.enviroapps.eapharmics.ui.pharma.PharmaVO;
import com.enviroapps.eapharmics.services.PharmaDelegate;
import com.enviroapps.eapharmics.bom.security.AppUser;
import com.enviroapps.eapharmics.common.services.UtilityServiceFactory;
import com.enviroapps.eapharmics.common.services.logging.ILogger;
import com.enviroapps.eapharmics.exception.EAPharmicsException;
/**
 * @author Ramya
 *
 */
public class PharmaService {
	private ILogger log = UtilityServiceFactory.getLogger();
	
	public List getPharma()throws EAPharmicsException {
		
		try {
			log.debug(this, "getAllPharma in Pharma Service", "********* getAllPharma *********");
			PharmaDelegate del = new PharmaDelegate();
			List list = del.getAllPharma();
			System.out.println("Values returned in PharmaService" + list);
			 return list;
			 
		    } catch (Exception e) {
		    	System.out.println("Errors" + e);
		    	return null;
			}
		
	}
	
	
public List getDictionary(String dictCode)throws EAPharmicsException {
      
      try {
         log.debug(this, "getPackSize in Pharma Service", "********* getAllPharma *********");
         PharmaDelegate del = new PharmaDelegate();
         List list = del.getDictionary(dictCode);
         System.out.println("Values returned in PharmaService" + list);
          return list;
          
          } catch (Exception e) {
            System.out.println("Errors" + e);
            return null;
         }
      
   }
	
	
public List getProduct()throws EAPharmicsException {
   
   try {
      log.debug(this, "getProduct in Pharma Service", "********* getAllPharma *********");
      PharmaDelegate del = new PharmaDelegate();
      List list = del.getProduct();
      System.out.println("Values returned in PharmaService" + list);
       return list;
       
       } catch (Exception e) {
         System.out.println("Errors" + e);
         return null;
      }
   
}
	
	
	
	
	public List insertSampleData(PharmaVO pharmaVO) {
		try {
		   log.debug(this, "Inserting Data in Pharma Service", "********* InsertSampleData *********");
			PharmaDelegate del = new PharmaDelegate();
			del.savePharma(pharmaVO);
			return getPharma();
		} catch (Exception e) {
			//e.printStackTrace();
		}
		//setContext(user);
		return null;
	}
	
	
	public List updateSampleData(PharmaVO pharmaVO) {
      try {
         log.debug(this, "Updating in Pharma Service", "********* updateSampleData *********");
         PharmaDelegate del = new PharmaDelegate();
         del.updatePharma(pharmaVO);
         return getPharma();
      } catch (Exception e) {
         //e.printStackTrace();
      }
      //setContext(user);
      return null;
   }
	
	
	
}
