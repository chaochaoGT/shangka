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
public class AccountInfoRequest {
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

    private String business;

    /**
     * 业务类型
     */
    private List<Integer> accountTypeList;

}
