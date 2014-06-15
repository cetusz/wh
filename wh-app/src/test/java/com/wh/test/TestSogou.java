package com.wh.test;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.commons.codec.binary.Base64;
import org.dom4j.Document;
import org.dom4j.Element;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.junit.Test;

import com.my.common.util.Dom4jUtil;
import com.my.common.util.HttpUtils;

public class TestSogou {
	//oIWsFtxEOaH_uvcGuOmNIfoVLBQ0
	public static void main(String[] args) {
		String url = "http://weixin.sogou.com/gzhjs?cb=sogou.weixin.gzhcb&openid=oIWsFt_w95KNGFyoMH1GNrL7T7co&page=1&t=1402489723418";
		String content = HttpUtils.getInstance().doGet(url, "utf-8",null);
		String json = content.substring(content.indexOf("(")+1,content.lastIndexOf(")"));
		//System.out.println(json);
		try {
			JSONObject obj = new JSONObject(json);
			System.out.println(obj.getInt("totalPages"));
			String xml = obj.getJSONArray("items").get(0).toString();
			Document doc = Dom4jUtil.parse(xml);
			Element root = doc.getRootElement();
			Element item =(Element)Dom4jUtil.getSubElementByTagName(root, "item").get(0);
			Element display = (Element)Dom4jUtil.getSubElementByTagName(item, "display").get(0);
			System.out.println(display.elementText("title"));
			System.out.println(display.elementText("url"));
			System.out.println(display.elementText("title1"));
			System.out.println(display.elementText("imglink"));
			System.out.println(display.elementText("sourcename"));
			System.out.println(display.elementText("content"));
			System.out.println(display.elementText("date"));
			System.out.println(display.elementText("docid"));
			System.out.println("ab735a258a90e8e1-6bee54fcbd896b2a-6436f094711dfed74663a5ecbddb97d3".length());
			String str = "http://weixin.sogou.com/weixin?type=1&query=%E9%87%91%E9%94%99%E5%88%80";
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQuery() throws IOException{
		String name = "大楚网";
		System.out.println(URLEncoder.encode(name));
		String str = "http://weixin.sogou.com/weixin?type=1&query="+URLEncoder.encode(name);
		org.jsoup.nodes.Document doc = Jsoup.connect(str).get();
		org.jsoup.select.Elements els = doc.select("div[class=results]").select("a");
		if(els.size()>0){
			org.jsoup.nodes.Element el = els.get(0);
			System.out.println(el.attr("href").split("openid=")[1]);
			org.jsoup.select.Elements imgels = el.select("img");
			if(imgels.size()>0){
				System.out.println(imgels.get(0).attr("src"));
			}
			org.jsoup.select.Elements wxhels = el.getElementsMatchingOwnText("微信号：");
			String wxh = wxhels.size()>0?wxhels.get(0).ownText().split("：")[1]:"";
			System.out.println(wxh);
			org.jsoup.select.Elements gnjsels = el.getElementsMatchingOwnText("功能介绍：");
			if(gnjsels.size()>0){
				org.jsoup.nodes.Element gnel = gnjsels.get(0);
				System.out.println(gnel.nextElementSibling().text());
			}
			org.jsoup.select.Elements xlwbels = el.getElementsMatchingOwnText("新浪微博认证");
			if(xlwbels.size()>0){
				System.out.println(xlwbels.get(0).text());
			}
			org.jsoup.select.Elements wxels = el.getElementsMatchingOwnText("认证：");
            if(wxels.size()>0){
                org.jsoup.nodes.Element wx = wxels.get(0);
                
            	if(wx.html().indexOf("2")>-1){
            		System.out.println(wx.nextElementSibling().text());
            	}	
            }
            org.jsoup.select.Elements vbox = el.select("div[class=v-box]");
            System.out.println(vbox.select("img").get(0).attr("src"));
			
		}
	}

}
