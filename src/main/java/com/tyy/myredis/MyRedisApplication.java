package com.tyy.myredis;

import io.lettuce.core.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Set;

@SpringBootApplication
@EnableScheduling
public class MyRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyRedisApplication.class, args);
    }

//    @Autowired
//    RedisTemplate<String,String> redisTemplate;
//
//    /**
//     * @param
//     * @method 每间隔1秒执行一次
//     */
//    @Scheduled(cron = "*/1 * * * * * ")
//    public void cs() {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println("------------等待消费--------------");
//        //取出QUEUENAME集合中的score在0-当前时间戳这个范围的所有值
//        Set<String> set = redisTemplate.opsForZSet().rangeByScore("delayed queue", 0, System.currentTimeMillis());
//        Iterator<String> iterator = set.iterator();
//        while (iterator.hasNext()) {
//            Integer value = Integer.valueOf(iterator.next());
//            //遍历取出每一个score
//            Double score = redisTemplate.opsForZSet().score("delayed queue", String.valueOf(value));
//            //达到了时间就进行消费
//            if (System.currentTimeMillis() > score) {
//                System.out.println("消费了：" + value + "消费时间为：" + simpleDateFormat.format(System.currentTimeMillis()));
//                redisTemplate.opsForZSet().remove("delayed queue",String.valueOf(value));
//
//            }
//        }
//
//
//    }



}
