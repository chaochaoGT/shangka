package com.geek.shengka.content.request;

import com.geek.shengka.common.basemodel.BaseHeadersRequest;
import lombok.Data;

/**
 * @Filename: SearchRequest
 * @Description:
 * @Version: 1.0
 * @Author: wangchao
 * @Email: wangchao@hellogeek.com
 * @date: 2019/8/2 ;
 */
@Data
public class SearchRequest extends BaseHeadersRequest {
    /**搜索信息*/
     private String keyWord;
    /**类型 1视频 2用户 3话题*/
    private int label;
    /**榜单id**/
    private Long meduleId;
    /**资源id**/
    private String sourceId;
    /**资源类型**/
    private String sourceType;
    /**榜单code*/
    private String moduleCode;
}
