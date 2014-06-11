package com.wh.test;

import org.json.JSONException;
import org.json.JSONObject;

import com.my.common.util.HttpUtils;

public class TestSogou {
	
	public static void main(String[] args) {
		String url = "http://weixin.sogou.com/gzhjs?cb=sogou.weixin.gzhcb&openid=oIWsFtxEOaH_uvcGuOmNIfoVLBQ0&page=1&t=1402489723418";
		String content = HttpUtils.getInstance().doGet(url, "utf-8",null);
		String json = content.substring(content.indexOf("(")+1,content.lastIndexOf(")"));
		//System.out.println(json);
		try {
			JSONObject obj = new JSONObject(json);
			System.out.println(obj.getInt("totalPages"));
			System.out.println(obj.getJSONArray("items").get(0));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
