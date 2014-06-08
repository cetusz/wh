/**
 * AbstractBaseMapper.java 下午1:57:00 2012-7-12
 *
 * Copyright(c) 2000-2012 coship.lestor, All Rights Reserved.
 */
package com.my.mybatis.support.mapper;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import com.my.common.domain.BaseEntity;
import com.my.common.domain.Query;
import com.my.common.util.ReflectUtil;
import com.my.mybatis.support.Page;
import com.my.mybatis.support.Sort;
import com.my.mybatis.support.interceptor.PageInterceptor;





/**
 * <p>
 * 把常用的方法抽象到此接口中，避免在多个接口中重复定义
 * </p>
 * 
 * @author luliangsong
 * @date 2014-2-12
 */
@SuppressWarnings("rawtypes")
@Repository
public abstract class AbstractBaseMapper<T extends BaseEntity> implements BaseMapperInterface<T>  {
	
	@Autowired protected SqlSessionTemplate sqlSessionTemplate;
	@Autowired private PlatformTransactionManager transactionManager;

	private Logger logger = Logger.getLogger(getClass());
	
	@Override
	public Serializable saveOrUpdate(T entity){
		
		//断言getSqlNamespace()是否实现
		checkSqlNamespace();
		if(entity.getId()==null)
		{
			this.save(entity);
		}
		else
		{
			sqlSessionTemplate.update(this.getSqlNamespace()+".update", entity);
		}
	
		return entity.getId();
	}
	
	@Override
	public Serializable save(T entity) {
		//断言getSqlNamespace()是否实现
		checkSqlNamespace();
		
		//未定义seqId取值，默认采用数据自增主键
		if(entity.getId()==null)
		{
			
			if(null==this.getSeqName()||this.getSeqName().trim().isEmpty())
			{
				logger.debug("no set SeqName , execute sql.");
				sqlSessionTemplate.insert(this.getSqlNamespace()+".save", entity);
			}
			else
			{
				logger.debug("get Id by SeqName , execute sql.");
				sqlSessionTemplate.insert(this.getSqlNamespace()+".save", entity);
			}
		}
		else
		{
			sqlSessionTemplate.insert(this.getSqlNamespace()+".save", entity);
		}
		return entity.getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findOne(Serializable pk) {
		//断言getSqlNamespace()是否实现
		checkSqlNamespace();
		return (T) sqlSessionTemplate.selectOne(this.getSqlNamespace()+".findOne", pk);
	}

	@Override
	@Transactional
	public void deleteOne(Serializable pk){
		//断言getSqlNamespace()是否实现
		checkSqlNamespace();
		sqlSessionTemplate.delete(this.getSqlNamespace()+".deleteOne", pk);
	}
	
	@Override
	public List<T> selectList(Query query, Sort sorts){
		//断言getSqlNamespace()是否实现
		checkSqlNamespace();
		Map<String, Object> parameter = new HashMap<String, Object>();
//		parameter.put(PO_KEY, entity);
		Map<String, Object> tmpMap = ReflectUtil.getObjectAsMap(query);
		parameter.putAll(tmpMap);
		parameter.put(ORDER_KEY, null==sorts?null:sorts.getTabelSort());
		return sqlSessionTemplate.selectList(this.getSqlNamespace()+".selectList", parameter);
	}


	@Override
	public Page<T> getPage(@Param(PageInterceptor.PAGE_KEY) Page<T> page,
			Query query,Sort sorts){
		
		//断言getSqlNamespace()是否实现
		checkSqlNamespace();
		
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put(PageInterceptor.PAGE_KEY, page);
		Map<String, Object> tmpMap = ReflectUtil.getObjectAsMap(query);
		parameter.putAll(tmpMap);
		parameter.put(ORDER_KEY, null==sorts?null:sorts.getTabelSort());
		sqlSessionTemplate.selectList(this.getSqlNamespace()+".getPageList", parameter);
		return page;
	}
	

	@Override
	public abstract String getSeqName();
	

	@Override
	public abstract String getSqlNamespace();
	
	/**
	 * 检查各子类是否实现sqlNamespace()
	 */
	protected void checkSqlNamespace()
	{
		if(null==this.getSqlNamespace()||this.getSqlNamespace().trim().isEmpty())
		{
			throw new RuntimeException("no implement getSqlNamespace(),can't find the sqlId.");
		}
	}
}
