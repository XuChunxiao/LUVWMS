package cn.luvletter.sys.api;

import cn.luvletter.bean.ApiResult;

/**
 * @author Zephyr
 * @Description:
 * @Date 2018/4/17
 */
public interface PermissionService {
    /**
     * @Description: 树根据key获取节点
     * @Date: 15:08 2018/4/17
     */
    ApiResult getPermission(String key);
}
