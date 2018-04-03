package cn.luvletter.main;

import cn.luvletter.bean.ApiResult;

/**
 * @author Zephyr
 * @Description:
 * @Date 2018/4/2
 */
public interface MainService {
    /**
     * @Description: 列表字段翻译
     * @Date: 15:07 2018/4/2
     */
    ApiResult getComboBox(String pid, String value);
}
