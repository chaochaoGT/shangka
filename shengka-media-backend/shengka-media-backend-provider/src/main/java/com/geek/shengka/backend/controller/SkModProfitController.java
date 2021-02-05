package com.geek.shengka.backend.controller;

import com.geek.shengka.backend.params.req.SkModProfitAddReqParam;
import com.geek.shengka.backend.params.req.SkModProfitListReqParam;
import com.geek.shengka.backend.params.req.SkModProfitUpdateReqParam;
import com.geek.shengka.backend.params.res.SkModProfitResParam;
import com.geek.shengka.backend.service.SkModProfitService;
import com.geek.shengka.backend.util.DataResult;
import com.geek.shengka.backend.util.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author qubianzhong
 * @date 2019/8/26 10:35
 */
@RestController
@RequestMapping(value = "/modProfit")
@Api(tags = "收入报表")
public class SkModProfitController extends BaseController {

    @Autowired
    private SkModProfitService skModProfitService;

    /**
     * 列表
     *
     * @param param
     * @author qubianzhong
     * @Date 11:03 2019/8/26
     */
    @ApiOperation(value = "列表")
    @GetMapping(value = "/list")
    public DataResult<PageVO<SkModProfitResParam>> list(SkModProfitListReqParam param) {
        return DataResult.ok(skModProfitService.list(param));
    }

    /**
     * 新增
     *
     * @param addDTO
     * @author qubianzhong
     * @Date 11:03 2019/8/26
     */
    @ApiOperation(value = "新增")
    @PostMapping(value = "/add")
    public DataResult<Boolean> add(@RequestBody SkModProfitAddReqParam addDTO) {
        addDTO.setCreateBy(getUserId());
        return DataResult.ok(skModProfitService.add(addDTO));
    }

    /**
     * 更新
     *
     * @param updateDTO
     * @author qubianzhong
     * @Date 11:03 2019/8/26
     */
    @ApiOperation(value = "更新")
    @PostMapping(value = "/update")
    public DataResult<Boolean> update(@RequestBody SkModProfitUpdateReqParam updateDTO) {
        updateDTO.setUpdateBy(getUserId());
        return DataResult.ok(skModProfitService.update(updateDTO));
    }


    /**
     * 删除
     *
     * @param id
     * @author qubianzhong
     * @Date 11:03 2019/8/26
     */
    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/delete/{id}")
    public DataResult<Boolean> delete(@PathVariable(value = "id") Long id) {
        return DataResult.ok(skModProfitService.delete(id));
    }
}
