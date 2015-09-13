package com.unionpay.uphdp.mr.histdb.convertor;

import com.unionpay.uphdp.mr.common.BaseRow;
import com.unionpay.uphdp.mr.common.RowConvertor;
import com.unionpay.uphdp.mr.histdb.row.PhysicalTableRow;
import com.unionpay.uphdp.mr.histdb.row.RawDataRow;

public class RawDataRowToPhysicalTableLoaderRow extends RowConvertor {
	
	public static RawDataRowToPhysicalTableLoaderRow INSTANCE = 
			new RawDataRowToPhysicalTableLoaderRow();
	
	static {
		//TODO: *****************add all to loader
		// id_and_name
		addFieldConvertor("id_and_name", new FieldConvertor() {

			@Override
			public Object getValue(BaseRow sourceRow) {
				String id = (String) sourceRow.getValue("id");
				String name = (String) sourceRow.getValue("name");
				return String.format("%s-%s", id, name);
			}

			@Override
			public String getExpression() {
				return "concat(id, '-', name)";
			}
		});
		
		// name_and_id
		addFieldConvertor("name_and_id", new FieldConvertor() {

			@Override
			public Object getValue(BaseRow sourceRow) {
				String id = (String) sourceRow.getValue("id");
				String name = (String) sourceRow.getValue("name");
				return String.format("%s-%s", name, id);
			}

			@Override
			public String getExpression() {
				return "concat(name, '-', id)";
			}
		});
	}
	
	public RawDataRowToPhysicalTableLoaderRow(){
		super(RawDataRow.class, PhysicalTableRow.class);
	}
}
