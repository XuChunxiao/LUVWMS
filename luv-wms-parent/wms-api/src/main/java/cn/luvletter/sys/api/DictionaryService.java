package cn.luvletter.sys.api;

import cn.luvletter.bean.ApiResult;
import cn.luvletter.main.model.Dictionary;

import javax.servlet.http.HttpServletRequest;
/**
 * @author Zephyr
 * @Description:
 * @Date 2018/4/21
 */
public interface DictionaryService {
    ApiResult getDictionary(HttpServletRequest httpServletRequest);

    ApiResult update(Dictionary dictionary, HttpServletRequest httpServletRequest);

    ApiResult delById(String id, HttpServletRequest httpServletRequest);

    ApiResult sava(Dictionary dictionary, HttpServletRequest httpServletRequest);
}
