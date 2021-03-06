package com.geek.shengka.common.util;


import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class RSACoder {

    //非对称密钥算法
    public static final String KEY_ALGORITHM = "RSA";
    //公钥
    public static final String PUBLIC_KEY = "RSAPublicKey";
    //私钥
    public static final String PRIVATE_KEY = "RSAPrivateKey";
    /**
     * 密钥长度，DH算法的默认密钥长度是1024
     * 密钥长度必须是64的倍数，在512到65536位之间
     */
    private static final int KEY_SIZE = 512;

    /**
     * 初始化密钥对
     *
     * @return Map 甲方密钥的Map
     */
    public static Map<String, Object> initKey() throws Exception {
        //实例化密钥生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        //初始化密钥生成器
        keyPairGenerator.initialize(KEY_SIZE);
        //生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //甲方公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        //甲方私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        //将密钥存储在map中
        Map<String, Object> keyMap = new HashMap<String, Object>();
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;

    }

    /**
     * 返回秘钥以及公钥
     *
     * @return
     * @throws Exception
     */
    public static Map<String, String> getInitKey() throws Exception {
        Map<String, Object> keyMap = initKey();
        Map<String, String> strMap = new HashMap<>(4);
        strMap.put(PUBLIC_KEY, Base64.encodeBase64String(getPublicKey(keyMap)));
        strMap.put(PRIVATE_KEY, Base64.encodeBase64String(getPrivateKey(keyMap)));
        return strMap;
    }

    /**
     * 私钥加密
     *
     * @param data 待加密数据
     * @param key  密钥
     * @return byte[] 加密数据
     */
    public static byte[] encryptByPrivateKey(byte[] data, byte[] key) throws Exception {

        //取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //生成私钥
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        //数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 私钥加密
     *
     * @param data       待加密数据
     * @param privateKey 私钥
     * @return 加密数据
     * @throws Exception
     */
    public static String encryptByPrivateKey(String data, String privateKey) throws Exception {
        byte[] dataArr = data.getBytes("UTF-8");
        byte[] privateKeyArr = Base64.decodeBase64(privateKey);
        return Base64.encodeBase64String(encryptByPrivateKey(dataArr, privateKeyArr));
    }

    /**
     * 公钥加密
     *
     * @param data 待加密数据
     * @param key  密钥
     * @return byte[] 加密数据
     */
    public static byte[] encryptByPublicKey(byte[] data, byte[] key) throws Exception {

        //实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //初始化公钥
        //密钥材料转换
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
        //产生公钥
        PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);

        //数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return cipher.doFinal(data);
    }

    /**
     * 公钥加密
     *
     * @param data      待加密数据
     * @param publicKey 公钥
     * @return 加密数据
     * @throws Exception
     */
    public static String encryptByPublicKey(String data, String publicKey) throws Exception {
        byte[] dataArr = data.getBytes("UTF-8");
        byte[] publicKeyArr = Base64.decodeBase64(publicKey);
        System.out.println("=====》》》》》"+encryptByPublicKey(dataArr, publicKeyArr));
        return Base64.encodeBase64String(encryptByPublicKey(dataArr, publicKeyArr));
    }

    /**
     * 私钥解密
     *
     * @param data 待解密数据
     * @param key  密钥
     * @return byte[] 解密数据
     */
    public static byte[] decryptByPrivateKey(byte[] data, byte[] key) throws Exception {
        //取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //生成私钥
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        //数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 私钥解密
     *
     * @param data       待解密字符串
     * @param privateKey 秘钥字符串
     * @return 解密后的字符串
     * @throws Exception
     */
    public static String decryptByPrivateKey(String data, String privateKey) throws Exception {
        byte[] dataArr = Base64.decodeBase64(data);
        byte[] privateKeyArr = Base64.decodeBase64(privateKey);
        return new String(decryptByPrivateKey(dataArr, privateKeyArr), "UTF-8");
    }

    /**
     * 公钥解密
     *
     * @param data 待解密数据
     * @param key  密钥
     * @return byte[] 解密数据
     */
    public static byte[] decryptByPublicKey(byte[] data, byte[] key) throws Exception {

        //实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //初始化公钥
        //密钥材料转换
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
        //产生公钥
        PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);
        //数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, pubKey);
        return cipher.doFinal(data);
    }

    /**
     * 公钥解密
     *
     * @param data      待解密字符串
     * @param publicKey 公钥字符串
     * @return 解密后的字符串
     * @throws Exception
     */
    public static String decryptByPublicKey(String data, String publicKey) throws Exception {
        byte[] dataArr = Base64.decodeBase64(data);
        byte[] publicKeyArr = Base64.decodeBase64(publicKey);
        return new String(decryptByPublicKey(dataArr, publicKeyArr), "UTF-8");
    }

    /**
     * 取得私钥
     *
     * @param keyMap 密钥map
     * @return byte[] 私钥
     */
    public static byte[] getPrivateKey(Map<String, Object> keyMap) {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return key.getEncoded();
    }

    /**
     * 取得公钥
     *
     * @param keyMap 密钥map
     * @return byte[] 公钥
     */
    public static byte[] getPublicKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return key.getEncoded();
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {


        /***
         * pub======>>>>>>>MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAMC99GAI1Y/kmjJSjue/O8ZZ2//mHkU/B4XKMJxcTQhX3/SKcQoognY7f4NM84Vyhpa16npiaUZ1JEHFPdtQoh8CAwEAAQ==
         * priv======>>>>>>>MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAwL30YAjVj+SaMlKO5787xlnb/+YeRT8HhcownFxNCFff9IpxCiiCdjt/g0zzhXKGlrXqemJpRnUkQcU921CiHwIDAQABAkB7tK93P9RKQZASQljEN0IXQa9pI/pDuXuLjET85mTut9JuiaCQjGAO+PTrhgQVlmT7SaRCc3948XdwZ9xycephAiEA6IlZDhupWcAr2xT/A4u2EpaC0DMXRfDuehIOEfitls8CIQDUMKyz24hjUXtipjfJTosMeM9yA+jamjbX2FwZi8YTsQIgCdT82fbbczsH36yEl87JdQG+KDVwz3k/bBPlWmcsV8sCIQCILaTo2R+y/fw914c9/tZmbrJT7gZnJUnFROn2yymYQQIgRikVEsYjFHsYYkb2wT10Xci5BWZ0EJuYdWCBT5nVuus=
         * 加密后dev===a/UHQG7Abj83TILITu2C1fTfeCwtijMhUJ7l38blvz5xZAxJBbxew+mrDuZd9G/KhqCHE7pPaYRxiqGU22WfDg==
         * 解密后publicDev===dev
         */
        test3();
//        Map<String, String> initKey = RSACoder.getInitKey();
//        System.out.println("pub======>>>>>>>"+initKey.get(PUBLIC_KEY));
//        System.out.println("priv======>>>>>>>"+initKey.get(PRIVATE_KEY));
//
//        String privateKey = initKey.get(PRIVATE_KEY);
//        String dev = encryptByPrivateKey("dev", privateKey);
//        System.out.println("加密后dev==="+dev);
//        String pub = initKey.get(PUBLIC_KEY);
//
//        String publicDev = decryptByPublicKey(dev, pub);
//        System.out.println("解密后publicDev==="+publicDev);


    }

    private static void test3() throws Exception {
        String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAMC99GAI1Y/kmjJSjue/O8ZZ2//mHkU/B4XKMJxcTQhX3/SKcQoognY7f4NM84Vyhpa16npiaUZ1JEHFPdtQoh8CAwEAAQ==";
        String privateKey = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAwL30YAjVj+SaMlKO5787xlnb/+YeRT8HhcownFxNCFff9IpxCiiCdjt/g0zzhXKGlrXqemJpRnUkQcU921CiHwIDAQABAkB7tK93P9RKQZASQljEN0IXQa9pI/pDuXuLjET85mTut9JuiaCQjGAO+PTrhgQVlmT7SaRCc3948XdwZ9xycephAiEA6IlZDhupWcAr2xT/A4u2EpaC0DMXRfDuehIOEfitls8CIQDUMKyz24hjUXtipjfJTosMeM9yA+jamjbX2FwZi8YTsQIgCdT82fbbczsH36yEl87JdQG+KDVwz3k/bBPlWmcsV8sCIQCILaTo2R+y/fw914c9/tZmbrJT7gZnJUnFROn2yymYQQIgRikVEsYjFHsYYkb2wT10Xci5BWZ0EJuYdWCBT5nVuus=";
        // dev
        String data = encryptByPublicKey("dev", publicKey);
        System.out.println(data);
        System.out.println(decryptByPrivateKey(data, privateKey));

        //group  A0001
        String group = encryptByPublicKey("A0001", publicKey);
        System.out.println("group私钥加密后："+group);
        System.out.println("group私钥解密后："+decryptByPrivateKey(group,privateKey));

        //platform-id   LAOWANG001
        String platformId = encryptByPublicKey("LAOWANG001", publicKey);
        System.out.println("platform-id私钥加密后："+platformId);
        System.out.println("platform-id私钥解密后："+decryptByPrivateKey(platformId,privateKey));

    }

    private static void test2() throws Exception {

        String privateKey = "MIIBVgIBADANBgkqhkiG9w0BAQEFAASCAUAwggE8AgEAAkEAgi/ZSo7VW58ilvGiTB1D14/0wsuZ42zGUNmqyjOU90M1+HPQcV6EE8Xfz0AGrqy3MvQ6YpWx7MHrDEAdh+NsWQIDAQABAkAD/aWClJhTw7ASwnuAK9F9lSsioY0pHJeDuPh0VFp3reuO2g+bM0YgyfHuKPNlSJIyNJnL84lrQ8jtUif2PGYVAiEAwQR3gELQwe7g4jfr82mNFdiw6ZgZfH8o6yy+OUJu0ncCIQCsquQr1Pz1vm8oI2KolYiyM40YZeczKOOFBTcOZkkbrwIhAKjkALCxRjs7i3lGV2aQ6EvCZOKxHTPrjGFjsDsoHxJ3AiEAglLFvxOWDgE7MsdqeqXKs/3W2/hmBaYx+7+Q64xmV78CIQCg8Pm9T1i6Yn7P7z6rJYcOnkqzx7SJmu5BSP/9UTuM1g==";
        String data = "fQmC0H0EblHHZxTZz73sf7ckc6ejuUUMVcs4KQXZWyGyIQI7xqrfhQAqq2roLv2Nn0Bo/gr4Drf1rFKos+OF+w==";
        String decreptedByPublicKey = decryptByPrivateKey(data, privateKey);

        System.out.println(decreptedByPublicKey);

//        Map<String, String> keyMap = RSACoder.getInitKey();
//        System.out.println("公钥: " + keyMap.get(PUBLIC_KEY));
//        System.out.println("私钥: " + keyMap.get(PRIVATE_KEY));
//
//        String data = "1_123457891112_100000";
//        System.out.println("原文: " + data);
//
//        //甲方私钥进行数据的加密
//        String encreptedByPrivateKey = encryptByPrivateKey(data, keyMap.get(PRIVATE_KEY));
//        System.out.println("甲方使用私钥加密后的字符串: " + encreptedByPrivateKey);
//
//        //乙方使用公钥对数据进行解密
//        String decreptedByPublicKey = decryptByPublicKey(encreptedByPrivateKey, keyMap.get(PUBLIC_KEY));
//        System.out.println("乙方使用公钥解密后的字符串: " + decreptedByPublicKey);
//
//        System.out.println("===========反向进行操作，乙方向甲方发送数据==============/n/n");
//
//        data = "1_123457891112_100002";
//
//        System.out.println("原文:" + data);
//        //乙方使用公钥对数据进行加密
//        String encreptedByPublicKey = encryptByPublicKey(data, keyMap.get(PUBLIC_KEY));
//        System.out.println("乙方使用公钥加密后的字符串: " + encreptedByPublicKey);
//
//        //甲方使用私钥对数据进行解密
//        String decreptedByPrivateKey = decryptByPrivateKey(encreptedByPublicKey, keyMap.get(PRIVATE_KEY));
//        System.out.println("甲方使用私钥解密后的字符串: " + decreptedByPrivateKey);
    }

    private static void test1() throws Exception {
        //初始化密钥
        //生成密钥对
        Map<String, Object> keyMap = RSACoder.initKey();
        //公钥
        byte[] publicKey = RSACoder.getPublicKey(keyMap);

        //私钥
        byte[] privateKey = RSACoder.getPrivateKey(keyMap);
        byte[] publicKeyArr = Base64.decodeBase64(Base64.encodeBase64String(publicKey));
        System.out.println("length is same: " + (publicKey.length == publicKeyArr.length));
        for (int i = 0; i < publicKey.length; i++) {
            if (publicKey[i] != publicKeyArr[i])
                System.out.println("秘钥不一致....");
        }
        System.out.println("公钥：/n" + Base64.encodeBase64String(publicKey));
        System.out.println("私钥：/n" + Base64.encodeBase64String(privateKey));

        System.out.println("================密钥对构造完毕,甲方将公钥公布给乙方，开始进行加密数据的传输=============");
        String str = "RSA密码交换算法";
        System.out.println("/n===========甲方向乙方发送加密数据==============");
        System.out.println("原文:" + str);
        //甲方进行数据的加密
        byte[] code1 = RSACoder.encryptByPrivateKey(str.getBytes(), privateKey);
        System.out.println("加密后的数据：" + Base64.encodeBase64String(code1));
        System.out.println("===========乙方使用甲方提供的公钥对数据进行解密==============");
        //乙方进行数据的解密
        byte[] decode1 = RSACoder.decryptByPublicKey(code1, publicKey);
        System.out.println("乙方解密后的数据：" + new String(decode1) + "/n/n");

        System.out.println("===========反向进行操作，乙方向甲方发送数据==============/n/n");

        str = "乙方向甲方发送数据RSA算法";

        System.out.println("原文:" + str);

        //乙方使用公钥对数据进行加密
        byte[] code2 = RSACoder.encryptByPublicKey(str.getBytes(), publicKey);
        System.out.println("===========乙方使用公钥对数据进行加密==============");
        System.out.println("加密后的数据：" + Base64.encodeBase64String(code2));

        System.out.println("=============乙方将数据传送给甲方======================");
        System.out.println("===========甲方使用私钥对数据进行解密==============");

        //甲方使用私钥对数据进行解密
        byte[] decode2 = RSACoder.decryptByPrivateKey(code2, privateKey);

        System.out.println("甲方解密后的数据：" + new String(decode2));
    }
}
