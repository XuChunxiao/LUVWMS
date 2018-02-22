package cn.luvletter.sys.dao;

import cn.luvletter.sys.model.OprtRole;
import cn.luvletter.sys.model.OprtRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OprtRoleMapper {
    int countByExample(OprtRoleExample example);

    int deleteByExample(OprtRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OprtRole record);

    int insertSelective(OprtRole record);

    List<OprtRole> selectByExample(OprtRoleExample example);

    OprtRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OprtRole record, @Param("example") OprtRoleExample example);

    int updateByExample(@Param("record") OprtRole record, @Param("example") OprtRoleExample example);

    int updateByPrimaryKeySelective(OprtRole record);

    int updateByPrimaryKey(OprtRole record);
}