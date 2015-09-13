package com.unionpay.uphdp.mr.common;

import java.util.Map;

public class LazyRow extends BaseRow {
	
	private BaseRow sourceRow;
	
	private RowConvertor convertor;

	public LazyRow(Map<String, Integer> colname2index, BaseRow sourceRow,
			RowConvertor convertor) {
		super(colname2index);
		this.sourceRow = sourceRow;
		this.convertor = convertor;
	}

	@Override
	public Object getValue(int index) {
		return convertor.getValue(sourceRow, this, getColname(index));
	}

	@Override
	public String getExpression(int index) {
		return convertor.getExpression(getColname(index));
	}
}
