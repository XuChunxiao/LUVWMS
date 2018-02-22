package cn.luvletter.sys.api;

import cn.luvletter.bean.ApiResult;
import cn.luvletter.bean.AuthenticationBean;
import cn.luvletter.sys.model.Operator;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Zephyr Ji
 * @ Description: TODO
 * @ Date 2018/2/13
 */
public interface SysService {
    ApiResult register(Operator operator);
    ApiResult login(AuthenticationBean authenticationBean, HttpServletResponse response);
    String refresh(String oldToken);
}
