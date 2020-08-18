package com.miracle.registry;

/**
 * SingletonBeanRegistry
 * 提供对单例bean的获取 注册操作.
 *
 * @author SHL
 * @version 1.0
 * @date 2020/8/14
 **/
public interface SingletonBeanRegistry {
    /**
     * 通过beanName获取单例bean
     * @param beanName
     * @return
     */
    Object getSingleton(String beanName);

    /**
     * 注册单例bean
     * @param beanName
     * @param bean
     */
    void registrySingleton(String beanName,Object bean);
}
