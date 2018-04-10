package cn.luvletter.main.dao;

import cn.luvletter.main.model.Dictionary;
import cn.luvletter.main.vo.SelectDSVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Zephyr
 * @Description:
 * @Date 2018/4/2
 */
@Repository
public interface DictionaryMapper {

    List<Dictionary> selectByParaId(@Param("pid") String pid, @Param("value") String value);

    List<SelectDSVo> selectByPid(@Param("pid") String pid);
}
