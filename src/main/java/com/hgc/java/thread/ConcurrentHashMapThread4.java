package com.hgc.java.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @author guicheng.huang
 * @date: 2017年5月16日 上午10:28:26
 * @version V0.0.1
 */
public class ConcurrentHashMapThread4 {


    public static void main(String[] args) throws InterruptedException {  
        for (int i = 0; i < 100; i++) {  
            System.out.println(test());  
        }  
    }  
      
    private static int test() throws InterruptedException {  
        Map<String, Integer> map = new HashMap<String, Integer>();  
        ExecutorService pool = Executors.newCachedThreadPool();  
        for (int i = 0; i < 8; i++) {  
            pool.execute(new MyTask4(map));  
        }  
        pool.shutdown();  
        pool.awaitTermination(1, TimeUnit.DAYS);  
          
        return map.get(MyTask.KEY);  
    }  
}  
  
class MyTask4 implements Runnable {  
    
	//被所有的任务类共享变量。
    public static Object lock = new Object();  
      
    public static final String KEY = "key";  
      
    private Map<String, Integer> map;  
      
    public MyTask4(Map<String, Integer> map) {  
        this.map = map;  
    }  
  
    @Override  
    public void run() {  
        for (int i = 0; i < 100; i++) {  
            synchronized (lock) {  
                this.addup();  
            }  
        }  
    }  
      
    private void addup() {  
        if (!map.containsKey(KEY)) {  
            map.put(KEY, 1);  
        } else {  
            map.put(KEY, map.get(KEY) + 1);  
        }  
    }  
}
