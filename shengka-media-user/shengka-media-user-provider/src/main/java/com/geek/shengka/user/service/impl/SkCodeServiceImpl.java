package com.geek.shengka.user.service.impl;

import com.geek.shengka.user.entity.SkCode;
import com.geek.shengka.user.mapper.SkCodeDAO;
import com.geek.shengka.user.response.SkCodeResponse;
import com.geek.shengka.user.service.SkCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 字典业务实现
 *
 * @author: yunfei
 * @create: 2019-08-07 09:29
 **/
@Service
public class SkCodeServiceImpl implements SkCodeService {

    @Autowired
    private SkCodeDAO skCodeDAO;


    /**
     * 根据codetype查询列表
     * @param codeType
     * @return
     */
    @Override
    public List<SkCodeResponse> listConfig(String codeType) {
        List<SkCode> list = skCodeDAO.selectBycodeType(codeType);
        return list.stream().map(skCode -> transform(skCode)).collect(Collectors.toList());
    }

    private SkCodeResponse transform(SkCode skCode) {
        SkCodeResponse response=new SkCodeResponse();
        response.setId(skCode.getId());
        response.setCode(skCode.getCode());
        response.setName(skCode.getName());
        return response;
    }
}
