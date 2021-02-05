package com.geek.shengka.backend.controller;

import com.geek.shengka.backend.annotation.SkCategoryAddCheck;
import com.geek.shengka.backend.annotation.SkCategoryUpdateCheck;
import com.geek.shengka.backend.exception.BaseException;
import com.geek.shengka.backend.params.req.SkCategoryAddReqParam;
import com.geek.shengka.backend.params.req.SkCategoryListReqParam;
import com.geek.shengka.backend.params.req.SkCategoryUpdateReqParam;
import com.geek.shengka.backend.params.res.SkCategoryInfoResParam;
import com.geek.shengka.backend.params.res.SkContentCenterCategorysResParam;
import com.geek.shengka.backend.service.SkCategoryService;
import com.geek.shengka.backend.util.DataResult;
import com.geek.shengka.backend.util.DataResultUtils;
import com.geek.shengka.backend.util.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author qubianzhong
 * @date 2019/7/31 17:22
 */
@RestController
@RequestMapping(value = "/category")
@Api(tags = "频道")
@Validated
public class SkCategoryController extends BaseController {

    @Autowired
    private SkCategoryService skCategoryService;


    @GetMapping(value = "/info/{id}")
    @ApiOperation(value = "频道的详情")
    public DataResult<SkCategoryInfoResParam> info(@PathVariable(value = "id") Long id) {
        return DataResult.ok(skCategoryService.info(id));
    }


    @GetMapping(value = "/list")
    @ApiOperation(value = "列表")
    public DataResult<PageVO<SkCategoryInfoResParam>> list(SkCategoryListReqParam param) {
        return DataResult.ok(skCategoryService.list(param));
    }

    @GetMapping(value = "/categories")
    @ApiOperation(value = "内容中心视频分类列表")
    public DataResult<List<SkContentCenterCategorysResParam>> categories() throws BaseException {
        return DataResult.ok(skCategoryService.categories());
    }

    @PostMapping(value = "/add")
    @ApiOperation(value = "新增")
    public DataResult<Boolean> add(@SkCategoryAddCheck @RequestBody SkCategoryAddReqParam add) {
        add.setCreateBy(getUserId());
        return DataResultUtils.add(skCategoryService.add(add));
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "更新")
    public DataResult<Boolean> update(@SkCategoryUpdateCheck @RequestBody SkCategoryUpdateReqParam update) {
        update.setUpdateBy(getUserId());
        return DataResultUtils.add(skCategoryService.update(update));
    }

    @DeleteMapping(value = "/delete/{id}")
    @ApiOperation(value = "删除")
    public DataResult<Boolean> delete(@PathVariable(value = "id") Long id) {
        return DataResultUtils.delete(skCategoryService.delete(id));
    }
}
