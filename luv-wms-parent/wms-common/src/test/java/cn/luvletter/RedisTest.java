package cn.luvletter;

import cn.luvletter.config.RedisConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Zephyr Ji
 * @ Description: TODO
 * @ Date 2018/3/5
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RedisConfig.class)
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void test01(){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("wee","123456789");
        Long test1 = valueOperations.size("test1");
        System.out.println(test1);
        Object wee = valueOperations.get("wee");
        System.out.println(wee.toString());
    }
}
