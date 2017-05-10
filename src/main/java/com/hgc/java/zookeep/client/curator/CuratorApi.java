package com.hgc.java.zookeep.client.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

/**
 * @Description:
 * @author guicheng.huang
 * @date: 2017年4月27日 上午10:35:40
 * @version V0.0.1
 */
public class CuratorApi {

	private static CuratorFramework cilent = null;
	
	private final String path = "/zk-test";
	static {
		if(cilent == null){
			cilent = CuratorFrameworkFactory.builder()
											.connectString("localhost:2181")
					                        .connectionTimeoutMs(3000)
					                        .sessionTimeoutMs(3000)
					                        .retryPolicy(new ExponentialBackoffRetry(1000, 3, 4000))
					                        .build();
			cilent.start();		                        
		}
	}
	
//	@Test
//	public void createNode(){
//		try {
//			cilent.create()
//			      .creatingParentsIfNeeded()
//			      .forPath(path,"test".getBytes());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
//
//	@Test
//	public void getVersion(){
//		try {
//			Stat stat = new Stat();
//			stat.setAversion(0);
//			byte [] b = cilent.getData()
//								   .storingStatIn(stat)
//								   .forPath(path);
//			System.out.println(stat);
//			System.out.println(new String(b));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	@Test
	public void listener(){
		try {
			System.out.println("开启结点监听");
		    final NodeCache cache = new NodeCache(cilent, "/lteam-job/lteam-job-example-spring/simpleJob/confignode", false);
		    cache.getListenable().addListener(new NodeCacheListener() {
				
				@Override
				public void nodeChanged() throws Exception {
					System.out.println("监听到结果");
					System.out.println(cache.getCurrentData().getData());		
				}
			});
		    cache.start(true);
		    Thread.currentThread().sleep(100000l);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
