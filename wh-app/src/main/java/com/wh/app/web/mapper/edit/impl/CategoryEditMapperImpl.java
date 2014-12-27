package com.wh.app.web.mapper.edit.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.my.mybatis.support.mapper.AbstractBaseMapper;
import com.wh.app.web.mapper.edit.CategoryEditMapper;
import com.wh.app.web.model.edit.CategoryEdit;

@Repository
public class CategoryEditMapperImpl extends AbstractBaseMapper<CategoryEdit> implements CategoryEditMapper {

	@Override
	public String getSeqName() {
		return null;
	}

	@Override
	public String getSqlNamespace() {
		return "com.wh.app.web.mapper.edit.CategoryEditMapper";
	}


}
