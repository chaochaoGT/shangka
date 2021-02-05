package com.geek.shengka.common.proxy;

import com.alibaba.fastjson.JSON;
import com.geek.shengka.common.http.HttpPoolProxy;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractProxy {

	private static final Logger logger = LoggerFactory.getLogger(AbstractProxy.class);

	protected static final String LAOWANGSHIPIN = "shengka";

	
	
	
	/***
	 *     用户中心 
	 * @param url
	 * @param jsonData
	 * @param rClass
	 * @param          <R>
	 * @return
	 */
	public final static <R> R postJsonCallUserCenter(String url, String jsonData, Class<R> rClass,
			Map<String, String> header) {
		R result = null;
		try {
			String response = HttpPoolProxy.postJson(url, jsonData, 2000, 2000, 2000, header);

			if (StringUtils.isBlank(response)) {
				return null;
			}
			result = JSON.parseObject(response, rClass);

		} catch (Exception e) {
			logger.info(e.getMessage(), e);
		}
		return result;
	}

	
	
	
	
	
	/***
	 *     账务中心 
	 * @param url
	 * @param jsonData
	 * @param rClass
	 * @param          <R>
	 * @return
	 */
	public final static <R> R postJsonCallAccount(String url, String jsonData, Class<R> rClass) {
		return postJsonCallAccount(url, jsonData, rClass, null);
	}

	

	
	/***
	 * 
	 *    账务中心 
	 * @param url
	 * @param jsonData
	 * @param rClass
	 * @param          <R>
	 * @return
	 */
	public final static <R> R postJsonCallAccount(String url, String jsonData, Class<R> rClass, String timestamp) {
		R result = null;
		try {
			if (StringUtils.isBlank(timestamp)) {
				timestamp = String.valueOf(System.currentTimeMillis());
			}
			Map<String, String> headers = new HashMap<>(2);
			headers.put("bizCode", LAOWANGSHIPIN);
			headers.put("timestamp", timestamp);
			String response = HttpPoolProxy.postJson(url, jsonData, 2000, 2000, 2000, headers);
			if (StringUtils.isBlank(response)) {
				return null;
			}
			result = JSON.parseObject(response, rClass);
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
		}
		return result;
	}
	
	

}
