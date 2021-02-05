package com.geek.shengka.backend.service.impl;

import com.geek.shengka.backend.mapper.SkUserBaseInfoDAO;
import com.geek.shengka.backend.params.req.SkUserBaseInfoListReqParam;
import com.geek.shengka.backend.params.res.SkUserBaseInfoListResParam;
import com.geek.shengka.backend.service.SkUserBaseInfoService;
import com.geek.shengka.backend.util.PageDataUtils;
import com.geek.shengka.backend.util.PageVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qubianzhong
 * @date 2019/8/20 16:26
 */
@Service
public class SkUserBaseInfoServiceImpl implements SkUserBaseInfoService {

    @Autowired
    private SkUserBaseInfoDAO skUserBaseInfoDAO;

    /**
     * 根据用户昵称分页查询用户列表
     *
     * @param param
     * @return com.geek.shengka.backend.util.PageVO<com.geek.shengka.backend.params.res.SkUserBaseInfoListResParam>
     * @author qubianzhong
     * @Date 16:33 2019/8/20
     */
    @Override
    public PageVO<SkUserBaseInfoListResParam> list(SkUserBaseInfoListReqParam param) {
        Page page = PageHelper.startPage(param.getPageNo(), param.getPageSize());
        List<SkUserBaseInfoListResParam> list = skUserBaseInfoDAO.list(param);
        return PageDataUtils.pageData(page, list);
    }
}
