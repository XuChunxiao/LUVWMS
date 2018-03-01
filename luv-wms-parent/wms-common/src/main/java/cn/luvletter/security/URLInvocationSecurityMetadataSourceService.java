package cn.luvletter.security;



import cn.luvletter.constant.SqlConstant;
import cn.luvletter.util.JdbcUtil;
import cn.luvletter.util.PropertyUtil;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.*;

/**
 * @author Zephyr Ji
 * @ Description: 添加全部url权限信息
 * @ Date 2018/2/10
 */

public class URLInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {
    private static JdbcUtil jdbcUtil;

    private Map<String, Collection<ConfigAttribute>> urlMap =null;


    private void loadResourceDefine() throws SQLException {
        List<Map<String,Object>> list = JdbcUtil.newInstance().selectByParams(SqlConstant.SELECT_ALL_AUTH, null);
        urlMap = new HashMap<>();
        Collection<ConfigAttribute> array;
        ConfigAttribute cfg;
        for(Map<String,Object> var : list) {
            array = new ArrayList<>();
            cfg = new SecurityConfig((String)var.get("name"));
            //此处只添加了用户的名字，其实还可以添加更多权限的信息，例如请求方法到ConfigAttribute的集合中去。此处添加的信息将会作为MyAccessDecisionManager类的decide的第三个参数。
            array.add(cfg);
            //用权限的getUrl() 作为map的key，用ConfigAttribute的集合作为 value，
            urlMap.put((String)var.get("url"), array);
        }
    }


    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if(urlMap ==null) {
            try {
                loadResourceDefine();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //object 中包含用户请求的request 信息
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        AntPathRequestMatcher matcher;
        String resUrl;
        for (String s : urlMap.keySet()) {
            resUrl = s;
            matcher = new AntPathRequestMatcher(resUrl);
            if (matcher.matches(request)) {
                return urlMap.get(resUrl);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
