package com.wh.app.web.extractor;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * 传送门爬取类
 * http://chuansongme.com
 * @author 907708
 *
 */
public class CSMExtractor {
	
	private String[] entryUrls = {
			"http://chuansongme.com/ideatech?start=%d",
			"http://chuansongme.com/newsmedia?start=%d",
			"http://chuansongme.com/fun?start=%d",
	        "http://chuansongme.com/lifejourney?start=%d",
	        "http://chuansongme.com/utility?start=%d",
	        "http://chuansongme.com/hisbook?start=%d"
	};
	/**
	 * 初始化门公众账号
	 */
	public void initPublicAccount(){
		
	}
	
	public static void main(String[] args) throws IOException {
		String url = "http://mp.weixin.qq.com/mp/getmasssendmsg?__biz=MjM5MDIwODkyMA==&uin=MjIxNDMwNDAxNw==&key=aae2f692d81876849e1cfee3147a4a018208ea90fd5b0069c144c73373fbc9f2fbeeeb49e183fbc2238266c88258c1ed&frommsgid=57921&count=10&f=json";
	}

}
