package com.geek.shengka.backend.service;

import com.geek.shengka.backend.params.req.SklSdsrAddReqParam;
import com.geek.shengka.backend.params.res.SkSdsrListReqParam;
import com.geek.shengka.backend.params.req.BasePageReqParam;
import com.geek.shengka.backend.util.PageVO;

import java.util.List;

/**
 * @author heguoya
 * @version 1.0
 * @className SkSdsrService
 * @description 收支
 * @date 2019/8/13 11:14
 */
public interface SkSdsrService {
    /**
     * 获取收支列表
     *
     * @param req
     * @return
     */
    PageVO<SkSdsrListReqParam> getSdsrList(BasePageReqParam req);

    /**
     * 上传更新数据
     * @param list
     */
    Boolean uploadData(List<SklSdsrAddReqParam> list);
}
