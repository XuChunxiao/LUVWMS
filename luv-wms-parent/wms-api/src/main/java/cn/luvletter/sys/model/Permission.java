package cn.luvletter.sys.model;

/**
 * @author Zephyr Ji
 * @ Description: 权限
 * @ Date 2018/2/10
 */
public class Permission {
    /**
     * 主键
     */
    private int id;
    private String permissionNo;
    private String permissioName;
    private String permissionDesc;
    private String permissionUrl;
    private String permissionPid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPermissionNo() {
        return permissionNo;
    }

    public void setPermissionNo(String permissionNo) {
        this.permissionNo = permissionNo;
    }

    public String getPermissioName() {
        return permissioName;
    }

    public void setPermissioName(String permissioName) {
        this.permissioName = permissioName;
    }

    public String getPermissionDesc() {
        return permissionDesc;
    }

    public void setPermissionDesc(String permissionDesc) {
        this.permissionDesc = permissionDesc;
    }

    public String getPermissionUrl() {
        return permissionUrl;
    }

    public void setPermissionUrl(String permissionUrl) {
        this.permissionUrl = permissionUrl;
    }

    public String getPermissionPid() {
        return permissionPid;
    }

    public void setPermissionPid(String permissionPid) {
        this.permissionPid = permissionPid;
    }
}
