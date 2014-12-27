package com.wh.app.web.mapper.pub.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.my.mybatis.support.mapper.AbstractBaseMapper;
import com.wh.app.web.mapper.pub.SearchRecordMapper;
import com.wh.app.web.model.pub.SearchRecord;

@Repository
public class SearchRecordMapperImpl extends AbstractBaseMapper<SearchRecord> implements SearchRecordMapper{

	@Override
	public String getSeqName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSqlNamespace() {
		// TODO Auto-generated method stub
		return "com.wh.app.web.mapper.pub.SearchRecordMapper";
	}

}
