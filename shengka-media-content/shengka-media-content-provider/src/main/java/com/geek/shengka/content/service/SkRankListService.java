package com.geek.shengka.content.service;

import com.geek.shengka.content.entity.SkRankList;
import com.geek.shengka.content.entity.vo.SkRankListVO;

import java.util.List;

/**
 * @author qubianzhong
 * @date 2019/8/17 14:01
 */
public interface SkRankListService {
    /**
     * 榜单列表数据
     *
     * @param
     * @return java.util.List<com.geek.shengka.content.entity.vo.SkRankList>
     * @author qubianzhong
     * @Date 14:09 2019/8/17
     */
    List<SkRankListVO> list();
}
