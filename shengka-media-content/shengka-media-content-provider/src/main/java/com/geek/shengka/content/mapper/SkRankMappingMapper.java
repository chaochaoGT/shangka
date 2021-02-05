package com.geek.shengka.content.mapper;

import com.geek.shengka.content.entity.SkRankMapping;
import com.geek.shengka.content.entity.vo.SkSearchSourceVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface SkRankMappingMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SkRankMapping record);

    int insertSelective(SkRankMapping record);

    SkRankMapping selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SkRankMapping record);

    int updateByPrimaryKey(SkRankMapping record);

	/**
	 * 统计总笔数
	 * @param paraMap:暂无参数
	 * @return
	 */
	Long selectSkRankMappingCount(Map<String, Object> paraMap);

	/**
	 * 取分页数据
	 * @param paraMap: startRecordNumb:开始记录号,pageSize:每页显示的笔数
	 * @return
	 */
	List<SkRankMapping> selectSkRankMappingList(Map<String, Object> paraMap);

    /**
     * 更多人气榜单
     * @param paraMap sourceIds  多个rankIds 逗号分隔
     * @return
     */
    List<SkSearchSourceVO> getPopularRankList(Map<String, Object> paraMap);

    /**
     * 榜单topOne
     * @param paraMap
     * @return
     */
    SkSearchSourceVO getTopOneObjInfo(Map<String, Object> paraMap);
}
