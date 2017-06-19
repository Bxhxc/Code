package com.hgc.java.hadoop.hdfs;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;

/**
 * @Description:
 * @author guicheng.huang
 * @date: 2017年6月19日 下午2:33:49
 * @version V0.0.1
 */
public class javaUrlApi {

	//直接从url中读取文件
	static{
		URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
	}
	
	public static void main(String[] args) {
		InputStream in = null;
		try {
			in = new URL(args[0]).openStream();
			IOUtils.copyBytes(in, System.out, 4096,false);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			IOUtils.closeStream(in);
		}
		
	}
}
