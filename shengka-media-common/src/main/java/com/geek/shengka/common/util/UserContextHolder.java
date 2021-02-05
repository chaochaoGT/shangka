package com.geek.shengka.common.util;

import com.geek.shengka.common.context.BaseContextHandler;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.geek.shengka.common.basemodel.BaseHeadersRequest;

import javax.servlet.http.HttpServletRequest;

/*
 * @Author yunfei
 * @Description
 * @Date
 **/
public class UserContextHolder {

	private static final Logger logger = LoggerFactory.getLogger(UserContextHolder.class);

	
    /**
     * 从本地缓存获取用户ID
     *
     * @return
     */
    public static Long getUserId() {
        String uId = BaseContextHandler.getCurrentUId();
        if(StringUtils.isBlank(uId)){
            uId="-1";
        }
        return Long.valueOf(uId);
    }

    /**
     * 从请求头获取用户ID
     *
     * 1.2.0 版本之后使用
     * @return
     */
    public static Long getUserIdByHeader() {
        Long userId = -1L;
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            if (request != null) {
                String userIdStr = StringUtils.trimToEmpty(request.getHeader("UserId"));
                userId = StringUtils.isEmpty(userIdStr) ? -1L : Long.parseLong(userIdStr);
            }
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
            userId = -1L;
        }
        return userId;
    }

    /**
     * 从请求头获取uuid
     * 
     * @return
     */
    public final static String getUuidByHeader() {
        String uuId = "";
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            if (request != null) {
                uuId = StringUtils.trimToEmpty(request.getHeader("uuid"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uuId;
    }
    
    
    /**
     * 从请求头获取 =====>  达观推荐唯一索引
     * 这里的imei不是真实imei，  而是app对android 和 imei 联合进行位移加密后的值
     * 
     * 1.2.0 版本之后使用
     * @return
     */
    public final static String getImeiDataGrandByHeader() {
        String imei = "";
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            if (request != null) {
            	imei = StringUtils.trimToEmpty(request.getHeader("imeiDataGrand"));
            	//老版本兼容处理
            	imei = StringUtils.isEmpty(imei) ? StringUtils.trimToEmpty(request.getHeader("uuid")) : imei;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imei;
    }

    /**
     * 从请求头获取业务线
     * @return
     */
    public static String getBizCode() {
        String bizCode = "";
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            if (request != null) {
                bizCode = StringUtils.trimToEmpty(request.getHeader("bizCode"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bizCode;
    }
    /**
     * 从请求头获取渠道号
     * @return
     */
    public static String getChannel() {
        String channel = "";
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            if (request != null) {
                channel = StringUtils.trimToEmpty(request.getHeader("channel"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return channel;
    }
    /**
     * 从请求头获取版本
     * @return
     */
    public static String getVersion() {
        String version = "";
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            if (request != null) {
                version = StringUtils.trimToEmpty(request.getHeader("version"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return version;
    }

    /**
     * 从请求头获取请求时间
     * @return
     */
    public static String getTimestamp() {
        String timestamp = "";
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            if (request != null) {
                timestamp = StringUtils.trimToEmpty(request.getHeader("timestamp"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return timestamp;
    }
    /**
     * 从请求头获取客户端类型
     * @return
     */
    public static String getPlatform() {
        String platform = "";
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            if (request != null) {
                platform = StringUtils.trimToEmpty(request.getHeader("platform"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return platform;
    }

    /**
     * 从请求头获取客户端版本
     * @return
     */
    public static String getPlatformVersion() {
        String platformVersion = "";
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            if (request != null) {
                platformVersion = StringUtils.trimToEmpty(request.getHeader("platformVersion"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return platformVersion;
    }

    /**
     * 从请求头获取扩展信息
     * @return
     */
    public static String getExtra() {
        String extra = "";
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            if (request != null) {
                extra = StringUtils.trimToEmpty(request.getHeader("extra"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return extra;
    }

    /**
     * 获取请求头信息
     * @param t
     * @param <T>
     * @return
     */
    public static  BaseHeadersRequest getDataFromHeader(BaseHeadersRequest  t ) {

    	 try {
             HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
             if (request != null) {

            	 String version = StringUtils.trimToEmpty(request.getHeader("version"));
            	 t.setVersion(version);

            	 String timestamp = StringUtils.trimToEmpty(request.getHeader("timestamp"));
            	 t.setTimestamp(timestamp);

            	 String uuid = StringUtils.trimToEmpty(request.getHeader("uuid"));
            	 t.setUuid(uuid);

                 String imei = StringUtils.trimToEmpty(request.getHeader("imei"));
                 t.setImei(imei);

            	 String userId = StringUtils.trimToEmpty(request.getHeader("UserId"));
            	 t.setUserId(StringUtils.isNoneBlank(userId) ? Long.parseLong(userId) : -1L);

             }
         } catch (Exception e) {
        	 logger.warn(e.getMessage(), e);
         }

        return t;
    }

}
