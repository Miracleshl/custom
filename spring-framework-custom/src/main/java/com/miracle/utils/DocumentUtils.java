package com.miracle.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * DocumentUtils
 * Simple Class description.
 *
 * @author SHL
 * @version 1.0
 * @date 2020/8/14
 **/
public class DocumentUtils {
    public static Document getDocument(InputStream inputStream) {
        SAXReader reader = new SAXReader();
        try {
            return reader.read(inputStream);
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(inputStream)) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
