package com.hgc.java.algorithm;

import java.io.IOException;
import java.util.List;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
/**
 * @Description:文本提取
 * @author guicheng.huang
 * @date: 2017年5月31日 下午5:43:12
 * @version V0.0.1
 */
public class TextExtraction {

	 public static void main(String[] args) throws IOException {  
		 List<Term> parse = ToAnalysis.parse("WawaCluster[] clusters = kmeans.Clusters;");    
		 System.out.println(parse);
	 }  
}
