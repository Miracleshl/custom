package com.miracle.factory.support;

import com.miracle.config.BeanDefinition;
import com.miracle.factory.BeanFactory;
import com.miracle.registry.DefaultSingletonBeanRegistry;

import java.util.Objects;

/**
 * AbstractBeanFactory
 * Simple Class description.
 *
 * @author SHL
 * @version 1.0
 * @date 2020/8/14
 **/
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    @Override
    public Object getBean(String beanName) {
        Object bean = getSingleton(beanName);
        if (Objects.nonNull(bean)){
            return bean;
        }
        // 3.如果没有改对象，则获取对应的BeanDefinition信息
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        if (beanDefinition == null) {
            return null;
        }
        // 4.判断是单例还是多例，如果是单例，则走单例创建Bean流程
        bean = createBean(beanDefinition) ;
        if (beanDefinition.isSingleton()){
            registrySingleton(beanName,bean);
        }else if(beanDefinition.isPrototype()){
            //...
        }
        // 5.单例流程中，需要将创建出来的Bean放入singletonObjects集合中
        // 6.如果是多例，走多例的创建Bean流程
        return bean;
    }

    // 需要延迟到DefaultListableBeanFactory去实现
    protected abstract BeanDefinition getBeanDefinition(String beanName);

    // 需要延迟到AbstractAutowiredCapableBeanFactory去实现
    protected abstract Object createBean(BeanDefinition bd);
}
