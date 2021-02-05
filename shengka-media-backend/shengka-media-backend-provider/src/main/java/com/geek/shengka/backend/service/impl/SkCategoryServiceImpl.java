package com.geek.shengka.backend.service.impl;

import com.geek.shengka.backend.entity.SkCategory;
import com.geek.shengka.backend.exception.BaseException;
import com.geek.shengka.backend.mapper.SkCategoryDAO;
import com.geek.shengka.backend.mapper.SkCategoryMappingDAO;
import com.geek.shengka.backend.params.req.SkCategoryAddReqParam;
import com.geek.shengka.backend.params.req.SkCategoryListReqParam;
import com.geek.shengka.backend.params.req.SkCategoryMappingAddReqParam;
import com.geek.shengka.backend.params.req.SkCategoryUpdateReqParam;
import com.geek.shengka.backend.params.res.SkCategoryInfoResParam;
import com.geek.shengka.backend.params.res.SkContentCenterCategorysResParam;
import com.geek.shengka.backend.service.SkCategoryService;
import com.geek.shengka.backend.service.SkContentCenterService;
import com.geek.shengka.backend.util.BeanMapperUtils;
import com.geek.shengka.backend.util.PageDataUtils;
import com.geek.shengka.backend.util.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qubianzhong
 * @date 2019/7/31 17:20
 */
@Service
public class SkCategoryServiceImpl implements SkCategoryService {

    @Autowired
    private SkCategoryDAO skCategoryDAO;
    @Autowired
    private SkCategoryMappingDAO skCategoryMappingDAO;
    @Autowired
    private SkContentCenterService skContentCenterService;

    /**
     * info
     *
     * @param id
     * @return com.geek.shengka.backend.params.res.SkCategoryInfoResParam
     * @author qubianzhong
     * @Date 14:00 2019/8/5
     */
    @Override
    public SkCategoryInfoResParam info(Long id) {
        return skCategoryDAO.info(id);
    }

    /**
     * 列表--分页
     *
     * @param param
     * @return com.geek.shengka.backend.util.PageVO<com.geek.shengka.backend.entity.SkCategory>
     * @author qubianzhong
     * @Date 17:35 2019/7/31
     */
    @Override
    public PageVO<SkCategoryInfoResParam> list(SkCategoryListReqParam param) {
        int count = skCategoryDAO.listCount(param);
        List<SkCategoryInfoResParam> list = new ArrayList<>(param.getPageSize());
        if (count > 0 && count > param.getOffset()) {
            list = skCategoryDAO.list(param);
        }
        return PageDataUtils.normalData(count, param, list);
    }

    /**
     * 新增
     *
     * @param add
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 17:47 2019/7/31
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean add(SkCategoryAddReqParam add) {
        SkCategory category = BeanMapperUtils.map(add, SkCategory.class);
        Integer added = skCategoryDAO.insertSelective(category);
        if (added == 0) {
            return false;
        }
        addCategoryMappings(add.getCategoryMappings(), category.getId());
        return true;
    }

    private void addCategoryMappings(List<SkCategoryMappingAddReqParam> categoryMappings, Long categoryId) {
        if (!CollectionUtils.isEmpty(categoryMappings)) {
            categoryMappings.forEach(cm -> cm.setCategoryId(categoryId));
            skCategoryMappingDAO.insertSelectiveList(categoryMappings);
        }
    }

    /**
     * 更新
     *
     * @param update
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 17:47 2019/7/31
     */
    @Override
    public Boolean update(SkCategoryUpdateReqParam update) {
        SkCategory category = BeanMapperUtils.map(update, SkCategory.class);
        int updated = skCategoryDAO.updateByPrimaryKeySelective(category);
        if (updated == 0) {
            return false;
        }
        skCategoryMappingDAO.deleteByCategoryId(update.getId());
        addCategoryMappings(update.getCategoryMappings(), update.getId());
        return true;
    }

    /**
     * 删除
     *
     * @param id
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 17:49 2019/7/31
     */
    @Override
    public Boolean delete(Long id) {
        int deleted = skCategoryDAO.deleteByPrimaryKey(id);
        if (deleted == 0) {
            return false;
        }
        skCategoryMappingDAO.deleteByCategoryId(id);
        return true;
    }

    /**
     * 内容中心视频分类列表
     *
     * @return java.util.List<com.geek.shengka.backend.params.res.SkContentCenterCategorysResParam>
     * @author qubianzhong
     * @Date 16:55 2019/8/8
     */
    @Override
    public List<SkContentCenterCategorysResParam> categories() throws BaseException {
        return skContentCenterService.categories();
    }
}
