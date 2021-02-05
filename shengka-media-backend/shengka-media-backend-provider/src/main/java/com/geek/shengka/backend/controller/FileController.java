package com.geek.shengka.backend.controller;

import com.geek.shengka.backend.util.AliyunOSSClientUtil;
import com.geek.shengka.backend.util.DataResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author qubianzhong
 * @date 2019/6/7 17:07
 */
@RestController()
@RequestMapping(value = "/file")
@Api(tags = "上传文件")
public class FileController extends BaseController {

    /**
     * 文件上传
     *
     * @param multipartFile 上传文件
     * @return
     */
    @ApiOperation(value = "上传文件")
    @PostMapping(value = "/upload")
    public DataResult<String> fileUpload(@RequestParam(value = "file") MultipartFile multipartFile,
                                         @RequestParam(value = "fullPath", required = false, defaultValue = "true") Boolean fullPath) {
        return AliyunOSSClientUtil.uploadPub(multipartFile, fullPath);
    }

    /**
     * 文件多个上传
     *
     * @param multipartFiles 上传多个文件
     * @return
     */
    @ApiOperation(value = "上传多个文件")
    @PostMapping(value = "/uploads")
    public DataResult<List<String>> fileUploads(@RequestParam(value = "files") MultipartFile[] multipartFiles,
                                                @RequestParam(value = "fullPath", required = false, defaultValue = "true") Boolean fullPath) {
        return AliyunOSSClientUtil.uploadPubs(multipartFiles, fullPath);
    }
}
