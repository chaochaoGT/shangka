package com.geek.shengka.backend.controller;

import com.geek.shengka.backend.annotation.SkTreasureBoxConfigAddCheck;
import com.geek.shengka.backend.annotation.SkTreasureBoxConfigUpdateCheck;
import com.geek.shengka.backend.enums.SkTreasureBoxConfigEnableEnum;
import com.geek.shengka.backend.params.req.SkTreasureBoxConfigAddReqParam;
import com.geek.shengka.backend.params.req.SkTreasureBoxConfigEnableReqParam;
import com.geek.shengka.backend.params.req.SkTreasureBoxConfigListReqParam;
import com.geek.shengka.backend.params.req.SkTreasureBoxConfigUpdateReqParam;
import com.geek.shengka.backend.params.res.SkTreasureBoxConfigInfoResParam;
import com.geek.shengka.backend.params.res.SkTreasureBoxConfigListResParam;
import com.geek.shengka.backend.service.SkTreasureBoxConfigService;
import com.geek.shengka.backend.util.BeanMapperUtils;
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
@RequestMapping(value = "/treasureBox")
@Api(tags = "宝箱配置")
@Validated
public class SkTreasureBoxConfigController extends BaseController {

    @Autowired
    private SkTreasureBoxConfigService skTreasureBoxConfigService;

    @GetMapping(value = "/info/{id}")
    @ApiOperation(value = "详情")
    public DataResult<SkTreasureBoxConfigInfoResParam> info(@PathVariable(value = "id") Long id) {
        return DataResult.ok(skTreasureBoxConfigService.info(id));
    }

    @GetMapping(value = "/list")
    @ApiOperation(value = "列表")
    public DataResult<PageVO<SkTreasureBoxConfigListResParam>> list(SkTreasureBoxConfigListReqParam param) {
        return DataResult.ok(skTreasureBoxConfigService.list(param));
    }

    @PostMapping(value = "/add")
    @ApiOperation(value = "新增")
    public DataResult<Boolean> add(@SkTreasureBoxConfigAddCheck @RequestBody SkTreasureBoxConfigAddReqParam add) {
        add.setCreateBy(getUserId());
        int count = skTreasureBoxConfigService.countOfEffective(add.getStartTime(), add.getEndTime(), null);
        if (count > 0) {
            return DataResult.fail("当前时间段内，已存在有效的宝箱配置！");
        }
        return DataResultUtils.add(skTreasureBoxConfigService.add(add));
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "更新")
    public DataResult<Boolean> update(@SkTreasureBoxConfigUpdateCheck @RequestBody SkTreasureBoxConfigUpdateReqParam update) {
        update.setUpdateBy(getUserId());
        int count = skTreasureBoxConfigService.countOfEffective(update.getStartTime(), update.getEndTime(), update.getId());
        if (count > 0) {
            return DataResult.fail("当前时间段内，已存在有效的宝箱配置。");
        }
        return DataResultUtils.update(skTreasureBoxConfigService.update(update));
    }

    @PostMapping(value = "/enable")
    @ApiOperation(value = "开关,如果是开启，则进行当前时间端校验")
    public DataResult<Boolean> enable(@RequestBody SkTreasureBoxConfigEnableReqParam enable) {
        enable.setUpdateBy(getUserId());
        if (enable.getEnable().equals(SkTreasureBoxConfigEnableEnum.OPEN.getValue())) {
            SkTreasureBoxConfigInfoResParam infoResParam = skTreasureBoxConfigService.info(enable.getId());
            int count = skTreasureBoxConfigService.countOfEffective(infoResParam.getStartTime(), infoResParam.getEndTime(), enable.getId());
            if (count > 0) {
                return DataResult.fail("当前时间端内，已存在有效的宝箱配置！");
            }
        }
        return DataResultUtils.update(skTreasureBoxConfigService.update(BeanMapperUtils.map(enable, SkTreasureBoxConfigUpdateReqParam.class)));
    }

    @DeleteMapping(value = "/delete/{id}")
    @ApiOperation(value = "删除")
    public DataResult<Boolean> delete(@PathVariable(value = "id") Long id) {
        return DataResultUtils.delete(skTreasureBoxConfigService.delete(id));
    }
}
