package cn.luvletter.base;

import cn.luvletter.base.model.Goods;
import cn.luvletter.base.vo.GoodsSerarchVo;
import cn.luvletter.bean.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Zephyr Ji
 * @ Description: 商品
 * @ Date 2018/3/28
 */
@RestController
@RequestMapping("/goods")
public class GoodsController extends BaseController {
    @Autowired
    private GoodsService goodsService;
    @GetMapping("/list")
    public ApiResult getData(GoodsSerarchVo serarchVo, HttpServletRequest httpServletRequest){
        return goodsService.getGoods(serarchVo,httpServletRequest);
    }
    @PutMapping("/list")
    public ApiResult update(@Validated @RequestBody Goods goods, BindingResult br, HttpServletRequest httpServletRequest){
        ApiResult apiResult = new ApiResult();
        if(br.hasErrors()){
            apiResult.isFalse().setMessage(br.getFieldErrors());
            return apiResult;
        }
        return goodsService.update(goods, httpServletRequest);
    }
    @DeleteMapping("/list/{id}")
    public ApiResult delById(@PathVariable("id")String id, HttpServletRequest httpServletRequest){
        return goodsService.delById(id,httpServletRequest);
    }
    @PostMapping("/create")
    public ApiResult save(@Validated @RequestBody Goods goods, BindingResult br, HttpServletRequest httpServletRequest){
        ApiResult apiResult = new ApiResult();
        if(br.hasErrors()){
            apiResult.isFalse().setMessage(br.getFieldErrors());
            return apiResult;
        }
        return goodsService.sava(goods,httpServletRequest);
    }
}
