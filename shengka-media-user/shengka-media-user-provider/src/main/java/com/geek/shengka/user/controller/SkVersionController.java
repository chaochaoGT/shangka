package com.geek.shengka.user.controller;

import com.geek.shengka.common.annotation.IgnoreClientToken;
import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.common.cache.CacheProvider;
import com.geek.shengka.user.entity.SkVersion;
import com.geek.shengka.user.service.SkVersionService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * @author yunfei
 */
@RestController
@RequestMapping("/v1/skApp")
@IgnoreClientToken
public class SkVersionController {

    public final static Logger logger = LoggerFactory.getLogger(SkVersionController.class);

    @Autowired
    private SkVersionService skVersionService;
    /**
     * 默认渠道
     */
    private String defaultChannel = "sk_jinritoutiao_meinv";


    /**
     * 对包名带#的历史渠道包，不进行升级动作
     */
    private static final SkVersion ignoreVersion = new SkVersion();

    static {
        ignoreVersion.setVersionCode(0);
    }


    private final String APP_VERSION_KEY = "APP_VERSION_KEY:%s";

    @GetMapping("/lastVersion")
    @ResponseBody
    @IgnoreClientToken
    public BaseResponse<SkVersion> lastVersion(HttpServletRequest request) {

        String channel = null;
        try {
            channel = request.getHeader("channel");
        } catch (Exception e) {
            logger.warn("");
        }
        //sk_jinritoutiao_meinv-557
        channel = StringUtils.isBlank(channel) ? defaultChannel : channel;
        String prdType = getPrdType(channel);
        String[] channels = channel.split("-");
        String versionKey = String.format(APP_VERSION_KEY, channels[0]);
        //数字序号 557
        String serialNumber = (channels.length > 1 ? "-" + channels[1] : "");
        //1、先从redis缓存取数据
        SkVersion localVersion = CacheProvider.getObject(versionKey, SkVersion.class);
        if (localVersion == null) {
            //2、缓存不存在， 直接从数据库取
            localVersion = skVersionService.lastVersion(channels[0], prdType);
            //3、设置回redis缓存，失效期为默认60秒
            CacheProvider.set(versionKey, localVersion);
        }
        if (localVersion != null) {
            localVersion.setDownloadUrl(String.format(localVersion.getDownloadUrl(), serialNumber));
        } else {
            logger.warn("[channel-has-no-version-to-upgrade][channelCode=" + channel + "][check-within-cms-system]");
            //兜底处理
            localVersion = ignoreVersion;
        }
        return BaseResponse.newSuccess(localVersion);
    }

    private static final String getPrdType(String channelCode) {
        String[] strings = channelCode.split("_");
        if (strings.length != 0) {
            return strings[0];
        }
        //默认sk
        return "sk";
    }


}