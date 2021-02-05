package com.geek.shengka.common.sign;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.UUID;

/**
 * AES加密
 * @author jiangxinqiang
 * @date 2019-03-09
 *
 */
public class AESUtil {

	// 加密算法
	private static final String ALG = "AES/CBC/PKCS5Padding";
	// 密钥正规化算法
	private static final String SEC_NORMALIZE_ALG = "AES";
	// 字符编码
	private static final String ENCODE_TYPE = "UTF-8";
	// 加解密向量
	//private static final String iv = "0102030405060708";
	private static final String iv = "0102030405060708";
	//密钥长度要求
	public static final int SECRET_LENGTH = 16;
	// default 密钥
	private static final String SECRET = "geeker_geekinfo1";
	
	
	/**
	 *   从token动态确定secret
	 * @param token
	 * @return
	 */
	public static String secret(String token) {
		//确保token长度大于16
		if(token.length() < AESUtil.SECRET_LENGTH) {
			token = StringUtils.rightPad(token, AESUtil.SECRET_LENGTH , token.charAt(0));
		}else {
			token =  token.substring(0, AESUtil.SECRET.length());
		}
		
		return token;
		
	}
	
	

	/**
	 * AES加密
	 * 
	 * @param key  加密需要的KEY
	 * @param data 需要加密的数据
	 * @return
	 */
	public static String encrypt(String key, String data) {
		String encrypted = null;
		if (key != null && data != null) {
			byte[] encryptedBytes = {};
			byte[] keyBytes = key.getBytes();
			SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, AESUtil.SEC_NORMALIZE_ALG);
			try {
				Cipher cipher = Cipher.getInstance(AESUtil.ALG);
				cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec(iv.getBytes()));
				byte[] dataBytes = data.getBytes();
				encryptedBytes = cipher.doFinal(dataBytes);
				encrypted = new String(Base64.encodeBase64(encryptedBytes), AESUtil.ENCODE_TYPE);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return encrypted;
	}

	/**
	 * 
	 * @param key  解密需要的KEY 同加密
	 * @param data 需要解密的数据
	 * @return
	 */
	public static String decrypt(String key, String encryptData) {
		String content = "";
		byte[] keyBytes = key.getBytes();
		SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, AESUtil.SEC_NORMALIZE_ALG);
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance(AESUtil.ALG);
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(iv.getBytes()));// 初始化
			byte[] dataBytes = Base64.decodeBase64(encryptData);
			byte[] data = cipher.doFinal(dataBytes);
			content = new String(data, AESUtil.ENCODE_TYPE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}

	/**
	 * 加密
	 */
	public static String encrypt(String data) {
		return encrypt(AESUtil.SECRET, data);
	}

	/**
	 * 解密
	 */
	public static String decrypt(String encryptData) {
		return decrypt(AESUtil.SECRET, encryptData);
	}

	/**
	 encrypted:	XBF9DnAJl9TGEKPxGG/4bw==
	 XBF9DnAJl9TGEKPxGG%2F4bw%3D%3D
	 decrypt:	17075577887

	 * @param args
	 * @throws Exception
	 */
	public static void main(String... args) throws Exception {

		String text = "17075577887";// 需要加密的数据原文
		String uuid = UUID.randomUUID().toString();
		System.out.println("uuid:\t"+uuid);
		String secretV = secret(uuid);
		
		String encrypted = encrypt(secretV, text);
		System.out.println("encrypted:\t"+encrypted);
		
		System.out.println("decrypt:\t" + decrypt(secretV, encrypted));

	}
}
