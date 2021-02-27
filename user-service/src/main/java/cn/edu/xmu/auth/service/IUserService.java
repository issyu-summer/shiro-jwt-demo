package cn.edu.xmu.auth.service;

import cn.edu.xmu.auth.entity.Privilege;
import cn.edu.xmu.auth.entity.Role;
import cn.edu.xmu.auth.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author summer
 * @since 2021-02-26
 */
public interface IUserService extends IService<User> {

    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return User
     */
    User findUserByUserName(String username);

    /**
     * 根据用户名查找用户角色
     * @param username 用户名
     * @return Role
     */
    Role findRoleByUserName(String username);

    /**
     * 根据用户名查找用户拥有的权限
     * @param username 用户名
     * @return List<Privilege>
     */
    List<String> findPrivilegesByUserName(String username);
}
