package com.hgc.java.zookeep.api;
import java.io.IOException;
import java.util.List;

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
	
	/**
	 * 客户端连接zookeeper服务器,进行一次会话
	 * @param config :
	 *               connectString【zookkeeper服务器地址】, 
	 *               sessionTimeout【session的会话超时时间】,
	 *               watcher【通过构造方法连接zookeeper设置watcher,会被一直保存在ZKwatchermanger中的default watcher中】,
                     sessionId【seeeion会话 id】, 
                     sessionPasswd【seeeion会话 密码】, 
                     canBeReadOnly【此次会话是否仅仅可读】
                     
                     watcher设置后,一旦触发一次就会失效,需再次设置
	 * @throws IOException
	 */
	public void connect(ZookeeperConfig config) throws IOException{
		zooKeeper = new ZooKeeper(config.getZookeeperAddressList(), config.getSessionTimeOut(), config.getWatcher());
	}
	
	/**
	 * 客户端关闭与zookeeper服务器的会话
	 * @throws InterruptedException
	 */
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
     * 
     * order: create [-e:临时节点][-s:持久节点] path [data] acl
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
	
	/**
	 * 获取子结点
	 * @param znodeConfig
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public List<String> getChildren(ZnodeConfig znodeConfig) throws KeeperException, InterruptedException{
		List<String> result ;
	    result = zooKeeper.getChildren(znodeConfig.getNodePath(), false, null);
		return result;
	}
	
	
	public static void main(String[] args) {

		Watcher watcher = new Watcher() {
			
			@Override
			public void process(WatchedEvent event) {
				System.out.println(event.getType()+"        "+event.getState());
			}
		};
		Watcher watcher1 = new Watcher() {
			
			@Override
			public void process(WatchedEvent event) {
				System.out.println(event.getType()+"        "+event.getState());
			}
		};
		ZookeeperApi zookeeperApi = new ZookeeperApi();
		try {
			ZookeeperConfig config = new ZookeeperConfig();
			config.setZookeeperAddressList("127.0.0.1:2181");
			config.setSessionTimeOut(200000);
			config.setWatcher(watcher);
			
			zookeeperApi.connect(config);
			System.out.println("连接zookeeper成功");
			
			ZnodeConfig znodeConfig = new ZnodeConfig();
			znodeConfig.setNodePath("/");
			znodeConfig.setIsWatcher(true);
			znodeConfig.setWatcher(watcher1);
			System.out.println(zookeeperApi.getChildren(znodeConfig));
		} catch (IOException | KeeperException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}

