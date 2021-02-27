package cn.edu.xmu.auth.service.impl;

import cn.edu.xmu.auth.entity.Role;
import cn.edu.xmu.auth.mapper.RoleMapper;
import cn.edu.xmu.auth.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author summer
 * @since 2021-02-26
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
