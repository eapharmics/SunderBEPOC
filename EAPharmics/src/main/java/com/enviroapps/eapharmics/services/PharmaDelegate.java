package com.enviroapps.eapharmics.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.enviroapps.eapharmics.exception.EAPharmicsException;
import com.enviroapps.eapharmics.ui.pharma.PharmaVO;

/**
 * @author EnviroApps Ramya Srinivasan
 *
 */
public class PharmaDelegate extends AbstractServiceImpl {
	
	private PharmaImpl impl = null;
	private static final Logger logger = LoggerFactory.getLogger(PharmaDelegate.class);
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
			logger.debug("getAllPharma in Pharma Delegate", "********* getAllPharma *********");
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
   logger.debug("getPackSize in Pharma Delegate", "********* getPackSize *********");
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
   logger.debug("getProduct in Pharma Delegate", "********* getPackSize *********");
   list = impl.getProduct();
   System.out.println("Values returned in PharmaDelegate" + list);
   return list;
   
         } catch (RuntimeException e) {
       }
    return list;
   }
	
	
	
	
	public void savePharma(PharmaVO pharmavo) throws EAPharmicsException {
		try {
			
			logger.debug("About to Begin Transaction in PharmaDelegate", "********* Saving Pharma Through Value Objects In PharmaDelegate *********");
			super.beginTransaction();
			logger.debug("Transaction has began in PharmaDelegate", "********* Saving Pharma Through Value Objects In PharmaDelegate *********");
			impl.savePharma(pharmavo);
			logger.debug("Transaction complete in PharmaDelegate", "********* Saving Pharma Through Value Objects In PharmaDelegate *********");
			super.commitTransaction();
			logger.debug("Commited  the Transaction in PharmaDelegate,Impl ",".");
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
         
         logger.debug("About to Begin Transaction in PharmaDelegate for update", "********* Updating Pharma Through Value Objects In PharmaDelegate *********");
         super.beginTransaction();
         logger.debug("Transaction has began in PharmaDelegate for update", "********* Updating Pharma Through Value Objects In PharmaDelegate *********");
         impl.updatePharma(pharmavo);
         logger.debug("Transaction complete in PharmaDelegate for update", "********* Updating Pharma Through Value Objects In PharmaDelegate *********");
         super.commitTransaction();
         logger.debug("Commited  the Transaction in PharmaDelegate for update ,Impl ",".");
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
