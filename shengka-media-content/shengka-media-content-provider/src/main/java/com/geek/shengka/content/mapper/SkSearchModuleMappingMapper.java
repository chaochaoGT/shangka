package com.geek.shengka.content.mapper;

import com.geek.shengka.content.entity.SkSearchModuleMapping;
import com.geek.shengka.content.entity.vo.SkSearchSourceVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SkSearchModuleMappingMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SkSearchModuleMapping record);

    int insertSelective(SkSearchModuleMapping record);

    SkSearchModuleMapping selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SkSearchModuleMapping record);

    int updateByPrimaryKey(SkSearchModuleMapping record);

	/**
	 * 统计总笔数
	 * @param paraMap:暂无参数
	 * @return
	 */
	Long selectSkSearchModuleMappingCount(Map<String, Object> paraMap);

	/**
	 * 取分页数据
	 * @param paraMap: startRecordNumb:开始记录号,pageSize:每页显示的笔数
	 * @return
	 */
	List<SkSearchModuleMapping> selectSkSearchModuleMappingList(Map<String, Object> paraMap);

    /**
     * 获取视频ids
     * @param paraMap
     * @return
     */
    List<String> selectSourceList(Map<String, Object> paraMap);

    /**
     * 话题列表
     * @param paraMap
     * @return
     */
    List<SkSearchSourceVO> getHotTopicSearchs(Map<String, Object> paraMap);

    /**
     * 人气榜单
     * @param paraMap
     * @return
     */
    List<SkSearchSourceVO> getPopularRankList(Map<String, Object> paraMap);
}
