package com.geek.shengka.backend.service.impl;

import com.geek.shengka.backend.entity.SkVersion;
import com.geek.shengka.backend.enums.SkVersionStateEnum;
import com.geek.shengka.backend.mapper.SkVersionDAO;
import com.geek.shengka.backend.params.req.SkVersionAddReqParam;
import com.geek.shengka.backend.params.req.SkVersionListReqParam;
import com.geek.shengka.backend.params.req.SkVersionUpdateReqParam;
import com.geek.shengka.backend.params.res.SkVersionInfoResParam;
import com.geek.shengka.backend.params.res.SkVersionListResParam;
import com.geek.shengka.backend.service.SkVersionService;
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
 * @date 2019/8/1 13:44
 */
@Service
public class SkVersionServiceImpl implements SkVersionService {

    @Autowired
    private SkVersionDAO skVersionDAO;

    /**
     * 列表--分页
     *
     * @param param
     * @return com.geek.shengka.backend.util.PageVO<com.geek.shengka.backend.params.res.SkVersionListResParam>
     * @author qubianzhong
     * @Date 14:03 2019/8/1
     */
    @Override
    public PageVO<SkVersionListResParam> list(SkVersionListReqParam param) {
        Page page = PageHelper.startPage(param.getPageNo(), param.getPageSize());
        List<SkVersionListResParam> list = skVersionDAO.list(param);
        return PageDataUtils.pageData(page, list);
    }

    /**
     * 新增
     *
     * @param add
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 14:03 2019/8/1
     */
    @Override
    public Boolean add(SkVersionAddReqParam add) {
        SkVersion version = BeanMapperUtils.map(add, SkVersion.class);
        return skVersionDAO.insertSelective(version) > 0;
    }

    /**
     * 更新
     *
     * @param update
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 14:03 2019/8/1
     */
    @Override
    public Boolean update(SkVersionUpdateReqParam update) {
        SkVersion version = BeanMapperUtils.map(update, SkVersion.class);
        return skVersionDAO.updateByPrimaryKeySelective(version) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 14:03 2019/8/1
     */
    @Override
    public Boolean delete(Long id) {
        SkVersion version = new SkVersion();
        version.setId(id);
        version.setState(SkVersionStateEnum.DELETED.getValue());
        return skVersionDAO.updateByPrimaryKeySelective(version) > 0;
    }

    /**
     * 详情
     *
     * @param id
     * @return com.geek.shengka.backend.params.res.SkVersionInfoResParam
     * @author qubianzhong
     * @Date 15:49 2019/8/5
     */
    @Override
    public SkVersionInfoResParam info(Long id) {
        return skVersionDAO.info(id);
    }
}
