package cn.luvletter.main.impl;

import cn.luvletter.bean.ApiResult;
import cn.luvletter.main.MainService;
import cn.luvletter.main.dao.DictionaryMapper;
import cn.luvletter.main.model.Dictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Zephyr
 * @Description:
 * @Date 2018/4/2
 */
@Service
public class MainServiceImpl implements MainService {
    @Autowired
    private DictionaryMapper dictionaryMapper;
    @Override
    public ApiResult getComboBox(String pid,String value) {
        ApiResult apiResult = new ApiResult();
        Dictionary dictionary = dictionaryMapper.selectByParaId(pid, value);
        if(dictionary == null){
            apiResult.isFalse();
            return apiResult;
        }
        apiResult.setData(dictionary);
        return apiResult;
    }
}
