package com.geek.shengka.user.controller;

import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.common.basemodel.ResponseCode;
import com.geek.shengka.common.http.ContextTools;
import com.geek.shengka.common.util.UserContextHolder;
import com.geek.shengka.user.request.BlackPageRequest;
import com.geek.shengka.user.request.BlackRequest;
import com.geek.shengka.user.service.BlackService;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 黑名单
 *
 * @author: xuxuelei
 * @create: 2019-08-01 11:14
 **/
@RestController
@RequestMapping("/v1/app/black")
public class BlackController {

   @Autowired
   private BlackService blackService;

    /***
     * 加入黑名单
     * @param params
     * @return
     */
    @PostMapping("/addBlackUser")
    public BaseResponse addBlackUser(@RequestBody @Valid BlackRequest param) {
   	 long userId = UserContextHolder.getUserIdByHeader();
     if (userId <= 0) {
         return BaseResponse.newFailure(ResponseCode.REQUIRED_PARAMS_MISSING);
     }
        return BaseResponse.newSuccess(blackService.addBlackUser(param));
    }

    
    
    /***
     * 获取黑名单列表
     * @param params
     * @return
     */
    @PostMapping("/getBlacklist")
    public BaseResponse getBlacklist(@RequestBody @Valid BlackPageRequest param) {
   	 long userId = UserContextHolder.getUserIdByHeader();
     if (userId <= 0) {
         return BaseResponse.newFailure(ResponseCode.REQUIRED_PARAMS_MISSING);
     }
        return BaseResponse.newSuccess(blackService.selectByUserList(param));
    }
    
    
    
    /***
     * 删除黑名单
     * @param params
     * @return
     */
    @GetMapping("/delBlackUser")
    public BaseResponse delBlackUser(long backId) {
    	 long userId = UserContextHolder.getUserIdByHeader();
         if (userId <= 0||backId<=0) {
             return BaseResponse.newFailure(ResponseCode.REQUIRED_PARAMS_MISSING);
         }
        return BaseResponse.newSuccess(blackService.delBlackUser(backId));
    }
    
    
}
