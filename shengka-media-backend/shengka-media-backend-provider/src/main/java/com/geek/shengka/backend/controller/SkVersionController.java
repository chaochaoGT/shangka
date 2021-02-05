package com.geek.shengka.backend.controller;

import com.geek.shengka.backend.annotation.SkVersionAddCheck;
import com.geek.shengka.backend.annotation.SkVersionUpdateCheck;
import com.geek.shengka.backend.params.req.SkVersionAddReqParam;
import com.geek.shengka.backend.params.req.SkVersionListReqParam;
import com.geek.shengka.backend.params.req.SkVersionUpdateReqParam;
import com.geek.shengka.backend.params.res.SkVersionInfoResParam;
import com.geek.shengka.backend.params.res.SkVersionListResParam;
import com.geek.shengka.backend.service.SkVersionService;
import com.geek.shengka.backend.util.DataResult;
import com.geek.shengka.backend.util.DataResultUtils;
import com.geek.shengka.backend.util.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author qubianzhong
 * @date 2019/8/1 13:44
 */
@RestController
@RequestMapping(value = "/version")
@Api(tags = "版本")
@Validated
public class SkVersionController extends BaseController {
    @Autowired
    private SkVersionService skVersionService;

    @GetMapping(value = "/info/{id}")
    @ApiOperation(value = "详情")
    public DataResult<SkVersionInfoResParam> info(@PathVariable(value = "id") Long id) {
        return DataResult.ok(skVersionService.info(id));
    }

    @GetMapping(value = "/list")
    @ApiOperation(value = "列表")
    public DataResult<PageVO<SkVersionListResParam>> list(SkVersionListReqParam param) {
        return DataResult.ok(skVersionService.list(param));
    }

    @PostMapping(value = "/add")
    @ApiOperation(value = "新增")
    public DataResult<Boolean> add(@SkVersionAddCheck @RequestBody SkVersionAddReqParam add) {
        add.setCreateBy(getUserId());
        return DataResultUtils.add(skVersionService.add(add));
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "更新")
    public DataResult<Boolean> update(@SkVersionUpdateCheck @RequestBody SkVersionUpdateReqParam update) {
        update.setUpdateBy(getUserId());
        return DataResultUtils.update(skVersionService.update(update));
    }

    @DeleteMapping(value = "/delete/{id}")
    @ApiOperation(value = "删除")
    public DataResult<Boolean> delete(@PathVariable(value = "id") Long id) {
        return DataResultUtils.delete(skVersionService.delete(id));
    }
}
