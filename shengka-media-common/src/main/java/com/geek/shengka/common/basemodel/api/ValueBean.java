package com.geek.shengka.common.basemodel.api;

/**
 * 用于封装单个值的实例
 *
 * @author SteveGuo
 */
public interface ValueBean<T> {

    /**
     * 获取实例的描述
     * @return
     */
    String getDesc();

    /**
     * 获取实例的值
     * @return
     */
    T getValue();

}
