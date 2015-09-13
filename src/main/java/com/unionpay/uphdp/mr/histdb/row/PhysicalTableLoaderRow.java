package com.unionpay.uphdp.mr.histdb.row;
import java.util.Map;

import com.unionpay.uphdp.mr.common.LazyRow;
import com.unionpay.uphdp.mr.histdb.convertor.RawDataRowToPhysicalTableLoaderRow;

public class PhysicalTableLoaderRow extends LazyRow {

	public static Map<String, Integer> COLNAME_MAP = PhysicalTableRow.COLNAME_MAP;
	
	public PhysicalTableLoaderRow(RawDataRow sourceRow) {
		super(COLNAME_MAP, sourceRow, RawDataRowToPhysicalTableLoaderRow.INSTANCE);
	}
}