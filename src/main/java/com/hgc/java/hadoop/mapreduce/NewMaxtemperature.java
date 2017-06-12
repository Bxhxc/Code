package com.hgc.java.hadoop.mapreduce;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.machines_jsp;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @Description:
 * @author guicheng.huang
 * @date: 2017年6月9日 下午4:32:16
 * @version V0.0.1
 */
public class NewMaxtemperature {
    
	/**
	 * 1.使用继承Mapper<LongWritable, Text, Text, IntWritable>
	 * 2.重写map方法
	 * @author guicheng.huang
	 *
	 */
	static class NewMaxtemperatureMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	     @Override
	    protected void map(LongWritable key, Text inputValue,
	    		Mapper<LongWritable, Text, Text, IntWritable>.Context context)
	    		throws IOException, InterruptedException {
	    	super.map(key, inputValue, context);
	    	 
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
		    	 //OutputMap.collect(new Text(year), new IntWritable(temp));
		    	 context.write(new Text(year), new IntWritable(temp));
		     }
	    }
    }
 
	
	/**
	 * 使用:
	 * 1.继承 Reducer<Text, IntWritable, Text, IntWritable>
	 * 2.重写reduce方法
	 * @author guicheng.huang
	 *
	 */
    static class NewMaxtemperaturReduce extends Reducer<Text, IntWritable, Text, IntWritable> {
    	 @Override
    	protected void reduce(Text arg0, Iterable<IntWritable> arg1,
    			Reducer<Text, IntWritable, Text, IntWritable>.Context arg2)
    			throws IOException, InterruptedException {
    		super.reduce(arg0, arg1, arg2);
    		int maxValue = Integer.MIN_VALUE;
    	    for(IntWritable writable : arg1){
    	    	 maxValue = Math.max(maxValue, writable.get());
    	    }
    	    arg2.write(arg0, new IntWritable(maxValue));
    	}
    }
    
    public static void main(String[] args) {
    	        //验证参数
    			if(args.length != 2){
    				System.out.println("param error");
    				System.exit(-1);
    			}
    			
    			//定义job,设置hadoop寻找的类
    			Job job = null;
				try {
					job = new Job();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
    			job.setJarByClass(NewMaxtemperature.class);
    			job.setJobName("Maxtemperature");
    			
    			//定义输入/输入文件路径
    			try {
					FileInputFormat.addInputPath(job, new Path(args[0]));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    			FileOutputFormat.setOutputPath(job, new Path(args[1]));
    			
    			//设置map 和 reduce 类
    			job.setMapperClass(NewMaxtemperatureMapper.class);
    			job.setReducerClass(NewMaxtemperaturReduce.class);
    			
    			//定义输出Key
    			job.setOutputKeyClass(Text.class);
    			job.setOutputValueClass(IntWritable.class);
    			
    			try {
					System.exit(job.waitForCompletion(true) ? 0:1);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
}
