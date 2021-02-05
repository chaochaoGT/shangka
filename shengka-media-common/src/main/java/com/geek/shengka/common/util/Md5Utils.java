package com.geek.shengka.common.util;

/**
 * Author: cuihuayang
 * Date: 2019/6/10 09:42
 * Description:
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Utils {
    private static final Logger logger = LoggerFactory.getLogger(Md5Utils.class);

    public Md5Utils() {
    }

    public static String getMD5(String sourceStr) throws NoSuchAlgorithmException {
        String resultStr = "";
        byte[] temp = sourceStr.getBytes();
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(temp);
        byte[] b = md5.digest();

        for(int i = 0; i < b.length; ++i) {
            char[] digit = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
            char[] ob = new char[]{digit[b[i] >>> 4 & 15], digit[b[i] & 15]};
            resultStr = resultStr + new String(ob);
        }

        return resultStr;
    }
}