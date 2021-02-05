package com.geek.shengka.backend.util;


import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * dozer 类型转换
 *
 * @author qubianzhong
 * @date 2019/3/26 15:22
 */
public class BeanMapperUtils {
    private BeanMapperUtils() {
    }

    private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();


    public static <T> T map(Object source, Class<T> destinationClass) {
        if (source == null) {
            return null;
        }
        return mapper.map(source, destinationClass);
    }

    public static <T> List<T> mapList(Collection sourceList, Class<T> destinationClass) {
        if (sourceList == null || sourceList.isEmpty()) {
            return new ArrayList<>();
        }
        List<T> destinationList = new ArrayList<>();
        for (Object sourceObject : sourceList) {
            destinationList.add(mapper.map(sourceObject, destinationClass));
        }
        return destinationList;
    }
}
