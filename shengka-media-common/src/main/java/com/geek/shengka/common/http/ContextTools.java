package com.geek.shengka.common.http;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.geek.shengka.common.util.UserContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;


/**
 * 负责Web通用类包装
 * <p style="display:none">modifyRecord</p>
 * @date 2017年5月18日 上午11:20:58
 * @since
 * @version
 *
 * 
 *
 */
public class ContextTools {

    private static final Logger logger = LoggerFactory.getLogger(UserContextHolder.class);
	
	/**
	 * 获取request请求对象
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
		if(null == attributes){
			return null;
		}
		HttpServletRequest request = ((ServletRequestAttributes)attributes).getRequest();
		try {
			if(null != request){
				request.setCharacterEncoding("UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return request;
	}

    /**
     * 从请求头获取用户ID
     *
     * 1.2.0 版本之后使用
     * @return
     */
    public static String getVersion() {
        String version = "1.0";
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            if (request != null) {
                String userIdStr = StringUtils.trimToEmpty(request.getHeader("version"));
                version = StringUtils.isEmpty(userIdStr) ? version : userIdStr;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return version;
    }
	
	/**
	 * 获取response响应对象
	 * @return
	 */
	public static HttpServletResponse getResponse() {
		HttpServletResponse response = ((ServletWebRequest)RequestContextHolder.getRequestAttributes()).getResponse();
		return response;
	}
	

	 
 
	
}
