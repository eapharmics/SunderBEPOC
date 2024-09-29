package com.enviroapps.eapharmics.ui.pharma;

import java.util.*;



public class DictionaryVO 
{
  private    Long    eadictionarydetailid;
   private   String  dictionarycode;
   private   String  dictionaryvalue;
   private   String  dictionaryvaluedescription;
   private   Long    displayorder;
   private   String  customfield1;
   private   String  customfield2;;
   private   String  graphto;
   private   String  active;
   private   String  comments;
   private   Date    insertdate;
   private  Long  insertuser ;
   private  Date  updatedate ;
   private  Long  updateuser;
   private  Long  auditid;
   private  Date  startdate ;
   private Long locationId;
   


public Long geteadictionarydetailid() {
   return eadictionarydetailid;
}


public void seteadictionarydetailid(Long eadictionarydetailid) {
   this.eadictionarydetailid =eadictionarydetailid;
}

public String getdictionarycode() {
   return dictionarycode;
}


public void setdictionarycode(String dictionarycode) {
   this.dictionarycode =dictionarycode;
}


public String getdictionaryvalue() {
   return dictionaryvalue;
}


public void setdictionaryvalue(String dictionaryvalue) {
   this.dictionaryvalue =dictionaryvalue;
}


public String getdictionaryvaluedescription() {
   return dictionaryvaluedescription;
}


public void setdictionaryvaluedescription(String dictionaryvaluedescription) {
   this.dictionaryvaluedescription =dictionaryvaluedescription;
}


public Long getdisplayorder() {
   return displayorder;
}


public void setdisplayorder(Long displayorder) {
   this.displayorder =displayorder;
}


public String getcustomfield1() {
   return customfield1;
}


public void setcustomfield1(String customfield1) {
   this.customfield1 =customfield1;
}


public String getcustomfield2() {
   return customfield2;
}


public void setcustomfield2(String customfield2) {
   this.customfield2 =customfield2;
}

public String getgraphto() {
   return customfield2;
}


public void setgraphto(String graphto) {
   this.graphto =graphto;
}


public String getactive() {
   return active;
}


public void setactive(String active) {
   this.active =active;
}


public String getcomments() {
   return comments;
}


public void setcomments(String comments) {
   this.comments =comments;
}

public Date getinsertdate() {
   return insertdate;
}


public void setinsertdate(Date insertdate) {
   this.insertdate =insertdate;
}

public Long getinsertuser() {
   return insertuser;
}


public void setinsertuser(Long insertuser) {
   this.insertuser =insertuser;
}


public Date getupdatedate() {
   return updatedate;
}


public void setupdatedate(Date updatedate) {
   this.updatedate =updatedate;
}

public Long getupdateuser() {
   return updateuser;
}


public void setupdateuser(Long updateuser) {
   this.updateuser =updateuser;
}


public Long getauditid() {
   return auditid;
}


public void setauditid(Long auditid) {
   this.auditid =auditid;
}




public Date getstartdate() {
   return startdate;
}


public void setstartdate(Date startdate) {
   this.startdate =startdate;
}
/**
 * @param locationId the locationId to set
 */
public void setLocationId(Long locationId) {
	this.locationId = locationId;
}

/**
 * 
 * @return the locationId
 */
public Long getLocationId() {
	return locationId;
}

}
