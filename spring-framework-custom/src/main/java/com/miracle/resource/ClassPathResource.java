package com.miracle.resource;

import java.io.InputStream;

/**
 * ClasspathXmlResource
 * Simple Class description.
 *
 * @author SHL
 * @version 1.0
 * @date 2020/8/14
 **/
public class ClassPathResource implements Resource {
    private String location;

    public ClassPathResource(String location) {
        this.location = location;
    }

    @Override
    public InputStream getInputStream() {
        return this.getClass().getClassLoader().getResourceAsStream(location);
    }
}
