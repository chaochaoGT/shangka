package com.geek.shengka.backend.controller;

import com.geek.shengka.backend.exception.BaseException;
import com.geek.shengka.backend.params.req.SkTopicVideoListReqParam;
import com.geek.shengka.backend.params.res.SkContentVideoInfoResParam;
import com.geek.shengka.backend.service.SkTopicVideoService;
import com.geek.shengka.backend.util.DataResult;
import com.geek.shengka.backend.util.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qubianzhong
 * @date 2019/8/1 17:01
 */
@RestController
@RequestMapping(value = "/topicVideo")
@Api(tags = "话题视频")
@Validated
public class SkTopicVideoController extends BaseController{
    @Autowired
    private SkTopicVideoService skTopicVideoService;

    @GetMapping(value = "/list")
    @ApiOperation(value = "列表")
    public DataResult<PageVO<SkContentVideoInfoResParam>> list(SkTopicVideoListReqParam reqParam) throws BaseException {
        return DataResult.ok(skTopicVideoService.list(reqParam));
    }

}
