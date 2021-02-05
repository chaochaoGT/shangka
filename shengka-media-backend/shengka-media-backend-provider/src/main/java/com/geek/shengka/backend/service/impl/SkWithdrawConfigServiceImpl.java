package com.geek.shengka.backend.service.impl;

import com.geek.shengka.backend.entity.SkWithdrawConfig;
import com.geek.shengka.backend.mapper.SkWithdrawConfigDAO;
import com.geek.shengka.backend.params.req.SkWithdrawConfigAddReqParam;
import com.geek.shengka.backend.params.req.SkWithdrawConfigListReqParam;
import com.geek.shengka.backend.params.req.SkWithdrawConfigUpdateReqParam;
import com.geek.shengka.backend.params.res.SkWithdrawConfigInfoResParam;
import com.geek.shengka.backend.params.res.SkWithdrawConfigListResParam;
import com.geek.shengka.backend.service.SkWithdrawConfigService;
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
 * @date 2019/8/2 17:20
 */
@Service
public class SkWithdrawConfigServiceImpl implements SkWithdrawConfigService {
    @Autowired
    private SkWithdrawConfigDAO skWithdrawConfigDAO;

    /**
     * 列表
     *
     * @param param
     * @return com.geek.shengka.backend.util.PageVO<com.geek.shengka.backend.params.res.SkWithdrawConfigListResParam>
     * @author qubianzhong
     * @Date 18:17 2019/8/2
     */
    @Override
    public PageVO<SkWithdrawConfigListResParam> list(SkWithdrawConfigListReqParam param) {
        Page page = PageHelper.startPage(param.getPageNo(), param.getPageSize());
        List<SkWithdrawConfigListResParam> list = skWithdrawConfigDAO.list(param);
        return PageDataUtils.pageData(page, list);
    }

    /**
     * 新增
     *
     * @param add
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 18:17 2019/8/2
     */
    @Override
    public Boolean add(SkWithdrawConfigAddReqParam add) {
        SkWithdrawConfig withdrawConfig = BeanMapperUtils.map(add, SkWithdrawConfig.class);
        return skWithdrawConfigDAO.insertSelective(withdrawConfig) > 0;
    }

    /**
     * 更新
     *
     * @param update
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 18:17 2019/8/2
     */
    @Override
    public Boolean update(SkWithdrawConfigUpdateReqParam update) {
        SkWithdrawConfig withdrawConfig = BeanMapperUtils.map(update, SkWithdrawConfig.class);
        return skWithdrawConfigDAO.updateByPrimaryKeySelective(withdrawConfig) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 18:18 2019/8/2
     */
    @Override
    public Boolean delete(Long id) {
        return skWithdrawConfigDAO.deleteByPrimaryKey(id) > 0;
    }

    /**
     * 详情
     *
     * @param id
     * @return com.geek.shengka.backend.params.res.SkWithdrawConfigListResParam
     * @author qubianzhong
     * @Date 15:54 2019/8/5
     */
    @Override
    public SkWithdrawConfigInfoResParam info(Long id) {
        return skWithdrawConfigDAO.info(id);
    }
}
