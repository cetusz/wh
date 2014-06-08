package com.wh.app.web.crawler.manager;

import java.util.concurrent.ArrayBlockingQueue;

import org.apache.log4j.Logger;

import com.wh.app.web.model.raw.PublicAccountRaw;

public class PublicAccountManager {
	
	Logger logger = Logger.getLogger(PublicAccountManager.class);
	
	private  ArrayBlockingQueue<PublicAccountRaw> accounts = new  ArrayBlockingQueue<PublicAccountRaw>(100);
	
	
	private PublicAccountManager(){}
	
	private static PublicAccountManager instance;
	
	public static PublicAccountManager getInstance(){
		if(instance == null){
			instance = new PublicAccountManager();
		}
		return instance;
	}
	
	public PublicAccountRaw pop(){
		PublicAccountRaw publicAcountRaw = null;
		try {
			publicAcountRaw = accounts.take();
		} catch (InterruptedException e) {
			logger.error(e);
		}
		return publicAcountRaw;
	}
	
	public void push(PublicAccountRaw publicAcountRaw){
		try {
			accounts.put(publicAcountRaw);
		} catch (InterruptedException e) {
			logger.error(e);
		}
	}
	
	public int getRawCounts(){
		return accounts.size();
	}

}
