package com.geek.shengka.user.controller;

import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.user.entity.vo.SkThumbsUpNoticeVO;
import com.geek.shengka.user.service.impl.SkThumbsUpNoticeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/thumbsupnotice")
public class SKThumbsUpNoticeController {

	@Autowired
	private SkThumbsUpNoticeServiceImpl skThumbsUpNoticeServiceImpl;

    /**
     * 赞我的信息列表
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public BaseResponse<List<SkThumbsUpNoticeVO>> list(@RequestParam(value="pageIndex" ,required =true ) Integer pageIndex  , @RequestParam(value="pageSize" ,required =false ,defaultValue="10" ) Integer pageSize) {
        return skThumbsUpNoticeServiceImpl.noticeList(pageIndex,pageSize);
    }
}
