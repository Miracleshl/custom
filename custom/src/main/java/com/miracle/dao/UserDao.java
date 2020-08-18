package com.miracle.dao;

import com.miracle.po.User;

import java.util.List;
import java.util.Map;

/**
 * UserDao
 * Simple Class description.
 *
 * @author SHL
 * @version 1.0
 * @date 2020/8/14
 **/
public interface UserDao {
    /**
     * 根据参数查询User集合
     * @param param
     * @return
     */
    List<User> queryUserList(Map<String, Object> param);
}
