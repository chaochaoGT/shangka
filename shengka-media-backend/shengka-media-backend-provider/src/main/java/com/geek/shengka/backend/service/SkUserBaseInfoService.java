package com.geek.shengka.backend.service;

import com.geek.shengka.backend.params.req.SkUserBaseInfoListReqParam;
import com.geek.shengka.backend.params.res.SkUserBaseInfoListResParam;
import com.geek.shengka.backend.util.PageVO;

/**
 * @author qubianzhong
 * @date 2019/8/20 16:26
 */
public interface SkUserBaseInfoService {
    /**
     * 根据用户昵称分页查询用户列表
     *
     * @param param
     * @return com.geek.shengka.backend.util.PageVO<com.geek.shengka.backend.params.res.SkUserBaseInfoListResParam>
     * @author qubianzhong
     * @Date 16:33 2019/8/20
     */
    PageVO<SkUserBaseInfoListResParam> list(SkUserBaseInfoListReqParam param);
}
