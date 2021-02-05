package com.geek.shengka.content.service.recommand.entity;

import com.geek.shengka.content.enums.RecommendEnum;

import lombok.Data;

@Data
public class GetNotInsertedParam {
	private String imei;
	private int num;
	private Long categoryId;
	private RecommendEnum total;
	private RecommendEnum userInserted;
	private RecommendEnum userDiff;
}
