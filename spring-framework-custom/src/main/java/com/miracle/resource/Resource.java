package com.miracle.resource;

import java.io.InputStream;

/**
 * Resource
 * Simple Class description.
 *
 * @author SHL
 * @version 1.0
 * @date 2020/8/14
 **/
public interface Resource {
    /**
     * 获取资源信息流
     * @return
     */
    InputStream getInputStream();
}
