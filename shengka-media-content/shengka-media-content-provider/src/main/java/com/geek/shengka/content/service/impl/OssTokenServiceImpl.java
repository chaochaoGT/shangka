package com.geek.shengka.content.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.geek.shengka.content.config.oss.OssBucketPolicy;
import com.geek.shengka.content.config.oss.OssConfig;
import com.geek.shengka.content.response.OssSecurityTokenInfo;
@Service
public class OssTokenServiceImpl {
	
	public static final String REGION_CN_HANGZHOU = "cn-hangzhou";
	public static final String STS_API_VERSION = "2015-04-01";
	
    private Logger logger = LoggerFactory.getLogger(OssTokenServiceImpl.class);
   
    private OssBucketPolicy ossBucketPolicy;
    
    @Autowired
    private OssConfig ossConfig;
    
    public OssSecurityTokenInfo getOssToken() {
    	try {
    		if(ossBucketPolicy==null) {
    			ossBucketPolicy=new OssBucketPolicy(ossConfig.getBacketName());
    		}
        	String policy=JSONObject.toJSONString(ossBucketPolicy);
        	AssumeRoleResponse assumeRoleResponse= assumeRole(ossConfig.getAccesskeyId(), ossConfig.getAccesskeySecret(), ossConfig.getRoleArn(), ossConfig.getRoleSessionName(), policy,ossConfig.getTokenDurationSeconds());
        	OssSecurityTokenInfo ossSecurityTokenInfo=new OssSecurityTokenInfo(); 
        	ossSecurityTokenInfo.setExpiration(assumeRoleResponse.getCredentials().getExpiration());
        	ossSecurityTokenInfo.setToken(assumeRoleResponse.getCredentials().getSecurityToken());
        	ossSecurityTokenInfo.setAccessKeyId(assumeRoleResponse.getCredentials().getAccessKeyId());
        	ossSecurityTokenInfo.setAccessKeySecret(assumeRoleResponse.getCredentials().getAccessKeySecret());
        	return ossSecurityTokenInfo;
    	}catch (Exception e) {
    		logger.error(e.getMessage(),e);
		}
    	return null;
    }
    
	private AssumeRoleResponse assumeRole(String accessKeyId, String accessKeySecret, String roleArn,
			String roleSessionName, String policy,Long durationSeconds) throws ClientException 
	{
		try {
			// 创建一个 Aliyun Acs Client, 用于发起 OpenAPI 请求
			IClientProfile profile = DefaultProfile.getProfile(REGION_CN_HANGZHOU, accessKeyId, accessKeySecret);
			DefaultAcsClient client = new DefaultAcsClient(profile);

			// 创建一个 AssumeRoleRequest 并设置请求参数
			final AssumeRoleRequest request = new AssumeRoleRequest();
			request.setVersion(STS_API_VERSION);
			request.setMethod(MethodType.POST);
			request.setProtocol(ProtocolType.HTTPS);
			request.setRoleArn(roleArn);
			request.setRoleSessionName(roleSessionName);
			request.setPolicy(policy);
			request.setDurationSeconds(durationSeconds);

			// 发起请求，并得到response
			final AssumeRoleResponse response = client.getAcsResponse(request);

			return response;
		} catch (ClientException e) {
			throw e;
		}
	}
}
