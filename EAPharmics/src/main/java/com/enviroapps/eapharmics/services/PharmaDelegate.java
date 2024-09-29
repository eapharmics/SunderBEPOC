package com.enviroapps.eapharmics.services;

import java.util.ArrayList;
import java.util.List;
import com.enviroapps.eapharmics.common.services.UtilityServiceFactory;
import com.enviroapps.eapharmics.common.services.logging.ILogger;
import com.enviroapps.eapharmics.exception.EAPharmicsException;
import com.enviroapps.eapharmics.ui.pharma.PharmaVO;

/**
 * @author EnviroApps Ramya Srinivasan
 *
 */
public class PharmaDelegate extends AbstractServiceImpl {
	
	private PharmaImpl impl = null;
	private ILogger log = UtilityServiceFactory.getLogger();
	/**
	 * @param useLocal
	 */
	public PharmaDelegate () throws EAPharmicsException {
		super();
		impl = new PharmaImpl();
	}

	/**
	 * @param userId
	 * @param password
	 * @return
	 * @throws EAPharmcisException
	 */
	public List getAllPharma()
			throws EAPharmicsException {
		List list = new ArrayList();
		try {
			log.debug(this, "getAllPharma in Pharma Delegate", "********* getAllPharma *********");
			list = impl.getAllPharma();
			System.out.println("Values returned in PharmaImpl" + list);
			return list;
			
		} catch (RuntimeException e) {
		}
		return list;
	}

	public List getDictionary(String dictCode)
   throws EAPharmicsException {
     List list = new ArrayList();
        try {
   log.debug(this, "getPackSize in Pharma Delegate", "********* getPackSize *********");
   list = impl.getDictionary(dictCode);
   System.out.println("Values returned in PharmaImpl" + list);
   return list;
   
         } catch (RuntimeException e) {
       }
    return list;
   }
	
	
	public List getProduct()
   throws EAPharmicsException {
     List list = new ArrayList();
        try {
   log.debug(this, "getProduct in Pharma Delegate", "********* getPackSize *********");
   list = impl.getProduct();
   System.out.println("Values returned in PharmaDelegate" + list);
   return list;
   
         } catch (RuntimeException e) {
       }
    return list;
   }
	
	
	
	
	public void savePharma(PharmaVO pharmavo) throws EAPharmicsException {
		try {
			
			log.debug(this, "About to Begin Transaction in PharmaDelegate", "********* Saving Pharma Through Value Objects In PharmaDelegate *********");
			super.beginTransaction();
			log.debug(this, "Transaction has began in PharmaDelegate", "********* Saving Pharma Through Value Objects In PharmaDelegate *********");
			impl.savePharma(pharmavo);
			log.debug(this, "Transaction complete in PharmaDelegate", "********* Saving Pharma Through Value Objects In PharmaDelegate *********");
			super.commitTransaction();
			log.debug(this, "Commited  the Transaction in PharmaDelegate,Impl ",".");
			//return pharma;
		} catch (Exception e) {
			logException("createPharma", e);
			try {
				super.rollbackTransaction();
			} catch(Exception e1) {
				logException("createPharma", e1);
			}
			throw new EAPharmicsException(e);
		}
	}
	
	
	
	public void updatePharma(PharmaVO pharmavo) throws EAPharmicsException {
      try {
         
         log.debug(this, "About to Begin Transaction in PharmaDelegate for update", "********* Updating Pharma Through Value Objects In PharmaDelegate *********");
         super.beginTransaction();
         log.debug(this, "Transaction has began in PharmaDelegate for update", "********* Updating Pharma Through Value Objects In PharmaDelegate *********");
         impl.updatePharma(pharmavo);
         log.debug(this, "Transaction complete in PharmaDelegate for update", "********* Updating Pharma Through Value Objects In PharmaDelegate *********");
         super.commitTransaction();
         log.debug(this, "Commited  the Transaction in PharmaDelegate for update ,Impl ",".");
         //return pharma;
      } catch (Exception e) {
         logException("updatePharma", e);
         try {
            super.rollbackTransaction();
         } catch(Exception e1) {
            logException("updatePharma", e1);
         }
         throw new EAPharmicsException(e);
      }
   }
   
	
	
	
	
}
