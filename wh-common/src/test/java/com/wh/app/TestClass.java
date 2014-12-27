package com.wh.app;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Date;

//import net.paoding.analysis.analyzer.PaodingAnalyzer;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
//import org.apache.lucene.analysis.Analyzer;
//import org.apache.lucene.analysis.standard.StandardAnalyzer;
//import org.apache.lucene.document.Field;
//import org.apache.lucene.index.IndexReader;
//import org.apache.lucene.index.IndexWriter;
//import org.apache.lucene.index.IndexWriterConfig;
//import org.apache.lucene.index.IndexWriterConfig.OpenMode;
//import org.apache.lucene.queryParser.ParseException;
//import org.apache.lucene.queryParser.QueryParser;
//import org.apache.lucene.search.IndexSearcher;
//import org.apache.lucene.search.Query;
//import org.apache.lucene.search.ScoreDoc;
//import org.apache.lucene.search.TopDocs;
//import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
//import org.apache.lucene.store.Directory;
//import org.apache.lucene.store.FSDirectory;
//import org.apache.lucene.util.Version;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.my.common.util.HttpUtils;


public class TestClass {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		String user_name ="gh_adf3ed2334be";
		String key = "aae2f692d81876842d0138789dfa9e4b82d55633a801a6d1ddd8de8ae4136e11c8287f874ca147d5ea2eb9bcf9b9622c";
		System.out.println(key.length());
		Date date = new Date(1401680737);
		
		//1401681189266
		//1401680737
		//1401680737
		//System.out.println(System.currentTimeMillis());
		
		String md5 = "aae2f692d8187684";
		
