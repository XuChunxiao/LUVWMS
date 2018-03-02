package cn.luvletter.filter;

import cn.luvletter.util.ResponseUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Zephyr Ji
 * @ Description: option请求过滤器
 * @ Date 2018/2/11
 */
public class ProcessFilter implements Filter {

    private static final String OPTIONS = "OPTIONS";
    public static final Map<String,String> opM = new HashMap<>();

    static {
        opM.put("data","option");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");

        httpServletResponse.setHeader("Access-Control-Expose-Headers", "Authorization");

        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Content-Type,Content-Length, Authorization, Accept,X-Requested-With");

        httpServletResponse.setHeader("Access-Control-Allow-Methods","PUT,POST,GET,DELETE,OPTIONS");

        String method= httpServletRequest.getMethod();

        if (OPTIONS.equals(method)){
            httpServletResponse.setStatus(200);

            ResponseUtil.returnJson(httpServletResponse,opM);
            return ;
        }

        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }
}
