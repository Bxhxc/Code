package com.hgc.java.algorithm.bigDataAnalysis.cluster.partitionClustering;

import org.apache.mahout.text.SequenceFilesFromDirectory;

import com.alibaba.fastjson.parser.Keywords;

/**
 * @Description:硬聚类->划分->Kmeans 算法 & Kmeans++算法
 * @author guicheng.huang
 * @date: 2017年6月15日 上午10:04:09
 * @version V0.0.1
 */
public class Kmeans {
   //     ---------------#聚类算法的定义#---------------
   //聚类就是按照    #某个特定标准(如距离准则)#   把一个数据集分割成不同的类或簇，使得同一个簇内的数据对象的相似性尽可能大，
   //同时不在同一个簇中的数据对象的差异性也尽可能地大。即聚类后同一类的数据尽可能聚集到一起，不同数据尽量分离。
  
   //  ---------------#Kmeans算法的定义#---------------
   //K-Means是聚类算法中的一种，其中K表示类别数，Means表示均值。顾名思义K-Means是一种通过均值对数据点进行聚类的算法。
   //K-Means算法通过预先设定的K值及每个类别的#初始质心对相似#的数据点进行划分。并通过划分后的均值迭代优化获得最优的聚类结果。
  
   //  ---------------#Kmeans算法的问题#---------------
   //1.如何理解K-Means算法？
   //2.如何寻找K值及初始质心？>>1.需要人工来指定，建议根据实际的业务需求  or  2.通过层次聚类(Hierarchical Clustering)的方法获得数据的类别数量作为选择K值的参考
   //3.如何应用K-Means算法处理数据？
   //4.mahout kmeans算法的使用
   //5.如何将K-Means数据抽象出来(实际数据如何转换成算法能够)
	
   //  ---------------#Kmeans算法的步骤#---------------
   //1.初始化K值(分多少类),随机选取中心点
   //2.计算数据点与中心点的相似度(如聚类)，划分出K类
   //3.计算均值(means,如:每类的中心点),reapt 2，直到收敛
   //4.得到K类
	
   //  ---------------#Kmeans++算法#  k-means++就是选择初始中心节点的一种算法---------------
   //  ---------------#Kmeans++算法思想#  初始的聚类中心之间的相互距离要尽可能的远---------------
   //  ---------------#Kmeans++算法的思路#---------------
   //1.从输入的数据点集合中随机选择一个点作为第一个聚类中心
   //2.对于数据集中的每一个点x，计算它与最近聚类中心(指已选择的聚类中心)的距离D(x)
   //3.选择一个新的数据点作为新的聚类中心，选择的原则是：D(x)较大的点，被选取作为聚类中心的概率较大
   //4.重复2和3直到k个聚类中心被选出来
   //5.利用这k个初始的聚类中心来运行标准的k-means算法
	
   //先从我们的数据库随机挑个随机点当“种子点”。
   //对于每个点，我们都计算其和最近的一个“种子点”的距离D(x)并保存在一个数组里，然后把这些距离加起来得到Sum(D(x))。
   //然后，再取一个随机值，用权重的方式来取计算下一个“种子点”。这个算法的实现是，先取一个能落在Sum(D(x))中的随机值Random，然后用Random -= D(x)，直到其<=0，此时的点就是下一个“种子点”。
   //重复第（2）和第（3）步直到所有的K个种子点都被选出来。
   //进行K-Means算法。
   
   //  ---------------#Kmeans算法的应用#  文本聚类---------------
   //  文本聚类就是从很多文档中把一些 内容相似的文档聚为一类
   //  基于hadoop文本聚类 设计
   //  文本聚类:步骤
   //1.文本表示通常采用向量空间模型(vector space model,VSM)
   //2.将文档表示为加权的特征向 量：D=D(T1，W1；T2，W2；…；Tn，Wn)
	
   

   public static void main(String[] args) {

	String   [] arg ={"-i","C:\\Users\\guicheng.huang.BWH\\Desktop\\a.txt","-o","C:\\Users\\guicheng.huang.BWH\\Desktop\\data","-ow"};
	try {
		SequenceFilesFromDirectory.main(arg);
	} catch (Exception e) {
		e.printStackTrace();
	}
	
   }
   
	
}
