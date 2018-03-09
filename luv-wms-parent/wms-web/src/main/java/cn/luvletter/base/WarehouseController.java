package cn.luvletter.base;

import cn.luvletter.base.model.Warehouse;
import cn.luvletter.bean.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zephyr
 * @ Description: 仓库
 * @ Date 2018/3/6
 */
@RestController
@RequestMapping("/warehouse")
public class WarehouseController extends BaseController{
    @Autowired
    private WarehouseService warehouseService;
    @GetMapping
    public ApiResult getData(){
        return warehouseService.getStorageArea();
    }
    @DeleteMapping("/{id}")
    public ApiResult delById(@PathVariable("id")String id){
        return warehouseService.delById(id);
    }
    @PostMapping
    public ApiResult save(@RequestBody Warehouse warehouse){
        return warehouseService.sava(warehouse);
    }
}
