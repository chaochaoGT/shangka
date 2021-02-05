package com.geek.shengka.gold.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description: 每日任务领取金币
 * @author: xuxuelei
 * @create: 2019-06-04 17:29
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserTaskRecordNoticeMqRequest implements Serializable {

	private static final long serialVersionUID = -1115072486165042777L;

	private long taskConfigId;
	
	private long userId;
	
	private long timeInMilliseconds ;

}
