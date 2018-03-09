package cn.luvletter.base;

import cn.luvletter.base.model.Warehouse;
import cn.luvletter.bean.ApiResult;

/**
 * @author Zephyr Ji
 * @ Description: TODO
 * @ Date 2018/3/6
 */
public interface WarehouseService {
    ApiResult getStorageArea();

    ApiResult delById(String id);

    ApiResult sava(Warehouse warehouse);
}
