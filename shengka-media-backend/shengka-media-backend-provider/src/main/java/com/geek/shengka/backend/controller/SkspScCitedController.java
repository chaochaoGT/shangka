package com.geek.shengka.backend.controller;

import com.geek.shengka.backend.entity.SkspScCited;
import com.geek.shengka.backend.params.req.SkspScCitedDTO;
import com.geek.shengka.backend.params.req.SkspScCitedQueryDTO;
import com.geek.shengka.backend.service.SkspScCitedService;
import com.geek.shengka.backend.util.DataResult;
import com.geek.shengka.backend.util.PageVO;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 市场分包
 *
 * @author: yunfei
 * @create: 2019-08-26 10:31
 **/
@Api(tags = "市场分包消耗表")
@RequestMapping("/cited")
@RestController
public class SkspScCitedController extends BaseController {

    @Autowired
    private SkspScCitedService skspScCitedService;


    /**
     *新增
     *
     * @return
     */
    @PostMapping(value = "/add")
    public DataResult<Integer> add(@RequestBody SkspScCitedDTO skspScCitedDTO) {
        skspScCitedDTO.setCreateBy(getUserId());
        skspScCitedDTO.setUpdateBy(getUserId());
        skspScCitedDTO.setCreateTime(new Date());
        return DataResult.ok(skspScCitedService.add(skspScCitedDTO));
    }

    /**
     * 修改
     *
     * @return
     */
    @PutMapping(value = "/update")
    public DataResult<Boolean> update(@RequestBody SkspScCitedDTO skspScCitedDTO) {
        skspScCitedDTO.setUpdateBy(getUserId());
        return DataResult.ok(skspScCitedService.update(skspScCitedDTO) > 0);
    }

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/get/{id}")
    public DataResult get(@PathVariable("id") Long id) {
        return DataResult.ok(skspScCitedService.get(id));
    }
    
    

    /**
     * 查询详情
     *
     * @param param
     * @return
     */
    @PostMapping(value = "/getList")
    public DataResult<PageVO<SkspScCited>> getList(@RequestBody SkspScCitedQueryDTO param) {
        return DataResult.ok(skspScCitedService.getList(param));
    }
    
}
