package com.geek.shengka.content.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ContentDeleteVoiceRequest {
    
	private String audioId;
	private String status; 
}
