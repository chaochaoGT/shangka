package com.geek.shengka.backend.controller;

import com.geek.shengka.backend.annotation.SkWithdrawConfigAddCheck;
import com.geek.shengka.backend.annotation.SkWithdrawConfigUpdateCheck;
import com.geek.shengka.backend.params.req.SkWithdrawConfigAddReqParam;
import com.geek.shengka.backend.params.req.SkWithdrawConfigListReqParam;
import com.geek.shengka.backend.params.req.SkWithdrawConfigUpdateReqParam;
import com.geek.shengka.backend.params.res.SkWithdrawConfigInfoResParam;
import com.geek.shengka.backend.params.res.SkWithdrawConfigListResParam;
import com.geek.shengka.backend.service.SkWithdrawConfigService;
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
 * @date 2019/8/2 17:21
 */
@RestController
@RequestMapping(value = "/withdraw")
@Api(tags = "提现配置")
@Validated
public class SkWithdrawConfigController extends BaseController {

    @Autowired
    private SkWithdrawConfigService skWithdrawConfigService;

    @GetMapping(value = "/info/{id}")
    @ApiOperation(value = "详情")
    public DataResult<SkWithdrawConfigInfoResParam> info(@PathVariable(value = "id") Long id) {
        return DataResult.ok(skWithdrawConfigService.info(id));
    }

    @GetMapping(value = "/list")
    @ApiOperation(value = "列表")
    public DataResult<PageVO<SkWithdrawConfigListResParam>> list(SkWithdrawConfigListReqParam param) {
        return DataResult.ok(skWithdrawConfigService.list(param));
    }

    @PostMapping(value = "/add")
    @ApiOperation(value = "新增")
    public DataResult<Boolean> add(@SkWithdrawConfigAddCheck @RequestBody SkWithdrawConfigAddReqParam add) {
        add.setCreateBy(getUserId());
        return DataResultUtils.add(skWithdrawConfigService.add(add));
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "更新")
    public DataResult<Boolean> update(@SkWithdrawConfigUpdateCheck @RequestBody SkWithdrawConfigUpdateReqParam update) {
        update.setUpdateBy(getUserId());
        return DataResultUtils.update(skWithdrawConfigService.update(update));
    }

    @DeleteMapping(value = "/delete/{id}")
    @ApiOperation(value = "删除")
    public DataResult<Boolean> delete(@PathVariable(value = "id") Long id) {
        return DataResultUtils.delete(skWithdrawConfigService.delete(id));
    }
}
