package com.wh.app.web.service.edit.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my.common.domain.Query;
import com.my.mybatis.support.Page;
import com.my.mybatis.support.Sort;
import com.wh.app.web.mapper.edit.RecommendEditMapper;
import com.wh.app.web.model.edit.EassayEdit;
import com.wh.app.web.model.edit.RecommandEdit;
import com.wh.app.web.model.edit.RecommandEdit.RecommendStatus;
import com.wh.app.web.model.query.EassayEditQuery;
import com.wh.app.web.model.query.RecommandQuery;
import com.wh.app.web.service.edit.EassayEditService;
import com.wh.app.web.service.edit.RecommandEditService;

@Service
@Transactional
public class RecommendEditServiceImpl implements RecommandEditService {

	@Autowired RecommendEditMapper recommendEditMapper;
	@Autowired EassayEditService eassayEditService;
	@Override
	public void saveOrUpdate(RecommandEdit entity) {
		recommendEditMapper.saveOrUpdate(entity);
	}

	@Override
	public void deleteOne(Long id) {
		recommendEditMapper.deleteOne(id);
	}

	@Override
	public RecommandEdit findOne(Long id) {
		return recommendEditMapper.findOne(id);
	}

	@Override
	public void deleteMulti(List<Long> ids) {
		recommendEditMapper.deleteMutil(ids);
	}

	@Override
	public List<RecommandEdit> selectList(Query query, Sort sorts) {
		return recommendEditMapper.selectList(query, sorts);
	}

	@Override
	public Page<RecommandEdit> getPageList(Page<RecommandEdit> pager,
			Query query, Sort sorts) {
		return recommendEditMapper.getPage(pager, query, sorts);
	}

	@Override
	public void setRecommandEdit(List<Long> ids) {
		EassayEditQuery editQuery = new EassayEditQuery();
		editQuery.setIds(ids);
		List<EassayEdit> eassayList = eassayEditService.selectList(editQuery, null);
		for(EassayEdit easay:eassayList){
			RecommandEdit recommand = new RecommandEdit();
		    BeanUtils.copyProperties(easay, recommand, new String[]{"id","createTime","lastUpdateTime"});
		    RecommandQuery query = new RecommandQuery();
		    query.setEassayId(easay.getId());
		    List<RecommandEdit> recommands = recommendEditMapper.selectList(query, null);
		    if(recommands.size()==0){
			    recommand.setCreateTime(new Date());
			    recommand.setEassayId(easay.getId());
			    recommand.setLastUpdateTime(new Date());
			    recommand.setStatus(RecommendStatus.OPEN.getValue());
			    this.saveOrUpdate(recommand);
		    }
		   
		}
	}

}
