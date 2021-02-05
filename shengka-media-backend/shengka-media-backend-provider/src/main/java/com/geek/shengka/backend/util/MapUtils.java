package com.geek.shengka.backend.util;

/**
 * @Author: zzy
 * @Date: 2019/6/6 15:59
 * @Version 1.0
 */

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 使用反射进行转换
 */
@Slf4j
public class MapUtils {

    public static <T> List<T> mapToObject(List<Map<String, String>> list, Class<T> beanClass) {
        List<T> result = new ArrayList<>();
        if (list == null) {
            return Collections.emptyList();
        }
        T obj = null;
        try {
            for (Map<String, String> map : list) {
                obj = beanClass.newInstance();
                Field[] fields = obj.getClass().getDeclaredFields();
                for (Field field : fields) {
                    int mod = field.getModifiers();
                    if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                        continue;
                    }
                    field.setAccessible(true);
                    Class<?> type = field.getType();
                    if (type.equals(Date.class)) {
                        String dateStr = map.get(field.getName());
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = format.parse(dateStr);
                        field.set(obj, date);
                    } else if (type.equals(BigDecimal.class)) {
                        String s = map.get(field.getName());
                        if (!StringUtils.isEmpty(s)) {
                            field.set(obj, new BigDecimal(s));
                        } else {
                            field.set(obj, BigDecimal.ZERO);
                        }
                    } else if (type.equals(Long.class)) {
                        String s = map.get(field.getName());
                        if (!StringUtils.isEmpty(s)) {
                            field.set(obj, Long.valueOf(s));
                        } else {
                            field.set(obj, 0L);
                        }
                    } else if (type.equals(Short.class)) {
                        String s = map.get(field.getName());
                        if (!StringUtils.isEmpty(s)) {
                            field.set(obj, Short.valueOf(s));
                        } else {
                            field.set(obj, 0L);
                        }
                    } else if (type.equals(Integer.class)) {
                        String s = map.get(field.getName());
                        if (!StringUtils.isEmpty(s)) {
                            field.set(obj, Integer.valueOf(s));
                        } else {
                            field.set(obj, 0L);
                        }
                    } else if (type.equals(Byte.class)) {
                        String s = map.get(field.getName());
                        if (!StringUtils.isEmpty(s)) {
                            field.set(obj, Byte.valueOf(s));
                        } else {
                            field.set(obj, 0L);
                        }
                    } else {
                        field.set(obj, map.get(field.getName()));
                    }
                }
                result.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
        return result;
    }

    public static Map<String, Object> objectToMap(Object obj) {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        try {
            Field[] declaredFields = obj.getClass().getDeclaredFields();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }
        } catch (IllegalAccessException e) {
            log.error(e.toString());
        }
        return map;
    }
}