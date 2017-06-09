package com.hgc.java.hadoop.mapreduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.machines_jsp;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * @Description:
 * @author guicheng.huang
 * @date: 2017年6月9日 下午4:32:16
 * @version V0.0.1
 */
public class NewMaxtemperature {

	static class NewMaxtemperatureMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	 
    }
 
    static class NewMaxtemperaturReduce extends Reducer<Text, IntWritable, Text, IntWritable> {
    	
    }
    
    public static void main(String[] args) {
		
	}
}
