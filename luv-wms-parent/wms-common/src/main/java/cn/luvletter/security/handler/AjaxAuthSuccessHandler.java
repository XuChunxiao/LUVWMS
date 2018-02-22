package cn.luvletter.security.handler;

import cn.luvletter.bean.ApiResult;
import cn.luvletter.util.ResponseUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Zephyr Ji
 * @ Description: 登陆成功返回程序
 * @ Date 2018/2/11
 */
public class AjaxAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);

        ApiResult apiResult = new ApiResult();
        apiResult.setMessage("登陆成功");

        ResponseUtil.returnJson(response,apiResult);
    }
}
