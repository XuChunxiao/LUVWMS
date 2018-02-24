package cn.luvletter.filter;

import cn.luvletter.bean.AuthenticationBean;
import cn.luvletter.exception.ApplicationException;
import cn.luvletter.security.service.OprtService;
import cn.luvletter.util.JWTUtil;
import cn.luvletter.util.JdbcUtil;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/**
 * @author Zephyr Ji
 * @ Description: TODO
 * @ Date 2018/2/13
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String token = JWTUtil.getToken(httpServletRequest);
        if(StringUtils.isNotEmpty(token)) {
            String username = JWTUtil.getUsernameFromToken(token);
            if (username != null) {
                AuthenticationBean authenticationBean = OprtService.loadOprt(username);
                if(authenticationBean == null){
                    throw new ApplicationException("username:"+username+"not found");
                }
                String password = authenticationBean.getPassword();
                if (JWTUtil.validateToken(token, password)) {
                    UsernamePasswordAuthenticationToken authentication = null;
                    try {
                        authentication = new UsernamePasswordAuthenticationToken(username, password, getAuthentication(username));
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                                httpServletRequest));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
    public Collection<? extends GrantedAuthority> getAuthentication(String no) throws SQLException {
        List<Map<String, Object>> list = JdbcUtil.newInstance().selectByParams("select sr.role_name authority,u.no username\n" +
                "        from sys_operator u\n" +
                "        LEFT JOIN sys_oprt_role sor on u.no= sor.oprt_no\n" +
                "        LEFT JOIN sys_role sr on sor.role_no=sr.role_no\n" +
                "        LEFT JOIN sys_role_permission srp on srp.role_no=sr.role_no\n" +
                "        LEFT JOIN sys_permission sp on sp.permission_no =srp.permission_no\n" +
                "        where u.no=?", Arrays.asList(no));

        List<SimpleGrantedAuthority> v2 = new ArrayList<>();
        SimpleGrantedAuthority v3 = null;
        for(Map<String,Object> v : list){
            v3 = new SimpleGrantedAuthority((String)v.get("authority"));
            v2.add(v3);
        }
        return v2;
    }
}
