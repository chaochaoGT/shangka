package com.geek.shengka.backend.mapper;

import com.geek.shengka.backend.entity.SkUserBaseInfo;
import com.geek.shengka.backend.params.req.SkUserBaseInfoListReqParam;
import com.geek.shengka.backend.params.res.SkUserBaseInfoListResParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SkUserBaseInfoDAO extends MyBatisBaseDao<SkUserBaseInfo, Long> {

    /**
     * 根据用户昵称分页查询用户列表
     *
     * @param param
     * @return java.util.List<com.geek.shengka.backend.params.res.SkUserBaseInfoListResParam>
     * @author qubianzhong
     * @Date 16:34 2019/8/20
     */
    List<SkUserBaseInfoListResParam> list(SkUserBaseInfoListReqParam param);

    /**
     * 更新用户语音数加1
     *
     * @param userId
     * @return int
     * @author qubianzhong
     * @Date 18:40 2019/8/20
     */
    int updateVoiceNumPlusOne(Long userId);

    /**
     * 查询存在的userIds
     *
     * @param userIds
     * @return java.util.List<java.lang.Long>
     * @author qubianzhong
     * @Date 20:23 2019/8/20
     */
    List<Long> selectExistIds(@Param(value = "ids") Set<Long> userIds);
}