package cn.luvletter.sys.model;

/**
 * @author Zephyr
 * @Description:
 * @Date 2018/4/17
 */
public class Role {
    private Integer id;
    /**
     * 角色编号
     */
    private String roleNo;
    /**
     * 角色名称
     */
    private String roleName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleNo() {
        return roleNo;
    }

    public void setRoleNo(String roleNo) {
        this.roleNo = roleNo;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
