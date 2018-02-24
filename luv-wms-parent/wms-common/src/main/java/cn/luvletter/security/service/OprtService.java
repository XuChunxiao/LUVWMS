package cn.luvletter.security.service;

import cn.luvletter.bean.AuthenticationBean;
import cn.luvletter.constant.SqlConstant;
import cn.luvletter.util.JdbcUtil;

import java.util.Arrays;
import java.util.List;

/**
 * @author Zephyr Ji
 * @ Description: TODO
 * @ Date 2018/2/24
 */

public class OprtService {
    /**
     * @Description: 根据人员账号查询人员
     * @Date: 21:28 2018/2/24
     */
    public static AuthenticationBean loadOprt(String no) {
        List<AuthenticationBean> authenticationBeans = JdbcUtil.newInstance().selectBean(SqlConstant.SELECT_OPRT_BY_NO, Arrays.asList(no),AuthenticationBean.class);
        if(authenticationBeans!=null && authenticationBeans.size()!=0){
            return authenticationBeans.get(0);
        }
        return null;
    }
}
