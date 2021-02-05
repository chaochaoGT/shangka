package com.geek.shengka.common.ip;

import javax.servlet.http.HttpServletRequest;

/**
 * ip解析器
 * 
 * @author jiangxinqiang
 */
public interface RequestPareser {
	String ipLoc(HttpServletRequest request);
	
	String key();
}
