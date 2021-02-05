package com.geek.shengka.backend.advice;

import com.geek.shengka.backend.constant.CommonConstant;
import com.geek.shengka.backend.util.DataResult;
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
public class GlobalExceptionHandler {

    private String brace = "[";

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public DataResult errorHandler(HttpServletRequest req, Exception e) {
        DataResult response = DataResult.ok();
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
        response.setCode(CommonConstant.SYS_ERROR);
        log.error(message);
        e.printStackTrace();
        return response;
    }

}
