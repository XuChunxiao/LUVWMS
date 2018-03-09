package cn.luvletter.base;

import cn.luvletter.base.model.StorageLocation;
import cn.luvletter.bean.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zephyr
 * @ Description: 库位
 * @ Date 2018/3/6
 */
@RestController
@RequestMapping("/storage_location")
public class StorageLocationController extends BaseController{
    @Autowired
    private StorageLocationService storageLocationService;
    @GetMapping
    public ApiResult getData(){
        return storageLocationService.getStorageArea();
    }
    @DeleteMapping("/{id}")
    public ApiResult delById(@PathVariable("id")String id){
        return storageLocationService.delById(id);
    }
    @PostMapping
    public ApiResult save(@RequestBody StorageLocation StorageLocation){
        return storageLocationService.sava(StorageLocation);
    }
}
