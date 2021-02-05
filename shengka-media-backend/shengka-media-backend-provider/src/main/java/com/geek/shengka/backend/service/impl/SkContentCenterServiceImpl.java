package com.geek.shengka.backend.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.geek.shengka.backend.constant.CommonConstant;
import com.geek.shengka.backend.constant.RedisExpiraTimeConstant;
import com.geek.shengka.backend.constant.RedisKeyConstant;
import com.geek.shengka.backend.exception.BaseException;
import com.geek.shengka.backend.params.req.SkContentVoice;
import com.geek.shengka.backend.params.res.SkContentCenterCategorysResParam;
import com.geek.shengka.backend.params.res.SkContentVideoInfoResParam;
import com.geek.shengka.backend.service.SkContentCenterService;
import com.geek.shengka.backend.util.BeanMapperUtils;
import com.geek.shengka.backend.util.DataResult;
import com.geek.shengka.backend.util.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author qubianzhong
 * @date 2019/8/5 20:39
 */
@Service
@Slf4j
public class SkContentCenterServiceImpl implements SkContentCenterService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RedisService redisService;

    @Value("${permission.center.remote.url}")
    private String permissionCenterUrl;
    @Value("${content.center.login.name}")
    private String contentCenterLoginName;
    @Value("${content.center.login.pwd}")
    private String contentCenterLoginPwd;
    @Value("${content.center.login.channel-code}")
    private String contentCenterLoginChannelCode;
    @Value("${content.center.get-videos.url}")
    private String contentCenterGetVideosUrl;
    @Value("${content.center.categories.url}")
    private String contentCenterCategoriesUrl;
    @Value("${content.center.upload-voice.url}")
    private String contentCenterUploadVoiceUrl;
    @Value("${content.center.biz-code}")
    private String contentCenterBizCode;

    @Override
    public String getToken() {
        /**
         * {"loginName":"admin","loginPassword":"123456","channelCode":"contentOperation"}
         */
        String token = null;
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        JSONObject body = new JSONObject();
        body.put("loginName", contentCenterLoginName);
        body.put("loginPassword", contentCenterLoginPwd);
        body.put("channelCode", contentCenterLoginChannelCode);
        HttpEntity<String> formEntity = new HttpEntity<>(body.toJSONString(), headers);
        DataResult<String> dataResult = restTemplate.postForEntity(permissionCenterUrl + "/userLogin/login", formEntity, DataResult.class).getBody();
        if (dataResult != null && !StringUtils.isEmpty(dataResult.getData())) {
            token = dataResult.getData();
            redisService.pushWithTime(RedisKeyConstant.CONTENT_CENTER_TOKEN, token, RedisExpiraTimeConstant.CONTENT_CENTER_TOKEN, TimeUnit.SECONDS);
        }
        return token;
    }

    /**
     * 根据IDS来查询视频详情列表
     *
     * @param ids
     * @return java.util.List<com.geek.shengka.backend.params.res.SkContentVideoInfoResParam>
     * @author qubianzhong
     * @Date 13:30 2019/8/6
     */
    @Override
    public List<SkContentVideoInfoResParam> getVideoInfosByIds(List<String> ids) throws BaseException {
        if (CollectionUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }
        JSONObject body = new JSONObject();
        if (!CollectionUtils.isEmpty(ids)) {
            body.put("pageSize", ids.size());
            body.put("videoIds", ids);
        }
        Object data = getResult(body, contentCenterGetVideosUrl, HttpMethod.POST);
        /**
         * {"code":888,"msg":"TOKEN已失效"}
         */
        return JSONObject.parseArray(JSONObject.toJSONString(data), SkContentVideoInfoResParam.class);
    }

    private Object getResult(JSONObject body, String path, HttpMethod method) throws BaseException {
        String token = redisService.getString(RedisKeyConstant.CONTENT_CENTER_TOKEN);
        if (StringUtils.isEmpty(token)) {
            token = getToken();
        }
        DataResult dataResult = getResult(body, token, path, method);
        int code = dataResult.getCode();
        String msg = dataResult.getMsg();
        if (code == 0) {
            return dataResult.getData();
        } else if (code == CommonConstant.CONTENT_TOKEN_FAILURE_CODE
                || CommonConstant.CONTENT_TOKEN_FAILURE_MSG.equals(msg)) {
            token = getToken();
            dataResult = getResult(body, token, path, method);
            code = dataResult.getCode();
            msg = dataResult.getMsg();
            if (code == 0) {
                return dataResult.getData();
            }
        }
        throw new BaseException("通过视频ID查询视频列表接口异常：" + msg);
    }

    /**
     * 内容中心视频分类列表
     *
     * @return java.util.List<com.geek.shengka.backend.params.res.SkContentCenterCategorysResParam>
     * @author qubianzhong
     * @Date 16:55 2019/8/8
     */
    @Override
    public List<SkContentCenterCategorysResParam> categories() throws BaseException {
        Object data = getResult(null, contentCenterCategoriesUrl, HttpMethod.GET);
        if (data == null) {
            return new ArrayList<>();
        }
        JSONArray list = JSONObject.parseArray(JSONObject.toJSONString(data));
        if (list.isEmpty()) {
            return new ArrayList<>();
        }
        int size = list.size();
        List<SkContentCenterCategorysResParam> result = new ArrayList<>(size);
        SkContentCenterCategorysResParam resParam;
        for (int i = 0; i < size; i++) {
            resParam = BeanMapperUtils.map(list.getJSONObject(i), SkContentCenterCategorysResParam.class);
            if (resParam != null) {
                result.add(resParam);
            }
        }
        return result;

    }

    /**
     * 校验视频IDS
     *
     * @param ids
     * @return void
     * @author qubianzhong
     * @Date 15:57 2019/8/13
     */
    @Override
    public void checkVideoIds(List<String> ids) throws BaseException {
        List<SkContentVideoInfoResParam> videoInfoResParams = getVideoInfosByIds(ids);
        if (CollectionUtils.isEmpty(videoInfoResParams)) {
            throw new BaseException("视频ID不存在：" + JSONObject.toJSONString(ids));
        }
        List<String> notExistIds = videoInfoResParams.stream().map(SkContentVideoInfoResParam::getId).filter(id -> !ids.contains(id)).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(notExistIds)) {
            throw new BaseException("视频ID不存在：" + JSONObject.toJSONString(notExistIds));
        }
    }

    /**
     * 上传语音
     *
     * @param contentVoice
     * @return void
     * @author qubianzhong
     * @Date 18:02 2019/8/20
     */
    @Override
    public void uploadVoice(SkContentVoice contentVoice) throws BaseException {
        if (contentVoice == null) {
            return;
        }
        getResult(JSONObject.parseObject(JSONObject.toJSONString(contentVoice)), contentCenterUploadVoiceUrl, HttpMethod.POST);
    }

    private DataResult getResult(JSONObject body, String token, String path, HttpMethod method) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        headers.add("authorization", token);
        headers.add("bizCode", contentCenterBizCode);
        headers.add("version", "0.0.1");
        body = body == null ? new JSONObject() : body;
        HttpEntity formEntity = new HttpEntity(body.toJSONString(), headers);
        DataResult result = restTemplate.exchange(path, method, formEntity, DataResult.class).getBody();
        log.info("远程调用内容中心：body={},token={},path={},method={}", body.toJSONString(), token, path, method.name());
        log.info("远程调用内容中心：result={}", JSONObject.toJSONString(result));
        return result;
    }
}
