package com.miracle.reader;

import com.miracle.registry.BeanDefinitionRegistry;
import com.miracle.resource.Resource;
import com.miracle.utils.DocumentUtils;
import org.dom4j.Document;

/**
 * XmlBeanDefinitionReader
 * Simple Class description.
 *
 * @author SHL
 * @version 1.0
 * @date 2020/8/14
 **/
public class XmlBeanDefinitionReader {
    private BeanDefinitionRegistry beanDefinitionRegistry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    public void loadBeanDefinition(Resource resource) {
        Document document = DocumentUtils.getDocument(resource.getInputStream());
        XmlBeanDefinitionDocumentReader reader = new XmlBeanDefinitionDocumentReader(beanDefinitionRegistry);
        reader.doLoadBeanDefinition(document);
    }
}
