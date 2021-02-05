package com.geek.shengka.gold.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.*;

/**
 * 
 * 提现请求VO
 * <p style="display:none">modifyRecord</p>
 * <p style="display:none">version:V1.0,author:,date:2019-05-20 11:05:48, </p>
 * @author 
 * @date 2019-05-20 11:05:48
 * @since 
 * @version 
 *
 */

@Data
public class UserTaskRecordRequest{
    
	/** 用户参与任务记录表 id **/
    @NotNull
   private long taskRecordId;

}
