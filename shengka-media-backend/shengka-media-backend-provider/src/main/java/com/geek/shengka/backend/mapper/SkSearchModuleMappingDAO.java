package com.geek.shengka.backend.mapper;

import com.geek.shengka.backend.entity.SkSearchModuleMapping;
import com.geek.shengka.backend.params.req.SkSearchPageConfigMappingListReqParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author qubianzhong
 * @date 2019/8/5 10:39
 */
@Repository
public interface SkSearchModuleMappingDAO extends MyBatisBaseDao<SkSearchModuleMapping, Long> {
    /**
     * 批量新增
     *
     * @param moduleMappings
     * @return int
     * @author qubianzhong
     * @Date 11:03 2019/8/5
     */
    int insertSelectiveList(@Param(value = "list") List<SkSearchModuleMapping> moduleMappings);

    /**
     * 根据配置ID来删除
     *
     * @param configId
     * @return int
     * @author qubianzhong
     * @Date 11:15 2019/8/5
     */
    int deleteByConfigId(Long configId);

    /**
    *  通过配置ID来查询资源ID列表
    *
    * @author qubianzhong
    * @Date 14:27 2019/8/7
    * @param param
    * @return java.util.List<java.lang.String>
    */
    List<String> listIds(SkSearchPageConfigMappingListReqParam param);
}
