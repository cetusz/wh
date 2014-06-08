package com.wh.app.web.model.edit;

import com.wh.app.web.model.base.PublicAccountBase;

public class PublicAccountEdit extends PublicAccountBase {

	//公众账号加密过的唯一标识
	private String bizId;

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}
	
	
}