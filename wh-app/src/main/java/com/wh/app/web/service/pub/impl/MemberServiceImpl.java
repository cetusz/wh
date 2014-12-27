package com.wh.app.web.service.pub.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my.common.domain.Query;
import com.my.mybatis.support.Page;
import com.my.mybatis.support.Sort;
import com.wh.app.web.mapper.pub.MemberMapper;
import com.wh.app.web.model.pub.MemberEntity;
import com.wh.app.web.service.pub.MemberService;

@Service
@Transactional
public class MemberServiceImpl implements MemberService{

	@Autowired MemberMapper memberMapper;
	@Override
	public void saveOrUpdate(MemberEntity entity) {
		memberMapper.saveOrUpdate(entity);		
	}

	@Override
	public void deleteOne(Long id) {
		memberMapper.deleteOne(id);		
	}

	@Override
	public MemberEntity findOne(Long id) {
		return memberMapper.findOne(id);
	}

	@Override
	public void deleteMulti(List<Long> ids) {
		memberMapper.deleteMutil(ids);
	}

	@Override
	public List<MemberEntity> selectList(Query query, Sort sorts) {
		return memberMapper.selectList(query, sorts);
	}

	@Override
	public Page<MemberEntity> getPageList(Page<MemberEntity> pager,
			Query query, Sort sorts) {
		return memberMapper.getPage(pager, query, sorts);
	}

}
