package cn.luvletter.sys.model;

import java.util.List;

/**
 * @author Zephyr
 * @Description:
 * @Date 2018/4/18
 */
public class PermissionTree extends Permission {
    private List<Permission> permissionChild;

    public List<Permission> getPermissionChild() {
        return permissionChild;
    }

    public void setPermissionChild(List<Permission> permissionChild) {
        this.permissionChild = permissionChild;
    }
}
