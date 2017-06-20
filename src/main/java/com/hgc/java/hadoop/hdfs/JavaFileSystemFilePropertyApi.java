package com.hgc.java.hadoop.hdfs;

import java.io.IOException;
import java.io.InputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

/**
 * @Description:
 * @author guicheng.huang
 * @date: 2017年6月19日 下午2:41:47
 * @version V0.0.1
 */
public class JavaFileSystemFilePropertyApi {

	public void getFilePropertyInfo(String url){
		Configuration configuration = new Configuration();
		try {
			FileSystem fs = FileSystem.get(configuration);
			FileStatus status = fs.getFileStatus(new Path(url));
			System.out.println("path: "+status.getPath());
			System.out.println("path: "+status.isDir());
			System.out.println("length: "+status.getLen());
			System.out.println("modify time: "+status.getModificationTime());
			System.out.println("owner: "+status.getOwner());
			System.out.println("replication: "+status.getReplication());
			System.out.println("blockSize: "+status.getBlockSize());
			System.out.println("group: "+status.getGroup());
			System.out.println("permission: "+status.getPermission().toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getDirectionPropertyInfo(String url){
		Configuration configuration = new Configuration();
		try {
			FileSystem fs = FileSystem.get(configuration);
			FileStatus[] status = fs.listStatus(new Path(url));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
