package com.geek.shengka.backend.controller;

import com.geek.shengka.backend.entity.SkTopicConfig;
import com.geek.shengka.backend.enums.SkSearchPageConfigMoudleTypeEnum;
import com.geek.shengka.backend.exception.BaseException;
import com.geek.shengka.backend.params.req.SkSearchPageConfigMappingListReqParam;
import com.geek.shengka.backend.params.res.SkContentVideoInfoResParam;
import com.geek.shengka.backend.service.SkSearchPageConfigMappingService;
import com.geek.shengka.backend.util.DataResult;
import com.geek.shengka.backend.util.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qubianzhong
 * @date 2019/8/7 14:17
 */
@RestController
@RequestMapping(value = "/searchPageMapping")
@Api(tags = "搜索页配置映射")
@Validated
public class SkSearchPageConfigMappingController extends BaseController {

    @Autowired
    private SkSearchPageConfigMappingService skSearchPageConfigMappingService;

    @GetMapping(value = "/video/list")
    @ApiOperation(value = "视频列表")
    public DataResult<PageVO<SkContentVideoInfoResParam>> videoList(SkSearchPageConfigMappingListReqParam param) throws BaseException {
        param.setModuleType(SkSearchPageConfigMoudleTypeEnum.VIDEO.getValue());
        return DataResult.ok(skSearchPageConfigMappingService.videoList(param));
    }

    @GetMapping(value = "/topic/list")
    @ApiOperation(value = "话题列表")
    public DataResult<PageVO<SkTopicConfig>> topicList(SkSearchPageConfigMappingListReqParam param) throws BaseException {
        param.setModuleType(SkSearchPageConfigMoudleTypeEnum.TOPIC.getValue());
        return DataResult.ok(skSearchPageConfigMappingService.topicList(param));
    }
}
