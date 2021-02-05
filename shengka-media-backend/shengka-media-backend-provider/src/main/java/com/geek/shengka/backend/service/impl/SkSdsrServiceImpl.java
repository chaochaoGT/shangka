package com.geek.shengka.backend.service.impl;

import com.geek.shengka.backend.entity.SklSdsr;
import com.geek.shengka.backend.mapper.SkSdsrMapper;
import com.geek.shengka.backend.params.req.BasePageReqParam;
import com.geek.shengka.backend.params.req.SklSdsrAddReqParam;
import com.geek.shengka.backend.params.res.SkSdsrListReqParam;
import com.geek.shengka.backend.service.SkSdsrService;
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
 * @author heguoya
 * @version 1.0
 * @className SkSdsrServiceImpl
 * @description
 * @date 2019/8/13 14:06
 */
@Service
public class SkSdsrServiceImpl implements SkSdsrService {
    @Autowired
    private SkSdsrMapper skSdsrMapper;

    /**
     * 获取收支列表
     *
     * @param req
     * @return
     */
    @Override
    public PageVO<SkSdsrListReqParam> getSdsrList(BasePageReqParam req) {
        Page<SkSdsrListReqParam> page = PageHelper.startPage(req.getPageNo(), req.getPageSize(),
                req.getOrderBy());
        List<SkSdsrListReqParam> list = skSdsrMapper.selectAll();
        return PageDataUtils.pageData(page, list);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean uploadData(List<SklSdsrAddReqParam> addReqParams) {
        skSdsrMapper.truncate();
        List<SklSdsr> list = BeanMapperUtils.mapList(addReqParams, SklSdsr.class);
        return skSdsrMapper.insertBatch(list) > 0;
    }
}
