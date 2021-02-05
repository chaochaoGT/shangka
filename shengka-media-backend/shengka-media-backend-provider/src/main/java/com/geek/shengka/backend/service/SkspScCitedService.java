package com.geek.shengka.backend.service;


import com.geek.shengka.backend.entity.SkspScCited;
import com.geek.shengka.backend.params.req.SkspScCitedAddReqParam;
import com.geek.shengka.backend.params.req.SkspScCitedDTO;
import com.geek.shengka.backend.params.req.SkspScCitedQueryDTO;
import com.geek.shengka.backend.util.PageVO;

import java.util.List;

/**
 * @author: yunfei
 * @create: 2019-08-26 10:39
 **/
public interface SkspScCitedService {


    /**
     * add
     *
     * @param skspScCitedDTO
     * @return
     */
    int add(SkspScCitedDTO skspScCitedDTO);

    /**
     * UPDATE
     *
     * @param skspScCitedDTO
     * @return
     */
    int update(SkspScCitedDTO skspScCitedDTO);

    SkspScCitedDTO get(Long id);

    /**
     * 批量新增
     *
     * @param scCitedList
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 12:37 2019/8/29
     */
    Boolean uploadData(List<SkspScCitedAddReqParam> scCitedList);

    PageVO<SkspScCited> getList(SkspScCitedQueryDTO param);
}
