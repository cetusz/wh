package com.wh.app.web.service.edit.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.common.domain.Query;
import com.my.mybatis.support.Page;
import com.my.mybatis.support.Sort;
import com.wh.app.web.mapper.edit.PublicAccountEditMapper;
import com.wh.app.web.model.edit.PublicAccountEdit;
import com.wh.app.web.service.edit.PublicAccountEditService;

@Service
public class PublicAccountEditServiceImpl implements PublicAccountEditService {

	@Autowired PublicAccountEditMapper publicAccountEditMapper;
	@Override
	public void saveOrUpdate(PublicAccountEdit entity) {
		publicAccountEditMapper.saveOrUpdate(entity);
	}

	@Override
	public void deleteOne(Long id) {
		publicAccountEditMapper.deleteOne(id);
	}

	@Override
	public PublicAccountEdit findOne(Long id) {
		return publicAccountEditMapper.findOne(id);
	}

	@Override
	public void deleteMulti(List<Long> ids) {
		publicAccountEditMapper.deleteMutil(ids);
	}

	@Override
	public List<PublicAccountEdit> selectList(Query query, Sort sorts) {
		return publicAccountEditMapper.selectList(query, sorts);
	}

	@Override
	public Page<PublicAccountEdit> getPageList(Page<PublicAccountEdit> pager,
			Query query, Sort sorts) {
		return publicAccountEditMapper.getPage(pager, query, sorts);
	}

	@Override
	public void setTypeMutil(List<Long> ids, String typeId) {
		publicAccountEditMapper.setAccountType(ids, typeId);
	}

}
