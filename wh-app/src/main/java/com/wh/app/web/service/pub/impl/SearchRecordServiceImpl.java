package com.wh.app.web.service.pub.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my.common.domain.Query;
import com.my.mybatis.support.Page;
import com.my.mybatis.support.Sort;
import com.wh.app.web.mapper.pub.SearchRecordMapper;
import com.wh.app.web.model.pub.SearchRecord;
import com.wh.app.web.service.pub.SearchRecordService;

@Service
@Transactional
public class SearchRecordServiceImpl implements SearchRecordService {

	@Autowired SearchRecordMapper searchRecordMapper;
	@Override
	public void saveOrUpdate(SearchRecord entity) {
		searchRecordMapper.saveOrUpdate(entity);
	}

	@Override
	public void deleteOne(Long id) {
		searchRecordMapper.deleteOne(id);
	}

	@Override
	public SearchRecord findOne(Long id) {
		return searchRecordMapper.findOne(id);
	}

	@Override
	public void deleteMulti(List<Long> ids) {
		searchRecordMapper.deleteMutil(ids);
	}

	@Override
	public List<SearchRecord> selectList(Query query, Sort sorts) {
		return searchRecordMapper.selectList(query, sorts);
	}

	@Override
	public Page<SearchRecord> getPageList(Page<SearchRecord> pager,
			Query query, Sort sorts) {
		return searchRecordMapper.getPage(pager, query, sorts);
	}

}
