package com.geek.shengka.backend.util;

import com.geek.shengka.backend.entity.InvalidFailMsg;

/**
 * @Author: zzy
 * @Date: 2019/8/14 10:21
 * @Version 1.0
 */
public class InvalidMsgUtils {

    /**
     * 组装返回语句
     * @param invalidFailMsg
     * @param msg
     * @return
     */
    public static String result(InvalidFailMsg invalidFailMsg, String msg){
        return  "《" + invalidFailMsg.getSheetName() + "》 表中第 <"
                + invalidFailMsg.getRowNum() + "> 行，第 <" + invalidFailMsg.getCellNum()
                + "> 列验证失败，错误原因 <" + msg + ">";
    }

}
