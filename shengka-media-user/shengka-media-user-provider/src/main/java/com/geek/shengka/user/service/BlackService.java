package com.geek.shengka.user.service;

import java.util.List;
import java.util.Map;
import com.geek.shengka.user.request.BlackPageRequest;
import com.geek.shengka.user.request.BlackRequest;
import com.geek.shengka.user.response.SkBlackResponse;

/**
 * @author: xuxuelei
 * @create: 2019-08-07 09:29
 **/
public interface BlackService {

	/** 加入黑名单 **/
	int addBlackUser(BlackRequest param);

	/** 获取黑名单列表 **/
	List<SkBlackResponse> getBlacklist(BlackPageRequest param);

	/** 删除黑名单 **/
	int delBlackUser(long  backId);
	
	/** 获取黑名单列表 **/
	List<SkBlackResponse> selectByUserList(BlackPageRequest param);
}
