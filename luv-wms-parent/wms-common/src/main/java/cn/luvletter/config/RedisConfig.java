package cn.luvletter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author Zephyr Ji
 * @ Description: redis 的一些bean 库位
 * @ Date 2018/3/6
 */
@Configuration
@PropertySource("classpath:/redis.properties")
public class RedisConfig {
    @Autowired
    private Environment env;
    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(env.getProperty("redis.maxIdle",Integer.class));
        poolConfig.setMaxTotal(env.getProperty("redis.maxActive",Integer.class));
        poolConfig.setMaxWaitMillis(env.getProperty("redis.maxWait",Long.class));
        poolConfig.setTestOnBorrow(env.getProperty("redis.testOnBorrow",Boolean.class));
        return poolConfig;
    }
    @Bean
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig){
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        connectionFactory.setHostName(env.getProperty("redis.host"));
        connectionFactory.setPort(env.getProperty("redis.port",Integer.class));
        connectionFactory.setPassword(env.getProperty("redis.pass"));
        connectionFactory.setDatabase(env.getProperty("redis.dbIndex",Integer.class));
        connectionFactory.setPoolConfig(jedisPoolConfig);
        return connectionFactory;
    }
    @Bean
    public RedisTemplate redisTemplate (JedisConnectionFactory jedisConnectionFactory){
        RedisTemplate template = new RedisTemplate();
        RedisSerializer stringSerializer = new StringRedisSerializer();
        template.setConnectionFactory(jedisConnectionFactory);
        template.setDefaultSerializer(stringSerializer);
        return template;
    }
    @Bean
    public RedisCacheManager redisCacheManager(RedisTemplate redisTemplate){
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        cacheManager.setDefaultExpiration(env.getProperty("redis.expiration",Long.class));
        return cacheManager;
    }
 }
