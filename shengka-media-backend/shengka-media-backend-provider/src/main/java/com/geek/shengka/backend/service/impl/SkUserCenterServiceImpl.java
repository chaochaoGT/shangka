package com.geek.shengka.backend.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.geek.shengka.backend.exception.BaseException;
import com.geek.shengka.backend.params.res.SkUserInfoResParam;
import com.geek.shengka.backend.service.SkUserCenterService;
import com.geek.shengka.backend.util.BeanMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author qubianzhong
 * @date 2019/8/7 10:02
 */
@Service
public class SkUserCenterServiceImpl implements SkUserCenterService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${user.center.remote.url}")
    private String userCenterUrl;
    @Value("${user.center.header.platform-id}")
    private String userCenterPlatformId;
    @Value("${user.center.header.source}")
    private String userCenterSource;

    @Override
    public List<SkUserInfoResParam> getInfosByIds(List<Long> userIds) throws BaseException {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        headers.add("platform-id", userCenterPlatformId);
        headers.add("source", userCenterSource);
        HttpEntity formEntity = new HttpEntity(JSONObject.toJSONString(userIds), headers);
        JSONObject result = restTemplate.postForEntity(userCenterUrl + "/v1/getUserInfoByIds", formEntity, JSONObject.class).getBody();
        if (result.getIntValue("code") == 0) {
            return BeanMapperUtils.mapList(result.getJSONArray("data"), SkUserInfoResParam.class);
        }
        throw new BaseException(result.getString("msg"));
    }

    /**
     * 校验用户IDS
     *
     * @param ids
     * @return void
     * @author qubianzhong
     * @Date 15:58 2019/8/13
     */
    @Override
    public void checkUserIds(List<String> ids) throws BaseException {
        List<Long> userIds = ids.stream().map(Long::valueOf).collect(Collectors.toList());
        List<SkUserInfoResParam> infoResParams = getInfosByIds(userIds);
        if (CollectionUtils.isEmpty(infoResParams)) {
            throw new BaseException("用户ID不存在：" + JSONObject.toJSONString(ids));
        }
        List<Long> notExistIds = infoResParams.stream().map(SkUserInfoResParam::getId).filter(id -> !ids.contains(String.valueOf(id))).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(notExistIds)) {
            throw new BaseException("用户ID不存在：" + JSONObject.toJSONString(notExistIds));
        }
    }
}
