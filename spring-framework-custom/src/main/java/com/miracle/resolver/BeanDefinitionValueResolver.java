package com.miracle.resolver;

import com.miracle.config.RuntimeBeanReference;
import com.miracle.config.TypedStringValue;
import com.miracle.factory.BeanFactory;

/**
 * BeanDefinitionResove
 * Simple Class description.
 *
 * @author SHL
 * @version 1.0
 * @date 2020/8/14
 **/
public class BeanDefinitionValueResolver {
    private BeanFactory beanFactory;

    public BeanDefinitionValueResolver(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Object resolverValue(Object value){
        Object valueToUse = null;
        if (value instanceof TypedStringValue) {
            TypedStringValue typedStringValue = (TypedStringValue) value;
            String stringValue = typedStringValue.getValue();
            Class<?> clazz = typedStringValue.getTargetType();
            if (clazz == String.class){
                valueToUse = stringValue;
            }else if (clazz == Integer.class){
                valueToUse = Integer.valueOf(stringValue);
            }else {
                //.....
            }
        } else if (value instanceof RuntimeBeanReference) {
            RuntimeBeanReference reference = (RuntimeBeanReference) value;
            valueToUse = beanFactory.getBean(reference.getRef());
        }else {
            //....
        }
        return valueToUse;
    }
}
