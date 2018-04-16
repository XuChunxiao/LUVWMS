package cn.luvletter.security;



import cn.luvletter.constant.SqlConstant;
import cn.luvletter.util.JdbcUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.*;

/**
 * @author Zephyr Ji
 * @ Description: 添加全部url权限信息
 * @ Date 2018/2/10
 */
@Component
public class URLInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {
    private Logger log = LoggerFactory.getLogger(URLInvocationSecurityMetadataSourceService.class);

    private Map<String, Collection<ConfigAttribute>> urlMap =null;


    private void loadResourceDefine() throws SQLException {
        List<Map<String,Object>> list = JdbcUtil.newInstance().selectByParams(SqlConstant.SELECT_ALL_AUTH, null);
        urlMap = new HashMap<>();
        Collection<ConfigAttribute> array;
        ConfigAttribute cfg;
        StringBuilder logStr = new StringBuilder();
        for(Map<String,Object> var : list) {
            array = new ArrayList<>();
            String name = (String)var.get("name");
            String url = (String)var.get("url");
            cfg = new SecurityConfig(name);
            //此处只添加了名字，其实还可以添加更多权限的信息，例如请求方法到ConfigAttribute的集合中去。
            // 此处添加的信息将会作为MyAccessDecisionManager类的decide的第三个参数。
            array.add(cfg);
            //用权限的getUrl() 作为map的key，用ConfigAttribute的集合作为 value，
            urlMap.put(url, array);
            logStr.append("name:").append(name).append("url:").append(url).append("\n");
        }
        log.debug("load Permission Resource Define\n"+logStr.toString());
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
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        log.debug("Request '" + request.getMethod() + " " + request.getServletPath()+request.getPathInfo() + "'" + " start match DB url '");
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
