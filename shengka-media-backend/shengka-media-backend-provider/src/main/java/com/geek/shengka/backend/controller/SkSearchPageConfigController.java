package com.geek.shengka.backend.controller;

import com.geek.shengka.backend.annotation.SkSearchPageConfigAddCheck;
import com.geek.shengka.backend.annotation.SkSearchPageConfigUpdateCheck;
import com.geek.shengka.backend.params.req.SkSearchPageConfigAddReqParam;
import com.geek.shengka.backend.params.req.SkSearchPageConfigUpdateReqParam;
import com.geek.shengka.backend.params.res.SkSearchPageConfigInfoResParam;
import com.geek.shengka.backend.params.res.SkSearchPageConfigListResParam;
import com.geek.shengka.backend.service.SkSearchPageConfigService;
import com.geek.shengka.backend.util.DataResult;
import com.geek.shengka.backend.util.DataResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author qubianzhong
 * @date 2019/8/5 9:48
 */
@RestController
@RequestMapping(value = "/searchPage")
@Api(tags = "搜索页配置")
@Validated
public class SkSearchPageConfigController extends BaseController {

    @Autowired
    private SkSearchPageConfigService skSearchPageConfigService;

    @GetMapping(value = "/info/{id}")
    @ApiOperation(value = "列表")
    public DataResult<SkSearchPageConfigInfoResParam> info(@PathVariable(value = "id")Long id) {
        return DataResult.ok(skSearchPageConfigService.info(id));
    }

    @GetMapping(value = "/list")
    @ApiOperation(value = "列表")
    public DataResult<List<SkSearchPageConfigListResParam>> list() {
        return DataResult.ok(skSearchPageConfigService.list());
    }

    @PostMapping(value = "/add")
    @ApiOperation(value = "新增")
    public DataResult<Boolean> add(@SkSearchPageConfigAddCheck @RequestBody SkSearchPageConfigAddReqParam add) {
        add.setCreateBy(getUserId());
        return DataResultUtils.add(skSearchPageConfigService.add(add));
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "更新")
    public DataResult<Boolean> update(@SkSearchPageConfigUpdateCheck @RequestBody SkSearchPageConfigUpdateReqParam update) {
        update.setUpdateBy(getUserId());
        return DataResultUtils.update(skSearchPageConfigService.update(update));
    }

    @DeleteMapping(value = "/delete/{id}")
    @ApiOperation(value = "删除")
    public DataResult<Boolean> delete(@PathVariable(value = "id") Long id) {
        return DataResultUtils.delete(skSearchPageConfigService.delete(id));
    }
}
