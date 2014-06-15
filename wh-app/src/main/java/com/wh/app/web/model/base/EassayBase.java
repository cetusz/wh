package com.wh.app.web.model.base;

import java.io.Serializable;
import java.util.Date;

public class EassayBase extends BaseEntityPrimaryKey implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//微信源id
	private String sourceId;
	//标题
	private String title;
	//内容
	private String content;
	//简介
	private String intro;
	//源地址
	private String contentUrl;
	//分类ID
	private Long categoryId;
	//分类名称
	private String categoryName;
	//公众账号id
	private Long accountId;
	//公众账号名称
	private String accountName;
	//发布日期
	private Date pubDate;
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getContentUrl() {
		return contentUrl;
	}
	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public Date getPubDate() {
		return pubDate;
	}
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}
	
	
	
}
