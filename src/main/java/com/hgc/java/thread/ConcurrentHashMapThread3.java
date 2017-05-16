package com.hgc.java.thread;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @author guicheng.huang
 * @date: 2017年5月16日 上午10:26:27
 * @version V0.0.1
 */
public class ConcurrentHashMapThread3 {
	public static void main(String[] args) throws InterruptedException {  
        for (int i = 0; i < 10; i++) {  
            System.out.println(test());  
        }  
    }  
      
    private static int test() throws InterruptedException {  
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<String, Integer>();  
        ExecutorService pool = Executors.newCachedThreadPool();  
        for (int i = 0; i < 8; i++) {  
            pool.execute(new MyTask3(map));  
        }  
        pool.shutdown();  
        pool.awaitTermination(1, TimeUnit.DAYS);  
          
        return map.get(MyTask.KEY);  
    }  
}  
  
class MyTask3 implements Runnable {  
      
    public static final String KEY = "key";  
      
    private ConcurrentHashMap<String, Integer> map;  
      
    public MyTask3(ConcurrentHashMap<String, Integer> map) {  
        this.map = map;  
    }  
  
    @Override  
    public void run() {  
        for (int i = 0; i < 100; i++) {  
            synchronized (map) { // 对共享对象map上锁  
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
