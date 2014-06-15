package com.wh.app.web.service.edit.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.common.domain.Query;
import com.my.mybatis.support.Page;
import com.my.mybatis.support.Sort;
import com.wh.app.web.mapper.edit.CategoryEditMapper;
import com.wh.app.web.model.edit.CategoryEdit;
import com.wh.app.web.service.edit.CategoryEditService;


@Service
public class CategoryEditServiceImpl implements CategoryEditService {

	@Autowired CategoryEditMapper categoryEditMapper;
	@Override
	public void saveOrUpdate(CategoryEdit entity) {
		categoryEditMapper.saveOrUpdate(entity);
	}

	@Override
	public void deleteOne(Long id) {
		categoryEditMapper.deleteOne(id);
	}

	@Override
	public CategoryEdit findOne(Long id) {
		return categoryEditMapper.findOne(id);
	}

	@Override
	public void deleteMulti(List<Long> ids) {
		categoryEditMapper.deleteMutil(ids);
	}

	@Override
	public List<CategoryEdit> selectList(Query query, Sort sorts) {
		return categoryEditMapper.selectList(query, sorts);
	}

	@Override
	public Page<CategoryEdit> getPageList(Page<CategoryEdit> pager,
			Query query, Sort sorts) {
		return categoryEditMapper.getPage(pager, query, sorts);
	}
}
