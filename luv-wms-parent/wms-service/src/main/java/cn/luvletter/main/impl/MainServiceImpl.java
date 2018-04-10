package cn.luvletter.main.impl;

import cn.luvletter.bean.ApiResult;
import cn.luvletter.main.MainService;
import cn.luvletter.main.dao.DictionaryMapper;
import cn.luvletter.main.model.Dictionary;
import cn.luvletter.main.vo.SelectDSVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        List<Dictionary> dictionaries = dictionaryMapper.selectByParaId(pid, value);
        if(dictionaries == null){
            apiResult.isFalse();
            return apiResult;
        }
        apiResult.setData(dictionaries.get(0));
        return apiResult;
    }

    @Override
    public ApiResult getSelectDS(String pid) {
        ApiResult apiResult = new ApiResult();
        List<SelectDSVo> SelectDSVos = dictionaryMapper.selectByPid(pid);
        if(SelectDSVos == null){
            apiResult.isFalse();
            return apiResult;
        }
        apiResult.setData(SelectDSVos);
        return apiResult;
    }
}
