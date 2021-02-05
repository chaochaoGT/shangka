package com.geek.shengka.user.entity.vo;

import com.geek.shengka.common.basemodel.BaseMediaInfo;
import lombok.Data;

/**
 * @Filename: SkPublishVideoVO
 * @Description:
 * @Version: 1.0
 * @Author: wangchao
 * @Email: wangchao@hellogeek.com
 * @date: 2019/8/9 ;
 */
@Data
public class SkPublishVideoVO extends BaseMediaInfo {
    private Integer enable;
    private String remark;
    private Integer conceal;
}
