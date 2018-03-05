package cn.luvletter.sys.api;

import cn.luvletter.bean.ApiResult;
import cn.luvletter.sys.model.Permission;

/**
 * @author Zephyr Ji
 * @ Description: 系统设置接口
 * @ Date 2018/3/1
 */
public interface SettingService {
    /**
     * @Description: 得到所有菜单
     * @Date: 14:14 2018/3/1
     */
    ApiResult getNavigation();

    /**
     * @Description: 删除菜单配置
     * @Date: 14:46 2018/3/2
     */
    ApiResult delNavigation(String id);

    /**
     * @Description: 新增菜单
     * @Date: 11:25 2018/3/5
     */
    ApiResult saveNavigation(Permission permission);

    /**
     * @Description: 更新菜单
     * @Date: 11:27 2018/3/5
     */
    ApiResult updateNavigation(Permission permission);
}
