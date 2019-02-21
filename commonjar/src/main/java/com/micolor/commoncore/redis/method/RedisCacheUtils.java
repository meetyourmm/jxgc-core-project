package com.micolor.commoncore.redis.method;

import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * Project:logistics-single
 * Package:com.liheng.shock.commoncore.redis.method
 *
 * @Author: Evangoe
 * @Description: 用于操作redis数据库的操作类
 * @Date:09/07/17
 * @Modified:
 */
public class RedisCacheUtils {

    @Resource
    RedisTemplate<String,Object> redisTemplate;


    public void pushObject2Redis(String key,Object object){

    }

    public void deleteObjectFromRedis(String key){
        redisTemplate.delete("*");
    }

    public void updateObjectFromRedis(String key,Object object){
        
    }

}
