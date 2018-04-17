package cn.luvletter.sys.impl;

import cn.luvletter.bean.ApiResult;
import cn.luvletter.sys.api.PermissionService;
import cn.luvletter.sys.dao.PermissionMapper;
import cn.luvletter.sys.model.Permission;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Zephyr
 * @Description:
 * @Date 2018/4/17
 */
@Service
public class PermisssionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public ApiResult getPermission(String key) {
        ApiResult apiResult = new ApiResult();
        if(StringUtils.isEmpty(key)){
            return apiResult;
        }
        List<Permission> permissions = permissionMapper.findByPid(key);
        apiResult.setData(permissions);
        return apiResult;
    }
}
