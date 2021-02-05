package com.geek.shengka.content.controller;

import com.geek.shengka.common.annotation.IgnoreClientToken;
import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.common.basemodel.ResponseBeanCode;
import com.geek.shengka.common.util.UserContextHolder;
import com.geek.shengka.content.request.SkCategoryRequest;
import com.geek.shengka.content.service.SkCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class SkCategoryController {
    private Logger logger= LoggerFactory.getLogger(SkCategoryController.class);

    @Autowired
    private SkCategoryService skCategoryService;

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
}
