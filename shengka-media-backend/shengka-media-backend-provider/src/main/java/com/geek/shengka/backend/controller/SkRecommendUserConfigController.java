package com.geek.shengka.backend.controller;

import com.geek.shengka.backend.exception.BaseException;
import com.geek.shengka.backend.params.req.SkRecommendUserConfigAddReqParam;
import com.geek.shengka.backend.params.req.SkRecommendUserConfigListReqParam;
import com.geek.shengka.backend.params.res.SkRecommendUserConfigListResParam;
import com.geek.shengka.backend.service.SkRecommendUserConfigService;
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
 * @date 2019/8/22 13:43
 */
@RestController
@Api(tags = "推荐人物配置")
@RequestMapping(value = "/recommendUserConfig")
@Validated
public class SkRecommendUserConfigController extends BaseController {

    @Autowired
    private SkRecommendUserConfigService skRecommendUserConfigService;

    @GetMapping(value = "/list")
    @ApiOperation(value = "列表")
    public DataResult<PageVO<SkRecommendUserConfigListResParam>> list(SkRecommendUserConfigListReqParam param) {
        return DataResult.ok(skRecommendUserConfigService.list(param));
    }

    @PostMapping(value = "/add")
    @ApiOperation(value = "新增")
    public DataResult<Boolean> add(@RequestBody SkRecommendUserConfigAddReqParam addReqParam) throws BaseException {
        int count = skRecommendUserConfigService.count(addReqParam.getUserId());
        if (count > 0) {
            throw new BaseException("当前用户已经配置过啦！");
        }
        return DataResultUtils.add(skRecommendUserConfigService.add(addReqParam));
    }


    @DeleteMapping(value = "/delete/{id}")
    @ApiOperation(value = "删除")
    public DataResult<Boolean> delete(@PathVariable(value = "id") Long id) {
        return DataResultUtils.update(skRecommendUserConfigService.delete(id));
    }
}
