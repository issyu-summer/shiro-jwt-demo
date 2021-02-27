package cn.edu.xmu.auth.config;

import cn.edu.xmu.auth.entity.User;
import cn.edu.xmu.auth.service.IUserService;
import cn.edu.xmu.auth.utils.JwtToken;
import cn.edu.xmu.auth.utils.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author summer
 * @date 2021/2/26 19:28
 */
public class UserRealm extends AuthorizingRealm {


    /**
     * realm名称
     */
    private static final String REALM_NAME="shiro_realm";

    @Autowired
    private IUserService userService;


    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 校权，获取角色和权限
     *
     * @param token token
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection token) {
        return null;
    }

    /**
     * 用户认证
     *
     * @param token token
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        //supports()
        JwtToken jwtToken=(JwtToken)token;
        String tokenStr = jwtToken.getToken();
        String username = JwtUtil.getUsername(tokenStr);
        if(StringUtils.isBlank(username)){
            throw new AuthenticationException("token不合法");
        }
        User user = userService.findUserByUserName(username);

        if(!JwtUtil.verify(tokenStr,username,user.getPassword())){
            throw new AuthenticationException("token校验失败");
        }
        return new SimpleAuthenticationInfo(user,tokenStr,REALM_NAME);
    }
}
