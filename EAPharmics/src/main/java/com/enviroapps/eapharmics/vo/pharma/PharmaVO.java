/**
 * 
 */
package com.enviroapps.eapharmics.vo.pharma;

/**
 * @author Ramya
 *
 */


public class PharmaVO implements java.io.Serializable {

   private static final long serialVersionUID = 1L;
   private   Long  batch_no;
    private   String product;
    private  String  strength;
    private  String  batch_size;
    private  String  mfg;
    private  String  exp;;
    private  String  packsize;
    private  String  pkg;
    private  Boolean  r_Dbatch;
    private  Boolean  anda_batch;
    private  Boolean  annual_stability_batch ;
    private  Boolean  validity_batch ;
    private  String  others;
    private  Boolean  otherschk;
    private  Boolean  initial_analysis_yes;
    private  Boolean  initial_analysis_no ;
    private  Boolean  bulk ;
    private  Boolean  blend;
    private  Boolean  controlled;
    private  Boolean  accrelated;
    private  Boolean  refri;
    private  Boolean  intermediate;
    private  String  mfgpkgsite;
    private  String  apicontrolno ;
    private  String  apimfr;
    private  String  containerdesc ;
    private  String  itemcode_cont;
    private  String  closuredesc;
    private  String  itemcode_closure;
    private  String  cottonedesc;
    private  String  itemcode_cotton ;
    private  String  dessiccantsdesc;
    private  String  itemcode_desiccant ;
    private  String  qtysub;
    private  String  submittedby ;
    private  String  submittedbydate;
    private  String  receivedby ;
    private  String  receivedbydate ;
    
    /**
    * @return the appUserId
    */
   public Long getBatch_no() {
      return batch_no;
   }

   /**
    * @param appUserId the appUserId to set
    */
   public void setBatch_no(Long batch_no) {
      this.batch_no = batch_no;
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
    * @return the batch_size
    */
   public String getBatch_size() {
      return batch_size;
   }
   /**
    * @param batch_size the batch_size to set
    */
   public void setBatch_size(String batch_size) {
      this.batch_size = batch_size;
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
    * @return the r_Dbatch
    */
   public Boolean getR_Dbatch() {
      return r_Dbatch;
   }
   /**
    * @param dbatch the r_Dbatch to set
    */
   public void setR_Dbatch(Boolean r_Dbatch) {
      this.r_Dbatch = r_Dbatch;
   }
   /**
    * @return the anda_batch
    */
   public Boolean getAnda_batch() {
      return anda_batch;
   }
   /**
    * @param anda_batch the anda_batch to set
    */
   public void setAnda_batch(Boolean anda_batch) {
      this.anda_batch = anda_batch;
   }
   /**
    * @return the annual_stability_batch
    */
   public Boolean getAnnual_stability_batch() {
      return annual_stability_batch;
   }
   /**
    * @param annual_stability_batch the annual_stability_batch to set
    */
   public void setAnnual_stability_batch(Boolean annual_stability_batch) {
      this.annual_stability_batch = annual_stability_batch;
   }
   /**
    * @return the validity_batch
    */
   public Boolean getValidity_batch() {
      return validity_batch;
   }
   /**
    * @param validity_batch the validity_batch to set
    */
   public void setValidity_batch(Boolean validity_batch) {
      this.validity_batch = validity_batch;
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
    * @return the initial_analysis_yes
    */
   public Boolean getInitial_analysis_yes() {
      return initial_analysis_yes;
   }
   /**
    * @param initial_analysis_yes the initial_analysis_yes to set
    */
   public void setInitial_analysis_yes(Boolean initial_analysis_yes) {
      this.initial_analysis_yes = initial_analysis_yes;
   }
   /**
    * @return the initial_analysis_no
    */
   public Boolean getInitial_analysis_no() {
      return initial_analysis_no;
   }
   /**
    * @param initial_analysis_no the initial_analysis_no to set
    */
   public void setInitial_analysis_no(Boolean initial_analysis_no) {
      this.initial_analysis_no = initial_analysis_no;
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
    * @return the itemcode_cont
    */
   public String getItemcode_cont() {
      return itemcode_cont;
   }
   /**
    * @param itemcode_cont the itemcode_cont to set
    */
   public void setItemcode_cont(String itemcode_cont) {
      this.itemcode_cont = itemcode_cont;
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
    * @return the itemcode_closure
    */
   public String getItemcode_closure() {
      return itemcode_closure;
   }
   /**
    * @param itemcode_closure the itemcode_closure to set
    */
   public void setItemcode_closure(String itemcode_closure) {
      this.itemcode_closure = itemcode_closure;
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
    * @return the itemcode_cotton
    */
   public String getItemcode_cotton() {
      return itemcode_cotton;
   }
   /**
    * @param itemcode_cotton the itemcode_cotton to set
    */
   public void setItemcode_cotton(String itemcode_cotton) {
      this.itemcode_cotton = itemcode_cotton;
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
    * @return the itemcode_desiccant
    */
   public String getItemcode_desiccant() {
      return itemcode_desiccant;
   }
   /**
    * @param itemcode_desiccant the itemcode_desiccant to set
    */
   public void setItemcode_desiccant(String itemcode_desiccant) {
      this.itemcode_desiccant = itemcode_desiccant;
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
