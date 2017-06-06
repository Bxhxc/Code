package com.hgc.java.thread.deadlock;
/**
 * @Description:模型死锁
 * 死锁：指多个进程因竞争共享资源而造成的一种僵局，若无外力作用，这些进程都将永远不能再 向前推进。
 * 产生死锁的原因：（1）竞争系统资源 （2）进程的推进顺序不当
 * T1线程锁A ,期望锁B  T2线程锁B 期望锁A
 * 当然死锁的产生是必须要满足一些特定条件的： 
	1.互斥条件：进程对于所分配到的资源具有排它性，即一个资源只能被一个进程占用，直到被该进程释放 
	2.请求和保持条件：一个进程因请求被占用资源而发生阻塞时，对已获得的资源保持不放。 
	3.不剥夺条件：任何一个资源在没被该进程释放之前，任何其他进程都无法对他剥夺占用 
	4.循环等待条件：当发生死锁时，所等待的进程必定会形成一个环路（类似于死循环），造成永久阻塞。
       解决死锁的基本方法：
	预防死锁：
	资源一次性分配：（破坏请求和保持条件）
	可剥夺资源：即当某进程新的资源未满足时，释放已占有的资源（破坏不可剥夺条件）
	资源有序分配法：系统给每类资源赋予一个编号，每一个进程按编号递增的顺序请求资源，释放则相反（破坏环路等待条件）
      避免死锁:
	预防死锁的几种策略，会严重地损害系统性能。因此在避免死锁时，要施加较弱的限制，从而获得 较满意的系统性能。由于在避免死锁的策略中，允许进程动态地申请资源。因而，系统在进行资源分配之前预先计算资源分配的安全性。若此次分配不会导致系统进入不安全状态，则将资源分配给进程；否则，进程等待。其中最具有代表性的避免死锁算法是银行家算法。
      检测死锁
          首先为每个进程和每个资源指定一个唯一的号码；然后建立资源分配表和进程等待表
  解除死锁:
         当发现有进程死锁后，便应立即把它从死锁状态中解脱出来，常采用的方法有：
         剥夺资源：从其它进程剥夺足够数量的资源给死锁进程，以解除死锁状态；
         撤消进程：可以直接撤消死锁进程或撤消代价最小的进程，直至有足够的资源可用，死锁状态.消除为止；所谓代价是指优先级、运行代价、进程的重要性和价值等。
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
