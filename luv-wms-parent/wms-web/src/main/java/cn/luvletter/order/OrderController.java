package cn.luvletter.order;

import cn.luvletter.base.BaseController;
import cn.luvletter.bean.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Zephyr
 * @Description: 订单
 * @Date 2018/4/3
 */
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController{

    @Autowired
    private OrderService orderService;

    /**
     * @Description: 获取订单列表
     * @Date: 10:45 2018/4/3
     */
    @GetMapping
    public ApiResult getData(HttpServletRequest httpServletRequest){
        return orderService.getOrder(httpServletRequest);
    }
    /**
     * @Description:获取订单明细
     * @Date: 10:46 2018/4/3
     */
    @GetMapping("/{id}")
    public ApiResult getDtl(@PathVariable String id, HttpServletRequest httpServletRequest){
        return orderService.getOrderDtl(id, httpServletRequest);
    }
}
