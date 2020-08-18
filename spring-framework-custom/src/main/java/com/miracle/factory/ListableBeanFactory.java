package com.miracle.factory;

import java.util.List;

/**
 * ListableBeanFactory
 * 提供对bean批量操作的能力.
 *
 * @author SHL
 * @version 1.0
 * @date 2020/8/14
 **/
public interface ListableBeanFactory extends BeanFactory {
    /**
     * 获取当前类型及其子类的对象
     * @param clazz
     * @return
     */
    List<Object> getBeans(Class<?> clazz);
}
