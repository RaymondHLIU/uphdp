package com.cloudera.test;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hive.hcatalog.data.DefaultHCatRecord;
import org.apache.hive.hcatalog.data.schema.HCatSchema;
import org.apache.hive.hcatalog.mapreduce.HCatInputFormat;
import org.apache.hive.hcatalog.mapreduce.HCatOutputFormat;
import org.apache.hive.hcatalog.mapreduce.OutputJobInfo;

public class UseHCat extends Configured implements Tool {

    public int run(String[] args) throws Exception {
        Configuration conf = getConf();
        args = new GenericOptionsParser(conf, args).getRemainingArgs();

        // Get the input and output table names as arguments
        //String inputTableName =  "mrconnect";
        //String outputTableName = "mrconnectout";
        String inputTableName =  args[0];
        String outputTableName = args[1];
        // Assume the default database
        String dbName = "mydb";

        Job job = Job.getInstance(conf, "UseHCat");
        HCatInputFormat.setInput(job, dbName, inputTableName);
        job.setJarByClass(UseHCat.class);
        job.setMapperClass(RowTableToPhysicalTableMapper.class);
        job.setReducerClass(RowTableToPhysicalTableReducer.class);

        // An HCatalog record as input
        job.setInputFormatClass(HCatInputFormat.class);

        // Mapper emits a string as key and an integer as value
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(DefaultHCatRecord.class);

        // Ignore the key for the reducer output; emitting an HCatalog record as value
        job.setOutputKeyClass(WritableComparable.class);
        job.setOutputValueClass(DefaultHCatRecord.class);
        job.setOutputFormatClass(HCatOutputFormat.class);

        HCatOutputFormat.setOutput(job, OutputJobInfo.create(dbName,
                   outputTableName, null));
        HCatSchema s = HCatOutputFormat.getTableSchema(job.getConfiguration());
        System.err.println("INFO: output schema explicitly set for writing:" + s);
        HCatOutputFormat.setSchema(job, s);
        return (job.waitForCompletion(true) ? 0 : 1);
    }

    
    public static void main(String[] args) throws Exception {
    	try{
        int exitCode = ToolRunner.run(new UseHCat(), args);
        System.exit(exitCode);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
}
