package StrUtils;

import java.io.File;


public class sampleMain {

	public static void main(String[] args) {
		System.out.print("Start\n");
		String inputFile = "E:\\Lucene\\book.txt";
		
		String outputDir = "E:\\Lucene\\output\\";
	
		if(!new File(outputDir).exists())
		{
			new File(outputDir).mkdir();
			//new File(outputDir);
		}
		//FindPreprocess.preprocess(new File(inputFile), outputDir);
		//System.out.print("Middle\n");
		
		//IndexProcesser indexProcesser = new IndexProcesser();
		//indexProcesser.createIndex(outputDir);
		//System.out.print("End");
		
		Search search = new Search();
		search.indexSrearch("content", "保尔");
		
		//插入一个分隔符
		System.out.println();
		
		//通过字符串查找
		search.stringSearch("保尔", outputDir);
	}
}
