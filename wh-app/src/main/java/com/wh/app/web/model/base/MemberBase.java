package com.wh.app.web.model.base;

public class MemberBase extends BaseEntityPrimaryKey {
	private String deviceId;
	private String macAddress;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	
	
	
}
