package cn.luvletter.base;

import cn.luvletter.base.model.StorageArea;
import cn.luvletter.bean.ApiResult;

/**
 * @author Zephyr Ji
 * @ Description: TODO
 * @ Date 2018/3/6
 */
public interface StorageAreaService {
    ApiResult getStorageArea();

    ApiResult delById(String id);

    ApiResult sava(StorageArea storageArea);
}
