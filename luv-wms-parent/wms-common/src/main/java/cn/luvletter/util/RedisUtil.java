package cn.luvletter.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Zephyr Ji
 * @ Description: TODO
 * @ Date 2018/3/5
 */
@Component
public class RedisUtil {
    @Resource
    private RedisTemplate redisTemplate;

    public void hmSet(Object name, Object key, Object value){
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.put(name, key, value);
    }
    public Object hGet(Object name, Object key){
        if(name == null || key == null){
            return null;
        }
        HashOperations hashOperations = redisTemplate.opsForHash();
        return hashOperations.get(name, key);
    }
    public boolean hContainKey(Object name, Object key){
        HashOperations ops = redisTemplate.opsForHash();
        return ops.hasKey(name, key);
    }
    public long hRemoveByKey(Object name, Object key){
        HashOperations ops = redisTemplate.opsForHash();
        return ops.delete(name, key);
    }
}
