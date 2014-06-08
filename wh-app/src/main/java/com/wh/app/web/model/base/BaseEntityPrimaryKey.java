/**
 * 
 */
package com.wh.app.web.model.base;

import java.util.Date;

import com.my.common.domain.BaseEntity;



/**
 * 
 * @cms-domain
 * @BaseEntityPrimaryKey.java
 * @author 904032
 * @2014年3月21日-上午11:21:02
 */
public abstract class BaseEntityPrimaryKey implements BaseEntity<Long> {


	private Long id;
	private Date createTime;
	private Date lastUpdateTime;
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	
}
