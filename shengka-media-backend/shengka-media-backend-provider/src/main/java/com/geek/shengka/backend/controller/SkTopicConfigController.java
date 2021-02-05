package com.geek.shengka.backend.controller;

import com.geek.shengka.backend.annotation.SkTopicConfigAddCheck;
import com.geek.shengka.backend.annotation.SkTopicConfigUpdateCheck;
import com.geek.shengka.backend.exception.BaseException;
import com.geek.shengka.backend.params.req.SkTopicConfigAddReqParam;
import com.geek.shengka.backend.params.req.SkTopicConfigListReqParam;
import com.geek.shengka.backend.params.req.SkTopicConfigUpdateReqParam;
import com.geek.shengka.backend.params.res.SkTopicConfigInfoResParam;
import com.geek.shengka.backend.params.res.SkTopicConfigListResParam;
import com.geek.shengka.backend.service.SkTopicConfigService;
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
 * @date 2019/8/1 17:01
 */
@RestController
@RequestMapping(value = "/topic")
@Api(tags = "话题")
@Validated
public class SkTopicConfigController extends BaseController {
    @Autowired
    private SkTopicConfigService skTopicConfigService;

    @GetMapping(value = "/info/{id}")
    @ApiOperation(value = "详情")
    public DataResult<SkTopicConfigInfoResParam> info(@PathVariable(value = "id") Long id) {
        return DataResult.ok(skTopicConfigService.info(id));
    }

    @GetMapping(value = "/list")
    @ApiOperation(value = "列表")
    public DataResult<PageVO<SkTopicConfigListResParam>> list(SkTopicConfigListReqParam param) {
        return DataResult.ok(skTopicConfigService.list(param));
    }

    @PostMapping(value = "/add")
    @ApiOperation(value = "新增")
    public DataResult<Boolean> add(@SkTopicConfigAddCheck @RequestBody SkTopicConfigAddReqParam add) throws BaseException {
        Long topicConfigId = skTopicConfigService.countByTopicName(add.getTopicName());
        if (topicConfigId != null) {
            return DataResult.fail("话题名称重复，请修改后重试。[也有可能与用户自定义话题重复]");
        }

        add.setCreateBy(getUserId());
        return DataResultUtils.add(skTopicConfigService.add(add));
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "更新")
    public DataResult<Boolean> update(@SkTopicConfigUpdateCheck @RequestBody SkTopicConfigUpdateReqParam update) throws BaseException {
        Long topicConfigId = skTopicConfigService.countByTopicName(update.getTopicName());
        if (topicConfigId != null && topicConfigId.longValue() != update.getId()) {
            return DataResult.fail("话题名称重复，请修改后重试。[也有可能与用户自定义话题重复]");
        }
        update.setUpdateBy(getUserId());
        return DataResultUtils.update(skTopicConfigService.update(update));
    }

    @DeleteMapping(value = "/delete/{id}")
    @ApiOperation(value = "删除")
    public DataResult<Boolean> delete(@PathVariable(value = "id") Long id) throws BaseException {
        return DataResultUtils.delete(skTopicConfigService.delete(id));
    }
}
