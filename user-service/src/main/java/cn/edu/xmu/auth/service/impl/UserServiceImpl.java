package cn.edu.xmu.auth.service.impl;

import cn.edu.xmu.auth.entity.*;
import cn.edu.xmu.auth.mapper.UserMapper;
import cn.edu.xmu.auth.service.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author summer
 * @since 2021-02-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPrivilegeService privilegeService;

    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private IRolePrivilegeService rolePrivilegeService;


    @Override
    public User findUserByUserName(String username) {
        return this.getOne(
                new QueryWrapper<User>()
                        .lambda().eq(User::getUsername,username));
    }

    @Override
    public Role findRoleByUserName(String username) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserRole::getUserId,findUserIdByUserName(username));
        Long roleId = userRoleService.getOne(queryWrapper).getRoleId();
        return roleService.getById(roleId);
    }

    @Override
    public List<String> findPrivilegesByUserName(String username) {

        QueryWrapper<RolePrivilege> queryWrapper = new QueryWrapper<>();

        queryWrapper.lambda().eq(RolePrivilege::getRoleId,findRoleByUserName(username).getId());

        List<RolePrivilege> list
                = rolePrivilegeService.list(queryWrapper);

        List<Long> privilegeIds
                = list.stream().map(RolePrivilege::getPrivilegeId).collect(Collectors.toList());

        return privilegeService.list(
                new LambdaQueryWrapper<Privilege>().in(Privilege::getId,privilegeIds)
        ).stream().map(Privilege::getPrivilegeName).collect(Collectors.toList());
    }

    private Long findUserIdByUserName(String username){
        User user =this.findUserByUserName(username);
        if(ObjectUtils.isNotEmpty(user)){
            return user.getId();
        }
        return null;
    }
}
