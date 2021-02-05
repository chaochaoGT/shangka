package com.geek.shengka.common.sign;

import org.apache.commons.lang3.StringUtils;

/**
 *   签名及验证
 * @author jiangxinqiang
 * @date 2019-03-09
 */
public class CaptchaUtil {

	/**
	 * 根据token生成签名
	 * 
	 * @param token
	 * @return
	 */
	public static String captcha(String token) {
		if (StringUtils.isBlank(token)) {
			return null;
		}

		// 确保动态密钥长度等于16
		if (token.length() < AESUtil.SECRET_LENGTH) {
			token = StringUtils.rightPad(token, AESUtil.SECRET_LENGTH, token.charAt(0));
		} else {
			token = token.substring(0, AESUtil.SECRET_LENGTH);
		}

		return AESUtil.encrypt(AESUtil.secret(token), token);
	}

	/**
	 * 校验token和sign是否匹配
	 * 进行合法认证
	 * 
	 * @param token
	 * @return
	 */
	public static boolean checkCaptcha(String token, String sign) {
		if (StringUtils.isBlank(token) || StringUtils.isBlank(sign)) {
			return false;
		}

		if (sign.equalsIgnoreCase(captcha(token))) {
			return true;
		}

		return false;
	}

	public static void main(String... args) throws Exception {

		// UUID.randomUUID().toString();
		String token = "a557";
		System.out.println(StringUtils.rightPad(token, AESUtil.SECRET_LENGTH, token.charAt(0)));

		String sign = captcha(token);
		System.out.println("token:[" + token + "], sign:[" + sign + "]");

		System.out.println("check sign :[" + checkCaptcha(token, sign) + "]");

	}

}
