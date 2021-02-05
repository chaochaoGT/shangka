package com.geek.shengka.gold.service.impl;

import com.geek.shengka.common.cache.CacheProvider;
import com.geek.shengka.common.util.DateUtils;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @description: redis 工具类
 * @author: yunfei
 * @create: 2019-06-04 17:00
 **/
@Component
public class RedisManageServiceImpl {


    /**宝箱业务流水号*/
    private static final String SK_TREASURE_BOX_ORDERNO="sk_treasure_box:orderNo";
    

    /**宝箱业务流水号*/
    private static final String SK_TASK_ORDERNO="sk_task:orderNo";



    /**
     * 操作临时缓存
     *
     * @param key
     * @param userId
     * @param num
     */
    public void setUserValue(String key, long userId, int num,long expire) {
        String paramsKey = new StringBuffer().append(key).append(DateUtils.getFormatNow()).append(":").append(userId).toString();
        CacheProvider.setObject(paramsKey, num, expire);
    }

    /**
     * 操作临时缓存
     *
     * @param key
     * @param userId
     * @param num
     */
    public Long incUserValue(String key, long userId, int num,long expire) {
        String paramsKey = new StringBuffer().append(key).append(DateUtils.getFormatNow()).append(":").append(userId).toString();
        Long increment = CacheProvider.getTemplate().opsForValue().increment(paramsKey, num);
        CacheProvider.getTemplate().expire(paramsKey, expire, TimeUnit.MILLISECONDS);
        return increment;
    }

    /**
     * 操作临时缓存
     *
     * @param key
     * @param userId
     * @return
     */
    public String getUserValue(String key, long userId) {
        String paramsKey = new StringBuffer().append(key).append(DateUtils.getFormatNow()).append(":").append(userId).toString();
        return CacheProvider.get(paramsKey);
    }

    /**
     * 获取流水号
     */
    public String getOrderNo() {
        String paramsKey = new StringBuffer().append(SK_TREASURE_BOX_ORDERNO).append(":").append(DateUtils.getFormatNow()).toString();
        Long increment = CacheProvider.getTemplate().opsForValue().increment(paramsKey, 1L);
        CacheProvider.getTemplate().expire(paramsKey, 24, TimeUnit.HOURS);
        return "sk_box" + System.currentTimeMillis() + increment;
    }
    
    public String getUserTaskOrderNo() {
        String paramsKey = new StringBuffer().append(SK_TASK_ORDERNO).append(":").append(DateUtils.getFormatNow()).toString();
        Long increment = CacheProvider.getTemplate().opsForValue().increment(paramsKey, 1L);
        CacheProvider.getTemplate().expire(paramsKey, 24, TimeUnit.HOURS);
        return "sk_task" + System.currentTimeMillis() + increment;
    }
        

}
