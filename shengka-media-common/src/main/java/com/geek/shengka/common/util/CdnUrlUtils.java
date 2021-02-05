package com.geek.shengka.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

/**
 * cdn地址转化
 *
 * @description:
 * @author: xuxuelei
 * @create: 2019-03-30
 **/
@Slf4j
public class CdnUrlUtils {

    /**
     * 地址转化
     *
     * @param url
     * @return
     */
    public static String transferCdn(String url) {
        String paramUrl = null;
        // 增加视频封面地址转化 http start
        if (!StringUtils.isEmpty(url)) {
            if (url.startsWith("http")) {
                paramUrl = url;
            } else {
                if (CdnUrlConfig.domainUrl.endsWith("/")) {
                    paramUrl = CdnUrlConfig.domainUrl + url;
                } else {
                    paramUrl = CdnUrlConfig.domainUrl + "/" + url;
                }
            }
        }

        return paramUrl;
    }

}
