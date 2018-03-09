package cn.luvletter.sys.impl;

import cn.luvletter.bean.ApiResult;
import cn.luvletter.bean.AuthenticationBean;
import cn.luvletter.constant.SqlConstant;
import cn.luvletter.sys.api.SysService;
import cn.luvletter.sys.dao.OperatorMapper;
import cn.luvletter.sys.dao.OprtRoleMapper;
import cn.luvletter.sys.model.Operator;
import cn.luvletter.sys.model.OperatorExample;
import cn.luvletter.sys.model.OprtRole;
import cn.luvletter.util.AESUtil;
import cn.luvletter.util.JWTUtil;
import cn.luvletter.util.JdbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * @author Zephyr Ji
 * @ Description: TODO
 * @ Date 2018/2/13
 */
@Service
public class SysServiceImpl implements SysService {

    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private OperatorMapper operatorMapper;
    @Autowired
    private OprtRoleMapper oprtRoleMapper;
    @Override
    public ApiResult register(Operator operator) {
        final String no = operator.getNo();
        final OperatorExample operatorExample = new OperatorExample();
        operatorExample.createCriteria().andNoEqualTo(no);
        if(operatorMapper.selectByExample(operatorExample)!=null){
            return new ApiResult("已存在此用户",null);
        }
        operator.setPassword(AESUtil.AESEncode(operator.getPassword()));
        operatorMapper.insertSelective(operator);
        final OprtRole oprtRole = new OprtRole();
        oprtRole.setOprtNo(no);
        oprtRole.setRoleNo("1");
        oprtRole.setGmtCreate(new Date(System.currentTimeMillis()));
        oprtRoleMapper.insertSelective(oprtRole);

        return new ApiResult("注册成功",operator);
    }

    @Override
    public ApiResult login(AuthenticationBean authenticationBean, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(authenticationBean, response);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final OperatorExample operatorExample = new OperatorExample();
        operatorExample.createCriteria().andNoEqualTo(authenticationBean.getAccount());
        List<Operator> operators = operatorMapper.selectByExample(operatorExample);
        Operator operator = null;
        if(operators!=null && operators.size()!=0){
            operator = operators.get(0);
        }
        String token = null;

        token = jwtUtil.addAuthentication(response,authenticationBean);

        return new ApiResult(token,null);
    }

    @Override
    public String refresh(String oldToken) {
        return null;
    }
    /**
     * @Description: 获得所有url-权限信息
     * @Date: 16:15 2018/2/28
     */
    @Override
    public ApiResult getAuth() {
        ApiResult apiResult = new ApiResult();
        apiResult.setData(JdbcUtil.newInstance().selectByParams(SqlConstant.SELECT_ALL_AUTH,null));
        return apiResult;
    }
}
