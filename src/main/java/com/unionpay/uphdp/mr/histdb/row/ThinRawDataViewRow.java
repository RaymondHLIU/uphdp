package com.unionpay.uphdp.mr.histdb.row;

import java.util.HashMap;
import java.util.Map;

import com.unionpay.uphdp.mr.histdb.convertor.PhysicalTableRowToThinRawDataViewRow;

public class ThinRawDataViewRow extends ViewRow {

	public static final Map<String, Integer> COLNAME_MAP;
	
	static {
		COLNAME_MAP = new HashMap<String, Integer>();
		COLNAME_MAP.put(                   "id",   0);
		// ...
		// add more column name to index mapping here
		// ...
		COLNAME_MAP.put("very_long_column_name", 100);
	}
	
	public ThinRawDataViewRow(PhysicalTableRow sourceRow) {
		super(COLNAME_MAP, sourceRow, PhysicalTableRowToThinRawDataViewRow.INSTANCE);
	}
}
