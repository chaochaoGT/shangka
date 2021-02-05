package com.geek.shengka.backend.controller;

import com.geek.shengka.backend.annotation.SkTaskConfigAddCheck;
import com.geek.shengka.backend.annotation.SkTaskConfigUpdateCheck;
import com.geek.shengka.backend.params.req.SkTaskConfigAddReqParam;
import com.geek.shengka.backend.params.req.SkTaskConfigListReqParam;
import com.geek.shengka.backend.params.req.SkTaskConfigUpdateReqParam;
import com.geek.shengka.backend.params.res.SkTaskConfigInfoResParam;
import com.geek.shengka.backend.service.SkTaskConfigService;
import com.geek.shengka.backend.util.DataResult;
import com.geek.shengka.backend.util.DataResultUtils;
import com.geek.shengka.backend.util.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author qubianzhong
 * @date 2019/8/2 17:21
 */
@RestController
@RequestMapping(value = "/task")
@Api(tags = "任务配置")
@Validated
public class SkTaskConfigController extends BaseController {

    @Autowired
    private SkTaskConfigService skTaskConfigService;

    @GetMapping(value = "/info/{id}")
    @ApiOperation(value = "详情")
    public DataResult<SkTaskConfigInfoResParam> info(@PathVariable(value = "id") Long id) {
        return DataResult.ok(skTaskConfigService.info(id));
    }

    @GetMapping(value = "/list")
    @ApiOperation(value = "列表")
    public DataResult<PageVO<SkTaskConfigInfoResParam>> list(@Valid SkTaskConfigListReqParam param) {
        return DataResult.ok(skTaskConfigService.list(param));
    }

    @PostMapping(value = "/add")
    @ApiOperation(value = "新增")
    public DataResult<Boolean> add(@SkTaskConfigAddCheck @RequestBody SkTaskConfigAddReqParam add) {
        add.setCreateBy(getUserId());
        return DataResultUtils.add(skTaskConfigService.add(add));
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "更新")
    public DataResult<Boolean> update(@SkTaskConfigUpdateCheck @RequestBody SkTaskConfigUpdateReqParam update) {
        update.setUpdateBy(getUserId());
        return DataResultUtils.update(skTaskConfigService.update(update));
    }

    @DeleteMapping(value = "/delete/{id}")
    @ApiOperation(value = "删除")
    public DataResult<Boolean> delete(@PathVariable(value = "id") Long id) {
        return DataResultUtils.delete(skTaskConfigService.delete(id));
    }
}
