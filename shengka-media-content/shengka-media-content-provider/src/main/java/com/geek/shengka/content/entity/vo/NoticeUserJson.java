package com.geek.shengka.content.entity.vo;

/**
 * @Filename: SuperVO
 * @Description: 可以装载任意实体类之任意属性，可以用于装载多表连接查询返回的复合数据
 * @Version: 1.0
 * @Author:
 * @Email:
 * @History:<br> aaa
 * <li>Author: chao.wang</li>
 * <li>Date: 2018/11/19</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
import java.io.Serializable;
import java.util.TreeMap;

import lombok.Data;

@Data
public class NoticeUserJson  implements Serializable{

    private static final long serialVersionUID = 1L;
 
    private long userId;
    private String nickName;
}
