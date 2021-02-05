package com.geek.shengka.content.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author hexinya
 * @description redis的工具类
 */
@Component
public class RedisUtilService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 默认过期时长，单位：秒
     */
    public static final long DEFAULT_EXPIRE = 60 * 60 * 24L;

    /**
     * 不设置过期时长
     */
    public static final long NOT_EXPIRE = -1;

    /**
     * 往zSet中插入元素
     *
     * @param key key
     * @param id id
     * @return
     */
    public Boolean zSetPush(String key, String id) {
        return zSetPush(key, id, (double) System.currentTimeMillis());
    }

    /**
     * 往zSet中插入元素
     *
     * @param key
     * @param id
     * @return
     */
    public Boolean zSetPush(String key, String id, Double b) {
        return redisTemplate.opsForZSet().add(key, id, b);
    }

    /**
     * 删除zSet中从start到end的数据（包含end）
     *
     * @param key
     * @return
     */
    public Long zSetRemove(String key, Long start, Long end) {
        return redisTemplate.opsForZSet().removeRange(key, start, end);
    }

    /**
     * 获取从start到end的数据（包含end）
     *
     * @param key
     * @return
     */
    public Set zSetRange(String key, Long start, Long end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    /**
     * 查看zSet中key值元素个数
     *
     * @param key
     * @return
     */
    public Long zSetSize(String key) {
        return redisTemplate.opsForZSet().zCard(key);
    }


    /**
     * 是否存在key
     *
     * @param key
     * @return
     */
    public boolean existsKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 重名名key，如果newKey已经存在，则newKey的原值被覆盖
     *
     * @param oldKey
     * @param newKey
     */
    public void renameKey(String oldKey, String newKey) {
        redisTemplate.rename(oldKey, newKey);
    }

    /**
     * newKey不存在时才重命名
     *
     * @param oldKey
     * @param newKey
     * @return 修改成功返回true
     */
    public boolean renameKeyNotExist(String oldKey, String newKey) {
        return redisTemplate.renameIfAbsent(oldKey, newKey);
    }

    /**
     * 删除key
     *
     * @param key
     */
    public void deleteKey(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 删除多个key
     *
     * @param keys
     */
    public void deleteKey(String... keys) {
        Set<String> kSet = Stream.of(keys).map(k -> k).collect(Collectors.toSet());
        redisTemplate.delete(kSet);
    }

    /**
     * 删除多个key
     *
     * @param key
     */
    public int deleteRegularKey(String key) {
        Set<String> keys = redisTemplate.keys(key + "*");
        redisTemplate.delete(keys);
        return keys.size();
    }

    /**
     * 模糊匹配key
     * @param key
     * @return
     */
    public Set<String> getKeys(String key){
        return redisTemplate.keys("*" + key + "*");
    }

    /**
     * 删除Key的集合
     *
     * @param keys
     */
    public void deleteKey(Collection<String> keys) {
        redisTemplate.delete(keys);
    }

    /**
     * 设置key的生命周期
     *
     * @param key
     * @param time
     * @param timeUnit
     */
    public void expireKey(String key, long time, TimeUnit timeUnit) {
        redisTemplate.expire(key, time, timeUnit);
    }

    /**
     * 指定key在指定的日期过期
     *
     * @param key
     * @param date
     */
    public void expireKeyAt(String key, Date date) {
        redisTemplate.expireAt(key, date);
    }

    /**
     * 查询key的生命周期
     *
     * @param key
     * @param timeUnit
     * @return
     */
    public long getKeyExpire(String key, TimeUnit timeUnit) {
        return redisTemplate.getExpire(key, timeUnit);
    }

    /**
     * 将key设置为永久有效
     *
     * @param key
     */
    public void persistKey(String key) {
        redisTemplate.persist(key);
    }


    /**
     * 存入redis并设置过期时间，劳苦时间为当天晚上凌晨
     *
     * @param key
     * @param value
     */
    public void pushStringAndexpire(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 自定义过期时间保存至redis
     *
     * @param key      redis的key值
     * @param value    redis的value值
     * @param timeout  超时时间
     * @param timeUnit 超时时间单位
     */
    public void pushWithTime(String key, String value, Long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value,timeout,timeUnit);
    }

    /**
     * 往redis里面放值
     *
     * @param key
     * @param value
     */
    public void pushString(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 从redis里面获取对应的值
     *
     * @param key
     * @return
     */
    public String getString(String key) {
        if (StringUtils.isNotEmpty(key)) {
            return redisTemplate.opsForValue().get(key);
        } else {
            return null;
        }
    }

}
