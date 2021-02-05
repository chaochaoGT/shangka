package com.geek.shengka.content.controller;

import com.alibaba.fastjson.JSON;
import com.geek.shengka.common.annotation.IgnoreClientToken;
import com.geek.shengka.common.annotation.OnlyUserIgnoreToken;
import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.common.basemodel.ResponseBeanCode;
import com.geek.shengka.common.util.UserContextHolder;
import com.geek.shengka.content.request.SearchRequest;
import com.geek.shengka.content.service.SkSearchService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 搜索
 * @author
 *
 */
@RestController
@RequestMapping("v1/app")
@IgnoreClientToken
public class SkSearchController {
    private Logger logger= LoggerFactory.getLogger(SkSearchController.class);

    @Autowired
    private SkSearchService skSearchService;

    /**
     *  搜索页面配置
     * @return
     */
    @GetMapping("/searchPages")
    public BaseResponse searchPages() {
        return  BaseResponse.newSuccess(skSearchService.searchPages(UserContextHolder.getChannel()));
    }


    /**
     *  搜索  根据类型搜索  1视频 2用户 3话题
     * @param param
     * @return
     */
    @PostMapping("/searchByLabel")
    @OnlyUserIgnoreToken
    public BaseResponse searchByLabel(@RequestBody SearchRequest param) {
        logger.debug("searchByLabel is param !!!  {}", JSON.toJSONString(param));

        if (0==param.getLabel()){
            return BaseResponse.newFailure(ResponseBeanCode.REQUIRED_PARAMS_MISSING.getCode(),ResponseBeanCode.REQUIRED_PARAMS_MISSING.getMessage());
        }
        UserContextHolder.getDataFromHeader(param);
        return BaseResponse.newSuccess(skSearchService.searchByLabel(param));
    }



    /**
     *  榜单查看更多
     * @param param （ meduleId    moduleCode）
     * @return
     */
    @PostMapping("/seeMoreSourceList")
    public BaseResponse seeMoreSourceList(@RequestBody SearchRequest param) {
        UserContextHolder.getDataFromHeader(param);
        return BaseResponse.newSuccess(skSearchService.seeMoreSourceList(param));
    }

    /**
     *  人气榜单详情
     * @param param （ meduleId    moduleCode）
     * @return
     */
    @PostMapping("/popularRankInfo")
    public BaseResponse popularRankInfo(@RequestBody SearchRequest param) {
        UserContextHolder.getDataFromHeader(param);
        if (StringUtils.isEmpty(param.getSourceId())||StringUtils.isEmpty(param.getSourceType())){
            return BaseResponse.newFailure(ResponseBeanCode.REQUIRED_PARAMS_MISSING.getCode(),ResponseBeanCode.REQUIRED_PARAMS_MISSING.getMessage());
        }
        return BaseResponse.newSuccess(skSearchService.popularRankInfo(param));
    }
}
