package cn.luvletter.sys;

import cn.luvletter.base.BaseController;
import cn.luvletter.bean.ApiResult;
import cn.luvletter.bean.AuthenticationBean;
import cn.luvletter.sys.api.SysService;
import cn.luvletter.sys.model.Operator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Zephyr Ji
 * @ Description: TODO
 * @ Date 2018/2/22
 */
@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController {
    @Autowired
    private SysService sysService;

    @PostMapping("/login")
    public ApiResult login(@RequestBody AuthenticationBean authenticationBean, HttpServletResponse response){
        return sysService.login(authenticationBean,response);
    }
    @PostMapping("/register")
    public ApiResult register(@RequestBody Operator operator){
        return sysService.register(operator);
    }

    @GetMapping()
    public ApiResult getAuth(){
        return sysService.getAuth();
    }

    @GetMapping("/nav")
    public ApiResult getNav(@RequestParam("operatorNo") String operatorNo){
        return sysService.getNav(operatorNo);
    }

}
