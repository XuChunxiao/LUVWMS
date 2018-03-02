package cn.luvletter.sys.impl;

import cn.luvletter.bean.ApiResult;
import cn.luvletter.sys.api.SettingService;
import cn.luvletter.sys.dao.PermissionMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Zephyr Ji
 * @ Description: 系统设置
 * @ Date 2018/3/1
 */
@Service
public class SettingServiceImpl implements SettingService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public ApiResult getNavigation() {
        ApiResult apiResult = new ApiResult();
        apiResult.setData(permissionMapper.findAll());
        return apiResult;
    }

    @Override
    public ApiResult delNavigation(String id) {
        if(StringUtils.isEmpty(id)){
            return new ApiResult("id不能为空!");
        }
        permissionMapper.delById(id);
        return new ApiResult("删除成功");
    }
}
