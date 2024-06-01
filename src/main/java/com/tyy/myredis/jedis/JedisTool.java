package com.tyy.myredis.jedis;

import com.tyy.myredis.jedis.entity.Student;
import redis.clients.jedis.Jedis;

import java.util.HashMap;

public class JedisTool {
    public static String save(Jedis jedis, Student student){
        String id = student.getId();
        HashMap<String, String> hash = new HashMap<>();
        hash.put("id",student.getId());
        hash.put("name",student.getName());
        hash.put("age",String.valueOf(student.getAge()));
        hash.put("address",student.getAddress());
        String result = jedis.hmset(id, hash);
        return result;
    }
}
