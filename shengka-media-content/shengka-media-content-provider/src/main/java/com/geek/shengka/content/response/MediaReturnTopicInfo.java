package com.geek.shengka.content.response;

import java.io.Serializable;

import lombok.Data;

@Data
public class MediaReturnTopicInfo implements Serializable{
	private static final long serialVersionUID = -7109849181565054895L;
 
    /** 话题id **/
    private long id;
    /** 话题名称 **/
    private String topicName;
    
}
