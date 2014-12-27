package com.wh.app.web.mapper.pub.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.my.mybatis.support.mapper.AbstractBaseMapper;
import com.wh.app.web.mapper.pub.MemberMapper;
import com.wh.app.web.model.pub.MemberEntity;


@Repository
public class MemberMapperImpl extends AbstractBaseMapper<MemberEntity> implements MemberMapper{

	@Override
	public String getSeqName() {
		return null;
	}

	@Override
	public String getSqlNamespace() {
		return "com.wh.app.web.mapper.pub.MemberMapper";
	}

}
