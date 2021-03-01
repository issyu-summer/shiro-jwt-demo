package cn.edu.xmu.other;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author summer
 * @date 2021/2/27 10:39
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OtherApplication {
    public static void main(String[] args) {
        SpringApplication.run(OtherApplication.class);
    }
}
