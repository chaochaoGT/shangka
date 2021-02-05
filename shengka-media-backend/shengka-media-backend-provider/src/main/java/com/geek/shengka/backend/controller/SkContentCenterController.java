package com.geek.shengka.backend.controller;

import com.geek.shengka.backend.exception.BaseException;
import com.geek.shengka.backend.params.res.SkContentVideoInfoResParam;
import com.geek.shengka.backend.service.SkContentCenterService;
import com.geek.shengka.backend.util.DataResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author qubianzhong
 * @date 2019/8/5 20:55
 */
@RestController
@RequestMapping(value = "/contentCenter")
@Api(tags = "内容中心--非直接调用，测试使用")
@Validated
public class SkContentCenterController extends BaseController {

    @Autowired
    private SkContentCenterService skContentCenterService;

    @ApiOperation(value = "获取token")
    @GetMapping(value = "/token")
    public DataResult<String> token() {
        return DataResult.ok(skContentCenterService.getToken());
    }

    @ApiOperation(value = "列表-通过ids查询")
    @PostMapping(value = "/list")
    public DataResult<List<SkContentVideoInfoResParam>> list(@RequestBody List<String> ids) throws BaseException {
        return DataResult.ok(skContentCenterService.getVideoInfosByIds(ids));
    }

}
