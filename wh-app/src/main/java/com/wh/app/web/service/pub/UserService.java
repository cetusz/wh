package com.wh.app.web.service.pub;

import com.wh.app.web.model.pub.UserEntity;
import com.wh.app.web.service.BaseService;

public interface UserService extends BaseService<UserEntity> {

	public UserEntity login(String userName,String password);
}
