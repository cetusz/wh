package com.wh.app.web.service.pub.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my.common.domain.Query;
import com.my.mybatis.support.Page;
import com.my.mybatis.support.Sort;
import com.wh.app.web.mapper.pub.FollowMapper;
import com.wh.app.web.model.edit.FollowEntity;
import com.wh.app.web.service.pub.FollowService;

@Service
@Transactional
public class FollowServiceImpl implements FollowService {
	
	@Autowired FollowMapper followMapper;

	@Override
	public void saveOrUpdate(FollowEntity entity) {
		followMapper.saveOrUpdate(entity);
	}

	@Override
	public void deleteOne(Long id) {
		followMapper.deleteOne(id);
	}

	@Override
	public FollowEntity findOne(Long id) {
		return followMapper.findOne(id);
	}

	@Override
	public void deleteMulti(List<Long> ids) {
		followMapper.deleteMutil(ids);
	}

	@Override
	public List<FollowEntity> selectList(Query query, Sort sorts) {
		return followMapper.selectList(query, sorts);
	}

	@Override
	public Page<FollowEntity> getPageList(Page<FollowEntity> pager,
			Query query, Sort sorts) {
		return followMapper.getPage(pager, query, sorts);
	}

}
