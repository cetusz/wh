package com.wh.app.web.model.edit;

import com.wh.app.web.model.base.EassayBase;

public class EassayEdit extends EassayBase{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2058194560722237813L;
	//源数据中的公众账号id
	private String openId;
	//是否推荐
	private boolean isRecommanded;
	
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public boolean isRecommanded() {
		return isRecommanded;
	}

	public void setRecommanded(boolean isRecommanded) {
		this.isRecommanded = isRecommanded;
	}

	
	
	

}
