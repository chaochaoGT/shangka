package com.geek.shengka.content.request;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class ContentVoiceRequest {
    
	/** 视频类别**/
	private int pageSize;
    @NotNull
	private int pageNumber;
	private String lastIndexId;
    @NotNull
	private String videoId;
}
