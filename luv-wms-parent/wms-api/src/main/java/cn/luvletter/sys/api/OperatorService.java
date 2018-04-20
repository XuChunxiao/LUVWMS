package cn.luvletter.sys.api;

import cn.luvletter.bean.ApiResult;
import cn.luvletter.sys.model.Operator;

import javax.servlet.http.HttpServletRequest; /**
 * @author Zephyr
 * @Description: 人员
 * @Date 2018/4/20
 */
public interface OperatorService {
    /**
     * @Description: 获取人员列表
     * @Date: 16:46 2018/4/20
     */
    ApiResult getOperators(HttpServletRequest httpServletRequest);

    /**
     * @Description: 更新人员信息
     * @Date: 16:47 2018/4/20
     */
    ApiResult update(Operator operator, HttpServletRequest httpServletRequest);

    /**
     * @Description: 删除人员信息
     * @Date: 16:48 2018/4/20
     */
    ApiResult delById(String id, HttpServletRequest httpServletRequest);

    /**
     * @Description: 新增人员信息
     * @Date: 16:48 2018/4/20
     */
    ApiResult sava(Operator operator, HttpServletRequest httpServletRequest);
}
