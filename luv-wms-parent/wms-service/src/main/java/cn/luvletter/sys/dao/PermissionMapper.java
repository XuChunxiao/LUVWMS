package cn.luvletter.sys.dao;

import cn.luvletter.sys.model.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Zephyr Ji
 * @ Description: TODO
 * @ Date 2018/2/10
 */
public interface PermissionMapper {

    List<Permission> findAll();

    List<Permission> findByOperatorNo(@Param("no") String no);

}
