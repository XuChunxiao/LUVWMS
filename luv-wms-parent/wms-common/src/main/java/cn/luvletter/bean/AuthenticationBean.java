package cn.luvletter.bean;

/**
 * @author Zephyr Ji
 * @ Description: 登陆信息
 * @ Date 2018/2/11
 */
public class AuthenticationBean {
    private String account;
    private String password;
    private String roleNo;
    private boolean checkbox;


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleNo() {
        return roleNo;
    }

    public void setRoleNo(String roleNo) {
        this.roleNo = roleNo;
    }

    public boolean isCheckbox() {
        return checkbox;
    }

    public void setCheckbox(boolean checkbox) {
        this.checkbox = checkbox;
    }
}
