package com.wh.app.web.mapper.edit.impl;

import org.springframework.stereotype.Component;

import com.my.mybatis.support.mapper.AbstractBaseMapper;
import com.wh.app.web.mapper.edit.PublicAccountEditMapper;
import com.wh.app.web.model.edit.PublicAccountEdit;

@Component
public class PublicAccountEditMapperImpl extends AbstractBaseMapper<PublicAccountEdit> implements PublicAccountEditMapper {

	@Override
	public String getSeqName() {
		return null;
	}

	@Override
	public String getSqlNamespace() {
		return "com.wh.app.web.mapper.edit.PublicAccountEditMapper";
	}



}
