package cn.luvletter.out;

import cn.luvletter.bean.ApiResult;

import javax.servlet.http.HttpServletRequest;
/**
 * @author Zephyr
 * @Description:
 * @Date 2018/3/29
 */
public interface OutStockService {
    /**
     * @Description: 获取出库单列表
     * @Date: 13:31 2018/3/29
     */
    ApiResult getOutStock(HttpServletRequest httpServletRequest);
    /**
     * @Description: 获取出库单明细
     * @Date: 13:31 2018/3/29
     */
    ApiResult getOutStockDtl(String id, HttpServletRequest httpServletRequest);
}
