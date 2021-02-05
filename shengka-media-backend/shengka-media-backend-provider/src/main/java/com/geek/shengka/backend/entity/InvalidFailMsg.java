package com.geek.shengka.backend.entity;

import lombok.Data;

/**
 * @Author: zzy
 * 参数校验返回类
 * @Date: 2019/8/13 20:19
 * @Version 1.0
 */
@Data
public class InvalidFailMsg {

    private String sheetName;

    private Integer rowNum;

    private Integer cellNum;

    private String cellENName;

    private String cellCNName;

    private String value;

    private String msg;

    private String result;

}
