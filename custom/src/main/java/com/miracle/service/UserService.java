package com.miracle.service;

import com.miracle.po.User;

import java.util.List;
import java.util.Map;


public interface UserService {
    /**
     * 试试加个tag
     *
     * @param param
     * @return
     */
    List<User> queryUsers(Map<String, Object> param);
}
