package com.geek.shengka.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.user.entity.vo.SkCommentNoticeVo;
import com.geek.shengka.user.service.impl.SkCommentNoticeServiceImpl;

@RestController
@RequestMapping("/v1/commentnotice")
public class SkCommentNoticeController {

	@Autowired
	private SkCommentNoticeServiceImpl skCommentNoticeServiceImpl;
	
    @GetMapping("/list")
    public BaseResponse<List<SkCommentNoticeVo>> list(@RequestParam(value="pageIndex" ,required =true ) Integer pageIndex  , @RequestParam(value="pageSize" ,required =false ,defaultValue="10" ) Integer pageSize) {
        return skCommentNoticeServiceImpl.noticeList(pageIndex,pageSize);
    }
    
    @GetMapping("/delete")
    public BaseResponse<List<SkCommentNoticeVo>> delete(@RequestParam(value="ids" ,required =true) String ids){
        skCommentNoticeServiceImpl.delete(ids);
        return BaseResponse.newSuccess(null);
    }
}
