package com.tyy.myredis;

import com.tyy.myredis.zset.SkipList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class MyRedisApplicationTests {

    @Autowired
    RedisTemplate<String,String> redisTemplate;
    @Test
    void contextLoads() {
        String name = "delayed queue";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (int i = 0; i <= 10; i++) {
            Calendar now = Calendar.getInstance();
            now.setTime(new Date());
            now.set(Calendar.MINUTE,now.get(Calendar.MINUTE)+i);
            System.out.println("当前时间:"+simpleDateFormat.format(System.currentTimeMillis()));
            System.out.println("消费时间:"+simpleDateFormat.format(now.getTime()));

            //第一个getTime()返回的是Date对象，第二个getTime()返回的是时间戳
            redisTemplate.opsForZSet().add(name,String.valueOf(i),now.getTime().getTime());

        }
    }

    @Test
    public void calendar(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(simpleDateFormat.format(calendar.getTime()));
        System.out.println(calendar.getTime());
        System.out.println(calendar.getTime().getTime());
        System.out.println(System.currentTimeMillis());

    }


}
