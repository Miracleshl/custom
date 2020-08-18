package com.miracle.nbew;

import com.miracle.a.A;
import com.miracle.factory.BeanFactory;
import com.miracle.factory.support.DefaultListableBeanFactory;
import com.miracle.po.User;
import com.miracle.service.UserService;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private BeanFactory beanFactory;
    @Before
    public void before(){
        String location = "beans.xml";
        beanFactory = new DefaultListableBeanFactory(location);
    }
    // 由A程序员编写
    @Test
    public void test(){
        // A 程序员他其实只想使用业务对象去调用对应的服务
        // B 程序员编写了一段代码给A程序员提供对象
//        UserService userService = getUserService();
        UserService userService = (UserService) beanFactory.getBean("userService");
        //实现用户查询功能
        Map<String, Object> map = new HashMap<>();
        map.put("username","赵云");

        List<User> users = userService.queryUsers(map);
        System.out.println(users);
    }

    @Test
    public void a(){
        new A().show("s");
    }
}
