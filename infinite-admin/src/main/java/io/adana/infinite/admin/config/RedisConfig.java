package io.adana.infinite.admin.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author sakura
 * @version 1.1
 * @date 2020-02-19 10:45
 * @description RedisConfig
 * @Scope infinite-admin
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        return RedisCacheManager.create(factory);
    }

    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // connect to the factory.
        template.setConnectionFactory(factory);
        // the JDK's default serialization.
        Jackson2JsonRedisSerializer<Object> jackson2Serializer = new Jackson2JsonRedisSerializer<>(Object.class);

        ObjectMapper objectMapper = new ObjectMapper();
        // allocate property descriptors of type,ALL which includes the keywords of field,get, set and decorated symbol;
        // ANY contains the keywords of private and public.
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // set the type of serialized inputting, the class must be decorated by non-final.
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.WRAPPER_ARRAY);
        jackson2Serializer.setObjectMapper(objectMapper);
        // it use the type of json to serialize.
        template.setValueSerializer(jackson2Serializer);
        // use the class StringRedisSerializer to serialize the key in redis or deserialize.
        template.setKeySerializer(new StringRedisSerializer());

        // config the setting of which mode is hash key and hash value.
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(jackson2Serializer);
        template.afterPropertiesSet();
        return template;
    }

    /**
     * operate the data struct of type which is hash.
     *
     * @param redisTemplate {@link org.springframework.data.redis.core.RedisTemplate}
     * @return {@link HashOperations}
     */
    @Bean
    public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForHash();
    }

    /**
     * operate the data of the type which is String.
     *
     * @param redisTemplate {@link RedisTemplate}
     * @return {@link ValueOperations}
     */
    @Bean
    public ValueOperations<String, Object> valueOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForValue();
    }

    /**
     * operate the data of the type which is list
     *
     * @param redisTemplate {@link RedisTemplate}
     * @return {@link ListOperations}
     */
    @Bean
    public ListOperations<String,Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForList();
    }

    /**
     * operate the data of the type which is set for no sorted.
     *
     * @param redisTemplate {@link RedisTemplate}
     * @return {@link SetOperations}
     */
    @Bean
    public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForSet();
    }

    /**
     * operate the data of the type which is ZSet for sorted.
     *
     * @param redisTemplate {@link RedisTemplate}
     * @return {@link ZSetOperations}
     */
    @Bean
    public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForZSet();
    }
}
