package com.geek.shengka.user.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

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
        return System.currentTimeMillis() + getRandomString(length);
    }

    public static final String ALLStr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_.";
    public static final String ALLNum = "0123456789";

    /**
     * 返回一个定长的随机字符串(只包含大小写字母、数字)
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public static String getRandomString(int length) {
        return generate(length, ALLStr);
    }


    public static String getRandomNumber(int length) {
        return generate(length, ALLNum);
    }

    public static String generate(int length, String data) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int charLength = data.length();
        for (int i = 0; i < length; i++) {
            sb.append(data.charAt(random.nextInt(10000) % charLength));
        }
        return sb.toString();
    }

    public static boolean checkSkCode(String skCode) {
        char[] skCodeArr = skCode.toCharArray();
        String data = ALLStr+ALLNum;
        for (char c : skCodeArr) {
            if (data.indexOf(String.valueOf(c)) == -1) {
                return false;
            }
        }
        return true;
    }
}
