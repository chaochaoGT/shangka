package com.geek.shengka.user.service.impl;

import com.geek.shengka.user.entity.SkChannel;
import com.geek.shengka.user.entity.SkVersion;
import com.geek.shengka.user.mapper.SkChannelDAO;
import com.geek.shengka.user.mapper.SkVersionDAO;
import com.geek.shengka.user.service.SkVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 版本业务实现
 *
 * @author: yunfei
 * @create: 2019-08-01 13:56
 **/
@Service
public class SkVersionServiceImpl implements SkVersionService {

    @Autowired
    private SkVersionDAO skVersionDAO;

    @Autowired
    private SkChannelDAO skChannelDAO;


    @Override
    public SkVersion lastVersion(String channelCode, String prdType) {
        SkChannel channel = skChannelDAO.selectChannelByCode(channelCode);
        if(channel==null){
            return null;
        }
        return skVersionDAO.lastVersion(channel.getId(),prdType);
    }
}
