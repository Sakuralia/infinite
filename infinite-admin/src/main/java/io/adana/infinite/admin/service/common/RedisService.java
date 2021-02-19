package io.adana.infinite.admin.service.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author sakura
 * @version 1.1
 * @date 2020-02-19 14:42
 * @description RedisService
 * @Scope infinite-admin
 */
@Component
public class RedisService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public static final long DEFAULT_EXPIRE = 60 * 60 * 24;

    public static final long NOT_EXPIRE = -1;

    /**
     * get the key is exist or not.
     *
     * @param key key which store in redis
     * @return {@link Boolean}
     */
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * rename the key in addition to the newKey not exists
     *
     * @param oldKey resource value
     * @param newKey destination value
     */
    public void renameKey(String oldKey, String newKey) {
        redisTemplate.rename(oldKey, newKey);
    }

    /**
     * rename the key when new key is not existed.
     *
     * @param oldKey resource value
     * @param newKey destination value
     * @return {@link Boolean}
     */
    public boolean renameKeyHasNot(String oldKey, String newKey) {
        return redisTemplate.renameIfAbsent(oldKey, newKey);
    }

    /**
     * remove the key
     *
     * @param key key which stored in redis
     */
    public void deleteKey(String key) {
        redisTemplate.delete(key);
    }

    /**
     * remove the key in batch
     *
     * @param keys some keys which stored in redis
     */
    public void deleteKey(String... keys) {
        Set<String> keySet = Stream.of(keys).collect(Collectors.toSet());
        redisTemplate.delete(keySet);
    }

    /**
     * the key expire in the time which set the value.
     *
     * @param key key which stored in redis
     * @param timeout  timeout.
     * @param timeUnit the unit of time.
     */
    public void expireKey(String key, long timeout, TimeUnit timeUnit) {
        redisTemplate.expire(key, timeout, timeUnit);
    }

    /**
     * the key expire in the time
     *
     * @param key key which stored in redis
     * @param date {@link Date}
     */
    public void expireKeyInTime(String key, Date date) {
        redisTemplate.expireAt(key, date);
    }

    /**
     * get the life of key.
     *
     * @param key key stored in redis
     * @param timeUnit the unit of time
     * @return {@link java.lang.Long}
     */
    public long getKeyExpire(String key, TimeUnit timeUnit) {
        return redisTemplate.getExpire(key,timeUnit);
    }

    /**
     * permanent key
     *
     * @param key key stored in redis
     */
    public void persistKey(String key) {
        redisTemplate.persist(key);
    }
}
