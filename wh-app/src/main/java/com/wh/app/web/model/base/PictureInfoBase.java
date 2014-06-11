package com.wh.app.web.model.base;

public class PictureInfoBase extends BaseEntityPrimaryKey{

	private String sourceEassayId;
	private String sourceUrl;
	private String downloadUrl;
	public String getSourceEassayId() {
		return sourceEassayId;
	}
	public void setSourceEassayId(String sourceEassayId) {
		this.sourceEassayId = sourceEassayId;
	}
	public String getSourceUrl() {
		return sourceUrl;
	}
	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}
	public String getDownloadUrl() {
		return downloadUrl;
	}
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	
	

}
