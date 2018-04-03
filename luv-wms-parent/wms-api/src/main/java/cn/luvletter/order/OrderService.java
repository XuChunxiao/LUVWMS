package cn.luvletter.order;

import cn.luvletter.bean.ApiResult;

import javax.servlet.http.HttpServletRequest; /**
 * @author Zephyr
 * @Description: 订单接口
 * @Date 2018/4/3
 */
public interface OrderService {
    /**
     * @Description: 获取订单列表
     * @Date: 10:45 2018/4/3
     */
    ApiResult getOrder(HttpServletRequest httpServletRequest);
    /**
     * @Description:获取订单明细
     * @Date: 10:46 2018/4/3
     */
    ApiResult getOrderDtl(String id, HttpServletRequest httpServletRequest);
}
