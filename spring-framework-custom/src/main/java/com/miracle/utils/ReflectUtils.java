package com.miracle.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * ReflenceUtils
 * Simple Class description.
 *
 * @author SHL
 * @version 1.0
 * @date 2020/8/14
 **/
public class ReflectUtils {
    public static void invokeMethod(Object bean, String initMethod) {
        try {
            Class<?> clazz = bean.getClass();
            Method method = clazz.getDeclaredMethod(initMethod);
            method.setAccessible(true);
            method.invoke(bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object createObjectByConstructor(Class<?> clazz) {
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setProperty(Object bean, String name, Object valueToUse) {
        try {
            Class<?> clazz = bean.getClass();
            Field field = findField(clazz, name);
            field.setAccessible(true);
            field.set(bean, valueToUse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Class<?> getClassType(String clazz) {
        try {
            return Class.forName(clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Field findField(Class<?> clazzType, String name) {
        List<Field> fieldList = new ArrayList<>();
        try {
            while (clazzType != null) {
                Field[] fields = clazzType.getDeclaredFields();
                fieldList.addAll(Arrays.asList(fields));
                clazzType = clazzType.getSuperclass();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Field field : fieldList) {
            if (Objects.equals(name, field.getName())) {
                field.setAccessible(true);
                return field;
            }
        }
        return null;
    }

    public static Class<?> getTypeByFieldName(Class<?> clazzType, String name) {
        Field field = findField(clazzType, name);
        return field.getType();
    }
}
