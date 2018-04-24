package cn.luvletter.base;

import cn.luvletter.base.model.StorageLocation;
import cn.luvletter.base.vo.StorageLocationVo;
import cn.luvletter.bean.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Zephyr
 * @ Description: 库位
 * @ Date 2018/3/6
 */
@RestController
@RequestMapping("/sys/storageLocation")
public class StorageLocationController extends BaseController{
    @Autowired
    private StorageLocationService storageLocationService;
    @GetMapping
    public ApiResult getData(HttpServletRequest httpServletRequest){
        return storageLocationService.getStorageLocation(httpServletRequest);
    }
    @PutMapping
    public ApiResult update(@RequestBody StorageLocation storageLocation, HttpServletRequest httpServletRequest){
        return storageLocationService.update(storageLocation, httpServletRequest);
    }
    @DeleteMapping("/{id}")
    public ApiResult delById(@PathVariable("id")String id, HttpServletRequest httpServletRequest){
        return storageLocationService.delById(id,httpServletRequest);
    }
    @PostMapping
    public ApiResult save(@Validated @RequestBody StorageLocationVo storageLocationVo, BindingResult br, HttpServletRequest httpServletRequest){
        ApiResult apiResult = new ApiResult();
        if(br.hasErrors()){
            apiResult.isFalse().setMessage(br.getFieldErrors());
            return apiResult;
        }
        return storageLocationService.sava(storageLocationVo,httpServletRequest);
    }
}
