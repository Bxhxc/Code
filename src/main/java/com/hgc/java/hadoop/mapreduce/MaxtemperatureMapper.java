package com.hgc.java.hadoop.mapreduce;
import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
/**
 * @Description:hadoop 老版本使用
 * @author guicheng.huang
 * @date: 2017年6月9日 下午3:12:52
 * @version V0.0.1
 */
public class MaxtemperatureMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable>{

	@Override
	public void map(LongWritable arg0, Text arg1,
			OutputCollector<Text, IntWritable> arg2, Reporter arg3)
			throws IOException {
	
	}

}
