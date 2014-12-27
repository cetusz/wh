package com.wh.app.web.model.query;

import java.util.List;

import com.my.common.domain.Query;
import com.wh.app.web.model.edit.EassayEdit;

public class EassayEditQuery extends EassayEdit implements Query{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String dateAfter;
	
	private List<Long> ids;
	
	private Long lastId;

	public String getDateAfter() {
		return dateAfter;
	}

	public void setDateAfter(String dateAfter) {
		this.dateAfter = dateAfter;
	}

	public Long getLastId() {
		return lastId;
	}

	public void setLastId(Long lastId) {
		this.lastId = lastId;
	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

}
