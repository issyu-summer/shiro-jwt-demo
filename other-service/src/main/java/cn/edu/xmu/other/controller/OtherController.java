package cn.edu.xmu.other.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author summer
 * @date 2021/2/27 10:40
 */
@RestController
@RequestMapping
public class OtherController {

    @GetMapping("/other")
    public String test(){
        return "other will be filtered";
    }
}
