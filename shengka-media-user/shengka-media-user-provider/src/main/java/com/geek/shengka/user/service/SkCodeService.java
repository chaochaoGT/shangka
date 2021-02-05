package com.geek.shengka.user.service;

import com.geek.shengka.user.response.SkCodeResponse;

import java.util.List;

/**
 * @author: yunfei
 * @create: 2019-08-07 09:29
 **/
public interface SkCodeService {

    /**
     * 字典业务查询
     * @param codeType
     * @return
     */
    List<SkCodeResponse> listConfig(String codeType);
}
