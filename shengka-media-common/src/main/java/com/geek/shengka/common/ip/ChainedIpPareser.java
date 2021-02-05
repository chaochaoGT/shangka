package com.geek.shengka.common.ip;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.geek.shengka.common.util.RequestUtil;


/**
 * IpParser聚合解析器
 * @author jiangxinqiang
 */
public class ChainedIpPareser implements IpPareser, RequestPareser {
	protected static Logger logger = LoggerFactory.getLogger(ChainedIpPareser.class);

	private static ChainedIpPareser INSTANCE = new ChainedIpPareser();
	private ChainedIpPareser(){}
	public static ChainedIpPareser instance() {
		return INSTANCE;
	}
	
	private Map<String, IpPareser> parserMap = new LinkedHashMap<String, IpPareser>();
	{
		parserMap.put(IpParserBaidu.instance().key(), IpParserBaidu.instance());
		parserMap.put(IpParserChinaz.instance().key(), IpParserChinaz.instance());
		parserMap.put(IpParserChinazPost.instance().key(), IpParserChinazPost.instance());
		parserMap.put(IpParserPcOnline.instance().key(), IpParserPcOnline.instance());
		parserMap.put(IpParserTaobao.instance().key(), IpParserTaobao.instance());
		
		MapUtils.synchronizedMap(parserMap);
	}

	@Override
	public String ipLoc(HttpServletRequest request) {
		String ipAddr = RequestUtil.getIpAddr(request);
		String result =  StringUtils.isEmpty(ipAddr) ?  null : this.ipLoc(ipAddr);
		return result;
	}
	
	@Override
	public String ipLoc(String ipAddr) {
		String res = null;
		if(StringUtils.isEmpty(ipAddr) )
		{return null; }
		List<String> emptyParser = new ArrayList<String>();
		for(Map.Entry<String, IpPareser> ipEntryParser : parserMap.entrySet()) {
			if((res = ipEntryParser.getValue().ipLoc(ipAddr)) != null) {
				//重置当前parser顺序
				if(CollectionUtils.isNotEmpty(emptyParser)) {
					for(String key : emptyParser) {
						IpPareser ipPareser = parserMap.get(key);
						parserMap.remove(key);
						parserMap.put(ipPareser.key(), ipPareser);
					}
				}
				logger.info("analyze-ip["+ipAddr+"],current-key["+ipEntryParser.getValue().key()+"] ,get-loc["+res+"]");
				return res;
			}else {
				emptyParser.add(ipEntryParser.getKey());
			}
		}
		
		emptyParser.clear();
		return res;
	}

	
	
	@Override
	public String key() {
		return null;
	}
	
	

	public static void main(String[] args) throws Exception {
		String ipLookup = ChainedIpPareser.instance().ipLoc("119.139.196.127");
		System.out.println("ipLookup ======================== " + ipLookup);
	}
	
}
