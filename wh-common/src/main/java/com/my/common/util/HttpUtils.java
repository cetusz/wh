package com.my.common.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HttpUtils {
	private static final String COOKIE="bid=\"dprHP/2aPyc\"; ll=\"118282\"; __utma=30149280.1649332086.1375150457.1375150457.1375150457.1; __utmb=30149280.2.10.1375150457; __utmc=30149280; __utmz=30149280.1375150457.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none)";
	private static HttpUtils httpUtils = null;
	private HttpUtils(){
		
	}
	public static HttpUtils getInstance(){
		if(httpUtils==null)
			return new HttpUtils();
		return httpUtils;
	}
	Logger log = LoggerFactory.getLogger(HttpUtils.class);
	public String doGet(String url,String charset,String reffer, String cookie) {
		HttpClient client = new HttpClient(); // 实例化httpClient
		HttpMethod method = new GetMethod(url); //
		if(StringUtils.isNotEmpty(reffer)){
			method.addRequestHeader("reffer",reffer);
		}
		method.addRequestHeader("Cache-Control", "max-age=0");  
		method.addRequestHeader("Connection", "keep-alive");
		method.addRequestHeader("Cookie", COOKIE.replaceFirst("dprHP", String.valueOf(Math.round((Math.random()*1000000000))+Math.round(Math.random()*10000000)))+"");
		method.addRequestHeader("User-Agent","Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.116 Safari/537.36");
		//method.addRequestHeader("User-Agent","Android");
		method.addRequestHeader("Cookie", cookie);
		//使用系统提供的默认的恢复策略
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, 
			    new DefaultHttpMethodRetryHandler()); 
		client.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
		client.getHttpConnectionManager().getParams().setSoTimeout(10000); 
		String responseContent = "";
		try {
			int stateCode = client.executeMethod(method); // 执行
			if(stateCode != HttpStatus.SC_OK){
				log.error("Method failed: " + method.getStatusLine()+" url:["+url+"]");
				return "error:"+stateCode;
			}
			InputStream jsonStr;
			jsonStr = method.getResponseBodyAsStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int i = -1;
			while ((i = jsonStr.read()) != -1) {
				baos.write(i);
			}
			responseContent = baos.toString(charset);
			jsonStr.close();
			baos.close();
			method.releaseConnection();
			if(log.isDebugEnabled()){
				log.debug("成功解析:"+url);
			}
		} catch (HttpException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return responseContent;
	}
	
	public  String doPost(String url,Map<String,String> params,String charset) {
		HttpClient client = new HttpClient(); // 实例化httpClient
		PostMethod method = new PostMethod(url); //	
		client.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
		client.getHttpConnectionManager().getParams().setSoTimeout(10000);
		method.addRequestHeader("Cache-Control", "max-age=0");  
		method.addRequestHeader("Connection", "keep-alive");
		method.addRequestHeader("Cookie", COOKIE.replaceFirst("dprHP", String.valueOf(Math.round((Math.random()*1000000000))+Math.round(Math.random()*10000000)))+"");
		method.setRequestHeader("User-Agent","Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.116 Safari/537.36");
		if (params != null) {
			 StringPart[] parts=new StringPart[params.size()];
		     int i=0;           
             for (Map.Entry<String, String> entry : params.entrySet()) {
            	 parts[i]=new StringPart(entry.getKey(), entry.getValue());
            	 i++;
             }
             method.setRequestEntity(new MultipartRequestEntity(parts, method.getParams()));
		 }
			
		String responseContent = "";
		try {
			int stateCode = client.executeMethod(method); // 执行
			if(stateCode != HttpStatus.SC_OK){
				log.error("Method failed: " + method.getStatusLine());
				return "error:"+stateCode;
			}
			InputStream jsonStr;
			jsonStr = method.getResponseBodyAsStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int i = -1;
			while ((i = jsonStr.read()) != -1) {
				baos.write(i);
			}
			responseContent = baos.toString(charset);
			jsonStr.close();
			baos.close();
			method.releaseConnection();
			if(log.isDebugEnabled()){
				log.debug("成功解析:"+url);
			}
		} catch (HttpException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return responseContent;
	}
	public String doGetWithReconnect(String url,String charset){
		String content = doGet(url,charset,null, "CXID=96986C6E3D0585A7AB48A91591B16288; SUID=34044D742141900A5433885C000B63C3; SUV=1412664458538123; SMYUV=1412664458972595; redref=http://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&tn=99911542_s_hao_pg&wd=%E5%A6%82%E4%BD%95%E9%81%BF%E5%85%8D%E4%B9%85%E5%9D%90&rsv_enter=1&rsv_sug3=210&rsv_sug4=13429&rsv_sug1=91&rsv_sug2=0&inputT=3743&oq=%E4%BA%94%E7%AC%94%E7%BB%83%E4%B9%A0&rsp=1; IPLOC=CN4403; ssuid=6507441392; pid=baike.box; ld=Olllllllll2USirjlllllVS4u7wlllllN9192lllll9lllllVklll5@@@@@@@@@@; ss_pidf=1; sct=1; SUIR=1414078029; SNUID=497930087C797B64CFDD8B437DE0E2F0; ABTEST=8|1414286380|v1; LSTMV=539%2C135; LCLKINT=7106; ad=Jt94vyllll2UGcPtlllllVSGgmylllllN9192lllll9lllllpklll5@@@@@@@@@@");
		//检测重连
		if(StringUtils.isEmpty(content)){
			for(int i=0;i<3;i++){
				content = HttpUtils.getInstance().doGet(url, "utf-8",null, "");
				if(StringUtils.isNotEmpty(content)){
					break;
				}
			}
		}
		return content;
	}
	
     /**
	 * @param url
	 * @param charset
	 * @param header 此参数为access_token 用于添加到POST的header信息里面
	 * @return 获取豆瓣受限制的API 
	 */
	public  String doubanGet(String url,String charset,String header) {
		HttpClient client = new HttpClient(); // 实例化httpClient
		HttpMethod method = new GetMethod(url); //
		method.addRequestHeader("Cookie", COOKIE.replaceFirst("dprHP", String.valueOf(Math.round((Math.random()*1000000000))+Math.round(Math.random()*10000000)))+"");
		method.addRequestHeader("User-Agent","Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.116 Safari/537.36");
		if(null!=header&&!"".equals(header)){
			method.addRequestHeader("Authorization", "Bearer "+header);
		}
		//使用系统提供的默认的恢复策略
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, 
			    new DefaultHttpMethodRetryHandler()); 
		client.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
		client.getHttpConnectionManager().getParams().setSoTimeout(10000); 
		String responseContent = "";
		try {
			int stateCode = client.executeMethod(method); // 执行
			if(stateCode != HttpStatus.SC_OK){
				log.error("Method failed: " + method.getStatusLine()+" url:["+url+"]");
				return "error:"+stateCode;
			}
			InputStream jsonStr;
			jsonStr = method.getResponseBodyAsStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int i = -1;
			while ((i = jsonStr.read()) != -1) {
				baos.write(i);
			}
			responseContent = baos.toString(charset);
			jsonStr.close();
			baos.close();
			method.releaseConnection();
			if(log.isDebugEnabled()){
				log.debug("成功解析:"+url);
			}
		} catch (HttpException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return responseContent;
	}
	public static void main(String[] args) {
		String url = "http://14.17.82.18/live/c2_bjwsHD_s10.f4v?key=a37b3510ec066b10&ran=0.36604522867128253";
		System.out.println(HttpUtils.getInstance().doGet(url,"utf-8",null, ""));
	}
	


}
