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

    @Test
    public void test01() throws SQLException {
        List<Map<String,Object>> list = JdbcUtil.newInstance().selectByParams("select permission_name,permission_url from sys_permission", null);
        for(Map<String,Object> var : list) {
            for(Map.Entry<String,Object> s:var.entrySet()){
                System.out.println(s.getKey());
                System.out.println(";");
                System.out.println(s.getValue());
            }
        }
    }
    @Test
    public void test02() throws SQLException {
        List<UrlBean> list = JdbcUtil.newInstance().selectBean("select permission_name name,permission_url url from sys_permission", null,UrlBean.class);
        for(UrlBean urlBean : list){
            System.out.println(urlBean.toString());
        }
    }
}
