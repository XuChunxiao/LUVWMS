package cn.luvletter.in;

import cn.luvletter.bean.ApiResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest; /**
 * @author Zephyr Ji
 * @ Description: TODO
 * @ Date 2018/3/29
 */
public interface InStockService {
    /**
     * @Description: 获取入库单列表
     * @Date: 11:14 2018/3/29
     */
    ApiResult getInStock(HttpServletRequest httpServletRequest);
    /**
     * @Description: 根据id获取入库单明细
     * @Date: 11:14 2018/3/29
     */
    ApiResult getInDtl(String id, HttpServletRequest httpServletRequest);

    /**
     * @Description: 导入入库单
     * @Date: 14:06 2018/4/3
     */
    ApiResult uploadInStock(MultipartFile file, HttpServletRequest httpServletRequest);
}
