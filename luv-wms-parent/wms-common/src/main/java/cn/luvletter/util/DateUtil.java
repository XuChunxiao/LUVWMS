package cn.luvletter.util;

import org.apache.commons.lang.time.DateUtils;

import java.util.Date;

/**
 * @author Zephyr Ji
 * @ Description: TODO
 * @ Date 2018/2/24
 */
public class DateUtil extends DateUtils {
    public static Date now(){
        return new Date(System.currentTimeMillis());
    }
}
