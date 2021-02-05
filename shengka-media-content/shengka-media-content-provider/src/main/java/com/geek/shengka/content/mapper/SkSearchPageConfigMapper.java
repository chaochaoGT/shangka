package com.geek.shengka.content.mapper;

import com.geek.shengka.content.entity.SkSearchPageConfig;
import com.geek.shengka.content.entity.vo.SkSearchModuleVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface SkSearchPageConfigMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SkSearchPageConfig record);

    int insertSelective(SkSearchPageConfig record);

    SkSearchPageConfig selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SkSearchPageConfig record);

    int updateByPrimaryKey(SkSearchPageConfig record);

	/**
	 * 统计总笔数
	 * @param paraMap:暂无参数
	 * @return
	 */
	Long selectSkSearchPageConfigCount(Map<String, Object> paraMap);

	/**
	 * 取分页数据
	 * @param paraMap: startRecordNumb:开始记录号,pageSize:每页显示的笔数
	 * @return
	 */
	List<SkSearchModuleVO> selectSkSearchPageConfigList(Map<String, Object> paraMap);

}
