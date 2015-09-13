package com.unionpay.uphdp.mr.common;

import java.util.HashMap;
import java.util.Map;

public abstract class RowConvertor {
	
	public static abstract class FieldConvertor {
		public abstract Object getValue(BaseRow sourceRow);
		public abstract String getExpression();
	}

	private static Map<String, FieldConvertor> registeredFieldConvertors =
			new HashMap<String, FieldConvertor>();

	public static void addFieldConvertor(String colname,
			FieldConvertor fieldConvertor) {
		registeredFieldConvertors.put(colname, fieldConvertor);
	}
	
	public static FieldConvertor getFieldConvertor(String colname) {
		return registeredFieldConvertors.get(colname);
	}

	@SuppressWarnings("unused")
	private Class<? extends BaseRow> sourceRowClass, targetRowClass;

	public RowConvertor(Class<? extends BaseRow> sourceRowClass,
			Class<? extends BaseRow> targetRowClass) {
		this.sourceRowClass = sourceRowClass;
		this.targetRowClass = targetRowClass;
	}

	public Object getValue(BaseRow sourceRow, BaseRow targetRow,
			String colname) {
		FieldConvertor fieldConvertor = getFieldConvertor(colname);
		if (fieldConvertor != null)
			return fieldConvertor.getValue(sourceRow);
		else
			return sourceRow.getValue(colname);
	}
	
	public String getExpression(String colname) {
		FieldConvertor fieldConvertor = getFieldConvertor(colname);
		if (fieldConvertor != null)
			return fieldConvertor.getExpression();
		else
			return colname;
	}
}
