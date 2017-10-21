package StrUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class FindPreprocess {
	
	//替换全角标点
	public static String replace(String strLine)
	{
		//创建一个hashmap,存储全角字符和半角字符对应关系
		HashMap map = new HashMap();
		map.put("，", ",");
		map.put("。", ".");
		map.put("〈", "<");
		map.put("〉", ">");
		map.put("‖", "|");
		map.put("《", "<");
		map.put("》", ">");
		map.put("〔", "[");
		map.put("〕", "]");
		map.put("﹖", "?");
		map.put("？", "?");
		map.put("“", "\"");
		map.put("”", "\"");
		map.put("：", ":");
		map.put("、", ",");
		map.put("（", "(");
		map.put("）", ")");
		map.put("【", "[");
		map.put("】", "]");
		map.put("—", "-");
		map.put("～", "~");
		map.put("！", "!");
		map.put("‵", "'");
		map.put("①", "1");
		map.put("②", "2");
		map.put("③", "3");
		map.put("④", "4");
		map.put("⑤", "5");
		map.put("⑥", "6");
		map.put("⑦", "7");
		map.put("⑧", "8");
		map.put("⑨", "9");
		
		int  length = strLine.length();
		for(int i = 0; i < length; ++i)
		{
			String charat = strLine.substring(i, i+1);
			if(map.get(charat) != null)
			{
				strLine = strLine.replace(charat, (String)map.get(charat));
			}
		}
		return strLine;
	}
	
	//将整个文件中文字都替换
	public static File charactorProcess(File file, String destFile)  throws Exception{
		//创建输出流
		BufferedWriter writer = new BufferedWriter(new FileWriter(destFile));
		//创建输入流
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		//读取文件
		String strLine = reader.readLine();
		while (strLine != null) {
			//调用replace替换字符穿
			String newLine = replace(strLine);
			
			//替换后的字符串写到新文件
			writer.write(newLine);
			strLine = reader.readLine(); //移到下一行
		}
		reader.close();
		writer.close();
		return new File(destFile);
	}
	
	//切割文件
	public static void splitToSmallFiles(File file, String outputpath) throws IOException
	{
		//文件计数器
		int fileNum = 0;
		
		//定义单个文件最大长度
		int MAX_SIZE = 10240;
		
		//创建文件输入流
		BufferedWriter writer = null;
		
		//创建文件输入流
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		//简历字符串缓冲区
		StringBuffer buffer = new StringBuffer();
		
		//循环遍历读取每行字符串
		String strLine = reader.readLine();
		while(strLine != null)
		{
			buffer.append(strLine).append("\r\n");
			if(buffer.toString().getBytes().length >= MAX_SIZE)
			{
				File FileTmp = new File(outputpath + "output" + fileNum + ".txt");
				if (!FileTmp.exists()) {
					FileTmp.createNewFile();
				}
				writer = new BufferedWriter(new FileWriter(FileTmp));
				writer.write(buffer.toString());
				writer.flush();
				writer.close();
				fileNum++;
				buffer = new StringBuffer();
			}
			strLine = reader.readLine();
		}
		
		writer = new BufferedWriter(new FileWriter(new File(outputpath + "output" + fileNum + ".txt")));
		writer.write(buffer.toString());
		writer.close();
		reader.close();
	}
	
	//同意借口
	public static void preprocess(File file, String outputDir) {
		try {
			splitToSmallFiles(file, outputDir);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
