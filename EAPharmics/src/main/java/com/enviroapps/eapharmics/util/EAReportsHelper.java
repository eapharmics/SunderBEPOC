package com.enviroapps.eapharmics.util;

import java.awt.Color;

import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Stretching;
import ar.com.fdvs.dj.domain.constants.Transparency;

public class EAReportsHelper {
	public static final String REPORTS_BASE_DIR = System.getProperty("application.reports.location");
	public static final String REPORTS_BASE_URL = "/servlet/ReportsServlet?reportOutput=";
	public static final String REPORTS_OUTPUT_DIR = REPORTS_BASE_DIR + "/output";;

	public static final Style TITLE_STYLE = getTitleStyle();
	public static final Style SUB_TITLE_STYLE = getSubTitleStyle();
	public static final Style COLUMN_HEADER_STYLE = getColumnHeaderStyle();
	public static final Style COLUMN_DETAIL_STYLE = getColumnDetailStyle();
	public static final Style DETAIL_RIGHT_ALIGN_STYLE = getDetailRightAlignStyle();
	public static final Style DETAIL_CENTER_ALIGN_STYLE = getDetailCenterAlignStyle();

	
	private static Style getTitleStyle() {
		Style titleStyle = Style.createBlankStyle("titleStyle");
		titleStyle.setFont((new Font(9, "Arial", true)));
		titleStyle.setStreching(Stretching.RELATIVE_TO_TALLEST_OBJECT);
		titleStyle.setStretchWithOverflow(true);
		titleStyle.setHorizontalAlign(HorizontalAlign.LEFT);
		return TITLE_STYLE;
	}

	private static Style getSubTitleStyle() {
		Style subTitleStyle = Style.createBlankStyle("subTitleStyle");
		subTitleStyle.setFont((new Font(9, "Arial", true)));
		return subTitleStyle;
	}

	private static Style getColumnHeaderStyle() {
		Style columnHeaderStyle = Style.createBlankStyle("columnHeaderStyle");
		columnHeaderStyle.setFont((new Font(9, "Arial", true)));
		columnHeaderStyle.setHorizontalAlign(HorizontalAlign.CENTER);
		columnHeaderStyle.setBorderColor(Color.BLACK);
		columnHeaderStyle.setTransparency(Transparency.OPAQUE);
		columnHeaderStyle.setBackgroundColor(new Color(250, 250, 204)); // Yellow
		columnHeaderStyle.setBorder(Border.PEN_1_POINT());
		columnHeaderStyle.setStreching(Stretching.RELATIVE_TO_TALLEST_OBJECT);
		columnHeaderStyle.setPaddingBottom(1);
		columnHeaderStyle.setPaddingLeft(1);
		columnHeaderStyle.setPaddingRight(1);
		columnHeaderStyle.setPaddingTop(1);
		return columnHeaderStyle;
	}

	private static Style getColumnDetailStyle() {
		Style columnDetailStyle = Style.createBlankStyle("columnDetailStyle");
		columnDetailStyle.setFont((new Font(9, "Arial", false)));
		columnDetailStyle.setBorderColor(Color.BLACK);
		columnDetailStyle.setBorder(Border.THIN());
		columnDetailStyle.setStreching(Stretching.RELATIVE_TO_TALLEST_OBJECT);
		columnDetailStyle.setPaddingBottom(1);
		columnDetailStyle.setPaddingLeft(1);
		columnDetailStyle.setPaddingRight(1);
		columnDetailStyle.setPaddingTop(1);
		return columnDetailStyle;
	}

	private static Style getDetailRightAlignStyle() {
		Style style = getColumnDetailStyle();
		style.setName("detailRightAlignStyle");
		style.setHorizontalAlign(HorizontalAlign.RIGHT);
		return style;
	}

	private static Style getDetailCenterAlignStyle() {
		Style style = getColumnDetailStyle();
		style.setName("detailRightAlignStyle");
		style.setHorizontalAlign(HorizontalAlign.CENTER);
		return style;
	}
	
	public static String getTemplateFile(int totalColumnWidth) {

//		if (totalColumnWidth <= 792) {
//			return REPORTS_BASE_DIR + "/ea_landscapeT.jrxml";
//		} else if (totalColumnWidth <= 1008) {
//			return REPORTS_BASE_DIR + "/ea_landscape_legalT.jrxml";
//		}
		return REPORTS_BASE_DIR + "/ea_landscape_ledgerT.jrxml";
	}

}
