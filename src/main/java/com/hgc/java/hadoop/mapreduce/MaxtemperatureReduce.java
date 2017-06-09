package com.hgc.java.hadoop.mapreduce;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

/**
 * @Description:hadoop 老版本reduce使用
 * @author guicheng.huang
 * @date: 2017年6月9日 下午3:27:41
 * @version V0.0.1
 */
public class MaxtemperatureReduce extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable>{

	@Override
	public void reduce(Text arg0, Iterator<IntWritable> arg1,
			OutputCollector<Text, IntWritable> arg2, Reporter arg3)
			throws IOException {

		
	}
}


