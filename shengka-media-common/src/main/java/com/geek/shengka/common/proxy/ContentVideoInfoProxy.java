package com.geek.shengka.common.proxy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.geek.shengka.common.basemodel.BaseMediaInfo;
import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.common.enums.UserActionEventEnum;
import com.geek.shengka.common.http.ContextTools;
import com.geek.shengka.common.http.HttpPoolProxy;
import com.geek.shengka.common.request.BaseContentRequest;
import com.geek.shengka.common.request.ContentActionData;
import com.geek.shengka.common.request.ContextReportRequest;
import com.geek.shengka.common.response.ContentCenterResponse;
import com.geek.shengka.common.util.UUIDUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 内容中心代理
 *
 * @author xuxuelei
 */
@Component
public class ContentVideoInfoProxy {
    private static final Logger logger = LoggerFactory.getLogger(ContentVideoInfoProxy.class);
    @Autowired
    private ContentCenterProxy contentProxyV4;

    @Autowired
    private ContentUrlConfig contentUrlConfig;


    //private static final String CONTENT_MEDIA_KEY = "CONTENT:MEDIA:%s";

    private static final String ROLLING_MEDIA_KEY = "ROLLING:CONTENT:MEDIA:%s";

    private static final long expireTime = 60 * 10 * 1000; // 默认失效时间：10分钟


    /**
     * 内容中心-内容上报接口
     *
     * @param params
     */
    public void userBehaviorReported(ContextReportRequest params) {
        ContentCenterResponse ContentCenterResponse = contentProxyV4.getAuthToken();
        if (null == ContentCenterResponse) {
            return;
        }
        ContentActionData msg = new ContentActionData();
        msg.setEvent(UUIDUtils.generateShortUuid(), UserActionEventEnum.getEventId(params.getType()), UserActionEventEnum.getEventName(params.getType()), params.getRemark(),"home");
        msg.setContentsId(params.getVideoId());
        msg.setContentsType("video");
        msg.setUserId(params.getUserId());

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("authorization", ContentCenterResponse.getToken());
        headers.put("timestamp", System.currentTimeMillis()+"");
        headers.put("bizCode", ContentCenterResponse.getBizCode());
        headers.put("version", params.getVersion());
        headers.put("Content-Type",MediaType.APPLICATION_JSON_VALUE);
        String url = contentUrlConfig.getBehaviorReportedMethod();
        try {
            String body = HttpPoolProxy.postJson(url, JSON.toJSONString(msg), 700, 700, 1000, headers);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
    }


    /**
     * 内容中心-多条件获取视频接口
     * @param param
     */
    public <T extends BaseContentRequest> List<BaseMediaInfo> multiConditionGetMedias(T param) {
        String responseData="";
        try {
            ContentCenterResponse ContentCenterResponse = contentProxyV4.getAuthToken();
            if (null == ContentCenterResponse) {
                return null;
            }
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("authorization", ContentCenterResponse.getToken());
            headers.put("bizCode", ContentCenterResponse.getBizCode());
            headers.put("version", ContextTools.getRequest().getHeader("version"));

            String url = contentUrlConfig.getContentCenterUrl() + contentUrlConfig.getMultiConditionMedias();
            responseData = HttpPoolProxy.postJson(url,JSON.toJSONString(param), 3000, 1000, 1000, headers);
            if (StringUtils.isNotBlank(responseData)) {
                try {
                    BaseResponse<List<BaseMediaInfo>> listBaseResponse = JSON.parseObject(responseData,
                            new TypeReference<BaseResponse<List<BaseMediaInfo>>>() {
                    });
                    return listBaseResponse.getData();
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
        } catch (Exception e) {
            logger.error("mitiConditionGetMedias responseData={} error={}",responseData, e);
        }
        return null;
    }

}
