package com.tyy.myredis.redisTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class Main {

    @Autowired
    RedisTemplate<String,String> redisTemplate;



}
