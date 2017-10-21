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
	
	//常见索引
	public void createIndex(String inputDir){
		try {
			//MMAnalyzer 作为分词工具创建一个IndexWriter
			IndexWriter writer = new IndexWriter(INDEX_STORE_PATH, new MMAnalyzer(), true);
			File filesDir = new File(inputDir);
			
			//取得文件数组
			File[] files = filesDir.listFiles();
			
			//遍历数组
			for(int i = 0; i < files.length; ++i)
			{
				String fileName = files[i].getName();
				if(fileName.substring(fileName.lastIndexOf(".")).equals(".txt")){
					//创建一个新的Doc
					Document doc = new Document();
					//为文件创建一个Filed
					Field field = new Field("filename", files[i].getName(), Field.Store.YES, Field.Index.TOKENIZED);
					doc.add(field);
					
					//为文件内容创建一个Field
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
