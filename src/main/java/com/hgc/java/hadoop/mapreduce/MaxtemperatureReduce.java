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

	/**
	 * Reduce使用:
	 *  使用: 定义输入Key,输入Value  
	 *      定义输出key,输出Value
	 * Haddop IO 常见类型：
	 *      LongWritable long
	 *      Text String
	 *      IntWritable int
	 * 继承MapReduceBase
	 * 实现Reducer<Text, IntWritable, Text, IntWritable>接口
	 * 重写reduce方法
	 */
	@Override
	public void reduce(Text arg0, Iterator<IntWritable> arg1,
			OutputCollector<Text, IntWritable> arg2, Reporter arg3)
			throws IOException {
         int maxValue = Integer.MIN_VALUE;
         //找出最大值
         while(arg1.hasNext()){
        	 maxValue = Math.max(maxValue, arg1.next().get());
         }
         arg2.collect(new Text(arg0), new IntWritable(maxValue));
	}
}


