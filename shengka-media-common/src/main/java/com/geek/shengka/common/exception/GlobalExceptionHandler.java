package com.geek.shengka.common.exception;

import javax.servlet.http.HttpServletRequest;

import com.geek.shengka.common.basemodel.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.geek.shengka.common.basemodel.ResponseCode;


/** 
* @author: bolei
* @date：2018年8月24日 下午12:37:59 
* @description：处理全局异常
*/


@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {

    }

    /**
     * 处理所有不可知的异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    Object handleException(Exception e, HttpServletRequest request){
        LOGGER.error(e.getMessage(), e);
        String requestURI = request.getRequestURI();
        LOGGER.error("occurs error when execute url ={} ,message {}",requestURI,e.getMessage());
        
        return BaseResponse.newFailure(ResponseCode.SYSTEM_ERROR);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Object MethodArgumentNotValidException(Exception e, HttpServletRequest request){
        LOGGER.error(e.getMessage(), e);
        String requestURI = request.getRequestURI();
        LOGGER.error("occurs error when execute url ={} ,message {}",requestURI,e.getMessage());

        return BaseResponse.newFailure(ResponseCode.REQUIRED_PARAMS_MISSING);
    }
 

}
