package com.hgc.java.hadoop.mapreduce;

import java.io.IOException;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;

/**
 * @Description:运行reduce
 * @author guicheng.huang
 * @date: 2017年6月9日 下午3:32:41
 * @version V0.0.1
 */
public class Maxtemperature {

	public static void main(String[] args) {
		
		//验证参数
		if(args.length != 2){
			System.out.println("param error");
			System.exit(-1);
		}
		
		//定义job,设置hadoop寻找的类
		JobConf job = new JobConf(Maxtemperature.class);
		job.setJobName("Maxtemperature");
		
		//定义输入/输入文件路径
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		//设置map 和 reduce 类
		job.setMapperClass(MaxtemperatureMapper.class);
		job.setReducerClass(MaxtemperatureReduce.class);
		
		//定义输出Key
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		try {
			JobClient.runJob(job);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
