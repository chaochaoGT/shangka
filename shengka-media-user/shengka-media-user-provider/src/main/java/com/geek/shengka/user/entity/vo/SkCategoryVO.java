package com.geek.shengka.user.entity.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Filename: SkCategoryVO
 * @Description:
 * @Version: 1.0
 * @Author: wangchao
 * @Email: wangchao@hellogeek.com
 * @date: 2019/8/1 ;
 */
@Data
public class SkCategoryVO {
    /**
     * 主键
     */
    private Long id;

    /**
     * 频道名称
     */
    private String categoryName;

    /**
     * 0-禁用,1-启用
     */
    private Integer enable;

    /**
     * icon图片
     */
    private String iconUrl;

    /**
     * 排序
     */
    private Integer seq;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 是否自己的
     */
    private int isMyself;

}
