package com.geek.shengka.user.service.impl;

import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.common.cache.CacheProvider;
import com.geek.shengka.common.cache.CacheTimeConstant;
import com.geek.shengka.user.entity.SkNativeMessage;
import com.geek.shengka.user.mapper.SkNativeMessageDAO;
import com.geek.shengka.user.service.SkNativeMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 系统消息
 *
 * @author: yunfei
 * @create: 2019-08-08 16:34
 **/
@Service
public class SkNativeMessageServiceImpl implements SkNativeMessageService {

    @Autowired
    private SkNativeMessageDAO skNativeMessageDAO;

    private static String SK_NATIVE_MESSAGE = "sk_native_message";


    @Override
    public BaseResponse message() {
        List<SkNativeMessage> list = (List<SkNativeMessage>)CacheProvider.getObject(SK_NATIVE_MESSAGE, List.class);
        if (CollectionUtils.isEmpty(list)) {
            list = skNativeMessageDAO.list();
            CacheProvider.setObject(SK_NATIVE_MESSAGE, list, CacheTimeConstant.COMMON_EXPIRATION_TIME);
        }
        return BaseResponse.newSuccess(list);
    }
}
