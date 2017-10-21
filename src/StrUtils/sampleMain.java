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
		FindPreprocess.preprocess(new File(inputFile), outputDir);
		System.out.print("Middle\n");
		
		IndexProcesser indexProcesser = new IndexProcesser();
		indexProcesser.createIndex(outputDir);
		System.out.print("End");
	}
}
