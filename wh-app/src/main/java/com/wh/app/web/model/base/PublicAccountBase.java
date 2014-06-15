package com.wh.app.web.model.base;

import java.io.Serializable;

public class PublicAccountBase extends BaseEntityPrimaryKey implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//微信公众账号ID
	private String accountId;
	//头像地址
	private String headImg;
	//中文名称
	private String chineseName;
	//功能简介
	private String funcintro;
	//微信认证信息
	private String wxcredit;
	//新浪认证
	private String sinacredit;
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
	
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public String getFuncintro() {
		return funcintro;
	}
	public void setFuncintro(String funcintro) {
		this.funcintro = funcintro;
	}
	public String getWxcredit() {
		return wxcredit;
	}
	public void setWxcredit(String wxcredit) {
		this.wxcredit = wxcredit;
	}

	public String getSinacredit() {
		return sinacredit;
	}
	public void setSinacredit(String sinacredit) {
		this.sinacredit = sinacredit;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
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
