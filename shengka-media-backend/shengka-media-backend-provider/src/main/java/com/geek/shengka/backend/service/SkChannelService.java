package com.geek.shengka.backend.service;

import com.geek.shengka.backend.params.req.SkChannelAddReqParam;
import com.geek.shengka.backend.params.req.SkChannelListReqParam;
import com.geek.shengka.backend.params.req.SkChannelUpdateReqParam;
import com.geek.shengka.backend.params.res.SkChannelInfoResParam;
import com.geek.shengka.backend.params.res.SkChannelListNoPageResParam;
import com.geek.shengka.backend.util.PageVO;

import java.util.List;

/**
 * @author qubianzhong
 * @date 2019/7/31 17:21
 */
public interface SkChannelService {
    /**
     * 删除
     *
     * @param id
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 18:07 2019/7/31
     */
    Boolean delete(Long id);

    /**
     * 列表分页
     *
     * @param param
     * @return com.geek.shengka.backend.util.PageVO<com.geek.shengka.backend.entity.SkChannel>
     * @author qubianzhong
     * @Date 18:08 2019/7/31
     */
    PageVO<SkChannelInfoResParam> list(SkChannelListReqParam param);

    /**
     * 新增
     *
     * @param add
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 18:08 2019/7/31
     */
    Boolean add(SkChannelAddReqParam add);

    /**
     * 更新
     *
     * @param update
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 18:08 2019/7/31
     */
    Boolean update(SkChannelUpdateReqParam update);

    /**
     * 详情
     *
     * @param id
     * @return com.geek.shengka.backend.params.res.SkChannelInfoResParam
     * @author qubianzhong
     * @Date 14:09 2019/8/5
     */
    SkChannelInfoResParam info(Long id);

    /**
     * 获取所有的渠道列表--不分页
     *
     * @param
     * @return java.util.List<com.geek.shengka.backend.params.res.SkChannelListNoPageResParam>
     * @author qubianzhong
     * @Date 10:41 2019/8/13
     */
    List<SkChannelListNoPageResParam> listNoPage();
}
