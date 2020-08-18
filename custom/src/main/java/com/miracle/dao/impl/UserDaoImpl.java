package com.miracle.dao.impl;

import com.miracle.dao.UserDao;
import com.miracle.po.User;
import com.miracle.utils.ReflectUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * UserDaoImpl
 * Simple Class description.
 *
 * @author SHL
 * @version 1.0
 * @date 2020/8/14
 **/
public class UserDaoImpl implements UserDao {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<User> queryUserList(Map<String, Object> param) {
        Connection connection;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<User> users = new ArrayList<>();
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("select * from user where username = ? ");
            preparedStatement.setObject(1, param.get("username"));
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ResultSetMetaData metaData = resultSet.getMetaData();
                User user = new User();
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    Object value = resultSet.getObject(metaData.getColumnLabel(i));
                    ReflectUtils.setProperty(user, metaData.getColumnLabel(i), value);
                }
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }
}
