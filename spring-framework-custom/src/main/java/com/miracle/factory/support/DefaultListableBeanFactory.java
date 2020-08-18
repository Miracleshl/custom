package com.miracle.factory.support;

import com.miracle.config.BeanDefinition;
import com.miracle.reader.XmlBeanDefinitionReader;
import com.miracle.registry.BeanDefinitionRegistry;
import com.miracle.resource.ClassPathResource;
import com.miracle.resource.Resource;

import java.util.HashMap;
import java.util.Map;

/**
 * DefaultListableBeanFactory
 * Simple Class description.
 *
 * @author SHL
 * @version 1.0
 * @date 2020/8/14
 **/
public class DefaultListableBeanFactory extends AbstractAutowiredCapableBeanFactory implements BeanDefinitionRegistry {
    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    public DefaultListableBeanFactory() {
    }

    public DefaultListableBeanFactory(String location) {
        loadBeanDefinition(location);
    }

    public void loadBeanDefinition(String location) {
        beanDefinitionMap.clear();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(this);
        //完成XML解析，其实就是完成BeanDefinition的注册
        // XML解析，解析的结果，放入beanDefinitions中
        // 获取流对象
        Resource resource = new ClassPathResource(location);
        // 按照spring定义的标签语义去解析Document文档
        reader.loadBeanDefinition(resource);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return beanDefinitionMap.get(beanName);
    }

    @Override
    public void registryBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }
}
