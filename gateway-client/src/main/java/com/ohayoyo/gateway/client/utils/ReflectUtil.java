package com.ohayoyo.gateway.client.utils;

import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ReflectUtil {

    public static Class classGenericType(Class<?> classType) {
        return classGenericType(classType, 0);
    }

    public static Class classGenericType(Class<?> classType, int index) {
        List<Class<?>> classes = classesGenericType(classType);
        Class resultClass = null;
        if (!CollectionUtils.isEmpty(classes)) {
            int bound = classes.size() - 1;
            if (index >= 0 && index <= bound) {
                resultClass = classes.get(index);
            }
        }
        return resultClass;
    }

    public static List<Class<?>> classesGenericType(Class<?> classType) {
        List<Class<?>> classes = null;
        Type genericType = classType.getGenericSuperclass();
        if (null != genericType) {
            classes = new ArrayList<Class<?>>();
            if (genericType instanceof ParameterizedType) {
                Type[] types = ((ParameterizedType) genericType).getActualTypeArguments();
                if (!ObjectUtils.isEmpty(types)) {
                    for (Type type : types) {
                        if (type instanceof Class) {
                            classes.add((Class<?>) type);
                        } else {
                            classes.add(Object.class);
                        }
                    }
                }
            } else if (genericType instanceof Class) {
                classes.add((Class<?>) genericType);
            } else {
                classes.add(Object.class);
            }
        }
        return classes;
    }

}
