package com.example.springsecuritydemo.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisTemplateConfig {
    @Bean("sysMyRedisTemplate")
    public <T> RedisTemplate<String, T> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, T> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        ObjectMapper om = new ObjectMapper();
        // 持久化改动.设置可见性,
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 持久化改动.非final类型的对象，把对象类型也序列化进去，以便反序列化推测正确的类型
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        // 持久化改动.null字段不显示
        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 持久化改动.POJO无public属性或方法时不报错
        om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 持久化改动.setObjectMapper方法移除.使用构造方法传入ObjectMapper
        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer(om);
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}

