package cn.luvletter.out;

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
@RequestMapping("/outStock")
public class OutStockController {
    @Autowired
    private OutStockService outStockService;
    @GetMapping
    public ApiResult getData(HttpServletRequest httpServletRequest){
        return outStockService.getOutStock(httpServletRequest);
    }
    @GetMapping("/{id}")
    public ApiResult getData(@PathVariable String id, HttpServletRequest httpServletRequest){
        return outStockService.getOutStockDtl(id,httpServletRequest);
    }

}
