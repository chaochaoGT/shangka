package com.geek.shengka.backend.mapper;

import com.geek.shengka.backend.entity.SkPublishVideo;
import com.geek.shengka.backend.params.req.SkPublishVideoListReqParam;
import com.geek.shengka.backend.params.res.SkPublishVideoListResParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author qubianzhong
 * @date 2019/8/20 15:30
 */
@Repository
public interface SkPublishVideoDAO extends MyBatisBaseDao<SkPublishVideo, String> {
    /**
     * 根据视频标题进行分页查询
     *
     * @param param
     * @return java.util.List<com.geek.shengka.backend.params.res.SkPublishVideoListResParam>
     * @author qubianzhong
     * @Date 15:53 2019/8/20
     */
    List<SkPublishVideoListResParam> list(SkPublishVideoListReqParam param);

    /**
     * 根据视频IDS查询时系统导入的视频IDS
     *
     * @param videoIds
     * @return java.util.List<java.lang.String>
     * @author qubianzhong
     * @Date 11:41 2019/8/21
     */
    List<String> selectPgcVideoIds(@Param("ids") List<String> videoIds);
}
