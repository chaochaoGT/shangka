package com.geek.shengka.backend.service;

import com.geek.shengka.backend.exception.BaseException;
import com.geek.shengka.backend.params.req.SkContentVoice;
import com.geek.shengka.backend.params.res.SkContentCenterCategorysResParam;
import com.geek.shengka.backend.params.res.SkContentVideoInfoResParam;

import java.util.List;

/**
 * @author qubianzhong
 * @date 2019/8/5 20:39
 */
public interface SkContentCenterService {

    /**
     * 获取token
     *
     * @param
     * @return java.lang.String
     * @author qubianzhong
     * @Date 20:54 2019/8/5
     */
    String getToken();

    /**
     * 根据IDS来查询视频详情列表
     *
     * @param ids
     * @return java.util.List<com.geek.shengka.backend.params.res.SkContentVideoInfoResParam>
     * @author qubianzhong
     * @Date 13:30 2019/8/6
     */
    List<SkContentVideoInfoResParam> getVideoInfosByIds(List<String> ids) throws BaseException;


    /**
     * 内容中心视频分类列表
     *
     * @param
     * @return java.util.List<com.geek.shengka.backend.params.res.SkContentCenterCategorysResParam>
     * @author qubianzhong
     * @Date 16:55 2019/8/8
     */
    List<SkContentCenterCategorysResParam> categories() throws BaseException;

    /**
     * 校验视频IDS
     *
     * @param ids
     * @return void
     * @author qubianzhong
     * @Date 15:57 2019/8/13
     */
    void checkVideoIds(List<String> ids) throws BaseException;

    /**
     * 上传语音
     *
     * @param contentVoice
     * @return void
     * @author qubianzhong
     * @Date 18:02 2019/8/20
     */
    void uploadVoice(SkContentVoice contentVoice) throws BaseException;
}
