package com.wh.app.web.mapper.pub.impl;

import org.springframework.stereotype.Repository;

import com.my.mybatis.support.mapper.AbstractBaseMapper;
import com.wh.app.web.mapper.pub.FollowMapper;
import com.wh.app.web.model.edit.FollowEntity;

@Repository
public class FollowMapperImpl extends AbstractBaseMapper<FollowEntity>
		implements FollowMapper {

	@Override
	public String getSeqName() {
		return null;
	}

	@Override
	public String getSqlNamespace() {
		return "com.wh.app.web.mapper.pub.FollowMapper";
	}
}
