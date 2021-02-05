package com.geek.shengka.backend.service.impl;

import com.geek.shengka.backend.entity.SkTreasureBoxConfig;
import com.geek.shengka.backend.mapper.SkTreasureBoxConfigDAO;
import com.geek.shengka.backend.params.req.SkTreasureBoxConfigAddReqParam;
import com.geek.shengka.backend.params.req.SkTreasureBoxConfigListReqParam;
import com.geek.shengka.backend.params.req.SkTreasureBoxConfigUpdateReqParam;
import com.geek.shengka.backend.params.res.SkTreasureBoxConfigInfoResParam;
import com.geek.shengka.backend.params.res.SkTreasureBoxConfigListResParam;
import com.geek.shengka.backend.service.SkTreasureBoxConfigService;
import com.geek.shengka.backend.util.BeanMapperUtils;
import com.geek.shengka.backend.util.PageDataUtils;
import com.geek.shengka.backend.util.PageVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author qubianzhong
 * @date 2019/8/2 17:20
 */
@Service
public class SkTreasureBoxConfigServiceImpl implements SkTreasureBoxConfigService {
    @Autowired
    private SkTreasureBoxConfigDAO skTreasureBoxConfigDAO;

    /**
     * 宝箱列表--分页
     *
     * @param param
     * @return com.geek.shengka.backend.util.PageVO<com.geek.shengka.backend.params.res.SkTreasureBoxConfigListResParam>
     * @author qubianzhong
     * @Date 18:04 2019/8/2
     */
    @Override
    public PageVO<SkTreasureBoxConfigListResParam> list(SkTreasureBoxConfigListReqParam param) {
        Page page = PageHelper.startPage(param.getPageNo(), param.getPageSize());
        List<SkTreasureBoxConfigListResParam> list = skTreasureBoxConfigDAO.list(param);
        return PageDataUtils.pageData(page, list);
    }

    /**
     * 新增
     *
     * @param add
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 18:04 2019/8/2
     */
    @Override
    public Boolean add(SkTreasureBoxConfigAddReqParam add) {
        SkTreasureBoxConfig treasureBoxConfig = BeanMapperUtils.map(add, SkTreasureBoxConfig.class);
        return skTreasureBoxConfigDAO.insertSelective(treasureBoxConfig) > 0;
    }

    /**
     * 更新
     *
     * @param update
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 18:04 2019/8/2
     */
    @Override
    public Boolean update(SkTreasureBoxConfigUpdateReqParam update) {
        SkTreasureBoxConfig treasureBoxConfig = BeanMapperUtils.map(update, SkTreasureBoxConfig.class);
        return skTreasureBoxConfigDAO.updateByPrimaryKeySelective(treasureBoxConfig) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 18:04 2019/8/2
     */
    @Override
    public Boolean delete(Long id) {
        return skTreasureBoxConfigDAO.deleteByPrimaryKey(id) > 0;
    }

    /**
     * 详情
     *
     * @param id
     * @return com.geek.shengka.backend.params.res.SkTreasureBoxConfigInfoResParam
     * @author qubianzhong
     * @Date 15:45 2019/8/5
     */
    @Override
    public SkTreasureBoxConfigInfoResParam info(Long id) {
        return skTreasureBoxConfigDAO.info(id);
    }

    /**
     * 获取有效的宝箱配置数量
     *
     * @param startTime
     * @param endTime
     * @param id
     */
    @Override
    public int countOfEffective(Date startTime, Date endTime, Long id) {
        if (startTime != null && endTime != null) {
            return skTreasureBoxConfigDAO.countOfEffective(startTime, endTime, id);
        }
        return 0;
    }
}
