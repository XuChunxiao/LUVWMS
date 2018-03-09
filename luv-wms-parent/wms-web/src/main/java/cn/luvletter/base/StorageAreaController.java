package cn.luvletter.base;

import cn.luvletter.base.model.StorageArea;
import cn.luvletter.bean.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zephyr
 * @ Description: 库区
 * @ Date 2018/3/6
 */
@RestController
@RequestMapping("/storage_area")
public class StorageAreaController extends BaseController{
    @Autowired
    private StorageAreaService storageAreaService;
    @GetMapping
    public ApiResult getData(){
        return storageAreaService.getStorageArea();
    }
    @DeleteMapping("/{id}")
    public ApiResult delById(@PathVariable("id")String id){
        return storageAreaService.delById(id);
    }
    @PostMapping
    public ApiResult save(@RequestBody StorageArea storageArea){
        return storageAreaService.sava(storageArea);
    }
}
