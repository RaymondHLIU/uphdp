package com.unionpay.uphdp.mr.histdb.row;

import java.util.Map;

import com.unionpay.uphdp.mr.common.LazyRow;
import com.unionpay.uphdp.mr.common.RowConvertor;

public abstract class ViewRow extends LazyRow {
	
	public ViewRow(Map<String, Integer> colname2index, PhysicalTableRow sourceRow,
			RowConvertor convertor) {
		super(colname2index, sourceRow, convertor);
	}
}
