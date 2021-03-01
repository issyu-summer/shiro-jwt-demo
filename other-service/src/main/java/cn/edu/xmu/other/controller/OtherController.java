package cn.edu.xmu.other.controller;

import cn.edu.xmu.core.utils.Response;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


/**
 * @author summer
 * @date 2021/2/27 10:40
 */
@RestController
@RequestMapping
public class OtherController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/admin/login")
    public Object test(){

        ServiceInstance serviceInstance = loadBalancerClient.choose("provider");
        String path
                = String.format("http://%s:%s/user/login",serviceInstance.getHost(),serviceInstance.getPort());
        Map<String,String> body = new HashMap<>();
        body.put("username","admin");
        body.put("password","admin");
        Object result
                = restTemplate.postForObject(path,new UserVo().setUsername("admin").setPassword("admin"),Response.class);
        return result;
    }
    @GetMapping("/test")
    public Object other(){

        return "test-success";
//        ServiceInstance serviceInstance = loadBalancerClient.choose("provider");
//        String path
//                = String.format("http://%s:%s/user/test",serviceInstance.getHost(),serviceInstance.getPort());
//        Object result=restTemplate.getForObject(path,Response.class);
//        return result;
    }

    @Accessors(chain = true)
    @Data
    public static class UserVo{
        private String username;
        private String password;
    }

}
