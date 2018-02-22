package cn.luvletter;

import cn.luvletter.util.JdbcUtil;
import cn.luvletter.util.PropertyUtil;
import org.apache.commons.collections.MapUtils;
import org.junit.Test;

import java.sql.SQLException;
import java.util.*;

/**
 * @author Zephyr Ji
 * @ Description: TODO
 * @ Date 2018/2/22
 */
public class JdbcUtilTest {
    private static JdbcUtil jdbcUtil;
    static {
        jdbcUtil = new JdbcUtil(PropertyUtil.getProperty("driver"),
                PropertyUtil.getProperty("url"),
                PropertyUtil.getProperty("username"),
                PropertyUtil.getProperty("password"));

    }
    @Test
    public void test01() throws SQLException {
        List<Map> list = jdbcUtil.selectByParams("select permission_name,permission_url from sys_permission", null);
        for(Map<String,String> var : list) {
            for(Map.Entry<String,String> s:var.entrySet()){
                System.out.println(s.getKey());
                System.out.println(";");
                System.out.println(s.getValue());
            }
        }
    }
}
