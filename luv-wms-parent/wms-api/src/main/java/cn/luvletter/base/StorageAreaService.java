package cn.luvletter.base;

import cn.luvletter.base.model.StorageArea;
import cn.luvletter.bean.ApiResult;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Zephyr Ji
 * @ Description: 库区接口
 * @ Date 2018/3/6
 */
public interface StorageAreaService {
    /**
     * @Description: 获取所有库区
     * @Date: 9:17 2018/3/28
     */
    ApiResult getStorageArea(HttpServletRequest httpServletRequest);

    /**
     * @Description: 根据id删除库区
     * @Date: 9:17 2018/3/28
     */
    ApiResult delById(String id, HttpServletRequest httpServletRequest);

    /**
     * @Description: 新增库区
     * @Date: 9:18 2018/3/28
     */
    ApiResult sava(StorageArea storageArea, HttpServletRequest httpServletRequest);

    /**
     * @Description: 更新库区
     * @Date: 13:43 2018/3/28
     */
    ApiResult update(StorageArea storageArea, HttpServletRequest httpServletRequest);
}
