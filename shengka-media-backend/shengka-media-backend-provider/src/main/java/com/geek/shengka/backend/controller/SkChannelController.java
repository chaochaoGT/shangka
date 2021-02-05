package com.geek.shengka.backend.controller;

import com.geek.shengka.backend.annotation.SkChannelAddCheck;
import com.geek.shengka.backend.annotation.SkChannelUpdateCheck;
import com.geek.shengka.backend.params.req.SkChannelAddReqParam;
import com.geek.shengka.backend.params.req.SkChannelListReqParam;
import com.geek.shengka.backend.params.req.SkChannelUpdateReqParam;
import com.geek.shengka.backend.params.res.SkChannelInfoResParam;
import com.geek.shengka.backend.params.res.SkChannelListNoPageResParam;
import com.geek.shengka.backend.service.SkChannelService;
import com.geek.shengka.backend.util.DataResult;
import com.geek.shengka.backend.util.DataResultUtils;
import com.geek.shengka.backend.util.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author qubianzhong
 * @date 2019/7/31 17:24
 */
@RestController
@RequestMapping(value = "/channel")
@Api(tags = "渠道")
@Validated
public class SkChannelController extends BaseController {

    @Autowired
    private SkChannelService skChannelService;


    @GetMapping(value = "/info/{id}")
    @ApiOperation(value = "详情")
    public DataResult<SkChannelInfoResParam> info(@PathVariable(value = "id") Long id) {
        return DataResult.ok(skChannelService.info(id));
    }

    @GetMapping(value = "/list")
    @ApiOperation(value = "列表")
    public DataResult<PageVO<SkChannelInfoResParam>> list(SkChannelListReqParam param) {
        return DataResult.ok(skChannelService.list(param));
    }

    @GetMapping(value = "/listNoPage")
    @ApiOperation(value = "列表--不分页")
    public DataResult<List<SkChannelListNoPageResParam>> listNoPage() {
        return DataResult.ok(skChannelService.listNoPage());
    }

    @PostMapping(value = "/add")
    @ApiOperation(value = "新增")
    public DataResult<Boolean> add(@SkChannelAddCheck @RequestBody SkChannelAddReqParam add) {
        add.setCreateBy(getUserId());
        return DataResultUtils.add(skChannelService.add(add));
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "更新")
    public DataResult<Boolean> update(@SkChannelUpdateCheck @RequestBody SkChannelUpdateReqParam update) {
        update.setUpdateBy(getUserId());
        return DataResultUtils.update(skChannelService.update(update));
    }

    @DeleteMapping(value = "/delete/{id}")
    @ApiOperation(value = "删除")
    public DataResult<Boolean> delete(@PathVariable(value = "id") Long id) {
        return DataResultUtils.delete(skChannelService.delete(id));
    }
}
