package com.wh.app.web.model.base;

public class SearchRecordBase  extends BaseEntityPrimaryKey{

	//用户唯一标示
	private String deviceId;
	//搜索关键词
	private String keyWord;
	//搜索次数
	private Long searchCounts;
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public Long getSearchCounts() {
		return searchCounts;
	}
	public void setSearchCounts(Long searchCounts) {
		this.searchCounts = searchCounts;
	}
	
	
}
