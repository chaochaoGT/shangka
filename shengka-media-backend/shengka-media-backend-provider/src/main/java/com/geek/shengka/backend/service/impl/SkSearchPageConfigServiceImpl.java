package com.geek.shengka.backend.service.impl;

import com.geek.shengka.backend.entity.SkSearchModuleMapping;
import com.geek.shengka.backend.entity.SkSearchPageConfig;
import com.geek.shengka.backend.mapper.SkSearchModuleMappingDAO;
import com.geek.shengka.backend.mapper.SkSearchPageConfigDAO;
import com.geek.shengka.backend.params.req.SkSearchPageConfigAddReqParam;
import com.geek.shengka.backend.params.req.SkSearchPageConfigUpdateReqParam;
import com.geek.shengka.backend.params.res.SkSearchPageConfigInfoResParam;
import com.geek.shengka.backend.params.res.SkSearchPageConfigListResParam;
import com.geek.shengka.backend.service.SkSearchPageConfigService;
import com.geek.shengka.backend.util.BeanMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author qubianzhong
 * @date 2019/8/5 9:48
 */
@Service
public class SkSearchPageConfigServiceImpl implements SkSearchPageConfigService {
    @Autowired
    private SkSearchPageConfigDAO skSearchPageConfigDAO;
    @Autowired
    private SkSearchModuleMappingDAO skSearchModuleMappingDAO;

    /**
     * 搜索页配置列表--不分页
     *
     * @return java.util.List<com.geek.shengka.backend.params.res.SkSearchPageConfigListResParam>
     * @author qubianzhong
     * @Date 10:48 2019/8/5
     */
    @Override
    public List<SkSearchPageConfigListResParam> list() {
        return skSearchPageConfigDAO.list();
    }

    /**
     * 新增
     *
     * @param add
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 10:49 2019/8/5
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean add(SkSearchPageConfigAddReqParam add) {
        SkSearchPageConfig searchPageConfig = BeanMapperUtils.map(add, SkSearchPageConfig.class);
        boolean added = skSearchPageConfigDAO.insertSelective(searchPageConfig) > 0;
        if (!added) {
            return false;
        }
        add.getModuleMappings().forEach(mm -> {
            mm.setCreateBy(searchPageConfig.getCreateBy());
            mm.setConfigId(searchPageConfig.getId());
            mm.setModuleType(searchPageConfig.getModuleType());
        });
        skSearchModuleMappingDAO.insertSelectiveList(BeanMapperUtils.mapList(add.getModuleMappings(), SkSearchModuleMapping.class));
        return true;
    }

    /**
     * 更新
     *
     * @param update
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 10:49 2019/8/5
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean update(SkSearchPageConfigUpdateReqParam update) {
        SkSearchPageConfig searchPageConfig = BeanMapperUtils.map(update, SkSearchPageConfig.class);
        boolean updated = skSearchPageConfigDAO.updateByPrimaryKeySelective(searchPageConfig) > 0;
        if (!updated) {
            return false;
        }
        update.getModuleMappings().forEach(mm -> {
            mm.setCreateBy(searchPageConfig.getUpdateBy());
            mm.setConfigId(searchPageConfig.getId());
            mm.setModuleType(searchPageConfig.getModuleType());
        });
        skSearchModuleMappingDAO.deleteByConfigId(searchPageConfig.getId());
        skSearchModuleMappingDAO.insertSelectiveList(BeanMapperUtils.mapList(update.getModuleMappings(), SkSearchModuleMapping.class));
        return true;
    }

    /**
     * 删除
     *
     * @param id
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 10:49 2019/8/5
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(Long id) {
        boolean deleted = skSearchPageConfigDAO.deleteByPrimaryKey(id) > 0;
        if (!deleted) {
            return false;
        }
        skSearchModuleMappingDAO.deleteByConfigId(id);
        return true;
    }

    /**
     * 详情
     *
     * @param id
     * @return com.geek.shengka.backend.params.res.SkSearchPageConfigInfoResParam
     * @author qubianzhong
     * @Date 11:20 2019/8/5
     */
    @Override
    public SkSearchPageConfigInfoResParam info(Long id) {
        return skSearchPageConfigDAO.info(id);
    }
}
