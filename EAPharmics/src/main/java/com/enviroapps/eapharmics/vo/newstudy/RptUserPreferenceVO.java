package com.enviroapps.eapharmics.vo.newstudy;

import java.util.Date;

public class RptUserPreferenceVO {

	private static final long serialVersionUID = 1L;

	private Long rptUserPreferenceId;
	private Long reportColumnId;
	private String reportGroupName;
	private String reportName;
	private String fieldName;
	private String fieldHeader;
	private Long displayOrder;
	private boolean active;
	private Long appUserId;
	private Date updateDate;
	private Long updateUser;
	private Long auditId;
	private String orderBy;
	private Long orderByPosition;
	private boolean displayInSelectionScreen;
	private String sortFieldName;
	private boolean activeField;
	private String alternateFieldName;
	private String fieldDataType;
	private String fieldValue;
	private Long locationId;
	/**
	 * @return the rptUserPreferenceId
	 */
	public Long getRptUserPreferenceId() {
		return rptUserPreferenceId;
	}
	/**
	 * @param rptUserPreferenceId the rptUserPreferenceId to set
	 */
	public void setRptUserPreferenceId(Long rptUserPreferenceId) {
		this.rptUserPreferenceId = rptUserPreferenceId;
	}
	/**
	 * @return the reportColumnId
	 */
	public Long getReportColumnId() {
		return reportColumnId;
	}
	/**
	 * @param reportColumnId the reportColumnId to set
	 */
	public void setReportColumnId(Long reportColumnId) {
		this.reportColumnId = reportColumnId;
	}
	/**
	 * @return the reportGroupName
	 */
	public String getReportGroupName() {
		return reportGroupName;
	}
	/**
	 * @param reportGroupName the reportGroupName to set
	 */
	public void setReportGroupName(String reportGroupName) {
		this.reportGroupName = reportGroupName;
	}
	/**
	 * @return the reportName
	 */
	public String getReportName() {
		return reportName;
	}
	/**
	 * @param reportName the reportName to set
	 */
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	/**
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}
	/**
	 * @param fieldName the fieldName to set
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	/**
	 * @return the fieldHeader
	 */
	public String getFieldHeader() {
		return fieldHeader;
	}
	/**
	 * @param fieldHeader the fieldHeader to set
	 */
	public void setFieldHeader(String fieldHeader) {
		this.fieldHeader = fieldHeader;
	}
	/**
	 * @return the displayOrder
	 */
	public Long getDisplayOrder() {
		return displayOrder;
	}
	/**
	 * @param displayOrder the displayOrder to set
	 */
	public void setDisplayOrder(Long displayOrder) {
		this.displayOrder = displayOrder;
	}
	/**
	 * @return the active
	 */
	public boolean getActive() {
		return active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	/**
	 * @return the appUserId
	 */
	public Long getAppUserId() {
		return appUserId;
	}
	/**
	 * @param appUserId the appUserId to set
	 */
	public void setAppUserId(Long appUserId) {
		this.appUserId = appUserId;
	}
	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}
	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * @return the updateUser
	 */
	public Long getUpdateUser() {
		return updateUser;
	}
	/**
	 * @param updateUser the updateUser to set
	 */
	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}
	/**
	 * @return the auditId
	 */
	public Long getAuditId() {
		return auditId;
	}
	/**
	 * @param auditId the auditId to set
	 */
	public void setAuditId(Long auditId) {
		this.auditId = auditId;
	}
   /**
    * @return the orderBy
    */
   public String getOrderBy()
   {
      return orderBy;
   }
   /**
    * @param orderBy the orderBy to set
    */
   public void setOrderBy(String orderBy)
   {
      this.orderBy = orderBy;
   }
   /**
    * @return the orderByPosition
    */
   public Long getOrderByPosition()
   {
      return orderByPosition;
   }
   /**
    * @param orderByPosition the orderByPosition to set
    */
   public void setOrderByPosition(Long orderByPosition)
   {
      this.orderByPosition = orderByPosition;
   }
   /**
    * @return the displayInSelectionScreen
    */
   public boolean isDisplayInSelectionScreen()
   {
      return displayInSelectionScreen;
   }
   /**
    * @param displayInSelectionScreen the displayInSelectionScreen to set
    */
   public void setDisplayInSelectionScreen(boolean displayInSelectionScreen)
   {
      this.displayInSelectionScreen = displayInSelectionScreen;
   }
   /**
    * @return the sortFieldName
    */
   public String getSortFieldName()
   {
      return sortFieldName;
   }
   /**
    * @return the activeField
    */
   public boolean isActiveField()
   {
      return activeField;
   }
   /**
    * @param sortFieldName the sortFieldName to set
    */
   public void setSortFieldName(String sortFieldName)
   {
      this.sortFieldName = sortFieldName;
   }
   /**
    * @param activeField the activeField to set
    */
   public void setActiveField(boolean activeField)
   {
      this.activeField = activeField;
   }
   /**
    * @return the alternateFieldName
    */
   public String getAlternateFieldName()
   {
      return alternateFieldName;
   }
   /**
    * @return the fieldDataType
    */
   public String getFieldDataType()
   {
      return fieldDataType;
   }
   /**
    * @return the fieldValue
    */
   public String getFieldValue()
   {
      return fieldValue;
   }
   /**
    * @param alternateFieldName the alternateFieldName to set
    */
   public void setAlternateFieldName(String alternateFieldName)
   {
      this.alternateFieldName = alternateFieldName;
   }
   /**
    * @param fieldDataType the fieldDataType to set
    */
   public void setFieldDataType(String fieldDataType)
   {
      this.fieldDataType = fieldDataType;
   }
   /**
    * @param fieldValue the fieldValue to set
    */
   public void setFieldValue(String fieldValue)
   {
      this.fieldValue = fieldValue;
   }
   /**
    * @return the locationId
    */
   public Long getLocationId()
   {
      return locationId;
   }
   /**
    * @param locationId the locationId to set
    */
   public void setLocationId(Long locationId)
   {
      this.locationId = locationId;
   }
}
