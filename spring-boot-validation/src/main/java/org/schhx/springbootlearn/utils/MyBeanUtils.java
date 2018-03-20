package org.schhx.springbootlearn.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.cglib.core.ReflectUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MyBeanUtils {

    public static <T, S> T newInstance(S source, Class<T> clazz) {
        if (source == null) {
            return null;
        }
        T t = (T) ReflectUtils.newInstance(clazz);
        BeanUtils.copyProperties(source, t);
        return t;
    }

    public static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }

    public static void copyNotNullProperties(Object source, Object target) {
        List<String> ignoreProperties = new ArrayList<>();
        try {
            for (Field field : source.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (field.get(source) != null) {
                    ignoreProperties.add(field.getName());
                }
            }
        } catch (Exception e){

        }
        BeanUtils.copyProperties(source, target, ignoreProperties.toArray(new String[ignoreProperties.size()]));
    }

    public static Boolean isEmpty(Object o) {
        try {
            for (Field field : o.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (field.get(o) != null) {
                    return false;
                }
            }
        } catch (Exception e) {

        }
        return true;
    }

    public static Boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }
}
