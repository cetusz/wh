package com.wh.app.web.extractor;

import java.io.IOException;
import java.net.URLEncoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.wh.app.web.model.edit.PublicAccountEdit;

@Component
public class WeixinAccountExtractor {

	public PublicAccountEdit extractAccount(String accountName) throws IOException{
		System.out.println(URLEncoder.encode(accountName));
		String str = "http://weixin.sogou.com/weixin?type=1&query="+URLEncoder.encode(accountName);
		Document doc = Jsoup.connect(str).timeout(20000)
				.userAgent("Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.116 Safari/537.36")
				.get();
		Elements els = doc.select("div[class=results]").select("a");
		PublicAccountEdit account = null;
		if(els.size()>0){
			account = new PublicAccountEdit();
			account.setChineseName(accountName);
			Element el = els.get(0);
			account.setBizId(el.attr("href").split("openid=")[1].trim());
			Elements imgels = el.select("img");
			if(imgels.size()>0){
				account.setHeadImg(imgels.get(0).attr("src").trim());
			}
			Elements wxhels = el.getElementsMatchingOwnText("微信号：");
			String wxh = wxhels.size()>0?wxhels.get(0).ownText().split("：")[1]:"";
			account.setAccountId(wxh);
			Elements gnjsels = el.getElementsMatchingOwnText("功能介绍：");
			if(gnjsels.size()>0){
				Element gnel = gnjsels.get(0);
				account.setFuncintro(gnel.nextElementSibling().text().trim());
			}
			Elements xlwbels = el.getElementsMatchingOwnText("新浪微博认证");
			if(xlwbels.size()>0){
				account.setSinacredit(xlwbels.get(0).text());
			}
			Elements wxels = el.getElementsMatchingOwnText("认证：");
            if(wxels.size()>0){
            	Element wx = wxels.get(0);
            	if(wx.html().indexOf("2")>-1){
            		account.setWxcredit(wx.nextElementSibling().text());
            	}
            }
            Elements vbox = el.select("div[class=v-box]");
            account.setQrCodeUrl(vbox.select("img").get(0).attr("src"));
		}
		return account;
	}
}
