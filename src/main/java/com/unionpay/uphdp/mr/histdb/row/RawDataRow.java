package com.unionpay.uphdp.mr.histdb.row;

import java.util.HashMap;
import java.util.Map;

import com.unionpay.uphdp.mr.common.BaseRow;

public class RawDataRow extends BaseRow {
	
	public static final Map<String, Integer> COLNAME_MAP;
	
	static {
		COLNAME_MAP = new HashMap<String, Integer>();
		COLNAME_MAP.put(                   "id",   0);
		COLNAME_MAP.put(                 "name",   1);
		// ...
		// add more column name to index mapping here
		// ...
		COLNAME_MAP.put("very_long_column_name", 100);
	}
	
	private String[] fields;
	
	public RawDataRow(String[] fields) {
		super(COLNAME_MAP);
		this.fields = fields;
	}

	@Override
	public Object getValue(int index) {
		return fields[index];
	}
}
