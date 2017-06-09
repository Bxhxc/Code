package com.hgc.java.thread.use.threadPool;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description:线程池的使用
 * @author guicheng.huang
 * @date: 2017年6月7日 下午4:34:31
 * @version V0.0.1
 */
public class ThreadPoolUse {

	ThreadPoolExecutor te = new ThreadPoolExecutor(4, 4, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(4));
}
