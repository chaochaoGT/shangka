package com.geek.shengka.content.config; 
  
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "recommend")
@Data
public class RecommendConfig{ 
	  private int redisTotalNum=200;	//redis中存放的总库记录数量
    /**内容中心默认页*/
    private  int defaultPageSize=5;
}