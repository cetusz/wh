package com.wh.app.web.model.base;

public class PublicAccountBase extends BaseEntityPrimaryKey{
	
	//微信公众账号ID
	private String accountId;
	//中文名称
	private String chineseName;
	//简介
	private String intro;
	//是否爬取
	private boolean isCrawler;
	//二维码地址
	private String qrCodeUrl;
	//分类IDs
	private String cateIds;
	//版本号
	private String version;
	
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public boolean isCrawler() {
		return isCrawler;
	}
	public void setCrawler(boolean isCrawler) {
		this.isCrawler = isCrawler;
	}
	public String getQrCodeUrl() {
		return qrCodeUrl;
	}
	public void setQrCodeUrl(String qrCodeUrl) {
		this.qrCodeUrl = qrCodeUrl;
	}
	public String getCateIds() {
		return cateIds;
	}
	public void setCateIds(String cateIds) {
		this.cateIds = cateIds;
	}
	
	public String getChineseName() {
		return chineseName;
	}
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	
	
	

}
