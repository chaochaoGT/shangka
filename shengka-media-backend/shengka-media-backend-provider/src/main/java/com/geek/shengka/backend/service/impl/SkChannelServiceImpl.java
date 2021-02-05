package com.geek.shengka.backend.service.impl;

import com.geek.shengka.backend.entity.SkChannel;
import com.geek.shengka.backend.mapper.SkChannelDAO;
import com.geek.shengka.backend.params.req.SkChannelAddReqParam;
import com.geek.shengka.backend.params.req.SkChannelListReqParam;
import com.geek.shengka.backend.params.req.SkChannelUpdateReqParam;
import com.geek.shengka.backend.params.res.SkChannelInfoResParam;
import com.geek.shengka.backend.params.res.SkChannelListNoPageResParam;
import com.geek.shengka.backend.service.SkChannelService;
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
 * @date 2019/7/31 17:21
 */
@Service
public class SkChannelServiceImpl implements SkChannelService {
    @Autowired
    private SkChannelDAO skChannelDAO;

    /**
     * 删除
     *
     * @param id
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 18:07 2019/7/31
     */
    @Override
    public Boolean delete(Long id) {
        return skChannelDAO.deleteByPrimaryKey(id) > 0;
    }

    /**
     * 列表分页
     *
     * @param param
     * @return com.geek.shengka.backend.util.PageVO<com.geek.shengka.backend.entity.SkChannel>
     * @author qubianzhong
     * @Date 18:08 2019/7/31
     */
    @Override
    public PageVO<SkChannelInfoResParam> list(SkChannelListReqParam param) {
        Page page = PageHelper.startPage(param.getPageNo(), param.getPageSize());
        List<SkChannelInfoResParam> list = skChannelDAO.list(param);
        return PageDataUtils.pageData(page, list);
    }

    /**
     * 新增
     *
     * @param add
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 18:08 2019/7/31
     */
    @Override
    public Boolean add(SkChannelAddReqParam add) {
        SkChannel channel = BeanMapperUtils.map(add, SkChannel.class);
        int seqCount = skChannelDAO.count();
        channel.setSeq(seqCount++);
        return skChannelDAO.insertSelective(channel) > 0;
    }

    /**
     * 更新
     *
     * @param update
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 18:08 2019/7/31
     */
    @Override
    public Boolean update(SkChannelUpdateReqParam update) {
        SkChannel channel = BeanMapperUtils.map(update, SkChannel.class);
        return skChannelDAO.updateByPrimaryKeySelective(channel) > 0;
    }

    /**
     * 详情
     *
     * @param id
     * @return com.geek.shengka.backend.params.res.SkChannelInfoResParam
     * @author qubianzhong
     * @Date 14:09 2019/8/5
     */
    @Override
    public SkChannelInfoResParam info(Long id) {
        return skChannelDAO.info(id);
    }

    /**
     * 获取所有的渠道列表--不分页
     *
     * @return java.util.List<com.geek.shengka.backend.params.res.SkChannelListNoPageResParam>
     * @author qubianzhong
     * @Date 10:41 2019/8/13
     */
    @Override
    public List<SkChannelListNoPageResParam> listNoPage() {
        return skChannelDAO.listNoPage();
    }
}
