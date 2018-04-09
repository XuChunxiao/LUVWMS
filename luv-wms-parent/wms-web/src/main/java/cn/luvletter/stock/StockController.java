package cn.luvletter.stock;

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
 * @Description: 库存
 * @Date 2018/4/9
 */
@RestController
@RequestMapping("/stock")
public class StockController extends BaseController{
    @Autowired
    private StockService stockService;
    @GetMapping
    public ApiResult getData(HttpServletRequest httpServletRequest){
        return stockService.getStock(httpServletRequest);
    }
    @GetMapping("/{id}")
    public ApiResult getData(@PathVariable String id, HttpServletRequest httpServletRequest){
        return stockService.getStockDtl(id,httpServletRequest);
    }

}
