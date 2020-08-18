package com.miracle.factory;

/**
 * BeanFactory
 * Simple Class description.
 *
 * @author SHL
 * @version 1.0
 * @date 2020/8/14
 **/
public interface BeanFactory {
    /**
     * 通过beanName获取bean实例
     * @param beanName
     * @return 容器中beanName对应的bean实例
     */
    Object getBean(String beanName);
}
