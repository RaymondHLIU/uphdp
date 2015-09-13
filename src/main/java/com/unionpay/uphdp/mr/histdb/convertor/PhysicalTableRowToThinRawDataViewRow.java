package com.unionpay.uphdp.mr.histdb.convertor;

import com.unionpay.uphdp.mr.common.RowConvertor;
import com.unionpay.uphdp.mr.histdb.row.PhysicalTableRow;
import com.unionpay.uphdp.mr.histdb.row.ThinRawDataViewRow;

public class PhysicalTableRowToThinRawDataViewRow extends RowConvertor {
	
	public static PhysicalTableRowToThinRawDataViewRow INSTANCE =
			new PhysicalTableRowToThinRawDataViewRow();

	public PhysicalTableRowToThinRawDataViewRow() {
		super(PhysicalTableRow.class, ThinRawDataViewRow.class);
	}
}