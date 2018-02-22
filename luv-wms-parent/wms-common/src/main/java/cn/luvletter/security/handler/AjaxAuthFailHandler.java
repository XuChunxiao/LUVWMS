package cn.luvletter.security.handler;

import cn.luvletter.bean.ApiResult;
import cn.luvletter.util.ResponseUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Zephyr Ji
 * @ Description: 登陆失败返回程序
 * @ Date 2018/2/11
 */
public class AjaxAuthFailHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);

        ApiResult apiResult = new ApiResult().isFalse();
        apiResult.setMessage("登陆失败，失败原因:"+exception.getMessage());

        ResponseUtil.returnJson(response,apiResult);
    }
}
