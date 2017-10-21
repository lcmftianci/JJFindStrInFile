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
	
	//利用Lucene搜索
	public void indexSrearch(String seachType, String searchKey){
		try {
			System.out.println("++使用索引方式搜索++");
			System.out.println("-----------------------------");
			
			//根据索引位置简历IndexSearcher
			IndexSearcher searcher = new IndexSearcher(INDEX_STORE_PATH);
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
		
		//返回目录文件夹文件数组
		File[] files = filesDir.listFiles();
		
		//HashMap保存文件名和匹配次数对
		LinkedHashMap rs = new LinkedHashMap();
		
		//记录搜索开始时间
		Date beginTime = new Date();
		
		//遍历所有文件
		for(int i = 0; i < files.length; ++i)
		{
			//初始化匹配次数
			int hits = 0;
			try {
				//读取文件
				BufferedReader br = new BufferedReader(new FileReader(files[i]));
				StringBuffer sb = new StringBuffer();
				String strLine = br.readLine();
				while(strLine != null)
				{
					sb.append(strLine);
					strLine = br.readLine();
				}
				br.close();
				//将StringBuffer转化成String，便于搜索
				String strToSearch = sb.toString();
		
				//初始化fromIndex
				int fromIndex = -keyword.length();
				
				//逐个匹配关键字
				while((fromIndex = strToSearch.indexOf(keyword, fromIndex + keyword.length())) != -1){
					hits++;
				}
				//将文件名匹配次数加入到HashMap
				rs.put(files[i].getName(), new Integer(hits));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		//输出查询结果
		Iterator it = rs.keySet().iterator();
		while (it.hasNext()) {
			String fileName = (String)it.next();
			Integer hits = (Integer)rs.get(fileName);
			System.out.println("find " + hits.intValue() + "matches in " + fileName);
		}
		//记录结束时间
		Date endTime = new Date();
		//得到搜索耗费时间
		long timeOfSearch = endTime.getTime() - beginTime.getTime();
		System.out.println("使用字符串搜索总耗时 " + timeOfSearch + " ms");
	}
}
