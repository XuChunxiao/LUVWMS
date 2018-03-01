package cn.luvletter.constant;

/**
 * @author Zephyr Ji
 * @ Description: TODO
 * @ Date 2018/2/24
 */
public class SqlConstant {
    public static final String SELECT_OPRT_BY_NO = "select No account,`password` from sys_operator where NO=? ";
    public static final String SELECT_ALL_AUTH = "select sr.role_name name,sp.permission_url url from sys_permission sp LEFT JOIN sys_role_permission srp on sp.permission_no=srp.permission_no\n" +
            "LEFT JOIN sys_role sr on srp.role_no=sr.role_no";
}
