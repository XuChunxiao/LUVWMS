package cn.luvletter.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

/**
 * @author Zephyr Ji
 * @ Description: 获取当前用户信息
 * @ Date 2018/2/11
 */
public class ActiveUserUtil {

    public static String getAccount() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        return userDetails.getUsername();
    }

    public static String getIp() {
        WebAuthenticationDetails wauth = (WebAuthenticationDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getDetails();
        return wauth.getRemoteAddress();
    }

}
