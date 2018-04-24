package cn.luvletter.security;



import cn.luvletter.bean.AuthBean;
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
        List<AuthBean> authBeans = JdbcUtil.newInstance().selectBean(SqlConstant.SELECT_ALL_AUTH, null, AuthBean.class);
        Collection<ConfigAttribute> array;
        ConfigAttribute cfg;
        urlMap = new HashMap<>();
        for(AuthBean authBean : authBeans){
            String url = authBean.getUrl();
            if(urlMap.containsKey(url)){
                cfg = new SecurityConfig(authBean.getName());
                urlMap.get(url).add(cfg);
                continue;
            }
            array = new ArrayList<>();
            cfg = new SecurityConfig(authBean.getName());
            array.add(cfg);
            urlMap.put(url,array);
        }
        log.debug("load Permission Resource Define\n"+urlMap.toString());
    }


    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if(urlMap == null || urlMap.isEmpty()) {
            try {
                loadResourceDefine();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        log.debug("Request '" + request.getMethod() + " " + request.getServletPath()+request.getPathInfo() + "'" + " start match database url '");
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
