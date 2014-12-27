package com.wh.app.web.service.pub.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my.common.domain.Query;
import com.my.mybatis.support.Page;
import com.my.mybatis.support.Sort;
import com.wh.app.web.mapper.pub.CollectionMapper;
import com.wh.app.web.model.edit.CollectionEntity;
import com.wh.app.web.service.pub.CollectionService;

@Service
@Transactional
public class CollectionServiceImpl implements CollectionService {

	@Autowired CollectionMapper collectionMapper;
	@Override
	public void saveOrUpdate(CollectionEntity entity) {
		collectionMapper.saveOrUpdate(entity);
	}

	@Override
	public void deleteOne(Long id) {
		collectionMapper.deleteOne(id);
	}

	@Override
	public CollectionEntity findOne(Long id) {
		return collectionMapper.findOne(id);
	}

	@Override
	public void deleteMulti(List<Long> ids) {
		collectionMapper.deleteMutil(ids);
	}

	@Override
	public List<CollectionEntity> selectList(Query query, Sort sorts) {
		return collectionMapper.selectList(query, sorts);
	}

	@Override
	public Page<CollectionEntity> getPageList(Page<CollectionEntity> pager,
			Query query, Sort sorts) {
		return collectionMapper.getPage(pager, query, sorts);
	}

}
