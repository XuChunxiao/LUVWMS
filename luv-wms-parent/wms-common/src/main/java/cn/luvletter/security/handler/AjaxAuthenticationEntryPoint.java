package cn.luvletter.security.handler;

import cn.luvletter.bean.ApiResult;
import cn.luvletter.util.ResponseUtil;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Zephyr
 * @Description: 权限验证失败
 * @Date 2018/4/16
 */
@Component
public class AjaxAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);

        ApiResult apiResult = new ApiResult().isFalse();
        String msg = e.getMessage();
        if(e instanceof InsufficientAuthenticationException){
            msg = "认证失败！";
        }
        apiResult.setMessage(msg);

        ResponseUtil.returnJson(httpServletResponse,apiResult);
    }
}
