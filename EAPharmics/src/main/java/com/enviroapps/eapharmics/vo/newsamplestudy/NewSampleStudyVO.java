/**
 *
 */
package com.enviroapps.eapharmics.vo.newsamplestudy;
import com.enviroapps.eapharmics.das.persistence.AbstractPersistableObject;
/**
 * @author Ramya
 *
 */

import java.io.Serializable;
//import java.util.Date;



public class NewSampleStudyVO extends AbstractPersistableObject {

   private static final long serialVersionUID = 1L;
   private   Long    batchno;
    private  String  product;
    private  String  strength;
    private  String  batchsize;
    private  String  mfg;
    private  String  exp;;
    private  String  packsize;
    private  String  pkg;
    private  Boolean rDbatch;
    private  Boolean andabatch;
    private  Boolean annualstabilitybatch ;
    private  Boolean validitybatch ;
    private  String  others;
    private  Boolean otherschk;
    private  Boolean initialanalysisyes;
    private  Boolean initialanalysisno ;
    private  Boolean bulk ;
    private  Boolean blend;
    private  Boolean controlled;
    private  Boolean accrelated;
    private  Boolean refri;
    private  Boolean intermediate;
    private  String  mfgpkgsite;
    private  String  apicontrolno ;
    private  String  apimfr;
    private  String  containerdesc ;
    private  String  itemcodecont;
    private  String  closuredesc;
    private  String  itemcodeclosure;
    private  String  cottonedesc;
    private  String  itemcodecotton ;
    private  String  dessiccantsdesc;
    private  String  itemcodedesiccant ;
    private  String  qtysub;
    private  String  submittedby ;
    private  String  submittedbydate;
    private  String  receivedby ;
    private  String  receivedbydate ;

   
    public Serializable getPK() {
      return batchno;
   }

   /* (non-Javadoc)
    * @see com.psc.das.persistence.AbstractPersistableObject#loadPK(java.lang.Object[])
    */
   public void loadPK(Object[] pks) {
      this.batchno = (Long)pks[0];

   }

   /**
    * @return the appUserId
    */
   public Long getBatchno() {
      return batchno;
   }

   /**
    * @param appUserId the appUserId to set
    */
   public void setBatchno(Long batchno) {
      this.batchno = batchno;
   }

