package com.geek.shengka.user.entity.vo;

import com.geek.shengka.user.entity.SkUserBaseInfo;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qubianzhong
 * @date 2019/8/7 11:33
 */
@Data
public class SkUserBaseInfoVO extends SkUserBaseInfo implements Serializable {

    /**
    * 是否关注此人：0-单粉，1-互粉，null-未关注
    */
    private Integer attention;

    /**
     * 从用户中心获取的扩展信息
     */
    private SkUserCenterExtendVO extendVO;
}
