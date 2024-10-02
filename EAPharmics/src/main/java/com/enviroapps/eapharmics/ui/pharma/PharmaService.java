/**
 * 
 */
package com.enviroapps.eapharmics.ui.pharma;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.enviroapps.eapharmics.exception.EAPharmicsException;
import com.enviroapps.eapharmics.services.PharmaDelegate;
/**
 * @author Ramya
 *
 */
public class PharmaService {
	
	private static final Logger logger = LoggerFactory.getLogger(PharmaService.class);
	
	public List getPharma()throws EAPharmicsException {
		
		try {
			logger.debug("getAllPharma in Pharma Service", "********* getAllPharma *********");
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
         logger.debug("getPackSize in Pharma Service", "********* getAllPharma *********");
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
      logger.debug("getProduct in Pharma Service", "********* getAllPharma *********");
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
		   logger.debug("Inserting Data in Pharma Service", "********* InsertSampleData *********");
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
         logger.debug("Updating in Pharma Service", "********* updateSampleData *********");
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
