package com.unionpay.uphdp.test;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hive.hcatalog.data.DefaultHCatRecord;
import org.apache.hive.hcatalog.data.HCatRecord;

import com.unionpay.uphdp.mr.histdb.row.PhysicalTableLoaderRow;
import com.unionpay.uphdp.mr.histdb.row.RawDataRow;

public class RowTableToPhysicalTableMapper 
	extends Mapper<WritableComparable<?>, HCatRecord, Text, HCatRecord> {
	 static String[] row = new String[2];

     @Override
     protected void map( WritableComparable<?> key,
                       HCatRecord value,
                       org.apache.hadoop.mapreduce.Mapper<WritableComparable<?>, HCatRecord,
                       Text, HCatRecord>.Context context)
         throws IOException, InterruptedException {

    	 //Raw data is stored as non-orc file on hive data warehouse, why we need string[]?
         row[0] = value.get(0).toString();
         row[1] = value.get(1).toString();
         
         RawDataRow row1 = new RawDataRow(row);
 		 PhysicalTableLoaderRow row2 = new PhysicalTableLoaderRow(row1);

 		 HCatRecord record = new DefaultHCatRecord(1);
 		 record.set(0, row2.getValue("id_and_name"));

         // Just select and emit the name and ID
         context.write(new Text(row[0]), record);
     }

}
