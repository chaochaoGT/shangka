package com.geek.shengka.backend.service.impl;

import com.geek.shengka.backend.entity.SkRankList;
import com.geek.shengka.backend.mapper.SkRankListDAO;
import com.geek.shengka.backend.mapper.SkRankMappingDAO;
import com.geek.shengka.backend.params.req.*;
import com.geek.shengka.backend.params.res.SkRankInfoResParam;
import com.geek.shengka.backend.params.res.SkRankListResParam;
import com.geek.shengka.backend.service.SkRankListService;
import com.geek.shengka.backend.util.BeanMapperUtils;
import com.geek.shengka.backend.util.PageDataUtils;
import com.geek.shengka.backend.util.PageVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author qubianzhong
 * @date 2019/8/2 10:54
 */
@Service
public class SkRankListServiceImpl implements SkRankListService {

    @Autowired
    private SkRankListDAO skRankListDAO;
    @Autowired
    private SkRankMappingDAO skRankMappingDAO;

    /**
     * 列表--分页
     *
     * @param param
     * @return com.geek.shengka.backend.util.PageVO<com.geek.shengka.backend.params.res.SkRankListResParam>
     * @author qubianzhong
     * @Date 11:14 2019/8/2
     */
    @Override
    public PageVO<SkRankListResParam> list(SkRankListReqParam param) {
        Page page = PageHelper.startPage(param.getPageNo(), param.getPageSize());
        List<SkRankListResParam> list = skRankListDAO.list(param);
        return PageDataUtils.pageData(page, list);
    }

    /**
     * 新增
     *
     * @param add
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 11:28 2019/8/2
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean add(SkRankAddReqParam add) {
        SkRankList rankList = BeanMapperUtils.map(add, SkRankList.class);
        boolean added = skRankListDAO.insertSelective(rankList) > 0;
        if (!added) {
            return false;
        }
        addRankMappings(add.getRankMappings(), rankList.getId(), add.getCreateBy());
        return true;
    }

    private void addRankMappings(List<SkRankMappingAddReqParam> rankMappings, Long rankListId, String userId) {
        if (!CollectionUtils.isEmpty(rankMappings)) {
            rankMappings.forEach(rm -> {
                rm.setRankId(rankListId);
                rm.setCreateBy(userId);
            });
            skRankMappingDAO.insertSelectiveList(rankMappings);
        }
    }

    /**
     * 更新
     *
     * @param update
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 11:28 2019/8/2
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean update(SkRankUpdateReqParam update) {
        SkRankList rankList = BeanMapperUtils.map(update, SkRankList.class);
        boolean updated = skRankListDAO.updateByPrimaryKeySelective(rankList) > 0;
        if (!updated) {
            return false;
        }
        skRankMappingDAO.deleteByRankId(rankList.getId());
        addRankMappings(update.getRankMappings(), rankList.getId(), update.getUpdateBy());
        return true;
    }

    /**
     * 删除
     *
     * @param id
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 11:28 2019/8/2
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(Long id) {
        boolean deleted = skRankListDAO.deleteByPrimaryKey(id) > 0;
        if (!deleted) {
            return false;
        }
        skRankMappingDAO.deleteByRankId(id);
        return true;
    }

    /**
     * 详情
     *
     * @param id
     * @return com.geek.shengka.backend.params.res.SkRankInfoResParam
     * @author qubianzhong
     * @Date 14:46 2019/8/5
     */
    @Override
    public SkRankInfoResParam info(Long id) {
        return skRankListDAO.info(id);
    }

    /**
     * 开关
     *
     * @param enable
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 18:27 2019/8/6
     */
    @Override
    public Boolean enable(SkRankEnableReqParam enable) {
        SkRankList rankList = BeanMapperUtils.map(enable, SkRankList.class);
        return skRankListDAO.updateByPrimaryKeySelective(rankList) > 0;
    }
}