		System.out.println(DigestUtils.md5Hex("whale_studio"));
	}
	@Test
	public void testWx() throws IOException{
		Document doc = Jsoup.connect("http://www.baidu.com/").get();
		Elements els = doc.select("img");
		for(Element el:els){
			System.out.println(el.attr("src", "test"));
		}
	}
	
	@Test
	public  void testHttp() {
		String url = "http://weixin.sogou.com/gzhjs?cb=sogou.weixin.gzhcb&openid=oIWsFtxEOaH_uvcGuOmNIfoVLBQ0&page=1&t=1402489723418";
		HttpClient client = new HttpClient(); // 实例化httpClient
		HttpMethod method = new GetMethod(url); //
		//method.addRequestHeader("Host", "short.weixin.qq.com");  
		method.addRequestHeader("Cache-Control", "max-age=0");  
		method.addRequestHeader("Connection", "keep-alive");
		method.addRequestHeader("accept", "*/*");
		method.addRequestHeader("User-Agent","Android QQMail HTTP Client");
		method.addRequestHeader("Content-Type","application/octet-stream");
		//method.addRequestHeader("User-Agent","Android");
		//使用系统提供的默认的恢复策略
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, 
			    new DefaultHttpMethodRetryHandler()); 
		client.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
		client.getHttpConnectionManager().getParams().setSoTimeout(10000); 
		String responseContent = "";
		try {
			int stateCode = client.executeMethod(method); // 执行
			if(stateCode != HttpStatus.SC_OK){
				System.out.println("Method failed: " + method.getStatusLine());
				return;
			}
			InputStream jsonStr;
			jsonStr = method.getResponseBodyAsStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int i = -1;
			while ((i = jsonStr.read()) != -1) {
				baos.write(i);
			}
			responseContent = baos.toString("utf-8");
			jsonStr.close();
			baos.close();
			method.releaseConnection();
			System.out.println(responseContent);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void jsoupTest() throws IOException{
		//String url = "https://wx2.qq.com/cgi-bin/mmwebwx-bin/webwxcheckurl?uin=2768183621&sid=m4Z9lc%2BsgTxKxmUg&skey=%40crypt_3d539bce_dc97c55ffff78ef9b6f2edffd4dde6e6&deviceid=e664179215001909&opcode=2&requrl=http%3A%2F%2Fmp.weixin.qq.com%2Fmp%2Fgetmasssendmsg%3F__biz%3DMjM5NDAxMTE0MA%3D%3D%23wechat_webview_type%3D1%26wechat_redirect&scene=1&username=wxid_4flqugtqlbdo21";
		//String url = "https://wx.qq.com";
		String uuid_url = " https://login.weixin.qq.com/jslogin?appid=wx782c26e4c19acffb&redirect_uri=https%3A%2F%2Fwx2.qq.com%2Fcgi-bin%2Fmmwebwx-bin%2Fwebwxnewloginpage&fun=new&lang=zh_CN";
		String url = "http://weixin.sogou.com/gzhjs?cb=sogou.weixin.gzhcb&openid=oIWsFtxEOaH_uvcGuOmNIfoVLBQ0&page=1&t=1402489723418";
		Document doc = Jsoup.connect(url)
				.referrer("http://weixin.sogou.com/gzh?openid=oIWsFtxEOaH_uvcGuOmNIfoVLBQ0")
				.userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.172 Safari/537.22")
				.timeout(20000)
				//.cookie("SUID", "87084D743DCA0B0A539331A200010F81")
				//.cookie("CXID","2C64DAB3ED939E823020D1960279676A")
				//.cookie("SUV","003B59B2744D0887539841057E153749")
				//.cookie("ad","NJp3Syllll2F68pAlllllVnYS7llllllN91wPZllllollllljZlll5@@@@@@@@@@")
				//.cookie("IPLOC","CN4403")
				//.cookie("SNUID","E060251C676C9CE9FC716A8568B47409")
				.get();
		System.out.println(doc);
      
	}
	
	@Test
	public void testSogou(){
		String url = "http://weixin.sogou.com/gzhjs?cb=sogou.weixin.gzhcb&openid=oIWsFtxEOaH_uvcGuOmNIfoVLBQ0&page=1&t=1402489723418";
		String content = HttpUtils.getInstance().doGet(url, "utf-8",null, "");
		String json = content.substring(content.indexOf("(")+1,content.lastIndexOf(")"));
		System.out.println(json);
		
	}
	
//	@Test
//	public void testLucene() throws IOException{
//        File fileDir = new File("F:\\source");    
//    
//        /* 这里放索引文件的位置 */    
//        File indexDir = new File("F:\\index");    
//        Directory dir = FSDirectory.open(indexDir);  
//        Analyzer luceneAnalyzer = new PaodingAnalyzer(); 
//        IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_35,luceneAnalyzer);  
//        iwc.setOpenMode(OpenMode.CREATE);  
//        IndexWriter indexWriter = new IndexWriter(dir,iwc);    
//        File[] textFiles = fileDir.listFiles();    
//        long startTime = new Date().getTime();    
//            
//        //增加document到索引去    
//        for (int i = 0; i < textFiles.length; i++) {    
//            if (textFiles[i].isFile()    
//                    && textFiles[i].getName().endsWith(".txt")) {    
//                System.out.println("File " + textFiles[i].getCanonicalPath()    
//                        + "正在被索引....");    
//                String temp = FileReaderAll(textFiles[i].getCanonicalPath(),    
//                        "GBK");    
//                System.out.println(temp);    
//                org.apache.lucene.document.Document document = new org.apache.lucene.document.Document();    
//                Field FieldPath = new Field("path", textFiles[i].getPath(),    
//                        Field.Store.YES, Field.Index.NO);    
//                Field FieldBody = new Field("body", temp, Field.Store.YES,    
//                        Field.Index.ANALYZED,    
//                        Field.TermVector.WITH_POSITIONS_OFFSETS);    
//                document.add(FieldPath);    
//                document.add(FieldBody);    
//                indexWriter.addDocument(document);    
//            }    
//        }    
//        indexWriter.close();    
//            
//        //测试一下索引的时间    
//        long endTime = new Date().getTime();    
//        System.out    
//                .println("这花费了"    
//                        + (endTime - startTime)    
//                        + " 毫秒来把文档增加到索引里面去!"    
//                        + fileDir.getPath());    
//    }    
//    
//    public  String FileReaderAll(String FileName, String charset)    
//            throws IOException {    
//        BufferedReader reader = new BufferedReader(new InputStreamReader(    
//                new FileInputStream(FileName), charset));    
//        String line = new String();    
//        String temp = new String();    
//            
//        while ((line = reader.readLine()) != null) {    
//            temp += line;    
//        }    
//        reader.close();    
//        return temp;    
//    } 
//    
//    @Test
//    public void testQuery() throws IOException{
//    	 String index = "F:\\index";         //搜索的索引路径  
//         IndexReader reader = IndexReader.open(FSDirectory.open(new File(index)));  
//         IndexSearcher searcher = new IndexSearcher(reader);    
//           
//         ScoreDoc[] hits = null;    
//         String queryString = "绝对秋香";   //搜索的关键词  
//         Query query = null;    
//           
//     
//         Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_35);    
//         try {    
//             QueryParser qp = new QueryParser(Version.LUCENE_35,"body", analyzer);    
//             query = qp.parse(queryString);    
//         } catch (ParseException e) {    
//         }    
//         if (searcher != null) {    
//             TopDocs results = searcher.search(query,10);    //返回最多为10条记录  
//             hits = results.scoreDocs; 
//             if (hits.length > 0) {    
//                 System.out.println("找到:" + hits.length + " 个结果!");    
//             }    
//             for(ScoreDoc doc:hits){
//            	 org.apache.lucene.document.Document targetDoc = searcher.doc(doc.doc);
//            	 System.out.println(targetDoc.get("path"));
//            	 SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<font color='red'><strong>", "</strong></font>"); 
//             }
//             searcher.close();  
//         }   
//          
//     }    

	}

