package cn.luvletter.security.handler;

import cn.luvletter.bean.ApiResult;
import cn.luvletter.util.ResponseUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Zephyr Ji
 * @ Description: TODO
 * @ Date 2018/2/24
 */
@Component
public class AjaxAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);

        ApiResult apiResult = new ApiResult().isFalse();
        apiResult.setMessage(e.getMessage());

        ResponseUtil.returnJson(httpServletResponse,apiResult);
    }
}
