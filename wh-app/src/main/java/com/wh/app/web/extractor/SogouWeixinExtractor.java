package com.wh.app.web.extractor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.my.common.util.Dom4jUtil;
import com.my.common.util.HttpUtils;
import com.wh.app.web.model.edit.EassayEdit;
import com.wh.app.web.model.edit.PublicAccountEdit;
import com.wh.app.web.model.query.EassayEditQuery;
import com.wh.app.web.service.edit.EassayEditService;
import com.wh.app.web.service.edit.PublicAccountEditService;

@Component("SogouWeixinExtractor")
public class SogouWeixinExtractor {
	Logger logger = Logger.getLogger(SogouWeixinExtractor.class);
	@Autowired EassayEditService eassayEditService;
	@Autowired PublicAccountEditService accountEditService;
	
	String pageUrl =  "http://weixin.sogou.com/gzhjs?cb=sogou.weixin.gzhcb&openid=%s&page=%d&t=%d";
	String entry = "http://weixin.sogou.com/gzhjs?cb=sogou.weixin.gzhcb&openid=%s";
	public static boolean isRunning = false;
	public void execute(){
		if(!isRunning){
			isRunning = true;
			//数量不多时可以全取
			List<PublicAccountEdit> accounts = accountEditService.selectList(null, null);
			for(PublicAccountEdit account:accounts){
				extractEassay(account);
			}
		}
	}
	
	private void extractEassay(PublicAccountEdit account){
		int totalPage = 2;
		String openId = account.getBizId();
		for(int pageindex=1;pageindex<=totalPage;pageindex++){
			String page =  String.format(pageUrl, openId,pageindex,System.currentTimeMillis());
			String content = HttpUtils.getInstance().doGet(page, "utf-8",null);
			String json = content.substring(content.indexOf("(")+1,content.lastIndexOf(")"));
			try {
				JSONObject obj = new JSONObject(json);
				int totalPages = obj.getInt("totalPages");
				if(totalPage!=totalPages){
					totalPage = totalPages;
				}
				JSONArray array = obj.getJSONArray("items");
				for(int index=0,len=array.length();index<len;index++){
					String xml = obj.getJSONArray("items").get(index).toString();
					Document doc = Dom4jUtil.parse(xml);
					Element root = doc.getRootElement();
					Element item =(Element)Dom4jUtil.getSubElementByTagName(root, "item").get(0);
					Element display = (Element)Dom4jUtil.getSubElementByTagName(item, "display").get(0);
					String sourceId = display.elementText("docid");
					EassayEditQuery query = new EassayEditQuery();
					query.setSourceId(sourceId);
					query.setOpenId(openId);
					List<EassayEdit> edits = eassayEditService.selectList(query, null);
					if(edits.size()==0){
						EassayEdit eassay = new EassayEdit();
						eassay.setAccountId(account.getId());
						eassay.setOpenId(openId);
						eassay.setTitle(display.elementText("title"));
						eassay.setContentUrl(display.elementText("url"));
						eassay.setIntro(display.elementText("content"));
						eassay.setAccountName(display.elementText("sourcename"));
						String date = display.elementText("date");
						if(StringUtils.isNotEmpty(date)){
							date = date.indexOf(":")==-1?date+" 00:00:00":date;
							Date dateTime = parseDate(date);
							eassay.setPubDate(dateTime);
						}
						eassay.setSourceId(sourceId);
						eassayEditService.saveOrUpdate(eassay);
					}
					//System.out.println(display.elementText("title1"));
					//System.out.println(display.elementText("imglink"));
	
				}
			} catch (JSONException e) {
				logger.error(e);
			} catch (ParseException e) {
				logger.error(e);
			} 
		}
		isRunning = false;
	}
	
	private Date parseDate(String dateValue) throws ParseException{
		String parttern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(parttern);
		return simpleDateFormat.parse(dateValue);
	}

}
