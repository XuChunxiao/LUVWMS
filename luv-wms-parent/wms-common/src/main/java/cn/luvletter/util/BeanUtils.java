package cn.luvletter.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author Zephyr Ji
 * @ Description: bean工具类
 * @ Date 2018/2/23
 */
public class BeanUtils {
    /**
     * @Description: map 转 bean
     * @Date: 21:24 2018/2/24
     */
    public static <T> T map2Bean(Map<String, Object> map, Class<T> clazz) {
        T bean = null;
        try {
            bean = clazz.newInstance();
            org.apache.commons.beanutils.BeanUtils.populate(bean,map);
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return bean;
    }
}
