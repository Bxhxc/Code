package com.hgc.java.thread.use.threadPool;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Description:ExecutorService 使用
 * @author guicheng.huang
 * @date: 2017年6月16日 下午2:03:23
 * @version V0.0.1
 */
public class ExecutorServiceUse {
  //Java.util.concurrent.ExecutorService接口代表一种异步执行机制，它能够在后台执行任务。
  //因此ExecutorService与thread pool是非常相似的。事实上，
  //在java.util.package包中ExecutorService的具体实现就是一个线程池的具体实现。
  //ThreadPoolExecutor,ExecutorService的实现类，具体一个线程池, ExecutorService接口代表一种异步执行机制
  
  //ExecutorService常见方法:
  //1.execute(Runnable) 方法接受一个java.lang.Runable对象的实例，并异步执行之,这种方式不能获得Runnable执行的结果，如果有这种需要，你将要使用Callable。
  //2.submit(Runnable) 方法也接收一个Runnable接口的具体实现，并返回一个Future对象。Future对象可以用来检测Runable是否执行完成。
  //3.submit(Callable)方法与submit(Runnable)方法相似，除了接收的参数有所不同。Callable实例非常类似于Runnable,不同的是call方法可以返回一个结果，Runnable.run()方法不能返回一个结果。
  //4.invokeAny()方法接收一个Callable对象或者Callable的子接口实例的#集合#作为参数，这个方法不会返回Future,但会返回集合中某一个Callable的结果。你不能确定你得到是哪个Callable的结果。只是已执行完成的Callable中的一个。
  //注:多个任务只要第一个执行的结果
  //5.invokeAll()接收一个Callable对象的集合作为参数，该方法会调用你传给他的集合中的所有Callable对象。Invoke()会返回一个Future对象的列表，通过这个列表你可以获取每一个Callable执行的结果。
	
	
  //1.execute(Runnable)
  public void createThreadMethodExecute(){
	  //Executors.newFixedThreadPool()//固定数量的线程池,可控制线程最大并发数，超出的线程会在队列中等待。
	  //Executors.newCachedThreadPool();//可缓存的线程池
	  //Executors.newSingleThreadExecutor()//单一线程池
	  //newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
	  ExecutorService pool = Executors.newCachedThreadPool();
	  //Runnable接口的匿名实现类作为参数被传递给execute()方法。
	  //Runable将会被ExecutorService中的一个线程来执行。(#任务委托（Task Delegation#）)
	  //一旦，线程委托任务给ExecutorService，线程会独立任务的执行而继续自己之后的操作。 理解:创建分支,让分支单独执行,主线程继续执行,与单独开个线程去执行任务区别，托管了
	  pool.execute(new Runnable() {
		
		@Override
		public void run() {
			System.out.println("ExecutorService thread"+Thread.currentThread().getName() + System.currentTimeMillis());
		}
	  });
	  
	  System.out.println("Main thread"+Thread.currentThread().getName() + System.currentTimeMillis());
  }
  
  //2.submit(Runnable)
  public void createThreadMethodSubmit() throws InterruptedException, ExecutionException{
	  //Executors.newFixedThreadPool()//固定数量的线程池,可控制线程最大并发数，超出的线程会在队列中等待。
	  //Executors.newCachedThreadPool();//可缓存的线程池
	  //Executors.newSingleThreadExecutor()//单一线程池
	  //newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
	  ExecutorService pool = Executors.newCachedThreadPool();
	  //Runnable接口的匿名实现类作为参数被传递给execute()方法。
	  //Runable将会被ExecutorService中的一个线程来执行。(#任务委托（Task Delegation#）)
	  //一旦，线程委托任务给ExecutorService，线程会独立任务的执行而继续自己之后的操作。 理解:创建分支,让分支单独执行,主线程继续执行,与单独开个线程去执行任务区别，托管了
	  Future future = pool.submit(new Runnable() {
		
		@Override
		public void run() {
			System.out.println("ExecutorService thread"+Thread.currentThread().getName() + System.currentTimeMillis());
		}
	  });
	  future.get();  //returns null if the task has finished correctly.
	  
	  
	  System.out.println("Main thread"+Thread.currentThread().getName() + System.currentTimeMillis());
  }
  
