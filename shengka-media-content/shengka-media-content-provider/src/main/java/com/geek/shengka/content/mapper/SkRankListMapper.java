package com.geek.shengka.content.mapper;

import com.geek.shengka.content.entity.SkRankList;
import com.geek.shengka.content.entity.vo.SkRankListVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SkRankListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SkRankList record);

    int insertSelective(SkRankList record);

    SkRankList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SkRankList record);

    int updateByPrimaryKey(SkRankList record);

    /**
     * 统计总笔数
     *
     * @param paraMap:暂无参数
     * @return
     */
    Long selectSkRankListCount(Map<String, Object> paraMap);

    /**
     * 取分页数据
     *
     * @param paraMap: startRecordNumb:开始记录号,pageSize:每页显示的笔数
     * @return
     */
    List<SkRankList> selectSkRankListList(Map<String, Object> paraMap);

    /**
     * 榜单列表数据  不分页
     *
     * @param
     * @return java.util.List<com.geek.shengka.content.entity.SkRankList>
     * @author qubianzhong
     * @Date 14:14 2019/8/17
     */
    List<SkRankListVO> list();
}
