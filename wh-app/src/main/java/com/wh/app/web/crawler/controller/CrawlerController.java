package com.wh.app.web.crawler.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.wh.app.web.crawler.worker.PublicAccountWorker;



public class CrawlerController {
	
	public static void startCrawlerAccount() throws InterruptedException{
		String[] entryUrls = {
				"http://chuansongme.com/ideatech?start=%d",
				"http://chuansongme.com/newsmedia?start=%d",
				"http://chuansongme.com/fun?start=%d",
		        "http://chuansongme.com/lifejourney?start=%d",
		        "http://chuansongme.com/utility?start=%d",
		        "http://chuansongme.com/hisbook?start=%d"
		};
		ExecutorService  executor =Executors.newCachedThreadPool();
		List<Thread> threads = new ArrayList<Thread>();
		for(String url : entryUrls){
			PublicAccountWorker work = new PublicAccountWorker(url);
			Thread thread = new Thread(work);
			threads.add(thread);
			thread.start();
			//executor.execute(work);
			//executor.shutdown();
		}
		
	}
	
	public static void main(String[] args) {
		try {
			startCrawlerAccount();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
