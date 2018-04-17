package cn.luvletter.sys.dao;

import cn.luvletter.sys.model.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Zephyr
 * @Description:
 * @Date 2018/4/17
 */
@Repository
public interface RoleMapper {
    List<Role> findAll();
}
