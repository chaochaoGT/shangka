package com.geek.shengka.backend.service;

import com.geek.shengka.backend.entity.SkTopicConfig;
import com.geek.shengka.backend.exception.BaseException;
import com.geek.shengka.backend.params.req.SkTopicConfigAddReqParam;
import com.geek.shengka.backend.params.req.SkTopicConfigListReqParam;
import com.geek.shengka.backend.params.req.SkTopicConfigUpdateReqParam;
import com.geek.shengka.backend.params.res.SkTopicConfigInfoResParam;
import com.geek.shengka.backend.params.res.SkTopicConfigListResParam;
import com.geek.shengka.backend.util.PageVO;

import java.util.List;

/**
 * @author qubianzhong
 * @date 2019/8/1 17:02
 */
public interface SkTopicConfigService {
    /**
     * 列表--分页
     *
     * @param param
     * @return com.geek.shengka.backend.util.PageVO<com.geek.shengka.backend.params.res.SkTopicConfigListResParam>
     * @author qubianzhong
     * @Date 17:19 2019/8/1
     */
    PageVO<SkTopicConfigListResParam> list(SkTopicConfigListReqParam param);

    /**
     * 新增
     *
     * @param add
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 17:22 2019/8/1
     */
    Boolean add(SkTopicConfigAddReqParam add) throws BaseException;

    /**
     * 更新
     *
     * @param update
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 17:22 2019/8/1
     */
    Boolean update(SkTopicConfigUpdateReqParam update) throws BaseException;

    /**
     * 删除
     *
     * @param id
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 17:22 2019/8/1
     */
    Boolean delete(Long id) throws BaseException;

    /**
     * 根据话题名称查询数量
     *
     * @param topicName
     * @return int
     * @author qubianzhong
     * @Date 18:16 2019/8/1
     */
    Long countByTopicName(String topicName);

    /**
     * 详情
     *
     * @param id
     * @return com.geek.shengka.backend.params.res.SkTopicConfigInfoResParam
     * @author qubianzhong
     * @Date 15:15 2019/8/5
     */
    SkTopicConfigInfoResParam info(Long id);

    /**
     * 通过ID来查询列表
     *
     * @param sourceIdStrList
     * @return java.util.List<com.geek.shengka.backend.entity.SkTopicConfig>
     * @author qubianzhong
     * @Date 14:33 2019/8/7
     */
    List<SkTopicConfig> getInfosByIds(List<String> sourceIdStrList);

    /**
     * 校验话题IDS
     *
     * @param ids
     * @return void
     * @author qubianzhong
     * @Date 16:19 2019/8/13
     */
    void checkTopicIds(List<String> ids) throws BaseException;
}
