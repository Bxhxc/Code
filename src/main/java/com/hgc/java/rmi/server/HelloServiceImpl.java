package com.hgc.java.rmi.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @Description:
 * @author guicheng.huang
 * @date: 2017年4月11日 上午11:30:20
 * @version V0.0.1
 */
public class HelloServiceImpl extends UnicastRemoteObject  implements IHelloService {

	protected HelloServiceImpl() throws RemoteException {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7065966904792939985L;

	@Override
	public void sayHello(String content) throws RemoteException {
		System.out.println(content);
	}

}
