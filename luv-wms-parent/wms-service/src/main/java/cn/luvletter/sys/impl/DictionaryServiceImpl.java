package cn.luvletter.sys.impl;

import cn.luvletter.base.impl.StorageLocationServiceImpl;
import cn.luvletter.bean.ApiResult;
import cn.luvletter.main.dao.DictionaryMapper;
import cn.luvletter.main.model.Dictionary;
import cn.luvletter.main.vo.SelectDSVo;
import cn.luvletter.sys.api.DictionaryService;
import cn.luvletter.util.DateUtil;
import cn.luvletter.util.WMSUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zephyr
 * @Description: 数据字典
 * @Date 2018/4/21
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {
    private final Logger logger = LoggerFactory.getLogger(StorageLocationServiceImpl.class);
    @Autowired
    private DictionaryMapper dictionaryMapper;
    @Autowired
    private WMSUtil wmsUtil;

    @Override
    public ApiResult getDictionary(HttpServletRequest httpServletRequest) {
        String pid = httpServletRequest.getParameter("pid");
        ApiResult apiResult = new ApiResult();
        if(StringUtils.isEmpty(pid)){
            return apiResult;
        }
        List<Dictionary> dictionarys = dictionaryMapper.selectDictionaryByPid(pid);
        apiResult.setData(dictionarys);
        return apiResult;
    }

    @Override
    public ApiResult update(Dictionary dictionary, HttpServletRequest httpServletRequest) {
        final ApiResult apiResult = new ApiResult();
        Long id = dictionary.getId();
        if(id == null || id == 0L){
            apiResult.isFalse().setMessage("id不能为空");
            return apiResult;

        }
        dictionary.setGmtModified(DateUtil.now());
        dictionaryMapper.updateByPrimaryKeySelective(dictionary);
        apiResult.setMessage("更新成功");
        return apiResult;
    }

    @Override
    public ApiResult delById(String id, HttpServletRequest httpServletRequest) {
        final ApiResult apiResult = new ApiResult();
        final String currUser = wmsUtil.getCurrUser(httpServletRequest);
        Long lId = Long.valueOf(id);
        Dictionary dictionary = dictionaryMapper.selectByPrimaryKey(lId);
        if(dictionary == null){
            apiResult.isFalse().setMessage("系统未找到此字典数据");
            return apiResult;
        }
        int i = dictionaryMapper.deleteByPrimaryKey(lId);
        if(i == 1){
            logger.info("删除字典：id："+id+"用户："+currUser);
            apiResult.setMessage("删除成功");
        }else {
            apiResult.isFalse().setMessage("删除失败");
        }
        return apiResult;
    }

    @Override
    public ApiResult sava(Dictionary dictionary, HttpServletRequest httpServletRequest) {
        final ApiResult apiResult = new ApiResult();
        final String currUser = wmsUtil.getCurrUser(httpServletRequest);
        dictionary.setGmtCreate(DateUtil.now());
        int i = dictionaryMapper.insertSelective(dictionary);
        if(i == 1){
            logger.info("新增字典："+dictionary.toString()+"用户："+currUser);
            apiResult.setMessage("新增成功");
        }else {
            apiResult.isFalse().setMessage("新增失败");
        }
        return apiResult;
    }
}
