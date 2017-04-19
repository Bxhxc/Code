package com.hgc.java.zookeep.config;

import java.util.ArrayList;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.ACL;

/**
 * @Description:zookeeper 节点
 * @author guicheng.huang
 * @date: 2017年4月18日 上午10:48:26
 * @version V0.0.1
 */
public class ZnodeConfig {

	//节点名称
	private String nodePath;
	
	//节点值
	private String nodeData;
	
	//节点的类型
	private CreateMode nodeType = CreateMode.PERSISTENT;
	
	//节点的权限设置，默认开放
	private ArrayList<ACL> acl = Ids.OPEN_ACL_UNSAFE;

	//版本控制,默认不做版本控制
    private int Dataversion = -1;
    
    //监听器，默认为空
  	private Boolean isWatcher = false;
  	
    public Boolean getIsWatcher() {
		return isWatcher;
	}

	public void setIsWatcher(Boolean isWatcher) {
		this.isWatcher = isWatcher;
	}

	//监听器，默认为空
  	private Watcher watcher;
  	
	public int getDataversion() {
		return Dataversion;
	}

	public Watcher getWatcher() {
		return watcher;
	}

	public void setWatcher(Watcher watcher) {
		this.watcher = watcher;
	}

	public void setDataversion(int dataversion) {
		Dataversion = dataversion;
	}

	public String getNodePath() {
		return nodePath;
	}

	public void setNodePath(String nodePath) {
		this.nodePath = nodePath;
	}

	public byte[] getNodeData() {
		return nodeData.getBytes();
	}

	public void setNodeData(String nodeData) {
		this.nodeData = nodeData;
	}

	public CreateMode getNodeType() {
		return nodeType;
	}

	public void setNodeType(CreateMode nodeType) {
		this.nodeType = nodeType;
	}

	public ArrayList<ACL> getAcl() {
		return acl;
	}

	public void setAcl(ArrayList<ACL> acl) {
		this.acl = acl;
	}
}
