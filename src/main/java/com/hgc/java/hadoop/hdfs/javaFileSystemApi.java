package com.hgc.java.hadoop.hdfs;

import java.io.IOException;
import java.io.InputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
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
public class javaFileSystemApi {

	public static void readFileToSysOut(String dst) {
		InputStream in = null ;
		try {
			FileSystem fs = FileSystem.get(new Configuration());
            in = fs.open(new Path(dst));
            IOUtils.copyBytes(in, System.out, 4096,false);
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			IOUtils.closeStream(in);
		}
	}
	
	 //创建新文件
	 public static void createFile(String dst , byte[] contents) throws IOException{
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        Path dstPath = new Path(dst); //目标路径
        //打开一个输出流
        FSDataOutputStream outputStream = fs.create(dstPath);
        outputStream.write(contents);
	    outputStream.close();
        fs.close();
        System.out.println("文件创建成功！");
     }
	 
	 //上传本地文件
	 public static void uploadFile(String src,String dst) throws IOException{
          Configuration conf = new Configuration();
          FileSystem fs = FileSystem.get(conf);
          Path srcPath = new Path(src); //原路径
          Path dstPath = new Path(dst); //目标路径
          //调用文件系统的文件复制函数,前面参数是指是否删除原文件，true为删除，默认为false
          fs.copyFromLocalFile(false,srcPath, dstPath);     
	      //打印文件路径
          System.out.println("Upload to "+conf.get("fs.default.name"));
          System.out.println("------------list files------------"+"\n");
          FileStatus [] fileStatus = fs.listStatus(dstPath);
	      for (FileStatus file : fileStatus){
               System.out.println(file.getPath());
          }
          fs.close();
     }
	 
	 //文件重命名
	 public static void rename(String oldName,String newName) throws IOException{
         Configuration conf = new Configuration();
	     FileSystem fs = FileSystem.get(conf);
	     Path oldPath = new Path(oldName);
	     Path newPath = new Path(newName);
	     boolean isok = fs.rename(oldPath, newPath);
	     if(isok){
	        System.out.println("rename ok!");
	     }else{
	        System.out.println("rename failure");
	     }
	     fs.close();
	 }
	 
	 //删除文件
	 public static void delete(String filePath) throws IOException{
	     Configuration conf = new Configuration();
	     FileSystem fs = FileSystem.get(conf);
	     Path path = new Path(filePath);
	     boolean isok = fs.deleteOnExit(path);
	     if(isok){
	        System.out.println("delete ok!");
	     }else{
	        System.out.println("delete failure");
	     }
	     fs.close();
	 }
	 
	 //创建目录
	 public static void mkdir(String path) throws IOException{
	     Configuration conf = new Configuration();
	     FileSystem fs = FileSystem.get(conf);
	     Path srcPath = new Path(path);
	     boolean isok = fs.mkdirs(srcPath);
	     if(isok){
	        System.out.println("create dir ok!");
	     }else{
	        System.out.println("create dir failure");
	     }
	     fs.close();
	 }

	 //读取文件的内容
	 public static void readFile(String filePath) throws IOException{
         Configuration conf = new Configuration();
	     FileSystem fs = FileSystem.get(conf);
	     Path srcPath = new Path(filePath);
	     InputStream in = null;
	     try {
	       in = fs.open(srcPath);
	       IOUtils.copyBytes(in, System.out, 4096, false); //复制到标准输出流
	     } finally {
	       IOUtils.closeStream(in);
	     }
	}
	 
	//读取文件的内容
	public static void exitsFile(String filePath) throws IOException{
	   Configuration conf = new Configuration();
	   FileSystem fs = FileSystem.get(conf);
	   boolean isExits = fs.exists(new Path(filePath));
	}
	
	public static void renameFile(String filePath,String newfilePath) throws IOException{
	   Configuration conf = new Configuration();
	   FileSystem fs = FileSystem.get(conf);
	   fs.rename(new Path(filePath), new Path(newfilePath));
	}
	
	public static void getBlackPosition(String filePath) throws IOException{
		   Configuration conf = new Configuration();
		   FileSystem fs = FileSystem.get(conf);
		   FileStatus status = fs.getFileStatus(new Path(filePath));
		   BlockLocation localtion[] = fs.getFileBlockLocations(status, 0, status.getLen());
		   int blockLength = localtion.length;
		   for (int i = 0; i < localtion.length; i++) {
			   String hosts[] = localtion[i].getHosts();
			   System.out.println(hosts);
		   }
	}
}
