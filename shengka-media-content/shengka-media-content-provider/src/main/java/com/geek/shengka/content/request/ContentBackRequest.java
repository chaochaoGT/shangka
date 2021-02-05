package com.geek.shengka.content.request;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class ContentBackRequest {
    /**内容id*/
    @NotNull
    private String contentId;
    /**内容类型（1-视频 2-语音）*/
    @NotNull
	private int contentType;
    /**状态（1-审核通过 2-审核失败 3-被删除）*/
    @NotNull
	private int state; 
    /**原因描述*/
    private String reason;
	 
}
