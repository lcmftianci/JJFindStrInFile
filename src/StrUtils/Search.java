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
	
	//利用Lucene搜索
	public void indexSrearch(String seachType, String searchKey){
		try {
			System.out.println("++使用索引方式搜索++");
			System.out.println("-----------------------------");
			
			//根据索引位置简历IndexSearcher
			IndexSearcher searcher = new IndexSearcher(iNDEX_STORE_PATH);
			//建立搜索单元
			Term t = new Term(seachType, searchKey);
			
			//由Term生成一个Query
			Query q = new TermQuery(t);
			
			Date beginTime = new Date();
			//获取一个<documents, frequency> 的枚举对象TermDocs
			TermDocs termDocs = searcher.getIndexReader().termDocs(t);
			
			while (termDocs.next()) {
				//输出文档出现关键字次数
				System.out.print("find" + termDocs.freq() + "matches in");
				//输出搜索到关键词的文档
				System.out.println(searcher.getIndexReader().document(termDocs.doc()).getField("filename").stringValue());
			}
			//搜索完成时间
			Date endTime = new Date();
			
			//搜索耗时
			long timeofSearch = endTime.getTime() - beginTime.getTime();
			System.out.println("使用索引方式搜索总耗时" + timeofSearch + "ms");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//利用String的搜索
	public void stringSearch(String keyword, String searchDir){
		System.out.println("++使用字符串匹配方式所有++");
		System.out.println("----------------------------------");
		
		File filesDir = new File(searchDir);
		
		File[] files = filesDir.listFiles();
	}
}
