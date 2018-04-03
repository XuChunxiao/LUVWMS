package cn.luvletter.order.impl;

import cn.luvletter.bean.ApiResult;
import cn.luvletter.in.model.InStock;
import cn.luvletter.in.model.InStockDtlExample;
import cn.luvletter.order.OrderService;
import cn.luvletter.order.dao.OrderDtlMapper;
import cn.luvletter.order.dao.OrderMapper;
import cn.luvletter.order.model.Order;
import cn.luvletter.order.model.OrderDtl;
import cn.luvletter.order.model.OrderDtlExample;
import cn.luvletter.order.model.OrderExample;
import cn.luvletter.util.WMSUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Zephyr
 * @Description:
 * @Date 2018/4/3
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private WMSUtil wmsUtil;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDtlMapper orderDtlMapper;
    @Override
    public ApiResult getOrder(HttpServletRequest httpServletRequest) {
        final String currWNo = wmsUtil.getCurrWNo(httpServletRequest);
        String orderNo = httpServletRequest.getParameter("orderNo");
        String orderStatus = httpServletRequest.getParameter("orderStatus");
        String start = wmsUtil.getStart(httpServletRequest);
        String limit = wmsUtil.getLimit(httpServletRequest);
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        if(StringUtils.isNotBlank(orderNo)){
            criteria.andOrderNoLike("%"+orderNo+"%");
        }
        if(StringUtils.isNotBlank(orderStatus)){
            criteria.andOrderStatusEqualTo(orderStatus);
        }
        criteria.andWarehouseNoEqualTo(currWNo);
        orderExample.setStart(start);
        orderExample.setLimit(limit);
        List<Order> orders = orderMapper.selectByExample(orderExample);
        ApiResult apiResult = new ApiResult();
        apiResult.setData(orders);
        apiResult.setTotal(orderMapper.countByExample(orderExample));
        return apiResult;
    }

    @Override
    public ApiResult getOrderDtl(String id, HttpServletRequest httpServletRequest) {
        ApiResult apiResult = new ApiResult();
        if(StringUtils.isBlank(id)|| !NumberUtils.isDigits(id)){
            apiResult.isFalse().setMessage("id不能为空");
            return apiResult;
        }
        OrderExample orderExample = new OrderExample();
        orderExample.createCriteria().andIdEqualTo(Long.valueOf(id));
        List<Order> orders = orderMapper.selectByExample(orderExample);
        if(orders == null || orders.size()==0){
            apiResult.isFalse().setMessage("系统未找到此订单详情");
            return apiResult;
        }
        String start = wmsUtil.getStart(httpServletRequest);
        String limit = wmsUtil.getLimit(httpServletRequest);
        String orderNo = orders.get(0).getOrderNo();
        OrderDtlExample orderDtlExample = new OrderDtlExample();
        orderDtlExample.createCriteria().andOrderNoEqualTo(orderNo);
        orderDtlExample.setStart(start);
        orderDtlExample.setLimit(limit);
        List<OrderDtl> orderDtls = orderDtlMapper.selectByExample(orderDtlExample);
        apiResult.setData(orderDtls);
        apiResult.setTotal(orderDtlMapper.countByExample(orderDtlExample));
        return apiResult;
    }
}
