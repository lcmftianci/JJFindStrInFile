package StrUtils;


import java.io.File;
import java.io.FileReader;
import java.util.Date;

import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermDocs;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;

public class Search {
	
	private String iNDEX_STORE_PATH = "E:\\Lucene\\index";
	
	//����Lucene����
	public void indexSrearch(String seachType, String searchKey){
		try {
			System.out.println("++ʹ��������ʽ����++");
			System.out.println("-----------------------------");
			
			//��������λ�ü���IndexSearcher
			IndexSearcher searcher = new IndexSearcher(iNDEX_STORE_PATH);
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
		
		File[] files = filesDir.listFiles();
	}
}
