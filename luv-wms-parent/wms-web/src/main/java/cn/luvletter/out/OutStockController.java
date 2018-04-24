package cn.luvletter.out;

import cn.luvletter.base.BaseController;
import cn.luvletter.bean.ApiResult;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Zephyr
 * @Description: 出库
 * @Date 2018/3/29
 */
@RestController
@RequestMapping("/outStock/list")
public class OutStockController extends BaseController{
    @Autowired
    private OutStockService outStockService;

    /**
     * @Description: 获取库存表头
     * @Date: 16:43 2018/4/9
     */
    @GetMapping
    public ApiResult getData(HttpServletRequest httpServletRequest){
        return outStockService.getOutStock(httpServletRequest);
    }

    /**
     * @Description: 获取库存表明细
     * @Date: 16:43 2018/4/9
     */
    @GetMapping("/{id}")
    public ApiResult getData(@PathVariable String id, HttpServletRequest httpServletRequest){
        return outStockService.getOutStockDtl(id,httpServletRequest);
    }

}
