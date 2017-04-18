package com.hgc.java.zookeep.config;

import org.apache.zookeeper.Watcher;

/**
 * @Description:ZookeeperConfig
 * @author guicheng.huang
 * @date: 2017年4月18日 上午10:23:58
 * @version V0.0.1
 */
public class ZookeeperConfig {

	//zookeeper连接地址,多个地址通过，split
	private String zookeeperAddressList;
	
	//客户端连接超时,默认2000毫秒
	private int sessionTimeOut = 2000;
	
	//监听器，默认为空
	private Watcher watcher;

	//是否仅仅刻度,默认false
	private Boolean ocanBeReadOnly = false;
	
	//sessionId
	private long sessionId;
	
    //sessionPasswd
	private String sessionPasswd;
	
	public long getSessionId() {
		return sessionId;
	}

	public void setSessionId(long sessionId) {
		this.sessionId = sessionId;
	}

	public String getSessionPasswd() {
		return sessionPasswd;
	}

	public void setSessionPasswd(String sessionPasswd) {
		this.sessionPasswd = sessionPasswd;
	}

	public Boolean getOcanBeReadOnly() {
		return ocanBeReadOnly;
	}

	public void setOcanBeReadOnly(Boolean ocanBeReadOnly) {
		this.ocanBeReadOnly = ocanBeReadOnly;
	}

	public Watcher getWatcher() {
		return watcher;
	}

	public void setWatcher(Watcher watcher) {
		this.watcher = watcher;
	}

	public String getZookeeperAddressList() {
		return zookeeperAddressList;
	}

	public void setZookeeperAddressList(String zookeeperAddressList) {
		this.zookeeperAddressList = zookeeperAddressList;
	}

	public int getSessionTimeOut() {
		return sessionTimeOut;
	}

	public void setSessionTimeOut(int sessionTimeOut) {
		this.sessionTimeOut = sessionTimeOut;
	}
	
}
