package com.miracle.service;

import com.miracle.po.User;

import java.util.List;
import java.util.Map;


public interface UserService {
	List<User> queryUsers(Map<String, Object> param);
}
