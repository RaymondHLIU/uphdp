package com.unionpay.uphdp.mr.histdb.convertor;

import com.unionpay.uphdp.mr.common.BaseRow;
import com.unionpay.uphdp.mr.common.RowConvertor;
import com.unionpay.uphdp.mr.histdb.row.PhysicalTableRow;
import com.unionpay.uphdp.mr.histdb.row.RawDataViewRow;

public class PhysicalTableRowToRawDataViewRow extends RowConvertor {
	
	public static PhysicalTableRowToRawDataViewRow INSTANCE =
			new PhysicalTableRowToRawDataViewRow();
	
	static {
		// id
		addFieldConvertor("id", new FieldConvertor() {

			@Override
			public Object getValue(BaseRow sourceRow) {
				String idAndName = (String) sourceRow.getValue("id_and_name");
				return idAndName.split("-")[0];
			}

			@Override
			public String getExpression() {
				return "get_id(id_and_name)";
			}
		});
		
		// name
		addFieldConvertor("name", new FieldConvertor() {

			@Override
			public Object getValue(BaseRow sourceRow) {
				String idAndName = (String) sourceRow.getValue("id_and_name");
				return idAndName.split("-")[1];
			}

			@Override
			public String getExpression() {
				return "get_name(id_and_name)";
			}
		});
	}
	
	public PhysicalTableRowToRawDataViewRow() {
		super(PhysicalTableRow.class, RawDataViewRow.class);
	}
}
