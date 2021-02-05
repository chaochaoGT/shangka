package com.geek.shengka.common.ip;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.geek.shengka.common.http.HttpPoolProxy;

/**
 * @author jiangxinqiang
 */
public class IpParserChinazPost implements IpPareser {

	protected static Logger logger = LoggerFactory.getLogger(IpParserChinazPost.class);

	//http://ip.tool.chinaz.com/ajaxsync.aspx?at=ip&callback=jQuery11130847990510782543_1563201679579
	//POST
	// ip 119.139.196.127
	//http://ip.tool.chinaz.com/ajaxsync.aspx?at=ip&callback=jQuery11130847990510782543_1563201679579
	private final static String REMOTE_URL = "http://ip.tool.chinaz.com/ajaxsync.aspx?at=ip&callback=jQuery11130847990510782543_1563201679579";

	private static IpParserChinazPost INSTANCE = new IpParserChinazPost();
	private IpParserChinazPost(){}
	protected static IpParserChinazPost instance() {
		return INSTANCE;
	}

	@Override
	public String key() {
		return "chinazPost";
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

		Map<String, String> param = new HashMap<String, String>();
		param.put("ip", ip);
		try {
			res = HttpPoolProxy.postWithParamsForString(REMOTE_URL, param);
		} catch ( Exception e1) {
			logger.error(e1.getMessage(), e1);
		}
		
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
		 

		/**
		 * http://ip.tool.chinaz.com/siteip post Content-Type:
		 * application/x-www-form-urlencoded
		 * 
		 * ips: 119.139.196.127 submore: 查询
		 * 
		 * 
		 */
		res = this.process(res);
		
		logger.info("ip-parser-key["+this.key()+"]input=["+ip+"]output=["+res+"]");
		
		return res;
	}

	/**
	 *  jQuery11130847990510782543_1563201679579([{domain:'119.139.196.127',address:'119.139.196.127',numAddress:'2005648511',location:'广东省深圳市 电信'}])
	 *  jQuery11130847990510782543_1563201679579([{msg:'IP输入有误',domain:'119.139.196.127,117.89.35.58'}])
	 * 
	 * @param res
	 * @return
	 */
	private String process(String res) {
		String address = null;
		if (res != null) {
			try {
				res = res.replaceAll("jQuery11130847990510782543_1563201679579", "");
				//([{domain:'119.139.196.127',address:'119.139.196.127',numAddress:'2005648511',location:'广东省深圳市 电信'}])
				res = res.substring(2, res.length()-2);
				JSONObject jsonObject = JSONObject.parseObject(res);
				address = (String)jsonObject.get("location");
				// 广东省深圳市 电信
				res = address.split(" ")[0];
				// 广东省深圳市
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		return res;
	}

	public static void main(String[] args) throws Exception {
		String ipLookup = new IpParserChinazPost().ipLoc("119.139.196.127"); 
		System.out.println("ipLookup ======================== " + ipLookup);
	}
}
