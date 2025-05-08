package com.piecepost.cache.services.redis;

import java.util.concurrent.TimeUnit;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate<String,Object> redisTemplate;
    public void set(String key, String value) {
        StringBuilder sb = new StringBuilder();
        sb.append("Set Value Key:").append(key).append(" Value:").append(value);
        System.err.println(sb);
        redisTemplate.opsForValue().set(key, value);
    }
    public void setEX(String key, String value, long duration,TimeUnit timeUnit) {
        StringBuilder sb = new StringBuilder();
        sb.append("Set Ex Value Key:").append(key).append(" Value:").append(value).append(" Duration:").append(duration).append(" TimeUnit:").append(timeUnit);
        System.err.println(sb);
        redisTemplate.opsForValue().set(key, value,duration,timeUnit);
    }
    public Object get(String key){
        StringBuilder sb = new StringBuilder();
        sb.append("Get Value Key:").append(key);
        System.err.println(sb);
        return redisTemplate.opsForValue().get(key);
    }
    public Object incr(String key){
        StringBuilder sb = new StringBuilder();
        sb.append("Incr Value Key:").append(key);
        System.err.println(sb);
        return redisTemplate.opsForValue().increment(key);
    }
    public Object expire(String key,long duration,TimeUnit timeUnit){
        StringBuilder sb = new StringBuilder();
        sb.append("Expire Value Key:").append(key).append(" TTL:").append(duration).append(" TimeUnit:").append(timeUnit);;
        System.err.println(sb);
        return redisTemplate.opsForValue().getAndExpire(key,duration,timeUnit);
    }
    public boolean setIfAbsent(String key,Object value,long duration,TimeUnit timeUnit){
        return redisTemplate.opsForValue().setIfAbsent(key, value,duration,timeUnit);
    }
    public boolean setIfPresent(String key,Object value,long duration,TimeUnit timeUnit){
        return redisTemplate.opsForValue().setIfPresent(key, value,duration,timeUnit);
    }
    public Long duration(String key){
        return redisTemplate.getExpire(key);
    }
    public boolean delete(String key){
        return redisTemplate.delete(key);
    }
}