   /**
    * @return the product
    */
   public String getProduct() {
      return product;
   }
   /**
    * @param product the product to set
    */
   public void setProduct(String product) {
      this.product = product;
   }
   /**
    * @return the strength
    */
   public String getStrength() {
      return strength;
   }
   /**
    * @param strength the strength to set
    */
   public void setStrength(String strength) {
      this.strength = strength;
   }
   /**
    * @return the batchsize
    */
   public String getBatchsize() {
      return batchsize;
   }
   /**
    * @param batchsize the batchsize to set
    */
   public void setBatchsize(String batchsize) {
      this.batchsize = batchsize;
   }
   /**
    * @return the mfg
    */
   public String getMfg() {
      return mfg;
   }
   /**
    * @param mfg the mfg to set
    */
   public void setMfg(String mfg) {
      this.mfg = mfg;
   }
   /**
    * @return the exp
    */
   public String getExp() {
      return exp;
   }
   /**
    * @param exp the exp to set
    */
   public void setExp(String exp) {
      this.exp = exp;
   }
   /**
    * @return the packsize
    */
   public String getPacksize() {
      return packsize;
   }
   /**
    * @param packsize the packsize to set
    */
   public void setPacksize(String packsize) {
      this.packsize = packsize;
   }
   /**
    * @return the pkg
    */
   public String getPkg() {
      return pkg;
   }
   /**
    * @param pkg the pkg to set
    */
   public void setPkg(String pkg) {
      this.pkg = pkg;
   }
   /**
    * @return the rDbatch
    */
   public Boolean getrDbatch() {
      return rDbatch;
   }
   /**
    * @param dbatch the rDbatch to set
    */
   public void setrDbatch(Boolean rDbatch) {
      this.rDbatch = rDbatch;
   }
   /**
    * @return the andabatch
    */
   public Boolean getAndabatch() {
      return andabatch;
   }
   /**
    * @param andabatch the andabatch to set
    */
   public void setAndabatch(Boolean andabatch) {
      this.andabatch = andabatch;
   }
   /**
    * @return the annualstabilitybatch
    */
   public Boolean getAnnualstabilitybatch() {
      return annualstabilitybatch;
   }
   /**
    * @param annualstabilitybatch the annualstabilitybatch to set
    */
   public void setAnnualstabilitybatch(Boolean annualstabilitybatch) {
      this.annualstabilitybatch = annualstabilitybatch;
   }
   /**
    * @return the validitybatch
    */
   public Boolean getValiditybatch() {
      return validitybatch;
   }
   /**
    * @param validitybatch the validitybatch to set
    */
   public void setValiditybatch(Boolean validitybatch) {
      this.validitybatch = validitybatch;
   }
   /**
    * @return the others
    */
   public String getOthers() {
      return others;
   }
   /**
    * @param others the others to set
    */
   public void setOthers(String others) {
      this.others = others;
   }
   /**
    * @return the otherschk
    */
   public Boolean getOtherschk() {
      return otherschk;
   }
   /**
    * @param otherschk the otherschk to set
    */
   public void setOtherschk(Boolean otherschk) {
      this.otherschk = otherschk;
   }
   /**
    * @return the initialanalysisyes
    */
   public Boolean getInitialanalysisyes() {
      return initialanalysisyes;
   }
   /**
    * @param initialanalysisyes the initialanalysisyes to set
    */
   public void setInitialanalysisyes(Boolean initialanalysisyes) {
      this.initialanalysisyes = initialanalysisyes;
   }
   /**
    * @return the initialanalysisno
    */
   public Boolean getInitialanalysisno() {
      return initialanalysisno;
   }
   /**
    * @param initialanalysisno the initialanalysisno to set
    */
   public void setInitialanalysisno(Boolean initialanalysisno) {
      this.initialanalysisno = initialanalysisno;
   }
   /**
    * @return the bulk
    */
   public Boolean getBulk() {
      return bulk;
   }
   /**
    * @param bulk the bulk to set
    */
   public void setBulk(Boolean bulk) {
      this.bulk = bulk;
   }
   /**
    * @return the blend
    */
   public Boolean getBlend() {
      return blend;
   }
   /**
    * @param blend the blend to set
    */
   public void setBlend(Boolean blend) {
      this.blend = blend;
   }
   /**
    * @return the controlled
    */
   public Boolean getControlled() {
      return controlled;
   }
   /**
    * @param controlled the controlled to set
    */
   public void setControlled(Boolean controlled) {
      this.controlled = controlled;
   }
   /**
    * @return the accrelated
    */
   public Boolean getAccrelated() {
      return accrelated;
   }
   /**
    * @param accrelated the accrelated to set
    */
   public void setAccrelated(Boolean accrelated) {
      this.accrelated = accrelated;
   }
   /**
    * @return the refri
    */
   public Boolean getRefri() {
      return refri;
   }
   /**
    * @param refri the refri to set
    */
   public void setRefri(Boolean refri) {
      this.refri = refri;
   }
   /**
    * @return the intermediate
    */
   public Boolean getIntermediate() {
      return intermediate;
   }
   /**
    * @param intermediate the intermediate to set
    */
   public void setIntermediate(Boolean intermediate) {
      this.intermediate = intermediate;
   }
   /**
    * @return the mfgpkgsite
    */
   public String getMfgpkgsite() {
      return mfgpkgsite;
   }
   /**
    * @param mfgpkgsite the mfgpkgsite to set
    */
   public void setMfgpkgsite(String mfgpkgsite) {
      this.mfgpkgsite = mfgpkgsite;
   }
   /**
    * @return the apicontrolno
    */
   public String getApicontrolno() {
      return apicontrolno;
   }
   /**
    * @param apicontrolno the apicontrolno to set
    */
   public void setApicontrolno(String apicontrolno) {
      this.apicontrolno = apicontrolno;
   }
   /**
    * @return the apimfr
    */
   public String getApimfr() {
      return apimfr;
   }
   /**
    * @param apimfr the apimfr to set
    */
   public void setApimfr(String apimfr) {
      this.apimfr = apimfr;
   }
   /**
    * @return the containerdesc
    */
   public String getContainerdesc() {
      return containerdesc;
   }
   /**
    * @param containerdesc the containerdesc to set
    */
   public void setContainerdesc(String containerdesc) {
      this.containerdesc = containerdesc;
   }
   /**
    * @return the itemcodecont
    */
   public String getItemcodecont() {
      return itemcodecont;
   }
   /**
    * @param itemcodecont the itemcodecont to set
    */
   public void setItemcodecont(String itemcodecont) {
      this.itemcodecont = itemcodecont;
   }
   /**
    * @return the closuredesc
    */
   public String getClosuredesc() {
      return closuredesc;
   }
   /**
    * @param closuredesc the closuredesc to set
    */
   public void setClosuredesc(String closuredesc) {
      this.closuredesc = closuredesc;
   }
   /**
    * @return the itemcodeclosure
    */
   public String getItemcodeclosure() {
      return itemcodeclosure;
   }
   /**
    * @param itemcodeclosure the itemcodeclosure to set
    */
   public void setItemcodeclosure(String itemcodeclosure) {
      this.itemcodeclosure = itemcodeclosure;
   }
   /**
    * @return the cottonedesc
    */
   public String getCottonedesc() {
      return cottonedesc;
   }
   /**
    * @param cottonedesc the cottonedesc to set
    */
   public void setCottonedesc(String cottonedesc) {
      this.cottonedesc = cottonedesc;
   }
   /**
    * @return the itemcodecotton
    */
   public String getItemcodecotton() {
      return itemcodecotton;
   }
   /**
    * @param itemcodecotton the itemcodecotton to set
    */
   public void setItemcodecotton(String itemcodecotton) {
      this.itemcodecotton = itemcodecotton;
   }
   /**
    * @return the dessiccantsdesc
    */
   public String getDessiccantsdesc() {
      return dessiccantsdesc;
   }
   /**
    * @param dessiccantsdesc the dessiccantsdesc to set
    */
   public void setDessiccantsdesc(String dessiccantsdesc) {
      this.dessiccantsdesc = dessiccantsdesc;
   }
   /**
    * @return the itemcodedesiccant
    */
   public String getItemcodedesiccant() {
      return itemcodedesiccant;
   }
   /**
    * @param itemcodedesiccant the itemcodedesiccant to set
    */
   public void setItemcodedesiccant(String itemcodedesiccant) {
      this.itemcodedesiccant = itemcodedesiccant;
   }
   /**
    * @return the qtysub
    */
   public String getQtysub() {
      return qtysub;
   }
   /**
    * @param qtysub the qtysub to set
    */
   public void setQtysub(String qtysub) {
      this.qtysub = qtysub;
   }
   /**
    * @return the submittedby
    */
   public String getSubmittedby() {
      return submittedby;
   }
   /**
    * @param submittedby the submittedby to set
    */
   public void setSubmittedby(String submittedby) {
      this.submittedby = submittedby;
   }
   /**
    * @return the submittedbydate
    */
   public String getSubmittedbydate() {
      return submittedbydate;
   }
   /**
    * @param submittedbydate the submittedbydate to set
    */
   public void setSubmittedbydate(String submittedbydate) {
      this.submittedbydate = submittedbydate;
   }
   /**
    * @return the receivedby
    */
   public String getReceivedby() {
      return receivedby;
   }
   /**
    * @param receivedby the receivedby to set
    */
   public void setReceivedby(String receivedby) {
      this.receivedby = receivedby;
   }

   public String getReceivedbydate() {
      return receivedbydate;
   }
   /**
    * @param receivedby the receivedbydate to set
    */
   public void setReceivedbydate(String receivedbydate) {
      this.receivedbydate = receivedbydate;
   }


}
