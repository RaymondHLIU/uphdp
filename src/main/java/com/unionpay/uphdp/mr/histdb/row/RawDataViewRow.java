package com.unionpay.uphdp.mr.histdb.row;

import java.util.Map;

import com.unionpay.uphdp.mr.histdb.convertor.PhysicalTableRowToRawDataViewRow;

public class RawDataViewRow extends ViewRow {
	
	public static Map<String, Integer> COLNAME_MAP = RawDataRow.COLNAME_MAP;

	public RawDataViewRow(PhysicalTableRow sourceRow) {
		super(COLNAME_MAP, sourceRow, PhysicalTableRowToRawDataViewRow.INSTANCE);
	}
}
