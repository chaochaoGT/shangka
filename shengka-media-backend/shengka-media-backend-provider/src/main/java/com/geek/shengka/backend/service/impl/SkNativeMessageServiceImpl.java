package com.geek.shengka.backend.service.impl;

import com.geek.shengka.backend.entity.SkNativeMessage;
import com.geek.shengka.backend.mapper.SkNativeMessageDAO;
import com.geek.shengka.backend.params.req.SkNativeMessageAddReqParam;
import com.geek.shengka.backend.params.req.SkNativeMessageListReqParam;
import com.geek.shengka.backend.params.req.SkNativeMessageUpdateReqParam;
import com.geek.shengka.backend.service.SkNativeMessageService;
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
 * @date 2019/8/5 10:26
 */
@Service
public class SkNativeMessageServiceImpl implements SkNativeMessageService {
    @Autowired
    private SkNativeMessageDAO skNativeMessageDAO;

    /**
     * 详情
     *
     * @param id
     * @return com.geek.shengka.backend.entity.SkNativeMessage
     * @author qubianzhong
     * @Date 16:14 2019/8/5
     */
    @Override
    public SkNativeMessage info(Long id) {
        return skNativeMessageDAO.selectByPrimaryKey(id);
    }

    /**
     * 列表--分页
     *
     * @param param
     * @return com.geek.shengka.backend.util.PageVO<com.geek.shengka.backend.entity.SkNativeMessage>
     * @author qubianzhong
     * @Date 16:14 2019/8/5
     */
    @Override
    public PageVO<SkNativeMessage> list(SkNativeMessageListReqParam param) {
        Page page = PageHelper.startPage(param.getPageNo(), param.getPageSize());
        List<SkNativeMessage> list = skNativeMessageDAO.list(param);
        return PageDataUtils.pageData(page, list);
    }

    /**
     * 新增
     *
     * @param add
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 16:15 2019/8/5
     */
    @Override
    public Boolean add(SkNativeMessageAddReqParam add) {
        SkNativeMessage nativeMessage = BeanMapperUtils.map(add, SkNativeMessage.class);
        return skNativeMessageDAO.insertSelective(nativeMessage) > 0;
    }

    /**
     * 更新
     *
     * @param update
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 16:15 2019/8/5
     */
    @Override
    public Boolean update(SkNativeMessageUpdateReqParam update) {
        SkNativeMessage nativeMessage = BeanMapperUtils.map(update, SkNativeMessage.class);
        return skNativeMessageDAO.updateByPrimaryKeySelective(nativeMessage) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 16:15 2019/8/5
     */
    @Override
    public Boolean delete(Long id) {
        return skNativeMessageDAO.deleteByPrimaryKey(id) > 0;
    }
}
