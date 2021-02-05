package com.geek.shengka.backend.service.impl;

import com.geek.shengka.backend.entity.SkRecommendUserConfig;
import com.geek.shengka.backend.mapper.SkRecommendUserConfigDAO;
import com.geek.shengka.backend.params.req.SkRecommendUserConfigAddReqParam;
import com.geek.shengka.backend.params.req.SkRecommendUserConfigListReqParam;
import com.geek.shengka.backend.params.res.SkRecommendUserConfigListResParam;
import com.geek.shengka.backend.service.SkRecommendUserConfigService;
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
 * @date 2019/8/22 13:44
 */
@Service
public class SkRecommendUserConfigServiceImpl implements SkRecommendUserConfigService {
    @Autowired
    private SkRecommendUserConfigDAO skRecommendUserConfigDAO;

    /**
     * 列表
     *
     * @param param
     * @return com.geek.shengka.backend.util.PageVO<com.geek.shengka.backend.params.res.SkRecommendUserConfigListResParam>
     * @author qubianzhong
     * @Date 15:06 2019/8/22
     */
    @Override
    public PageVO<SkRecommendUserConfigListResParam> list(SkRecommendUserConfigListReqParam param) {
        Page page = PageHelper.startPage(param.getPageNo(), param.getPageSize());
        List<SkRecommendUserConfigListResParam> list = skRecommendUserConfigDAO.list(param);
        return PageDataUtils.pageData(page, list);
    }

    /**
     * 新增
     *
     * @param addReqParam
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 15:06 2019/8/22
     */
    @Override
    public Boolean add(SkRecommendUserConfigAddReqParam addReqParam) {
        SkRecommendUserConfig config = BeanMapperUtils.map(addReqParam, SkRecommendUserConfig.class);
        return skRecommendUserConfigDAO.insertSelective(config) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 15:06 2019/8/22
     */
    @Override
    public Boolean delete(Long id) {
        return skRecommendUserConfigDAO.deleteByPrimaryKey(id) > 0;
    }

    /**
     * 通过用户ID查询数量
     *
     * @param userId
     * @return int
     * @author qubianzhong
     * @Date 18:19 2019/8/22
     */
    @Override
    public int count(Long userId) {
        return skRecommendUserConfigDAO.count(userId);
    }
}
