package cn.luvletter.main.dao;

import cn.luvletter.main.model.Dictionary;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Zephyr
 * @Description:
 * @Date 2018/4/2
 */
@Repository
public interface DictionaryMapper {

    Dictionary selectByParaId(@Param("pid") String pid, @Param("value") String value);
}
