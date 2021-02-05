package com.geek.shengka.backend.mapper;

import com.geek.shengka.backend.entity.SkVoice;
import com.geek.shengka.backend.params.res.SkVoiceListResParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkVoiceDAO extends MyBatisBaseDao<SkVoice, String> {
    /**
     * 通过视频ID查询所有的系统导入的语音列表
     *
     * @param videoId
     * @return java.util.List<com.geek.shengka.backend.params.res.SkVoiceListResParam>
     * @author qubianzhong
     * @Date 14:54 2019/8/21
     */
    List<SkVoiceListResParam> listByVideoIdOfPgc(@Param("videoId") String videoId);

    /**
     * 删除系统上传的历史数据
     *
     * @param videoId
     * @return int
     * @author qubianzhong
     * @Date 16:35 2019/8/21
     */
    int deleteByVideoIdOfPgc(@Param("videoId") String videoId);
}