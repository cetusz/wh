package com.wh.app.web.model.base;

/**
 * 收藏类
 * @author Benjamin
 *
 */
public class CollectionBase extends BaseEntityPrimaryKey {
	
	//用户设备id
	private String deviceId;
	//原来微信文章的id
	private String docId;
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	

}
