package com.geek.shengka.content.response;

import java.io.Serializable;
import lombok.Data;

@Data
public class VoiceParam implements Serializable{
	private static final long serialVersionUID = -7109849181565054895L;
	   
    private String userIds;
    private String voiceIds;
    
}
