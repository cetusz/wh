package com.wh.app.web.service.pub.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my.common.domain.Query;
import com.my.mybatis.support.Page;
import com.my.mybatis.support.Sort;
import com.wh.app.web.mapper.pub.UserMapper;
import com.wh.app.web.model.pub.UserEntity;
import com.wh.app.web.model.query.UserQuery;
import com.wh.app.web.service.pub.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	UserMapper userMapper;
	@Override
	public void saveOrUpdate(UserEntity entity) {
		userMapper.saveOrUpdate(entity);		
	}

	@Override
	public void deleteOne(Long id) {
		userMapper.deleteOne(id);		
	}

	@Override
	public UserEntity findOne(Long id) {
		return userMapper.findOne(id);
	}

	@Override
	public void deleteMulti(List<Long> ids) {
		userMapper.deleteMutil(ids);
	}

	@Override
	public List<UserEntity> selectList(Query query, Sort sorts) {
		// TODO Auto-generated method stub
		return userMapper.selectList(query, sorts);
	}

	@Override
	public Page<UserEntity> getPageList(Page<UserEntity> pager, Query query,
			Sort sorts) {
		return userMapper.getPage(pager, query, sorts);
	}

	@Override
	@Transactional
	public UserEntity login(String userName, String password) {
		UserQuery query = new UserQuery();
		query.setUserName(userName);
		query.setPassWord(password);
		List<UserEntity> users = this.selectList(query, null);
		if(users.size()>0){
			return users.get(0);
		}
		return null;
	}

}
