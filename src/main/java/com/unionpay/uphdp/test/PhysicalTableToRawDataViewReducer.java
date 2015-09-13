package com.unionpay.uphdp.test;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hive.hcatalog.data.HCatRecord;

public class PhysicalTableToRawDataViewReducer 
extends Reducer<Text, IntWritable, WritableComparable<?>, HCatRecord>{

	protected void reduce( Text key,
			java.lang.Iterable<HCatRecord> values, org.apache.hadoop.mapreduce.Reducer<Text, IntWritable,
			WritableComparable<?>, HCatRecord>.Context context)
					throws IOException, InterruptedException {

		Iterator<HCatRecord> iter = values.iterator();

		while(iter.hasNext()){
			context.write(null, iter.next());
		}
	}

}
