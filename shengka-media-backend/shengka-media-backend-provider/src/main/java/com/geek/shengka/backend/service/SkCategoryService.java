package com.geek.shengka.backend.service;

import com.geek.shengka.backend.exception.BaseException;
import com.geek.shengka.backend.params.req.SkCategoryAddReqParam;
import com.geek.shengka.backend.params.req.SkCategoryListReqParam;
import com.geek.shengka.backend.params.req.SkCategoryUpdateReqParam;
import com.geek.shengka.backend.params.res.SkCategoryInfoResParam;
import com.geek.shengka.backend.params.res.SkContentCenterCategorysResParam;
import com.geek.shengka.backend.util.PageVO;

import java.util.List;

/**
 * @author qubianzhong
 * @date 2019/7/31 17:20
 */
public interface SkCategoryService {

    /**
     * info
     *
     * @param id
     * @return com.geek.shengka.backend.params.res.SkCategoryInfoResParam
     * @author qubianzhong
     * @Date 14:00 2019/8/5
     */
    SkCategoryInfoResParam info(Long id);

    /**
     * 列表--分页
     *
     * @param param
     * @return com.geek.shengka.backend.util.PageVO<com.geek.shengka.backend.entity.SkCategory>
     * @author qubianzhong
     * @Date 17:35 2019/7/31
     */
    PageVO<SkCategoryInfoResParam> list(SkCategoryListReqParam param);

    /**
     * 新增
     *
     * @param add
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 17:47 2019/7/31
     */
    Boolean add(SkCategoryAddReqParam add);

    /**
     * 更新
     *
     * @param update
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 17:47 2019/7/31
     */
    Boolean update(SkCategoryUpdateReqParam update);

    /**
     * 删除
     *
     * @param id
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 17:49 2019/7/31
     */
    Boolean delete(Long id);

    /**
     * 内容中心视频分类列表
     *
     * @param
     * @return java.util.List<com.geek.shengka.backend.params.res.SkContentCenterCategorysResParam>
     * @author qubianzhong
     * @Date 16:55 2019/8/8
     */
    List<SkContentCenterCategorysResParam> categories() throws BaseException;
}
