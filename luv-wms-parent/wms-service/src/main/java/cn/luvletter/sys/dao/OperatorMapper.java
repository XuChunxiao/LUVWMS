package cn.luvletter.sys.dao;

import cn.luvletter.sys.model.Operator;
import cn.luvletter.sys.model.OperatorExample;
import java.util.List;

import cn.luvletter.sys.vo.OperatorVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatorMapper {
    int countByExample(OperatorExample example);

    int deleteByExample(OperatorExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Operator record);

    int insertSelective(Operator record);

    List<Operator> selectByExample(OperatorExample example);

    Operator selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Operator record, @Param("example") OperatorExample example);

    int updateByExample(@Param("record") Operator record, @Param("example") OperatorExample example);

    int updateByPrimaryKeySelective(Operator record);

    int updateByPrimaryKey(Operator record);

    List<OperatorVo> selectByExampleAndJoinRole(OperatorExample operatorExample);

    List<String> selectByNo(String no);
}