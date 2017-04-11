package com.hgc.java.rmi.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.hgc.java.rmi.server.IHelloService;

/**
 * @Description:
 * @author guicheng.huang
 * @date: 2017年4月11日 上午11:34:49
 * @version V0.0.1
 */
public class RmiClient {

	public static void main(String[] args) {
		IHelloService hello;
		try {
			hello = (IHelloService) Naming.lookup("rmi://localhost:8056/hello");
			hello.sayHello("hgc");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
