package com.unionpay.uphdp.mr.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public abstract class BaseRow {
	
	private Map<String, Integer> colname2index;
	
	private Map<Integer, String> index2colname;
	
	public BaseRow(Map<String, Integer> colname2index) {
		this.colname2index = colname2index;
		
		index2colname = new HashMap<Integer, String>();
		for (Entry<String, Integer> entry : colname2index.entrySet())
			index2colname.put(entry.getValue(), entry.getKey());
	}

	public abstract Object getValue(int index);
	
	public Object getValue(String colname) {
		return getValue(getIndex(colname));
	}
	
	public String getExpression(int index) {
		return getColname(index);
	}
	
	public String getExpression(String colname) {
		return getExpression(getIndex(colname));
	}
	
	public int getNumOfColumns() {
		return colname2index.size();
	}
	
	public int getIndex(String colname) {
		return colname2index.get(colname);
	}
	
	public String getColname(int index) {
		return index2colname.get(index);
	}
}
