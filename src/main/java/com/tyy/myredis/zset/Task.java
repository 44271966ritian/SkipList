package com.tyy.myredis.zset;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Set;

@Component
public class Task {

    @Autowired
    RedisTemplate<String,String> redisTemplate;


    //TODO 每秒执行一次
    @Scheduled(cron = "*/1 * * * * * ")
    public void execute(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("等待消费中...");
        //从redisTemplate中读取知道当前时间戳为止的数据
        //这里读出来的是值集合（不是分数）
        Set<String> delayedQueue = redisTemplate.opsForZSet().rangeByScore("delayed queue", 0, System.currentTimeMillis());
        Iterator<String> iterator = delayedQueue.iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            Double timestamp = redisTemplate.opsForZSet().score("delayed queue",key);
            if(System.currentTimeMillis()>timestamp){
                System.out.println("时间到了,消费数据:"+key);
                redisTemplate.opsForZSet().remove("delayed queue",key);
            }
        }
    }

}
