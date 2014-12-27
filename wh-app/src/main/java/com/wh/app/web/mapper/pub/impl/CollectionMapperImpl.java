package com.wh.app.web.mapper.pub.impl;

import org.springframework.stereotype.Repository;

import com.my.mybatis.support.mapper.AbstractBaseMapper;
import com.wh.app.web.mapper.pub.CollectionMapper;
import com.wh.app.web.model.edit.CollectionEntity;

@Repository
public class CollectionMapperImpl extends AbstractBaseMapper<CollectionEntity>
		implements CollectionMapper {

	@Override
	public String getSeqName() {
		return null;
	}

	@Override
	public String getSqlNamespace() {
		return "com.wh.app.web.mapper.pub.CollectionMapper";
	}

}
