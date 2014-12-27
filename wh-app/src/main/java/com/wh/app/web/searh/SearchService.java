package com.wh.app.web.searh;

import java.io.File;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TopFieldCollector;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Component;

import com.my.common.util.PropertiesFileUtil;
import com.wh.app.web.model.search.AccountSearchVo;
import com.wh.app.web.model.search.EassaySearchVo;
import com.wh.app.web.model.search.Pager;

@Component
public class SearchService {

	private static String accountIndexPath;
	private static String essayIndexpPath;
	static{
		accountIndexPath = PropertiesFileUtil.getStringValue("config.properties", "account-index-path");
		essayIndexpPath = PropertiesFileUtil.getStringValue("config.properties", "eassay-index-path");
	}
	/**
	 * 分页搜索账户
	 * @param keyWord
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public Pager<AccountSearchVo> searchAccount(String keyWord,int page,int pageSize)throws Exception{
		Pager<AccountSearchVo> result = new Pager<AccountSearchVo>();
		File indexFile = new File(accountIndexPath); 
		IndexReader reader = IndexReader.open(FSDirectory.open(indexFile));   
        //庖丁解牛分词器   
        Analyzer analyzer = new PaodingAnalyzer();  
		//Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_36);
        QueryParser parser = new QueryParser(Version.LUCENE_36,"chineseName",analyzer);   
        IndexSearcher searcher  = new IndexSearcher(reader);   
        Query query = parser.parse(keyWord); 
        Sort sort=new Sort(new SortField[]{new SortField("chineseName", SortField.SCORE, true)});
    	TopFieldCollector topCollector = TopFieldCollector.create(sort, page * pageSize, false, false, false, false);
		searcher.search(query, topCollector);
		TopDocs topDocs = topCollector.topDocs((page - 1) * pageSize, pageSize);
		int totalCount = topDocs.totalHits;
		int pageCount = totalCount / pageSize + (totalCount % pageSize == 0 ? 0 : 1);
        for(ScoreDoc scoreDoc:topDocs.scoreDocs){
        	Document document = searcher.doc(scoreDoc.doc);
        	String chineseName = document.get("chineseName");
        	String accountId = document.get("accountId");
        	String qrCodeUrl = document.get("qrCodeUrl");
        	String headImg = document.get("headImg");
        	AccountSearchVo account = new AccountSearchVo();
			SimpleHTMLFormatter sHtmlF = new SimpleHTMLFormatter("<b><font color='red'>", "</font></b>");   
			  //高亮对象   
			Highlighter highlighter = new Highlighter(sHtmlF,new QueryScorer(query));   
			  //设置高亮附近的字数   
			highlighter.setTextFragmenter(new SimpleFragmenter(50));  
			String chineseNameHighLight = highlighter.getBestFragment(analyzer,"chineseName",chineseName);
			account.setAccountId(accountId);
			account.setChineseName(chineseNameHighLight);
			account.setQrCodeUrl(qrCodeUrl);
			account.setHeadImg(headImg);
			result.getResult().add(account);
        }  
        searcher.close();
    	result.setPage(page);
		result.setPageCount(pageCount);
		result.setPageSize(pageSize);
		result.setTotalCount(totalCount);
        return result;   
    }   	
	
	public Pager<EassaySearchVo> searchEassay(String keyWord,int page,int pageSize) throws Exception{
		Pager<EassaySearchVo> result = new Pager<EassaySearchVo>();
		File indexFile = new File(essayIndexpPath); 
		IndexReader reader = IndexReader.open(FSDirectory.open(indexFile));  
		IndexSearcher searcher  = new IndexSearcher(reader);
		Analyzer analyzer = new PaodingAnalyzer();
		//Analyzer analyzer = new SimpleAnalyzer();
		BooleanClause.Occur[] clauses = { BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD };  
	    Query query = MultiFieldQueryParser.parse(Version.LUCENE_36,new String[]{keyWord,keyWord}, new String[]{"title","intro"}, clauses,analyzer);
	    Sort sort=new Sort(new SortField[]{new SortField("title", SortField.SCORE, true),new SortField("pubDate", SortField.STRING, true)});
	    TopFieldCollector topCollector = TopFieldCollector.create(sort, page * pageSize, false, false, false, false);
	    searcher.search(query, topCollector);
		TopDocs topDocs = topCollector.topDocs((page - 1) * pageSize, pageSize);
		int totalCount = topDocs.totalHits;
		int pageCount = totalCount / pageSize + (totalCount % pageSize == 0 ? 0 : 1);
		for(ScoreDoc scoreDoc:topDocs.scoreDocs){
			Document document = searcher.doc(scoreDoc.doc);
			String title = document.get("title");
			String intro = document.get("intro");
			String pubDateStr = document.get("pubDateStr");
			String contentUrl = document.get("contentUrl");
			String accountName = document.get("accountName");
			EassaySearchVo edit = new EassaySearchVo();
			
			SimpleHTMLFormatter sHtmlF = new SimpleHTMLFormatter("<b><font color='red'>", "</font></b>");   
			  //高亮对象   
			Highlighter highlighter = new Highlighter(sHtmlF,new QueryScorer(query));   
			  //设置高亮附近的字数   
			highlighter.setTextFragmenter(new SimpleFragmenter(100));  
			String titleHighLight = highlighter.getBestFragment(analyzer,"title",title);
			titleHighLight = titleHighLight==null?title:titleHighLight;
			if(intro!=null){
				String introHighLight = highlighter.getBestFragment(analyzer,"intro",intro);
				edit.setIntro(introHighLight);
			}
			edit.setTitle(titleHighLight);
			edit.setPubDate(pubDateStr);
			edit.setContentUrl(contentUrl);
			edit.setAccountName(accountName);
			result.getResult().add(edit);
		}
	    searcher.close();
    	result.setPage(page);
		result.setPageCount(pageCount);
		result.setPageSize(pageSize);
		result.setTotalCount(totalCount);
        return result;   
	}

}
