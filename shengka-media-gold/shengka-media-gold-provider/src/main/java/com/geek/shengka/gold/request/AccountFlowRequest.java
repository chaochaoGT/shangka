package com.geek.shengka.gold.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: yunfei
 * @create: 2019-06-06 20:28
 **/
@Data
@Builder
public class AccountFlowRequest {
    /**
     * {
     *  "userId":"xxddfdfdf",
     *  "business":"1",
     *  "accountTypeList":[100001,200001]
     * }
     */

    /**
     * 用户id
     */
    private String userId;

    /**
     * 账户类型 0：人民币账户，1金币账户
     */
    private int accountType;

    /**
     * 第几页
     */
    private int pageNo;

    /**
     * 每页条数
     */
    private int pageSize;


}
