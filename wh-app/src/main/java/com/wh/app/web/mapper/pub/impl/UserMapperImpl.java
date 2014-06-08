package com.wh.app.web.mapper.pub.impl;

import org.springframework.stereotype.Component;

import com.my.mybatis.support.mapper.AbstractBaseMapper;
import com.wh.app.web.mapper.pub.UserMapper;
import com.wh.app.web.model.pub.UserEntity;

@Component
public class UserMapperImpl  extends AbstractBaseMapper<UserEntity> implements UserMapper {

	@Override
	public String getSeqName() {
		return null;
	}

	@Override
	public String getSqlNamespace() {
		return "com.wh.app.web.mapper.pub.UserMapper";
	}

}
