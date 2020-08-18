package com.miracle.registry;

import java.util.HashMap;
import java.util.Map;

/**
 * DefaultSingletonBeanRegistry
 * Simple Class description.
 *
 * @author SHL
 * @version 1.0
 * @date 2020/8/14
 **/
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    private Map<String,Object> singletonBeans = new HashMap<>();
    @Override
    public Object getSingleton(String beanName) {
        return singletonBeans.get(beanName);
    }

    @Override
    public void registrySingleton(String beanName, Object bean) {
        singletonBeans.put(beanName,bean);
    }
}
