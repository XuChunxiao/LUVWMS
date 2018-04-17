package cn.luvletter.sys.impl;

import cn.luvletter.bean.ApiResult;
import cn.luvletter.sys.api.RoleService;
import cn.luvletter.sys.dao.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Zephyr
 * @Description:
 * @Date 2018/4/17
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public ApiResult getRule() {
        ApiResult apiResult = new ApiResult();
        apiResult.setData(roleMapper.findAll());
        return apiResult;
    }
}
