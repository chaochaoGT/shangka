package com.geek.shengka.content.request;

import com.geek.shengka.common.basemodel.BaseHeadersRequest;
import lombok.Data;

/**
 * @Filename: SkCategoryRequest
 * @Description:
 * @Version: 1.0
 * @Author: wangchao
 * @Email: wangchao@hellogeek.com
 * @date: 2019/7/31 ;
 */
@Data
public class SkCategoryRequest extends BaseHeadersRequest {
    /**频道id*/
    private Long categoryId;
    /**上页最后一个视频的索引*/
    private String indexId;

}
