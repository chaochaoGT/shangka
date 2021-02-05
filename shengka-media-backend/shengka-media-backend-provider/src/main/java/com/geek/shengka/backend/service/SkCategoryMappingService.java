package com.geek.shengka.backend.service;

import com.geek.shengka.backend.entity.SkCategoryMapping;

import java.util.List;

/**
 * @author qubianzhong
 * @date 2019/7/31 18:34
 */
public interface SkCategoryMappingService {

    /**
     * 根据频道ID查询列表
     *
     * @param categoryId
     * @return java.util.List<com.geek.shengka.backend.entity.SkCategoryMapping>
     * @author qubianzhong
     * @Date 18:37 2019/7/31
     */
    List<SkCategoryMapping> listByCategoryId(Long categoryId);

    /**
     * 新增
     *
     * @param add
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 18:37 2019/7/31
     */
    Boolean add(SkCategoryMapping add);

    /**
     * 根据频道ID删除
     *
     * @param categoryId
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 18:37 2019/7/31
     */
    Boolean deleteByCategoryId(Long categoryId);
}
