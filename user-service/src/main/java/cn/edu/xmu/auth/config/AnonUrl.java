package cn.edu.xmu.auth.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author summer
 * @date 2021/2/26 20:29
 */
public enum AnonUrl {
    LOGIN_URL("/user/login");
    private String url;

    AnonUrl(String s) {
        this.url=s;
    }

    public void setUrl(String url){
        this.url=url;
    }

    public String getUrl(){
        return url;
    }

    public static List<String> getAnonUrl(){
        List<String> anonUrl=new ArrayList<>();
        for (AnonUrl a : Arrays.asList(values())) {
            anonUrl.add(a.getUrl());
        }
        return anonUrl;
    }
}
