package com.geek.shengka.common.ip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.geek.shengka.common.http.HttpPoolProxy;

public class IpParserBaidu implements IpPareser {

	protected static Logger logger = LoggerFactory.getLogger(IpParserBaidu.class);

	private static IpParserBaidu INSTANCE = new IpParserBaidu();
	private IpParserBaidu(){}
	protected static IpParserBaidu instance() {
		return INSTANCE;
	}
	
	//http://api.map.baidu.com/location/ip?ak=0QDaLukGIKr22SwQKTWNxGSz&ip=182.139.133.135
	private final static String REMOTE_URL = "http://api.map.baidu.com/location/ip?ak=0QDaLukGIKr22SwQKTWNxGSz&ip=";

	@Override
	public String key() {
		return "baidu";
	}
	
	// 根据现有IP地址获取其地理位置（省份,城市等）的方法
	// http://ip.taobao.com/service/getIpInfo.php
	public String ipLoc(String ip) {

		String res = null;
		// String res = HttpUtils.doGet("http://pv.sohu.com/cityjson?ip=119.139.196.127"
		// );
		// String res =
		// HttpUtils.doGet("http://whois.pconline.com.cn/ip.jsp?ip=119.139.196.127");

		// String res =
		// HttpPoolProxy.get("http://whois.pconline.com.cn/ip.jsp?ip=119.139.196.127");
		// String res =
		// HttpUtils.doGet("http://whois.pconline.com.cn/?ip=119.139.196.127");

		/**
		 * <dt>您的IP</dt>
		 * <dd class="fz24">180.168.154.110</dd>
		 * <dt>来自</dt>
		 * <dd>上海市
		 * 电信<a href="http://tool.chinaz.com/contact" target="_blank" class="col-blue02
		 * pl5">(纠错)</a></dd>
		 * <dt>操作系统</dt>
		 * <dd>Windows 10</dd>
		 */
		try {
			res = HttpPoolProxy.get(REMOTE_URL + ip);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		/**
		 * http://ip.tool.chinaz.com/siteip post Content-Type:
		 * application/x-www-form-urlencoded
		 * 
		 * ips: 119.139.196.127 submore: 查询
		 * 
		 * 
		 */
		// return res;
		res = this.process(res);
		
		logger.info("ip-parser-key["+this.key()+"]input=["+ip+"]output=["+res+"]");
		
		return res;
	}

	/**
	 *  {"address":"CN|\u5e7f\u4e1c|\u6df1\u5733|None|CHINANET|0|0","content":{"address_detail":{"province":"\u5e7f\u4e1c\u7701","city":"\u6df1\u5733\u5e02","district":"","street":"","street_number":"","city_code":340},"address":"\u5e7f\u4e1c\u7701\u6df1\u5733\u5e02","point":{"y":"2560682.35","x":"12693451.44"}},"status":0}
	 * 
	 * @param res
	 * @return
	 */
	private String process(String res) {
		String address = null;
		try {
			if (res != null) {
				JSONObject jsonObject = JSONObject.parseObject(res);
				JSONObject contentJson = (JSONObject)jsonObject.get("content");
				if(contentJson != null) {
					address = (String)contentJson.get("address");
					//logger.info("address ========================= "+ address);
					
					// 广东省深圳市 电信
					res = address.split(" ")[0];
					// 广东省深圳市
				}
				
				if(res != null && res.indexOf("illegal") != -1 ) {
					res = null;
				}
				
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return res;
	}

	public static void main(String[] args) throws Exception {
		String ipLookup = new IpParserBaidu().ipLoc("119.139.196.127"); // baidu.com IP地址 //ipInfos 是一个数组
		System.out.println("ipLookup ======================== " + ipLookup);
	}


}
