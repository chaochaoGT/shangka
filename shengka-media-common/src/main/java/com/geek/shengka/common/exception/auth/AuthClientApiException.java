package com.geek.shengka.common.exception.auth;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthClientApiException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private int code ;
    private String message ;

    public AuthClientApiException(int code,String message){
        super(message);
        this.code = code;
        this.message = message;
    }



   

}
