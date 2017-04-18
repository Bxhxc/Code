package com.hgc.java.zookeep.api;

import java.io.IOException;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hgc.java.zookeep.config.ZnodeConfig;
import com.hgc.java.zookeep.config.ZookeeperConfig;

/**
 * @Description:
 * @author guicheng.huang
 * @date: 2017年4月18日 上午10:22:35
 * @version V0.0.1
 */
public class ZookeeperApi {

	public static final Logger LOGGER = LoggerFactory.getLogger(ZookeeperApi.class);  
	
	private ZooKeeper zooKeeper;
	
	//连接zookeeper
	public void connect(ZookeeperConfig config) throws IOException{
		zooKeeper = new ZooKeeper(config.getZookeeperAddressList(), config.getSessionTimeOut(), config.getWatcher(), config.getSessionId(), config.getSessionPasswd().getBytes(), config.getOcanBeReadOnly());
	}
	
	//关闭
	public void close() throws InterruptedException{
		zooKeeper.close();
	}
	
	/** 
     * 创建一个znode  
     *  1.CreateMode 取值   
     *  PERSISTENT：持久化，这个目录节点存储的数据不会丢失 
     *  PERSISTENT_SEQUENTIAL：顺序自动编号的目录节点，这种目录节点会根据当前已近存在的节点数自动加 1，然后返回给客户端已经成功创建的目录节点名； 
     *  EPHEMERAL：临时目录节点，一旦创建这个节点的客户端与服务器端口也就是 session过期超时，这种节点会被自动删除 
     *  EPHEMERAL_SEQUENTIAL：临时自动编号节点 
     * <br>------------------------------<br> 
     */  
	public void creatNode(ZnodeConfig znodeConfig) throws KeeperException, InterruptedException{
		zooKeeper.create(znodeConfig.getNodePath(), znodeConfig.getNodeData(), znodeConfig.getAcl(), znodeConfig.getNodeType());
	}
	
	/**
	 * 删除某一节点，version=-1，,不做版本校验直接删除
	 * @param znodeConfig
	 * @throws InterruptedException
	 * @throws KeeperException
	 */
	public void deleteNode(ZnodeConfig znodeConfig) throws InterruptedException, KeeperException{
		zooKeeper.delete(znodeConfig.getNodePath(), znodeConfig.getDataversion());
	}
	
	/**
	 * 获取节点的数据
	 * @param znodeConfig
	 * @return
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public String getData(ZnodeConfig znodeConfig) throws KeeperException, InterruptedException{
		byte[] by = null;
		if(!znodeConfig.getIsWatcher()){
			by = zooKeeper.getData(znodeConfig.getNodePath(),false, null);
		}else{
			by = zooKeeper.getData(znodeConfig.getNodePath(), znodeConfig.getWatcher(), null);
		}
		return new String(by);
	}
	
	/**
	 * 设置节点数据
	 * @param znodeConfig
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public void setData(ZnodeConfig znodeConfig) throws KeeperException, InterruptedException{
		zooKeeper.setData(znodeConfig.getNodePath(), znodeConfig.getNodeData(), znodeConfig.getDataversion());
	}
	
	
	public void getChildren(ZnodeConfig znodeConfig) throws KeeperException, InterruptedException{
		if(!znodeConfig.getIsWatcher()){
			zooKeeper.getChildren(znodeConfig.getNodePath(), false, null);
		}else{
			zooKeeper.getChildren(znodeConfig.getNodePath(), znodeConfig.getWatcher(), null);
		}
	}
}

