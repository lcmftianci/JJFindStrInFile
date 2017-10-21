package StrUtils;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javafx.scene.shape.Line;

import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermDocs;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;

import com.sun.javafx.collections.MappingChange.Map;

public class Search {
	
	private String INDEX_STORE_PATH = "E:\\Lucene\\index";
	
	//����Lucene����
	public void indexSrearch(String seachType, String searchKey){
		try {
			System.out.println("++ʹ��������ʽ����++");
			System.out.println("-----------------------------");
			
			//��������λ�ü���IndexSearcher
			IndexSearcher searcher = new IndexSearcher(INDEX_STORE_PATH);
			//����������Ԫ
			Term t = new Term(seachType, searchKey);
			
			//��Term����һ��Query
			Query q = new TermQuery(t);
			
			Date beginTime = new Date();
			//��ȡһ��<documents, frequency> ��ö�ٶ���TermDocs
			TermDocs termDocs = searcher.getIndexReader().termDocs(t);
			
			while (termDocs.next()) {
				//����ĵ����ֹؼ��ִ���
				System.out.print("find" + termDocs.freq() + "matches in");
				//����������ؼ��ʵ��ĵ�
				System.out.println(searcher.getIndexReader().document(termDocs.doc()).getField("filename").stringValue());
			}
			//�������ʱ��
			Date endTime = new Date();
			
			//������ʱ
			long timeofSearch = endTime.getTime() - beginTime.getTime();
			System.out.println("ʹ��������ʽ�����ܺ�ʱ" + timeofSearch + "ms");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//����String������
	public void stringSearch(String keyword, String searchDir){
		System.out.println("++ʹ���ַ���ƥ�䷽ʽ����++");
		System.out.println("----------------------------------");
		
		File filesDir = new File(searchDir);
		
		//����Ŀ¼�ļ����ļ�����
		File[] files = filesDir.listFiles();
		
		//HashMap�����ļ�����ƥ�������
		LinkedHashMap rs = new LinkedHashMap();
		
		//��¼������ʼʱ��
		Date beginTime = new Date();
		
		//���������ļ�
		for(int i = 0; i < files.length; ++i)
		{
			//��ʼ��ƥ�����
			int hits = 0;
			try {
				//��ȡ�ļ�
				BufferedReader br = new BufferedReader(new FileReader(files[i]));
				StringBuffer sb = new StringBuffer();
				String strLine = br.readLine();
				while(strLine != null)
				{
					sb.append(strLine);
					strLine = br.readLine();
				}
				br.close();
				//��StringBufferת����String����������
				String strToSearch = sb.toString();
		
				//��ʼ��fromIndex
				int fromIndex = -keyword.length();
				
				//���ƥ��ؼ���
				while((fromIndex = strToSearch.indexOf(keyword, fromIndex + keyword.length())) != -1){
					hits++;
				}
				//���ļ���ƥ��������뵽HashMap
				rs.put(files[i].getName(), new Integer(hits));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		//�����ѯ���
		Iterator it = rs.keySet().iterator();
		while (it.hasNext()) {
			String fileName = (String)it.next();
			Integer hits = (Integer)rs.get(fileName);
			System.out.println("find " + hits.intValue() + "matches in " + fileName);
		}
		//��¼����ʱ��
		Date endTime = new Date();
		//�õ������ķ�ʱ��
		long timeOfSearch = endTime.getTime() - beginTime.getTime();
		System.out.println("ʹ���ַ��������ܺ�ʱ " + timeOfSearch + " ms");
	}
}
