package cn.luvletter.sys.api;

import cn.luvletter.bean.ApiResult;

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
}
