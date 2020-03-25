package cn.qiang.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//@EnableEurekaClient
@EnableDiscoveryClient //该注解用于使用 zookeeper和consul为注册中心时使用
public class ApplicationOrder {
    public static void main(String[] args) {

        SpringApplication.run(ApplicationOrder.class, args);
    }
}
