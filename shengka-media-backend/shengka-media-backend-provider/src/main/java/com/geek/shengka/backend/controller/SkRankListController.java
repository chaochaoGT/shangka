package com.geek.shengka.backend.controller;

import com.geek.shengka.backend.annotation.SkRankAddCheck;
import com.geek.shengka.backend.annotation.SkRankUpdateCheck;
import com.geek.shengka.backend.enums.SkRankTypeEnum;
import com.geek.shengka.backend.exception.BaseException;
import com.geek.shengka.backend.params.req.*;
import com.geek.shengka.backend.params.res.SkRankInfoResParam;
import com.geek.shengka.backend.params.res.SkRankListResParam;
import com.geek.shengka.backend.service.SkContentCenterService;
import com.geek.shengka.backend.service.SkRankListService;
import com.geek.shengka.backend.service.SkTopicConfigService;
import com.geek.shengka.backend.service.SkUserCenterService;
import com.geek.shengka.backend.util.DataResult;
import com.geek.shengka.backend.util.DataResultUtils;
import com.geek.shengka.backend.util.PageVO;
import com.geek.shengka.backend.util.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author qubianzhong
 * @date 2019/8/2 10:56
 */
@RestController
@RequestMapping(value = "/rankList")
@Api(tags = "人气榜单")
@Validated
public class SkRankListController extends BaseController {
    @Autowired
    private SkRankListService skRankListService;
    @Autowired
    private SkContentCenterService skContentCenterService;
    @Autowired
    private SkTopicConfigService skTopicConfigService;
    @Autowired
    private SkUserCenterService skUserCenterService;
    @Autowired
    private RedisService redisService;

    private final static String RANKLIST_REDIS_KEY = "sk:rankList";

    @GetMapping(value = "/info/{id}")
    @ApiOperation(value = "详情")
    public DataResult<SkRankInfoResParam> list(@PathVariable(value = "id") Long id) {
        return DataResult.ok(skRankListService.info(id));
    }

    @GetMapping(value = "/list")
    @ApiOperation(value = "列表")
    public DataResult<PageVO<SkRankListResParam>> list(SkRankListReqParam param) {
        return DataResult.ok(skRankListService.list(param));
    }

    @PostMapping(value = "/add")
    @ApiOperation(value = "新增")
    public DataResult<Boolean> add(@SkRankAddCheck @RequestBody SkRankAddReqParam add) throws BaseException {
        redisService.deleteKey(RANKLIST_REDIS_KEY);
        add.setCreateBy(getUserId());
        /**
         * 校验
         */
        checkSourceIds(add.getRankType(), add.getRankMappings());
        return DataResultUtils.add(skRankListService.add(add));
    }

    private void checkSourceIds(Byte rankType, List<SkRankMappingAddReqParam> rankMappings) throws BaseException {
        List<String> sourceIds = rankMappings.stream().map(SkRankMappingAddReqParam::getRelId).collect(Collectors.toList());
        if (SkRankTypeEnum.VIDEO.getValue().equals(rankType)) {
            skContentCenterService.checkVideoIds(sourceIds);
        } else if (SkRankTypeEnum.TOPIC.getValue().equals(rankType)) {
            skTopicConfigService.checkTopicIds(sourceIds);
        } else if (SkRankTypeEnum.USER.getValue().equals(rankType)) {
            skUserCenterService.checkUserIds(sourceIds);
        }
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "更新")
    public DataResult<Boolean> update(@SkRankUpdateCheck @RequestBody SkRankUpdateReqParam update) throws BaseException {
        redisService.deleteKey(RANKLIST_REDIS_KEY);
        update.setUpdateBy(getUserId());
        /**
         * 校验
         */
        checkSourceIds(update.getRankType(), update.getRankMappings());
        return DataResultUtils.update(skRankListService.update(update));
    }


    @PostMapping(value = "/enable")
    @ApiOperation(value = "开关")
    public DataResult<Boolean> update(@RequestBody SkRankEnableReqParam enable) {
        redisService.deleteKey(RANKLIST_REDIS_KEY);
        enable.setUpdateBy(getUserId());
        return DataResultUtils.update(skRankListService.enable(enable));
    }

    @DeleteMapping(value = "/delete/{id}")
    @ApiOperation(value = "删除")
    public DataResult<Boolean> delete(@PathVariable(value = "id") Long id) {
        redisService.deleteKey(RANKLIST_REDIS_KEY);
        return DataResultUtils.delete(skRankListService.delete(id));
    }
}
