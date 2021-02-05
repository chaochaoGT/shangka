package com.geek.shengka.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.geek.shengka.common.exception.BaseException;
import com.geek.shengka.common.proxy.UserTokenHttpProxy;
import com.geek.shengka.common.util.UserContextHolder;
import com.geek.shengka.user.entity.SkBlack;
import com.geek.shengka.user.mapper.SkBlackDAO;
import com.geek.shengka.user.mapper.SkUserBaseInfoDAO;
import com.geek.shengka.user.request.BlackPageRequest;
import com.geek.shengka.user.request.BlackRequest;
import com.geek.shengka.user.response.SkBlackResponse;
import com.geek.shengka.user.service.BlackService;
import com.geek.shengka.user.utils.RedisService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 黑名单
 *
 * @author: xuxuelei
 * @create: 2019-08-07 09:29
 **/
@Service
public class BlackServiceImpl implements BlackService {
    private final static Logger logger = LoggerFactory.getLogger(BlackServiceImpl.class);
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RedisService redisService;

    @Value("${user.center.remote.url}")
    private String userCenterUrl;
    @Value("${user.center.header.platform-id}")
    private String userCenterPlatformId;
    @Value("${user.center.header.source}")
    private String userCenterSource;
    
    
    @Autowired
    private SkBlackDAO skBlackDAO;
    @Autowired
    private UserTokenHttpProxy userTokenHttpProxy;
    @Autowired
    private SkUserBaseInfoDAO skUserBaseInfoDAO;
    
	@Override
	public int addBlackUser(BlackRequest param) {
		SkBlack skBlack = new SkBlack();
		skBlack.setUserId(UserContextHolder.getUserIdByHeader());
		skBlack.setBlackUserId(param.getDefriendUserId());
		skBlack.setCreateTime(new Date());
		return skBlackDAO.insertSelective(skBlack);
	}

	@Override
	public List<SkBlackResponse> getBlacklist(BlackPageRequest param) {
		List<SkBlackResponse> skBlackResponses = new ArrayList<SkBlackResponse>();
		if(0==param.getPageSize()) {
			param.setPageSize(10);
		}
		SkBlack skBlackBean = new SkBlack();
		skBlackBean.setUserId(UserContextHolder.getUserIdByHeader());
		PageHelper.startPage(param.getPageIndex(), param.getPageSize());
		List<SkBlack> skBlacks = skBlackDAO.selectList(skBlackBean);
		PageInfo<SkBlack> pageInfo = new PageInfo<>(skBlacks);
		if(CollectionUtils.isNotEmpty(pageInfo.getList())) {
			for(SkBlack skBlack:skBlacks) {
	            SkBlackResponse skBlackResponse = new SkBlackResponse();
	            try {
		        JSONObject result = getInfoById(skBlack.getBlackUserId());
		        JSONObject info = result.getJSONObject("userInfoResponse");
		        if (info == null) {
		            info = new JSONObject();
		        }
	            skBlackResponse.setNickname(info.getString("nickname"));
	            skBlackResponse.setUserAvatar(info.getString("userAvatar"));
 	            skBlackResponse.setRemark(info.getString("remark"));
	            }catch(Exception e) {
	            	logger.error("对应的用户id查询不到信息");
	            }
	            skBlackResponse.setBackId(skBlack.getId());
	            skBlackResponse.setBlackUserId(skBlack.getBlackUserId());
	            skBlackResponses.add(skBlackResponse);
			}
		}
		return skBlackResponses;
	}

	@Override
	public int delBlackUser(long  backId) {
		return skBlackDAO.deleteByPrimaryKey(backId);
	}

	
    private JSONObject getInfoById(Long userId) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        headers.add("platform-id", userCenterPlatformId);
        headers.add("source", userCenterSource);
// 
        HttpEntity httpEntity = new HttpEntity(headers);
        JSONObject result = restTemplate.exchange(userCenterUrl + "/v2/showUser?userId=" + userId, HttpMethod.GET, httpEntity, JSONObject.class).getBody();
        String code = result.getString("code");
        if ("".equals(code.replace("0", ""))) {
            return result.getJSONObject("data");
        }
        String message = result.getString("message");
        throw new BaseException(message);
    }

	@Override
	public List<SkBlackResponse> selectByUserList(BlackPageRequest param) {
		if(0==param.getPageSize()) {
			param.setPageSize(10);
		}
		SkBlack skBlackBean = new SkBlack();
		skBlackBean.setUserId(UserContextHolder.getUserIdByHeader());
		PageHelper.startPage(param.getPageIndex(), param.getPageSize());
		List<SkBlackResponse> skBlacks = skBlackDAO.selectByUserList(skBlackBean);
		PageInfo<SkBlackResponse> pageInfo = new PageInfo<>(skBlacks);

		return pageInfo.getList();
	}
 
}
