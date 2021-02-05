package com.geek.shengka.backend.controller;

import com.geek.shengka.backend.params.req.SkPublishVideoListReqParam;
import com.geek.shengka.backend.params.res.SkPublishVideoListResParam;
import com.geek.shengka.backend.service.SkPublishVideoService;
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
 * @date 2019/8/20 15:27
 */
@RestController
@RequestMapping(value = "/publishVideo")
@Api(tags = "发布视频列表")
@Validated
public class SkPublishVideoController extends BaseController {

    @Autowired
    private SkPublishVideoService skPublishVideoService;

    @GetMapping(value = "/list")
    @ApiOperation(value = "列表")
    public DataResult<PageVO<SkPublishVideoListResParam>> list(SkPublishVideoListReqParam param) {
        return DataResult.ok(skPublishVideoService.list(param));
    }
}
