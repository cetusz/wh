package com.wh.app.web.mapper.edit.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.my.mybatis.support.mapper.AbstractBaseMapper;
import com.wh.app.web.mapper.edit.EassayEditMapper;
import com.wh.app.web.model.edit.EassayEdit;


@Repository
public class EassayEditMapperImpl  extends AbstractBaseMapper<EassayEdit> implements EassayEditMapper{

	@Autowired SqlSession sqlSession;
	@Override
	public String getSeqName() {
		return null;
	}

	@Override
	public String getSqlNamespace() {
		return "com.wh.app.web.mapper.edit.EassayEditMapper";
	}

	@Override
	public void setTypeMutil(List<Long> ids, String typeId, String typeName) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("ids", ids);
		paramMap.put("typeId", typeId);
		paramMap.put("typeName", typeName);
		sqlSession.update(this.getSqlNamespace()+".setTypeMutil", paramMap);
	}

	@Override
	public void setRecommend(List<Long> ids,boolean isRecmmend) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("ids", ids);
		paramMap.put("isRecmmend", isRecmmend);
		sqlSession.update(this.getSqlNamespace()+".setRecommend",paramMap);
	}


}
