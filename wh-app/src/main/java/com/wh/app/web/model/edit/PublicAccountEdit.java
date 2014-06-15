package com.wh.app.web.model.edit;

import com.wh.app.web.model.base.PublicAccountBase;

public class PublicAccountEdit extends PublicAccountBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//公众账号加密过的唯一标识
	private String bizId;
	//是否屏蔽 true是false否，默认否
	private boolean isShielded;
	//分类名称显示
	private String cateNames;
	
	//上次更新完畢的時間
	private String lastCrawlerDate;

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public boolean isShielded() {
		return isShielded;
	}

	public void setShielded(boolean isShielded) {
		this.isShielded = isShielded;
	}

	public String getLastCrawlerDate() {
		return lastCrawlerDate;
	}

	public void setLastCrawlerDate(String lastCrawlerDate) {
		this.lastCrawlerDate = lastCrawlerDate;
	}

	public String getCateNames() {
		return cateNames;
	}

	public void setCateNames(String cateNames) {
		this.cateNames = cateNames;
	}
	
	
	
	
	
	
	
	
}