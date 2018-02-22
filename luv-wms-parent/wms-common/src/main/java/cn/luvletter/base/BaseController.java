package cn.luvletter.base;

import cn.luvletter.bean.ApiResult;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Zephyr Ji
 * @ Description: 基础controller ,异常处理
 * @ Date 2018/2/9
 */
public class BaseController {
    @ExceptionHandler
    public ApiResult exceptionHandler(Exception e){
        ApiResult apiResult = new ApiResult().isFalse();
        if(e instanceof ApplicationContext){
            apiResult.setMessage(e.getMessage());
        }else {
            e.printStackTrace();
            apiResult.setMessage("系统异常");
        }
        return apiResult;
    }
}
