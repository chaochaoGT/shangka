package com.geek.shengka.backend.service;

import com.geek.shengka.backend.exception.BaseException;
import com.geek.shengka.backend.params.req.SkVoicePublishReqParam;
import com.geek.shengka.backend.params.res.SkVoiceListResParam;

import java.util.List;

/**
 * @author qubianzhong
 * @date 2019/8/20 17:05
 */
public interface SkVoiceService {
    /**
     * 发布语音
     *
     * @param body
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 17:11 2019/8/20
     */
    Boolean publish(List<SkVoicePublishReqParam> body) throws BaseException;

    /**
     * 通过视频ID查询所有的系统导入的语音列表
     *
     * @param videoId
     * @return java.lang.Object
     * @author qubianzhong
     * @Date 14:52 2019/8/21
     */
    List<SkVoiceListResParam> listByVideoIdOfPgc(String videoId);
}
