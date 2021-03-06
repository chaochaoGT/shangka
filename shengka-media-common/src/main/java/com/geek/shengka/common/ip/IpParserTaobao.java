package com.geek.shengka.common.ip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.geek.shengka.common.http.HttpPoolProxy;

/**
 * @author jiangxinqiang
 */
public class IpParserTaobao implements IpPareser {

	protected static Logger logger = LoggerFactory.getLogger(IpParserTaobao.class);

	//http://ip.taobao.com/service/getIpInfo.php?ip=119.139.196.127
	private final static String REMOTE_URL = "http://ip.taobao.com/service/getIpInfo.php?ip=";


	private static IpParserTaobao INSTANCE = new IpParserTaobao();
	private IpParserTaobao(){}
	public static IpParserTaobao instance() {
		return INSTANCE;
	}
	
	@Override
	public String key() {
		return "taobao";
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
		res = this.process(res);
		
		logger.info("ip-parser-key["+this.key()+"]input=["+ip+"]output=["+res+"]");
		
		return res;
	}

	/**
	 * <dt>来自</dt>
	 * <dd>上海市 电信<a
	 * 
	 * @param res
	 * @return
	 */
	private String process(String res) {
		if (res != null) {
			// res = res.substring(res.indexOf("来自</dt>\r\n")+"来自</dt>\r\n".length(),
			// res.indexOf("来自</dt>\r\n")+100);
			// <dd>上海市 电信<a href="http://too
			// res = res.substring(res.indexOf("<dd>")+"<dd>".length(), res.indexOf("<a"));
			// 广东省深圳市 电信
			res = res.split(" ")[0];
			// 广东省深圳市
		}
		return res;
	}

	public static void main(String[] args) throws Exception {
		String ipLookup = new IpParserTaobao().ipLoc("119.139.196.127"); // baidu.com IP地址 //ipInfos 是一个数组
		System.out.println("ipLookup ======================== " + ipLookup);
	}
}
