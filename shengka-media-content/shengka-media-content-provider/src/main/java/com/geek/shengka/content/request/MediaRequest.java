package com.geek.shengka.content.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MediaRequest {


    private long userId;
	private int pageSize; // 取多少条
	private int pageIndex; // 第几条开始
//    private String imei;
//    private String channel;
	 
}
