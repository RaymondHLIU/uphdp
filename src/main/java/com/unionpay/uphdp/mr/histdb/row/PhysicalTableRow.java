package com.unionpay.uphdp.mr.histdb.row;

import java.util.HashMap;
import java.util.Map;

import org.apache.hive.hcatalog.data.HCatRecord;

import com.unionpay.uphdp.mr.common.BaseRow;

public class PhysicalTableRow extends BaseRow {
	
	public static Map<String, Integer> COLNAME_MAP;
	
	static {
		COLNAME_MAP = new HashMap<String, Integer>();
		COLNAME_MAP.put(          "id_and_name",   0);
		// ...
		// add more column name to index mapping here
		// ...
		COLNAME_MAP.put("very_long_column_name", 100);
	}
	
	private HCatRecord hcatRecord;
	
	public PhysicalTableRow(HCatRecord hcatRecord) {
		super(COLNAME_MAP);
		this.hcatRecord = hcatRecord;
	}

	@Override
	public Object getValue(int index) {
		return hcatRecord.get(index);
	}
}
