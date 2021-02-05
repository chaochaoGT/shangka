package com.geek.shengka.content.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * sk_black
 * @author 
 */
@Entity
@Table(name="sk_black")
@Data
public class SkBlack implements Serializable {
    /**
     * 主键
     */
	@Id
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 黑名单用户id
     */
    private Long blackUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

    
}