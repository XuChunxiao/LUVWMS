import cn.luvletter.bean.AuthBean;
import cn.luvletter.constant.SqlConstant;
import cn.luvletter.util.JdbcUtil;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author Zephyr
 * @Description:
 * @Date 2018/4/24
 */
public class AuthTest {
    @Test
    public void test01(){
        List<AuthBean> authBeans = JdbcUtil.newInstance().selectBean(SqlConstant.SELECT_ALL_AUTH, null, AuthBean.class);
        Collection<ConfigAttribute> array;
        ConfigAttribute cfg;
        Map<String,Collection<ConfigAttribute>> authMap = new HashMap<>();
        for(AuthBean authBean : authBeans){
            String url = authBean.getUrl();
            if(authMap.containsKey(url)){
                cfg = new SecurityConfig(authBean.getName());
                authMap.get(url).add(cfg);
                continue;
            }
            array = new ArrayList<>();
            cfg = new SecurityConfig(authBean.getName());
            array.add(cfg);
            authMap.put(url,array);
        }
        System.out.println(authMap);
    }
}
