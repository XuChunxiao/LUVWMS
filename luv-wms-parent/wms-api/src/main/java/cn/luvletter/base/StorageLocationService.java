package cn.luvletter.base;

import cn.luvletter.base.model.StorageLocation;
import cn.luvletter.base.vo.StorageLocationVo;
import cn.luvletter.bean.ApiResult;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Zephyr Ji
 * @ Description: 库位
 * @ Date 2018/3/6
 */
public interface StorageLocationService {
    /**
     * @Description: 获取所有库位
     * @Date: 13:11 2018/3/28
     * @param httpServletRequest
     */
    ApiResult getStorageArea(HttpServletRequest httpServletRequest);

    /**
     * @Description: 根据id删除库位
     * @Date: 13:11 2018/3/28
     */
    ApiResult delById(String id, HttpServletRequest httpServletRequest);

    /**
     * @Description: 批量新增库位
     * @Date: 13:12 2018/3/28
     * @param storageLocationVo
     * @param httpServletRequest
     */
    ApiResult sava(StorageLocationVo storageLocationVo, HttpServletRequest httpServletRequest);

    /**
     * @Description: 更新库位
     * @Date: 13:52 2018/3/28
     */
    ApiResult update(StorageLocation storageLocation, HttpServletRequest httpServletRequest);
}
