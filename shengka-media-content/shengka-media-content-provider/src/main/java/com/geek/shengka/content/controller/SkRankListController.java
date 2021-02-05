package com.geek.shengka.content.controller;

import com.geek.shengka.common.annotation.IgnoreClientToken;
import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.content.entity.SkRankList;
import com.geek.shengka.content.entity.vo.SkRankListVO;
import com.geek.shengka.content.service.SkRankListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author qubianzhong
 * @date 2019/8/17 13:58
 */
@RestController
@RequestMapping("/v1/app/rankList")
@IgnoreClientToken
public class SkRankListController {

    @Autowired
    private SkRankListService skRankListService;

    @GetMapping(value = "/list")
    public BaseResponse<List<SkRankListVO>> list() {
        return BaseResponse.newSuccess(skRankListService.list());
    }
}
