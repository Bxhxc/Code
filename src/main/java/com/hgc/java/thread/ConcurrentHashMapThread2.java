package com.hgc.java.thread;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @author guicheng.huang
 * @date: 2017年5月16日 上午10:19:50
 * @version V0.0.1
 */
public class ConcurrentHashMapThread2 {

	 public static void main(String[] args) throws InterruptedException {  
	        for (int i = 0; i < 10; i++) {  
	            System.out.println(test());  
	        }  
	    }  
	      
	    private static int test() throws InterruptedException {  
	    	//共享变量是线程安全的
	        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<String, Integer>();  
	        ExecutorService pool = Executors.newCachedThreadPool();  
	        for (int i = 0; i < 8; i++) {  
	            pool.execute(new MyTask2(map));  
	        }  
	        pool.shutdown();  
	        pool.awaitTermination(1, TimeUnit.DAYS);  
	          
	        return map.get(MyTask2.KEY);  
	    }  
	}  
	  
	class MyTask2 implements Runnable {  
	      
	    public static final String KEY = "key";  
	      
	    private ConcurrentHashMap<String, Integer> map;  
	      
	    public MyTask2(ConcurrentHashMap<String, Integer> map) {  
	        this.map = map;  
	    }  
	  
	    @Override  
	    public void run() {  
	        for (int i = 0; i < 100; i++) {  
	            this.addup();  
	        }  
	    }  
	      
	    /**
	     * 通过synchronized修改方法
	     * 问题:，不管synchronized是用来修饰方法，还是修饰代码块，
	     *      其本质都是锁定某一个对象。修饰方法时，锁上的是调用这个方法的对象，即this；
	     *      修饰代码块时，锁上的是括号里的那个对象。
	     *      上面的代码中，很明显就是锁定的MyTask对象本身。
	     *      但是由于在每一个线程中，MyTask对象都是独立的，这就导致实际上每个线程都对自己的MyTask进行锁定，
	     *      而并不会干涉其它线程的MyTask对象。换言之，上锁压根没有意义
	     */
	    private synchronized void addup() { // 用关键字synchronized修饰addup方法  
	        if (!map.containsKey(KEY)) {  
	            map.put(KEY, 1);  
	        } else {  
	            map.put(KEY, map.get(KEY) + 1);  
	        }  
	    }  
	    
}
