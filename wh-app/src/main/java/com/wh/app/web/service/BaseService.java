package com.wh.app.web.service;

import java.util.List;

import com.my.common.domain.Query;
import com.my.mybatis.support.Page;
import com.my.mybatis.support.Sort;

/**
 * 
 * @author Benjamin
 * 
 * @param <T>
 */
public interface BaseService<T> {
	
	public void saveOrUpdate(T entity);

	public void deleteOne(Long id);

	public T findOne(Long id);

	public void deleteMulti(List<Long> ids);

	public List<T> selectList(Query query, Sort sorts);
	
	public Page<T> getPageList(Page<T> pager,Query query,Sort sorts);

}