  //3.submit(Callable)
  public void createThreadMethodCallable() throws InterruptedException, ExecutionException{
	  //Executors.newFixedThreadPool()//固定数量的线程池,可控制线程最大并发数，超出的线程会在队列中等待。
	  //Executors.newCachedThreadPool();//可缓存的线程池
	  //Executors.newSingleThreadExecutor()//单一线程池
	  //newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
	  ExecutorService pool = Executors.newCachedThreadPool();
	  //Runnable接口的匿名实现类作为参数被传递给execute()方法。
	  //Runable将会被ExecutorService中的一个线程来执行。(#任务委托（Task Delegation#）)
	  //一旦，线程委托任务给ExecutorService，线程会独立任务的执行而继续自己之后的操作。 理解:创建分支,让分支单独执行,主线程继续执行,与单独开个线程去执行任务区别，托管了
	  Future future = pool.submit(new Callable<Object>() {

		@Override
		public Object call() throws Exception {
			System.out.println("ExecutorService thread"+Thread.currentThread().getName() + System.currentTimeMillis());
			return "Callable Result";
		}
	  });
	  
	  System.out.println("future.get() = " + future.get());  //future.get() = Callable Result
	  System.out.println("Main thread"+Thread.currentThread().getName() + System.currentTimeMillis());
  }
  
  
  //4.invokeAny()
  public void createThreadMethodInvokeAny() throws InterruptedException, ExecutionException{
	  //Executors.newFixedThreadPool()//固定数量的线程池,可控制线程最大并发数，超出的线程会在队列中等待。
	  //Executors.newCachedThreadPool();//可缓存的线程池
	  //Executors.newSingleThreadExecutor()//单一线程池
	  //newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
	  ExecutorService pool = Executors.newSingleThreadExecutor();
	  //Runnable接口的匿名实现类作为参数被传递给execute()方法。
	  //Runable将会被ExecutorService中的一个线程来执行。(#任务委托（Task Delegation#）)
	  //一旦，线程委托任务给ExecutorService，线程会独立任务的执行而继续自己之后的操作。 理解:创建分支,让分支单独执行,主线程继续执行,与单独开个线程去执行任务区别，托管了
	  Set<Callable<String>> callables = new HashSet<Callable<String>>();

	  callables.add(new Callable<String>() {
	      public String call() throws Exception {
	    	  System.out.println("Task 1");
	          return "Task 1";
	      }
	  });
	  callables.add(new Callable<String>() {
	      public String call() throws Exception {
	    	  System.out.println("Task 2");
	          return "Task 2";
	      }
	  });
	  callables.add(new Callable<String>() {
	      public String call() throws Exception {
	    	  System.out.println("Task 3");
	          return "Task 3";
	      }
	  });

	  String result = pool.invokeAny(callables);

	  System.out.println("result = " + result);

	  pool.shutdown();
	  
	  
	  System.out.println("Main thread"+Thread.currentThread().getName() + System.currentTimeMillis());
  }
  
//4.invokeAny()
  public void createThreadMethodInvokeAll() throws InterruptedException, ExecutionException{
	  //Executors.newFixedThreadPool()//固定数量的线程池,可控制线程最大并发数，超出的线程会在队列中等待。
	  //Executors.newCachedThreadPool();//可缓存的线程池
	  //Executors.newSingleThreadExecutor()//单一线程池
	  //newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
	  ExecutorService pool = Executors.newSingleThreadExecutor();
	  //Runnable接口的匿名实现类作为参数被传递给execute()方法。
	  //Runable将会被ExecutorService中的一个线程来执行。(#任务委托（Task Delegation#）)
	  //一旦，线程委托任务给ExecutorService，线程会独立任务的执行而继续自己之后的操作。 理解:创建分支,让分支单独执行,主线程继续执行,与单独开个线程去执行任务区别，托管了
	  Set<Callable<String>> callables = new HashSet<Callable<String>>();

	  callables.add(new Callable<String>() {
	      public String call() throws Exception {
	    	  System.out.println("Task 1");
	          return "Task 1";
	      }
	  });
	  callables.add(new Callable<String>() {
	      public String call() throws Exception {
	    	  System.out.println("Task 2");
	          return "Task 2";
	      }
	  });
	  callables.add(new Callable<String>() {
	      public String call() throws Exception {
	    	  System.out.println("Task 3");
	          return "Task 3";
	      }
	  });

	  List<Future<String>> futures = pool.invokeAll(callables);

	  for(Future<String> future : futures){
		    System.out.println("future.get = " + future.get());
	  }

	  pool.shutdown();
	  
	  
	  System.out.println("Main thread"+Thread.currentThread().getName() + System.currentTimeMillis());
  }
  
  public static void main(String[] args) throws InterruptedException, ExecutionException {
	  ExecutorServiceUse service = new ExecutorServiceUse();
	  service.createThreadMethodInvokeAny();
  }
}
