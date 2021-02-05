package com.geek.shengka.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.user.entity.vo.SkFansNoticeVo;
import com.geek.shengka.user.service.impl.SkFansNoticeServiceImpl;

@RestController
@RequestMapping("/v1/fansnotice")
public class SkFansNoticeController {

	@Autowired
	private SkFansNoticeServiceImpl skFansNoticeServiceImpl;
	
    @GetMapping("/list")
    public BaseResponse<List<SkFansNoticeVo>> list(@RequestParam(value="pageIndex" ,required =true ) Integer pageIndex  , @RequestParam(value="pageSize" ,required =false ,defaultValue="10" ) Integer pageSize) {
        return skFansNoticeServiceImpl.noticeList(pageIndex,pageSize);
    }
}
