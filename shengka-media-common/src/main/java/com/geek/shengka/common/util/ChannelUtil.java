package com.geek.shengka.common.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChannelUtil {
	public final static Logger logger = LoggerFactory.getLogger(ChannelUtil.class);

	public static final String CHILD_CHANNEL = "childChannel";
	public static final String CHANNEL = "channel";
	public static final String PRD_TYPE = "prd_type";

	// 既有的四大渠道
	public static Set<String> oldChannels = new HashSet<String>();
	static {
		oldChannels.add("sc_meinv");
		oldChannels.add("sc_fengshui");
		oldChannels.add("sc_meishi");
		oldChannels.add("sc_gaoxiao");
	}

	/**
	 * 分析截取渠道包名字-----------拿到渠道内容map
	 * 
	 * @param channel
	 * @return
	 */
	public static Map<String, String> channelParameters(String channel) {

		Map<String, String> map = new HashMap<String, String>(4);
		map.put(PRD_TYPE, "");
		map.put(CHILD_CHANNEL, "");
		map.put(CHANNEL, "");
		
		
		
		if(ChannelUtil.oldChannels.contains(channel)) {
	        //对包名带#的历史渠道包 ，统一升级到meinv包 
			map.put(PRD_TYPE, "sc");
			map.put(CHILD_CHANNEL, "meinv");
			map.put(CHANNEL, "");
			return map;
	    }
		
		

		// Guard 1.
		if (StringUtils.isBlank(channel)) {
			return map;
		}

		// sc_主渠道_子渠道序号
		try {
			// 去除数字序号
			channel = channel.replaceAll("\\d", "");
			String[] params = channel.split("_");
			// 产品线
			map.put(PRD_TYPE, params[0]);

			//主渠道
			if (params.length >= 2) {
				map.put(CHANNEL, params[1]);
			}

			//次渠道
			if (params.length >= 3) {
				map.put(CHILD_CHANNEL, params[2]);
			}

		} catch (Exception e) {
			logger.error("get request params fail: " + e.getMessage());
		}
		return map;
	}

	/**
	 * 分析截取渠道包名字-----------拿到 **_***** 前缀内容
	 * 
	 * @param channel
	 * @return
	 */
	public static String filterChannel(String channel) {
		if (StringUtils.isEmpty(channel))
			return "";

		// 去除序号
		channel = channel.replaceAll("\\d+", "");
		String[] params = channel.split("_");
		if (params.length >= 2) {
			// sc_meinv
			// sc_meinv_toutiao101
			// qk_alibaba
			// qk_jinritoutiao101
			// qk__wifi101
			return params[0] + "_" + params[1];
		} else {
			return channel;
		}
	}

//	public static void main(String []args) {
//		Set<String> chs = new HashSet<String>();
//		chs.add("sc_alibaba");
//		chs.add("sc_meishi_alibaba");
//		chs.add("sc_meinv_alibaba");
//		chs.add("sc_gaoxiao_alibaba");
//		chs.add("sc_meishi_jinritoutiao199");
//		chs.add("sc_gaoxiao_jinritoutiao101");
//		chs.add("sc_fengshui_jinritoutiao101");
//		chs.add("sc_meishi_boboshipin101");
//		chs.add("sc_gaoxiao_boboshipin199");
//		chs.add("sc_meishi_wifi101");
//		chs.add("sc_gaoxiao_wifi101");
//		chs.add("qk_alibaba");
//		chs.add("qk_boboshipin101");
//		chs.add("qk_wifi101");
//		
//		for(String ch : chs) {
//			System.out.println("chs_name["+ch+"][params="+channelParameters(ch)+"]");
//		}
//	}

}
