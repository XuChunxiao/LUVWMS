package cn.luvletter.sys;

import cn.luvletter.base.BaseController;
import cn.luvletter.bean.ApiResult;
import cn.luvletter.sys.api.OperatorService;
import cn.luvletter.sys.model.Operator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Zephyr
 * @Description: 人员
 * @Date 2018/4/20
 */
@RestController
@RequestMapping("/authority/operator")
public class OperatorController extends BaseController {

    @Autowired
    private OperatorService operatorService;

    /**
     * @Description: 获取人员列表
     * @Date: 11:14 2018/3/29
     */
    @GetMapping
    public ApiResult getData(HttpServletRequest httpServletRequest){
        return operatorService.getOperators(httpServletRequest);
    }
    @PutMapping
    public ApiResult update(@RequestBody Operator operator, HttpServletRequest httpServletRequest){
        return operatorService.update(operator, httpServletRequest);
    }
    @DeleteMapping("/{id}")
    public ApiResult delById(@PathVariable("id")String id, HttpServletRequest httpServletRequest){
        return operatorService.delById(id,httpServletRequest);
    }
    @PostMapping
    public ApiResult save(@Validated @RequestBody Operator operator, BindingResult br, HttpServletRequest httpServletRequest){
        ApiResult apiResult = new ApiResult();
        if(br.hasErrors()){
            apiResult.isFalse().setMessage(br.getFieldErrors());
            return apiResult;
        }
        return operatorService.sava(operator,httpServletRequest);
    }

}
