package com.miracle.reader;

import com.miracle.config.BeanDefinition;
import com.miracle.config.PropertyValue;
import com.miracle.config.RuntimeBeanReference;
import com.miracle.config.TypedStringValue;
import com.miracle.registry.BeanDefinitionRegistry;
import com.miracle.utils.ReflectUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * BeanDefinitionDocumentReader
 * Simple Class description.
 *
 * @author SHL
 * @version 1.0
 * @date 2020/8/14
 **/
public class XmlBeanDefinitionDocumentReader {
    private static Logger logger = Logger.getLogger("XmlBeanDefinitionDocumentReader");
    private BeanDefinitionRegistry beanDefinitionRegistry;

    public XmlBeanDefinitionDocumentReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    public void doLoadBeanDefinition(Document document) {
        parserRootElement(document.getRootElement());
    }

    public void parserRootElement(Element rootElement) {
        List<Element> beanElements = rootElement.elements("bean");
        for (Element beanElement : beanElements) {
            parserBeanElement(beanElement);
        }
    }

    private void parserBeanElement(Element beanElement) {
        String name = beanElement.attributeValue("name");
        String id = beanElement.attributeValue("id");
        String clazz = beanElement.attributeValue("class");
        String scope = beanElement.attributeValue("scope");
        String initMethod = beanElement.attributeValue("init-method");
        scope = StringUtils.isBlank(scope)?"singleton":scope;
        if (StringUtils.isBlank(name) && StringUtils.isBlank(id) && StringUtils.isBlank(clazz)) {
            logger.info("property id and name and class all null");
        }
        Class<?> clazzType = ReflectUtils.getClassType(clazz);
        String className = StringUtils.substringAfterLast(clazz,".");
        String beanName = StringUtils.isNotBlank(name) ? name : StringUtils.isNotBlank(id) ? id : className;
        List<Element> pvElements = beanElement.elements("property");
        List<PropertyValue> propertyValues = new ArrayList<>(pvElements.size());
        for (Element propertyElement : pvElements) {
            PropertyValue propertyValue = parserPropertyElement(propertyElement,clazzType);
            propertyValues.add(propertyValue);
        }
        BeanDefinition beanDefinition = new BeanDefinition(clazz,beanName);
        beanDefinition.setInitMethod(initMethod);
        beanDefinition.setClazzName(clazz);
        beanDefinition.setScope(scope);
        beanDefinition.setPropertyValues(propertyValues);
        beanDefinitionRegistry.registryBeanDefinition(beanName,beanDefinition);
    }

    private PropertyValue parserPropertyElement(Element propertyElement, Class<?> clazzType) {
        String name = propertyElement.attributeValue("name");
        String ref = propertyElement.attributeValue("ref");
        String valueAttr = propertyElement.attributeValue("value");
        if (StringUtils.isNotBlank(ref) && StringUtils.isNotBlank(valueAttr)){
            logger.info("ref and value not all is not blank");
        }
        if (StringUtils.isBlank(ref) && StringUtils.isBlank(valueAttr)){
            logger.info("ref and value not all is blank");
        }
        Object value = null;
        if (StringUtils.isNotBlank(valueAttr)){
            Class<?> targetType = ReflectUtils.getTypeByFieldName(clazzType,name);
            value = new TypedStringValue(valueAttr,targetType);
        }else if (StringUtils.isNotBlank(ref)){
            value = new RuntimeBeanReference(ref);
        }
        return new PropertyValue(name,value);
    }
}
