package cn.qiang.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient //注册服务客户端开启
@EnableDiscoveryClient //发现服务客户端开启
public class ApplicationOrder {
    public static void main(String[] args) {

        SpringApplication.run(ApplicationOrder.class, args);
    }
}
