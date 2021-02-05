package com.geek.shengka.common.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleCacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.lang.reflect.Method;

/**
 * @Description:缓存配置-使用Lettuce客户端，自动注入配置的方式 用于调优缓存默认配置
 */

@Slf4j
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    /**
     * 设置统一的生成key的方式
     * 自定义缓存key的生成策略。默认的生成策略是看不懂的(乱码内容) 
     * 通过Spring 的依赖注入特性进行自定义的配置注入并且此类是一个配置类可以更多程度的自定义配置
     * @return
     */
    @Bean
    public KeyGenerator wiselyKeyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append("-");
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }

    /**
     * 缓存配置管理器
     */
    @Bean
    public CacheManager cacheManager(LettuceConnectionFactory factory) {
        //以锁写入的方式创建RedisCacheWriter对象
        RedisCacheWriter writer = RedisCacheWriter.lockingRedisCacheWriter(factory);
        /*
        设置CacheManager的Value序列化方式为JdkSerializationRedisSerializer,
        但其实RedisCacheConfiguration默认就是使用
        StringRedisSerializer序列化key，
        JdkSerializationRedisSerializer序列化value,
        所以以下注释代码就是默认实现，没必要写，直接注释掉
         */
        // RedisSerializationContext.SerializationPair pair = RedisSerializationContext.SerializationPair.fromSerializer(new JdkSerializationRedisSerializer(this.getClass().getClassLoader()));
        // RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);
        //创建默认缓存配置对象
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        RedisCacheManager cacheManager = new RedisCacheManager(writer, config);
        return cacheManager;
    }
    
    /**
     * 错误处理
     * @return
     */
    @Bean
    public CacheErrorHandler errorHandler () {
        return new SimpleCacheErrorHandler() {
            @Override
            public void handleCacheGetError (RuntimeException exception , Cache cache , Object key ) {
                log.error( "cache : {} , key : {}" , cache , key );
                log.error( "handleCacheGetError" , exception );
                super.handleCacheGetError( exception , cache , key );
            }

            @Override
            public void handleCachePutError (RuntimeException exception , Cache cache , Object key ,
                                             Object value ) {
                log.error( "cache : {} , key : {} , value : {} " , cache , key ,
                        value
                );
                log.error( "handleCachePutError" , exception );
                super.handleCachePutError( exception , cache , key , value );
            }

            @Override
            public void handleCacheEvictError (RuntimeException exception , Cache cache , Object key ) {
                log.error( "cache : {} , key : {}" , cache , key );
                log.error( "handleCacheEvictError" , exception );
                super.handleCacheEvictError( exception , cache , key );
            }

            @Override
            public void handleCacheClearError ( RuntimeException exception , Cache cache ) {
                log.error( "cache : {} " , cache );
                log.error( "handleCacheClearError" , exception );
                super.handleCacheClearError( exception , cache );
            }
        };
    }

    /**
     * 设置统一序列化
     * 获取缓存操作助手对象
     * @param factory
     * @return
     */
    @DependsOn
    @Bean
    public RedisTemplate<String, String> redisTemplate(LettuceConnectionFactory factory) {
    	//创建Redis缓存操作助手RedisTemplate对象
    	//StringRedisTemplate是RedisTempLate<String, String>的子类
        StringRedisTemplate template = new StringRedisTemplate(factory);
        //以下代码为将RedisTemplate的Value序列化方式由JdkSerializationRedisSerializer更换为Jackson2JsonRedisSerializer
        //此种序列化方式结果清晰、容易阅读、存储字节少、速度快，所以推荐更换
        /*Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);*/
        //template.setKeySerializer(new StringRedisSerializer());//RedisTemplate对象需要指明Key序列化方式，如果声明StringRedisTemplate对象则不需要
        //template.setEnableTransactionSupport(true);//是否启用事务
        //template.afterPropertiesSet();
        return template;
    }

}
