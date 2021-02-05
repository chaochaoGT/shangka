package com.geek.shengka.user.controller;

import com.geek.shengka.common.annotation.IgnoreClientToken;
import com.geek.shengka.common.annotation.OnlyUserIgnoreToken;
import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.common.basemodel.ResponseBeanCode;
import com.geek.shengka.common.util.UserContextHolder;
import com.geek.shengka.user.request.SkCategoryRequest;
import com.geek.shengka.user.service.SkCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Filename: SkCategoryController
 * @Description:  频道
 * @Version: 1.0
 * @Author: wangchao
 * @Email: wangchao@hellogeek.com
 * @date: 2019/7/31 ;
 */
@RestController
@RequestMapping("v1/app/category")
public class SkCategoryController  {
    private Logger logger= LoggerFactory.getLogger(SkCategoryController.class);

    @Autowired
    private SkCategoryService skCategoryService;

    /**
     * 频道页顶部视频、up主列表
     * @param request
     * @return
     */
    @PostMapping("/topMediaUpList")
    @IgnoreClientToken
    public BaseResponse topMediaUpList(@RequestBody SkCategoryRequest request){
        logger.debug("topMediaUpList  request={}",request);
        UserContextHolder.getDataFromHeader(request);
     return BaseResponse.newSuccess(skCategoryService.topMediaUpList(request));
    }
    /**
     * 频道页顶部视频
     * @param request
     * @return
     */
    @PostMapping("/topMediaList")
    @IgnoreClientToken
    public BaseResponse topMediaList(@RequestBody SkCategoryRequest request){
        logger.debug("topMediaList  request={}",request);
        UserContextHolder.getDataFromHeader(request);
     return BaseResponse.newSuccess(skCategoryService.topMediaList(request));
    }
    /**
     * 频道页up主列表
     * @param request
     * @return
     */
    @PostMapping("/topUpList")
    @OnlyUserIgnoreToken
    public BaseResponse topUpList(@RequestBody SkCategoryRequest request){
        logger.debug("topUpList  request={}",request);
        UserContextHolder.getDataFromHeader(request);
     return BaseResponse.newSuccess(skCategoryService.topUpList(request));
    }

    /**
     * 频道推荐控制-我的频道+待添加频道
     * @return
     */
    @GetMapping("/categoryRecList")
    @OnlyUserIgnoreToken
    public BaseResponse categoryRecList(){
        logger.debug("categoryRecList  ");
        return BaseResponse.newSuccess(skCategoryService.categoryRecList(UserContextHolder.getChannel(),UserContextHolder.getUserId()));
    }

    /**
     * 删除我的频道
     * @param categoryId
     * @return
     */
    @GetMapping("/delMyCategory")
    public BaseResponse delMyCategory(Long categoryId){
        logger.debug("delMyCategory  categoryId={}",categoryId);
        if (null==categoryId||0==categoryId){
            return BaseResponse.newFailure(ResponseBeanCode.REQUIRED_PARAMS_MISSING.getCode(), ResponseBeanCode.REQUIRED_PARAMS_MISSING.getMessage());
        }
        Long userId = UserContextHolder.getUserId();
        skCategoryService.delMyCategory(categoryId,userId);
        return BaseResponse.newSuccess();
    }

    /**
     * 添加我的频道
     * @param categoryId
     * @return
     */
    @GetMapping("/addMyCategory")
    public BaseResponse addMyCategory(String categoryId){
        logger.debug("addMyCategory  categoryId={}",categoryId);
        Long userId = UserContextHolder.getUserId();
        String channel = UserContextHolder.getChannel();
        skCategoryService.addMyCategory(categoryId,userId,channel);
        return BaseResponse.newSuccess();
    }

    /**
     * 根据频道获取视频
     * @return
     */
    @PostMapping("/mediaListByCategoryId")
    @IgnoreClientToken
    public BaseResponse mediaListByCategoryId(@RequestBody SkCategoryRequest request){
        UserContextHolder.getDataFromHeader(request);
        logger.info("mediaListByCategoryId  request={}",request);
        if (null==request.getCategoryId()||0==request.getCategoryId()){
            return BaseResponse.newFailure(ResponseBeanCode.REQUIRED_PARAMS_MISSING.getCode(), ResponseBeanCode.REQUIRED_PARAMS_MISSING.getMessage());
        }
        boolean b = skCategoryService.checkCategoryByChannel(request.getCategoryId(), "");
        if (!b){
            return BaseResponse.newFailure(1,"当前频道已失效！");
        }
        return BaseResponse.newSuccess(skCategoryService.mediaListByCategoryId(request));
    }

    /**
     * 根据获取我的频道
     * @return
     */
    @GetMapping("/myCategoryList")
    @OnlyUserIgnoreToken
    public BaseResponse myCategoryList(){
        String channel = UserContextHolder.getChannel();
        Long userId = UserContextHolder.getUserId();
        return BaseResponse.newSuccess(skCategoryService.myCategoryList(channel,userId));
    }
    /**
     * 获取待添加频道
     * @param
     * @return
     */
    @GetMapping("/willAddCategoryList")
    @OnlyUserIgnoreToken
    public BaseResponse willAddCategoryList(){
        String channel = UserContextHolder.getChannel();
        Long userId = UserContextHolder.getUserId();
        return BaseResponse.newSuccess(skCategoryService.willAddCategoryList(channel,userId));
    }
}
