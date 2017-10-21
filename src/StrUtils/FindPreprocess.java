package StrUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class FindPreprocess {
	
	//�滻ȫ�Ǳ��
	public static String replace(String strLine)
	{
		//����һ��hashmap,�洢ȫ���ַ��Ͱ���ַ���Ӧ��ϵ
		HashMap map = new HashMap();
		map.put("��", ",");
		map.put("��", ".");
		map.put("��", "<");
		map.put("��", ">");
		map.put("��", "|");
		map.put("��", "<");
		map.put("��", ">");
		map.put("��", "[");
		map.put("��", "]");
		map.put("�t", "?");
		map.put("��", "?");
		map.put("��", "\"");
		map.put("��", "\"");
		map.put("��", ":");
		map.put("��", ",");
		map.put("��", "(");
		map.put("��", ")");
		map.put("��", "[");
		map.put("��", "]");
		map.put("��", "-");
		map.put("��", "~");
		map.put("��", "!");
		map.put("�F", "'");
		map.put("��", "1");
		map.put("��", "2");
		map.put("��", "3");
		map.put("��", "4");
		map.put("��", "5");
		map.put("��", "6");
		map.put("��", "7");
		map.put("��", "8");
		map.put("��", "9");
		
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
	
	//�������ļ������ֶ��滻
	public static File charactorProcess(File file, String destFile)  throws Exception{
		//���������
		BufferedWriter writer = new BufferedWriter(new FileWriter(destFile));
		//����������
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		//��ȡ�ļ�
		String strLine = reader.readLine();
		while (strLine != null) {
			//����replace�滻�ַ���
			String newLine = replace(strLine);
			
			//�滻����ַ���д�����ļ�
			writer.write(newLine);
			strLine = reader.readLine(); //�Ƶ���һ��
		}
		reader.close();
		writer.close();
		return new File(destFile);
	}
	
	//�и��ļ�
	public static void splitToSmallFiles(File file, String outputpath) throws IOException
	{
		//�ļ�������
		int fileNum = 0;
		
		//���嵥���ļ���󳤶�
		int MAX_SIZE = 10240;
		
		//�����ļ�������
		BufferedWriter writer = null;
		
		//�����ļ�������
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		//�����ַ���������
		StringBuffer buffer = new StringBuffer();
		
		//ѭ��������ȡÿ���ַ���
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
	
	//ͬ����
	public static void preprocess(File file, String outputDir) {
		try {
			splitToSmallFiles(file, outputDir);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
