package cn.luvletter.stock;

import cn.luvletter.bean.ApiResult;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Zephyr
 * @Description: 库存
 * @Date 2018/4/9
 */
public interface StockService {

    /**
     * @Description: 获取库存表头
     * @Date: 16:43 2018/4/9
     */
    ApiResult getStock(HttpServletRequest httpServletRequest);

    /**
     * @Description: 获取库存表明细
     * @Date: 16:43 2018/4/9
     */
    ApiResult getStockDtl(String id, HttpServletRequest httpServletRequest);
}
