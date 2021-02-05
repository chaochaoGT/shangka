package com.geek.shengka.user.entity.vo;

import com.geek.shengka.user.entity.SkUserBaseInfo;
import lombok.Data;

/**
 * @Filename: SkFansVO
 * @Description:
 * @Version: 1.0
 * @Author: wangchao
 * @Email: wangchao@hellogeek.com
 * @date: 2019/8/8 ;
 */
@Data
public class SkFansVO extends SkUserBaseInfo {
    /**关注状态0 已关注，1互相关注*/
    private Integer fansState;
}
