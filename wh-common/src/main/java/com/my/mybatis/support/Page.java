package com.my.mybatis.support;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <p>分页信息。</p>
 * <p>继承ArrayList是因为如果BaseMapper.getPage方法的返回类型是Page，而mybatis有如下判断：</p>
 * <pre>
 * if (List.class.isAssignableFrom(method.getReturnType())) {
 *    returnsList = true;//即只有返回List才执行selectList。
 * }
 * </pre>
 * 
 * @author 904032
 * @date 2014-2-14
 * @date 2012-7-12
 */
public class Page<T> extends ArrayList<T> implements Serializable {
	private static final long serialVersionUID = -1241179900114637258L;
	
	/**每页显示几条*/
	private int size = 20;
	
	/**总条数*/
	private int total = 0; 
	
	/**当前页*/
	private int currentPage = 0; 
	
	/**当前记录起始索引*/
	private int currentResult = 0; 

	/**存放结果集*/
	private List<T> result = new ArrayList<T>();

	public Page(){
		this(20,1);
	}
	
	/**
	 * 
	 * @param pageSize 分页记录
	 * @param currentPage 当前页码
	 */
	public Page(int pageSize,int currentPage){
		this.size = pageSize;
		this.currentPage = currentPage;
	}
	
	/**
	 * 
	 * <p>获取结果集</p>
	 *
	 * @return
	 */
	public List<T> getResult() {
		if (result == null) {
			return new ArrayList<T>();
		}
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	/**
	 * 
	 * <p>
	 * 获取总页数
	 * </p>
	 * 
	 * @return
	 */
	public int getTotalPage() {
		if (total % size == 0) {
			return total / size;
		}
		return total / size + 1;
	}

	/**
	 * 
	 * <p>
	 * 获取总条数
	 * </p>
	 * 
	 * @return
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * 
	 * <p>
	 * 设置总条数
	 * </p>
	 * 
	 * @param total
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	public int getCurrentPage() {
		if (currentPage <= 0) {
			currentPage = 1;
		}
		if (currentPage > getTotalPage() && getTotalPage()>1) {
			currentPage = getTotalPage();
		}
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		if (size == 0) {
			size = 10;
		}
		this.size = size;
	}

	public int getCurrentResult() {
		currentResult = (getCurrentPage() - 1) * getSize();
		if (currentResult < 0) {
			currentResult = 0;
		}
		return currentResult;
	}

	public void setCurrentResult(int currentResult) {
		this.currentResult = currentResult;
	}
}