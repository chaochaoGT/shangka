package com.geek.shengka.backend.controller;


import com.geek.shengka.backend.constant.ErrorCode;
import com.geek.shengka.backend.exception.BaseException;
import com.geek.shengka.backend.service.proxy.SkMarketServiceProxy;
import com.geek.shengka.backend.util.DataResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


/**
 * @author qubianzhong
 * @Date 10:30 2019/8/29
 */
@RestController
@Slf4j
@Api(tags = "导入Excel")
public class ExcelController extends BaseController {

    @Autowired
    private SkMarketServiceProxy skMarketServiceProxy;

    /**
     * 上传文件的接口
     *
     * @param excelFile
     * @return
     */
    @PostMapping(value = "/excel/import")
    @ApiOperation(value = "上传excel")
    public DataResult<List<String>> excelImport(@RequestParam(value = "excelFile") MultipartFile excelFile) throws BaseException {
        if (excelFile == null) {
            throw new BaseException(ErrorCode.FILE_IS_NULL.getCode(), ErrorCode.FILE_IS_NULL.getMsg());
        }
        //读取input流
        try {
            InputStream fis = excelFile.getInputStream();
            List<String> list = skMarketServiceProxy.parseExcel(fis, getUserId());
            if (!CollectionUtils.isEmpty(list)) {
                return DataResult.fail(list, ErrorCode.PARAM_ERROR.getCode(), ErrorCode.PARAM_ERROR.getMsg());
            }
        } catch (IOException e) {
            log.error(e.toString());
            return DataResult.fail(ErrorCode.FILE_PARSE_FAIL.getCode(), ErrorCode.FILE_PARSE_FAIL.getMsg());
        }
        return DataResult.ok();
    }

}
