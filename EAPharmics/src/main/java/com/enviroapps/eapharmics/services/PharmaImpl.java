package com.enviroapps.eapharmics.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.enviroapps.eapharmics.exception.EAPharmicsException;
import com.enviroapps.eapharmics.persistence.PharmaFactory;
import com.enviroapps.eapharmics.ui.pharma.PharmaVO;

/**
 * @author EnviroApps
 *
 */
public class PharmaImpl {

	// Used for logging.
	private static final Logger logger = LoggerFactory.getLogger(PharmaImpl.class);

	private PharmaFactory pharmaFactory = PharmaFactory.getInstance();

	public List getAllPharma()
			throws EAPharmicsException {

		logger.debug("getAllPharma in Pharma Impl", "********* getAllPharma *********");
		List list = pharmaFactory.getAllPharma();
		System.out.println("Values returned in Impl" + list);
		return list;
	}

  public List getDictionary(String dictCode) throws EAPharmicsException
  {
         logger.debug("getPackSize in Pharma Impl", "********* getPackSize *********");
         List list = pharmaFactory.getDictionary(dictCode);
         System.out.println("Values returned in Impl" + list);
         return list;
}

  public List getProduct() throws EAPharmicsException
  {
         logger.debug("getProduct in Pharma Impl", "********* getPackSize *********");
         List list = pharmaFactory.getProduct();
         System.out.println("Values returned in PharmaImpl" + list);
         return list;
}

	//public void savePharma(Pharma pharma )
	//throws EAPharmicsException {
		//logger.debug("savePharma", "********* savePharma *********");

		//pharmaFactory.savePharma(pharma);
	//}


	public void  savePharma(PharmaVO pharmaVO) throws EAPharmicsException {

		logger.debug("PharmaImpl", "********* Saving Pharma Through Value Objects In PharmaImpl *********");
		PharmaVO pharmaimp = new PharmaVO();
		//pharmaimp.setBatch_no(pharmaVO.getBatch_no());
		pharmaimp.setProduct(pharmaVO.getProduct());
		pharmaimp.setStrength(pharmaVO.getStrength());
		pharmaimp.setBatch_size(pharmaVO.getBatch_size());
		pharmaimp.setMfg(pharmaVO.getMfg());
		pharmaimp.setExp(pharmaVO.getExp());
		pharmaimp.setPacksize(pharmaVO.getPacksize());
		pharmaimp.setPkg(pharmaVO.getPkg());
		pharmaimp.setR_Dbatch(pharmaVO.getR_Dbatch());
		pharmaimp.setAnda_batch(pharmaVO.getAnda_batch());
		pharmaimp.setAnnual_stability_batch(pharmaVO.getAnnual_stability_batch());
		pharmaimp.setValidity_batch(pharmaVO.getValidity_batch());
		pharmaimp.setOthers(pharmaVO.getOthers());
		pharmaimp.setOtherschk(pharmaVO.getOtherschk());
		pharmaimp.setInitial_analysis_yes(pharmaVO.getInitial_analysis_yes());
		pharmaimp.setInitial_analysis_no(pharmaVO.getInitial_analysis_no());
		pharmaimp.setBulk(pharmaVO.getBulk());
		pharmaimp.setBlend(pharmaVO.getBlend());
		pharmaimp.setControlled(pharmaVO.getControlled());
		pharmaimp.setAccrelated(pharmaVO.getAccrelated());
		pharmaimp.setRefri(pharmaVO.getRefri());
		pharmaimp.setIntermediate(pharmaVO.getIntermediate());
		pharmaimp.setMfgpkgsite(pharmaVO.getMfgpkgsite());
		pharmaimp.setApicontrolno(pharmaVO.getApicontrolno());
		pharmaimp.setApimfr(pharmaVO.getApimfr());
		pharmaimp.setContainerdesc(pharmaVO.getContainerdesc());
		pharmaimp.setItemcode_cont(pharmaVO.getItemcode_cont());
		pharmaimp.setClosuredesc(pharmaVO.getClosuredesc());
		pharmaimp.setItemcode_closure(pharmaVO.getItemcode_closure());
		pharmaimp.setCottonedesc(pharmaVO.getCottonedesc());
		pharmaimp.setItemcode_cotton(pharmaVO.getItemcode_cotton());
		pharmaimp.setDessiccantsdesc(pharmaVO.getDessiccantsdesc());
		pharmaimp.setItemcode_desiccant(pharmaVO.getItemcode_desiccant());
		pharmaimp.setQtysub(pharmaVO.getQtysub());
		pharmaimp.setSubmittedby(pharmaVO.getSubmittedby());
		pharmaimp.setSubmittedbydate(pharmaVO.getSubmittedbydate());
		pharmaimp.setReceivedby(pharmaVO.getReceivedby());
		pharmaimp.setReceivedbydate(pharmaVO.getReceivedbydate());
		pharmaFactory.savePharma(pharmaimp);
		logger.debug("Gone to PharmaFactory for insert Hibernate actions", ".");
		//return pharma;
	}




