package cn.luvletter.sys;

import cn.luvletter.base.BaseController;
import cn.luvletter.bean.ApiResult;
import cn.luvletter.sys.api.PermissionService;
import cn.luvletter.sys.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zephyr
 * @Description:
 * @Date 2018/4/17
 */
@RestController
@RequestMapping("/roleAssianment")
public class RoleAssianmentController extends BaseController{
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    @GetMapping("/role")
    public ApiResult getRuleData(){
        return roleService.getRule();
    }
    @GetMapping("/tree")
    public ApiResult getTreeData(@RequestParam("key") String key){
        return permissionService.getPermission(key);
    }
}
