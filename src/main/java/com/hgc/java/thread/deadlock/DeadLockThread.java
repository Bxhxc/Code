package com.hgc.java.thread.deadlock;
/**
 * @Description:模型死锁
 * T1线程锁A ,期望锁B  T2线程锁B 期望锁A
 * @author guicheng.huang
 * @date: 2017年5月16日 上午11:20:55
 * @version V0.0.1
 */
public class DeadLockThread implements Runnable{

	//共享资源A,B
	private static final Object objectA = new Object();
    private static final Object objectB = new Object();
    //标记两个线程
    private boolean flag;

    @Override
    public void run() {
    	//获取当前线程的名称
        String threadName = Thread.currentThread().getName();
        System.out.println("当前线程 为：" + threadName + "\tflag = " + flag);
        if (flag) {
        	//锁A
            synchronized (objectA) {
                try {
                	//当前线程sleep
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println(threadName + "已进入同步代码块objectA，准备进入objectB");
                synchronized (objectB) {
                    System.out.println(threadName + "已经进入同步代码块objectB");
                }
            }

        } else {
        	//锁B
            synchronized (objectB) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println(threadName + "已进入同步代码块objectB，准备进入objectA");
                synchronized (objectA) {
                    System.out.println(threadName + "已经进入同步代码块objectA");
                }
            }
        }
    }

    public static void main(String[] args) {
        DeadLockThread deadlock1 = new DeadLockThread();
        DeadLockThread deadlock2 = new DeadLockThread();
        deadlock1.flag = true;
        deadlock2.flag = false;
        Thread thread1 = new Thread(deadlock1);
        Thread thread2 = new Thread(deadlock2);
        thread1.start();
        thread2.start();

    }
}
