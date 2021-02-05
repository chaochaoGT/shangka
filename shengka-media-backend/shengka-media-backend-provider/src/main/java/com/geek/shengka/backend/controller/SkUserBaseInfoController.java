package com.geek.shengka.backend.controller;

import com.geek.shengka.backend.params.req.SkUserBaseInfoListReqParam;
import com.geek.shengka.backend.params.res.SkUserBaseInfoListResParam;
import com.geek.shengka.backend.service.SkUserBaseInfoService;
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
 * @date 2019/8/20 16:25
 */
@RestController
@RequestMapping(value = "/userBaseInfo")
@Api(tags = "用户列表")
@Validated
public class SkUserBaseInfoController extends BaseController {

    @Autowired
    private SkUserBaseInfoService skUserBaseInfoService;

    @GetMapping(value = "/list")
    @ApiOperation(value = "列表")
    public DataResult<PageVO<SkUserBaseInfoListResParam>> list(SkUserBaseInfoListReqParam param){
        return DataResult.ok(skUserBaseInfoService.list(param));
    }
}