	public void  updatePharma(PharmaVO pharmaVO) throws EAPharmicsException {

      logger.debug("PharmaImpl", "********* Updating Pharma Through Value Objects In PharmaImpl *********");
      PharmaVO pharmaimp = new PharmaVO();
      pharmaimp.setBatch_no(pharmaVO.getBatch_no());
      pharmaimp.setProduct(pharmaVO.getProduct());
      pharmaimp.setStrength(pharmaVO.getStrength());
      pharmaimp.setBatch_size(pharmaVO.getBatch_size());
      pharmaimp.setMfg(pharmaVO.getMfg());
      pharmaimp.setExp(pharmaVO.getExp());
      pharmaimp.setPacksize(pharmaVO.getPacksize());
      pharmaimp.setPkg(pharmaVO.getPkg());
      pharmaimp.setR_Dbatch(pharmaVO.getR_Dbatch());
      pharmaimp.setAnda_batch(pharmaVO.getAnda_batch());
      pharmaimp.setAnnual_stability_batch(pharmaVO.getAnnual_stability_batch());
      pharmaimp.setValidity_batch(pharmaVO.getValidity_batch());
      pharmaimp.setOthers(pharmaVO.getOthers());
      pharmaimp.setOtherschk(pharmaVO.getOtherschk());
      pharmaimp.setInitial_analysis_yes(pharmaVO.getInitial_analysis_yes());
      pharmaimp.setInitial_analysis_no(pharmaVO.getInitial_analysis_no());
      pharmaimp.setBulk(pharmaVO.getBulk());
      pharmaimp.setBlend(pharmaVO.getBlend());
      pharmaimp.setControlled(pharmaVO.getControlled());
      pharmaimp.setAccrelated(pharmaVO.getAccrelated());
      pharmaimp.setRefri(pharmaVO.getRefri());
      pharmaimp.setIntermediate(pharmaVO.getIntermediate());
      pharmaimp.setMfgpkgsite(pharmaVO.getMfgpkgsite());
      pharmaimp.setApicontrolno(pharmaVO.getApicontrolno());
      pharmaimp.setApimfr(pharmaVO.getApimfr());
      pharmaimp.setContainerdesc(pharmaVO.getContainerdesc());
      pharmaimp.setItemcode_cont(pharmaVO.getItemcode_cont());
      pharmaimp.setClosuredesc(pharmaVO.getClosuredesc());
      pharmaimp.setItemcode_closure(pharmaVO.getItemcode_closure());
      pharmaimp.setCottonedesc(pharmaVO.getCottonedesc());
      pharmaimp.setItemcode_cotton(pharmaVO.getItemcode_cotton());
      pharmaimp.setDessiccantsdesc(pharmaVO.getDessiccantsdesc());
      pharmaimp.setItemcode_desiccant(pharmaVO.getItemcode_desiccant());
      pharmaimp.setQtysub(pharmaVO.getQtysub());
      pharmaimp.setSubmittedby(pharmaVO.getSubmittedby());
      pharmaimp.setSubmittedbydate(pharmaVO.getSubmittedbydate());
      pharmaimp.setReceivedby(pharmaVO.getReceivedby());
      pharmaimp.setReceivedbydate(pharmaVO.getReceivedbydate());
      pharmaFactory.updatePharma(pharmaimp);
      logger.debug("Gone to PharmaFactory in update for Hibernate actions", ".");
      //return pharma;
   }


}
