package com.geek.shengka.content.enums;

import lombok.Getter;

/**
 * 视频推荐枚举，key和timeout
 *
 * @author: lifukang
 * @create: 2019-07-31 18:07
 **/
@Getter
public enum  RecommendEnum{

    /**小视频总库，1小时，单位毫秒*/
	SMALLVIDEOTOTAL("smallvideototal",24*60*60*1000L),
    /**短视频总库，1小时，单位毫秒*/
	SHORTVIDEOTOTAL("shortvideototal",24*60*60*1000L),
    /**分类短视频总库,1小时，单位毫秒*/
	CATEGORYSHORTVIDEOTOTAL("categoryshortvideototal:%s",24*60*60*1000L),
	
    /**用户已插入小视频库,60天，单位毫秒*/
	USERINSERTEDSMALLVIDEO("user:insertedsmallvideo:%s",60*24*60*60*1000L),
    /**用户已插入短视频库,60天，单位毫秒*/
	USERINSERTEDSHORTVIDEO("user:insertedshortvideo:%s",60*24*60*60*1000L),
    /**用户已插入分类短视频库,60天，单位毫秒*/
	USERINSERTEDCATGORYSHORTVIDEO("user:insertedcatgoryshortvideo:%s:%s",60*24*60*60*1000L),
	
    /**用户小视频总库和用户已插入小视频库求差集，2分钟，单位毫秒*/
	USERSMALLVIDEODIFF("user:smallvideo:diff:%s",2*60*1000L),
    /**用户短视频总库和用户已插入短视频库求差集，2分钟，单位毫秒*/
	USERSHORTVIDEODIFF("user:shortvideo:diff:%s",2*60*1000L),	
    /**用户分类短视频总库和用户已插入分类短视频库求差集，2分钟，单位毫秒*/
	USERCATEGORYSHORTVIDEODIFF("user:categoryshortvideo:diff:%s:%s", 2*60*1000L);
    
	
	RecommendEnum(String key,long expireTime){
        this.key=key;
        this.expireTime=expireTime;
    }
	
    private String key;
    private long expireTime;
    
}
