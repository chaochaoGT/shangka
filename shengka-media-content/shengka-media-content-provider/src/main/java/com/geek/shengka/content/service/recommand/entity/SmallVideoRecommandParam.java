package com.geek.shengka.content.service.recommand.entity;

import lombok.Data;

@Data
public class SmallVideoRecommandParam {
	private String imei;	//imei
	private int pageSize;  //取多少条
	private int pageIndex;  //第几条开始
}
