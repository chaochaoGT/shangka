package com.geek.shengka.backend.controller;

import com.geek.shengka.backend.service.HelloService;
import com.geek.shengka.backend.util.DataResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qubianzhong
 * @date 2019/7/31 14:44
 */
@RestController
public class HelloController extends BaseController {

    @Autowired
    private HelloService helloService;

    @ApiOperation(value = "hello world")
    @GetMapping(value = "/hello")
    public DataResult<String> hello() {
        return DataResult.ok(helloService.hello());
    }
}
