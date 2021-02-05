package com.geek.shengka.user.service;

import com.geek.shengka.user.entity.SkVersion;


/**
 * 版本信息
 * @author: yunfei
 * @create: 2019-08-01 13:56
 **/
public interface SkVersionService {

    /**
     * 获取版本信息
     * @param channelCode
     * @param prdType
     * @return
     */
    SkVersion lastVersion(String channelCode, String prdType);
}
