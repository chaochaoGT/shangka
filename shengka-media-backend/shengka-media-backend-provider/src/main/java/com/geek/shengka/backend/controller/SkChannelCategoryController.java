package com.geek.shengka.backend.controller;

import com.geek.shengka.backend.entity.SkChannelCategory;
import com.geek.shengka.backend.service.SkChannelCategoryService;
import com.geek.shengka.backend.util.DataResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author qubianzhong
 * @date 2019/7/31 17:23
 */
@RestController
@RequestMapping(value = "/channelCategory")
@Api(tags = "渠道频道关联")
public class SkChannelCategoryController extends BaseController{

    @Autowired
    private SkChannelCategoryService skChannelCategoryService;


    @GetMapping(value = "/list")
    @ApiOperation(value = "列表")
    public DataResult<SkChannelCategory> list() {
        return DataResult.ok();
    }

    @PostMapping(value = "/add")
    @ApiOperation(value = "新增")
    public DataResult<SkChannelCategory> add() {
        return DataResult.ok();
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "更新")
    public DataResult<SkChannelCategory> update() {
        return DataResult.ok();
    }

    @DeleteMapping(value = "/delete")
    @ApiOperation(value = "删除")
    public DataResult<SkChannelCategory> delete() {
        return DataResult.ok();
    }
}
