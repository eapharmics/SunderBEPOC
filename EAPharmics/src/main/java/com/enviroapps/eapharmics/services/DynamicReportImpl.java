package com.enviroapps.eapharmics.services;

import java.util.HashMap;
import java.util.Map;

import com.enviroapps.eapharmics.common.Utility;
import com.enviroapps.eapharmics.common.services.UtilityServiceFactory;
import com.enviroapps.eapharmics.util.EAReportsHelper;

import ar.com.fdvs.dj.domain.CustomExpression;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;

public class DynamicReportImpl extends AbstractServiceImpl {

	private int totalColumnWidth = 0;

	public int getTotalColumnWidth() {
		return totalColumnWidth;
	}
	
	public void addColumn(FastReportBuilder frb, HashMap columns, String column, String columnTitle, String clazz, int columnWidth) throws Exception {
		if (columns.containsKey(column)) {
			if (clazz.equals("java.lang.Long")) {
				frb.addField(column, clazz);
				frb.addColumn(getLongColumnFromField(columnTitle, column, columnWidth));
			} else if (clazz.equals("java.util.Date")){
				frb.addField(column, clazz);
				frb.addColumn(getDateColumnFromField(columnTitle, column, columnWidth));
			}
			  else {
				frb.addColumn(columnTitle, column,clazz,columnWidth);
			}
		}
		totalColumnWidth += columnWidth;
	}
	

	public void addColumn(FastReportBuilder frb, HashMap columns, String column, String columnTitle, String clazz, int columnWidth, boolean fixedWidth) throws Exception {
		if (columns.containsKey(column)) {
			if (clazz.equals("java.lang.Long")) {
				frb.addField(column, clazz);
				AbstractColumn longColumn = getLongColumnFromField(columnTitle, column, columnWidth);
				longColumn.setFixedWidth(fixedWidth);
				frb.addColumn(longColumn);
			} else if (clazz.equals("java.util.Date")){
				frb.addField(column, clazz);
				AbstractColumn dateColumn = getDateColumnFromField(columnTitle, column, columnWidth);
				dateColumn.setFixedWidth(fixedWidth);
				frb.addColumn(dateColumn);
			}
			else {
				frb.addColumn(columnTitle, column,clazz,columnWidth, fixedWidth);
			}
		}
		totalColumnWidth += columnWidth;
	}

	public AbstractColumn getLongColumnFromField(String columnTitle, final String fieldName, int width) throws Exception {
		ColumnBuilder cb = ColumnBuilder.getNew()
		.setTitle(columnTitle)
		.setWidth(width)
		.setCustomExpression(
		                new CustomExpression() {
		                        public Object evaluate(Map fields, Map variables, Map parameters) {
		                        	Long field = (java.lang.Long)fields.get(fieldName);
		                        	if (field == null) {
		                        		return null;
		                        	}
		                        	return field.toString();
		                        }
		                        public String getClassName() {
		                            return "java.lang.String";
		                        }
		                })
		.setStyle(EAReportsHelper.DETAIL_RIGHT_ALIGN_STYLE);
		return cb.build();
	}

	public AbstractColumn getDateColumnFromField(String columnTitle, final String fieldName, int width) throws Exception {
		final String locale = UtilityServiceFactory.getUserFromFlexSession().getLocale();
		ColumnBuilder cb = ColumnBuilder.getNew()
		.setTitle(columnTitle)
		.setWidth(width)
		.setCustomExpression(
		                new CustomExpression() {
		                        public Object evaluate(Map fields, Map variables, Map parameters) {
		                        	java.util.Date field = (java.util.Date)fields.get(fieldName);
		                        	if (field == null) {
		                        		return null;
		                        	}
		                        	return Utility.getDateStringForLocale(field, locale);
		                        }
		                        public String getClassName() {
		                            return "java.lang.String";
		                        }
		                })
		.setStyle(EAReportsHelper.DETAIL_CENTER_ALIGN_STYLE);
		return cb.build();
	}

	public void addColumn(FastReportBuilder frb, AbstractColumn column) {
		frb.addColumn(column);
		totalColumnWidth += column.getWidth();
	}

}
