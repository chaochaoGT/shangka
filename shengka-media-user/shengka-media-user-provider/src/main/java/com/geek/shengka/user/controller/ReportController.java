package com.geek.shengka.user.controller;

import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.common.basemodel.ResponseCode;
import com.geek.shengka.common.util.UserContextHolder;
import com.geek.shengka.user.request.UserReportRequest;
import com.geek.shengka.user.service.ReportService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 举报
 *
 * @author: xuxuelei
 * @create: 2019-08-01 11:14
 **/
@RestController
@RequestMapping("/v1/app/report")
public class ReportController {

   @Autowired
   private ReportService reportService;

    /***
           * 举报用户
     * @param reportUserId
     * @return
     */
    @PostMapping("/reportUser")
    public BaseResponse addBlackUser(@RequestBody @Valid UserReportRequest userReportRequest) {
        long userId = UserContextHolder.getUserId();
        if (userId <= 0) {
            return BaseResponse.newFailure(ResponseCode.REQUIRED_PARAMS_MISSING);
        }
        return BaseResponse.newSuccess(reportService.reportUser(userReportRequest));
    }

    
    
 
    
}
