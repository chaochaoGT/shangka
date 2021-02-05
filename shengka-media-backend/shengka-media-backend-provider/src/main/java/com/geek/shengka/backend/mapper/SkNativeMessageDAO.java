package com.geek.shengka.backend.mapper;

import com.geek.shengka.backend.entity.SkNativeMessage;
import com.geek.shengka.backend.params.req.SkNativeMessageListReqParam;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author qubianzhong
 * @date 2019/8/5 10:23
 */
@Repository
public interface SkNativeMessageDAO extends MyBatisBaseDao<SkNativeMessage, Long> {
    /**
     * 列表
     *
     * @param param
     * @return java.util.List<com.geek.shengka.backend.entity.SkNativeMessage>
     * @author qubianzhong
     * @Date 16:18 2019/8/5
     */
    List<SkNativeMessage> list(SkNativeMessageListReqParam param);
}
