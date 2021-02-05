package com.geek.shengka.user.mapper;

import com.geek.shengka.user.entity.SkNativeMessage;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SkNativeMessageDAO继承基类
 */
@Repository
public interface SkNativeMessageDAO extends MyBatisBaseDao<SkNativeMessage, Long> {

    /**
     * 系统消息列表
     * @return
     */
    List<SkNativeMessage>  list();
}