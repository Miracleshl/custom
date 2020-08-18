package com.miracle.factory.support;

import com.miracle.config.BeanDefinition;
import com.miracle.config.PropertyValue;
import com.miracle.resolver.BeanDefinitionValueResolver;
import com.miracle.utils.ReflectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * AbstractAutowiredCapableBeanFactory
 * Simple Class description.
 *
 * @author SHL
 * @version 1.0
 * @date 2020/8/14
 **/
public abstract class AbstractAutowiredCapableBeanFactory extends AbstractBeanFactory {
    @Override
    public Object createBean(BeanDefinition beanDefinition) {
        //实例化bean
        Object bean = doCreateBean(beanDefinition);
        //TODO 循环依赖
        //属性填充
        populateBean(bean, beanDefinition);
        //初始化bean
        initializeBean(bean, beanDefinition);
        return bean;
    }

    private void initializeBean(Object bean, BeanDefinition beanDefinition) {
        // TODO 需要针对Aware接口标记的类进行特殊处理

        // TODO 可以进行IntilizingBean接口的处理
        invokeInitMethod(bean,beanDefinition);
    }
    private void invokeInitMethod(Object bean, BeanDefinition beanDefinition) {
        String initMethod = beanDefinition.getInitMethod();
        if (StringUtils.isNotBlank(initMethod)) {
            ReflectUtils.invokeMethod(bean, initMethod);
        }
    }

    private void populateBean(Object bean, BeanDefinition beanDefinition) {
        List<PropertyValue> propertyValues = beanDefinition.getPropertyValues();
        BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(this);
        for (PropertyValue propertyValue : propertyValues) {
            String name = propertyValue.getName();
            Object value = propertyValue.getValue();
            Object valueToUse = resolver.resolverValue(value);
            ReflectUtils.setProperty(bean, name, valueToUse);
        }
    }

    private Object doCreateBean(BeanDefinition beanDefinition) {
        // TODO 静态工厂方法、工厂实例方法
        // 构造器方式去创建Bean实例
        Class<?> clazz = beanDefinition.getClazzType();
        return ReflectUtils.createObjectByConstructor(clazz);
    }
}
