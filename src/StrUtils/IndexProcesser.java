package StrUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import jeasy.analysis.MMAnalyzer;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;


public class IndexProcesser {
	private String INDEX_STORE_PATH = "E:\\Lucene\\index\\";
	
	//��������
	public void createIndex(String inputDir){
		try {
			//MMAnalyzer ��Ϊ�ִʹ��ߴ���һ��IndexWriter
			IndexWriter writer = new IndexWriter(INDEX_STORE_PATH, new MMAnalyzer(), true);
			File filesDir = new File(inputDir);
			
			//ȡ���ļ�����
			File[] files = filesDir.listFiles();
			
			//��������
			for(int i = 0; i < files.length; ++i)
			{
				String fileName = files[i].getName();
				if(fileName.substring(fileName.lastIndexOf(".")).equals(".txt")){
					//����һ���µ�Doc
					Document doc = new Document();
					//Ϊ�ļ�����һ��Filed
					Field field = new Field("filename", files[i].getName(), Field.Store.YES, Field.Index.TOKENIZED);
					doc.add(field);
					
					//Ϊ�ļ����ݴ���һ��Field
					field = new Field("content", loadFileToString(files[i]), Field.Store.NO, Field.Index.TOKENIZED);
					doc.add(field);
					
					writer.addDocument(doc);
				}
			}
			writer.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private String loadFileToString(File file) {
		// TODO Auto-generated method stub
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			StringBuffer sb = new StringBuffer();
			String strLine = br.readLine();
			while(strLine != null){
				sb.append(strLine);
				strLine = br.readLine();
			}
			br.close();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			// TODO: handle exception
		}
	}
}
