package com.wh.app.web.crawler.worker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.wh.app.web.crawler.manager.PublicAccountManager;
import com.wh.app.web.model.raw.PublicAccountRaw;

public class PublicAccountWorker extends BaseWorker implements Runnable{
	
	Logger loger = Logger.getLogger(PublicAccountWorker.class);
	
	private static List<String> visitedAccount = new ArrayList<String>();
	
	private static LinkedList<String> failureUrls = new LinkedList<String>();
	
    private int retryConnectTimes = 20;
	
	
	public PublicAccountWorker(String entryUrl){
		System.out.println("crawlering url:"+entryUrl);
		this.crawlUrl = entryUrl;
	}
	
	public PublicAccountWorker(){
		
	}
	
	@Override
	public void run() {
		loger.info("entryurl:"+ crawlUrl +" executing!");
		for(int index =0;index<=180;index+=20){
			String url = String.format(crawlUrl, index);
			Document doc = null;
			try {
				doc = getDocument(url,"list");
				extractList(doc);
				
			} catch (IOException e) {
				e.printStackTrace();
				for(int i=0;i<retryConnectTimes;i++){
					System.out.println("url:"+url+" request "+ i);
					try {
						doc = getDocument(url,"list");
						if(doc!=null){
							extractList(doc);
							break;
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				synchronized(this){
					if(doc==null){
						if(!failureUrls.contains(url)){
							failureUrls.add(url);
						}
					}
				}
				continue;
			}
			
		
		}
		//对爬取失败的地址再执行爬取
		while(failureUrls.size()>0){
			String url = failureUrls.pollFirst();
			Document doc = null;
			try {
				doc = getDocument(url,"list");
				extractList(doc);
				
			} catch (IOException e) {
				e.printStackTrace();
				for(int i=0;i<retryConnectTimes;i++){
					System.out.println("failure url:"+url+" request "+ i);
					try {
						doc = getDocument(url,"list");
						if(doc!=null){
							extractList(doc);
							break;
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
		for(String accoutUrl:visitedAccount){
			Document accountDoc;
			try {
				accountDoc = getDocument(accoutUrl,"account");
				extractAccount(accountDoc);
				//PublicAccountManager.getInstance().push(raw);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				for(int i=0;i<retryConnectTimes;i++){
					System.out.println("url:"+accoutUrl+" request "+ i);
					try {
						accountDoc = getDocument(accoutUrl,"account");
						if(accountDoc!=null){
							extractAccount(accountDoc);
							break;
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				continue;
			}
			
		}
	    System.out.println(PublicAccountManager.getInstance().getRawCounts());
	}
	
	
	private void extractList(Document doc){
		Elements els = doc.select("a[class=topic_name]");
		for(Element el:els){
			String href = "http://chuansongme.com"+el.attr("href");
			enQueueURL(href);
		}
	}
	
	private void extractAccount(Document accountDoc){
		Elements titles = accountDoc.select("span[id=ld_XT398O_291]");
		Elements wxids = accountDoc.select("div[id=ld_E6G600_305]");
		Elements intrs = accountDoc.select("span[id=__w2_qcGcPvc_text_snip_content]");
		PublicAccountRaw raw = new PublicAccountRaw();
		raw.setChineseName(titles.size()>0?titles.get(0).text():"");
		raw.setAccountId(wxids.size()>0?wxids.get(0).text().split(":")[1]:"");
		raw.setCrawler(true);
		//raw.setIntro(intrs.size()>0?intrs.get(0).text():"");
		//raw.setQrCodeUrl(qrCodeUrl);.
		PublicAccountManager.getInstance().push(raw);
		System.out.println(raw.getAccountId());
	}
	
	private synchronized void enQueueURL(String url){
		if(!visitedAccount.contains(url)){
			visitedAccount.add(url);
		}
	}
	
	private Document getDocument(String url,String type) throws IOException{
		String cookie = "087cf5331f974160826e11da83178b30; __utma=245094362.1446428147.1401412191.1401587261.1401590128.10; __utmb=245094362.5.10.1401590128; __utmc=245094362; __utmz=245094362.1401412191.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none)";
		if(type.equals("account")){
			cookie = "087cf5331f974160826e11da83178b30; __utma=245094362.1446428147.1401412191.1401587261.1401590128.10; __utmb=245094362.11.10.1401590128; __utmc=245094362; __utmz=245094362.1401412191.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none)";
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document doc =  Jsoup.connect(url)
					.referrer("http://chuansongme.com/ideatech")
					.userAgent("User-Agent:Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.114 Safari/537.36")
					.cookie("_xsrf", cookie)
					.timeout(200000).get();
		return  doc;
	}
	
	
	

}
