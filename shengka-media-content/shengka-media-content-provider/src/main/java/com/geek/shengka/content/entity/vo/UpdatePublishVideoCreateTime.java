package com.geek.shengka.content.entity.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * sk_publish_video
 * @author 
 */
@Data
public class UpdatePublishVideoCreateTime implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * 主键
     */
    private String id;
    
    /**
     * 创建时间
     */
    private Date createTime;
 
}