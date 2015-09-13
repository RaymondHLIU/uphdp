package com.unionpay.uphdp.test;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hive.hcatalog.data.DefaultHCatRecord;
import org.apache.hive.hcatalog.data.HCatRecord;

import com.unionpay.uphdp.mr.histdb.row.PhysicalTableRow;
import com.unionpay.uphdp.mr.histdb.row.RawDataViewRow;

public class PhysicalTableToRawDataViewMapper 
	extends Mapper<WritableComparable<?>, HCatRecord, Text, HCatRecord>{
	
	String id;

    @Override
  protected void map( WritableComparable<?> key,
                      HCatRecord value, org.apache.hadoop.mapreduce.Mapper<WritableComparable<?>, HCatRecord,
                      Text, HCatRecord>.Context context)
        throws IOException, InterruptedException {

    	PhysicalTableRow row1 = new PhysicalTableRow(value);
		RawDataViewRow row2 = new RawDataViewRow(row1);
		
		id = (String) row2.getValue(0);
    	
    	HCatRecord record = new DefaultHCatRecord(2);
		record.set(0, row2.getValue(0));
		record.set(1, row2.getValue(1));
    	
        // Just select and emit the name and ID
        context.write(new Text(id), record);
    }

}
