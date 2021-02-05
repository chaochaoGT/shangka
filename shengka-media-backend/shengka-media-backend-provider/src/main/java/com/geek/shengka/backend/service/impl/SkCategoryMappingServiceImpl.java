package com.geek.shengka.backend.service.impl;

import com.geek.shengka.backend.entity.SkCategoryMapping;
import com.geek.shengka.backend.mapper.SkCategoryMappingDAO;
import com.geek.shengka.backend.service.SkCategoryMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qubianzhong
 * @date 2019/7/31 18:34
 */
@Service
public class SkCategoryMappingServiceImpl implements SkCategoryMappingService {
    @Autowired
    private SkCategoryMappingDAO skCategoryMappingDAO;

    /**
     * 根据频道ID查询列表
     *
     * @param categoryId
     * @return java.util.List<com.geek.shengka.backend.entity.SkCategoryMapping>
     * @author qubianzhong
     * @Date 18:37 2019/7/31
     */
    @Override
    public List<SkCategoryMapping> listByCategoryId(Long categoryId) {
        return skCategoryMappingDAO.listByCategoryId(categoryId);
    }

    /**
     * 新增
     *
     * @param add
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 18:37 2019/7/31
     */
    @Override
    public Boolean add(SkCategoryMapping add) {
        return skCategoryMappingDAO.insertSelective(add) > 0;
    }

    /**
     * 根据频道ID删除
     *
     * @param categoryId
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 18:37 2019/7/31
     */
    @Override
    public Boolean deleteByCategoryId(Long categoryId) {
        return skCategoryMappingDAO.deleteByCategoryId(categoryId);
    }
}
