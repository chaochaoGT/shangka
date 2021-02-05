package com.geek.shengka.backend.controller;

import com.geek.shengka.backend.exception.BaseException;
import com.geek.shengka.backend.params.req.SkVoicePublishReqParam;
import com.geek.shengka.backend.params.res.SkVoiceListResParam;
import com.geek.shengka.backend.service.SkVoiceService;
import com.geek.shengka.backend.util.DataResult;
import com.geek.shengka.backend.util.DataResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author qubianzhong
 * @date 2019/8/20 17:05
 */
@RestController
@RequestMapping(value = "/voice")
@Api(tags = "语音")
@Validated
public class SkVoiceController extends BaseController {
    @Autowired
    private SkVoiceService skVoiceService;

    @PostMapping(value = "/publish")
    @ApiOperation(value = "发布语音")
    public DataResult<Boolean> publish(@RequestBody @Valid List<SkVoicePublishReqParam> body) throws BaseException {
        return DataResultUtils.add(skVoiceService.publish(body));
    }

    @GetMapping(value = "/list/{videoId}")
    @ApiOperation(value = "通过视频ID查询所有的语音列表")
    public DataResult<List<SkVoiceListResParam>> listByVideoId(@PathVariable(value = "videoId") String videoId) {
        return DataResult.ok(skVoiceService.listByVideoIdOfPgc(videoId));
    }

}
