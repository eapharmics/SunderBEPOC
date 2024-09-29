package com.enviroapps.eapharmics.reports;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.enviroapps.eapharmics.bom.dictionary.DictionaryDetail;
import com.enviroapps.eapharmics.bom.newstudy.NewStudyProduct;
import com.enviroapps.eapharmics.common.Utility;
import com.enviroapps.eapharmics.common.services.UtilityServiceFactory;
import com.enviroapps.eapharmics.exception.EAPharmicsException;
import com.enviroapps.eapharmics.persistence.DictionaryFactory;
import com.enviroapps.eapharmics.persistence.NewStudyLoginFactory;
import com.enviroapps.eapharmics.vo.newstudy.NewStudySummaryVO;

public class SummaryReportHeader {
	private int rowNumber;
	private String headerLabel;
	private String headerDescription;
	private Boolean showOnAllPages = false;

	public SummaryReportHeader() {		
	}

	public SummaryReportHeader(String headerLabel, String headerDescription, int rowNumber, Boolean showOnAllPages) {
		this.setHeaderLabel(headerLabel);
		this.setHeaderDescription(headerDescription);
		this.setRowNumber(rowNumber);
		this.setShowOnAllPages(showOnAllPages);
	}

	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}

	public int getRowNumber() {
		return rowNumber;
	}

	public void setHeaderLabel(String headerLabel) {
		this.headerLabel = headerLabel;
	}


	public String getHeaderLabel() {
		return headerLabel;
	}


	public void setHeaderDescription(String headerDescription) {
		this.headerDescription = headerDescription;
	}


	public String getHeaderDescription() {
		return headerDescription;
	}

	/**
	 * @return the showOnAllPages
	 */
	public Boolean getShowOnAllPages() {
		return showOnAllPages;
	}

	/**
	 * @param showOnAllPages the showOnAllPages to set
	 */
	public void setShowOnAllPages(Boolean showOnAllPages) {
		this.showOnAllPages = showOnAllPages;
	}

	public static List<List> getHeaders(String studyId, HashMap<String, Boolean> reportSettings) throws EAPharmicsException {
		
		List<SummaryReportHeader> headers = new ArrayList<SummaryReportHeader>();
		List<SummaryReportHeader> headersAllPages = new ArrayList<SummaryReportHeader>();
		String locale = UtilityServiceFactory.getUserFromFlexSession().getLocale();
		
		try {
			
			List<DictionaryDetail> userDefinedDictionary = DictionaryFactory.getInstance().getDictionaryDetailForCode("USER DEFINED FIELDS");
			HashMap<Integer, String> userdefinedColumns = new HashMap<Integer, String>();
			int i = 1;
			for (Iterator iterator = userDefinedDictionary.iterator(); iterator.hasNext();) {
				DictionaryDetail dictionary = (DictionaryDetail) iterator.next();
				userdefinedColumns.put(i, dictionary.getDictionaryValueDescription() + ":");
				i++;
			}
			NewStudySummaryVO summary = NewStudyLoginFactory.getInstance().getNewStudySummaryForReport(studyId);
			//NewStudyProduct product = NewStudyLoginFactory.getInstance().getProductForStudy(studyId);

			headersAllPages.add(new SummaryReportHeader("Product:", summary.getProductName(), 1, true));
			headersAllPages.add(new SummaryReportHeader("Description:", summary.getProductDescription(), 2, true));
			
			setHeader(headers, headersAllPages, reportSettings, "P_PRODUCT_CODE", "Code:", summary.getProductCode(), true);
			setHeader(headers, headersAllPages, reportSettings, "P_STUDY_ID", "ID #:", studyId, true);
			setHeader(headers, headersAllPages, reportSettings, "P_LOT_NUMBER", "Lot#:", summary.getLotNumber(), true);
			setHeader(headers, headersAllPages, reportSettings, "P_EXPIRY", "Expiry:", summary.getExpiry(), false);
			setHeader(headers, headersAllPages, reportSettings, "P_FORMULA", "Formula:", summary.getFormula(), false);
			setHeader(headers, headersAllPages, reportSettings, "P_BATCHSIZE", "Batch Size:", summary.getBatchSize(), false);
			setHeader(headers, headersAllPages, reportSettings, "P_MFG_DATE", "Manufactured:", Utility.getDateStringForLocale(summary.getManufacturingDate(), locale), false);
			setHeader(headers, headersAllPages, reportSettings, "P_PACKAGE_DATE", "Packaged:", Utility.getDateStringForLocale(summary.getPackageDate(), locale), false);
			setHeader(headers, headersAllPages, reportSettings, "P_STABILITY_DATE", "On Stability:", Utility.getDateStringForLocale(summary.getOnStabilityDate(), locale), false);
			setHeader(headers, headersAllPages, reportSettings, "P_CONDITION", "Condition:", summary.getStudyCondition(), true);
			setHeader(headers, headersAllPages, reportSettings, "P_REASON", "Reason:", summary.getStudyReasonDescription(), true);
			
			setHeader(headers, headersAllPages, reportSettings, "P_MFG_SITE", "Mfg. Site:", summary.getManufacturingSiteCode(), false);
			setHeader(headers, headersAllPages, reportSettings, "P_PKG_SITE", "Pkg. Site:", summary.getPackagingSiteCode(), false);
			
			setHeader(headers, headersAllPages, reportSettings, "P_FILL", "Fill:", summary.getFill(), true);
			setHeader(headers, headersAllPages, reportSettings, "P_CONTAINER", "Container:", summary.getContainerCode(), true);
			setHeader(headers, headersAllPages, reportSettings, "P_CONTAINER_MATERIAL", "Container Material:", summary.getContainerResinCode(), false);
			setHeader(headers, headersAllPages, reportSettings, "P_CONTAINER_MFG", "Container Mfg.:", summary.getContainerManufacturerCode(), false);
			setHeader(headers, headersAllPages, reportSettings, "P_CLOSURE", "Closure:", summary.getClosure(), false);
			setHeader(headers, headersAllPages, reportSettings, "P_CLOSURE_MFG", "Closure Mfg.:", summary.getClosureManufacturerCode(), false);
			setHeader(headers, headersAllPages, reportSettings, "P_INNER_SEAL", "Inner Seal:", summary.getInnersealCode(), false);
			setHeader(headers, headersAllPages, reportSettings, "P_LINER", "Liner:", summary.getLinerCode(), false);
			setHeader(headers, headersAllPages, reportSettings, "P_LINER_MFG", "Liner Mfg.:", summary.getLinerManufacturerCode() , false);
			setHeader(headers, headersAllPages, reportSettings, "P_FILLER", "Filler:", summary.getFillerCode(), false);
			setHeader(headers, headersAllPages, reportSettings, "P_FILLER_MFG", "Filler Mfg.:", summary.getFillerManufacturerCode() , false);
			setHeader(headers, headersAllPages, reportSettings, "P_OTHER", "Other:", summary.getOtherCode1() , false);
			setHeader(headers, headersAllPages, reportSettings, "P_OTHER_MFG", "Other Mfg.:", summary.getOtherManufacturerCode1(), false);
			setHeader(headers, headersAllPages, reportSettings, "P_OTHER2", "Other (2):", summary.getOtherCode2(), false);
			setHeader(headers, headersAllPages, reportSettings, "P_OTHER2_MFG", "Other (2) Mfg.:", summary.getOtherManufacturerCode2(), false);
			
			setHeader(headers, headersAllPages, reportSettings, "P_USER_DEFINED_COLUMN1", userdefinedColumns.get(1), summary.getUserDefinedColumn1(), false);
			setHeader(headers, headersAllPages, reportSettings, "P_USER_DEFINED_COLUMN2", userdefinedColumns.get(2), summary.getUserDefinedColumn2(), false);
			setHeader(headers, headersAllPages, reportSettings, "P_USER_DEFINED_COLUMN3", userdefinedColumns.get(3), summary.getUserDefinedColumn3(), false);
			setHeader(headers, headersAllPages, reportSettings, "P_USER_DEFINED_COLUMN4", userdefinedColumns.get(4), summary.getUserDefinedColumn4(), false);
			setHeader(headers, headersAllPages, reportSettings, "P_USER_DEFINED_COLUMN5", userdefinedColumns.get(5), summary.getUserDefinedColumn5(), false);
			setHeader(headers, headersAllPages, reportSettings, "P_USER_DEFINED_COLUMN6", userdefinedColumns.get(6), summary.getUserDefinedColumn6(), false);
			setHeader(headers, headersAllPages, reportSettings, "P_USER_DEFINED_COLUMN7", userdefinedColumns.get(7), summary.getUserDefinedColumn7(), false);
			setHeader(headers, headersAllPages, reportSettings, "P_USER_DEFINED_COLUMN8", userdefinedColumns.get(8), summary.getUserDefinedColumn8(), false);
			setHeader(headers, headersAllPages, reportSettings, "P_USER_DEFINED_COLUMN9", userdefinedColumns.get(9), summary.getUserDefinedColumn9(), false);
			setHeader(headers, headersAllPages, reportSettings, "P_USER_DEFINED_COLUMN10", userdefinedColumns.get(10), summary.getUserDefinedColumn10(), false);

			setRowNumbers(headers);
			setRowNumbers(headersAllPages);
			
		} catch (Throwable th) {
			th.printStackTrace();
		}
		
		List<List> headersList = new ArrayList<List>();
		headersList.add(headers);
		headersList.add(headersAllPages);

		return headersList;
	}


	private static void setHeader(List<SummaryReportHeader> headers,
			List<SummaryReportHeader> headersAllPages,
			HashMap<String, Boolean> reportSettings, String reportSetting,
			String label, String description, Boolean showOnAllPages) {
		Boolean visible = reportSettings.get(reportSetting);		
		if (visible != null && description != null && visible == true ) {
			int rowNumber = headers.size();
			headers.add(new SummaryReportHeader(label, description, rowNumber++, showOnAllPages));
			if (showOnAllPages) {
				rowNumber = headersAllPages.size();
				headersAllPages.add(new SummaryReportHeader(label, description, rowNumber++, showOnAllPages));
			}
		}
	}

	private static void setRowNumbers(List<SummaryReportHeader> headers) {
		int totalRows = headers.size();
		int maxRows = (int) Math.ceil((float)totalRows/2.0);
		for(int i = 0; i < totalRows; i++) {
			headers.get(i).setRowNumber(i <= maxRows ? 1 : 2);
		}
	}


}
