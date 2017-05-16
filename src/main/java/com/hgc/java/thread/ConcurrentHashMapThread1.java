package com.hgc.java.thread;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @author guicheng.huang
 * @date: 2017年5月15日 下午6:02:51
 * @version V0.0.1
 */
public class ConcurrentHashMapThread1 {
    /**
     * 1.ConcurrentHashMap 是线程安全的但是是针对每个子操作put,get,但是组合起来就不是线程安全的 HashMap不是线程安全的
     * 2.
     * @param args
     * @throws InterruptedException
     */
	public static void main(String[] args) throws InterruptedException {  
        for (int i = 0; i < 10; i++) {  
            System.out.println(test());  
        }  
    }  
      
    private static int test() throws InterruptedException {  
    	//共享变量
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<String, Integer>();  
        //生成线程池
        ExecutorService pool = Executors.newCachedThreadPool();  
        for (int i = 0; i < 8; i++) {  
        	//执行任务
            pool.execute(new MyTask(map));  
        }  
        pool.shutdown();  
        pool.awaitTermination(1, TimeUnit.DAYS);  
          
        return map.get(MyTask.KEY);  
    }  
}  
  
class MyTask implements Runnable {  
      
    public static final String KEY = "key";  
      
    private ConcurrentHashMap<String, Integer> map;  
      
    public MyTask(ConcurrentHashMap<String, Integer> map) {  
        this.map = map;  
    }  
  
    @Override  
    public void run() {  
        for (int i = 0; i < 100; i++) {  
            this.addup();  
        }  
    }  
      
    /**
     * 问题:
     * ConcurrentHashMap 单独对每个操作是线程安全的,即原子性
     * 但是组合起来是非线程安全的
     * 
     */
    private void addup() {  
        if (!map.containsKey(KEY)) {  
            map.put(KEY, 1);  
        } else {  
            map.put(KEY, map.get(KEY) + 1);  
        }       
    }  
}
