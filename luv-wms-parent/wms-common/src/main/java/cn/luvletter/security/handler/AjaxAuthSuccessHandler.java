package cn.luvletter.security.handler;

import cn.luvletter.bean.ApiResult;
import cn.luvletter.bean.AuthenticationBean;
import cn.luvletter.exception.ApplicationException;
import cn.luvletter.security.service.OprtService;
import cn.luvletter.util.JWTUtil;
import cn.luvletter.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author Zephyr Ji
 * @ Description: 登陆成功返回程序
 * @ Date 2018/2/11
 */
@Component
public class AjaxAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);
        User user = (User) authentication.getPrincipal();
        String username = user.getUsername();
        AuthenticationBean authenticationBean = OprtService.loadOprt(username);
        if(authenticationBean == null){
            throw new ApplicationException("username:"+username+"not found");
        }
        authenticationBean.setRoleNo(JWTUtil.authenticationToString(authentication.getAuthorities()));
        String token = null;


        token = JWTUtil.addAuthentication(response,authenticationBean);
        ApiResult apiResult = new ApiResult();
        apiResult.setMessage("登陆成功");
        apiResult.setData(token);
        ResponseUtil.returnJson(response,apiResult);
    }
}
