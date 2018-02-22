package cn.luvletter;

import cn.luvletter.util.ActiveUserUtil;
import org.junit.Test;

/**
 * @author Zephyr Ji
 * @ Description: TODO
 * @ Date 2018/2/11
 */
public class ActiveUserUtilTest {
    @Test
    public void test01(){
        String account = ActiveUserUtil.getAccount();
        System.out.println(account);
        String ip = ActiveUserUtil.getIp();
        System.out.println(ip);
    }
}
