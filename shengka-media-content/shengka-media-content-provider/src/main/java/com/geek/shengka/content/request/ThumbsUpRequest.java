package com.geek.shengka.content.request;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * sk_thumbs_up
 * @author 
 */
@Data
public class ThumbsUpRequest implements Serializable {
 
    /**  点赞资源id */
    @NotNull
    private String voiceId;
    /**  0-点赞，1-取消点赞 */
    @NotNull
    private int voiceType;
    
   
 
 
}