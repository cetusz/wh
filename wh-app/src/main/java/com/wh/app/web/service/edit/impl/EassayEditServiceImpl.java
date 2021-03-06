package com.wh.app.web.service.edit.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my.common.domain.Query;
import com.my.mybatis.support.Page;
import com.my.mybatis.support.Sort;
import com.wh.app.web.mapper.edit.EassayEditMapper;
import com.wh.app.web.model.edit.EassayEdit;
import com.wh.app.web.service.edit.EassayEditService;
import com.wh.app.web.service.edit.RecommandEditService;

@Service
@Transactional
public class EassayEditServiceImpl implements EassayEditService {

	@Autowired EassayEditMapper eassayEditMapper;
	@Autowired RecommandEditService recommandEditService;
	@Override
	public void saveOrUpdate(EassayEdit entity) {
		eassayEditMapper.saveOrUpdate(entity);
	}

	@Override
	public void deleteOne(Long id) {
		eassayEditMapper.deleteOne(id);
	}

	@Override
	public EassayEdit findOne(Long id) {
		return eassayEditMapper.findOne(id);
	}

	@Override
	public void deleteMulti(List<Long> ids) {
		eassayEditMapper.deleteMutil(ids);
	}

	@Override
	public List<EassayEdit> selectList(Query query, Sort sorts) {
		return eassayEditMapper.selectList(query, sorts);
	}

	@Override
	public Page<EassayEdit> getPageList(Page<EassayEdit> pager, Query query,
			Sort sorts) {
		return eassayEditMapper.getPage(pager, query, sorts);
	}

	@Override
	public void setTypeMutil(List<Long> ids, String typeId,String typeName) {
		eassayEditMapper.setTypeMutil(ids, typeId, typeName);		
	}

	@Override
	public void setRecommend(List<Long> ids, boolean isRecmmend) {
		eassayEditMapper.setRecommend(ids, isRecmmend);
		recommandEditService.setRecommandEdit(ids);
	}
	
}
