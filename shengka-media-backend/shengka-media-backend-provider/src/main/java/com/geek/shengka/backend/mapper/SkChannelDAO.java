package com.geek.shengka.backend.mapper;

import com.geek.shengka.backend.entity.SkChannel;
import com.geek.shengka.backend.params.req.SkChannelListReqParam;
import com.geek.shengka.backend.params.res.SkChannelInfoResParam;
import com.geek.shengka.backend.params.res.SkChannelListNoPageResParam;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SkChannelDAO继承基类
 */
@Repository
public interface SkChannelDAO extends MyBatisBaseDao<SkChannel, Long> {
    /**
     * 列表分页
     *
     * @param param
     * @return java.util.List<com.geek.shengka.backend.entity.SkChannel>
     * @author qubianzhong
     * @Date 18:11 2019/7/31
     */
    List<SkChannelInfoResParam> list(SkChannelListReqParam param);

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
     * 查找总的数量，以便对排序进行初始化
     *
     * @param
     * @return int
     * @author qubianzhong
     * @Date 17:22 2019/8/12
     */
    int count();

    /**
     * 列表--不分页
     *
     * @param
     * @return java.util.List<com.geek.shengka.backend.params.res.SkChannelListNoPageResParam>
     * @author qubianzhong
     * @Date 10:41 2019/8/13
     */
    List<SkChannelListNoPageResParam> listNoPage();
}