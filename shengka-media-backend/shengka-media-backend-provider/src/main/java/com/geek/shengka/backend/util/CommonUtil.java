package com.geek.shengka.backend.util;

import com.alibaba.fastjson.JSONObject;
import com.geek.shengka.backend.constant.CommonConstant;

import java.util.Random;

/**
 * @author qubianzhong
 * @date 2019/6/4 21:16
 */
public class CommonUtil {
    private CommonUtil() {
    }

    /**
     * 判断此字符串是否是JSON
     *
     * @param str str
     * @return boolean
     * @author qubianzhong
     * @Date 21:15 2019/6/4
     */
    public static boolean isJSON(String str) {
        try {
            JSONObject.parseObject(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    public static String getRandomStr(int length) {
        return System.currentTimeMillis() + generateString(length);
    }

    public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 返回一个定长的随机字符串(只包含大小写字母、数字)
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    private static String generateString(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
        }
        return sb.toString();
    }

    /**
     * 获取全路径
     *
     * @param path
     * @return java.lang.String
     * @author qubianzhong
     * @Date 17:11 2019/8/21
     */
    public static String getFullPath(String path) {
        return OSSConfig.httpTitle + OSSConfig.backetname + CommonConstant.POINT + OSSConfig.endpoint + CommonConstant.SLASH + path;
    }
}
