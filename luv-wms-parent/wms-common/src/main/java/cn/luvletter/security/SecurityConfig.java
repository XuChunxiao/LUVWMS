package cn.luvletter.security;

import cn.luvletter.filter.CustomAuthenticationFilter;
import cn.luvletter.filter.JwtAuthenticationTokenFilter;
import cn.luvletter.security.handler.AjaxAccessDeniedHandler;
import cn.luvletter.security.handler.AjaxAuthFailHandler;
import cn.luvletter.security.handler.AjaxAuthSuccessHandler;
import cn.luvletter.security.handler.AjaxAuthenticationEntryPoint;
import cn.luvletter.util.PropertyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;
import java.util.Properties;


/**
 * @author Zephyr Ji
 * @ Description: spring security 配置程序
 * @ Date 2018/2/10
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private URLFilterSecurityInterceptor urlFilterSecurityInterceptor;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private AjaxAuthSuccessHandler ajaxAuthSuccessHandler;

    @Autowired
    private AjaxAuthenticationEntryPoint ajaxAuthenticationEntryPoint;

    @Autowired
    private AjaxAuthFailHandler ajaxAuthFailHandler;

    @Autowired
    private DataSource dataSource;

//    static {
//        dataSource = DataSourceUtils.
//        dataSource = new BasicDataSource();
//        dataSource.setDriverClassName(PropertyUtil.getProperty("driver"));
//        dataSource.setUrl(PropertyUtil.getProperty("url"));
//        dataSource.setUsername(PropertyUtil.getProperty("username"));
//        dataSource.setPassword(PropertyUtil.getProperty("password"));
//    }

    /**
     * @Description:    以jdbc形式获取用户角色和权限
     * @Date: 14:44 2018/2/11
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT a.NO username,a.PASSWORD password,if(a.FLAG=1,true,false)" +
                        " from sys_operator a where a.NO = ?")
                .authoritiesByUsernameQuery("select sp.permission_name authority,u.no username\n" +
                        "        from sys_operator u\n" +
                        "        LEFT JOIN sys_oprt_role sor on u.no= sor.oprt_no\n" +
                        "        LEFT JOIN sys_role sr on sor.role_no=sr.role_no\n" +
                        "        LEFT JOIN sys_role_permission srp on srp.role_no=sr.role_no\n" +
                        "        LEFT JOIN sys_permission sp on sp.permission_no =srp.permission_no\n" +
                        "        where u.no=?")
                .passwordEncoder(new AESPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/common/**").permitAll()
                .anyRequest().authenticated() //任何请求,登录后可以访问
                .and().formLogin()
                .loginPage("/")
                .loginProcessingUrl("/login")
                .usernameParameter("account")
                .successHandler(ajaxAuthSuccessHandler)
                .failureHandler(ajaxAuthFailHandler)
                .permitAll() //登录页面用户任意访问
                .and().logout().permitAll()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().exceptionHandling().authenticationEntryPoint(ajaxAuthenticationEntryPoint)
                .accessDeniedHandler(new AjaxAccessDeniedHandler())
                //关闭csrf
                .and().csrf().disable();
        //在身份验证前添加token验证
        http.addFilterBefore(jwtAuthenticationTokenFilter,UsernamePasswordAuthenticationFilter.class);
        //替换身份验证过滤器，就是替换账号密码获取方式
        http.addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        //在权限筛选前添加自定义的url方式筛选
        http.addFilterBefore(urlFilterSecurityInterceptor,FilterSecurityInterceptor.class);
    }
    @Bean
    CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(ajaxAuthSuccessHandler);
        filter.setAuthenticationFailureHandler(ajaxAuthFailHandler);
        filter.setFilterProcessesUrl("/login");

        //这句很关键，重用WebSecurityConfigurerAdapter配置的AuthenticationManager，不然要自己组装AuthenticationManager
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }
}
