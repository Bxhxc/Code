package com.hgc.java.algorithm;

import net.sf.classifier4J.summariser.ISummariser;
import net.sf.classifier4J.summariser.SimpleSummariser;

/**
 * @Description:功能自动提取出文章摘要
 * TF-IDF与余弦相似性的应用
 * @author guicheng.huang
 * @date: 2017年5月18日 上午10:37:07
 * @version V0.0.1
 */
public class AutomaticExtraction {

	 public static void main(String args[]) {  
         
	        String input = "My father was a self-taught mandolin player. He was one of the best string instrument players in our town. He could not read music, but if he heard a tune a few times, he could play it. When he was younger, he was a member of a small country music band. They would play at local dances and on a few occasions would play for the local radio station. He often told us how he had auditioned and earned a position in a band that featured Patsy Cline as their lead singer. He told the family that after he was hired he never went back. Dad was a very religious man. He stated that there was a lot of drinking and cursing the day of his audition and he did not want to be around that type of environment.";

	              
	        ISummariser summariser = new SimpleSummariser();  
	          
	        String result = summariser.summarise(input, 1);  
	        System.out.println(result);  
	 }  
}
