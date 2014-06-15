package com.wh.app.web.mapper.edit.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.my.mybatis.support.mapper.AbstractBaseMapper;
import com.wh.app.web.mapper.edit.PublicAccountEditMapper;
import com.wh.app.web.model.edit.PublicAccountEdit;

@Component
public class PublicAccountEditMapperImpl extends AbstractBaseMapper<PublicAccountEdit> implements PublicAccountEditMapper {

	@Autowired SqlSession sqlSession;
	@Override
	public String getSeqName() {
		return null;
	}

	@Override
	public String getSqlNamespace() {
		return "com.wh.app.web.mapper.edit.PublicAccountEditMapper";
	}

	@Override
	public void setAccountType(List<Long> ids, String typeId) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("ids", ids);
		paramMap.put("typeId", typeId);
		sqlSession.update(this.getSqlNamespace()+".setTypeMutil", paramMap);
	}



}
