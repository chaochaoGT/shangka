package com.geek.shengka.content.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ContentDeleteVideoRequest {
    
	private String videoId;
	private String status; 
}
