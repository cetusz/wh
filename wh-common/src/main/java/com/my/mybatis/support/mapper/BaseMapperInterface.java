package com.my.mybatis.support.mapper;

import java.io.Serializable;
import java.util.List;

import com.my.common.domain.BaseEntity;
import com.my.common.domain.Query;
import com.my.mybatis.support.Page;
import com.my.mybatis.support.Sort;





/**
 * 
 * @cms-mybatis-support
 * @BaseMapperInterface.java
 * @author 904032
 * @2014年3月5日-下午5:24:39
 */
@SuppressWarnings("rawtypes")
public interface BaseMapperInterface<T extends BaseEntity> {

	public static final String PO_KEY = "po";
	public static final String ORDER_KEY = "tableSorts";

	/**
	 * 适合数据主键由自身实体定义的新闻情况
	 * 或更新情况
	 * @param entity
	 * @return
	 */
	public abstract Serializable saveOrUpdate(T entity);
	
	/**
	 * 提供新增方法，适合主键由外部提供的新增方法
	 * @param entity
	 * @return
	 */
	public abstract Serializable save(T entity);
	
	/**
	 * 查询
	 * @param pk
	 * @return
	 */
	public abstract T findOne(Serializable pk);

	/**
	 * 删除
	 * @param pk
	 */
	public abstract void deleteOne(Serializable pk);

	/**
	 * 列表查询
	 * @param query
	 * @param sorts
	 * @return
	 */
	public abstract List<T> selectList(Query query, Sort sorts);
	
	/**
	 * 批量删除数据
	 * @param ids
	 */
	public abstract void deleteMutil(List<Long> ids);

	/**
	 * 分页查询
	 * @param pager
	 * @param query
	 * @param sorts
	 * @return
	 */
	public abstract Page<T> getPage(Page<T> pager,
			Query query, Sort sorts);

	/**
	 * 用于子类实现seqId取值
	 * @return
	 */
	public abstract String getSeqName();

	/**
	 * 用于子类实现mapper命名空间实现
	 * @return
	 */
	public abstract String getSqlNamespace();
}