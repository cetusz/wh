package com.wh.app.web.model.base;


/**
 * 关注类
 * @author Benjamin
 *
 */
public class FollowBase extends BaseEntityPrimaryKey{

	//设备唯一id
	private String deviceId;
	//公众账号id
	private String accountId;
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	
}
