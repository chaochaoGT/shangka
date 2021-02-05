package com.geek.shengka.user.advice;

import com.geek.shengka.common.basemodel.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author qubianzhong
 * @Date 17:43 2019/6/4
 */
@ControllerAdvice
@Slf4j
public class GlobalUserExceptionHandler {

    private String brace = "[";

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseResponse errorHandler(HttpServletRequest req, Exception e) {
        BaseResponse response = BaseResponse.newSuccess();
        String message;
        if (e instanceof MethodArgumentNotValidException
                || e instanceof BindException) {
            String errorStr = e.getMessage();
            int keyEnd = e.getMessage().lastIndexOf("]];");
            int keyEnd2 = e.getMessage().lastIndexOf("],");
            keyEnd = keyEnd2 > keyEnd ? keyEnd2 : keyEnd;
            int keyStart = 0;
            if (keyEnd > 0) {
                keyStart = e.getMessage().substring(0, keyEnd).lastIndexOf(brace);
            }
            int msgStart = e.getMessage().lastIndexOf(brace);
            int msgEnd = e.getMessage().lastIndexOf("]]");
            if (msgStart > 0 && msgEnd > 0 && msgEnd > msgStart && keyEnd > 0 && keyStart > 0 && keyEnd > keyStart) {
                errorStr = e.getMessage().substring(keyStart + 1, keyEnd) + ":" + e.getMessage().substring(msgStart + 1, msgEnd);
            } else if (keyEnd < 0 && msgStart > 0 && msgEnd > 0 && msgEnd > msgStart) {
                errorStr = e.getMessage().substring(msgStart + 1, msgEnd);
            } else if (msgEnd < msgStart) {
                msgEnd = e.getMessage().lastIndexOf("]");
                errorStr = e.getMessage().substring(keyStart + 1, keyEnd) + ":" + e.getMessage().substring(msgStart + 1, msgEnd);
            }
            message = errorStr;
        } else if (e instanceof NullPointerException) {
            message = "NullPointerException ";
        } else {
            message = e.getMessage();
        }
        response.setMsg(message);
        response.setCode(-1);
        log.error(message);
        //打印堆栈信息
        e.printStackTrace();
        return response;
    }

}
