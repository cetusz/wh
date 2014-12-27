package com.wh.app.web.searh;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.my.common.util.DateUtils;
import com.my.common.util.PropertiesFileUtil;
import com.my.mybatis.support.Page;
import com.wh.app.web.model.edit.EassayEdit;
import com.wh.app.web.model.edit.PublicAccountEdit;
import com.wh.app.web.model.query.EassayEditQuery;
import com.wh.app.web.model.query.PublicAccountEditQuery;
import com.wh.app.web.service.edit.EassayEditService;
import com.wh.app.web.service.edit.PublicAccountEditService;

@Component
public class IndexService {
	
	Logger loger = Logger.getLogger(IndexService.class);
	@Autowired PublicAccountEditService publicAccountService;
	@Autowired EassayEditService eassayEditService;
	private static String accountIndexPath;
	private static String essayIndexpPath;
	static{
		accountIndexPath = PropertiesFileUtil.getStringValue("config.properties", "account-index-path");
		essayIndexpPath = PropertiesFileUtil.getStringValue("config.properties", "eassay-index-path");
	}
	
	/**
	 * 增量索引账户
	 */
	public void executeAccountIndex(){
		Date date = new Date();
		Date currentDate = DateUtils.getSimpleDate(date);
		indexAccount(currentDate,IndexType.APPEND);
	}
	
	/**
	 * 增量索引文章
	 */
	public void executeEassayIndex(){
		Date date = new Date();
		Date currentDate = DateUtils.getSimpleDate(date);
		indexEassay(currentDate,IndexType.APPEND);
	}
	/**
	 * 索引账户
	 */
	public void indexAccount(Date currentDate,IndexType indexType){
		File indexDir = new File(accountIndexPath);
		if(!indexDir.isDirectory()){
			indexDir.mkdir();
		}
		try {
			Directory dir = FSDirectory.open(indexDir);
			Analyzer luceneAnalyzer = new PaodingAnalyzer();  
			//Analyzer luceneAnalyzer = new StandardAnalyzer(Version.LUCENE_36);
	        IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_36,luceneAnalyzer);  
	        if(indexType.equals(IndexType.ALL)){
	        	iwc.setOpenMode(OpenMode.CREATE);  
	        }else{
	        	iwc.setOpenMode(OpenMode.APPEND);  
	        }
	        IndexWriter indexWriter = new IndexWriter(dir,iwc); 
	        PublicAccountEditQuery query = new PublicAccountEditQuery();
	        query.setCreateTime(currentDate);
	        List<PublicAccountEdit> accounts = publicAccountService.selectList(query, null);
	        for(PublicAccountEdit account:accounts){
	        	Document doc = new Document();
	        	Field chineseNameField = new Field("chineseName", account.getChineseName(),    
	                        Field.Store.YES, Field.Index.ANALYZED);
	        	Field accountIdField = new Field("accountId", account.getAccountId(),    
                        Field.Store.YES, Field.Index.ANALYZED);
	        	Field qrCodeUrlField = new Field("qrCodeUrl", account.getQrCodeUrl(),    
                        Field.Store.YES, Field.Index.NOT_ANALYZED); 
	        	
	        	Field headImgField = new Field("headImg", account.getHeadImg(),    
                        Field.Store.YES, Field.Index.NOT_ANALYZED);
	        	doc.add(chineseNameField);
	        	doc.add(accountIdField);
	        	doc.add(qrCodeUrlField);
	        	doc.add(headImgField);
	        	indexWriter.addDocument(doc);
	        	
	        }
	        indexWriter.close();
	        
		} catch (IOException e) {
			loger.error("error", e);
		}  
       
	}
	
	/**
	 * 索引文章
	 * @param currentDate 起始时间点
	 * @param indexType 索引类型 增量还是全量
	 */
	public void indexEassay(Date currentDate,IndexType indexType){
		File indexDir = new File(essayIndexpPath);
		if(!indexDir.isDirectory()){
			indexDir.mkdir();
		}
		IndexWriter indexWriter = null;
		try {
			Directory dir = FSDirectory.open(indexDir);
			Analyzer luceneAnalyzer = new PaodingAnalyzer(); 
			//Analyzer luceneAnalyzer =  new SimpleAnalyzer();
			//Analyzer luceneAnalyzer = new IKAnalyzer();
	        IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_36,luceneAnalyzer);  
	        if(indexType.equals(IndexType.ALL)){
	        	iwc.setOpenMode(OpenMode.CREATE);  
	        }else{
	        	iwc.setOpenMode(OpenMode.APPEND);  
	        }
	        indexWriter = new IndexWriter(dir,iwc); 
	        EassayEditQuery query = new EassayEditQuery();
	        query.setCreateTime(currentDate);
	        int page = 1;
	        int pageSize = 20;
	        int pageTotal = 1;
	        Long lastId = 0L;
	        query.setLastId(lastId);
	        while(page<=pageTotal){
		        Page<EassayEdit> pager = new Page<EassayEdit>(pageSize,page);
		        eassayEditService.getPageList(pager, query, null);
		        pageTotal = pager.getTotalPage();
		        List<EassayEdit> list = pager.getResult();
		        for(EassayEdit eassay:list){
		        	Document doc = new Document();
		        	Field titleField = new Field("title", eassay.getTitle(),    
		                        Field.Store.YES, Field.Index.ANALYZED);
		        	Field introField = new Field("intro", eassay.getIntro()==null?"":eassay.getIntro(),    
	                        Field.Store.YES, Field.Index.ANALYZED);
		        	Field pubDateField = new Field("pubDateStr", DateUtils.fomateDate(eassay.getPubDate(), "yyyy-MM-dd"),  
	                        Field.Store.YES, Field.Index.NOT_ANALYZED);
		        	Field contentUrlField = new Field("contentUrl", eassay.getContentUrl(),    
	                        Field.Store.YES, Field.Index.NOT_ANALYZED);
		         	Field accountNamefield = new Field("accountName", eassay.getAccountName(),    
	                        Field.Store.YES, Field.Index.NOT_ANALYZED);
		         	doc.add(new Field("_name", "eassay_doc", Field.Store.YES,Field.Index.NOT_ANALYZED));
		        	doc.add(titleField);
		        	doc.add(introField);
		        	doc.add(pubDateField);
		        	doc.add(contentUrlField);
		        	doc.add(accountNamefield);
		        	indexWriter.addDocument(doc);
		        	
		        }
		        query.setLastId(pager.getResult().get(pager.getResult().size()-1).getId());
		        page++;
	        }
	        //indexWriter.close();
		} catch (IOException e) {
			loger.error("error", e);
		}finally{
			try {
				indexWriter.close();
			} catch (CorruptIndexException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
       
	}
	public static enum IndexType{
		APPEND,ALL;
	}
	
}

