package com.geek.shengka.user.controller;

import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.user.utils.AliyunOSSClientUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author qubianzhong
 * @date 2019/8/12 10:19
 */
@RestController
@RequestMapping("/v1/file")
public class SkFileController {
    /**
     * 文件上传
     *
     * @param multipartFile 上传文件
     * @return
     */
    @PostMapping(value = "/upload")
    public BaseResponse<String> fileUpload(@RequestParam(value = "file") MultipartFile multipartFile) {
        return AliyunOSSClientUtil.uploadPub(multipartFile);
    }

    /**
     * 文件多个上传
     *
     * @param multipartFiles 上传多个文件
     * @return
     */
    @PostMapping(value = "/uploads")
    public BaseResponse<List<String>> fileUploads(@RequestParam(value = "files") MultipartFile[] multipartFiles) {
        return AliyunOSSClientUtil.uploadPubs(multipartFiles);
    }
}
