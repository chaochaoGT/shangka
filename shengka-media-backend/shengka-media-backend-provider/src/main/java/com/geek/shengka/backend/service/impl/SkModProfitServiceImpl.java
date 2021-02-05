package com.geek.shengka.backend.service.impl;

import com.geek.shengka.backend.entity.SkModProfit;
import com.geek.shengka.backend.mapper.SkModProfitDAO;
import com.geek.shengka.backend.params.req.SkModProfitAddReqParam;
import com.geek.shengka.backend.params.req.SkModProfitListReqParam;
import com.geek.shengka.backend.params.req.SkModProfitUpdateReqParam;
import com.geek.shengka.backend.params.res.SkModProfitResParam;
import com.geek.shengka.backend.service.SkModProfitService;
import com.geek.shengka.backend.util.BeanMapperUtils;
import com.geek.shengka.backend.util.PageDataUtils;
import com.geek.shengka.backend.util.PageVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author qubianzhong
 * @date 2019/8/26 10:36
 */
@Service
public class SkModProfitServiceImpl implements SkModProfitService {

    @Autowired
    private SkModProfitDAO skModProfitDAO;

    /**
     * 分页查询
     *
     * @param param
     * @return com.geek.core.common.v2.PageBaseV2ApiResponse
     * @author qubianzhong
     * @Date 10:47 2019/8/26
     */
    @Override
    public PageVO<SkModProfitResParam> list(SkModProfitListReqParam param) {
        Page page = PageHelper.startPage(param.getPageNo(), param.getPageSize());
        List<SkModProfitResParam> list = skModProfitDAO.list(param);
        return PageDataUtils.pageData(page, list);
    }

    /**
     * 新增
     *
     * @param addDTO
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 10:56 2019/8/26
     */
    @Override
    public Boolean add(SkModProfitAddReqParam addDTO) {
        SkModProfit profit = BeanMapperUtils.map(addDTO, SkModProfit.class);
        return skModProfitDAO.insertSelective(profit) > 0;
    }

    /**
     * 更新
     *
     * @param updateDTO
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 11:00 2019/8/26
     */
    @Override
    public Boolean update(SkModProfitUpdateReqParam updateDTO) {
        SkModProfit profit = BeanMapperUtils.map(updateDTO, SkModProfit.class);
        return skModProfitDAO.updateByPrimaryKeySelective(profit) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 11:02 2019/8/26
     */
    @Override
    public Boolean delete(Long id) {
        return skModProfitDAO.deleteByPrimaryKey(id) > 0;
    }

    /**
     * 批量插入
     *
     * @param addReqParamList
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 11:54 2019/8/29
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean uploadData(List<SkModProfitAddReqParam> addReqParamList) {
        skModProfitDAO.truncate();
        List<SkModProfit> skModProfitList = BeanMapperUtils.mapList(addReqParamList, SkModProfit.class);
        return skModProfitDAO.insertBatch(skModProfitList) > 0;
    }
}
