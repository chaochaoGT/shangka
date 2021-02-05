package com.geek.shengka.backend.controller;

import com.geek.shengka.backend.annotation.SkNativeMessageAddCheck;
import com.geek.shengka.backend.annotation.SkNativeMessageUpdateCheck;
import com.geek.shengka.backend.entity.SkNativeMessage;
import com.geek.shengka.backend.params.req.SkNativeMessageAddReqParam;
import com.geek.shengka.backend.params.req.SkNativeMessageListReqParam;
import com.geek.shengka.backend.params.req.SkNativeMessageUpdateReqParam;
import com.geek.shengka.backend.service.SkNativeMessageService;
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
 * @date 2019/8/5 10:26
 */
@RestController
@RequestMapping(value = "/nativeMessage")
@Api(tags = "站内信配置")
@Validated
public class SkNativeMessageController extends BaseController {

    @Autowired
    private SkNativeMessageService skNativeMessageService;


    @GetMapping(value = "/info/{id}")
    @ApiOperation(value = "详情")
    public DataResult<SkNativeMessage> info(@PathVariable(value = "id") Long id) {
        return DataResult.ok(skNativeMessageService.info(id));
    }

    @GetMapping(value = "/list")
    @ApiOperation(value = "列表")
    public DataResult<PageVO<SkNativeMessage>> list(SkNativeMessageListReqParam param) {
        return DataResult.ok(skNativeMessageService.list(param));
    }

    @PostMapping(value = "/add")
    @ApiOperation(value = "新增")
    public DataResult<Boolean> add(@SkNativeMessageAddCheck @RequestBody SkNativeMessageAddReqParam add) {
        add.setCreateBy(getUserId());
        return DataResultUtils.add(skNativeMessageService.add(add));
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "更新")
    public DataResult<Boolean> update(@SkNativeMessageUpdateCheck @RequestBody SkNativeMessageUpdateReqParam update) {
        update.setUpdateBy(getUserId());
        return DataResultUtils.update(skNativeMessageService.update(update));
    }

    @DeleteMapping(value = "/delete/{id}")
    @ApiOperation(value = "删除")
    public DataResult<Boolean> delete(@PathVariable(value = "id") Long id) {
        return DataResultUtils.delete(skNativeMessageService.delete(id));
    }
}
