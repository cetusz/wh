package com.wh.app.web.model.edit;

import com.wh.app.web.model.base.RecommandBase;

public class RecommandEdit extends RecommandBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String status;
	
	private Long eassayId;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public Long getEassayId() {
		return eassayId;
	}

	public void setEassayId(Long eassayId) {
		this.eassayId = eassayId;
	}




	public static enum RecommendStatus{
		SHIELD("shield"),OPEN("open");
		private String value;
		private RecommendStatus(String value){
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}
}
