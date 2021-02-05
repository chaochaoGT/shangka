package com.geek.shengka.backend.service;

import com.geek.shengka.backend.params.req.SkTreasureBoxConfigAddReqParam;
import com.geek.shengka.backend.params.req.SkTreasureBoxConfigListReqParam;
import com.geek.shengka.backend.params.req.SkTreasureBoxConfigUpdateReqParam;
import com.geek.shengka.backend.params.res.SkTreasureBoxConfigInfoResParam;
import com.geek.shengka.backend.params.res.SkTreasureBoxConfigListResParam;
import com.geek.shengka.backend.util.PageVO;

import java.util.Date;

/**
 * @author qubianzhong
 * @date 2019/8/2 17:20
 */
public interface SkTreasureBoxConfigService {
    /**
     * 宝箱列表--分页
     *
     * @param param
     * @return com.geek.shengka.backend.util.PageVO<com.geek.shengka.backend.params.res.SkTreasureBoxConfigListResParam>
     * @author qubianzhong
     * @Date 18:04 2019/8/2
     */
    PageVO<SkTreasureBoxConfigListResParam> list(SkTreasureBoxConfigListReqParam param);

    /**
     * 新增
     *
     * @param add
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 18:04 2019/8/2
     */
    Boolean add(SkTreasureBoxConfigAddReqParam add);

    /**
     * 更新
     *
     * @param update
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 18:04 2019/8/2
     */
    Boolean update(SkTreasureBoxConfigUpdateReqParam update);

    /**
     * 删除
     *
     * @param id
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 18:04 2019/8/2
     */
    Boolean delete(Long id);

    /**
     * 详情
     *
     * @param id
     * @return com.geek.shengka.backend.params.res.SkTreasureBoxConfigInfoResParam
     * @author qubianzhong
     * @Date 15:45 2019/8/5
     */
    SkTreasureBoxConfigInfoResParam info(Long id);

    /**
     * 获取有效的宝箱配置数量
     *
     * @param
     * @param startTime
     * @param endTime
     * @param id
     * @return int
     * @author qubianzhong
     * @Date 15:57 2019/8/12
     */
    int countOfEffective(Date startTime, Date endTime, Long id);
}
