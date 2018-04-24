package cn.luvletter.sys;

import cn.luvletter.base.BaseController;
import cn.luvletter.bean.ApiResult;
import cn.luvletter.main.model.Dictionary;
import cn.luvletter.sys.api.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Zephyr
 * @Description: 数据字典
 * @Date 2018/4/21
 */
@RestController
@RequestMapping("/sys/dictionary")
public class DictionaryController extends BaseController {
    @Autowired
    private DictionaryService dictionaryService;
    @GetMapping
    public ApiResult getData(HttpServletRequest httpServletRequest){
        return dictionaryService.getDictionary(httpServletRequest);
    }
    @PutMapping
    public ApiResult update(@RequestBody Dictionary dictionary, HttpServletRequest httpServletRequest){
        return dictionaryService.update(dictionary, httpServletRequest);
    }
    @DeleteMapping("/{id}")
    public ApiResult delById(@PathVariable("id")String id, HttpServletRequest httpServletRequest){
        return dictionaryService.delById(id,httpServletRequest);
    }
    @PostMapping
    public ApiResult save(@Validated @RequestBody Dictionary dictionary, BindingResult br, HttpServletRequest httpServletRequest){
        ApiResult apiResult = new ApiResult();
        if(br.hasErrors()){
            apiResult.isFalse().setMessage(br.getFieldErrors());
            return apiResult;
        }
        return dictionaryService.sava(dictionary,httpServletRequest);
    }
}
