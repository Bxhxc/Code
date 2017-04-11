package com.hgc.java.rmi.server;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * @Description:
 * @author guicheng.huang
 * @date: 2017年4月11日 上午11:30:57
 * @version V0.0.1
 */
public class RmiServer {

	//rmi server
	/**
	 * 一开发步骤:
	 * 1.定义服务接口  extends Remote
	 *   定义对外暴露方法:声明抛出RemoteException
	 * 2.定义服务接口实现类 extends UnicastRemoteObject 序列化 定义构造方法
	 * 3.定义服务类 
	 *   启动注册中心 LocateRegistry.createRegistry(8056);  
	 *   绑定命名空间 Naming.bind("rmi://localhost:8056/hello", hello);
	 * 4.生成存根和骨架
	 * 5.开启服务和客户端
	 * 6.客户端查询名称空间获取服务对象(hello = (IHelloService) Naming.lookup("rmi://localhost:8056/hello");),调用服务方法
	 * 
	 * 二RMI原理:
	 * 
	 */
 
	public static void main(String[] args) {
		IHelloService hello;
		try {
			hello = new HelloServiceImpl();
			//方式一:开启注册中心
			//方式二:通过start rmiregistry 命名开启注册中心服务
			LocateRegistry.createRegistry(8056);  
			//绑定命名
			//实现方式一:java.rmi.Naming
			//实现方式二:javax.naming.*
			//实现方式三:jndi
			Context namingContext = new InitialContext(); 
			namingContext.bind("rmi://localhost:8056/hello", hello);
			Naming.bind("rmi://localhost:8056/hello", hello);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (MalformedURLException e) {
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
