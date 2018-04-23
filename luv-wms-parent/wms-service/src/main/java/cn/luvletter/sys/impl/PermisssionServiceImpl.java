package cn.luvletter.sys.impl;

import cn.luvletter.bean.ApiResult;
import cn.luvletter.constant.WMSConstant;
import cn.luvletter.sys.api.PermissionService;
import cn.luvletter.sys.dao.PermissionMapper;
import cn.luvletter.sys.model.Permission;
import cn.luvletter.sys.model.PermissionTree;
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
        List<PermissionTree> permissionTrees = permissionMapper.findTree();
        if(permissionTrees == null ||permissionTrees.isEmpty()){
            return apiResult;
        }
        for(PermissionTree permissionTree : permissionTrees){
            List<Permission> permissionChild = permissionTree.getPermissionChild();
            if(permissionMapper.decideIsRole(permissionTree.getPermissionNo(),key)>0){
                permissionTree.setCheck(WMSConstant.YES);
            }else{
                permissionTree.setCheck(WMSConstant.NO);
            }
            for(Permission permission : permissionChild){
                if(permissionMapper.decideIsRole(permission.getPermissionNo(),key)>0){
                    permission.setCheck(WMSConstant.YES);
                }else{
                    permission.setCheck(WMSConstant.NO);
                }
            }
        }
        apiResult.setData(permissionTrees);
        return apiResult;
    }
}
