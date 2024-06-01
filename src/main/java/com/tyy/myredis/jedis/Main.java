package com.tyy.myredis.jedis;

import com.tyy.myredis.jedis.entity.Student;
import redis.clients.jedis.Jedis;

public class Main {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.6.100",6379);
        String set = jedis.set("hello", "jedis");
        System.out.println("set = " + set);
        String s = jedis.get("hello");
        System.out.println("s = " + s);
        /*
        * TODO 我们在java代码中经常需要封装对象，然后存储到redis中
        *  中间涉及到 redis数据类型和Java对象的转换
        *  这个东西手动写一个工具类去封装其实也不是很难
        *  但是涉及到特别多的对象的情况下，工作量和难度就不同了
        *  所以，总的来说，jedis操作redis，还是不太方便
        *  所以使用 RedisTemplate
        * */

        Student student = new Student("2104010719", "tyy", 22, "jiangxi");
        System.out.println("student = " + student);

        /*
        * TODO 我要使用工具类把student对象封装到redis中去
        *  用hash数据结构来存储
        * */

        String result = JedisTool.save(jedis, student);
        System.out.println("result = " + result);

        jedis.close();
    }
}
