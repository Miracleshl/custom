package com.miracle.registry;

import com.miracle.config.BeanDefinition;

/**
 * BeanDefitionRegistry
 * 提供beanDefinition的获取和注册功能.
 *
 * @author SHL
 * @version 1.0
 * @date 2020/8/14
 **/
public interface BeanDefinitionRegistry {
    /**
     * 获取beanName对应的bd信息
     * @param beanName
     * @return
     */
    BeanDefinition getBeanDefinition(String beanName);

    /**
     * 注册bd信息
     * @param beanName
     * @param beanDefinition
     */
    void registryBeanDefinition(String beanName , BeanDefinition beanDefinition);
}
