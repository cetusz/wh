package com.wh.app.web.mapper.edit.impl;

import org.springframework.stereotype.Component;

import com.my.mybatis.support.mapper.AbstractBaseMapper;
import com.wh.app.web.mapper.edit.EassayEditMapper;
import com.wh.app.web.model.edit.EassayEdit;


@Component
public class EassayEditMapperImpl  extends AbstractBaseMapper<EassayEdit> implements EassayEditMapper{

	@Override
	public String getSeqName() {
		return null;
	}

	@Override
	public String getSqlNamespace() {
		return "com.wh.app.web.mapper.edit.EassayEditMapper";
	}

}
