package cn.luvletter.base;

import cn.luvletter.base.model.StorageArea;
import cn.luvletter.bean.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Zephyr
 * @ Description: 库区
 * @ Date 2018/3/6
 */
@RestController
@RequestMapping("/sys/storageArea")
public class StorageAreaController extends BaseController{
    @Autowired
    private StorageAreaService storageAreaService;
    @GetMapping
    public ApiResult getData(HttpServletRequest httpServletRequest){
        return storageAreaService.getStorageArea(httpServletRequest);
    }
    @PutMapping
    public ApiResult update(@RequestBody StorageArea storageArea, HttpServletRequest httpServletRequest){
        return storageAreaService.update(storageArea, httpServletRequest);
    }
    @DeleteMapping("/{id}")
    public ApiResult delById(@PathVariable("id")String id, HttpServletRequest httpServletRequest){
        return storageAreaService.delById(id,httpServletRequest);
    }
    @PostMapping
    public ApiResult save(@Validated @RequestBody StorageArea storageArea, BindingResult br, HttpServletRequest httpServletRequest){
        ApiResult apiResult = new ApiResult();
        if(br.hasErrors()){
            apiResult.isFalse().setMessage(br.getFieldErrors());
            return apiResult;
        }
        return storageAreaService.sava(storageArea,httpServletRequest);
    }
}
