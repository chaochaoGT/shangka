package com.geek.shengka.user.entity.vo;

import com.geek.shengka.user.entity.SkTopicConfig;
import lombok.Data;

/**
 * @Filename: SkTopicConfigVO
 * @Description:
 * @Version: 1.0
 * @Author: wangchao
 * @Email: wangchao@hellogeek.com
 * @date: 2019/8/11 ;
 */
@Data
public class SkTopicConfigVO extends SkTopicConfig {
    //订阅数
    private Integer subscribeTopicNum;

    //发布数
    private Integer pushCounts;

}
