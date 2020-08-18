package com.miracle.service.impl;

import com.miracle.dao.UserDao;
import com.miracle.po.User;
import com.miracle.service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {

	/** 依赖注入UserDao*/
	private UserDao userDao;

	/** setter方法注入UserDao*/
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<User> queryUsers(Map<String, Object> param) {
		return userDao.queryUserList(param);
	}

}
