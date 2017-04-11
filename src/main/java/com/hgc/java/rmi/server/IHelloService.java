package com.hgc.java.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @Description:
 * @author guicheng.huang
 * @date: 2017年4月11日 上午11:29:08
 * @version V0.0.1
 */
public interface IHelloService extends Remote {

	public void sayHello(String content) throws RemoteException;
}
