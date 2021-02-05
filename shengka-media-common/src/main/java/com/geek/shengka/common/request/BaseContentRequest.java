package com.geek.shengka.common.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Filename: BaseContentRequest
 * @Description:
 * @Version: 1.0
 * @Author: wangchao
 * @Email: wangchao@hellogeek.com
 * @date: 2019/8/20 ;
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseContentRequest implements Serializable {
    // 当前页数
    private Integer pageNo;
    // 每页的数据条数
    private Integer pageSize;
    /**0横屏 1 竖屏*/
    private String watchMode;
    /**单个categoryId*/
    private String categoryCode;
    /**多个categoryIds*/
    private List<String> categoryCodes;
    /**多个videoId*/
    private List<String> videoIds;
    /**上页最后一个视频的索引*/
    private String indexId;
    /**用户id*/
    private Long userId;

}
