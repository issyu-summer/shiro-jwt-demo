package cn.edu.xmu.gateway;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author summer
 * @date 2021/3/1 17:30
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GateWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class);
    }

    /**
     * 注入json
     * @return objectMapper
     */
    @Bean
    ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    /**
     * 注入restTemplate
     * @return restTemplate
     */
    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
