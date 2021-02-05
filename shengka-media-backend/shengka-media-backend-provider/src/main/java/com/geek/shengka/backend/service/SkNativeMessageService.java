package com.geek.shengka.backend.service;

import com.geek.shengka.backend.entity.SkNativeMessage;
import com.geek.shengka.backend.params.req.SkNativeMessageAddReqParam;
import com.geek.shengka.backend.params.req.SkNativeMessageListReqParam;
import com.geek.shengka.backend.params.req.SkNativeMessageUpdateReqParam;
import com.geek.shengka.backend.util.PageVO;

/**
 * @author qubianzhong
 * @date 2019/8/5 10:26
 */
public interface SkNativeMessageService {
    /**
     * 详情
     *
     * @param id
     * @return com.geek.shengka.backend.entity.SkNativeMessage
     * @author qubianzhong
     * @Date 16:14 2019/8/5
     */
    SkNativeMessage info(Long id);

    /**
     * 列表--分页
     *
     * @param param
     * @return com.geek.shengka.backend.util.PageVO<com.geek.shengka.backend.entity.SkNativeMessage>
     * @author qubianzhong
     * @Date 16:14 2019/8/5
     */
    PageVO<SkNativeMessage> list(SkNativeMessageListReqParam param);

    /**
     * 新增
     *
     * @param add
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 16:15 2019/8/5
     */
    Boolean add(SkNativeMessageAddReqParam add);

    /**
     * 更新
     *
     * @param update
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 16:15 2019/8/5
     */
    Boolean update(SkNativeMessageUpdateReqParam update);

    /**
     * 删除
     *
     * @param id
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 16:15 2019/8/5
     */
    Boolean delete(Long id);
}
