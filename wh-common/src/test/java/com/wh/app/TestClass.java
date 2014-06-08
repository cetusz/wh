package com.wh.app;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

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
		String url = "https://wx2.qq.com/cgi-bin/mmwebwx-bin/webwxcheckurl?uin=2768183621&sid=m4Z9lc%2BsgTxKxmUg&skey=%40crypt_3d539bce_dc97c55ffff78ef9b6f2edffd4dde6e6&deviceid=e664179215001909&opcode=2&requrl=http%3A%2F%2Fmp.weixin.qq.com%2Fmp%2Fgetmasssendmsg%3F__biz%3DMjM5NDAxMTE0MA%3D%3D%23wechat_webview_type%3D1%26wechat_redirect&scene=1&username=wxid_4flqugtqlbdo21";
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
		String url = "https://wx.qq.com";
		String uuid_url = " https://login.weixin.qq.com/jslogin?appid=wx782c26e4c19acffb&redirect_uri=https%3A%2F%2Fwx2.qq.com%2Fcgi-bin%2Fmmwebwx-bin%2Fwebwxnewloginpage&fun=new&lang=zh_CN";
		Document doc = Jsoup.connect(uuid_url).timeout(20000).followRedirects(true).get();
		System.out.println(doc.body().toString().replace("&quot;",""));
      
	}

}
