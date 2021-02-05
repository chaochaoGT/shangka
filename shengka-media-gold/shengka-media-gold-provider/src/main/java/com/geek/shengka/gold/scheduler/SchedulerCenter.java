package com.geek.shengka.gold.scheduler;

import com.geek.shengka.common.annotation.IgnoreClientToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *    调度定时器
 * 
 * @author xuxuelei
 *
 */
//@Component
@RestController
@RequestMapping(value = "/system/xlx")
@IgnoreClientToken
@EnableScheduling 
public class SchedulerCenter {
	private static final Logger logger = LoggerFactory.getLogger(SchedulerCenter.class);

 
	@Autowired
	private TaskWithdraw withdrawTask;
 
	
	 
	 /*
	  * 提现  6s
	  */
	 @Scheduled(fixedRate = 6000)
	    public void processDraw() {
			logger.debug("====================================定时提现任务启动----请求账务中心提现====================================");
			
			try {
				withdrawTask.handle();
			}catch(Exception e) {
				logger.error(e.getMessage() ,e);
			}
			
	    }
 
	 
	
	 
	 
	 
	 

}
