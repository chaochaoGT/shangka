package com.geek.shengka.backend.service.impl;

import com.geek.shengka.backend.entity.SkTaskConfig;
import com.geek.shengka.backend.mapper.SkTaskConfigDAO;
import com.geek.shengka.backend.params.req.SkTaskConfigAddReqParam;
import com.geek.shengka.backend.params.req.SkTaskConfigListReqParam;
import com.geek.shengka.backend.params.req.SkTaskConfigUpdateReqParam;
import com.geek.shengka.backend.params.res.SkTaskConfigInfoResParam;
import com.geek.shengka.backend.service.SkTaskConfigService;
import com.geek.shengka.backend.util.BeanMapperUtils;
import com.geek.shengka.backend.util.PageDataUtils;
import com.geek.shengka.backend.util.PageVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qubianzhong
 * @date 2019/8/2 17:19
 */
@Service
public class SkTaskConfigServiceImpl implements SkTaskConfigService {

    @Autowired
    private SkTaskConfigDAO skTaskConfigDAO;

    /**
     * 列表--分页
     *
     * @param param
     * @return com.geek.shengka.backend.util.PageVO<com.geek.shengka.backend.params.res.SkTaskConfigInfoResParam>
     * @author qubianzhong
     * @Date 17:43 2019/8/2
     */
    @Override
    public PageVO<SkTaskConfigInfoResParam> list(SkTaskConfigListReqParam param) {
        Page page = PageHelper.startPage(param.getPageNo(), param.getPageSize());
        List<SkTaskConfigInfoResParam> list = skTaskConfigDAO.list(param);
        return PageDataUtils.pageData(page, list);
    }

    /**
     * 新增
     *
     * @param add
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 17:43 2019/8/2
     */
    @Override
    public Boolean add(SkTaskConfigAddReqParam add) {
        SkTaskConfig taskConfig = BeanMapperUtils.map(add, SkTaskConfig.class);
        return skTaskConfigDAO.insertSelective(taskConfig) > 0;
    }

    /**
     * 更新
     *
     * @param update
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 17:44 2019/8/2
     */
    @Override
    public Boolean update(SkTaskConfigUpdateReqParam update) {
        SkTaskConfig taskConfig = BeanMapperUtils.map(update, SkTaskConfig.class);
        return skTaskConfigDAO.updateByPrimaryKeySelective(taskConfig) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 17:44 2019/8/2
     */
    @Override
    public Boolean delete(Long id) {
        return skTaskConfigDAO.deleteByPrimaryKey(id) > 0;
    }

    /**
     * 详情
     *
     * @param id
     * @return com.geek.shengka.backend.params.res.SkTaskConfigInfoResParam
     * @author qubianzhong
     * @Date 15:04 2019/8/5
     */
    @Override
    public SkTaskConfigInfoResParam info(Long id) {
        return skTaskConfigDAO.info(id);
    }
}
