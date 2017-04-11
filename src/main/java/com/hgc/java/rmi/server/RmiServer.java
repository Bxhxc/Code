package com.hgc.java.rmi.server;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * @Description:
 * @author guicheng.huang
 * @date: 2017年4月11日 上午11:30:57
 * @version V0.0.1
 */
public class RmiServer {

	//rmi server
	//1.
	public static void main(String[] args) {
		IHelloService hello;
		try {
			hello = new HelloServiceImpl();
			LocateRegistry.createRegistry(8056);  
			Naming.bind("rmi://localhost:8056/hello", hello);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (MalformedURLException e) {
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
	}
}
