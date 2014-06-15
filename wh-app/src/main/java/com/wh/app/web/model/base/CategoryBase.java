package com.wh.app.web.model.base;
/**
 * 公众账号分类
 * @author Benjamin
 *
 */
public class CategoryBase extends BaseEntityPrimaryKey{

	private String cateName;
	private Integer orderNum;
	private String version;
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
}
