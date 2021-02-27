package cn.edu.xmu.auth.controller;


import cn.edu.xmu.auth.entity.User;
import cn.edu.xmu.auth.entity.vo.UserRetVo;
import cn.edu.xmu.auth.entity.vo.UserVo;
import cn.edu.xmu.auth.service.IUserService;
import cn.edu.xmu.auth.utils.JwtUtil;
import cn.edu.xmu.auth.utils.Md5Util;
import cn.edu.xmu.core.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author summer
 * @since 2021-02-26
 */
@RestController
@RequestMapping(value = "/user",produces = "application/json;charset=UTF-8")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public Object login(@RequestBody UserVo userVo, HttpServletRequest request){

        String username = userVo.getUsername();
        String password = Md5Util.encrypt(username,userVo.getUsername());
        User user = userService.findUserByUserName(username);
        if(user==null||!user.getPassword().equals(password)){
             return new Response<>().setCode(999).setMsg("用户名或密码错误");
        }
        UserRetVo userRetVo = new UserRetVo(username, JwtUtil.sign(username,password));
        return new Response<>()
                .setCode(0)
                .setData(userRetVo)
                .setMsg("成功");
    }

    @GetMapping("/test")
    public Object test(HttpServletRequest request){
        if(request.getHeader("Authorization")==null){
            return new Response<>().setCode(999).setMsg("token不存在");
        }
        return new Response<>().setCode(201).setMsg("创建成功");
    }
}

