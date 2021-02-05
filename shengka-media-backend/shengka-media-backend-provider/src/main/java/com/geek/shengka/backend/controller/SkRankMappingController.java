package com.geek.shengka.backend.controller;

import com.geek.shengka.backend.entity.SkTopicConfig;
import com.geek.shengka.backend.enums.SkRankTypeEnum;
import com.geek.shengka.backend.exception.BaseException;
import com.geek.shengka.backend.params.req.SkRankMappingListReqParam;
import com.geek.shengka.backend.params.res.SkContentVideoInfoResParam;
import com.geek.shengka.backend.params.res.SkUserInfoResParam;
import com.geek.shengka.backend.service.SkRankMappingService;
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
 * @date 2019/8/2 10:56
 */
@RestController
@RequestMapping(value = "/rankMapping")
@Api(tags = "人气榜单映射")
@Validated
public class SkRankMappingController extends BaseController {

    @Autowired
    private SkRankMappingService skRankMappingService;

    @GetMapping(value = "/video/list")
    @ApiOperation(value = "视频列表")
    public DataResult<PageVO<SkContentVideoInfoResParam>> videoList(SkRankMappingListReqParam param) throws BaseException {
        param.setRankType(SkRankTypeEnum.VIDEO.getValue());
        return DataResult.ok(skRankMappingService.videoList(param));
    }

    @GetMapping(value = "/topic/list")
    @ApiOperation(value = "话题列表")
    public DataResult<PageVO<SkTopicConfig>> topicList(SkRankMappingListReqParam param) {
        param.setRankType(SkRankTypeEnum.TOPIC.getValue());
        return DataResult.ok(skRankMappingService.topicList(param));
    }

    @GetMapping(value = "/user/list")
    @ApiOperation(value = "用户列表")
    public DataResult<PageVO<SkUserInfoResParam>> userList(SkRankMappingListReqParam param) throws BaseException {
        param.setRankType(SkRankTypeEnum.USER.getValue());
        return DataResult.ok(skRankMappingService.userList(param));
    }
}
