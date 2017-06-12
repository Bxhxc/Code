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

	/**
	 * 使用: 定义输入Key,输入Value  
	 *      定义输出key,输出Value
	 * Haddop IO 常见类型：
	 *      LongWritable long
	 *      Text String
	 *      IntWritable int
	 * 
	 * 继承 MapReduceBase
	 * 实现 Mapper<LongWritable, Text, Text, IntWritable> 接口
	 * 重写map,实现并发分销逻辑
	 */
	@Override
	public void map(LongWritable inputKey, Text inputValue,
			OutputCollector<Text, IntWritable> OutputMap, Reporter arg3)
			throws IOException {
		 //获取名航数据
	     String line = inputValue.toString();
	     //解析每行数据
	     //1.获取年
	     String year = line.substring(15, 19);
	     //2.获取每行的温度
	     int temp ;
	     if(line.charAt(87) == '+'){
	    	 temp = Integer.valueOf(line.substring(88,92));
	     }else{
	    	 temp = Integer.valueOf(line.substring(88,92));
	     }
	     if(temp != 9999 && line.substring(92, 93).matches("[01459]")){
	    	 //需将java->hadoop 类型
	    	 OutputMap.collect(new Text(year), new IntWritable(temp));
	     }
		
	}

}
