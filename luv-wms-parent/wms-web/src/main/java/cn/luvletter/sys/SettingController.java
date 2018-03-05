package cn.luvletter.sys;

import cn.luvletter.base.BaseController;
import cn.luvletter.bean.ApiResult;
import cn.luvletter.sys.api.SettingService;
import cn.luvletter.sys.model.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zephyr Ji
 * @ Description: 系统设置控制层
 * @ Date 2018/3/1
 */
@RestController
@RequestMapping("/setting")
public class SettingController extends BaseController {
    @Autowired
    private SettingService settingService;
    @GetMapping("/navigation")
    public ApiResult getNavigation(){
        return settingService.getNavigation();
    }
    @DeleteMapping("/navigation/{id}")
    public ApiResult delNavigation(@PathVariable("id") String id){
        return settingService.delNavigation(id);
    }
    @PostMapping("/navigation")
    public ApiResult saveNavigation(@RequestBody Permission permission){
        return settingService.saveNavigation(permission);
    }
    @PutMapping("/navigation")
    public ApiResult updateNavigation(@RequestBody Permission permission){
        return settingService.updateNavigation(permission);
    }
}
