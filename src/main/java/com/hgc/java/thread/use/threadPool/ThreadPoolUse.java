package com.hgc.java.thread.use.threadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description:线程池的使用，比较使用线程池和不使用线程池的区别
 * @author guicheng.huang
 * @date: 2017年6月7日 下午4:34:31
 * @version V0.0.1
 */
public class ThreadPoolUse {

	static class NoThreadPool{
	   static int count =100;//默认10个线程
	   static List<Integer> list = new ArrayList<Integer>();
	   public static void main(String[] args) {
		   final Random random = new Random();
		   System.out.println("开启多线程");
		   long statime = System.currentTimeMillis();
		   for(int i=0;i<count;i++){
			   Thread th = new Thread(new Runnable() {
					public void run() {
					    //线程逻辑
						System.out.println(Thread.currentThread().getName()); // 
						list.add(random.nextInt());
					}
			   });
			   th.start();
			   try {
				   th.join();
				} catch (Exception e) {
				}
		   }
		   
		   System.out.println("多线程结束,花费时间"+(System.currentTimeMillis()-statime));
	   }
	}
	
	
	static class ThreadPool{
		   static int count =100;//默认10个线程
		   static List<Integer> list = new ArrayList<Integer>();
		   public static void main(String[] args) {
			   final Random random = new Random();
			   System.out.println("开启多线程");
			   long statime = System.currentTimeMillis();
			   for(int i=0;i<count;i++){
				   ThreadPoolExecutor th = new  ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue)
			   }
			   
			   System.out.println("多线程结束,花费时间"+(System.currentTimeMillis()-statime));
		   }
	}
}
